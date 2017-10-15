package ru.job4j.nonblockingcache;

/**
 * Class user for simple DIY non-blocking cache.
 *
 * @since 12/10/2017
 * @version 1
 */
public class Task {

    private static int iDhelper = (int) (Math.random()*100_000);

    private String name;

    private int version;

    private int iD;

    public Task(String name) {
        this.name = name;
        version = 0;
        iD = iDhelper++;
    }

    public int getiD() {
        return iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
