package com.tencent.tmassistantsdk.openSDK;

import android.content.Intent;
import java.util.ArrayList;
import java.util.Iterator;

class b
  implements Runnable
{
  b(DownloadStateChangedReceiver paramDownloadStateChangedReceiver, Intent paramIntent)
  {
  }

  public void run()
  {
    h localh = new h();
    localh.b = this.a.getStringExtra("hostPackageName");
    localh.c = this.a.getStringExtra("hostVersion");
    localh.d = this.a.getStringExtra("taskId");
    localh.f = Integer.parseInt(this.a.getStringExtra("errorCode"));
    localh.g = this.a.getStringExtra("errorMsg");
    localh.e = Integer.parseInt(this.a.getStringExtra("state"));
    g localg = new g();
    localg.a = this.a.getStringExtra("sngAppId");
    localg.b = this.a.getStringExtra("taskAppId");
    localg.c = this.a.getStringExtra("taskApkId");
    localg.f = this.a.getStringExtra("taskPackageName");
    localg.d = Integer.parseInt(this.a.getStringExtra("taskVersion"));
    localg.e = this.a.getStringExtra("via");
    localg.g = this.a.getStringExtra("uin");
    localg.h = this.a.getStringExtra("uinType");
    localh.a = localg;
    Iterator localIterator = this.b.e.iterator();
    while (localIterator.hasNext())
      ((c)localIterator.next()).a(localh);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.b
 * JD-Core Version:    0.6.0
 */