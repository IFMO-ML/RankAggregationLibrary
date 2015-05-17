package ral;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListOfRanks<T> implements Iterable<ListOfRanks<T>.Rank> {
    private final List<Rank> ranks;
    private HashMap<T, Integer> itemsMap;
    private List<T> itemsMapReverse;

    public ListOfRanks(T[][] lists) {
        ranks = Arrays.stream(lists).map(list -> new Rank(1, list)).collect(Collectors.toList());
    }

    public ListOfRanks(T[][] lists, double[] weights) {
        if (lists.length != weights.length) {
            throw new IllegalArgumentException("lists size doesn't match");
        }

        ranks = IntStream.range(0, lists.length).mapToObj(i -> new Rank(weights[i], lists[i])).collect(Collectors.toList());
    }

    /**
     * Returns an iterator over elements of type {@code WeighedList<T>}.
     *
     * @return an Iterator<WeighedList<T>>.
     */
    @Override
    public Iterator<Rank> iterator() {
        return ranks.iterator();
    }

    private HashMap<T, Integer> initItemsMap() {
        if (itemsMap != null) {
            return itemsMap;
        }

        itemsMap = new HashMap<>();
        itemsMapReverse = new ArrayList<>();

        for (Rank rank : this) {
            for (T item : rank) {
                if (!itemsMap.containsKey(item)) {
                    itemsMap.put(item, itemsMap.size());
                    itemsMapReverse.add(item);
                }
            }
        }

        for (Rank rank : this) {
            rank.reverse = new int[itemsMap.size()];
            Arrays.fill(rank.reverse, rank.length);
            IntStream.range(0, rank.length).forEach(i -> rank.reverse[itemsMap.get(rank.get(i))] = i);
        }

        return itemsMap;
    }

    public int countOfItems() {
        return initItemsMap().size();
    }

    public int countOfRanks() {
        return ranks.size();
    }

    public T itemByNumber(int n) {
        initItemsMap();
        return itemsMapReverse.get(n);
    }

    public class Rank implements Iterable<T> {
        public final double weight;
        public final int length;
        private final T[] list;
        private int[] reverse;

        public Rank(double weight, T[] list) {
            this.weight = weight;
            this.list = list;
            this.length = list.length;
        }

        public int R(T u) {
            return position(initItemsMap().get(u));
        }

        public int position(int u) {
            return reverse[u];
        }

        public T get(int i) {
            return list[i];
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
