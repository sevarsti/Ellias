<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2017-5-31
  Time: 17:21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="../include/include.jsp"%>
<html>
  <head><title>Simple jsp page</title></head>
  <body>
        <%
            List<String[]> songs = new ArrayList<String[]>();
            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);
            List<Map<String, Object>> list = jt.queryForList("select * from rm_song");
            for(int i = 0; i < list.size(); i++) {
                Map<String, Object> m = list.get(i);
                songs.add(new String[]{m.get("songname").toString(), m.get("pinyin").toString(), m.get("songid").toString()});
            }
        %>
    <input type="text" name="name"/>
    <table border="0" cellpadding="1" cellspacing="1">
        <tr><td colspan="10"><input type="button" value="CLEAR" onclick="document.getElementsByName('name')[0].value = '';"/></td></tr>
        <tr>
            <td><input type="button" name="1" value="1" onclick="updateName(this);"/></td>
            <td><input type="button" name="2" value="2" onclick="updateName(this);"/></td>
            <td><input type="button" name="3" value="3" onclick="updateName(this);"/></td>
            <td><input type="button" name="4" value="4" onclick="updateName(this);"/></td>
            <td><input type="button" name="5" value="5" onclick="updateName(this);"/></td>
            <td><input type="button" name="6" value="6" onclick="updateName(this);"/></td>
            <td><input type="button" name="7" value="7" onclick="updateName(this);"/></td>
            <td><input type="button" name="8" value="8" onclick="updateName(this);"/></td>
            <td><input type="button" name="9" value="9" onclick="updateName(this);"/></td>
            <td><input type="button" name="0" value="0" onclick="updateName(this);"/></td>
        </tr>
        <tr>
            <td><input type="button" name="Q" value="Q" onclick="updateName(this);"/></td>
            <td><input type="button" name="W" value="W" onclick="updateName(this);"/></td>
            <td><input type="button" name="E" value="E" onclick="updateName(this);"/></td>
            <td><input type="button" name="R" value="R" onclick="updateName(this);"/></td>
            <td><input type="button" name="T" value="T" onclick="updateName(this);"/></td>
            <td><input type="button" name="Y" value="Y" onclick="updateName(this);"/></td>
            <td><input type="button" name="U" value="U" onclick="updateName(this);"/></td>
            <td><input type="button" name="I" value="I" onclick="updateName(this);"/></td>
            <td><input type="button" name="O" value="O" onclick="updateName(this);"/></td>
            <td><input type="button" name="P" value="P" onclick="updateName(this);"/></td>
        </tr>
        <tr>
            <td><input type="button" name="A" value="A" onclick="updateName(this);"/></td>
            <td><input type="button" name="S" value="S" onclick="updateName(this);"/></td>
            <td><input type="button" name="D" value="D" onclick="updateName(this);"/></td>
            <td><input type="button" name="F" value="F" onclick="updateName(this);"/></td>
            <td><input type="button" name="G" value="G" onclick="updateName(this);"/></td>
            <td><input type="button" name="H" value="H" onclick="updateName(this);"/></td>
            <td><input type="button" name="J" value="J" onclick="updateName(this);"/></td>
            <td><input type="button" name="K" value="K" onclick="updateName(this);"/></td>
            <td><input type="button" name="L" value="L" onclick="updateName(this);"/></td>
        </tr>
        <tr>
            <td><input type="button" name="Z" value="Z" onclick="updateName(this);"/></td>
            <td><input type="button" name="X" value="X" onclick="updateName(this);"/></td>
            <td><input type="button" name="C" value="C" onclick="updateName(this);"/></td>
            <td><input type="button" name="V" value="V" onclick="updateName(this);"/></td>
            <td><input type="button" name="B" value="B" onclick="updateName(this);"/></td>
            <td><input type="button" name="N" value="N" onclick="updateName(this);"/></td>
            <td><input type="button" name="M" value="M" onclick="updateName(this);"/></td>
            <td><input type="button" name="," value="," onclick="updateName(this);"/></td>
            <td><input type="button" name="'" value="'" onclick="updateName(this);"/></td>
            <td><input type="button" name="-" value="-" onclick="updateName(this);"/></td>
        </tr>
        <tr>
            <td colspan="10">
                <input style="width:100%" type="button" name=" " value=" " onclick="updateName(this);"/>
            </td>
        </tr>
    </table>
    <table border="0" cellpadding="1" cellspacing="1" id="songlist">
    </table>
    <table border="0" cellpadding="1" cellspacing="1" bgcolor="black">
        <tr><td class="fieldname">歌曲名称</td><td class="fieldvalue"><span id="songname"></span></td></tr>
        <tr><td class="fieldname">歌曲ID</td><td class="fieldvalue"><span id="songid"></span></td></tr>
        <tr><td class="fieldname">歌曲PATH</td><td class="fieldvalue"><span id="songpath"></span></td></tr>
        <tr><td class="fieldname">作者</td><td class="fieldvalue"><span id="songauthor"></span></td></tr>
        <tr><td class="fieldname">BPM</td><td class="fieldvalue"><span id="songbpm"></span></td></tr>
        <tr><td class="fieldname">长度</td><td class="fieldvalue"><span id="songlength"></span></td></tr>
        <tr><td class="fieldname">是否拥有</td><td class="fieldvalue"><span id="songhas"></span></td></tr>
    </table>
    <table border="0" cellpadding="1" cellspacing="1" bgcolor="black" id="scoredetail">
        <tr class="head">
            <th colspan="2"></th>
            <th>满键</th>
            <th>满分</th>
            <th>SSS</th>
            <th>SS</th>
            <th>S</th>
            <th>三有</th>
            <th>评价</th>
            <th>更新时间</th>
            <th>三无</th>
            <th>评价</th>
            <th>更新时间</th>
        </tr>
        <tr class="row1">
            <td rowspan="3" style="border-bottom-style:solid;border-bottom-color:black;">EASY</td>
            <td>4Key</td>
            <td/><td/><td/><td/><td style="border-right-style:solid;border-right-color:black;"/><td style="color:red;"></td><td style="color:red;"></td><td/><td style="color:red;"></td><td style="color:red;"></td><td/>
        </tr>
        <tr class="row1">
            <td>5Key</td>
            <td/><td/><td/><td/><td style="border-right-style:solid;border-right-color:black;"/><td style="color:red;"></td><td style="color:red;"></td><td/><td style="color:red;"></td><td style="color:red;"></td><td/>
        </tr>
        <tr class="row1">
            <td style="border-bottom-style:solid;border-bottom-color:black;">6Key</td>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="border-right-style:solid;border-right-color:black;border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="color:red;border-bottom-style:solid;border-bottom-color:black;"></td>
            <td style="color:red;border-bottom-style:solid;border-bottom-color:black;"></td>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="color:red;border-bottom-style:solid;border-bottom-color:black;"></td>
            <td style="color:red;border-bottom-style:solid;border-bottom-color:black;"></td>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
        </tr>
        <tr class="row2">
            <td rowspan="3" style="border-bottom-style:solid;border-bottom-color:black;">NORMAL</td>
            <td>4Key</td>
            <td/><td/><td/><td/><td style="border-right-style:solid;border-right-color:black;"/><td style="color:red;"></td><td style="color:red;"></td><td/><td style="color:red;"></td><td style="color:red;"></td><td/>
        </tr>
        <tr class="row2">
            <td>5Key</td>
            <td/><td/><td/><td/><td style="border-right-style:solid;border-right-color:black;"/><td style="color:red;"></td><td style="color:red;"></td><td/><td style="color:red;"></td><td style="color:red;"></td><td/>
        </tr>
        <tr class="row2">
            <td style="border-bottom-style:solid;border-bottom-color:black;">6Key</td>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="border-right-style:solid;border-right-color:black;border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="color:red;border-bottom-style:solid;border-bottom-color:black;"></td>
            <td style="color:red;border-bottom-style:solid;border-bottom-color:black;"></td>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="color:red;border-bottom-style:solid;border-bottom-color:black;"></td>
            <td style="color:red;border-bottom-style:solid;border-bottom-color:black;"></td>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
        </tr>
        <tr class="row1">
            <td rowspan="3" style="border-bottom-style:solid;border-bottom-color:black;">HARD</td>
            <td>4Key</td>
            <td/><td/><td/><td/><td style="border-right-style:solid;border-right-color:black;"/><td style="color:red;"></td><td style="color:red;"></td><td/><td style="color:red;"></td><td style="color:red;"></td><td/>
        </tr>
        <tr class="row1">
            <td>5Key</td>
            <td/><td/><td/><td/><td style="border-right-style:solid;border-right-color:black;"/><td style="color:red;"></td><td style="color:red;"></td><td/><td style="color:red;"></td><td style="color:red;"></td><td/>
        </tr>
        <tr class="row1">
            <td style="border-bottom-style:solid;border-bottom-color:black;">6Key</td>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="border-right-style:solid;border-right-color:black;border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="color:red;border-bottom-style:solid;border-bottom-color:black;"></td>
            <td style="color:red;border-bottom-style:solid;border-bottom-color:black;"></td>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
            <td style="color:red;border-bottom-style:solid;border-bottom-color:black;"></td>
            <td style="color:red;border-bottom-style:solid;border-bottom-color:black;"></td>
            <td style="border-bottom-style:solid;border-bottom-color:black;"/>
        </tr>
    </table>
  </body>
