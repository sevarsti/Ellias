<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-15
  Time: 20:01:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.ConfigUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <!--<script type='text/javascript' src='../include/json.js'> </script>-->
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/OgzqDwr.js'></script>
    </head>
    <body>
    <%
        List<String> configNames = new ArrayList<String>();
        configNames.add("球员搜索范围");
        configNames.add("是否搜索球员");
        configNames.add("是否踢竞技场");
        configNames.add("竞技场高级道具");
        configNames.add("是否踢球会大作战");
        configNames.add("球会战开始");
        configNames.add("球会战结束");
        configNames.add("是否踢巡回赛");
        configNames.add("是否踢训练赛");
        configNames.add("是否训练");
        configNames.add("训练赛范围");
//        configNames.add("训练赛对手");
        configNames.add("球员保留");
//        configNames.add("球会战开始");
//        configNames.add("球会战结束");
        configNames.add("世界杯难度");
//        configNames.add("是否踢副本1");
//        configNames.add("是否踢副本2");
//        configNames.add("是否踢副本3");
//        configNames.add("是否踢副本4");
//        configNames.add("是否踢副本5");
        configNames.add("是否激情训练");
        configNames.add("是否卖激情");
        configNames.add("是否踢欧冠之巅");
        configNames.add("欧冠之巅对手");
        configNames.add("搜集资料");
        configNames.add("是否踢联盟训练赛");
        configNames.add("是否踢巅峰之路");

        List<String> ids = IDUtils.GETIDS();
    %>
    <table id="confTable" border="0" cellpadding="1" cellspacing="1" class="frame" bgcolor="black">
        <tr class="head">
            <th colspan="2">参数</th>
            <c:forEach items="<%=configNames%>" var="c">
                <th>${c}</th>
            </c:forEach>
        </tr>
        <tr class="row1">
            <td colspan="2">全局</td>
            <%
                for(String conf : configNames) {
            %>
            <td><%=ConfigUtils.getConf(null, conf)%></td>
            <%
                }
            %>
        </tr>
        <%
            for(int i = 0; i < ids.size(); i++) {
                String id = ids.get(i);
                if(i % 15 == 0) {
        %>
        <tr class="head">
            <th colspan="2">参数</th>
            <c:forEach items="<%=configNames%>" var="c">
                <th>${c}</th>
            </c:forEach>
        </tr>
        <%
                }
        %>
        <tr class="row<%=2 - i % 2%>">
            <td><%=id%></td>
            <td><%=IDUtils.getNick(id)%></td>
            <%
                for(String conf : configNames) {
                    String myConf = ConfigUtils.getConfExact(id, conf);
                    String globalConf = ConfigUtils.getConf(id, conf);
            %>
            <td nowrap onclick="edit(this, '<%=id%>', '<%=conf%>', '<%=myConf == null ? "" : myConf%>', event);">
                <%=myConf == null ? "" : "<span style=\"color:red;\">" + myConf + "</span>"%>
                <c:if test="<%=myConf != null%>"><input type="image" style="cursor:pointer;" src="../images/cancel.gif" title="删除" onclick="if(confirm('确定要删除<%=conf%>.<%=id%>吗？')) {deleteConf('<%=conf%>.<%=id%>');}"/></c:if>
            </td>
            <%
                }
            %>
        </tr>
        <%
            }
        %>
    </table>
        <table id="info" border="0" cellpadding="1" cellspacing="1" class="rowover">
            <tr class="head">
                <th>KEY</th>
                <th>VALUE</th>
                <th>号</th>
                <th>OPERATION</th>
            </tr>
            <%
                List<String[]> list = ConfigUtils.listAll();
                for(int i = 0; i < list.size(); i++) {
                    String[] conf = list.get(i);
                    if(conf[0].indexOf(".") >= 0) {
                        if(IDUtils.IDInfos.get(conf[0].substring(conf[0].indexOf(".") + 1)) != null) {
                            continue;
                        }
                    }
            %>
                <tr class="row<%=i % 2 + 1%>">
                    <td onclick="doEdit('<%=conf[0]%>','<%=conf[1]%>');" style="cursor:pointer;">
                        <%=conf[0]%>
                    </td>
                    <td>
                        <%=conf[1]%>
                    </td>
                    <td>
                        <%=conf[0].indexOf(".") >= 0 ? (IDUtils.IDInfos.get(conf[0].substring(conf[0].indexOf(".") + 1)) == null ? "??" : IDUtils.getNick(conf[0].substring(conf[0].indexOf(".") + 1))) : "--"%>
                    </td>
                    <td>
                        <input type="button" value="删除" onclick="deleteConf('<%=conf[0]%>')"/>
                    </td>
                </tr>
            <%
                }
            %>
        </table>
    增加：
    <select id="item">
        <option value="球员搜索范围">球员搜索范围</option>
        <option value="是否搜索球员">是否搜索球员</option>
        <option value="是否踢竞技场">是否踢竞技场</option>
        <option value="竞技场高级道具">竞技场高级道具</option>
        <option value="是否踢球会大作战">是否踢球会大作战</option>
        <option value="球会战开始">球会战开始</option>
        <option value="球会战结束">球会战结束</option>
        <option value="是否踢巡回赛">是否踢巡回赛</option>
        <option value="是否踢训练赛">是否踢训练赛</option>
        <option value="是否训练">是否训练</option>
        <option value="训练赛范围">训练赛范围</option>
        <!--<option value="训练赛对手">训练赛对手</option>-->
        <option value="球员保留">球员保留</option>
        <option value="世界杯难度">世界杯难度</option>
        <!--<option value="是否踢副本1">是否踢副本1</option>-->
        <!--<option value="是否踢副本2">是否踢副本2</option>-->
        <!--<option value="是否踢副本3">是否踢副本3</option>-->
        <!--<option value="是否踢副本4">是否踢副本4</option>-->
        <!--<option value="是否踢副本5">是否踢副本5</option>-->
        <option value="是否激情训练">是否激情训练</option>
        <option value="是否卖激情">是否卖激情</option>
        <option value="是否踢欧冠之巅">是否踢欧冠之巅</option>
        <option value="欧冠之巅对手">欧冠之巅对手</option>
        <option value="搜集资料">搜集资料</option>
        <option value="是否踢联盟训练赛">是否踢联盟训练赛</option>
        <option value="是否踢巅峰之路">是否踢巅峰之路</option>
    </select>
    范围：
    <select id="scope">
        <option value="-1">全部</option>
        <%
            ids = IDUtils.GETIDS();
            for(String id : ids) {
        %>
        <option value="<%=id%>"><%=IDUtils.getNick(id)%></option>
        <%
            }
        %>
    </select>
        值：
    <input type="text" id="value"/>
    <input type="button" value="保存" onclick="doSave();"/>
    </body>
