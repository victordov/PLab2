package md.victordov.lab.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.dao.CursDAO;
import md.victordov.lab.dao.GenericDAO;
import md.victordov.lab.dao.ProfesorDAO;
import md.victordov.lab.dao.StudentDAO;
import md.victordov.lab.dao.UnivDAO;
import md.victordov.lab.services.CursService;
import md.victordov.lab.services.GenericService;
import md.victordov.lab.services.ProfesorService;
import md.victordov.lab.services.StudentService;
import md.victordov.lab.services.UniversitateService;

public class Main {

	/**
	 * @param args
	 */
	public static Scanner sc = new Scanner(System.in);
	public static GenericDAO gDAO = null;
	public static GenericService genService = null;

	public void Menu() throws MyDaoException {
		System.out.println(MenuText.outMenuPrincipal);
		int select = Integer.parseInt(sc.nextLine());
		switch (select) {
		case 1:
			student_Menu();
			break;
		case 2:
			profesor_Menu();
			break;
		case 3:
			universitate_Menu();
			break;
		case 4:
			curs_Menu();
			break;
		case 5:
			System.out.println("La Revedere... ");
			System.exit(0);
		default:
			Menu();
		}
	}

	private void student_Menu() throws MyDaoException {

		System.out.println(MenuText.outStudMenu);
		gDAO = new StudentDAO();
		genService = new StudentService(new StudentDAO());
		long id;
		int select = 6;
		try {
			select = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException nfe) {
			System.out.println("Nu a fost introdus un numar valid");
			student_Menu();
		}

		switch (select) {
		case 1:
			System.out.println("ID-ul creat este: "
					+ genService.createFunction());
			student_Menu();
			break;
		case 2:
			genService.printOne();
			student_Menu();
			break;
		case 3:
			System.out
					.println(genService.updateFunction() ? MenuText.outSuccess
							: MenuText.outEsuare);
			student_Menu();
			break;
		case 4:
			System.out.println(MenuText.inID);
			id = Long.parseLong(sc.nextLine());
			gDAO.delete(id);
			student_Menu();
			break;
		case 5:
			genService.printAll();
			student_Menu();
			break;
		default:
			Menu();
		}
	}

	private void profesor_Menu() throws MyDaoException {
		System.out.println(MenuText.outProfMenu);
		gDAO = new ProfesorDAO();
		genService = new ProfesorService(new ProfesorDAO());
		long id;
		int select = 6;
		try {
			select = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException nfe) {
			System.out.println("Nu ati introdus un nuar valid");
			System.out.println("Introduceti un numar intre 1 si 6");
			profesor_Menu();
		}

		switch (select) {
		case 1:
			System.out.println("ID-ul creat este: "
					+ genService.createFunction());
			profesor_Menu();
			break;
		case 2:
			genService.printOne();
			profesor_Menu();
			break;
		case 3:
			System.out
					.println(genService.updateFunction() ? MenuText.outSuccess
							: MenuText.outEsuare);
			profesor_Menu();
			break;
		case 4:
			System.out.println(MenuText.inID);
			id = Long.parseLong(sc.nextLine());
			gDAO.delete(id);
			profesor_Menu();
			break;
		case 5:
			genService.printAll();
			profesor_Menu();
			break;
		default:
			Menu();
		}
	}

	private void universitate_Menu() throws MyDaoException {
		System.out.println(MenuText.outUnivMenu);
		gDAO = new UnivDAO();
		genService = new UniversitateService(new UnivDAO());
		long id;
		int select = 6;
		try {
			select = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException nfe) {
			System.out.println("Nu ati introdus un nuar valid");
			System.out.println("Introduceti un numar intre 1 si 6");
			profesor_Menu();
		}
		switch (select) {
		case 1:
			System.out.println("ID-ul creat este: "
					+ genService.createFunction());
			universitate_Menu();
			break;
		case 2:
			genService.printOne();
			universitate_Menu();
			break;
		case 3:
			System.out
					.println(genService.updateFunction() ? MenuText.outSuccess
							: MenuText.outEsuare);
			universitate_Menu();
			break;
		case 4:
			System.out.println(MenuText.inID);
			id = Long.parseLong(sc.nextLine());
			gDAO.delete(id);
			universitate_Menu();
			break;
		case 5:
			genService.printAll();
			universitate_Menu();
			break;
		default:
			Menu();
		}
	}

	private void curs_Menu() throws MyDaoException {
		System.out.println(MenuText.outCursMenu);
		gDAO = new CursDAO();
		genService = new CursService(new CursDAO());
		long id;
		int select = Integer.parseInt(sc.nextLine());
		switch (select) {
		case 1:
			System.out.println("ID-ul creat este: "
					+ genService.createFunction());
			curs_Menu();
			break;
		case 2:
			genService.printOne();
			curs_Menu();
			break;
		case 3:
			System.out
					.println(genService.updateFunction() ? MenuText.outSuccess
							: MenuText.outEsuare);
			curs_Menu();
			break;
		case 4:
			System.out.println(MenuText.inID);
			id = Long.parseLong(sc.nextLine());
			gDAO.delete(id);
			curs_Menu();
			break;
		case 5:
			genService.printAll();
			curs_Menu();
			break;
		default:
			Menu();

		}

	}

	public static void main(String[] args) {
		Main UniverDB = new Main();
		Map<String, String> erors = new HashMap<String, String>(0);
		erors.put("numar.mare", "My MSG SQL");
		try {
			UniverDB.Menu();
		} catch (MyDaoException e) {
			System.out.println(erors.get(e.get_msg()));
		}
	}

}
