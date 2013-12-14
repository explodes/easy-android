package io.explod.android.data.async.once;

import java.util.HashMap;
import java.util.Map;

/**
 * A Oncer that stores results in memory (by way of HashMap)
 * 
 * @author evan
 * 
 */
public class MemoryOncer<KeyType, DataType, ErrorType> extends Oncer<KeyType, DataType, ErrorType> {

	private Map<KeyType, Result<DataType, ErrorType>> memory;

	public MemoryOncer() {
		super();
		this.memory = new HashMap<KeyType, Result<DataType, ErrorType>>();
	}

	@Override
	public void putResult(KeyType key, Result<DataType, ErrorType> result) {
		this.memory.put(key, result);
	}

	@Override
	public Result<DataType, ErrorType> getResult(KeyType key) {
		return this.memory.get(key);
	}

	@Override
	public boolean hasResult(KeyType key) {
		return this.memory.containsKey(key);
	}

	@Override
	public void resetKey(KeyType key) {
		this.memory.remove(key);
	}

}
