package com.tencent.msdk.doctor;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.tencent.msdk.tools.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CheckBase
{
  public Activity mContext = null;

  public CheckBase(Activity paramActivity)
  {
    this.mContext = paramActivity;
  }

  public abstract ArrayList<String> check();

  public boolean queryIntentFilter(Intent paramIntent, String paramString)
  {
    Iterator localIterator = this.mContext.getPackageManager().queryIntentActivities(paramIntent, 65536).iterator();
    int i;
    while (true)
    {
      boolean bool = localIterator.hasNext();
      i = 0;
      if (!bool)
        break;
      if (!paramString.equals(((ResolveInfo)localIterator.next()).activityInfo.name))
        continue;
      i = 1;
    }
    if (i == 0)
      Logger.e("Msdk: the intent-filter of " + paramString + " has not be configured correctly");
    return i;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.doctor.CheckBase
 * JD-Core Version:    0.6.0
 */