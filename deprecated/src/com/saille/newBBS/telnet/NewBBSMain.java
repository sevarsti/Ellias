package com.saille.newBBS.telnet;

import com.saille.newBBS.User;
import com.saille.newBBS.UserInfo;
import com.saille.newBBS.service.NewBBSService;
import com.saille.newBBS.telnet.util.IO;
import com.saille.newBBS.telnet.util.RegisterID;
import com.saille.newBBS.telnet.util.ShowArticle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import org.apache.commons.lang.StringUtils;

public class NewBBSMain extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintStream out;
    private UserInfo userInfo;
    private User currentuser;

    public NewBBSMain(Socket s) throws Exception {
        this.socket = s;
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new PrintStream(this.socket.getOutputStream(), false);
    }

    public void run() {
        try {
            ShowArticle.ShowArticle(this, "D:\\yssymain.txt");

            IO.println(this, "\033[1;35m欢迎光临\033[1;40;33m【 饮水思源 】 [\033[0;1;33;41m Add '.' after YourID to login for BIG5 \033[m]");
            IO.println(this, "\033[0;1;32m本站可注册帐号数: [\033[1;36m" + StringUtils.leftPad(String.valueOf(100), 2, " ") + "\033[0;1;32m] \033[m");
            IO.println(this, "\033[1;32m从 [\033[36m2001年 9月15日\033[32m] 起, 最高人数记录: [\033[36m8176\033[32m] 累计访问人次: [\033[36m45921388\033[m");
            String id = null;
            boolean isGuest = false;
            boolean isNew = false;
            while(id == null) {
                IO.print(this, "\033[1;33m请输入帐号\033[0;37m(试用请输入 `\033[1;36mguest\033[0;37m', 注册请输入`\033[1;31mnew\033[0;37m'): \033[m");
                id = IO.getString(this, 12, true);
                if(StringUtils.isEmpty(id)) {
                    id = null;
                    IO.println(this, "\033[1;31m经查证，无此 ID (User ID Error)...\033[m");
                    continue;
                }
                if(id.equalsIgnoreCase("GUEST")) {
                    isGuest = true;
                    break;
                }
                if(id.equalsIgnoreCase("NEW")) {
                    isNew = true;
                    break;
                }
                NewBBSService service = NewBBSService.getInstance();
                User user = service.getUserById(id, true);
                if(user == null) {
                    id = null;
                    IO.println(this, "\033[1;31m经查证，无此 ID (User ID Error)...\033[m");
                    continue;
                }
            }

            if(!isGuest) {
                if(isNew) {
                    RegisterID.doRegister(this);
                } else {
                    IO.print(this, "请输入密码：");
                    IO.getString(this, 12, false);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public BufferedReader getIn() {
        return this.in;
    }

    public PrintStream getOut() {
        return this.out;
    }

    public User getCurrentuser() {
        return this.currentuser;
    }

    public void setCurrentuser(User currentuser) {
        this.currentuser = currentuser;
    }
}