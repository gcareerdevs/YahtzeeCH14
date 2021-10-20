package com.careerdevs;

import java.util.HashMap;

public class Scorecard {
    private int grandTotal;
    private int upperTotal;
    private int lowerTotal;
    private int bonus;
    private HashMap<String, Combination> upperCard;
    private HashMap<String, Combination> lowerCard;

    public Scorecard() {
        grandTotal = 0;
        upperTotal = 0;
        lowerTotal = 0;
        bonus = 0;
        upperCard = new HashMap<>();
        lowerCard = new HashMap<>();
    }

    class Combination {
        public Combination() {

        }
    }

    public void testScore() {
        System.out.println("Scoring");
    }
}


