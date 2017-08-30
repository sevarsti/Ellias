package com.saille.rm;

import com.GlobalConstant;
import com.saille.util.IOUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-6-30
 * Time: 13:28:24
 * To change this template use File | Settings | File Templates.
 */
public class ChangeFileName {
    private static String scanDir = GlobalConstant.DISKPATH + "rm\\imageTemp";
    public static void main(String[] args) {
        try {
            System.out.println("input scan directory:");
            String filename = IOUtils.readLine();
            if(filename == null || filename.trim().length() == 0) {
                filename = scanDir;
            }
            File f = new File(filename);
            if(f.exists() && f.isDirectory()) {
                File[] files = f.listFiles();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmmss");
                for(File ff : files) {
                    String suffix = ff.getName().substring(ff.getName().lastIndexOf("."));
                    File newfile = new File(f.getCanonicalPath() + File.separator + sdf.format(new Date(ff.lastModified())) + suffix);
                    ff.renameTo(newfile);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
