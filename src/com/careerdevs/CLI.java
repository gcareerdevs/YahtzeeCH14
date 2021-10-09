package com.careerdevs;

import java.util.Scanner;

public class CLI {

    Scanner scanner = new Scanner(System.in);


    public static String getString(String statement) {
        String input = "";
        System.out.print("Input: ");
        return input;
    }

    public static int getNum(int min, int max) {
        int input = 0;
        System.out.print("Input: ");
        return input;
    }

    public static char getChar(char statement) {
        char input = 0;
        System.out.print("Input: ");
        return input;
    }

    public static void exit() {
        System.out.println("Exiting program. Come again!");
        System.exit(0);
    }

    public static String readEnter() {
        String input = "";
        while (true) {
            System.out.print("Input: ");
            if (!input.equals("")) {
                System.out.println("Please press enter to continue.");
            } else {
                break;
            }

        }
        return input;
    }

    public static void createSeperator (String seperator, int repeat){
        System.out.println(seperator.repeat(repeat));
    }

}
