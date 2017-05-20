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
    private LocalDate dateOfBirth;
    private List<Conta> contas;

    public Cliente(String userName, String nomeCliente, String nif, Image assinatura, Image foto) {

        //gera um Universal Unique ID para cada cliente
        this.idCliente = UUID.randomUUID().toString();
        this.userName = userName;
        this.nomeCliente = nomeCliente;
        this.nif = nif;
        this.assinatura = assinatura;
        this.foto = foto;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
}


