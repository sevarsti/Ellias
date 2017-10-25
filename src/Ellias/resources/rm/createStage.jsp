<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2017-10-25
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title>创建闯关</title>
    <style>
    .button2
    {
        background-color: buttonface;
        background-repeat: repeat;
        background-attachment: scroll;
        border-left: 1px solid buttonhighlight;
        border-right: 1px solid buttonshadow;
        border-top: 1px solid buttonhighlight;
        border-bottom: 1px solid buttonshadow;
        background-position: 0%"
    }
    </style>
</head>
<body>
<table class="black" id="table">
    <tr class="head">
        <td>歌曲md5</td>
        <td>歌曲名称</td>
        <td>谱面md5</td>
        <td>键数</td>
        <td>难度</td>
        <td>操作</td>
    </tr>
    <tr class="row1">
        <td>
            <input type="text" name="mp3md5" maxlength="32" size="33" onchange="updatesong(this);" class="inputbox"/>
        </td>
        <td>
            &nbsp;
        </td>
        <td>
            <input type="text" name="imdmd5" maxlength="32" size="33" onchange="updateimd(this);" class="inputbox"/>
        </td>
        <td></td>
        <td></td>
        <td>
            <input type="button" value="增加" class="button2"/>
            <input type="button" value="删除"/>
        </td>
    </tr>
</table>
</body>
<script type="text/javascript">
    function updatesong(obj)
    {
        var row = obj.parentNode.parentNode;
        var songmd5 = obj.value;
//        alert(songmd5);
        RMDwr.getSongByMd5(songmd5, row.rowIndex, aftersong);
    }
    function aftersong(obj)
    {
        var rowindex = obj.substr(0, obj.indexOf("-"));
        if(obj.indexOf("-") < (obj.length - 1))
        {
            document.getElementById("table").rows[rowindex].cells[1].innerHTML = obj.substring(obj.indexOf("-") + 1).replace(/\//g, "<br/>");
        }
        else
        {
            document.getElementById("table").rows[rowindex].cells[1].innerHTML = "";
        }
    }
</script>
</html>
