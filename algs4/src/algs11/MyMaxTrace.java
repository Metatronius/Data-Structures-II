package algs11;

import stdlib.StdOut;

// Homework 2, Parts 2 and 3 (Version 2)
//
// Part 1. See MyDebugging.java.
//
// Part 2.
// The motivation for this homework is to help you develop the mental model for how Java
// functions and loops execute.
//
// TODO: Hand-trace the examples given in the main program. There are three of them.
// You only need to trace the execution of the max method, from the beginning to the end.
// Be sure to show how each variable changes. You do not need to show line numbers
// in your traces.
//
// Scan your traces and submit them. Or you can do it in ASCII or a Word document.
//
// Part 3.
// Re-write the max function on paper, or in a Word document, using a for loop.
//
// TODO: Hand in your new max method with a for loop.
//
// Both your traces and your new max method should be in the same file as Part 1.
// Upload a scan of your handwritten answers or your Word document to D2L.
//
// DO NOT TAKE A PICTURE WITH YOUR PHONE.
//
// You do not need to hand in this file!
//

public class MyMaxTrace {
    public static Double max(double[] a) {
        if (a.length == 0) {
            throw new IllegalArgumentException();
        }

        // DO NOT change this to "double" with a lower-case "d."
        Double m = a[0];
        int i = 1;
        while (i < a.length) {
            if (m < a[i]) {
                m = a[i];
            }
            i += 1;
        }
        return m;
    }

    public static void testMax(double expected, double[] a) {
        Double actual = max(a);
        if (expected != actual) {
            StdOut.format("max failed: Expecting [%d] Actual [%d] with argument %s\n", expected, actual, java.util.Arrays.toString(a));
        }
    }

    public static void main(String[] args) {
        // Test 1
        testMax(31, new double[]{11, 21, 31});

        // Test 2
        testMax(31, new double[]{31, 11, 21});

        // Test 3
        testMax(81, new double[]{21, 31, 11, 71, 51, 81, 41});
    }
}
