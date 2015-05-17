package ral;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class Interface {
    private final MyScanner in;
    private final MyWriter out;

    public Interface(String inFile, String outFile) {
        this.in = new MyScanner(inFile);
        this.out = new MyWriter(outFile);
    }

    public static void main(String[] argv) {
        new Interface(argv[0] + ".in", argv[0] + ".out").run();
    }

    public void run() {
        int n = in.nextInt();

        List<Integer>[] list = new List[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
            int m = in.nextInt();
            for (int j = 0; j < m; j++) {
                list[i].add(in.nextInt());
            }
        }

//        out.printArray(BordasMethod.<Integer>arithmeticAverageRanker().rank(list).toArray(new Integer[0]));
//        out.printArray(BordasMethod.<Integer>medianRanker().rank(list).toArray(new Integer[0]));
//        out.printArray(BordasMethod.<Integer>geometricMeanRanker().rank(list).toArray(new Integer[0]));
//        out.printArray(BordasMethod.<Integer>L2NormRanker().rank(list).toArray(new Integer[0]));
//        out.printArray(MarkovChain.MC1().aggregate(list).toArray(new Integer[0]));
//        out.printArray(MarkovChain.MC2().aggregate(list).toArray(new Integer[0]));
//        out.printArray(MarkovChain.MC3().aggregate(list).toArray(new Integer[0]));
//        out.printArray(BordasMethod.<Integer>medianRanker().aggregate(list).toArray(new Integer[0]));

        in.close();
        out.close();
    }

    @SuppressWarnings("UnusedDeclaration")
    private static class MyScanner implements Closeable {
        private final BufferedReader bufferedReader;
        private StringTokenizer stringTokenizer = null;

        public MyScanner() {
            this("");
        }

        public MyScanner(String fileName) {

            if (fileName.isEmpty()) {
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            } else {
                try {
                    bufferedReader = new BufferedReader(new FileReader(fileName));
                } catch (FileNotFoundException e) {
                    throw new Error(e);
                }
            }
        }

        public String next() {
            try {
                while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {

                    if (bufferedReader.ready()) {
                        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                    } else {
                        return null;
                    }
                }

                return stringTokenizer.nextToken();
            } catch (IOException e) {
                return null;
            }
        }

        public String nextLine() {
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public String nextString() {
            return next();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String read() {
            StringBuilder stringBuffer = new StringBuilder();
            String tmp;

            while ((tmp = next()) != null) {
                stringBuffer.append(tmp);
                stringBuffer.append('\n');
            }

            return stringBuffer.toString();
        }

        public void close() {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    private static class MyWriter implements Closeable {
        private final PrintWriter out;

        private boolean isSystemOut;
        private boolean realTime = false;

        public MyWriter() {
            this("");
        }

        public MyWriter(String fileName) {

            if (fileName.isEmpty()) {
                out = new PrintWriter(System.out);
                isSystemOut = true;
            } else {
                try {
                    out = new PrintWriter(new PrintStream(fileName));
                } catch (FileNotFoundException e) {
                    throw new Error(e);
                }
            }
        }

        public MyWriter(File file) throws FileNotFoundException {
            out = new PrintWriter(file);
        }

        public boolean isRealTime() {
            return realTime;
        }

        public void setRealTime(boolean realTime) {
            this.realTime = realTime;
        }

        public void print(Object object) {
            out.print(object);
            flushIfRealTime();
        }

        private void flushIfRealTime() {
            if (realTime) {
                flush();
            }
        }

        public void flush() {
            out.flush();
        }

        public void println() {
            out.println();
            flushIfRealTime();
        }

        public void print(double f) {
            out.printf(Locale.US, "%.5f", f);
        }

        public void println(double f) {
            out.printf(Locale.US, "%.5f", f);
            println();
        }

        public void println(Object object) {
            print(object);
            println();
        }

        public void printArray(Integer[] object) {
            printArray(object, 0);
        }

        public void printArray(Integer[] object, int beginIndex) {
            printArray(object, beginIndex, object.length);
        }

        public void printArray(Integer[] object, int beginIndex, int endIndex) {
            final int start = max(beginIndex, 0);
            final int stop = Math.min(endIndex, object.length) - 1;

            for (int i = start; i < stop; ++i) {
                print(object[i] + " ");
            }

            println(object[stop]);
        }

        public void printArray(long[] object) {
            printArray(object, 0);
        }

        public void printArray(long[] object, int beginIndex) {
            printArray(object, beginIndex, object.length);
        }

        public void printArray(long[] object, int beginIndex, int endIndex) {
            final int start = max(beginIndex, 0);
            final int stop = Math.min(endIndex, object.length) - 1;

            for (int i = start; i < stop; ++i) {
                print(object[i] + " ");
            }

            println(object[stop]);
        }

        public boolean checkError() {
            return out.checkError();
        }

        public void close() {
            if (isSystemOut) {
                out.flush();
            } else {
                out.close();
            }
        }
    }
}
