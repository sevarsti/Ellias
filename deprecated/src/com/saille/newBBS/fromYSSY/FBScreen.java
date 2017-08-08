package com.saille.newBBS.fromYSSY;

import com.saille.newBBS.UserInfo;
import com.saille.newBBS.telnet.NewBBSMain;
import com.saille.newBBS.telnet.Screenline;
import com.saille.newBBS.telnet.util.StringUtil;

import java.io.PrintStream;
import java.util.List;

public class FBScreen {
    public static void outns(NewBBSMain thread, String chars, boolean cc) {
    }

    public static void outc(NewBBSMain thread, char c) {
        UserInfo userInfo = thread.getUserInfo();

        if((userInfo.isInansi()) && (c == 'm')) {
            userInfo.setInansi(false);
            return;
        }

        if((c == '\033') && (!userInfo.isIscolor())) {
            userInfo.setInansi(true);
            return;
        }
        if(userInfo.isDumb_term()) {
            if(StringUtil.isAlpha(c)) {
                if(c == '\n') {
                    ochar(thread, 13);
                } else if((c != '\033') || (!userInfo.isShowansi())) {
                    c = '*';
                }
            }
            ochar(thread, c);
            return;
        }
        int reg_line = userInfo.getCur_col() + userInfo.getRoll();
        int reg_scrln = userInfo.getScr_lns();
        while((reg_scrln != 0) && (reg_line >= reg_scrln)) {
            reg_line -= reg_scrln;
        }
        Screenline slp = (Screenline) userInfo.getBig_picture().get(reg_line);

        int reg_col = userInfo.getCur_col();
        if(!StringUtil.isAlpha(c)) {
            if((c == '\n') || (c == '\r')) {
                if(userInfo.isStanding()) {
                    slp.setEso(Math.max(slp.getEso(), reg_col));
                    userInfo.setStanding(false);
                }
                if(reg_col > slp.getLen()) {
                    for(int i = slp.getLen(); i <= reg_col; i++) {
                        slp.getData()[i] = 32;
                    }
                }
                slp.setLen(reg_col);
                userInfo.setCur_col(0);
                if(userInfo.getCur_ln() < userInfo.getScr_lns()) {
                    userInfo.setCur_ln(userInfo.getCur_ln() + 1);
                }
                return;
            }
            if((c != '\033') || (!userInfo.isShowansi())) {
                c = '*';
            }
        }
        if(reg_col >= slp.getLen()) {
            for(int i = slp.getLen(); i < reg_col; i++) {
                slp.getData()[i] = 32;
            }
            slp.getData()[reg_col] = 0;
            slp.setLen(reg_col + 1);
        }
        if(slp.getData()[reg_col] != c) {
            if((slp.getMode() & 0x1) != 1) {
                slp.setSmod(reg_col);
                slp.setEmod(reg_col);
            } else {
                if(reg_col > slp.getEmod()) {
                    slp.setEmod(reg_col);
                }
                if(reg_col < slp.getSmod()) {
                    slp.setSmod(reg_col);
                }
            }
            slp.setMode(slp.getMode() | 0x1);
        }
        slp.getData()[reg_col] = c;
        reg_col++;
        if(reg_col >= userInfo.getScr_cols()) {
            if((userInfo.isStanding()) && ((slp.getMode() & 0x2) != 0)) {
                userInfo.setStanding(false);
                slp.setEso(Math.max(slp.getEso(), reg_col));
            }
            reg_col = 0;
            if(userInfo.getCur_ln() < userInfo.getScr_lns()) {
                userInfo.setCur_ln(userInfo.getCur_ln() + 1);
            }
        }
        userInfo.setCur_col(reg_col);
    }

    public static int ochar(NewBBSMain thread, int c) {
        thread.getOut().print((char) c);
        return c;
    }

    public static void prints(NewBBSMain thread, String fmt) {
        for(int i = 0; i < fmt.length(); i++) {
            char c = fmt.charAt(i);
            if(c == 0) {
                return;
            }
            outc(thread, c);
        }
    }

    public static void clear(NewBBSMain thread) {
        UserInfo userInfo = thread.getUserInfo();

        if(userInfo.isDumb_term()) {
            return;
        }
        userInfo.setRoll(0);
        userInfo.setDocls(1);
        userInfo.setDownfrom(0);
        for(int i = 0; i < userInfo.getScr_lns(); i++) {
            Screenline slp = (Screenline) userInfo.getBig_picture().get(i);
            slp.setMode(0);
            slp.setLen(0);
            slp.setOldlen(0);
        }
        move(thread, 0, 0);
    }

    public static void move(NewBBSMain thread, int y, int x) {
        UserInfo userInfo = thread.getUserInfo();
        userInfo.setCur_col(x);
        userInfo.setCur_ln(y);
        thread.getOut().print("\033[" + y + ";" + x + "H");
    }

    public static void clrtobot(NewBBSMain thread) {
        UserInfo userInfo = thread.getUserInfo();

        if(userInfo.isDumb_term()) {
            return;
        }
        for(int i = userInfo.getCur_ln(); i < userInfo.getScr_lns(); i++) {
            int j = i + userInfo.getRoll();
            while(j >= userInfo.getScr_lns()) {
                j -= userInfo.getScr_lns();
            }
            Screenline slp = (Screenline) userInfo.getBig_picture().get(j);
            slp.setMode(0);
            slp.setLen(0);
            if(slp.getOldlen() > 0) {
                slp.setOldlen(255);
            }
        }
    }
}