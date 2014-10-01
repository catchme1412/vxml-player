package com.vxml.browser.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RecursiveAction;

public class EventHandler extends RecursiveAction {

    private Queue<Event> eventQueue;
    private List<EventListener> listeners;
    private Producer producer;
    private Consumer consumer;

    public EventHandler() {
        eventQueue = new LinkedBlockingDeque<Event>();
        listeners = new ArrayList<EventListener>();

    }

    public void add(Event outputEvent) {
        eventQueue.add(outputEvent);
        for (EventListener listener : listeners) {
            listener.onEvent(outputEvent);
        }
    }

    public void listen(EventListener listener) {
        listeners.add(listener);
    }

    @Override
    protected void compute() {
        System.out.println("Start");
        producer = new Producer(eventQueue);
        consumer = new Consumer(eventQueue);
        consumer.fork();
        producer.fork();
        try {
            producer.get();
            consumer.get();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        EventHandler job = new EventHandler();
        forkJoinPool.invoke(job);
        job.join();
    }

}
