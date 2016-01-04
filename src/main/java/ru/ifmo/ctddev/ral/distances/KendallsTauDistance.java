package ru.ifmo.ctddev.ral.distances;

import java.util.Arrays;

/**
 * This class provide way to calculate distances between to top-r ranked lists that are not necessarily of the
 * same length.
 * <p>
 * For calculation used Kendall’s tau distance that described in work:
 * On Maximum Rank Aggregation Problems written by Christian Bachmaier, Franz Josef Brandenburg, Andreas Gleißner,
 * and Andreas Hofmeier
 */
public class KendallsTauDistance<T> implements Distance<T> {
    private final double p;

    public KendallsTauDistance() {
        this(0);
    }

    public KendallsTauDistance(double p) {
        this.p = p;
    }

    /**
     * This method obtain two lists that are not necessarily of the same length and return Kendall’s tau Distance
     * between them.
     *
     * @param a first list
     * @param b seckond list
     * @return Kendall’s tau Distance between given lists
     */
    @Override
    public double calculate(T[] a, T[] b) {
        final int la = a.length;
        final int lb = b.length;

        int[] ra = new int[lb];
        int[] rb = new int[la];
        Arrays.fill(ra, -1);
        Arrays.fill(rb, -1);

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
            for (int j = 0; j < lb; j++) {
                if (rb[j] == -1 || ra[i] == -1) {
                    distance += p;
                    continue;
                }

                if ((i - rb[j]) * (ra[i] - j) < 0) {
                    distance++;
                }
            }
        }

        return distance;
    }
}
