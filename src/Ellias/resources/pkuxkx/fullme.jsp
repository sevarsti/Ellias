<%@ page import="com.saille.pkuxkx.FullmeUtils" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2018-07-18
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%
    String url = request.getParameter("url");
    if(StringUtils.isBlank(url)) {
        out.print("url is null");
        return;
    }
//    DefaultHttpClient client = new DefaultHttpClient();
//    HttpGet gm = new HttpGet("http://pkuxkx.com/antirobot/robot.php?filename=" + url);
//    HttpResponse resp = client.execute(gm);
//    InputStream is = resp.getEntity().getContent();
//    String content = CommonUtils.getString(is, "GBK");
//    System.out.println("fullme: " + url + ",content=\n" + content);
//    if(StringUtils.isBlank(content)) {
//        out.print("fullme expired");
//        return;
//    }
//    Pattern p = Pattern.compile("^.+/b2evo_captcha_([A-F0-9]+)\\.jpg.+\\n.+$");
//    Matcher m = p.matcher(content);
//    if(!m.matches()) {
//        out.println("parse url failed");
//        return;
//    }
//    String imgurl = m.group(1);
    FullmeUtils.add(url);
    out.print("fullme done");
%>
