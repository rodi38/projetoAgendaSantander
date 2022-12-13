package controller;

import model.Contato;
import model.Endereco;
import model.Telefone;
import util.ConsoleUIHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
implements Serializable 
 */
public class Agenda {
    private List<Contato> contatos;
    private List<Telefone> telefones;

    public Agenda() {
        this.contatos = new ArrayList<>();
    }

    public List<Contato> getContatos() {
        return this.contatos;
    }

    public void adicionar(Contato contato) {
        contatos.add(contato);


    }
    public void excluir(Contato contato){
        contatos.remove(contato);
    }

    public List<Contato> pesquisarNome(String nome) {
        List<Contato> contatosEncontrados = new ArrayList<>();
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).getNomeCompleto().contains(nome)) {
                contatosEncontrados.add(contatos.get(i));
            }
        }
        return contatosEncontrados;
    }

    public List<Contato> listar(int start, int quantidade) {
        if (start < 0 || start >= contatos.size()) {
            start = 0;
        }
        if (quantidade < 0) {
            quantidade = 0;
        }
        if (quantidade > contatos.size()) {
            quantidade = contatos.size();
        }

        if (start + quantidade >= contatos.size()) {
            quantidade = contatos.size() - start;
        }

        List<Contato> contatoesEncontrados = new ArrayList<>();
        for (int i = start; i < start + quantidade; i++) {
            contatoesEncontrados.add(contatos.get(i));
        }
        return contatoesEncontrados;
    }

    public String printAgenda() {
        if (contatos.isEmpty()) {
            return "Nenhum contato salvo na agenda!";
        }
        StringBuilder dados = new StringBuilder();
        for (int i = 0; i < contatos.size(); i++) {
            dados.append(contatos.get(i).getNomeCompleto().toUpperCase());
            var telefones = contatos.get(i).getTelefones();
            var enderecos = contatos.get(i).getEnderecos();
            if (telefones.size() > 0) {
                dados.append("\n");
                dados.append("Telefones: \n");
                for (int j = 0; j < telefones.size(); j++) {
                    dados.append("\t").append(telefones.get(j));
                    if ((j < telefones.size()-1)){
                        dados.append("\n");
                    }
                }
            } else {
                dados.append("\nContato sem telefones!");
            }
            if (enderecos.size() > 0) {
                dados.append("\n");
                dados.append("Endereços: \n");
                for (int j = 0; j < enderecos.size(); j++) {
                    dados.append("\t").append(enderecos.get(j));
                    if ((j < enderecos.size()-1)){
                        dados.append("\n");
                    }
                }
            } else {
                dados.append("\nContato sem endereços!");
            }
            dados.append("\n");
            dados.append("#".repeat(120));
            dados.append("\n");
        }
        return dados.toString();
    }
    public String listarTodosContatos() {
        StringBuilder dados = new StringBuilder();
        for (int i = 0; i < contatos.size(); i++) {
            dados.append(contatos.get(i).getNomeCompleto().toUpperCase());
            dados.append("\n");
            dados.append("#".repeat(120));
            dados.append("\n");
        }
        return dados.toString();
    }
    /*public String printAgenda() {
        if (contatos.isEmpty()) {
            return "Nenhum contato salvo na agenda!";
        }
        StringBuilder dados = new StringBuilder();
        for (int i = 0; i < contatos.size(); i++) {
            dados.append(contatos.get(i).getNomeCompleto().toUpperCase()).append(" | Tipo: ");
            dados.append(contatos.get(i).getTipoContato());
            var telefones = contatos.get(i).getTelefones();
            var enderecos = contatos.get(i).getEnderecos();
            if (telefones.size() > 0) {
                dados.append("\n");
                dados.append("Telefones: \n");
                for (int j = 0; j < telefones.size(); j++) {
                    dados.append("\t").append(telefones.get(j));
                    if ((j < telefones.size()-1)){
                        dados.append("\n");
                    }
                }
            } else {
                dados.append("\nContato sem telefones!");
            }
            if (enderecos.size() > 0) {
                dados.append("\n");
                dados.append("Endereços: \n");
                for (int j = 0; j < enderecos.size(); j++) {
                    dados.append("\t").append(enderecos.get(j));
                    if ((j < enderecos.size()-1)){
                        dados.append("\n");
                    }
                }
            } else {
                dados.append("\nContato sem endereços!");
            }
            dados.append("\n");
            dados.append("#".repeat(120));
            dados.append("\n");
        }
        return dados.toString();
    }*/
    public String printTelefones() {
        List<Telefone> telefones = new ArrayList<>();
        Contato contato = null;
        String nome = ConsoleUIHelper.askNoEmptyInput("Informe o nome do contato cujos telefones gostaria de  exibir.", 5);
        List<Contato> contatosAchados = this.pesquisarNome(nome);
        if (contatosAchados.size() == 0){
            System.out.println("Contato não encontrado.");
            return "Contato não encontrado";
        }
        List<String> ids = new ArrayList<>();
        for (int i = 0; i <= contatosAchados.size(); i++) {
            if (i < contatosAchados.size()) {
                System.out.println("ID: " + (i + 1) + " " + contatosAchados.get(i) + "\n");
                ids.add(""+i);
                continue;
            }
            int id = ConsoleUIHelper.askNumberInt("Digite o ID do contato") - 1;
            if (ids.contains(""+id)) {
                contato = contatosAchados.remove(id);
            }else {
                System.out.println("ID inexistente.");
            }
        }
        StringBuilder dados = new StringBuilder();
        for (int i = 0; i < this.getContatos().size(); i++) {
            if (this.getContatos().get(i).equals(contato)) {
                dados.append(contatos.get(i).getNomeCompleto());
                telefones = contatos.get(i).getTelefones();
                if (telefones.size() > 0) {
                    dados.append("\n");
                    dados.append("Telefones: \n");
                    for (int j = 0; j < telefones.size(); j++) {
                        dados.append("\t").append(telefones.get(j));
                        if ((j < telefones.size()-1)){
                            dados.append("\n");
                        }
                    }
                } else {
                    dados.append("\nContato sem telefones!");
                }

                dados.append("\n");
                dados.append("#".repeat(120));
                dados.append("\n");
            }
        }
        return dados.toString();
    }

    public String printEnderecos(){
        List<Endereco> enderecos = new ArrayList<>();
        Contato contato = null;
        String nome = ConsoleUIHelper.askNoEmptyInput("Informe o nome do contato cujo endereços gostaria de exibir.", 5);
        List<Contato> contatosAchados = this.pesquisarNome(nome);
        if (contatosAchados.size() == 0){
            System.out.println("Contato não encontrado.");
            return "Contato não encontrado";
        }
        List<String> ids = new ArrayList<>();
        for (int i = 0; i <= contatosAchados.size(); i++) {
            if (i < contatosAchados.size()) {
                System.out.println("ID: " + (i + 1) + " " + contatosAchados.get(i) + "\n");
                ids.add(""+i);
                continue;
            }
            int id = ConsoleUIHelper.askNumberInt("Digite o ID do contato") - 1;
            if (ids.contains(""+id)) {
                contato = contatosAchados.remove(id);
            }else {
                System.out.println("ID inexistente.");
            }
        }
        StringBuilder dados = new StringBuilder();
        for (int i = 0; i < this.getContatos().size(); i++) {
            if (this.getContatos().get(i).equals(contato)) {
                dados.append(contatos.get(i).getNomeCompleto());
                enderecos = contatos.get(i).getEnderecos();
                if (enderecos.size() > 0) {
                    dados.append("\n");
                    dados.append("Endereços: \n");
                    for (int j = 0; j < enderecos.size(); j++) {
                        dados.append("\t").append(enderecos.get(j));
                        if ((j < enderecos.size()-1)){
                            dados.append("\n");
                        }
                    }
                } else {
                    dados.append("\nContato sem endereços!");
                }

                dados.append("\n");
                dados.append("#".repeat(120));
                dados.append("\n");
            }
        }
        return dados.toString();
    }

    public void removerTodosOsContatos() {
        contatos = new ArrayList<>();
    }

}