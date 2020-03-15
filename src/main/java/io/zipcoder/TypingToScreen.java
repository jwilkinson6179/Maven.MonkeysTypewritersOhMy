package io.zipcoder;

public class TypingToScreen implements Runnable
{
    String message;

    public TypingToScreen(String input)
    {
        message = input;
    }

    @Override
    public void run()
    {
        for(Integer i = 0; i < message.length(); i++)
        {
            Character letter = message.charAt(i);
            System.out.print(letter);

            try
            {
                Thread.sleep(150);
            } catch(InterruptedException e)
            {
                System.out.println("SOMETHING WOKE ME UP");
            }
        }
    }
}
