package Cliente.model;

import java.util.ArrayList;
import java.util.List;
import Cliente.control.ClienteSimplesTCP;
import Protocolo.Protocolo;
import commun.Cliente;
import commun.Conta;
import commun.Emprestimo;
import commun.Movimento;

public class ClientModel {

	private Conta currentAccount;
	private Cliente user;
	private ArrayList<String> balanceList;
	private ClienteSimplesTCP tcp;
	private Protocolo pro;
	private XMLInteration xmlInt;
	
	public ClientModel(ClienteSimplesTCP clienteTCP, Cliente user){
		this.user = user;
		currentAccount = user.getContas().get(0);
		this.tcp = clienteTCP;
		pro = new Protocolo();
		xmlInt = new XMLInteration();
	}
	
	
	public void logout(){

		tcp.writeSocket(pro.queryServidor("logout"));

		//return xmlInt.getLoginAnswer(Protocolo.convertStringToDocument(tcp.readSocket()));

		/* *********** TESTE NA UI SEM SOCKET **********/
		/*
		Document doc = pro.logout(true);
		System.out.println(Protocolo.getStringFromDocument(doc));
		return true;

		/* *********************************************/
	}
	
	
	public void setAccountList(List<Conta> list){
		user.addContas(list);
		setBalanceList();
	}
	
	private void setBalanceList(){
		balanceList = new ArrayList<String>();
		for(Conta conta : user.getContas()){
			balanceList.add(conta.getSaldoContabilistico()+"");
		}
	}
	
	public void setLoanList(List<Emprestimo> list){
		user.setEmprestimos(list);
	}

	public ArrayList<Conta> getAccountList() {
		return (ArrayList<Conta>)user.getContas();
	}

	
	public void setCurrentAccount(Conta conta) {
		currentAccount = conta;
	}

	public void setNewUserName(String newName) {

		tcp.writeSocket(pro.changeUserName(newName));

		boolean changed = xmlInt.getLoginAnswer(Protocolo.convertStringToDocument(tcp.readSocket()));
		if(changed)
			user.setUserName(newName);

		/* *********** TESTE NA UI SEM SOCKET **********/
		/*
		Document doc = pro.changeUserName(newName);
		System.out.println(Protocolo.getStringFromDocument(doc));
		boolean changed = true;
		if(changed)
			user.setUserName(newName);
		/**********************************************/
	}

	
	public void setLoansList(List<Emprestimo> list){
		user.setEmprestimos(list);
	}
	
	public ArrayList<Emprestimo> getAccountsLoansList() {
		return (ArrayList<Emprestimo>)user.getEmprestimos();
	}
	
	public ArrayList<String> getAccountBalanceList() {
		ArrayList<String> contas = new ArrayList<String>();
		for(Conta conta : user.getContas()){
			contas.add(conta.getNomeConta() + " : " + conta.getSaldoContabilistico());
		}
		return contas;
	}

	
	public void setMovementsList(ArrayList<Movimento> list){
		currentAccount.setMovimentos(list);
	}
	
	public List<Movimento> getCurrentAccountMovimentsList(){
		return currentAccount.getMovimentos();
	}
	
	public ArrayList<Movimento> getAccountMovementsList() {
		ArrayList<Movimento> movimento= new ArrayList<Movimento>();
		for(Conta conta : user.getContas()){
			movimento.addAll(conta.getMovimentos());
		}
		return movimento;
	}
	

	public Conta getCurrentAccount() {
		return currentAccount;
	}

	public boolean transferMoney(double amount, String nib) {

		tcp.writeSocket(pro.makeTransfer(nib, amount));

		boolean changed = xmlInt.getLoginAnswer(Protocolo.convertStringToDocument(tcp.readSocket()));
		if(changed)
			return true;

		
		/* *********** TESTE NA UI SEM SOCKET **********/
		/*
		Document doc = pro.makeTransfer(nib, amount);
		System.out.println(Protocolo.getStringFromDocument(doc));
		boolean changed = true;
		if(changed)
			return true;
		/**********************************************/
		return false;
	}
	
	
	
	

}
