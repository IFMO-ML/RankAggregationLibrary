package ral.borda;

import org.junit.Assert;
import org.junit.Test;
import ral.Pair;
import ral.Ranker;

import java.util.Arrays;
import java.util.List;

public class BordasMethodTest {
    @SuppressWarnings("unchecked")
    private static final Pair<Double, List<Integer>>[] iceCreamFlavors = new Pair[]{
            Pair.of(1., Arrays.asList(1, 2, 3, 5)),
            Pair.of(1., Arrays.asList(3, 5, 1, 2)),
            Pair.of(1., Arrays.asList(1, 3, 5, 2)),
    };
    private static final Integer[] iceCreamFlavorsAnswer = new Integer[]{1, 3, 5, 2};

    @SuppressWarnings("unchecked")
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
    private static final Integer[] longListsBrodaARM = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 56, 57, 47, 58, 59, 48, 60, 41, 42};
    private static final Integer[] longListsBrodaMED = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 31, 32, 33, 34, 35};
    private static final Integer[] longListsBrodaGEM = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 56, 30, 57, 58, 46, 59, 47, 60, 41, 48, 42};
    private static final Integer[] longListsBrodaL2N = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 47, 56, 57, 48, 58, 59, 60, 49, 41};

    private void rankVerificationWeighted(Ranker<Integer> ranker, Pair<Double, List<Integer>>[] in, Integer[] expected) {
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(Arrays.copyOf(ranker.rank(in).toArray(), expected.length)));
    }

    private void rankVerification(Ranker<Integer> ranker, List<Integer>[] in, Integer[] expected) {
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(Arrays.copyOf(ranker.rank(in).toArray(), expected.length)));
    }

    @Test
    public void arithmeticAverageRankerTestIceCreamFlavors() {
        rankVerificationWeighted(BordasMethod.arithmeticAverageRanker(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void arithmeticAverageRankerTestLongLists() {
        rankVerification(BordasMethod.arithmeticAverageRanker(), longLists, longListsBrodaARM);
        rankVerificationWeighted(BordasMethod.arithmeticAverageRanker(), longListsWeighted, longListsBrodaARM);
    }

    @Test
    public void medianRankerTestIceCreamFlavors() {
        rankVerificationWeighted(BordasMethod.medianRanker(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void medianRankerTestLongLists() {
        rankVerification(BordasMethod.medianRanker(), longLists, longListsBrodaMED);
        rankVerificationWeighted(BordasMethod.medianRanker(), longListsWeighted, longListsBrodaMED);
    }

    @Test
    public void geometricMeanRankerTestIceCreamFlavors() {
        rankVerificationWeighted(BordasMethod.geometricMeanRanker(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void geometricMeanRankerTestLongLists() {
        rankVerification(BordasMethod.geometricMeanRanker(), longLists, longListsBrodaGEM);
        rankVerificationWeighted(BordasMethod.geometricMeanRanker(), longListsWeighted, longListsBrodaGEM);
    }

    @Test
    public void L2NormRankerTestIceCreamFlavors() {
        rankVerificationWeighted(BordasMethod.L2NormRanker(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void L2NormRankerTestLongLists() {
        rankVerification(BordasMethod.L2NormRanker(), longLists, longListsBrodaL2N);
        rankVerificationWeighted(BordasMethod.L2NormRanker(), longListsWeighted, longListsBrodaL2N);
    }
}
