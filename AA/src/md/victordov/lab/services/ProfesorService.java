package md.victordov.lab.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.common.other.HasNumber;
import md.victordov.lab.dao.ProfesorDAO;
import md.victordov.lab.vo.Profesor;

public class ProfesorService implements GenericService<Profesor> {

	private ProfesorDAO _profesorDAO;
	private Scanner _sc;

	public ProfesorService(ProfesorDAO profesorDAO) {
		_profesorDAO = profesorDAO;
		_sc = new Scanner(System.in);
	}

	@Override
	public long createFunction() throws MyDaoException {
		boolean nextInput = false;
		String tempString = new String();
		Profesor prof = new Profesor();
		do {
			try {
				// System.out.println(MenuText.inID);
				prof.setProfesorId(Long.parseLong(_sc.nextLine()));
				nextInput = true;
			} catch (NumberFormatException nfe) {
				// System.out.println("Introduceti un numar valid");
			}

		} while (nextInput != true);

		nextInput = false;

		do {
			// System.out.println(MenuText.inNume);
			tempString = _sc.nextLine();
			if (!HasNumber.hasNumber(tempString)) {
				prof.setNume(tempString);
				nextInput = true;
			}
		} while (nextInput != true);

		nextInput = false;

		do {
			// System.out.println(MenuText.inPrenume);
			tempString = _sc.nextLine();
			if (!HasNumber.hasNumber(tempString)) {
				prof.setPrenume(tempString);
				nextInput = true;
			}
		} while (nextInput != true);

		// System.out.println(MenuText.inAdresa);
		prof.setAdresa(_sc.nextLine());
		return prof.getProfesorId();
	}

	@Override
	public boolean updateFunction(Profesor prof) throws MyDaoException {

		return _profesorDAO.update(prof);
	}

	@Override
	public Profesor getOne(Long id) throws MyDaoException {

		Profesor prof = new Profesor();

		prof = _profesorDAO.retrieve(id);
		return prof;

	}

	@Override
	public ArrayList<Profesor> getAll() throws MyDaoException {
		ArrayList<Profesor> arrayProf = new ArrayList<Profesor>();
		arrayProf.addAll(_profesorDAO.retrieve());
		Iterator<Profesor> it = arrayProf.iterator();
		// System.out.printf("|%-10s|%-15s|%-15s|%-25s|\n", "ID Prof", "Nume",
		// "Prenume", "Adresa");
		while (it.hasNext()) {
			Profesor outputProf = new Profesor();
			outputProf = it.next();
			outputProf.printProfesor();
		}
		return arrayProf;
	}

	@Override
	public boolean deleteFunction(Long id) {

		return _profesorDAO.delete(id);
	}

}
