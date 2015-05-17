package ral.borda;

import ral.Pair;
import ral.Ranker;

import java.util.List;
import java.util.stream.Collectors;

public class BordasMethod<T> implements Ranker<T> {
    private final AggregationFunction<T> aggregationFunction;

    public BordasMethod(AggregationFunction<T> aggregationFunction) {
        this.aggregationFunction = aggregationFunction;
    }

    public static <T> BordasMethod<T> arithmeticAverageRanker() {
        return new BordasMethod<>(new ArithmeticAverage<>());
    }

    public static <T> BordasMethod<T> medianRanker() {
        return new BordasMethod<>(new Median<>());
    }

    public static <T> BordasMethod<T> geometricMeanRanker() {
        return new BordasMethod<>(new GeometricMean<>());
    }

    public static <T> BordasMethod<T> L2NormRanker() {
        return new BordasMethod<>(new L2Norm<>());
    }

    @Override
    public List<T> rank(Pair<Double, List<T>>[] weighedList) {
        aggregationFunction.addAll(weighedList);
        return aggregationFunction.calculate().stream().map(pair -> pair.second).collect(Collectors.toList());
    }

    @Override
    public List<T> rank(List<T>[] lists) {
        aggregationFunction.addAll(lists);
        return aggregationFunction.calculate().stream().map(pair -> pair.second).collect(Collectors.toList());
    }
}
