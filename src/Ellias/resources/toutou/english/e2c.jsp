<%--
  Created by IntelliJ IDEA.
  User: H00672
  Date: 2018-09-06
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../include/include.jsp"%>
<html>
<head>
    <title>单词背诵</title>
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
    <tr><td>&nbsp;</td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr id="c1" style="display: none;"><td colspan="2" align="center">
        <input id="cc1" type="button" value="星期一" style="width:700px;height: 200px;font-size: 100px;background-color: #808080;" onclick="addvalue(0);"/>
    </td></tr>
    <tr id="c2" style="display: none;"><td colspan="2" align="center">
        <input id="cc2" type="button" value="星期一" style="width:700px;height: 200px;font-size: 100px;background-color: #808080;" onclick="addvalue(1);"/>
    </td></tr>
    <tr id="c3" style="display: none;"><td colspan="2" align="center">
        <input id="cc3" type="button" value="星期一星期一" style="width:700px;height: 200px;font-size: 100px;background-color: #808080;" onclick="addvalue(2);"/>
    </td></tr>
    <tr id="c4" style="display: none;"><td colspan="2" align="center">
        <input id="cc4" type="button" value="星期一" style="width:700px;height: 200px;font-size: 100px;background-color: #808080;" onclick="addvalue(3);"/>
    </td></tr>
</table>
</body>
<script type="text/javascript">
    var questions = new Array();
    var idx = 0;
    function begin()
    {
        HMFDwr.getEnglishRandomWords(5, afterGetQuestion);
    }
    function afterGetQuestion(list)
    {
        for(var i = questions.length - 1; i >= 0; i--) {
            questions[i] = null;
        }
        idx = 0;
        for(var i = 0; i < list.length; i++)
        {
            questions[i] = new Array();
            questions[i][0] = list[i][0];
            questions[i][1] = list[i][1];
            questions[i][2] = list[i][2];
            questions[i][3] = list[i][3];
            questions[i][4] = list[i][4];
        }
        var r = parseInt(Math.random() * 4, 10);
        for(var i = 0; i < 4; i++) {
            if(i < r) {
                document.getElementById('cc' + (i + 1)).value = questions[idx][i + 2];
            } else if(i > r) {
                document.getElementById('cc' + (i + 1)).value = questions[idx][i + 1];
            } else {
                document.getElementById('cc' + (i + 1)).value = questions[idx][1];
            }
            document.getElementById('c' + (i + 1)).style.display = '';
        }
        document.getElementById('content').innerHTML = questions[idx][0];
    }
</script>
</html>
