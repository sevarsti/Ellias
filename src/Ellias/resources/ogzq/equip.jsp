<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-16
  Time: 22:33:25
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-15
  Time: 20:01:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="com.saille.ogzq.*" %>
<%@ page import="java.net.URLEncoder" %>
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
    <%!
        String convertAttributes(String in1, String in2) {
            int gk = 0, d = 0, m = 0, f = 0;
            if(in1 != null && in1.length() > 0) {
                String[] s = in1.split("，");
                for(String ss : s) {
                    if(ss.startsWith("突破") || ss.startsWith("射门")) {
                        f += Integer.parseInt(ss.substring(ss.indexOf("+") + 1));
                    } else if(ss.startsWith("传球") || ss.startsWith("速度")) {
                        m += Integer.parseInt(ss.substring(ss.indexOf("+") + 1));
                    } else if(ss.startsWith("断球") || ss.startsWith("铲球")) {
                        d += Integer.parseInt(ss.substring(ss.indexOf("+") + 1));
                    } else if(ss.startsWith("扑救") || ss.startsWith("出击")) {
                        gk += Integer.parseInt(ss.substring(ss.indexOf("+") + 1));
                    }
                }
            }
            if(in2 != null && in2.length() > 0) {
                String[] s = in2.split("，");
                for(String ss : s) {
                    if(ss.startsWith("突破") || ss.startsWith("射门")) {
                        f += Integer.parseInt(ss.substring(ss.indexOf("+") + 1));
                    } else if(ss.startsWith("传球") || ss.startsWith("速度")) {
                        m += Integer.parseInt(ss.substring(ss.indexOf("+") + 1));
                    } else if(ss.startsWith("断球") || ss.startsWith("铲球")) {
                        d += Integer.parseInt(ss.substring(ss.indexOf("+") + 1));
                    } else if(ss.startsWith("扑救") || ss.startsWith("出击")) {
                        gk += Integer.parseInt(ss.substring(ss.indexOf("+") + 1));
                    }
                }
            }
            if(gk + d + m + f == 0) {
                return "";
            } else {
                return "<br/>门：" + (gk) + "，卫：" + (d) + "，中：" + (m) + "，锋：" + (f);
            }
        }

        List<String[]> insertItems(List<String[]> items, String[] newItem) {
            boolean insert = false;
            for(int i = 0; i < items.size(); i++) {
                String[] item = items.get(i);
                if(item[1].compareTo(newItem[3]) >= 0) {
                    items.add(i, new String[]{newItem[0], newItem[3], newItem[11], newItem[7], newItem[13], newItem[3].replaceAll("[^0-9]", "")});
                    insert = true;
                    break;
                }
            }
            if(!insert) {
                items.add(new String[]{newItem[0], newItem[3], newItem[11], newItem[7], newItem[13], newItem[3].replaceAll("[^0-9]", "")});
            }
            return items;
        }

        String getXianliangDesc(String in) {
            if(in == null || in.length() == 0) {
                return "";
            }
            String[] parts = in.split("，");
            String gk = "0", d = "0", m = "0", f = "0";
            boolean begin = false;
            for(int i = 0; i < parts.length; i++) {
                String p = parts[i];
                if(i == 0 && p.indexOf("属性") >= 0) {
                    continue;
                } else if(i == 0) {
                    i = 1;
                    continue;
                }
                if(p.indexOf(",") > 0) {
                    p = p.substring(0, p.indexOf(","));
                }
                if(p.startsWith("<")) {
                    p = p.substring(p.indexOf(">") + 1);
                }
                if(p.indexOf(">") > 0) {
                    p = p.substring(0, p.indexOf("<"));
                }
                if(p.startsWith("铲球") || p.startsWith("断球")) {
                    d = (Integer.parseInt(d) + Integer.parseInt(p.substring(p.indexOf("+") + 1))) + "";
                } else if(p.startsWith("突破") || p.startsWith("射门")) {
                    f = (Integer.parseInt(f) + Integer.parseInt(p.substring(p.indexOf("+") + 1))) + "";
                } else if(p.startsWith("出击") || p.startsWith("扑救")) {
                    gk = (Integer.parseInt(gk) + Integer.parseInt(p.substring(p.indexOf("+") + 1))) + "";
                } else if(p.startsWith("传球") || p.startsWith("速度")) {
                    m = (Integer.parseInt(m) + Integer.parseInt(p.substring(p.indexOf("+") + 1))) + "";
                }
            }
            return "门：" + gk + "，卫：" + d + "，中：" + m + "，锋：" + f;
        }

        List<String[]> getItems(String email) throws Exception {
            List<String[]> ret = new ArrayList<String[]>();
            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            params.add(new BasicNameValuePair("itemtype", "2"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(email, pm);
            String[] items = s.split("[|]");
            List<String[]> xianliang = new ArrayList<String[]>();
            List<String[]> qiuyi = new ArrayList<String[]>();
            List<String[]> huxi = new ArrayList<String[]>();
            List<String[]> qiuxie = new ArrayList<String[]>();
            List<String[]> shoutao = new ArrayList<String[]>();
            List<String[]> xiubiao = new ArrayList<String[]>();
            for(String item : items) {
                String[] atts = item.split("[*]");
                if(atts[3].startsWith("限量")) {
                    xianliang.add(new String[]{atts[0], atts[3], atts[11], atts[7], getXianliangDesc(atts[13]), atts[3].replaceAll("[^0-9]", "")});
                }else if(atts[3].startsWith("战袍")) {
                    insertItems(qiuyi, atts);
                } else if(atts[3].startsWith("护腿")) {
                    insertItems(huxi, atts);
                } else if(atts[3].startsWith("战靴")) {
                    insertItems(qiuxie, atts);
                } else if(atts[3].startsWith("手套")) {
                    insertItems(shoutao, atts);
                } else if(atts[3].startsWith("袖标")) {
                    insertItems(xiubiao, atts);
                }
            }
            ret.addAll(xianliang);
            ret.addAll(qiuyi);
            ret.addAll(huxi);
            ret.addAll(qiuxie);
            ret.addAll(shoutao);
            ret.addAll(xiubiao);
            return ret;
        }
    %>
    <%
        String email = request.getParameter("email");
        List<Map<String, String>> players = new OgzqDwr().viewPlayer(email);
        List<String[]> equips = this.getItems(email);
        request.setAttribute("equips", equips);

        //限量积分
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("EuroleaguePoint1", ""));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String xianliangPoint = IDUtils.execute(email, pm);
    %>
        <%=email%>/<%=IDUtils.getNick(email)%>/<%=IDUtils.IDInfos.get(email).get("silver")%>
    <input type="button" value="宝石镶嵌" onclick="javascript:window.open('equipgem.jsp?email=<%=URLEncoder.encode(email)%>')"/>
    <br/>
        <table id="info" width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
            <tr class="head">
                <!--<th>ID</th>-->
                <th>名称</th>
                <th>位置</th>
                <th>能力</th>
                <th>替补</th>
                <th>战袍</th>
                <th>护腿</th>
                <th>战靴</th>
                <th>手套</th>
                <th>袖标</th>
            </tr>
            <%
                for(int i = 1; i < players.size(); i++) {
                    Map<String, String> p = players.get(i);
//                    Map<String, String> detail = OperationUtils.viewPlayer(email, p.get("id"));
                    Map<String, String> detail = CacheManager.loadPlayer(email, p.get("id"), false);
                    String color = "black";
            %>
            <tr class="row<%=i % 2 + 1%>">
                <%--<td><%=p.get("id")%></td>--%>
                <td>
                    <input type="radio" name="equipPlayer" value="<%=p.get("id")%>" style="padding-left: 1px;padding-right: 1px;"/>
                    <%=p.get("name")%>
                </td>
                <td><%=detail.get("pos")%></td>
                <td>
                    <%=detail.get("ability")+"/"+p.get("ability")%>
                </td>
                <td><%=p.get("tibu")%></td>
            <!--</tr>-->
            <%--<tr class="row<%=i % 2 + 1%>" id="eq<%=p.get("id")%>" style="display:none;">--%>
                <!--<td colspan="16">-->
                    <!--<table border="0" cellpadding="1" cellspacing="1" width="100%">-->
                        <!--<tr>-->
                            <td>
                                <input type="button" value="卸" onclick="removeEquipment('<%=p.get("id")%>', '0')"/>
                                <%=detail.get("qiuyi")%>
                                <c:if test="<%=!(detail.get("qiuyi3") == null || "0".equals(detail.get("qiuyi3")))%>">
                                    -<%=detail.get("qiuyi3")%>级宝石
                                </c:if>
                                <%if(detail.containsKey("qiuyi1")){%>
                                <%=convertAttributes(detail.get("qiuyi1"), detail.get("qiuyi2"))%>
                                <%}%>
                            </td>
                            <td>
                                <input type="button" value="卸" onclick="removeEquipment('<%=p.get("id")%>', '1')"/>
                                <%=detail.get("huxi")%>
                                <c:if test="<%=!(detail.get("huxi3") == null || "0".equals(detail.get("huxi3")))%>">
                                    -<%=detail.get("huxi3")%>级宝石
                                </c:if>
                                <%if(detail.containsKey("huxi1")){%>
                                <%=convertAttributes(detail.get("huxi1"), detail.get("huxi2"))%>
                                <%--<%=detail.get("huxi1")%>，<%=detail.get("huxi2")%>--%>
                                <%}%>
                            </td>
                            <td>
                                <input type="button" value="卸" onclick="removeEquipment('<%=p.get("id")%>', '2')"/>
                                <%=detail.get("qiuxie")%>
                                <c:if test="<%=!(detail.get("qiuxie3") == null || "0".equals(detail.get("qiuxie3")))%>">
                                    -<%=detail.get("qiuxie3")%>级宝石
                                </c:if>
                                <%if(detail.containsKey("qiuxie1")){%>
                                <%=convertAttributes(detail.get("qiuxie1"), detail.get("qiuxie2"))%>
                                <%}%>
                            </td>
                            <td>
                                <input type="button" value="卸" onclick="removeEquipment('<%=p.get("id")%>', '3')"/>
                                <%=detail.get("shoutao")%>
                                <c:if test="<%=!(detail.get("shoutao3") == null || "0".equals(detail.get("shoutao3")))%>">
                                    -<%=detail.get("shoutao3")%>级宝石
                                </c:if>
                                <%if(detail.containsKey("shoutao1")){%>
                                <%=convertAttributes(detail.get("shoutao1"), detail.get("shoutao2"))%>
                                <%}%>
                            </td>
                    <td>
                        <%if("-1".equals(detail.get("xiubiao")) && Integer.parseInt(p.get("pinzhi")) > 3){%>
                            <input type="button" value="开槽" onclick="openXiubiao('<%=p.get("id")%>')"/>
                        <%}else{%>
                            <input type="button" value="卸" onclick="removeEquipment('<%=p.get("id")%>', '4')"/>
                            <%=detail.get("xiubiao")%>
                            <c:if test="<%=!(detail.get("xiubiao3") == null || "0".equals(detail.get("xiubiao3")))%>">
                                -<%=detail.get("xiubiao3")%>级宝石
                            </c:if>
                            <%if(detail.containsKey("xiubiao1")){%>
                            <%=convertAttributes(detail.get("xiubiao1"), detail.get("xiubiao2"))%>
                            <%--<%=detail.get("xiubiao1")%>，<%=detail.get("xiubiao2")%>--%>
                            <%}%>
                        <%}%>
                    </td>
                        <!--</tr>-->
                    <!--</table>-->
                <!--</td>-->
            </tr>
            <%
                }
            %>
        </table>
    银币：<%=IDUtils.IDInfos.get(email).get("silver")%>，限量积分：<%=xianliangPoint%>
    <br/>
    <c:forEach items="${equips}" var="e">
        ${e[0]}-${e[2]}-${e[1]}，${e[3]}个，${e[4]}
        <input type="button" value="穿" onclick="wearEquip('${e[0]}', '${e[2]}');"/>
        <c:choose>
            <c:when test="${fn:startsWith(e[1], '限量')}">
                <input type="button" value="刷新" onclick="refreshEquip('${e[0]}', '${e[5]}')"/>
            </c:when>
            <c:otherwise>
                <input type="button" value="加成" onclick="bonusEquip('${e[0]}')"/>
            </c:otherwise>
        </c:choose>
        <br/>
    </c:forEach>
    </body>
<script type="text/javascript">
    function after(msg) {
        alert(msg);
    }

    function refreshEquip(id, level) {
        OgzqDwr.equipRefresh('<%=email%>', id, level, after);
    }

    function bonusEquip(id) {
        OgzqDwr.equipBonus('<%=email%>', id, after);
    }

    function removeEquipment(playerId, type) {
        OgzqDwr.removeEquipment('<%=email%>', playerId, type, after);
    }

    function openXiubiao(id) {
        OgzqDwr.openXiubiao('<%=email%>', id, after);
    }

    function wearEquip(equipId, itemId) {
        var list = document.getElementsByName('equipPlayer');
        var playerId = '';
        for(var i = 0; i < list.length; i++) {
            if(list[i].checked) {
                playerId = list[i].value;
                break;
            }
        }
        if(playerId != '') {
            OgzqDwr.wearEquipment('<%=email%>', playerId, equipId, itemId, after);
        }
    }
</script>
</html>