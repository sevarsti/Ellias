package com.tencent.tmassistantsdk.e;

import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.openSDK.opensdktomsdk.TMOpenSDKAuthorizedInfo;
import com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.c;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends b
{
  protected e a = null;

  private com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.b a(JSONObject paramJSONObject)
  {
    com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.b localb = new com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.b();
    localb.a = paramJSONObject.getInt("hasAuthoried");
    localb.c = paramJSONObject.getInt("errorCode");
    localb.d = paramJSONObject.getString("downloadUrl");
    localb.e = paramJSONObject.getInt("versionCode");
    JSONObject localJSONObject = paramJSONObject.getJSONObject("tipsInfo");
    Object localObject = null;
    if (localJSONObject != null)
    {
      int i = localJSONObject.length();
      localObject = null;
      if (i > 0)
      {
        c localc = new c();
        localc.a = localJSONObject.getString("title");
        localc.b = localJSONObject.getString("content");
        JSONArray localJSONArray = localJSONObject.getJSONArray("actionButton");
        ArrayList localArrayList = new ArrayList();
        if ((localJSONArray != null) && (localJSONArray.length() > 0))
          for (int j = 0; j < localJSONArray.length(); j++)
          {
            com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.a locala = new com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.a();
            locala.a = localJSONArray.getJSONObject(j).getString("textInstalled");
            locala.b = localJSONArray.getJSONObject(j).getString("textUninstalled");
            locala.c = localJSONArray.getJSONObject(j).getInt("jumpType");
            locala.d = localJSONArray.getJSONObject(j).getString("jumpUrl");
            localArrayList.add(locala);
          }
        localc.c = localArrayList;
        localObject = localc;
      }
    }
    localb.b = localObject;
    l.b("GetAuthorizedHttpRequest", "dataInfo :" + localb.toString());
    return localb;
  }

  private String b(TMOpenSDKAuthorizedInfo paramTMOpenSDKAuthorizedInfo)
  {
    String str1 = "" + "?appId=" + URLEncoder.encode(paramTMOpenSDKAuthorizedInfo.appId);
    String str2 = str1 + "&userId=" + URLEncoder.encode(paramTMOpenSDKAuthorizedInfo.userId);
    String str3 = str2 + "&userIdType=" + URLEncoder.encode(paramTMOpenSDKAuthorizedInfo.userIdType);
    String str4 = str3 + "&identityInfo=" + URLEncoder.encode(paramTMOpenSDKAuthorizedInfo.identityInfo);
    String str5 = str4 + "&identityType=" + URLEncoder.encode(paramTMOpenSDKAuthorizedInfo.identityType);
    String str6 = str5 + "&gamePackageName=" + URLEncoder.encode(paramTMOpenSDKAuthorizedInfo.gamePackageName);
    String str7 = str6 + "&gameVersionCode=" + URLEncoder.encode(paramTMOpenSDKAuthorizedInfo.gameVersionCode);
    String str8 = str7 + "&gameChannelId=" + URLEncoder.encode(paramTMOpenSDKAuthorizedInfo.gameChannelId);
    String str9 = str8 + "&actionFlag=" + URLEncoder.encode(paramTMOpenSDKAuthorizedInfo.actionFlag);
    String str10 = str9 + "&verifyType=" + URLEncoder.encode(paramTMOpenSDKAuthorizedInfo.verifyType);
    String str11 = str10 + "&via=" + URLEncoder.encode(paramTMOpenSDKAuthorizedInfo.via);
    return str11 + "&actionType=" + paramTMOpenSDKAuthorizedInfo.actionType;
  }

  public void a(e parame)
  {
    this.a = parame;
  }

  public void a(TMOpenSDKAuthorizedInfo paramTMOpenSDKAuthorizedInfo)
  {
    if (paramTMOpenSDKAuthorizedInfo != null)
      super.a(b(paramTMOpenSDKAuthorizedInfo));
  }

  protected void a(JSONObject paramJSONObject, int paramInt)
  {
    if (this.a == null)
      l.b("GetAuthorizedHttpRequest", "mGetAuthorizedListener is null !");
    while (true)
    {
      return;
      if ((paramInt != 0) || (paramJSONObject == null))
        break;
      try
      {
        com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.b localb = a(paramJSONObject);
        if (localb == null)
          continue;
        this.a.a(localb, 0);
        return;
      }
      catch (JSONException localJSONException)
      {
        this.a.a(null, 704);
        localJSONException.printStackTrace();
        return;
      }
    }
    this.a.a(null, paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.e.a
 * JD-Core Version:    0.6.0
 */