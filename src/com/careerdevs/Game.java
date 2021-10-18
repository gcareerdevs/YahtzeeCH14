package com.careerdevs;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<Player> winners;
    private final byte NUM_ROUNDS = 13;
    private final byte NUM_DICE = 5;
    private final byte NUM_TURNS = 3;

    public Game(int numPlayers) {

    }

    public void createPlayer(int numPlayers) {

    }

    public void playerTurn(Player player) {
        CLI.createSeperator("-", 10);
        System.out.println(player.name + "'s turn");
        CLI.createSeperator("-", 10);
        System.out.println("Rolling Initial Dice");
        player.hand.resetDice();
        player.hand.rollDice();

        for (int i = 0; i < NUM_TURNS; i++) {
            int input;
            boolean menuActive = true;
            System.out.println("Turn " + (i + 1));
            while (menuActive) {
                CLI.createSeperator("-", 10);
                printActiveDice(player.hand.activeDice);
                printHeldDice(player.hand.heldDice);
                String answer = CLI.getString("Would you like to hold or activate dice? (Input H to Hold, A to Activate, or R to Roll all Active Dice)");
                switch (answer.toUpperCase()) {
                    case "H":
                        CLI.createSeperator("-", 10);
                        printActiveDice(player.hand.activeDice);
                        System.out.println("Which die would you like to hold?");
                        input = CLI.getNum(1, player.hand.activeDice.size());
                        player.hand.holdOrRerollDie(player.hand.activeDice, input, player.hand.heldDice);
                        break;
                    case "A":
                        CLI.createSeperator("-", 10);
                        printHeldDice(player.hand.heldDice);
                        System.out.println("Which die would you like to Activate?");
                        input = CLI.getNum(1, player.hand.heldDice.size());
                        player.hand.holdOrRerollDie(player.hand.heldDice, input, player.hand.activeDice);
                        break;
                    case "R":
                        menuActive = false;
                        CLI.createSeperator("-", 10);
                        player.hand.rollDice();
                        break;
                    default:
                        break;
                }
            }
        }


    }

    public void printActiveDice(ArrayList<Die> activeArray) {
        String activeDice = "Active Dice: ";
        for (int i = 0; i < activeArray.size(); i++) {
            activeDice = activeDice.concat((i + 1) + ": ").concat("[" + activeArray.get(i).getValue() + "], ");
        }
        System.out.println(activeDice);
    }

    public void printHeldDice(ArrayList<Die> heldArray) {
        String heldDice = "Held Dice: ";
        for (int i = 0; i < heldArray.size(); i++) {
            heldDice = heldDice.concat((i + 1) + ": ").concat("[" + heldArray.get(i).getValue() + "], ");
        }
        System.out.println(heldDice);
    }

    public void createDice() {

    }

    public void startGame() {

    }

    public void endGame() {

    }

    public void checkWinners() {

    }


}
