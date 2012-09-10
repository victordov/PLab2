package md.victordov.lab.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.common.other.HasNumber;
import md.victordov.lab.dao.UnivDAO;
import md.victordov.lab.vo.Universitate;

public class UniversitateService implements GenericService<Universitate> {

	private Scanner _sc;
	private UnivDAO _univDAO;

	public UniversitateService(UnivDAO univDAO) {
		_univDAO = univDAO;
		_sc = new Scanner(System.in);
	}

	@Override
	public long createFunction() throws MyDaoException {
		boolean nextInput = false;
		String tempString = new String();
		Universitate univ = new Universitate();
		do {
			try {
				// System.out.println(MenuText.inID);
				univ.setUniversitateId(Long.parseLong(_sc.nextLine()));
				nextInput = true;
			} catch (NumberFormatException nfe) {
				// System.out.println("Introduceti un numar valid");
			}

		} while (nextInput != true);

		nextInput = false;

		do {
			// System.out.println(MenuText.inNumeUniver);
			tempString = _sc.nextLine();
			if (!HasNumber.hasNumber(tempString)) {
				univ.setNumeUniversitate(tempString);
				nextInput = true;
			}
		} while (nextInput != true);

		// System.out.println(MenuText.inAdresa);
		univ.setAdresa(_sc.nextLine());

		// System.out.println(MenuText.inTelefon);
		univ.setTelefon(_sc.nextLine());
		_univDAO.create(univ);
		return univ.getUniversitateId();
	}

	@Override
	public boolean updateFunction(Universitate univ) throws MyDaoException {
		
		return _univDAO.update(univ);
	}

	@Override
	public Universitate getOne(Long id) throws MyDaoException {
		//Long id = 0L;
		Universitate univ = new Universitate();
		
			univ = (Universitate) _univDAO.retrieve(id);
		
		return (Universitate)univ;

	}

	@Override
	public ArrayList<Universitate> getAll() throws MyDaoException {
		ArrayList<Universitate> arrayUniver = new ArrayList<Universitate>();

		arrayUniver.addAll(_univDAO.retrieve());
		Iterator<Universitate> it = arrayUniver.iterator();
		// System.out.printf("|%-10s|%-19s|%-27s|%-15s\n", "ID Univer",
		// "Nume Universitate", "Adresa", "Telefon");
//		while (it.hasNext()) {
//			Universitate outputUniver = new Universitate();
//			outputUniver = it.next();
//			outputUniver.printUniversitate();
//		}
		return arrayUniver;

	}

	@Override
	public boolean deleteFunction(Long id) {
		
		return _univDAO.delete(id); 
	}


}
