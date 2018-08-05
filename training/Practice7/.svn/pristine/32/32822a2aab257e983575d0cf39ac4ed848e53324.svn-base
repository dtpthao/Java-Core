package ua.nure.thao.Practice7.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.thao.Practice7.constants.Constants;
import ua.nure.thao.Practice7.constants.XML;
import ua.nure.thao.Practice7.entity.Bank;
import ua.nure.thao.Practice7.entity.Deposit;

public class DOMController {

	private String xmlFileName;
	private Bank bank;

	public DOMController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public Bank getBank() { return bank;}

	public void parse(boolean validate) 
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		
		if (validate) {
			dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		DocumentBuilder db = dbf.newDocumentBuilder();

		db.setErrorHandler(new DefaultHandler() {
			@Override
			public void error(SAXParseException e) throws SAXException {
				throw e;
			}
		});

		Document document = db.parse(xmlFileName);
		Element root = document.getDocumentElement();
		bank = new Bank();

		NodeList depositNodes = root
				.getElementsByTagName(XML.DEPOSIT.value());

		for (int j = 0; j < depositNodes.getLength(); j++) {
			Deposit deposit = getDeposit(depositNodes.item(j));
			bank.getDeposits().add(deposit);
		}
	}

	private Deposit getDeposit(Node dNode) {
		Deposit deposit = new Deposit();
		NodeList nodeList = ((Element) dNode).getElementsByTagName("*");
		deposit.setName(nodeList.item(0).getTextContent());
		deposit.setCountry(nodeList.item(1).getTextContent());
		deposit.setDepositor(nodeList.item(2).getTextContent());
		deposit.setAccountId(nodeList.item(3).getTextContent());
		deposit.setType(nodeList.item(4).getTextContent());
		deposit.setAmountOnDeposit(nodeList.item(5).getTextContent());
		deposit.setProfitability(nodeList.item(6).getTextContent());
		deposit.setTimeConstraints(nodeList.item(7).getTextContent());
		return deposit;
	}

	public static Document getDocument(Bank bank) throws ParserConfigurationException {
	
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		Element bElement = document.createElement(XML.BANK.value());

		document.appendChild(bElement);

		for (Deposit deposit : bank.getDeposits()) {

			Element dElement = document.createElement(XML.DEPOSIT.value());
			bElement.appendChild(dElement);

			Element dpElement = 
					document.createElement(XML.NAME.value());
			dpElement.setTextContent(deposit.getName());
			dElement.appendChild(dpElement);
			
			dpElement = 
					document.createElement(XML.COUNTRY.value());
			dpElement.setTextContent(deposit.getCountry());
			dElement.appendChild(dpElement);
			
			dpElement = 
					document.createElement(XML.DEPOSITOR.value());
			dpElement.setTextContent(deposit.getDepositor());
			dElement.appendChild(dpElement);
			
			dpElement = 
					document.createElement(XML.ACCOUNTID.value());
			dpElement.setTextContent(deposit.getAccountId());
			dElement.appendChild(dpElement);
			
			dpElement = 
					document.createElement(XML.TYPE.value());
			dpElement.setTextContent(deposit.getType());
			dElement.appendChild(dpElement);
			
			dpElement = 
					document.createElement(XML.AMOUNTONDEPOSIT.value());
			dpElement.setTextContent(deposit.getAmountOnDeposit());
			dElement.appendChild(dpElement);
			
			dpElement = 
					document.createElement(XML.PROFITABILITY.value());
			dpElement.setTextContent(deposit.getProfitability());
			dElement.appendChild(dpElement);
			
			dpElement = 
					document.createElement(XML.TIMECONSTRAINTS.value());
			dpElement.setTextContent(deposit.getTimeConstraints());
			dElement.appendChild(dpElement);
		}
		return document;		
	}
	
	public static void saveToXML(Bank bank, String xmlFileName)
			throws ParserConfigurationException, TransformerException {		
		saveToXML(getDocument(bank), xmlFileName);		
	}
	
	public static void saveToXML(Document document, String xmlFileName) 
			throws TransformerException {
		
		StreamResult result = new StreamResult(new File(xmlFileName));
		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");			
		t.transform(new DOMSource(document), result);
	}

	public static void main(String[] args) throws Exception {

		DOMController domContr = new DOMController(Constants.INVALID_XML_FILE);
		try {
			domContr.parse(true);
		} catch (SAXException ex) {
			System.err.println("====================================");
			System.err.println("XML not valid");
			System.err.println("Bank object --> " + domContr.getBank());
			System.err.println("====================================");
		}

		domContr.parse(false);
		System.out.println("====================================");
		System.out.println("Here is the bank deposits: \n" + domContr.getBank());
		System.out.println("====================================");

		Bank bank = domContr.getBank();
		DOMController.saveToXML(bank, Constants.INVALID_XML_FILE + ".dom-result.xml");
	}
}
