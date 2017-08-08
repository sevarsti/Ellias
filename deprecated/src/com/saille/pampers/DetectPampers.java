package com.saille.pampers;

import org.apache.log4j.Logger;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;
import org.htmlparser.Parser;
import org.htmlparser.tags.FormTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.filters.LinkStringFilter;
import org.htmlparser.util.NodeList;
import org.springframework.beans.factory.InitializingBean;

import java.util.*;

import servlet.GlobalContext;
import com.sinitek.busin.core.IInitializingBean;
import com.saille.util.CommonUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-2-16
 * Time: 23:41:46
 * To change this template use File | Settings | File Templates.
 */
public class DetectPampers extends Thread implements InitializingBean {
    private final static Logger LOGGER = Logger.getLogger(DetectPampers.class);
    public static boolean loop = true;
    private PampersDao dao;
    String username = "sucan611@gmail.com";
    String pwd = "woshimaizi";
    HttpClient client;
    boolean login = false;
    public List<Map<String, Object>> currentItems = null;

    public DetectPampers() {
        try {
            this.dao = (PampersDao) GlobalContext.getSpringContext().getBean("pampersDao");
        } catch(NullPointerException ex) {
            LOGGER.warn("dao not found!");
        }
    }

    public static void main(String[] args) {
        DetectPampers d = new DetectPampers();
        d.run();
    }

    private void initItems() {
        if(this.dao != null) {
            this.currentItems = this.dao.queryAllItems();
        }
    }

