package com.careerdevs;

import java.util.ArrayList;

public class Hand {
    public ArrayList<Die> heldDice;
    public ArrayList<Die> activeDice;

    public Hand(ArrayList<Die> activeDice){
        this.heldDice = new ArrayList<>();
        this.activeDice = activeDice;
    }

    public void rollDice(){
        // Takes function as param - higher order function
        for (Die d : activeDice) {
            //d.roll();
        }
    }

    public void holdDice(Die die){
    }

    public void resetDice(){ // Use private when using methods for specific classes (resetDice will only be called in this class)
        for (Die d : heldDice) {
            //d.reset();
        }
    }

    public void activateDice(Die die) {
    }
}