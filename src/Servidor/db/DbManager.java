package Servidor.db;


import XML.XMLDoc;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class DbManager {

    public static synchronized void writeToDB(Document d, String filename){
        XMLDoc.writeDocument(d, filename);
    }

    public static synchronized Document readFromDB(String filename){
        return XMLDoc.parseFile(filename);
    }

    public static synchronized boolean validateLogin(String user, String pass, Document db){
        NodeList users = XMLDoc.getXPath("//user", db);
        for (int i =0; i < users.getLength(); i++){
            if (users.item(i).getFirstChild().getTextContent().equals(user) && users.item(i).getLastChild().getTextContent().equals(pass)){
                return true;
            }
        }
        return false;
    }
}
