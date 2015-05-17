package ral.mc;

import ral.Pair;
import ral.Ranker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class MarkovChain implements Ranker<Integer> {
    private final Type type;

    private MarkovChain(Type type) {
        this.type = type;
    }

    private static double[] lsolve(double[][] m) {
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

    public static MarkovChain MC1() {
        return new MarkovChain(Type.MC1);
    }

    public static MarkovChain MC2() {
        return new MarkovChain(Type.MC2);
    }

    public static MarkovChain MC3() {
        return new MarkovChain(Type.MC3);
    }

    @Override
    public List<Integer> rank(Pair<Double, List<Integer>>[] weighedLists) {
        return null;
    }

    private void transfer(double[][] p, double a) {
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                p[i][j] = (1 - a) * p[i][j] + a / p[0].length;
            }
        }
    }

    @Override
    public List<Integer> rank(List<Integer>[] lists) {
        final TreeSet<Integer> set = new TreeSet<>();
        Arrays.stream(lists).forEach(list -> list.stream().forEach(set::add));

        int n = lists.length;
        int m = set.size();

        List<Integer> features = set.stream().collect(Collectors.toList());
        Collections.sort(features);

        ArrayList<ArrayList<Pair<Integer, Integer>>> input = new ArrayList<>();
        for (List<Integer> list : lists) {
            final ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<>();
            final TreeSet<Integer> set2 = new TreeSet<>();

            for (int i = 0; i < list.size(); i++) {
                pairs.add(Pair.of(list.get(i), i));
                set2.add(list.get(i));
            }

            set.stream().filter(t -> !set2.contains(t)).forEach(t -> pairs.add(Pair.of(t + 1, list.size())));
            Collections.sort(pairs, (o1, o2) -> Integer.compare(o1.first, o2.first));
            input.add(pairs);
        }

        double subtrahend = 1 / (double) m;
        double subtrahend2 = 1 / (double) (m * n);

        double medium = n / 2.;
        double[][] ma1 = new double[m][m];
        double[][] ma2 = new double[m][m];
        double[][] ma3 = new double[m][m];

        for (int u = 0; u < m; u++) {

            for (int v = 0; v < m; v++) {
                int c = 0;

                for (ArrayList<Pair<Integer, Integer>> list : input) {
                    if (list.get(u).second > list.get(v).second) {
                        c += 1;
                    }
                }

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

        transfer(ma, 0.05);
        double[] weights = lsolve(ma);

//        for (double[] doubles : ma) {
//            System.out.println(Arrays.toString(doubles));
//        }
//        System.out.println(Arrays.toString(weights));

        return IntStream.range(0, m).mapToObj(i -> Pair.of(weights[i], features.get(i))).sorted((o1, o2) -> -Double.compare(o1.first, o2.first)).map(pair -> pair.second).collect(Collectors.toList());
    }

    private static enum Type {
        MC1, MC2, MC3
    }
}
