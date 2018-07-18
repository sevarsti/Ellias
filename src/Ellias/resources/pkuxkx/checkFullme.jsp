<%@ page import="org.apache.commons.lang.StringUtils" %><%@ page import="com.saille.pkuxkx.FullmeUtils" %><%
    String url = request.getParameter("url");
    if(StringUtils.isBlank(url)) {
        return;
    }
    String content = FullmeUtils.getValue(url);
    if(StringUtils.isNotBlank(content)) {
        out.print(content);
        return;
    }
    return;
%>