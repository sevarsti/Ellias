package com.saille.newBBS;

import com.saille.newBBS.telnet.NewBBSMain;

public class BBSConstant {
    public static final String GUEST = "GUEST";
    public static final String NEW = "NEW";
    public static final int MAX_USERS = 100;
    public static final int SCREEN_COLS = 80;
    public static final int SCREEN_ROWS = 24;
    public static final int LINELEN = 256;
    public static final int KEY_TAB = 9;
    public static final int KEY_ESC = 27;
    public static final int KEY_UP = 257;
    public static final int KEY_DOWN = 258;
    public static final int KEY_RIGHT = 259;
    public static final int KEY_LEFT = 260;
    public static final int KEY_HOME = 513;
    public static final int KEY_INS = 514;
    public static final int KEY_DEL = 515;
    public static final int KEY_END = 516;
    public static final int KEY_PGUP = 517;
    public static final int KEY_PGDN = 518;
    public static final int MODIFIED = 1;
    public static final int STANDOUT = 2;
    public static final int MORE_MODE_COMMON = 0;
    public static final int PERMISSION_DEF_FRIENDCALL = 1;
    public static final int PERMISSION_DEF_ALLMSG = 2;
    public static final int PERMISSION_DEF_FRIENDMSG = 4;
    public static final int PERMISSION_DEF_SOUNDMSG = 8;
    public static final int PERMISSION_DEF_COLOR = 16;
    public static final int PERMISSION_DEF_ACBOARD = 32;
    public static final int PERMISSION_DEF_ENDLINE = 64;
    public static final int PERMISSION_DEF_EDITMSG = 128;
    public static final int PERMISSION_DEF_NOTMSGFRIEND = 256;
    public static final int PERMISSION_DEF_NORMALSCR = 512;
    public static final int PERMISSION_DEF_NEWPOST = 1024;
    public static final int PERMISSION_DEF_CIRCLE = 2048;
    public static final int PERMISSION_DEF_FIRSTNEW = 4096;
    public static final int PERMISSION_DEF_LOGFRIEND = 8192;
    public static final int PERMISSION_DEF_LOGINFROM = 16384;
    public static final int PERMISSION_DEF_NOTEPAD = 32768;
    public static final int PERMISSION_DEF_NOLOGINSEND = 65536;
    public static final int PERMISSION_DEF_THESIS = 131072;
    public static final int PERMISSION_DEF_MSGGETKEY = 262144;
    public static final int PERMISSION_DEF_GRAPH = 524288;
    public static final int PERMISSION_DEF_TOP10 = 1048576;
    public static final int PERMISSION_DEF_RANDSIGN = 2097152;
    public static final int PERMISSION_DEF_S_HOROSCOPE = 4194304;
    public static final int PERMISSION_DEF_COLOREDSEX = 8388608;
    public static final int PERMISSION_DEF_NOT_N_MASK = 16777216;
    public static final int PERMISSION_DEF_DELDBLCHAR = 33554432;
    public static final int PERMISSION_DEF_AUTOWRAP = 67108864;
    public static final int PERMISSION_DEF_USEGB = 134217728;
    public static final int PERMISSION_DEF_NOGMAIL = 268435456;
    public static final int PERMISSION_DEF_BOARDNOTE = 536870912;
    public static final int PERMISSION_DEF_MORENOCURSOR = 1073741824;
    public static final int PERMISSION_DEF_EXT_4 = -2147483648;

    public static boolean DEFINE(NewBBSMain thread, int x) {
        if((thread == null) || (x == 0)) {
            return true;
        }
        User currentuser = thread.getCurrentuser();
        if(currentuser == null) {
            return true;
        }
        return (currentuser.getUserdefine() & x) != 0;
    }
}