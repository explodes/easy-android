package io.explod.android.collections;

import java.util.ArrayList;

/**
 * A list that has a name. Backed by ArrayList.
 *
 * @param <T> The type of object this list holds
 * @author evan
 */
public class NamedList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 6158584853980274284L;

    private String name;

    public NamedList(String name) {
        super();
        this.name = name;
    }

    /**
     * Get the name of this list
     *
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of this list
     *
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

}
