package com.tencent.msdk.handle;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.permission.PermissionManage;
import com.tencent.msdk.stat.Stat;
import com.tencent.msdk.tools.Http;
import com.tencent.msdk.tools.Logger;
import java.util.HashMap;

public class MsdkThread extends HandlerThread
  implements Handler.Callback
{
  public static final int clearLocation = 25;
  public static final int closeScrollNotice = 22;
  public static final int feedback = 4;
  public static final int feedbackWithAppId = 23;
  public static final int getNearbyPlayer = 24;
  public static final int getNotice = 20;
  public static final int getPermission = 0;
  public static final int getPfKeyReq = 7;
  public static final int getPfKeyReqWithWakeup = 8;
  protected static final int getQQFirstLoginPfKeyReq = 18;
  public static final int getScheduling = 1;
  protected static final int hideQmi = 29;
  public static final int login = 3;
  public static final int openUrl = 27;
  public static final int qqA8Req = 9;
  public static final int queryQQGameFriendsInfo = 14;
  public static final int queryQQUserInfo = 13;
  public static final int queryWXGameFriendsInfo = 16;
  public static final int queryWXUserInfo = 15;
  public static final int reportLogin = 2;
  public static final int sendMessageToWechatGameCenter = 26;
  public static final int sendToQQGameFriend = 10;
  public static final int sendToWXGameFriend = 11;
  protected static final int setGameEngineType = 30;
  public static final int showNoticeByScene = 21;
  public static final int showQmi = 28;
  public static final int verifyLocalAndRefreshWxToken = 32;
  public static final int verifyLocalQQToken = 31;
  public static final int wxExpiredLoginReq = 6;
  public static final int wxFirstLoginReq = 5;
  protected static final int wxRefreshTokenLoginReq = 17;

  public MsdkThread(String paramString)
  {
    super(paramString);
  }

  private void feedback(Bundle paramBundle)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("platID", "" + paramBundle.getString("platID"));
    localHashMap.put("gameID", paramBundle.getString("gameID"));
    localHashMap.put("openID", paramBundle.getString("openID"));
    localHashMap.put("question", paramBundle.getString("question"));
    int i = Http.post(WeGame.getInstance().getApiDomain() + "/index.php", localHashMap);
    Logger.d("MsdkThread", "feedback post status " + i);
  }

  private void reportLogin(Bundle paramBundle)
  {
    String str = paramBundle.getString("openId");
    int i = paramBundle.getInt("platId");
    WeGame.getInstance().getStat().reportLogin(str, i);
  }

  public boolean handleMessage(Message paramMessage)
  {
    Logger.d("MsdkThread", " handleMessage CurrentThread = " + Thread.currentThread().getName());
    switch (paramMessage.what)
    {
    case 1:
    case 3:
    default:
    case 0:
    case 2:
    case 4:
    }
    while (true)
    {
      return true;
      PermissionManage.getInstance().init();
      continue;
      reportLogin(paramMessage.getData());
      continue;
      feedback(paramMessage.getData());
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.handle.MsdkThread
 * JD-Core Version:    0.6.0
 */