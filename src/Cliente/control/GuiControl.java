package Cliente.control;

import Cliente.OnCommunEventListener;
import Cliente.model.ClientModel;
import Cliente.model.LoginModel;
import Cliente.model.ManagerModel;
import Cliente.model.XMLInteration;
import Cliente.view.client.ClientGui;
import Cliente.view.client.OnClientEventListener;
import Cliente.view.login.LoginGui;
import Cliente.view.login.OnLoginEventListener;
import Cliente.view.manager.ManagerGui;
import Cliente.view.manager.OnManagerEventListener;
import Protocolo.Protocolo;
import commun.Cliente;
import commun.Conta;
import commun.Emprestimo;
import commun.Movimento;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GuiControl implements OnLoginEventListener, OnClientEventListener, OnCommunEventListener, OnManagerEventListener{
	
	private LoginGui frameLogin;
	private ClientGui frameClient;
	private ManagerGui frameManager;
	
	private XMLInteration xmlInt;
	
	private LoginModel loginModel;
	private ClientModel clientM;
	private ManagerModel managerM;
	private boolean isAdminGui;
	private ClienteSimplesTCP clienteTCP;
	private Protocolo pro;
	
	public GuiControl(ClienteSimplesTCP clienteTCP ){
		this.clienteTCP = clienteTCP;
		createLoginGui();
	}
	
	/* ########################## BODY METHODS #########################  */	

	/* #################################################################  */	
	
	
	/* ###################### IMPLEMENTED METHODS ######################  */
	/* ********** LOGIN/LOGOUT ********** */
	@Override
	public void onActionLogin(String user, String pass) {
		if(loginModel.validateLogin(user, pass)){
			this.frameLogin.dispose();//close login window;
			if(loginModel.isAdmin()){
				createManagerGui(loginModel.getCliente());
			}else{
				createClientGui(loginModel.getCliente());
			}
		}else{
			JOptionPane.showMessageDialog(frameLogin, "Invalid username or password!");
		}
	}
	
	@Override
	public void onLogoutOrCloseApp(){
			if(isAdminGui && managerM.logout()){
				this.frameManager.dispose();//close client window;
				createLoginGui();
			}else{
				if(!isAdminGui && clientM.logout()){
					this.frameClient.dispose();//close client window;
					createLoginGui();
				}else{
					JOptionPane.showMessageDialog(frameLogin, "The app couldn't make the logout! Please try again.");
				}
			}
	}
	
	@Override
	public void onCloseApp() {
		if(isAdminGui && managerM.logout()){
			this.frameManager.dispose();//close client window;
			clienteTCP.writeSocket(pro.queryServidor("logout"));
			clienteTCP.closeSocket();
		}else{
			if(!isAdminGui && clientM.logout()){
				this.frameClient.dispose();//close client window;
				clienteTCP.writeSocket(pro.queryServidor("logout"));
				clienteTCP.closeSocket();
			}else{
				JOptionPane.showMessageDialog(frameLogin, "The app couldn't make the logout! Please try again.");
			}
		}
		
	}
	/* ********************************** */

	/* ************* CLIENT ************* */
	@Override
	public void onSelectedAccount(String account) {
		ArrayList<Conta> accountList =  clientM.getAccountList();
		for(Conta conta : accountList){
			if(conta.getNomeConta().equals(account)){
				clientM.setCurrentAccount(conta);
				break;
			}
		}
	}

	@Override
	public void onNewUserChange(String newName) {
			clientM.setNewUserName(newName);
		
	}

	@Override
	public void onAmountTransfer(String amount, String nib) {
		if(nib == null || nib.equals("")){
			JOptionPane.showMessageDialog(frameClient, "No target account found!");
			return;
		}
			
		try{
			double tAmount = Double.parseDouble(amount);
			
			if(clientM.transferMoney(tAmount, nib)){
				JOptionPane.showMessageDialog(frameClient, "Success");
			}else{
				JOptionPane.showMessageDialog(frameClient, "It was not possible to make the transfer!");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(frameClient, "Only numbers are allowed to transfers!");
		}
	}
	
	@Override
	public ArrayList<Movimento> onGetAllAccountsMovements() {
		return clientM.getAccountMovementsList();
	}
	
	@Override
	public ArrayList<String> onGetAccountBalance() {
		return clientM.getAccountBalanceList();
	}
	
	@Override
	public List<Movimento> onGetAccountMovements() {
		return clientM.getCurrentAccountMovimentsList();
	}
	
	@Override
	public ArrayList<Emprestimo> onGetAccountLoans() {
		return clientM.getAccountsLoansList();
	}
	
	@Override
	public ArrayList<Conta> onGetAccounts() {
		return clientM.getAccountList();
	}
	

	@Override
	public Conta onGetCurrentAccount() {
		return clientM.getCurrentAccount();
	}
	/* ********************************** */
	
	/* ************* Manager ************ */
	@Override
	public List<String> onGetAccountTypes() {
		return managerM.getAccountTypes();
	}
	
	@Override
	public void createAccount(String nib, String accountName, String accountType) {
		if(!nib.equals("") && !accountName.equals("") && !accountType.equals("") && managerM.createAccountRequest(nib, accountName, accountType)){
			JOptionPane.showMessageDialog(frameManager, "Account created");
		}else{
			JOptionPane.showMessageDialog(frameManager, "The account was not created!");
		}
	}

	@Override
	public void closeAccount(String nib, String accountNumber) {
		if(!nib.equals("") && !accountNumber.equals("") && managerM.closeAccountRequest(nib, accountNumber)){
			JOptionPane.showMessageDialog(frameManager, "Account closed");
		}else{
			JOptionPane.showMessageDialog(frameManager, "The account was not closed!");
		}
	}

	@Override
	public void createLoan(String nib, String amount, String payment, String rate) {
		if(!nib.equals("") && !amount.equals("") && !payment.equals("") && !rate.equals("") && managerM.createLoanRequest(nib, amount, payment, rate)){
			JOptionPane.showMessageDialog(frameManager, "Loan created");
		}else{
			JOptionPane.showMessageDialog(frameManager, "The loan was not created!");
		}
	}
	/* ********************************** */
	/* #################################################################  */
	
	
	/* ########################### CREATE GUI ###########################  */
	private void createLoginGui(){
		frameLogin = new LoginGui();
		
		centreWindow(frameLogin);
		loginModel = new LoginModel(clienteTCP);
	    frameLogin.setOnLoginEventListener(this);
		frameLogin.setVisible(true);
	}

	
	private void createClientGui(Cliente user){
		isAdminGui = false;
		frameClient = new ClientGui(user);
		
		centreWindow(frameClient);
		clientM = new ClientModel(clienteTCP, user);
		frameClient.setOnClientEventListener(this);
		frameClient.setOnCommunEventListener(this);
		this.frameClient.setVisible(true);
	}
	
	private void createManagerGui(Cliente user){
		isAdminGui = true;
		frameManager = new ManagerGui(user);
		
		centreWindow(frameManager);
		managerM = new ManagerModel(clienteTCP, user);
		
		frameManager.setOnManagerEventListener(this);
		frameManager.setOnCommunEventListener(this);
		this.frameManager.setVisible(true);
	}

	/**
	 * centre login gui
	 * @param frame
	 */
	private static void centreWindow(JFrame frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	/* #################################################################  */
}
