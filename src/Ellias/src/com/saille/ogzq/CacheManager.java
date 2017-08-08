package com.saille.ogzq;

import org.codehaus.jettison.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2014-10-9
 * Time: 10:30:05
 * To change this template use File | Settings | File Templates.
 */
public class CacheManager {
    private final static Logger LOGGER = Logger.getLogger(CacheManager.class);
    public static void savePlayerCache(String email, String playerId, Map<String, String> maps) {
        try {
//            String dirpath = CacheManager.class.getResource("../../../../../ogzq/cache/player/").getPath();
//            File dir = new File(dirpath);
//            if(!dir.exists()) {
//                dir.mkdirs();
//            }
//            email = email.replaceAll("\\*", "~");
//            String filepath = CacheManager.class.getResource("../../../../../ogzq/cache/player/").getPath() + email + "_" + playerId + ".txt";
//            JSONObject obj = new JSONObject();
//            if(maps != null && maps.size() > 0) {
//                for(String key : maps.keySet()) {
//                    obj.put(key, maps.get(key));
//                }
//            }
//            File f = new File(filepath);
//            if(!f.exists()) {
//                f.createNewFile();
//            }
//            FileOutputStream fos = new FileOutputStream(f);
//            fos.write(obj.toString().getBytes());
//            fos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Map<String, String> loadPlayer(String email, String playerId, boolean forceRefresh) {
        try {
//            String saveemail = email.replaceAll("\\*", "~");
//            File f = new File(CacheManager.class.getResource("../../../../../ogzq/cache/player/").getPath() + saveemail + "_" + playerId + ".txt");
//            if(!f.exists() || forceRefresh) {
                Map<String, String> map = OperationUtils.viewPlayer(email, playerId);
//                savePlayerCache(email, playerId, map);
//            }
//            FileReader fr = new FileReader(f);
//            BufferedReader br = new BufferedReader(fr);
//            String s = "", tmp;
//            while((tmp = br.readLine()) != null) {
//                s += tmp;
//            }
//            br.close();
//            fr.close();
//            JSONObject obj = new JSONObject(s);
//            Iterator it = obj.keys();
//            Map<String, String> ret = new HashMap<String, String>();
//            while(it.hasNext()) {
//                Object key = it.next();
//                ret.put(key.toString(), obj.get(key.toString()).toString());
//            }
//            return ret;
            return map;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void deletePlayer(String email, String playerId) {
        try {
            File f = new File(CacheManager.class.getResource("../../../../../ogzq/cache/player/").getPath() + email + "_" + playerId + ".txt");
            if(f.exists()) {
                f.delete();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
