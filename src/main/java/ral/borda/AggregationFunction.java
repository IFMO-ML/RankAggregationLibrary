package ral.borda;

import ral.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public interface AggregationFunction<T> {
    public void add(double weight, int position, T item);

    default void add(int position, T item) {
        add(1, position, item);
    }

    default void addAll(Pair<Double, List<T>>[] weighedList) {
        final TreeSet<T> set = new TreeSet<>();

        Arrays.stream(weighedList).forEach(pair -> pair.second.stream().forEach(set::add));
        for (Pair<Double, List<T>> pair : weighedList) {
            final TreeSet<T> set2 = new TreeSet<>();

            for (int i = 0; i < pair.second.size(); i++) {
                set2.add(pair.second.get(i));
                add(pair.first, i + 1, pair.second.get(i));
            }

            set.stream().filter(t -> !set2.contains(t)).forEach(t -> add(pair.first, pair.second.size() + 1, t));
        }
    }

    default void addAll(List<T>[] lists) {
        final TreeSet<T> set = new TreeSet<>();

        Arrays.stream(lists).forEach(list -> list.stream().forEach(set::add));
        for (List<T> list : lists) {
            final TreeSet<T> set2 = new TreeSet<>();

            for (int i = 0; i < list.size(); i++) {
                set2.add(list.get(i));
                add(i + 1, list.get(i));
            }

            set.stream().filter(t -> !set2.contains(t)).forEach(t -> add(list.size() + 1, t));
        }
    }

    /**
     * return ordered pairs of position/item
     */
    public List<Pair<Double, T>> calculate();
}
