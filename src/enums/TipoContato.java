package enums;

public enum TipoContato {
    Pessoal("Pessoal", 1),
    Profissional("Profissional", 2);

    String descricao;
    int valor;

    TipoContato(String descricao, int valor){
        this.descricao = descricao;
        this.valor = valor;
    }

    public static TipoContato getByValor(int valor) {
        TipoContato tipoContato = null;
        for (int i = 0; i < TipoContato.values().length; i++) {
            if (TipoContato.values()[i].valor == valor) {
                tipoContato = TipoContato.values()[i];
                break;
            }
        }
        return tipoContato;
    }

    @Override
    public String toString() {
        return valor + "-" + descricao;
    }
}