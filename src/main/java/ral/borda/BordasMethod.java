package ral.borda;

import ral.ListOfRanks;
import ral.RankAggregator;

import java.util.List;
import java.util.stream.Collectors;

public class BordasMethod<T> implements RankAggregator<T> {
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
    public List<T> aggregate(ListOfRanks<T> ranks) {
        aggregationFunction.addAll(ranks);
        return aggregationFunction.calculate().stream().map(pair -> pair.second).collect(Collectors.toList());
    }
}
