package test;

import com.baidubce.util.DateUtils;
import com.baidubce.util.HttpUtils;
import com.saille.util.CommonUtils;
import com.saille.util.IOUtils;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URLEncoder;
import java.net.URLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-10-26
 * Time: 18:02:32
 * To change this template use File | Settings | File Templates.
 */
public class Test{
    public final static int size = 10;
    public static boolean ok[] = new boolean[10];
    public static int getNext() {
        for(int i = 0; i < ok.length; i++) {
            if(ok[i]) {
                return i;
            }
        }
        return -1;
    }
    private static int high(int in) {
        while(in > 9) {
            in /= 10;
        }
        return in;
    }
    private static int[] yueshu(int in) {
        int[] ret = new int[10];
        int max = (int)Math.sqrt(in);
        for(int i = 2; i <= max; i++) {
            if(in % i == 0) {
                ret[high(i)]++;
            }
        }
        ret[1]++;
        if(in != 1) {
            ret[high(in)]++;
        }
        return ret;
    }
    static int[] combine(int[] full, int[] cur) {
        for(int i = 0; i < full.length; i++) {
            full[i] += cur[i];
        }
        return full;
    }
    public static void main(String[] args) {
//        int i = 0;
        try {
            if(true) {
                int[] result = new int[10];
                Scanner in = new Scanner(System.in);
                int begin = in.nextInt();
                int end = in.nextInt();
                long now = System.currentTimeMillis();
                for(int i = begin; i <=end; i++) {
                    int[] res = yueshu(i);
                    result = combine(result, res);
                }
                now = System.currentTimeMillis() - now;
                for(int i = 1; i < 10; i++) {
                    System.out.println(result[i]);
                }
                System.out.println("cost" + now);
                System.exit(0);
            }
            if(false) {
                URL url = new URL("http://ll.webpatch.sdg-china.com/ll/prod/4.0.83/package/android/813d94519b012ad49973308fb0d067ec.1493032106");
                InputStream is = url.openStream();
//                ZipInputStream zis = new ZipInputStream(is);
//                System.out.println(zis);
                FileOutputStream fos = new FileOutputStream("D:\\fjwie.zip");
                byte[] bytes = new byte[1024];
                int i = 0;
                while((i = is.read(bytes)) > 0) {
                    fos.write(bytes, 0, i);
                }
                fos.close();
                is.close();
            }
            if(false) {
                File dir = new File("D:\\rm\\TableComBin");
                File[] files = dir.listFiles();
                for(File f : files) {
                    if(f.getName().indexOf("副本") >= 0) {
                        continue;
                    }
                    FileInputStream fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int)f.length()];
                    fis.read(bytes);
                    fis.close();
                    for(int i = 0; i < bytes.length - 2; i++) {
                        int b1 = bytes[i], b2 = bytes[i + 1], b3 = bytes[i + 2];
                        if(b1 < 0) b1 = 256 + b1;
                        if(b2 < 0) b2 = 256 + b2;
                        if(b3 < 0) b3 = 256 + b3;
                        if(b1 == 139 && b2 == 65 && b3 == 5) {
                            System.out.println(i + "\t" + f.getName());
                        }
                    }
                }
                System.exit(0);
            }
            if(false) {
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                HttpPost pm = new HttpPost("http://v1.avatardata.cn/Sms/Send");
                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("key", "09412191ee0644db8ece67dbe3648c26"));
                params.add(new BasicNameValuePair("mobile", "13818207760"));
                params.add(new BasicNameValuePair("templateId", "a09c3a5bfec541b4b6078ea6f56de6b8"));
                params.add(new BasicNameValuePair("param", "20170101"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                DefaultHttpClient client = new DefaultHttpClient();
                CloseableHttpResponse resp = client.execute(pm);
                String ret = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
                System.out.println(ret);
                System.exit(0);
            }
            if(false) {
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet gm = new HttpGet("http://llhelper.duapp.com/llnewcarddata");
                CloseableHttpResponse resp = client.execute(gm);
                InputStream is = resp.getEntity().getContent();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String tmp;
                while((tmp = br.readLine()) != null) {
                    if(tmp.indexOf("var cardsjson ") >= 0) {
                        break;
                    }
                }
                String cards = tmp.substring(tmp.indexOf("\"") + 1);
                cards = cards.substring(0, cards.indexOf("replace"));
                cards = cards.substring(0, cards.lastIndexOf("\""));
                cards = cards.replaceAll("&#34;", "\"").replaceAll("&#39;", "'");
                JSONObject json = new JSONObject(cards);
                br.close();
                isr.close();
                is.close();
//                String cards = CommonUtils.getString(resp.getEntity().getContent(), "GBK");

            }
            if(true) {
                String url = "http://ll.webpatch.sdg-china.com/ll/prod/4.0.83/micro_download/android/assets/image/unit/22003001/tx_u_22003001_normal_icon.texb";
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet gm = new HttpGet(url);
                CloseableHttpResponse resp = client.execute(gm);
                InputStream is = resp.getEntity().getContent();
//                String cards = CommonUtils.getString(is, "GBK");
//                ZipInputStream zis = new ZipInputStream(is);
                FileOutputStream fos = new FileOutputStream("D:\\patch.zip");
                byte[] bytes = new byte[1024];
                int c = 0;
                while((c = is.read(bytes)) > 0) {
                    fos.write(bytes, 0, c);
                }
                fos.close();
                is.close();
                System.exit(0);
            }
            if(false) { //ll卡牌更新
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet gm = new HttpGet("https://app.lovelivewiki.com/js/cards.js");
                CloseableHttpResponse resp = client.execute(gm);
                InputStream is = resp.getEntity().getContent();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String cards = br.readLine();
                br.close();
                isr.close();
                is.close();
//                String cards = CommonUtils.getString(resp.getEntity().getContent(), "GBK");
                resp.close();
                cards = "{cards:" + cards.substring(cards.indexOf("["));
                cards = cards.substring(0, cards.length() - 1);
                cards += "}";
                JSONObject json = null;
                json = new JSONObject(cards);
                JSONArray arrays = json.getJSONArray("cards");
                for(int i = 0; i < arrays.length(); i++) {
                    JSONObject obj = arrays.getJSONObject(i);
                    System.out.print(obj.opt("id") + "\t");
                    System.out.print(obj.opt("order") + "\t");
                    System.out.print(obj.opt("rarity") + "\t");
                    System.out.print(obj.opt("name") + "\t");
                    System.out.print(obj.opt("attribute") + "\t");
                    System.out.print(obj.opt(obj.opt("attribute") + "_max") + "\t");
                    System.out.print(obj.opt("type") + "\t");
                    String series = obj.optString("series");
                    System.out.print((series.equals("[]") ? "" : series) + "\t");
                    System.out.print(obj.optString("leader") + "\t");
                    JSONObject obj2 = obj.optJSONObject("skill");
//                    String eponym = obj.getJSONObject("skill").optString("name");
                    System.out.print((obj2 == null ? "" : obj2.optString("name", "")) + "\t");
                    JSONObject skills = obj.optJSONObject("skill");
                    if(skills != null) {
                        JSONArray list = skills.getJSONArray("text");
                        System.out.print(list.getString(list.length() - 1) + "\t");
                    }
                    System.out.println();
                }
            }
            if(false) {
                File dir = new File("D:\\rm\\song");
                File[] ff = dir.listFiles();
                Pattern p = Pattern.compile("(.+\\.imd)\\.\\d+");
                for(File f : ff) {
                    File[] fff = f.listFiles();
                    for(File ffff : fff) {
                        String name = ffff.getName();
                        Matcher m = p.matcher(name);
                        if(m.matches()) {
                            File nf = new File(ffff.getParent() + "\\" + m.group(1));
                            System.out.println(nf.getName() + new SimpleDateFormat(" yyyyMMdd").format(new Date(nf.lastModified())));
                        }
                    }
                }
            }
            if(false) {
//                String exponent = "010001";
//                String modulus = "00a0b4f194a2e4b356708c0334bac667515f5267e159a804bcf3dc48a43aaac7de19db6f3b355adce3628582aa6d486d57e7fedd20634446b99dcb52871f71700f5f15376084f6de37316bff6e2f0f636ee3002a24c5148f67c85923af07a96e028ae686f6d040b5acca8014f701b2c8b425ead99a5f89a3e270d80db86341eee3";
//                ScriptEngineManager sem = new ScriptEngineManager();
//                ScriptEngine se = sem.getEngineByName("js");
//                se.eval(jsScript);
//                Invocable inv = (Invocable) se;
//String res = (String) inv.invokeFunction("test", new Object[]{exponent, "", modulus, "zloves"});
////                 Map<String, Object> key = getKeyPair(exponent, "", modulus);
//                System.out.println(res);

//                DefaultHttpClient client = new DefaultHttpClient();
//                HttpPost pm = new HttpPost("http://youxi.baidu.com/sso/login/security/login.json");
//                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("loginName", "Ellias"));
//                params.add(new BasicNameValuePair("password", res));
//                params.add(new BasicNameValuePair("platformId", "1"));
//                params.add(new BasicNameValuePair("autoLogin", "true"));
//                params.add(new BasicNameValuePair("mergeType", "0"));
//                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//                CloseableHttpResponse resp = client.execute(pm);
//                String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
//                System.out.println(msg);

//                HttpPost pm = new HttpPost("http://youxi.baidu.com/sso/login/security/login.json");
//                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("loginName", "Ellias"));
//                params.add(new BasicNameValuePair("password", res));
//                params.add(new BasicNameValuePair("platformId", "1"));
//                params.add(new BasicNameValuePair("autoLogin", "true"));
//                params.add(new BasicNameValuePair("mergeType", "0"));
//                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//                CloseableHttpResponse resp = client.execute(pm);
//                String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

                System.exit(0);
//        var encryptPassword = RSAUtils.encryptedString(key, password);
            }
            if(false) {
                String accesskeyid = "a57dac57f60047ccab67151ffa5cbd54";
                String secretaccesskey = "31ffa06873584dfcbd11697944635479";

                DefaultHttpClient client = new DefaultHttpClient();
                Calendar c = Calendar.getInstance();
                Date date = c.getTime();
//                String host = "bos.bj.baidubce.com";
                String host = "apis.baidu.com";
//                String requesturi = "/v1/ellias-excel";
                String requesturi = "/apistore/idservice/id?id=310105198408081219";
                String contenttype = "application/json; charset=utf-8";
                HttpGet pm = new HttpGet("http://" + host + requesturi);
                pm.addHeader("Date", DateUtils.formatRfc822Date(c.getTime()));
                pm.addHeader("Content-type", contenttype);
//                pm.addHeader("Host", "ocr.bj.baidubce.com");
                pm.addHeader("Host", host);
                pm.addHeader("apikey", "ebac072334872562391c5e514f90f1e0");
//                pm.addHeader("Content-length", queryString.length() + "");

//                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("bosPath", "ellias-backup/call.do.jpg"));
//                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                String method = pm.getMethod();
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Host", host);
//                headers.put("Content-type", contenttype);
//                headers.put("Content-length", queryString.length() + "");

//                String authorization = getKey(method, requesturi, headers, accesskeyid, secretaccesskey, date, queryString);

//                pm.addHeader("Authorization", authorization);

                for(Header h : pm.getAllHeaders()) {
                    System.out.println(h.getName() + "=" + h.getValue());
                }
//                Thread.sleep(5000);
                HttpResponse response = client.execute(pm);
                String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                JSONObject obj = new JSONObject(ret);
                System.out.println(new JSONObject(ret));
                System.exit(0);
            }
            if(false) {
                HttpClient client = new DefaultHttpClient();
                HttpPost pm = new HttpPost("https://graph.qq.com/weiyun/get_music_list");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("offset", "0"));
                params.add(new BasicNameValuePair("number", "100"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                HttpResponse response = client.execute(pm);
                String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                System.out.println(ret);
            }
            if(false) {
                String accesskeyid = "a57dac57f60047ccab67151ffa5cbd54";
                String secretaccesskey = "31ffa06873584dfcbd11697944635479";

                DefaultHttpClient client = new DefaultHttpClient();
                Calendar c = Calendar.getInstance();
                Date date = c.getTime();
//                String host = "bos.bj.baidubce.com";
                String host = "ocr.bj.baidubce.com";
//                String requesturi = "/v1/ellias-excel";
                String requesturi = "/v1/recognize/line";
                String contenttype = "application/json; charset=utf-8";
//                HttpPost pm = new HttpPost("http://ocr.bj.baidubce.com/v1/recognize/line");
                String queryString = "bosPath=ellias-backup/call.do.jpg";
                HttpPost pm = new HttpPost("http://" + host + requesturi);
                pm.addHeader("Date", DateUtils.formatRfc822Date(c.getTime()));
                pm.addHeader("Content-type", contenttype);
//                pm.addHeader("Host", "ocr.bj.baidubce.com");
                pm.addHeader("Host", host);
//                pm.addHeader("Content-length", queryString.length() + "");

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("bosPath", "ellias-backup/call.do.jpg"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                String method = pm.getMethod();
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Host", host);
                headers.put("Content-type", contenttype);
                headers.put("Content-length", queryString.length() + "");

                String authorization = getKey(method, requesturi, headers, accesskeyid, secretaccesskey, date, queryString);

                pm.addHeader("Authorization", authorization);

                for(Header h : pm.getAllHeaders()) {
                    System.out.println(h.getName() + "=" + h.getValue());
                }
//                Thread.sleep(5000);
                HttpResponse response = client.execute(pm);
                String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                System.out.println(ret);
            }
            if(false) {
                String ak = "AYdvDMsaGG9fYBw8blFPGkiL";
                String location = "上海";
//                String location = URLEncoder.encode("上海");
                Map<String, String> paramsMap = new HashMap<String, String>();
                paramsMap.put("location", location);
                paramsMap.put("output", "json");
                paramsMap.put("ak", ak);
                String paramsStr = toQueryString(paramsMap);
                String wholeStr = new String("/geocoder/v2/?" + paramsStr + ak);
                String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
                String sn = MD5(tempStr);
                String url = "http://api.map.baidu.com/telematics/v3/weather";
                HttpClient client = new DefaultHttpClient();

//                HttpGet gm = new HttpGet("http://apistore.baidu.com/microservice/cityinfo?cityname=" + location);
//                HttpResponse response = client.execute(gm);
//                String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
//                System.out.println(ret);
//                HttpPost pm = new HttpPost(url);
//                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("location", location));
//                params.add(new BasicNameValuePair("output", "json"));
//                params.add(new BasicNameValuePair("ak", ak));
//                params.add(new BasicNameValuePair("sn", sn));
//                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                HttpGet gm = new HttpGet(url + "?location=" + location + "&output=json&ak=" + ak + "&sn=" + sn);
                HttpResponse response = client.execute(gm);
                String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                JSONObject obj = new JSONObject(ret);
                if("0".equals(obj.getString("error"))) {

                }
                System.out.println(ret);
            }
        } catch(Exception ex) {
//            System.out.println("i = " + i);
            ex.printStackTrace();
        }
    }

    public static String toQueryString(Map<?, ?> data)
            throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),
                    "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static synchronized void log(String value) {
        try {
            File f = new File("D:\\club.log");
            if(!f.exists()) {
                f.createNewFile();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            FileOutputStream fos = new FileOutputStream(f, true);
            StringBuffer sb = new StringBuffer(value + "\r\n");
            fos.write(sb.toString().getBytes());
            fos.close();
        } catch(Exception ex) {
ex.printStackTrace();
        }
    }

