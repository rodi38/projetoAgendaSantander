package util;

import model.Contato;
import model.Telefone;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class InputHelper {
    public static Contato getContatoPesquisado(List<Contato> lista){
        Contato contato = null;
        List<String> ids = new ArrayList<>();
        for (int i = 0; i <= lista.size(); i++) {
            if (i < lista.size()) {
                System.out.println("ID: " + (i + 1) + " " + lista.get(i) + "\n");
                ids.add("" + i);
                continue;
            }
            int id;
            try {
                id = ConsoleUIHelper.askNumberInt("Digite o ID do contato") - 1;
            } catch (InputMismatchException e){
                System.out.println("Informe apenas numeros inteiros.");
                i--;
                continue;
            }
            if (ids.contains("" + id)) {
                contato = lista.remove(id);
            } else {
                System.out.println("ID inexistente.");
            }
        }
        return contato;
    }
    public static Telefone getTelefoneContato(Contato contato){
        Telefone telefone = null;
        List<String> ids = new ArrayList<>();
        for (int i = 0; i <= contato.getTelefones().size() ; i++) {
            if (i < contato.getTelefones().size()){
                System.out.println("Telefone " + (i + 1) + "º: " + contato.getTelefones().get(i).getTelefoneCompleto());
                ids.add("" + i);
                continue;
            }
            int id;
            try {
                id = ConsoleUIHelper.askNumberInt("Digite o ID do Telefone: ") - 1;
            } catch (InputMismatchException e){
                System.out.println("Informe apenas numeros inteiros.");
                i--;
                continue;
            }
            if (ids.contains("" + id)) {
                telefone = contato.getTelefones().get(id);
            } else {
                System.out.println("ID inexistente.");
            }
        }
        return telefone;
    }
}
