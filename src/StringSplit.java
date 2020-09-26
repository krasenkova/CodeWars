import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class StringSplit {
    public static String[] solution(String s) {
        if (s.length() % 2 != 0) {
            s = s + "_";
        }
        char[] chars = s.toCharArray();
        String[] result = new String[chars.length / 2];
        for (int i = 0; i < chars.length; i += 2) {
            result[i / 2] = String.valueOf(chars, i, 2);
        }
        return result;
//        char[] chars = s.toCharArray();
//        boolean odd = chars.length % 2 != 0;
//        String[] result = new String[odd ? chars.length / 2 + 1 : chars.length / 2];
    }

    @Test
    public void testEvenString() {
        String s = "abcdef";
        String s1 = "HelloWorld";
        assertEquals("Should handle even string", "[ab, cd, ef]", Arrays.toString(StringSplit.solution(s)));
        assertEquals("Should handle even string", "[He, ll, oW, or, ld]", Arrays.toString(StringSplit.solution(s1)));
    }

    @Test
    public void testOddString() {
        String s = "abcde";
        String s1 = "LovePizza";
        assertEquals("Should handle odd string", "[ab, cd, e_]", Arrays.toString(StringSplit.solution(s)));
        assertEquals("Should handle odd string", "[Lo, ve, Pi, zz, a_]", Arrays.toString(StringSplit.solution(s1)));
    }

}