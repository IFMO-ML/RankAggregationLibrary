package ru.ifmo.ctddev.ral.distances;

import org.junit.Assert;
import org.junit.Test;

public class KendallsTauDistanceTest {
    @Test
    public void test1() {
        Assert.assertEquals(
                0,
                new KendallsTauDistance<Integer>().calculate(
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
                new KendallsTauDistance<Integer>().calculate(
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
                new KendallsTauDistance<Integer>().calculate(
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
                0,
                new KendallsTauDistance<Integer>().calculate(
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
                0,
                new KendallsTauDistance<Integer>().calculate(
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
                0,
                new KendallsTauDistance<Integer>().calculate(
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
                0,
                new KendallsTauDistance<Integer>().calculate(
                        new Integer[]{
                                1, 3
                        }, new Integer[]{
                                2, 1
                        }),
                0
        );
    }

    @Test
    public void test4withP() {
        Assert.assertEquals(
                1.5,
                new KendallsTauDistance<Integer>(0.5).calculate(
                        new Integer[]{
                                1, 2
                        }, new Integer[]{
                                1, 3
                        }),
                0
        );
    }

    @Test
    public void test4rwithP() {
        Assert.assertEquals(
                1.5,
                new KendallsTauDistance<Integer>(0.5).calculate(
                        new Integer[]{
                                1, 3
                        }, new Integer[]{
                                1, 2
                        }),
                0
        );
    }

    @Test
    public void test5withP() {
        Assert.assertEquals(
                1.5,
                new KendallsTauDistance<Integer>(0.5).calculate(
                        new Integer[]{
                                2, 1
                        }, new Integer[]{
                                1, 3
                        }),
                0
        );
    }

    @Test
    public void test5rwithP() {
        Assert.assertEquals(
                1.5,
                new KendallsTauDistance<Integer>(0.5).calculate(
                        new Integer[]{
                                1, 3
                        }, new Integer[]{
                                2, 1
                        }),
                0
        );
    }
}
