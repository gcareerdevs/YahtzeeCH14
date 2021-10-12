package com.careerdevs;

public class Menu {

    public static String welcome_msg;
    public static String rules;

    public static String showWelcome(){
        return welcome_msg;
    }

    public static String showRules(){
        return rules;
    }

    public static void start(){

        System.out.println("*************************");
        System.out.println(" Welcome to Yahtzee ");
        System.out.println("*************************");

        System.out.println("\nWelcome lets get started.");

        System.out.println("\nPlease select a option");
        System.out.println("(1) Start Game.\n(2) Exit");
        System.out.print("\nChoice: ");
        int choice = CLI.getNum(1,2);
        int numPlayers = 0;

        if (choice == 1) {
            System.out.println("\nGreat, How many people will me playing?");
            System.out.print("\nNumber of Players: ");
            numPlayers = CLI.getNum(2, 10);
        }else if (choice == 2){
            System.out.println("Okay maybe we can play next time, come back soon");
        }

        System.out.println("\nOkay so now lets start the game.");
        Game newGame = new Game(numPlayers);

    }


}
