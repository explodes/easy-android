package io.explod.easyandroid.remote.once;

/**
 * Interface for async callbacks
 * 
 * @author evan
 * 
 * @param <DataType>
 * @param <ErrorType>
 */
public interface Callback<DataType, ErrorType> {

	/**
	 * When an async operation is completed, this function is called
	 * 
	 * @param data
	 * @param error
	 */
	public void onResult(DataType data, ErrorType error);

}
