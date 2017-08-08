package com.tencent.msdk.request;

import com.tencent.msdk.notice.NoticeRequestPara;
import org.json.JSONException;
import org.json.JSONObject;

public class NoticeRequest
{
  public JSONObject getRequestJson(NoticeRequestPara paramNoticeRequestPara, String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("appid", paramNoticeRequestPara.mAppId);
      localJSONObject.put("matid", paramNoticeRequestPara.mMatid);
      localJSONObject.put("openid", paramNoticeRequestPara.mOpenId);
      localJSONObject.put("os", paramNoticeRequestPara.mOs);
      localJSONObject.put("osVersion", paramNoticeRequestPara.mOsVersion);
      localJSONObject.put("tradeMark", paramNoticeRequestPara.mTradeMark);
      localJSONObject.put("resolution", paramNoticeRequestPara.mResolution);
      localJSONObject.put("apn", paramNoticeRequestPara.mApn);
      localJSONObject.put("msdkVersion", paramNoticeRequestPara.mMsdkVersion);
      localJSONObject.put("protocolVer", paramNoticeRequestPara.mProtocolVer);
      localJSONObject.put("lastTime", paramString);
      localJSONObject.put("noticeVersion", paramNoticeRequestPara.mNoticeVersion);
      localJSONObject.put("screenDir", paramNoticeRequestPara.mScreenDir);
      localJSONObject.put("screenDpi", paramNoticeRequestPara.mScreenDpi);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localJSONObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.request.NoticeRequest
 * JD-Core Version:    0.6.0
 */