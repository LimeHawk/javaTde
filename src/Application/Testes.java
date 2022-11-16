package Application;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import models.Entities.BotaoEmergencia;
import models.Entities.Fitness;
import models.Entities.Medicamento;
import models.Entities.Rotina;
import models.Entities.User;
import models.Entities.Vacina;

public class Testes {

    public static void main(String[] args) {

        User user = new User();
        Vacina vac = new Vacina();
        Fitness fit = new Fitness();
        BotaoEmergencia botaoEmergencia = new BotaoEmergencia();
        Medicamento med = new Medicamento();
        Rotina rot = new Rotina();

        user = new User("Kaike", "1234", "Lime", 18, 70.0, 180, "Ectomorfo", "Masculino");

        //JOptionPane.showMessageDialog(null, med.listMed(med.getListaSus()));
        //JOptionPane.showMessageDialog(null, vac.listVac(vac.getVac(user)));
        //JOptionPane.showMessageDialog(null, fit.bibliotecaNutricional(user));
        //JOptionPane.showMessageDialog(null, fit.bibliotecaNutricional(user));
        //JOptionPane.showMessageDialog(null, botaoEmergencia.listBotao(botaoEmergencia.getList()));
        //vac.listVac(vac.getVac(user));
        //med.cadastramentoMed();
        //med.cadastramentoMed();
        //JOptionPane.showMessageDialog(null, med.listaMedicamentos());

        //rot.cadastramentoRotina();
        //JOptionPane.showMessageDialog(null, rot.listaRotina());
        
    }

}
