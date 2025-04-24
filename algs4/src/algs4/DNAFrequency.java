package algs4;

import stdlib.StdIn;
import stdlib.StdOut;

import java.util.Arrays;

public class DNAFrequency
{
    public static void main(String[] args)
    {
        try
        {
            stdlib.StdIn.fromFile("algs4/data/largesequences.txt");
        } catch (Error e1)
        {
            try
            {
                stdlib.StdIn.fromFile("data/largesequences.txt");
            } catch (Error e2)
            {
                stdlib.StdOut.println("Could not find the input file in either path.");
                return;
            }
        }
        while (StdIn.hasNextLine())
        {
            String line = StdIn.readLine();
            String[] parsed = line.split("\t");

            if (parsed.length < 2) continue;

            String species = parsed[0];
            String sequence = parsed[1];

            SymbolTable<Character, Integer> table = new SymbolTable<>();

            for (int i = 0; i < sequence.length(); i++)
            {
                char base = sequence.charAt(i);
                Integer count = table.get(base);
                if (count == null)
                {
                    table.put(base, 1);
                }
                else
                {
                    table.put(base, count + 1);
                }
            }

            int length = sequence.length();
            StdOut.printf("%s: ", species);
            char[] key = {'A', 'C', 'G', 'T'};
            for (char symbol : key)
            {
                int count = table.get(symbol) != null ? table.get(symbol) : 0;
                double percentage = (double) count / length * 100;
                StdOut.printf("%c = %.2f%% ", symbol, percentage);
            }
            StdOut.println();
        }
    }

    public static class SymbolTable<Key, Value>
    {
        private static final int INIT_CAPACITY = 10;
        private Key[] keys;
        private Value[] vals;
        private int n = 0;

        public SymbolTable()
        {
            keys = (Key[]) new Object[INIT_CAPACITY];
            vals = (Value[]) new Object[INIT_CAPACITY];
        }

        public int size()
        {
            return n;
        }

        public boolean isEmpty()
        {
            return size() == 0;
        }

        public boolean contains(Key key)
        {
            return get(key) != null;
        }

        public Value get(Key key)
        {
            for (int i = 0; i < n; i++)
            {
                if (keys[i].equals(key))
                {
                    return vals[i];
                }
            }
            return null;
        }

        public void put(Key key, Value val)
        {
            for (int i = 0; i < n; i++)
            {
                if (keys[i].equals(key))
                {
                    vals[i] = val;
                    return;
                }
            }

            if (n == keys.length) resize(2 * keys.length);

            keys[n] = key;
            vals[n] = val;
            n++;
        }

        private void resize(int capacity)
        {
            Key[] tempKeys = (Key[]) new Object[capacity];
            Value[] tempVals = (Value[]) new Object[capacity];
            for (int i = 0; i < n; i++)
            {
                tempKeys[i] = keys[i];
                tempVals[i] = vals[i];
            }
            keys = tempKeys;
            vals = tempVals;
        }

        public Iterable<Key> keys()
        {
            java.util.ArrayList<Key> list = new java.util.ArrayList<>();
            list.addAll(Arrays.asList(keys).subList(0, n));
            return list;
        }
    }
}
