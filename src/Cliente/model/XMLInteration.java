package Cliente.model;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import commun.Cliente;
import commun.Conta;
import commun.Movimento;
import commun.TipoMovimento;

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
	
	private NodeList getAllSameElements(String element, Document doc){
		try{
			return doc.getElementsByTagName(element);
		}catch(Exception e){
			System.out.println("WARNING: Element " + element + " was not found on the xml tree!");
		}
		return null;
	}
	
	public Cliente getClient(Document doc){

		Cliente cliente = new Cliente(getXPathV("//cliente/nomeCliente", doc), Integer.parseInt(getXPathV("//cliente/nif", doc)), null, null);
		cliente.setMorada(getXPathV("//cliente/morada", doc));
		cliente.setNumConta(Integer.parseInt(getXPathV("//cliente/numConta", doc)));
		cliente.setNumTelefone(Integer.parseInt(getXPathV("//cliente/numTelefone", doc)));

		return cliente;
	}
	
	public ArrayList<Conta> getAccount(Document doc){
		ArrayList<Conta> contasList = new ArrayList<Conta>();
		NodeList contas = getXPath("//conta", doc);
		
		for(int i = 0; i < contas.getLength(); i++){			
			Conta conta = new Conta(getXPathV("//conta/nomeConta", doc),
					getXPathV("//conta/numConta", doc),
					Integer.parseInt(getXPathV("//conta/idCliente", doc)),
					Double.parseDouble(getXPathV("//conta/saldoContabilistico", doc)),
					getXPathV("//conta/nib", doc),
					getXPathV("//conta/iban", doc));
			
			conta.setSaldoAutorizado(Double.parseDouble(getXPathV("//conta/saldoAutorizado", doc)));
			conta.setSaldoDisponivel(Double.parseDouble(getXPathV("//conta/saldoDisponivel", doc)));
			
			ArrayList<Movimento> movimentos = new ArrayList<Movimento>();
		    String descricao = "";
		    LocalDate dataValor = null;
		    LocalDate dataLancamento = null;
		    double valor = 0.0;
		    TipoMovimento tipo = null;
		    String contaRemetente = "";
		    String contaDestino = "";
		    
			NodeList list = getXPath("//conta["+(i+1)+"]/movimentos/movimento", doc);
			
			System.out.println("++++ " + list.getLength());
			
			for(int y = 0; y < list.getLength(); y++){
				NodeList attributes = list.item(y).getChildNodes();
				for(int x = 0; x < attributes.getLength(); x++ ){
					Node node = attributes.item(x);
					
					switch(node.getNodeName()){
					case "descricao":
						descricao = node.getTextContent();
						break;
					case "dataValor":
						dataValor = LocalDate.parse(node.getTextContent());
						break;
					case "dataLancamento":
						dataLancamento = LocalDate.parse(node.getTextContent());
						break;
					case "valor":
						valor = Double.parseDouble(node.getTextContent());
						break;
					case "tipo":
						tipo = (node.getTextContent().equals("CREDITO")? TipoMovimento.CREDITO : TipoMovimento.DEBITO);
						break;
					case "contaDestino":
						contaDestino = node.getTextContent();
						break;
					case "contaRemetente":
						contaRemetente = node.getTextContent();
						break;	
					}	
				}			
				movimentos.add(new Movimento(descricao, dataValor, dataLancamento, valor, tipo, contaDestino, contaRemetente));	
			}
			
			conta.setMovimentos(movimentos);
			contasList.add(conta);
		}
		
		return contasList;
	}
	
	
	
	
	
	
	
	/**
     * Devolve lista de nï¿½s gerada pela expressï¿½o xPath indicada
     *
     * @param expression
     *            xpath
     * @param doc
     *            raiz do documento XML
     * @return
     * 			lista de nï¿½s
     */

    public static final NodeList getXPath(final String expression, final Document doc) {
        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes;
        try {
            nodes = (NodeList) xpath.evaluate(expression, doc,
                    XPathConstants.NODESET);
            return nodes;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Executa uma expressï¿½o XPath numa arvore DOM e devolve o 1ï¿½ string (valor)
     *
     * @param expression
     * @param doc
     * @return string que ï¿½ o valor do nï¿½ encontrado
     *
     */
    public static final String getXPathV(final String expression, final Document doc) {
        NodeList aux = getXPath(expression, doc);
        if (aux == null)
            return null;
        else if (aux.item(0) == null)
            return null;
        else
            return aux.item(0).getTextContent();
    }
}
