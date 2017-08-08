package com.pay.network.modle;

import com.pay.common.tool.APLog;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class APhfPayAns extends APBaseHttpAns
{
  private String a = "delay";

  public APhfPayAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
  }

  public String getHfStatus()
  {
    return this.a;
  }

  public void onErrorAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onFinishAns(byte[] paramArrayOfByte, APBaseHttpReq paramAPBaseHttpReq)
  {
    super.onFinishAns(paramArrayOfByte, paramAPBaseHttpReq);
    String str = new String(paramArrayOfByte);
    APLog.i("APhfAns", "resultData=" + str);
    try
    {
      JSONObject localJSONObject = new JSONObject(str);
      this.resultCode = Integer.parseInt(localJSONObject.getString("ret").toString());
      if (this.resultCode != 0)
        break label152;
      switch (localJSONObject.getInt("state"))
      {
      default:
        this.a = "failed";
        return;
      case 3:
      case 5:
        this.a = "failed";
        return;
      case 1:
      case 2:
      case 6:
      case 4:
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      return;
    }
    this.a = "delay";
    return;
    this.a = "succeed";
    return;
    label152: this.a = "delay";
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
 * Qualified Name:     com.pay.network.modle.APhfPayAns
 * JD-Core Version:    0.6.0
 */