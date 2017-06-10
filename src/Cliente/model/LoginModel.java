package Cliente.model;

import Cliente.control.ClienteSimplesTCP;
import Protocolo.Protocolo;
import XML.XMLDoc;
import commun.Cliente;
import org.w3c.dom.Document;

public class LoginModel {

	private XMLInteration xmlInt;
	private Cliente clt;
	private ClienteSimplesTCP tcp;
	private Protocolo pro;
	
	public LoginModel(ClienteSimplesTCP clienteTCP){
		tcp = clienteTCP;
		xmlInt = new XMLInteration();
		pro = new Protocolo();
	}
	
	public boolean validateLogin(String user, String pass){
		boolean login = false;
		
		Document doc = pro.writeLogin(user, pass);
		tcp.writeSocket(doc);
		
		String readedMSG = tcp.readSocket();
		doc = Protocolo.convertStringToDocument(readedMSG);
		boolean isLogin = xmlInt.getLoginAnswer(doc);

		if(isLogin){
			doc = pro.queryServidor("getUserInfo");
			tcp.writeSocket(doc);

			String dadosCliente = tcp.readSocket();
			System.out.println(dadosCliente);

			Document docCliente = Protocolo.convertStringToDocument(dadosCliente);
			XMLDoc.writeDocument(docCliente, "daoosClienteoutput.xml");
			clt = xmlInt.getClient(docCliente);
			login = true;
		}

		return login;
	}
	
	public Cliente getCliente(){
		return clt;
	}
	
	public boolean isAdmin(){
		//return (clt instanceof Funcionario); //o funcionario ainda n�o � um cliente;
		return false; //para teste
	}
	
	

	
}
