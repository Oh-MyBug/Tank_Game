package com.ohmybug.tank;

import java.util.Random;

public enum Direction {
    L, U, R, D;

    private static Random random = new Random();

    public static Direction randomDirection() {
        return Direction.values()[random.nextInt(Direction.values().length)];
    }
}
