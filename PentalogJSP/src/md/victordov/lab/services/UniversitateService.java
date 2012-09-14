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

	@Override
	public long createFunction(Universitate univ) throws MyDaoException {

		_univDAO.create(univ);
		return univ.getUniversitateId();
	}

	@Override
	public boolean updateFunction(Universitate univ) throws MyDaoException {

		return _univDAO.update(univ);
	}

	@Override
	public Universitate getOne(Long id) throws MyDaoException {
		// Long id = 0L;
		Universitate univ = new Universitate();

		univ = (Universitate) _univDAO.retrieve(id);

		return (Universitate) univ;

	}

	@Override
	public ArrayList<Universitate> getAll() throws MyDaoException {
		ArrayList<Universitate> arrayUniver = new ArrayList<Universitate>();

		arrayUniver.addAll(_univDAO.retrieve());
		return arrayUniver;

	}

	@Override
	public boolean deleteFunction(Long id) {

		return _univDAO.delete(id);
	}

}
