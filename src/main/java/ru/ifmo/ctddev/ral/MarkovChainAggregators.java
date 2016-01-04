package ru.ifmo.ctddev.ral;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class MarkovChainAggregators<T> implements RankAggregator<T> {
    private final Type type;

    private MarkovChainAggregators(Type type) {
        this.type = type;
    }

    public static <T> MarkovChainAggregators<T> MC1() {
        return new MarkovChainAggregators<>(Type.MC1);
    }

    public static <T> MarkovChainAggregators<T> MC2() {
        return new MarkovChainAggregators<>(Type.MC2);
    }

    public static <T> MarkovChainAggregators<T> MC3() {
        return new MarkovChainAggregators<>(Type.MC3);
    }

//    public static <T> MarkovChainAggregators<T> MC1() {
//        return new MarkovChainAggregators<>((c, medium) -> (c == 0.) ? 0. : 1.);
//    }
//
//    public static <T> MarkovChainAggregators<T> MC2() {
//        return new MarkovChainAggregators<>((c, medium) -> (c > medium) ? 0. : 1.);
//    }
//
//    public static <T> MarkovChainAggregators<T> MC3() {
//        return new MarkovChainAggregators<>((c, medium) -> c);
//    }

    private static double[] findStationaryDistribution(double[][] m) {
        int n = m.length;
        double[] b = new double[n];
        double[][] a = new double[n][n];

        b[n - 1] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[j][i] = m[i][j];
            }

            a[i][i] -= 1;
            a[n - 1][i] = 1;
        }

        for (int i = 0; i < n; i++) {
            double t = a[i][i];

            for (int j = i; j < n; j++) {
                a[i][j] /= t;
            }

            b[i] /= t;

            for (int l = i + 1; l < n; l++) {
                for (int j = i + 1; j < n; j++) {
                    a[l][j] -= a[i][j] * a[l][i];
                }

                b[l] -= b[i] * a[l][i];
            }
        }

        double[] x = new double[n];

        x[n - 1] = b[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            x[i] = b[i];

            for (int j = i + 1; j < n; j++) {
                x[i] -= a[i][j] * x[j];
            }
        }

        return x;
    }

    private double[][] transfer(double[][] p, double a) {
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                p[i][j] = (1 - a) * p[i][j] + a / p[0].length;
            }
        }

        return p;
    }

    @Override
    public List<T> aggregate(ListOfRanks<T> ranks) {
        int m = ranks.countOfItems();
        double n_ = DoubleStream.of(ranks.weights()).sum();
        double subtrahend = 1 / (double) m;
        double subtrahend2 = 1 / (m * n_);

        double medium = n_ / 2.;
        double[][] ma1 = new double[m][m];
        double[][] ma2 = new double[m][m];
        double[][] ma3 = new double[m][m];

        for (int u = 0; u < m; u++) {
            for (int v = 0; v < m; v++) {
                final int finalU = u;
                final int finalV = v;

                double c = ranks
                        .stream()
                        .filter(rank -> rank.position(finalU) > rank.position(finalV))
                        .mapToDouble(rank -> rank.weight)
                        .sum();

                if (c > 0) { // MC1
                    ma1[u][v] = subtrahend;
                }

                if (c >= medium) { // MC2
                    ma2[u][v] = subtrahend;
                }

                ma3[u][v] = c * subtrahend2; // MC3
            }
        }

        double[][] ma;
        switch (type) {
            case MC1:
                ma = ma1;
                break;
            case MC2:
                ma = ma2;
                break;
            default:
                ma = ma3;
        }

        for (int i = 0; i < m; i++) {
            ma[i][i] = 1 - DoubleStream.of(ma[i]).sum();
        }

        double[] weights = findStationaryDistribution(transfer(ma, 0.05));

        return IntStream
                .range(0, m)
                .mapToObj(i -> Pair.of(weights[i], ranks.itemByNumber(i)))
                .sorted((o1, o2) -> -Double.compare(o1.first, o2.first))
                .map(pair -> pair.second)
                .collect(Collectors.toList());
    }

    private enum Type {
        MC1, MC2, MC3
    }
}
