package model;

import enums.TipoTelefone;

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
    public String toString() {
        return "\nTelefone{" +
                "tipoTelefone=" + tipoTelefone +
                ", ddd='" + ddd + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
