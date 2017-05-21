package commun;

public enum TipoClienteEnum {
    CLIENTEINDIVIDUAL("Cliente Individual"),
    CLIENTEEMPRESARIAL("Cliente Empresarial");

    private final String tipo;

    TipoClienteEnum(String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
