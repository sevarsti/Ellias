package com.saille.pampers;

import servlet.GlobalContext;
import org.apache.commons.configuration.PropertiesConfiguration;
import com.saille.ogzq.ConfigUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-2-10
 * Time: 2:07:27
 * To change this template use File | Settings | File Templates.
 */
public class PampersDwr {
    public String addId(String user, String pwd, String name, String email, String mobile, String province,
                      String city, String address, String zipcode, String itemid) {
        PampersService service = (PampersService) GlobalContext.getSpringContext().getBean("pampersService", PampersService.class);
        PropertiesConfiguration conf = service.getConf();
        String curId = conf.getString("id");
        if(curId.length() > 0) {
            curId += ",";
        }
        conf.setProperty("id", curId + user );
        conf.setProperty(user + ".pwd", pwd);
        conf.setProperty(user + ".name", name);
        conf.setProperty(user + ".email", email);
        conf.setProperty(user + ".mobile", mobile);
        conf.setProperty(user + ".province", province);
        conf.setProperty(user + ".city", city);
        conf.setProperty(user + ".address", address);
        conf.setProperty(user + ".zipcode", zipcode);
        conf.setProperty(user + ".zipcode", zipcode);
        conf.setProperty(user + ".itemid", itemid);
        return service.addId(user, pwd, name, email, mobile, province, city, address, zipcode, itemid);
    }

    public String startRun(String id) {
        PampersService service = (PampersService) GlobalContext.getSpringContext().getBean("pampersService", PampersService.class);
        service.startThread(id);
        return "done";
    }

    public String stopRun(String id) {
        PampersService service = (PampersService) GlobalContext.getSpringContext().getBean("pampersService", PampersService.class);
        service.stopThread(id);
        return "done";
    }

    public String deleteId(String id) {
        PampersService service = (PampersService) GlobalContext.getSpringContext().getBean("pampersService", PampersService.class);
        service.deleteId(id);
        return "done";
    }

    public String init() {
        PampersService service = (PampersService) GlobalContext.getSpringContext().getBean("pampersService", PampersService.class);
        try {
//            PropertiesConfiguration conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../pampers/id.ini"));
            PropertiesConfiguration conf = service.getConf();
            String[] ids = conf.getStringArray("id");
            String[] params = new String[]{"pwd", "name", "email", "mobile", "province", "city", "address", "zipcode", "itemid"};
            String ret = "";
            for(String id : ids) {
                String[] pp = new String[params.length];
                for(int i = 0; i < params.length; i++) {
                    pp[i] = conf.getString(id + "." + params[i]);
                }
                service.addId(id, pp[0], pp[1], pp[2], pp[3], pp[4], pp[5], pp[6], pp[7], pp[8]);
                ret += "," + id;
            }
            return ret.substring(1) + " done";
        } catch(Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    public List<String[]> getProvince() {
        return Constant.getProvinces();
    }

    public List<String[]> getCities(int provinceId) {
        return Constant.getCities(provinceId);
    }
}
