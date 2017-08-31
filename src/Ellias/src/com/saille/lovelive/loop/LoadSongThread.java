package com.saille.lovelive.loop;

import com.saille.sys.BaseThread;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import java.io.*;

/**
 * Created by Ellias on 2017-08-31.
 */
public class LoadSongThread extends BaseThread{
    private final static Logger LOGGER = Logger.getLogger(LoadSongThread.class);
    public static void main(String[] args) {
        new LoadSongThread().execute();
    }

    @Override
    protected int execute() {
        try {
            String filepath = "F:" + "\\temp\\llcard.txt";
            File tempfile = new File(filepath);
            if(!tempfile.exists()) {
                tempfile.createNewFile();
            }
//            downloadcard(filepath);
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer msg = new StringBuffer();
            String tmp;
            while ((tmp = br.readLine()) != null) {
                msg.append(tmp);
            }
            String s = msg.substring(11, msg.length() - 2);
//            new org.json.JSONObject("\"a\":["+s+"]");
//            String s2 = msg.substring(4, msg.length() - 1).toString();
            int count = 0;
            String[] parts = s.split("\\{\\\"navi\\\"");
//            while (s.indexOf("{\"navi") >= 0) {
            for (String sub : parts) {
//                String sub = "{\"navi" + s.substring(0, s.indexOf("{\"navi"));
                if(sub.length() == 0) {
                    continue;
                }
                sub = "{\"navi\"" + sub.substring(0, sub.length() - 1);
//                s = s.substring(s.indexOf("{\"navi") + "{\"navi".length());
                System.out.println(new JSONObject(sub).getString("id") + "\t" + count);
                count++;
            }
            System.out.println(s);
            System.out.println(s);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    private void downloadcard(String filepath) throws Exception {
        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
        HttpGet gm = new HttpGet("https://app.lovelivewiki.com/js/cards.js");
        CloseableHttpResponse response = client.execute(gm);
        Header[] headers = response.getHeaders("Content-Length");
        InputStream is = response.getEntity().getContent();
//        if(headers.length > 0) {
//            byte[] bytes = new byte[Integer.parseInt(response.getHeaders("Content-Length")[0].getValue())];
//            int i = is.read(bytes);
//            if(i < bytes.length) {
//                bytes = ArrayUtils.subarray(bytes, 0, i);
//            }
//            System.out.println("read " + i + " bytes");
//            System.out.println(new String(bytes));
//        } else {
            FileOutputStream fos = new FileOutputStream(filepath);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            byte[] bytes = new byte[81920];
            int i = 0;
            while ((i = is.read(bytes)) > 0) {
                bos.write(bytes, 0, i);
                bos.flush();
            }
            response.getEntity().getContent().close();
            response.close();
            gm.releaseConnection();
            bos.close();
            fos.close();
//        }
    }
}
