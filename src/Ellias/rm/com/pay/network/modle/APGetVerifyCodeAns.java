package com.pay.network.modle;

import com.pay.common.tool.APBase64;
import com.pay.common.tool.APLog;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APDataInterface;
import com.pay.tool.APToolAES;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class APGetVerifyCodeAns extends APBaseHttpAns
{
  private byte[] a = null;
  private String b = "";

  public APGetVerifyCodeAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    super(paramAPHttpHandle, paramIAPHttpAnsObserver, paramHashMap, paramString);
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

  public byte[] getVerifyCode()
  {
    return this.a;
  }

  public String getVerifySession()
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
    APLog.i("APVerifyCodeAns", "resultData=" + str1);
    try
    {
      JSONObject localJSONObject = new JSONObject(str1);
      this.resultCode = Integer.parseInt(localJSONObject.getString("ret").toString());
      if (this.resultCode == 0)
      {
        this.b = localJSONObject.getString("verify_session").toString();
        this.a = APBase64.decode(localJSONObject.getString("bin").toString());
        return;
      }
      a(localJSONObject);
      this.resultMsg = localJSONObject.getString("msg");
      String str2 = localJSONObject.getString("err_code").toString();
      if (!str2.equals(""))
      {
        this.resultMsg = ("系统繁忙,请稍后再试\n(" + str2 + ")");
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      this.a = APBase64.decode("");
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
 * Qualified Name:     com.pay.network.modle.APGetVerifyCodeAns
 * JD-Core Version:    0.6.0
 */