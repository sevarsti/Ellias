<%@ page import="javax.sql.DataSource" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2018-09-22
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/include/include.jsp"%>
<html>
<head>
    <title>英语单词列表</title>
</head>
<body>
<%
    DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
    JdbcTemplate jt = new JdbcTemplate(ds);
    String sql = "select a.id, a.english, a.chinese, b.c, b.correct from hmf_english a left join (select english, sum(correct) as correct, count(1) as c from hmf_englishresult group by english) b on a.english = b.english order by a.english";
    List<Map<String, Object>> list = jt.queryForList(sql);
    List<String[]> words = new ArrayList<String[]>();
    DecimalFormat df = new DecimalFormat("0.00%");
    for(Map<String, Object> map : list) {
        String[] word = new String[6];
        word[0] = map.get("english").toString();
        word[1] = map.get("chinese").toString();
        Object obj = map.get("c");
        word[2] = obj == null ? "0" : obj.toString();
        obj = map.get("correct");
        word[3] = obj == null ? "0" : obj.toString();
        word[4] = word[2].equals("0") ? "-1" : (Double.parseDouble(word[3]) / Double.parseDouble(word[2]) + "");
        word[5] = map.get("id").toString();
        words.add(word);
    }
%>
<table id="info" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
    <tr class="head">
        <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, false)">英语</th>
        <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false)">中文</th>
        <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, true)">次数</th>
        <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, true)">正确</th>
        <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, true)">正确率</th>
        <th>操作</th>
    </tr>
    <%
        for(int i = 0; i < words.size(); i++) {
            String[] word = words.get(i);
    %>
    <tr class="row<%=i % 2 + 1%>">
        <td><%=word[0]%></td>
        <td><%=word[1]%></td>
        <td><%=word[2]%></td>
        <td><%=word[3]%></td>
        <td align="right" value="<%=word[4]%>"><%=word[4].equals("-1") ? "0.00%" : df.format(Double.parseDouble(word[4]))%></td>
        <td>
            <input type="button" value="修改" onclick="doEdit(<%=word[5]%>,'<%=word[0]%>', '<%=word[1]%>')"/>
            <input type="button" value="删除" onclick="doDelete('<%=word[0]%>')"/>
        </td>
    </tr>
    <%
        }
    %>
</table>
<table>
    <tr>
        <td class="fieldname">英文</td>
        <td class="fieldvalue">
            <input type="hidden" name="wordid" value="0">
            <input type="text" name="eng"/>
        </td>
    </tr>
    <tr>
        <td class="fieldname">中文</td>
        <td class="fieldvalue">
            <input type="text" name="chn"/>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="button" value="保存" onclick="doSave()"/>
            <input type="button" value="清除" onclick="document.getElementsByName('eng')[0].value = '';document.getElementsByName('chn')[0].value = ''"/>
        </td>
    </tr>
</table>
<script type="text/javascript">
    function doEdit(id, english, chinese)
    {
        document.getElementsByName('wordid')[0].value = id;
        document.getElementsByName('eng')[0].value = english;
        document.getElementsByName('chn')[0].value = chinese;
        document.getElementsByName('eng')[0].focus();
    }
    function doSave()
    {
        HMFDwr.saveEnglishWord(document.getElementsByName('wordid')[0].value,
                document.getElementsByName('eng')[0].value,
                document.getElementsByName('chn')[0].value, after);
    }
    function doDelete(id)
    {
        HMFDwr.deleteEnglishWord(id, after);
    }
    function after(result)
    {
        if(result)
        {
            alert('操作成功');
        }
        else
        {
            alert('操作失败');
        }
    }
</script>
</body>
</html>
