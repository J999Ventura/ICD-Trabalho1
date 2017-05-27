package Protocolo;

import XML.XMLDoc;
import commun.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.*;
import java.util.List;


public class Protocolo {

    private Document D = null; // representa a arvore DOM com o login
    private Element protocol_tag;
    private DocumentBuilder builder;

    public Protocolo() {
        DocumentBuilderFactory factory;
        builder = null;

        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Metodos do Cliente
     */

    public Document criarConta(String nib, String newAccountName, String newAccountType){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element tipo_pedido = D.createElement("tipopedido");
            Element nib_tag = D.createElement("nib");
            Element newAccountName_tag = D.createElement("newAccountName");
            Element newAccountType_tag = D.createElement("newAccountType");

            tipo_pedido.setTextContent("abrirConta");
            nib_tag.setTextContent(nib);
            newAccountName_tag.setTextContent(newAccountName);
            newAccountType_tag.setTextContent(newAccountType);

            protocol_tag.appendChild(tipo_pedido);
            protocol_tag.appendChild(nib_tag);
            protocol_tag.appendChild(newAccountName_tag);
            protocol_tag.appendChild(newAccountType_tag);
        }

        return D;
    }

    public Document fecharConta(String nib, String accountNumber){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element tipo_pedido = D.createElement("tipopedido");
            Element nib_tag = D.createElement("nib");
            Element accountNumber_tag = D.createElement("accountNumber");

            tipo_pedido.setTextContent("fecharConta");
            nib_tag.setTextContent(nib);
            accountNumber_tag.setTextContent(accountNumber);

            protocol_tag.appendChild(tipo_pedido);
            protocol_tag.appendChild(nib_tag);
            protocol_tag.appendChild(accountNumber_tag);
        }

