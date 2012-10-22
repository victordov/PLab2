/**
 * 
 */
package md.victordov.lab.services;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.common.exception.MyServiceException;
import md.victordov.lab.common.other.LabParseStringConstants;
import md.victordov.lab.dao.UnivDAO;
import md.victordov.lab.vo.Universitate;

/**
 * @author victor
 * 
 */
public class UnivToXmlParserService {

	/**
	 * @param args
	 * @param parser, will be accessed as a static function without Class initialization
	 */
	public static String parser() {
		GenericService<Universitate> genService = new UniversitateService(
				new UnivDAO());
		ArrayList<Universitate> univList = new ArrayList<Universitate>();
		try {
			univList = genService.getAll();
		} catch (MyDaoException e2) {

			System.out.println(e2.getMessage());
		}

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = factory.newTransformer();
		} catch (TransformerConfigurationException e2) {
			System.out.println(e2.getMessage());
		}

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
				"XmlValidator.dtd");

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e2) {
			System.out.println(e2.getMessage());
		}
		Document doc = docBuilder.newDocument();

		// questionset elements
		Element rootElement = doc.createElement("lab2dBXml");

		doc.appendChild(rootElement);
		rootElement.setAttributeNS(LabParseStringConstants.W3_XMLNS, "xmlns:u1",
				LabParseStringConstants.UNIV_NS_LINK);
		rootElement.setAttributeNS(LabParseStringConstants.W3_XMLNS, "xmlns:c1",
				LabParseStringConstants.CURS_NS_LINK);
		rootElement.setAttributeNS(LabParseStringConstants.W3_XMLNS, "xmlns:p1",
				LabParseStringConstants.PROFESOR_NS_LINK);
		rootElement.setAttributeNS(LabParseStringConstants.W3_XMLNS, "xmlns:s1",
				LabParseStringConstants.STUDENT_NS_LINK);

		// student elements
		Element universitati = doc.createElement(LabParseStringConstants.UNIV_ROOT);
		rootElement.appendChild(universitati);

		for (int i = 0; i < univList.size(); i++) {
			Element universitate = doc.createElement(LabParseStringConstants.UNIV_TAG);

			universitati.appendChild(universitate);
			Element u_id = doc.createElement(LabParseStringConstants.UNIV_ID);
			universitate.appendChild(u_id);
			u_id.appendChild(doc.createTextNode(Long.toString(univList.get(i)
					.getUniversitateId())));

			Element nume = doc.createElement(LabParseStringConstants.UNIV_NUME);
			universitate.appendChild(nume);
			nume.appendChild(doc.createTextNode(univList.get(i)
					.getNumeUniversitate()));

			Element adresa = doc.createElement(LabParseStringConstants.UNIV_ADRESA);
			universitate.appendChild(adresa);
			adresa.appendChild(doc.createTextNode(univList.get(i).getAdresa()));

			Element telefon = doc.createElement(LabParseStringConstants.UNIV_TEL);
			universitate.appendChild(telefon);
			telefon.appendChild(doc
					.createTextNode(univList.get(i).getTelefon()));

		}

		// aici transformer

		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			/* Arunca Exceptia MyService inconjurat de try-catch block */
			try {
				throw new MyServiceException(e.getMessage(), e);
			} catch (MyServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		String xmlString = sw.toString();
		//System.out.println(xmlString);

		return xmlString;
	}

}
