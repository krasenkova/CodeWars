import org.junit.Test;

import java.util.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static org.junit.Assert.assertEquals;

public class DuplicateEncoder {
    static String encode(String word) {
        String[] wordLowerCase = word.toLowerCase().split("");
        Set<String> repeatedSymbols = Arrays.stream(wordLowerCase)
            .collect(groupingBy(identity(), counting()))
            .entrySet()
            .stream()
            .filter(e -> e.getValue() > 1)
            .map(Map.Entry::getKey)
            .collect(toSet());
        return Arrays.stream(wordLowerCase)
            .map(s -> repeatedSymbols.contains(s) ? ")" : "(")
            .collect(joining());
    }

    @Test
    public void test() {
        assertEquals(")()())()(()()(", DuplicateEncoder.encode("Prespecialized"));
        assertEquals("))))())))", DuplicateEncoder.encode("   ()(   "));
    }

}