        return D;
    }

    public Document pedirEmprestimo(String nib, String loanAmount, String payment, String rate){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element tipo_pedido = D.createElement("tipopedido");
            Element nib_tag = D.createElement("nib");
            Element loanAmount_tag = D.createElement("loanAmount");
            Element payment_tag = D.createElement("payment");
            Element rate_tag = D.createElement("rate");

            tipo_pedido.setTextContent("abrirConta");
            nib_tag.setTextContent(nib);
            loanAmount_tag.setTextContent(loanAmount);
            payment_tag.setTextContent(payment);
            rate_tag.setTextContent(rate);

            protocol_tag.appendChild(tipo_pedido);
            protocol_tag.appendChild(nib_tag);
            protocol_tag.appendChild(loanAmount_tag);
            protocol_tag.appendChild(payment_tag);
            protocol_tag.appendChild(rate_tag);
        }

        return D;
    }

    public Document writeLogin(String user, String pass){

        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element tipo_pedido = D.createElement("tipopedido");
            Element user_tag = D.createElement("user");
            Element pass_tag = D.createElement("pass");

            tipo_pedido.setTextContent("login");
            user_tag.setTextContent(user);
            pass_tag.setTextContent(pass);

            protocol_tag.appendChild(tipo_pedido);
            protocol_tag.appendChild(user_tag);
            protocol_tag.appendChild(pass_tag);
        }

        return D;
    }



    public Document fecharConta(String numConta){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element tipo_pedido = D.createElement("tipopedido");
            Element numConta_tag = D.createElement("user");

            tipo_pedido.setTextContent("fecharconta");
            numConta_tag.setTextContent(numConta);

            protocol_tag.appendChild(tipo_pedido);
            protocol_tag.appendChild(numConta_tag);
        }
        return D;
    }

    public Document atribuirEmprestimo(String user, String pass){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element tipo_pedido = D.createElement("tipopedido");
            Element user_tag = D.createElement("user");
            Element pass_tag = D.createElement("pass");

            tipo_pedido.setTextContent("login");
            user_tag.setTextContent(user);
            pass_tag.setTextContent(pass);

            protocol_tag.appendChild(tipo_pedido);
            protocol_tag.appendChild(user_tag);
            protocol_tag.appendChild(pass_tag);
        }
        return D;
    }

    public Document queryServidor(String pedido){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element tipo_pedido = D.createElement("tipopedido");

            tipo_pedido.setTextContent(pedido);
            protocol_tag.appendChild(tipo_pedido);
        }
        return D;
    }

    public Document makeTransfer(String nib, double value){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element transfer_tag = D.createElement("transfer");
            Element nib_tag = D.createElement("nib");
            Element value_tag = D.createElement("value");

            nib_tag.setTextContent(nib);
            value_tag.setTextContent(""+value);

            protocol_tag.appendChild(transfer_tag);
            transfer_tag.appendChild(nib_tag);
            transfer_tag.appendChild(value_tag);
        }
        return D;
    }

    public Document changeUserName(String name){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element userName_tag = D.createElement("changeUserName");

            userName_tag.setTextContent(name);

            protocol_tag.appendChild(userName_tag);
        }
        return D;
    }


    /**
     *  Metodos do Servidor
     */


    public Document respostaServidor(boolean validation){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element ok_tag = D.createElement("OK");

            ok_tag.setTextContent(validation ? "true" : "false");

            protocol_tag.appendChild(ok_tag);
        }
        return D;
    }

    public Document logout(boolean validation){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element logout_tag = D.createElement("logout");
            Element ok_tag = D.createElement("OK");

            ok_tag.setTextContent(validation ? "true" : "false");

            protocol_tag.appendChild(logout_tag);
            logout_tag.appendChild(ok_tag);
        }
        return D;
    }

    public Document nameChanged(boolean validation){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element name_tag = D.createElement("nameChanged");
            Element ok_tag = D.createElement("OK");

            ok_tag.setTextContent(validation ? "true" : "false");

            protocol_tag.appendChild(name_tag);
            name_tag.appendChild(ok_tag);
        }
        return D;
    }

    public Document transferMade(boolean validation){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element transfer_tag = D.createElement("transferMade");
            Element ok_tag = D.createElement("OK");

            ok_tag.setTextContent(validation ? "true" : "false");

            protocol_tag.appendChild(transfer_tag);
            transfer_tag.appendChild(ok_tag);
        }
        return D;
    }

    public Document pedirCliente(String user){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element clientePedido_tag = D.createElement("clientePedido");
            Element actual_tag = D.createElement("actual");

            actual_tag.setTextContent(user);

            protocol_tag.appendChild(clientePedido_tag);
            clientePedido_tag.appendChild(actual_tag);
        }
        return D;
    }


    public Document infoCliente(Cliente cliente){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element cliente_tag = D.createElement("cliente");
            Element userName_tag = D.createElement("userName");
            Element nomeCliente_tag = D.createElement("nomeCliente");
            Element idCliente_tag = D.createElement("idCliente");
            Element nif_tag = D.createElement("nif");
            Element morada_tag = D.createElement("morada");
            Element numTelefone_tag = D.createElement("numTelefone");
            Element foto_tag = D.createElement("foto");
            Element assinatura_tag = D.createElement("assinatura");
            Element tipoCliente_tag = D.createElement("tipoCliente");
            Element contas_tag = D.createElement("contas");
            Element emprestimo_tag = D.createElement("emprestimos");

            userName_tag.setTextContent(cliente.getUserName());
            nomeCliente_tag.setTextContent(cliente.getNomeCliente());
            idCliente_tag.setTextContent(cliente.getIdCliente());
            nif_tag.setTextContent(cliente.getNif());
            morada_tag.setTextContent(cliente.getMorada());
            numTelefone_tag.setTextContent(cliente.getNumTelefone());
            foto_tag.setTextContent(imageToBase64Encode(cliente.getFoto()));
            assinatura_tag.setTextContent(imageToBase64Encode(cliente.getAssinatura()));

            protocol_tag.appendChild(cliente_tag);
            cliente_tag.appendChild(userName_tag);
            cliente_tag.appendChild(nomeCliente_tag);
            cliente_tag.appendChild(idCliente_tag);
            cliente_tag.appendChild(nif_tag);
            cliente_tag.appendChild(morada_tag);
            cliente_tag.appendChild(numTelefone_tag);
            cliente_tag.appendChild(foto_tag);
            cliente_tag.appendChild(assinatura_tag);

            if (cliente instanceof ClienteIndividual){

                Element numCartaoCidadao_tag = D.createElement("numCartaoCidadao");
                Element numPassaporte_tag = D.createElement("numPassaporte");
                Element dataDeNascimento_tag = D.createElement("dataDeNascimento");

                numCartaoCidadao_tag.setTextContent(((ClienteIndividual) cliente).getNumCartaoCidadao());
                numPassaporte_tag.setTextContent(((ClienteIndividual) cliente).getNumPassaporte());
                dataDeNascimento_tag.setTextContent(((ClienteIndividual) cliente).getDataDeNascimento().toString());
                tipoCliente_tag.setTextContent(cliente.getTipoCliente().getTipo());

                cliente_tag.appendChild(numCartaoCidadao_tag);
                cliente_tag.appendChild(numPassaporte_tag);
                cliente_tag.appendChild(dataDeNascimento_tag);
                cliente_tag.appendChild(tipoCliente_tag);
                cliente_tag.appendChild(contas_tag);
                cliente_tag.appendChild(emprestimo_tag);

            } else if (cliente instanceof ClienteEmpresarial) {

                Element nomeResponsavel_tag = D.createElement("nomeResponsavel");
                Element cae_tag = D.createElement("cae");

                nomeResponsavel_tag.setTextContent(((ClienteEmpresarial) cliente).getNomeResponsavel());
                cae_tag.setTextContent(((ClienteEmpresarial) cliente).getCae());
                tipoCliente_tag.setTextContent(cliente.getTipoCliente().getTipo());

                cliente_tag.appendChild(nomeResponsavel_tag);
                cliente_tag.appendChild(cae_tag);
                cliente_tag.appendChild(tipoCliente_tag);
                cliente_tag.appendChild(contas_tag);
                cliente_tag.appendChild(emprestimo_tag);

            }

            if (!cliente.getContas().isEmpty()){

                for (int i = 0; i < cliente.getContas().size(); i++){

                    Conta conta = cliente.getContas().get(i);

                    Element conta_tag = D.createElement("conta");
                    Element tipoConta_tag = D.createElement("tipoConta");
                    Element titular_tag = D.createElement("titular");
                    Element numConta_tag = D.createElement("numConta");
                    Element nomeConta_tag = D.createElement("nomeConta");
                    Element nib_tag = D.createElement("nib");
                    Element iban_tag = D.createElement("iban");
                    Element saldoContabilistico_tag = D.createElement("saldoContabilistico");
                    Element saldoDisponivel_tag = D.createElement("saldoDisponivel");
                    Element saldoAutorizado_tag = D.createElement("saldoAutorizado");
                    Element movimentos_tag = D.createElement("movimentos");

                    //
                    tipoConta_tag.setTextContent(conta.getTipoConta().getTipo());
                    titular_tag.setTextContent(conta.getTitular());
                    numConta_tag.setTextContent(conta.getNumConta());
                    nomeConta_tag.setTextContent(conta.getNomeConta());
                    nib_tag.setTextContent(conta.getNib());
                    iban_tag.setTextContent(conta.getIban());
                    saldoContabilistico_tag.setTextContent(Double.toString(conta.getSaldoContabilistico()));
                    saldoDisponivel_tag.setTextContent(Double.toString(conta.getSaldoDisponivel()));
                    saldoAutorizado_tag.setTextContent(Double.toString(conta.getSaldoAutorizado()));

                    contas_tag.appendChild(conta_tag);
                    conta_tag.appendChild(tipoConta_tag);
                    conta_tag.appendChild(titular_tag);
                    conta_tag.appendChild(numConta_tag);
                    conta_tag.appendChild(nomeConta_tag);
                    conta_tag.appendChild(nib_tag);
                    conta_tag.appendChild(iban_tag);
                    conta_tag.appendChild(saldoContabilistico_tag);
                    conta_tag.appendChild(saldoDisponivel_tag);
                    conta_tag.appendChild(saldoAutorizado_tag);
                    conta_tag.appendChild(movimentos_tag);


                    if (conta.getMovimentos() != null) {
                        for (int m = 0; m < conta.getMovimentos().size(); m++) {

                            Movimento mov = conta.getMovimentos().get(m);

                            Element movimento_tag = D.createElement("movimento");
                            Element descricao_tag = D.createElement("descricao");
                            Element dataValor_tag = D.createElement("dataValor");
                            Element dataLancamento_tag = D.createElement("dataLancamento");
                            Element valor_tag = D.createElement("valor");
                            Element tipoMovimento_tag = D.createElement("tipomovimento");
                            Element contaRemetente_tag = D.createElement("contaRemetente");
                            Element contaDestino_tag = D.createElement("contaDestino");

                            descricao_tag.setTextContent(mov.getDescricao());
                            dataValor_tag.setTextContent(mov.getDataValor().toString());
                            dataLancamento_tag.setTextContent(mov.getDataLancamento().toString());
                            valor_tag.setTextContent(Double.toString(mov.getValor()));
                            tipoMovimento_tag.setTextContent(mov.getTipoMovimento().getTipo());
                            contaDestino_tag.setTextContent(mov.getContaDestino());
                            contaRemetente_tag.setTextContent(mov.getContaRemetente());

                            movimentos_tag.appendChild(movimento_tag);
                            movimento_tag.appendChild(dataValor_tag);
                            movimento_tag.appendChild(dataLancamento_tag);
                            movimento_tag.appendChild(descricao_tag);
                            movimento_tag.appendChild(valor_tag);
                            movimento_tag.appendChild(tipoMovimento_tag);
                            movimento_tag.appendChild(contaRemetente_tag);
                            movimento_tag.appendChild(contaDestino_tag);
                        }
                    }


                    //Document infoConta = infoConta(cliente.getContas().get(i), false);
                    //Node conta = infoConta.getFirstChild().cloneNode(true);
                    //D.adoptNode(conta);
                    //D.importNode(conta, true);
                    //D.appendChild(conta);

                    //Node conta = D.importNode(infoConta(cliente.getContas().get(i), false).getDocumentElement(), true);
                    //Node conta = infoConta(cliente.getContas().get(i), false).getDocumentElement().cloneNode(true);
                    //D.adoptNode(conta);

                    // Make the new node an actual item in the target document
                    //cliente_tag.appendChild(conta);

                    //conta_tag.appendChild(conta);
/*
                    NodeList listaNosConta = infoConta(cliente.getContas().get(i), false).getChildNodes();
                    for (int r = 0; r <= listaNosConta.getLength(); r++){


                        Element teste =
                        cliente_tag.appendChild(listaNosConta.item(r));
                    }
*/
                }
            }

            if (!cliente.getEmprestimos().isEmpty()){

                for (int i = 0; i <= cliente.getEmprestimos().size(); i++){
                    cliente_tag.appendChild(infoEmprestimo(cliente.getEmprestimos()));
                }
            }
        }

        XMLDoc.writeDocument(D, "teste.xml");

        return D;
    }

    public Document infoConta(Conta conta, boolean query){
        if (builder != null) {
            D = builder.newDocument();

            if (query) {
                protocol_tag = D.createElement("protocolo");
                D.appendChild(protocol_tag);
                Element tipo_pedido = D.createElement("tipopedido");
                tipo_pedido.setTextContent("tipopedido");
                protocol_tag.appendChild(tipo_pedido);
            }

            Element conta_tag = D.createElement("conta");
            Element tipoConta_tag = D.createElement("tipoConta");
            Element titular_tag = D.createElement("titular");
            Element numConta_tag = D.createElement("numConta");
            Element nomeConta_tag = D.createElement("nomeConta");
            Element nib_tag = D.createElement("nib");
            Element iban_tag = D.createElement("iban");
            Element saldoContabilistico_tag = D.createElement("saldoContabilistico");
            Element saldoDisponivel_tag = D.createElement("saldoDisponivel");
            Element saldoAutorizado_tag = D.createElement("saldoAutorizado");
            Element movimentos_tag = D.createElement("movimentos");

            //
            tipoConta_tag.setTextContent(conta.getTipoConta().getTipo());
            titular_tag.setTextContent(conta.getTitular());
            numConta_tag.setTextContent(conta.getNumConta());
            nomeConta_tag.setTextContent(conta.getNomeConta());
            nib_tag.setTextContent(conta.getNib());
            iban_tag.setTextContent(conta.getIban());
            saldoContabilistico_tag.setTextContent(Double.toString(conta.getSaldoContabilistico()));
            saldoDisponivel_tag.setTextContent(Double.toString(conta.getSaldoDisponivel()));
            saldoAutorizado_tag.setTextContent(Double.toString(conta.getSaldoAutorizado()));

            //
            if (query){
                protocol_tag.appendChild(conta_tag);
            } else {
                D.appendChild(conta_tag);
            }

            conta_tag.appendChild(tipoConta_tag);
            conta_tag.appendChild(titular_tag);
            conta_tag.appendChild(numConta_tag);
            conta_tag.appendChild(nomeConta_tag);
            conta_tag.appendChild(nib_tag);
            conta_tag.appendChild(iban_tag);
            conta_tag.appendChild(saldoContabilistico_tag);
            conta_tag.appendChild(saldoDisponivel_tag);
            conta_tag.appendChild(saldoAutorizado_tag);
            conta_tag.appendChild(movimentos_tag);

            if (conta.getMovimentos() != null) {
                for (int i = 0; i < conta.getMovimentos().size(); i++) {

                    Element movimento_tag = D.createElement("movimento");
                    Element descricao_tag = D.createElement("descricao");
                    Element dataValor_tag = D.createElement("dataValor");
                    Element dataLancamento_tag = D.createElement("dataLancamento");
                    Element valor_tag = D.createElement("valor");
                    Element tipoMovimento_tag = D.createElement("tipomovimento");
                    Element contaRemetente_tag = D.createElement("contaRemetente");
                    Element contaDestino_tag = D.createElement("contaDestino");

                    descricao_tag.setTextContent(conta.getMovimentos().get(i).getDescricao());
                    dataValor_tag.setTextContent(conta.getMovimentos().get(i).getDataValor().toString());
                    dataLancamento_tag.setTextContent(conta.getMovimentos().get(i).getDataLancamento().toString());
                    valor_tag.setTextContent(Double.toString(conta.getMovimentos().get(i).getValor()));
                    tipoMovimento_tag.setTextContent(conta.getMovimentos().get(i).getTipoMovimento().getTipo());
                    contaDestino_tag.setTextContent(conta.getMovimentos().get(i).getContaDestino());
                    contaRemetente_tag.setTextContent(conta.getMovimentos().get(i).getContaRemetente());

                    movimentos_tag.appendChild(movimento_tag);

                    movimento_tag.appendChild(dataValor_tag);
                    movimento_tag.appendChild(dataLancamento_tag);
                    movimento_tag.appendChild(descricao_tag);
                    movimento_tag.appendChild(valor_tag);
                    movimento_tag.appendChild(tipoMovimento_tag);
                    movimento_tag.appendChild(contaRemetente_tag);
                    movimento_tag.appendChild(contaDestino_tag);
                }
            }
        }
        return D;
    }



    public Document infoEmprestimo(List<Emprestimo> list){
        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

            Element emprestimos_tag = D.createElement("emprestimos");

            protocol_tag.appendChild(emprestimos_tag);

            if (list != null) {

                for (int i = 0; i < list.size(); i++) {

                    Element emprestimo_tag = D.createElement("emprestimo");
                    Element valorTotal_tag = D.createElement("valorTotal");
                    Element nomeConta = D.createElement("nomeDeConta");
                    Element emFalta = D.createElement("emFalta");
                    Element juros = D.createElement("juros");
                    Element mensal = D.createElement("mensal");
                    Element timeToPay = D.createElement("timeToPay");

                    valorTotal_tag.setTextContent(""+list.get(i).getValorTotal());
                    emFalta.setTextContent(""+list.get(i).getEmFalta());
                    juros.setTextContent(""+list.get(i).getJuros());
                    timeToPay.setTextContent(list.get(i).getTempoRestante());
                    mensal.setTextContent(""+list.get(i).getValorMensal());
                    nomeConta.setTextContent(list.get(i).getNomeConta());

                    emprestimos_tag.appendChild(emprestimo_tag);

                    emprestimo_tag.appendChild(nomeConta);
                    emprestimo_tag.appendChild(valorTotal_tag);
                    emprestimo_tag.appendChild(emFalta);
                    emprestimo_tag.appendChild(juros);
                    emprestimo_tag.appendChild(mensal);
                    emprestimo_tag.appendChild(timeToPay);
                }
            }
        }
        return D;
    }

    /**
     *  Metodos auxiliares
     */


    private synchronized static String md5Hash(String encode){

        try {
            byte[] bytesOfMessage = encode.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");

            return (md.digest(bytesOfMessage).toString());

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }


    private synchronized static String imageToBase64Encode(Image img) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String encodedImage = null;
        try {
            ImageIO.write((RenderedImage) img, "png", baos);
            baos.flush();
            encodedImage = Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return encodedImage;
    }

    public synchronized static BufferedImage imageToBase64Decode(String encodedImage) {

        byte[] bytes = Base64.getDecoder().decode(encodedImage);
        BufferedImage img = null;

        try {
            img = ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    public synchronized static void removeChilds(Node node) {
        while (node.hasChildNodes()) {
            node.removeChild(node.getFirstChild());
        }
    }

    //method to convert Document to String
    public synchronized static String getStringFromDocument(Document doc)
    {
        try
        {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        }
        catch(TransformerException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public synchronized static Document convertStringToDocument(String xmlStr) {
        try
        {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.parse( new InputSource( new StringReader( xmlStr ) ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public synchronized static String prettyPrint(Document xml) {
        try {
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "NO");
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tf.setOutputProperty(OutputKeys.INDENT, "YES");
            Writer out = new StringWriter();
            tf.transform(new DOMSource(xml), new StreamResult(out));
            //System.out.println(out.toString());
            return out.toString();
        } catch (TransformerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  script de teste
     */
    public static void main(String[] args) {
        //user e pass para teste
        String u = "joaofilipevaz";
        String pass = "asdwf23425";

        Protocolo proto = new Protocolo();

        Document d = proto.writeLogin(u, pass);
        XMLDoc.writeDocument(d, "teste.xml");

        removeChilds(d.getDocumentElement());

        d = proto.respostaServidor(true);
        XMLDoc.writeDocument(d, "resposta.xml");

        removeChilds(d.getDocumentElement());

        d = proto.queryServidor("1");
        XMLDoc.writeDocument(d, "queryServidor.xml");

        removeChilds(d.getDocumentElement());
        Image assinatura = null;
        Image foto = null;
        try {
            foto = ImageIO.read(new File("foto.jpg"));
            assinatura = ImageIO.read(new File("assinatura.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Cliente clt = new ClienteIndividual("joaofilipevaz","Joao Filipe Sant'Ana Ruivo Neves Vaz",
                "","207905835", "Avenida de Berlim Lt K", "+351963938893", foto, assinatura,
                "123512354","PT654867321354", LocalDate.of(1981, 8, 23));

        Cliente cliente_emp = new ClienteEmpresarial("VazHoldings", "VazHoldings", "",
                "504825035", "Praceta do Cerrado da Bica 1º Esq", "2153321456", foto, assinatura,
                "Antonio Fagundes", "654322");

        d = proto.infoCliente(clt);
        XMLDoc.writeDocument(d, "cliente.xml");

        removeChilds(d.getDocumentElement());
        Conta contaaordem = new Conta("contaaordem", "276214522", "PT50321568432513215346", "joaofilipevaz", 103256221,
                0.0, "321568432513215346", TipoContaEnum.CONTAORDEM);

        Conta contaprazo = new Conta("contaaordem", "276214522", "PT50321568432513215346", "joaofilipevaz", 103256221,
                0.0, "321568432513215346", TipoContaEnum.CONTAPRAZO);

        Movimento mov1 = new Movimento("pag propinas isel", LocalDate.of(2017, 6, 14),
                LocalDate.of(2017, 6, 14), 160.0, TipoMovimentoEnum.DEBITO ,
                "PT50321568432513215346","PT50321568432513215368453");

        Movimento mov2 = new Movimento("ordenado", LocalDate.of(2017, 5, 30),
                LocalDate.of(2017, 5, 30), 1000.0, TipoMovimentoEnum.CREDITO ,
                "PT50321568432513215368453","PT50321568432513215346");

        contaaordem.setMovimento(mov1);
        contaaordem.setMovimento(mov2);

        d = proto.infoConta(contaaordem, true);
        XMLDoc.writeDocument(d, "conta.xml");

        d = proto.infoConta(contaprazo, true);
        XMLDoc.writeDocument(d, "conta.xml");
    }


}
