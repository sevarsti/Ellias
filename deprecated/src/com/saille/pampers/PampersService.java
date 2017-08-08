package com.saille.pampers;

import org.springframework.beans.factory.InitializingBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.*;

import com.saille.ogzq.ConfigUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-2-9
 * Time: 21:34:07
 * To change this template use File | Settings | File Templates.
 */
public class PampersService implements InitializingBean {
    private PropertiesConfiguration conf;
    Map<String, RobPampers[]> ids = new HashMap<String, RobPampers[]>();
    public void afterPropertiesSet() throws Exception {
        conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../pampers/id.ini"));
        conf.setAutoSave(true);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void main(String[] args) {
//        RobPampers p = new RobPampers("arabel@126.com", "Woshimaizi1", "何沁云", "arabel@126.com", "13818207760",
//                                      "26", "396", "上海市徐汇区乐山路33号 1号楼401", "200030");
//        RobPampers p = new RobPampers("arabel@126.com", "Woshimaizi1", "何沁云", "arabel@126.com", "13818207760",
//                                      "26", "396", "上海市徐汇区乐山路33号 1号楼401", "200030");
//        p.run();
    }

    public List<RobPampers[]> getIds() {
        Set<String> keys = this.ids.keySet();
        List<String> k = new ArrayList<String>();
        for(String s : keys) {
            k.add(s);
        }
        Collections.sort(k);
        List<RobPampers[]> ret = new ArrayList<RobPampers[]>();
        for(String s : k) {
            ret.add(this.ids.get(s));
        }
        return ret;
    }

    public String addId(String user, String pwd, String name, String email, String mobile, String province,
                      String city, String address, String zipcode, String itemid) {
        int count = 1;
        List<RobPampers> list = new ArrayList<RobPampers>();
        for(int i = 0; i < count; i++) {
            RobPampers p = new RobPampers(user, pwd, name, email, mobile, province, city, address, zipcode, itemid);
            p.setIsRunning(false);
            p.start();
            list.add(p);
        }
        RobPampers[] array = new RobPampers[count];
        list.toArray(array);
//        RobPampers p2 = new RobPampers(user, pwd, name, email, mobile, province, city, address, zipcode, itemid);
//        p2.setIsRunning(false);
//        p2.start();
//        RobPampers p3 = new RobPampers(user, pwd, name, email, mobile, province, city, address, zipcode, itemid);
//        p3.setIsRunning(false);
//        p3.start();
        this.ids.put(user, array);
        return "done";
    }

    public String startThread(String id) {
        RobPampers[] rps = this.ids.get(id);
        if(rps != null) {
            for(RobPampers rp : rps) {
                if(rp.getIsRunning()) {
//                    return "该帐号已经运行中";
                } else {
                    rp.setIsRunning(true);
//                    return "done";
                }
            }
            return "done";
        } else{
            return "没有该帐号";
        }
    }

    public String stopThread(String id) {
        RobPampers[] rps = this.ids.get(id);
        if(rps != null) {
            for(RobPampers rp : rps) {
                if(rp.getIsRunning()) {
                    rp.setIsRunning(false);
//                    return "done";
                } else {
//                    return "该帐号已经停止";
                }
            }
            return "done";
        } else{
            return "没有该帐号";
        }
    }

    public String deleteId(String id) {
        RobPampers[] rps = this.ids.get(id);
        if(rps != null) {
            for(RobPampers rp : rps) {
                rp.setIsRunning(false);
            }
            this.ids.remove(id);
            return "done";
        } else {
            return "没有该帐号";
        }
    }

    public PropertiesConfiguration getConf() {
        return conf;
    }

    public void setConf(PropertiesConfiguration conf) {
        this.conf = conf;
    }
}
