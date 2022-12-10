package model;

import enums.TipoContato;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contato {
    private String nome;
    private String sobreNome;
    private TipoContato tipoContato;
    private List<Endereco> enderecos;
    private List<Telefone> telefones;

    public Contato(String nome, String sobreNome) {
        this.tipoContato = TipoContato.Pessoal;
        this.nome = nome;
        this.sobreNome = sobreNome;
    }



    public Contato(TipoContato tipoContato, String nome, String sobreNome, List<Endereco> enderecos,
                   List<Telefone> telefones) {
        this.tipoContato = tipoContato;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }


    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(ArrayList<Telefone> telefones) {
        this.telefones = telefones;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSobreNome() {
        return this.sobreNome;
    }

    public TipoContato getTipoContato() {
        return this.tipoContato;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(nome, contato.nome) && Objects.equals(sobreNome, contato.sobreNome) && tipoContato == contato.tipoContato;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobreNome, tipoContato);
    }

    public String getNomeCompleto() {
        String nome = this.nome;
        if (!sobreNome.isBlank()) {
            nome += " " + sobreNome;
        }
        return nome.trim().replaceAll("\\s{2,}", " ");
    }

    @Override
    public String toString() {
        return "Contato{" +
                " Tipo de contato = " + tipoContato +
                ", nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                ", endereco=" + enderecos +
                ", telefone=" + telefones +
                '}';
    }

}
