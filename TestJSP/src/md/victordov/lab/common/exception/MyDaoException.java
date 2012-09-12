/**
 * 
 */
package md.victordov.lab.common.exception;

/**
 * @author victordov
 *
 */
public class MyDaoException extends Exception {

	private String _msg;
	private Exception _e;
	
	public MyDaoException(String msg, Exception e) {
		this._msg = msg;
		this._e = e;
	}
	
	@Override
	public String toString() {
		return "MyDaoException : "+this._msg+" [ " +this._e.getMessage()+ "]";
	}

	public String get_msg() {
		return _msg;
	}
	
}
