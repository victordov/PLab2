package md.victordov.lab.services;

import java.util.ArrayList;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.dao.ProfesorDAO;
import md.victordov.lab.vo.Profesor;

public class ProfesorService implements GenericService<Profesor> {

	private ProfesorDAO _profesorDAO;

	public ProfesorService(ProfesorDAO profesorDAO) {
		_profesorDAO = profesorDAO;
	}

	
	public long createFunction(Profesor prof) throws MyDaoException {

		_profesorDAO.create(prof);
		return prof.getProfesorId();
	}
	 
	
	public boolean updateFunction(Profesor prof) throws MyDaoException {

		return _profesorDAO.update(prof);
	}

	
	public Profesor getOne(Long id) throws MyDaoException {

		Profesor prof = new Profesor();

		prof = _profesorDAO.retrieve(id);
		return prof;

	}

	
	public ArrayList<Profesor> getAll() throws MyDaoException {
		ArrayList<Profesor> arrayProf = new ArrayList<Profesor>();
		arrayProf.addAll(_profesorDAO.retrieve());
		
		return arrayProf;
	}

	
	public boolean deleteFunction(Long id) {

		return _profesorDAO.delete(id);
	}

}
