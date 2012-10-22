package md.victordov.lab.services;

import java.io.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.*;

import md.victordov.lab.vo.*;
import md.victordov.lab.common.exception.MyServiceException;
import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.common.other.GetTagValue;
import md.victordov.lab.dao.*;
import md.victordov.lab.common.other.LabParseStringConstants;

public class LabXmlParser {
	private static DocumentBuilderFactory DB_FACTORY = null;
	private static Document DOC = null;
	private static NodeList N_LIST = null;

	public void parseStudentXML() throws MyServiceException {
		N_LIST = DOC.getElementsByTagName(LabParseStringConstants.STUD_TAG);
		System.out.println("Nr de studenti in lista: " + N_LIST.getLength());
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
						
					}

				}
			}
		}
	}

	public void parseProfesorXML() throws MyServiceException {
		/*
		 * Incercarea de a adauga Profesor
		 */
		try {
			N_LIST = DOC.getElementsByTagName(LabParseStringConstants.PROF_TAG);
			System.out.println("Nr de profesori in lista: "
					+ N_LIST.getLength());
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
		} catch (Exception e) {
			System.out.println("Eroare produsa in Elementul Profesor");
			throw new MyServiceException("Eroare in Elementul Profesor", e);
		}
	}

	public void parseCursXML() throws MyServiceException {
		/*
		 * Incercarea de a adauga Curs
		 */
		try {
			N_LIST = DOC.getElementsByTagName(LabParseStringConstants.CURS_TAG);
			System.out.println("Nr de cursuri in lista: " + N_LIST.getLength());
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
			System.out.println("Eroare produsa in Elementul Curs");
		}
	}

	public void parseUniversitateXML() throws MyServiceException {
		/*
		 * Incercarea de a adauga universitate
		 */
		try {
			N_LIST = DOC.getElementsByTagName(LabParseStringConstants.UNIV_TAG);
			System.out.println("Nr de universitati in lista: "
					+ N_LIST.getLength());
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
			System.out.println("Eroare produsa in Elementul Universitate");
		}
	}

	public LabXmlParser(String file) throws MyServiceException {
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

	private String file;

}
