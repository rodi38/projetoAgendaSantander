package model;

import enums.TipoEndereco;

import java.util.List;

public class Endereco {
    private TipoEndereco tipoEndereco;
    private String cep;
    private String logradouro;
    private String cidade;
    private String estado;
    private String numero;


    public Endereco (TipoEndereco tipoEndereco, String cep, String logradouro, String numero, String cidade, String estado) {
        this.tipoEndereco = tipoEndereco;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Endereco(TipoEndereco tipo, String logradouro, String numero) {
        this(tipo,"",logradouro,numero,"","");
    }

    public String getEnderecoCompleto() {
        StringBuilder endereco = new StringBuilder(tipoEndereco.toString() + " " + logradouro);
        List<String> atributos = List.of(numero,cidade, estado, cep);
        for (int i = 0; i < atributos.size() ; i++) {
            if (!atributos.get(i).isBlank()){
                endereco.append(" | ").append(atributos.get(i));
            }
        }
        return endereco.toString().trim().replaceAll("\\s{2,}", " ");
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        if (cep.matches("[0-9]{5}-[0-9]{3}")) {
            this.cep = cep;
        }
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "\nEndereco{" +
                " tipoEndereco=" + tipoEndereco +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
