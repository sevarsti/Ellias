package com.tencent.component.net.download.multiplex.download;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.tencent.component.ComponentContext;

class h extends Thread
{
  h(DownloadTask paramDownloadTask)
  {
  }

  public void run()
  {
    Context localContext = ComponentContext.a();
    String str = this.a.H + "/" + this.a.G;
    PackageInfo localPackageInfo = localContext.getPackageManager().getPackageArchiveInfo(str, 1);
    DownloadTask.a(this.a, localPackageInfo, localContext);
    if (((0x10 & DownloadTask.a(this.a)) != 16) && (localPackageInfo != null));
    try
    {
      Drawable localDrawable = localContext.getPackageManager().getApplicationIcon(localPackageInfo.packageName);
      if ((localDrawable instanceof BitmapDrawable))
        ((BitmapDrawable)localDrawable).getBitmap();
      this.a.az();
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        localNameNotFoundException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.h
 * JD-Core Version:    0.6.0
 */