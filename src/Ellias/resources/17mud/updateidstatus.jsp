<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.saille.mud17.IDUtils" %>
<%!
    void update(HttpServletRequest request, JspWriter out) throws Exception{
        String id = request.getParameter("id");
        if(StringUtils.isBlank(id)) {
            return;
        }
        String exp = request.getParameter("exp");
        if(StringUtils.isBlank(exp)) {
            return;
        }
        String qn = request.getParameter("qn");
        if(StringUtils.isBlank(qn)) {
            return;
        }
        String tihui = request.getParameter("tihui");
        if(StringUtils.isBlank(tihui)) {
            return;
        }
        String martial = request.getParameter("martial");
        if(StringUtils.isBlank(martial)) {
            return;
        }
        String yueli = request.getParameter("yueli");
        if(StringUtils.isBlank(yueli)) {
            return;
        }
        String weiwang = request.getParameter("weiwang");
        if(StringUtils.isBlank(weiwang)) {
            return;
        }
        String zhengqi = request.getParameter("zhengqi");
        if(StringUtils.isBlank(zhengqi)) {
            return;
        }
        String gongji = request.getParameter("gongji");
        if(StringUtils.isBlank(gongji)) {
            return;
        }
        String jungong = request.getParameter("jungong");
        if(StringUtils.isBlank(jungong)) {
            return;
        }
        String linghui = request.getParameter("linghui");
        if(StringUtils.isBlank(linghui)) {
            return;
        }
        String meili = request.getParameter("meili");
        if(StringUtils.isBlank(meili)) {
            return;
        }
        String shaqi = request.getParameter("shaqi");
        if(StringUtils.isBlank(shaqi)) {
            return;
        }
        String liqi = request.getParameter("liqi");
        if(StringUtils.isBlank(liqi)) {
            return;
        }
        String lv = request.getParameter("lv");
        if(StringUtils.isBlank(lv)) {
            return;
        }
        String money = request.getParameter("money");
        if(StringUtils.isBlank(money)) {
            return;
        }
        String updatetime = request.getParameter("updatetime");
        if(StringUtils.isBlank(updatetime)) {
            return;
        }
        if(!IDUtils.ids.containsKey(id)) {
            Map<String, String> values = new HashMap<String, String>();
            values.put("updatetime", "0000-00-00 00:00:00");
            IDUtils.ids.put(id, values);
        }
        if(updatetime.compareTo(IDUtils.ids.get(id).get("updatetime")) > 0) {
            IDUtils.ids.get(id).put("exp", exp);
            IDUtils.ids.get(id).put("exp", exp);
            IDUtils.ids.get(id).put("qn", qn);
            IDUtils.ids.get(id).put("tihui", tihui);
            IDUtils.ids.get(id).put("martial", martial);
            IDUtils.ids.get(id).put("yueli", yueli);
            IDUtils.ids.get(id).put("weiwang", weiwang);
            IDUtils.ids.get(id).put("zhengqi", zhengqi);
            IDUtils.ids.get(id).put("gongji", gongji);
            IDUtils.ids.get(id).put("jungong", jungong);
            IDUtils.ids.get(id).put("linghui", linghui);
            IDUtils.ids.get(id).put("meili", meili);
            IDUtils.ids.get(id).put("shaqi", shaqi);
            IDUtils.ids.get(id).put("liqi", liqi);
            IDUtils.ids.get(id).put("lv", lv);
            IDUtils.ids.get(id).put("money", money);
            IDUtils.ids.get(id).put("updatetime", updatetime);

            Map map = request.getParameterMap();
            Map<String, Integer> skills = new HashMap<String, Integer>();
            for(Object k : map.keySet()) {
                if(k.toString().startsWith("skill_")) {
                    skills.put(k.toString().substring(6), Integer.parseInt(((String[])map.get(k))[0].toString()));
                }
            }
            IDUtils.skills.put(id, skills);
            out.print("done");
            return;
        }
    }

    void query(HttpServletRequest request, JspWriter out) throws Exception{
        String id = request.getParameter("id");
        if(StringUtils.isBlank(id)) {
            return;
        }
        System.out.println("²éÑ¯17mud×´Ì¬£¬id=" + id);
        if(!IDUtils.ids.containsKey(id)) {
            return;
        }
        String content = "";
        content = content + IDUtils.ids.get(id).get("exp");
        content = content + "," + IDUtils.ids.get(id).get("qn");
        content = content + "," + IDUtils.ids.get(id).get("tihui");
        content = content + "," + IDUtils.ids.get(id).get("martial");
        content = content + "," + IDUtils.ids.get(id).get("yueli");
        content = content + "," + IDUtils.ids.get(id).get("weiwang");
        content = content + "," + IDUtils.ids.get(id).get("zhengqi");
        content = content + "," + IDUtils.ids.get(id).get("gongji");
        content = content + "," + IDUtils.ids.get(id).get("jungong");
        content = content + "," + IDUtils.ids.get(id).get("linghui");
        content = content + "," + IDUtils.ids.get(id).get("meili");
        content = content + "," + IDUtils.ids.get(id).get("shaqi");
        content = content + "," + IDUtils.ids.get(id).get("liqi");
        content = content + "," + IDUtils.ids.get(id).get("lv");
        content = content + "," + IDUtils.ids.get(id).get("money");
        content = content + "," + IDUtils.ids.get(id).get("updatetime");
        content += ",";
        Map<String, Integer> skills = IDUtils.skills.get(id);
        StringBuilder sb = new StringBuilder();
        for(String k : skills.keySet()) {
            Integer v = skills.get(k);
            if(sb.length() > 0) {
                sb.append("&");
            }
            sb.append(k).append("=").append(v);
        }
        content += sb.toString();
        out.print(content);
        return;
    }

    void queryid(HttpServletRequest request, JspWriter out) throws Exception {
        String ret = "";
        for(String s : IDUtils.ids.keySet()) {
            ret += "," + s;
        }
        out.print(ret.substring(1));
        return;
    }
%>
<%
    String type = request.getParameter("type");
    if("update".equals(type)) {
        update(request, out);
    } else if("query".equals(type)) {
        query(request, out);
    } else if("queryid".equals(type)) {
        queryid(request, out);
    }
%>
