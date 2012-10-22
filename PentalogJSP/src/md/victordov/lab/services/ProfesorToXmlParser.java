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
import md.victordov.lab.dao.ProfesorDAO;
import md.victordov.lab.vo.Profesor;

/**
 * @author victor
 *
 */
public class ProfesorToXmlParser {

	/**
	 * @param args
	 * @param parser, will be accessed as a static function without Class initialization
	 */
	public static String parser() {
		GenericService<Profesor> genService = new ProfesorService(
				new ProfesorDAO());
		ArrayList<Profesor> profList = new ArrayList<Profesor>();
		try {
			profList = genService.getAll();
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
		rootElement.setAttributeNS(LabParseStringConstants.W3_XMLNS, "xmlns:s1" ,
				LabParseStringConstants.STUDENT_NS_LINK);

		// student elements
		Element profesori = doc.createElement(LabParseStringConstants.PROF_ROOT);
		rootElement.appendChild(profesori);

		for (int i = 0; i < profList.size(); i++) {
			Element profesor = doc.createElement(LabParseStringConstants.PROF_TAG);

			profesori.appendChild(profesor);
			Element u_id = doc.createElement(LabParseStringConstants.PROF_ID);
			profesor.appendChild(u_id);
			u_id.appendChild(doc.createTextNode(Long.toString(profList.get(i)
					.getProfesorId())));

			Element nume = doc.createElement(LabParseStringConstants.PROF_NUME);
			profesor.appendChild(nume);
			nume.appendChild(doc.createTextNode(profList.get(i)
					.getNume()));

			Element prenume = doc.createElement(LabParseStringConstants.PROF_PRENUME);
			profesor.appendChild(prenume);
			prenume.appendChild(doc.createTextNode(profList.get(i).getPrenume()));

			Element adresa = doc.createElement(LabParseStringConstants.PROF_ADRESA);
			profesor.appendChild(adresa);
			adresa.appendChild(doc
					.createTextNode(profList.get(i).getAdresa()));

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
		System.out.println(xmlString);

		return xmlString;
	}

}