package md.victordov.lab.services;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import md.victordov.lab.Test.XmlDomHandler;
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
	 * @param parser
	 *            , will be accessed as a static function without Class
	 *            initialization
	 * @throws TransformerException
	 */
	static Logger logger = LogManager.getLogger(StudentToXmlParserService.class);
	
	public static String parser() throws MyServiceException, MyDaoException,
			TransformerException {
		logger.info("Initializarea methoda parser");
		GenericService<Student> genService = new StudentService(
				new StudentDAO());
		ArrayList<Student> studList = new ArrayList<Student>();
		try {
			studList = genService.getAll();
		} catch (MyDaoException mDexcp) {
			throw mDexcp;
		}

		XmlDomHandler mxh = new XmlDomHandler();
		Transformer transformer = mxh.getTransformer();
		Document doc = mxh.getDoc();

		Element rootElement = mxh.getRootElement();
		doc.appendChild(rootElement);

		Element studenti = doc.createElement(LabParseStringConstants.STUD_ROOT);
		rootElement.appendChild(studenti);

		for (int i = 0; i < studList.size(); i++) {
			Element student = doc
					.createElement(LabParseStringConstants.STUD_TAG);

			studenti.appendChild(student);
			Element s_id = doc.createElement(LabParseStringConstants.STUD_ID);
			student.appendChild(s_id);
			s_id.appendChild(doc.createTextNode(Long.toString(studList.get(i)
					.getStudentId())));

			Element nume = doc.createElement(LabParseStringConstants.STUD_NUME);
			student.appendChild(nume);
			nume.appendChild(doc.createTextNode(studList.get(i).getNume()));

			Element prenume = doc
					.createElement(LabParseStringConstants.STUD_PRENUME);
			student.appendChild(prenume);
			prenume.appendChild(doc
					.createTextNode(studList.get(i).getPrenume()));

			Element grupa = doc
					.createElement(LabParseStringConstants.STUD_GRUPA);
			student.appendChild(grupa);
			grupa.appendChild(doc.createTextNode(studList.get(i).getGrupa()));

			Element email = doc
					.createElement(LabParseStringConstants.STUD_EMAIL);
			student.appendChild(email);
			email.appendChild(doc.createTextNode(studList.get(i).getEmail()));

			Element telFix = doc
					.createElement(LabParseStringConstants.STUD_TEL_FIX);
			student.appendChild(telFix);
			telFix.appendChild(doc.createTextNode(studList.get(i).getTelFix()));

		}


		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			throw e;
		}
		String xmlString = sw.toString();
		logger.info("Sfirsit methoda parser");
		return xmlString;
	}

}