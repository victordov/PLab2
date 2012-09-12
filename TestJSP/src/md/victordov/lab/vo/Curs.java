package md.victordov.lab.vo;

public class Curs {
	
	private long cursId;
	private String numeCurs;
	private long universitateId;
	private long profesorId;

	public Curs() {
		this.setCursId(0);
		this.setNumeCurs("");
		this.setUniversitateId(0);
		this.setProfesorId(0);
	}

	Curs(long ID, String Num_Curs, long U_ID, long P_ID) {
		this.setCursId(ID);
		this.setNumeCurs(Num_Curs);
		this.setUniversitateId(U_ID);
		this.setProfesorId(P_ID);
	}


	public long getCursId() {
		return cursId;
	}

	public void setCursId(long c_id) {
		this.cursId = c_id;
	}

	public String getNumeCurs() {
		return numeCurs;
	}

	public void setNumeCurs(String nume_curs) {
		this.numeCurs = nume_curs;
	}

	public long getUniversitateId() {
		return universitateId;
	}

	public void setUniversitateId(long u_id) {
		this.universitateId = u_id;
	}

	public long getProfesorId() {
		return profesorId;
	}

	public void setProfesorId(long p_id) {
		this.profesorId = p_id;
	}
	
	public void printCurs(){
	    String format = "|%1$-10d|%2$-20s|%3$-15d|%4$-12d|\n";
	    System.out.printf("|%-10d|%-20.20s|%-15d|%-12d|\n", 
		    		this.getCursId(),this.getNumeCurs(),
		    		this.getUniversitateId(),this.getProfesorId());
	}
}
