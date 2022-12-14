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

    public void excluir(Contato contato) {
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

    public List<Contato> pesquisarNomeTeste(String nome) {
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

    public String exibirTodasInfosContato(Contato contato) {
        StringBuilder dados = new StringBuilder();
        dados.append("Nome: ").append(contato.getNome()).append("\n");
        dados.append("Sobrenome: ").append(contato.getSobreNome()).append("\n");
        var telefones = contato.getTelefones();
        var enderecos = contato.getEnderecos();
        if (telefones.size() > 0) {
            dados.append("\n");
            dados.append("Telefones: \n");
            for (int j = 0; j < telefones.size(); j++) {
                dados.append("\t").append(telefones.get(j));
                if ((j < telefones.size() - 1)) {
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
                if ((j < enderecos.size() - 1)) {
                    dados.append("\n");
                }
            }
        } else {
            dados.append("\nContato sem endereços!");
        }
        dados.append("\n");
        dados.append("#".repeat(120));
        dados.append("\n");

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

    public String exibirTelefonesContato(Contato contato) {
        StringBuilder dados = new StringBuilder();
        dados.append("Nome: ").append(contato.getNome()).append("\n");
        dados.append("Sobrenome: ").append(contato.getSobreNome()).append("\n");
        var telefones = contato.getTelefones();
        if (telefones.size() > 0) {
            dados.append("\n");
            dados.append("Telefones: \n");
            for (int j = 0; j < telefones.size(); j++) {
                dados.append("\t").append(telefones.get(j)
                        .toString().replace("[", "")
                        .replace("]", ""));
                if ((j < telefones.size() - 1)) {
                    dados.append("\n");
                }
            }
        } else {
            dados.append("\nContato sem telefones!");
        }
        return dados.toString();
    }

    public String listarTelefoneBasico(Contato contato) {
        StringBuilder dados = new StringBuilder();
//        dados.append(contato.getNomeCompleto()).append("\n");
        var telefones = contato.getTelefones();
        if (telefones.size() > 0) {
            dados.append("\n");
            dados.append("Telefones: \n");
            for (int j = 0; j < telefones.size(); j++) {
                dados.append("\t").append(telefones.get(j).getTelefoneCompleto());
                if ((j < telefones.size() - 1)) {
                    dados.append("\n");
                }
            }
        } else {
            dados.append("\nContato sem telefones!");
        }
        return dados.toString();
    }

    public String exibirEnderecosContato(Contato contato) {
        StringBuilder dados = new StringBuilder();
        dados.append("Nome: ").append(contato.getNome()).append("\n");
        dados.append("Sobrenome: ").append(contato.getSobreNome()).append("\n");
        var enderecos = contato.getEnderecos();
        if (enderecos.size() > 0) {
            dados.append("\n");
            dados.append("Endereços: \n");
            for (int j = 0; j < enderecos.size(); j++) {
                dados.append("\t").append(enderecos.get(j)
                        .toString().replace("[", "")
                        .replace("]", ""));
                if ((j < enderecos.size() - 1)) {
                    dados.append("\n");
                }
            }
        } else {
            dados.append("\nContato sem endereços!");
        }
        return dados.toString();
    }

    public String listarEnderecosBasico(Contato contato) {
        StringBuilder dados = new StringBuilder();
        var enderecos = contato.getEnderecos();
        if (enderecos.size() > 0) {
            dados.append("\n");
            dados.append("Endereços: \n");
            for (int j = 0; j < enderecos.size(); j++) {
                dados.append("\t").append(enderecos.get(j).getEnderecoCompleto());
                if ((j < enderecos.size() - 1)) {
                    dados.append("\n");
                }
            }
        } else {
            dados.append("\nContato sem endereços!");
        }
        return dados.toString();
    }

    public void removerTodosOsContatos() {
        contatos = new ArrayList<>();
    }

}