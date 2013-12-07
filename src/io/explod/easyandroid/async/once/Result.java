package io.explod.easyandroid.async.once;

/**
 * Async result class. Stores data and errors
 * 
 * @author evan
 * 
 * @param <DataType>
 * @param <ErrorType>
 */
public class Result<DataType, ErrorType> {

	private DataType data;
	private ErrorType error;

	/**
	 * Construct a result store with data and errors, can be null
	 * 
	 * @param data
	 * @param error
	 */
	public Result(DataType data, ErrorType error) {
		this.data = data;
		this.error = error;
	}

	/**
	 * Get the data associated with this result, can be null
	 * 
	 * @return
	 */
	public DataType getData() {
		return this.data;
	}

	/**
	 * Get the error associated with this result, can be null
	 * 
	 * @return
	 */
	public ErrorType getError() {
		return this.error;
	}

	/**
	 * Indicates whether or not this result is in an error state
	 * 
	 * @return
	 */
	public boolean isError() {
		final ErrorType error = this.getError();
		return error != null;
	}
}
