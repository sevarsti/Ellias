<%@ page import="com.saille.bwdl.BwdlConstant" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-6-18
  Time: 23:26:02
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>

<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>编辑武器</title>
    </head>
    <body>
        <c:set var="form" value="${wuqiForm}"/>
        <html:form action="/bwdl/wuqi.do">
            <input type="hidden" name="method"/>
            <html:hidden property="version"/>
            <html:hidden property="wuqiId"/>
            <table width="40%" id="table">
                <tr class="row1">
                    <td class="fieldname">
                        名字
                    </td>
                    <td class="fieldvalue">
                        <html:text property="name"/>
                    </td>
                </tr>
                <tr class="row2">
                    <td class="fieldname">类别</th>
                    <td class="fieldvalue">
                        <html:select property="type">
                            <%
                                String[] types = BwdlConstant.TYPE_WUQI;
                                for(int i = 0; i < types.length; i++) {
                            %>
                            <html:option value="<%=String.valueOf(i)%>"><%=types[i]%></html:option>
                            <%
                                }
                            %>
                        </html:select>
                    </td>
                </tr>
                <tr class="row1">
                    <td class="fieldname">攻击</th>
                    <td class="fieldvalue">
                        <html:text property="gongji"/>
                    </td>
                </tr>
                <tr class="row2">
                    <td class="fieldname">重量</th>
                    <td class="fieldvalue">
                        <html:text property="weight"/>
                    </td>
                </tr>
                <tr class="row1">
                    <td class="fieldname">价格</th>
                    <td class="fieldvalue">
                        <html:text property="price"/>
                    </td>
                </tr>
            </table>
            <input type="button" value="保存" onclick="saveWuQi();" class="otterbtn"/>
        </html:form>
        <script type="text/javascript">
            function saveWuQi()
            {
                document.getElementsByName('method')[0].value = 'save';
                document.forms[0].submit();
            }
        </script>
    </body>
</html>