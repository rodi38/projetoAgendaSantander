package model;

import enums.TipoTelefone;

import java.util.Objects;

public class Telefone {
    private TipoTelefone tipoTelefone;
    private String ddd;
    private String numero;

    public Telefone(TipoTelefone tipoTelefone, String ddd, String numero) {
        this.tipoTelefone = tipoTelefone;
        this.ddd = ddd;
        this.numero = numero;
    }


    public Telefone(String ddd, String numero) {
        this(TipoTelefone.Celular, ddd, numero);
    }


    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }


    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTelefoneCompleto() {
        String telefone = tipoTelefone + " (" + ddd + ") "  + numero;
        return telefone.trim().replaceAll("\\s{2,}", " ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return tipoTelefone == telefone.tipoTelefone && ddd.equals(telefone.ddd) && numero.equals(telefone.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoTelefone, ddd, numero);
    }

    @Override
    public String toString() {
        return "Tipo do Telefone: " + tipoTelefone +
                " | DDD: " + ddd +
                " | Numero: " + numero;
    }
}
