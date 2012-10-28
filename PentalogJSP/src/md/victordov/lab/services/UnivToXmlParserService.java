/**
 * 
 */
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
import md.victordov.lab.dao.UnivDAO;
import md.victordov.lab.vo.Universitate;

/**
 * @author victor
 * 
 */
public class UnivToXmlParserService {

	/**
	 * @param args
	 * @param parser
	 *            , will be accessed as a static function without Class
	 *            initialization
	 * @throws TransformerException
	 */
	static Logger logger = LogManager.getLogger(UnivToXmlParserService.class);
	public static String parser() throws MyServiceException, MyDaoException,
			TransformerException {
		logger.info("Initializare extragere BD in String");
		GenericService<Universitate> genService = new UniversitateService(
				new UnivDAO());
		ArrayList<Universitate> univList = new ArrayList<Universitate>();
		try {
			univList = genService.getAll();
		} catch (MyDaoException mDexcp) {

			throw mDexcp;
		}

		XmlDomHandler mxh = new XmlDomHandler();
		Transformer transformer = mxh.getTransformer();
		Document doc = mxh.getDoc();

		Element rootElement = mxh.getRootElement();
		doc.appendChild(rootElement);

		// student elements
		Element universitati = doc
				.createElement(LabParseStringConstants.UNIV_ROOT);
		rootElement.appendChild(universitati);

		for (int i = 0; i < univList.size(); i++) {
			Element universitate = doc
					.createElement(LabParseStringConstants.UNIV_TAG);

			universitati.appendChild(universitate);
			Element u_id = doc.createElement(LabParseStringConstants.UNIV_ID);
			universitate.appendChild(u_id);
			u_id.appendChild(doc.createTextNode(Long.toString(univList.get(i)
					.getUniversitateId())));

			Element nume = doc.createElement(LabParseStringConstants.UNIV_NUME);
			universitate.appendChild(nume);
			nume.appendChild(doc.createTextNode(univList.get(i)
					.getNumeUniversitate()));

			Element adresa = doc
					.createElement(LabParseStringConstants.UNIV_ADRESA);
			universitate.appendChild(adresa);
			adresa.appendChild(doc.createTextNode(univList.get(i).getAdresa()));

			Element telefon = doc
					.createElement(LabParseStringConstants.UNIV_TEL);
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
			throw e;
		}
		String xmlString = sw.toString();
		logger.info("Sfirsit extragere BD in String");

		return xmlString;
	}

}
