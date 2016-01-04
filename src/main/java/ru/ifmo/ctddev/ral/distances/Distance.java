package ru.ifmo.ctddev.ral.distances;

/**
 * This interface provide way to calculate distances between to top-r ranked lists that are not necessarily of the
 * same length.
 */
public interface Distance<T> {
    /**
     * This method obtain two lists that are not necessarily of the same length and return calculated distance between
     * them.
     *
     * @param a first list
     * @param b seckond list
     * @return distance between given lists
     */
    public double calculate(T[] a, T[] b);
}
