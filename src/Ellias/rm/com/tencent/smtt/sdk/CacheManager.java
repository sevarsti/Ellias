package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.WebViewWizardBase;
import com.tencent.smtt.utils.ReflectionUtils;
import java.io.File;

public final class CacheManager
{
  @Deprecated
  public static boolean cacheDisabled()
  {
    SDKEngine localSDKEngine = SDKEngine.getInstance(false);
    boolean bool;
    if ((localSDKEngine != null) && (localSDKEngine.isX5Core()))
      bool = ((Boolean)localSDKEngine.wizard().cacheDisabled()).booleanValue();
    Object localObject;
    do
    {
      return bool;
      localObject = ReflectionUtils.invokeStatic("android.webkit.CacheManager", "cacheDisabled");
      bool = false;
    }
    while (localObject == null);
    return ((Boolean)localObject).booleanValue();
  }

  @Deprecated
  public static File getCacheFileBaseDir()
  {
    SDKEngine localSDKEngine = SDKEngine.getInstance(false);
    if ((localSDKEngine != null) && (localSDKEngine.isX5Core()))
      return (File)localSDKEngine.wizard().getCachFileBaseDir();
    return (File)ReflectionUtils.invokeStatic("android.webkit.CacheManager", "getCacheFileBaseDir");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.CacheManager
 * JD-Core Version:    0.6.0
 */