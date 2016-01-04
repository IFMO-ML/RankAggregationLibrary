package ru.ifmo.ctddev.ral;

import java.util.List;

public class CrossEntropyMonteCarloAggregators<T> implements RankAggregator<T> {
    @Override
    public List<T> aggregate(ListOfRanks<T> ranks) {
        return null;
    }
//    private final Distance<Integer> distance;
//    private final double pi0 = 0.325;  // 0.25-0.5
//    private final double pi = 0.325;   // 0.25-0.5
//    private final double eps;
//    private final int MAX_ITERATIONS = 100; // todo
//    private final double c1 = 10;
//    private final double N1;
//    private final double N;
//    private final double ro = 0.075;   // 0.05-0.1
//
//    public CEMC(Distance<Integer> distance, double eps, double N) {
//        this.distance = distance;
//        this.eps = eps;
//        this.N = N;  // N ≥ kn
//        this.N1 = 1. / this.c1 * this.N;
//    }
//
//    private double calculateDistances(List<Integer> opt, Pair<Double, List<Integer>>[] weighedLists) {
//        double sum = 0;
////        Arrays.stream(weighedLists).
//
//        for (Pair<Double, List<Integer>> pair : weighedLists) {
//            sum += pair.first * distance.calculate(
//                    opt.toArray(new Integer[opt.size()]),
//                    pair.second.toArray(new Integer[pair.second.size()])
//            );
//        }
//
//        return sum;
//    }
//
//    private double[][] calculateNewU(double[] phis, double[][][] x, double y) {
//        double[][] result = new double[x[0].length][x[0][0].length];
//
//
//        for (int j = 0; j < x[0].length; j++) {
//            for (int r = 0; r < x[0][j].length; r++) {
//                double s1 = 0;
//                double s2 = 0;
//
//                for (int i = 0; i < x.length; i++) {
//                    if (phis[i] < y) {
//                        s1 += x[i][j][r];
//                        s2 += 1;
//                    }
//                }
//
//                result[j][r] = s1 / s2;
//            }
//        }
//
//        return null;
//    }
//
//    private double[][] getU0(int n, int r) {
//        double[][] u0_NI = new double[n][r];
//        double p0 = 1.0 / n;
//
//        for (int i = 0; i < n; i++) {
//            Arrays.fill(u0_NI[i], p0);
//        }
//
//        return u0_NI;
//    }
//
//    private double norm(double[][] u1, double[][] u2) {
//        double result = 0;
//
//        for (int i = 0; i < u1.length; i++) {
//            for (int j = 0; j < u1[0].length; j++) {
//                result += Math.abs(u1[i][j] - u2[i][j]);
//            }
//        }
//
//        if (u1.length == 0) {
//            return 0;
//        }
//
//        return result / u1.length / u1[0].length;
//    }
//
//    private double[][] getUpdatedU(double[][] u1, double[][] uNew) {
//        double[][] uUpdated = new double[u1.length][u1[0].length];
//
//        for (int i = 0; i < u1.length; i++) {
//            for (int j = 0; j < u1[0].length; j++) {
//                uUpdated[i][j] = (1 - pi) * u1[i][j] + pi * uNew[i][j];
//            }
//        }
//
//        return uUpdated;
//    }
//
//    @Override
//    public List<Integer> rank(Pair<Double, List<Integer>>[] weighedLists) {
//        // k - count of elements in each list
//        // k - size of S
//        int r = 1; //todo
//        int n = 1; //todo
//        double[][] u1 = getU0(n, r);
//        double[][] u2 = null;
//
//        for (int iteration_count = 0; u2 == null || (norm(u1, u2) >= eps && iteration_count < MAX_ITERATIONS); iteration_count++) {
////            double[][] uNew = calculateNewU(y, x, weighedLists);
//            u2 = u1;
////            u1 = getUpdatedU(u1, uNew);
//        }
//
//        return null;
//    }
//
//    @Override
//    public List<Integer> rank(List<Integer>[] lists) {
//        return null;
//    }
}
