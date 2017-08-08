package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ConvertSVN {
    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss, yyyy年M月d日");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            File f = new File("D:\\My Documents\\Sinitek\\portfolio\\531-xxx.txt");
            InputStream is = new FileInputStream(f);
            InputStreamReader stdin = new InputStreamReader(is);
            BufferedReader bufin = new BufferedReader(stdin);

            List list = new ArrayList();
            Entity e = null;
            boolean inInfo = false;
            String info = "";
            String module = "";
            boolean afterInfo = false;
            String tmp;
            while((tmp = bufin.readLine()) != null) {
//                System.out.println(tmp);
                if(tmp.startsWith("版本: ")) {
                    if(e != null) {
                        list.add(e);
                    }
                    if(tmp.substring(4).equals("1")) {
                        break;
                    }
                    e = new Entity();
                    e.version = tmp.substring(4);
                    continue;
                }
                if(tmp.startsWith("作者: ")) {
                    e.author = tmp.substring(4);
                    continue;
                }
                if(tmp.startsWith("日期: ")) {
                    Date d = sdf.parse(tmp.substring(4));
                    e.date = sdf2.format(d);
                    continue;
                }
                if(tmp.startsWith("信息:")) {
                    inInfo = true;
                    info = "";
                    continue;
                }
                if(tmp.startsWith("----")) {
                    info = info.replaceAll("\"", "\"\"");
                    if(info.indexOf("\n") != -1) {
                        info = "\"" + info + "\"";
                    }
                    e.info = info;
                    inInfo = false;
                    afterInfo = true;
                    continue;
                }
                if(inInfo) {
                    if(info.length() > 0) {
                        info = info + "\n";
                    }
                    info = info + tmp;
                    continue;
                }
                if(afterInfo) {
                    if(StringUtils.isEmpty(tmp)) {
                        afterInfo = false;
                        e.module = module;
                        module = "";
                        continue;
                    }
                    String m = tmp.substring(tmp.indexOf("/") + 1);
                    if(m.startsWith("branches")) {
                        m = m.substring("branches/".length());
                        if(m.indexOf("/") != -1) {
                            m = m.substring(0, m.indexOf("/"));
                        }
                    } else {
                        m = "trunk";
                    }

                    if(module.indexOf(m) != -1) {
                        continue;
                    }
                    if(module.length() > 0) {
                        module = module + "*";
                    }
                    module = module + m;
                }

            }

            System.out.println(list.size());
            write(list);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void write(List<Entity> list) throws Exception {
        File f = new File("D:\\My Documents\\Sinitek\\portfolio\\out.txt");
        if(!f.exists()) {
            f.createNewFile();
        }
        OutputStream os = new FileOutputStream(f);
        OutputStreamWriter stdout = new OutputStreamWriter(os);
        BufferedWriter w = new BufferedWriter(stdout);
        for(Entity e : list) {
            w.write(e.version);
            w.write("\t");
            w.write(e.author);
            w.write("\t");
            w.write(e.date);
            w.write("\t");
            w.write(e.info);
            w.write("\t");
            w.write(e.module);
            w.write("\n");
        }
        w.close();
        stdout.close();
        os.close();
    }
}