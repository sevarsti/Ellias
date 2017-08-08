package com.tencent.msdk.remote.api;

import com.tencent.msdk.tools.Logger;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class QQInfoFormatter
{
  public static Vector<PersonInfo> formatFriends(JSONArray paramJSONArray)
  {
    Vector localVector = new Vector();
    if ((paramJSONArray == null) || (paramJSONArray.length() == 0));
    while (true)
    {
      return localVector;
      int i = 0;
      try
      {
        while (i < paramJSONArray.length())
        {
          JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
          PersonInfo localPersonInfo = new PersonInfo(localJSONObject.getString("nickName"), localJSONObject.getString("openid"), localJSONObject.getString("gender"), localJSONObject.getString("figureurl_qq") + "40", localJSONObject.getString("figureurl_qq") + "40", localJSONObject.getString("figureurl_qq") + "100", "", "");
          Logger.d(localPersonInfo);
          localVector.add(localPersonInfo);
          i++;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    return localVector;
  }

  public static PersonInfo formatMyInfo(String paramString, SafeJSONObject paramSafeJSONObject)
  {
    try
    {
      PersonInfo localPersonInfo = new PersonInfo(paramSafeJSONObject.getString("nickName"), "", paramSafeJSONObject.getString("gender"), paramSafeJSONObject.getString("picture40"), paramSafeJSONObject.getString("picture40"), paramSafeJSONObject.getString("picture100"), "", "", paramSafeJSONObject.getString("language"), paramSafeJSONObject.getString("country"));
      return localPersonInfo;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return new PersonInfo();
  }

  public static Vector<PersonInfo> formatNearby(JSONArray paramJSONArray)
  {
    Vector localVector = new Vector();
    if ((paramJSONArray == null) || (paramJSONArray.length() == 0));
    while (true)
    {
      return localVector;
      int i = 0;
      try
      {
        while (true)
        {
          int j = paramJSONArray.length();
          if (i >= j)
            break;
          JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
          String str1 = localJSONObject.getString("nickName");
          String str2 = localJSONObject.getString("openid");
          String str3;
          label79: String str4;
          String str5;
          String str6;
          float f;
          if ("1".equals(localJSONObject.getString("gender")))
          {
            str3 = "男";
            str4 = localJSONObject.getString("figureurl_qq") + "/40";
            str5 = localJSONObject.getString("figureurl_qq") + "/40";
            str6 = localJSONObject.getString("figureurl_qq") + "/100";
            double d = localJSONObject.getDouble("distance");
            f = (float)d;
          }
          try
          {
            int k = Integer.parseInt(localJSONObject.getString("is_friend"));
            if (k == 1);
            for (bool = true; ; bool = false)
            {
              PersonInfo localPersonInfo = new PersonInfo(str1, str2, str3, str4, str5, str6, "", "", f, bool, localJSONObject.getLong("timestamp"));
              Logger.d(localPersonInfo);
              localVector.add(localPersonInfo);
              i++;
              break;
              str3 = "女";
              break label79;
            }
          }
          catch (Exception localException)
          {
            while (true)
              boolean bool = false;
          }
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    return localVector;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.QQInfoFormatter
 * JD-Core Version:    0.6.0
 */