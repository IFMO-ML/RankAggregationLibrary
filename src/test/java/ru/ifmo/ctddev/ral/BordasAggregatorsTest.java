package ru.ifmo.ctddev.ral;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BordasAggregatorsTest {
    private static final ListOfRanks<Integer> iceCreamFlavors = new ListOfRanks<>(new Integer[][]{
            {1, 2, 3, 5},
            {3, 5, 1, 2},
            {1, 3, 5, 2},
    });

    private static final Integer[] iceCreamFlavorsAnswer = new Integer[]{1, 3, 5, 2};

    private static final ListOfRanks<Integer> longLists = new ListOfRanks<>(new Integer[][]{
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 56, 57, 58, 59, 60, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 61, 62, 63, 64, 65},
    });

    private static final ListOfRanks<Integer> longListsW = new ListOfRanks<>(
            new Integer[][]{
                    {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40},
                    {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55},
                    {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 56, 57, 58, 59, 60, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 61, 62, 63, 64, 65},
            }, new double[]{30, 30, 30}
    );

    private static final ListOfRanks<Integer> longListsW2 = new ListOfRanks<>(
            new Integer[][]{
                    {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40},
                    {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55},
                    {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 56, 57, 58, 59, 60, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 61, 62, 63, 64, 65},
            }, new double[]{1. / 30, 1. / 30, 1. / 30}
    );

    private static final Integer[] longListsBrodaARM = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 56, 57, 47, 58, 59, 48, 60, 41, 42};
    private static final Integer[] longListsBrodaMED = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 31, 32, 33, 34, 35};
    private static final Integer[] longListsBrodaGEM = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 56, 30, 57, 58, 46, 59, 47, 60, 41, 48, 42};
    private static final Integer[] longListsBrodaL2N = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 47, 56, 57, 48, 58, 59, 60, 49, 41};

    private static final ListOfRanks<String> gens = new ListOfRanks<>(new String[][]{
            {"HPN", "AMACR", "CYP1B1", "ATF5", "BRCA1", "LGALS3", "MYC", "PCDHGC3", "WT1", "TFF3", "MARCKS", "OS-9", "CCND2", "NME1", "DRRK1A", "TRAP1", "FMO5", "ZHX2", "RPL36AL", "ITPR3", "GCSH", "DDB2", "TFCP2", "TRAM1", "YTHDF3"},
            {"HPN", "AMACR", "OACT2", "GDF15", "FASN", "ANK3", "KRT18", "UAP1", "GRP58", "PPIB", "KRT7", "NME1", "STRA13", "DAPK1", "TMEM4", "CANX", "TRA1", "PRSS8", "EMTPD6", "PPP1CA", "ACADSB", "PTPLB", "TMEM23", "MRPL3", "SLC19A1"},
            {"OGT", "AMACR", "FASN", "HPN", "UAP1", "GUCY1A3", "OACT2", "SLC19A1", "KRT18", "EEF2", "STRA13", "ALCAM", "GDF15", "NME1", "CALR", "SND1", "STAT6", "TCEB3", "EIF4A1", "LMAN1", "MAOA", "ATP6V0B", "PPIB", "FMO5", "SLC7A5"},
            {"AMACR", "HPN", "NME2", "CBX3", "GDF15", "MTHFD2", "MRPL3", "SLC25A6", "NME1", "COX6C", "JTV1", "CCNG2", "AP3S1", "EEF2", "RAN", "PRKACA", "RAD23B", "PSAP", "CCT2", "G3BP", "EPRS", "CKAP1", "LIG3", "SNX4", "NSMAF"},
            {"HPN", "SLC25A6", "EEF2", "SAT", "NME2", "LDHA", "CANX", "NACA", "FASN", "SND1", "KRT18", "RPL15", "TNFSF10", "SERP1", "GRP58", "ALCAM", "GDF15", "TMEM4", "CCT2", "SLC39A6", "RPL5", "RPS13", "MTHFD2", "G3BP2", "UAP1"},
    });

    private static final String[] gensBrodaARM = new String[]{"HPN", "AMACR", "GDF15", "FASN", "NME1", "KRT18", "EEF2", "NME2", "OACT2", "SLC25A6", "UAP1", "CANX", "GRP58", "STRA13", "SND1", "OGT", "ALCAM", "CYP1B1", "MTHFD2", "ATF5", "CBX3", "SAT", "BRCA1", "MRPL3", "LGALS3"};
    private static final String[] gensBrodaMED = new String[]{"HPN", "AMACR", "FASN", "KRT18", "GDF15", "NME1", "EEF2", "UAP1", "CYP1B1", "ATF5", "BRCA1", "LGALS3", "MYC", "PCDHGC3", "WT1", "TFF3", "MARCKS", "OS-9", "CCND2", "DYRK1A", "TRAP1", "FMO5", "ZHX2", "RPL36AL", "ITPR3"};
    private static final String[] gensBrodaGEM = new String[]{"HPN", "AMACR", "FASN", "GDF15", "NME2", "SLC25A6", "EEF2", "OACT2", "OGT", "KRT18", "NME1", "UAP1", "CYP1B1", "ATF5", "CBX3", "SAT", "CANX", "BRCA1", "GRP58", "MTHFD2", "STRA13", "LGALS3", "ANK3", "GUCY1A3", "LDHA"};
    private static final String[] gensBrodaL2N = new String[]{"HPN", "AMACR", "GDF15", "NME1", "FASN", "KRT18", "EEF2", "NME2", "UAP1", "OACT2", "SLC25A6", "STRA13", "CANX", "GRP58", "SND1", "ALCAM", "TMEM4", "MTHFD2", "MRPL3", "PPIB", "OGT", "CYP1B1", "SLC19A1", "ATF5", "CBX3"};
