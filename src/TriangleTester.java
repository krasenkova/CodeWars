public class TriangleTester {
    /*
   Любая сторона треугольника меньше суммы двух других сторон и больше
      их разности ( a < b + c,  a > b – c;  b < a + c,  b > a – c;  c < a + b,  c > a – b ).
     */
    public static boolean isTriangle(int a, int b, int c) {
        return a < (b + c) && a > (b - c)
            && b < (a + c) && b > (a - c)
            && c < (a + b) && c > (a - b);
    }
}
