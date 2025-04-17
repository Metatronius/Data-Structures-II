package algs13;

import stdlib.StdOut;
import stdlib.Trace;

/**
 * MyListAccessor (debugging).
 * <p>
 * This homework is intended to give you hands-on experience debugging and testing code. The intent is to give
 * you more practice with linked lists and develop your intuition about how linked lists work.
 * <p>
 * Instructions:
 * <p>
 * 1. Make sure you have watched last week's lecture. Read over the main method and the test methods to develop an understanding
 * of what they are doing.
 * <p>
 * 2. Look for the TODO in the code below.
 * <p>
 * 3. Utilize the techniques presented (your choice of Inspection, print statements, trace on paper, Trace utility,
 * or IntelliJ Interactive debugger) to find and fix the bugs.
 * <p>
 * This is a "written homework" assignment. You do NOT need to submit this file with solutions as part of this homework.
 * You DO need to submit your answers to the questions posed below.
 */
public class MyListAccessor {

    private Node first;
    private int N;

    private static class Node {
        final double item;
        Node next;

        Node(double item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public int numFives1() {
        int result = 0;
        for (Node x = first; x != null; x = x.next)
            if (x.item == 5.0)
                result = result + 1;
        return result;
    }

    public int numFives2() {
        Node x = first;
        int result = 0;
        while (x != null) {
            if (x.item == 5.0)
                result = result + 1;
            x = x.next;
        }
        return result;
    }

    public int numFives3() {
        Node x = first;
        int result = 0;
        while (x != null) {
            if (x.item == 5.0)
                result = result + 1;
            x = x.next.next;
        }
        return result;
    }

    public int numFives4() {
        if (first == null) return 0;
        int result = 0;
        if (first.item == 5.0)
            result = result + 1;
        Node x = first;
        while (x.next != null) {
            if (x.next.item == 5.0)
                result = result + 1;
            x.next = x.next.next;
        }
        return result;
    }

    public int numFives5() {
        int result = 0;
        for (Node x = first; x.next != null && x.next.item == 5.0; x = x.next) {
            result += 1;
        }
        return result;
    }

    public int numFives6() {
        if (first == null) return 0;
        int result = 0;
        if (first.item == 5.0)
            result = result + 1;
        Node x = first;
        while (x.next != null) {
            if (x.item == 5.0)
                result = result + 1;
            x = x.next;
        }
        return result;
    }

    public int numFives7() {
        if (first.next == null) return 0;
        Node x = first;
        int result = 0;
        while (x.next != null) {
            if (x.item != 5.0) x = x.next;
            if (x.item == 5.0) {
                result = result + 1;
                x = x.next;
            }
        }
        if (x.next == null && x.item == 5.0) {
            result = result + 1;
        }
        return result;
    }

    public int numFives8() {
        if (first == null) return 0;
        Node x = first;
        Node y = first.next;
        int result = 0;
        while (x != null) {
            if (x.item == 5.0) result = result + 1;
            x = y;
        }
        return result;
    }

    public int numFives9() {
        int result = 0;
        if (first.item == 5.0)
            result = result + 1;
        Node x = first;
        while (x.next != null) {
            if (x.next.item == 5.0)
                result = result + 1;
            x = x.next;
        }
        return result;
    }

    public int numFives10() {
        int result = 0;
        for (Node x = first; x != null; x = x.next) {
            if (x.item == 5.0)
                result = result + 1;
            x = x.next;
        }
        return result;
    }

    /**
     * TODO: Change this to invoke each method, numFives1, numFives2, ... numFives10,
     * in turn. You can comment out the first line and uncomment the second line.
     * <p>
     * For each version of the method, tell me:
     * <p>
     * a. Whether there is a bug or not. (Note: There are no compiler errors in this file).
     * b. What's wrong with the method and how to fix it.
     * <p>
     * Fix the code for each method to make it work. Some of the implementations may end up
     * looking the same, or very similar.
     * <p>
     * Write your answers the the questions about each implementation of numFives on paper,
     * or put them in a Word document. Upload a scan of your handwritten answers or your
     * Word document to D2L. Do not submit image files directly, although you can paste
     * photographs of your handwritten answers into the Word document. A better alternative
     * would be to use a scanning app on your phone, and submit a PDF of your handwritten answers.
     * <p>
     * You do not need to hand in this file.
     */

    public static int numFives(MyListAccessor list) {
        return list.numFives1();
        // return list.numFives2();
        // return list.numFives3();
        // return list.numFives4();
        // return list.numFives5();
        // return list.numFives6();
        // return list.numFives7();
        // return list.numFives8();
        // return list.numFives9();
        // return list.numFives10();
    }

    /* A main function for testing */
    public static void main(String[] args) {

        // TODO: If you want, you can uncomment this line of code and use Trace for visualizing
        //       the execution of the program.
        // Trace.run();
        Trace.drawStepsOfMethod("numFives1");
        Trace.drawStepsOfMethod("numFives2");
        Trace.drawStepsOfMethod("numFives3");
        Trace.drawStepsOfMethod("numFives4");
        Trace.drawStepsOfMethod("numFives5");
        Trace.drawStepsOfMethod("numFives6");
        Trace.drawStepsOfMethod("numFives7");
        Trace.drawStepsOfMethod("numFives8");
        Trace.drawStepsOfMethod("numFives9");
        Trace.drawStepsOfMethod("numFives10");

        testNumFives(3, listOf(11, 21, 5, 31, 5, 41, 5, 51));
        testNumFives(4, listOf(11, 21, 5, 31, 5, 5, 41, 5, 51));
        testNumFives(4, listOf(11, 21, 5, 5, 5, 31, 41, 5, 51));
        testNumFives(0, listOf(11, 21, 31, 41));
        testNumFives(1, listOf(11, 21, 5, 31, 41));
        testNumFives(1, listOf(11, 21, 31, 41, 5));
        testNumFives(1, listOf(5, 11, 21, 31, 41));
        testNumFives(0, listOf(11));
        testNumFives(1, listOf(5));
        testNumFives(3, listOf(5, 5, 5));
        testNumFives(0, listOf());
        StdOut.println("Finished tests");
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        Node x = first;
        b.append("[ ");
        while (x != null) {
            if (x != first) b.append(", ");
            b.append(x.item);
            x = x.next;
        }
        b.append(" ]");
        return b.toString();
    }

    private static MyListAccessor listOf(double... items) {
        Node next = null;
        for (int i = items.length - 1; i >= 0; i--) {
            next = new Node(items[i], next);
        }
        MyListAccessor list = new MyListAccessor();
        list.first = next;
        list.N = items.length;
        return list;
    }

    /* This is a test function */
    private static void testNumFives(int expected, MyListAccessor list) {
        int actual = numFives(list);
        if (expected != actual) {
            StdOut.format("Failed: Expecting [%d] Actual [%d] with argument %s\n", expected, actual, list.toString());
        }
        checkInvariants(list);
    }

    private static void showError(String message) {
        Trace.draw();
        StdOut.println(message);
        throw new Error(); // stops execution
    }

    private static void checkInvariants(MyListAccessor that) {
        int N = that.N;
        Node first = that.first;
        if (N < 0) throw new Error();
        if (N == 0) {
            if (first != null) {
                showError("N==0, Expected first == null.");
            }
        } else {
            if (first == null) {
                showError("N!=0, Expected first != null.");
            }
        }
        if (N > 0) {
            Node current = first;
            for (int i = 0; i < N; i++) {
                if (current == null) {
                    showError(String.format("N==%d, Expected %d next nodes, but got fewer.", N, N));
                    return;
                }
                current = current.next;
            }
            if (current != null) {
                showError(String.format("N==%d, Expected %d next nodes, but got more.", N, N));
            }
        }
    }
}