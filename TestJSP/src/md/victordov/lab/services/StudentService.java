package md.victordov.lab.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.common.other.HasNumber;
import md.victordov.lab.dao.StudentDAO;
import md.victordov.lab.main.MenuText;
import md.victordov.lab.vo.Student;

public class StudentService implements GenericService {

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
				//System.out.println(MenuText.inID);
				stud.setStudentId(Long.parseLong(sc.nextLine()));
				nextInput = true;
			} catch (NumberFormatException nfe) {
				//System.out.println("Introduceti un numar valid");
			}

		} while (nextInput != true);

		nextInput = false;

		do {
			//System.out.println(MenuText.inNume);
			tempString = sc.nextLine();
			if (!HasNumber.hasNumber(tempString)) {
				stud.setNume(tempString);
				nextInput = true;
			}
		} while (nextInput != true);

		nextInput = false;

		do {
			//System.out.println(MenuText.inPrenume);
			tempString = sc.nextLine();
			if (!HasNumber.hasNumber(tempString)) {
				stud.setPrenume(tempString);
				nextInput = true;
			}
		} while (nextInput != true);

		//System.out.println(MenuText.inGrupa);
		stud.setGrupa(sc.nextLine());

		//System.out.println(MenuText.inEmail);
		stud.setEmail(sc.nextLine());

		//System.out.println(MenuText.inTelefon);
		stud.setTelFix(sc.nextLine());
		_studentDAO.create(stud);
		return stud.getStudentId();
	}

	@Override
	public boolean updateFunction() throws MyDaoException {
		Student stud = new Student();
		Scanner sc = new Scanner(System.in);
		boolean nextInput = false;
		boolean updated = false;
		String tempString = new String();

		do {
			try {
				//System.out.println(MenuText.inID);
				stud.setStudentId(Long.parseLong(sc.nextLine()));
				nextInput = true;
			} catch (NumberFormatException nfe) {
				//System.out.println("Introduceti un numar valid");
			}

		} while (nextInput != true);

		nextInput = false;

		do {
			//System.out.println(MenuText.inNume);
			tempString = sc.nextLine();
			if (!HasNumber.hasNumber(tempString)) {
				stud.setNume(tempString);
				nextInput = true;
			}
		} while (nextInput != true);

		nextInput = false;

		do {
			//System.out.println(MenuText.inPrenume);
			tempString = sc.nextLine();
			if (!HasNumber.hasNumber(tempString)) {
				stud.setPrenume(tempString);
				nextInput = true;
			}
		} while (nextInput != true);

		//System.out.println(MenuText.inGrupa);
		stud.setGrupa(sc.nextLine());

		//System.out.println(MenuText.inEmail);
		stud.setEmail(sc.nextLine());

		//System.out.println(MenuText.inTelefon);
		stud.setTelFix(sc.nextLine());
		updated = _studentDAO.update(stud);
		try{
			sc.close();
		}catch(IllegalStateException e){
			e.printStackTrace();
		}
		return updated;
	}

	@Override
	public void printOne() {
		Scanner sc = new Scanner(System.in);
		Long id = 0L;
		Student stud = new Student();
		//System.out
		//		.println("Introdu ID-ul studentului ce urmeaza sa fie afisat");
		try {
			id = Long.parseLong(sc.nextLine());
			stud = (Student) _studentDAO.retrieve(id);
			//System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-25s|%-12s|\n",
			//		"ID Stud", "Nume", "Prenume", "Grupa", "Email", "TelFix");
			stud.printStudent();
		} catch (NumberFormatException nfe) {
			System.err.println("Introduceti un numar intreg valid");
		}

	}

	@Override
	public ArrayList<Student> printAll() {

		ArrayList<Student> arrayStud = new ArrayList<Student>();
		arrayStud.addAll(_studentDAO.retrieve());
		Iterator<Student> it = arrayStud.iterator();
		//System.out.printf("|%-10s|%-15s|%-15s|%-10s|%-25s|%-12s|\n", "ID Stud",
		//		"Nume", "Prenume", "Grupa", "Email", "TelFix");
		while (it.hasNext()) {
			Student outputStud = new Student();
			outputStud = it.next();
			outputStud.printStudent();

		}
		return arrayStud;
	}

}
