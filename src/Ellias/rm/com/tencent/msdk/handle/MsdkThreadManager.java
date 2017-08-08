package com.tencent.msdk.handle;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.api.WakeupRet;
import com.tencent.msdk.db.QQLoginModel;
import com.tencent.msdk.db.WxLoginModel;
import com.tencent.msdk.lbs.LocationInfo;
import com.tencent.msdk.notice.NoticeManager;
import com.tencent.msdk.notice.NoticeRequestPara;
import com.tencent.msdk.notice.eMSG_NOTICETYPE;
import com.tencent.msdk.qmi.QmiSdkApiProxy;
import com.tencent.msdk.remote.api.RemoteApiWrapper;
import com.tencent.msdk.request.NoticeMsgMng;
import com.tencent.msdk.request.PfKeyRequestMng;
import com.tencent.msdk.request.QQA8RequestMng;
import com.tencent.msdk.request.WxRequestMng;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import com.tencent.msdk.webview.WebViewManager;
import com.tencent.msdk.weixin.BtnBase;
import com.tencent.msdk.weixin.MsgBase;
import com.tencent.msdk.weixin.MsgWechatWrapper;

public class MsdkThreadManager
{
  private static volatile MsdkThreadManager instance = null;
  private Handler mainHandler = null;
  private Handler msdkHandler = null;
  private MsdkThread msdkThread = null;

