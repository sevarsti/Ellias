package com.tencent.tmassistantsdk.selfUpdateSDK;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.tmassistantsdk.c.c;
import com.tencent.tmassistantsdk.c.i;
import com.tencent.tmassistantsdk.g.l;

class g
  implements Runnable
{
  g(f paramf, String paramString, int paramInt)
  {
  }

  public void run()
  {
    try
    {
      if (this.c.a.getClient(false) != null)
      {
        String str = this.c.a.getClient(false).a(this.a).b;
        Log.i("SelfUpdateSDK", "OnDownloadSDKTaskStateChanged：url:" + this.a + "; state:" + this.b + "; yybpath:" + str);
        if (!TextUtils.isEmpty(str))
        {
          TMSelfUpdateSDK.access$000(this.c.a, str, "com.tencent.android.qqdownloader", this.c.a.updateType);
          this.c.a.isFromStartUpdate = true;
          l.b("SelfUpdateSDK", "isFromStartUpdate;OnDownloadSDKTaskStateChanged():" + this.c.a.isFromStartUpdate);
        }
      }
      localContext = this.c.a.mContext;
      com.tencent.tmassistantsdk.c.f.b(localContext);
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        Context localContext = this.c.a.mContext;
      }
    }
    finally
    {
      com.tencent.tmassistantsdk.c.f.b(this.c.a.mContext);
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.g
 * JD-Core Version:    0.6.0
 */