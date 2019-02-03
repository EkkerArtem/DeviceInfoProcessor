package com.dataart.intern.impl.conversation;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class ProducerSpeedTest {

    @Test
    public void ProducerThreadTimeTest(){
        CountDownLatch latch = new CountDownLatch(1);
            ProducerThread producerThread = new ProducerThread(latch);
            Thread myThread = new Thread(producerThread);
            myThread.start();
            final int fiftySecondsInMills = 50000;
            final long startTime=System.currentTimeMillis();
            latch.countDown();

        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final long endTime=System.currentTimeMillis();

        Assert.assertEquals(startTime, endTime-fiftySecondsInMills,100);

    }
}