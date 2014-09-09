package com.vxml.tag;

import java.util.Scanner;

public class DtmfInput {

    public String read() {
        System.out.print("Input>");
        Scanner in = new Scanner(System.in);
        String value = in.next();
        return value;
    }
}