<script type="text/javascript">
    var songs = new Array();
    function updateName(obj)
    {
        document.getElementsByName('name')[0].value = document.getElementsByName('name')[0].value + obj.value;
        updatesonglist();
    }
    function updatesonglist()
    {
        var table = document.getElementById('songlist');
        for(var i = table.rows.length - 1; i >= 0; i--)
        {
            table.deleteRow(i);
        }
        var pinyin = document.getElementsByName('name')[0].value;
        var count = 0;
        var row, cell;
        for(var i = 0; i < songs.length; i++)
        {
            if(songs[i][1].toUpperCase().indexOf(pinyin) >= 0)
            {
                if(count == 0)
                {
                    row = table.insertRow(-1);
                }
                cell = row.insertCell(-1);
                cell.innerHTML = "<input type=\"button\" value=\"" + songs[i][0] + "\" onclick=\"document.getElementsByName('name')[0].value = '';querySongDetail(" + songs[i][2] + ")\"/>";
                count++;
                if(count >= 5) {
                    count = 0;
                }
            }
        }
    }

            function querySongDetail(songid)
            {
                RMDwr.getSongdetailById(songid, after);
            }

            function after(list)
            {
                document.getElementById('songname').innerHTML = list[0]['songname'];
                document.getElementById('songid').innerHTML = list[0]['songid'];
                document.getElementById('songpath').innerHTML = list[0]['songpath'];
                document.getElementById('songauthor').innerHTML = list[0]['songauthor'];
                document.getElementById('songbpm').innerHTML = list[0]['songbpm'];
                document.getElementById('songlength').innerHTML = list[0]['songlength'];
                document.getElementById('songhas').innerHTML = list[0]['songhas'];
                var table = document.getElementById('scoredetail');
                for(var i = 0; i < 11; i++) {
                    for(var j = 0; j < 9; j++) {
                        table.rows[j + 1].cells[(j % 3 == 0) ? (i + 2) : (i + 1)].innerHTML = (list[1][j][i] == null) ? "" : list[1][j][i];
                    }
                }
            }
    <%
        for(int i = 0; i < songs.size(); i++) {
            String[] song = songs.get(i);
    %>
songs[<%=i%>] = new Array();songs[<%=i%>][0] = "<%=song[0]%>";songs[<%=i%>][1] = "<%=song[1]%>";songs[<%=i%>][2] = "<%=song[2]%>";
    <%
        }
    %>
</script>
</html>