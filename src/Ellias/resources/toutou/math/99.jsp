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
        <td style="font-size:50pt;">正确率：</td>
        <td style="font-size:50pt;" id="ratio">0/0</td>
    </tr>
    <tr>
        <td colspan="2" align="center" id="content" style="font-size: 200px;">
            <input style="font-size: 200px;" value="开始" type="button" onclick="begin();">
        </td>
    </tr>
    <tr><td colspan="2" align="center">
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
    function begin()
    {
        a = parseInt(Math.random() * 9, 10) + 1;
        b = parseInt(Math.random() * 9, 10) + 1;
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
        total++;
        document.getElementById("ratio").innerHTML = correct + "/" + total;
        begin();
    }
</script>
</html>
