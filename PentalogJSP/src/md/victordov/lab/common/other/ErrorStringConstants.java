package md.victordov.lab.common.other;

public interface ErrorStringConstants {
	/**
	 * @author victor, Constante string pentrul inlocuirea mesajelor de tip
	 *         string pentru stocarea lor in fiserea log aruncate de execeptii
	 */
	public static String CL_NOT_FOUND = "Verificati existenta driverului ( connectorului ) MySQL";

	public static String CANNOT_EXTRACT = "Nu e posibil de extras date de tip ";
	public static String CANNOT_INSERT = "Nu a putut fi introduse date de tip ";
	public static String CANNOT_UPDATE = "Nu a putut fi reiinoite date de tip ";
	public static String CANNOT_DELETE = "Nu a putut fi sterse date de tip ";
	public static String VERIFY_CONNECTION = "verificati conexiunea, si existenta tabelului";

	// Student
	public static String STUDENT_NOT_FOUND = CANNOT_EXTRACT + "Student,"
			+ VERIFY_CONNECTION + " Student";
	public static String STUDENT_NOT_CREATED = CANNOT_INSERT
			+ " Student in tabel," + VERIFY_CONNECTION + " Student";
	public static String STUDENT_NOT_UPDATED = CANNOT_UPDATE + "Student,"
			+ VERIFY_CONNECTION + " Student";
	public static String STUDENT_NOT_DELETED = CANNOT_DELETE + "Student,"
			+ VERIFY_CONNECTION + " Student";

	// Profesor
	public static String PROFESOR_NOT_FOUND = CANNOT_EXTRACT + "Profesor,"
			+ VERIFY_CONNECTION + " Profesor";
	public static String PROFESOR_NOT_CREATED = CANNOT_INSERT
			+ "  Profesor in tabel," + VERIFY_CONNECTION + " Profesor";
	public static String PROFESOR_NOT_UPDATED = CANNOT_UPDATE + "Profesor,"
			+ VERIFY_CONNECTION + " Profesor";
	public static String PROFESOR_NOT_DELETED = CANNOT_DELETE + "Profesor,"
			+ VERIFY_CONNECTION + " Profesor";

	// Curs
	public static String CURS_NOT_FOUND = CANNOT_EXTRACT + "Curs,"
			+ VERIFY_CONNECTION + " Curs";
	public static String CURS_NOT_CREATED = CANNOT_INSERT + "  Curs in tabel,"
			+ VERIFY_CONNECTION + " Curs";
	public static String CURS_NOT_UPDATED = CANNOT_UPDATE + "Curs,"
			+ VERIFY_CONNECTION + " Curs";
	public static String CURS_NOT_DELETED = CANNOT_DELETE + "Curs,"
			+ VERIFY_CONNECTION + " Curs";

	// Universitate
	public static String UNIVERSITATE_NOT_FOUND = CANNOT_EXTRACT
			+ "Universitate," + VERIFY_CONNECTION + " Universitate";
	public static String UNIVERSITATE_NOT_CREATED = CANNOT_INSERT
			+ " Universitate in tabel," + VERIFY_CONNECTION + " Universitate";
	public static String UNIVERSITATE_NOT_UPDATED = CANNOT_UPDATE
			+ "Universitate," + VERIFY_CONNECTION + " Universitate";
	public static String UNIVERSITATE_NOT_DELETED = CANNOT_DELETE
			+ "Universitate," + VERIFY_CONNECTION + " Universitate";

	public static String PREP_OR_RS = "Erroare in prepared statement sau result set ";
	// Simple Exception message
	public static String ERR_RETRIEVE = "Eroare produsa in metoda retrieve";
	public static String ERR_CREATE = "Eroare produsa in metoda create";
	public static String ERR_UPDATE = "Eroare produsa in metoda update";
	public static String ERR_DELETE = "Eroare produsa in metoda delete";
}
