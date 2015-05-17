package ral.borda;

import ral.ListOfRanks;
import ral.Pair;

import java.util.List;

public interface AggregationFunction<T> {
    public void add(double weight, int position, T item);

    default void addAll(ListOfRanks<T> ranks) {
        for (ListOfRanks<T>.Rank rank : ranks) {
            for (T item : ranks.items()) {
                add(rank.weight, rank.R(item) + 1, item);
            }
        }
    }

    /**
     * return ordered pairs of position/item
     */
    public List<Pair<Double, T>> calculate();
}
