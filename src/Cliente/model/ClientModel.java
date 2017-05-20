package Cliente.model;

import java.util.ArrayList;

import commun.Cliente;
import commun.Conta;
import commun.Emprestimo;
import commun.Movimento;

public class ClientModel {

	private Conta currentAccount;
	private Cliente user;
	private ArrayList<Conta> accountList;
	private ArrayList<String> balanceList;
	private ArrayList<Emprestimo> emprestimos;
	
	public ClientModel(Cliente user){
		this.user = user;
	}
	
	
	public boolean logout(){
		// TODO Auto-generated method stub
		return true;
	}
	
	
	public void setAccountList(ArrayList<Conta> list){
		accountList = list;
		setBalanceList();
	}
	
	private void setBalanceList(){
		balanceList = new ArrayList<String>();
		for(Conta conta : accountList){
			balanceList.add(conta.getSaldoContabilistico()+"");
		}
	}
	
	public void setLoanList(ArrayList<Emprestimo> list){
		emprestimos = list;
	}

	public ArrayList<Conta> getAccountList() {
		return accountList;
	}

	
	public void setCurrentAccount(Conta conta) {
		currentAccount = conta;
	}

	public void setNewUserName(String newName) {
		user.setUserName(newName);	
	}

	
	public void setLoansList(ArrayList<Emprestimo> list){
		emprestimos = list;
	}
	
	public ArrayList<Emprestimo> getAccountsLoansList() {
		return emprestimos;
	}
	
	public ArrayList<String> getAccountBalanceList() {
		ArrayList<String> contas = new ArrayList<String>();
		for(Conta conta : accountList){
			contas.add(conta.getNomeConta() + " : " + conta.getSaldoContabilistico());
		}
		return contas;
	}

	
	public void setMovementsList(ArrayList<Movimento> list){
		currentAccount.setMovimentos(list);
	}
	
	public ArrayList<Movimento> getCurrentAccountMovimentsList(){
		return currentAccount.getMovimentos();
	}
	
	public ArrayList<Movimento> getAccountMovementsList() {
		ArrayList<Movimento> movimento= new ArrayList<Movimento>();
		for(Conta conta : this.accountList){
			movimento.addAll(conta.getMovimentos());
		}
		return movimento;
	}
	

	public Conta getCurrentAccount() {
		return currentAccount;
	}

	public boolean transferMoney(double amount, String account) {
		System.out.println("" + amount);
		if(amount > 500000)
			return false;
		
		return true;
	}
	
	
	
	

}
