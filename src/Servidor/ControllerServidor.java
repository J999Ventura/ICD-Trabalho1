package Servidor;

import Protocolo.Protocolo;
import Servidor.db.DbManager;
import XML.XMLDoc;
import org.w3c.dom.Document;

public class ControllerServidor {

    private final static int DEFAULT_PORT = 5025;
    private static Protocolo log;
    private static Document db;
    private static String user = "";

    static String gestorComunicacao(String msg){

        Document d = Protocolo.convertStringToDocument(msg);
        String tipoPedido = XMLDoc.getXPathV("//tipopedido",d);

        if (tipoPedido != null) {

            switch (tipoPedido) {
                case "login":
                    user = XMLDoc.getXPathV("//user", d);
                    String pass = XMLDoc.getXPathV("//pass", d);
                    System.out.println(user);
                    System.out.println(pass);
                    if (DbManager.validateLogin(user, pass, db)) {
                        return Protocolo.getStringFromDocument(log.respostaServidor(true));
                    } else {
                        return Protocolo.getStringFromDocument(log.respostaServidor(false));
                    }

                case "getUserInfo":
                    String resposta = Protocolo.getStringFromDocument(DbManager.getClientDataFromDB(user, db));
                    if (resposta != null) {
                        return resposta;
                    } else{
                        return Protocolo.getStringFromDocument(log.respostaServidor(false));
                    }

                case "infoManager":
                    break;

                case "movimentoConta":
                    break;

                case "abrirConta":
                    break;

                case "fecharConta":
                    break;

                case "atribuirEmprestimo":
                    break;

                case "changeUserName":
                    break;

                case "logout":
                    break;

                default:
                    break;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        log = new Protocolo();
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