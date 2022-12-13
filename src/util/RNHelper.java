package util;

import controller.Agenda;
import model.Telefone;

import java.util.List;

public class RNHelper {

    public static void trataTelefone(Agenda agenda, List<Telefone> telefones){
        for (int j = 0; j <telefones.size() ; j++) {
            Telefone telefone = telefones.get(j);
            for (int k = 0; k < agenda.getContatos().size(); k++) {
                if (agenda.getContatos().get(k).getTelefones().get(k).equals(telefone)){
                    System.out.println("DDD e Numero já cadastrado em um telefone, tente outro.");
                    telefones.remove(telefone);
                    ConsoleUIHelper.drawLine(120);
                }
            }
        }
    }
}
