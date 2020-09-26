import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;


public class ConwayLife {

    private static class Coords {
        int h;
        int w;

        Coords(int h, int w) {
            this.h = h;
            this.w = w;
        }

        Stream<Coords> getNeighbours() {
            return Stream.of(
                new Coords(h - 1, w - 1),
                new Coords(h - 1, w),
                new Coords(h - 1, w + 1),
                new Coords(h, w - 1),
                new Coords(h, w + 1),
                new Coords(h + 1, w + 1),
                new Coords(h + 1, w),
                new Coords(h + 1, w - 1)
            );
        }

        long countLiveNeighbours(Set<Coords> liveCells) {
            return getNeighbours()
                .filter(liveCells::contains)
                .count();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coords coords = (Coords) o;
            return h == coords.h &&
                w == coords.w;
        }

        @Override
        public int hashCode() {
            return Objects.hash(h, w);
        }

        @Override
        public String toString() {
            return "Coords{" +
                "h=" + h +
                ", w=" + w +
                '}';
        }
    }

    public static int[][] getGeneration(int[][] cells, int generations) {
        Set<Coords> liveCells = convertToSet(cells);
        for (int i = 0; i < generations; i++) {
            doGeneration(liveCells);
        }
        return convertToMatrix(liveCells);
    }

    private static Set<Coords> convertToSet(int[][] cells) {
        Set<Coords> liveCells = new HashSet<>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == 1) {
                    liveCells.add(new Coords(i, j));
                }
            }
        }
        return liveCells;
    }

    private static void doGeneration(Set<Coords> liveCells) {
        Set<Coords> makeDead = new HashSet<>();
        Set<Coords> makeAlive = new HashSet<>();

        for (Coords cell : liveCells) {
            long liveNeighbours = cell.countLiveNeighbours(liveCells);
            if (liveNeighbours < 2 || liveNeighbours > 3) {
                makeDead.add(cell);
            }
            cell.getNeighbours()
                .filter(neighbour -> !makeAlive.contains(neighbour))
                .filter(neighbour -> neighbour.countLiveNeighbours(liveCells) == 3)
                .forEach(makeAlive::add);
        }

        liveCells.removeAll(makeDead);
        liveCells.addAll(makeAlive);
    }

    private static int[][] convertToMatrix(Set<Coords> liveCells) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Coords coords : liveCells) {
            if (coords.h < minX) minX = coords.h;
            if (coords.h > maxX) maxX = coords.h;
            if (coords.w < minY) minY = coords.w;
            if (coords.w > maxY) maxY = coords.w;
        }
        int width = maxX - minX + 1;
        int height = maxY - minY + 1;
        int[][] result = new int[width][height];
        for (Coords coords : liveCells) {
            result[coords.h - minX][coords.w - minY] = 1;
        }
        return result;
    }

    @Test
    public void testGlider() {
        int[][][] gliders = {
            {
                {1, 0, 0},
                {0, 1, 1},
                {1, 1, 0}
            },
            {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1}
            }
        };
        System.out.println("Input:");
        LifeDebug.print(gliders[0]);
        int[][] res = ConwayLife.getGeneration(gliders[0], 1);
        assertTrue(
            String.format("Got %s instead of %s", LifeDebug.htmlize(res), LifeDebug.htmlize(gliders[1])),
            LifeDebug.equals(res, gliders[1])
        );
    }

    static class LifeDebug {
        static void print(int[][] set) {
            System.out.println(htmlize(set));
        }

        static String htmlize(int[][] set) {
            if (set == null) {
                return "[]";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("  ");
            for (int i = 0; i < set[0].length; i++) {
                sb.append(" ").append(i).append(":");
            }
            sb.append("\n");
            for (int i = 0; i < set.length; i++) {
                sb.append(i).append(":");
                for (int j = 0; j < set[0].length; j++) {
                    sb.append(" ").append(set[i][j]).append(" ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        static boolean equals(int[][] actual, int[][] expected) {
            return Arrays.deepEquals(actual, expected);
        }
    }
}