    public static String getKey(String method, String requesturi, Map<String, String> headers, String accesskey, String secreatkey, Date date, String queryString) throws Exception {
//        String date = DateUtils.formatRfc822Date(new Date());
//        String formatdate = DateUtils.formatAlternateIso8601Date(new Date());

//        date = "2015-12-09T02:32:29Z";
//        formatdate = "Wed, 09 Dec 2015 02:32:29 GMT";


        String authString =
                "bce-auth-v1" + "/" + accesskey + "/"
                        + DateUtils.formatAlternateIso8601Date(date) + "/" + "1800";
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secreatkey.getBytes(), "HmacSHA256"));
        String signkey = new String(Hex.encodeHex(mac.doFinal(authString.getBytes())));
        System.out.println("signkey=" + signkey);
        String canonicalURI = requesturi;
        System.out.println("canonicalURI=" + canonicalURI);
        String canonicalQueryString = HttpUtils.normalize(queryString.substring(0, queryString.indexOf("="))) + "=" + HttpUtils.normalize(queryString.substring(queryString.indexOf("=") + 1));
        System.out.println("canonicalQueryString=" + canonicalQueryString);
        String canonicalHeaders = "content-type:" + HttpUtils.normalize(headers.get("Content-type")) + "\n" + "host:" + HttpUtils.normalize(headers.get("Host"));
        System.out.println("canonicalHeaders=" + canonicalHeaders);

        String canonicalRequest = method + "\n" + canonicalURI + "\n" + canonicalQueryString + "\n" + canonicalHeaders;
        System.out.println("canonicalRequest=" + canonicalRequest);
        mac.init(new SecretKeySpec(signkey.getBytes(), "HmacSHA256"));
        String signature = new String(Hex.encodeHex(mac.doFinal(canonicalRequest.getBytes())));
        String authorizationHeader = signature;
        System.out.println("authorizationHeader="+authorizationHeader);

        String authorization = authString + "/" + "content-type;host" + "/" + authorizationHeader;
        System.out.println(authorization);
        return authorization;
    }

    public static int byte2int(byte[] bb) {
        int ret = 0;
        for(int i = 0; i < bb.length; i++) {
            ret += Math.pow(256, i) * (int)(bb[i] < 0 ? (256 + bb[i]) : bb[i]);
        }
        return ret;
    }

    private static int loadSingleImd(String path) throws Exception {
        File f = new File(path);
        boolean showlog = false;
        byte[] bb = new byte[4];

        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        fis.skip(4);
        fis.read(bb);
        int length = byte2int(bb);
        double bpm = 0d;
        for(int i = 0; i < length; i++) {
            fis.skip(4);
            double tempbpm = Double.longBitsToDouble(Long.reverseBytes(dis.readLong()));
            if(bpm == 0) {
                bpm = tempbpm;
            } else if(bpm != tempbpm) {
                if(path.indexOf("numbernine") >= 0 || path.indexOf("number9_") >= 0) {
                    bpm = 128d;
                    break;
                } else {
                    System.out.print(path + " bpm發生變化，" + bpm + "->" + tempbpm + "請輸入正確的bpm:");
                    bpm = Double.parseDouble(IOUtils.readLine());
                    break;
                }
            }
        }
        //todo:檢查bpm
        fis = new FileInputStream(f);
        fis.skip(4);

        fis.read(bb);
        fis.skip(12 * byte2int(bb));
        fis.skip(2);
        fis.read(bb);
        int size = byte2int(bb);
        int key = 0;
        int k00=0, k01=0, k02=0, ka1=0, k21=0,k61=0,ka2=0,k22=0,k62=0;
        for(int i = 0; i < size; i++) {
            bb = new byte[11];
            fis.read(bb);
            byte b = bb[0];
            if(b == 0x00) { //00
                key++;
                k00++;
                if(showlog) {
                    System.out.println(key + "，单键");
                }
            } else if(b == 0x01) { //01
                key++;
                k01++;
                if(showlog) {
                    System.out.println(key + "，划键");
                }
            } else if(b == 3 || b == -93) { //03, a3
//                System.out.println("03/a3");
//                return 0;
//                int ii = bb[7] >= 0 ? bb[7] : (256 + bb[7]);
//                key += Math.round(((bb[8] * 256 + ii) / (60000d / bpm / 4d))) + 1;
            } else if(b == 0x02) { //02, ok
                int ii = bb[7] >= 0 ? bb[7] : (256 + bb[7]);
                int c = (int)(((bb[8] * 256 + ii) / ((int)(60000d / bpm / 4d)))) + 1;
                key += c;
                k02++;
                if(showlog) {
                    System.out.println(key + "，面条");
                }
            } else if(b == -95) { //a1面条结尾划键,
                key += 1;
                ka1+=1;
                if(showlog) {
                    System.out.println(key + "，面条结尾划键");
                }
            } else if(b == 0x21) { //21划键，
                key += 1;
                k21+=1;
                if(showlog) {
                    System.out.println(key + "，面条中间划键");
                }
            } else if(b == 0x61) { //61面条开始的划键,
                k61+=1;
                key += 1;
                if(showlog) {
                    System.out.println(key + "，面条开始划键");
                }
            } else if(b == -94) { //a2,长键结尾
                int ii = bb[7] >= 0 ? bb[7] : (256 + bb[7]);
                int c = (int)(((bb[8] * 256 + ii) / ((int)(60000d / bpm / 4d))));
                key += c;
//                ka2+=c;
                ka2++;
                if(showlog) {
                    System.out.println(key + "，面条结束长键");
                }
            } else if(b == 0x22) { //22，长键中间的面条
                int ii = bb[7] >= 0 ? bb[7] : (256 + bb[7]);
                int c = (int)(((bb[8] * 256 + ii) / ((int)(60000d / bpm / 4d))));
                key += c;
                k22+=c;
                if(showlog) {
                    System.out.println(key + "，面条中间长键");
                }
            } else if(b == 0x62) { //62，长键开始
                int ii = bb[7] >= 0 ? bb[7] : (256 + bb[7]);
                int c = (int)(((bb[8] * 256 + ii) / ((int)(60000d / bpm / 4d)))) + 1;
                key += c;
//                k62+=c;
                k62++;
                if(showlog) {
                    System.out.println(key + "，面条开始长键");
                }
            } else if(b == -96) { //a0
//                key--;
//                key++;
//                System.out.print("");
//                return 0;
            } else {
                System.out.println("!!!!!!");
            }
        }
        int[] keys = new int[]{key, k00, k01, k02, k21,k22,k61,k62,ka1,ka2};
        return -1;
    }
}
