import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Ellias
 * Date: 2018/01/10 0010
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
public class DataForward {
    public static void main(String[] args) {
        Properties p = new Properties();
        try {
            File configfile = new File("config.ini");
            FileInputStream fis = new FileInputStream(configfile);
            p.load(fis);
            fis.close();
            ServerSocket serverSocket = new ServerSocket(5110);
            while(true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println(socket);
                    String remoteIp = socket.getRemoteSocketAddress().toString();
                    remoteIp = remoteIp.substring(1, remoteIp.indexOf(":"));
                    String target = p.getProperty(remoteIp);
                    if(target != null) {
                        InputStream is = socket.getInputStream();
                        OutputStream os = socket.getOutputStream();
                        String targetIp = target.substring(0, target.indexOf(":"));
                        int targetPort = Integer.parseInt(target.substring(target.indexOf(":") + 1));
                        Socket targetSocket = new Socket(targetIp, targetPort);
                        InputStream targetIs = targetSocket.getInputStream();
                        OutputStream targetOs = targetSocket.getOutputStream();
                        ForwardThread thread = new ForwardThread(is, targetOs);
                        thread.start();
                        thread = new ForwardThread(targetIs, os);
                        thread.start();
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

class ForwardThread extends Thread {
    private InputStream is;
    private OutputStream os;
    public ForwardThread(InputStream is, OutputStream os) {
        this.is = is;
        this.os = os;
    }
    public void run() {
        int i;
        try {
            while(true) {
                i = is.read();
                os.write(i);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}