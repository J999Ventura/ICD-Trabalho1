package commun;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private List<Emprestimo> emprestimos;
    private TipoClienteEnum tipoCliente;



    public Cliente(String userName, String nomeCliente, String idCliente, String nif, String morada, String numTelefone, Image assinatura, Image foto, TipoClienteEnum tipoCliente) {

        //gera um Universal Unique ID para cada cliente
        this.idCliente = idCliente;
        this.userName = userName;
        this.nomeCliente = nomeCliente;
        this.nif = nif;
        this.morada = morada;
        this.numTelefone = numTelefone;
        this.assinatura = assinatura;
        this.foto = foto;
        this.tipoCliente = tipoCliente;
        contas = new ArrayList<>();
        emprestimos = new ArrayList<>();
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

    public void setIdCliente() {
        this.idCliente = UUID.randomUUID().toString();
    }
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String name){
		this.userName = name;
	}

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
    
    public void setEmprestimos(List<Emprestimo> list){
    	emprestimos = list;
    }
    
    public void addConta(Conta conta){
    	this.contas.add(conta);
    }

    public List<Conta> getContas() {
        return this.contas;
    }

    public TipoClienteEnum getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoClienteEnum tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
}


