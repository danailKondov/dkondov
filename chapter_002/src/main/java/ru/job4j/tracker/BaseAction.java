package ru.job4j.tracker;

/**
 * Superclass for actions.
 * @since 05/08/2017
 * @version 1
 */
public abstract class BaseAction implements UserAction {

    /**
     *  Action functionality description.
     */
    protected String name;

    /**
     * Number of action in menu option.
     */
    protected int key;

    /**
     * Constructor with parameters.
     * @param name - action functionality description
     * @param key - number of action in menu option
     */
    protected BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }

    /**
     * Info about action.
     * @return info about action (number of option in menu and description)
     */
    @Override
    public String info() {
        return String.format("%s. %s", key, name);
    }
}
