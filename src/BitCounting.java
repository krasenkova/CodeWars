import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class BitCounting {

    public static int countBits(int n) {
        int counter = 0;
        while (n != 0) {
            counter += (n & 1);
            n = n >> 1;
        }
        return counter;
    }

    public static int countBits_var1(int n) {
//        System.out.println(Integer.toBinaryString(n));
//        System.out.println(Integer.toBinaryString(n >>> 1));//right unsigned
//        System.out.println(Integer.toBinaryString(n<<1)); //left
//       n>>1 & 1;
//        System.out.println(Integer.toBinaryString(n >> 1));//right signed - keeps correct sign
//        System.out.println(Integer.toBinaryString(n >> 1 & 1));
        int counter = 0;
        while (n != 0) {
            counter += (n & 1);
            n = n >> 1;
        }
        return counter;
    }

    @Test
    public void test() {
        Assert.assertEquals(Integer.bitCount(0), countBits(0));
        Random rand = new Random(0);
        for (int i = 0; i < 500; i++) {
            int n = rand.nextInt(Integer.MAX_VALUE);
            System.out.println("Testing n = " + n);
            Assert.assertEquals(Integer.bitCount(n), countBits(n));
            Assert.assertEquals(Integer.bitCount(n), countBits_var1(n));
        }
    }
}
