package ru.ifmo.ctddev.ral;

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

    private static final Integer[] longListsMC1Answer = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 26, 24, 27, 25, 28, 46, 29, 47, 30, 48, 56, 49, 57, 50, 58, 59, 41};
    //    private static final Integer[] longListsMC2Answer = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 57, 56, 58, 60, 59};
    private static final Integer[] longListsMC2Answer2 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 57, 56, 58, 59, 60};
    private static final Integer[] longListsMC3Answer = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 46, 47, 48, 49, 50, 56, 57, 58, 59, 60};


    private static final ListOfRanks<String> gens = new ListOfRanks<>(new String[][]{
            {"HPN", "AMACR", "CYP1B1", "ATF5", "BRCA1", "LGALS3", "MYC", "PCDHGC3", "WT1", "TFF3", "MARCKS", "OS-9", "CCND2", "NME1", "DRRK1A", "TRAP1", "FMO5", "ZHX2", "RPL36AL", "ITPR3", "GCSH", "DDB2", "TFCP2", "TRAM1", "YTHDF3"},
            {"HPN", "AMACR", "OACT2", "GDF15", "FASN", "ANK3", "KRT18", "UAP1", "GRP58", "PPIB", "KRT7", "NME1", "STRA13", "DAPK1", "TMEM4", "CANX", "TRA1", "PRSS8", "EMTPD6", "PPP1CA", "ACADSB", "PTPLB", "TMEM23", "MRPL3", "SLC19A1"},
            {"OGT", "AMACR", "FASN", "HPN", "UAP1", "GUCY1A3", "OACT2", "SLC19A1", "KRT18", "EEF2", "STRA13", "ALCAM", "GDF15", "NME1", "CALR", "SND1", "STAT6", "TCEB3", "EIF4A1", "LMAN1", "MAOA", "ATP6V0B", "PPIB", "FMO5", "SLC7A5"},
            {"AMACR", "HPN", "NME2", "CBX3", "GDF15", "MTHFD2", "MRPL3", "SLC25A6", "NME1", "COX6C", "JTV1", "CCNG2", "AP3S1", "EEF2", "RAN", "PRKACA", "RAD23B", "PSAP", "CCT2", "G3BP", "EPRS", "CKAP1", "LIG3", "SNX4", "NSMAF"},
            {"HPN", "SLC25A6", "EEF2", "SAT", "NME2", "LDHA", "CANX", "NACA", "FASN", "SND1", "KRT18", "RPL15", "TNFSF10", "SERP1", "GRP58", "ALCAM", "GDF15", "TMEM4", "CCT2", "SLC39A6", "RPL5", "RPS13", "MTHFD2", "G3BP2", "UAP1"},
    });

    private static final String[] gensMC1Answer = new String[]{"HPN", "AMACR", "GDF15", "NME1", "FASN", "EEF2", "KRT18", "NME2", "SLC25A6", "UAP1", "OACT2", "CYP1B1", "ATF5", "CANX", "OGT", "BRCA1", "MTHFD2", "LGALS3", "GRP58", "SND1", "CBX3", "MRPL3", "MYC", "STRA13", "ALCAM"};
    private static final String[] gensMC2Answer = new String[]{"HPN", "AMACR", "GDF15", "FASN", "KRT18", "NME1", "EEF2", "UAP1", "OACT2", "NME2", "SLC25A6", "OGT", "SAT", "NACA", "LDHA", "CANX", "SLC19A1", "ANK3", "ALCAM", "GUCY1A3", "SND1", "MTHFD2", "TMEM4", "MRPL3", "GRP58"};
    private static final String[] gensMC3Answer = new String[]{"HPN", "AMACR", "GDF15", "NME1", "FASN", "EEF2", "KRT18", "UAP1", "NME2", "SLC25A6", "OACT2", "CANX", "GRP58", "STRA13", "SND1", "ALCAM", "MTHFD2", "MRPL3", "SLC19A1", "TMEM4", "PPIB", "CCT2", "FMO5", "OGT", "CYP1B1"};

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
    public void markovChainTestMC1IceCreamFlavors() {
        rankVerification(MarkovChainAggregators.MC1(), iceCreamFlavors, iceCreamFlavorsAnswer2);
    }

    @Test
    public void markovChainTestMC1LongList() {
        rankVerification(MarkovChainAggregators.MC1(), longLists, longListsMC1Answer);
    }

    @Test
    public void markovChainTestMC1LongListWeights() {
        rankVerificationWeights(MarkovChainAggregators.MC1(), longLists, longListsW);
    }

    @Test
    public void markovChainTestMC1LongListWeights2() {
        rankVerificationWeights(MarkovChainAggregators.MC1(), longLists, longListsW2);
    }

//    @Test
//    public void markovChainTestMC1gens() {
//        rankVerification(MarkovChainAggregators.MC1(), gens, gensMC1Answer);
//    }

    @Test
    public void markovChainTestMC2IceCreamFlavors() {
        rankVerification(MarkovChainAggregators.MC2(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void markovChainTestMC2LongList() {
        rankVerification(MarkovChainAggregators.MC2(), longLists, longListsMC2Answer2);
    }

    @Test
    public void markovChainTestMC2LongListWeights() {
        rankVerificationWeights(MarkovChainAggregators.MC2(), longLists, longListsW);
    }

    @Test
    public void markovChainTestMC2LongListWeights2() {
        rankVerificationWeights(MarkovChainAggregators.MC2(), longLists, longListsW2);
    }

//    @Test
//    public void markovChainTestMC2gens() {
//        rankVerification(MarkovChainAggregators.MC2(), gens, gensMC2Answer);
//    }

    @Test
    public void markovChainTestMC3IceCreamFlavors() {
        rankVerification(MarkovChainAggregators.MC3(), iceCreamFlavors, iceCreamFlavorsAnswer);
    }

    @Test
    public void markovChainTestMC3LongList() {
        rankVerification(MarkovChainAggregators.MC3(), longLists, longListsMC3Answer);
    }

    @Test
    public void markovChainTestMC3LongListWeights() {
        rankVerificationWeights(MarkovChainAggregators.MC3(), longLists, longListsW);
    }

    @Test
    public void markovChainTestMC3LongListWeights2() {
        rankVerificationWeights(MarkovChainAggregators.MC3(), longLists, longListsW2);
    }

//    @Test
//    public void markovChainTestMC3gens() {
//        rankVerification(MarkovChainAggregators.MC3(), gens, gensMC3Answer);
//    }
}
