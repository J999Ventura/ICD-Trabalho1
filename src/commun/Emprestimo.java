package commun;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Emprestimo {

	private double valorTotal;
	private double emFalta;
	private double juros;
	private Date timeToPay;
	private double valorMensal;
	private String accountName;
	
	public Emprestimo(String accountName, double valorTotal, double emFalta, double juros, double valorMensal){
		this.valorTotal = valorTotal;
		this.emFalta = emFalta;
		this.valorMensal = valorMensal;
		this.juros = juros;
		this.accountName = accountName;
	}
	
	public void setEmFalta(double valor){
		emFalta -= valor;
	}
	
	public void juros(double juros){
		this.juros = juros;
	}
	
	public double getValorTotal(){
		return valorTotal;
	}
	
	public double getEmFalta(){
		return emFalta;
	}
	
	public Date getTempoRestante(){
		return timeToPay;
	}
	
	public double getJuros(){
		return juros;
	}
	
	public double getValorMensal(){
		return valorMensal;
	}
	
	public String getNomeConta(){
		return accountName;
	}
	
	public String toString(){
		return "| Account: " + this.getNomeConta() + " | Total amount: " + this.getValorTotal() + " | Missing: " + this.getEmFalta() + " | Mounth Value: " + this.getValorMensal() + " | Interest: " + this.getJuros() + " |";
	}
	
}
