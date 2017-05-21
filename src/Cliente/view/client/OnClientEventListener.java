package Cliente.view.client;

import java.util.List;

import commun.Conta;
import commun.Emprestimo;
import commun.Movimento;

public interface OnClientEventListener {
	
	public void onSelectedAccount(String account);
	public void onNewUserChange(String newName);
	public void onAmountTransfer(String amount, String account);
	public List<String> onGetAccountBalance();
	public List<Movimento> onGetAccountMovements();
	public List<Emprestimo> onGetAccountLoans();
	public List<Movimento> onGetAllAccountsMovements();
	public List<Conta> onGetAccounts();
	public Conta onGetCurrentAccount();
}
