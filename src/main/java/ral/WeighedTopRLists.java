package ral;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WeighedTopRLists<T> implements Iterable<WeighedTopRLists.WeighedList<T>> {
    private final List<WeighedList<T>> weighedLists;

    public WeighedTopRLists(T[][] lists) {
        weighedLists = Arrays.stream(lists).map(list -> new WeighedList<T>(1, list)).collect(Collectors.toList());
    }

    public WeighedTopRLists(T[][] lists, double[] weights) {
        if (lists.length != weights.length) {
            throw new IllegalArgumentException("lists size doesn't match");
        }

        weighedLists = IntStream.range(0, lists.length).mapToObj(i -> new WeighedList<T>(weights[i], lists[i])).collect(Collectors.toList());
    }

    /**
     * Returns an iterator over elements of type {@code WeighedList<T>}.
     *
     * @return an Iterator<WeighedList<T>>.
     */
    @Override
    public Iterator<WeighedList<T>> iterator() {
        return weighedLists.iterator();
    }

    public static class WeighedList<T> implements Iterable<T> {
        public final double weight;
        private final T[] list;

        public WeighedList(double weight, T[] list) {
            this.weight = weight;
            this.list = list;
        }

        /**
         * Returns an iterator over elements of type {@code T}.
         *
         * @return an Iterator<T>.
         */
        @Override
        public Iterator<T> iterator() {
            return Arrays.stream(list).iterator();
        }
    }
}
