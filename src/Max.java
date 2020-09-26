import org.junit.Test;

import static java.util.Arrays.fill;
import static org.junit.Assert.assertEquals;

public class Max {
    public static int sequence(int[] arr) {
        int len = arr.length;
        int max = 0;
        int currentSum;
        int[][] sums = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                currentSum = j == 0 ? arr[i] : sums[i][j - 1] + arr[j];
                if (currentSum > max) {
                    max = currentSum;
                }
                sums[i][j] = currentSum;
            }
        }
        print(sums);
        return max;
    }

    public static int sequence_(int[] arr) {
        int max_ending_here = 0, max_so_far = 0;
        for (int v : arr) {
            max_ending_here = Math.max(0, max_ending_here + v);
            max_so_far = Math.max(max_so_far, max_ending_here);
        }
        return max_so_far;
    }

    private static void print(int[][] matrix) {
        System.out.print("    ");
        for (int i = 0; i < matrix.length; i++) {
            System.out.printf("%5s", "j:" + i);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) { //this equals to the row in our matrix.
            System.out.print("i:" + i);
            for (int j = 0; j < matrix[i].length; j++) { //this equals to the column in each row.
                System.out.printf("%5d", matrix[i][j]);
            }
            System.out.println(); //change line on console as row comes to end in the matrix.
        }
    }

    @Test
    public void testEmptyArray() throws Exception {
        assertEquals("Empty arrays should have a max of 0", 0, Max.sequence(new int[]{}));
    }

    @Test
    public void testExampleArray() throws Exception {
        assertEquals("Example array should have a max of 6", 6, Max.sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    @Test
    public void testX() throws Exception {
        assertEquals(10, Max.sequence(new int[]{10, -2, -1, -3}));
        assertEquals(0, Max.sequence(new int[]{-2, -1, -3}));
    }
}