package md.victordov.lab.services;

import java.util.ArrayList;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.dao.StudentDAO;
import md.victordov.lab.vo.Student;

public class StudentService implements GenericService<Student> {

	private StudentDAO studentDAO;

	public StudentService(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public long createFunction(Student stud) {

		this.studentDAO.create(stud);
		return stud.getStudentId();
	}

	public boolean updateFunction(Student stud) throws MyDaoException {

		return this.studentDAO.update(stud);
	}

	public Student getOne(Long id) {
		Student stud = new Student();
		stud = (Student) this.studentDAO.retrieve(id);
		return stud;
	}

	public ArrayList<Student> getAll() {
		ArrayList<Student> arrayStud = new ArrayList<Student>();
		arrayStud.addAll(this.studentDAO.retrieve());

		return arrayStud;
	}

	public boolean deleteFunction(Long id) {

		return this.studentDAO.delete(id);
	}

}
