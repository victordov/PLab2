package md.victordov.lab.services;

import java.util.ArrayList;
import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.dao.CursDAO;
import md.victordov.lab.vo.Curs;

public class CursService implements GenericService<Curs> {

	private CursDAO _cursDAO;

	public CursService(CursDAO cursDAO) {
		_cursDAO = cursDAO;

	}

	
	public long createFunction(Curs curs) throws MyDaoException {

		_cursDAO.create(curs);
		return curs.getCursId();
	}

	
	public boolean updateFunction(Curs curs) throws MyDaoException {

		return _cursDAO.update(curs);

	}

	
	public Curs getOne(Long id) throws MyDaoException {
		Curs curs = new Curs();
		curs = _cursDAO.retrieve(id);
		return curs;

	}

	
	public ArrayList<Curs> getAll() throws MyDaoException {
		ArrayList<Curs> arrayCurs = new ArrayList<Curs>();
		arrayCurs.addAll(_cursDAO.retrieve());
		return arrayCurs;
	}

	
	public boolean deleteFunction(Long id) {

		return _cursDAO.delete(id);
	}

}
