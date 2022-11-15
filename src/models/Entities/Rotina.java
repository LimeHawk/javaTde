package models.Entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Rotina {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private String nome;
    private String data;

    public Rotina() {
    }

    public Rotina(String nome, String data) {
        this.nome = nome;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return nome + "," + data;
    }

    public void cadastramentoRotina() {
        List<Rotina> list = new ArrayList<>();
        User user = new User();
        String nome = JOptionPane.showInputDialog("Digite a especialidade do Medico ");
        Date date = null;
        try {
            date = sdf.parse(JOptionPane.showInputDialog("Digite a data marcada para o medico: (dd/MM/yyyy) "));
        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        String data = sdf.format(date);

        list.add(new Rotina(nome, data));
        String path = ("C:\\Users\\limeh\\Documents\\Projetos\\NetBeans\\JavaTde\\JavaApplication18\\src\\Dados\\Rotina.txt");

        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            String line = null;

            for (Rotina rot : list) {
                line = rot.toString();
            }

            bw.write(line);
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public String listaRotina() {

        List<Rotina> list = new ArrayList<>();

        String rotina = "";

        String path = ("C:\\Users\\limeh\\Documents\\Projetos\\NetBeans\\JavaTde\\JavaApplication18\\src\\Dados\\Rotina.txt");

        try ( BufferedReader br = new BufferedReader(new FileReader(path))) {

            String itemCsv = br.readLine();

            if (itemCsv == null) {
                itemCsv = br.readLine();
            }

            while (itemCsv != null) {

                String[] fields = itemCsv.split(",");

                String nome = "";
                String data = "";

                for (int i = 0; i < fields.length; i++) {
                    if (i == 0) {
                        nome = fields[0];
                    }
                    if (i == 1) {
                        data = fields[1];
                    }
                }

                list.add(new Rotina(nome, data));
                itemCsv = br.readLine();

            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        rotina = "Seus Apontamentos: " + "\n";
        for (Rotina rot : list) {
            rotina += rot.toString() + "\n";

        }

        return rotina;
    }

    public Date adicionaHoras(Date data, int horas) {
        Calendar calendario = Calendar.getInstance(); // Cria um objeto de data
        calendario.setTime(data); // Inicia o objeto com a data original
        calendario.add(Calendar.HOUR_OF_DAY, horas); // Adiciona as horas necessárias

        return calendario.getTime(); // Retorna a hora formatada
    }

}
