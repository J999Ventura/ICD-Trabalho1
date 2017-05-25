package Servidor.db;


import Protocolo.Protocolo;
import XML.XMLDoc;
import commun.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.dc.pr.PRError;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

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
        NodeList noscliente = XMLDoc.getXPath("//cliente[userName/text()='" + user + "']", db);

        if (noscliente != null) {
            try {
                Document cliente = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder().newDocument();
                Element root = cliente.createElement("protocolo");
                cliente.appendChild(root);

                root.appendChild(cliente.adoptNode(noscliente.item(0).cloneNode(true)));

                if (Objects.equals(XMLDoc.getXPathV("//tipoCliente", cliente), "Cliente Individual")){

                    String userName = XMLDoc.getXPathV("//userName", cliente);
                    String nomeCliente = XMLDoc.getXPathV("//nomeCliente", cliente);
                    String idCliente = XMLDoc.getXPathV("//idCliente", cliente);
                    String nif = XMLDoc.getXPathV("//nif", cliente);
                    String morada = XMLDoc.getXPathV("//morada", cliente);
                    String numTelefone = XMLDoc.getXPathV("//numTelefone", cliente);
                    String foto = XMLDoc.getXPathV("//foto", cliente);
                    String assinatura = XMLDoc.getXPathV("//assinatura", cliente);
                    String numCartaoCidadao = XMLDoc.getXPathV("//numCartaoCidadao", cliente);
                    String numPassaporte = XMLDoc.getXPathV("//numPassaporte", cliente);
                    String dataDeNascimento = XMLDoc.getXPathV("//dataDeNascimento", cliente);

                    Cliente novo_cli = new ClienteIndividual(userName, nomeCliente, idCliente, nif, morada, numTelefone,
                            Protocolo.imageToBase64Decode(foto), Protocolo.imageToBase64Decode(assinatura),
                            numCartaoCidadao, numPassaporte, LocalDate.parse(dataDeNascimento));



                    NodeList contas = cliente.getElementsByTagName("contas");
                    System.out.println("Total of elements : " + contas.getLength());

                    List<String> listaNomesNos = new ArrayList<>();

                    for (int i = 1; i <= contas.getLength(); i++){
                        NodeList nosconta = XMLDoc.getXPath("//cliente[userName/text() = '" + user +
                                "']/contas/conta["+ i +"]/*", cliente);
                        for (int z = 0; z < nosconta.getLength()-1; z++){
                            listaNomesNos.add(nosconta.item(z).getTextContent());
                            //System.out.println(nosconta.item(z).getTextContent());
                        }

                        String tipoConta = listaNomesNos.get(0);
                        String titular = listaNomesNos.get(1);
                        String numConta = listaNomesNos.get(2);
                        String nomeConta = listaNomesNos.get(3);
                        String nib = listaNomesNos.get(4);
                        String iban = listaNomesNos.get(5);
                        Double saldoContabilistico = Double.parseDouble(listaNomesNos.get(6));
                        Double saldoDisponivel = Double.parseDouble(listaNomesNos.get(7));
                        Double saldoAutorizado = Double.parseDouble(listaNomesNos.get(8));

                        Conta nova_conta = null;
                        listaNomesNos.clear();

                        if (Objects.equals(XMLDoc.getXPathV("//conta[numConta/text() = '"+ numConta+"']/tipoConta", cliente), "Conta a Ordem")) {
                            novo_cli.addConta(nova_conta = new Conta(numConta, nib, iban, titular,
                                    saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAORDEM));
                        } else if (Objects.equals(XMLDoc.getXPathV("//conta[numConta/text() = '"+ numConta+"']/tipoConta", cliente), "Conta a Prazo")){
                            novo_cli.addConta(nova_conta = new Conta(numConta, nib, iban, titular,
                                    saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAPRAZO));
                        } else if (Objects.equals(XMLDoc.getXPathV("//conta[numConta/text() = '"+ numConta+"']/tipoConta", cliente), "Conta Jovem")){
                            novo_cli.addConta(nova_conta = new Conta(numConta, nib, iban, titular,
                                    saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAJOVEM));
                        } else if (Objects.equals(XMLDoc.getXPathV("//conta[numConta/text() = '"+ numConta+"']/tipoConta", cliente), "Conta Ordenado")){
                            novo_cli.addConta(nova_conta = new Conta(numConta, nib, iban, titular,
                                    saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAORDENADO));
                        } else if (Objects.equals(XMLDoc.getXPathV("//conta[numConta/text() = '"+ numConta+"']/tipoConta", cliente), "Conta Poupança")){
                            novo_cli.addConta(nova_conta = new Conta(numConta, nib, iban, titular,
                                    saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAPOUPANCA));
                        }

                        //Protocolo.infoConta(nova_conta, false);
                    }





                    return Protocolo.infoCliente(novo_cli);

                } else {

                    String userName = XMLDoc.getXPathV("//userName/text()", cliente);
                    String nomeCliente = XMLDoc.getXPathV("//nomeCliente/text()", cliente);
                    String idCliente = XMLDoc.getXPathV("//idCliente/text()", cliente);
                    String nif = XMLDoc.getXPathV("//nif/text()", cliente);
                    String morada = XMLDoc.getXPathV("//morada/text()", cliente);
                    String numTelefone = XMLDoc.getXPathV("//numTelefone/text()", cliente);
                    String foto = XMLDoc.getXPathV("//foto/text()", cliente);
                    String assinatura = XMLDoc.getXPathV("//assinatura/text()", cliente);
                    String nomeResponsavel = XMLDoc.getXPathV("//nomeResponsavel/text()", cliente);
                    String cae = XMLDoc.getXPathV("//cae/text()", cliente);

                    Cliente novo_cli_emp = new ClienteEmpresarial(userName, nomeCliente, idCliente, nif, morada, numTelefone,
                            Protocolo.imageToBase64Decode(foto), Protocolo.imageToBase64Decode(assinatura),
                            nomeResponsavel, cae);

                    return Protocolo.infoCliente(novo_cli_emp);

                }

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
