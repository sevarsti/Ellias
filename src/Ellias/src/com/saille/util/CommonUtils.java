package com.saille.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CommonUtils {
    public static String getString(InputStream is, String charset) throws Exception {
        List list = new ArrayList();
        int i;
        while((i = is.read()) != -1) {
            list.add(Byte.valueOf((byte) i));
        }
        byte[] bb = new byte[list.size()];
        for(int ii = 0; ii < list.size(); ii++) {
            bb[ii] = ((Byte) list.get(ii)).byteValue();
        }
        String ret = new String(bb, charset);
        return ret;
    }
}