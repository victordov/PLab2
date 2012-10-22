/**
 * 
 */
package md.victordov.lab.common.exception;

/**
 * @author victordov
 *
 */
public class MyDaoException extends Exception {

	private static final long serialVersionUID = -2731922916964083145L;
	private final transient String msgAtribute;
	private final transient Exception excp;
	
	public MyDaoException(String msg, Exception e) {
		this.msgAtribute = msg;
		this.excp = e;
	}
	
	
	public String toString() {
		return "MyDaoException : "+this.msgAtribute+" [ " +this.excp.getMessage()+ "]";
	}

	public String getMsg() {
		return msgAtribute;
	}
	
}
