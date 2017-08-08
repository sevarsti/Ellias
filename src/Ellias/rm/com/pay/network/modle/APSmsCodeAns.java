package com.pay.network.modle;

import android.text.TextUtils;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APAppDataInterface;
import java.io.PrintStream;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class APSmsCodeAns extends APBaseHttpAns
{
  private String a = "";
  private String b = "0";

  public APSmsCodeAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
  }

  public String getCount()
  {
    return this.b;
  }

  public String getMobile()
  {
    return this.a;
  }

  public void onErrorAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onFinishAns(byte[] paramArrayOfByte, APBaseHttpReq paramAPBaseHttpReq)
  {
    super.onFinishAns(paramArrayOfByte, paramAPBaseHttpReq);
    String str1 = new String(paramArrayOfByte);
    System.out.println("resultData=" + str1);
    JSONObject localJSONObject;
    try
    {
      localJSONObject = new JSONObject(str1);
      this.resultCode = Integer.parseInt(localJSONObject.getString("ret").toString());
      if (this.resultCode == 0)
      {
        this.a = localJSONObject.getString("mobile").toString();
        this.b = localJSONObject.getString("remain").toString();
        if (!localJSONObject.has("need_change_key"))
          return;
        if (localJSONObject.getInt("need_change_key") == 1)
        {
          APAppDataInterface.singleton().setChangeKey(true);
          return;
        }
        APAppDataInterface.singleton().setChangeKey(false);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      return;
    }
    if (localJSONObject.has("msg"))
      this.resultMsg = localJSONObject.getString("msg").toString();
    if (localJSONObject.has("err_code"))
    {
      String str2 = localJSONObject.getString("err_code").toString();
      if (!TextUtils.isEmpty(str2))
        this.resultMsg = ("系统繁忙,请稍后再试\n(" + str2 + ")");
    }
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

  public void setCount(String paramString)
  {
    this.b = paramString;
  }

  public void setMobile(String paramString)
  {
    this.a = paramString;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APSmsCodeAns
 * JD-Core Version:    0.6.0
 */