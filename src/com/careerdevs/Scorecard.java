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

        public void lockSelection() {
            isFilled = true;
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
        for (int i = 0; i < scoreCardKeys.length; i++) {
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
        int count = 0;

        for (int side : currentDiceValues) { //Loops thru the Arraylist of dice posted by the player.
            if (side == (comboCheck + 1)) { //If the value of the dice is equal to the argument comboCheck given when method fires...
                count++; //adds to the count.
            }
        }

        int total = (comboCheck + 1) * count; //Calculates total score for the algorithm.

        for (ComboKey combo : ComboKey.values() //For each loop that loops through the enum ComboKey.
        ) {
            if (combo.ordinal() == comboCheck) { //Ordinal makes an array to iterate through and checks the argument of comboCheck for that iteration.
                card.get(combo.toString()).setScore(total); //grabs that enum object and adds the total variable to the score field for that enum.
                break;
            }
        }

    }

    private void scoreFullHouse() {

        boolean check3 = false;
        boolean check2 = false;

        for (int one : currentDiceValues) {
            int count = 0;
            for (int two : currentDiceValues) {
                if (one == two) {

                    count++;
                }
            }
            if (count == 3) {
                check3 = true;
            }
            if (count == 2) {
                check2 = true;
            }
        }
        if (check3 && check2) {
            card.get("FLLHSE").setScore(25);

        }

    }

    private void scoreOfAKind(boolean isThreeOfAKind) {
        //If isThreeOfAKind == false, assign the score for Four of A Kind
        //If the three-of-a-kind can't be made, do nothing

        ArrayList<Integer> frequencyCounts = new ArrayList<>();

        int points = currentDiceValues.stream().mapToInt(i -> i).sum();

        for (int i = 0; i < 6; i++) {
            frequencyCounts.add(Collections.frequency(currentDiceValues, i + 1));
        }

        for (Integer frequencyCount : frequencyCounts) {

            if (frequencyCount >= 3 && isThreeOfAKind) {
                card.get("TOAK").setScore(points);

            } else if (frequencyCount >= 4) {
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
            if (sortDice.get(i) == (sortDice.get(i + 1) - 1)) {
                counter++;
            } else {
                counter = 0;
            }
        }


        if (counter == 3 && isSmallStraight) {
            card.get("SMSTR").score = 30;
        } else if (counter == 4 && !isSmallStraight) {
            card.get("LRGSTR").score = 40;
        }


    }

    private void jokerRules() {
        HashMap<String, Combo> availableCombos = findAvailableCombos();
        Object[] scoreCardKeys = card.keySet().toArray();
        for (int i = 0; i < scoreCardKeys.length; i++) {
            String key = (String) scoreCardKeys[i];
            if (availableCombos.get(key) != null) {
                if (i < 6) {
                    scoreUpper(i);
                } else if (i < 8) {
                    scoreOfAKind(i == 6);
                } else if (i < 10) {
                    if (i == 8) {
                        //Small - 30
                        card.get("SMSTR").score = 30;
                    } else {
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
        if (counter == 5) {
            if (!card.get("YHTZE").isFilled) {
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

    private void setBonus(int points) {
        bonus = points;
    }

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

    //TODO: Delete main() when testing complete
    public static void main(String[] args) {
        Scorecard card = new Scorecard();
        System.out.println(card.toString());
        ArrayList<Integer> testDice = new ArrayList<>();

        //ONLY CHANGE THESE VALUES TO TEST YOUR SCORING LOGIC
        testDice.add(1);
        testDice.add(1);
        testDice.add(1);
        testDice.add(1);
        testDice.add(1);

        card.currentDiceValues = testDice;
        System.out.println(testDice);
        card.scoreUpper(0);
        card.scoreUpper(1);
        card.scoreYahtzee();
        card.scoreOfAKind(true);
        card.scoreOfAKind(false);
        card.scoreChance();
        card.scoreFullHouse();
        card.scoreStraight(true);
        card.scoreStraight(false);

        System.out.println("Test for Ones: " + card.card.get("ACE").getScore());
        System.out.println("Test for Twos: " + card.card.get("TWO").getScore());


        System.out.println("Test for Three of a Kind: " + card.card.get("TOAK").getScore());
        System.out.println("Test for Four of a Kind: " + card.card.get("FOAK").getScore());
        System.out.println("Test for Small Straight: " + card.card.get("SMSTR").getScore());
        System.out.println("Test for Large Straight: " + card.card.get("LRGESTR").getScore());
        System.out.println("Test for Yahtzee: " + card.card.get("YHTZE").getScore());
        System.out.println("Test for Chance: " + card.card.get("CHNC").getScore());
        System.out.println("Test for Full House: " + card.card.get("FLLHSE").getScore());

    }

}


