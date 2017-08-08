package com.tencent.open;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.tencent.connect.a.a;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.utils.Util;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationApi extends BaseApi
  implements d.a
{
  private HandlerThread a;
  private Handler b;
  private Handler c;
  private d d;
  private Bundle e;
  private IUiListener f;

  public LocationApi(Context paramContext, QQAuth paramQQAuth, QQToken paramQQToken)
  {
    super(paramContext, paramQQAuth, paramQQToken);
    a();
  }

  public LocationApi(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
    a();
  }

  private void a()
  {
    this.d = new d();
    this.a = new HandlerThread("get_location");
    this.a.start();
    this.b = new Handler(this.a.getLooper());
    this.c = new Handler(this.mContext.getMainLooper())
    {
      public void handleMessage(Message paramMessage)
      {
        switch (paramMessage.what)
        {
        case 102:
        default:
        case 103:
        case 104:
        case 101:
        }
        while (true)
        {
          super.handleMessage(paramMessage);
          return;
          com.tencent.a.a.d.b("openSDK_LOG", "location: verify sosocode success.");
          LocationApi.b(LocationApi.this).a(LocationApi.a(LocationApi.this), LocationApi.this);
          LocationApi.c(LocationApi.this).sendEmptyMessageDelayed(101, 10000L);
          continue;
          com.tencent.a.a.d.b("openSDK_LOG", "location: verify sosocode failed.");
          LocationApi.a(LocationApi.this, -14, "定位失败，验证定位码错误！");
          continue;
          com.tencent.a.a.d.b("openSDK_LOG", "location: get location timeout.");
          LocationApi.a(LocationApi.this, -13, "定位超时，请稍后再试或检查网络状况！");
        }
      }
    };
  }

  private void a(int paramInt, String paramString)
  {
    this.d.b();
    if (this.f == null)
      return;
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("ret", paramInt);
      localJSONObject.put("errMsg", paramString);
      this.f.onComplete(localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }

  private void a(Location paramLocation)
  {
    com.tencent.a.a.d.b("openSDK_LOG", "location: search mParams: " + this.e);
    Bundle localBundle;
    if (this.e != null)
    {
      localBundle = new Bundle(this.e);
      localBundle.putAll(composeCGIParams());
    }
    while (true)
    {
      String str1 = String.valueOf(paramLocation.getLatitude());
      String str2 = String.valueOf(paramLocation.getLongitude());
      localBundle.putString("appid", this.mToken.getAppId());
      if (!localBundle.containsKey("latitude"))
        localBundle.putString("latitude", str1);
      if (!localBundle.containsKey("longitude"))
        localBundle.putString("longitude", str2);
      if (!localBundle.containsKey("page"))
        localBundle.putString("page", String.valueOf(1));
      localBundle.putString("encrytoken", Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4"));
      com.tencent.a.a.d.b("openSDK_LOG", "location: search params: " + localBundle);
      a locala = new a(this.f);
      HttpUtils.requestAsync(this.mToken, this.mContext, "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_getnear.cgi", localBundle, "GET", locala);
      return;
      localBundle = composeCGIParams();
    }
  }

  private void a(String paramString, String[] paramArrayOfString)
  {
    this.b.post(new Runnable(paramArrayOfString, paramString)
    {
      public void run()
      {
        if ((this.a == null) || (this.a.length == 0))
          return;
        if ("search_nearby".equals(this.b));
        for (String str = "id_search_nearby"; ; str = "id_delete_location")
        {
          a.a(LocationApi.d(LocationApi.this), LocationApi.e(LocationApi.this), str, this.a);
          return;
        }
      }
    });
  }

  private void b()
  {
    this.d.b();
  }

  private boolean c()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)this.mContext.getSystemService("connectivity");
    if (localConnectivityManager != null)
    {
      NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      return (localNetworkInfo != null) && (localNetworkInfo.isAvailable());
    }
    return false;
  }

  private JSONObject d()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("ret", -9);
      localJSONObject.put("errMsg", "网络连接异常，请检查后重试!");
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localJSONObject;
  }

  public void deleteLocation(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    if (!c())
    {
      if (paramIUiListener != null)
        paramIUiListener.onComplete(d());
      return;
    }
    Bundle localBundle;
    if (paramBundle != null)
    {
      localBundle = new Bundle(paramBundle);
      localBundle.putAll(composeCGIParams());
    }
    while (true)
    {
      localBundle.putString("appid", this.mToken.getAppId());
      localBundle.putString("timestamp", String.valueOf(System.currentTimeMillis()));
      localBundle.putString("encrytoken", Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4"));
      com.tencent.a.a.d.b("openSDK_LOG", "location: delete params: " + localBundle);
      a locala = new a(paramIUiListener);
      HttpUtils.requestAsync(this.mToken, this.mContext, "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_delete.cgi", localBundle, "GET", locala);
      a("delete_location", new String[] { "success" });
      return;
      localBundle = composeCGIParams();
    }
  }

  public void onLocationUpdate(Location paramLocation)
  {
    a(paramLocation);
    b();
    this.c.removeMessages(101);
  }

  public void searchNearby(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    if (!c())
    {
      if (paramIUiListener != null)
        paramIUiListener.onComplete(d());
      return;
    }
    this.e = paramBundle;
    this.f = paramIUiListener;
    this.b.post(new Runnable()
    {
      public void run()
      {
        if (LocationApi.b(LocationApi.this).a())
        {
          Message.obtain(LocationApi.c(LocationApi.this), 103).sendToTarget();
          return;
        }
        Message.obtain(LocationApi.c(LocationApi.this), 104).sendToTarget();
      }
    });
  }

  private class a extends LocationApi.b
  {
    private IUiListener c;

    public a(IUiListener arg2)
    {
      super(null);
      Object localObject;
      this.c = localObject;
    }

    protected void a(Exception paramException)
    {
      if (this.c != null)
        this.c.onError(new UiError(100, paramException.getMessage(), null));
    }

    public void onComplete(JSONObject paramJSONObject)
    {
      if (this.c != null)
        this.c.onComplete(paramJSONObject);
    }
  }

  private abstract class b
    implements IRequestListener
  {
    private b()
    {
    }

    protected abstract void a(Exception paramException);

    public void onConnectTimeoutException(ConnectTimeoutException paramConnectTimeoutException)
    {
      a(paramConnectTimeoutException);
    }

    public void onHttpStatusException(HttpUtils.HttpStatusException paramHttpStatusException)
    {
      a(paramHttpStatusException);
    }

    public void onIOException(IOException paramIOException)
    {
      a(paramIOException);
    }

    public void onJSONException(JSONException paramJSONException)
    {
      a(paramJSONException);
    }

    public void onMalformedURLException(MalformedURLException paramMalformedURLException)
    {
      a(paramMalformedURLException);
    }

    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException paramNetworkUnavailableException)
    {
      a(paramNetworkUnavailableException);
    }

    public void onSocketTimeoutException(SocketTimeoutException paramSocketTimeoutException)
    {
      a(paramSocketTimeoutException);
    }

    public void onUnknowException(Exception paramException)
    {
      a(paramException);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.LocationApi
 * JD-Core Version:    0.6.0
 */