package Cliente.model;

import Cliente.control.ClienteSimplesTCP;
import Protocolo.Protocolo;
import commun.Cliente;

public class LoginModel {

	private XMLInteration xmlInt;
	private Cliente cli;
	private Protocolo pro;
	private ClienteSimplesTCP tcp = new ClienteSimplesTCP();
	
	public LoginModel(){
		initCommunicationClientServer();
		
		pro = new Protocolo();
		xmlInt = new XMLInteration();
		//cli =  xmlInt.getClient(pro.infoCliente(new Cliente("nigga","Joao Filipe Vaz", 207905835, false)));
	}

	private void initCommunicationClientServer() {
		
	}
	
	public boolean validateLogin(String user, String pass){
		boolean login = false;
		/*
		 * 
		boolean login = false;
		Document doc = pro.writeLogin(user, pass);
		String msg = pro.getStringFromDocument(doc);
		tcp.writeSocket(msg);
		
		String readedMSG = tcp.readSocket();
		doc = XMLDoc.parseFile(readedMSG);
		boolean isLogin = xmlInt.getLoginAnswer(doc);

		if(isLogin){
			doc = pro.pedirCliente(user);
			msg = pro.getStringFromDocument(doc);
			tcp.writeSocket(msg);
			
			readedMSG = tcp.readSocket();
			doc = XMLDoc.parseFile(readedMSG);
			cli = xmlInt.getClient(doc);
			login = true;
		}*/
		login = true;
		return login;
	}
	
	public Cliente getCliente(){
		return cli;
	}
	
	public boolean isAdmin(){
		return false;//cli.getIsAdmin();
	}
	
	
	

	
}
