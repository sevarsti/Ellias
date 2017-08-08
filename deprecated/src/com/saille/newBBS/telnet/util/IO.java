package com.saille.newBBS.telnet.util;

import com.saille.newBBS.UserInfo;
import com.saille.newBBS.telnet.NewBBSMain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.log4j.Logger;

public class IO {
    private static final Logger LOGGER = Logger.getLogger(IO.class);

    public static void clearScreen(NewBBSMain thread) throws IOException {
        moveTo(thread, 24, 80);
        print(thread, "\033[1J");
    }

    public static void moveTo(NewBBSMain thread, int x, int y) throws IOException {
        thread.getOut().print("\033[" + x + ";" + y + "H");
        thread.getUserInfo().setCur_col(x);
        thread.getUserInfo().setCur_ln(y);
    }

    public static void moveLeft(NewBBSMain thread, int num) throws IOException {
        thread.getOut().print("\033[" + num + "D");
        thread.getUserInfo().setCur_col(thread.getUserInfo().getCur_col() - 1);
    }

    public static void moveRight(NewBBSMain thread, int num) throws IOException {
        thread.getOut().print("\033[" + num + "C");
        thread.getUserInfo().setCur_col(thread.getUserInfo().getCur_col() + 1);
    }

    public static void println(NewBBSMain thread, String string) throws IOException {
        print(thread, string);
        print(thread, "\n");
    }

    public static void printChar(NewBBSMain thread, char ch) throws IOException {
        if(ch == '\r') {
            return;
        }
        PrintStream out = thread.getOut();
        UserInfo userInfo = thread.getUserInfo();

        if(userInfo.getAnsiMode()) {
            switch(ch) {
                case'A':
                    break;
                case'B':
                    break;
                case'C':
                    break;
                case'D':
            }

        }

//        String s = ch;

        out.print(ch);
        if(ch == '\n') {
            out.print('\r');
        }
    }

    public static void print(NewBBSMain thread, String string) throws IOException {
        UserInfo userInfo = thread.getUserInfo();
        if(string == null) {
            return;
        }
        for(int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            printChar(thread, c);
        }
    }

    public static char getChar(NewBBSMain thread) throws IOException {
        int ch = thread.getIn().read();
        return (char) ch;
    }

    public static char getKey(NewBBSMain thread) throws IOException {
        PrintStream out = thread.getOut();
        BufferedReader in = thread.getIn();
        UserInfo userInfo = thread.getUserInfo();
        char last;
        int mode = last = 0;
        while(true) {
            char ch = getChar(thread);
            if(mode == 0) {
                if(ch == '\033') {
                    mode = 1;
                } else {
                    return ch;
                }
            } else if(mode == 1) {
                if((ch == '[') || (ch == 'O')) {
                    mode = 2;
                } else if((ch == '1') || (ch == '4')) {
                    mode = 3;
                } else {
                    return '\033';
                }
            } else if(mode == 2) {
                if((ch >= 'A') && (ch <= 'D')) {
                    return (char) (257 + (ch - 'A'));
                }
                if((ch >= '1') && (ch <= '6')) {
                    mode = 3;
                } else {
                    return ch;
                }
            } else if(mode == 3) {
                if(ch == '~') {
                    return (char) (513 + (last - '1'));
                }
                return ch;
            }

            last = ch;
        }
    }

    public static String getString(NewBBSMain thread, int length, boolean show) throws IOException {
        PrintStream out = thread.getOut();
        StringBuffer ret = new StringBuffer("");

        int curLength = 0;
        char ch;
        while((ch = getKey(thread)) != '\r') {
            if(ch == StringUtil.Ctrl('h')) {
                if(curLength == 0) {
                    continue;
                }
                curLength--;
                printChar(thread, StringUtil.Ctrl('h'));
                printChar(thread, ' ');
                printChar(thread, StringUtil.Ctrl('h'));
            }
            if((curLength >= length) || (!StringUtil.isPrint(ch))) {
                continue;
            }
            if(show) {
                printChar(thread, ch);
            } else {
                printChar(thread, '*');
            }
            ret.append(ch);
            curLength++;
        }

        printChar(thread, '\n');
        return ret.toString();
    }
}