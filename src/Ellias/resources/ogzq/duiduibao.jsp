<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%!
    int[] convert(int idx) {
        int i = idx / 8;
        int j = idx % 8;
        return new int[]{i, j};
    }
%>
<%
    //    String id = request.getParameter("email");
    String id = "544397212@qq.com";

    //type=6: 准备重置
    //-1|3405|29 3405: 剩余秒数，29：花费金币

    boolean cont = false;
//    String id = "sevarsti@sina.com";
    HttpPost pm = new HttpPost(OgzqURL.URL + "/DuiDuiBao.aspx");

    List<NameValuePair> pp = new ArrayList<NameValuePair>();
    pp.add(new BasicNameValuePair("type", "1"));
    pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
    String ret = IDUtils.execute(id, pm);
//    String ret = "1206|1905|7001|2406|1905|1504|10012|1205*7002|2302|1304|1402|1302|2401|7002|1205*2406|1205|2105|5005|2902|3303|1501|10012*1205|1404|1206|1306|1402|2907|1205|7001*7007|2703|1302|2506|1504|3303|1504|1306*2302|2101|1304|1803|1404|1205|2605|1803*2703|2105|7007|1905|2401|2907|2101|2605*1504|1501|5005|7007|2902|1905|2506|7007^0|0|0|0|0^0^黑龙会小罗^35^70^1^409";
    String[] params = ret.split("\\^");
    long now = System.currentTimeMillis();
    if(Integer.parseInt(params[5]) <= 0) { //已经结束
//        int cd = Integer.parseInt(params[6]);
//        if(cd <= 0) {
        pm = new HttpPost(OgzqURL.URL + "/DuiDuiBao.aspx");
        pp = new ArrayList<NameValuePair>();
        pp.add(new BasicNameValuePair("type", "3"));
        pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
        System.out.println("reset: " + IDUtils.execute(id, pm));
        cont = true;
        pm = new HttpPost(OgzqURL.URL + "/DuiDuiBao.aspx");
        pp = new ArrayList<NameValuePair>();
        pp.add(new BasicNameValuePair("type", "1"));
        pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
        ret = IDUtils.execute(id, pm);
        now = System.currentTimeMillis();
//        }
    } else {
        cont = true;
    }
    if(cont) {
        ret = ret.substring(0, ret.indexOf("^"));
        System.out.println(ret);
        String[] lines = ret.split("[*]");
        List<String> items = new ArrayList<String>();
        for(int i = 0; i < 8; i++) {
            String l = lines[i];
            String[] ii = l.split("[|]");
            for(String s : ii) {
                items.add(s);
            }
        }
        System.out.println("size: " + items.size() + "<br/>");
        for(int i = 0; i < items.size(); i++) {
            out.println(i + ":" + items.get(i) + "<br/>");
        }
        int count = 0;
        for(int i = 0; i < 64; i++) {
            if(items.get(i).equals("0")) {
                continue;
            }
            for(int j = i + 1; j < 64; j++) {
                if(items.get(j).equals("0")) {
                    continue;
                }
                if(items.get(i).equals(items.get(j))) {
                    int[] place1 = convert(i);
                    int[] place2 = convert(j);
                    pm = new HttpPost(OgzqURL.URL + "/DuiDuiBao.aspx");
                    pp = new ArrayList<NameValuePair>();
                    pp.add(new BasicNameValuePair("type", "2"));
                    pp.add(new BasicNameValuePair("i1", "" + place1[0]));
                    pp.add(new BasicNameValuePair("j1", "" + place1[1]));
                    pp.add(new BasicNameValuePair("i2", "" + place2[0]));
                    pp.add(new BasicNameValuePair("j2", "" + place2[1]));
                    pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));

                    if(count == 31) {
                        long cur = System.currentTimeMillis();
                        Thread.sleep(19900 - (cur - now));
                    }
                    System.out.println("i=" + i + ", count=" + count + ": " + IDUtils.execute(id, pm));
//                    Thread.sleep(600);
                    out.println(count + "---" + place1[0] + "/" + place1[1] + "--" + place2[0] + "/" + place2[1] + "--------" + items.get(i) + "<br/>");
                    count++;
                    items.set(i, "0");
                    items.set(j, "0");
                }
            }
        }
        System.out.println("total cost: " + (System.currentTimeMillis() - now));
    }
%>