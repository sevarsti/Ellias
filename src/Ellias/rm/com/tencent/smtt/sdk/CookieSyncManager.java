package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.WebViewWizardBase;
import java.lang.reflect.Field;

public class CookieSyncManager
{
  private static CookieSyncManager sRef;
  private static android.webkit.CookieSyncManager sysCookieSyncManager;

  private CookieSyncManager(Context paramContext)
  {
  }

  public static CookieSyncManager createInstance(Context paramContext)
  {
    monitorenter;
    try
    {
      sysCookieSyncManager = android.webkit.CookieSyncManager.createInstance(paramContext);
      if (sRef == null)
        sRef = new CookieSyncManager(paramContext.getApplicationContext());
      CookieSyncManager localCookieSyncManager = sRef;
      return localCookieSyncManager;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static CookieSyncManager getInstance()
  {
    monitorenter;
    try
    {
      if (sRef == null)
        throw new IllegalStateException("CookieSyncManager::createInstance() needs to be called before CookieSyncManager::getInstance()");
    }
    finally
    {
      monitorexit;
    }
    CookieSyncManager localCookieSyncManager = sRef;
    monitorexit;
    return localCookieSyncManager;
  }

  public void startSync()
  {
    SDKEngine localSDKEngine = SDKEngine.getInstance(false);
    if ((localSDKEngine != null) && (localSDKEngine.isX5Core()))
    {
      localSDKEngine.wizard().cookieSyncManager_startSync();
      return;
    }
    sysCookieSyncManager.startSync();
    try
    {
      Field localField = Class.forName("android.webkit.WebSyncManager").getDeclaredField("mSyncThread");
      localField.setAccessible(true);
      ((Thread)localField.get(sysCookieSyncManager)).setUncaughtExceptionHandler(new SQLiteUncaughtExceptionHandler());
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void stopSync()
  {
    SDKEngine localSDKEngine = SDKEngine.getInstance(false);
    if ((localSDKEngine != null) && (localSDKEngine.isX5Core()))
    {
      localSDKEngine.wizard().cookieSyncManager_stopSync();
      return;
    }
    sysCookieSyncManager.stopSync();
  }

  public void sync()
  {
    SDKEngine localSDKEngine = SDKEngine.getInstance(false);
    if ((localSDKEngine != null) && (localSDKEngine.isX5Core()))
    {
      localSDKEngine.wizard().cookieSyncManager_Sync();
      return;
    }
    sysCookieSyncManager.sync();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.CookieSyncManager
 * JD-Core Version:    0.6.0
 */