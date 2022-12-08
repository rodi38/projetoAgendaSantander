package model;

import enums.TipoContato;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contato {
    private String nome;
    private String sobreNome;
    private String email;
    private TipoContato tipo;
    private ArrayList<Endereco> enderecos;
    private ArrayList<Telefone> telefones;

    public Contato(String nome, String sobreNome, String email,
                   TipoContato tipoContato) {
        this(nome, tipoContato);
        this.sobreNome = sobreNome;
        this.email = email;
    }

    public Contato(String nome, TipoContato tipoContato) {
        this.nome = nome;
        this.tipo = tipoContato;
    }

    public Contato(String nome, String sobreNome, String email,
                   TipoContato tipoContato, ArrayList<Endereco> enderecos, ArrayList<Telefone> telefones) {
        this(nome, sobreNome, email, tipoContato);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSobreNome() {
        return this.sobreNome;
    }

    public TipoContato getTipo() {
        return this.tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public void setTipo(TipoContato tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(nome, contato.nome) && Objects.equals(sobreNome, contato.sobreNome) && tipo == contato.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobreNome, tipo);
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
                " nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                ", email='" + email + '\'' +
                ", tipoContato=" + tipo +
                ", endereco=" + enderecos +
                ", telefone=" + telefones +
                '}';
    }

}
