package Cliente;

import Protocolo.Protocolo;
import XML.XMLDoc;
import org.w3c.dom.Document;

import Cliente.control.ClienteSimplesTCP;

public class ControllerCliente {

    public final static String DEFAULT_HOSTNAME = "localhost";
    public final static int DEFAULT_PORT = 5025;

    public static void main(String[] args) {

        String host = DEFAULT_HOSTNAME;  // M�quina onde reside a aplica��o servidora
        int port = DEFAULT_PORT;      // Porto da aplica��o servidora

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

    } // end main
    
    
}
