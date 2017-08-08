package com.saille.newBBS.fromYSSY;

import com.saille.newBBS.UserInfo;
import com.saille.newBBS.telnet.NewBBSMain;

public class FBMore {
    public int ansimore(NewBBSMain thread, String filename, int promptend_in) {
        return ansimore_withmode(thread, filename, promptend_in, 0);
    }

    public int ansimore_withmode(NewBBSMain thread, String filename, int promptend_in, int more_mode_in) {
        UserInfo userInfo = thread.getUserInfo();
        int ch = 0;

        userInfo.setMore_mode(more_mode_in);
        userInfo.setPromptend(promptend_in);
        FBScreen.clear(thread);

        return ch;
    }
}