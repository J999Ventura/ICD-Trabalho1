package Protocolo;

import XML.XMLDoc;
import commun.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
import java.util.ArrayList;
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

        Element login_tag = D.createElement("login");
        Element user_tag = D.createElement("user");
        Element pass_tag = D.createElement("pass");

        user_tag.setTextContent(md5Hash(user));
        pass_tag.setTextContent(md5Hash(pass));

        protocol_tag.appendChild(login_tag);
        login_tag.appendChild(user_tag);
        login_tag.appendChild(pass_tag);

        return D;
    }
    
    public Document writeLogout(String user){
        Element logout_tag = D.createElement("logout");
        Element user_tag = D.createElement("user");

        user_tag.setTextContent(md5Hash(user));

        protocol_tag.appendChild(logout_tag);
        logout_tag.appendChild(user_tag);
        return D;
    }
    
    public Document getUserInfo(){
        Element user_tag = D.createElement("userInfo");
        
        user_tag.setTextContent("getUserInfo");
        
        protocol_tag.appendChild(user_tag);
        return D;
    }

    /* ? */
    public Document queryServidor(String modelo){
        Element query_tag = D.createElement("query");
        
        query_tag.setTextContent(modelo);
        
        protocol_tag.appendChild(query_tag);
        return D;
    }
    
    public Document makeTransfer(String nib, double value){
        Element transfer_tag = D.createElement("transfer");
        Element nib_tag = D.createElement("nib");
        Element value_tag = D.createElement("value");
        
        nib_tag.setTextContent(nib);
        value_tag.setTextContent(""+value);
        
        protocol_tag.appendChild(transfer_tag);
        transfer_tag.appendChild(nib_tag);
        transfer_tag.appendChild(value_tag);
        return D;
    }
    
    public Document changeUserName(String name){
        Element userName_tag = D.createElement("changeUserName");
        
        userName_tag.setTextContent(name);
        
        protocol_tag.appendChild(userName_tag);
        return D;
    }


    /**
     *  Metodos do Servidor
     */

    public Document loginOk(boolean validation){

        Element login_tag = D.createElement("login");
        Element ok_tag = D.createElement("OK");

        ok_tag.setTextContent(validation ? "true" : "false");

        protocol_tag.appendChild(login_tag);
        login_tag.appendChild(ok_tag);

        return D;
    }
    
    public Document logout(boolean validation){

        Element logout_tag = D.createElement("logout");
        Element ok_tag = D.createElement("OK");

        ok_tag.setTextContent(validation ? "true" : "false");

        protocol_tag.appendChild(logout_tag);
        logout_tag.appendChild(ok_tag);

        return D;
    }
    
    public Document nameChanged(boolean validation){

        Element name_tag = D.createElement("nameChanged");
        Element ok_tag = D.createElement("OK");

        ok_tag.setTextContent(validation ? "true" : "false");

        protocol_tag.appendChild(name_tag);
        name_tag.appendChild(ok_tag);

        return D;
    }
    
    public Document transferMade(boolean validation){

        Element transfer_tag = D.createElement("transferMade");
        Element ok_tag = D.createElement("OK");

        ok_tag.setTextContent(validation ? "true" : "false");

        protocol_tag.appendChild(transfer_tag);
        transfer_tag.appendChild(ok_tag);

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
       // Element foto_tag = D.createElement("foto");
        //Element assinatura_tag = D.createElement("assinatura");

        nomeCliente_tag.setTextContent(cliente.getNomeCliente());
        nif_tag.setTextContent(Integer.toString(cliente.getNif()));
        idCliente_tag.setTextContent(Integer.toString(cliente.getIdCliente()));
        morada_tag.setTextContent(cliente.getMorada());
        numTelefone_tag.setTextContent(Integer.toString(cliente.getNumTelefone()));
        numConta_tag.setTextContent(Integer.toString(cliente.getNumConta()));
        //foto_tag.setTextContent(imageToBase64Encode(cliente.getFoto()));
        //assinatura_tag.setTextContent(imageToBase64Encode(cliente.getAssinatura()));

        protocol_tag.appendChild(cliente_tag);
        cliente_tag.appendChild(nomeCliente_tag);
        cliente_tag.appendChild(idCliente_tag);
        cliente_tag.appendChild(nif_tag);
        cliente_tag.appendChild(morada_tag);
        cliente_tag.appendChild(numTelefone_tag);
        cliente_tag.appendChild(numConta_tag);
        //cliente_tag.appendChild(foto_tag);
        //cliente_tag.appendChild(assinatura_tag);

        return D;
    }

    public Document infoConta(Conta conta){

        Element conta_tag = D.createElement("conta");
        Element numConta_tag = D.createElement("numConta");
        Element nomeConta_tag = D.createElement("nomeConta");
        Element nib_tag = D.createElement("nib");
        Element iban_tag = D.createElement("iban");
        Element idCliente_tag = D.createElement("idCliente");
        Element saldoContabilistico_tag = D.createElement("saldoContabilistico");
        Element saldoDisponivel_tag = D.createElement("saldoDisponivel");
        Element saldoAutorizado_tag = D.createElement("saldoAutorizado");
        Element movimentos_tag = D.createElement("movimentos");

        numConta_tag.setTextContent(conta.getNumConta());
        nomeConta_tag.setTextContent(conta.getNomeConta());
        nib_tag.setTextContent(conta.getNib());
        iban_tag.setTextContent(conta.getIban());
        idCliente_tag.setTextContent(conta.getIdCliente()+"");
        saldoContabilistico_tag.setTextContent(conta.getSaldoContabilistico()+"");
        saldoDisponivel_tag.setTextContent(Double.toString(conta.getSaldoDisponivel()));
        saldoAutorizado_tag.setTextContent(Double.toString(conta.getSaldoAutorizado()));

        protocol_tag.appendChild(conta_tag);
        conta_tag.appendChild(numConta_tag);
        conta_tag.appendChild(nomeConta_tag);
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
                Element contaDestino_tag = D.createElement("contaDestino");
                Element contaRemetente_tag = D.createElement("contaRemetente");
   
                descricao_tag.setTextContent(conta.getMovimentos().get(i).getDescricao());
                dataValor_tag.setTextContent("");//conta.getMovimentos().get(i).getDataValor().toString());
                dataLancamento_tag.setTextContent("");//conta.getMovimentos().get(i).getDataLancamento().toString());
                valor_tag.setTextContent(Double.toString(conta.getMovimentos().get(i).getValor()));
                tipo_tag.setTextContent(conta.getMovimentos().get(i).getTipo().toString());
                contaDestino_tag.setTextContent(conta.getMovimentos().get(i).getContaDestino());
                contaRemetente_tag.setTextContent(conta.getMovimentos().get(i).getContaRemetente());

                movimentos_tag.appendChild(movimento_tag);
                
                movimento_tag.appendChild(contaRemetente_tag);
                movimento_tag.appendChild(contaDestino_tag);
                movimento_tag.appendChild(descricao_tag);
                movimento_tag.appendChild(dataValor_tag);
                movimento_tag.appendChild(dataLancamento_tag);
                movimento_tag.appendChild(valor_tag);
                movimento_tag.appendChild(tipo_tag);
            }

        }

        return D;
    }
    
    
    public Document enviarEmprestimo(ArrayList<Emprestimo> list){
    	
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
                timeToPay.setTextContent(list.get(i).getTempoRestante().toString());
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
    public String getStringFromDocument(Document doc)
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
}
