package com.pay.network.modle;

import com.pay.common.tool.APLog;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APDataInterface;
import java.util.HashMap;
import org.json.JSONObject;

public class APQueryMarketResultAns extends APBaseHttpAns
{
  public APQueryMarketResultAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
  }

  public void onErrorAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onFinishAns(byte[] paramArrayOfByte, APBaseHttpReq paramAPBaseHttpReq)
  {
    super.onFinishAns(paramArrayOfByte, paramAPBaseHttpReq);
    String str1 = new String(paramArrayOfByte);
    APLog.i("APQueryMarketResultAns", "resultData == " + str1);
    try
    {
      JSONObject localJSONObject = new JSONObject(str1);
      this.resultCode = Integer.parseInt(localJSONObject.getString("ret").toString());
      String str5;
      if (this.resultCode == 0)
      {
        int i = localJSONObject.getInt("present_flag");
        String str3 = localJSONObject.getString("goods_name");
        String str4 = localJSONObject.getString("goods_num");
        APDataInterface.singleton().setPresent_flag(i);
        APDataInterface.singleton().setGoods_name(str3);
        APDataInterface.singleton().setGoods_num(str4);
        str5 = "";
      }
      String str2;
      do
      {
        try
        {
          str5 = localJSONObject.getString("extend");
          APDataInterface.singleton().setGoods_extend(str5);
          return;
        }
        catch (Exception localException2)
        {
          APLog.i("APQueryMarketResultAns extend == ", str5);
          return;
        }
        this.resultMsg = localJSONObject.getString("msg").toString();
        str2 = localJSONObject.getString("err_code").toString();
      }
      while (str2.equals(""));
      this.resultMsg = ("系统繁忙,请稍后再试\n(" + str2 + ")");
      return;
    }
    catch (Exception localException1)
    {
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
 * Qualified Name:     com.pay.network.modle.APQueryMarketResultAns
 * JD-Core Version:    0.6.0
 */