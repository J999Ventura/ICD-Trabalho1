package commun;

public enum TipoContaEnum {

    CONTAORDEM("Conta a Ordem"),
    CONTAPRAZO("Conta a Prazo"),
    CONTAORDENADO("Conta Ordenado"),
    CONTAJOVEM("Conta Jovem"),
    CONTAPOUPANCA("Conta Poupança");

    private final String tipo;

    TipoContaEnum(String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
