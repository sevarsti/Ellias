package com.tencent.qqgamemi.plugin.api;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=9)
public abstract interface QMiApi$GameEngineCallback
{
  @PluginApi(a=9)
  public abstract int onBeginDraw();

  @PluginApi(a=9)
  public abstract int onEndDraw();

  @PluginApi(a=9)
  public abstract void onGameEnterBackground();

  @PluginApi(a=9)
  public abstract void onGameEnterForeground();

  @PluginApi(a=9)
  public abstract void onStartRecordVideo();

  @PluginApi(a=15)
  public abstract void onStartRecordVideo(long paramLong);

  @PluginApi(a=9)
  public abstract void onStopRecordVideo();

  @PluginApi(a=9)
  public abstract void onUpdateVideoFrame();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.api.QMiApi.GameEngineCallback
 * JD-Core Version:    0.6.0
 */