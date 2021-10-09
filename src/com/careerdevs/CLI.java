package com.careerdevs;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI {

    private static Scanner scanner = new Scanner(System.in);


    public static String getString(String statement) {
        String input = "";
        try {
            while (true) {
                System.out.print("Input: ");
                input = scanner.nextLine();
                if (input.length() == 0) {
                    System.out.println("Input contains no characters! Please try again");
                }
                else if (!input.equalsIgnoreCase(statement)){
                    System.out.println("Statement does not match given option. Please try again.");
                }
                else {
                    break;
                }
            }
        } catch (InputMismatchException exception) {
            System.out.println("Incorrect input! Please try again");
        } catch (Exception exception) {
            System.out.println("Unknown error. Please try again. If this message appears again please contact the developers about this issue.");
        }
        return input;
    }

    public static int getNum(int min, int max) {
        int input = 0;
        try {
            while (true) {
                System.out.print("Input: ");
                input = scanner.nextInt();
                if (input > max || input < min) {
                    System.out.println("Input out of range! Please try again with a number between " + min + " and " + max + ".");
                } else {
                    break;
                }
            }
        } catch (InputMismatchException exception) {
            System.out.println("Incorrect input! Please try again with a set of numbers.");
        } catch (Exception exception) {
            System.out.println("Unknown error. Please try again. If this message appears again please contact the developers about this issue.");
        }
        return input;
    }

    public static char getChar(char statement) {
        char input = 0;
        try {
            while (true) {
                System.out.print("Input: ");
                input = scanner.next().charAt(0);
                break;
            }
            return input;
        } catch (InputMismatchException exception){
            System.out.println("Incorrect input! Please try again.");
        }
        catch (Exception exception){
            System.out.println("Unknown error. Please try again. If this message appears again please contact the developers about this issue.");
        }
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
            input = scanner.nextLine();
            if (!input.equals("")) {
                System.out.println("Please press enter to continue.");
            } else {
                break;
            }

        }
        return input;
    }

    public static void createSeperator(String seperator, int repeat) {
        System.out.println(seperator.repeat(repeat));
    }

    public static void main(String[] args) {
//        readEnter();
//        getNum(1, 2);
//        createSeperator("- ", 20);
        getString("Yes");
    }

}
