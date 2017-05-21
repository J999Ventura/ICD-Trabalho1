package Cliente.control;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.w3c.dom.Document;

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
import commun.*;


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
	
	public GuiControl(ClienteSimplesTCP clienteTCP ){
		this.clienteTCP = clienteTCP;
		createLoginGui();
	}
	
	/* ########################## BODY METHODS #########################  */	
	public void fillClientObj(){
		Protocolo pro = new Protocolo();
		xmlInt = new XMLInteration();
		/*Conta c = new Conta("teste1", "3215648948", 1234578, 1.0, ""+111111, ""+111111);
		c.setMovimento(new Movimento("mov1", "dsfdsf", "sdfdf", 1.1, TipoMovimentoEnum.DEBITO, "qwe1", "asd1"));
		Conta c = new Conta("teste1", "3215648948","3215648948","3215648948",
				1234578, 1.0, ""+111111, TipoContaEnum.CONTAJOVEM);
		c.setMovimento(new Movimento("mov1", LocalDate.of(2017, 5, 30), LocalDate.of(2017, 5, 30), 1.1, TipoMovimentoEnum.DEBITO, "qwe1", "asd1"));
		c.setMovimento(new Movimento("mov2", null, null, 2.2, TipoMovimentoEnum.CREDITO, "qwe2", "asd2"));
		c.setMovimento(new Movimento("mov3", null, null, 3.3, TipoMovimentoEnum.DEBITO, "qwe3", "asd3"));
		c.setMovimento(new Movimento("mov4", null, null, 4.4, TipoMovimentoEnum.CREDITO, "qwe4", "asd4"));
		c.setMovimento(new Movimento("mov5", null, null, 5.5, TipoMovimentoEnum.DEBITO, "qwe5", "asd5"));*/

		
		ArrayList<Emprestimo> e = new ArrayList<Emprestimo>();
		e.add(new Emprestimo("teste1", 725.0, 5.0, 2.0, 6));
		e.add(new Emprestimo("teste1", 45.0, 5.0, 3.0, 7));

		//clientM.setAccountList(xmlInt.getAccounts(pro.criarConta(c)));
		//clientM.setLoansList(xmlInt.getLoans(pro.enviarEmprestimo(e)));
		//clientM.setAccountList(xmlInt.getAccounts(pro.criarConta(c)));
		clientM.setLoansList(xmlInt.getLoans(pro.infoEmprestimo(e)));
	}
	
	
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
			clienteTCP.closeSocket();
		}else{
			if(!isAdminGui && clientM.logout()){
				this.frameClient.dispose();//close client window;
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
	public void onAmountTransfer(String amount, String account) {
		if(account == null || account.equals("")){
			JOptionPane.showMessageDialog(frameClient, "No target account found!");
			return;
		}
			
		try{
			double tAmount = Double.parseDouble(amount);
			
			if(clientM.transferMoney(tAmount, account)){
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
	public void createAccount(String clientID, String accountName, String accountType) {
		if(managerM.createAccountRequest(clientID, accountName, accountType)){
			JOptionPane.showMessageDialog(frameManager, "Account created");
		}else{
			JOptionPane.showMessageDialog(frameManager, "The account was not created!");
		}
	}

	@Override
	public void closeAccount(String clientID, String accountNumber) {
		if(managerM.closeAccountRequest(clientID, accountNumber)){
			JOptionPane.showMessageDialog(frameManager, "Account closed");
		}else{
			JOptionPane.showMessageDialog(frameManager, "The account was not closed!");
		}
	}

	@Override
	public void createLoan(String clientID, String amount, String payment, String rate) {
		if(managerM.createLoanRequest(clientID, amount, payment, rate)){
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
		fillClientObj();
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
