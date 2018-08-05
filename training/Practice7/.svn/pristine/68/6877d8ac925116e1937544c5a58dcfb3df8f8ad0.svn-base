package ua.nure.thao.Practice7.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.thao.Practice7.constants.Constants;
import ua.nure.thao.Practice7.constants.XML;
import ua.nure.thao.Practice7.entity.Bank;
import ua.nure.thao.Practice7.entity.Deposit;

public class STAXController extends DefaultHandler {

	private String xmlFileName;

	private Bank bank;

	public Bank getBank() {
		return bank;
	}

	public STAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse() throws ParserConfigurationException, SAXException,
			IOException, XMLStreamException {

		Deposit deposit = null;
		
		String currentElement = null;
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		
		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

		XMLEventReader reader = factory.createXMLEventReader(
				new StreamSource(xmlFileName));

		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();

			if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
				continue;
			}

			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				currentElement = startElement.getName().getLocalPart();

				if (XML.BANK.equalsTo(currentElement)) {
					bank = new Bank();
					continue;
				}

				if (XML.DEPOSIT.equalsTo(currentElement)) {
					deposit = new Deposit();
					continue;
				}
				
			}

			if (event.isCharacters()) {
				Characters characters = event.asCharacters();

				if (XML.NAME.equalsTo(currentElement)) {
					deposit.setName(characters.getData());
					continue;
				}
		
				if (XML.COUNTRY.equalsTo(currentElement)) {
					deposit.setCountry(characters.getData());
					continue;
				}
				
				if (XML.DEPOSITOR.equalsTo(currentElement)) {
					deposit.setDepositor(characters.getData());
					continue;
				}
				
				if (XML.ACCOUNTID.equalsTo(currentElement)) {
					deposit.setAccountId(characters.getData());
					continue;
				}
				
				if (XML.TYPE.equalsTo(currentElement)) {
					deposit.setType(characters.getData());
					continue;
				}
				
				if (XML.AMOUNTONDEPOSIT.equalsTo(currentElement)) {
					deposit.setAmountOnDeposit(characters.getData());
					continue;
				}
				
				if (XML.PROFITABILITY.equalsTo(currentElement)) {
					deposit.setProfitability(characters.getData());
					continue;
				}
				
				if (XML.TIMECONSTRAINTS.equalsTo(currentElement)) {
					deposit.setTimeConstraints(characters.getData());
					continue;
				}
			}

			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				String localName = endElement.getName().getLocalPart();

				if (XML.DEPOSIT.equalsTo(localName)) {
					bank.getDeposits().add(deposit);
					continue;
				}

			}
		}
		reader.close();
	}

	public static void main(String[] args) throws Exception {

		STAXController staxContr = new STAXController(Constants.VALID_XML_FILE);
		staxContr.parse();

		Bank bank = staxContr.getBank();

		System.out.println("====================================");
		System.out.println("Here is the bank deposits: \n" + bank);
		System.out.println("====================================");
	}
}