package ua.nure.thao.Practice7.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.thao.Practice7.constants.Constants;
import ua.nure.thao.Practice7.constants.XML;
import ua.nure.thao.Practice7.entity.Bank;
import ua.nure.thao.Practice7.entity.Deposit;

public class SAXController extends DefaultHandler {
	
	private String xmlFileName;

	private String currentElement;

	private Bank bank;
	
	private Deposit deposit;
	
	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse(boolean validate) 
			throws ParserConfigurationException, SAXException, IOException {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();

		factory.setNamespaceAware(true);
		
		if (validate) {
			factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		SAXParser parser = factory.newSAXParser();
		parser.parse(xmlFileName, this);
	}

	@Override
	public void error(org.xml.sax.SAXParseException e) throws SAXException {
		throw e;
	};

	public Bank getBank() {
		return bank;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		currentElement = localName;

		if (XML.BANK.equalsTo(currentElement)) {
			bank = new Bank();
			return;
		}

		if (XML.DEPOSIT.equalsTo(currentElement)) {
			deposit = new Deposit();
			return;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String elementText = new String(ch, start, length).trim();

		if (elementText.isEmpty()) return;

		if (XML.NAME.equalsTo(currentElement)) {
			deposit.setName(elementText);
			return;
		}
		
		if (XML.COUNTRY.equalsTo(currentElement)) {
			deposit.setCountry(elementText);
			return;
		}
		
		if (XML.DEPOSITOR.equalsTo(currentElement)) {
			deposit.setDepositor(elementText);
			return;
		}
		
		if (XML.ACCOUNTID.equalsTo(currentElement)) {
			deposit.setAccountId(elementText);
			return;
		}
		
		if (XML.TYPE.equalsTo(currentElement)) {
			deposit.setType(elementText);
			return;
		}
		
		if (XML.AMOUNTONDEPOSIT.equalsTo(currentElement)) {
			deposit.setAmountOnDeposit(elementText);
			return;
		}
		
		if (XML.PROFITABILITY.equalsTo(currentElement)) {
			deposit.setProfitability(elementText);
			return;
		}
		
		if (XML.TIMECONSTRAINTS.equalsTo(currentElement)) {
			deposit.setTimeConstraints(elementText);
			return;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (XML.DEPOSIT.equalsTo(localName)) {
			bank.getDeposits().add(deposit);
			return;
		}
	}

	public static void main(String[] args) throws Exception {

		SAXController saxContr = new SAXController(Constants.VALID_XML_FILE);
		
		saxContr.parse(true);
		
		Bank bank = saxContr.getBank();

		System.out.println("====================================");
		System.out.println("Here is the bank deposits: \n" + bank);
		System.out.println("====================================");

		saxContr = new SAXController(Constants.INVALID_XML_FILE);
		try {			
			saxContr.parse(true);
		} catch (Exception ex) {
			System.err.println("====================================");
			System.err.println("Validation is failed:\n" + ex.getMessage());
			System.err
					.println("Try to print bank object:\n" + saxContr.getBank());
			System.err.println("====================================");
		}

		saxContr.parse(false);		

		System.out.println("====================================");
		System.out.println("Here is the bank deposits: \n" + saxContr.getBank());
		System.out.println("====================================");
	}
}