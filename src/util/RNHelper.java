package util;

import controller.Agenda;
import model.Contato;
import model.Endereco;
import model.Telefone;

import java.util.List;

public class RNHelper {

    public static void trataTelefone(Agenda agenda, List<Telefone> telefones){
        Telefone telefone = null;
        if (agenda.getContatos().size() == 0){
            for (int j = 0; j <telefones.size()-1 ; j++) {
                telefone = telefones.get(j);
                if (telefones.contains(telefone)){
                    System.out.println("DDD e Numero já cadastrado em um telefone, tente outro.");
                    telefones.remove(telefone);
                    ConsoleUIHelper.drawLine(120);
                }
            }
            return;
        }
        if (telefones.size() == 1){
            telefone = telefones.get(0);
            for (int i = 0; i < agenda.getContatos().size() ; i++) {
                if (agenda.getContatos().get(i).getTelefones().contains(telefone)){
                    System.out.println("DDD e Numero já cadastrado em um telefone, tente outro.");
                    telefones.remove(telefone);
                    ConsoleUIHelper.drawLine(120);
                }
            }
            return;
        }
        for (int j = 0; j <telefones.size() ; j++) {
            telefone = telefones.get(j);
            for (int k = 0; k < agenda.getContatos().size(); k++) {
                if (agenda.getContatos().get(k).getTelefones().get(k).equals(telefone)){
                    System.out.println("DDD e Numero já cadastrado em um telefone, tente outro.");
                    telefones.remove(telefone);
                    ConsoleUIHelper.drawLine(120);
                }
            }
        }
    }
    public static int trataContato(Agenda agenda, Contato contato){
        for (int i = 0; i < agenda.getContatos().size(); i++) {
            if (agenda.getContatos().get(i).equals(contato)) {
                System.out.println("Usuario já cadastrado, tente novamente. ");
                ConsoleUIHelper.drawLine(120);
                ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu");
                return 1;
            }
        }
        return 0;
    }
    public static int trataNomeCompleto(Agenda agenda, String nomeCompleto){
        for (int i = 0; i < agenda.getContatos().size() ; i++) {
            if (agenda.getContatos().get(i).getNomeCompleto().equals(nomeCompleto)){
                System.out.printf("Contato %s já está cadastrado. %n", nomeCompleto);
                return 1;
            }
        }
        return 0;
    }
    public static void trataEndereco(Agenda agenda, List<Endereco> enderecos){
        Endereco endereco = null;
        if (agenda.getContatos().size() == 0){
            for (int j = 0; j <enderecos.size() ; j++) {
                endereco = enderecos.get(j);
                if (enderecos.contains(endereco)){
                    System.out.println("Cep e numero já cadastrados em um endereço, tente novamente.");
                    enderecos.remove(endereco);
                    ConsoleUIHelper.drawLine(120);
                }
            }
            return;
        }
        if (enderecos.size() == 1){
            endereco = enderecos.get(0);
            for (int i = 0; i < agenda.getContatos().size() ; i++) {
                if (agenda.getContatos().get(i).getTelefones().contains(endereco)){
                    System.out.println("DDD e Numero já cadastrado em um telefone, tente outro.");
                    enderecos.remove(endereco);
                    ConsoleUIHelper.drawLine(120);
                }
            }
            return;
        }
        for (int i = 0; i <enderecos.size() ; i++) {
            endereco = enderecos.get(i);
            for (int j = 0; j < agenda.getContatos().size(); j++) {
                if (agenda.getContatos().get(j).getEnderecos().get(j).equals(endereco)){
                    System.out.println("Cep e numero já cadastrados em um endereço, tente novamente.");
                    enderecos.remove(endereco);
                    ConsoleUIHelper.drawLine(120);
                }
            }
        }
    }
}
