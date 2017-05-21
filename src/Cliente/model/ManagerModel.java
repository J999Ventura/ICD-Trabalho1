package Cliente.model;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;

import Cliente.control.ClienteSimplesTCP;
import Protocolo.Protocolo;
import commun.Cliente;
import commun.TipoContaEnum;

public class ManagerModel {
	
	private ClienteSimplesTCP tcp;
	private Protocolo pro;
	private XMLInteration xmlInt;
	private Cliente user;
	
	public ManagerModel(ClienteSimplesTCP clienteTCP, Cliente user){
		this.tcp = clienteTCP;
		this.user = user;
	}

	public boolean logout() {
		/*
	    Document doc = pro.logout(true);
		tcp.writeSocket(doc);
		
		return getAnswerFromSocket(doc);
		*/
		return true;
	}
	
	public boolean createAccountRequest(String nib, String accountName, String accountType){
		/*
	    Document doc = pro.criarConta(nib, accountName, accountType);
		tcp.writeSocket(doc);
		
		return getAnswerFromSocket(doc);
		*/
		return false;
	}

	public boolean closeAccountRequest(String nib, String accountNumber) {
		/*
	    Document doc = pro.fecharConta(nib, accountNumber);
		tcp.writeSocket(doc);
		
		return getAnswerFromSocket(doc);
		*/
		return false;
	}

	public boolean createLoanRequest(String nib, String amount, String payment, String rate) {
		/*
	    Document doc = pro.criarConta(nib, amount, payment, rate);
		tcp.writeSocket(doc);
		
		return getAnswerFromSocket(doc);
		*/
		return false;
	}

	public List<String> getAccountTypes() {
		List<String> types = new LinkedList<String>();
		types.add(TipoContaEnum.CONTAJOVEM.getTipo());
		types.add(TipoContaEnum.CONTAORDEM.getTipo());
		types.add(TipoContaEnum.CONTAORDENADO.getTipo());
		types.add(TipoContaEnum.CONTAPOUPANCA.getTipo());
		types.add(TipoContaEnum.CONTAPRAZO.getTipo());
		return types;
	}
	
	private boolean getAnswerFromSocket(Document doc){
		String readedMSG = tcp.readSocket();
		doc = pro.convertStringToDocument(readedMSG);
		return xmlInt.getLoginAnswer(doc);
	}

}
