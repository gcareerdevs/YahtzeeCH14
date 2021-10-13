package com.careerdevs;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI {

    private static Scanner scanner = new Scanner(System.in);

    public static String getString(String statement) /*The method is implemented to put in the statement/question we want to ask the user.*/ {
        String input = "";
        while (true) {
            try {
                System.out.print(statement + ": ");
                input = scanner.nextLine().trim();
                if (input.length() == 0) {
                    System.out.println("Input contains no characters! Please try again");
                }
//                else if (!input.equalsIgnoreCase(statement)){
//                    System.out.println("Statement does not match given option. Please try again.");
//                }
                else {
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println("Incorrect input! Please try again");
            } catch (Exception exception) {
                System.out.println("Unknown error. Please try again. If this message appears again please contact the developers about this issue.");
            }
        }
        return input;
    }

    public static int getNum(int min, int max) /*Method to have a player pick a number between given min and max. Most likely used for menus*/ {
        int input;
        while (true) {
            try {
                System.out.print("Enter a number between " + min + " and " + max + "\nInput: ");
                input = Integer.parseInt(scanner.nextLine());
                if (input > max || input < min) {
                    System.out.println("Input out of range! Please try again with a number between " + min + " and " + max + ".");
                } else {
                    break;
                }
            } catch (Exception exception) {
                System.out.println("Error! Please try again.");
            }
        }
        return input;
    }

    public static char getChar(char statement) /*Unfinished within scope. Method takes in the message we want to give to the player.*/ {
        char input;
        while (true) {
            try {
                System.out.print(statement + ":");
                input = scanner.next().charAt(0);
                break;
            } catch (Exception exception) {
                System.out.println("Error! Please try again");
            }
        }
        return input;
    }

    public static boolean yesOrNo(String statement) /*Method takes in the message we want to give the player and gives them the option of 'yes' or no' and returns a boolean*/ {
        while (true) {
            System.out.print("\nInput (y/n): ");
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.length() > 0 && input.charAt(0) == 'y') { //input.substring(0,1) == "y"
                return true;
            } else if (input.length() > 0 && input.charAt(0) == 'n') {
                return false;
            }

            System.out.println("\nPlease enter Yes OR No");
        }
    }

    public static void exit() /*Method allows the user to safely exit the program without error*/ {
        System.out.println("Exiting program. Come again!");
        System.exit(0);
    }

    public static String readEnter() /*Method allows the user to simply press the enter key to advance in that part of the program*/ {
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

    public static void createSeperator(String seperator, int repeat) /*Allows for easy application of seperators in the presentation of our program.*/ {
        System.out.println(seperator.repeat(repeat));
    }

    public static void main(String[] args) /*For my own testing of these methods.*/ {

        while (true) {
//          readEnter();
            getNum(1, 2);
//        createSeperator("- ", 20);
//        getString("Does this thing work?");

        }
    }

}
