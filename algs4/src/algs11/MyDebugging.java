package algs11;

//import java.util.Arrays;
//
//import stdlib.*;
//
///**
// * Part 1: MyDebugging (Version 2)
// *
// * This homework is intended to give you hands-on experience debugging and testing code. The intent is to give
// * you more practice with loops and develop your intuition about how Java works. Making sure you understand this
// * material thoroughly should set you up for success for the rest of the course.
// *
// * Remember, for the exam, you must be comfortable actually writing Java code on paper. To that end, I suggest that
// * if you're having trouble with Java and loops, practice. After you've fixed one or two of the methods below, write
// * it out on paper. Keep practicing until you can implement numFives on paper from scratch without looking at the code.
// * You can test out your on-paper coding ability by typing in an implementation from paper and running the tests to
// * see if they pass.
// *
// * Instructions:
// *
// * 1. Make sure you have watched last week's lecture. Read over the main method and the test methods to develop an understanding
// *    of what they are doing.
// *
// * 2. Look for the TODO in the code below.
// *
// * 3. Utilize the techniques presented (your choice of Inspection, print statements, trace on paper, Trace program,
// *    or IntelliJ Interactive debugger) to find and fix the bugs.
// */
//public class MyDebugging {
//
//    /* A main function for testing */
//    public static void main(String[] args) {
//        testNumFives(3, new double[]{11, 21, 5, 31, 5, 41, 5, 51});
//        testNumFives(4, new double[]{11, 21, 5, 31, 5, 5, 41, 5, 51});
//        testNumFives(4, new double[]{11, 21, 5, 5, 5, 31, 41, 5, 51});
//        testNumFives(0, new double[]{11, 21, 31, 41});
//        testNumFives(1, new double[]{11, 21, 5, 31, 41});
//        testNumFives(1, new double[]{11, 21, 31, 41, 5});
//        testNumFives(1, new double[]{5, 11, 21, 31, 41});
//        testNumFives(0, new double[]{11});
//        testNumFives(1, new double[]{5});
//        testNumFives(3, new double[]{5, 5, 5});
//        testNumFives(0, new double[]{});
//        StdOut.println("Finished tests");
//    }
//
//    /* This is a test function */
//    public static void testNumFives(int expected, double[] list) {
//        int actual = numFives(list);
//        if (expected != actual) {
//            StdOut.format("Failed: Expecting [%d] Actual [%d] with argument %s\n", expected, actual, Arrays.toString(list));
//        }
//    }
//
//    /**
//     * TODO: Change this to invoke each method, numFives1, numFives2, ... numFives10,
//     * in turn. You can comment out the first line and uncomment the second line.
//     *
//     * For each version of the method, tell me:
//     *
//     *   a. Whether there is a compiler error or a runtime bug, or both
//     *   b. All the things that are wrong with the method, and the changes you would need to make to get it working.
//     *
//     * Fix the method to make it compile if it does not, and you may as well fix the code.
//     * Some of the implementations may end up looking the same, or very similar.
//     *
//     * Write your answers on paper, or put them in a Word document. Upload a scan of your
//     * handwritten answers or your Word document to D2L. DO NOT TAKE A PICTURE WITH YOUR PHONE.
//     * You do not need to hand in this file!
//     */
//
//    public static int numFives(double[] list) {
//        return numFives1(list);
//        // return numFives2(list);
//        // return numFives3(list);
//        // return numFives4(list);
//        // return numFives5(list);
//        // return numFives6(list);
//        // return numFives7(list);
//        // return numFives8(list);
//        // return numFives9(list);
//        // return numFives10(list);
//    }
//    /* Return number of times 5.0 occurs in the list */
//    public static int numFives1(double[] a) {
//        int result = 0;
//        for (int i = 0; i < a.length; i++)
//            if (a == 5.0)
//                result++;
//        return result;
//    }
//
//    /* Return number of times 5.0 occurs in the list */
//    public static int numFives2(double[] a) {
//        int result = 0;
//        for (int i = 0; i < a.length; i++) {
//            if (a[i] == 5.0)
//                result++;
//            else
//                return result;
//            return result;
//        }
//        StdOut.print(result);
//    }
//
//    /* Return number of times 5.0 occurs in the list */
//    public static int numFives3(double[] a) {
//        double[] list = new double[]{4, 5, 6, 5, 3};
//        int result = 0;
//        for (int i = 0; i < list.length; i++)
//            if (list[i] == 5.0)
//                result++;
//        return result;
//    }
//
//    /* Return number of times 5.0 occurs in the list */
//    public static int numFives4(double[] a) {
//        int result = 0;
//        for (int i = 0; i < a.length; i++)
//            if (a[i] == 5.0)
//                result++;
//            else
//                i++;
//        return result;
//    }
//
//    /* Return number of times 5.0 occurs in the list */
//    public static int numFives5(double[] a) {
//        int result = 0;
//        for (int i = 0; i < a.length; i++)
//            if (i == 5.0)
//                result++;
//        return result;
//    }
//
//    /* Return number of times 5.0 occurs in the list */
//    public static int numFives6(double[] a) {
//        int result = 0;
//        for (int i = 0; 0 < a.length; i++)
//            if (a[i] == 5.0)
//                result++;
//        return result;
//    }
//
//    /* Return number of times 5.0 occurs in the list */
//    public static int numFives7(double[] a) {
//        int result = 0;
//        for (int i = 0; i > 0; i++)
//            if (a[i] == 5.0)
//                result++;
//        return result;
//    }
//
//    /* Return number of times 5.0 occurs in the list */
//    public static int numFives8(double[] a) {
//        int result = 0;
//        int i = 0;
//        while (i < a.length) {
//            if (a[i] == 5.0) {
//                result++;
//                i++;
//            } else {
//                i++;
//            }
//        }
//        return result;
//    }
//
//    /* Return number of times 5.0 occurs in the list */
//    public static int numFives9(double[] a) {
//        int result = 0;
//        int i = 0;
//        while (i < a.length) {
//            if (a[i] == 5.0) {
//                result++;
//                i++;
//            }
//            if (a[i] != 5.0)
//                i++;
//        }
//        return result;
//    }
//
//    /* Return number of times 5.0 occurs in the list */
//    public static int numFives10(double[] a) {
//        int result = 0;
//        for (int i = 0; i < a.length; i++)
//            if (a[i] == 5.0)
//                result++;
//        return result;
//    }
//}