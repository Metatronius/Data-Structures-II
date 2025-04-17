package algs13;

import stdlib.StdOut;

import java.text.DecimalFormat;

public class MyListMutator {
    private Node first;
    static class Node {
        public Node (double item, Node next) { this.item = item; this.next = next; }
        public double item;
        public Node next;
    }

    public void insert1(double item) {
        Node x = first;
        while(x.next != null && x.next.item < item) {
            x = x.next;
        }
        x.next = new Node(item, x.next);
    }

    public void insert2(double item) {
        if(first == null || first.item >= item) {
            first = new Node(item, first);
            return;
        }
        Node x = first;
        if(x.next == null || x.next.item >= item) {
            x.next = new Node(item, x.next);
        }
    }

    public void insert3(double item) {
        if(first == null || first.item >= item) {
            new Node(item, first);
        }
        else {
            Node x = first;
            while(x.next != null && x.next.item <= item) {
                x = x.next;
            }
            x.next = new Node(item, x.next);
        }
    }

    public void insert4(double item) {
        if(first == null || first.item >= item) {
            first = new Node(item, first);
        }
        else {
            Node x = first;
            while(x.next != null && x.next.item < item) {
                x = x.next;
            }
            new Node(item, x.next);
        }
    }

    public void insert5(double item) {
        Node x = first;
        while(x != null && x.item < item) {
            x = x.next;
        }
        if(x != null) {
            x.next = new Node(item, x);
        }
    }

    public void insert6(double item) {
        Node x = first;
        while(x != null && x.next.item < item) {
            x = x.next;
        }
        if(x != null) {
            new Node(item, x.next);
        }
    }

    public void insert7(double item) {
        Node x = first;
        while(x != null && x.next.item <= item) {
            x = x.next;
        }
        if(x != null) {
            x.next = new Node(item, x.next);
        }
    }

    public void insert(double item) {
        insert1(item);
        // insert2(item);
        // insert3(item);
        // insert4(item);
        // insert5(item);
        // insert6(item);
        //insert7(item);
    }

    /* ToString method to print */
    public String toString () {
        // Use DecimalFormat #.### rather than String.format 0.3f to leave off trailing zeroes
        DecimalFormat format = new DecimalFormat ("#.###");
        StringBuilder result = new StringBuilder ("[ ");
        for (Node x = first; x != null; x = x.next) {
            result.append (format.format (x.item));
            result.append (" ");
        }
        result.append ("]");
        return result.toString ();
    }
    /* Method to create lists */
    public static MyListMutator from(String s) {
        MyListMutator result = new MyListMutator();
        if ("".equals (s)) return result;

        Node first = null;
        String[] nums = s.split (" ");
        for (int i = nums.length-1; i >= 0; i--) {
            try {
                double num = Double.parseDouble (nums[i]);
                first = new Node (num, first);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException (String.format ("Bad argument \"%s\": could not parse \"%s\" as a double", s, nums[i]));
            }
        }
        result.first = first;
        return result;
    }

    private static void testInsert (String expected, String list, double item) {
        MyListMutator aList = MyListMutator.from (list);
        aList.insert (item);
        String actual = aList.toString ();
        if (! expected.equals (actual)) {
            StdOut.format ("Failed [%s].insert(%f): Expecting (%s) Actual (%s)\n", list, item, expected, actual);
        }
    }
    public static void main(String[] args) {
        testInsert ("[ 11 21 31 41 51 ]", "11 21 41 51", 31);
        testInsert ("[ 11 21 31 41 51 ]", "11 31 41 51", 21);
        testInsert ("[ 11 21 31 41 51 ]", "11 21 31 51", 41);
        testInsert ("[ 11 21 31 41 51 ]", "11 21 31 41", 51);
        testInsert ("[ 11 ]", "", 11);
        testInsert ("[ 11 21 31 41 51 ]", "21 31 41 51", 11);
        StdOut.println ("Finished tests");
    }
}