package com.saille.newBBS.telnet.util;

import com.saille.newBBS.telnet.NewBBSMain;

import java.io.IOException;

public class RegisterID {
    public static void doRegister(NewBBSMain thread) throws IOException {
        IO.clearScreen(thread);
        IO.moveTo(thread, 1, 1);
        ShowArticle.ShowArticle(thread, "D:\\work\\Ellias\\exploded\\etc\\doregist.txt");
    }
}