  public static MsdkThreadManager getInstance()
  {
    if (instance == null)
      monitorenter;
    try
    {
      if (instance == null)
        instance = new MsdkThreadManager();
      return instance;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean clearLocation()
  {
    if (WGPlatform.WGGetLoginRecord(new LoginRet()) == 0)
      return false;
    Logger.d("called");
    Message localMessage = new Message();
    localMessage.what = 25;
    this.mainHandler.sendMessage(localMessage);
    return true;
  }

  public void closeScrollNotice()
  {
    Logger.d("closeScrollNotice");
    this.mainHandler.sendEmptyMessage(22);
  }

  public void getNearbyPlayer(LocationInfo paramLocationInfo)
  {
    Logger.d("called");
    Message localMessage = new Message();
    localMessage.what = 24;
    localMessage.obj = paramLocationInfo;
    this.mainHandler.sendMessage(localMessage);
  }

  public void getNoticeReq(NoticeRequestPara paramNoticeRequestPara)
  {
    Logger.d("getNoticeReq");
    Message localMessage = new Message();
    localMessage.what = 20;
    localMessage.obj = paramNoticeRequestPara;
    this.mainHandler.sendMessage(localMessage);
  }

  public void getPfKeyReq(int paramInt)
  {
    Logger.d("getPfKeyReq(int notifyState)");
    Message localMessage = new Message();
    localMessage.what = 7;
    localMessage.arg1 = paramInt;
    this.mainHandler.sendMessage(localMessage);
  }

  public void getPfKeyReqWithWakeup(int paramInt, WakeupRet paramWakeupRet)
  {
    Logger.d("getPfKeyReq(int notifyState,WakeupRet wakeup)");
    Message localMessage = new Message();
    localMessage.what = 8;
    localMessage.arg1 = paramInt;
    localMessage.obj = paramWakeupRet;
    this.mainHandler.sendMessage(localMessage);
  }

  public void getQQFirstLoginPfKeyReq(QQLoginModel paramQQLoginModel)
  {
    Logger.d("getPfKeyReq(int notifyState)");
    Message localMessage = new Message();
    localMessage.what = 18;
    localMessage.obj = paramQQLoginModel;
    this.mainHandler.sendMessage(localMessage);
  }

  public void hideQMi()
  {
    Logger.d("hideQMi");
    Message localMessage = new Message();
    localMessage.what = 29;
    this.mainHandler.sendMessage(localMessage);
  }

  public void init()
  {
    this.msdkThread = new MsdkThread("MsdkThread");
    this.msdkThread.start();
    this.msdkHandler = new Handler(this.msdkThread.getLooper(), this.msdkThread);
    this.mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramMessage)
      {
        switch (paramMessage.what)
        {
        case 4:
        case 12:
        case 17:
        case 19:
        default:
          Logger.d("invalid message");
        case 3:
        case 5:
        case 6:
        case 7:
        case 18:
        case 31:
        case 32:
        case 8:
        case 9:
        case 10:
        case 11:
        case 13:
        case 14:
        case 15:
        case 16:
        case 20:
        case 21:
        case 22:
        case 23:
        case 24:
        case 25:
        case 27:
        case 26:
        case 28:
        case 29:
        case 30:
        }
        while (true)
        {
          return false;
          WeGame.getInstance().login(paramMessage.arg1);
          continue;
          MsdkThreadManager.WxFirstLoginInfo localWxFirstLoginInfo = (MsdkThreadManager.WxFirstLoginInfo)paramMessage.obj;
          if (localWxFirstLoginInfo == null)
            continue;
          new WxRequestMng().wxFirstLoginReq(localWxFirstLoginInfo.wxCode, localWxFirstLoginInfo.notifyState);
          continue;
          new WxRequestMng().wxExpiredLoginReq(paramMessage.arg1);
          continue;
          new PfKeyRequestMng().getPfKeyReq(paramMessage.arg1);
          continue;
          QQLoginModel localQQLoginModel2 = (QQLoginModel)paramMessage.obj;
          if (localQQLoginModel2 == null)
            continue;
          new PfKeyRequestMng().getQQFirstLoginPfKeyReq(localQQLoginModel2);
          continue;
          QQLoginModel localQQLoginModel1 = (QQLoginModel)paramMessage.obj;
          if (localQQLoginModel1 == null)
            continue;
          new PfKeyRequestMng().verifyLocalQQToken(localQQLoginModel1);
          continue;
          WxLoginModel localWxLoginModel = (WxLoginModel)paramMessage.obj;
          if (localWxLoginModel == null)
            continue;
          new WxRequestMng().verifyLocalAndRefreshWxToken(localWxLoginModel);
          continue;
          WakeupRet localWakeupRet = (WakeupRet)paramMessage.obj;
          if (localWakeupRet == null)
            continue;
          new PfKeyRequestMng().getPfKeyReq(paramMessage.arg1, localWakeupRet);
          continue;
          MsdkThreadManager.QQA8Info localQQA8Info = (MsdkThreadManager.QQA8Info)paramMessage.obj;
          if (localQQA8Info == null)
            continue;
          new QQA8RequestMng().qqA8Req(localQQA8Info.openAuthData, localQQA8Info.openAuthSt, localQQA8Info.notifyState);
          continue;
          MsdkThreadManager.SendToQQGameFriend localSendToQQGameFriend = (MsdkThreadManager.SendToQQGameFriend)paramMessage.obj;
          if (localSendToQQGameFriend == null)
            continue;
          RemoteApiWrapper.WGSendToQQGameFriend(localSendToQQGameFriend.act, localSendToQQGameFriend.friendOpenId, localSendToQQGameFriend.title, localSendToQQGameFriend.summary, localSendToQQGameFriend.targetUrl, localSendToQQGameFriend.imageUrl, localSendToQQGameFriend.previewText, localSendToQQGameFriend.gameTag, localSendToQQGameFriend.msdkExtInfo);
          continue;
          MsdkThreadManager.SendToWXGameFriend localSendToWXGameFriend = (MsdkThreadManager.SendToWXGameFriend)paramMessage.obj;
          if (localSendToWXGameFriend == null)
            continue;
          RemoteApiWrapper.WGSendToWXGameFriend(localSendToWXGameFriend.friendOpenid, localSendToWXGameFriend.title, localSendToWXGameFriend.description, localSendToWXGameFriend.messageExt, localSendToWXGameFriend.mediaTagName, localSendToWXGameFriend.thumbMediaId, localSendToWXGameFriend.msdkExtInfo);
          continue;
          RemoteApiWrapper.WGQueryQQUserInfo();
          continue;
          RemoteApiWrapper.WGQueryQQGameFriendsInfo();
          continue;
          RemoteApiWrapper.WGQueryWXUserInfo();
          continue;
          RemoteApiWrapper.WGQueryWXGameFriendsInfo();
          continue;
          NoticeRequestPara localNoticeRequestPara = (NoticeRequestPara)paramMessage.obj;
          Logger.d(localNoticeRequestPara);
          if (localNoticeRequestPara != null)
          {
            new NoticeMsgMng().getNoticeReq(localNoticeRequestPara);
            continue;
          }
          Logger.w("noticeInfo is null");
          continue;
          MsdkThreadManager.ShowNoticeInfoByScene localShowNoticeInfoByScene = (MsdkThreadManager.ShowNoticeInfoByScene)paramMessage.obj;
          if (localShowNoticeInfoByScene == null)
            continue;
          NoticeManager.getInstance().showNoticeByScene(localShowNoticeInfoByScene.type, localShowNoticeInfoByScene.scene);
          continue;
          NoticeManager.getInstance().closeScrollNotice();
          continue;
          RemoteApiWrapper.Feedback((String)paramMessage.obj);
          continue;
          if (paramMessage.obj == null)
          {
            Logger.d("location info null");
            return false;
          }
          if (!(paramMessage.obj instanceof LocationInfo))
          {
            Logger.d("obj is not a LocationInfo");
            return false;
          }
          RemoteApiWrapper.QueryNearbyPlayer((LocationInfo)paramMessage.obj);
          continue;
          RemoteApiWrapper.ClearLocation();
          continue;
          if (paramMessage.obj == null)
          {
            Logger.d("openUrl info null");
            return false;
          }
          WebViewManager.getInstance().openUrl((String)paramMessage.obj);
          continue;
          if (paramMessage.obj == null)
          {
            Logger.d("sendMessageToWechatGameCenter info null");
            return false;
          }
          if (!(paramMessage.obj instanceof MsdkThreadManager.SendMessageToWechatGameCenterReq))
          {
            Logger.d("obj is not a SendMessageToWechatGameCenterReq");
            return false;
          }
          MsdkThreadManager.SendMessageToWechatGameCenterReq localSendMessageToWechatGameCenterReq = (MsdkThreadManager.SendMessageToWechatGameCenterReq)paramMessage.obj;
          RemoteApiWrapper.SendMessageToWechatGameCenter(localSendMessageToWechatGameCenterReq.wechatMsg, localSendMessageToWechatGameCenterReq.msdkExtInfo);
          continue;
          QmiSdkApiProxy.showQMi(WeGame.getInstance().getActivity(), "Android");
          continue;
          QmiSdkApiProxy.hideQMi(WeGame.getInstance().getActivity());
          continue;
          if (paramMessage.obj == null)
          {
            Logger.d("setGameEngineType null");
            return false;
          }
          if (!(paramMessage.obj instanceof String))
          {
            Logger.d("obj is not a String");
            return false;
          }
          Logger.d("setGameEngineType: " + (String)paramMessage.obj);
          QmiSdkApiProxy.setGameEngineType((String)paramMessage.obj);
        }
      }
    });
  }

  public void openUrl(String paramString)
  {
    if (T.ckIsEmpty(paramString))
    {
      Logger.w("Url is empty!");
      return;
    }
    Logger.d("called");
    Message localMessage = new Message();
    localMessage.what = 27;
    localMessage.obj = paramString;
    this.mainHandler.sendMessage(localMessage);
  }

  public void qqA8Req(String paramString1, String paramString2, int paramInt)
  {
    Logger.d("qqA8Req");
    Message localMessage = new Message();
    localMessage.what = 9;
    QQA8Info localQQA8Info = new QQA8Info();
    localQQA8Info.openAuthData = paramString1;
    localQQA8Info.openAuthSt = paramString2;
    localQQA8Info.notifyState = paramInt;
    localMessage.obj = localQQA8Info;
    this.mainHandler.sendMessage(localMessage);
  }

  public void queryQQGameFriendsInfo()
  {
    Logger.d("queryQQGameFriendsInfo");
    Message localMessage = new Message();
    localMessage.what = 14;
    this.mainHandler.sendMessage(localMessage);
  }

  public void queryQQUserInfo()
  {
    Logger.d("queryQQMyInfo");
    Message localMessage = new Message();
    localMessage.what = 13;
    this.mainHandler.sendMessage(localMessage);
  }

  public void queryWXGameFriendsInfo()
  {
    Logger.d("queryWXGameFriendsInfo");
    Message localMessage = new Message();
    localMessage.what = 16;
    this.mainHandler.sendMessage(localMessage);
  }

  public void queryWXUserInfo()
  {
    Logger.d("queryWXMyInfo");
    Message localMessage = new Message();
    localMessage.what = 15;
    this.mainHandler.sendMessage(localMessage);
  }

  public void sendFeedback(String paramString1, String paramString2, int paramInt, String paramString3)
  {
    Logger.d("sendFeedback");
    Message localMessage = new Message();
    localMessage.what = 4;
    Bundle localBundle = new Bundle();
    localBundle.putString("platID", "" + paramInt);
    localBundle.putString("gameID", paramString1);
    localBundle.putString("openID", paramString3);
    localBundle.putString("question", paramString2);
    localMessage.setData(localBundle);
    this.msdkHandler.sendMessage(localMessage);
  }

  public void sendFeedbackWithAppid(String paramString)
  {
    Logger.d("sendFeedbackWithAppid");
    Message localMessage = new Message();
    localMessage.what = 23;
    localMessage.obj = paramString;
    this.mainHandler.sendMessage(localMessage);
  }

  public void sendGetPermissionMsg()
  {
    Logger.d("sendGetPermissionMsg");
    this.msdkHandler.sendEmptyMessage(0);
  }

  public void sendGetSchedulingMsg(Context paramContext)
  {
    Logger.d("sendGetSchedulingMsg");
    Message localMessage = new Message();
    localMessage.what = 1;
    localMessage.obj = paramContext;
    this.msdkHandler.sendMessage(localMessage);
  }

  public void sendLoginMsg(int paramInt)
  {
    Logger.d("sendLoginMsg");
    Message localMessage = new Message();
    localMessage.what = 3;
    localMessage.arg1 = paramInt;
    this.mainHandler.sendMessage(localMessage);
  }

  public boolean sendMessageToWechatGameCenter(String paramString1, String paramString2, String paramString3, MsgBase paramMsgBase, BtnBase paramBtnBase, String paramString4)
  {
    if (WGPlatform.WGGetLoginRecord(new LoginRet()) == 0)
      return false;
    Logger.d("called");
    Message localMessage = new Message();
    localMessage.what = 26;
    MsgWechatWrapper localMsgWechatWrapper = new MsgWechatWrapper(paramString1, paramString2, paramString3, paramBtnBase, paramMsgBase);
    SendMessageToWechatGameCenterReq localSendMessageToWechatGameCenterReq = new SendMessageToWechatGameCenterReq();
    localSendMessageToWechatGameCenterReq.wechatMsg = localMsgWechatWrapper;
    localSendMessageToWechatGameCenterReq.msdkExtInfo = paramString4;
    localMessage.obj = localSendMessageToWechatGameCenterReq;
    this.mainHandler.sendMessage(localMessage);
    return true;
  }

  public void sendReportLogin(String paramString, int paramInt)
  {
    Logger.d("sendReportLogin");
    Bundle localBundle = new Bundle();
    localBundle.putString("openId", paramString);
    localBundle.putInt("platId", paramInt);
    Message localMessage = new Message();
    localMessage.what = 2;
    localMessage.setData(localBundle);
    this.msdkHandler.sendMessage(localMessage);
  }

  public void sendToQQGameFriend(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8)
  {
    Logger.d("sendToQQGameFriend");
    Message localMessage = new Message();
    localMessage.what = 10;
    SendToQQGameFriend localSendToQQGameFriend = new SendToQQGameFriend();
    localSendToQQGameFriend.act = paramInt;
    localSendToQQGameFriend.friendOpenId = paramString1;
    localSendToQQGameFriend.title = paramString2;
    localSendToQQGameFriend.summary = paramString3;
    localSendToQQGameFriend.targetUrl = paramString4;
    localSendToQQGameFriend.imageUrl = paramString5;
    localSendToQQGameFriend.previewText = paramString6;
    localSendToQQGameFriend.gameTag = paramString7;
    localSendToQQGameFriend.msdkExtInfo = paramString8;
    localMessage.obj = localSendToQQGameFriend;
    this.mainHandler.sendMessage(localMessage);
  }

  public void sendToWXGameFriend(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    Logger.d("sendToWXGameFriend");
    Message localMessage = new Message();
    localMessage.what = 11;
    SendToWXGameFriend localSendToWXGameFriend = new SendToWXGameFriend();
    localSendToWXGameFriend.friendOpenid = paramString1;
    localSendToWXGameFriend.title = paramString2;
    localSendToWXGameFriend.description = paramString3;
    localSendToWXGameFriend.messageExt = paramString4;
    localSendToWXGameFriend.mediaTagName = paramString5;
    localSendToWXGameFriend.thumbMediaId = paramString6;
    localSendToWXGameFriend.msdkExtInfo = paramString7;
    localMessage.obj = localSendToWXGameFriend;
    this.mainHandler.sendMessage(localMessage);
  }

  public void setGameEngineType(String paramString)
  {
    Logger.d("setGameEngineType");
    Message localMessage = new Message();
    localMessage.what = 30;
    localMessage.obj = paramString;
    this.mainHandler.sendMessage(localMessage);
  }

  public void showNoticeByScene(eMSG_NOTICETYPE parameMSG_NOTICETYPE, String paramString)
  {
    Logger.d("showNotice");
    Message localMessage = new Message();
    ShowNoticeInfoByScene localShowNoticeInfoByScene = new ShowNoticeInfoByScene();
    localShowNoticeInfoByScene.scene = paramString;
    localShowNoticeInfoByScene.type = parameMSG_NOTICETYPE;
    localMessage.what = 21;
    localMessage.obj = localShowNoticeInfoByScene;
    this.mainHandler.sendMessage(localMessage);
  }

  public void showQMi()
  {
    Logger.d("showQmi");
    Message localMessage = new Message();
    localMessage.what = 28;
    this.mainHandler.sendMessage(localMessage);
  }

  public void verifyLocalAndRefreshWxToken(WxLoginModel paramWxLoginModel)
  {
    Logger.d("getPfKeyReq(int notifyState)");
    Message localMessage = new Message();
    localMessage.what = 32;
    localMessage.obj = paramWxLoginModel;
    this.mainHandler.sendMessage(localMessage);
  }

  public void verifyLocalQQToken(QQLoginModel paramQQLoginModel)
  {
    Logger.d("getPfKeyReq(int notifyState)");
    Message localMessage = new Message();
    localMessage.what = 31;
    localMessage.obj = paramQQLoginModel;
    this.mainHandler.sendMessage(localMessage);
  }

  public void wxExpiredLoginReq(int paramInt)
  {
    Logger.d("wxExpiredLoginReq");
    Message localMessage = new Message();
    localMessage.what = 6;
    localMessage.arg1 = paramInt;
    this.mainHandler.sendMessage(localMessage);
  }

  public void wxFirstLoginReq(String paramString, int paramInt)
  {
    Logger.d("wxFirstLoginReq");
    Message localMessage = new Message();
    localMessage.what = 5;
    WxFirstLoginInfo localWxFirstLoginInfo = new WxFirstLoginInfo();
    localWxFirstLoginInfo.wxCode = paramString;
    localWxFirstLoginInfo.notifyState = paramInt;
    localMessage.obj = localWxFirstLoginInfo;
    this.mainHandler.sendMessage(localMessage);
  }

  public class QQA8Info
  {
    int notifyState = 0;
    String openAuthData = "";
    String openAuthSt = "";

    public QQA8Info()
    {
    }
  }

  public class SendMessageToWechatGameCenterReq
  {
    String msdkExtInfo = "";
    MsgWechatWrapper wechatMsg;

    public SendMessageToWechatGameCenterReq()
    {
    }
  }

  public class SendToQQGameFriend
  {
    int act = 0;
    String friendOpenId = "";
    String gameTag = "";
    String imageUrl = "";
    String msdkExtInfo = "";
    String previewText = "";
    String summary = "";
    String targetUrl = "";
    String title = "";

    public SendToQQGameFriend()
    {
    }
  }

  public class SendToQzone
  {
    String description = "";
    String imageUrl = "";
    String summary = "";
    String targetUrl = "";
    String title = "";

    public SendToQzone()
    {
    }
  }

  public class SendToWXGameFriend
  {
    String description = "";
    String friendOpenid = "";
    String mediaTagName = "";
    String messageExt = "";
    String msdkExtInfo = "";
    String thumbMediaId = "";
    String title = "";

    public SendToWXGameFriend()
    {
    }
  }

  public class ShowNoticeInfoByOpenId
  {
    String openId = "";
    int type = 0;

    public ShowNoticeInfoByOpenId()
    {
    }
  }

  public class ShowNoticeInfoByScene
  {
    String scene = "1";
    eMSG_NOTICETYPE type = eMSG_NOTICETYPE.eMSG_NOTICETYPE_ALERT;

    public ShowNoticeInfoByScene()
    {
    }
  }

  public class WxFirstLoginInfo
  {
    int notifyState = 0;
    String wxCode = "";

    public WxFirstLoginInfo()
    {
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.handle.MsdkThreadManager
 * JD-Core Version:    0.6.0
 */