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
        Protocolo proto = new Protocolo();
        ClienteSimplesTCP clienteTCP = new ClienteSimplesTCP();
        clienteTCP.openSocket(DEFAULT_HOSTNAME, DEFAULT_PORT);

        //user e pass para teste
        String u = "joaofilipevaz";
        String pass = "asdwf23425";

        Document d = proto.writeLogin(u, pass);

        clienteTCP.writeSocket(d);

        //while(session){
            clienteTCP.readSocket();
        //}

        Protocolo.removeChilds(d.getDocumentElement());

        Document d2 = proto.queryServidor("getUserInfo");

        clienteTCP.writeSocket(d2);

        String dados = clienteTCP.readSocket();
        System.out.println(dados);
        //Document d4 = XMLDoc.parseFile("resposta_dados.xml");
        Document infoCliente = Protocolo.convertStringToDocument(dados);
        XMLDoc.writeDocument(infoCliente, "src/Protocolo/xml_xsd_valid/xml_infoCliente.xml");


        while(session) {
            clienteTCP.readSocket();
        }

        clienteTCP.closeSocket();

        //

    } // end main
}
