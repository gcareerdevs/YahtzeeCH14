package com.careerdevs;

public class Die {
    private int sides;
    private int value;

    public Die(int numSides) {
        this.sides = numSides;
        this.value = 0;
    }

    public void roll() {
        value = (int) (Math.random() * sides + 1);
    }

    public int getValue() {
        return value;
    }
}
