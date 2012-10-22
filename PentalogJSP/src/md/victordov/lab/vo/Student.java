package md.victordov.lab.vo;

public class Student {

   

    public Student() {
	this.setStudentId(0);
	this.setNume("");
	this.setPrenume("");
	this.setGrupa("");
	this.setEmail("");
	this.setTelFix("");
    }

    public Student(long s_s_id, String s_nume, String s_prenume,
	    String s_grupa, String s_email, String s_telFix) {
	this.setStudentId(s_s_id);
	this.setNume(s_nume);
	this.setPrenume(s_prenume);
	this.setGrupa(s_grupa);
	this.setEmail(s_email);
	this.setTelFix(s_telFix);
    }

    public long getStudentId() {
	return studentId;
    }

    public void setStudentId(long s_id) {
	this.studentId = s_id;
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

    public String getGrupa() {
	return grupa;
    }

    public void setGrupa(String grupa) {
	this.grupa = grupa;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getTelFix() {
	return telFix;
    }

    public void setTelFix(String telFix) {
	this.telFix = telFix;
    }

    public void printStudent() {
	System.out.printf("|%-10d|%-15.15s|%-15.15s|%-10.10s|%-25.25s|%-12.12s|\n",
		this.getStudentId(), this.getNume(), this.getPrenume(),
		this.getGrupa(), this.getEmail(), this.getTelFix());
    }
    private long studentId;
    private String nume, prenume, grupa, email, telFix;

}
