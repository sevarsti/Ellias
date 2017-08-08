package com.tencent.qqgamemi.plugin.api;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=12)
public class QMiApi$DefaultGameEngineCallback
  implements QMiApi.GameEngineCallback
{
  @PluginApi(a=12)
  public int onBeginDraw()
  {
    return 0;
  }

  @PluginApi(a=12)
  public int onEndDraw()
  {
    return 0;
  }

  @PluginApi(a=12)
  public void onGameEnterBackground()
  {
  }

  @PluginApi(a=12)
  public void onGameEnterForeground()
  {
  }

  @PluginApi(a=12)
  public void onStartRecordVideo()
  {
  }

  @PluginApi(a=15)
  public void onStartRecordVideo(long paramLong)
  {
  }

  @PluginApi(a=12)
  public void onStopRecordVideo()
  {
  }

  @PluginApi(a=12)
  public void onUpdateVideoFrame()
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.api.QMiApi.DefaultGameEngineCallback
 * JD-Core Version:    0.6.0
 */