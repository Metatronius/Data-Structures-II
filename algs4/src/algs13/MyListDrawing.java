package algs13;

// Part 3: MyListDrawing. Practice drawing lists.
//
// This is a "written homework" assignment. You do NOT need to submit this file with solutions as part of this homework.
// You DO need to submit your answers to the questions posed below.
//
// Look for the TODO below, in the main method. Hand in your drawings of the lists requested.

public class MyListDrawing {

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

    public void insert(double item) {
        if (first == null || first.item >= item) {
            first = new Node(item, first);
        } else {
            Node x = first;
            while (x.next != null && x.next.item < item) {
                x = x.next;
            }
            x.next = new Node(item, x.next);
        }
        N++;
    }

    public void delete (int k) {
        if (k < 0 || k >= N) throw new IllegalArgumentException ();
        if(k == 0) first = first.next;
        else {
            Node prev = first;
            for(int i = 1; i < k; i++) {
                prev = prev.next;
            }
            prev.next = prev.next.next;
        }
        N--;
    }

    public static void main(String[] args) {
        MyListDrawing list1 = listOf(4, 7, 9, 11);
        list1.insert(3);

        MyListDrawing list2 = listOf(4, 7, 9, 11);
        list2.insert(8);

        MyListDrawing list3 = listOf(4, 7, 9, 11);
        list3.insert(13);

        MyListDrawing list4 = listOf(4, 7, 9, 11);
        list4.delete(0);

        MyListDrawing list5 = listOf(4, 7, 9, 11);
        list5.insert(10);
        list5.insert(12);
        list5.delete(2);

        // TODO: Draw each list declared above, i.e. list1, list2, list3, list4, list5, before the main program exits.

        // NOTE: On an exam, you will obviously not have the Trace utility available to you. You need to be
        // able to look at code that operates on linked structures and draw the result. You should try
        // to understand how this code is manipulating the pointers in the list. You are welcome to test your understanding
        // with Trace, if you so choose, but do not rely on it to do the work for you - you need to develop your
        // understanding of linked structures!
    }

    private static MyListDrawing listOf(double... items) {
        Node next = null;
        for (int i = items.length - 1; i >= 0; i--) {
            next = new Node(items[i], next);
        }
        MyListDrawing list = new MyListDrawing();
        list.first = next;
        list.N = items.length;
        return list;
    }
}