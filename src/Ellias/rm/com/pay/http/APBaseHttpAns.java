package com.pay.http;

import android.os.Message;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APDataInterface;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class APBaseHttpAns
  implements IAPHttpAns
{
  protected final String[] AesEncodeKey = { "Td8qRx7IdbbSyw3K", "elddjmxNE2FK8cch", "n6QnJOTocDGX5dXR", "caUdsBbJ1oOxMbPy", "ehDFwSSDOFz3U1d3", "nmiFzdsTgUYGcMeg", "t3W6mdGCbIfFcwdR", "PLSeUfBBSgfDWAuA", "ayGzfJkNBZKE9UZf", "yVBtdRgAEx3EgG31" };
  private APHttpHandle a;
  private HashMap b;
  private IAPHttpAnsObserver c;
  private APBaseHttpReq d;
  private int e = 0;
  protected String errorMsg = "";
  private int f = 0;
  public String httpReqKey = "";
  protected int resultCode = -1;
  protected String resultMsg = "系统繁忙,请稍后再试\n" + APErrorCode.getErrorCode(2000);

  public APBaseHttpAns(APHttpHandle paramAPHttpHandle, IAPHttpAnsObserver paramIAPHttpAnsObserver, HashMap paramHashMap, String paramString)
  {
    this.a = paramAPHttpHandle;
    this.b = paramHashMap;
    this.httpReqKey = paramString;
    this.c = paramIAPHttpAnsObserver;
    this.a.register(this.httpReqKey, paramIAPHttpAnsObserver);
  }

  private void a()
  {
    Message localMessage = new Message();
    localMessage.what = 4;
    localMessage.obj = this;
    this.a.sendMessage(localMessage);
  }

  private void a(int paramInt)
  {
    String str = "{\"ret\":" + paramInt + ",\"err_code\":\"100-100-5001\",\"msg\":\"getKey error\"}";
    this.d.setContent(str.getBytes());
    reRegister();
    onFinish(this.d);
  }

  private void b()
  {
    this.b.remove(this.httpReqKey);
  }

  public void changeKey(boolean paramBoolean)
  {
    APNetworkManager.getInstance().getKey(0, this.f, new b(this, paramBoolean));
  }

  public String getErrorMessage()
  {
    return this.errorMsg;
  }

  public String getHttpReqKey()
  {
    return this.httpReqKey;
  }

  public int getResultCode()
  {
    return this.resultCode;
  }

  public String getResultMessage()
  {
    return this.resultMsg;
  }

  public void onError(APBaseHttpReq paramAPBaseHttpReq, int paramInt, String paramString)
  {
    this.errorMsg = paramString;
    this.resultMsg = paramString;
    this.resultCode = paramInt;
    b();
    onErrorAns(paramAPBaseHttpReq);
    a();
  }

  public void onErrorAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onFinish(APBaseHttpReq paramAPBaseHttpReq)
  {
    b();
    if (paramAPBaseHttpReq.getContent() == null)
    {
      this.resultCode = -1;
      this.resultMsg = ("系统繁忙,请稍后再试" + APErrorCode.getErrorCode(1004));
      a();
      return;
    }
    this.d = paramAPBaseHttpReq;
    byte[] arrayOfByte = paramAPBaseHttpReq.getContent();
    String str;
    if (arrayOfByte != null)
    {
      str = new String(arrayOfByte);
      if ((this.httpReqKey.equals("save")) && (AndroidPay.singleton().IsNeedUinLogin()) && ((APDataInterface.singleton().getOrderInfo().saveType == 2) || (APDataInterface.singleton().getOrderInfo().saveType == 3)))
        this.f = 1;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject(str);
      this.resultCode = Integer.parseInt(localJSONObject.getString("ret").toString());
      APLog.i("APImpAns", "resultCode=" + this.resultCode);
      int i = this.resultCode;
      switch (i)
      {
      case 1018:
      default:
      case 0:
        while (true)
        {
          onFinishAns(paramAPBaseHttpReq.getContent(), paramAPBaseHttpReq);
          paramAPBaseHttpReq.getContent();
          Message localMessage = new Message();
          localMessage.what = 3;
          localMessage.obj = this;
          this.a.sendMessage(localMessage);
          return;
          if ((!localJSONObject.has("need_change_key")) || (localJSONObject.getInt("need_change_key") != 1))
            continue;
          APAppDataInterface.singleton().setChangeKey(true);
          changeKey(false);
        }
      case 1094:
      case 1099:
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        localJSONException.printStackTrace();
        continue;
        APLog.i("APImpAns", "resultData=" + str);
        changeKey(true);
      }
    }
  }

  public void onFinishAns(byte[] paramArrayOfByte, APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onReceive(byte[] paramArrayOfByte, int paramInt, long paramLong, APBaseHttpReq paramAPBaseHttpReq)
  {
    onReceiveAns(paramArrayOfByte, paramInt, paramLong, paramAPBaseHttpReq);
  }

  public void onReceiveAns(byte[] paramArrayOfByte, int paramInt, long paramLong, APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onStart(APBaseHttpReq paramAPBaseHttpReq)
  {
    this.b.put(this.httpReqKey, paramAPBaseHttpReq);
    onStartAns(paramAPBaseHttpReq);
  }

  public void onStartAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void onStop(APBaseHttpReq paramAPBaseHttpReq)
  {
    b();
    onStopAns(paramAPBaseHttpReq);
    Message localMessage = new Message();
    localMessage.what = 5;
    localMessage.obj = this;
    this.a.sendMessage(localMessage);
  }

  public void onStopAns(APBaseHttpReq paramAPBaseHttpReq)
  {
  }

  public void reRegister()
  {
    this.a.register(this.httpReqKey, this.c);
  }

  public void requestAgain()
  {
    if ((this.d != null) && (this.e <= 1))
    {
      this.e = (1 + this.e);
      reRegister();
      new Thread(new a(this)).start();
      return;
    }
    reRegister();
    onError(this.d, -1, "");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APBaseHttpAns
 * JD-Core Version:    0.6.0
 */