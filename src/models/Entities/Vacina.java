package models.Entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;




public class Vacina{
 
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    private String vacName;
    private String numeroDoses;
    private Integer idadeRecomendada;
    private Date intervaloDoses;

    public Vacina() {
    }

    public Vacina(String vacName, String numeroDoses, Integer idadeRecomendada) {
        this.vacName = vacName;
        this.numeroDoses = numeroDoses;
        this.idadeRecomendada = idadeRecomendada;
    }

    public Vacina(String vacName, String numeroDoses, Integer idadeRecomendada, Date intervaloDoses) {
        this.vacName = vacName;
        this.numeroDoses = numeroDoses;
        this.idadeRecomendada = idadeRecomendada;
        this.intervaloDoses = intervaloDoses;
    }

    public String getVacName() {
        return vacName;
    }

    public void setVacName(String vacName) {
        this.vacName = vacName;
    }

    public String getNumeroDoses() {
        return numeroDoses;
    }

    public void setNumeroDoses(String numeroDoses) {
        this.numeroDoses = numeroDoses;
    }

    public Integer getIdadeRecomendada() {
        return idadeRecomendada;
    }

    public void setIdadeRecomendada(Integer idadeRecomendada) {
        this.idadeRecomendada = idadeRecomendada;
    }

    public Date getIntervaloDoses() {
        return intervaloDoses;
    }

    public void setIntervaloDoses(Date intervaloDoses) {
        this.intervaloDoses = intervaloDoses;
    }

    @Override
    public String toString() {
        return "Vacina " + vacName + ", numero de Doses = " + numeroDoses + ", idade Recomendada = " + idadeRecomendada;
    }
    
    
    
    
    public List<Vacina> getVac(User user){
        
        List<Vacina> list = new ArrayList<>();
        
        List<Vacina> vacList = new ArrayList<>();
        
        //String path = ("C:\\Users\\limeh\\Documents\\Projetos\\NetBeans\\JavaTde\\JavaApplication18\\src\\Dados\\Vacina.txt");
        String path = ("src\\Dados\\Vacina.txt");

        try ( BufferedReader br = new BufferedReader(new FileReader(path))) {

            String itemCsv = br.readLine();
            
            while (itemCsv != null) {

                String[] fields = itemCsv.split(",");
                
                String vacName = null;
                
                String doses = null;
                
                int idadeR = 0;
                
                for (int i = 0; i < fields.length; i++){
                    if(i == 0){
                        vacName = fields[i];
                    }
                    if(i == 1){
                        doses = fields[i];;
                    }
                    if(i == 2){
                        idadeR = Integer.parseInt(fields[i]);
                }
                
                
            }
                list.add(new Vacina(vacName,doses,idadeR));
                
                
                itemCsv = br.readLine();
            }
        
        if(user.getIdade() <= 9){
            vacList = list.stream()
                    .filter(p -> p.getIdadeRecomendada() < 9)
                    .collect(Collectors.toList());
        } 
        else{
            vacList = list.stream()
                    .collect(Collectors.toList());
        }
           
        
        
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        vacList.remove(7);
        
        return vacList;
    }
    
    public String listVac(List<Vacina> list){
        
        String vacinas = "Vacinas que deveria ser tomadas: " + "\n";
        for(Vacina vacina : list){
            vacinas += vacina.toString() + "\n";
        }
        
        return vacinas;
    }
       
}
    
    

