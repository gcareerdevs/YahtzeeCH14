package com.careerdevs;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<Player> winners;
    private final byte NUM_ROUNDS = 13;
    private final byte NUM_DICE = 5;
    private final byte NUM_TURNS= 3;

    public Game(int numPlayers) {

    }

    public void createPlayer(int numPlayers) {

    }

    public void playerTurn(Player player) {
        CLI.createSeperator("-",10);
        System.out.println(player.name + "'s turn");
        CLI.createSeperator("-",10);
        System.out.println("Rolling Initial Dice");
        player.hand.resetDice();
        player.hand.rollDice();

        for (int i = 0; i < NUM_TURNS; i++) {
            boolean menuActive = true;
            System.out.println("Turn " + (i + 1));
            while (menuActive){
                CLI.createSeperator("-",10);
                printActiveDice(player.hand.activeDice);
                printHeldDice(player.hand.heldDice);
                String answer = CLI.getString("What action would you like to Perform? (Input H to Hold a die\nInput A to Activate a die\nInput R to Roll all Active Dice\nInput S to Score the Current Set of Dice)");
                switch(answer.toUpperCase()){
                    case "H":
                        CLI.createSeperator("-",10);
                        printActiveDice(player.hand.activeDice);
                        System.out.println("Which die would you like to hold?");
                        player.hand.holdDice(player.hand.activeDice.get(CLI.getNum(1,6) - 1));
                        break;
                    case "A":
                        CLI.createSeperator("-",10);
                        printHeldDice(player.hand.heldDice);
                        System.out.println("Which die would you like to Activate?");
                        player.hand.activateDice(player.hand.heldDice.get(CLI.getNum(1,6) - 1));
                        break;
                    case "R":
                        menuActive = false;
                        CLI.createSeperator("-",10);
                        player.hand.rollDice();
                        break;
                    case "S":
                        menuActive = false;
                        CLI.createSeperator("-",10);
                        //TODO After Scorecard is implemented, make sure to add the Implementation here
                        break;
                    default:
                        break;
                }
            }
        }


    }

    public void printActiveDice(ArrayList<Die> activeArray){
        String activeDice = "Active Dice: ";
        for (int i = 0; i < activeArray.size(); i++) {
            activeDice = activeDice.concat((i+1) + ": ").concat("[" + activeArray.get(i).value + "], ");
        }
        System.out.println(activeDice);
    }
    public void printHeldDice(ArrayList<Die> heldArray){
        String heldDice = "Held Dice: ";
        for (int i = 0; i < heldArray.size(); i++) {
            heldDice = heldDice.concat((i+1) + ": ").concat("[" + heldArray.get(i).value + "], ");
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
