package ru.ifmo.ctddev.ral;

import ru.ifmo.ctddev.ral.borda.*;

import java.util.List;
import java.util.stream.Collectors;

public class BordasAggregators<T> implements RankAggregator<T> {
    private final AggregationFunction<T> aggregationFunction;

    public BordasAggregators(AggregationFunction<T> aggregationFunction) {
        this.aggregationFunction = aggregationFunction;
    }

    public static <T> BordasAggregators<T> arithmeticAverageRanker() {
        return new BordasAggregators<>(new ArithmeticAverage<>());
    }

    public static <T> BordasAggregators<T> medianRanker() {
        return new BordasAggregators<>(new Median<>());
    }

    public static <T> BordasAggregators<T> geometricMeanRanker() {
        return new BordasAggregators<>(new GeometricMean<>());
    }

    public static <T> BordasAggregators<T> L2NormRanker() {
        return new BordasAggregators<>(new L2Norm<>());
    }

    @Override
    public List<T> aggregate(ListOfRanks<T> ranks) {
        aggregationFunction.addAll(ranks);
        return aggregationFunction.calculate().stream().map(pair -> pair.second).collect(Collectors.toList());
    }
}
