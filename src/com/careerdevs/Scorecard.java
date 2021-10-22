package com.careerdevs;

import java.util.Date;
import java.util.HashMap;

public class Scorecard {
    private int grandTotal;
    private int upperTotal;
    private int lowerTotal;
    private int bonus;
    private final int MAX_SCORE_NAME_LENGTH = 17;
    private HashMap<String, Combo> card;
    private enum ComboKey {
        ACE ("Aces"),
        TWO ("Twos"),
        THREE ("Threes"),
        FOUR ("Fours"),
        FIVE ("Fives"),
        SIX ("Sixes"),
        TOAK ("Three of a Kind"),
        FOAK ("Four of a Kind"),
        SMSTR ("Small Straight"),
        LRGSTR ("Large Straight"),
        FLLHSE ("Full House"),
        YHTZE ("Yahtzee"),
        CHNC ("Chance");

        private String enumName;

        ComboKey(String name) {
            enumName = name;
        }
        
        private String getName() {
            return enumName;
        }

    };

    /*private static final String[] COMBO_NAMES = {
            "Aces", "Twos", "Threes", "Fours", "Fives", "Sixes",
            "Three of a Kind", "Four of a Kind", "Small Straight", "Large Straight",
            "Full House", "Yahtzee", "Chance"
    };*/


    public Scorecard() {
        grandTotal = 0;
        upperTotal = 0;
        lowerTotal = 0;
        bonus = 0;
        card = new HashMap<>();
        initializeCard();
    }

    class Combo {
        private int score;
        private String name;
        private boolean isFilled;
        private boolean isUpper;

        public Combo(String name, boolean isUpper) {
            this.name = name;
            this.score = 0;
            this.isFilled = false;
            this.isUpper = isUpper;
        }

        public String getName() {
            return name;
        }
        @Override
        public String toString() {
            String scoreSpacer = (" ".repeat(MAX_SCORE_NAME_LENGTH - name.length()));
            return name + scoreSpacer + " || Score: " + score + " Used " + (isFilled ? "[X]" : "[ ]");
        }
    }

    private void initializeCard() {
        for (ComboKey key : ComboKey.values()) {
            if (key.ordinal() < 6) {
                card.put(key.toString(), new Combo(key.getName(),true));
            } else {
                card.put(key.toString(), new Combo(key.getName(),false));
            }
        }

        /*for (int i = 0; i < ComboName.values().length; i++) {
            if (i < 6) {
                card.put(ComboName.values()[i].toString(), new Combo(true));
            } else {
                card.put(ComboName.values()[i].toString(), new Combo(false));
            }
        }*/
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

    @Override
    public String toString() {
        String output = "";

        for (ComboKey c : ComboKey.values()) {
            output += (card.get(c.toString())) + "\n";
        }
        /*for (int i = 0; i < card.size(); i++) {
            output += card.values();
        }*/
        return output;
    }
    public void testScore() {
        System.out.println("Scoring");
    }
}


