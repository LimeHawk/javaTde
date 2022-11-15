package models.Entities;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Medicamento {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private String name;
    private Integer quantidade;
    private String datas;

    public Medicamento() {
    }

    public Medicamento(String name, Integer quantidade) {
        this.name = name;
        this.quantidade = quantidade;
    }

    public Medicamento(String name, Integer quantidade, String datas) {
        this.name = name;
        this.quantidade = quantidade;
        this.datas = datas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return name + ", Quantidade = " + quantidade + ", Datas: " + datas;
    }

    public void cadastramentoMed() {
        Medicamento med = new Medicamento();
        String nome = JOptionPane.showInputDialog("Digite o nome do medicamento ");
        Integer quantidadeDoses = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de doses que você deve tomar do medicamento "));

        Integer intervalo = Integer.parseInt(JOptionPane.showInputDialog("Digite os intervalos que você deve tomar o medicamento (Em horas) "));

        String dates = "";

        Date date = new Date();

        List<Medicamento> list = new ArrayList<>();

        for (int i = 0; i < quantidadeDoses; i++) {

            if (i == 0) {
                dates += sdf.format(date) + ",";
            } else if (i == quantidadeDoses - 1) {
                date = med.adicionaHoras(date, intervalo);
                dates += sdf.format(date);
            } else {
                date = med.adicionaHoras(date, intervalo);
                dates += sdf.format(date) + ",";
            }

        }

        list.add(new Medicamento(nome, quantidadeDoses, dates));

        String path = ("C:\\Users\\limeh\\Documents\\Projetos\\NetBeans\\JavaTde\\JavaApplication18\\src\\Dados\\MedicamentoUsuario.txt");

        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            String line = null;

            for (Medicamento medica : list) {
                line = medica.getName() + "," + medica.getQuantidade() + "," + medica.getDatas();
            }

            bw.write(line);
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public String listaMedicamentos() {

        List<Medicamento> list = new ArrayList<>();

        String todosMed = "";

        String path = ("C:\\Users\\limeh\\Documents\\Projetos\\NetBeans\\JavaTde\\JavaApplication18\\src\\Dados\\MedicamentoUsuario.txt");

        try ( BufferedReader br = new BufferedReader(new FileReader(path))) {

            String itemCsv = br.readLine();

            if (itemCsv == null) {
                itemCsv = br.readLine();
            }

            while (itemCsv != null) {

                String[] fields = itemCsv.split(",");

                String nome = "";
                Integer qnt = 0;
                String datas = "";

                for (int i = 0; i < fields.length; i++) {
                    if (i == 0) {
                        nome = fields[0];
                    }
                    if (i == 1) {
                        qnt = Integer.parseInt(fields[1]);
                    }
                    if (i == 2) {
                        if (fields[2] != null) {
                            datas += " " + fields[2];
                        }
                    }
                    if (i == 3 && fields[3] != null) {

                        datas += " | " + fields[3];

                    }
                    if (i == 4 && fields[4] != null) {

                        datas += " | " + fields[4];

                    }
                    if (i == 5 && fields[5] != null) {
                        datas += " | " + fields[5];

                    }

                }

                list.add(new Medicamento(nome, qnt, datas));
                itemCsv = br.readLine();

            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        list.remove(0);
        todosMed = "Medicamentos: " + "\n";
        for (Medicamento medicamento : list) {
            todosMed += medicamento.toString() + "\n";

        }

        return todosMed;
    }

    public void abrirPdf() {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(new File("C:\\Users\\limeh\\Documents\\Projetos\\NetBeans\\JavaTde\\JavaApplication18\\src\\Dados\\relacao_medicamentos_rename_2020.pdf"));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Date adicionaHoras(Date data, int horas) {
        Calendar calendario = Calendar.getInstance(); // Cria um objeto de data
        calendario.setTime(data); // Inicia o objeto com a data original
        calendario.add(Calendar.HOUR_OF_DAY, horas); // Adiciona as horas necessárias

        return calendario.getTime(); // Retorna a hora formatada
    }

}
