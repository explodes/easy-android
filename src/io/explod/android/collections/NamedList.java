package io.explod.android.collections;

import java.util.ArrayList;

public class NamedList<T> extends ArrayList<T> {

	private static final long serialVersionUID = 6158584853980274284L;

	private String name;

	public NamedList(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
