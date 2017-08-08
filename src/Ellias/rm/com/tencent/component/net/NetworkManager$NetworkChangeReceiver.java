package com.tencent.component.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import com.tencent.component.utils.NetworkUtil;
import com.tencent.component.utils.log.LogUtil;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

public class NetworkManager$NetworkChangeReceiver extends BroadcastReceiver
{
  private static final String a = "NetworkChangeReceiver";
  private Context b;
  private String c = "none";

  public NetworkManager$NetworkChangeReceiver(Context paramContext)
  {
    this.b = paramContext;
  }

  public String a()
  {
    return this.c;
  }

  public void a(String paramString)
  {
    this.c = paramString;
  }

  public Context b()
  {
    return this.b;
  }

  public boolean c()
  {
    NetworkInfo localNetworkInfo = NetworkUtil.d(this.b);
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }

  public String d()
  {
    NetworkInfo localNetworkInfo = NetworkUtil.d(this.b);
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()))
      return "none";
    if (1 == localNetworkInfo.getType())
      return "wifi";
    if (localNetworkInfo.getExtraInfo() != null)
      return localNetworkInfo.getExtraInfo().toLowerCase();
    return "unknown";
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("NetworkChangeReceiver onReceive()");
    String str1;
    if (paramContext == null)
      str1 = " with Context";
    String str2;
    while (true)
    {
      LogUtil.i("NetworkChangeReceiver", str1);
      if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(paramIntent.getAction()))
        return;
      str2 = d();
      LogUtil.i("NetworkChangeReceiver", "old apn:" + this.c + "  new apn:" + str2);
      if (str2.equals(this.c))
        break;
      synchronized (NetworkManager.c())
      {
        Iterator localIterator = NetworkManager.d().iterator();
        NetworkManager.NetStatusListener localNetStatusListener;
        do
        {
          if (!localIterator.hasNext())
            break;
          localNetStatusListener = (NetworkManager.NetStatusListener)((WeakReference)localIterator.next()).get();
        }
        while (localNetStatusListener == null);
        localNetStatusListener.a(this.c, str2);
      }
      str1 = " without Context";
      continue;
      monitorexit;
    }
    this.c = str2;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.NetworkManager.NetworkChangeReceiver
 * JD-Core Version:    0.6.0
 */