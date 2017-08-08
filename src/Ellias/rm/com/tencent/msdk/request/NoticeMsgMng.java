package com.tencent.msdk.request;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.communicator.HttpRequestManager;
import com.tencent.msdk.communicator.IHttpRequestListener;
import com.tencent.msdk.communicator.UrlManager;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.db.AppDBModel;
import com.tencent.msdk.db.NoticeDBModel;
import com.tencent.msdk.notice.NoticeInfo;
import com.tencent.msdk.notice.NoticeMsgFromNet;
import com.tencent.msdk.notice.NoticePic;
import com.tencent.msdk.notice.NoticeRequestPara;
import com.tencent.msdk.notice.eMSG_CONTENTTYPE;
import com.tencent.msdk.stat.BeaconHelper;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NoticeMsgMng
  implements IHttpRequestListener
{
  private long refreshStartTime = 0L;

  private void addOneNotice(NoticeInfo paramNoticeInfo)
  {
    if (eMSG_CONTENTTYPE.eMSG_CONTENTTYPE_IMAGE == paramNoticeInfo.mNoticeContentType)
      NoticePic.saveNoticePics(paramNoticeInfo);
    new NoticeDBModel().save(paramNoticeInfo);
  }

  private void deleteNoticeByMsgList(String paramString)
  {
    if (T.ckIsEmpty(paramString))
    {
      Logger.d("msgList is null");
      return;
    }
    int i = new NoticeDBModel().deleteNoticeInDBByMsgList(paramString);
    Logger.d("Num of notice has been deleted：" + i);
  }

  private void reportEventToBeacon(boolean paramBoolean1, int paramInt, boolean paramBoolean2)
  {
    if (paramBoolean1)
    {
      BeaconHelper.reportMSDKEvent("getNotice", this.refreshStartTime, true, null, true);
      return;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("param_FailCode", "" + paramInt);
    StringBuilder localStringBuilder = new StringBuilder().append("");
    if (paramBoolean2);
    for (int i = 1; ; i = 0)
    {
      localHashMap.put("msdk_logic_error", i);
      BeaconHelper.reportMSDKEvent("getNotice", this.refreshStartTime, false, localHashMap, true);
      return;
    }
  }

  private void saveNotice(ArrayList<NoticeInfo> paramArrayList)
  {
    if ((paramArrayList == null) || (paramArrayList.isEmpty()));
    while (true)
    {
      return;
      Iterator localIterator = paramArrayList.iterator();
      while (localIterator.hasNext())
        addOneNotice((NoticeInfo)localIterator.next());
    }
  }

  public void decodeNoticeJson(JSONObject paramJSONObject, NoticeMsgFromNet paramNoticeMsgFromNet)
  {
    if ((paramJSONObject == null) || (paramNoticeMsgFromNet == null))
      Logger.w("noticeJson or noticeMsg is null!");
    label82: label471: 
    while (true)
    {
      return;
      try
      {
        if (!paramJSONObject.has("ret"))
        {
          Logger.e("ret lost in the response!");
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        Logger.e("decodeNoticeJson JSONException");
        localJSONException.printStackTrace();
        return;
      }
      String str1 = "";
      String str2;
      if (paramJSONObject.has("sendTime"))
      {
        str1 = paramJSONObject.getString("sendTime");
        str2 = "";
        if (!paramJSONObject.has("appid"))
          break label253;
        str2 = paramJSONObject.getString("appid");
        Logger.d("requestAppid:" + str2 + ";updateTime:" + str1);
        if ((T.ckIsEmpty(str2)) || (T.ckIsEmpty(str1)))
          break label261;
        new AppDBModel().setUpdateTimeByAppId(str2, str1);
      }
      int j;
      while (true)
      {
        if (!paramJSONObject.has("invalidMsgid"))
          break label447;
        JSONArray localJSONArray2 = paramJSONObject.getJSONArray("invalidMsgid");
        j = 0;
        if (j >= localJSONArray2.length())
          break label269;
        JSONObject localJSONObject2 = localJSONArray2.getJSONObject(j);
        if (!localJSONObject2.has("invalidMsgid"))
          break label461;
        String str3 = localJSONObject2.getString("invalidMsgid");
        NoticePic.deleteNoticePicByNoticeId(Integer.parseInt(str3));
        paramNoticeMsgFromNet.invalidMsgIdList = (paramNoticeMsgFromNet.invalidMsgIdList + str3 + ", ");
        break label461;
        Logger.e("mUpdateTime lost in the response!");
        break;
        label253: Logger.e("appid lost in the response!");
        break label82;
        Logger.e("lastUpdateTime update failed!");
      }
      Logger.d("invalidMsgIdList:" + paramNoticeMsgFromNet.invalidMsgIdList);
      JSONArray localJSONArray1;
      if (paramJSONObject.has("list"))
        localJSONArray1 = paramJSONObject.getJSONArray("list");
      for (int i = 0; ; i++)
      {
        if (i >= localJSONArray1.length())
          break label471;
        JSONObject localJSONObject1 = localJSONArray1.getJSONObject(i);
        if (!localJSONObject1.has("noticeType"))
          continue;
        NoticeInfo localNoticeInfo = new NoticeInfo();
        localNoticeInfo.getBaseInfoFromJson(localJSONObject1);
        if (T.ckIsEmpty(localNoticeInfo.mAppId))
        {
          localNoticeInfo.mAppId = str2;
          Logger.w("notice do not have its own appid");
        }
        paramNoticeMsgFromNet.noticeList.add(localNoticeInfo);
        Logger.d("add a notice to list. msg_id:" + localNoticeInfo.mNoticeId + ",msg_type: " + localNoticeInfo.mNoticeType + ",contentType:" + localNoticeInfo.mNoticeContentType);
        continue;
        Logger.d("notice response INVALID_LIST is empty");
        break label294;
        Logger.d("notice response NOTICE_MSG is empty");
        return;
        j++;
        break;
      }
    }
  }

  public void delRedundancyNotice()
  {
    String str = String.valueOf(System.currentTimeMillis() / 1000L);
    if (!T.ckIsEmpty(str))
    {
      deleteNoticeByMsgList(new NoticeDBModel().getRedundancyNoticeListByTime(str));
      return;
    }
    Logger.w("currentTime is null");
  }

  public String getAppIdByJson(JSONObject paramJSONObject)
  {
    try
    {
      String str = paramJSONObject.getString("appid");
      return str;
    }
    catch (JSONException localJSONException)
    {
      Logger.e("getAppIdByJson JSONException");
      localJSONException.printStackTrace();
    }
    return "";
  }

  public String getLastUpdateTime(String paramString)
  {
    return new AppDBModel().getUpdateTimeByAppId(paramString);
  }

  public JSONObject getNoticeJsonPacket(NoticeRequestPara paramNoticeRequestPara, String paramString)
  {
    JSONObject localJSONObject1 = new JSONObject();
    if (paramNoticeRequestPara == null)
      return localJSONObject1;
    JSONObject localJSONObject2 = new JSONObject();
    try
    {
      localJSONObject2.put("appid", paramNoticeRequestPara.mAppId);
      localJSONObject2.put("matid", paramNoticeRequestPara.mMatid);
      localJSONObject2.put("openid", paramNoticeRequestPara.mOpenId);
      localJSONObject2.put("os", paramNoticeRequestPara.mOs);
      localJSONObject2.put("osVersion", paramNoticeRequestPara.mOsVersion);
      localJSONObject2.put("tradeMark", paramNoticeRequestPara.mTradeMark);
      localJSONObject2.put("resolution", paramNoticeRequestPara.mResolution);
      localJSONObject2.put("apn", paramNoticeRequestPara.mApn);
      localJSONObject2.put("msdkVersion", paramNoticeRequestPara.mMsdkVersion);
      localJSONObject2.put("protocolVer", paramNoticeRequestPara.mProtocolVer);
      localJSONObject2.put("lastTime", paramString);
      localJSONObject2.put("noticeVersion", paramNoticeRequestPara.mNoticeVersion);
      localJSONObject2.put("screenDir", paramNoticeRequestPara.mScreenDir);
      localJSONObject2.put("screenDpi", paramNoticeRequestPara.mScreenDpi);
      return localJSONObject2;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }

  public void getNoticeReq(NoticeRequestPara paramNoticeRequestPara)
  {
    this.refreshStartTime = System.currentTimeMillis();
    String str1 = getLastUpdateTime(paramNoticeRequestPara.mAppId);
    if (T.ckIsEmpty(str1))
      str1 = "0";
    JSONObject localJSONObject = getNoticeJsonPacket(paramNoticeRequestPara, str1);
    if (paramNoticeRequestPara.mAppId.indexOf("|") > 0);
    for (String str2 = (String)paramNoticeRequestPara.mAppId.subSequence(0, paramNoticeRequestPara.mAppId.indexOf("|")); ; str2 = paramNoticeRequestPara.mAppId)
    {
      String str3 = paramNoticeRequestPara.mAppKey;
      String str4 = UrlManager.getUrl("/notice/gather_data/", ConfigManager.getApiDomain(WeGame.getInstance().getActivity()), str2, str3, paramNoticeRequestPara.mMsdkVersion);
      new HttpRequestManager(this).postTextAsync(str4, localJSONObject.toString(), 3000);
      return;
    }
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    Logger.d("onFailure" + paramString);
    reportEventToBeacon(false, paramInt1, false);
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      Logger.d("onSuccess" + paramString);
      if (paramString == null)
      {
        Logger.d("getNotice onSuccess,but content is null!");
        reportEventToBeacon(false, 1002, false);
        return;
      }
      JSONObject localJSONObject = new JSONObject(paramString);
      if ((localJSONObject != null) && (localJSONObject.has("sendTime")))
        processNoticeMsgRsp(localJSONObject);
      reportEventToBeacon(true, 0, false);
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      reportEventToBeacon(false, 1001, false);
    }
  }

  public void processNoticeMsgRsp(JSONObject paramJSONObject)
  {
    NoticeMsgFromNet localNoticeMsgFromNet = new NoticeMsgFromNet();
    decodeNoticeJson(paramJSONObject, localNoticeMsgFromNet);
    saveNotice(localNoticeMsgFromNet.noticeList);
    delRedundancyNotice();
    deleteNoticeByMsgList(localNoticeMsgFromNet.invalidMsgIdList);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.request.NoticeMsgMng
 * JD-Core Version:    0.6.0
 */