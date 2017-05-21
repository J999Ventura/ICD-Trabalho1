package Cliente.model;

import org.w3c.dom.Document;

import Cliente.control.ClienteSimplesTCP;
import Protocolo.Protocolo;
import XML.XMLDoc;
import commun.Cliente;
import commun.Funcionario;

public class LoginModel {

	private XMLInteration xmlInt;
	private Cliente cli;
	private Protocolo pro;
	private ClienteSimplesTCP tcp;
	
	public LoginModel(ClienteSimplesTCP clienteTCP){
		tcp = clienteTCP;
		
		pro = new Protocolo();
		xmlInt = new XMLInteration();
	}

	
	public boolean validateLogin(String user, String pass){
		boolean login = false;
		
		Document doc = pro.writeLogin(user, pass);
		tcp.writeSocket(doc);
		
		String readedMSG = tcp.readSocket();
		doc = pro.convertStringToDocument(readedMSG);
		boolean isLogin = xmlInt.getLoginAnswer(doc);

		if(isLogin){
			doc = pro.queryServidor("getUserInfo");
			tcp.writeSocket(doc);
			
			readedMSG = tcp.readSocket();
			System.out.println(readedMSG);
			doc = pro.convertStringToDocument(readedMSG);
			//cli = xmlInt.getClient(doc);
			login = true;
		}
		
		//boolean login = true; //para teste na manager ui
		return login;
	}
	
	public Cliente getCliente(){
		return cli;
	}
	
	public boolean isAdmin(){
		//return (cli instanceof Funcionario); //o funcionario ainda não é um cliente;
		return false; //para teste na manager ui
	}
	
	

	
}
