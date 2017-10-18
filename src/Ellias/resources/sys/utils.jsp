<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2017-10-18
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-15
  Time: 20:01:22
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
    </head>
    <body>
    <table width="100%" class="frame" bgcolor="black" cellpadding="1" cellspacing="1">
        <tr class="head">
            <td colspan="5">节奏大师</td>
        </tr>
        <tr class="row1">
            <td width="20%">
                <input type="button" value="更新自制谱谱面详情" onclick="callFunction('RMDwr.fullKeyDetail')"/>
            </td>
            <td width="20%">
                <input type="button" value="getSongdetail" onclick="callFunction('RMDwr.getSongdetail')"/>
            </td>
            <td width="20%">
            </td>
            <td width="20%">
            </td>
            <td width="20%">
            </td>
        </tr>
    </table>
    <br/>
    参数：<input id="param" value="" size="50" type="text"/>
    <br/>
    <span id="content"/>
    </body>
<script type="text/javascript">
    function callFunction(methodName) {
        var param = document.getElementById('param').value;
        document.getElementById('content').innerHTML = '';
        if(param.length == 0)
        {
            eval(methodName + "(" + "after)");
        }
        else
        {
            eval(methodName + "(" + param + ",after)");
        }
//        window[methodName.substring(0,methodName.indexOf('.'))].callFunction(methodName, param, after);
        document.getElementById('param').value = '';
    }

    function after(obj) {
        document.getElementById('content').innerHTML = obj;
        alert('done');
    }
</script>
</html>