package ru.ifmo.ctddev.ral.borda;

import ru.ifmo.ctddev.ral.Pair;

import java.util.*;

public class ArithmeticAverage<T> implements AggregationFunction<T> {
    /**
     * T - item,
     * Double1 - L (norm multiplier)
     * Double2 - score
     */
    private Map<T, Pair<Double, Double>> map = new HashMap<>();

    @Override
    public void add(double weight, int position, T item) {
        Pair<Double, Double> pair;

        if (map.containsKey(item)) {
            pair = map.get(item);
        } else {
            pair = Pair.of(.0, .0);
        }

        map.put(item, Pair.of(pair.first + weight, pair.second + (double) position * weight));
    }

    /**
     * return ordered pairs of position/item
     */
    @Override
    public List<Pair<Double, T>> calculate() {
        List<Pair<Double, T>> list = new ArrayList<>(map.size());

        for (Map.Entry<T, Pair<Double, Double>> entry : map.entrySet()) {
            Pair<Double, Double> pair = entry.getValue();
            list.add(Pair.of(pair.second / pair.first, entry.getKey()));
        }

        Collections.sort(list, (o1, o2) -> Double.compare(o1.first, o2.first));
        return list;
    }
}
