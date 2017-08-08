package com.saille.html.travian;

import com.saille.html.HTMLUtil;
import com.saille.html.TravianForm;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TravianUtil {
    private static TravianUtil instance;

    public static TravianUtil getInstance() {
        if(instance == null) {
            instance = new TravianUtil();
        }
        return instance;
    }

    public TravianForm getForm(String url) {
        try {
            HTMLUtil htmlUtil = HTMLUtil.getInstance();
            return parse(htmlUtil.getWeb(url, null, null), url);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public TravianForm parse(String content, String url) throws Exception {
        TravianForm form = new TravianForm();
        form.setUrl(new URL(url).getPath());
        List param = new ArrayList();
        List paramValue = new ArrayList();
        List links = new ArrayList();
        int start = content.indexOf("<body");
        if(start == -1) {
            return null;
        }
        int end = content.indexOf("</body>");
        if(end == -1) {
            return null;
        }

        int linkPos = content.indexOf("<a ");
        if(linkPos == -1) {
            form.setLinks(new ArrayList());
        } else {
            String linkContent = content.substring(linkPos);
            while(linkContent.indexOf("<a ") != -1) {
                linkContent = linkContent.substring(linkContent.indexOf("<a "));
                String s = linkContent.substring(linkContent.indexOf("href=") + 5);
                if(s.charAt(0) == '"') {
                    s = s.substring(1);
                    s = s.substring(0, s.indexOf("\""));
                    links.add(s);
                } else {
                    s = s.substring(0, s.indexOf(" "));
                    links.add(s);
                }
                linkContent = linkContent.substring(linkContent.indexOf(">"));
            }
        }
        form.setLinks(links);

        String cont = content.substring(start, end + 7);
        String f = cont.substring(cont.indexOf("<form"));
        String formContent = f.substring(0, f.indexOf("</form>"));

        String usr = formContent.substring(formContent.indexOf("用户名"));
        usr = usr.substring(usr.indexOf("<input"));
        usr = usr.substring(usr.indexOf("name=\"") + 6);
        usr = usr.substring(0, usr.indexOf("\""));
        param.add(usr);
        paramValue.add("");
        String pwd = formContent.substring(formContent.indexOf("密码"));
        pwd = pwd.substring(pwd.indexOf("<input"));
        pwd = pwd.substring(pwd.indexOf("name=\"") + 6);
        pwd = pwd.substring(0, pwd.indexOf("\""));
        param.add(pwd);
        paramValue.add("");

        f = f.substring(0, f.indexOf(">") + 1);
        f = f.substring(f.indexOf("action=\"") + 7);
        if(f.charAt(0) == '"') {
            f = f.substring(1);
            String s = f.substring(0, f.indexOf("\""));
            form.setFormName(s);
        } else {
            String s = f.substring(0, f.indexOf(" "));
            form.setFormName(s);
        }
        f = f.substring(0, f.indexOf("\""));
        form.setFormName(f);
        while(formContent.indexOf("<input") != -1) {
            formContent = formContent.substring(formContent.indexOf("<input"));
            int valuePos = formContent.indexOf("value=");
            if(valuePos == -1) {
                paramValue.add(null);
            } else {
                String value = formContent.substring(valuePos + 6);
                value = value.substring(0, value.indexOf(">"));
                if(value.charAt(0) == '"') {
                    value = value.substring(1);
                    String s = value.substring(0, value.indexOf("\""));
                    paramValue.add(s);
                } else {
                    String s = value.substring(0, value.indexOf(" "));
                    paramValue.add(s);
                }
            }

            formContent = formContent.substring(formContent.indexOf("name=") + 5);
            if(formContent.charAt(0) == '"') {
                formContent = formContent.substring(1);
                String s = formContent.substring(0, formContent.indexOf("\""));
                param.add(s);
            } else {
                String s = formContent.substring(0, formContent.indexOf(" "));
                param.add(s);
            }
        }
        for(int i = param.size() - 1; i >= 2; i--) {
            if((((String) param.get(i)).equals(param.get(0))) || (((String) param.get(i)).equals(param.get(1)))) {
                param.remove(i);
                paramValue.remove(i);
            }
        }
        form.setParam(param);
        form.setParamValue(paramValue);
        return form;
    }

    public List<TravianTown> generateTowns(String stream) {
        List ret = new ArrayList();
        String s = stream.substring(stream.indexOf("商人"));
        s = s.substring(s.indexOf("村庄"));
        s = s.substring(0, s.indexOf("粮食消耗"));
        int pos = -1;
        while((pos = s.indexOf("?newdid=")) != -1) {
            TravianTown town = new TravianTown();
            s = s.substring(pos + 8);
            String id = s.substring(0, s.indexOf("\""));
            town.setId(Integer.parseInt(id));
            s = s.substring(s.indexOf(">") + 1);
            String name = s.substring(0, s.indexOf("<"));
            town.setName(name);
            s = s.substring(s.indexOf("<"));
            s = s.substring(s.indexOf("<td class=\"right dlist1\">(") + 26);
            String x = s.substring(0, s.indexOf("<"));
            town.setX(Integer.parseInt(x));
            s = s.substring(s.indexOf("<td class=\"left dlist3\">") + 24);
            String y = s.substring(0, s.indexOf("<") - 1);
            town.setY(Integer.parseInt(y));
            ret.add(town);
        }

        s = stream.substring(stream.indexOf("商人"));
        s = s.substring(0, s.indexOf("村庄:"));
        pos = -1;
        int index = 0;
        while((pos = s.indexOf("<span class=\"c0 t\">")) != -1) {
            s = s.substring(pos + "<span class=\"c0 t\">".length());
            String ss = s.substring(0, s.indexOf("<"));
            String[] s2 = ss.split("/");
            ((TravianTown) ret.get(index)).setMerchants(Integer.parseInt(s2[0]));
            ((TravianTown) ret.get(index)).setMaxMerchants(Integer.parseInt(s2[1]));
            index++;
        }
        return ret;
    }

    public String parseBuild(String stream, String target) {
        if(stream.indexOf("升级到等级") != -1) {
            return "1";
        }
        if(stream.indexOf("资源不足") != -1) {
            String s = stream.substring(stream.indexOf("成本"));
            s = s.substring(s.indexOf("img"));
            s = s.substring(s.indexOf(">") + 1);
            String wood = s.substring(0, s.indexOf(" "));
            s = s.substring(s.indexOf("img"));
            s = s.substring(s.indexOf(">") + 1);
            String clay = s.substring(0, s.indexOf(" "));
            s = s.substring(s.indexOf("img"));
            s = s.substring(s.indexOf(">") + 1);
            String iron = s.substring(0, s.indexOf(" "));
            s = s.substring(s.indexOf("img"));
            s = s.substring(s.indexOf(">") + 1);
            String food = s.substring(0, s.indexOf(" "));
            return "2," + wood + "," + clay + "," + iron + "," + food;
        }
        if(stream.indexOf("已经有建筑在建造中") != -1) {
            return "3";
        }
        return null;
    }

    public String parseMerchant(String stream) {
        String s = stream.substring(stream.indexOf("和电脑商人交易") + "和电脑商人交易".length());
        s = s.substring(s.indexOf("商人") + "商人".length() + 1);
        String number = s.substring(0, s.indexOf("<"));
        String ssize = s.substring(s.indexOf("每个商人可以运载<b>") + "每个商人可以运载<b>".length());

        ssize = ssize.substring(0, ssize.indexOf("</b>资源"));
        int size = Integer.parseInt(ssize);
        int myMerchant = s.indexOf("自己的商人在途中");
        List<String> mers = new ArrayList<String>();
        if(myMerchant != -1) {
            s = s.substring(myMerchant);
            while((s.indexOf("从村庄") != -1) || (s.indexOf("向村庄") != -1)) {
                s = s.substring(s.indexOf("村庄") - 1);
                if(s.indexOf("向") == 0) {
                    String m = "1-" + s.substring(0, s.indexOf("运送") + 2);
                    s = s.substring(s.indexOf("目的地"));
                    s = s.substring(s.indexOf("timer"));
                    s = s.substring(s.indexOf(">") + 1);

                    m = m + "-" + s.substring(0, s.indexOf("<"));
                    s = s.substring(s.indexOf("在") + 2);
                    m = m + "-" + s.substring(0, s.indexOf("<"));
                    s = s.substring(s.indexOf("img"));
                    s = s.substring(s.indexOf(">") + 1);
                    String wood = s.substring(0, s.indexOf(" "));
                    m = m + "-" + wood;
                    s = s.substring(s.indexOf("img"));
                    s = s.substring(s.indexOf(">") + 1);
                    String clay = s.substring(0, s.indexOf(" "));
                    m = m + "-" + clay;
                    s = s.substring(s.indexOf("img"));
                    s = s.substring(s.indexOf(">") + 1);
                    String iron = s.substring(0, s.indexOf(" "));
                    m = m + "-" + iron;
                    s = s.substring(s.indexOf("img"));
                    s = s.substring(s.indexOf(">") + 1);
                    String food = s.substring(0, s.indexOf("<"));
                    m = m + "-" + food;
                    int totalRes = Integer.parseInt(wood) + Integer.parseInt(clay) + Integer.parseInt(iron) + Integer.parseInt(food);
                    int merchants = totalRes / size;
                    if(totalRes % size != 0) {
                        merchants++;
                    }
                    m = m + "-" + merchants;
                    mers.add(m);
                } else if(s.indexOf("从") == 0) {
                    String m = "2-" + s.substring(0, s.indexOf("回来") + 2);
                    s = s.substring(s.indexOf("目的地"));
                    s = s.substring(s.indexOf("timer"));
                    s = s.substring(s.indexOf(">") + 1);

                    m = m + "-" + s.substring(0, s.indexOf("<"));
                    s = s.substring(s.indexOf("在") + 2);
                    m = m + "-" + s.substring(0, s.indexOf("<"));
                    s = s.substring(s.indexOf("img"));
                    s = s.substring(s.indexOf(">") + 1);
                    String wood = s.substring(0, s.indexOf(" "));
                    m = m + "-" + wood;
                    s = s.substring(s.indexOf("img"));
                    s = s.substring(s.indexOf(">") + 1);
                    String clay = s.substring(0, s.indexOf(" "));
                    m = m + "-" + clay;
                    s = s.substring(s.indexOf("img"));
                    s = s.substring(s.indexOf(">") + 1);
                    String iron = s.substring(0, s.indexOf(" "));
                    m = m + "-" + iron;
                    s = s.substring(s.indexOf("img"));
                    s = s.substring(s.indexOf(">") + 1);
                    String food = s.substring(0, s.indexOf("<"));
                    m = m + "-" + food;
                    int totalRes = Integer.parseInt(wood) + Integer.parseInt(clay) + Integer.parseInt(iron) + Integer.parseInt(food);
                    int merchants = totalRes / size;
                    if(totalRes % size != 0) {
                        merchants++;
                    }
                    m = m + "-" + merchants;
                    mers.add(m);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for(String st : mers) {
            if(mers.indexOf(st) != 0) {
                sb.append(",");
            }
            sb.append(st);
        }
        return sb.toString();
    }
}