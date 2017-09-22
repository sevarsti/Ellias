<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2017-3-23
  Time: 14:05:01
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
  <head><title>分数统计</title></head>
  <body>
  <%!
      void getResult(int[][] values, List<Map<String, Object>> list) {
          for(int i = 0; i < list.size(); i++) {
              Map<String, Object> m = list.get(i);
              int keytype = ((Number)m.get("keytype")).intValue();
              int playtype = ((Number)m.get("playtype")).intValue(); //0三有1三无2拥有三有3拥有三无
              int total = ((Number)m.get("total")).intValue();
              int done = ((Number)m.get("done")).intValue();
              int undone = ((Number)m.get("undone")).intValue();
              int full = ((Number)m.get("full")).intValue();
              int sss = ((Number)m.get("sss")).intValue();
              int ss = ((Number)m.get("ss")).intValue();
              int other = ((Number)m.get("other")).intValue();
              if(playtype % 2 == 0) {
                  values[keytype][playtype * 6 + playtype / 2] = total;
              }
              values[keytype][playtype * 6 + playtype / 2 + 1] = done;
              values[keytype][playtype * 6 + playtype / 2 + 2] = undone;
              values[keytype][playtype * 6 + playtype / 2 + 3] = full;
              values[keytype][playtype * 6 + playtype / 2 + 4] = sss;
              values[keytype][playtype * 6 + playtype / 2 + 5] = ss;
              values[keytype][playtype * 6 + playtype / 2 + 6] = other;
          }
      }
      void getSumResult(double[][] scores, Map<String, Object> map) {
          scores[0][0] = ((Number)map.get("full")).doubleValue();
          scores[0][1] = ((Number)map.get("hasfull")).doubleValue();
          scores[0][2] = ((Number)map.get("nofull")).doubleValue();
          scores[1][0] = ((Number)map.get("my")).doubleValue();
          scores[1][1] = ((Number)map.get("hasmy")).doubleValue();
          scores[1][2] = ((Number)map.get("nomy")).doubleValue();
          scores[2][0] = ((Number)map.get("ratio")).doubleValue();
          scores[2][1] = ((Number)map.get("hasratio")).doubleValue();
          scores[2][2] = ((Number)map.get("noratio")).doubleValue();
          scores[3][0] = ((Number)map.get("target")).doubleValue();
          scores[3][1] = ((Number)map.get("hastarget")).doubleValue();
          scores[3][2] = ((Number)map.get("notarget")).doubleValue();
          scores[4][0] = ((Number)map.get("diff")).doubleValue();
          scores[4][1] = ((Number)map.get("hasdiff")).doubleValue();
          scores[4][2] = ((Number)map.get("nodiff")).doubleValue();
          scores[5][0] = ((Number)map.get("fullex")).doubleValue();
          scores[5][1] = ((Number)map.get("hasfullex")).doubleValue();
          scores[5][2] = ((Number)map.get("nofullex")).doubleValue();
          scores[6][0] = ((Number)map.get("myex")).doubleValue();
          scores[6][1] = ((Number)map.get("hasmyex")).doubleValue();
          scores[6][2] = ((Number)map.get("nomyex")).doubleValue();
          scores[7][0] = ((Number)map.get("ratioex")).doubleValue();
          scores[7][1] = ((Number)map.get("hasratioex")).doubleValue();
          scores[7][2] = ((Number)map.get("noratioex")).doubleValue();
          scores[8][0] = ((Number)map.get("targetex")).doubleValue();
          scores[8][1] = ((Number)map.get("hastargetex")).doubleValue();
          scores[8][2] = ((Number)map.get("notargetex")).doubleValue();
          scores[9][0] = ((Number)map.get("diffex")).doubleValue();
          scores[9][1] = ((Number)map.get("hasdiffex")).doubleValue();
          scores[9][2] = ((Number)map.get("nodiffex")).doubleValue();
      }

      int[][] getDiff(int[][] list1, int[][] list2) {
          int[][] ret = new int[list1.length][list1[0].length];
          for(int i = 0; i < list1.length; i++) {
              for(int j = 0; j < list1[0].length; j++) {
                  ret[i][j] = list1[i][j] - list2[i][j];
              }
          }
          return ret;
      }
      double[][] getDiff2(double[][] list1, double[][] list2) {
          double[][] ret = new double[list1.length][list1[0].length];
          for(int i = 0; i < list1.length; i++) {
              for(int j = 0; j < list1[0].length; j++) {
                  ret[i][j] = list1[i][j] - list2[i][j];
              }
          }
          return ret;
      }
  %>
        <%
            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);

            int maxdt = jt.queryForInt("select max(pubdate) from rm_songstatus");
            int prvdt = jt.queryForInt("select max(pubdate) from rm_songstatus where pubdate < ?", new Object[]{maxdt});
            List<Map<String, Object>> list = jt.queryForList("select * from rm_songstatus where pubdate = ? order by keytype, playtype", new Object[]{maxdt});
            int[][] todaylist = new int[16][26];
            getResult(todaylist, list);
            list = jt.queryForList("select * from rm_songstatus where pubdate = ? order by keytype, playtype", new Object[]{prvdt});
            int[][] prvlist = new int[16][26];
            getResult(prvlist, list);
            int[][] diff = getDiff(todaylist, prvlist);
            String[] desc = new String[]{"4 Easy","5 Easy","6 Easy","4 Normal","5 Normal","6 Normal","4 Hard","5 Hard","6 Hard","Easy","Normal","Hard","4 Key","5 Key","6 Key","Total"};
            Map<String, Object> map = jt.queryForMap("select * from rm_songstatussum where pubdate = ?", new Object[]{maxdt});
            double[][] scores = new double[10][3];
            getSumResult(scores, map);
            map = jt.queryForMap("select * from rm_songstatussum where pubdate = ?", new Object[]{prvdt});
            double[][] prvscores = new double[10][3];
            getSumResult(prvscores, map);
            double[][] scorediff = getDiff2(scores, prvscores);
            DecimalFormat intDf = new DecimalFormat("0");
            DecimalFormat percentDf = new DecimalFormat("0.0000%");
            String[] desc2 = new String[]{"已打歌曲满分","已打歌曲总分","百分比","目标","差距","去除极限","已打歌曲满分","已打歌曲总分","百分比","目标","差距"};
        %>
        <table border="0" cellpadding="1" cellspacing="1" bgcolor="black">
            <tr class="head">
                <th rowspan="3" style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">&nbsp;</th>
                <th colspan="13" style="border-right-style:solid;border-right-color:black;">总数</th>
                <th colspan="13" style="border-right-style:solid;border-right-color:black;">已有歌曲</th>
            </tr>
            <tr class="head">
                <th rowspan="2"style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">歌曲总数</th>
                <th colspan="6" style="border-right-style:solid;border-right-color:black;">三有</th>
                <th colspan="6" style="border-right-style:solid;border-right-color:black;">三无</th>
                <th rowspan="2"style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">歌曲总数</th>
                <th colspan="6" style="border-right-style:solid;border-right-color:black;">三有</th>
                <th colspan="6" style="border-right-style:solid;border-right-color:black;">三无</th>
            </tr>
            <tr class="head">
                <th style="border-bottom-style:solid;border-bottom-color:black;">已打总数</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">未打</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">FULL</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SSS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">OTHER</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">已打总数</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">未打</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">FULL</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SSS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">OTHER</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">已打总数</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">未打</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">FULL</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SSS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">OTHER</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">已打总数</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">未打</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">FULL</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SSS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">OTHER</th>
            </tr>
            <%
                for(int i = 0; i < 16; i++) {
                    int[] today = todaylist[i];
                    int[] diffs = diff[i];
            %>
            <tr class="row<%=(i / 3) % 2 + 1%>">
                <td style="<%=(i % 3 == 2 || i == 15) ? "border-bottom-style:solid;border-bottom-color:black;" : ""%>border-right-style:solid;border-right-color:black;"><%=desc[i]%></td>
                <%
                    for(int j = 0; j < 26; j++) {
                        boolean needRight = false;
                        if(j == 0 || j == 6 || j == 12 || j == 13 || j == 19 || j == 20 || j == 25) {
                            needRight = true;
                        }
                %>
                    <td onclick="showdetail(<%=i%>, <%=j%>);" style="cursor:pointer;<%=(i % 3 == 2 || i == 15) ? "border-bottom-style:solid;border-bottom-color:black;" : ""%><%=needRight ? "border-right-style:solid;border-right-color:black;" : ""%>" align="right">
                        <%if(diffs[j] > 0){%>
                        (<span style="color: red;">+<%=diffs[j]%></span>)
                        <%}%>
                        <%if(diffs[j] < 0){%>
                        (<span style="color: green;"><%=diffs[j]%></span>)
                        <%}%>
                        <%=today[j]%>
                    </td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>
        </table>
        <br/>
        <br/>
        <table border="0" cellpadding="1" cellspacing="1" bgcolor="black">
            <tr class="head">
                <td>&nbsp;</td>
                <td>共计</td>
                <td>三有</td>
                <td>三无</td>
            </tr>
            <%
                for(int i = 0; i < 10; i++) {
            %>
            <c:if test="<%=i==5%>">
                <tr class="head">
                    <td colspan="4">去除极限</td>
                </tr>
            </c:if>
            <tr class="row<%=i % 2 + 1%>">
                <td>
                    <%=desc2[i]%>
                </td>
                <td align="right">
                    <c:if test="<%=scorediff[i][0]>0%>">
                        (<span style="color: red;">+<%=(i == 2 || i == 3 || i == 7 || i == 8) ? intDf.format(scorediff[i][0] * 10000000) : intDf.format(scorediff[i][0])%></span>)
                    </c:if>
                    <c:if test="<%=scorediff[i][0]<0%>">
                        (<span style="color: green;"><%=(i == 2 || i == 3 || i == 7 || i == 8) ? intDf.format(scorediff[i][0] * 10000000) : intDf.format(scorediff[i][0])%></span>)
                    </c:if>
                    <%=(i == 2 || i == 3 || i == 7 || i == 8) ? percentDf.format(scores[i][0]) : intDf.format(scores[i][0])%>
                </td>
                <td align="right">
                    <c:if test="<%=scorediff[i][1]>0%>">
                        (<span style="color: red;">+<%=(i == 2 || i == 3 || i == 7 || i == 8) ? intDf.format(scorediff[i][1] * 10000000) : intDf.format(scorediff[i][1])%></span>)
                    </c:if>
                    <c:if test="<%=scorediff[i][1]<0%>">
                        (<span style="color: green;"><%=(i == 2 || i == 3 || i == 7 || i == 8) ? intDf.format(scorediff[i][1] * 10000000) : intDf.format(scorediff[i][1])%></span>)
                    </c:if>
                    <%=(i == 2 || i == 3 || i == 7 || i == 8) ? percentDf.format(scores[i][1]) : intDf.format(scores[i][1])%>
                </td>
                <td align="right">
                    <c:if test="<%=scorediff[i][2]>0%>">
                        (<span style="color: red;">+<%=(i == 2 || i == 3 || i == 7 || i == 8) ? intDf.format(scorediff[i][2] * 10000000) : intDf.format(scorediff[i][2])%></span>)
                    </c:if>
                    <c:if test="<%=scorediff[i][2]<0%>">
                        (<span style="color: green;"><%=(i == 2 || i == 3 || i == 7 || i == 8) ? intDf.format(scorediff[i][2] * 10000000) : intDf.format(scorediff[i][2])%></span>)
                    </c:if>
                    <%=(i == 2 || i == 3 || i == 7 || i == 8) ? percentDf.format(scores[i][2]) : intDf.format(scores[i][2])%>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <br/>
        <br/>
  <span id="content"></span>
  </body>
<script type="text/javascript">
    function showdetail(i, j)
    {
        RMDwr.getSongdetail(i, j, after);
    }

    function after(obj)
    {
        document.getElementById('content').innerHTML = obj;
//        alert('done');
    }
</script>
</html>