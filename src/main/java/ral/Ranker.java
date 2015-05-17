package ral;

import java.util.List;

public interface Ranker<T> {
    List<T> rank(Pair<Double, List<T>>[] weighedLists);

    List<T> rank(List<T>[] lists);
}
