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
	static Logger logger = LogManager.getLogger(CursToXmlParserService.class);
	public static String parser() throws MyServiceException, MyDaoException {
		
		logger.info("Inceput Clasa CursXml Parser Service");
		GenericService<Curs> genService = new CursService(new CursDAO());
		ArrayList<Curs> cursList = new ArrayList<Curs>();
		try {
			cursList = genService.getAll();
		} catch (MyDaoException e) {
			throw e;
		}

		XmlDomHandler mxh = new XmlDomHandler();
		Transformer transformer = mxh.getTransformer();

		Document doc = mxh.getDoc();
		Element rootElement = mxh.getRootElement();
		doc.appendChild(rootElement);

		// curs elements
		Element cursuri = doc.createElement(LabParseStringConstants.CURS_ROOT);
		rootElement.appendChild(cursuri);

		for (int i = 0; i < cursList.size(); i++) {
			Element curs = doc.createElement(LabParseStringConstants.CURS_TAG);
			cursuri.appendChild(curs);

			Element s_id = doc.createElement(LabParseStringConstants.CURS_ID);
			s_id.appendChild(doc.createTextNode(Long.toString(cursList.get(i)
					.getCursId())));
			curs.appendChild(s_id);

			Element nume_curs = doc
					.createElement(LabParseStringConstants.CURS_NUME);
			nume_curs.appendChild(doc.createTextNode(cursList.get(i)
					.getNumeCurs()));
			curs.appendChild(nume_curs);

			Element unver_id = doc
					.createElement(LabParseStringConstants.CURS_UNIV_ID);
			unver_id.appendChild(doc.createTextNode(Long.toString(cursList.get(
					i).getUniversitateId())));
			curs.appendChild(unver_id);

			Element prof_id = doc
					.createElement(LabParseStringConstants.CURS_PROF_ID);
			prof_id.appendChild(doc.createTextNode(Long.toString(cursList
					.get(i).getProfesorId())));
			curs.appendChild(prof_id);

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
		logger.info("Sfirsit Clasa CursXml Parser Service");
		return xmlString;
	}

}