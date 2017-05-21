package commun;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Cliente {

    private String userName;
    private String nomeCliente;
    private String idCliente;
    private String nif;
    private String morada;
    private String numTelefone;
    private Image foto;
    private Image assinatura;
    private List<Conta> contas;
    private TipoClienteEnum tipoCliente;

    public Cliente(String userName, String nomeCliente, String nif, String morada, String numTelefone, Image assinatura, Image foto, TipoClienteEnum tipoCliente) {

        //gera um Universal Unique ID para cada cliente
        this.idCliente = UUID.randomUUID().toString();
        this.userName = userName;
        this.nomeCliente = nomeCliente;
        this.nif = nif;
        this.morada = morada;
        this.numTelefone = numTelefone;
        this.assinatura = assinatura;
        this.foto = foto;
        this.tipoCliente = tipoCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNif() {
        return nif;
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

    public String getNumTelefone() {
        return numTelefone;
    }

    public void setNumTelefone(String numTelefone) {
        this.numTelefone = numTelefone;
    }

	public String getIdCliente() {
        return idCliente;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String name){
		this.userName = name;
	}

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public TipoClienteEnum getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoClienteEnum tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
}


