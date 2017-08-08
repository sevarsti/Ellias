package com.pay.network.modle;

import com.pay.common.tool.APLog;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APErrorCode;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APDataInterface;
import com.pay.tool.APToolAES;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class APGetTokenAns extends APBaseHttpAns
{
  private String a = "";

  public APGetTokenAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
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

  public String getToken()
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
    }
    label164: String str2;
    do
    {
      JSONObject localJSONObject;
      while (true)
      {
        return;
        String str1 = new String(paramArrayOfByte);
        APLog.i("APGetTokenAns", "resultData=" + str1);
        try
        {
          localJSONObject = new JSONObject(str1);
          this.resultCode = Integer.parseInt(localJSONObject.getString("ret").toString());
          if (this.resultCode != 0)
            break label164;
          this.a = localJSONObject.getString("token").toString();
          if (!localJSONObject.has("need_change_key"))
            continue;
          if (localJSONObject.getInt("need_change_key") != 1)
            break;
          APAppDataInterface.singleton().setChangeKey(true);
          return;
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          return;
        }
      }
      APAppDataInterface.singleton().setChangeKey(false);
      return;
      a(localJSONObject);
      this.resultMsg = localJSONObject.getString("msg").toString();
      str2 = localJSONObject.getString("err_code").toString();
    }
    while (str2.equals(""));
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
 * Qualified Name:     com.pay.network.modle.APGetTokenAns
 * JD-Core Version:    0.6.0
 */