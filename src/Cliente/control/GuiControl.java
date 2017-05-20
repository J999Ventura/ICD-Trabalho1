package Cliente.control;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.w3c.dom.Document;

import Cliente.OnCommunEventListener;
import Cliente.model.ClientModel;
import Cliente.model.LoginModel;
import Cliente.model.User;
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
import commun.TipoMovimento;


public class GuiControl implements OnLoginEventListener, OnClientEventListener, OnCommunEventListener, OnManagerEventListener{
	
	private LoginGui frameLogin;
	private ClientGui frameClient;
	private ManagerGui frameManager;
	
	private XMLInteration xmlInt;
	
	private LoginModel loginModel;
	private ClientModel clientM;
	
	public final static String DEFAULT_HOSTNAME = "localhost";
	public final static int DEFAULT_PORT = 5025;
	
	public GuiControl(/* irei receber tambem um servidor e um cliente */ ){
		fillClientObj();
		createLoginGui();
	}
	
	/* ########################## BODY METHODS #########################  */	
	public Cliente fillClientObj(){
		Protocolo pro = new Protocolo();
		
		xmlInt = new XMLInteration();
		Cliente cli =  xmlInt.getClient(pro.infoCliente(new Cliente("Joao Filipe Vaz", 207905835, null, null)));
		
		System.out.println(cli.getNomeCliente() + " " + cli.getNif());
		
		
		
		//xmlInt = new XMLInteration();
		Conta c = new Conta("teste1", "3215648948", 1234578, 1.0, ""+111111, ""+111111);
		c.setMovimento(new Movimento("mov", null, null, 1.1, TipoMovimento.DEBITO, "qwe", "asd"));
		c.setMovimento(new Movimento("mov", null, null, 1.1, TipoMovimento.DEBITO, "qwe", "asd"));
		c.setMovimento(new Movimento("mov", null, null, 1.1, TipoMovimento.DEBITO, "qwe", "asd"));
		c.setMovimento(new Movimento("mov", null, null, 1.1, TipoMovimento.DEBITO, "qwe", "asd"));
		c.setMovimento(new Movimento("mov", null, null, 1.1, TipoMovimento.DEBITO, "qwe", "asd"));
		Conta conta = xmlInt.getAccount(pro.infoConta(c)).get(0);
		System.out.println(conta.getNomeConta() + " " + conta.getIdCliente());// + " " + conta.getMovimentos().get(0).getContaDestino());
		return null;
	}
	
	
	/* #################################################################  */	
	
	
	/* ###################### IMPLEMENTED METHODS ######################  */
	/* ********** LOGIN/LOGOUT ********** */
	@Override
	public void onActionLogin(String user, String pass) {
		if(loginModel.validateLogin(user, pass)){
			this.frameLogin.dispose();//close login window;
			if(loginModel.isAdmin()){
				createManagerGui(loginModel.getUser());
			}else{
				
				createClientGui(loginModel.getUser());
			}
		}else{
			JOptionPane.showMessageDialog(frameLogin, "Invalid username or password!");
		}
	}
	
	@Override
	public void onLogoutOrCloseApp(){
			if(clientM.logout()){
				this.frameClient.dispose();//close client window;
				createLoginGui();
			}else{
				JOptionPane.showMessageDialog(frameLogin, "The app couldn't make the logout! Please try again.");
			}
	}
	
	@Override
	public void onCloseApp() {
		if(clientM.logout()){
			this.frameClient.dispose();//close client window;
		}else{
			JOptionPane.showMessageDialog(frameLogin, "The app couldn't make the logout! Please try again.");
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
		// TODO Auto-generated method stub
		return clientM.getAccountMovementsList();
	}
	
	@Override
	public ArrayList<String> onGetAccountBalance() {
		return clientM.getAccountBalanceList();
	}
	
	@Override
	public ArrayList<Movimento> onGetAccountMovements() {
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
	
	/* #################################################################  */
	
	
	/* ########################### CREATE GUI ###########################  */
	private void createLoginGui(){
		frameLogin = new LoginGui();
		
		centreWindow(frameLogin);
		loginModel = new LoginModel();
	    frameLogin.setOnLoginEventListener(this);
		frameLogin.setVisible(true);
	}

	
	private void createClientGui(User user){
		frameClient = new ClientGui(user);
		
		centreWindow(frameClient);
		clientM = new ClientModel(user);
		frameClient.setOnClientEventListener(this);
		frameClient.setOnCommunEventListener(this);
		this.frameClient.setVisible(true);
	}
	
	private void createManagerGui(User user){
		frameManager = new ManagerGui(user);
		
		centreWindow(frameManager);
		//frameManager = new ManagerModel();
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
	
	
	
	/* ####################### SOCKET CONNECTION #######################  */
	private void setUpConnection(){
		 String host = DEFAULT_HOSTNAME;  // Maquina onde reside a aplicacao servidora
	        int port = DEFAULT_PORT;      // Porto da aplicacao servidora

	        ClienteSimplesTCP clienteTCP = new ClienteSimplesTCP();
	        clienteTCP.openSocket(host, port);

	        //user e pass para teste
	        String u = "zeto";
	        String pass = "asdwf23425";

	        Protocolo log = new Protocolo();

	        Document d = log.writeLogin(u, pass);

	        clienteTCP.writeSocket(log.getStringFromDocument(d));

	        clienteTCP.closeSocket();

	        //log.removeChilds(d.getDocumentElement());
	}
	/* #################################################################  */
}
