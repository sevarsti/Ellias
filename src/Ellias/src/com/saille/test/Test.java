package com.saille.test;

import com.saille.ogzq.IDUtils;
import com.saille.ogzq.OgzqURL;
import com.saille.ogzq.LoginUtils;
import com.saille.util.FileUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.NameValuePair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
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

    public static void main(String[] args) {
//        item();
        load();
    }

    public static void load() {
        try {
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "60000");
            File f = new File("D:\\alllaobing.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String s;
            File saveFile = new File("D:\\laobing.txt");
            File exceptionFile = new File("D:\\silvers2.txt");
            int count = 0;
            while((s = br.readLine()) != null) {
                try {
                    System.out.println(s + (count++));
                    String[] parts = s.split("\t");
                    String email = parts[0];
                    String pwd = parts[1];
                    HttpClient client = LoginUtils.Login(email, pwd);

                    System.out.println("email:" + email);
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/LaoBingChuanQi.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("LoadTab01", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(client, email, pm);

                    if(s.indexOf("╋") > 0) {
                        s = s.substring(0, s.indexOf("╋"));
                    }
                    parts = s.split("@");
                    System.out.println("剩余次数：" + parts[0]);
                    if(Integer.parseInt(parts[0]) > 0) { //剩余召唤次数
                        if(Integer.parseInt(parts[2]) > 0) {
                            System.out.println("CD: " + parts[2]);
                        } else {
                            pm = new HttpPost(OgzqURL.URL + "/LaoBingChuanQi.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("zhaoHuan", "1"));
                            params.add(new BasicNameValuePair("mode", "1"));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            s = IDUtils.execute(client, email, pm);
                            System.out.println("召唤结果：" + s);
                            FileUtils.WriteFile(saveFile, email + "\t" + s + "\r\n", true);
                        }
                    }
                } catch(Exception ex) {
                    FileUtils.WriteFile(exceptionFile, s + "\r\n", true);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void sell() {
        try {
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "60000");
            File f = new File("D:\\alllaobing.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String s;
            File saveFile = new File("D:\\laobing.txt");
            File exceptionFile = new File("D:\\silvers2.txt");
            int count = 0;
            while((s = br.readLine()) != null) {
                try {
                    System.out.println(s + (count++));
                    String[] parts = s.split("\t");
                    String email = parts[0];
                    String pwd = parts[1];
                    HttpClient client = LoginUtils.Login(email, pwd);

                    System.out.println("email:" + email);
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/LaoBingChuanQi.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("LoadTab01", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(client, email, pm);

                    if(s.indexOf("╋") > 0) {
                        s = s.substring(0, s.indexOf("╋"));
                    }
                    parts = s.split("@");
                    System.out.println("剩余次数：" + parts[0]);
                    if(Integer.parseInt(parts[0]) > 0) { //剩余召唤次数
                        if(Integer.parseInt(parts[2]) > 0) {
                            System.out.println("CD: " + parts[2]);
                        } else {
                            pm = new HttpPost(OgzqURL.URL + "/LaoBingChuanQi.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("zhaoHuan", "1"));
                            params.add(new BasicNameValuePair("mode", "1"));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            s = IDUtils.execute(client, email, pm);
                            System.out.println("召唤结果：" + s);
                            FileUtils.WriteFile(saveFile, email + "\t" + s + "\r\n", true);
                        }
                    }
                } catch(Exception ex) {
                    FileUtils.WriteFile(exceptionFile, s + "\r\n", true);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void item() {
        try {
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "60000");
            File f = new File("D:\\alllaobing.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String s;
            File saveFile = new File("D:\\laobing.txt");
            File exceptionFile = new File("D:\\silvers2.txt");
            int count = 0;
            while((s = br.readLine()) != null) {
                try {
//                    System.out.println(s + (count++));
                    String[] parts = s.split("\t");
                    String email = parts[0];
                    String pwd = parts[1];
                    HttpClient client = LoginUtils.Login(email, pwd);

//                    System.out.println("email:" + email);
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/LaoBingChuanQi.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("showItems", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(client, email, pm);

                    if("-1".equals(s)) {
                        continue;
                    }
                    String[] cards = s.split("[*]");
                    for(String c : cards) {
                        System.out.println(email + "\t" + c.replaceAll("[|]", "\t"));
                    }

                } catch(Exception ex) {
                    FileUtils.WriteFile(exceptionFile, s + "\r\n", true);
                }
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