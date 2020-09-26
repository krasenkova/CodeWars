import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class BouncingBall {

    public static int bouncingBall(double h, double bounce, double window) {
        if (h <= 0 || bounce <= 0 || bounce >= 1 || window >= h) {
            return -1;
        }
//        int counter = 1; // first fall to the ground
//        double currentBallHeight = h * bounce;
//        while (currentBallHeight > window) {
//            counter += 2;
//            currentBallHeight = currentBallHeight * bounce;
//        }
        int counter = (int) Stream.iterate(
            h * bounce,
            currentBallHeight -> currentBallHeight > window,
            currentBallHeight -> currentBallHeight * bounce
        )
        .count() * 2 + 1;
        return counter;
    }

    @Test
    public void test1() {
        assertEquals(3, BouncingBall.bouncingBall(3.0, 0.66, 1.5));
    }

    @Test
    public void test2() {
        assertEquals(15, BouncingBall.bouncingBall(30.0, 0.66, 1.5));
    }
}