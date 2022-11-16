package Application;

import javax.swing.JOptionPane;
import models.Entities.BotaoEmergencia;
import models.Entities.Fitness;
import models.Entities.Medicamento;
import models.Entities.Rotina;
import models.Entities.User;
import models.Entities.Vacina;

public class Program {

    public static void main(String[] args) {
        int menu = 0;
        User user = new User();
        User usuario = null;
        Vacina vac = new Vacina();
        Fitness fit = new Fitness();
        Rotina rot = new Rotina();
        Medicamento med = new Medicamento();
        BotaoEmergencia bEmergencia = new BotaoEmergencia();

        while (menu == 0) {

            int option = Integer.parseInt(JOptionPane.showInputDialog(null, "__________[ OWNCARE 💊]__________\n\n "
                    + "Bem vindo ao aplicativo OwnCare\n\n"
                    + "Para fazer login digite 1\n"
                    + "Para se cadastrar digite 2 "));

            switch (option) {
                case 1:
                    usuario = user.login();
                    if (usuario.getNome() != null) {
                        menu++;
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro! Não foi realizado o login, retornando ao menu");
                        break;
                    }

                case 2:
                    user.cadastro();
                    JOptionPane.showMessageDialog(null, "Obrigado pelo cadastro, prosseguindo ao login!");
                    usuario = user.login();
                    if (usuario.getNome() != null) {
                        menu++;
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro! Não foi realizado o login, retornando ao menu");
                        break;
                    }

            }

        }

        while (menu == 1) {

            int option = Integer.parseInt(JOptionPane.showInputDialog("__________[ OWNCARE 💊]__________\n\n Usuário: " + usuario.getNome() + "\n\n 0- Intruções OwnCare \n 1- Vacina \n 2- Fitness \n 3- Medicamento \n 4- Rotina \n 5- ⚠Emergência️⚠\n\n --Digite outro número para sair--"));

            switch (option) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Intruções OwnCare: \n\n"
                            + "VACINA:\n"
                            + "-Lista todas as vacinas, com base no site do SUS, que uma criança, adolescente ou adulto, devem tomar.\n\n"
                            + "FITNESS:\n"
                            + "-Mostra o consumo diário de água de acordo com o seu peso;\n"
                            + "-Mostra uma biblioteca nutricional com base no seu tipo de biotipo (Ectomorfo, Mesomorfo, Endomorfo);\n"
                            + "-Prediz a quantidade de calorias você deve comer, incluindo a Taxa Metabólica Basal (TMB), que calcula o tanto de energia seu corpo consome em pleno funcionamento em vida. Além da manipulação percentual dos macronutrientes com base no biotipo;\n"
                            + "-Meta calórica apresenta uma meta diária de calorias que devem ser consumidas, com base no seu biotipo.\n\n"
                            + "MEDICAMENTO:\n"
                            + "-Cadastra o medicamento que o usuário desejar;\n"
                            + "-Lista os medicamentos cadastrados;\n"
                            + "-Abre o arquivo RENAME do SUS para checagem da disponibilidade do medicamento de forma gratuita pelo Governo;\n\n"
                            + "ROTINA:\n"
                            + "-Cadastra a rotina que o usuário desejar;\n"
                            + "-Lista as rotinas cadastradas;\n\n"
                            + "EMERGÊNCIA:\n"
                            + "-Lista todos os possíveis números de emergência mais utilizados");
                            break;
                
                case 1:
                    option = Integer.parseInt(JOptionPane.showInputDialog("__________[ OWNCARE 💊]__________ \n 1- Listar possivaies vacinas para serem tomadas"));
                    if (option == 1) {
                        JOptionPane.showMessageDialog(null, vac.listVac(vac.getVac(usuario)));
                    }
                    break;

                case 2:
                    while (option != 5) {
                        option = Integer.parseInt(JOptionPane.showInputDialog("__________[ OWNCARE 💊]__________ \n \n 1- Consumo de Água ideal por dia\n 2- Sua Biblioteca Nutricional \n 3- Predições baseados no seu consumo Diario \n 4- Meta Calorica \n 5- Retornar"));
                        if (option == 1) {
                            JOptionPane.showMessageDialog(null, fit.ConsumoAgua(usuario));
                        }
                        if (option == 2) {
                            JOptionPane.showMessageDialog(null, fit.bibliotecaNutricional(usuario));
                        }
                        if (option == 3) {
                            JOptionPane.showMessageDialog(null, fit.consumoDiario(usuario));
                        }
                        if (option == 4) {
                            JOptionPane.showMessageDialog(null, fit.metaCalorica(usuario));
                        }
                        if (option == 5) {
                            break;
                        }

                    }

                case 3:
                    while (option != 5) {

                        option = Integer.parseInt(JOptionPane.showInputDialog("__________[ OWNCARE 💊]__________  \n\n 1- Cadastrar medicamento\n 2- Listar seus medicamentos \n 3- Abrir pdf Rename \n 4- Retornar"));
                        if (option == 1) {
                            med.cadastramentoMed();
                        }
                        if (option == 2) {
                            JOptionPane.showMessageDialog(null, med.listaMedicamentos());

                        }
                        if (option == 3) {
                            med.abrirPdf();

                        }
                        if (option == 4) {
                            break;
                        }

                    }
                    break;

                case 4:
                    while (option != 3) {

                        option = Integer.parseInt(JOptionPane.showInputDialog("__________[ OWNCARE 💊]__________ \n\n 1- Cadastrar Apontamento\n 2- Listar seus apontamentos \n 3- Retornar"));
                        if (option == 1) {
                            rot.cadastramentoRotina();
                        }
                        if (option == 2) {
                            JOptionPane.showMessageDialog(null, rot.listaRotina());

                        }
                        if (option == 3) {
                            break;
                        }

                    }
                    break;
                
                case 5:
                    JOptionPane.showMessageDialog(null, bEmergencia.listBotao(bEmergencia.getList()));
                    break;

                default:
                    menu++;
                    break;

            }

        }

        JOptionPane.showMessageDialog(null, "Obrigado por utilizar nossos serviços :) ");

    }

}
