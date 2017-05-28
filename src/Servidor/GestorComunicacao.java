package Servidor;

import Protocolo.Protocolo;
import Servidor.db.DbManager;
import XML.XMLDoc;
import org.w3c.dom.Document;

class GestorComunicacao {

    private Protocolo proto;
    private String user;
    private boolean autorizado;

    GestorComunicacao() {
        this.proto = new Protocolo();
        this.user = "";
        this.autorizado = false;
    }

    String trataPedido(String pedido, DbManager dbManager){

        Document d = Protocolo.convertStringToDocument(pedido);
        String tipoPedido = XMLDoc.getXPathV("//tipopedido",d);

        if (tipoPedido != null) {

            switch (tipoPedido) {
                case "login":
                    user = XMLDoc.getXPathV("//user", d);
                    String pass = XMLDoc.getXPathV("//pass", d);
                    System.out.println(user);
                    System.out.println(pass);
                    if (dbManager.validateLogin(user, pass)) {
                        autorizado = true;
                        return Protocolo.getStringFromDocument(proto.respostaServidor(true));
                    } else {
                        autorizado = false;
                        return Protocolo.getStringFromDocument(proto.respostaServidor(false));
                    }

                case "getUserInfo":
                    if (autorizado) {
                        String resposta = Protocolo.getStringFromDocument(dbManager.getClientDataFromDB(user));
                        if (resposta != null) {
                            return resposta;
                        } else {
                            return Protocolo.getStringFromDocument(proto.respostaServidor(false));
                        }
                    }

                case "infoManager":
                    if (autorizado) {
                        return "";

                    }
                    break;

                case "movimentoConta":
                    if (autorizado) {
                        return "";

                    }
                    break;

                case "abrirConta":
                    if (autorizado) {
                        return "";

                    }
                    break;

                case "fecharConta":
                    if (autorizado) {
                        return "";

                    }
                    break;

                case "atribuirEmprestimo":
                    if (autorizado) {
                        return "";

                    }
                    break;

                case "changeUserName":
                    if (autorizado) {

                        return "";
                    }
                    break;

                case "logout":
                    if (autorizado) {
                        System.out.println("Stopping Server");
                        autorizado = false;
                        return "logout";
                    }
                    break;

                default:
                    break;
            }
        }
        return null;
    }
}