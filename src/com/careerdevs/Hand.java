package com.careerdevs;

import java.util.ArrayList;

public class Hand {
    public ArrayList<Die> heldDice;
    public ArrayList<Die> activeDice;

    public Hand(ArrayList<Die> activeDice) {
        this.heldDice = new ArrayList<>();
        this.activeDice = activeDice;
    }

    public void rollDice() {
        // Takes function as param - higher order function
        for (Die d : activeDice) {
            d.roll();
        }
    }

    public void holdOrRerollDie(ArrayList<Die> currentList, int selectedDie, ArrayList<Die> newList) /*Takes in both array lists and the index - 1 of the dice they want to select. Requires input for the user for the int. currentList is the list that the die will be taken from. newList is the list the die is being added to.*/ {
        newList.add(currentList.get((selectedDie) - 1));
        currentList.remove(currentList.get((selectedDie) - 1));
    }

    public void resetDice() { // Use private when using methods for specific classes (resetDice will only be called in this class)
        activeDice.addAll(heldDice);
        for (int i = 0; i < heldDice.size(); i++) {
            heldDice.remove(i);
        }
    }

    public void activateDice(Die die) {
    }

    public ArrayList<Integer> getDiceValues() {
        return new ArrayList<Integer>();
    }
}