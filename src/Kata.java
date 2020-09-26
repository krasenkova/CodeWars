import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Kata {
    public static double findUniq(double arr[]) {
        if (arr.length < 3) {
            throw new IllegalArgumentException();
        }
        double repeatedElement = arr[0];
        if (arr[0] != arr[1] && arr[0] != arr[2]) {
            return arr[0];
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != repeatedElement) {
                return arr[i];
            }
        }
        throw new RuntimeException("All elements are equal");
    }

    private double precision = 0.0000000000001;

    @Test
    public void sampleTestCases() {
        assertEquals(1.0, Kata.findUniq(new double[]{0, 1, 0}), precision);
        assertEquals(2.0, Kata.findUniq(new double[]{1, 1, 1, 2, 1, 1}), precision);
    }

}