package com.saille.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FileUtils {
    public static synchronized void WriteFile(File f, String content, boolean append) {
        try {
            if(!f.exists()) {
                f.createNewFile();
            }
            OutputStream os = new FileOutputStream(f, append);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter writer = new BufferedWriter(osw);
            writer.write(content);
            writer.close();
            osw.close();
            os.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}