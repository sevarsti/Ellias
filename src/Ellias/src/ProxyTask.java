import com.GlobalConstant;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ProxyTask implements Runnable {
    private Socket socketIn;
    private Socket socketOut;

    private long totalUpload = 0l;//总计上行比特数
    private long totalDownload = 0l;//总计下行比特数

    public ProxyTask(Socket socket) {
        this.socketIn = socket;
    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    /**
     * 已连接到请求的服务器
     */
    private static final String AUTHORED = "HTTP/1.1 200 Connection established\r\n\r\n";
    /** 本代理登陆失败(此应用暂时不涉及登陆操作) */
    //private static final String UNAUTHORED="HTTP/1.1 407 Unauthorized\r\n\r\n";
    /**
     * 内部错误
     */
    private static final String SERVERERROR = "HTTP/1.1 500 Connection FAILED\r\n\r\n";

    @Override
    public void run() {

        StringBuilder builder = new StringBuilder();
        try {
            builder.append("\r\n").append("Request Time  ：" + sdf.format(new Date()));

            InputStream isIn = socketIn.getInputStream();
            OutputStream osIn = socketIn.getOutputStream();
            //从客户端流数据中读取头部，获得请求主机和端口
            HttpHeader header = HttpHeader.readHeader(isIn);

            //添加请求日志信息
            builder.append("\r\n").append("From    Host  ：" + socketIn.getInetAddress());
            builder.append("\r\n").append("From    Port  ：" + socketIn.getPort());
            builder.append("\r\n").append("Proxy   Method：" + header.getMethod());
            builder.append("\r\n").append("Request Host  ：" + header.getHost());
            builder.append("\r\n").append("Request Port  ：" + header.getPort());

            //如果没解析出请求请求地址和端口，则返回错误信息
            if(header.getHost() == null || header.getPort() == null) {
                osIn.write(SERVERERROR.getBytes());
                osIn.flush();
                return;
            }

            // 查找主机和端口
            socketOut = new Socket(header.getHost(), Integer.parseInt(header.getPort()));
            socketOut.setKeepAlive(true);
            InputStream isOut = socketOut.getInputStream();
            OutputStream osOut = socketOut.getOutputStream();
            //新开一个线程将返回的数据转发给客户端,串行会出问题，尚没搞明白原因
            Thread ot = new DataSendThread(isOut, osIn);
            ot.start();
            if(header.getMethod().equals(HttpHeader.METHOD_CONNECT)) {
                // 将已联通信号返回给请求页面
                osIn.write(AUTHORED.getBytes());
                osIn.flush();
            } else {
                //http请求需要将请求头部也转发出去
                byte[] headerData = header.toString().getBytes();
                totalUpload += headerData.length;
                osOut.write(headerData);
                osOut.flush();
            }
            //读取客户端请求过来的数据转发给服务器
            readForwardDate(isIn, osOut);
            //等待向客户端转发的线程结束
            ot.join();
        } catch(Exception e) {
            e.printStackTrace();
            if(!socketIn.isOutputShutdown()) {
                //如果还可以返回错误状态的话，返回内部错误
                try {
                    socketIn.getOutputStream().write(SERVERERROR.getBytes());
                } catch(IOException e1) {
                }
            }
        } finally {
            try {
                if(socketIn != null) {
                    socketIn.close();
                }
            } catch(IOException e) {
            }
            if(socketOut != null) {
                try {
                    socketOut.close();
                } catch(IOException e) {
                }
            }
            //纪录上下行数据量和最后结束时间并打印
            builder.append("\r\n").append("Up    Bytes  ：" + totalUpload);
            builder.append("\r\n").append("Down  Bytes  ：" + totalDownload);
            builder.append("\r\n").append("Closed Time  ：" + sdf.format(new Date()));
            builder.append("\r\n");
            if(builder.toString().indexOf("sdo") >= 0) {
                logRequestMsg(builder.toString());
            }
        }
    }

    /**
     * 避免多线程竞争把日志打串行了
     *
     * @param msg
     */
    private synchronized void logRequestMsg(String msg) {
        System.out.println(msg);
    }

    /**
     * 读取客户端发送过来的数据，发送给服务器端
     *
     * @param isIn
     * @param osOut
     */
    private void readForwardDate(InputStream isIn, OutputStream osOut) {
        byte[] buffer = new byte[4096];
        try {
            int len;
            while((len = isIn.read(buffer)) != -1) {
                if(len > 0) {
                    System.out.println(new String(buffer));
                    osOut.write(buffer, 0, len);
                    osOut.flush();
                }
                totalUpload += len;
                if(socketIn.isClosed() || socketOut.isClosed()) {
                    break;
                }
            }
        } catch(Exception e) {
            try {
                socketOut.close();// 尝试关闭远程服务器连接，中断转发线程的读阻塞状态
            } catch(IOException e1) {
            }
        }
    }

    /**
     * 将服务器端返回的数据转发给客户端
     */
    class DataSendThread extends Thread {
        private InputStream isOut;
        private OutputStream osIn;

        DataSendThread(InputStream isOut, OutputStream osIn) {
            this.isOut = isOut;
            this.osIn = osIn;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[4096];
            try {
                int len;
                while((len = isOut.read(buffer)) != -1) {
                    if(len > 0) {
                        System.out.println(new String(buffer));
                        File f = new File(GlobalConstant.DISKPATH + "temp\\asdf.zip");
                        if(!f.exists()) {
                            f.createNewFile();
                        }
                        FileOutputStream fos = new FileOutputStream(f, true);
                        fos.write(buffer, 0, len);
                        fos.close();
                        // logData(buffer, 0, len);
                        osIn.write(buffer, 0, len);
                        osIn.flush();
                        totalDownload += len;
                    }
                    if(socketIn.isOutputShutdown() || socketOut.isClosed()) {
                        break;
                    }
                }
            } catch(Exception e) {
            }
        }
    }

}

final class HttpHeader {

    private List<String> header = new ArrayList<String>();

    private String method;
    private String host;
    private String port;

    public static final int MAXLINESIZE = 4096;

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_CONNECT = "CONNECT";

    private HttpHeader() {
    }

    /**
     * 从数据流中读取请求头部信息，必须在放在流开启之后，任何数据读取之前
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static final HttpHeader readHeader(InputStream in) throws IOException {
        HttpHeader header = new HttpHeader();
        StringBuilder sb = new StringBuilder();
        //先读出交互协议来，
        char c = 0;
        while((c = (char) in.read()) != '\n') {
            sb.append(c);
            if(sb.length() == MAXLINESIZE) {//不接受过长的头部字段
                break;
            }
        }
        //如能识别出请求方式则则继续，不能则退出
        if(header.addHeaderMethod(sb.toString()) != null) {
            do {
                sb = new StringBuilder();
                while((c = (char) in.read()) != '\n') {
                    sb.append(c);
                    if(sb.length() == MAXLINESIZE) {//不接受过长的头部字段
                        break;
                    }
                }
                if(sb.length() > 1 && header.notTooLong()) {//如果头部包含信息过多，抛弃剩下的部分
                    header.addHeaderString(sb.substring(0, sb.length() - 1));
                } else {
                    break;
                }
            } while(true);
        }

        return header;
    }

    /**
     * @param str
     */
    private void addHeaderString(String str) {
        str = str.replaceAll("\r", "");
        header.add(str);
        if(str.startsWith("Host")) {//解析主机和端口
            String[] hosts = str.split(":");
            host = hosts[1].trim();
            if(method.endsWith(METHOD_CONNECT)) {
                port = hosts.length == 3 ? hosts[2] : "443";//https默认端口为443
            } else if(method.endsWith(METHOD_GET) || method.endsWith(METHOD_POST)) {
                port = hosts.length == 3 ? hosts[2] : "80";//http默认端口为80
            }
        }
    }

    /**
     * 判定请求方式
     *
     * @param str
     * @return
     */
    private String addHeaderMethod(String str) {
        str = str.replaceAll("\r", "");
        header.add(str);
        if(str.startsWith(METHOD_CONNECT)) {//https链接请求代理
            method = METHOD_CONNECT;
        } else if(str.startsWith(METHOD_GET)) {//http GET请求
            method = METHOD_GET;
        } else if(str.startsWith(METHOD_POST)) {//http POST请求
            method = METHOD_POST;
        }
        return method;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(String str : header) {
            sb.append(str).append("\r\n");
        }
        sb.append("\r\n");
        return sb.toString();
    }

    public boolean notTooLong() {
        return header.size() <= 16;
    }


    public List<String> getHeader() {
        return header;
    }


    public void setHeader(List<String> header) {
        this.header = header;
    }


    public String getMethod() {
        return method;
    }


    public void setMethod(String method) {
        this.method = method;
    }

    public String getHost() {
        return host;
    }


    public void setHost(String host) {
        this.host = host;
    }


    public String getPort() {
        return port;
    }


    public void setPort(String port) {
        this.port = port;
    }

}