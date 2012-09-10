package md.victordov.lab.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.common.other.HasNumber;
import md.victordov.lab.dao.StudentDAO;
import md.victordov.lab.vo.Student;

public class StudentService implements GenericService<Student> {

	private StudentDAO _studentDAO;

	public StudentService(StudentDAO studentDAO) {

		_studentDAO = studentDAO;
	}

	@Override
	public long createFunction() {
		Student stud = new Student();
		Scanner sc = new Scanner(System.in);
		boolean nextInput = false;
		String tempString = new String();
		do {
			try {
				// System.out.println(MenuText.inID);
				stud.setStudentId(Long.parseLong(sc.nextLine()));
				nextInput = true;
			} catch (NumberFormatException nfe) {
				// System.out.println("Introduceti un numar valid");
			}

		} while (nextInput != true);

		nextInput = false;

		do {
			// System.out.println(MenuText.inNume);
			tempString = sc.nextLine();
			if (!HasNumber.hasNumber(tempString)) {
				stud.setNume(tempString);
				nextInput = true;
			}
		} while (nextInput != true);

		nextInput = false;

		do {
			// System.out.println(MenuText.inPrenume);
			tempString = sc.nextLine();
			if (!HasNumber.hasNumber(tempString)) {
				stud.setPrenume(tempString);
				nextInput = true;
			}
		} while (nextInput != true);

		// System.out.println(MenuText.inGrupa);
		stud.setGrupa(sc.nextLine());

		// System.out.println(MenuText.inEmail);
		stud.setEmail(sc.nextLine());

		// System.out.println(MenuText.inTelefon);
		stud.setTelFix(sc.nextLine());
		_studentDAO.create(stud);
		return stud.getStudentId();
	}

	@Override
	public boolean updateFunction(Student stud) throws MyDaoException {

	
		return _studentDAO.update(stud);
	}

	@Override
	public Student getOne(Long id) {
		Student stud = new Student();
		stud = (Student) _studentDAO.retrieve(id);

		return stud;

	}

	@Override
	public ArrayList<Student> getAll() {

		ArrayList<Student> arrayStud = new ArrayList<Student>();
		arrayStud.addAll(_studentDAO.retrieve());
		Iterator<Student> it = arrayStud.iterator();
		// System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-25s|%-12s|\n",
		// "ID Stud",
		// "Nume", "Prenume", "Grupa", "Email", "TelFix");
		while (it.hasNext()) {
			Student outputStud = new Student();
			outputStud = it.next();
			outputStud.printStudent();

		}
		return arrayStud;
	}

	@Override
	public boolean deleteFunction(Long id) {
		
		return _studentDAO.delete(id);
	}

}
