package com.saille.util;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-3-15
 * Time: 9:29:27
 * To change this template use File | Settings | File Templates.
 */
public class SendSMSUtils {
    private final static String APPKEY = "09412191ee0644db8ece67dbe3648c26";
    private final static Logger LOGGER = Logger.getLogger(SendSMSUtils.class);

    public static void sendSMS(String templateId, String param) {
        try {
            HttpPost pm = new HttpPost("http://v1.avatardata.cn/Sms/Send");
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("key", APPKEY));
            params.add(new BasicNameValuePair("mobile", "13818207760"));
            params.add(new BasicNameValuePair("templateId", templateId));
            params.add(new BasicNameValuePair("param", param));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            DefaultHttpClient client = new DefaultHttpClient();
            CloseableHttpResponse resp = client.execute(pm);
            String ret = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
//            System.out.println(ret);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            LOGGER.info(sdf.format(new Date()) + "\t发送短信，模板ID=" + templateId);
            LOGGER.info("发送结果：" + ret);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
