<%@ page import="com.saille.bbs.yssy.YSSYUtil"%>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2009-1-4
  Time: 16:20:53
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=gb2312" language="java" pageEncoding="gb2312" %>--%>
<html>
<%
    YSSYUtil util = new YSSYUtil();
    try {
        String cookie = util.login("pq", "pmgkglory");
        if(cookie != null) {
            String smd = util.viewSmd("pq", cookie).trim();
            if(smd != null && !smd.equals("")) {
                System.out.print("smd is not null");
            } else {
            }
        }
//            System.out.print(i + ":" + s + "...");
//            i++;
//            String cookie = util.login(s, "pmgkglory");
//            System.out.print("ok...");
//            if(cookie != null) {
//                ids.add(new String[]{s, cookie});
//                String smd = util.viewSmd(s, cookie).trim();
//                if(smd != null && !smd.equals("")) {
//                    System.out.print("smd is not null");
//                }
//            }
//            System.out.println("");
//        }
    } catch(Exception ex) {
        ex.printStackTrace();
    }
%>
  <head><title>Simple jsp page</title></head>
  <body>
  </body>
</html>