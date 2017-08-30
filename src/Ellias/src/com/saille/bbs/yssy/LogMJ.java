package com.saille.bbs.yssy;

import com.txsec.lc.is.security.EncryptUtil;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2012-12-13
 * Time: 22:51:30
 * To change this template use File | Settings | File Templates.
 */
public class LogMJ {
    private static String KEY = "F289A70483E9C779C175F198";
    private final static String pwd = "FK7KDOU42B8NcqBFloktng==";
    private final static String MAIN_PWD = "RnoNZfQ93dg=";
    public static void main(String[] args) {
        System.out.println(EncryptUtil.encode("pmgk", KEY));
//        List<String> mainIds = new ArrayList<String>();
//        mainIds.add("ellias");
//        mainIds.add("ythtforever");
//        try {
//            if(args == null || args.length != 1) {
//                System.out.println("请输入晒mj类型，0：全部；1：<364；2：<119");
//                System.exit(0);
//            }
//            int inType = -1;
//            try {
//                inType = Integer.parseInt(args[0]);
//            } catch(Exception ex) {
//                System.out.println("请输入正确的晒mj类型，0：全部；1：<364；2：<119");
//                System.exit(0);
//            }
//            File f = new File(GlobalConstant.DISKPATH + "vbs\\id.txt");
//            FileReader fr = new FileReader(f);
//            BufferedReader br = new BufferedReader(fr);
//            String id;
//            int type = 0;
//            while((id = br.readLine()) != null) {
//                if(id.trim().equals("***")) {
//                    type++;
//                }
//                if(type >= inType) {
//                    String iid = id.split("\t")[0];
//                    if(!mainIds.contains(iid.toLowerCase())) {
//                        URL url = new URL("http://bbs.sjtu.edu.cn/bbslogin?id=" + iid + "&pw=" + EncryptUtil.decode(pwd, KEY));
//                        url.openStream();
//                    }
//                }
//            }
//        } catch(Exception ex) {
//            ex.printStackTrace();
//        }
    }
}
