import java.util.stream.Stream;

public class Arge {

    public static int nbYear(int p0, double percent, int aug, int p) {
        long count = Stream.iterate((double) p0, population -> population + population * percent / 100 + aug)
            .takeWhile(population -> population < p)
            .count();
        return (int) count;
    }

    public static int nbYear_(int p0, double percent, int aug, int p) {
        int years = 0;
        double population = p0;
        while (population < p) {
            population = population + population * percent / 100 + aug;
            years++;
        }
        return years;
    }
}