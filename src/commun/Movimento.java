package commun;


public class Movimento {

    private String descricao;
    private String dataValor;
    private String dataLancamento;
    private double valor;
    private TipoMovimentoEnum tipoMovimento;
    private String contaRemetente;
    private String contaDestino;

    public Movimento(String descricao, String dataValor, String dataLancamento, double valor, TipoMovimentoEnum tipo, String contaD, String contaR) {
        this.descricao = descricao;
        this.dataValor = dataValor;
        this.dataLancamento = dataLancamento;
        this.valor = valor;
        this.tipoMovimento = tipo;
        this.contaDestino = contaD;
        this.contaRemetente = contaR;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public String getContaDestino() {
        return contaDestino;
    }
    
    public String getContaRemetente() {
        return contaRemetente;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataValor() {
        return dataValor;
    }

    public void setDataValor(String dataValor) {
        this.dataValor = dataValor;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoMovimentoEnum getTipoMovimento() {
        return tipoMovimento;
    }

    public String toString(){
    	return "| From: " + this.getContaRemetente() + " | To: " + this.getContaDestino() + " | Type: " + this.getTipoMovimento() + " | Value: " + this.getValor() + "� | Date: " + this.getDataLancamento() + " |";
    }
}
