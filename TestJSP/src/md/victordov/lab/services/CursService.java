package md.victordov.lab.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.common.other.HasNumber;
import md.victordov.lab.dao.CursDAO;
import md.victordov.lab.main.MenuText;
import md.victordov.lab.vo.Curs;

public class CursService implements GenericService {

	private Scanner _sc;
	private CursDAO _cursDAO;

	public CursService(CursDAO cursDAO) {
		_cursDAO = cursDAO;
		_sc = new Scanner(System.in);
	}

	@Override
	public long createFunction() throws MyDaoException {
		Curs curs = new Curs();
		boolean nextInput = false;
		String tempString = new String();
		do {
			try {
				//System.out.println(MenuText.inID);
				curs.setCursId(Long.parseLong(_sc.nextLine()));
				nextInput = true;
			} catch (NumberFormatException nfe) {
				//System.out.println("Introduceti un numar valid");
			}

		} while (nextInput != true);

		nextInput = false;

		do {
			//System.out.println(MenuText.inNumeCurs);
			tempString = _sc.nextLine();
			if (!HasNumber.hasNumber(tempString)) {
				curs.setNumeCurs(tempString);
				nextInput = true;
			}
		} while (nextInput != true);

		//System.out.println(MenuText.inIdUniver);
		curs.setUniversitateId(Long.parseLong(_sc.nextLine()));

		//System.out.println(MenuText.inIdProfesor);
		curs.setProfesorId(Long.parseLong(_sc.nextLine()));

		_cursDAO.create(curs);

		return curs.getCursId();
	}

	@Override
	public boolean updateFunction() throws MyDaoException {
		Curs curs = new Curs();
		boolean nextInput = false;
		String tempString = new String();
		do {
			try {
				//System.out.println(MenuText.inID);
				curs.setCursId(Long.parseLong(_sc.nextLine()));
				nextInput = true;
			} catch (NumberFormatException nfe) {
				//System.out.println("Introduceti un numar valid");
			}

		} while (nextInput != true);

		nextInput = false;

		do {
			//System.out.println(MenuText.inNumeCurs);
			tempString = _sc.nextLine();
			if (!HasNumber.hasNumber(tempString)) {
				curs.setNumeCurs(tempString);
				nextInput = true;
			}
		} while (nextInput != true);

		//System.out.println(MenuText.inIdUniver);
		curs.setUniversitateId(Long.parseLong(_sc.nextLine()));

		//System.out.println(MenuText.inIdProfesor);
		curs.setProfesorId(Long.parseLong(_sc.nextLine()));

		return _cursDAO.update(curs);

	}

	@Override
	public void printOne() throws MyDaoException {
		Long id = 0L;
		Curs curs = new Curs();
		try {
			//System.out.println(MenuText.inID);
			id = Long.parseLong(_sc.nextLine());
			curs = _cursDAO.retrieve(id);
			//System.out.printf("|%-10s|%-20s|%-15s|%-12s|\n", "ID Curs",
			//		"Nume Curs", "ID Universitate", "ID Profesor");
			curs.printCurs();
		} catch (NumberFormatException nfe) {
			System.err.println("Introduceti un numar intreg valid");
		}

	}

	@Override
	public ArrayList<Curs> printAll() throws MyDaoException {
		ArrayList<Curs> arrayCurs = new ArrayList<Curs>();
		arrayCurs.addAll(_cursDAO.retrieve());
		Iterator<Curs> it = arrayCurs.iterator();
		//System.out.printf("|%-10s|%-20s|%-15s|%-12s|\n", "ID Curs",
		//		"Nume Curs", "ID Universitate", "ID Profesor");
		while (it.hasNext()) {
			Curs outputCurs = new Curs();
			outputCurs = it.next();
			outputCurs.printCurs();
		}
		return arrayCurs;
	}

}
