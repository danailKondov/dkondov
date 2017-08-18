package ru.job4j.genericsimplearray;

/**
 * Class is base for models.
 *
 * @since 18/08/2017
 * @version 1
 */
public abstract class Base {

    /**
     * ID.
     */
    protected String id;

    /**
     * Getter for ID.
     * @return id
     */
   abstract String getId();

    /**
     * Setter for id.
     * @param id - id
     */
   abstract void setId(String id);
}
