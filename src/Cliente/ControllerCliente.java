package Cliente;

import Cliente.control.ClienteSimplesTCP;
import Protocolo.Protocolo;
import XML.XMLDoc;
import org.w3c.dom.Document;

public class ControllerCliente {

    private final static String DEFAULT_HOSTNAME = "localhost";
    private final static int DEFAULT_PORT = 5025;
    private static boolean session = true;

    public static void end(){
        session = false;
    }

    public static void main(String[] args) {

        ClienteSimplesTCP clienteTCP = new ClienteSimplesTCP();
        clienteTCP.openSocket(DEFAULT_HOSTNAME, DEFAULT_PORT);

        //user e pass para teste
        String u = "joaofilipevaz";
        String pass = "asdwf23425";

        Protocolo log = new Protocolo();

        Document d = log.writeLogin(u, pass);

        clienteTCP.writeSocket(d);

        //while(session){
            clienteTCP.readSocket();
        //}

        log.removeChilds(d.getDocumentElement());

        Document d2 = log.queryServidor("getUserInfo");

        clienteTCP.writeSocket(d2);

        String dados = clienteTCP.readSocket();
        System.out.println(dados);
        //Document d4 = XMLDoc.parseFile("resposta_dados.xml");
        Document infoCliente = log.convertStringToDocument(dados);

        while(session) {
            clienteTCP.readSocket();
        }

        clienteTCP.closeSocket();

        //

    } // end main
}
