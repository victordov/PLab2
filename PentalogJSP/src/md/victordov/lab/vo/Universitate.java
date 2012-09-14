package md.victordov.lab.vo;

public class Universitate {

	private long universitateId;
	private String numeUniversitate;
	private String adresa;
	private String telefon;

	public Universitate() {
		this.universitateId = 0;
		this.numeUniversitate = "";
		this.adresa = "";
		this.telefon = "";
	}

	public Universitate(long u_ID, String u_Denumirea, String u_Adresa,
			String u_Telefon) {
		this.setUniversitateId(u_ID);
		this.setNumeUniversitate(u_Denumirea);
		this.setAdresa(u_Adresa);
		this.setTelefon(u_Telefon);
	}

	public long getUniversitateId() {
		return universitateId;
	}

	public void setUniversitateId(long u_id) {
		this.universitateId = u_id;
	}

	public String getNumeUniversitate() {
		return numeUniversitate;
	}

	public void setNumeUniversitate(String nume_univer) {
		this.numeUniversitate = nume_univer;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	public void printUniversitate(){
	    System.out.printf("|%-10d|%-19.19s|%-27.27s|%-15.15s|\n",
		    		this.getUniversitateId(),this.getNumeUniversitate(),
		    		this.getAdresa(),this.getTelefon());
	}

}
