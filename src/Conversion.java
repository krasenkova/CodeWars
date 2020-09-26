import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Conversion {
    /*
Symbol    Value
I          1
V          5
X          10
L          50
C          100
D          500
M          1,000
     */

    public String solution(int n) {
        if (n > 3999) {
            throw new IllegalArgumentException("The largest number in Roman notation is 3999");
        }
        String[] symbols = new String[]{"I", "V", "X", "L", "C", "D", "M"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0, num = n; i < symbols.length && num > 0; i += 2, num = num / 10) {
            int digit = num % 10;
            if (digit > 0 && digit < 4) {
                sb.append(symbols[i].repeat(digit));
            } else if (digit == 4) {
                sb.append(symbols[i + 1]).append(symbols[i]);
            } else if (digit == 5) {
                sb.append(symbols[i + 1]);
            } else if (digit > 5 && digit < 9) {
                sb.append(symbols[i].repeat(digit - 5)).append(symbols[i + 1]);
            } else if (digit == 9) {
                sb.append(symbols[i + 2]).append(symbols[i]);
            }
            System.out.println("num = " + num);
            System.out.println("digit = " + digit);
            System.out.println("symbols[i] = " + symbols[i]);
        }
        sb.reverse();
        System.out.println("sb = " + sb);
        return sb.toString();
    }

    @Test
    public void shouldConvertToRoman() {
        assertEquals("solution(1) should equal to I", "I", solution(1));
        assertEquals("solution(4) should equal to IV", "IV", solution(4));
        assertEquals("solution(6) should equal to VI", "VI", solution(6));
    }
}