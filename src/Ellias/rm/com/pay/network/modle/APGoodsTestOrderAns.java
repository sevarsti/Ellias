package com.pay.network.modle;

import com.pay.common.tool.APLog;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class APGoodsTestOrderAns extends APBaseHttpAns
{
  private String a = "";
  private String b = "";

  public APGoodsTestOrderAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
  }

  public String getToken()
  {
    return this.b;
  }

  public String getUrl()
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
    APLog.i("APGoodsTestOrderAns", "resultData=" + str1);
    try
    {
      JSONObject localJSONObject = new JSONObject(str1);
      this.resultCode = Integer.parseInt(localJSONObject.getString("ret").toString());
      if (this.resultCode == 0)
      {
        this.a = localJSONObject.getString("url_params");
        this.b = localJSONObject.getString("token");
        return;
      }
      this.resultMsg = localJSONObject.getString("msg").toString();
      String str2 = localJSONObject.getString("err_code").toString();
      if (!str2.equals(""))
      {
        this.resultMsg = ("系统繁忙,请稍后再试\n(" + str2 + ")");
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
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
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.network.modle.APGoodsTestOrderAns
 * JD-Core Version:    0.6.0
 */