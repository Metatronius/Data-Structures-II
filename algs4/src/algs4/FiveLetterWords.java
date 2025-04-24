package algs4;

import stdlib.StdIn;

import java.util.*;

public class FiveLetterWords
{

    public static void main(String[] args)
    {
        // 1. Print your name followed by three asterisks
        System.out.println("Paul Lederer***");

        // 2. Read the words in the text file into a String array
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

        List<String> words = new ArrayList<>();

        while (StdIn.hasNextLine())
        {
            String line = StdIn.readLine().trim();
            String[] lineWords = line.split("\\s+");
            Collections.addAll(words, lineWords);
        }
        String[] wordArray = words.toArray(new String[0]);

        // 3. Print the number of words in the file
        System.out.println("Number of words: ");
        System.out.println(wordArray.length);

        // 4. Count and print the number of five-letter words
        int fiveLetterCount = countFiveLetterWords(wordArray);
        System.out.print("Number of five-letter words: ");
        System.out.println(fiveLetterCount);

        // 5. Print all five-letter words starting with a vowel
        System.out.print("Five-letter words starting with a vowel:");
        int count = 0;
        for (String word : wordArray)
        {
            if (word.length() == 5 && isVowel(word.charAt(0)))
            {
                char separator = count % 20 == 0 ? '\n' : '\t';
                System.out.print(separator);
                System.out.print(word);
                ++count;
            }
        }
        System.out.print("\nNumber of five letter words that start with a vowel: ");
        System.out.println(count);

        // 6. Find and print the alphabetically first and last five-letter words
        String[] fiveLetterWords = getFiveLetterWords(wordArray);
        if (fiveLetterWords.length > 0)
        {
            Arrays.sort(fiveLetterWords);
            System.out.print("Alphabetically first five-letter word: ");
            System.out.println(fiveLetterWords[0]);

            System.out.println("Alphabetically last five-letter word: ");
            System.out.println(fiveLetterWords[fiveLetterWords.length - 1]);
        }
        else
        {
            System.out.println("No five-letter words found.");
        }
    }

    // Helper functions
    public static int countFiveLetterWords(String[] words)
    {
        int count = 0;
        for (String word : words)
        {
            if (word.length() == 5)
            {
                count++;
            }
        }
        return count;
    }

    public static boolean isVowel(char c)
    {
        c = Character.toLowerCase(c);
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }

    public static String[] getFiveLetterWords(String[] words)
    {
        List<String> fiveLetterList = new ArrayList<>();
        for (String word : words)
        {
            if (word.length() == 5)
            {
                fiveLetterList.add(word);
            }
        }
        return fiveLetterList.toArray(new String[0]);
    }
}
