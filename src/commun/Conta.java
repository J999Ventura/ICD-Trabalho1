package commun;

import java.util.ArrayList;
import java.util.List;

public class Conta {

    private String numConta;
    private String nib;
    private String iban;
    private String titular;
    private double saldoContabilistico;
    private double saldoDisponivel;
    private double saldoAutorizado;
    private List<Movimento> movimentos;
    private String nomeConta;
    private TipoContaEnum tipoConta;


    public Conta(String numConta, String nib, String iban, String titular, double saldoContabilistico, double saldoDisponivel,
                  String nomeConta, TipoContaEnum tipoConta) {
        this.numConta = numConta;
        this.nib = nib;
        this.iban = iban;
        this.titular = titular;
        this.saldoContabilistico = saldoContabilistico;
        this.saldoDisponivel = saldoDisponivel;
        this.nomeConta = nomeConta;
        this.tipoConta = tipoConta;
        movimentos = new ArrayList<>();
    }
    
    public String getNomeConta() {
        return nomeConta;
    }
 
    public String getNib() {
        return nib;
    }

    public void setNib(String nib) {
        this.nib = nib;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public TipoContaEnum getTipoConta() {
        return tipoConta;
    }

    public void setMovimentos(List<Movimento> movimentos) {

        this.movimentos = movimentos;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public double getSaldoContabilistico() {
        return saldoContabilistico;
    }

    public void setSaldoContabilistico(double saldoContabilistico) {
        this.saldoContabilistico = saldoContabilistico;
    }

    public double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(double saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    public double getSaldoAutorizado() {
        return saldoAutorizado;
    }

    public void setSaldoAutorizado(double saldoAutorizado) {
        this.saldoAutorizado = saldoAutorizado;
    }

    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public List<Movimento> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(ArrayList<Movimento> movimentos) {
        this.movimentos = movimentos;
    }
    
    public void setMovimento(Movimento movimento) {
        this.movimentos.add(movimento);
    }
}
