package com.tencent.msdk.push.req;

import android.os.Handler;
import android.os.Looper;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.communicator.HttpRequestManager;
import com.tencent.msdk.communicator.IHttpRequestListener;
import com.tencent.msdk.remote.api.SafeJSONObject;
import com.tencent.msdk.tools.Logger;
import org.json.JSONObject;

public abstract class BaseReq
  implements IHttpRequestListener
{
  protected JSONObject jsonBody = new SafeJSONObject();

  protected String getCustomDomain()
  {
    return WeGame.getInstance().getApiDomain();
  }

  protected String getExtUrlParams()
  {
    return "";
  }

  protected abstract int getMyId();

  protected String getPath()
  {
    return "";
  }

  public void send()
  {
    send(this.jsonBody.toString());
  }

  protected void send(String paramString)
  {
    String str = getCustomDomain() + getPath();
    Logger.d("url: " + str);
    Logger.d("body: " + paramString);
    new Handler(Looper.getMainLooper()).post(new Runnable(str, paramString)
    {
      public void run()
      {
        new HttpRequestManager(BaseReq.this).postTextAsync(this.val$url, this.val$body, BaseReq.this.getMyId());
      }
    });
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.req.BaseReq
 * JD-Core Version:    0.6.0
 */