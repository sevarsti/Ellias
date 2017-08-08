package com.tencent.component.cache;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

final class c extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    CacheManager.c(Environment.getExternalStorageState());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.c
 * JD-Core Version:    0.6.0
 */