package com.tencent.msdk;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.os.Bundle;
import com.tencent.msdk.tools.Logger;
import java.util.List;

public class MsdkActivity extends Activity
{
  private boolean isReduplicateGameActivity()
  {
    return getActivityNum() > 1;
  }

  public int getActivityNum()
  {
    ActivityManager localActivityManager = (ActivityManager)getSystemService("activity");
    if (localActivityManager == null);
    List localList;
    do
    {
      return 0;
      localList = localActivityManager.getRunningTasks(1);
    }
    while (localList == null);
    return ((ActivityManager.RunningTaskInfo)localList.get(0)).numActivities;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (isReduplicateGameActivity())
    {
      Logger.d("MsdkActivity onCreate~~~.Reduplicate game activity was detected.Return immediately.");
      finish();
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (isReduplicateGameActivity())
      Logger.d("MsdkActivity onDestroy~~~.Reduplicate game activity was detected.Return immediately.");
  }

  public void onLowMemory()
  {
    Logger.d("***~~~!!!onLowMemory()!!!~~~***");
  }

  protected void onPause()
  {
    super.onPause();
    if (isReduplicateGameActivity())
    {
      Logger.d("MsdkActivity onPause~~~.Reduplicate game activity was detected.Return immediately.");
      return;
    }
    Logger.d("onPause()");
  }

  protected void onRestart()
  {
    Logger.d("MsdkActivity onRestart~~~");
    super.onRestart();
    if (isReduplicateGameActivity())
      Logger.d("YellowGame onRestart~~~.Reduplicate game activity was detected.Return immediately.");
  }

  protected void onResume()
  {
    super.onResume();
    if (isReduplicateGameActivity())
    {
      Logger.d("MsdkActivity onResume~~~.Reduplicate game activity was detected.Return immediately.");
      return;
    }
    Logger.d("onResume()");
  }

  protected void onStart()
  {
    super.onStart();
    if (isReduplicateGameActivity())
    {
      Logger.d("MsdkActivity onStart~~~.Reduplicate game activity was detected.Return immediately.");
      return;
    }
    Logger.d("onStart()");
  }

  protected void onStop()
  {
    super.onStop();
    if (isReduplicateGameActivity())
    {
      Logger.d("MsdkActivity onStop~~~.Reduplicate game activity was detected.Return immediately.");
      return;
    }
    Logger.d("onStop()");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.MsdkActivity
 * JD-Core Version:    0.6.0
 */