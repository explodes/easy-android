package io.explod.android.data.async;

/**
 * Interface for async callbacks
 * 
 * @author evan
 * 
 * @param <DataType>
 * @param <ErrorType>
 */
public interface IResultCallback<DataType, ErrorType> {

	/**
	 * When an async operation is completed, this function is called
	 * 
	 * @param data
	 * @param error
	 */
	public void onResult(DataType data, ErrorType error);

}
