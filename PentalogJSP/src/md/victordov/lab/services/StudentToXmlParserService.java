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
import md.victordov.lab.dao.StudentDAO;
import md.victordov.lab.vo.Student;

/**
 * @author victor
 * 
 */
public class StudentToXmlParserService {

	/**
	 * @param args
	 * @param parser, will be accessed as a static function without Class initialization
	 */
	public static String parser() {
		GenericService<Student> genService = new StudentService(
				new StudentDAO());
		ArrayList<Student> studList = new ArrayList<Student>();
		try {
			studList = genService.getAll();
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
		Element studenti = doc.createElement(LabParseStringConstants.STUD_ROOT);
		rootElement.appendChild(studenti);

		for (int i = 0; i < studList.size(); i++) {
			Element student = doc.createElement(LabParseStringConstants.STUD_TAG);

			studenti.appendChild(student);
			Element s_id = doc.createElement(LabParseStringConstants.STUD_ID);
			student.appendChild(s_id);
			s_id.appendChild(doc.createTextNode(Long.toString(studList.get(i)
					.getStudentId())));

			Element nume = doc.createElement(LabParseStringConstants.STUD_NUME);
			student.appendChild(nume);
			nume.appendChild(doc.createTextNode(studList.get(i)
					.getNume()));

			Element prenume = doc.createElement(LabParseStringConstants.STUD_PRENUME);
			student.appendChild(prenume);
			prenume.appendChild(doc.createTextNode(studList.get(i).getPrenume()));

			Element grupa = doc.createElement(LabParseStringConstants.STUD_GRUPA);
			student.appendChild(grupa);
			grupa.appendChild(doc
					.createTextNode(studList.get(i).getGrupa()));
			
			Element email = doc.createElement(LabParseStringConstants.STUD_EMAIL);
			student.appendChild(email);
			email.appendChild(doc
					.createTextNode(studList.get(i).getEmail()));
			

			Element telFix = doc.createElement(LabParseStringConstants.STUD_TEL_FIX);
			student.appendChild(telFix);
			telFix.appendChild(doc
					.createTextNode(studList.get(i).getTelFix()));

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