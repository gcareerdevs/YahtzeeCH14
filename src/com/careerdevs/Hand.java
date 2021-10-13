package com.careerdevs;

import java.util.ArrayList;

public class Hand {
    public ArrayList<Die> heldDice;
    public ArrayList<Die> activeDice;
    private final byte NUM_DICE = 5;

    public Hand() {
        this.heldDice = new ArrayList<Die>();
        this.activeDice = createDice(NUM_DICE);

    }

    public void rollDice() {
        // Takes function as param - higher order function
        for (Die d : activeDice) {
            //d.roll();
        }
    }

    public void activateDice(Die dice) {

    }

    public void holdDice(Die dice) {
    }


    public void resetDice() { // Use private when using methods for specific classes (resetDice will only be called in this class)
        for (Die d : heldDice) {
            //d.reset();
        }
    }

    public ArrayList<Die> createDice(byte numDice) {
        ArrayList<Die> tempArr = new ArrayList<>();
        for (int i = 0; i < numDice; i++) {
            Die die = new Die(6);
            tempArr.add(die);
        }
        return tempArr;
    }
}