package com.vxml.browser.runtime;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.RecursiveTask;

public class Consumer extends RecursiveTask<Collection<Event>> {

    private static final long serialVersionUID = 1L;

    private Queue<Event> eventQueue;

    public Consumer(Queue<Event> outputQueue) {
        this.eventQueue = outputQueue;
    }

    protected Collection<Event> compute() {
        while (!isDone()) {
            if (!eventQueue.isEmpty()) {
                System.out.println("Consumer" + eventQueue.remove());
            }
        }
        return null;
    }
}
