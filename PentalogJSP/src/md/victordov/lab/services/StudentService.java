package md.victordov.lab.services;

import java.util.ArrayList;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.dao.StudentDAO;
import md.victordov.lab.vo.Student;

public class StudentService implements GenericService<Student> {

	private StudentDAO _studentDAO;

	public StudentService(StudentDAO studentDAO) {

		_studentDAO = studentDAO;
	}

	
	public long createFunction(Student stud) {
		
		_studentDAO.create(stud);
		return stud.getStudentId();
	}

	
	public boolean updateFunction(Student stud) throws MyDaoException {

	
		return _studentDAO.update(stud);
	}

	
	public Student getOne(Long id) {
		Student stud = new Student();
		stud = (Student) _studentDAO.retrieve(id);

		return stud;

	}

	
	public ArrayList<Student> getAll() {
		ArrayList<Student> arrayStud = new ArrayList<Student>();
		arrayStud.addAll(_studentDAO.retrieve());
				
		return arrayStud;
	}

	
	public boolean deleteFunction(Long id) {
		
		return _studentDAO.delete(id);
	}

}
