package io.zipcoder;

import java.util.Random;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

/**
 * Modify the run function so that the monkeys each grab the next word and write it to the copy.
 */
public class UnsafeCopier extends Copier
{
    private static final Logger LOGGER = Logger.getLogger(UnsafeCopier.class.getName());
    Random rand;

    public UnsafeCopier(String toCopy)
    {
        super(toCopy);
        rand = new Random();
    }

    public void run()
    {
        StringBuilder wordAdder = new StringBuilder();
        wordAdder.append(copied);
        while(stringIterator.hasNext())
        {
            LOGGER.log(INFO, Thread.currentThread().getName() + " is reading.");
            String word = stringIterator.next();
            try {
                Thread.currentThread().sleep(5 + rand.nextInt(15));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            char[] wordAsChars = word.toCharArray();



            for(Character letter : wordAsChars)
            {
                wordAdder.append(letter);
                try {
                    Thread.currentThread().sleep(5 + rand.nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            copied = wordAdder.append(" ").toString();
//            copied += word + " ";
        }
    }
}