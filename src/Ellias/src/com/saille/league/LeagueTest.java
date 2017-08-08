package com.saille.league;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-12-28
 * Time: 20:57:55
 * To change this template use File | Settings | File Templates.
 */
public class LeagueTest {
    public static void main(String[] args) {
        int team1 = 20000;
        int team2 = 10000;
        int[] result = new int[3];
        for(int i = 0; i < 10000; i++) {
            result[doMatch(team1, team2)]++;
        }
        for(int i = 0; i < 3; i++) {
            System.out.println(result[i]);
        }
    }

    public static int doMatch(int team1, int team2) {
        int avg = (int) Math.sqrt(team1 * team2);
        int sum = team1 + team2 + avg;
        double rateWin = ((double) team1) / sum;
        double rateDraw = ((double) avg) / sum;
        double rateLose = ((double) team2) / sum;
        double rand = new Random().nextDouble();
        if(rand < rateWin) {
            return 0;
        } else if(rand < (rateWin + rateDraw)) {
            return 1;
        } else {
            return 2;
        }
    }
}
