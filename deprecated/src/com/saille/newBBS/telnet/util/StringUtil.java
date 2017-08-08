package com.saille.newBBS.telnet.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

    public StringUtil() {
    }

    public static boolean isAlpha(char c) {
        return StringUtils.isAlpha(new String(new char[]{c}));
    }

    public static boolean isPrint(char c) {
        if(c < '\177' && c >= ' ') {
            return true;
        }
        if(c >= '\u3400' && c <= '\u4DB5') {
            return true;
        }
        if(c >= '\u4E00' && c <= '\u9FA5') {
            return true;
        }
        if(c >= '\u9FA6' && c <= '\u9FBB') {
            return true;
        }
        if(c >= '\uF900' && c <= '\uFA2D') {
            return true;
        }
        if(c >= '\uFA30' && c <= '\uFA6A') {
            return true;
        }
        if(c >= '\uFA70' && c <= '\uFAD9') {
            return true;
        }
        if(c >= '\0' && c <= '\0') {
            return true;
        }
        if(c >= '\0' && c <= '\0') {
            return true;
        }
        if((c >= '\uFF00') & (c <= '\uFFEF')) {
            return true;
        }
        if((c >= '\u2E80') & (c <= '\u2EFF')) {
            return true;
        }
        if((c >= '\u3000') & (c <= '\u303F')) {
            return true;
        }
        if((c >= '\u31C0') & (c <= '\u31EF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2FF0') & (c <= '\u2FFF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        if((c >= '\u2F00') & (c <= '\u2FDF')) {
            return true;
        }
        return (c >= '\u2F00') & (c <= '\u2FDF');
    }

    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static char Ctrl(char c) {
        return (char) (c & 0x1f);
    }
}
