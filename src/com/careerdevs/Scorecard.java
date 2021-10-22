package com.careerdevs;

import java.util.HashMap;

public class Scorecard {
    private int grandTotal;
    private int upperTotal;
    private int lowerTotal;
    private int bonus;
    private HashMap<String, Combo> upperCard;
    private HashMap<String, Combo> lowerCard;

    public Scorecard() {
        grandTotal = 0;
        upperTotal = 0;
        lowerTotal = 0;
        bonus = 0;
        upperCard = new HashMap<>();
        lowerCard = new HashMap<>();
    }

    class Combo {
        public int score;
        public boolean isFilled;
        public Combo() {
            this.score = 0;
            this.isFilled = false;
        }
    }

    public void testScore() {
        System.out.println("Scoring");
    }
}


