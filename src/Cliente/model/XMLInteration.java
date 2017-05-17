package Cliente.model;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import commun.Cliente;
import commun.Conta;
import commun.Movimento;

public class XMLInteration {
	
	/* exemplo de validação com xsd de um protocolo */
	public boolean validarXML(String xml) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;

		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return false;
		}
		try {
			document = builder.parse(new InputSource(new StringReader(xml)));
		} catch (SAXException e) {
			e.printStackTrace();
			System.out.println("Não foi possivel analisar a mensagem!");
			return false;
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Não foi possivel analisar a mensagem!");
			return false;
		}
		if (!Validar(document, "Protocolo\\protocolo.xsd")){
			System.out.println("A mensagem não respeita o protocolo!");
			return false;
		}else{
			System.out.println("A mensagem respeita o protocolo!");
			return true;
		}
	}
	
	public boolean Validar(Document document, String xsdFile) {
		Schema schema = null;
		try {
			String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
			SchemaFactory factory = SchemaFactory.newInstance(language);
			schema = factory.newSchema(new File(xsdFile));
			Validator validator = schema.newValidator();
			validator.validate(new DOMSource(document)); // se falhar existe
															// excepção
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/* lê um dom do de um stream de input */
	public Document readDocument(InputStream input) {
		// create a new DocumentBuilderFactory
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      Document doc=null;
	      try {
	         // use the factory to create a documentbuilder
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         doc = builder.parse(input);
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
	      return doc;
	}
	
	/* escreve um dom para um stream de output */
	public static final void writeDocument(Document input, OutputStream output) {
        try {
        	DOMSource domSource = new DOMSource(input);
        	StreamResult resultStream = new StreamResult(output);
        	TransformerFactory transformFactory = TransformerFactory.newInstance();
        	Transformer transformer = transformFactory.newTransformer();
        	try {
        		transformer.transform(domSource, resultStream);
        	} catch (javax.xml.transform.TransformerException e) {
        	}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	
	private String getString(String element, Document doc){
		try{
			return doc.getElementsByTagName(element).item(0).getTextContent();
		}catch(Exception e){
			System.out.println("WARNING: Element " + element + " was not found on the xml tree!");
		}
		return null;
	}
	
	public Cliente getClient(Document doc){

		Cliente cliente = new Cliente(getString("nomeCliente", doc), Integer.parseInt(getString("nif", doc)), null, null);
		cliente.setMorada(getString("morada", doc));
		cliente.setNumConta(Integer.parseInt(getString("numConta", doc)));
		cliente.setNumTelefone(Integer.parseInt(getString("numTelefone", doc)));
		//cliente.setAssinatura(assinatura);
		//cliente.setFoto(foto);
		
		return cliente;
	}
	
	public Conta getAccount(Document doc){
		Conta conta = new Conta(getString("nomeConta", doc),
				getString("numConta", doc),
				Integer.parseInt(getString("idCliente", doc)),
				Double.parseDouble(getString("saldoContabilistico", doc)),
				getString("nib", doc),
				getString("iban", doc));
		
		conta.setSaldoAutorizado(Double.parseDouble(getString("saldoAutorizado", doc)));
		conta.setSaldoDisponivel(Double.parseDouble(getString("saldoDisponivel", doc)));
		
		//conta.setMovimentos(movimentos);
		
		return conta;
	}

}
