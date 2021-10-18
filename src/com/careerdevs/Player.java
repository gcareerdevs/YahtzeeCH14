package com.careerdevs;

public class Player {

    public String name;
    public Scorecard scorecard;
    public Hand hand;

    public Player(String name) {
        this.name = name;
        scorecard = new Scorecard();
    }

    public String getName() {
        return name;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
