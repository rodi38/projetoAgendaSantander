package model;

import enums.TipoTelefone;

public class Telefone {
    private TipoTelefone tipoTelefone;
    private String ddi;
    private String ddd;
    private String numero;

    public Telefone(TipoTelefone tipoTelefone, String ddi, String ddd) {
        this.tipoTelefone = tipoTelefone;
        this.ddi = ddi;
        this.ddd = ddd;

    }

    public Telefone(TipoTelefone tipo, String ddi, String ddd, String numero) {
        this(tipo, ddi, ddd);
        this.numero = numero;
    }

    public Telefone(String ddd, String numero) {
        this(TipoTelefone.Celular, "", ddd, numero);
    }


    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
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
        String telefone = "(" + ddd + ")" + " " + numero;
        String telefoneCompleto = "";
        if (!ddi.isBlank()) {
            telefoneCompleto += "+" + ddi + telefone;
        } else {
            telefoneCompleto = telefone;
        }
        telefoneCompleto += " " + tipoTelefone;
        return telefoneCompleto.trim().replaceAll("\\s{2,}", " ");
    }



    @Override
    public String toString() {
        return "\nTelefone{" +
                "tipoTelefone=" + tipoTelefone +
                ", ddi='" + ddi + '\'' +
                ", ddd='" + ddd + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
