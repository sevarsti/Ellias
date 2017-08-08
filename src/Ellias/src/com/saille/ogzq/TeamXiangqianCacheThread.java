package com.saille.ogzq;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2014-9-10
 * Time: 8:51:38
 * To change this template use File | Settings | File Templates.
 */
public class TeamXiangqianCacheThread extends Thread{
    private final static Logger LOGGER = Logger.getLogger(TeamXiangqianCacheThread.class);
    private List<String> ids;
    private int level;
    private boolean forceRefresh;

    public TeamXiangqianCacheThread(List<String> ids, int level, boolean forceRefresh) {
        this.ids = ids;
        this.level = level;
        this.forceRefresh = forceRefresh;
    }

    public void run() {
        int size = this.ids.size();
        for(int i = (level == 0 ? 1 : level); i <= (level == 0 ? 6 : level); i++) {
            for(int j = 0; j < size; j++) {
                String id = this.ids.get(j);
                System.out.println(j + "/" + size + ":" + id);
                try {
                    String filepath = TeamXiangqianCacheThread.class.getResource("../../../../../ogzq/cache/xiangqian/").getPath() + id + "_" + i + ".txt";
                    filepath = filepath.replaceAll("[*]", "~");
                    if(!forceRefresh) {
                        if(!new File(filepath).exists()) {
                            loadSinglePlayerLevelXiangqian(id, i);
                        }
                    } else {
                        loadSinglePlayerLevelXiangqian(id, i);
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static List<String[]> loadSinglePlayerLevelXiangqian(String email, int level) {
        try {
            List<String[]> players = OperationUtils.loadXiangqianTactic(email, level);
            saveCache(email, level, players);
            return players;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static synchronized void saveCache(String email, int level, List<String[]> players) {
        File dir = new File(TeamXiangqianCacheThread.class.getResource("../../../../../ogzq/cache/xiangqian").getFile());
        if(!dir.exists()) {
            dir.mkdirs();
        }
        String filepath = TeamXiangqianCacheThread.class.getResource("../../../../../ogzq/cache/xiangqian/").getPath() + email + "_" + level + ".txt";
        filepath = filepath.replaceAll("[*]", "~");
        File f = new File(filepath);
        if(f.exists()) {
            f.delete();
        }
        if(players == null || players.size() == 0) {
            return;
        }
        FileOutputStream fos = null;
        try {
            f.createNewFile();
            fos = new FileOutputStream(f);
            for(String[] p : players) {
//                String playerId
                if(p[1].equals("卡列洪")) {
                    HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.VIEW_PLAYER);
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("LoadPlayer1", p[0]));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String str = IDUtils.execute(email, pm);
                    String[] parts = str.split("\\*");
                    if(parts[7].equals("391")) {
                        String[] targetzhong= new String[]{"+38","+43","+43","+40","+64","+55","+24","+24"};
                        String[] targetqian = new String[]{"+57","+56","+40","+36","+48","+60","+24","+24"};
                        boolean exact = true;
                        for(int i = 0; i < 8; i++) {
                            if(!targetzhong[i].equals(parts[i + 88])) {
                                exact = false;
                                break;
                            }
                        }
                        if(exact) {
                            p[1] = "卡列洪中";
                        } else {
                            exact = true;
                            for(int i = 0; i < 8; i++) {
                                if(!targetqian[i].equals(parts[i + 88])) {
                                    exact = false;
                                    break;
                                }
                            }
                            if(exact) {
                                p[1] = "卡列洪前";
                            }
                        }
                    }
                }
                for(String s : p) {
                    fos.write(s.getBytes());
                    fos.write("|".getBytes());
                }
                fos.write("\n".getBytes());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally{
            if(fos != null) {
                try {
                    fos.close();
                } catch(Exception ex) {}
            }
        }
    }
}
