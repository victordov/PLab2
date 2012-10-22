package md.victordov.lab.vo;

public class Profesor {

    private long profesorId;
    private String nume;
    private String prenume;
    private String adresa;

    public Profesor() {
	this.profesorId=0;
	this.nume= "";
	this.prenume="";
	this.adresa="";
    }

    public Profesor(long P_ProfesorID, String P_Nume, String P_Prenume,
	    String P_Adresa) {
	this.profesorId = P_ProfesorID;
	this.nume = P_Nume;
	this.prenume = P_Prenume;
	this.adresa = P_Adresa;

    }

    public long getProfesorId() {
	return profesorId;
    }

    public void setProfesorId(long p_id) {
	this.profesorId = p_id;
    }

    public String getNume() {
	return nume;
    }

    public void setNume(String nume) {
	this.nume = nume;
    }

    public String getPrenume() {
	return prenume;
    }

    public void setPrenume(String prenume) {
	this.prenume = prenume;
    }

    public String getAdresa() {
	return adresa;
    }

    public void setAdresa(String adresa) {
	this.adresa = adresa;
    }

    public void printProfesor() {
	//System.out.printf("|%-10d|%-15.15s|%-15.15s|%-25.25s|\n", this.getProfesorId(), this.getNume(), this.getPrenume(),this.getAdresa());
    }
}
