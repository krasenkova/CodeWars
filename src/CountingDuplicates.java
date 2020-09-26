import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountingDuplicates {
    public static void main(String[] args) {

    }

    public static int duplicateCount(String text) {
        Map<String, Integer> counter = new HashMap<>();
        String[] letters = text.split("");
        for (String letter : letters) {
            counter.merge(
                letter.toLowerCase(),
                1,
                Integer::sum
            );
        }

        return (int) counter.entrySet().stream()
            .filter(stringIntegerEntry -> stringIntegerEntry.getValue() > 1)
            .count();
    }

    public static int duplicateCount2(String text) {
        return (int) Arrays.stream(text.split(""))
            .map(String::toLowerCase)
            .collect(Collectors.toMap(Function.identity(), s -> 1, Integer::sum))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .count();
    }
}
