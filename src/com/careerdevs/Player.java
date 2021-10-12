package com.careerdevs;

public class Player {

    public String name;
    public Scorecard scorecard;
    public Hand hand;

    public Player(String name, Scorecard scorecard) {
        this.name = name;
        this.scorecard = scorecard;
    }

    public String getName() {
        return name;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
