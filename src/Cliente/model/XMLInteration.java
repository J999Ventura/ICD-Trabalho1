package Cliente.model;
import Protocolo.Protocolo;
import XML.XMLDoc;
import commun.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class XMLInteration {

    /* exemplo de valida��o com xsd de um protocolo */
    public boolean validarXML(String xml) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;

        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        }
        try {
            document = builder.parse(new InputSource(new StringReader(xml)));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            System.out.println("N�o foi possivel analisar a mensagem!");
            return false;
        }
        if (!Validar(document, "Protocolo\\protocolo.xsd")){
            System.out.println("A mensagem n�o respeita o protocolo!");
            return false;
        }else{
            System.out.println("A mensagem respeita o protocolo!");
            return true;
        }
    }

    public boolean Validar(Document document, String xsdFile) {
        Schema schema;
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            schema = factory.newSchema(new File(xsdFile));
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document)); // se falhar existe
            // excep��o
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* l� um dom do de um stream de input */
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
    public static void writeDocument(Document input, OutputStream output) {
        try {
            DOMSource domSource = new DOMSource(input);
            StreamResult resultStream = new StreamResult(output);
            TransformerFactory transformFactory = TransformerFactory.newInstance();
            Transformer transformer = transformFactory.newTransformer();
            try {
                transformer.transform(domSource, resultStream);
            } catch (javax.xml.transform.TransformerException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    Cliente getClient(Document doc){
    	//Protocolo pro = new Protocolo();
    	String clientType = XMLDoc.getXPathV("//cliente/tipoCliente", doc);
    	Cliente cliente;
    	
    	if(Objects.equals(clientType, TipoClienteEnum.CLIENTEINDIVIDUAL.getTipo())){
    		cliente = new ClienteIndividual(XMLDoc.getXPathV("//cliente/userName",doc),
    				XMLDoc.getXPathV("//cliente/nomeCliente",doc),
    				XMLDoc.getXPathV("//cliente/nif", doc),
                    XMLDoc.getXPathV("//cliente/idCliente", doc),
    				XMLDoc.getXPathV("//cliente/morada", doc),
    				XMLDoc.getXPathV("//cliente/numTelefone", doc),
                    Protocolo.imageToBase64Decode(XMLDoc.getXPathV("//cliente/foto", doc)),
                    Protocolo.imageToBase64Decode(XMLDoc.getXPathV("//cliente/assinatura", doc)),
    				XMLDoc.getXPathV("//cliente/numCartaoCidadao", doc),
    				XMLDoc.getXPathV("//cliente/numPassaporte", doc),
    				LocalDate.parse(XMLDoc.getXPathV("//cliente/dataDeNascimento", doc)));
    		
    		cliente.addContas(getAccounts(doc));
    		cliente.setEmprestimos(getLoans(doc));
    		
    	}else{
    		cliente = new ClienteEmpresarial(XMLDoc.getXPathV("//cliente/userName",doc),
    				XMLDoc.getXPathV("//cliente/nomeCliente",doc),
    				XMLDoc.getXPathV("//cliente/nif", doc),
                    XMLDoc.getXPathV("//cliente/idCliente", doc),
    				XMLDoc.getXPathV("//cliente/morada", doc),
    				XMLDoc.getXPathV("//cliente/numTelefone", doc),
                    Protocolo.imageToBase64Decode(XMLDoc.getXPathV("//cliente/foto", doc)),
                    Protocolo.imageToBase64Decode(XMLDoc.getXPathV("//cliente/assinatura", doc)),
    				XMLDoc.getXPathV("//cliente/nomeResponsavel", doc),
    				XMLDoc.getXPathV("//cliente/cae", doc));
    	}
		
		return cliente;
    }

    private List<Conta> getAccounts(Document doc){
        List<Conta> contasList = new ArrayList<>();
        NodeList contas = XMLDoc.getXPath("//conta", doc);

        if (contas != null) {
            for(int i = 0; i < contas.getLength(); i++){
                Conta conta;

                String tipoConta = XMLDoc.getXPathV("//conta["+(i+1)+"]/tipoConta", doc);
                String titular = XMLDoc.getXPathV("//conta["+(i+1)+"]/titular", doc);
                String numConta = XMLDoc.getXPathV("//conta["+(i+1)+"]/numConta", doc);
                String nomeConta = XMLDoc.getXPathV("//conta["+(i+1)+"]/nomeConta", doc);
                Double saldoContabilistico =  Double.parseDouble(XMLDoc.getXPathV(
                        "//conta["+(i+1)+"]/saldoContabilistico", doc));
                Double saldoDisponivel = Double.parseDouble(XMLDoc.getXPathV("//conta["+(i+1)+"]/saldoDisponivel", doc));

                if (tipoConta.equals(TipoContaEnum.CONTAORDEM.getTipo())) {
                    conta = new Conta(numConta, titular,
                            saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAORDEM);
                } else if (tipoConta.equals(TipoContaEnum.CONTAPRAZO.getTipo())){
                    conta = new Conta(numConta, titular,
                            saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAPRAZO);
                } else if (tipoConta.equals(TipoContaEnum.CONTAJOVEM.getTipo())){
                    conta = new Conta(numConta, titular,
                            saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAJOVEM);
                } else if (tipoConta.equals(TipoContaEnum.CONTAORDENADO.getTipo())){
                    conta = new Conta(numConta, titular,
                            saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAORDENADO);
                } else {
                    conta = new Conta(numConta, titular,
                            saldoContabilistico, saldoDisponivel, nomeConta, TipoContaEnum.CONTAPOUPANCA);
                }

                List<Movimento> movimentos = new ArrayList<Movimento>();

                int list_size = XMLDoc.getXPath("//conta["+(i+1)+"]/movimentos/movimento", doc).getLength();
                for(int x = 1; x <= list_size; x++){

                    LocalDate dataValor = LocalDate.parse(XMLDoc.getXPathV("//conta["+(i+1)+"]/movimentos/movimento["+x+"]/dataValor", doc));
                    LocalDate dataLancamento = LocalDate.parse(XMLDoc.getXPathV("//conta["+(i+1)+"]/movimentos/movimento["+x+"]/dataLancamento", doc));
                    String descricao = XMLDoc.getXPathV("//conta["+(i+1)+"]/movimentos/movimento["+x+"]/descricao", doc);
                    double valor = Double.parseDouble(XMLDoc.getXPathV("//conta["+(i+1)+"]/movimentos/movimento["+x+"]/valor", doc));
                    String tipomovimento = XMLDoc.getXPathV("//conta["+(i+1)+"]/movimentos/movimento["+x+"]/tipomovimento", doc);
                    String contaRemetente = XMLDoc.getXPathV("//conta["+(i+1)+"]/movimentos/movimento["+x+"]/contaRemetente", doc);
                    String contaDestino = XMLDoc.getXPathV("//conta["+(i+1)+"]/movimentos/movimento["+x+"]/contaDestino", doc);

                    if (tipomovimento.equals(TipoMovimentoEnum.CREDITO.getTipo())) {

                        movimentos.add(new Movimento(descricao, dataValor, dataLancamento, valor,
                                TipoMovimentoEnum.CREDITO, contaDestino, contaRemetente));

                    } else {
                        movimentos.add(new Movimento(descricao, dataValor, dataLancamento, valor,
                                TipoMovimentoEnum.DEBITO, contaDestino, contaRemetente));
                    }
                }
                conta.setMovimentos(movimentos);
                contasList.add(conta);
            }
        }

        return contasList;
    }

    public List<Emprestimo> getLoans(Document doc){
        List<Emprestimo> emprestimoList = new ArrayList<Emprestimo>();

        int list_size = XMLDoc.getXPath("//emprestimos/emprestimo", doc).getLength();
        for(int i = 1; i <= list_size; i++){
            Emprestimo emp = new Emprestimo(XMLDoc.getXPathV("//emprestimos/emprestimo["+i+"]/nomeDeConta", doc),
                    Double.parseDouble(XMLDoc.getXPathV("//emprestimos/emprestimo["+i+"]/valorTotal", doc)),
                    Double.parseDouble(XMLDoc.getXPathV("//emprestimos/emprestimo["+i+"]/emFalta", doc)),
                    Double.parseDouble(XMLDoc.getXPathV("//emprestimos/emprestimo["+i+"]/juros", doc)),
                    Double.parseDouble(XMLDoc.getXPathV("//emprestimos/emprestimo["+i+"]/mensal", doc)));
            emp.setTimeToPay(XMLDoc.getXPathV("//emprestimos/emprestimo["+i+"]/timeToPay", doc));
            emprestimoList.add(emp);
        }

        return emprestimoList;
    }

    public boolean getLoginAnswer(Document doc) {
    	System.out.println(XMLDoc.getXPathV("//OK",doc));
        return (Objects.equals(XMLDoc.getXPathV("//OK", doc), "true"));
    }
}
