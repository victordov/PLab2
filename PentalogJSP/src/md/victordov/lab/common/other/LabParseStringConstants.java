package md.victordov.lab.common.other;

public interface LabParseStringConstants {
	
	static String STUD_ROOT = "s1:studenti";
	static String STUD_TAG ="s1:student";
	static String  STUD_ID= "s1:s_id";
	static String  STUD_NUME= "s1:nume";
	static String  STUD_PRENUME= "s1:prenume";
	static String  STUD_GRUPA= "s1:grupa";
	static String  STUD_EMAIL= "s1:email";
	static String  STUD_TEL_FIX= "s1:telFix";
	
	static String PROF_ROOT = "p1:profesori";
	static String PROF_TAG ="p1:profesor";
	static String  PROF_ID= "p1:p_id";
	static String  PROF_NUME= "p1:nume";
	static String  PROF_PRENUME= "p1:prenume";
	static String  PROF_ADRESA= "p1:adresa";
	
	static String CURS_ROOT = "c1:cursuri";
	static String CURS_TAG ="c1:curs";
	static String  CURS_ID= "c1:c_id";
	static String  CURS_NUME= "c1:nume_curs";
	static String  CURS_UNIV_ID= "c1:u_id";
	static String  CURS_PROF_ID= "c1:p_id";
	
	static String UNIV_ROOT = "u1:universitati";
	static String UNIV_TAG ="u1:universitate";
	static String  UNIV_ID= "u1:u_id";
	static String  UNIV_NUME= "u1:nume_univer";
	static String  UNIV_ADRESA= "u1:adresa";
	static String  UNIV_TEL= "u1:telefon";
	
	static String W3_XMLNS = "http://www.w3.org/2000/xmlns/";
	
	/* Url for uniqueness of the namespace of Student, Profesor, Curs, Universitate*/
	static String STUDENT_NS_LINK = "http://www.w3.org/TR/html4/";
	static String PROFESOR_NS_LINK = "http://www.w3schools.com/p1";
	static String CURS_NS_LINK = "http://www.w3schools.com/c1";
	static String UNIV_NS_LINK = "http://www.w3schools.com/u1";
}