    public void run() {
        this.initItems();
        
        HttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "user_agent");
        client.getParams().setParameter("http.protocol.single-cookie-header", true);
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000l);
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000l);
        while(loop) {
            try {
                if(!login) {
                    login();
                }
                String url = "https://epoint.pampers.com.cn/pages/rewards.aspx?r=" + Math.random();
                HttpGet gm = new HttpGet(url);
                HttpResponse response = client.execute(gm);
                String contentType = response.getEntity().getContentType().getValue();
                contentType = contentType.substring(contentType.indexOf("charset=") + "charset=".length());
                String str = CommonUtils.getString(response.getEntity().getContent(), contentType);
                List<String[]> objs = this.generateObjs(str, contentType);
                this.updateItems(objs);
                Thread.sleep(5000);
//                System.out.println(objs);
            } catch(Exception ex) {}
        }
    }

    private void updateItems(List<String[]> objs) {
        if(this.currentItems == null) {
            this.currentItems = new ArrayList<Map<String, Object>>();
        }
        for(String[] obj : objs) {//0: id, 1: name, 2: count
            boolean found = false;
            for(Map<String, Object> current : this.currentItems) {
                if(String.valueOf(current.get("ITEMID")).equalsIgnoreCase(obj[0])) {
                    found = true;
                    int old = Integer.parseInt(String.valueOf(current.get("COUNT")));
                    int now = Integer.parseInt(obj[2]);
                    current.put("COUNT", obj[2]);
                    if(now != old) {
                        RobPampers.LOG(obj[1] + "数量有变化：" + old + "->" + now);
                        current.put("UPDATETIME", new Date());
                        if(now > old) {
                            RobPampers.LOG(obj[1] + "上新了");
                            RobPampers.newItemTime = new Date();
                            current.put("ADDTIME", new Date());
                        }
                    }
//                    if(now )
                    break;
                }
            }
            if(!found) {
                Map<String, Object> addItem = new HashMap<String, Object>();
                addItem.put("ITEMID", obj[0]);
                addItem.put("COUNT", obj[2]);
                addItem.put("NAME", obj[1]);
                addItem.put("UPDATETIME", new Date());
                addItem.put("ADDTIME", new Date());
                this.currentItems.add(addItem);
            }
        }
    }

    private void login() throws Exception{
        String url = "http://epoint.pampers.com.cn/system/Login2.aspx";
        boolean login = false;
        HttpGet gm = new HttpGet("https://epoint.pampers.com.cn/");
        HttpResponse response = client.execute(gm);
        int status = response.getStatusLine().getStatusCode();
        String contentType = response.getEntity().getContentType().getValue();
        contentType = contentType.substring(contentType.indexOf("charset=") + "charset=".length());
        List<String[]> params = getFormParams(CommonUtils.getString(response.getEntity().getContent(), contentType), contentType, url);
        while(!login) {
            url = "http://epoint.pampers.com.cn/system/Login2.aspx";
            HttpPost pm = new HttpPost(url);
            for(String[] p : params) {
                if(p[0].equals("email")) {
                    p[1] = this.username;
                } else if(p[0].equals("password")) {
                    p[1] = this.pwd;
                }
                if(p != null && p[0] != null && p[1] != null) {
                    List<NameValuePair> pp = new ArrayList<NameValuePair>();
                    pp.add(new BasicNameValuePair(p[0], p[1]));
                    pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
                }
            }
            response = client.execute(pm);
            status = response.getStatusLine().getStatusCode();
            if(status == HttpStatus.SC_MOVED_TEMPORARILY) {
                url = "http://epoint.pampers.com.cn" + response.getFirstHeader("location").getValue();
                gm = new HttpGet(url);
                response = client.execute(gm);
                contentType = response.getEntity().getContentType().getValue();
                contentType = contentType.substring(contentType.indexOf("charset=") + "charset=".length());
                String content = CommonUtils.getString(response.getEntity().getContent(), contentType);
//                System.out.println(content);
//                System.out.println(url);
                if(content.indexOf("您目前可用积分") >= 0) {
                    login = true;
                }
            }
        }
    }

    private List<String[]> getFormParams(String content, String charset, String formName) throws Exception{
        Parser parser = Parser.createParser(content, charset);
        NodeList list = parser.extractAllNodesThatMatch(new TagNameFilter("FORM"));
        FormTag node = null;
        for(int i = 0; i < list.size(); i++) {
            if(list.elementAt(i) != null && list.elementAt(i) instanceof FormTag) {
                FormTag form = (FormTag) list.elementAt(i);
                if(form.getFormLocation().equals(formName)) {
                    node = form;
                    break;
                }
            }
        }
        if(node == null) {
            return new ArrayList<String[]>();
        }
        String s = node.getStringText();
        parser = Parser.createParser(s, charset);
        list = parser.extractAllNodesThatMatch(new TagNameFilter("INPUT"));
        List<String[]> ret = new ArrayList<String[]>();
        for(int i = 0; i < list.size(); i++) {
            if(list.elementAt(i) != null && list.elementAt(i) instanceof InputTag) {
                InputTag tag = (InputTag) list.elementAt(i);
                ret.add(new String[]{tag.getAttribute("name"), tag.getAttribute("value")});
            }
        }
        return ret;
    }

    private List<String[]> generateObjs(String str, String charset) {
        try {
            Parser parser = Parser.createParser(str, charset);
            NodeList list = parser.extractAllNodesThatMatch(new TagNameFilter("TABLE"));
            FormTag node = null;
            String tableStr = "";
            for(int i = 0; i < list.size(); i++) {
                if(list.elementAt(i) != null && list.elementAt(i) instanceof TableTag) {
                    TableTag table = (TableTag) list.elementAt(i);
                    if("DLgift1".equalsIgnoreCase(table.getAttribute("ID"))) {
                        tableStr = table.getStringText();
                    }
                }
            }

            parser = Parser.createParser(tableStr, charset);
            list = parser.extractAllNodesThatMatch(new TagNameFilter("TABLE"));
            List<String> objs = new ArrayList<String>();
            for(int i = 0; i < list.size(); i++) {
                if(list.elementAt(i) != null && list.elementAt(i) instanceof TableTag) {
                    TableTag table = (TableTag) list.elementAt(i);
                    if("index2_my".equalsIgnoreCase(table.getAttribute("CLASS"))) {
                        objs.add(table.getStringText());
                    }
                }
            }
            List<String[]> ret = new ArrayList<String[]>();
            for(String s : objs) {
                //get id
                parser = Parser.createParser(s, charset);
                list = parser.extractAllNodesThatMatch(new LinkStringFilter("rewards_detail.aspx?p1="));
                int id = 0;
                if(list.size() > 0 && list.elementAt(0) instanceof LinkTag) {
                    LinkTag tag = (LinkTag) list.elementAt(0);
                    String ss = tag.getLink();
                    ss = ss.substring(ss.indexOf("=") + 1);
                    id = Integer.parseInt(ss);
                }
                //get name & count
                parser = Parser.createParser(s, charset);
                list = parser.extractAllNodesThatMatch(new TagNameFilter("STRONG"));
//                System.out.println(list);
                String name = list.elementAt(0).getNextSibling().getText();
                if(s.indexOf("兑换完毕") < 0) {
                    parser = Parser.createParser(s, charset);
                    list = parser.extractAllNodesThatMatch(new StringFilter("库存数："));
                    ret.add(new String[]{id + "", name, list.elementAt(0).getParent().getNextSibling().getChildren().elementAt(0).getText()});
//                    System.out.println(list);
                } else {
                    ret.add(new String[]{id + "", name, "0"});
                }
            }
            return ret;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getItemName(int id) {
        DetectPampers service = (DetectPampers) GlobalContext.getSpringContext().getBean("pampersDetect", DetectPampers.class);
        List<Map<String, Object>> list = service.currentItems;
        if(list == null) {
            return "" + id;
        }
        for(Map<String, Object> obj : list) {
            if(String.valueOf(obj.get("ITEMID")).equals(String.valueOf(id))) {
                return String.valueOf(obj.get("NAME"));
            }
        }
        return "未知";
    }
    
    public void setDao(PampersDao dao) {
        this.dao = dao;
    }

    public void afterPropertiesSet() throws Exception {
//        this.start();
    }
}
