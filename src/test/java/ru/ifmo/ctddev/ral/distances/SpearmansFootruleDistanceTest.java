package ru.ifmo.ctddev.ral.distances;

import org.junit.Assert;
import org.junit.Test;

public class SpearmansFootruleDistanceTest {
    @Test
    public void test1() {
        Assert.assertEquals(
                0,
                new SpearmansFootruleDistance<Integer>().calculate(
                        new Integer[]{
                                1
                        }, new Integer[]{
                                1
                        }),
                0
        );
    }

    @Test
    public void test2() {
        Assert.assertEquals(
                0,
                new SpearmansFootruleDistance<Integer>().calculate(
                        new Integer[]{
                                1, 2
                        }, new Integer[]{
                                1, 2
                        }),
                0
        );
    }

    @Test
    public void test3() {
        Assert.assertEquals(
                2,
                new SpearmansFootruleDistance<Integer>().calculate(
                        new Integer[]{
                                2, 1
                        }, new Integer[]{
                                1, 2
                        }),
                0
        );
    }

    @Test
    public void test4() {
        Assert.assertEquals(
                2,
                new SpearmansFootruleDistance<Integer>().calculate(
                        new Integer[]{
                                1, 2
                        }, new Integer[]{
                                1, 3
                        }),
                0
        );
    }

    @Test
    public void test4r() {
        Assert.assertEquals(
                2,
                new SpearmansFootruleDistance<Integer>().calculate(
                        new Integer[]{
                                1, 3
                        }, new Integer[]{
                                1, 2
                        }),
                0
        );
    }

    @Test
    public void test5() {
        Assert.assertEquals(
                4,
                new SpearmansFootruleDistance<Integer>().calculate(
                        new Integer[]{
                                2, 1
                        }, new Integer[]{
                                1, 3
                        }),
                0
        );
    }

    @Test
    public void test5r() {
        Assert.assertEquals(
                4,
                new SpearmansFootruleDistance<Integer>().calculate(
                        new Integer[]{
                                1, 3
                        }, new Integer[]{
                                2, 1
                        }),
                0
        );
    }
}
