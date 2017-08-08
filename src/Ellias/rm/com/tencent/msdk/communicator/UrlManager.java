package com.tencent.msdk.communicator;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.tools.HexUtil;
import com.tencent.msdk.tools.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class UrlManager
{
  public static String getUrl(String paramString, int paramInt)
  {
    String str1 = "";
    String str2 = "";
    Object localObject = "";
    try
    {
      String str3 = WeGame.getInstance().getApiDomain();
      String str4 = "" + System.currentTimeMillis();
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      Logger.d("platform: " + WeGame.getInstance().getPlatId());
      if ((paramInt == WeGame.QQPLATID) || (paramInt == WeGame.QQHALL))
      {
        str2 = WeGame.getInstance().qq_appid;
        str1 = WeGame.getInstance().qqAppKey;
      }
      while (true)
      {
        String str5 = str1 + str4;
        Logger.d("Original Sig: " + str5);
        localMessageDigest.update(str5.getBytes());
        String str6 = HexUtil.bytes2HexStr(localMessageDigest.digest()).toLowerCase(Locale.CHINA);
        localObject = str3 + paramString;
        localObject = (String)localObject + "?appid=" + str2;
        localObject = (String)localObject + "&version=" + WGPlatform.WGGetVersion();
        localObject = (String)localObject + "&timestamp=" + str4;
        localObject = (String)localObject + "&sig=" + str6;
        String str7 = (String)localObject + "&encode=1";
        localObject = str7;
        Logger.d("url: " + (String)localObject);
        return localObject;
        if (paramInt != WeGame.WXPLATID)
          continue;
        str2 = WeGame.getInstance().wx_appid;
        str1 = WeGame.getInstance().wxAppKey;
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        localNoSuchAlgorithmException.printStackTrace();
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
        localNumberFormatException.printStackTrace();
    }
  }

  public static String getUrl(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    Object localObject = "";
    try
    {
      String str1 = "" + System.currentTimeMillis();
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      String str2 = paramString4 + str1;
      Logger.d("Original Sig: " + str2);
      localMessageDigest.update(str2.getBytes());
      String str3 = HexUtil.bytes2HexStr(localMessageDigest.digest()).toLowerCase(Locale.CHINA);
      localObject = paramString2 + paramString1;
      localObject = (String)localObject + "?appid=" + paramString3;
      localObject = (String)localObject + "&version=" + paramString5;
      localObject = (String)localObject + "&timestamp=" + str1;
      localObject = (String)localObject + "&sig=" + str3;
      String str4 = (String)localObject + "&encode=1";
      localObject = str4;
      Logger.d("url: " + (String)localObject);
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        localNoSuchAlgorithmException.printStackTrace();
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
        localNumberFormatException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.communicator.UrlManager
 * JD-Core Version:    0.6.0
 */