<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.commons.httpclient.methods.PostMethod" %>
<%@ page import="java.util.List" %>
<%
    try {
//        领贝克汉姆个人战礼包
        PostMethod pm;
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            pm = new PostMethod(OgzqURL.URL + "/Beckham_LevelUp.aspx");
            pm.addParameter("load", "1");
            IDUtils.execute(id, pm);
            for(int i = 1; i <= 20; i++) {
                pm = new PostMethod(OgzqURL.URL + "/Beckham_LevelUp.aspx");
                pm.addParameter("getReward", ""+i);
//                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("getReward", "" + i));
//                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                out.println("id:" + id + ", i=" + i + "---" + IDUtils.execute(id, pm) + "<br/>");
            }
        }
    } catch(Exception ex) {
        ex.printStackTrace();
    }
%>