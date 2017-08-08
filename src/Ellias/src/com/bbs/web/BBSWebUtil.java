package com.bbs.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BBSWebUtil {
    private BBSWebUtil instance = null;
    private List<String[]> cookies = new ArrayList();
    private Socket socket = null;

    public void httpClient() {
    }

    public boolean getWeb(String u) {
        try {
            URL url = new URL(u);
            this.socket = new Socket(InetAddress.getByName(url.getHost()), url.getPort() == -1 ? 80 : url.getPort());
            BufferedOutputStream os = new BufferedOutputStream(this.socket.getOutputStream());
            BufferedInputStream is = new BufferedInputStream(this.socket.getInputStream());

            sendMessage(os, "GET " + url.getFile() + " HTTP/1.1\r\nAccept-Language: zh-cn\r\nUser-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 1.1.4322)\r\nHost: bbs.sjtu.cn\r\nConnection: Keep-AliveAccept: */*\r\n");

            receiveMessage(is);
            url = new URL("http://bbs.sjtu.edu.cn/bbssnd?" + ((String[]) this.cookies.get(0))[0] + "=" + ((String[]) this.cookies.get(0))[1] + "&" + ((String[]) this.cookies.get(1))[0] + "=" + ((String[]) this.cookies.get(1))[1] + "&" + ((String[]) this.cookies.get(2))[0] + "=" + ((String[]) this.cookies.get(2))[1] + "&board=test&text=fortest&signature=1");
            System.out.println(url.getFile());
            receiveMessage(url.openStream());
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean postWeb(String u) {
        try {
            BufferedOutputStream os = new BufferedOutputStream(this.socket.getOutputStream());
            BufferedInputStream is = new BufferedInputStream(this.socket.getInputStream());
            os.write("POST bbssnd HTTP/1.1\r\n".getBytes());
            os.write("Accept: */*\r\n".getBytes());
            os.write("Accept-Language: zh-cn\r\n".getBytes());
            os.write("Content-Type: multipart/form-data\r\n".getBytes());
            os.write("Accept-Encoding: gzip, deflate\r\n".getBytes());
            os.write("User-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 1.1.4322)\r\n".getBytes());
            os.write("Connection: Keep-Alive\r\n".getBytes());
            os.write("Cache-Control: no-cache\r\n".getBytes());
            if((this.cookies != null) && (this.cookies.size() > 0)) {
                os.write("Cookie: ".getBytes());
                for(String[] s : this.cookies) {
                    if(this.cookies.indexOf(s) > 0) {
                        os.write("; ".getBytes());
                    }
                    os.write(s[0].getBytes());
                    os.write("=".getBytes());
                    os.write(s[1].getBytes());
                }
                os.write("\r\n".getBytes());
            }
            os.write("Content-length: ".getBytes());
            String con = generateContent("test", "²âÊÔ", "ÕýÎÄ", 1, 1);
            os.write(String.valueOf(con.getBytes().length).getBytes());
            os.write("\r\n\r\n".getBytes());
            os.write(con.getBytes());
            os.write("\r\n".getBytes());
            os.flush();
            receiveMessage(is);
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private String generateContent(String board, String title, String content, int signature, int autocr) {
        StringBuffer sb = new StringBuffer("");
        sb.append("board=").append(board);
        sb.append("&title=").append(title);
        sb.append("&text=").append(content);
        if(signature > 0) {
            sb.append("&signature=").append(signature);
        }
        if(autocr > 0) {
            sb.append("&autocr=on");
        }
        sb.append("\r\n");
        return sb.toString();
    }

    private void sendMessage(BufferedOutputStream os, String msg) throws Exception {
        System.out.println(msg);
        os.write(msg.getBytes());
        if((this.cookies != null) && (this.cookies.size() > 0)) {
            os.write("Cookie: ".getBytes());
            for(String[] s : this.cookies) {
                if(this.cookies.indexOf(s) > 0) {
                    os.write("; ".getBytes());
                }
                os.write(s[0].getBytes());
                os.write("=".getBytes());
                os.write(s[1].getBytes());
            }
            os.write("\r\n".getBytes());
        }
        os.write("\r\n".getBytes());
        os.flush();
    }

    private void receiveMessage(InputStream is) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        byte[] b = new byte[1024];
        String s = "";

        while((s = br.readLine()) != null) {
            if(s.indexOf("Set-Cookie") != -1) {
                String c = s.substring(12, s.indexOf(";"));
                String[] cookie = c.split("=");
                this.cookies.add(cookie);
            }
            System.out.println(s);
        }
        System.out.println("end");
    }
}