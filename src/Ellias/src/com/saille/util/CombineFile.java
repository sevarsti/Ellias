package com.saille.util;

import com.GlobalConstant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-10-25
 * Time: 16:07:19
 * To change this template use File | Settings | File Templates.
 */
public class CombineFile {
    public static void main(String[] args) throws Exception {
        try {
            File f = new File("C:\\Documents and Settings\\Ellias\\×ÀÃæ\\apk\\huangdi\\huangdi.src");
            File out = new File(GlobalConstant.DISKPATH + "outEmperor.txt");
            FileOutputStream fos = new FileOutputStream(out);
            r(f, fos);
            fos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void r(File f, FileOutputStream fos) throws Exception {
        if(f.isFile()) {
            FileInputStream fis = new FileInputStream(f);
            fos.write("\r\n".getBytes());
            int size = (int) f.length();
            fos.write(("===========\r\n" + f.getAbsolutePath() + "===========\r\n").getBytes());
            byte[] b = new byte[size];
            fis.read(b);
            fos.write(b);
            fis.close();
            return;
        } else if(f.isDirectory()) {
            File[] ff = f.listFiles();
            for(File fff : ff) {
                r(fff, fos);
            }
        }
    }
}
