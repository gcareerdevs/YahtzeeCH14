package com.careerdevs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Scorecard {
    private int grandTotal;
    private int upperTotal;
    private int lowerTotal;
    private int bonus;
    private final int MAX_BONUS_POINTS = 35;
    private ArrayList<Integer> currentDiceValues;
    private final int MAX_SCORE_NAME_LENGTH = 17;
    private HashMap<String, Combo> card;

    private enum ComboKey {
        ACE("Aces"),
        TWO("Twos"),
        THREE("Threes"),
        FOUR("Fours"),
        FIVE("Fives"),
        SIX("Sixes"),
        TOAK("Three of a Kind"),
        FOAK("Four of a Kind"),
        SMSTR("Small Straight"),
        LRGSTR("Large Straight"),
        FLLHSE("Full House"),
        YHTZE("Yahtzee"),
        CHNC("Chance");

        private String enumName;

        ComboKey(String name) {
            enumName = name;
        }

        private String getName() {
            return enumName;
        }

    }

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

        public void setScore(int value) {
            if (!isFilled) {
                score = value;
            } else {
                System.out.println("\n".repeat(3) + "ERROR: UNAUTHORIZED SCORE CHANGE" + "\n".repeat(3));
            }
        }

        public int getScore() {
            return score;
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
                card.put(key.toString(), new Combo(key.getName(), true));
            } else {
                card.put(key.toString(), new Combo(key.getName(), false));
            }
        }
    }


    public void calculateTotalScore() {
        Object[] scoreCardKeys = card.keySet().toArray();
        for (int i = 0; i < scoreCardKeys.length ; i++) {
            String key = (String) scoreCardKeys[i];
            grandTotal += card.get(key).score;
        }
    }

    public HashMap<String, Combo> scoreRoll(ArrayList<Integer> dice) {
        HashMap<String, Combo> availableCombos = findAvailableCombos();
        currentDiceValues = dice;
        scoreAvailableCombos(availableCombos);
        return availableCombos;
    }

    private void scoreAvailableCombos(HashMap<String, Combo> availableCombos) {
        //In each method, assign the particular combo with your calculated score.
        //ex. calculating aces, got a score of 3
        // card.get("ACE").setScore(3);
        Object[] scoreCardKeys = card.keySet().toArray();
        for (int i = 0; i < scoreCardKeys.length; i++) {
            String key = (String) scoreCardKeys[i];
            if (availableCombos.get(key) != null) {
                if (i < 6) {
                    scoreUpper(i);
                } else if (i < 8) {
                    scoreOfAKind(i == 6);
                } else if (i < 10) {
                    scoreStraight(i == 8);
                } else if (i == 11) {
                    scoreFullHouse();
                } else if (i == 12) {
                    scoreYahtzee();
                } else {
                    scoreChance();
                }
            }
        }
    }

    private HashMap<String, Combo> findAvailableCombos() {
        HashMap<String, Combo> availableCombos = new HashMap<>();
        //separates the filled from the unfilled combinations
        for (String comboKey : card.keySet()) {
            Combo combo = card.get(comboKey);
            if (!combo.isFilled) {
                //Resetting scores to 0 before executing scoring logic
                combo.setScore(0);
                availableCombos.put(comboKey, combo);
            }
        }
        return availableCombos;
    }

    private void scoreUpper(int comboCheck) {
        // comboCheck will be a value from 0 (checking for Aces) to 5 (checking for Sixes)
    }

    private void scoreFullHouse() {
        HashMap<Integer, Integer> tempMap = new HashMap<Integer, Integer>();

        tempMap.put(1,0);
        tempMap.put(2,0);
        tempMap.put(3,0);
        tempMap.put(4,0);
        tempMap.put(5,0);
        tempMap.put(6,0);

        for(Map.Entry<Integer, Integer> entry : tempMap.entrySet()) {
            Integer face = entry.getKey();
            Integer count = entry.getValue();

            for (Integer currentDiceValue : currentDiceValues) {

                if (Objects.equals(currentDiceValue, face)) {
                    count++;
                }
            }
        }

        for(Map.Entry<Integer, Integer> entry : tempMap.entrySet()) {
            Integer face = entry.getKey();
            Integer count = entry.getValue();

            if (count == 2 || count == 3){
                if (count == 2 || count == 3){
                    card.get("FLLHSE").setScore(25);
                }
            }else card.get("FLLHSE").setScore(0);

        }


    }

    private void scoreOfAKind(boolean isThreeOfAKind) {
        //If isThreeOfAKind == false, assign the score for Four of A Kind
        //If the three-of-a-kind can't be made, do nothing
        int threeOfAKind = 3, fourOfAKind = 4, points = 0;
        int countOfOnes = Collections.frequency(currentDiceValues, 1);
        int countOfTwos = Collections.frequency(currentDiceValues, 2);
        int countOfThrees = Collections.frequency(currentDiceValues, 3);
        int countOfFours = Collections.frequency(currentDiceValues, 4);
        int countOfFives = Collections.frequency(currentDiceValues, 5);
        int countOfSixes = Collections.frequency(currentDiceValues, 6);

        for (Integer n : currentDiceValues) {
            points += n;
        }

        for (int i = 0; i < currentDiceValues.size(); i++) {
            if (countOfOnes == threeOfAKind || countOfTwos == threeOfAKind || countOfThrees == threeOfAKind ||
                    countOfFours == threeOfAKind || countOfFives == threeOfAKind || countOfSixes == threeOfAKind && isThreeOfAKind) {
                isThreeOfAKind = true;
                card.get("TOAK").setScore(points);
            } else if (countOfOnes == fourOfAKind || countOfTwos == fourOfAKind || countOfThrees == fourOfAKind ||
                    countOfFours == fourOfAKind || countOfFives == fourOfAKind || countOfSixes == fourOfAKind) {
                isThreeOfAKind = false;
                card.get("FOAK").setScore(points);
            }
        }
    }

    private void scoreStraight(boolean isSmallStraight) {
        //If isSmallStraight == false, assign the score for large straight
        //If the straight can't be made, do nothing

        ArrayList<Integer> sortDice = currentDiceValues;
        Collections.sort(sortDice);
        int counter = 0;

        for (int i = 0; i < sortDice.size(); i++) {
            if(sortDice.get(i) == (sortDice.get(i+1) - 1)){
                counter++;
            }
            else{
                counter = 0;
            }
        }


        if(counter == 3 && isSmallStraight){
            card.get("SMSTR").score = 30;
        }
        else if(counter == 4 && !isSmallStraight){
            card.get("LRGSTR").score = 40;
        }


    }

    private void jokerRules(){
        HashMap<String, Combo> availableCombos = findAvailableCombos();
        Object[] scoreCardKeys = card.keySet().toArray();
        for (int i = 0; i < scoreCardKeys.length ; i++) {
            String key = (String) scoreCardKeys[i];
            if (availableCombos.get(key) != null) {
                if (i < 6) {
                    scoreUpper(i);
                } else if (i < 8) {
                    scoreOfAKind(i == 6);
                } else if (i < 10) {
                    if(i == 8){
                        //Small - 30
                        card.get("SMSTR").score = 30;
                    }
                    else{
                        //Large Straight - 40
                        card.get("LRGSTR").score = 40;
                    }
                } else if (i == 11) {
                    //Full House - 25
                    card.get("FLLHSE").score = 40;
                } else if (i == 12) {
                    scoreChance();
                }
            }
        }

    }


    private void scoreYahtzee() {
        int counter = 0;
        for (int i = 0; i < currentDiceValues.size(); i++) {
            if (currentDiceValues.get(0) == currentDiceValues.get(i)) {
                counter++;
            }
        }
        if (counter == 5){
            if (!card.get("YHTZE").isFilled){
                card.get("YHTZE").score = 50;
            } else {
                bonus += 100;
                //jokerRules();
            }
        }
    }

    private void scoreChance() {
        int sumOfDice = 0;
        for (Integer i : currentDiceValues) {
            sumOfDice += i;
        }

        card.get("CHNC").setScore(sumOfDice);
    }

    private void checkForBonus() {
        if (upperTotal >= 63) {
            setBonus(MAX_BONUS_POINTS);
        }
    }

    private int getUpperTotal() {
        return upperTotal;
    }

    private void setBonus(int points) { bonus = points; }

    @Override
    public String toString() {
        String output = "";

        for (ComboKey c : ComboKey.values()) {
            output += (card.get(c.toString())) + "\n";
        }

        return output;
    }

    public void testScore() {
        System.out.println("Scoring");
    }

    public ArrayList<Integer> getCurrentDiceValues() {
        return currentDiceValues;
    }
}


