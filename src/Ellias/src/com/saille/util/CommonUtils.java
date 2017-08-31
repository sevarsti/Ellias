package com.saille.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommonUtils {
    private final static Logger LOGGER = Logger.getLogger(CommonUtils.class);
    public static String getString(InputStream is, String charset) throws Exception {
        List list = new ArrayList();
        int i;
        byte[] bytes = new byte[4096];
        while((i = is.read(bytes)) > 0) {
            list.addAll(Arrays.asList(ArrayUtils.toObject(Arrays.copyOfRange(bytes, 0, i))));
//            list.add(Byte.valueOf((byte) i));
        }
        Byte[] bb = new Byte[list.size()];
        list.toArray(bb);
        bytes = ArrayUtils.toPrimitive(bb);
//        for(int ii = 0; ii < list.size(); ii++) {
//            bb[ii] = ((Byte) list.get(ii)).byteValue();
//        }
        String ret = new String(bytes, charset);
        return ret;
    }

    public static boolean hasSystemProcess(String filter) {
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb = pb.command("tasklist");
            Process p = pb.start();
            BufferedReader out = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getInputStream()), Charset.forName("GB2312")));
            BufferedReader err = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getErrorStream())));
            String ostr;

            StringBuilder sb = new StringBuilder();
            while((ostr = out.readLine()) != null) {
                if(ostr.indexOf("没有运行的任务匹配指定标准") >= 0) {
                    return true;
                }
                sb.append(ostr).append("\n");
                if(ostr.toUpperCase().indexOf(filter) >= 0) {
                    return true;
                }
            }
            LOGGER.info("*********************");
            LOGGER.info(sb.toString());
            LOGGER.info("*********************");
            String estr = err.readLine();
            if(estr != null) {
                LOGGER.info("\nError Info");
                LOGGER.info(estr);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}