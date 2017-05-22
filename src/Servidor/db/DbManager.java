package Servidor.db;


import Protocolo.Protocolo;
import XML.XMLDoc;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.dc.pr.PRError;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DbManager {

    public static synchronized void writeToDB(Document d, String filename) {
        XMLDoc.writeDocument(d, filename);
    }

    public static synchronized Document readFromDB(String filename) {
        return XMLDoc.parseFile(filename);
    }

    public static synchronized boolean validateLogin(String user, String pass, Document db) {

        String userInDB = XMLDoc.getXPathV("//user/username[text() = '" + user + "']", db);
        if (userInDB != null) {
            String passInDB = XMLDoc.getXPathV("//user[username/text() = '" + userInDB + "']/pass[text() = '"
                    + pass + "']", db);
            if (passInDB != null) {
                if ((userInDB.equals(user)) && (passInDB.equals(pass))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static synchronized Document getClientDataFromDB(String user, Document db) {
        NodeList noscliente = XMLDoc.getXPath("//cliente[userName/text()='" + user + "']/*", db);
        NodeList nosconta = XMLDoc.getXPath("//cliente[userName/text()='" + user + "']/contas/*", db);
        if (noscliente != null) {
            try {
                Document cliente = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder().newDocument();
                Element root = cliente.createElement("protocolo");
                cliente.appendChild(root);
                //root.appendChild(cliente.adoptNode(noscliente.item(0).cloneNode(true)));

                for (int i = 0; i < noscliente.getLength(); i++) {
                    //root.appendChild(noscliente.item(i).cloneNode(true));
                    Node node = noscliente.item(i);
                    //Node copyNode = cliente.adoptNode(node.cloneNode(true));
                    Node copyNode = cliente.importNode(node, true);
                    root.appendChild(copyNode);
                }

                for (int i = 0; i < nosconta.getLength(); i++) {
                    //root.appendChild(noscliente.item(i).cloneNode(true));
                    Node node = nosconta.item(i);
                    //Node copyNode = cliente.adoptNode(node.cloneNode(true));
                    Node copyNode = cliente.importNode(node, true);
                    cliente.getLastChild().appendChild(copyNode);
                }
                Protocolo.prettyPrint(cliente);
                XMLDoc.writeDocument(cliente, "resposta_dados.xml");
                return cliente;
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
