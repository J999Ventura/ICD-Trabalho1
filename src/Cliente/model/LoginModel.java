package Cliente.model;

import org.w3c.dom.Document;

import Cliente.control.ClienteSimplesTCP;
import Protocolo.Protocolo;
import XML.XMLDoc;
import commun.Cliente;

public class LoginModel {

	private XMLInteration xmlInt;
	private Cliente cli;
	private Protocolo pro;
	private ClienteSimplesTCP tcp;
	
	public LoginModel(ClienteSimplesTCP clienteTCP){
		tcp = clienteTCP;
		
		pro = new Protocolo();
		xmlInt = new XMLInteration();
		//cli =  xmlInt.getClient(pro.infoCliente(new Cliente("nigga","Joao Filipe Vaz", 207905835, true)));
	}

	
	public boolean validateLogin(String user, String pass){
		boolean login = false;
		
		Document doc = pro.writeLogin(user, pass);
		tcp.writeSocket(doc);
		
		String readedMSG = tcp.readSocket();
		doc = Protocolo.convertStringToDocument(readedMSG);
		boolean isLogin = xmlInt.getLoginAnswer(doc);

		if(isLogin){
			/*
			doc = pro.pedirCliente(user);
			tcp.writeSocket(doc);
			
			readedMSG = tcp.readSocket();
			doc = Protocolo.convertStringToDocument(readedMSG);
			cli = xmlInt.getClient(doc);
			*/
			login = true;
		}
		
		return login;
	}
	
	public Cliente getCliente(){
		return cli;
	}
	
	public boolean isAdmin(){
		return false;
	}
	
	

	
}
