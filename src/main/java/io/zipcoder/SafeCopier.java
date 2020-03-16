package io.zipcoder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import static java.util.logging.Level.*;

/**
 * Make this extend the Copier like `UnsafeCopier`, except use locks to make sure that the actual intro gets printed
 * correctly every time.  Make the run method thread safe.
 */
public class SafeCopier extends Copier
{
    private static final Logger LOGGER = Logger.getLogger(SafeCopier.class.getName());
    ReentrantLock lock;
    public SafeCopier(String toCopy)
    {
        super(toCopy);
        lock = new ReentrantLock();
    }

    public void run()
    {
        Boolean gotTheLock = null;
        try
        {
            gotTheLock = lock.tryLock(1, TimeUnit.SECONDS);
            LOGGER.log(INFO, String.format("%s has the lock.", Thread.currentThread().getName()));
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        try
        {
            while (gotTheLock && stringIterator.hasNext())
            {
                String word = stringIterator.next();
                copied += word + " ";
            }
        }
        finally
        {
            LOGGER.log(INFO, String.format("%s gives up the lock.", Thread.currentThread().getName()));
            lock.unlock();
        }
    }
}
