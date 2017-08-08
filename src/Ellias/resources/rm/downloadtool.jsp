<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: ELLIAS
  Date: 2016-1-1
  Time: 21:55:03
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
  <head><title>节奏大师工具下载</title></head>
    <body>
    <%
        List<String[]> tools = new ArrayList<String[]>();
        String name, author, type, desc, link;
        name = "MROCK_SONG_CLIENT_ANDROID修改工具EXCEL";
        author = "Ellias";
        type = "工具";
        desc = "可以读取bin文件至表格中，修改完之后直接保存至bin文件，不需要转换为xml<br/>使用时需要启用宏，并配置文件的读取和保存路径";
        link = "MROCK_SONG_CLIENT_ANDROID修改工具EXCEL";
        tools.add(new String[]{name, author, type, desc, link});

        name = "安卓简易官方谱面下载器";
        author = "节奏大师干驴";
        type = "工具";
        desc = "首先，保证你有/sdcard/RM/res/ mrock_song_client_android.xml，以便程序读取path，如果没有，首次打开会自动下载。<br/>\n" +
"其次，保证你想下载的东西在节奏大师中没下载过，如果想重新下载，请删除原来的文件。<br/>\n" +
"然后，点击你想要下载的歌曲，选择要下载的文件。<br/>\n" +
"最后，到节奏大师相应的文件夹去找。";
        link = "节操下载器";
        tools.add(new String[]{name, author, type, desc, link});

        name = "节奏作谱师v2";
        author = "节奏大师干驴";
        type = "工具";
        desc = "更新日志：<br/>\n" +
"+去除短键<br/>\n" +
"+txt谱面<br/>\n" +
"+面条排序<br/>\n" +
"*修复谱面结尾错误<br/>\n" +
"-跳转到按钮<br/>\n" +
"<br/>\n" +
"新版本说明：<br/>\n" +
"1.新增去除短键功能，输入时间小于多少的短面条自动转化为单键，规则如下：只去除开头和结尾的竖线，开头向后对齐，结尾向前对齐，单个竖面条向前对齐。<br/>\n" +
"2.txt格式谱面，当保存文件时，同时保存imd,pts和txt谱面，txt可以手动修改。<br/>\n" +
"<br/>\n" +
"txt格式说明：<br/>\n" +
"PtsFile文件头<br/>\n" +
"歌曲时间，几键模式<br/>\n" +
"节拍时间，bpm…<br/>\n" +
"BeatsEnd节拍结束<br/>\n" +
"轨道，时间—轨道，时间…<br/>\n" +
"PointsEnd端点结束<br/>\n" +
"每一行为一个整体，面条中对应的是端点位置，两个端点之间用断线连接。";
        link = "节奏作谱师v2";
        tools.add(new String[]{name, author, type, desc, link});

        name = "最强出击（安卓版）";
        author = "节奏大师干驴";
        type = "工具";
        desc = "使用说明：<br/>\n" +
"1、你要有一个文件浏览器<br/>\n" +
"2、用文件浏览器打开谱面所在文件夹<br/>\n" +
"3、保证同一文件夹里有imd谱面和mp3歌曲，png图片不必须<br/>\n" +
"4、点击imd文件，用最强出击打开<br/>\n" +
"声明：<br/>\n" +
"1、判定比较松，非常容易打出大Perfect，几乎打不出来Great<br/>\n" +
"2、连击数可能略少于XXXX<br/>\n" +
"3、调试时发现有时可能会闪退，错误出现在触摸屏幕的事件上，但无论如何我也找不到具体解决措施<br/>\n" +
"4、暂时只提供4倍速<br/>\n" +
"5、干驴荣誉出品，感谢★邹开发小组的支持 @_星邹_@qq1473636901 @安灬霁乔<br/>";
        link = "最强出击（安卓版）";
        tools.add(new String[]{name, author, type, desc, link});

        name = "节操文件下载器";
        author = "★邹";
        type = "工具";
        desc = "用来下载节奏大师官方谱面图片音乐。需要MD5list.xml这个文件。输入path后点击下载曲谱会把文件下载到程序运行目录";
        link = "节操文件下载器";
        tools.add(new String[]{name, author, type, desc, link});

        name = "谱面改名器";
        author = "节奏大师干驴";
        type = "工具";
        desc = "改一个文件夹中的音乐、谱面、图片的文件名";
        link = "谱面改名器（安卓版）";
        tools.add(new String[]{name, author, type, desc, link});

        name = "CharacterBinEditor 人物开场白修改工具";
        author = "萨弗拉斯之锤";
        type = "工具";
        desc = "★注意：仅限于mrock.character_client.bin的修改<br/>\n" +
"★使用方法：<br/>\n" +
"1.点击“打开”按钮读取已有的bin，或者“官方bin”自动下载并读取最新的bin<br/>\n" +
"2.在左侧选择框中选择需要修改的开场白<br/>\n" +
"3.在下方Gaming Speaking框中修改文字（注意长度限制）<br/>\n" +
"4.点击下方“修改”按钮<br/>\n" +
"5.点击“保存”按钮<br/>\n" +
"6.其他功能自行探索，不在此赘述<br/>\n" +
"★若下载后安全软件报毒，请添加信任<br/>\n" +
"★请正常使用该工具，若出现错误，作者概不负责！<br/>\n" +
"★Made by hTC<br/>";
        link = "CharacterBinEditor";
        tools.add(new String[]{name, author, type, desc, link});
    %>
    <h1>说明：本站仅提供下载，不对内容有效性及安全性作任何保证，如有疑惑请勿下载。<br/>使用说明请参阅百度睡梦中吧。</h1>
        <table cellpadding="1" cellspacing="1" border="0" bgcolor="black" width="100%">
            <tr class="head">
                <th>名称</th>
                <th>作者</th>
                <th>分类</th>
                <th>描述</th>
                <th>链接</th>
            </tr>
            <%
                for(int i = 0; i < tools.size(); i++) {
                    name = tools.get(i)[0];
                    author = tools.get(i)[1];
                    type = tools.get(i)[2];
                    desc = tools.get(i)[3];
                    link = tools.get(i)[4];
            %>
            <tr class="row<%=i % 2 + 1%>">
                <td><%=name%></td>
                <td><%=author%></td>
                <td><%=type%></td>
                <td><%=desc%></td>
                <td>
                    <a href="download.do?name=<%=link%>">
                        下载
                    </a>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>