package md.victordov.lab.services;

import java.io.*;

import javax.xml.parsers.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.*;

import md.victordov.lab.vo.*;
import md.victordov.lab.common.exception.MyServiceException;
import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.common.other.GetTagValue;
import md.victordov.lab.dao.*;
import md.victordov.lab.common.other.LabParseStringConstants;

public class LabXmlParser {
	static Logger logger = LogManager.getLogger(LabXmlParser.class);

	public LabXmlParser(String file) throws MyServiceException {
		logger.info("Initialisation of class");
		this.file = file;

		DB_FACTORY = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = DB_FACTORY.newDocumentBuilder();
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		}

		try {
			DOC = dBuilder.parse(file);
		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		DOC.getDocumentElement().normalize();

	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void parseStudentXML() throws MyServiceException {
		logger.info("Start of Method parseStudent");
		N_LIST = DOC.getElementsByTagName(LabParseStringConstants.STUD_TAG);
		if (N_LIST.getLength() > 0) {

			GenericService<Student> genService = new StudentService(
					new StudentDAO());
			for (int temp = 0; temp < N_LIST.getLength(); temp++) {

				Node nNode = N_LIST.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					Student stud = new Student();
					stud.setStudentId(Long.parseLong(GetTagValue.getTagValue(
							LabParseStringConstants.STUD_ID, eElement)));
					stud.setNume(GetTagValue.getTagValue(
							LabParseStringConstants.STUD_NUME, eElement));
					stud.setPrenume(GetTagValue.getTagValue(
							LabParseStringConstants.STUD_PRENUME, eElement));
					stud.setGrupa(GetTagValue.getTagValue(
							LabParseStringConstants.STUD_GRUPA, eElement));
					stud.setEmail(GetTagValue.getTagValue(
							LabParseStringConstants.STUD_EMAIL, eElement));
					stud.setTelFix(GetTagValue.getTagValue(
							LabParseStringConstants.STUD_TEL_FIX, eElement));

					try {
						genService.createFunction(stud);
					} catch (MyDaoException e) {
						logger.error("Could not store student in database", e);
					}

				}
			}
		}
	}

	public void parseProfesorXML() throws MyServiceException {
		/*
		 * Incercarea de a adauga Profesor
		 */
		logger.info("Start of method to add profesor from xml");
		try {
			logger.debug("In try method of addjng profesor");
			N_LIST = DOC.getElementsByTagName(LabParseStringConstants.PROF_TAG);
			if (N_LIST.getLength() > 0) {

				GenericService<Profesor> genService = new ProfesorService(
						new ProfesorDAO());
				for (int temp = 0; temp < N_LIST.getLength(); temp++) {

					Node nNode = N_LIST.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						Profesor prof = new Profesor();
						prof.setProfesorId(Long.parseLong(GetTagValue
								.getTagValue(LabParseStringConstants.PROF_ID,
										eElement)));
						prof.setNume(GetTagValue.getTagValue(
								LabParseStringConstants.PROF_NUME, eElement));
						prof.setPrenume(GetTagValue.getTagValue(
								LabParseStringConstants.PROF_PRENUME, eElement));
						prof.setAdresa(GetTagValue.getTagValue(
								LabParseStringConstants.PROF_ADRESA, eElement));

						genService.createFunction(prof);

					}
				}
			}
			logger.debug("End of try method of adding profeosr");
		} catch (Exception e) {
			throw new MyServiceException("Eroare in Elementul Profesor", e);
		}
		logger.info("End of method of adding profesor from xml");
	}

	public void parseCursXML() throws MyServiceException {
		/*
		 * Incercarea de a adauga Curs
		 */
		logger.info("Start of parseCursXml");
		try {
			logger.debug("Trying to extract Curs from xml");
			N_LIST = DOC.getElementsByTagName(LabParseStringConstants.CURS_TAG);
			if (N_LIST.getLength() > 0) {
				GenericService<Curs> genService = new CursService(new CursDAO());
				for (int temp = 0; temp < N_LIST.getLength(); temp++) {

					Node nNode = N_LIST.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						Curs curs = new Curs();
						curs.setCursId(Long.parseLong(GetTagValue.getTagValue(
								LabParseStringConstants.CURS_ID, eElement)));
						curs.setNumeCurs(GetTagValue.getTagValue(
								LabParseStringConstants.CURS_NUME, eElement));
						curs.setUniversitateId(Long.parseLong(GetTagValue
								.getTagValue(
										LabParseStringConstants.CURS_UNIV_ID,
										eElement)));
						curs.setProfesorId(Long.parseLong(GetTagValue
								.getTagValue(
										LabParseStringConstants.CURS_UNIV_ID,
										eElement)));

						genService.createFunction(curs);

					}
				}
			}
		} catch (Exception e) {
			logger.warn("Eroare produsa in Elementul Curs");
		}
		logger.info("End of parseCursXml");
	}

	public void parseUniversitateXML() throws MyServiceException {
		/*
		 * Incercarea de a adauga universitate
		 */
		logger.info("Start of the parse Xml Univeristy");
		try {
			N_LIST = DOC.getElementsByTagName(LabParseStringConstants.UNIV_TAG);
			if (N_LIST.getLength() > 0) {

				GenericService<Universitate> genService = new UniversitateService(
						new UnivDAO());
				for (int temp = 0; temp < N_LIST.getLength(); temp++) {

					Node nNode = N_LIST.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						Universitate univ = new Universitate();
						univ.setUniversitateId(Long.parseLong(GetTagValue
								.getTagValue(LabParseStringConstants.UNIV_ID,
										eElement)));
						univ.setNumeUniversitate(GetTagValue.getTagValue(
								LabParseStringConstants.UNIV_NUME, eElement));
						univ.setAdresa(GetTagValue.getTagValue(
								LabParseStringConstants.UNIV_ADRESA, eElement));
						univ.setTelefon(GetTagValue.getTagValue(
								LabParseStringConstants.UNIV_TEL, eElement));

						genService.createFunction(univ);

					}
				}
			}
		} catch (Exception e) {
			logger.warn("Eroare produsa in Elementul Universitate");
		}
		logger.info("End of the parse Xml Univeristy");
	}

	private String file;
	private static DocumentBuilderFactory DB_FACTORY = null;
	private static Document DOC = null;
	private static NodeList N_LIST = null;
}
