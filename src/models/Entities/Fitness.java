package models.Entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Fitness {

    private Double calGasta;
    private Double consAgua;

    private User user;

    public Fitness() {
    }

    public Fitness(Double calGasta, Double consAgua, User user) {
        this.calGasta = calGasta;
        this.consAgua = consAgua;
        this.user = user;
    }

    public Double getCalGasta() {
        return calGasta;
    }

    public void setCalGasta(Double calGasta) {
        this.calGasta = calGasta;
    }

    public Double getConsAgua() {
        return consAgua;
    }

    public void setConsAgua(Double consAgua) {
        this.consAgua = consAgua;
    }

    public User getUser() {
        return user;
    }

    public String ConsumoAgua(User user) {
        return "Voce deveria tomar " + user.getPeso() * 0.05 + " Litros de Água";

    }

    public Double taxaBasal(User user) {

        switch (user.getSexo()) {

            case "Masculino":
                return (66 + (13.7 * user.getPeso()) + (5 * user.getAltura()) - (6.8 * user.getIdade()));

            case "Feminino":
                return (655 + (9.6 * user.getPeso()) + (1.8 * user.getAltura()) - (4.7 * user.getIdade()));
        }

        return null;
    }

    public String consumoDiario(User user) {
        Fitness fit = new Fitness();
        Double consDiario = 50 * user.getPeso();

        String texto = null;
        switch (user.getBiotipo()) {

            case "Ectomorfo" ->
                texto = "O seu gasto calórico diário (Ectomorfo) é de: " + consDiario + " Cal \n " + "A proporção é de: \n\n" + " 20% de Proteína: " + (0.2 * consDiario) + " Cal \n 20% de Carboidrato: " + (0.2 * consDiario) + " Cal \n 60% de Gorduras (HDL): " + (0.6 * consDiario) + " Cal \n\n A sua TMB diária é de: " + fit.taxaBasal(user) + " Cal" + "\n\n --✅ Dicas: \n-Treino em menor tempo; \n-Exercícíos com diversos agrupamentos musculares; \n-Hidratar-se; \n-Descanso é fundamental para não catabolizar; \n-Diminua a imperatividade na sua rotina.";

            case "Mesomorfo" ->
                texto = "O seu gasto calórico diário (Mesomorfo) é de: " + consDiario + " Cal \n " + "A proporção é de: \n\n" + " 20% de Proteína: " + (0.2 * consDiario) + " Cal \n 60% de Carboidrato: " + (0.6 * consDiario) + " Cal \n 20% de Gorduras (HDL): " + (0.2 * consDiario) + " Cal \n\n A sua TMB diária é de: " + fit.taxaBasal(user) + " Cal" + "\n\n --✅ Dicas: \n-Exercícíos balanceados de progressão de carga e resistência; \n-Drop-sets, bisets, rest-pause ajudam a quebrar a homeostase; \n-Hidratação; \n-Cardios moderados em cutting.";

            case "Endomorfo" ->
                texto = "O seu gasto calórico diário (Endomorfo) é de: " + consDiario + " Cal \n " + "A proporção é de: \n\n" + " 35% de Proteína: " + (0.35 * consDiario) + " Cal \n 25% de Carboidrato: " + (0.25 * consDiario) + " Cal \n 40% de Gorduras (HDL): " + (0.4 * consDiario) + " Cal \n\n A sua TMB diária é de: " + fit.taxaBasal(user) + " Cal" + "\n\n --✅ Dicas: \n-Cafeína, 25 a 35 mg x 3/4 por dia; \n-Não exceder o teto calórico; \n-Exercícíos com foco em progressão de carga; \n-Hidratação; \n-Cardios em jejum.";

            default -> {
                JOptionPane.showMessageDialog(null, "Voltando ao menu...");

            }

        }
        return texto;
    }

    public String bibliotecaNutricional(User user) {

        String texto = " ";

        String path = ("C:\\Users\\limeh\\Documents\\Projetos\\NetBeans\\JavaTde\\JavaApplication18\\src\\Dados\\bibliotecaNutricional.txt");

        try ( BufferedReader br = new BufferedReader(new FileReader(path))) {

            String itemCsv = br.readLine();

            if (itemCsv == null) {
                itemCsv = br.readLine();
            }

            while (itemCsv != null) {

                String[] fields = itemCsv.split(",");

                String biotipo = fields[0];

                if (user.getBiotipo().equals(biotipo)) {
                    switch (biotipo) {

                        case "Ectomorfo":
                            for (int i = 1; i < fields.length; i++) {
                                texto += "\n" + fields[i];
                            }
                            return texto;

                        case "Endomorfo":
                            for (int i = 1; i < fields.length; i++) {
                                texto += "\n" + fields[i];
                            }
                            return texto;

                        case "Mesomorfo":
                            for (int i = 1; i < fields.length; i++) {
                                texto += "\n" + fields[i];
                            }
                            return texto;

                    }

                } else {
                    itemCsv = br.readLine();
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

        return null;
    }

    public String metaCalorica(User user) {
        Fitness fit = new Fitness();

        String texto = null;
        Double consDiario = 50 * user.getPeso();

        Double caloriasGasta = Double.parseDouble(JOptionPane.showInputDialog("Adicione suas calorias gastas ao dia (Kg): \n "));

        Double caloriasGastaProteinas;
        Double caloriasGastaCarboidratos;
        Double caloriasGastaGorduras;

        switch (user.getBiotipo()) {

            case "Ectomorfo":
                caloriasGastaProteinas = (caloriasGasta - consDiario - fit.taxaBasal(user)) * 0.2;
                caloriasGastaCarboidratos = (caloriasGasta - consDiario - fit.taxaBasal(user)) * 0.2;
                caloriasGastaGorduras = (caloriasGasta - consDiario - fit.taxaBasal(user)) * 0.6;

                texto = "O seu consumo diario com a TMB foi de: " + "\nProteina: " + String.format("%.2f", caloriasGastaProteinas) + " Cal \nCarboidratos: " + String.format("%.2f", caloriasGastaCarboidratos) + " Cal \n Gordura: " + String.format("%.2f", caloriasGastaGorduras) + "Cal\n\nO seu gasto calórico ideal diário (Ectomorfo) é de: " + consDiario + " Cal \n " + "A proporção é de: \n\n" + " 20% de Proteína: " + (0.2 * consDiario) + " Cal \n 20% de Carboidrato: " + (0.2 * consDiario) + " Cal \n 60% de Gorduras (HDL): " + (0.6 * consDiario) + " Cal";
                break;

            case "Mesomorfo":
                caloriasGastaProteinas = (caloriasGasta - consDiario - fit.taxaBasal(user)) * 0.2;
                caloriasGastaCarboidratos = (caloriasGasta - consDiario - fit.taxaBasal(user)) * 0.2;
                caloriasGastaGorduras = (caloriasGasta - consDiario - fit.taxaBasal(user)) * 0.6;

                texto = "O seu consumo diario com a TMB foi de: " + "\nProteina: " + String.format("%.2f", caloriasGastaProteinas) + " Cal \nCarboidratos: " + String.format("%.2f", caloriasGastaCarboidratos) + " Cal \n Gordura: " + String.format("%.2f", caloriasGastaGorduras) + "Cal\n\nO seu gasto calórico ideal diário (Mesomorfo) é de: " + consDiario + " Cal \n " + "A proporção é de: \n\n" + " 20% de Proteína: " + (0.2 * consDiario) + " Cal \n 60% de Carboidrato: " + (0.6 * consDiario) + " Cal \n 20% de Gorduras (HDL): " + (0.2 * consDiario) + " Cal";
                break;

            case "Endomorfo":
                caloriasGastaProteinas = (caloriasGasta - consDiario - fit.taxaBasal(user)) * 0.35;
                caloriasGastaCarboidratos = (caloriasGasta - consDiario - fit.taxaBasal(user)) * 0.25;
                caloriasGastaGorduras = (caloriasGasta - consDiario - fit.taxaBasal(user)) * 0.40;

                texto = "O seu consumo diario com a TMB foi de: " + "\nProteina: " + String.format("%.2f", caloriasGastaProteinas) + " Cal \nCarboidratos: " + String.format("%.2f", caloriasGastaCarboidratos) + " Cal \n Gordura: " + String.format("%.2f", caloriasGastaGorduras) + "Cal\n\nO seu gasto calórico ideal diário (Endomorfo) é de: " + consDiario + " Cal \n " + "A proporção é de: \n\n" + " 35% de Proteína: " + (0.35 * consDiario) + " Cal \n 25% de Carboidrato: " + (0.25 * consDiario) + " Cal \n 40% de Gorduras (HDL): " + (0.4 * consDiario) + " Cal";
                break;
        }

        return texto;

    }

}
