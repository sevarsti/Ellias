package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings.LayoutAlgorithm;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings.PluginState;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings.RenderPriority;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings.TextSize;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings.ZoomDensity;
import com.tencent.smtt.utils.ReflectionUtils;

public class WebSettings
{
  public static final int LOAD_CACHE_ELSE_NETWORK = 1;
  public static final int LOAD_CACHE_ONLY = 3;
  public static final int LOAD_DEFAULT = -1;
  public static final int LOAD_NORMAL = 0;
  public static final int LOAD_NO_CACHE = 2;
  private boolean isUseX5 = false;
  private android.webkit.WebSettings mSystemWebSettings = null;
  private IX5WebSettings mWebSettingsImpl = null;

  WebSettings(android.webkit.WebSettings paramWebSettings)
  {
    this.mWebSettingsImpl = null;
    this.mSystemWebSettings = paramWebSettings;
    this.isUseX5 = false;
  }

  WebSettings(IX5WebSettings paramIX5WebSettings)
  {
    this.mWebSettingsImpl = paramIX5WebSettings;
    this.mSystemWebSettings = null;
    this.isUseX5 = true;
  }

  @TargetApi(17)
  public static String getDefaultUserAgent(Context paramContext)
  {
    if ((SDKEngine.getInstance(false) != null) && (SDKEngine.getInstance(false).isX5Core() == true));
    do
      return null;
    while (Build.VERSION.SDK_INT < 17);
    return android.webkit.WebSettings.getDefaultUserAgent(paramContext);
  }

  @Deprecated
  public boolean enableSmoothTransition()
  {
    boolean bool;
    if (this.isUseX5)
      bool = this.mWebSettingsImpl.enableSmoothTransition();
    Object localObject;
    do
    {
      int i;
      do
      {
        return bool;
        i = Build.VERSION.SDK_INT;
        bool = false;
      }
      while (i < 11);
      localObject = ReflectionUtils.invokeInstance(this.mSystemWebSettings, "enableSmoothTransition");
      bool = false;
    }
    while (localObject == null);
    return ((Boolean)localObject).booleanValue();
  }

