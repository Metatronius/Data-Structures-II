package algs4;

import algs31.BinarySearchST;
import algs32.BST;
import stdlib.StdIn;
import stdlib.Stopwatch;

public class FillAndSearchTime
{


    public static void main(String[] args)
    {

        //Read the words in the text file into a String array
        String fileName = args.length > 1 ? args[0] : "algs4/data/tale.txt";
        try
        {
            StdIn.fromFile(fileName);
            System.out.print("File opened successfully: ");
            System.out.println(fileName);
        } catch (Exception e)
        {
            System.out.print("Error reading the file: ");
            System.out.println(fileName);
            return;
        }

        String[] words = StdIn.readAllStrings();
        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<>();
        BST<String, Integer> bst = new BST<>();
        //fill one symbol table declared as algs4.BinarySearchST
        Stopwatch timer1 = new Stopwatch();
        for (String word : words)
        {
            if (binarySearchST.contains(word))
            {
                binarySearchST.put(word, binarySearchST.get(word) + 1);
            }
            else
            {
                binarySearchST.put(word, 1);
            }
        }
        double binaryInsertTime = timer1.elapsedTime();

        //and one declared as myalgs4.BSTEssential
        Stopwatch timer2 = new Stopwatch();
        for (String word : words)
        {
            if (bst.contains(word))
            {
                bst.put(word, bst.get(word) + 1);
            }
            else
            {
                bst.put(word, 1);
            }
        }
        double bstInsertTime = timer2.elapsedTime();

        //In two more separate loops, perform a get method call to each symbol table on
        //each word in the input file. Time those loops as well.

        //BinarySearchST
        Stopwatch timer3 = new Stopwatch();
        for (String word : words)
        {
            binarySearchST.get(word);
        }
        double binarySearchTime = timer3.elapsedTime();
        //BST
        Stopwatch timer4 = new Stopwatch();
        for (String word : words)
        {
            bst.get(word);
        }
        double bstSearchTime = timer4.elapsedTime();

        //At the end of the program, compute and print the total time the loops working
        //the BinarySearchST implementation took and the total time the loops with the
        //BST implementation took.
        double totalBinaryTime = binaryInsertTime + binarySearchTime;
        double totalBSTTime = bstInsertTime + bstSearchTime;

        System.out.println("\nTiming Results:");
        System.out.printf("BinarySearchST:\tInsert Time = %.4f s, Search Time = %.4f s, Total = %.4f s\n",
                binaryInsertTime, binarySearchTime, totalBinaryTime);
        System.out.printf("BST:\t\t\tInsert Time = %.4f s, Search Time = %.4f s, Total = %.4f s\n",
                bstInsertTime, bstSearchTime, totalBSTTime);
    }

}
