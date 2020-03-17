package io.zipcoder;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class MonkeyTypewriter {
    public static void main(String[] args) {
        String introduction = "It was the best of times,\n" +
                "it was the blurst of times,\n" +
                "it was the age of wisdom,\n" +
                "it was the age of foolishness,\n" +
                "it was the epoch of belief,\n" +
                "it was the epoch of incredulity,\n" +
                "it was the season of Light,\n" +
                "it was the season of Darkness,\n" +
                "it was the spring of hope,\n" +
                "it was the winter of despair,\n" +
                "we had everything before us,\n" +
                "we had nothing before us,\n" +
                "we were all going direct to Heaven,\n" +
                "we were all going direct the other way--\n" +
                "in short, the period was so far like the present period, that some of\n" +
                "its noisiest authorities insisted on its being received, for good or for\n" +
                "evil, in the superlative degree of comparison only.";

        // Do all of the Monkey / Thread building here
        // For each Copier(one safe and one unsafe), create and start 5 monkeys copying the introduction to
        // A Tale Of Two Cities.

        Thread monkey;
        Thread ape;
        UnsafeCopier copier = new UnsafeCopier(introduction);
        SafeCopier safeCopier = new SafeCopier(introduction);

        ArrayList<Thread> monkeyPool = new ArrayList<>();
        ArrayList<Thread> apePool = new ArrayList<>();
        for (Integer i = 0; i < 5; i++) {
            monkey = new Thread(copier, "Monkey #" + i);
            monkeyPool.add(monkey);
            ape = new Thread(safeCopier,  "Ape #" + i);
            apePool.add(ape);
        }
        for (Integer i = 0; i < 5; i++) {

            monkeyPool.get(i).start();
            apePool.get(i).start();
        }


        // This wait is here because main is still a thread and we want the main method to print the finished copies
        // after enough time has passed.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("MAIN INTERRUPTED");
        }

        // Print out the copied versions here.

        System.out.printf("Unsafe copy: ");
        String copiedText = copier.copied;
        String safeCopiedText = safeCopier.copied;

        if (!safeCopiedText.equals(introduction + " ")) {
            System.out.println("APE ERRORS");
            System.out.println(safeCopiedText);
        } else {
            System.out.println("Ape Success!");
//            System.out.println(safeCopiedText);
        }

        if (!copiedText.equals(introduction + " ")) {
            System.out.println("MONKEY ERRORS");
            System.out.println(copiedText);
        } else {
            System.out.println("Monkey Success!");
//            System.out.println(copiedText);
        }
    }
}