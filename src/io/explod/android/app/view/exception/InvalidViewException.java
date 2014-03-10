package io.explod.android.app.view.exception;

/**
 * Exception indicating that a view is invalid for an operation.
 * 
 * @author evan
 */
public class InvalidViewException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2385647224255620950L;

	/**
	 * @see {@link java.lang.Exception#Exception()}
	 */
	public InvalidViewException() {
	}

	/**
	 * @see {@link java.lang.Exception#Exception(String)}
	 */
	public InvalidViewException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * @see {@link java.lang.Exception#Exception(Throwable)}
	 */
	public InvalidViewException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * @see {@link java.lang.Exception#Exception(String, Throwable)}
	 */
	public InvalidViewException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

}
