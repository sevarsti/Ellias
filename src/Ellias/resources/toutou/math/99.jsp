<%--
  Created by IntelliJ IDEA.
  User: H00672
  Date: 2018-08-25
  Time: 00:06
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../include/include.jsp"%>
<html>
<head>
    <title>九九乘法表</title>
</head>
<body>
<table width="100%" border="0" cellpadding="1" cellspacing="1">
    <tr>
        <td style="font-size:50pt;">题号：</td>
        <td style="font-size:50pt;" id="ratio">0/0</td>
    </tr>
    <tr>
        <td colspan="2" align="center" id="content" style="font-size: 200px;">
            <input style="font-size: 200px;" value="开始" type="button" onclick="total = 0;correct = 0;begindate = new Date();begin();">
        </td>
    </tr>
    <tr id="r1"><td colspan="2" align="center">
        <input type="button" name="1" value="1" style="width:230px;height: 230px;font-size: 100px;font-weight: bold;" onclick="addvalue(1);"/>
        <input type="button" name="2" value="2" style="width:230px;height: 230px;font-size: 100px;font-weight: bold;" onclick="addvalue(2);"/>
        <input type="button" name="3" value="3" style="width:230px;height: 230px;font-size: 100px;font-weight: bold;" onclick="addvalue(3);"/>
    </td></tr>
    <tr><td colspan="2" align="center">
        <input type="button" name="1" value="4" style="width:230px;height: 230px;font-size: 100px;font-weight: bold;" onclick="addvalue(4);"/>
        <input type="button" name="2" value="5" style="width:230px;height: 230px;font-size: 100px;font-weight: bold;" onclick="addvalue(5);"/>
        <input type="button" name="3" value="6" style="width:230px;height: 230px;font-size: 100px;font-weight: bold;" onclick="addvalue(6);"/>
    </td></tr>
    <tr><td colspan="2" align="center">
        <input type="button" name="1" value="7" style="width:230px;height: 230px;font-size: 100px;font-weight: bold;" onclick="addvalue(7);"/>
        <input type="button" name="2" value="8" style="width:230px;height: 230px;font-size: 100px;font-weight: bold;" onclick="addvalue(8);"/>
        <input type="button" name="3" value="9" style="width:230px;height: 230px;font-size: 100px;font-weight: bold;" onclick="addvalue(9);"/>
    </td></tr>
    <tr><td colspan="2" align="center">
        <input type="button" name="1" value="清除" style="width:230px;height: 230px;font-size: 100px" onclick="doclear();"/>
        <input type="button" name="2" value="0" style="width:230px;height: 230px;font-size: 100px;font-weight: bold;" onclick="addvalue(0);"/>
        <input type="button" name="3" value="提交" style="width:230px;height: 230px;font-size: 100px" onclick="calc();"/>
    </td></tr>
</table>
</body>
<script type="text/javascript">
    var a, b;
    var correct = 0, total=0;
    var begindate;
    var errors = new Array();
    function begin()
    {
        a = parseInt(Math.random() * 9, 10) + 1;
        b = parseInt(Math.random() * 9, 10) + 1;
        total++;
        document.getElementById("ratio").innerHTML = total + "/10";
        document.getElementById("content").innerHTML = a + "×" + b + "=" +
                "<input id=\"result\" type=\"text\" size=\"2\" maxlength=\"2\" style=\"text-align:right;font-size: 200px;width: 250px;\"/>"
    }
    function addvalue(v)
    {
        var obj = document.getElementById("result");
        if(obj == null)
        {
            return;
        }
        if(document.getElementById("result").value.length >= 2)
        {
            return;
        }
        obj.value = obj.value + "" + v;
    }
    function doclear()
    {
        document.getElementById("result").value = '';
    }
    function calc()
    {
        var obj = document.getElementById("result");
        if(obj == null) {
            return;
        }
        var result = parseInt(obj.value, 10);
        if(result == a * b)
        {
            correct++;
        }
        else
        {
            var idx = errors.length;
            errors[idx] = new Array();
            errors[idx][0] = a;
            errors[idx][1] = b;
        }
        if(total >= 10)
        {
            var now = new Date();
            var sec = now - begindate;
            document.getElementById('content').style.fontSize = "100px";
            var input = "<input type=\"button\" style=\"font-size: 100px;color: red;\" value=\"再来一次\" onclick=\"total = 0;correct = 0;begindate = new Date();begin();\"/>";
            document.getElementById('content').innerHTML = "一共答对" + correct + "/" + total + "题，用时" + sec / 1000 + "秒" + input;
            recordError();
        }
        else
        {
            begin();
        }
    }

    function recordError()
    {
        var sendstr = '';
        if(errors.length > 0)
        {
            for(var i = 0; i < errors.length; i++)
            {
                if(i > 0) {
                    sendstr = sendstr + ",";
                }
                sendstr += errors[i][0] + "-" + errors[i][1];
            }
            HMFDwr.recordMath99Error(sendstr);
        }
        errors = new Array();
    }
</script>
</html>
