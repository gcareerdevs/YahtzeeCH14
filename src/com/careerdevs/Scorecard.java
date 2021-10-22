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
        initializeUpperCard();
        initializeLowerCard();
    }

    class Combo {
        public int score;
        public boolean isFilled;
        public Combo() {
            this.score = 0;
            this.isFilled = false;
        }
    }

    private void initializeUpperCard() {
        Combo combo1 = new Combo();
        Combo combo2 = new Combo();
        Combo combo3 = new Combo();
        Combo combo4 = new Combo();
        Combo combo5 = new Combo();
        Combo combo6 = new Combo();


        upperCard.put("Aces", combo1 );
        upperCard.put("Twos", combo2);
        upperCard.put("Threes", combo3);
        upperCard.put("Fours", combo4);
        upperCard.put("Fives", combo5);
        upperCard.put("Sixes", combo6);
    }

    private void initializeLowerCard() {
        Combo combo1 = new Combo();
        Combo combo2 = new Combo();
        Combo combo3 = new Combo();
        Combo combo4 = new Combo();
        Combo combo5 = new Combo();
        Combo combo6 = new Combo();
        Combo combo7 = new Combo();


        upperCard.put("Three of a Kind", combo1 );
        upperCard.put("Four of a Kind", combo2);
        upperCard.put("Small Straight", combo3);
        upperCard.put("Large Straight", combo4);
        upperCard.put("Full House", combo5);
        upperCard.put("Yahtzee", combo6);
        upperCard.put("Chance", combo7);
    }

    public void testScore() {
        System.out.println("Scoring");
    }
}


