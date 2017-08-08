package com.tencent.smtt.export.external.interfaces;

public abstract interface IX5WebSettings
{
  public static final int DEFAULT_CACHE_CAPACITY = 15;
  public static final int LOAD_CACHE_AD = 100;
  public static final int LOAD_CACHE_ELSE_NETWORK = 1;
  public static final int LOAD_CACHE_ONLY = 3;
  public static final int LOAD_DEFAULT = -1;
  public static final int LOAD_NORMAL = 0;
  public static final int LOAD_NO_CACHE = 2;

  public abstract boolean enableSmoothTransition();

  public abstract boolean getAllowContentAccess();

  public abstract boolean getAllowFileAccess();

  public abstract boolean getBlockNetworkImage();

  public abstract boolean getBlockNetworkLoads();

  public abstract boolean getBuiltInZoomControls();

  public abstract int getCacheMode();

  public abstract String getCursiveFontFamily();

  public abstract boolean getDatabaseEnabled();

  public abstract String getDatabasePath();

  public abstract int getDefaultFixedFontSize();

  public abstract int getDefaultFontSize();

  public abstract String getDefaultTextEncodingName();

  public abstract ZoomDensity getDefaultZoom();

  public abstract boolean getDisplayZoomControls();

  public abstract boolean getDomStorageEnabled();

  public abstract String getFantasyFontFamily();

  public abstract String getFixedFontFamily();

  public abstract boolean getJavaScriptCanOpenWindowsAutomatically();

  public abstract boolean getJavaScriptEnabled();

  public abstract LayoutAlgorithm getLayoutAlgorithm();

  public abstract boolean getLightTouchEnabled();

  public abstract boolean getLoadWithOverviewMode();

  public abstract boolean getLoadsImagesAutomatically();

  public abstract boolean getMediaPlaybackRequiresUserGesture();

  public abstract int getMinimumFontSize();

  public abstract int getMinimumLogicalFontSize();

  public abstract boolean getNavDump();

  public abstract PluginState getPluginState();

  public abstract boolean getPluginsEnabled();

  public abstract String getPluginsPath();

  public abstract String getSansSerifFontFamily();

  public abstract boolean getSaveFormData();

  public abstract boolean getSavePassword();

  public abstract String getSerifFontFamily();

  public abstract String getStandardFontFamily();

  public abstract TextSize getTextSize();

  public abstract int getTextZoom();

  public abstract boolean getUseWebViewBackgroundForOverscrollBackground();

  public abstract boolean getUseWideViewPort();

  public abstract String getUserAgent();

  public abstract String getUserAgentString();

  public abstract void setAllowContentAccess(boolean paramBoolean);

  public abstract void setAllowFileAccess(boolean paramBoolean);

  public abstract void setAllowFileAccessFromFileURLs(boolean paramBoolean);

  public abstract void setAllowUniversalAccessFromFileURLs(boolean paramBoolean);

  public abstract void setAppCacheEnabled(boolean paramBoolean);

  public abstract void setAppCacheMaxSize(long paramLong);

  public abstract void setAppCachePath(String paramString);

  public abstract void setBlockNetworkImage(boolean paramBoolean);

  public abstract void setBlockNetworkLoads(boolean paramBoolean);

  public abstract void setBuiltInZoomControls(boolean paramBoolean);

  public abstract void setCacheMode(int paramInt);

  public abstract void setCursiveFontFamily(String paramString);

  public abstract void setDatabaseEnabled(boolean paramBoolean);

  public abstract void setDatabasePath(String paramString);

  public abstract void setDefaultDatabasePath(boolean paramBoolean);

  public abstract void setDefaultFixedFontSize(int paramInt);

  public abstract void setDefaultFontSize(int paramInt);

  public abstract void setDefaultTextEncodingName(String paramString);

  public abstract void setDefaultZoom(ZoomDensity paramZoomDensity);

  public abstract void setDisplayZoomControls(boolean paramBoolean);

  public abstract void setDomStorageEnabled(boolean paramBoolean);

  public abstract void setEnableSmoothTransition(boolean paramBoolean);

  public abstract void setFantasyFontFamily(String paramString);

  public abstract void setFixedFontFamily(String paramString);

  public abstract void setGeolocationDatabasePath(String paramString);

  public abstract void setGeolocationEnabled(boolean paramBoolean);

  public abstract void setJavaScriptCanOpenWindowsAutomatically(boolean paramBoolean);

  public abstract void setJavaScriptEnabled(boolean paramBoolean);

  public abstract void setLayoutAlgorithm(LayoutAlgorithm paramLayoutAlgorithm);

  public abstract void setLightTouchEnabled(boolean paramBoolean);

  public abstract void setLoadWithOverviewMode(boolean paramBoolean);

  public abstract void setLoadsImagesAutomatically(boolean paramBoolean);

  public abstract void setMediaPlaybackRequiresUserGesture(boolean paramBoolean);

  public abstract void setMinimumFontSize(int paramInt);

  public abstract void setMinimumLogicalFontSize(int paramInt);

  public abstract void setNavDump(boolean paramBoolean);

  public abstract void setNeedInitialFocus(boolean paramBoolean);

  public abstract void setPluginEnabled(boolean paramBoolean);

  public abstract void setPluginState(PluginState paramPluginState);

  public abstract void setPluginsEnabled(boolean paramBoolean);

  public abstract void setPluginsPath(String paramString);

  public abstract void setRenderPriority(RenderPriority paramRenderPriority);

  public abstract void setSansSerifFontFamily(String paramString);

  public abstract void setSaveFormData(boolean paramBoolean);

  public abstract void setSavePassword(boolean paramBoolean);

  public abstract void setSerifFontFamily(String paramString);

  public abstract void setStandardFontFamily(String paramString);

  public abstract void setSupportMultipleWindows(boolean paramBoolean);

  public abstract void setSupportZoom(boolean paramBoolean);

  public abstract void setTextSize(TextSize paramTextSize);

  public abstract void setTextZoom(int paramInt);

  public abstract void setUseWebViewBackgroundForOverscrollBackground(boolean paramBoolean);

  public abstract void setUseWideViewPort(boolean paramBoolean);

  public abstract void setUserAgent(String paramString);

  public abstract void setUserAgentString(String paramString);

  public abstract boolean supportMultipleWindows();

  public abstract boolean supportZoom();

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
 * Qualified Name:     com.tencent.smtt.export.external.interfaces.IX5WebSettings
 * JD-Core Version:    0.6.0
 */