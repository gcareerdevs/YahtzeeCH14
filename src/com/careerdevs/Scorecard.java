package com.careerdevs;

import java.util.HashMap;

public class Scorecard {
    private int grandTotal;
    private int upperTotal;
    private int lowerTotal;
    private int bonus;
    private HashMap<String, Combo> card;
    private static final String[] COMBO_NAMES = {
            "Aces", "Twos", "Threes", "Fours", "Fives", "Sixes",
            "Three of a Kind", "Four of a Kind", "Small Straight", "Large Straight",
            "Full House", "Yahtzee", "Chance"
    };


    public Scorecard() {
        grandTotal = 0;
        upperTotal = 0;
        lowerTotal = 0;
        bonus = 0;
        initializeCard();
    }

    class Combo {
        public int score;
        public boolean isFilled;
        public boolean isUpper;


        public Combo(boolean isUpper) {
            this.score = 0;
            this.isFilled = false;
            this.isUpper = isUpper;
        }
    }

    private void initializeCard() {

        for (int i = 0; i < COMBO_NAMES.length ; i++) {
            if (i < 6) {
                card.put(COMBO_NAMES[i], new Combo(true));
            } else {
                card.put(COMBO_NAMES[i], new Combo(false));
            }
        }
    }

    private HashMap<String, Combo> findAvailableCombos() {
        HashMap<String, Combo> availableCombos = new HashMap<>();
        //separates the fillable from the unfillable combinations
        for (String s : card.keySet()) {
            if (!card.get(s).isFilled) {
                availableCombos.put(s, card.get(s));
            }
        }

        return availableCombos;


    }
    public void testScore() {
        System.out.println("Scoring");
    }
}


