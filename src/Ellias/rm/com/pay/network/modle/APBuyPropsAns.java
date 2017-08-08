package com.pay.network.modle;

import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APErrorCode;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class APBuyPropsAns extends APBaseHttpAns
{
  private String a = "";
  private String b = "";

  public APBuyPropsAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
  }

  public String getBalance()
  {
    return this.b;
  }

  public String getBillNo()
  {
    return this.a;
  }

  public void onErrorAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onFinishAns(byte[] paramArrayOfByte, APBaseHttpReq paramAPBaseHttpReq)
  {
    super.onFinishAns(paramArrayOfByte, paramAPBaseHttpReq);
    if (paramArrayOfByte == null)
    {
      this.resultCode = -1;
      this.resultMsg = ("系统繁忙,请稍后再试\n" + APErrorCode.getErrorCode(1004));
      return;
    }
    String str = new String(paramArrayOfByte);
    JSONObject localJSONObject;
    try
    {
      localJSONObject = new JSONObject(str);
      this.resultCode = Integer.parseInt(localJSONObject.getString("ret").toString());
      if (this.resultCode == 0)
      {
        this.a = localJSONObject.getString("billno");
        this.b = localJSONObject.getString("balance");
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      return;
    }
    this.resultMsg = localJSONObject.getString("msg").toString();
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
 * Qualified Name:     com.pay.network.modle.APBuyPropsAns
 * JD-Core Version:    0.6.0
 */