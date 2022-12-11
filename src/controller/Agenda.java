package controller;

import model.Contato;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contato> contatos;

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
            if (contatos.get(i).getNome().contains(nome)) {
                contatosEncontrados.add(contatos.get(i));
            }
        }
        return contatosEncontrados;
    }

    public List<Contato> pesquisarEmail(String email) {
        List<Contato> contatosEncontrados = new ArrayList<>();
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).getNome().contains(email)) {
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
        int cont = 50;



        StringBuilder dados = new StringBuilder();
        for (int i = 0; i < contatos.size(); i++) {
            dados.append(contatos.get(i).getNomeCompleto());
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
                    int tamanhoString = telefones.get(j).toString().length();
                    if (tamanhoString > cont){
                        cont = tamanhoString;
                    }
                }
            } else {
                dados.append("\nContato sem telefone!");
            }
            if (enderecos.size() > 0) {
                dados.append("\n");
                dados.append("Endereços: \n");
                for (int j = 0; j < enderecos.size(); j++) {
                    dados.append("\t").append(enderecos.get(j));
                    if ((j < enderecos.size()-1)){
                        dados.append("\n");
                    }
                    int tamanhoString = enderecos.get(j).toString().length();
                    if (tamanhoString > cont){
                        cont = tamanhoString;
                    }
                }
            } else {
                dados.append("\nContato sem endereços!");
            }
            dados.append("\n").append("#".repeat(cont));
            dados.append("\n");
        }
        return dados.toString();
    }

    public void removerTodosOsContatos() {
        contatos = new ArrayList<>();
    }

}
