package ua.nure.thao.Practice7;

import ua.nure.thao.Practice7.controller.DOMController;
import ua.nure.thao.Practice7.controller.SAXController;
import ua.nure.thao.Practice7.controller.STAXController;
import ua.nure.thao.Practice7.entity.Bank;
import ua.nure.thao.Practice7.util.Sorter;

public class Main {
	public static void usage() {
		System.out.println("Usage:\njava -jar Practice7.jar xmlFileName");
		System.out.println("java ua.nure.thao.Practice7.Main xmlFileName");
	}
	
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			usage();
			return;
		}
		
		String xmlFileName = args[0];
		System.out.println("Input ==> " + xmlFileName);
		
		
		DOMController domController = new DOMController(xmlFileName);
		domController.parse(true);
		Bank bank = domController.getBank();

		Sorter.sortDepositsByName(bank);
		
		String outputXmlFile = "output.dom.xml";
		DOMController.saveToXML(bank, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);

		
		SAXController saxController = new SAXController(xmlFileName);
		saxController.parse(true);
		bank = saxController.getBank();
		
		Sorter.sortDepositsByAccountId(bank);
		
		outputXmlFile = "output.sax.xml";
		
		DOMController.saveToXML(bank, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);
		
		
		STAXController staxController = new STAXController(xmlFileName);
		staxController.parse();
		bank = staxController.getBank();
		
		Sorter.sortDepositsByType(bank);
		
		outputXmlFile = "output.stax.xml";
		DOMController.saveToXML(bank, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);
		
		System.out.println("====================================");
		System.out.println("~~~~~~~~~~DOMController.main~~~~~~~~");
		DOMController.main(args);
		System.out.println("~~~~~~~~~~SAXController.main~~~~~~~~");
		SAXController.main(args);
		System.out.println("~~~~~~~~~~STAXController.main~~~~~~~");
		STAXController.main(args);
		System.out.println("~~~~~~~~~~~~~Sorter.main~~~~~~~~~~~~");
		Sorter.main(args);
	}

}