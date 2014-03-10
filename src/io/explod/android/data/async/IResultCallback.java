package io.explod.android.data.async;

/**
 * Interface for async callbacks
 * 
 * @param <DataType>
 * @param <ErrorType>
 * @author evan
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
