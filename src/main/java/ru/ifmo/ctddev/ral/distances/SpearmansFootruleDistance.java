package ru.ifmo.ctddev.ral.distances;

import java.util.Arrays;

/**
 * This class provide way to calculate distances between to top-r ranked lists that are not necessarily of the
 * same length.
 * <p>
 * For calculation used Spearman’s Footrule Distance that described in work:
 * On Maximum Rank Aggregation Problems written by Christian Bachmaier, Franz Josef Brandenburg, Andreas Gleißner,
 * and Andreas Hofmeier
 */
public class SpearmansFootruleDistance<T> implements Distance<T> {
    /**
     * This method obtain two lists that are not necessarily of the same length and return Spearman’s Footrule Distance
     * between them.
     *
     * @param a first list
     * @param b seckond list
     * @return Spearman’s Footrule Distance between given lists
     */
    @Override
    public double calculate(T[] a, T[] b) {
        final int la = a.length;
        final int lb = b.length;

        int[] ra = new int[lb];
        int[] rb = new int[la];
        Arrays.fill(ra, lb);
        Arrays.fill(rb, la);

        for (int i = 0; i < la; i++) {
            for (int j = 0; j < lb; j++) {
                if (a[i].equals(b[j])) {
                    ra[i] = j;
                    rb[j] = i;
                }
            }
        }

        double distance = 0;

        for (int i = 0; i < la; i++) {
            distance += Math.abs(i - ra[i]);
        }

        for (int j = 0; j < lb; j++) {
            if (rb[j] == la) {
                distance += Math.abs(j - rb[j]);
            }
        }

        return distance;
    }
}
