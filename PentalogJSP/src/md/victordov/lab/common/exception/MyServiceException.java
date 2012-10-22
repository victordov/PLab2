package md.victordov.lab.common.exception;

public class MyServiceException extends Exception {

	private static final long serialVersionUID = -2731922916964083145L;
	private final transient String msgAtribute;
	private final transient Exception excp;

	public MyServiceException(String msg, Exception e) {
		this.msgAtribute = msg;
		this.excp = e;
	}

	public String getMsgAtribute() {
		return msgAtribute;
	}
	
	public String toString() {
		return "MyServiceException : "+this.msgAtribute+" [ " +this.excp.getMessage()+ "]";
	}

	public String getMsg() {
		return msgAtribute;
	}

}
