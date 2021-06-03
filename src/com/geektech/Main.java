package com.geektech;

import java.util.Arrays;
import java.util.Random;

public class Main {
public static String[] bossName = {"King Kong"};
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenceType = " ";

    public static String[] heroesNames = {"Titan","Sub-Zero","Liu Kung","Jax"};
    public static int[] heroesHealth = {250, 250, 250, 300};
    public static int[] heroesDamage = {20, 20, 20, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Mental", "Treatment"};

    public static void main(String[] args) {

        fightInfo();
        while (!isFinished()) {
            round();
        }

    }

    public static void round() {
        changeBossDefence();
        bossHit();
        helpFromMedic();
        heroesHit();
        fightInfo();
    }


    public static void changeBossDefence() {
        Random random1 = new Random();
        int randomIndex = random1.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
    }


    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boos won!");
            return true;
        }
        return false;
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {

                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }
    public static void helpFromMedic() {
        for (int i = 0; i < heroesHealth.length  ; i++) {
            if (heroesHealth[heroesHealth.length -  1] != 0) {
                if (heroesHealth[i] < 300 && heroesHealth[i] > 0) {
                    System.out.println(heroesNames[i] + " " + heroesHealth[i] + " before Medic");
                    heroesHealth[i] += 100;
                    System.out.println(heroesNames[i] + " " + heroesHealth[i] + " after Medic");

                }
            } else {
                break;
            }

        }
    }



    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType.equals(heroesAttackType[i])) {
                    Random random2 = new Random();
                    int koeff = random2.nextInt(9) + 2;
                    if (bossHealth - heroesDamage[i] * koeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * koeff;
                    }
                    System.out.println(heroesAttackType[i] + " cRiTicAl HIT " + heroesDamage[i] * koeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {

                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }


    // Статистика боя

    public static void fightInfo() {
        System.out.println("____________________________________");
        System.out.println(Arrays.toString(bossName) + " = health " + bossHealth +
                " damage [" + bossDamage + "]");
        for (int i = 0; i < heroesNames.length; i++) {
            System.out.println(heroesNames[i] + " = health " +
                    heroesHealth[i] + " damage [" +
                    heroesDamage[i] + "]");


        }
    }
}