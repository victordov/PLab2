package md.victordov.lab.services;

import java.util.ArrayList;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.dao.UnivDAO;
import md.victordov.lab.vo.Universitate;

public class UniversitateService implements GenericService<Universitate> {

	private UnivDAO _univDAO;

	public UniversitateService(UnivDAO univDAO) {
		_univDAO = univDAO;

	}

	
	public long createFunction(Universitate univ) throws MyDaoException {

		_univDAO.create(univ);
		return univ.getUniversitateId();
	}

	
	public boolean updateFunction(Universitate univ) throws MyDaoException {

		return _univDAO.update(univ);
	}

	
	public Universitate getOne(Long id) throws MyDaoException {
		// Long id = 0L;
		Universitate univ = new Universitate();

		univ = (Universitate) _univDAO.retrieve(id);

		return (Universitate) univ;

	}

	
	public ArrayList<Universitate> getAll() throws MyDaoException {
		ArrayList<Universitate> arrayUniver = new ArrayList<Universitate>();

		arrayUniver.addAll(_univDAO.retrieve());
		return arrayUniver;

	}

	
	public boolean deleteFunction(Long id) {

		return _univDAO.delete(id);
	}

}
