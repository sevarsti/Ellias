package com.tencent.qqgamemi.protocol;

import android.os.Handler;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.protocol.ProtocolManager;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.protocol.business.AutoDownloadGameJoyRequest;
import com.tencent.qqgamemi.protocol.business.FeedbackRequest;
import com.tencent.qqgamemi.protocol.business.GameActionRequest;
import com.tencent.qqgamemi.protocol.business.GameItemRequest;
import com.tencent.qqgamemi.protocol.business.GetSybAcessTokenRequest;
import com.tencent.qqgamemi.protocol.business.PluginListRequest;
import com.tencent.qqgamemi.protocol.business.ReportRequest;
import com.tencent.qqgamemi.protocol.business.StartInfoRequest;
import com.tencent.qqgamemi.protocol.business.UserInfoRequest;
import java.util.List;

@PluginApi(a=6)
public class MsgHandle
{
  private static final String a = "MsgHandle";

  public static int a(Handler paramHandler, int paramInt)
  {
    UserInfoRequest localUserInfoRequest = new UserInfoRequest(paramHandler, paramInt);
    return ProtocolManager.b().a(localUserInfoRequest, QMiJceCommonData.b());
  }

  public static int a(Handler paramHandler, int paramInt1, int paramInt2)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt2);
    StartInfoRequest localStartInfoRequest = new StartInfoRequest(paramHandler, paramInt1, arrayOfObject);
    return ProtocolManager.b().a(localStartInfoRequest, QMiJceCommonData.b());
  }

  public static int a(Handler paramHandler, int paramInt1, int paramInt2, int paramInt3, String paramString, List paramList)
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Integer.valueOf(paramInt2);
    arrayOfObject[1] = Integer.valueOf(paramInt3);
    arrayOfObject[2] = paramString;
    arrayOfObject[3] = paramList;
    PluginListRequest localPluginListRequest = new PluginListRequest(paramHandler, paramInt1, arrayOfObject);
    return ProtocolManager.b().a(localPluginListRequest, QMiJceCommonData.b());
  }

  public static int a(Handler paramHandler, int paramInt, String paramString)
  {
    AutoDownloadGameJoyRequest localAutoDownloadGameJoyRequest = new AutoDownloadGameJoyRequest(paramHandler, paramInt, paramString);
    return ProtocolManager.b().a(localAutoDownloadGameJoyRequest, QMiJceCommonData.b());
  }

  public static int a(Handler paramHandler, int paramInt1, String paramString, int paramInt2)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = Integer.valueOf(paramInt2);
    GameActionRequest localGameActionRequest = new GameActionRequest(paramHandler, paramInt1, arrayOfObject);
    return ProtocolManager.b().a(localGameActionRequest, QMiJceCommonData.b());
  }

  public static int a(Handler paramHandler, int paramInt, String paramString1, String paramString2, String paramString3)
  {
    FeedbackRequest localFeedbackRequest = new FeedbackRequest(paramHandler, paramInt, paramString1, paramString2, paramString3);
    return ProtocolManager.b().a(localFeedbackRequest, QMiJceCommonData.b());
  }

  public static int a(Handler paramHandler, int paramInt1, String paramString1, String paramString2, byte[] paramArrayOfByte, int paramInt2)
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = paramString2;
    arrayOfObject[2] = paramArrayOfByte;
    arrayOfObject[3] = Integer.valueOf(paramInt2);
    GetSybAcessTokenRequest localGetSybAcessTokenRequest = new GetSybAcessTokenRequest(paramHandler, paramInt1, arrayOfObject);
    return ProtocolManager.b().a(localGetSybAcessTokenRequest, QMiJceCommonData.b());
  }

  public static int a(Handler paramHandler, int paramInt, List paramList)
  {
    GameItemRequest localGameItemRequest = new GameItemRequest(paramHandler, paramInt, new Object[] { paramList });
    return ProtocolManager.b().a(localGameItemRequest, QMiJceCommonData.b());
  }

  public static int b(Handler paramHandler, int paramInt, List paramList)
  {
    ReportRequest localReportRequest = new ReportRequest(paramHandler, paramInt, new Object[] { paramList });
    return ProtocolManager.b().a(localReportRequest, QMiJceCommonData.b());
  }

  @PluginApi(a=6)
  public static int sendPluginProtocol(QMiProtocolRequest paramQMiProtocolRequest)
  {
    LogUtil.d("MsgHandle", "send request" + paramQMiProtocolRequest.d());
    return ProtocolManager.b().a(paramQMiProtocolRequest, QMiJceCommonData.b());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.MsgHandle
 * JD-Core Version:    0.6.0
 */