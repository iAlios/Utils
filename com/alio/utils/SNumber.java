package com.alio.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SNumber {

    public static List<Integer> parseNumber(String sNum) {
        List<Integer> realNums = new ArrayList<>();
        String[] spls = sNum.split("[,，]");
        int rangeBegin = 0;
        int rangeEnd = 0;
        int step;
        for (String s : spls) {
            step = 1;
            if (s.matches("\\[([0-9\\-]+)\\][/]([0-9]+)")) {
                rangeBegin = Integer.valueOf(s.replaceAll("\\[([0-9]+)\\-([0-9]+)\\][/]([0-9]+)", "$1"));
                rangeEnd = Integer.valueOf(s.replaceAll("\\[([0-9]+)\\-([0-9]+)\\][/]([0-9]+)", "$2"));
                step = Integer.valueOf(s.replaceAll("\\[([0-9]+)\\-([0-9]+)\\][/]([0-9]+)", "$3"));
                for(int i = rangeBegin; i <= rangeEnd; i += step) {
                    realNums.add(i);
                }
            } else if (s.matches("([0-9]+)\\-([0-9]+)[/]([0-9]+)")) {
                rangeBegin = Integer.valueOf(s.replaceAll("([0-9]+)\\-([0-9]+)[/]([0-9]+)", "$1"));
                rangeEnd = Integer.valueOf(s.replaceAll("([0-9]+)\\-([0-9]+)[/]([0-9]+)", "$2"));
                step = Integer.valueOf(s.replaceAll("([0-9]+)\\-([0-9]+)[/]([0-9]+)", "$3"));
                for(int i = rangeBegin + step; i < rangeEnd; i += step) {
                    realNums.add(i);
                }
            } else if (s.matches("\\[([0-9]+)\\-([0-9]+)\\]")) {
                rangeBegin = Integer.valueOf(s.replaceAll("\\[([0-9]+)\\-([0-9]+)\\]", "$1"));
                rangeEnd = Integer.valueOf(s.replaceAll("\\[([0-9]+)\\-([0-9]+)\\]", "$2"));
                for(int i = rangeBegin; i <= rangeEnd; i += step) {
                    realNums.add(i);
                }
            } else if (s.matches("([0-9]+)\\-([0-9]+)")) {
                rangeBegin = Integer.valueOf(s.replaceAll("([0-9]+)\\-([0-9]+)", "$1"));
                rangeEnd = Integer.valueOf(s.replaceAll("([0-9]+)\\-([0-9]+)", "$2"));
                for(int i = rangeBegin + step; i < rangeEnd; i += step) {
                    realNums.add(i);
                }
            } else if (s.matches("([0-9]+)")) {
                realNums.add(Integer.valueOf(s));
            }
        }
        Collections.sort(realNums);
        return realNums;
    }

    public static void main(String[] args) {
        System.out.println("========3,[12-32]/2,45=======");
        SNumber.parseNumber("3,[12-32]/2,45").forEach(System.out::println);

        System.out.println("=======3,12-32/2,45========");

        SNumber.parseNumber("3,12-32/2,45").forEach(System.out::println);

        System.out.println("=======3,[12-32],45========");

        SNumber.parseNumber("3,[12-32],45").forEach(System.out::println);

        System.out.println("=======3,12-32,45========");

        SNumber.parseNumber("3,12-32,45").forEach(System.out::println);
    }

}
