package md.victordov.lab.services;

import java.util.ArrayList;
import java.util.Iterator;


import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.dao.CursDAO;
import md.victordov.lab.vo.Curs;

public class CursService implements GenericService<Curs> {

	private CursDAO _cursDAO;

	public CursService(CursDAO cursDAO) {
		_cursDAO = cursDAO;

	}

	@Override
	public long createFunction(Curs curs) throws MyDaoException {

		_cursDAO.create(curs);
		return curs.getCursId();
	}

	@Override
	public boolean updateFunction(Curs curs) throws MyDaoException {

		return _cursDAO.update(curs);

	}

	@Override
	public Curs getOne(Long id) throws MyDaoException {
		Curs curs = new Curs();
		curs = _cursDAO.retrieve(id);
		return curs;

	}

	@Override
	public ArrayList<Curs> getAll() throws MyDaoException {
		ArrayList<Curs> arrayCurs = new ArrayList<Curs>();
		arrayCurs.addAll(_cursDAO.retrieve());
		Iterator<Curs> it = arrayCurs.iterator();
		// System.out.printf("|%-10s|%-20s|%-15s|%-12s|\n", "ID Curs",
		// "Nume Curs", "ID Universitate", "ID Profesor");
		while (it.hasNext()) {
			Curs outputCurs = new Curs();
			outputCurs = it.next();
			outputCurs.printCurs();
		}
		return arrayCurs;
	}

	@Override
	public boolean deleteFunction(Long id) {

		return _cursDAO.delete(id);
	}

}