//    private static final String[] gensBrodaARM = new String[]{"HPN", "AMACR", "FASN", "GDF15", "UAP1", "OACT2", "KRT18", "SLC25A6", "NME1", "EEF2", "STRA13", "NME2", "CANX", "SND1", "GRP58", "ALCAM", "PPIB", "TMEM4", "CYP1B1", "MTHFD2", "ATF5", "MRPL3", "BRCA1", "LGALS3", "MYC"};
//    private static final String[] gensBrodaARM = new String[]{"HPN", "AMACR", "FASN", "GDF15", "NME2", "UAP1", "OACT2", "SLC25A6", "KRT18", "EEF2", "STRA13", "NME1", "CANX", "ALCAM", "GRP58", "SND1", "FMO5", "TMEM4", "CCT2", "PRKACA", "MTHFD2", "PTPLB", "PPIB", "MRPL3", "SLC19A1"};

    private <T> void rankVerification(RankAggregator<T> rankAggregator, ListOfRanks<T> ranks, T[] expected) {
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(Arrays.copyOf(rankAggregator.aggregate(ranks).toArray(), expected.length)));
    }

    private <T> void rankVerificationWeights(RankAggregator<T> rankAggregator, ListOfRanks<T> ranks, ListOfRanks<T> ranksW) {
        Assert.assertEquals(
                Arrays.toString(rankAggregator.aggregate(ranks).toArray()),
                Arrays.toString(rankAggregator.aggregate(ranksW).toArray())
        );
    }

    @Test
    public void arithmeticAverageRankerTestIceCreamFlavors() {
        rankVerification(BordasAggregators.arithmeticAverageRanker(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void arithmeticAverageRankerTestLongLists() {
        rankVerification(BordasAggregators.arithmeticAverageRanker(), longLists, longListsBrodaARM);
    }

    @Test
    public void arithmeticAverageRankerTestLongListsWeights() {
        rankVerificationWeights(BordasAggregators.arithmeticAverageRanker(), longLists, longListsW);
    }

    @Test
    public void arithmeticAverageRankerTestLongListsWeights2() {
        rankVerificationWeights(BordasAggregators.arithmeticAverageRanker(), longLists, longListsW2);
    }

//    @Test
//    public void arithmeticAverageRankerTestGens() {
//        rankVerification(BordasAggregators.arithmeticAverageRanker(), gens, gensBrodaARM);
//    }

    @Test
    public void medianRankerTestIceCreamFlavors() {
        rankVerification(BordasAggregators.medianRanker(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void medianRankerTestLongLists() {
        rankVerification(BordasAggregators.medianRanker(), longLists, longListsBrodaMED);
    }

    @Test
    public void medianRankerTestLongListsWeights() {
        rankVerificationWeights(BordasAggregators.medianRanker(), longLists, longListsW);
    }

    @Test
    public void medianRankerTestLongListsWeights2() {
        rankVerificationWeights(BordasAggregators.medianRanker(), longLists, longListsW2);
    }

//    @Test
//    public void medianRankerTestGens() {
//        rankVerification(BordasAggregators.medianRanker(), gens, gensBrodaMED);
//    }

    @Test
    public void geometricMeanRankerTestIceCreamFlavors() {
        rankVerification(BordasAggregators.geometricMeanRanker(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void geometricMeanRankerTestLongLists() {
        rankVerification(BordasAggregators.geometricMeanRanker(), longLists, longListsBrodaGEM);
    }

    @Test
    public void geometricMeanRankerTestLongListsWeights() {
        rankVerificationWeights(BordasAggregators.geometricMeanRanker(), longLists, longListsW);
    }

    @Test
    public void geometricMeanRankerTestLongListsWeights2() {
        rankVerificationWeights(BordasAggregators.geometricMeanRanker(), longLists, longListsW2);
    }

//    @Test
//    public void geometricMeanRankerTestGens() {
//        rankVerification(BordasAggregators.geometricMeanRanker(), gens, gensBrodaGEM);
//    }

    @Test
    public void L2NormRankerTestIceCreamFlavors() {
        rankVerification(BordasAggregators.L2NormRanker(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void L2NormRankerTestLongLists() {
        rankVerification(BordasAggregators.L2NormRanker(), longLists, longListsBrodaL2N);
    }

    @Test
    public void L2NormRankerTestLongListsWeights() {
        rankVerificationWeights(BordasAggregators.L2NormRanker(), longLists, longListsW);
    }

    @Test
    public void L2NormRankerTestLongListsWeights2() {
        rankVerificationWeights(BordasAggregators.L2NormRanker(), longLists, longListsW2);
    }

//    @Test
//    public void L2NormRankerTestGens() {
//        rankVerification(BordasAggregators.L2NormRanker(), gens, gensBrodaL2N);
//    }
}
