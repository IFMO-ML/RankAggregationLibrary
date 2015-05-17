package ral.borda;

import ral.Pair;

import java.util.*;

public class Median<T> implements AggregationFunction<T> {
    /**
     * map<item, Pair<List<Pair<position, weight>>, sum-of-weights>>
     */
    private Map<T, Pair<ArrayList<Pair<Integer, Double>>, Double>> map = new HashMap<>();

    @Override
    public void add(double weight, int position, T item) {
        Pair<ArrayList<Pair<Integer, Double>>, Double> pair;

        if (map.containsKey(item)) {
            pair = map.get(item);
        } else {
            pair = Pair.of(new ArrayList<>(1), 0.);
        }

        pair.first.add(Pair.of(position, weight));
        map.put(item, Pair.of(pair.first, pair.second + weight));
    }

    /**
     * return ordered pairs of position/item
     */
    @Override
    public List<Pair<Double, T>> calculate() {
        List<Pair<Double, T>> result = new ArrayList<>(map.size());

        for (Map.Entry<T, Pair<ArrayList<Pair<Integer, Double>>, Double>> entry : map.entrySet()) {
            ArrayList<Pair<Integer, Double>> list = entry.getValue().first;
            Double weightSum = entry.getValue().second;
            Collections.sort(list, (o1, o2) -> Integer.compare(o1.first, o2.first));
            int i = 0;
            for (double sum = 0; sum < weightSum / 2; i++) {
                sum += list.get(i).second;
            }

            result.add(Pair.of((double) list.get(i - 1).first, entry.getKey()));
        }

        Collections.sort(result, (o1, o2) -> Double.compare(o1.first, o2.first));
        return result;
    }
}
