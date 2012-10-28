package md.victordov.lab.common.other;

public abstract class HasNumber {

	public HasNumber() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
    // Validare String fara numar pentru nume si prenume
    public static boolean hasNumber(String toBeChecked) {
	boolean trueOrFalse = false;
	for (int i = 0; i < toBeChecked.length(); i++) {
	    if (Character.isDigit(toBeChecked.charAt(i))) {
	    /* Aceasta fraza este posibil de modificat, e posibil sa arunce MyDaoException
	     * pentru a fi logata intr-un fisier separat	
		 *System.out.println("Cuvintul introdus contine cifre");
		 * 
		 */
		trueOrFalse = true;
		break;
	    }

	}
	return trueOrFalse;
    }

}
