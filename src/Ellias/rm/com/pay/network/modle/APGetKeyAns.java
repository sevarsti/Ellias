package com.pay.network.modle;

import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.data.userInfo.APUserInfo;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APDataInterface;
import com.pay.tool.APSecretKeyManager;
import com.pay.tool.APToolAES;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class APGetKeyAns extends APBaseHttpAns
{
  private String a = "";
  private int b;

  public APGetKeyAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
  }

  private static String a(String paramString1, String paramString2)
  {
    String[] arrayOfString1 = paramString1.split("&");
    HashMap localHashMap = new HashMap();
    int i = 0;
    if (i >= arrayOfString1.length)
      return (String)localHashMap.get(paramString2);
    String[] arrayOfString2 = arrayOfString1[i].split("=");
    if (arrayOfString2.length > 1)
      localHashMap.put(arrayOfString2[0], arrayOfString2[1]);
    while (true)
    {
      i++;
      break;
      localHashMap.put(arrayOfString2[0], "");
    }
  }

  private void a(JSONObject paramJSONObject)
  {
    try
    {
      JSONObject localJSONObject = paramJSONObject.getJSONObject("user_info");
      String str1 = localJSONObject.getString("uin");
      String str2 = localJSONObject.getString("uin_type");
      int i = localJSONObject.getInt("uin_len");
      int j = localJSONObject.getInt("codeindex");
      if ((!str1.equals("")) && (j < this.AesEncodeKey.length))
      {
        String str3 = APToolAES.doDecode(str1, this.AesEncodeKey[j]).substring(0, i);
        APDataInterface.singleton().getUserInfo().uinFromSvr = str3;
      }
      APDataInterface.singleton().getUserInfo().uinTypeFromSvr = str2;
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }

  public String getKey()
  {
    return this.a;
  }

  public int getKeyType()
  {
    return this.b;
  }

  public void onErrorAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onFinishAns(byte[] paramArrayOfByte, APBaseHttpReq paramAPBaseHttpReq)
  {
    super.onFinishAns(paramArrayOfByte, paramAPBaseHttpReq);
    String str1 = new String(paramArrayOfByte);
    APGetKeyReq localAPGetKeyReq = (APGetKeyReq)paramAPBaseHttpReq;
    APLog.i("APGetKeyAns", "resultData=" + str1);
    JSONObject localJSONObject;
    while (true)
    {
      String str3;
      String str4;
      try
      {
        localJSONObject = new JSONObject(str1);
        this.resultCode = Integer.parseInt(localJSONObject.getString("ret").toString());
        if (this.resultCode != 0)
          break;
        str3 = localJSONObject.getString("key_info");
        str4 = localJSONObject.getString("key_info_len");
        this.b = localAPGetKeyReq.curReqType;
        if (!localJSONObject.has("need_change_key"))
          continue;
        if (localJSONObject.getInt("need_change_key") != 1)
          continue;
        APAppDataInterface.singleton().setChangeKey(true);
        if (this.b == 0)
        {
          String str9 = APToolAES.doDecode(str3, APAppDataInterface.singleton().getBaseKey()).substring(0, Integer.valueOf(str4).intValue());
          str8 = a(str9, "key");
          str6 = a(str9, "cryptkey");
          str7 = a(str9, "cryptkeytime");
          if (localAPGetKeyReq.changeKeyReason != 1)
            break label348;
          APSecretKeyManager.getInstance(AndroidPay.singleton().applicationContext).saveSecretKey(APDataInterface.singleton().getUserInfo().payId, str8);
          APSecretKeyManager.getInstance(AndroidPay.singleton().applicationContext).saveCryptKey(APDataInterface.singleton().getUserInfo().payId, str6);
          APSecretKeyManager.getInstance(AndroidPay.singleton().applicationContext).saveCryptKeyTime(APDataInterface.singleton().getUserInfo().payId, str7);
          a(localJSONObject);
          return;
          APAppDataInterface.singleton().setChangeKey(false);
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        this.resultMsg = "系统繁忙,请稍后再试10-10-01";
        localJSONException.printStackTrace();
        return;
      }
      String str5 = APToolAES.doDecode(str3, APAppDataInterface.singleton().getSecretKey()).substring(0, Integer.valueOf(str4).intValue());
      String str6 = a(str5, "key");
      String str7 = a(str5, "cryptkeytime");
      String str8 = null;
      continue;
      label348: APAppDataInterface.singleton().setSecretKey(str8);
      APAppDataInterface.singleton().setCryptKey(str6);
      APAppDataInterface.singleton().setCryptKeyTime(str7);
      APSecretKeyManager.getInstance(AndroidPay.singleton().applicationContext).saveSecretKey(APDataInterface.singleton().getUserInfo().openId, str8);
      APSecretKeyManager.getInstance(AndroidPay.singleton().applicationContext).saveCryptKey(APDataInterface.singleton().getUserInfo().openId, str6);
      APSecretKeyManager.getInstance(AndroidPay.singleton().applicationContext).saveCryptKeyTime(APDataInterface.singleton().getUserInfo().openId, str7);
    }
    this.resultMsg = localJSONObject.getString("msg");
    String str2 = localJSONObject.getString("err_code").toString();
    if (!str2.equals(""))
      this.resultMsg = ("系统繁忙,请稍后再试\n(" + str2 + ")");
  }

  public void onReceiveAns(byte[] paramArrayOfByte, int paramInt, long paramLong, APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onStartAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onStopAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APGetKeyAns
 * JD-Core Version:    0.6.0
 */