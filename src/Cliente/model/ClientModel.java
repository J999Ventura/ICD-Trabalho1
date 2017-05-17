package Cliente.model;

import java.util.ArrayList;

import commun.Conta;
import commun.Emprestimo;
import commun.Movimento;

public class ClientModel {

	private Conta currentAccount;
	private User user;
	private ArrayList<Conta> accountList;
	private ArrayList<String> balanceList;
	private ArrayList<Movimento> movementsList;
	
	public ClientModel(User user){
		this.user = user;
		currentAccount = new Conta("teste0", "3215648948", 1234578, 1.0, "000000", "000000"); //a minha conta inicial sera a primeira da lista de contas recebida;
		
		
		
		accountList = new ArrayList<Conta>();
		accountList.add(new Conta("teste1", "3215648948", 1234578, 1.0, ""+111111, ""+111111));
		accountList.get(0).setEmprestimo(new Emprestimo("teste1", 10.0, 5.0, 1.0, 5));
		accountList.get(0).setEmprestimo(new Emprestimo("teste1", 725.0, 5.0, 2.0, 6));
		accountList.get(0).setEmprestimo(new Emprestimo("teste1", 45.0, 5.0, 3.0, 7));
		
		accountList.add(new Conta("teste2", "5165616516", 6516515, 2.0, ""+222222, ""+222222));
		accountList.get(1).setEmprestimo(new Emprestimo("teste2", 85.0, 10.0, 2.0, 10));
		accountList.get(1).setEmprestimo(new Emprestimo("teste2", 25.0, 10.0, 4.0, 450));
		accountList.get(1).setEmprestimo(new Emprestimo("teste2", 76.0, 10.0, 5.0, 480));
	}
	
	public boolean logout(){
		// TODO Auto-generated method stub
		return true;
	}
	
	
	public void setAccountList(ArrayList<Conta> list){
		accountList = list;
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
		currentAccount.setEmprestimos(list);
	}
	
	public ArrayList<Emprestimo> getAccountsLoansList() {
		ArrayList<Emprestimo> emprestimos= new ArrayList<Emprestimo>();
		for(Conta conta : accountList){
			emprestimos.addAll(conta.getEmprestimos());
		}
		return emprestimos;
	}
	
	public ArrayList<Emprestimo> getCurrentAccountLoansList(){
		return currentAccount.getEmprestimos();
	}
	
	public void setBalanceList(ArrayList<String> list){
		balanceList = list;
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
