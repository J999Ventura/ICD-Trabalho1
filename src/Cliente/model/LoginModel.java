package Cliente.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import org.w3c.dom.Document;

import Cliente.control.ClienteSimplesTCP;
import Protocolo.Protocolo;

public class LoginModel {

	private HashMap<String, User> credencials = new HashMap<String,User>();
	private String admin = "admin";
	private boolean isAdmin = true;
	private String currentUserName;
	
	public LoginModel(){
		initCommunicationClientServer();
	}

	private void initCommunicationClientServer() {
		credencials.put("client", new User("client", "client", false, "joao", "24", "9/9/92", "rua x"));
		credencials.put("admin", new User("admin", "admin", true, "joao", "26", "24/5/91", "rua y"));
	}
	
	public boolean validateLogin(String user, String pass){
		boolean login = false;
		User userCredencials = credencials.get(user);
		currentUserName = "";
		if(userCredencials != null && userCredencials.getUserName().equals(user) && userCredencials.getPassword().equals(pass)){
			login = true;
			isAdmin = userCredencials.getIsAdmin();
			currentUserName = user;
		}
			
		return login;
	}
	
	public User getUser(){
		//enviar mensagem para o servidor com o user name
		//receber alguma coisa do servidor
		return credencials.get(currentUserName);
	}
	
	public boolean isAdmin(){
		
		return isAdmin;
	}
	
	
	

	
}
