package com.saille.util;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-7-9
 * Time: 16:41:03
 * To change this template use File | Settings | File Templates.
 */
public class IOUtils {
    public static String readLine() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String ret = br.readLine();
        return ret;
    }
}
