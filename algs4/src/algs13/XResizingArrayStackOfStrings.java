package algs13;
import stdlib.*;

public class XResizingArrayStackOfStrings {
	private String[] a;   // array of items
	private int N = 0;    // number of elements on stack
	// Invariant (after the constructor):
	//   a[0]..a[N-1] are non null
	//   a[N]..a[a.length-1] are null
	
	// create an empty stack
	public XResizingArrayStackOfStrings() {
		a = new String[N];
	}

	public boolean isEmpty() { return N == 0; }
	public int size()        { return N;      }

	// resize the underlying array holding the elements
	private void resize(int capacity) {
		String[] temp = new String[capacity];
		for (int i = 0; i < N; i++)
			temp[i] = a[i];
		a = temp;
	}

	// push a new item onto the stack
	public void push(String item) {
		if (N == a.length) resize(2*a.length);
		a[N] = item;
		N++;
	}

	// delete and return the item most recently added
	public String pop() {
		if (isEmpty()) { throw new Error("Stack underflow error"); }
		N--;
		String item = a[N];
		a[N] = null;  // to avoid loitering
		if (N > 0 && N == a.length/4) resize(a.length/2); // shrink size of array if necessary
		return item;
	}



	/* *********************************************************************
	 * Test routine.
	 **********************************************************************/
	public static void main(String[] args) {
		Trace.drawStepsOfMethod ("resize");
		Trace.run ();
		StdIn.fromString ("to be or not to - be - - that - - - is");

		XResizingArrayStackOfStrings s = new XResizingArrayStackOfStrings();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) s.push(item);
			else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
		}
		StdOut.println("(" + s.size() + " left on stack)");
	}
}
