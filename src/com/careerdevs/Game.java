package com.careerdevs;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<Player> winners;
    private final byte NUM_ROUNDS = 13;
    private final byte NUM_DICE = 5;
    private final byte NUM_TURNS = 3;

    public Game(int numPlayers) {
        players = new ArrayList<>();
        createPlayer(numPlayers);
        startGame();
    }

    public void createPlayer(int numPlayers) { // Added createPlayer()

        for (int i = 0; i < numPlayers; i++) {
            String name = CLI.getString("Enter your name");
            Player newPlayer = new Player(name);
            players.add(newPlayer);
            Hand hand = new Hand(createDice()); // Temporary
            newPlayer.setHand(hand);
        }
    }


    public void playerTurn(Player player) { // Tweaked playerTurn()
        CLI.createSeperator("-", 15);
        System.out.println(player.name + "'s Turn");
        CLI.createSeperator("-", 15);
        System.out.println("ROLLING INITIAL DICE");
        player.hand.resetDice();
        player.hand.rollDice();

        for (int i = 0; i < NUM_TURNS; i++) {
            int input;
            boolean menuActive = true;
            CLI.createSeperator("-", 15);
            System.out.println("\nT U R N  " + (i + 1) + "\n");
            CLI.createSeperator("-", 15);
            while (menuActive) {
                CLI.createSeperator("-", 15);
                printActiveDice(player.hand.activeDice);
                printHeldDice(player.hand.heldDice);
                CLI.createSeperator("-", 15);
                System.out.println("What would you like to do?\n1) Hold dice\n2) Activate dice\n3) Roll\n4) Score");
                CLI.createSeperator("-", 15);
                int answer = CLI.getNum(1, 4);
                switch (answer) {
                    case 1 -> {
                        CLI.createSeperator("-", 15);
                        printActiveDice(player.hand.activeDice);
                        System.out.println("\nWhich die would you like to hold?");
                        input = CLI.getNum(1, player.hand.activeDice.size());
                        player.hand.holdOrRerollDie(player.hand.activeDice, input, player.hand.heldDice);
                    }
                    case 2 -> {
                        CLI.createSeperator("-", 15);
                        printHeldDice(player.hand.heldDice);
                        System.out.println("\nWhich die would you like to Activate?");
                        input = CLI.getNum(1, player.hand.heldDice.size());
                        player.hand.holdOrRerollDie(player.hand.heldDice, input, player.hand.activeDice);
                    }
                    case 3 -> {
                        // if i is not equal to 2, do the following
                        if (i != 2) {
                            menuActive = false;
                            CLI.createSeperator("-", 15);
                            player.hand.rollDice();
                        } else {
                            System.out.println("\nYou can not re-roll, you must enter a score");
                        }
                    }
                    case 4 -> {
                        menuActive = false;
                        ArrayList<Integer> currentRoll = player.hand.getDiceValues();
                        HashMap<String, Scorecard.Combo> availableCombos = player.scorecard.scoreRoll(currentRoll);
                        Menu.selectCombo(availableCombos);

                    }
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

    public ArrayList<Die> createDice() { // Added createDice()
        ArrayList<Die> tempDice = new ArrayList<>();
        for (int i = 0; i < NUM_DICE; i++) {
            Die die = new Die();
            tempDice.add(die);
        }
        return tempDice;
    }

    public void startGame() { // Added startGame

        for (int i = 0; i < NUM_ROUNDS; i++) {
            CLI.createSeperator("-", 10);
            System.out.println("R O U N D  " + (i + 1));
            CLI.createSeperator("-", 10);
            for (Player player : players) {
                playerTurn(player);
            }
        }
    }

    public void endGame() {

    }

    public void checkWinners() {

    }
}
