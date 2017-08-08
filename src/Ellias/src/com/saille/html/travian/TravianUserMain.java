package com.saille.html.travian;

import com.saille.html.HTMLUtil;
import com.saille.html.TravianForm;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TravianUserMain {
    private String userid;
    private String pwd;
    private String cookie;
    private boolean loginok;
    public List<TravianTown> towns;

    public TravianUserMain(String id, String pwd) {
        this.userid = id;
        this.pwd = pwd;
        this.loginok = false;
    }

    public String login() {
        TravianUtil travianUtil = TravianUtil.getInstance();
        HTMLUtil htmlUtil = HTMLUtil.getInstance();
        TravianForm form = travianUtil.getForm("http://s3.travian.cn");

        form.getParamValue()[0] = this.userid;

        form.getParamValue()[1] = this.pwd;

        List p = new ArrayList();
        for(int i = 0; i < form.getParam().length; i++) {
            String[] s = new String[2];
            s[0] = form.getParam()[i];
            s[1] = form.getParamValue()[i];
            p.add(s);
        }

        this.loginok = true;
        return null;
    }

    public void refreshTowns() {
        if(!this.loginok) {
            login();
        }
        HTMLUtil htmlUtil = HTMLUtil.getInstance();
        TravianUtil travianUtil = TravianUtil.getInstance();
        String ss = htmlUtil.getWeb("http://s3.travian.cn/dorf3.php", null, this.cookie);
        this.towns = travianUtil.generateTowns(ss);
        for(TravianTown town : this.towns) {
            town.setOwner(this.userid);
            getTown(town);
        }
        System.out.println("end");
    }

    private TravianTown getTown(TravianTown town) {
        String url = "http://s3.travian.cn/dorf1.php?newdid=" + town.getId();
        HTMLUtil htmlUtil = HTMLUtil.getInstance();
        String s = htmlUtil.getWeb(url, null, this.cookie);
        String res = new String(s);

        for(int i = 0; i < 18; i++) {
            s = s.substring(s.indexOf("build.php?id=" + (i + 1)));
            s = s.substring(s.indexOf("title=\"") + 7);
            town.builds[i] = s.substring(0, s.indexOf(" "));
            s = s.substring(s.indexOf("等级 ") + "等级 ".length());
            town.levels[i] = Integer.parseInt(s.substring(0, s.indexOf("\"")));
        }

        res = res.substring(res.indexOf("<div id=\"lres0\">"));
        res = res.substring(res.indexOf("木材"));
        res = res.substring(res.indexOf("title=") + 6);
        town.setWoodProduct(Integer.parseInt(res.substring(0, res.indexOf(">"))));
        res = res.substring(res.indexOf(">") + 1);
        town.setWood(Integer.parseInt(res.substring(0, res.indexOf("/"))));
        res = res.substring(res.indexOf("/") + 1);
        town.setWoodSize(Integer.parseInt(res.substring(0, res.indexOf("<"))));

        res = res.substring(res.indexOf("泥土"));
        res = res.substring(res.indexOf("title=") + 6);
        town.setClayProduct(Integer.parseInt(res.substring(0, res.indexOf(">"))));
        res = res.substring(res.indexOf(">") + 1);
        town.setClay(Integer.parseInt(res.substring(0, res.indexOf("/"))));
        res = res.substring(res.indexOf("/") + 1);
        town.setClaySize(Integer.parseInt(res.substring(0, res.indexOf("<"))));

        res = res.substring(res.indexOf("铁块"));
        res = res.substring(res.indexOf("title=") + 6);
        town.setIronProduct(Integer.parseInt(res.substring(0, res.indexOf(">"))));
        res = res.substring(res.indexOf(">") + 1);
        town.setIron(Integer.parseInt(res.substring(0, res.indexOf("/"))));
        res = res.substring(res.indexOf("/") + 1);
        town.setIronSize(Integer.parseInt(res.substring(0, res.indexOf("<"))));

        res = res.substring(res.indexOf("粮食"));
        res = res.substring(res.indexOf("title=") + 6);
        town.setFoodProduct(Integer.parseInt(res.substring(0, res.indexOf(">"))));
        res = res.substring(res.indexOf(">") + 1);
        town.setFood(Integer.parseInt(res.substring(0, res.indexOf("/"))));
        res = res.substring(res.indexOf("/") + 1);
        town.setFoodSize(Integer.parseInt(res.substring(0, res.indexOf("<"))));

        if(s.indexOf("建造中") != -1) {
            String constructing = s.substring(s.indexOf("建造中"));
            constructing = constructing.substring(constructing.indexOf("取消"));
            constructing = constructing.substring(constructing.indexOf("<td>") + 4);
            town.setConstructing(constructing.substring(0, constructing.indexOf("</td>")));
            constructing = constructing.substring(constructing.indexOf("timer1>") + "timer1>".length());
            String restTime = constructing.substring(0, constructing.indexOf("<"));
            String[] times = restTime.split(":");
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(10, Integer.parseInt(times[0]));
            c.add(12, Integer.parseInt(times[1]));
            c.add(13, Integer.parseInt(times[2]));
            town.setEndTime(c.getTime());
        }

        url = "http://s3.travian.cn/dorf2.php?newdid=" + town.getId();
        s = htmlUtil.getWeb(url, null, this.cookie);
        String jjd = s.substring(s.indexOf("<area href=\"build.php?id=39\" title=\"") + "<area href=\"build.php?id=39\" title=\"".length());
        jjd = jjd.substring(0, jjd.indexOf(">"));
        if(jjd.indexOf("等级") != -1) {
            jjd = jjd.substring(jjd.indexOf("等级 ") + "等级 ".length());
            jjd = jjd.substring(0, jjd.indexOf("\""));
            town.levels[38] = Integer.parseInt(jjd);
        } else {
            town.levels[38] = 0;
        }
        town.builds[38] = "集结点";
        for(int i = 0; i < 22; i++) {
            if(i + 19 == 39) {
                continue;
            }
            s = s.substring(s.indexOf("build.php?id=" + (i + 19)));
            s = s.substring(s.indexOf("title=\"") + 7);
            if(s.indexOf("工地") == 0) {
                town.builds[(i + 18)] = "工地";
                town.levels[(i + 18)] = 0;
            } else {
                town.builds[(i + 18)] = s.substring(0, s.indexOf(" "));
                if(s.indexOf("外部工地") != 0) {
                    s = s.substring(s.indexOf("等级 ") + "等级 ".length());
                    town.levels[(i + 18)] = Integer.parseInt(s.substring(0, s.indexOf("\"")));
                } else {
                    town.builds[(i + 18)] = "外部工地";
                    town.levels[(i + 18)] = 0;
                }
            }
        }
        return null;
    }

    public void viewBuild(int townId, int buildId) {
        TravianTown town = null;
        for(TravianTown t : this.towns) {
            if(t.getId() == townId) {
                town = t;
                break;
            }
        }
        if(town == null) {
            return;
        }
        String url = "http://s3.travian.cn/build.php?newdid=" + townId + "&id=" + buildId;
        String stream = HTMLUtil.getInstance().getWeb(url, null, this.cookie);
        String value = TravianUtil.getInstance().parseBuild(stream, null);
        if(value.charAt(0) != '1') {
            int food;
            if(value.charAt(0) == '2') {
                String[] resources = value.split(",");
                int wood = Integer.parseInt(resources[1]);
                int clay = Integer.parseInt(resources[2]);
                int iron = Integer.parseInt(resources[3]);
                food = Integer.parseInt(resources[4]);
            } else if(value.charAt(0) != '3') {
                ;
            }
        }
    }

    public String viewMerchant(int townId) {
        TravianTown town = null;
        for(TravianTown t : this.towns) {
            if(t.getId() == townId) {
                town = t;
                break;
            }
        }
        if(town == null) {
            return null;
        }
        int buildId = -1;
        for(int i = 18; i < 38; i++) {
            if(town.getBuilds()[i].equals("市场")) {
                buildId = i;
                break;
            }
        }
        if(buildId == -1) {
            return null;
        }
        String stream = HTMLUtil.getInstance().getWeb("http://s3.travian.cn/build.php?newdid=" + townId + "&id=" + (buildId + 1), null, this.cookie);
        return TravianUtil.getInstance().parseMerchant(stream);
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCookie() {
        return this.cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public boolean isLoginok() {
        return this.loginok;
    }

    public void setLoginok(boolean loginok) {
        this.loginok = loginok;
    }

    public List<TravianTown> getTowns() {
        return this.towns;
    }

    public void setTowns(List<TravianTown> towns) {
        this.towns = towns;
    }
}