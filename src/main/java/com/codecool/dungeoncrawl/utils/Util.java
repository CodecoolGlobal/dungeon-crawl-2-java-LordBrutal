package com.codecool.dungeoncrawl.utils;

import java.util.Random;

public class Util {
    public static int generateRandomBetween(int min, int max){
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }
}
