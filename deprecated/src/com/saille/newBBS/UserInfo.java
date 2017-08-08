package com.saille.newBBS;

import com.saille.newBBS.telnet.Screenline;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class UserInfo {
    public final int OBUFSIZE = 4096;
    private String thread;
    private int cur_col;
    private int cur_ln;
    private int obufsize = 0;
    private String outbuf;
    private boolean ansiMode;
    private List<Screenline> big_picture;
    private int t_lines = 24;
    private int t_columns = 255;
    private boolean inansi;
    private boolean iscolor;
    private boolean dumb_term;
    private boolean showansi;
    private int roll;
    private int scr_lns;
    private int scr_cols;
    private boolean standing;
    private int docls;
    private int downfrom;
    private int more_mode;
    private int promptend;
    private boolean cursormode_def;
    private int more_size;

    public void oflush(PrintStream out) throws IOException {
        if(this.obufsize > 0) {
            out.write(this.outbuf.getBytes());
            this.obufsize = 0;
        }
    }

    public int ochar(int c, PrintStream out) throws IOException {
        if(this.obufsize > 4094) {
            oflush(out);
        }

        int size = this.obufsize;

        this.outbuf += (char) c;
        size++;

        this.obufsize = size;
        return c;
    }

    public UserInfo(String name) {
        this.thread = name;
        this.cur_col = 1;
        this.cur_ln = 1;
    }

    public String getThread() {
        return this.thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public int getCur_col() {
        return this.cur_col;
    }

    public void setCur_col(int cur_col) {
        this.cur_col = cur_col;
    }

    public int getCur_ln() {
        return this.cur_ln;
    }

    public void setCur_ln(int cur_ln) {
        this.cur_ln = cur_ln;
    }

    public List<Screenline> getBig_picture() {
        return this.big_picture;
    }

    public void setBig_picture(List<Screenline> big_picture) {
        this.big_picture = big_picture;
    }

    public boolean isInansi() {
        return this.inansi;
    }

    public void setInansi(boolean inansi) {
        this.inansi = inansi;
    }

    public boolean isIscolor() {
        return this.iscolor;
    }

    public void setIscolor(boolean iscolor) {
        this.iscolor = iscolor;
    }

    public boolean isDumb_term() {
        return this.dumb_term;
    }

    public void setDumb_term(boolean dumb_term) {
        this.dumb_term = dumb_term;
    }

    public boolean isShowansi() {
        return this.showansi;
    }

    public void setShowansi(boolean showansi) {
        this.showansi = showansi;
    }

    public int getRoll() {
        return this.roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getScr_lns() {
        return this.scr_lns;
    }

    public void setScr_lns(int scr_lns) {
        this.scr_lns = scr_lns;
    }

    public boolean isStanding() {
        return this.standing;
    }

    public void setStanding(boolean standing) {
        this.standing = standing;
    }

    public int getScr_cols() {
        return this.scr_cols;
    }

    public void setScr_cols(int scr_cols) {
        this.scr_cols = scr_cols;
    }

    public int getMore_mode() {
        return this.more_mode;
    }

    public void setMore_mode(int more_mode) {
        this.more_mode = more_mode;
    }

    public int getPromptend() {
        return this.promptend;
    }

    public void setPromptend(int promptend) {
        this.promptend = promptend;
    }

    public int getDocls() {
        return this.docls;
    }

    public void setDocls(int docls) {
        this.docls = docls;
    }

    public int getDownfrom() {
        return this.downfrom;
    }

    public void setDownfrom(int downfrom) {
        this.downfrom = downfrom;
    }

    public int getT_columns() {
        return this.t_columns;
    }

    public void setT_columns(int t_columns) {
        this.t_columns = t_columns;
    }

    public int getT_lines() {
        return this.t_lines;
    }

    public void setT_lines(int t_lines) {
        this.t_lines = t_lines;
    }

    public boolean getCursormode_def() {
        return this.cursormode_def;
    }

    public void setCursormode_def(boolean cursormode_def) {
        this.cursormode_def = cursormode_def;
    }

    public int getMore_size() {
        return this.more_size;
    }

    public void setMore_size(int more_size) {
        this.more_size = more_size;
    }

    public boolean getAnsiMode() {
        return this.ansiMode;
    }

    public void setAnsiMode(boolean ansiMode) {
        this.ansiMode = ansiMode;
    }
}