package ru.job4j.additionaltask;

/**
 * Class is time of customer in bank.
 *
 * @since 22/10/2017
 * @version 1
 */
public class TimeDistance {
    public long start;
    public long end;

    public TimeDistance(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeDistance that = (TimeDistance) o;

        if (start != that.start) return false;
        return end == that.end;
    }

    @Override
    public int hashCode() {
        int result = (int) (start ^ (start >>> 32));
        result = 31 * result + (int) (end ^ (end >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return start + "-" + end;
    }
}
