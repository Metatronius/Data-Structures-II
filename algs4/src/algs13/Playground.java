package algs13;
import java.text.DecimalFormat;
import stdlib.*;
public class Playground {
	private Node first;
	static class Node { 
		public double item; 
		public Node next; 
		public Node (double item, Node next) { 
			this.item = item; 
			this.next = next; 
		}
	}

	/* A silly main program to show list creation */
	public static void main (String[] args) {
		Trace.showObjectIdsRedundantly(true);
		Trace.drawStepsOfMethod ("main");
		//Trace.drawStepsOfMethod ("makeExample");
		//Trace.drawSteps();
		Trace.run ();
		Playground example1 = Playground.makeExample (1);
		Playground example2 = Playground.makeExample (5);
		Playground example3 = Playground.makeExample (7);
		Node x = example1.first;
		StdOut.println (x.item);
	}
	public static Playground makeExample(int i) {
		Node x1 = new Node (i+10, null);
		Node x2 = new Node (i+20, null);
		Node x3 = new Node (i+30, null);
		Node x4 = new Node (i+40, null);
		Playground result = new Playground ();
		result.first = x1;
		x1.next = x2;
		x2.next = x3;
		x3.next = x4;
		return result;
	}
	
	public static void main2 (String[] args) {
		//Trace.showObjectIdsRedundantly(true);
		Trace.drawStepsOfMethod ("numFives");
		Trace.drawStepsOfMethod ("numFivesH");
		Trace.run ();
		Playground list1 = Playground.from ("5 11 5 5");
		Playground list2 = Playground.from ("24 35 67");
		int result1 = list1.numFives ();
		Trace.draw();
		StdOut.println ("result: " + result1);
	}
	public int numFives () {
		return StdRandom.uniform (100); //TODO: fix this
	}
	public static void main3 (String[] args) {
		testNumFives (2, "11 5 5 21");
		testNumFives (0, "11 21 31 41");
		testNumFives (1, "11 21 5 31 41");
		testNumFives (1, "5 11 21 31 41");
		testNumFives (1, "11 21 31 41 5");
		testNumFives (5, "5 11 21 5 5 31 5 41 5");
		testNumFives (3, "5 5 5");
		testNumFives (1, "5");
		testNumFives (0, "11");
		testNumFives (0, "");
		StdOut.println ("Finished tests");
	}

	private static void testNumFives (int expected, String sList) {
		Playground list = Playground.from (sList); 
		String sStart = list.toString ();
		int actual = list.numFives ();
		if (expected != actual) {
			StdOut.format ("Failed: Expecting [%d] Actual [%d] with argument %s\n", expected, actual, list);
		}
		String sEnd = list.toString ();
		if (! sStart.equals (sEnd)) {
			StdOut.format ("Failed %s.numFives(): List changed to %s\n", sStart, sEnd);
		}
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
	public static Playground from(String s) {
		Playground result = new Playground ();
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
    private static Playground of(double... items) {
        Node next = null;
        for (int i = items.length - 1; i >= 0; i--) {
            next = new Node(items[i], next);
        }
        Playground result = new Playground();
        result.first = next;
        return result;
    }
}
