package com.saille.rm;

import com.saille.sys.Setting;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.util.Date;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2015-12-24
 * Time: 11:41:46
 * To change this template use File | Settings | File Templates.
 */
public class DownloadZipUtil {
    private static Date lastExecuteTime = null;
    public static synchronized void download() {
        try {
            if(lastExecuteTime != null) {
                if((new Date().getTime() - lastExecuteTime.getTime()) < 1000 * 60 * 5) {
                    return;
                }
            }
            String url = "http://game.ds.qq.com/Com_TableCom_Android_Bin/TableComBin.zip";
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet gm = new HttpGet(url);
            HttpResponse response = client.execute(gm);
            ZipInputStream zis = new ZipInputStream(response.getEntity().getContent());
            while(true) {
                ZipEntry entry = zis.getNextEntry();
                if(entry == null) {
                    break;
                }
                String filename = entry.getName();
                String rmpath = Setting.getSettingString("RM_PATH");
                if(rmpath == null) {
                    rmpath = "D:\\rm\\";
                }
                File f = new File(rmpath + "TableComBin\\" + filename);
                if(f.exists() && f.lastModified() < entry.getTime()) {
                    String bakfilename = "D:\\rm\\" + "TableComBin\\deprecated\\";
                    bakfilename += f.getName().substring(0, f.getName().lastIndexOf(".") + 1);
                    bakfilename += new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(f.lastModified()));
                    bakfilename += f.getName().substring(f.getName().lastIndexOf("."));
                    File bakfile = new File(bakfilename);
                    if(bakfile.exists()) {
                        bakfile.delete();
                    }
                    bakfile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(bakfile);
                    FileInputStream fis = new FileInputStream(f);
                    int i;
                    while((i = fis.read()) >= 0) {
                        fos.write(i);
                    }
                    fos.close();
                    fis.close();
                    f.delete();
                    f.createNewFile();
                } else if(f.exists() && f.lastModified() >= entry.getTime()) {
                    continue;
                } else {
                }
                FileOutputStream fos = new FileOutputStream(f);
                byte[] save = new byte[1024];
                int writecount = 0;
                while((writecount = zis.read(save)) > 0) {
                    fos.write(save, 0, writecount);
                }
                fos.close();
                f.setLastModified(entry.getTime());
            }
            zis.close();
            lastExecuteTime = new Date();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
