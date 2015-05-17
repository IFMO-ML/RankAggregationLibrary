package ral.mc;

import org.junit.Assert;
import org.junit.Test;
import ral.Pair;
import ral.Ranker;

import java.util.Arrays;
import java.util.List;

public class MCTest {
    @SuppressWarnings("unchecked")
    private static final List<Integer>[] iceCreamFlavors = new List[]{
            Arrays.asList(1, 2, 3, 5),
            Arrays.asList(3, 5, 1, 2),
            Arrays.asList(1, 3, 5, 2),
    };
    private static final Integer[] iceCreamFlavorsAnswer = new Integer[]{1, 3, 5, 2};

    private static final Pair<Double, List<Integer>>[] longListsWeighted = new Pair[]{
            Pair.of(1., Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40)),
            Pair.of(1., Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55)),
            Pair.of(1., Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 56, 57, 58, 59, 60, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 61, 62, 63, 64, 65)),
    };
    @SuppressWarnings("unchecked")
    private static final List<Integer>[] longLists = new List[]{
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40),
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55),
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 56, 57, 58, 59, 60, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 61, 62, 63, 64, 65),
    };
    private static final Integer[] longListsMC1Answer = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 26, 24, 27, 25, 28, 46, 29, 47, 30, 48, 56, 49, 57, 50, 58, 59, 41};
    private static final Integer[] longListsMC2Answer = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 57, 56, 58, 60, 59};
    private static final Integer[] longListsMC3Answer = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 56, 57, 58, 59, 60};

    private void rankVerification(Ranker<Integer> ranker, Pair<Double, List<Integer>>[] in, Integer[] expected) {
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(Arrays.copyOf(ranker.rank(in).toArray(), expected.length)));
    }

    private void rankVerification(Ranker<Integer> ranker, List<Integer>[] in, Integer[] expected) {
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(Arrays.copyOf(ranker.rank(in).toArray(), expected.length)));
    }

    @Test
    public void markovChainTestMC1IceCreamFlavors() {
        rankVerification(MarkovChain.MC1(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void markovChainTestMC1LongList() {
        rankVerification(MarkovChain.MC1(), longLists, longListsMC1Answer);
    }

//    @Test
//    public void markovChainTestMC1LongListWeighted() {
//        rankVerification(MarkovChain.MC1(), longListsWeighted, longListsMC1Answer);
//    }

    @Test
    public void markovChainTestMC2IceCreamFlavors() {
        rankVerification(MarkovChain.MC2(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void markovChainTestMC2LongList() {
        rankVerification(MarkovChain.MC2(), longLists, longListsMC2Answer);
    }

//    @Test
//    public void markovChainTestMC2LongListWeighted() {
//        rankVerification(MarkovChain.MC2(), longListsWeighted, longListsMC2Answer);
//    }

    @Test
    public void markovChainTestMC3IceCreamFlavors() {
        rankVerification(MarkovChain.MC3(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void markovChainTestMC3LongList() {
        rankVerification(MarkovChain.MC3(), longLists, longListsMC3Answer);
    }

//    @Test
//    public void markovChainTestMC3LongListWeighted() {
//        rankVerification(MarkovChain.MC3(), longListsWeighted, longListsMC2Answer);
//    }
}
