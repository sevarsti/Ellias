package com.tencent.tmassistantsdk.selfUpdateSDK;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.c.c;
import com.tencent.tmassistantsdk.c.f;
import com.tencent.tmassistantsdk.g.l;

class i
  implements Runnable
{
  i(h paramh, String paramString, int paramInt)
  {
  }

  public void run()
  {
    while (true)
    {
      try
      {
        if (this.c.a.getClient(true) == null)
          break label364;
        com.tencent.tmassistantsdk.c.i locali = this.c.a.getClient(true).a(this.a);
        if (locali == null)
          break label346;
        String str = locali.b;
        l.b("SelfUpdateSDK", "mClientSDKListener.OnDownloadSDKTaskStateChanged,url:" + this.a + "; state:" + this.b + "; patchPath:" + str);
        if (TextUtils.isEmpty(str))
          break label328;
        if (this.c.a.mCheckUpdateMethod != 4)
          continue;
        TMSelfUpdateSDK.access$100(this.c.a, str);
        localContext = this.c.a.mContext;
        f.b(localContext);
        return;
        if (this.c.a.mCheckUpdateMethod != 2)
          continue;
        TMSelfUpdateSDK.access$000(this.c.a, str, this.c.a.hostPackageName, this.c.a.updateType);
        this.c.a.onStateChanged(0, 0, "SelfUpdate success !");
        continue;
      }
      catch (Exception localException)
      {
        this.c.a.onStateChanged(2, -20, "mClientSDKListener," + localException.getMessage());
        localException.printStackTrace();
        Context localContext = this.c.a.mContext;
        continue;
        if (this.c.a.mCheckUpdateMethod == 1)
        {
          this.c.a.onStateChanged(0, -15, "SelfUpdate success, NO Update!");
          continue;
        }
      }
      finally
      {
        f.b(this.c.a.mContext);
      }
      this.c.a.onStateChanged(2, -20, "OnDownloadSDKTaskStateChanged,OnDownloadSDKTaskStateChanged,unknown exception!");
      continue;
      label328: this.c.a.onStateChanged(2, -19, "SelfUpdate failure,OnDownloadSDKTaskStateChanged SelfUpdateSDKErrorCode_getSavePath_IS_NULL!");
      continue;
      label346: this.c.a.onStateChanged(2, -19, "SelfUpdate failure,OnDownloadSDKTaskStateChanged SelfUpdateSDKErrorCode_GetDownloadTaskState_IS_NULL!");
      continue;
      label364: this.c.a.onStateChanged(2, -18, "SelfUpdate failure, TMAssistantDownloadSDKClient_IS_NULL!");
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.i
 * JD-Core Version:    0.6.0
 */