package models.Entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BotaoEmergencia {

    private String nome;
    private String numero;

    public BotaoEmergencia() {
    }

    public BotaoEmergencia(String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return nome + "," + numero;
    }

    public List<BotaoEmergencia> getList() {
        List<BotaoEmergencia> list = new ArrayList<>();

        String path = ("C:\\Users\\limeh\\Documents\\Projetos\\NetBeans\\JavaTde\\JavaApplication18\\src\\Dados\\botaoEmergencia.txt");

        try ( BufferedReader br = new BufferedReader(new FileReader(path))) {

            String itemCsv = br.readLine();

            while (itemCsv != null) {

                String[] fields = itemCsv.split(",");

                String name = null;

                String numero = null;

                for (int i = 0; i < fields.length; i++) {
                    if (i == 0) {
                        name = fields[i];
                    }
                    if (i == 1) {
                        numero = fields[i];;
                    }
                }

                list.add(new BotaoEmergencia(name, numero));

                itemCsv = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public String listBotao(List<BotaoEmergencia> list) {

        list.remove(19);

        String botaoEmergencias = "Lista de numeros: " + "\n";
        for (BotaoEmergencia botaoEmergencia : list) {
            botaoEmergencias += botaoEmergencia.toString() + "\n";

        }

        return botaoEmergencias;
    }

}
