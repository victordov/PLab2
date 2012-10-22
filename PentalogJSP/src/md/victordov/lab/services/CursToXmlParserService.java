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

import md.victordov.lab.Test.MxH;
import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.common.exception.MyServiceException;
import md.victordov.lab.common.other.LabParseStringConstants;
import md.victordov.lab.dao.CursDAO;
import md.victordov.lab.vo.Curs;

/**
 * @author victor
 * 
 */
public class CursToXmlParserService {

	/**
	 * @param args
	 * @param parser
	 *            , will be accessed as a static function without Class
	 *            initialization
	 */
	public static String parser() throws MyServiceException {
		GenericService<Curs> genService = new CursService(new CursDAO());
		ArrayList<Curs> cursList = new ArrayList<Curs>();
		try {
			cursList = genService.getAll();
		} catch (MyDaoException e2) {

			System.out.println(e2.getMessage());
		}

		MxH mxh = new MxH();
		Transformer transformer = mxh.getTransformer();
		

//		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
//				"XmlValidator.dtd");

//		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
//				.newInstance();
//		DocumentBuilder docBuilder = null;
//		try {
//			docBuilder = builderFactory.newDocumentBuilder();
//		} catch (ParserConfigurationException e2) {
//			System.out.println(e2.getMessage());
//		}
//		Document doc = docBuilder.newDocument();
		Document doc = mxh.getDoc();
		// questionset elements
//		Element rootElement = doc.createElement("lab2dBXml");
		Element rootElement = mxh.getRootElement();
		doc.appendChild(rootElement);
//		rootElement.setAttributeNS(LabParseStringConstants.W3_XMLNS, "xmlns:u1",
//				LabParseStringConstants.UNIV_NS_LINK);
//		rootElement.setAttributeNS(LabParseStringConstants.W3_XMLNS, "xmlns:c1",
//				LabParseStringConstants.CURS_NS_LINK);
//		rootElement.setAttributeNS(LabParseStringConstants.W3_XMLNS, "xmlns:p1",
//				LabParseStringConstants.PROFESOR_NS_LINK);
//		rootElement.setAttributeNS(LabParseStringConstants.W3_XMLNS, "xmlns:s1",
//				LabParseStringConstants.STUDENT_NS_LINK);

		// curs elements
		Element cursuri = doc.createElement(LabParseStringConstants.CURS_ROOT);
		rootElement.appendChild(cursuri);

		for (int i = 0; i < cursList.size(); i++) {
			Element curs = doc.createElement(LabParseStringConstants.CURS_TAG);
			cursuri.appendChild(curs);
			
			Element s_id = doc.createElement(LabParseStringConstants.CURS_ID);
			s_id.appendChild(doc.createTextNode(Long.toString(cursList.get(i).getCursId())));
			curs.appendChild(s_id);

			Element nume_curs = doc.createElement(LabParseStringConstants.CURS_NUME);
			nume_curs.appendChild(doc.createTextNode(cursList.get(i)
					.getNumeCurs()));
			curs.appendChild(nume_curs);

			Element unver_id = doc.createElement(LabParseStringConstants.CURS_UNIV_ID);
			curs.appendChild(unver_id);
			unver_id.appendChild(doc.createTextNode(Long.toString(cursList.get(
					i).getUniversitateId())));

			Element prof_id = doc.createElement(LabParseStringConstants.CURS_PROF_ID);
			curs.appendChild(prof_id);
			prof_id.appendChild(doc.createTextNode(Long.toString(cursList
					.get(i).getProfesorId())));

		}

		// aici transformer

		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			throw new MyServiceException(e.getMessage(), e);
		}
		String xmlString = sw.toString();
		

		return xmlString;
	}

}