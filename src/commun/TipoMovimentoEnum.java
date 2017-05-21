package commun;

public enum TipoMovimentoEnum {
    CREDITO("credito"),
    DEBITO("debito");

    private final String tipo;

    TipoMovimentoEnum(String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}



