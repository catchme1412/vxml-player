package com.vxml.browser.runtime;

import java.util.Collection;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.RecursiveTask;

public class Producer extends RecursiveTask<Collection<Event>> {

    private static final long serialVersionUID = 1L;
    
    private Queue<Event> eventQueue;

    public Producer(Queue<Event> outputQueue) {
        this.eventQueue = outputQueue;
    }

    @Override
    protected Collection<Event> compute() {
        while (!isDone()) {
            System.out.println("Producer");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNext()) {
                eventQueue.add(new Event());
            }
        }
        return null;
    }

}
