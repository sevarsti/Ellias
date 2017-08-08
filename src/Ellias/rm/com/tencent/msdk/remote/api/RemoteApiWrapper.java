package com.tencent.msdk.remote.api;

import com.tencent.msdk.lbs.LocationInfo;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import com.tencent.msdk.weixin.MsgWechatWrapper;

public class RemoteApiWrapper
{
  public static boolean ClearLocation()
  {
    new CleanLocation().send();
    return true;
  }

  public static void Feedback(String paramString)
  {
    new Feedback(paramString).send();
  }

  public static void QueryNearbyPlayer(LocationInfo paramLocationInfo)
  {
    if (paramLocationInfo == null)
    {
      Logger.d("null LocationInfo");
      return;
    }
    new QueryNearbyPlayer(paramLocationInfo).send();
  }

  public static void SendMessageToWechatGameCenter(MsgWechatWrapper paramMsgWechatWrapper, String paramString)
  {
    if (paramMsgWechatWrapper == null)
    {
      Logger.d("null MsgWechatWrapper");
      return;
    }
    new SendMessageToWechatGameCenter(paramMsgWechatWrapper, paramString).send();
  }

  public static boolean WGQueryQQGameFriendsInfo()
  {
    new QueryQQFriends().send();
    return true;
  }

  public static boolean WGQueryQQUserInfo()
  {
    new QueryQQUserInfo().send();
    return true;
  }

  public static boolean WGQueryWXGameFriendsInfo()
  {
    new QueryWXFriends().send();
    return true;
  }

  public static boolean WGQueryWXUserInfo()
  {
    new QueryWXUserInfo().send();
    return true;
  }

  public static boolean WGSendToQQGameFriend(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8)
  {
    if (T.ckNonEmpty(new String[] { paramString1, paramString2, paramString3, paramString4, paramString5 }))
    {
      Logger.e("friendOpenId, title, summary, targetUrl, imageUrl CAN NOT BE EMPTY");
      return false;
    }
    new ShareToQQ(paramInt, paramString1, paramString2, paramString3, paramString6, paramString4, paramString5, paramString7, paramString8).send();
    return true;
  }

  public static boolean WGSendToWXGameFriend(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    if (T.ckNonEmpty(new String[] { paramString1, paramString2, paramString3 }))
    {
      Logger.e("fopenid, title, description  CAN NOT BE EMPTY");
      return false;
    }
    new ShareToWX(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7).send();
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.RemoteApiWrapper
 * JD-Core Version:    0.6.0
 */