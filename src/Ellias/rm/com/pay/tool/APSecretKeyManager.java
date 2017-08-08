package com.pay.tool;

import android.content.Context;
import android.text.TextUtils;
import com.pay.data.userInfo.APUserInfo;

public class APSecretKeyManager
{
  private static APSecretKeyManager b = null;
  private Context a;

  private static String a()
  {
    String str1 = "caUdsBbJ1oOxMbPy".substring(0, 4);
    String str2 = "caUdsBbJ1oOxMbPy".substring(4, 8);
    String str3 = "caUdsBbJ1oOxMbPy".substring(8, 12);
    String str4 = "caUdsBbJ1oOxMbPy".substring(12, 16);
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(str3);
    localStringBuffer.append(str2);
    localStringBuffer.append(str1);
    localStringBuffer.append(str4);
    return localStringBuffer.toString();
  }

  public static APSecretKeyManager getInstance(Context paramContext)
  {
    if (b == null)
    {
      APSecretKeyManager localAPSecretKeyManager = new APSecretKeyManager();
      b = localAPSecretKeyManager;
      localAPSecretKeyManager.a = paramContext;
      String str = b.readSecretKey(APDataInterface.singleton().getUserInfo().openId);
      if (str == null)
        str = "";
      APAppDataInterface.singleton().setSecretKey(str);
    }
    return b;
  }

  public static void release()
  {
    b = null;
  }

  public String readCryptKey(String paramString)
  {
    String str1 = APAppDataInterface.singleton().getSecretKey();
    APDataStorage localAPDataStorage = new APDataStorage();
    String str2 = "";
    String str3 = localAPDataStorage.getData(this.a, "TencentUnipay", "1.3.7b_" + APAppDataInterface.singleton().getOfferid() + "_" + paramString + "_CryptEncodeKey");
    if (!str3.equals(""))
      str2 = APToolAES.doDecode(str3, str1);
    if (str2 == null)
      str2 = "";
    return str2;
  }

  public String readCryptKeyTime(String paramString)
  {
    return new APDataStorage().getData(this.a, "TencentUnipay", "1.3.7b_" + APAppDataInterface.singleton().getOfferid() + "_" + paramString + "_CryptEncodeKeyTime");
  }

  public String readSecretKey(String paramString)
  {
    String str1 = a();
    APDataStorage localAPDataStorage = new APDataStorage();
    String str2 = "";
    String str3 = localAPDataStorage.getData(this.a, "TencentUnipay", "1.3.7b_" + APAppDataInterface.singleton().getOfferid() + "_" + paramString + "_SecretEncodeKey");
    if (!str3.equals(""))
      str2 = APToolAES.doDecode(str3, str1);
    if (str2 == null)
      str2 = "";
    return str2;
  }

  public void saveCryptKey(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString2))
      return;
    String str1 = APAppDataInterface.singleton().getSecretKey();
    APDataStorage localAPDataStorage = new APDataStorage();
    String str2 = APToolAES.doEncode(paramString2, str1);
    localAPDataStorage.storeData(this.a, "TencentUnipay", "1.3.7b_" + APAppDataInterface.singleton().getOfferid() + "_" + paramString1 + "_CryptEncodeKey", str2);
  }

  public void saveCryptKeyTime(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString2))
      return;
    new APDataStorage().storeData(this.a, "TencentUnipay", "1.3.7b_" + APAppDataInterface.singleton().getOfferid() + "_" + paramString1 + "_CryptEncodeKeyTime", paramString2);
  }

  public void saveSecretKey(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString2))
      return;
    String str1 = a();
    APDataStorage localAPDataStorage = new APDataStorage();
    String str2 = APToolAES.doEncode(paramString2, str1);
    localAPDataStorage.storeData(this.a, "TencentUnipay", "1.3.7b_" + APAppDataInterface.singleton().getOfferid() + "_" + paramString1 + "_SecretEncodeKey", str2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APSecretKeyManager
 * JD-Core Version:    0.6.0
 */