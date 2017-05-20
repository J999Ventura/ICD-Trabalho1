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
	private String userName;
	private String age, birthday;
	private boolean isAdmin;

    public Cliente(String userName, String nomeCliente, int nif, boolean isAdmin) {
        this.nomeCliente = nomeCliente;
        this.nif = nif;
        this.userName = userName;
        this.isAdmin = isAdmin;
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
	
	public String getUserName(){
		return userName;
	}
	
	public void setAge(String a){
		age = a;
	}
	
	public String getAge(){
		return age;
	}
	
	public void setBirthday(String b){
		birthday = b;
	}
	
	public String getBirthday(){
		return birthday;
	}
	
	public boolean getIsAdmin(){
		return isAdmin;
	}
	
	public void setUserName(String name){
		this.userName = name;
	}
}
