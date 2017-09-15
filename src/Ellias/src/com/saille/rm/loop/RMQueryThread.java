package com.saille.rm.loop;

import com.saille.sys.BaseThread;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import servlet.GlobalContext;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ellias
 * Date: 2017/09/15 0015
 * Time: 08:46
 * To change this template use File | Settings | File Templates.
 */
public class RMQueryThread extends BaseThread{
    private RMQueryThread(){}

    @Override
    protected int execute() {
        try {
            ServerSocket serverSocket = new ServerSocket(9527);
            while(true) {
                Socket socket = serverSocket.accept();
                RMQuerySubThread workThread = new RMQuerySubThread(socket);
                workThread.start();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}

class RMQuerySubThread extends Thread {
    private final static Logger LOGGER = Logger.getLogger(RMQuerySubThread.class);
    private Socket socket;
    public RMQuerySubThread(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            byte[] bytes = new byte[32];
            is.read(bytes);
            String recvMd5 = new String(bytes);
            LOGGER.info(" ’µΩmd5¥Æ:" + recvMd5);
            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);
            List<Map<String, Object>> list = jt.queryForList("select id, name, path from rm_customsong where md5 = ?", new Object[]{recvMd5});
            if(list.size() == 0) {
                String ret = "0000";
                OutputStream os = socket.getOutputStream();
                os.write(ret.getBytes());
                os.close();
                is.close();
                socket.close();
                return;
            } else {
                String ret = "0001";
                OutputStream os = socket.getOutputStream();
                os.write(ret.getBytes());
                os.close();
                is.close();
                socket.close();
                return;
            }
//            StringBuilder sb = new StringBuilder();
//            for(int i = 0; i < list.size(); i++) {
//                Map<String, Object> map = list.get(i);
//                int songId = ((Number) map.get("id")).intValue();
//                String name = map.get("name").toString();
//                String path = map.get("path").toString();
//            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}