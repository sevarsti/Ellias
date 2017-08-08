package com.sanguo.game.service;

import com.sanguo.game.CurPerson;

public class FightService {
    public static int checkYiJiBiSha(int p1, int p2) {
        if(p1 - p2 > 30) {
            return 1;
        }
        if(p2 - p1 > 30) {
            return 2;
        }
        double diff = p1 - p2;
        if(diff > 0.0D) {
            double d1 = Math.exp(diff / 10.0D);
        }

        return 0;
    }

    public static int fightOneRound(CurPerson p1, CurPerson p2) {
        return 0;
    }
}