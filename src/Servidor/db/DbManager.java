package Servidor.db;


import Protocolo.Protocolo;
import XML.XMLDoc;
import commun.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DbManager {

    private static Document db;

    public DbManager() {
        db = readFromDB("src/servidor/db/db.xml");
    }

    private Protocolo proto = new Protocolo();

    public synchronized void writeToDB(Document d, String filename) {
        XMLDoc.writeDocument(d, filename);
    }

    private synchronized Document readFromDB(String filename) {
        return XMLDoc.parseFile(filename);
    }

    public synchronized boolean validateLogin(String user, String pass) {

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

    public synchronized Document getClientDataFromDB(String user) {
        Cliente novo_cli = null;
        NodeList noscliente = XMLDoc.getXPath("//cliente[userName/text()='" + user + "']", db);
        System.out.println("A obter dados do utilizador : " + user);

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

                    novo_cli = new ClienteIndividual(userName, nomeCliente, idCliente, nif, morada, numTelefone,
                            Protocolo.imageToBase64Decode(foto), Protocolo.imageToBase64Decode(assinatura),
                            numCartaoCidadao, numPassaporte, LocalDate.parse(dataDeNascimento));

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

                    novo_cli = new ClienteEmpresarial(userName, nomeCliente, idCliente, nif, morada, numTelefone,
                            Protocolo.imageToBase64Decode(foto), Protocolo.imageToBase64Decode(assinatura),
                            nomeResponsavel, cae);

                }


                NodeList contas = XMLDoc.getXPath("//contas/*", cliente);
                if (contas != null){

                    System.out.println("Total de contas : " + contas.getLength());

                    List<String> listaNomesNos = new ArrayList<>();

                    for (int i = 1; i < contas.getLength()+1; i++){
                        NodeList nosconta = XMLDoc.getXPath("//cliente[userName/text() = '" + user +
                                "']/contas/conta["+ i +"]/*", cliente);
                        if (nosconta != null) {
                            for (int z = 0; z < nosconta.getLength(); z++){
                                listaNomesNos.add(nosconta.item(z).getTextContent());
                                //System.out.println(nosconta.item(z).getTextContent());
                            }
                        }

                        String tipoConta = listaNomesNos.get(0);
                        String titular = listaNomesNos.get(1);
                        String numConta = listaNomesNos.get(2);
                        String nomeConta = listaNomesNos.get(3);
                        String nib = listaNomesNos.get(4);
                        String iban = listaNomesNos.get(5);
                        Double saldoContabilistico = Double.parseDouble(listaNomesNos.get(6));
                        Double saldoDisponivel = Double.parseDouble(listaNomesNos.get(7));
                        //Double saldoAutorizado = Double.parseDouble(listaNomesNos.get(8));

                        Conta nova_conta;
                        listaNomesNos.clear();

                        if (tipoConta.equals(TipoContaEnum.CONTAORDEM.getTipo())) {
                            nova_conta = new Conta(numConta, titular,
                                    saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAORDEM);
                        } else if (tipoConta.equals(TipoContaEnum.CONTAPRAZO.getTipo())){
                            nova_conta = new Conta(numConta, titular,
                                    saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAPRAZO);
                        } else if (tipoConta.equals(TipoContaEnum.CONTAJOVEM.getTipo())){
                            nova_conta = new Conta(numConta, titular,
                                    saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAJOVEM);
                        } else if (tipoConta.equals(TipoContaEnum.CONTAORDENADO.getTipo())){
                            nova_conta = new Conta(numConta, titular,
                                    saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAORDENADO);
                        } else {
                            nova_conta = new Conta(numConta, titular,
                                    saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAPOUPANCA);
                        }

                        NodeList movimentos = XMLDoc.getXPath("//conta[numConta/text() = '" + numConta + "']/movimentos/*", cliente);
                        if (movimentos != null) {
                            System.out.println("Total de Movimentos da conta " + numConta + " : " + movimentos.getLength());

                            for (int t = 1; t < movimentos.getLength()+1; t++) {
                                NodeList nosMovimento = XMLDoc.getXPath("//cliente[userName/text() = '" + user +
                                        "']/contas/conta[numConta/text() = '"+numConta+"']/movimentos/movimento[" + t + "]/*", cliente);
                                if (nosMovimento != null) {
                                    for (int z = 0; z < nosMovimento.getLength(); z++) {
                                        listaNomesNos.add(nosMovimento.item(z).getTextContent());
                                    }
                                }

                                LocalDate dataValor = LocalDate.parse(listaNomesNos.get(0));
                                LocalDate dataLancamento = LocalDate.parse(listaNomesNos.get(1));
                                String descricao = listaNomesNos.get(2);
                                double valor = Double.parseDouble(listaNomesNos.get(3));
                                String tipomovimento = listaNomesNos.get(4);
                                String contaRemetente = listaNomesNos.get(5);
                                String contaDestino = listaNomesNos.get(6);

                                listaNomesNos.clear();

                                if (tipomovimento.equals(TipoMovimentoEnum.CREDITO.getTipo())) {

                                    nova_conta.addMovimento(new Movimento(descricao, dataValor, dataLancamento, valor,
                                            TipoMovimentoEnum.CREDITO, contaDestino, contaRemetente));

                                } else {
                                    nova_conta.addMovimento(new Movimento(descricao, dataValor, dataLancamento, valor,
                                            TipoMovimentoEnum.DEBITO, contaDestino, contaRemetente));
                                }
                            }
                        }
                        novo_cli.addConta(nova_conta);
                    }
                }

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
        return proto.infoCliente(novo_cli);
    }



}
