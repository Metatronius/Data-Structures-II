package algs11;

import stdlib.StdOut;

public class MyRecursionTrace {

    public static int fib(int n) {
        if (n <= 1) return n;
        int nMinus1 = fib(n-1);
        int nMinus2 = fib(n-2);
        return nMinus1 + nMinus2;
    }

    public static int gcd(int p, int q) {
        StdOut.println("gcd(" + p + "," + q + ")");
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    public static void doSomething(int n) {
        if(n <= 1) {
            StdOut.print(n);
        } else if(n > 3) {
            StdOut.print(n);
            doSomething(n/2);
            StdOut.print(n);
        } else {
            StdOut.print(n);
            doSomething(n-1);
            doSomething(n-2);
            StdOut.print(n);
        }
    }

    public static void main(String[] args) {
        int f = fib(4);
        StdOut.format("f=%s\n", f);

        int g = gcd(9, 24);
        StdOut.format("g=%s\n", g);

        doSomething(7);
    }
}
