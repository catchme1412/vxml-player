package com.vxml.dtmf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DtmfInput {

    private String input;

    public String read() {
        String value = null;
        System.out.print("Input>");
        try {
            Scanner in = new Scanner(System.in);
            if (in.hasNext()) {
                value = in.next();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public String readWithTimeOut(int timeout) {
        System.out.print("Input(wait " + (timeout / 1000) + " sec)>");
        ReadThread myThread = new ReadThread();
        myThread.start();
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            // Do nothing
        }

        myThread.interrupt();
        return input;
    }

    private class ReadThread extends Thread {
        @Override
        public void run() {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

            while (!isInterrupted()) {
                try {
                    if (stdin.ready()) {
                        input = stdin.readLine();
                        System.out.println("Got: " + input);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                }
            }
            if (input == null) {
                System.out.println("Aborted.");
            } else {
                System.out.println("Got: " + input);
            }
        }
    }
}
