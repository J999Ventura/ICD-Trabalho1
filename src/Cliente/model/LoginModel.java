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

		/* ****************** para teste na ui sem socket	************ */
		/*
		boolean login = true; 
		Document doc = pro.writeLogin(user, pass);
		System.out.println(Protocolo.getStringFromDocument(doc));
		
		boolean isLogin = true;
		if(isLogin){
			doc = pro.queryServidor("getUserInfo");
			System.out.println(Protocolo.getStringFromDocument(doc));
			
			Image assinatura = null;
	        Image foto = null;
			clt = new ClienteIndividual("joaofilipevaz","Joao Filipe Sant'Ana Ruivo Neves Vaz", "",
	                "207905835", "Avenida de Berlim Lt K", "+351963938893", foto, assinatura,
	                "123512354","PT654867321354", LocalDate.of(1981, 8, 23));
			Conta contaaordem = new Conta("contaaordem", "joaofilipevaz", 103256221,
	                0.0, "321568432513215346", TipoContaEnum.CONTAORDEM);

	        Conta contaprazo = new Conta("contaaordem", "joaofilipevaz", 103256221,
	                0.0, "321568432513215346", TipoContaEnum.CONTAPRAZO);

	        Movimento mov1 = new Movimento("pag propinas isel", LocalDate.of(2017, 6, 14),
	                LocalDate.of(2017, 6, 14), 160.0, TipoMovimentoEnum.DEBITO ,
	                "PT50321568432513215346","PT50321568432513215368453");

	        Movimento mov2 = new Movimento("ordenado", LocalDate.of(2017, 5, 30),
	                LocalDate.of(2017, 5, 30), 1000.0, TipoMovimentoEnum.CREDITO ,
	                "PT50321568432513215368453","PT50321568432513215346");

	        contaaordem.setMovimento(mov1);
	        contaaordem.setMovimento(mov2);
	        
	        List<Conta> contas = new ArrayList<>();
	        contas.add(contaaordem);
	        contas.add(contaprazo);
	        
	        clt.addContas(contas); //apenas para teste sem sockets;

			login = true;
		}
		/**************************************************/
		
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
