package ral;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MCTest {
    private static final ListOfRanks<Integer> iceCreamFlavors = new ListOfRanks<>(new Integer[][]{
            {1, 2, 3, 5},
            {3, 5, 1, 2},
            {1, 3, 5, 2},
    });

    private static final Integer[] iceCreamFlavorsAnswer = new Integer[]{1, 3, 5, 2};
    private static final Integer[] iceCreamFlavorsAnswer2 = new Integer[]{1, 3, 2, 5};

    private static final ListOfRanks<Integer> longLists = new ListOfRanks<>(new Integer[][]{
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 56, 57, 58, 59, 60, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 61, 62, 63, 64, 65},
    });

    private static final Integer[] longListsMC1Answer = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 26, 24, 27, 25, 28, 46, 29, 47, 30, 48, 56, 49, 57, 50, 58, 59, 41};
    //    private static final Integer[] longListsMC2Answer = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 57, 56, 58, 60, 59};
    private static final Integer[] longListsMC2Answer2 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 57, 56, 58, 59, 60};
    private static final Integer[] longListsMC3Answer = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 56, 57, 58, 59, 60};

    private void rankVerification(RankAggregator<Integer> rankAggregator, ListOfRanks<Integer> ranks, Integer[] expected) {
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(Arrays.copyOf(rankAggregator.aggregate(ranks).toArray(), expected.length)));
    }

    @Test
    public void markovChainTestMC1IceCreamFlavors() {
        rankVerification(MarkovChainAggregators.MC1(), iceCreamFlavors, iceCreamFlavorsAnswer2);
    }

    @Test
    public void markovChainTestMC1LongList() {
        rankVerification(MarkovChainAggregators.MC1(), longLists, longListsMC1Answer);
    }

    @Test
    public void markovChainTestMC2IceCreamFlavors() {
        rankVerification(MarkovChainAggregators.MC2(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void markovChainTestMC2LongList() {
        rankVerification(MarkovChainAggregators.MC2(), longLists, longListsMC2Answer2);
    }

    @Test
    public void markovChainTestMC3IceCreamFlavors() {
        rankVerification(MarkovChainAggregators.MC3(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void markovChainTestMC3LongList() {
        rankVerification(MarkovChainAggregators.MC3(), longLists, longListsMC3Answer);
    }
}
