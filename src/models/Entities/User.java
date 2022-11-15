package models.Entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

public class User {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    private String nome;
    private String senha;
    private String login;
    private Integer idade;
    private Double peso;
    private Integer altura;
    private Double taxaBasal;
    private String biotipo;
    private String sexo;

    public User() {
    }

    public User(String nome, String senha, String login, Integer idade, Double peso, Integer altura, String biotipo, String sexo) {
        this.nome = nome;
        this.senha = senha;
        this.login = login;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.biotipo = biotipo;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Double getTaxaBasal() {
        return taxaBasal;
    }

    public void setTaxaBasal(Double taxaBasal) {
        this.taxaBasal = taxaBasal;
    }

    public String getBiotipo() {
        return biotipo;
    }

    public void setBiotipo(String biotipo) {
        this.biotipo = biotipo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return nome + "," + senha + "," + login + "," + idade + "," + peso + "," + altura + "," + biotipo + "," + sexo;
    }

    public void cadastro() {

        String cad = JOptionPane.showInputDialog("Cadastre seu login: ");

        String senha = JOptionPane.showInputDialog("Cadastre sua senha: ");

        String nome = JOptionPane.showInputDialog("Cadastre seu nome: ");

        int idade = Integer.parseInt(JOptionPane.showInputDialog("Cadastre sua idade: "));

        int optSexo = Integer.parseInt(JOptionPane.showInputDialog("Selecione o seu sexo: \n 1-Masculino \n 2- Feminino \n"));

        String sexo = null;
        if (optSexo == 1) {
            sexo = "Masculino";
        } else if (optSexo == 2) {
            sexo = "Feminino";
        }

        double peso = Double.parseDouble(JOptionPane.showInputDialog("Digite o seu peso (kg): \n"));

        int altura = Integer.parseInt(JOptionPane.showInputDialog("Digite a sua altura (cm): \n"));

        int optBiotipo = Integer.parseInt(JOptionPane.showInputDialog("Selecione o seu tipo de biotipo: \n 1-Ectomorfo \n 2- Mesomorfo \n 3- Endomorfo\n"));

        String biotipo = null;

        if (optBiotipo == 1) {
            biotipo = "Ectomorfo";
        } else if (optBiotipo == 2) {
            biotipo = "Mesomorfo";
        } else if (optBiotipo == 3) {
            biotipo = "Endomorfo";
        }

        User user = new User();

        if (cad != null && senha != null && nome != null) {
            user = new User(nome, senha, cad, idade, peso, altura, biotipo, sexo);
        } else {
            throw new RuntimeException("Unexpected error! One of the variavles is null ");
        }

        String path = ("C:\\Users\\limeh\\Documents\\Projetos\\NetBeans\\JavaTde\\JavaApplication18\\src\\Dados\\Bd.txt");

        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            String line = user.getNome() + "," + user.getSenha() + "," + user.getLogin() + "," + user.getIdade() + "," + user.getPeso() + "," + user.getAltura() + "," + user.getBiotipo() + "," + user.getSexo();

            bw.write(line);
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public User login() {

        String cad = JOptionPane.showInputDialog("Login: ");
        String sen = JOptionPane.showInputDialog("Senha: ");

        String path = ("C:\\Users\\limeh\\Documents\\Projetos\\NetBeans\\JavaTde\\JavaApplication18\\src\\Dados\\Bd.txt");

        try ( BufferedReader br = new BufferedReader(new FileReader(path))) {

            String itemCsv = br.readLine();

            if (itemCsv == null) {
                itemCsv = br.readLine();
            }

            while (itemCsv != null) {

                String[] fields = itemCsv.split(",");

                String name = null;

                String senha = null;

                String login = null;

                Integer idade = null;

                Double peso = null;

                Integer altura = null;

                String biotipo = null;

                String sexo = null;

                for (int i = 0; i < fields.length; i++) {
                    if (i == 0) {
                        name = fields[i];
                    }
                    if (i == 1) {
                        senha = fields[i];;
                    }
                    if (i == 2) {
                        login = fields[i];
                    }
                    if (i == 3) {
                        idade = Integer.parseInt(fields[i]);
                    }
                    if (i == 4) {
                        peso = Double.parseDouble(fields[i]);
                    }
                    if (i == 5) {
                        altura = Integer.parseInt(fields[i]);
                    }
                    if (i == 6) {
                        biotipo = fields[i];
                    }
                    if (i == 7) {
                        sexo = fields[i];
                    }

                }

                if (cad.equals(login) && senha.equals(sen)) {
                    User usuario = new User(name, senha, cad, idade, peso, altura, biotipo, sexo);

                    return usuario;
                } else {
                    itemCsv = br.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    

}
