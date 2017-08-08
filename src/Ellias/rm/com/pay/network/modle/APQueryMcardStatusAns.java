package com.pay.network.modle;

import com.pay.common.tool.APLog;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APDataInterface;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class APQueryMcardStatusAns extends APBaseHttpAns
{
  private String a = "";
  private String b = "";
  private String c = "";

  public APQueryMcardStatusAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
  }

  public String getMcardStatus()
  {
    return this.a;
  }

  public String getRealSaveNum()
  {
    return this.c;
  }

  public String getSaveAcctNum()
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
    APLog.i("APQueryMCardStatusAns", "resultData=" + str1);
    JSONObject localJSONObject;
    while (true)
    {
      try
      {
        localJSONObject = new JSONObject(str1);
        this.resultCode = Integer.parseInt(localJSONObject.getString("ret").toString());
        if (this.resultCode != 0)
          break;
        this.a = localJSONObject.getString("state").toString();
        if (!localJSONObject.has("qqacct_count"))
          continue;
        this.b = localJSONObject.getString("qqacct_count");
        APDataInterface.singleton().getOrderInfo().exSaveAcctNum = this.b;
        if (localJSONObject.has("provide_count"))
        {
          this.c = localJSONObject.getString("provide_count");
          APDataInterface.singleton().getOrderInfo().succSaveNum = this.c;
          return;
          this.b = "";
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
      this.c = "";
    }
    this.resultMsg = localJSONObject.getString("msg").toString();
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
 * Qualified Name:     com.pay.network.modle.APQueryMcardStatusAns
 * JD-Core Version:    0.6.0
 */