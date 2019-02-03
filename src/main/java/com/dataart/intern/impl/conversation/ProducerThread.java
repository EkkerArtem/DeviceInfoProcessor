package com.dataart.intern.impl.conversation;

import java.util.concurrent.CountDownLatch;

/**
 * Class that is creating producer in its own thread. It uses CountDownLatch to minimize the time between start of each producer.
 *
 * @see CountDownLatch
 * @see ConversationCreator#startMassaging()
 */
public class ProducerThread implements Runnable {
    private CountDownLatch latch;

    ProducerThread(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();          //The thread keeps waiting till it is informed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ConversationCreator.runProducer();
    }
}
