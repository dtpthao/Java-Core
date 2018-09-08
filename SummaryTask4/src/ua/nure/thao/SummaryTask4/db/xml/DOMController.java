package ua.nure.thao.SummaryTask4.db.xml;

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

import ua.nure.thao.SummaryTask4.db.entity.Answer;
import ua.nure.thao.SummaryTask4.db.entity.Question;
import ua.nure.thao.SummaryTask4.db.entity.Test;

public class DOMController {

	private String xmlFileName;

	private Test test;

	public DOMController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public Test getTest() {
		return test;
	}

	/**
	 * Parses XML document.
	 * 
	 * @param validate
	 *            If true validate XML document against its XML schema.
	 */
	public void parse(boolean validate) 
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(true);
		
		if (validate) {
			dbf.setFeature("http://xml.org/sax/features/validation", true);
			
			dbf.setFeature("http://apache.org/xml/features/validation/schema", true);
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

		test = new Test();
		
		Node ttNode = root.getElementsByTagName(XML.TESTTEXT.value()).item(0);
		test.setName(ttNode.getTextContent());
		
		Node cNode = root.getElementsByTagName(XML.CATEGORY.value()).item(0);
		test.setCategoryId(Integer.parseInt(cNode.getTextContent()));
		
		Node lNode = root.getElementsByTagName(XML.LEVEL.value()).item(0);
		test.setLevelId(Integer.parseInt(lNode.getTextContent()));
		
		Node dNode = root.getElementsByTagName(XML.DURATION.value()).item(0);
		test.setDuration(dNode.getTextContent());
		
		NodeList questionNodes = root
				.getElementsByTagName(XML.QUESTION.value());

		for (int j = 0; j < questionNodes.getLength(); j++) {
			Question question = getQuestion(questionNodes.item(j));
			test.getQuestions().add(question);
		}
	}

	/**
	 * Extracts question object from the question XML node.
	 * 
	 * @param qNode
	 *            Question node.
	 * @return Question object.
	 */
	private Question getQuestion(Node qNode) {
		Question question = new Question();
		Element qElement = (Element) qNode;

		Node qtNode = qElement.getElementsByTagName(XML.QUESTION_TEXT.value())
				.item(0);
		question.setName(qtNode.getTextContent());

		NodeList aNodeList = qElement.getElementsByTagName(XML.ANSWER.value());
		for (int j = 0; j < aNodeList.getLength(); j++) {
			Answer answer = getAnswer(aNodeList.item(j));

			question.getAnswers().add(answer);
		}

		return question;
	}

	/**
	 * Extracts answer object from the answer XML node.
	 * 
	 * @param aNode
	 *            Answer node.
	 * @return Answer object.
	 */
	private Answer getAnswer(Node aNode) {
		Answer answer = new Answer();
		Element aElement = (Element) aNode;

		String correct = aElement.getAttribute(XML.CORRECT.value());
		answer.setCorrect(Boolean.valueOf(correct));

		String content = aElement.getTextContent();
		answer.setName(content);

		return answer;
	}
	
	// //////////////////////////////////////////////////////
	// Static util methods
	// //////////////////////////////////////////////////////

	/**
	 * Creates and returns DOM of the Test container.
	 * 
	 * @param test
	 *            Test object.
	 * @throws ParserConfigurationException 
	 */
	public static Document getDocument(Test test) throws ParserConfigurationException {
	
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(true);

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		Element tElement = document.createElement(XML.TEST.value());

		document.appendChild(tElement);
		
		Element ttElement = document.createElement(XML.TESTTEXT.value());
		ttElement.setTextContent(test.getName());
		tElement.appendChild(ttElement);
		
		Element cElement = document.createElement(XML.CATEGORY.value());
		cElement.setTextContent(String.valueOf(test.getCategoryId()));
		tElement.appendChild(cElement);
		
		Element lElement = document.createElement(XML.LEVEL.value());
		lElement.setTextContent(String.valueOf(test.getLevelId()));
		tElement.appendChild(lElement);
		
		Element dElement = document.createElement(XML.DURATION.value());
		dElement.setTextContent(test.getDuration());
		tElement.appendChild(dElement);

		for (Question question : test.getQuestions()) {

			Element qElement = document.createElement(XML.QUESTION.value());
			tElement.appendChild(qElement);

			Element qtElement = 
					document.createElement(XML.QUESTION_TEXT.value());
			qtElement.setTextContent(question.getName());
			qElement.appendChild(qtElement);

			for (Answer answer : question.getAnswers()) {
				Element aElement = document.createElement(XML.ANSWER.value());
				aElement.setTextContent(answer.getName());
				
				if (answer.isCorrect()) {
					aElement.setAttribute(XML.CORRECT.value(), "true");
				}
				qElement.appendChild(aElement);
			}
		}

		return document;		
	}
	
	/**
	 * Saves Test object to XML file.
	 * 
	 * @param test
	 *            Test object to be saved.
	 * @param xmlFileName
	 *            Output XML file name.
	 */
	public static void saveToXML(Test test, String xmlFileName)
			throws ParserConfigurationException, TransformerException {		
		saveToXML(getDocument(test), xmlFileName);		
	}
	
	/**
	 * Save DOM to XML.
	 * 
	 * @param document
	 *            DOM to be saved.
	 * @param xmlFileName
	 *            Output XML file name.
	 */
	public static void saveToXML(Document document, String xmlFileName) 
			throws TransformerException {
		
		StreamResult result = new StreamResult(new File(xmlFileName));
		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");			
		
		t.transform(new DOMSource(document), result);
	}
	
}
