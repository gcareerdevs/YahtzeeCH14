package com.careerdevs;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Menu {

    public static String welcome_msg = "Hello and welcome to Yahtzee! Lets get started!";
    public static String rules = "Before you play Yahtzee, lets help you understand it's basic rules: \n" +
            "1. Players will take turns rolling the game dice for a total of 13 rounds \n" +
            "2. Each player will have at maximum 3 attempts to roll their dice during each of their turns, however not all 3 rolls have to be used \n" +
            "3. Each player will have a scorecard they must fill out each turn which will determine their final score. Scoring fields can be filled out with a 0 if chosen";

    public static void showWelcome() {
        CLI.createSeperator("*", 20);
        System.out.println(welcome_msg);
        CLI.createSeperator("*", 20);
    }

    public static void showRules() {
        System.out.println(rules);
    }

    public static void start() {

        showWelcome();

        int numPlayers = 0;

        while (true) {
            System.out.println("\nPlease select a option");
            System.out.println("(1) Start Game\n(2) Display Rules \n(3) Exit");
            int choice = CLI.getNum(1, 3);
            if (choice == 1) {
                System.out.println("\nGreat, How many people will be playing?");
                System.out.print("\nNumber of Players: ");
                numPlayers = CLI.getNum(2, 10);
                System.out.println("\nOkay so now lets start the game.");
                new Game(numPlayers);
            } else if (choice == 2) {
                showRules();
                CLI.readEnter();
            } else if (choice == 3) {
                CLI.exit();
            }
        }
    }

    public static void selectCombo(HashMap<String, Scorecard.Combo> availableCombos) {
        //Display the combos, allow user to see 0 score combos, allow user to lock in a selection.

        Object[] scoreCardKeys = availableCombos.keySet().toArray();
        for (int i = 0; i < scoreCardKeys.length; i++) { //Shows the player all of their combos that have a score greater 1
            String key = (String) scoreCardKeys[i];
            if (availableCombos.get(key) != null && availableCombos.get(key).getScore() >= 1) {
                System.out.println((i + 1) + ") " + availableCombos.get(key).getName() + " can score a total of " + availableCombos.get(key).getScore() + " points.");
            }
        }

        boolean allCombos = CLI.yesOrNo("Do you want to see the combinations that would score 0?");
        if (!allCombos) { //If they answer 'n'
            System.out.println("Ok! Select the combination you want to score.");
            int input = CLI.getNum(1, scoreCardKeys.length);
            //TODO:takes input and calls the lockSelection() method to change the isFilled condition to true.
        } else { //If they answer 'y'
            for (int i = 0; i < scoreCardKeys.length; i++) { //Will print the entire list of combinations.
                String key = (String) scoreCardKeys[i];
                if (availableCombos.get(key) != null) {
                    System.out.println((i + 1) + ") " + availableCombos.get(key).getName() + " can score a total of " + availableCombos.get(key).getScore() + " points.");
                }
                System.out.println("Ok! Select the combination you want to score.");
                int input = CLI.getNum(1, scoreCardKeys.length);
                //TODO:takes input and calls the lockSelection() method to change the isFilled condition to true.
            }


        }
    }
}
