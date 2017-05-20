package Servidor;

import Protocolo.Protocolo;
import Servidor.db.DbManager;
import XML.XMLDoc;
import org.w3c.dom.Document;

public class ControllerServidor {

    private final static int DEFAULT_PORT = 5025;
    private static Protocolo log = new Protocolo();
    private static int estado;
    private static final int login = 0;
    private static final int clienteDataTransfer = 1;
    private static final int managerDataTransfer = 2;
    private static Document db;

    public static String gestorComunicacao(String msg){

        Document d = Protocolo.convertStringToDocument(msg);
        String tipoPedido = XMLDoc.getXPathV("//tipo",d);
        if (tipoPedido.equals("login")){
            estado = login;
        }

        switch (estado){
            case login:

                String user = XMLDoc.getXPathV("//user",d);
                String pass = XMLDoc.getXPathV("//pass",d);
                System.out.println(user);
                System.out.println(pass);
                if (DbManager.validateLogin(user, pass, db)) {
                    return Protocolo.getStringFromDocument(log.loginReply(true));
                } else {
                    return Protocolo.getStringFromDocument(log.loginReply(false));
                }

            case clienteDataTransfer:
                break;

            case managerDataTransfer:
                break;

            default:
                break;
        }
        return null;
    }

    public static void main(String[] args) {
        db = DbManager.readFromDB("src/servidor/db/db.xml");

        ServidorTCPConcorrente sv = new ServidorTCPConcorrente(DEFAULT_PORT);
        new Thread(sv).start();

        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println("Stopping Server");
        //sv.stop();
    }
}