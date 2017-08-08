package com.tencent.msdk.remote.api;

import com.tencent.msdk.tools.Logger;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class WxInfoFormatter
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
        if (i >= paramJSONArray.length())
          continue;
        JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
        String str1 = localJSONObject.getString("nickName");
        String str2 = localJSONObject.getString("openid");
        if ("2".equals(localJSONObject.getString("sex")));
        for (String str3 = "女"; ; str3 = "男")
        {
          String str4 = localJSONObject.getString("picture") + "/46";
          String str5 = localJSONObject.getString("picture") + "/96";
          String str6 = localJSONObject.getString("picture") + "/132";
          String str7 = localJSONObject.getString("provice");
          String str8 = localJSONObject.getString("city");
          String str9 = "";
          if (localJSONObject.has("language"))
            str9 = localJSONObject.getString("language");
          String str10 = "";
          if (localJSONObject.has("country"))
            str10 = localJSONObject.getString("country");
          PersonInfo localPersonInfo = new PersonInfo(str1, str2, str3, str4, str5, str6, str7, str8, str9, str10);
          Logger.d(localPersonInfo);
          localVector.add(localPersonInfo);
          i++;
          break;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    return localVector;
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
          if ("2".equals(localJSONObject.getString("gender")))
          {
            str3 = "女";
            str4 = localJSONObject.getString("picture") + "/46";
            str5 = localJSONObject.getString("picture") + "/96";
            str6 = localJSONObject.getString("picture") + "/132";
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
              str3 = "男";
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
 * Qualified Name:     com.tencent.msdk.remote.api.WxInfoFormatter
 * JD-Core Version:    0.6.0
 */