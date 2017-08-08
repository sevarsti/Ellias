package com.saille.newBBS.telnet.util;

import com.saille.newBBS.telnet.NewBBSMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShowArticle {
    public static void ShowArticle(NewBBSMain thread, String filename) throws IOException {
        File f = new File(filename);
        FileInputStream fis = new FileInputStream(f);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        StringBuffer sb = new StringBuffer();
        String text = null;
        while((text = br.readLine()) != null) {
            sb.append(text).append("\n");
        }
        text = sb.toString();

        int linenum = 0;
        int len = 0;
        boolean in_esc = false;
        int bytes = 0;
        int c = 0;
        int link_candi = 0;
        int link_len = 0;

        for(int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if(ch == '\n') {
                linenum++;
                len = 0;
                in_esc = false;
                bytes = 0;
                c = 0;
                link_candi = 0;
                link_len = 0;
                IO.println(thread, "");
            } else if(ch == '\t') {
                do {
                    len++;
                } while(len % 4 != 0);
            } else if(ch == '\033') {
                c = 0;
                link_candi = 0;
                IO.printChar(thread, ch);
                in_esc = true;
            } else if(in_esc) {
                IO.printChar(thread, ch);
                if("[0123456789;,".indexOf(ch) == -1) {
                    in_esc = false;
                }
            } else if(StringUtil.isPrint(ch)) {
                if((ch != '[') || ((ch & 0x80) != 0)) {
                    c++;
                } else {
                    c = 0;
                }
                if(len >= 80) {
                    len = 0;
                    if((c > 0) && (c % 2 == 0)) {
                        IO.moveLeft(thread, 1);
                        IO.println(thread, " ");
                    } else {
                        IO.println(thread, "");
                    }
                }
                len++;
                IO.printChar(thread, ch);
            }
        }
    }
}