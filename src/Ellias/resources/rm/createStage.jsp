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
    <title>��������</title>
</head>
<body>
<%
    if("post".equalsIgnoreCase(request.getMethod())) {
        String[] songids = request.getParameterValues("songid");
        String[] imdids = request.getParameterValues("imdid");
        String[] targetypes = request.getParameterValues("targettype");
        String[] targetnumbers = request.getParameterValues("targetnumber");
    }
    String select = "<select name=\"targettype\"><option value=\"3\">Ѫ��������%</option><option value=\"4\">Ѫ��������%</option><option value=\"5\">��������ﵽ</option><option value=\"6\">�������������</option><option value=\"7\">�ɼ��ﵽ7SSS1D</option><option value=\"8\">MISS������</option><option value=\"9\">MISS������</option><option value=\"10\">GOOD������</option><option value=\"11\">GOOD������</option><option value=\"14\">�����ﵽAll Combo</option><option value=\"15\">��������</option><option value=\"16\">�����ﵽ</option><option value=\"17\">����������</option><option value=\"18\">��Ѫ����</option><option value=\"19\">Perfect�ﵽ%</option><option value=\"20\">Perfect������%</option><option value=\"21\">GOOD������%</option><option value=\"22\">GOOD������%</option><option value=\"23\">MISS������%</option><option value=\"24\">MISS������%</option><option value=\"25\">����������%</option><option value=\"26\">����������%</option><option value=\"27\">�����ﵽ%</option><option value=\"28\">����������%</option></select>";
%>
<form action="" method="post">
<table class="black" id="table">
    <tr class="head">
        <td>����md5</td>
        <td>��������</td>
        <td>����</td>
        <td>����</td>
        <td>����md5</td>
        <td>��λ</td>
        <td>�Ѷ�</td>
        <td>����</td>
        <td>�ȼ�</td>
        <td>Ҫ��</td>
        <td>��ֵ</td>
        <td>����</td>
    </tr>
    <tr class="row1">
        <td>
            <input type="hidden" name="songid"/>
            <input type="text" name="mp3md5" maxlength="32" size="33" onchange="updatesong(this);" class="inputbox"/>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td>
            <input type="hidden" name="imdid"/>
            <input type="text" name="imdmd5" maxlength="32" size="33" onchange="updateimd(this);" class="inputbox"/>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>
            <%=select%>
        </td>
        <td>
            <input type="text" name="targetnumber" size="8" maxlength="7"/>
        </td>
        <td>
            <input type="button" value="����" onclick="addrow(this)"/>
            <input type="button" value="ɾ��"/>
        </td>
    </tr>
</table>
</form>
</body>
<script type="text/javascript">
    function addrow(obj)
    {
        var row = document.getElementById("table").insertRow(obj.parentNode.parentNode.rowIndex + 1);
        var clazz = parseInt(obj.parentNode.parentNode.className.substr(3, 1), 10);

        for(var i = obj.parentNode.parentNode.rowIndex + 1; i < document.getElementById("table").rows.length; i++)
        {
            clazz = 3 - clazz;
            document.getElementById("table").rows[i].className = 'row' + clazz;
        }
        var cell = row.insertCell(-1);
        cell.innerHTML = "<input type=\"hidden\" name=\"songid\"/><input type=\"text\" name=\"mp3md5\" maxlength=\"32\" size=\"33\" onchange=\"updatesong(this);\" class=\"inputbox\"/>";
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell.innerHTML = "<input type=\"hidden\" name=\"imdid\"/><input type=\"text\" name=\"imdmd5\" maxlength=\"32\" size=\"33\" onchange=\"updateimd(this);\" class=\"inputbox\"/>";
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell.innerHTML = "<%=select.replaceAll("\"", "\\\\\"")%>";
        cell = row.insertCell(-1);
        cell.innerHTML = "<input type=\"text\" name=\"targetnumber\" size=\"8\" maxlength=\"7\"/>";
        cell = row.insertCell(-1);
        cell.innerHTML = "<input type=\"button\" value=\"����\" onclick=\"addrow(this)\"/>\n<input type=\"button\" value=\"ɾ��\"/>"
    }
    function updatesong(obj)
    {
        var row = obj.parentNode.parentNode;
        var songmd5 = obj.value;
        RMDwr.getSongByMd5(songmd5, row.rowIndex, aftersong);
    }
    function aftersong(obj)
    {
        var rowindex = obj[0];
        var name = '', author = '', length = '';
        for(var i = 1; i < obj.length; i++)
        {
            if(i > 1)
            {
                name += "<br/>";
                author += "<br/>";
                length += "<br/>";
            }
            name += obj[i]['name'];
            author += obj[i]['author'];
            length += obj[i]['length'];
        }
        document.getElementById("table").rows[rowindex].cells[1].innerHTML = name;
        document.getElementById("table").rows[rowindex].cells[2].innerHTML = author;
        document.getElementById("table").rows[rowindex].cells[3].innerHTML = length;
    }

    function updateimd(obj)
    {
        var row = obj.parentNode.parentNode;
        var imdmd5 = obj.value;
        var mp3md5 = row.cells[0].getElementsByTagName("input")[1].value;
        RMDwr.getImdByMd5(mp3md5, imdmd5, row.rowIndex, afterimd);
    }

    function afterimd(map)
    {
        var rowindex = map['rowindex'];
        if(map['songid'] == undefined)
        {
            document.getElementById('table').rows[rowindex].cells[0].getElementsByTagName('input')[0].value = '';
            document.getElementById('table').rows[rowindex].cells[4].getElementsByTagName('input')[0].value = '';
            document.getElementById("table").rows[rowindex].cells[5].innerHTML = '';
            document.getElementById("table").rows[rowindex].cells[6].innerHTML = '';
            document.getElementById("table").rows[rowindex].cells[7].innerHTML = '';
            document.getElementById("table").rows[rowindex].cells[8].innerHTML = '';
            return;
        }
        document.getElementById('table').rows[rowindex].cells[0].getElementsByTagName('input')[0].value = map['songid'];
        document.getElementById('table').rows[rowindex].cells[4].getElementsByTagName('input')[0].value = map['id'];
        document.getElementById("table").rows[rowindex].cells[1].innerHTML = map['name'];
        document.getElementById("table").rows[rowindex].cells[2].innerHTML = map['author'];
        document.getElementById("table").rows[rowindex].cells[3].innerHTML = map['length'];
        document.getElementById("table").rows[rowindex].cells[5].innerHTML = map['key'];
        document.getElementById("table").rows[rowindex].cells[6].innerHTML = map['level'];
        document.getElementById("table").rows[rowindex].cells[7].innerHTML = map['totalkey'];
        document.getElementById("table").rows[rowindex].cells[8].innerHTML = map['difficulty'];
    }
</script>
</html>
