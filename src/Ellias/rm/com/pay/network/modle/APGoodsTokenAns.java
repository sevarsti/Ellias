package com.pay.network.modle;

import com.pay.common.tool.APLog;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APErrorCode;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APAppDataInterface;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class APGoodsTokenAns extends APBaseHttpAns
{
  private String a = "";
  private String b = "";

  public APGoodsTokenAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
  }

  public String getGoodsTokenId()
  {
    return this.a;
  }

  public String getGoodsTokenUrl()
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
    APLog.i("APGoodsTokenAns", "resultData=" + str1);
    JSONObject localJSONObject;
    try
    {
      localJSONObject = new JSONObject(str1);
      this.resultCode = Integer.parseInt(localJSONObject.getString("ret").toString());
      if (this.resultCode == 0)
      {
        if (localJSONObject.has("token"))
          this.a = localJSONObject.getString("token");
        if (localJSONObject.has("mid"))
        {
          String str2 = localJSONObject.getString("mid");
          StringBuffer localStringBuffer = new StringBuffer();
          localStringBuffer.append("/v1");
          localStringBuffer.append("/" + str2);
          localStringBuffer.append("/" + APAppDataInterface.singleton().getOfferid());
          localStringBuffer.append("/mobile_goods_info?token_id=");
          localStringBuffer.append(this.a);
          this.b = localStringBuffer.toString();
          return;
        }
        this.b = "";
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      this.resultMsg = ("系统繁忙,请稍后再试\n" + APErrorCode.getErrorCode(1003));
      APLog.w("APGetBuyInfoAdapter", localJSONException.getMessage());
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
 * Qualified Name:     com.pay.network.modle.APGoodsTokenAns
 * JD-Core Version:    0.6.0
 */