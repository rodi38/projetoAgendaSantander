package model;

import enums.TipoTelefone;

public class Telefone {
    private TipoTelefone tipoTelefone;
    private String ddi;
    private String ddd;
    private String numero;
    private String ramal;
    private String contato;

    public Telefone(TipoTelefone tipoTelefone, String ddi, String ddd, String ramal) {
        this.tipoTelefone = tipoTelefone;
        this.ddi = ddi;
        this.ddd = ddd;
        this.ramal = ramal;
    }

    public Telefone(TipoTelefone tipo, String ddi, String ddd, String numero, String ramal, String contato) {
        this(tipo, ddi, ddd, ramal);
        this.numero = numero;
        this.contato = contato;
    }

    public Telefone(String ddd, String numero, String contato) {
        this(TipoTelefone.Celular, "", ddd, numero, "", contato);
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

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getContato() {
        return contato;
    }

    public String getTelefoneCompleto() {
        String telefone = "(" + ddd + ")" + " " + numero;
        String telefoneCompleto = "";
        if (!ddi.isBlank()) {
            telefoneCompleto += "+" + ddi + telefone;
        } else {
            telefoneCompleto = telefone;
        }
        if (!ramal.isBlank()) {
            telefoneCompleto += ramal;
        }
        telefoneCompleto += " " + tipoTelefone;
        return telefoneCompleto.trim().replaceAll("\\s{2,}", " ");
    }


    public void setContato(String contato) {
        this.contato = contato;
    }


    @Override
    public String toString() {
        return "\nTelefone{" +
                "tipoTelefone=" + tipoTelefone +
                ", ddi='" + ddi + '\'' +
                ", ddd='" + ddd + '\'' +
                ", numero='" + numero + '\'' +
                ", ramal='" + ramal + '\'' +
                ", contato='" + contato + '\'' +
                '}';
    }
}
