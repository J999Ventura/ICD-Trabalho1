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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class Protocolo {
    private Document D = null; // representa a arvore DOM com o login
    private Element protocol_tag;

    public Protocolo() {
        DocumentBuilderFactory factory;
        DocumentBuilder builder = null;

        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        if (builder != null) {
            D = builder.newDocument();
            protocol_tag = D.createElement("protocolo");
            D.appendChild(protocol_tag);

        }
    }

    /**
     *  Metodos do Cliente
     */

    public Document writeLogin(String user, String pass){

        Element tipo_pedido = D.createElement("tipo");
        Element user_tag = D.createElement("user");
        Element pass_tag = D.createElement("pass");

        tipo_pedido.setTextContent("login");
        user_tag.setTextContent(user);
        pass_tag.setTextContent(md5Hash(pass));

        protocol_tag.appendChild(tipo_pedido);
        protocol_tag.appendChild(user_tag);
        protocol_tag.appendChild(pass_tag);

        return D;
    }


    public Document queryServidor(String modelo){

        Element query_tag = D.createElement("query");

        query_tag.setTextContent(modelo);

        protocol_tag.appendChild(query_tag);

        return D;
    }

    /**
     *  Metodos do Servidor
     */

    public Document loginReply(boolean validation){

        Element tipo_pedido = D.createElement("tipo");
        Element ok_tag = D.createElement("OK");

        ok_tag.setTextContent(validation ? "true" : "false");

        protocol_tag.appendChild(tipo_pedido);
        protocol_tag.appendChild(ok_tag);

        return D;
    }

    public Document infoCliente(Cliente cliente){

        Element cliente_tag = D.createElement("cliente");
        Element nomeCliente_tag = D.createElement("nomeCliente");
        Element idCliente_tag = D.createElement("idCliente");
        Element nif_tag = D.createElement("nif");
        Element morada_tag = D.createElement("morada");
        Element numTelefone_tag = D.createElement("numTelefone");
        Element numConta_tag = D.createElement("numConta");
        Element foto_tag = D.createElement("foto");
        Element assinatura_tag = D.createElement("assinatura");

        nomeCliente_tag.setTextContent(cliente.getNomeCliente());
        nif_tag.setTextContent(Integer.toString(cliente.getNif()));
        morada_tag.setTextContent(cliente.getMorada());
        numTelefone_tag.setTextContent(Integer.toString(cliente.getNumTelefone()));
        numConta_tag.setTextContent(Integer.toString(cliente.getNumConta()));
        foto_tag.setTextContent(imageToBase64Encode(cliente.getFoto()));
        assinatura_tag.setTextContent(imageToBase64Encode(cliente.getAssinatura()));

        protocol_tag.appendChild(cliente_tag);
        cliente_tag.appendChild(nomeCliente_tag);
        cliente_tag.appendChild(nif_tag);
        cliente_tag.appendChild(morada_tag);
        cliente_tag.appendChild(numTelefone_tag);
        cliente_tag.appendChild(numConta_tag);
        cliente_tag.appendChild(foto_tag);
        cliente_tag.appendChild(assinatura_tag);

        return D;
    }

    public Document infoConta(Conta conta){

        Element conta_tag = D.createElement("conta");
        Element numConta_tag = D.createElement("numConta");
        Element nib_tag = D.createElement("nib");
        Element iban_tag = D.createElement("iban");
        Element idCliente_tag = D.createElement("idCliente");
        Element saldoContabilistico_tag = D.createElement("saldoContabilistico");
        Element saldoDisponivel_tag = D.createElement("saldoDisponivel");
        Element saldoAutorizado_tag = D.createElement("saldoAutorizado");
        Element movimentos_tag = D.createElement("movimentos");

        numConta_tag.setTextContent(conta.getNumConta());
        nib_tag.setTextContent(conta.getNib());
        iban_tag.setTextContent(conta.getIban());
        //idCliente_tag.setTextContent(Integer.toString(conta.getIdCliente()));
        saldoContabilistico_tag.setTextContent(Double.toString(conta.getSaldoContabilistico()));
        saldoDisponivel_tag.setTextContent(Double.toString(conta.getSaldoDisponivel()));
        saldoAutorizado_tag.setTextContent(Double.toString(conta.getSaldoAutorizado()));

        protocol_tag.appendChild(conta_tag);
        conta_tag.appendChild(numConta_tag);
        conta_tag.appendChild(nib_tag);
        conta_tag.appendChild(iban_tag);
        conta_tag.appendChild(idCliente_tag);
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
                Element tipo_tag = D.createElement("tipo");

                descricao_tag.setTextContent(conta.getMovimentos().get(i).getDescricao());
                dataValor_tag.setTextContent(conta.getMovimentos().get(i).getDataValor().toString());
                dataLancamento_tag.setTextContent(conta.getMovimentos().get(i).getDataLancamento().toString());
                valor_tag.setTextContent(Double.toString(conta.getMovimentos().get(i).getValor()));
                tipo_tag.setTextContent(conta.getMovimentos().get(i).getTipo().toString());

                movimentos_tag.appendChild(movimento_tag);
                movimento_tag.appendChild(descricao_tag);
                movimento_tag.appendChild(dataValor_tag);
                movimento_tag.appendChild(dataLancamento_tag);
                movimento_tag.appendChild(valor_tag);
                movimento_tag.appendChild(tipo_tag);
            }

        }

        return D;
    }

    /**
     *  Metodos auxiliares
     */


    private String md5Hash(String encode){

        try {
            byte[] bytesOfMessage = encode.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");

            return (md.digest(bytesOfMessage).toString());

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }


    private String imageToBase64Encode(Image img) {

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

    private BufferedImage imageToBase64Decode(String encodedImage) {

        byte[] bytes = Base64.getDecoder().decode(encodedImage);
        BufferedImage img = null;

        try {
            img = ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    public static void removeChilds(Node node) {
        while (node.hasChildNodes()) {
            node.removeChild(node.getFirstChild());
        }
    }

    //method to convert Document to String
    public static String getStringFromDocument(Document doc)
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

    public static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try
        {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) );
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *  script de teste
     */
    public static void main(String[] args) {
        //user e pass para teste
        String u = "joaofilipevaz";
        String pass = "asdwf23425";

        Protocolo log = new Protocolo();

        Document d = log.writeLogin(u, pass);
        XMLDoc.writeDocument(d, "teste.xml");

        removeChilds(d.getDocumentElement());

        d = log.loginReply(true);
        XMLDoc.writeDocument(d, "resposta.xml");

        removeChilds(d.getDocumentElement());

        d = log.queryServidor("1");
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

        Cliente clt = new Cliente("joaofilipevaz","Joao Filipe Vaz", 207905835, false);

        d = log.infoCliente(clt);
        XMLDoc.writeDocument(d, "cliente.xml");

        removeChilds(d.getDocumentElement());
        Conta conta = new Conta("contaaordem", "276214522", 103256221,
                0.0, "321568432513215346",
                "PT50321568432513215346");

        d = log.infoConta(conta);
        XMLDoc.writeDocument(d, "conta.xml");
    }
}