  @TargetApi(11)
  public boolean getAllowContentAccess()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.getAllowContentAccess();
    if (Build.VERSION.SDK_INT >= 11)
      return this.mSystemWebSettings.getAllowContentAccess();
    return false;
  }

  @TargetApi(3)
  public boolean getAllowFileAccess()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.getAllowFileAccess();
    return this.mSystemWebSettings.getAllowFileAccess();
  }

  public boolean getBlockNetworkImage()
  {
    monitorenter;
    try
    {
      boolean bool3;
      if (this.isUseX5)
        bool3 = this.mWebSettingsImpl.getBlockNetworkImage();
      boolean bool1;
      for (boolean bool2 = bool3; ; bool2 = bool1)
      {
        return bool2;
        bool1 = this.mSystemWebSettings.getBlockNetworkImage();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @TargetApi(8)
  public boolean getBlockNetworkLoads()
  {
    monitorenter;
    try
    {
      boolean bool1;
      if (this.isUseX5)
      {
        boolean bool3 = this.mWebSettingsImpl.getBlockNetworkLoads();
        bool1 = bool3;
      }
      while (true)
      {
        return bool1;
        if (Build.VERSION.SDK_INT >= 8)
        {
          boolean bool2 = this.mSystemWebSettings.getBlockNetworkLoads();
          bool1 = bool2;
          continue;
        }
        bool1 = false;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @TargetApi(3)
  public boolean getBuiltInZoomControls()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.getBuiltInZoomControls();
    return this.mSystemWebSettings.getBuiltInZoomControls();
  }

  public int getCacheMode()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.getCacheMode();
    return this.mSystemWebSettings.getCacheMode();
  }

  public String getCursiveFontFamily()
  {
    monitorenter;
    try
    {
      String str2;
      if (this.isUseX5)
        str2 = this.mWebSettingsImpl.getCursiveFontFamily();
      String str1;
      for (Object localObject2 = str2; ; localObject2 = str1)
      {
        return localObject2;
        str1 = this.mSystemWebSettings.getCursiveFontFamily();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  @TargetApi(5)
  public boolean getDatabaseEnabled()
  {
    monitorenter;
    try
    {
      boolean bool3;
      if (this.isUseX5)
        bool3 = this.mWebSettingsImpl.getDatabaseEnabled();
      boolean bool1;
      for (boolean bool2 = bool3; ; bool2 = bool1)
      {
        return bool2;
        bool1 = this.mSystemWebSettings.getDatabaseEnabled();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @TargetApi(5)
  public String getDatabasePath()
  {
    monitorenter;
    try
    {
      String str2;
      if (this.isUseX5)
        str2 = this.mWebSettingsImpl.getDatabasePath();
      String str1;
      for (Object localObject2 = str2; ; localObject2 = str1)
      {
        return localObject2;
        str1 = this.mSystemWebSettings.getDatabasePath();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public int getDefaultFixedFontSize()
  {
    monitorenter;
    try
    {
      int k;
      if (this.isUseX5)
        k = this.mWebSettingsImpl.getDefaultFixedFontSize();
      int i;
      for (int j = k; ; j = i)
      {
        return j;
        i = this.mSystemWebSettings.getDefaultFixedFontSize();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int getDefaultFontSize()
  {
    monitorenter;
    try
    {
      int k;
      if (this.isUseX5)
        k = this.mWebSettingsImpl.getDefaultFontSize();
      int i;
      for (int j = k; ; j = i)
      {
        return j;
        i = this.mSystemWebSettings.getDefaultFontSize();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public String getDefaultTextEncodingName()
  {
    monitorenter;
    try
    {
      String str2;
      if (this.isUseX5)
        str2 = this.mWebSettingsImpl.getDefaultTextEncodingName();
      String str1;
      for (Object localObject2 = str2; ; localObject2 = str1)
      {
        return localObject2;
        str1 = this.mSystemWebSettings.getDefaultTextEncodingName();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  @TargetApi(7)
  public ZoomDensity getDefaultZoom()
  {
    if (this.isUseX5)
      return ZoomDensity.valueOf(this.mWebSettingsImpl.getDefaultZoom().name());
    return ZoomDensity.valueOf(this.mSystemWebSettings.getDefaultZoom().name());
  }

  @TargetApi(11)
  public boolean getDisplayZoomControls()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.getDisplayZoomControls();
    if (Build.VERSION.SDK_INT >= 11)
      return this.mSystemWebSettings.getDisplayZoomControls();
    return false;
  }

  @TargetApi(7)
  public boolean getDomStorageEnabled()
  {
    monitorenter;
    try
    {
      boolean bool3;
      if (this.isUseX5)
        bool3 = this.mWebSettingsImpl.getDomStorageEnabled();
      boolean bool1;
      for (boolean bool2 = bool3; ; bool2 = bool1)
      {
        return bool2;
        bool1 = this.mSystemWebSettings.getDomStorageEnabled();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public String getFantasyFontFamily()
  {
    monitorenter;
    try
    {
      String str2;
      if (this.isUseX5)
        str2 = this.mWebSettingsImpl.getFantasyFontFamily();
      String str1;
      for (Object localObject2 = str2; ; localObject2 = str1)
      {
        return localObject2;
        str1 = this.mSystemWebSettings.getFantasyFontFamily();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public String getFixedFontFamily()
  {
    monitorenter;
    try
    {
      String str2;
      if (this.isUseX5)
        str2 = this.mWebSettingsImpl.getFixedFontFamily();
      String str1;
      for (Object localObject2 = str2; ; localObject2 = str1)
      {
        return localObject2;
        str1 = this.mSystemWebSettings.getFixedFontFamily();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public boolean getJavaScriptCanOpenWindowsAutomatically()
  {
    monitorenter;
    try
    {
      boolean bool3;
      if (this.isUseX5)
        bool3 = this.mWebSettingsImpl.getJavaScriptCanOpenWindowsAutomatically();
      boolean bool1;
      for (boolean bool2 = bool3; ; bool2 = bool1)
      {
        return bool2;
        bool1 = this.mSystemWebSettings.getJavaScriptCanOpenWindowsAutomatically();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean getJavaScriptEnabled()
  {
    monitorenter;
    try
    {
      boolean bool3;
      if (this.isUseX5)
        bool3 = this.mWebSettingsImpl.getJavaScriptEnabled();
      boolean bool1;
      for (boolean bool2 = bool3; ; bool2 = bool1)
      {
        return bool2;
        bool1 = this.mSystemWebSettings.getJavaScriptEnabled();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public LayoutAlgorithm getLayoutAlgorithm()
  {
    monitorenter;
    try
    {
      LayoutAlgorithm localLayoutAlgorithm2;
      if (this.isUseX5)
        localLayoutAlgorithm2 = LayoutAlgorithm.valueOf(this.mWebSettingsImpl.getLayoutAlgorithm().name());
      LayoutAlgorithm localLayoutAlgorithm1;
      for (Object localObject2 = localLayoutAlgorithm2; ; localObject2 = localLayoutAlgorithm1)
      {
        return localObject2;
        localLayoutAlgorithm1 = LayoutAlgorithm.valueOf(this.mSystemWebSettings.getLayoutAlgorithm().name());
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public boolean getLightTouchEnabled()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.getLightTouchEnabled();
    return this.mSystemWebSettings.getLightTouchEnabled();
  }

  @TargetApi(7)
  public boolean getLoadWithOverviewMode()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.getLoadWithOverviewMode();
    return this.mSystemWebSettings.getLoadWithOverviewMode();
  }

  public boolean getLoadsImagesAutomatically()
  {
    monitorenter;
    try
    {
      boolean bool3;
      if (this.isUseX5)
        bool3 = this.mWebSettingsImpl.getLoadsImagesAutomatically();
      boolean bool1;
      for (boolean bool2 = bool3; ; bool2 = bool1)
      {
        return bool2;
        bool1 = this.mSystemWebSettings.getLoadsImagesAutomatically();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @TargetApi(17)
  public boolean getMediaPlaybackRequiresUserGesture()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.getMediaPlaybackRequiresUserGesture();
    if (Build.VERSION.SDK_INT >= 17)
      return this.mSystemWebSettings.getMediaPlaybackRequiresUserGesture();
    return false;
  }

  public int getMinimumFontSize()
  {
    monitorenter;
    try
    {
      int k;
      if (this.isUseX5)
        k = this.mWebSettingsImpl.getMinimumFontSize();
      int i;
      for (int j = k; ; j = i)
      {
        return j;
        i = this.mSystemWebSettings.getMinimumFontSize();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int getMinimumLogicalFontSize()
  {
    monitorenter;
    try
    {
      int k;
      if (this.isUseX5)
        k = this.mWebSettingsImpl.getMinimumLogicalFontSize();
      int i;
      for (int j = k; ; j = i)
      {
        return j;
        i = this.mSystemWebSettings.getMinimumLogicalFontSize();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean getNavDump()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.getNavDump();
    Object localObject = ReflectionUtils.invokeInstance(this.mSystemWebSettings, "getNavDump");
    if (localObject == null)
      return false;
    return ((Boolean)localObject).booleanValue();
  }

  @Deprecated
  @TargetApi(8)
  public PluginState getPluginState()
  {
    monitorenter;
    try
    {
      Object localObject2;
      if (this.isUseX5)
      {
        PluginState localPluginState2 = PluginState.valueOf(this.mWebSettingsImpl.getPluginState().name());
        localObject2 = localPluginState2;
      }
      while (true)
      {
        return localObject2;
        int i = Build.VERSION.SDK_INT;
        localObject2 = null;
        if (i < 8)
          continue;
        Object localObject3 = ReflectionUtils.invokeInstance(this.mSystemWebSettings, "getPluginState");
        localObject2 = null;
        if (localObject3 == null)
          continue;
        PluginState localPluginState1 = PluginState.valueOf(((android.webkit.WebSettings.PluginState)localObject3).name());
        localObject2 = localPluginState1;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  @Deprecated
  public boolean getPluginsEnabled()
  {
    monitorenter;
    try
    {
      boolean bool1;
      if (this.isUseX5)
      {
        boolean bool2 = this.mWebSettingsImpl.getPluginsEnabled();
        bool1 = bool2;
      }
      while (true)
      {
        return bool1;
        if (Build.VERSION.SDK_INT <= 17)
        {
          Object localObject2 = ReflectionUtils.invokeInstance(this.mSystemWebSettings, "getPluginsEnabled");
          bool1 = false;
          if (localObject2 == null)
            continue;
          bool1 = ((Boolean)localObject2).booleanValue();
          continue;
        }
        int i = Build.VERSION.SDK_INT;
        bool1 = false;
        if (i != 18)
          continue;
        android.webkit.WebSettings.PluginState localPluginState1 = this.mSystemWebSettings.getPluginState();
        android.webkit.WebSettings.PluginState localPluginState2 = android.webkit.WebSettings.PluginState.ON;
        bool1 = false;
        if (localPluginState2 != localPluginState1)
          continue;
        bool1 = true;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  @Deprecated
  public String getPluginsPath()
  {
    monitorenter;
    try
    {
      String str1;
      if (this.isUseX5)
      {
        String str2 = this.mWebSettingsImpl.getPluginsPath();
        str1 = str2;
      }
      while (true)
      {
        return str1;
        if (Build.VERSION.SDK_INT <= 17)
        {
          Object localObject2 = ReflectionUtils.invokeInstance(this.mSystemWebSettings, "getPluginsPath");
          if (localObject2 == null)
          {
            str1 = null;
            continue;
          }
          str1 = (String)localObject2;
          continue;
        }
        str1 = null;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public String getSansSerifFontFamily()
  {
    monitorenter;
    try
    {
      String str2;
      if (this.isUseX5)
        str2 = this.mWebSettingsImpl.getSansSerifFontFamily();
      String str1;
      for (Object localObject2 = str2; ; localObject2 = str1)
      {
        return localObject2;
        str1 = this.mSystemWebSettings.getSansSerifFontFamily();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public boolean getSaveFormData()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.getSaveFormData();
    return this.mSystemWebSettings.getSaveFormData();
  }

  public boolean getSavePassword()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.getSavePassword();
    return this.mSystemWebSettings.getSavePassword();
  }

  public String getSerifFontFamily()
  {
    monitorenter;
    try
    {
      String str2;
      if (this.isUseX5)
        str2 = this.mWebSettingsImpl.getSerifFontFamily();
      String str1;
      for (Object localObject2 = str2; ; localObject2 = str1)
      {
        return localObject2;
        str1 = this.mSystemWebSettings.getSerifFontFamily();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public String getStandardFontFamily()
  {
    monitorenter;
    try
    {
      String str2;
      if (this.isUseX5)
        str2 = this.mWebSettingsImpl.getStandardFontFamily();
      String str1;
      for (Object localObject2 = str2; ; localObject2 = str1)
      {
        return localObject2;
        str1 = this.mSystemWebSettings.getStandardFontFamily();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public TextSize getTextSize()
  {
    if (this.isUseX5)
      return TextSize.valueOf(this.mWebSettingsImpl.getTextSize().name());
    return TextSize.valueOf(this.mSystemWebSettings.getTextSize().name());
  }

  @TargetApi(14)
  public int getTextZoom()
  {
    monitorenter;
    try
    {
      int i;
      if (this.isUseX5)
      {
        int k = this.mWebSettingsImpl.getTextZoom();
        i = k;
      }
      while (true)
      {
        return i;
        if (Build.VERSION.SDK_INT >= 14)
        {
          int j = this.mSystemWebSettings.getTextZoom();
          i = j;
          continue;
        }
        i = 0;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @Deprecated
  public boolean getUseWebViewBackgroundForOverscrollBackground()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.getUseWebViewBackgroundForOverscrollBackground();
    Object localObject = ReflectionUtils.invokeInstance(this.mSystemWebSettings, "getUseWebViewBackgroundForOverscrollBackground");
    if (localObject == null)
      return false;
    return ((Boolean)localObject).booleanValue();
  }

  public boolean getUseWideViewPort()
  {
    monitorenter;
    try
    {
      boolean bool3;
      if (this.isUseX5)
        bool3 = this.mWebSettingsImpl.getUseWideViewPort();
      boolean bool1;
      for (boolean bool2 = bool3; ; bool2 = bool1)
      {
        return bool2;
        bool1 = this.mSystemWebSettings.getUseWideViewPort();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @TargetApi(3)
  public String getUserAgentString()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.getUserAgentString();
    return this.mSystemWebSettings.getUserAgentString();
  }

  @TargetApi(11)
  public void setAllowContentAccess(boolean paramBoolean)
  {
    if (this.isUseX5)
      this.mWebSettingsImpl.setAllowContentAccess(paramBoolean);
    do
      return;
    while (Build.VERSION.SDK_INT < 11);
    this.mSystemWebSettings.setAllowContentAccess(paramBoolean);
  }

  @TargetApi(3)
  public void setAllowFileAccess(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setAllowFileAccess(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setAllowFileAccess(paramBoolean);
  }

  @TargetApi(16)
  public void setAllowFileAccessFromFileURLs(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setAllowFileAccessFromFileURLs(paramBoolean);
      return;
    }
    android.webkit.WebSettings localWebSettings = this.mSystemWebSettings;
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = Boolean.TYPE;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    ReflectionUtils.invokeInstance(localWebSettings, "setAllowFileAccessFromFileURLs", arrayOfClass, arrayOfObject);
  }

  @TargetApi(16)
  public void setAllowUniversalAccessFromFileURLs(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setAllowUniversalAccessFromFileURLs(paramBoolean);
      return;
    }
    android.webkit.WebSettings localWebSettings = this.mSystemWebSettings;
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = Boolean.TYPE;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    ReflectionUtils.invokeInstance(localWebSettings, "setAllowUniversalAccessFromFileURLs", arrayOfClass, arrayOfObject);
  }

  @TargetApi(7)
  public void setAppCacheEnabled(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setAppCacheEnabled(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setAppCacheEnabled(paramBoolean);
  }

  @TargetApi(7)
  public void setAppCacheMaxSize(long paramLong)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setAppCacheMaxSize(paramLong);
      return;
    }
    this.mSystemWebSettings.setAppCacheMaxSize(paramLong);
  }

  @TargetApi(7)
  public void setAppCachePath(String paramString)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setAppCachePath(paramString);
      return;
    }
    this.mSystemWebSettings.setAppCachePath(paramString);
  }

  public void setBlockNetworkImage(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setBlockNetworkImage(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setBlockNetworkImage(paramBoolean);
  }

  @TargetApi(8)
  public void setBlockNetworkLoads(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setBlockNetworkLoads(paramBoolean);
      while (true)
      {
        return;
        if (Build.VERSION.SDK_INT < 8)
          continue;
        this.mSystemWebSettings.setBlockNetworkLoads(paramBoolean);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @TargetApi(3)
  public void setBuiltInZoomControls(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setBuiltInZoomControls(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setBuiltInZoomControls(paramBoolean);
  }

  public void setCacheMode(int paramInt)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setCacheMode(paramInt);
      return;
    }
    this.mSystemWebSettings.setCacheMode(paramInt);
  }

  public void setCursiveFontFamily(String paramString)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setCursiveFontFamily(paramString);
      while (true)
      {
        return;
        this.mSystemWebSettings.setCursiveFontFamily(paramString);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @TargetApi(5)
  public void setDatabaseEnabled(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setDatabaseEnabled(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setDatabaseEnabled(paramBoolean);
  }

  @Deprecated
  @TargetApi(5)
  public void setDatabasePath(String paramString)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setDatabasePath(paramString);
      return;
    }
    ReflectionUtils.invokeInstance(this.mSystemWebSettings, "setDatabasePath", new Class[] { String.class }, new Object[] { paramString });
  }

  public void setDefaultFixedFontSize(int paramInt)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setDefaultFixedFontSize(paramInt);
      while (true)
      {
        return;
        this.mSystemWebSettings.setDefaultFixedFontSize(paramInt);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setDefaultFontSize(int paramInt)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setDefaultFontSize(paramInt);
      while (true)
      {
        return;
        this.mSystemWebSettings.setDefaultFontSize(paramInt);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setDefaultTextEncodingName(String paramString)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setDefaultTextEncodingName(paramString);
      while (true)
      {
        return;
        this.mSystemWebSettings.setDefaultTextEncodingName(paramString);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @TargetApi(7)
  public void setDefaultZoom(ZoomDensity paramZoomDensity)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setDefaultZoom(IX5WebSettings.ZoomDensity.valueOf(paramZoomDensity.name()));
      return;
    }
    this.mSystemWebSettings.setDefaultZoom(android.webkit.WebSettings.ZoomDensity.valueOf(paramZoomDensity.name()));
  }

  @TargetApi(11)
  public void setDisplayZoomControls(boolean paramBoolean)
  {
    if (this.isUseX5)
      this.mWebSettingsImpl.setDisplayZoomControls(paramBoolean);
    do
      return;
    while (Build.VERSION.SDK_INT < 11);
    this.mSystemWebSettings.setDisplayZoomControls(paramBoolean);
  }

  @TargetApi(7)
  public void setDomStorageEnabled(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setDomStorageEnabled(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setDomStorageEnabled(paramBoolean);
  }

  @Deprecated
  @TargetApi(11)
  public void setEnableSmoothTransition(boolean paramBoolean)
  {
    if (this.isUseX5)
      this.mWebSettingsImpl.setEnableSmoothTransition(paramBoolean);
    do
      return;
    while (Build.VERSION.SDK_INT < 11);
    android.webkit.WebSettings localWebSettings = this.mSystemWebSettings;
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = Boolean.TYPE;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    ReflectionUtils.invokeInstance(localWebSettings, "setEnableSmoothTransition", arrayOfClass, arrayOfObject);
  }

  public void setFantasyFontFamily(String paramString)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setFantasyFontFamily(paramString);
      while (true)
      {
        return;
        this.mSystemWebSettings.setFantasyFontFamily(paramString);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setFixedFontFamily(String paramString)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setFixedFontFamily(paramString);
      while (true)
      {
        return;
        this.mSystemWebSettings.setFixedFontFamily(paramString);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @TargetApi(5)
  public void setGeolocationDatabasePath(String paramString)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setGeolocationDatabasePath(paramString);
      return;
    }
    this.mSystemWebSettings.setGeolocationDatabasePath(paramString);
  }

  @TargetApi(5)
  public void setGeolocationEnabled(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setGeolocationEnabled(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setGeolocationEnabled(paramBoolean);
  }

  public void setJavaScriptCanOpenWindowsAutomatically(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setJavaScriptCanOpenWindowsAutomatically(paramBoolean);
      while (true)
      {
        return;
        this.mSystemWebSettings.setJavaScriptCanOpenWindowsAutomatically(paramBoolean);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @Deprecated
  public void setJavaScriptEnabled(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setJavaScriptEnabled(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setJavaScriptEnabled(paramBoolean);
  }

  public void setLayoutAlgorithm(LayoutAlgorithm paramLayoutAlgorithm)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setLayoutAlgorithm(IX5WebSettings.LayoutAlgorithm.valueOf(paramLayoutAlgorithm.name()));
      return;
    }
    this.mSystemWebSettings.setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.valueOf(paramLayoutAlgorithm.name()));
  }

  public void setLightTouchEnabled(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setLightTouchEnabled(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setLightTouchEnabled(paramBoolean);
  }

  @TargetApi(7)
  public void setLoadWithOverviewMode(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setLoadWithOverviewMode(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setLoadWithOverviewMode(paramBoolean);
  }

  public void setLoadsImagesAutomatically(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setLoadsImagesAutomatically(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setLoadsImagesAutomatically(paramBoolean);
  }

  @TargetApi(17)
  public void setMediaPlaybackRequiresUserGesture(boolean paramBoolean)
  {
    if (this.isUseX5)
      this.mWebSettingsImpl.setMediaPlaybackRequiresUserGesture(paramBoolean);
    do
      return;
    while (Build.VERSION.SDK_INT < 17);
    this.mSystemWebSettings.setMediaPlaybackRequiresUserGesture(paramBoolean);
  }

  public void setMinimumFontSize(int paramInt)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setMinimumFontSize(paramInt);
      while (true)
      {
        return;
        this.mSystemWebSettings.setMinimumFontSize(paramInt);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setMinimumLogicalFontSize(int paramInt)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setMinimumLogicalFontSize(paramInt);
      while (true)
      {
        return;
        this.mSystemWebSettings.setMinimumLogicalFontSize(paramInt);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setNavDump(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setNavDump(paramBoolean);
      return;
    }
    android.webkit.WebSettings localWebSettings = this.mSystemWebSettings;
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = Boolean.TYPE;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    ReflectionUtils.invokeInstance(localWebSettings, "setNavDump", arrayOfClass, arrayOfObject);
  }

  public void setNeedInitialFocus(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setNeedInitialFocus(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setNeedInitialFocus(paramBoolean);
  }

  @Deprecated
  @TargetApi(8)
  public void setPluginState(PluginState paramPluginState)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setPluginState(IX5WebSettings.PluginState.valueOf(paramPluginState.name()));
      while (true)
      {
        return;
        if (Build.VERSION.SDK_INT < 8)
          continue;
        android.webkit.WebSettings.PluginState localPluginState = android.webkit.WebSettings.PluginState.valueOf(paramPluginState.name());
        ReflectionUtils.invokeInstance(this.mSystemWebSettings, "setPluginState", new Class[] { android.webkit.WebSettings.PluginState.class }, new Object[] { localPluginState });
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @Deprecated
  public void setPluginsEnabled(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setPluginsEnabled(paramBoolean);
      return;
    }
    android.webkit.WebSettings localWebSettings = this.mSystemWebSettings;
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = Boolean.TYPE;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    ReflectionUtils.invokeInstance(localWebSettings, "setPluginsEnabled", arrayOfClass, arrayOfObject);
  }

  @Deprecated
  public void setPluginsPath(String paramString)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setPluginsPath(paramString);
      while (true)
      {
        return;
        ReflectionUtils.invokeInstance(this.mSystemWebSettings, "setPluginsPath", new Class[] { String.class }, new Object[] { paramString });
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setRenderPriority(RenderPriority paramRenderPriority)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setRenderPriority(IX5WebSettings.RenderPriority.valueOf(paramRenderPriority.name()));
      return;
    }
    this.mSystemWebSettings.setRenderPriority(android.webkit.WebSettings.RenderPriority.valueOf(paramRenderPriority.name()));
  }

  public void setSansSerifFontFamily(String paramString)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setSansSerifFontFamily(paramString);
      while (true)
      {
        return;
        this.mSystemWebSettings.setSansSerifFontFamily(paramString);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setSaveFormData(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setSaveFormData(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setSaveFormData(paramBoolean);
  }

  public void setSavePassword(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setSavePassword(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setSavePassword(paramBoolean);
  }

  public void setSerifFontFamily(String paramString)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setSerifFontFamily(paramString);
      while (true)
      {
        return;
        this.mSystemWebSettings.setSerifFontFamily(paramString);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setStandardFontFamily(String paramString)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setStandardFontFamily(paramString);
      while (true)
      {
        return;
        this.mSystemWebSettings.setStandardFontFamily(paramString);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setSupportMultipleWindows(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setSupportMultipleWindows(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setSupportMultipleWindows(paramBoolean);
  }

  public void setSupportZoom(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setSupportZoom(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setSupportZoom(paramBoolean);
  }

  public void setTextSize(TextSize paramTextSize)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setTextSize(IX5WebSettings.TextSize.valueOf(paramTextSize.name()));
      return;
    }
    this.mSystemWebSettings.setTextSize(android.webkit.WebSettings.TextSize.valueOf(paramTextSize.name()));
  }

  @TargetApi(14)
  public void setTextZoom(int paramInt)
  {
    monitorenter;
    try
    {
      if (this.isUseX5)
        this.mWebSettingsImpl.setTextZoom(paramInt);
      while (true)
      {
        return;
        if (Build.VERSION.SDK_INT < 14)
          continue;
        this.mSystemWebSettings.setTextZoom(paramInt);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @Deprecated
  public void setUseWebViewBackgroundForOverscrollBackground(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setUseWebViewBackgroundForOverscrollBackground(paramBoolean);
      return;
    }
    android.webkit.WebSettings localWebSettings = this.mSystemWebSettings;
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = Boolean.TYPE;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    ReflectionUtils.invokeInstance(localWebSettings, "setUseWebViewBackgroundForOverscrollBackground", arrayOfClass, arrayOfObject);
  }

  public void setUseWideViewPort(boolean paramBoolean)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setUseWideViewPort(paramBoolean);
      return;
    }
    this.mSystemWebSettings.setUseWideViewPort(paramBoolean);
  }

  public void setUserAgent(String paramString)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setUserAgent(paramString);
      return;
    }
    this.mSystemWebSettings.setUserAgentString(paramString);
  }

  @TargetApi(3)
  public void setUserAgentString(String paramString)
  {
    if (this.isUseX5)
    {
      this.mWebSettingsImpl.setUserAgentString(paramString);
      return;
    }
    this.mSystemWebSettings.setUserAgentString(paramString);
  }

  public boolean supportMultipleWindows()
  {
    monitorenter;
    try
    {
      boolean bool3;
      if (this.isUseX5)
        bool3 = this.mWebSettingsImpl.supportMultipleWindows();
      boolean bool1;
      for (boolean bool2 = bool3; ; bool2 = bool1)
      {
        return bool2;
        bool1 = this.mSystemWebSettings.supportMultipleWindows();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean supportZoom()
  {
    if (this.isUseX5)
      return this.mWebSettingsImpl.supportZoom();
    return this.mSystemWebSettings.supportZoom();
  }

  public static enum LayoutAlgorithm
  {
    static
    {
      NARROW_COLUMNS = new LayoutAlgorithm("NARROW_COLUMNS", 2);
      LayoutAlgorithm[] arrayOfLayoutAlgorithm = new LayoutAlgorithm[3];
      arrayOfLayoutAlgorithm[0] = NORMAL;
      arrayOfLayoutAlgorithm[1] = SINGLE_COLUMN;
      arrayOfLayoutAlgorithm[2] = NARROW_COLUMNS;
      $VALUES = arrayOfLayoutAlgorithm;
    }
  }

  public static enum PluginState
  {
    static
    {
      OFF = new PluginState("OFF", 2);
      PluginState[] arrayOfPluginState = new PluginState[3];
      arrayOfPluginState[0] = ON;
      arrayOfPluginState[1] = ON_DEMAND;
      arrayOfPluginState[2] = OFF;
      $VALUES = arrayOfPluginState;
    }
  }

  public static enum RenderPriority
  {
    static
    {
      HIGH = new RenderPriority("HIGH", 1);
      LOW = new RenderPriority("LOW", 2);
      RenderPriority[] arrayOfRenderPriority = new RenderPriority[3];
      arrayOfRenderPriority[0] = NORMAL;
      arrayOfRenderPriority[1] = HIGH;
      arrayOfRenderPriority[2] = LOW;
      $VALUES = arrayOfRenderPriority;
    }
  }

  public static enum TextSize
  {
    int value;

    static
    {
      SMALLER = new TextSize("SMALLER", 1, 75);
      NORMAL = new TextSize("NORMAL", 2, 100);
      LARGER = new TextSize("LARGER", 3, 125);
      LARGEST = new TextSize("LARGEST", 4, 150);
      TextSize[] arrayOfTextSize = new TextSize[5];
      arrayOfTextSize[0] = SMALLEST;
      arrayOfTextSize[1] = SMALLER;
      arrayOfTextSize[2] = NORMAL;
      arrayOfTextSize[3] = LARGER;
      arrayOfTextSize[4] = LARGEST;
      $VALUES = arrayOfTextSize;
    }

    private TextSize(int paramInt)
    {
      this.value = paramInt;
    }
  }

  public static enum ZoomDensity
  {
    int value;

    static
    {
      CLOSE = new ZoomDensity("CLOSE", 2, 75);
      ZoomDensity[] arrayOfZoomDensity = new ZoomDensity[3];
      arrayOfZoomDensity[0] = FAR;
      arrayOfZoomDensity[1] = MEDIUM;
      arrayOfZoomDensity[2] = CLOSE;
      $VALUES = arrayOfZoomDensity;
    }

    private ZoomDensity(int paramInt)
    {
      this.value = paramInt;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.WebSettings
 * JD-Core Version:    0.6.0
 */