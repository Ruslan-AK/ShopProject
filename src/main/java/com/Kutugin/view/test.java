package com.Kutugin.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Long> longs = new ArrayList<>();
        longs.add(23L);
        longs.add(232L);
        longs.add(3L);
        System.out.println(listToString(longs));

    }

    private static String listToString(List<Long> input) {
        String s = "";
        int count = 0;
        for (Long l : input) {
            s += l;
            if (!(++count == input.size())) {
                s += ",";
            }
        }
        return s;
    }
}
