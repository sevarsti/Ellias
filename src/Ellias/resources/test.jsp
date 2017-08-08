<%@ page import="com.saille.rm.loop.UpdateScoreThread" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<%@ page import="com.saille.rm.loop.WeekSThread" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ include file="include/include.jsp"%>
<%
    try {
        WeekSThread.getInstance(0).execute();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("end");
    } catch(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        ex.printStackTrace();
        String s = sw.toString();
        s = s.replaceAll("\n", "<br/>");
        out.println(s);
    }
%>
<script type="text/javascript">
</script>