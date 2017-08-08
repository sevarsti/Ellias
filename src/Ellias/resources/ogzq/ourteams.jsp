<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2015-9-15
  Time: 11:20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="com.saille.ogzq.*" %>
<%@ page import="org.apache.commons.configuration.PropertiesConfiguration" %>
<%@ page import="org.apache.http.client.HttpClient" %>
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
        public static List<String[]> sort(List<String[]> list, int start, int end) {
            if(start >= end) {
                return list;
            }
            int pos = start;
            for(int i = pos + 1; i < end; i++) {
                boolean needSwap = false;
                if(Integer.parseInt(list.get(i)[4]) > Integer.parseInt(list.get(pos)[4])) {
                    needSwap = true;
                }

                if(needSwap) {
                    String[] tmp = list.get(i);
                    for(int m = i; m > pos; m--) {
                        list.set(m, list.get(m - 1));
                    }
                    list.set(pos, tmp);
                }
                pos = i;
            }
            sort(list, start, pos);
            sort(list, pos + 1, end);
            return list;
        }

        public static List<String[]> sort2(List<String[]> list, int start, int end, List<String> list2) {
            if(start >= end) {
                return list;
            }
            int pos = start;
            for(int i = pos + 1; i < end; i++) {
                boolean needSwap = false;
                if(Integer.parseInt(list.get(i)[2]) > Integer.parseInt(list.get(pos)[2])) {
                    needSwap = true;
                }

                if(needSwap) {
                    String[] tmp = list.get(i);
                    String t2 = list2.get(i);
                    for(int m = i; m > pos; m--) {
                        list.set(m, list.get(m - 1));
                        list2.set(m, list2.get(m - 1));
                    }
                    list.set(pos, tmp);
                    list2.set(pos, t2);
                }
                pos = i;
            }
            sort2(list, start, pos, list2);
            sort2(list, pos + 1, end, list2);
            return list;
        }
    %>
    <%
        if("toother".equals(request.getParameter("action"))) { //转让
            String target = request.getParameter("target");
            /* 目标号 */
            PropertiesConfiguration conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/allid.ini"));
            conf.setEncoding("GB2312");
            String teamid = conf.getString(target);
            conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/allpwd.ini"));
            String pwd = conf.getString(teamid);
            if(teamid == null || pwd == null) {
                request.setAttribute("msg", "找不到号: " + teamid);
            } else {
                HttpClient client = LoginUtils.Login(teamid, pwd);
                if(client == null) {
                    request.setAttribute("msg", "登录失败");
                } else {
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "12"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String ret = IDUtils.execute(client, teamid, pm);
                    String clubid = ret.split("\\|")[0];

                    pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "2"));
                    params.add(new BasicNameValuePair("TeamID", clubid));
                    
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    ret = IDUtils.execute(client, teamid, pm);
                    String[] teams = ret.split("@")[2].split("!")[1].split("&");

                    String leadernick = null;
                    for(String team : teams) {
                        if(team.split("\\|")[3].equals("0")) {
                            leadernick = team.split("\\|")[1];
                            break;
                        }
                    }
                    if(leadernick == null) {
                        request.setAttribute("msg", "获取会长号失败，teamid=" + clubid);
                    } else {
                        conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/allid.ini"));
                        conf.setEncoding("GB2312");
                        String leaderid = conf.getString(leadernick);
                        conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/allpwd.ini"));
                        String leaderpwd = conf.getString(leaderid);
                        if(leaderid == null || leaderpwd == null) {
                            request.setAttribute("msg", "找不到会长号: " + leadernick);
                        } else {
                            client = LoginUtils.Login(leaderid, leaderpwd);
                            if(client == null) {
                                request.setAttribute("msg", "会长号登录失败");
                            } else {
                                pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("type", "19"));
                                params.add(new BasicNameValuePair("teamid", clubid));
                                params.add(new BasicNameValuePair("clubid", IDUtils.IDObjIds.get(teamid)[0]));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                ret = IDUtils.execute(client, teamid, pm);
                                request.setAttribute("msg", "球会转让结果：" + ret);
                            }
                        }
                    }
                }
            }

        } else if("change".equals(request.getParameter("action"))) {
            String teamnick = request.getParameter("teamname");
            String targetclub = request.getParameter("target");
            if(teamnick == null || targetclub == null) {
                request.setAttribute("msg", "找不到参数");
            } else {
                try {
                    /* 获取会长号 */
                    String leadernick = null;
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "2"));
                    params.add(new BasicNameValuePair("TeamID", targetclub));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String ret = IDUtils.execute(IDUtils.GETIDS().get(0), pm);
                    String[] teams = ret.split("@")[2].split("!")[1].split("&");
                    for(String team : teams) {
                        if(team.split("\\|")[3].equals("0")) {
                            leadernick = team.split("\\|")[1];
                            break;
                        }
                    }
                    if(leadernick == null) {
                        request.setAttribute("msg", "获取会长号失败，teamid=" + targetclub);
                    } else {
                        PropertiesConfiguration conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/allid.ini"));
                        conf.setEncoding("GB2312");
                        String teamid = conf.getString(teamnick);
                        String leaderid = conf.getString(leadernick);
                        conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/allpwd.ini"));
                        String pwd = conf.getString(teamid);
                        String leaderpwd = conf.getString(leaderid);
                        if(pwd == null || leaderpwd == null) {
                            request.setAttribute("msg", "获取密码失败，teamid=" + targetclub + ", leader=" + leadernick);
                        } else {
                            request.setAttribute("msg", "teamnick=" + teamnick + ", targetclub=" + targetclub + ",teamid=" + teamid + ",leaderid=" + leaderid);

                            /* 退会 */
                            HttpClient client = LoginUtils.Login(teamid, pwd);

                            pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("type", "12"));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            String s = IDUtils.execute(client, teamid, pm);
                            String currentid = s.split("\\|")[0];

                            pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("type", "11"));
                            params.add(new BasicNameValuePair("teamid", currentid));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            s = IDUtils.execute(client, teamid, pm);
                            System.out.println("退会结果: " + s);

                            /* 申请 */
                            pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("type", "5"));
                            params.add(new BasicNameValuePair("TeamID", targetclub));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            s = IDUtils.execute(client, teamid, pm);
                            System.out.println("球会申请结果：" + s);

                            /* 批准 */
                            client = LoginUtils.Login(leaderid, leaderpwd);
                            pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("type", "7"));
                            params.add(new BasicNameValuePair("clubid", IDUtils.IDObjIds.get(teamid)[0]));
                            params.add(new BasicNameValuePair("TeamID", targetclub));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            s = IDUtils.execute(client, leaderid, pm);
                            System.out.println("球会申请同意结果：" + s);
                        }
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                    request.setAttribute("msg", "获取密码失败，team=" + teamnick);
                }
            }
        }
