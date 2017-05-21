package Cliente.model;

import commun.Cliente;

public class ManagerModel {
	
	public ManagerModel(Cliente user){
		
	}

	public boolean logout() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean createAccountRequest(String clientID, String accountName, String accountType){
		return false;
	}

	public boolean closeAccountRequest(String clientID, String accountNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createLoanRequest(String clientID, String amount, String payment, String rate) {
		// TODO Auto-generated method stub
		return false;
	}

}
