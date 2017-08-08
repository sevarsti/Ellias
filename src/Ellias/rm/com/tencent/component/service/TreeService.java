package com.tencent.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.tencent.component.utils.log.LogUtil;

public class TreeService extends Service
{
  private static final String a = "TreeService";

  public IBinder onBind(Intent paramIntent)
  {
    LogUtil.i("TreeService", "onBind");
    return new m(getApplicationContext());
  }

  public void onCreate()
  {
    super.onCreate();
    LogUtil.i("TreeService", "onCreate");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.TreeService
 * JD-Core Version:    0.6.0
 */