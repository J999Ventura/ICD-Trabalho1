package commun;


import java.awt.*;

/**
 * Created by Mónica on 26/04/2017.
 */
public class Cliente {

    private String nomeCliente;
    private int idCliente;
    private int nif;
    protected String morada;
    protected int numTelefone;
    protected int numConta;
    private Image foto;
    private Image assinatura;

    public Cliente(String nomeCliente, int nif, Image foto, Image assinatura) {
        this.nomeCliente = nomeCliente;
        this.nif = nif;
        this.foto = foto;
        this.assinatura = assinatura;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public Image getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Image assinatura) {
        this.assinatura = assinatura;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNumTelefone() {
        return numTelefone;
    }

    public void setNumTelefone(int numTelefone) {
        this.numTelefone = numTelefone;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

	public int getIdCliente() {
		// TODO Auto-generated method stub
		return 0;
	}
}