//        String[] teamids = new String[]{"106", "132", "2329", "4721", "17033", "868", "924", "585", "2569", "10134", "137", "8584", "2365", "366", "124", "256", "51", "25516", "502", "40", "94"};
//        String[] teamids = new String[]{"10134","1056","106","11266","1180","1188","124","12994","132","1355","137","1373","14422","1463","1475","14953","1497","1506","15662","15750","15796","15808","1590","15905","16","16007","1602","16839","16882","16918","17033","17067","1710","184","204","2040","20454","2057","20619","20631","20643","20655","21000","2112","2119","21204","21216","21228","21264","21276","21288","21300","21392","21529","21540","21551","21586","21610","21660","21708","21756","21792","21804","21828","21840","21864","21876","21888","21900","21912","21924","21936","21948","21960","21972","21996","22032","22044","22068","22092","22104","22116","22140","22152","22246","22318","22330","22342","22354","22379","22731","2329","23321","2365","24","247","24946","25516","256","2569","26472","2651","26562","26610","2663","26694","2675","27046","27288","27682","27820","27850","27856","28030","28036","28366","28432","28612","28636","28648","28654","2866","28660","28666","28672","28678","28684","28690","28750","28786","28846","28912","28930","28936","28942","29002","29050","29068","29104","29128","29170","29176","29218","29224","29230","29242","29248","29266","29272","2941","299","30633","30683","307","34766","34970","35029","356","3575","3755","3803","39073","39157","39241","39333","39885","40","42193","4236","42789","42795","42801","42807","42813","42819","42825","42831","42837","42843","42849","42855","42861","42867","42873","42885","42891","42897","42903","42909","43137","43143","43149","43155","43161","43167","43173","43179","43185","43191","43197","43203","43209","43215","43221","43227","43233","43239","43245","43251","43257","43443","43581","43587","43593","43599","43605","43611","43617","43647","43653","43659","43665","43683","43689","43695","43701","43707","43713","43719","43725","43731","43737","43743","43749","43755","43761","43767","43773","43779","43785","43791","43797","43803","43809","43815","43821","43827","43833","43839","43845","43851","43857","43863","43869","43875","43881","43893","43899","43905","43911","43917","43923","43929","43935","43941","43989","44185","44307","44313","44319","44325","44331","44601","44751","44811","47137","4721","479","4828","484","49009","49477","4950","5002","502","50353","50485","51","51163","51484","51489","51492","51500","51503","51506","51507","51510","51511","51515","51516","51517","51521","51525","51530","51540","51542","51543","51544","51546","51547","51548","51550","51551","51552","51553","51554","51555","51556","51557","51558","51560","51561","51562","51563","51564","51565","51566","51567","51568","51569","51570","51571","51572","51573","51574","51575","51576","51577","51578","51579","51580","51581","51582","51583","51584","51585","51586","51587","51588","51589","51590","51591","51592","51593","51594","51595","51596","51597","51598","51599","51600","51601","51602","51603","51604","51605","51606","51607","51608","51609","51610","51611","51612","51613","51614","51615","51616","51617","51618","51619","51620","51621","51622","51623","51624","51625","51626","51627","51628","51629","51630","51631","51632","51633","51634","51635","51636","51637","51638","51639","51640","51641","51642","51643","51644","51645","51646","51647","51649","5165","51650","51651","51653","51654","51655","51656","51657","51659","51660","51661","51662","51663","51664","51665","51666","51668","51669","51670","51671","51672","51673","51674","51675","51676","51677","51678","51679","51680","51681","51682","51683","51684","51685","51686","51687","51688","51689","51690","51691","51692","51693","51694","51695","51696","51697","51698","51699","51700","51702","51703","51704","51705","51706","51707","51708","51709","51710","51711","51712","51713","51714","51715","51716","51717","51718","51719","51720","51721","51722","51723","51724","51725","51726","51727","51728","51729","51730","51731","51732","51733","51734","51735","51736","51737","51738","51739","51740","51741","51742","51743","51744","51745","51746","51747","51748","51749","51750","51751","51752","51753","51754","51755","51757","51758","51759","51760","51761","51762","51763","51764","51765","51766","51767","51768","51769","51770","51771","51772","51773","51774","51775","51777","51778","51779","51780","51781","51782","51783","51784","51785","51786","51787","51788","51789","51790","51791","51792","51793","51794","51795","51797","51798","51799","51800","51801","51802","51803","51804","51805","51806","51807","51808","51809","51810","51811","51812","51813","51814","51815","51816","51817","51818","51819","51820","51821","51822","51823","51824","51825","51826","51827","51828","51829","51830","51832","51833","51834","51835","51836","51837","51838","51839","51840","51841","51842","51843","51844","51845","51846","51847","51848","51849","51850","51851","51852","51853","51854","51855","51856","51857","51858","51859","51860","51861","51862","51863","51864","51865","51866","51867","51868","51869","51870","51871","51872","51874","51875","51876","51877","51878","51879","51880","51881","51883","51885","51886","51887","51888","51889","51890","51892","51893","51894","51895","51896","51897","51898","51899","51900","51901","51902","51903","51904","51905","51906","51907","51908","51909","51910","51911","51912","51913","51914","51915","51916","51917","51918","51919","51920","51921","51922","51923","51944","51946","5195","5820","5824","585","6125","6412","646","6501","6503","6586","6610","7570","774","7815","7839","8021","8148","8232","83","8584","868","886","924","94"};
        String[] teamids = new String[]{"106","132","2329","4721","17033","868","924","585","2569","10134","6501","137","8584","204","2365","24","124","256","51","1180","25516","502","40","94","307","2866","83","484","356","8021","184","15662","22116","21756","22104","22152","21972","2941","21948","21792","22140","21804","21876", "43941"};
        List<String[]> clubs = new ArrayList<String[]>();
        List<String[]> allclubs = new ArrayList<String[]>();
        List<String> clubnames = new ArrayList<String>();
        for(int j = 0; j < teamids.length; j++) {
            String id = teamids[j];
            System.out.println(j + "/" + teamids.length + ":" + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("TeamID", id));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String ret = IDUtils.execute(IDUtils.GETIDS().get(0), pm);
            String teamname = ret.split("@")[1].split("\\|")[0] + ret.split("@")[1].split("\\|")[6];
            allclubs.add(new String[]{id, teamname, ret.split("@")[1].split("\\|")[11]});
            clubnames.add(ret.split("@")[1].split("\\|")[1]);
            String[] teams = ret.split("@")[2].split("!")[1].split("&");
            for(String team : teams) {
                String[] club = new String[5];
                club[0] = teamname;
                if(team.split("\\|")[3].equals("0")) {
                    club[1] = "会长";
                } else if(team.split("\\|")[3].equals("1")) {
                    club[1] = "副";
                } else {
                    club[1] = "";
                }
                club[2] = team.split("\\|")[1];
                club[3] = id;
                club[4] = ret.split("@")[1].split("\\|")[11];
                clubs.add(club);
            }
        }
        sort(clubs, 0, clubs.size());
        sort2(allclubs, 0, allclubs.size(), clubnames);
    %>
    <c:if test="${!empty msg}">
        <div class="alert">
            ${msg}
        </div>
    </c:if>
    <br/>
    <select id="targetclub">
        <option value="">--选--</option>
        <%
            for(int j = 0; j < allclubs.size(); j++) {
        %>
        <option value="<%=allclubs.get(j)[0]%>"><%=allclubs.get(j)[1]%></option>
        <%
            }
        %>
    </select>

        <table id="info" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th>球会</th>
                <th>职位</th>
                <th>号</th>
                <th>操作</th>
                <th>球会</th>
                <th>职位</th>
                <th>号</th>
                <th>操作</th>
                <th>球会</th>
                <th>职位</th>
                <th>号</th>
                <th>操作</th>
                <th>球会</th>
                <th>职位</th>
                <th>号</th>
                <th>操作</th>
            </tr>
            <tr class="head"><td colspan="16"><%=clubnames.get(0)%></td></tr>
            <%
                int newline = 0;
                int clubidx = 0;
                for(int i = 0; i < clubs.size(); i++) {
            %>
            <%
                if(newline % 4 == 0) {
            %>
            <tr class="row1">
            <%
                }
            %>
                <td>
                    <%=clubs.get(i)[0]%>
                </td>
                <td>
                    <%=clubs.get(i)[1]%>
                </td>
                <td><%=clubs.get(i)[2]%></td>
                <td>
                    <%
                        if(clubs.get(i)[1].equals("")) {
                    %>
                    <input type="button" value="调整" onclick="dochange('<%=clubs.get(i)[2]%>');"/>
                    <input type="button" value="转让" onclick="dogiveother('<%=clubs.get(i)[2]%>');"/>
                    <%
                        }
                    %>
                </td>
                <%
                    if(newline % 4 == 3) {
                %>
            </tr>
                <%
                    }
                    newline++;
                %>
            <%
                if(i < clubs.size() - 1 && !clubs.get(i)[0].equals(clubs.get(i + 1)[0])) {
                    if(newline % 4 != 0) {
                        for(int j = 0; j < 4 - (newline % 4); j++) {
                            out.print("<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>");
                        }
                        out.print("</tr>");
                    }
                    newline = 0;
                    clubidx++;
                    if(clubidx < clubnames.size()) {
                        out.println("<tr class=\"head\"><td colspan=\"16\">" + clubnames.get(clubidx) + "</td></tr>");
                    } else {
                        out.println("<tr class=\"head\"><td colspan=\"16\">&nbsp;</td></tr>");
                    }
                }
                }
            %>
            <tr class="head"><td colspan="16">&nbsp;</td></tr>
        </table>
    <br/>
    <form action="" method="post">
        <input type="hidden" name="action" value="change"/>
        <input type="hidden" name="target" value=""/>
        <input type="hidden" name="teamname" value=""/>
    </form>
    </body>
<script type="text/javascript">
    function dochange(nick)
    {
        var obj = document.getElementById('targetclub');
        var item = obj.options[obj.selectedIndex].value;
        document.getElementsByName('target')[0].value = item;
        document.getElementsByName('teamname')[0].value = nick;
        document.forms[0].submit();
    }

    function dogiveother(nick)
    {
        document.getElementsByName('action')[0].value = 'toother';
        document.getElementsByName('target')[0].value = nick;
        document.forms[0].submit();
    }
</script>
</html>