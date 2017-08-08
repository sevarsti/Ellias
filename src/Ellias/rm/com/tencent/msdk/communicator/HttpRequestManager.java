package com.tencent.msdk.communicator;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.tencent.msdk.tools.Logger;

public class HttpRequestManager
{
  public static final String NOTICE_ACTION = "/notice/gather_data/";
  public static final String PFKEY_ACTION = "/auth/getlogin_info/";
  public static final String QQA8LOGIN_ACTION = "/auth/qqa8_login/";
  public static final String RSP_KEY = "http_rsp";
  public static final String WXEXPIRED_LOGIN_ACTION = "/auth/wxexpired_login/";
  public static final String WXFIRST_LOGIN_ACTION = "/auth/wxfirst_login/";
  public static final Boolean isEncode = Boolean.valueOf(true);
  private IHttpRequestListener mListener;
  private Handler mWorkerHandler;

  public HttpRequestManager(IHttpRequestListener paramIHttpRequestListener)
  {
    this.mListener = paramIHttpRequestListener;
    initHandle();
  }

  private void initHandle()
  {
    this.mWorkerHandler = new Handler(Looper.getMainLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramMessage)
      {
        if ((paramMessage == null) || (paramMessage.getData() == null))
        {
          Logger.e("msg || msg.getData() is null");
          return false;
        }
        int i = paramMessage.arg1;
        MHttpResponse localMHttpResponse2;
        switch (i)
        {
        default:
          localMHttpResponse2 = (MHttpResponse)paramMessage.getData().getParcelable("http_rsp");
          if (localMHttpResponse2 != null)
            break;
          HttpRequestManager.this.notifyRequestfailure(Integer.valueOf(paramMessage.what), "http error:" + i + ", response no params", i);
          Logger.d(paramMessage.what + " HTTP_Failed statusCode：" + i);
          return false;
        case 200:
          MHttpResponse localMHttpResponse1 = (MHttpResponse)paramMessage.getData().getParcelable("http_rsp");
          if (localMHttpResponse1 == null)
          {
            Logger.d(paramMessage.what + " HTTP_SUCCESS, response params but body is null");
            HttpRequestManager.this.notifyRequestfailure(Integer.valueOf(paramMessage.what), "response no params", 1002);
            return false;
          }
          if (localMHttpResponse1.getBody() == null)
          {
            Logger.d(paramMessage.what + " HTTP_SUCCESS and rsp.getStatus:" + localMHttpResponse1.getStatus() + ",response params but body is null");
            HttpRequestManager.this.notifyRequestfailure(Integer.valueOf(paramMessage.what), "response params but body is null", 1002);
            return false;
          }
          Logger.d(paramMessage.what + " HTTP_SUCCESS, status:" + localMHttpResponse1.getStatus());
          HttpRequestManager.this.notifyRequestSuccess(Integer.valueOf(paramMessage.what), localMHttpResponse1.getBody(), localMHttpResponse1.getStatus());
          return false;
        }
        if (localMHttpResponse2.getBody() == null)
        {
          Logger.d("MHttpResponse.HTTP_Failed :" + localMHttpResponse2.getBody());
          if (localMHttpResponse2.getMsg() == null)
          {
            HttpRequestManager.this.notifyRequestfailure(Integer.valueOf(paramMessage.what), "response params but body and msg are null", localMHttpResponse2.getStatus());
            Logger.d("MHttpResponse.HTTP_Failed 1, statusCode:" + localMHttpResponse2.getStatus() + "：response params but body and msg are null");
            return false;
          }
          HttpRequestManager.this.notifyRequestfailure(Integer.valueOf(paramMessage.what), localMHttpResponse2.getMsg(), localMHttpResponse2.getStatus());
          Logger.d("MHttpResponse.HTTP_Failed 2, statusCode:" + localMHttpResponse2.getStatus() + "：" + localMHttpResponse2.getMsg());
          return false;
        }
        HttpRequestManager.this.notifyRequestfailure(Integer.valueOf(paramMessage.what), new String(localMHttpResponse2.getBody()), localMHttpResponse2.getStatus());
        Logger.d("MHttpResponse.HTTP_Failed 3, statusCode:" + localMHttpResponse2.getStatus());
        return false;
      }
    });
  }

  private void notifyRequestSuccess(Integer paramInteger, String paramString, int paramInt)
  {
    if (this.mListener != null)
      this.mListener.onSuccess(paramString, paramInt, paramInteger.intValue());
  }

  private void notifyRequestfailure(Integer paramInteger, String paramString, int paramInt)
  {
    if (this.mListener != null)
      this.mListener.onFailure(paramString, paramInt, paramInteger.intValue());
  }

  public void postTextAsync(String paramString1, String paramString2, int paramInt)
  {
    monitorenter;
    try
    {
      if (Looper.myLooper() == null)
        Logger.w("The calling thread has not called Looper.prepare()");
      MHttpRequest localMHttpRequest = new MHttpRequest();
      localMHttpRequest.setUrl(paramString1);
      localMHttpRequest.setMethod(MHttpRequest.HttpMethod.POST);
      localMHttpRequest.setBody(paramString2);
      new HttpTask(this.mWorkerHandler, paramInt).execute(new MHttpRequest[] { localMHttpRequest });
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.communicator.HttpRequestManager
 * JD-Core Version:    0.6.0
 */