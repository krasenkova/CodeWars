public class Xbonacci {

//    public double[] tribonacci(double[] s, int n) {
//        if (n == 0) {
//            return new double[]{};
//        }
//        double[] result = new double[n];
//        for (int i = 0; i < n; i++) {
//            if (i < 3) {
//                result[i] = s[i];
//            } else {
//                result[i] = box.rotateRight();
//            }
//        }
//        return result;
//    }
//
//    double[] rotateRight(double[] box) {
//        double sum = box[0] + box[1] + box[2];
//        return new double[]{box[0], box[1], sum};
//    }
//
    public double[] tribonacci_v1(double[] s, int n) {
        if (n == 0) {
            return new double[]{};
        }
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            if (i < 3) {
                result[i] = s[i];
            } else {
                result[i] = result[i - 3] + result[i - 2] + result[i - 1];
            }
        }
        return result;
    }
}