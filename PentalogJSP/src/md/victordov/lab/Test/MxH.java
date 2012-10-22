package md.victordov.lab.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

import md.victordov.lab.common.other.LabParseStringConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MxH {

	public MxH() {
		this.factory = TransformerFactory.newInstance();
		this.transformer = null;
		try {
			transformer = factory.newTransformer();
		} catch (TransformerConfigurationException e2) {

		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
				"XmlValidator.dtd");
		this.builderFactory = DocumentBuilderFactory.newInstance();
		this.docBuilder = null;
		try {
			docBuilder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e2) {
			System.out.println(e2.getMessage());
		}
		this.doc = docBuilder.newDocument();

		// questionset elements
		this.rootElement = doc.createElement("lab2dBXml");
		rootElement.setAttributeNS(LabParseStringConstants.W3_XMLNS,
				"xmlns:u1", LabParseStringConstants.UNIV_NS_LINK);
		rootElement.setAttributeNS(LabParseStringConstants.W3_XMLNS,
				"xmlns:c1", LabParseStringConstants.CURS_NS_LINK);
		rootElement.setAttributeNS(LabParseStringConstants.W3_XMLNS,
				"xmlns:p1", LabParseStringConstants.PROFESOR_NS_LINK);
		rootElement.setAttributeNS(LabParseStringConstants.W3_XMLNS,
				"xmlns:s1", LabParseStringConstants.STUDENT_NS_LINK);

	}

	public Element getRootElement() {
		return this.rootElement;
	}

	public Transformer getTransformer() {
		return this.transformer;
	}

	public Document getDoc() {
		return this.doc;
	}

	private Transformer transformer;
	private TransformerFactory factory;
	private Element rootElement;
	private DocumentBuilderFactory builderFactory;
	private DocumentBuilder docBuilder;
	private Document doc;

}
