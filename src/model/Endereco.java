package model;

import enums.TipoEndereco;

import java.util.List;

public class Endereco {
    private TipoEndereco tipo;
    private String pais;
    private String cep;
    private String logradouro;
    private String cidade;
    private String bairro;
    private String complemento;
    private String estado;
    private String numero;

    public Endereco() {}

    public Endereco(String pais, String cep, String cidade, String bairro, String complemento) {
        this.pais = pais;
        this.cep = cep;
        this.cidade = cidade;
        this.bairro = bairro;
        this.complemento = complemento;
        this.logradouro = "";
        this.tipo = TipoEndereco.Residencial;

    }

    public Endereco(TipoEndereco tipo, String logradouro) {
        this("", "", "", "", "");
        this.tipo = tipo;
        this.logradouro = logradouro;
    }

    public String getEnderecoCompleto() {
        StringBuilder endereco = new StringBuilder(tipo.toString() + " " + logradouro);
        List<String> atributos = List.of(pais,cep,cidade,bairro,complemento);
        for (int i = 0; i < atributos.size() ; i++) {
            if (!atributos.get(i).isBlank()){
                endereco.append(" ").append(atributos.get(i));
            }
        }
        return endereco.toString().trim().replaceAll("\\s{2,}", " ");
    }

    public TipoEndereco getTipo() {
        return tipo;
    }

    public void setTipo(TipoEndereco tipo) {
        this.tipo = tipo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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
                " tipoEndereco=" + tipo +
                ", pais='" + pais + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", estado='" + estado + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
