package com.saille.travian;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class Sheep {
    private int id;
    private String value;
    static int cell = 1;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private static List cc() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/ellias";
        String username = "root";
        String pwd = "sjtuagent";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, pwd);
        Statement stmt = conn.createStatement(1005, 1008);
        String sql = "select x, y, type, food2 from travian where (type = 'f1' or type = 'f6')";
        ResultSet rs = stmt.executeQuery(sql);
        List ret = new ArrayList();
        while(rs.next()) {
            Map m = new HashMap();
            m.put("X", Integer.valueOf(rs.getInt("x")));
            m.put("Y", Integer.valueOf(rs.getInt("y")));
            String type = rs.getString("type");
            m.put("T", Integer.valueOf(type.equals("f1") ? 9 : 15));
            m.put("F", Integer.valueOf(rs.getInt("food2")));
            ret.add(m);
        }

        return ret;
    }

    static List<Map> dd() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/ellias";
        String username = "root";
        String pwd = "sjtuagent";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, pwd);
        Statement stmt = conn.createStatement(1005, 1008);
        String sql = "select x, y, wood, clay, iron, food from travian where type = 'fo' and food = 50";
        ResultSet rs = stmt.executeQuery(sql);
        List ret = new ArrayList();
        while(rs.next()) {
            Map m = new HashMap();
            m.put("X", Integer.valueOf(rs.getInt("x")));
            m.put("Y", Integer.valueOf(rs.getInt("y")));
            m.put("F", Integer.valueOf(rs.getInt("food")));
            ret.add(m);
        }
        return ret;
    }

    public static void main(String[] args) {
        try {
            File f = new File("d:\\tttravian.txt");
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String tmp = null;
            tmp = br.readLine();
            JSONObject j = new JSONObject(tmp);
            JSONArray arrays = j.getJSONObject("data").getJSONArray("tiles");
            for(int i = 0; i < arrays.length(); i++) {
                JSONObject json = arrays.getJSONObject(i);
                if(!json.has("u")) {
                    continue;
                }
                String villageName = json.getString("c");
                if(villageName.indexOf("{k.dt} ") == -1) {
                    continue;
                }
                System.out.print(json.getString("x") + "\t" + json.getString("y") + "\t");

                villageName = villageName.substring("{k.dt} ".length());
                System.out.print(villageName + "\t");
                String values = json.getString("t");
                String name = values.substring(values.indexOf("{k.spieler} ") + "{k.spieler} ".length());
                name = name.substring(0, name.indexOf("<br />"));
                String renkou = values.substring(values.indexOf("{k.einwohner} ") + "{k.einwohner} ".length());
                renkou = renkou.substring(0, renkou.indexOf("<br />"));
                String allianz = values.substring(values.indexOf("{k.allianz} ") + "{k.allianz} ".length());
                allianz = allianz.substring(0, allianz.indexOf("<br />"));
                System.out.println(name + "\t" + renkou + "\t" + allianz);
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void log(String s) {
        try {
            File f = new File("D:\\legendplayers.txt");
            if(!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f, true);
            fos.write(s.getBytes());
            fos.write("\r\n".getBytes());
            fos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getOsName() {
        String os = "";
        os = System.getProperty("os.name");
        return os;
    }

    public static String getMACAddress() {
        String address = "";
        String os = getOsName();
        if(os.startsWith("Windows")) {
            try {
                String command = "cmd.exe /c ipconfig /all";
                Process p = Runtime.getRuntime().exec(command);
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while((line = br.readLine()) != null) {
                    if(line.indexOf("Physical Address") > 0) {
                        int index = line.indexOf(":");
                        index += 2;
                        address = line.substring(index);
                    }
                }

                br.close();
                return address.trim();
            } catch(IOException e) {
            }
        } else if(os.startsWith("Linux")) {
            String command = "/bin/sh -c ifconfig -a";
            try {
                Process p = Runtime.getRuntime().exec(command);
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while((line = br.readLine()) != null) {
                    if(line.indexOf("HWaddr") > 0) {
                        int index = line.indexOf("HWaddr") + "HWaddr".length();
                        address = line.substring(index);
                    }
                }

                br.close();
            } catch(IOException e) {
            }
        }
        address = address.trim();
        return address;
    }

    public static String getMACAddress(String ipAddress) {
        String str = "";
        String strMAC = "";
        String macAddress = "";
        try {
            Process pp = Runtime.getRuntime().exec("nbtstat -a " + ipAddress);
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for(int i = 1; i < 100; i++) {
                str = input.readLine();
                if((str == null) || (str.indexOf("MAC Address") <= 1)) {
                    continue;
                }
                strMAC = str.substring(str.indexOf("MAC Address") + 14, str.length());

                break;
            }
        } catch(IOException ex) {
            return "Can't Get MAC Address!";
        }

        if(strMAC.length() < 17) {
            return "Error!";
        }

        macAddress = strMAC.substring(0, 2) + ":" + strMAC.substring(3, 5) + ":" + strMAC.substring(6, 8) + ":" + strMAC.substring(9, 11) + ":" + strMAC.substring(12, 14) + ":" + strMAC.substring(15, 17);

        return macAddress;
    }
}