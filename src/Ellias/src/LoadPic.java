import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2015-8-21
 * Time: 22:39:05
 * To change this template use File | Settings | File Templates.
 */
public class LoadPic {
    private static PropertiesConfiguration conf = null;
//    private final static String DIR = "D:\\zhuaqu\\";
    private final static String DIR = "pic\\";
    private static String curnation = null;
    private static int curpage = 1;

    public static void main(String[] args) {
        loadConfig();
        try {
            int page = 1;
            List<String[]> nationalities = loadNations();
            List<String[]> members = new ArrayList<String[]>();
            int begin = -1;
            for(String[] n : nationalities) { //循环国家
                if(begin < 0) {
                    if(curnation != null && curnation.length() > 0) {
                        if(!n[0].equals(curnation)) {
                            continue;
                        } else {
                            begin = 0;
                        }
                    } else {
                        begin = 0;
                    }
                }
                page = 1;
                if(begin == 0) {
                    page = curpage;
                    begin = 1;
                }
//                File f = new File(DIR + n[0]);
//                if(!f.exists()) {
//                    f.mkdirs();
//                }
                saveConfig("nation", n[0]);
                while(true) { //循环获得国家内的明星
                    String tmp = n[1].substring(0, n[1].indexOf("-celebrities/"));
                    tmp = tmp.substring(tmp.lastIndexOf("/") + 1);
                    String path = "http://lists.famousfix.com/sections/celebrities/nationality/" + tmp;
                    if(page > 1) {
                        path += "_" + page;
                    }
                    boolean end = false;
                    System.out.println(nationalities.indexOf(n) + "/" + nationalities.size() + ":" + n[0] + "\t" + path);
                    String content = execute(path);
                    String next = content.substring(content.indexOf("Next") - 2);
                    if(!next.startsWith("\">")) {
                        end = true;
                    }
                    content = content.substring(content.indexOf("<ol") + 3);
                    content = content.substring(0, content.indexOf("</ol"));
                    content = content.substring(content.indexOf("<li") + 3);
//                    System.out.println("size: " + content.split("\\<li").length);
                    String[] contents = content.split("\\<li");
                    for(String s : contents) {
                        if(s.indexOf("img") < 0) {
                            continue;
                        }
                        String ss = s.substring(s.indexOf("<a "));
                        ss = ss.substring(0, ss.indexOf(">"));
                        String name = ss.substring(ss.indexOf("\"") + 1);
                        name = name.substring(0, name.indexOf("\""));
                        String link = ss.substring(ss.indexOf("http"));
                        link = link.substring(0, link.indexOf("\""));
//                        members.add(new String[]{n[0], name, link});
                        System.out.println(n[0] + "\t" + name + "\t" + link);
//                        File f = new File(DIR + n[0] + "\\" + name);
//                        if(!f.exists()) {
//                            f.mkdirs();
//                        }
                        loadPic(link, DIR + n[0] + "\\" + name.replaceAll("\\?", "~").replaceAll("\\/", "~"));
//                        todo
                    }
                    if(end) {
                        break;
                    }
                    page++;
                    saveConfig("index", page + "");
                }
            }
            for(String[] m : members) {
                System.out.println(members.indexOf(m) + "/" + members.size() + ":" + m[0] + "\t" + m[1] + "\t" + m[2]);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void loadPic(String link, String localpath) {
        link = link.replaceAll("who\\.", "photos.");
        String url = link + "photos";

        String content = execute(url);
        String ss = content.substring(content.indexOf("<link rel=\"canonical\" href=\"") + "<link rel=\"canonical\" href=\"".length());
        if(!ss.startsWith("http://photos")) {
            return;
        }
        if(content.indexOf("age_gate_off") >= 0) {
            content = executePost(url);
        }
        String firstpic = "";
        content = content.substring(content.indexOf("<div class=\"headline\">") + "<div class=\"headline\">".length());
        String[] pics = content.split("<div class=\\\"headline\\\">");
        for(String p : pics) {
            if(p.indexOf("<a title=") < 0) {
                continue;
            }
            String img = p.substring(p.indexOf("<a title="));
            img = img.substring(0, img.indexOf(">"));
            img = img.substring(img.indexOf("href=\"") + "href=\"".length());
            img = img.substring(0, img.indexOf("\""));
            firstpic = img;
            break;
        }
        if(firstpic.equals("")) {
            return;
        }
        url = firstpic;
        int picindex = 1;
        while(true) {
            String newurl = null;
            content = execute(url);
            if(content.indexOf("age_gate_off") >= 0) {
                content = executePost(url);
            }
            boolean jixu = true;
            if(content.indexOf("NEXT PHOTO") < 0) {
                jixu = false;
            } else {
                newurl = content.substring(0, content.indexOf("NEXT PHOTO"));
                newurl = newurl.substring(0, newurl.lastIndexOf("\""));
                newurl = newurl.substring(newurl.lastIndexOf("\"") + 1);
            }
            if(content.indexOf("viewlrg") >= 0) {
                content = content.substring(content.indexOf("viewlrg"));
                content = content.substring(content.indexOf("href=\"") + "href=\"".length());
                content = content.substring(0, content.indexOf("\""));
                String u = content;
                content = execute(content);
                if(content.indexOf("age_gate_off") >= 0) {
                    content = executePost(u);
                }

                content = content.substring(content.indexOf("<div class=\"view-gallery\">") + "<div class=\"view-gallery\">".length());
                content = content.substring(content.indexOf("<img"));
                content = content.substring(content.indexOf("src=\"") + "src=\"".length());
                content = content.substring(0, content.indexOf("\""));
            } else {
                if(content.indexOf("imgorig") >= 0) {
                    content = content.substring(content.indexOf("imgorig"));
                    content = content.substring(content.indexOf("src=\"") + "src=\"".length());
                    content = content.substring(0, content.indexOf("\""));
                } else {
                    content = null;
                    if(!jixu) {
                        break;
                    } else {
                        url = newurl;
                    }
                }
            }
            if(content != null) {
                boolean  ok = savePic(content, localpath + "\\" + picindex, url);
                if(ok) {
                    picindex++;
                }
            }
            if(picindex > 500) {
                jixu = false;
            }
            if(!jixu) {
                break;
            } else {
                url = newurl;
            }
        }
    }

    public static String getString(InputStream is, String charset) throws Exception {
        List list = new ArrayList();
        int i;
        while((i = is.read()) != -1) {
            list.add(Byte.valueOf((byte) i));
        }
        byte[] bb = new byte[list.size()];
        for(int ii = 0; ii < list.size(); ii++) {
            bb[ii] = ((Byte) list.get(ii)).byteValue();
        }
        String ret = new String(bb, charset);
        return ret;
    }

    public static List<String[]> loadNations() throws Exception {
        List<String[]> list = new ArrayList<String[]>();
        boolean load = false;
        if(load) {
            int page = 1;
            while(true) {
                String path = "http://lists.famousfix.com/sections/celebrities/nationality";
                if(page > 1) {
                    path += "_" + page;
                }
                String content = execute(path);
                boolean end = false;
                String next = content.substring(content.indexOf("Next") - 2);
                if(!next.startsWith("\">")) {
                    end = true;
                }
                content = content.substring(content.indexOf("<ol") + 3);
                content = content.substring(0, content.indexOf("</ol"));
                content = content.substring(content.indexOf("<li") + 3);
                System.out.println("size: " + content.split("\\<li").length);
                String[] contents = content.split("\\<li");
                int count = 0;
                for(String s : contents) {
                    if(s.indexOf("<img") < 0) {
                        continue;
                    }
                    String ss = s.substring(s.indexOf("<a "));
                    ss = ss.substring(0, ss.indexOf(">"));
                    String nation = ss.substring(ss.indexOf("\"") + 1);
                    nation = nation.substring(0, nation.indexOf("\""));
                    String link = ss.substring(ss.indexOf("http"));
                    link = link.substring(0, link.indexOf("\""));
                    list.add(new String[]{nation.substring(0, nation.indexOf(" celebrities")), link});
                    System.out.println(nation.substring(0, nation.indexOf(" celebrities")) + "\t" + link);
                    count++;
                }
//                System.out.println(count);
                page++;
                if(end) {
                    break;
                }
            }
        } else {
            list.add(new String[]{"Afghan", "http://lists.famousfix.com/ctn_19256533/afghan-celebrities/"});
            list.add(new String[]{"Albanian", "http://lists.famousfix.com/ctn_19256534/albanian-celebrities/"});
            list.add(new String[]{"Algerian", "http://lists.famousfix.com/ctn_19256535/algerian-celebrities/"});
            list.add(new String[]{"American", "http://lists.famousfix.com/ctn_19256536/american-celebrities/"});
            list.add(new String[]{"Andorran", "http://lists.famousfix.com/ctn_19256537/andorran-celebrities/"});
            list.add(new String[]{"Angolan", "http://lists.famousfix.com/ctn_19256538/angolan-celebrities/"});
            list.add(new String[]{"Antiguans, Barbudans", "http://lists.famousfix.com/ctn_19256539/antiguans-barbudans-celebrities/"});
            list.add(new String[]{"Argentinean", "http://lists.famousfix.com/ctn_19256540/argentinean-celebrities/"});
            list.add(new String[]{"Armenian", "http://lists.famousfix.com/ctn_19256541/armenian-celebrities/"});
            list.add(new String[]{"Aruban", "http://lists.famousfix.com/ctn_32833856/aruban-celebrities/"});
            list.add(new String[]{"Australian", "http://lists.famousfix.com/ctn_19256542/australian-celebrities/"});
            list.add(new String[]{"Austrian", "http://lists.famousfix.com/ctn_19256543/austrian-celebrities/"});
            list.add(new String[]{"Azerbaijani", "http://lists.famousfix.com/ctn_19256544/azerbaijani-celebrities/"});
            list.add(new String[]{"Bahamanian", "http://lists.famousfix.com/ctn_19256545/bahamanian-celebrities/"});
            list.add(new String[]{"Bahraini", "http://lists.famousfix.com/ctn_19256546/bahraini-celebrities/"});
            list.add(new String[]{"Bangladeshi", "http://lists.famousfix.com/ctn_19256547/bangladeshi-celebrities/"});
            list.add(new String[]{"Barbadian", "http://lists.famousfix.com/ctn_19256548/barbadian-celebrities/"});
            list.add(new String[]{"Belarusian", "http://lists.famousfix.com/ctn_19256549/belarusian-celebrities/"});
            list.add(new String[]{"Belgian", "http://lists.famousfix.com/ctn_19256550/belgian-celebrities/"});
            list.add(new String[]{"Belizean", "http://lists.famousfix.com/ctn_19256551/belizean-celebrities/"});
            list.add(new String[]{"Beninese", "http://lists.famousfix.com/ctn_19256552/beninese-celebrities/"});
            list.add(new String[]{"Bermudian", "http://lists.famousfix.com/ctn_19256553/bermudian-celebrities/"});
            list.add(new String[]{"Bhutanese", "http://lists.famousfix.com/ctn_19256554/bhutanese-celebrities/"});
            list.add(new String[]{"Bolivian", "http://lists.famousfix.com/ctn_19256555/bolivian-celebrities/"});
            list.add(new String[]{"Bosnian, Herzegovinian", "http://lists.famousfix.com/ctn_19256556/bosnian-herzegovinian-celebrities/"});
            list.add(new String[]{"Brazilian", "http://lists.famousfix.com/ctn_19256557/brazilian-celebrities/"});
            list.add(new String[]{"British", "http://lists.famousfix.com/ctn_19256558/british-celebrities/"});
            list.add(new String[]{"Bruneian", "http://lists.famousfix.com/ctn_19256559/bruneian-celebrities/"});
            list.add(new String[]{"Bulgarian", "http://lists.famousfix.com/ctn_19256560/bulgarian-celebrities/"});
            list.add(new String[]{"Burkinabe", "http://lists.famousfix.com/ctn_19256561/burkinabe-celebrities/"});
            list.add(new String[]{"Burmese", "http://lists.famousfix.com/ctn_19256562/burmese-celebrities/"});
            list.add(new String[]{"Burundian", "http://lists.famousfix.com/ctn_19256563/burundian-celebrities/"});
            list.add(new String[]{"Cambodian", "http://lists.famousfix.com/ctn_19256564/cambodian-celebrities/"});
            list.add(new String[]{"Cameroonian", "http://lists.famousfix.com/ctn_19256565/cameroonian-celebrities/"});
            list.add(new String[]{"Canadian", "http://lists.famousfix.com/ctn_19256566/canadian-celebrities/"});
            list.add(new String[]{"Caymanian", "http://lists.famousfix.com/ctn_26609032/caymanian-celebrities/"});
            list.add(new String[]{"Central African", "http://lists.famousfix.com/ctn_19256568/central-african-celebrities/"});
            list.add(new String[]{"Chadian", "http://lists.famousfix.com/ctn_19256569/chadian-celebrities/"});
            list.add(new String[]{"Chilean", "http://lists.famousfix.com/ctn_19256570/chilean-celebrities/"});
            list.add(new String[]{"Chinese", "http://lists.famousfix.com/ctn_19256571/chinese-celebrities/"});
            list.add(new String[]{"Colombian", "http://lists.famousfix.com/ctn_19256572/colombian-celebrities/"});
            list.add(new String[]{"Congolese", "http://lists.famousfix.com/ctn_19256574/congolese-celebrities/"});
            list.add(new String[]{"Costa Rican", "http://lists.famousfix.com/ctn_19256575/costa-rican-celebrities/"});
            list.add(new String[]{"Croatian", "http://lists.famousfix.com/ctn_19256576/croatian-celebrities/"});
            list.add(new String[]{"Cuban", "http://lists.famousfix.com/ctn_19256577/cuban-celebrities/"});
            list.add(new String[]{"Cypriot", "http://lists.famousfix.com/ctn_19256578/cypriot-celebrities/"});
            list.add(new String[]{"Czech", "http://lists.famousfix.com/ctn_19256579/czech-celebrities/"});
            list.add(new String[]{"Danish", "http://lists.famousfix.com/ctn_19256580/danish-celebrities/"});
            list.add(new String[]{"Djibouti", "http://lists.famousfix.com/ctn_19256581/djibouti-celebrities/"});
            list.add(new String[]{"Dominican", "http://lists.famousfix.com/ctn_19256582/dominican-celebrities/"});
            list.add(new String[]{"Dutch", "http://lists.famousfix.com/ctn_19256583/dutch-celebrities/"});
            list.add(new String[]{"Ecuadorean", "http://lists.famousfix.com/ctn_19256584/ecuadorean-celebrities/"});
            list.add(new String[]{"Egyptian", "http://lists.famousfix.com/ctn_19256585/egyptian-celebrities/"});
            list.add(new String[]{"Emirian", "http://lists.famousfix.com/ctn_19256586/emirian-celebrities/"});
            list.add(new String[]{"English", "http://lists.famousfix.com/ctn_19256587/english-celebrities/"});
            list.add(new String[]{"Equatorial Guinean", "http://lists.famousfix.com/ctn_19256588/equatorial-guinean-celebrities/"});
            list.add(new String[]{"Eritrean", "http://lists.famousfix.com/ctn_19256589/eritrean-celebrities/"});
            list.add(new String[]{"Estonian", "http://lists.famousfix.com/ctn_19256590/estonian-celebrities/"});
            list.add(new String[]{"Ethiopian", "http://lists.famousfix.com/ctn_19256591/ethiopian-celebrities/"});
            list.add(new String[]{"Fijian", "http://lists.famousfix.com/ctn_19256592/fijian-celebrities/"});
            list.add(new String[]{"Filipino", "http://lists.famousfix.com/ctn_19256593/filipino-celebrities/"});
            list.add(new String[]{"Finnish", "http://lists.famousfix.com/ctn_19256594/finnish-celebrities/"});
            list.add(new String[]{"French", "http://lists.famousfix.com/ctn_19256595/french-celebrities/"});
            list.add(new String[]{"Gabonese", "http://lists.famousfix.com/ctn_19256596/gabonese-celebrities/"});
            list.add(new String[]{"Gambian", "http://lists.famousfix.com/ctn_19256597/gambian-celebrities/"});
            list.add(new String[]{"Georgian", "http://lists.famousfix.com/ctn_19256598/georgian-celebrities/"});
            list.add(new String[]{"German", "http://lists.famousfix.com/ctn_19256599/german-celebrities/"});
            list.add(new String[]{"Ghanaian", "http://lists.famousfix.com/ctn_19256600/ghanaian-celebrities/"});
            list.add(new String[]{"Gibraltarian", "http://lists.famousfix.com/ctn_26609033/gibraltarian-celebrities/"});
            list.add(new String[]{"Greek", "http://lists.famousfix.com/ctn_19256601/greek-celebrities/"});
            list.add(new String[]{"Greenlandian", "http://lists.famousfix.com/ctn_19256602/greenlandian-celebrities/"});
            list.add(new String[]{"Grenadian", "http://lists.famousfix.com/ctn_19256603/grenadian-celebrities/"});
            list.add(new String[]{"Guatemalan", "http://lists.famousfix.com/ctn_19256604/guatemalan-celebrities/"});
            list.add(new String[]{"Guinea-Bissauan", "http://lists.famousfix.com/ctn_19256605/guinea-bissauan-celebrities/"});
            list.add(new String[]{"Guinean", "http://lists.famousfix.com/ctn_19256606/guinean-celebrities/"});
            list.add(new String[]{"Guyanese", "http://lists.famousfix.com/ctn_19256607/guyanese-celebrities/"});
            list.add(new String[]{"Haitian", "http://lists.famousfix.com/ctn_19256608/haitian-celebrities/"});
            list.add(new String[]{"Honduran", "http://lists.famousfix.com/ctn_19256609/honduran-celebrities/"});
            list.add(new String[]{"Hong Konger", "http://lists.famousfix.com/ctn_19256610/hong-konger-celebrities/"});
            list.add(new String[]{"Hungarian", "http://lists.famousfix.com/ctn_19256611/hungarian-celebrities/"});
            list.add(new String[]{"Icelander", "http://lists.famousfix.com/ctn_19256613/icelander-celebrities/"});
            list.add(new String[]{"Indian", "http://lists.famousfix.com/ctn_19256614/indian-celebrities/"});
            list.add(new String[]{"Indonesian", "http://lists.famousfix.com/ctn_19256615/indonesian-celebrities/"});
            list.add(new String[]{"Iranian", "http://lists.famousfix.com/ctn_19256616/iranian-celebrities/"});
            list.add(new String[]{"Iraqi", "http://lists.famousfix.com/ctn_19256617/iraqi-celebrities/"});
            list.add(new String[]{"Irish", "http://lists.famousfix.com/ctn_19256618/irish-celebrities/"});
            list.add(new String[]{"Israeli", "http://lists.famousfix.com/ctn_19256619/israeli-celebrities/"});
            list.add(new String[]{"Italian", "http://lists.famousfix.com/ctn_19256620/italian-celebrities/"});
            list.add(new String[]{"Ivorian", "http://lists.famousfix.com/ctn_19256621/ivorian-celebrities/"});
            list.add(new String[]{"Jamaican", "http://lists.famousfix.com/ctn_19256622/jamaican-celebrities/"});
            list.add(new String[]{"Japanese", "http://lists.famousfix.com/ctn_19256623/japanese-celebrities/"});
            list.add(new String[]{"Jordanian", "http://lists.famousfix.com/ctn_19256624/jordanian-celebrities/"});
            list.add(new String[]{"Kazakhstani", "http://lists.famousfix.com/ctn_19256625/kazakhstani-celebrities/"});
            list.add(new String[]{"Kenyan", "http://lists.famousfix.com/ctn_19256626/kenyan-celebrities/"});
            list.add(new String[]{"Kittian and Nevisian", "http://lists.famousfix.com/ctn_19256627/kittian-and-nevisian-celebrities/"});
            list.add(new String[]{"Kosovar", "http://lists.famousfix.com/ctn_26609034/kosovar-celebrities/"});
            list.add(new String[]{"Kuwaiti", "http://lists.famousfix.com/ctn_19256628/kuwaiti-celebrities/"});
            list.add(new String[]{"Lao or Laotian", "http://lists.famousfix.com/ctn_19256630/lao-or-laotian-celebrities/"});
            list.add(new String[]{"Latvian", "http://lists.famousfix.com/ctn_19256631/latvian-celebrities/"});
            list.add(new String[]{"Lebanese", "http://lists.famousfix.com/ctn_19256632/lebanese-celebrities/"});
            list.add(new String[]{"Liberian", "http://lists.famousfix.com/ctn_19256633/liberian-celebrities/"});
            list.add(new String[]{"Libyan", "http://lists.famousfix.com/ctn_19256634/libyan-celebrities/"});
            list.add(new String[]{"Liechtensteiner", "http://lists.famousfix.com/ctn_19256635/liechtensteiner-celebrities/"});
            list.add(new String[]{"Lithuanian", "http://lists.famousfix.com/ctn_19256636/lithuanian-celebrities/"});
            list.add(new String[]{"Luxembourger", "http://lists.famousfix.com/ctn_19256637/luxembourger-celebrities/"});
            list.add(new String[]{"Macanese", "http://lists.famousfix.com/ctn_26609036/macanese-celebrities/"});
            list.add(new String[]{"Macedonian", "http://lists.famousfix.com/ctn_19256638/macedonian-celebrities/"});
            list.add(new String[]{"Malagasy", "http://lists.famousfix.com/ctn_19256639/malagasy-celebrities/"});
            list.add(new String[]{"Malawian", "http://lists.famousfix.com/ctn_19256640/malawian-celebrities/"});
            list.add(new String[]{"Malaysian", "http://lists.famousfix.com/ctn_19256641/malaysian-celebrities/"});
            list.add(new String[]{"Malian", "http://lists.famousfix.com/ctn_19256643/malian-celebrities/"});
            list.add(new String[]{"Maltese", "http://lists.famousfix.com/ctn_19256644/maltese-celebrities/"});
            list.add(new String[]{"Marshallese", "http://lists.famousfix.com/ctn_19256645/marshallese-celebrities/"});
            list.add(new String[]{"Mauritanian", "http://lists.famousfix.com/ctn_19256646/mauritanian-celebrities/"});
            list.add(new String[]{"Mauritian", "http://lists.famousfix.com/ctn_19256647/mauritian-celebrities/"});
            list.add(new String[]{"Mexican", "http://lists.famousfix.com/ctn_19256648/mexican-celebrities/"});
            list.add(new String[]{"Moldovan", "http://lists.famousfix.com/ctn_19256650/moldovan-celebrities/"});
            list.add(new String[]{"Monacan", "http://lists.famousfix.com/ctn_19256651/monacan-celebrities/"});
            list.add(new String[]{"Mongolian", "http://lists.famousfix.com/ctn_19256652/mongolian-celebrities/"});
            list.add(new String[]{"Montenegrin", "http://lists.famousfix.com/ctn_26609031/montenegrin-celebrities/"});
            list.add(new String[]{"Montserratian", "http://lists.famousfix.com/ctn_19256653/montserratian-celebrities/"});
            list.add(new String[]{"Moroccan", "http://lists.famousfix.com/ctn_19256654/moroccan-celebrities/"});
            list.add(new String[]{"Motswana", "http://lists.famousfix.com/ctn_19256656/motswana-celebrities/"});
            list.add(new String[]{"Mozambican", "http://lists.famousfix.com/ctn_19256657/mozambican-celebrities/"});
            list.add(new String[]{"Namibian", "http://lists.famousfix.com/ctn_19256658/namibian-celebrities/"});
            list.add(new String[]{"Nepalese", "http://lists.famousfix.com/ctn_19256660/nepalese-celebrities/"});
            list.add(new String[]{"New Zealander", "http://lists.famousfix.com/ctn_19256661/new-zealander-celebrities/"});
            list.add(new String[]{"Nicaraguan", "http://lists.famousfix.com/ctn_19256663/nicaraguan-celebrities/"});
            list.add(new String[]{"Nigerian", "http://lists.famousfix.com/ctn_19256664/nigerian-celebrities/"});
            list.add(new String[]{"North Korean", "http://lists.famousfix.com/ctn_19256665/north-korean-celebrities/"});
            list.add(new String[]{"Northern Irish", "http://lists.famousfix.com/ctn_19256666/northern-irish-celebrities/"});
            list.add(new String[]{"Norwegian", "http://lists.famousfix.com/ctn_19256667/norwegian-celebrities/"});
            list.add(new String[]{"Omani", "http://lists.famousfix.com/ctn_19256668/omani-celebrities/"});
            list.add(new String[]{"Pakistani", "http://lists.famousfix.com/ctn_19256669/pakistani-celebrities/"});
            list.add(new String[]{"Palauan", "http://lists.famousfix.com/ctn_19256670/palauan-celebrities/"});
            list.add(new String[]{"Palestinian", "http://lists.famousfix.com/ctn_19256671/palestinian-celebrities/"});
            list.add(new String[]{"Panamanian", "http://lists.famousfix.com/ctn_19256672/panamanian-celebrities/"});
            list.add(new String[]{"Papua New Guinean", "http://lists.famousfix.com/ctn_19256673/papua-new-guinean-celebrities/"});
            list.add(new String[]{"Paraguayan", "http://lists.famousfix.com/ctn_19256674/paraguayan-celebrities/"});
            list.add(new String[]{"Persian", "http://lists.famousfix.com/ctn_19256675/persian-celebrities/"});
            list.add(new String[]{"Peruvian", "http://lists.famousfix.com/ctn_19256676/peruvian-celebrities/"});
            list.add(new String[]{"Polish", "http://lists.famousfix.com/ctn_19256677/polish-celebrities/"});
            list.add(new String[]{"Portuguese", "http://lists.famousfix.com/ctn_19256678/portuguese-celebrities/"});
            list.add(new String[]{"Puerto Rican", "http://lists.famousfix.com/ctn_19256679/puerto-rican-celebrities/"});
            list.add(new String[]{"Qatari", "http://lists.famousfix.com/ctn_19256680/qatari-celebrities/"});
            list.add(new String[]{"Romanian", "http://lists.famousfix.com/ctn_19256681/romanian-celebrities/"});
            list.add(new String[]{"Russian", "http://lists.famousfix.com/ctn_19256682/russian-celebrities/"});
            list.add(new String[]{"Rwandan", "http://lists.famousfix.com/ctn_19256683/rwandan-celebrities/"});
            list.add(new String[]{"Saint Lucian", "http://lists.famousfix.com/ctn_19256685/saint-lucian-celebrities/"});
            list.add(new String[]{"Salvadoran", "http://lists.famousfix.com/ctn_19256686/salvadoran-celebrities/"});
            list.add(new String[]{"Samoan", "http://lists.famousfix.com/ctn_19256687/samoan-celebrities/"});
            list.add(new String[]{"Saudi Arabian", "http://lists.famousfix.com/ctn_19256690/saudi-arabian-celebrities/"});
            list.add(new String[]{"Scottish", "http://lists.famousfix.com/ctn_19256691/scottish-celebrities/"});
            list.add(new String[]{"Senegalese", "http://lists.famousfix.com/ctn_19256692/senegalese-celebrities/"});
            list.add(new String[]{"Serbian", "http://lists.famousfix.com/ctn_26609030/serbian-celebrities/"});
            list.add(new String[]{"Sierra Leonean", "http://lists.famousfix.com/ctn_19256694/sierra-leonean-celebrities/"});
            list.add(new String[]{"Singaporean", "http://lists.famousfix.com/ctn_19256695/singaporean-celebrities/"});
            list.add(new String[]{"Slovakian", "http://lists.famousfix.com/ctn_19256696/slovakian-celebrities/"});
            list.add(new String[]{"Slovenian", "http://lists.famousfix.com/ctn_19256697/slovenian-celebrities/"});
            list.add(new String[]{"Somali", "http://lists.famousfix.com/ctn_19256699/somali-celebrities/"});
            list.add(new String[]{"South African", "http://lists.famousfix.com/ctn_19256700/south-african-celebrities/"});
            list.add(new String[]{"South Korean", "http://lists.famousfix.com/ctn_19256701/south-korean-celebrities/"});
            list.add(new String[]{"Spanish", "http://lists.famousfix.com/ctn_19256703/spanish-celebrities/"});
            list.add(new String[]{"Sri Lankan", "http://lists.famousfix.com/ctn_19256704/sri-lankan-celebrities/"});
            list.add(new String[]{"Sudanese", "http://lists.famousfix.com/ctn_19256705/sudanese-celebrities/"});
            list.add(new String[]{"Surinamer", "http://lists.famousfix.com/ctn_19256706/surinamer-celebrities/"});
            list.add(new String[]{"Swazi", "http://lists.famousfix.com/ctn_19256707/swazi-celebrities/"});
            list.add(new String[]{"Swedish", "http://lists.famousfix.com/ctn_19256708/swedish-celebrities/"});
            list.add(new String[]{"Swiss", "http://lists.famousfix.com/ctn_19256709/swiss-celebrities/"});
            list.add(new String[]{"Syrian", "http://lists.famousfix.com/ctn_19256710/syrian-celebrities/"});
            list.add(new String[]{"Tahitian", "http://lists.famousfix.com/ctn_19256711/tahitian-celebrities/"});
            list.add(new String[]{"Taiwanese", "http://lists.famousfix.com/ctn_19256712/taiwanese-celebrities/"});
            list.add(new String[]{"Tajik", "http://lists.famousfix.com/ctn_19256713/tajik-celebrities/"});
            list.add(new String[]{"Tanzanian", "http://lists.famousfix.com/ctn_19256714/tanzanian-celebrities/"});
            list.add(new String[]{"Thai", "http://lists.famousfix.com/ctn_19256715/thai-celebrities/"});
            list.add(new String[]{"Togolese", "http://lists.famousfix.com/ctn_19256716/togolese-celebrities/"});
            list.add(new String[]{"Tongan", "http://lists.famousfix.com/ctn_19256717/tongan-celebrities/"});
            list.add(new String[]{"Trinidadian, Tobagonian", "http://lists.famousfix.com/ctn_19256718/trinidadian-tobagonian-celebrities/"});
            list.add(new String[]{"Tunisian", "http://lists.famousfix.com/ctn_19256719/tunisian-celebrities/"});
            list.add(new String[]{"Turkish", "http://lists.famousfix.com/ctn_19256720/turkish-celebrities/"});
            list.add(new String[]{"Turkmen(s)", "http://lists.famousfix.com/ctn_19256721/turkmen-s-celebrities/"});
            list.add(new String[]{"Turks And Caicos Islander", "http://lists.famousfix.com/ctn_26609035/turks-and-caicos-islander-celebrities/"});
            list.add(new String[]{"Ugandan", "http://lists.famousfix.com/ctn_19256723/ugandan-celebrities/"});
            list.add(new String[]{"Ukrainian", "http://lists.famousfix.com/ctn_19256724/ukrainian-celebrities/"});
            list.add(new String[]{"Uruguayan", "http://lists.famousfix.com/ctn_19256725/uruguayan-celebrities/"});
            list.add(new String[]{"Uzbekistani", "http://lists.famousfix.com/ctn_19256726/uzbekistani-celebrities/"});
            list.add(new String[]{"Vatican", "http://lists.famousfix.com/ctn_19256727/vatican-celebrities/"});
            list.add(new String[]{"Venezuelan", "http://lists.famousfix.com/ctn_19256728/venezuelan-celebrities/"});
            list.add(new String[]{"Vietnamese", "http://lists.famousfix.com/ctn_19256729/vietnamese-celebrities/"});
            list.add(new String[]{"Vincentian", "http://lists.famousfix.com/ctn_19256730/vincentian-celebrities/"});
            list.add(new String[]{"Welsh", "http://lists.famousfix.com/ctn_19256731/welsh-celebrities/"});
            list.add(new String[]{"Yemeni", "http://lists.famousfix.com/ctn_19256732/yemeni-celebrities/"});
            list.add(new String[]{"Yugoslavian", "http://lists.famousfix.com/ctn_19256733/yugoslavian-celebrities/"});
            list.add(new String[]{"Zambian", "http://lists.famousfix.com/ctn_19256734/zambian-celebrities/"});
            list.add(new String[]{"Zimbabwean", "http://lists.famousfix.com/ctn_19256736/zimbabwean-celebrities/"});
        }
        return list;
    }

    public static String execute(String url) {
        while(true) {
            try {
                if(!url.startsWith("http")) {
                    return "";
                }
                HttpClient client = new DefaultHttpClient();
                client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 50000);
                client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 50000);
                client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true); //启用重定向
                HttpGet gm = new HttpGet(url);
                gm.addHeader("Referer", "http://photos.famousfix.com");

//                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("age_gate_off", "1"));
//                gm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                HttpResponse response = client.execute(gm);
                String content = getString(response.getEntity().getContent(), "UTF-8");
                return content;
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String executePost(String url) {
        while(true) {
            try {
                HttpClient client = new DefaultHttpClient();
                client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 50000);
                client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 50000);
                client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true); //启用重定向
                HttpPost pm = new HttpPost(url);
                pm.addHeader("Referer", "http://photos.famousfix.com");

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("age_gate_off", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                HttpResponse response = client.execute(pm);
                String content = getString(response.getEntity().getContent(), "UTF-8");
                return content;
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void saveConfig(String key, String value) {
        conf.setProperty(key, value);
    }

    public static void loadConfig() {
        try {
//            conf = new PropertiesConfiguration(DIR + "config.ini");
            conf = new PropertiesConfiguration("config.ini");
            conf.setAutoSave(true);
            curnation = conf.getString("nation");
            curpage = conf.getInt("index");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean savePic(String url, String localfile, String refer) {
        try {
            System.out.println("picurl=" + url);
            String ext = url.substring(0, url.lastIndexOf("?"));
            ext = ext.substring(ext.lastIndexOf("."));
            File f = new File(localfile + ext);
            File dir = new File(localfile.substring(0, localfile.lastIndexOf("\\")));
            if(!dir.exists()) {
                dir.mkdirs();
            }
            if(!f.exists()) {
                f.createNewFile();
            }
//            URL u = new URL(url);
//            URLConnection conn = u.openConnection();
//            InputStream is = conn.getInputStream();
            while(true) {
                try {
                    FileOutputStream fos = new FileOutputStream(f);
                    HttpClient client = new DefaultHttpClient();
                    client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
                    client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
                    client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true); //启用重定向
                    HttpGet gm = new HttpGet(url);
                    gm.addHeader("Referer", refer);
                    HttpResponse response = client.execute(gm);
                    InputStream is = response.getEntity().getContent();
                    byte[] bytes = new byte[8192];
                    int count = 0;
                    while((count = is.read(bytes)) > 0) {
                        fos.write(bytes, 0, count);
                    }
                    fos.close();
                    is.close();
                    break;
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