<script type="text/javascript">
    function doEdit(key, value) {
        var p = key.indexOf(".");
        var kk = key;
        if(p != -1) {
            kk = key.substr(0,p);
        }
        for(var i = 0; i < document.getElementById('item').options.length; i++) {
            if(document.getElementById('item').options[i].value == kk) {
                document.getElementById('item').selectedIndex = i;
                break;
            }
        }
//        document.getElementById('item').value = key.substr(0,p);
        document.getElementById('scope').value = (p == -1) ? "-1" : key.substr(key.indexOf(".") + 1);
        document.getElementById('value').value = value;
        document.getElementById('value').focus();
    }

    function doSave() {
        var item = document.getElementById('item').value;
        var scope = document.getElementById('scope').value;
        var value = document.getElementById('value').value;
        var key = item;
        if(scope != -1) {
            key = key + "." + scope;
        }
        OgzqDwr.saveConfig(key, value, afterSave);
    }

    function afterSave(obj)
    {
        alert(obj);
        document.getElementById('item').value = '';
        document.getElementById('scope').value = '';
        document.getElementById('value').value = '';
    }

    function deleteConf(key)
    {
        OgzqDwr.deleteConfig(key, afterSave);
    }

    function edit(obj, email, conf, value, event) {
        if(event.target.tagName == 'INPUT') {
            return;
        }
        obj.innerHTML = "<input type='text' value='" + value + "' size='2' class='inputbox'/>" +
                        "<input type='hidden' value='" + email + "' name='email'/>" +
                        "<input type='hidden' value='" + conf + "' name='conf'/>" +
                        "<input type='button' value='存' onclick='saveConf(this);' style='padding-left: 1px;padding-right: 1px;'/>";
//        alert(obj.getElementsByTagName('input')[0].value);
    }

    function saveConf(obj) {
        var td = obj.parentNode;
        var value = td.getElementsByTagName('input')[0].value;
        var email = td.getElementsByTagName('input')[1].value;
        var conf = td.getElementsByTagName('input')[2].value;


        OgzqDwr.saveConfig(conf + (email == '' ? '' : ('.' + email)), value, afterSave);
    }
</script>
</html>