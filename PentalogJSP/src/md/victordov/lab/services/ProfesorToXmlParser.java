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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import md.victordov.lab.Test.XmlDomHandler;
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
	 * @param parser
	 *            , will be accessed as a static function without Class
	 *            initialization
	 * @throws MyServiceException
	 * @throws MyDaoException
	 * <strong>parser method will extract the records from table profesor from database and will be stored in the Array of Profesor class</strong>
	 *  
	 */

	static Logger logger = LogManager.getLogger(ProfesorToXmlParser.class);
	public static String parser() throws MyServiceException, MyDaoException {
		logger.info("Initializare metoda parser");
		GenericService<Profesor> genService = new ProfesorService(
				new ProfesorDAO());
		ArrayList<Profesor> profList = new ArrayList<Profesor>();
		try {
			profList = genService.getAll();
		} catch (MyDaoException e2) {

			throw new MyServiceException("Hello", e2);
		}

		XmlDomHandler mxh = new XmlDomHandler();
		Transformer transformer = mxh.getTransformer();
		Document doc = mxh.getDoc();

		Element rootElement = mxh.getRootElement();
		doc.appendChild(rootElement);

		// student elements
		Element profesori = doc
				.createElement(LabParseStringConstants.PROF_ROOT);
		rootElement.appendChild(profesori);

		for (int i = 0; i < profList.size(); i++) {
			Element profesor = doc
					.createElement(LabParseStringConstants.PROF_TAG);

			profesori.appendChild(profesor);
			Element u_id = doc.createElement(LabParseStringConstants.PROF_ID);
			profesor.appendChild(u_id);
			u_id.appendChild(doc.createTextNode(Long.toString(profList.get(i)
					.getProfesorId())));

			Element nume = doc.createElement(LabParseStringConstants.PROF_NUME);
			profesor.appendChild(nume);
			nume.appendChild(doc.createTextNode(profList.get(i).getNume()));

			Element prenume = doc
					.createElement(LabParseStringConstants.PROF_PRENUME);
			profesor.appendChild(prenume);
			prenume.appendChild(doc
					.createTextNode(profList.get(i).getPrenume()));

			Element adresa = doc
					.createElement(LabParseStringConstants.PROF_ADRESA);
			profesor.appendChild(adresa);
			adresa.appendChild(doc.createTextNode(profList.get(i).getAdresa()));

		}

		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			throw new MyServiceException(e.getMessage(), e);
		}
		String xmlString = sw.toString();
		logger.info("Sfirsit metoda parser");
		return xmlString;
	}
}