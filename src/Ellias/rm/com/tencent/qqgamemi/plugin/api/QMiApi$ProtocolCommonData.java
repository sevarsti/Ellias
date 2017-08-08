package com.tencent.qqgamemi.plugin.api;

import com.tencent.component.annotation.PluginApi;
import com.tencent.qqgamemi.protocol.QMiJceCommonData;

@PluginApi(a=6)
public final class QMiApi$ProtocolCommonData
{
  @PluginApi(a=6)
  public static String getChannel()
  {
    return QMiJceCommonData.f;
  }

  @PluginApi(a=6)
  public static String getCoChannel()
  {
    return QMiJceCommonData.j;
  }

  @PluginApi(a=6)
  public static byte getPlatform()
  {
    return QMiJceCommonData.i;
  }

  @PluginApi(a=6)
  public static short getProtocolVer()
  {
    return QMiJceCommonData.g;
  }

  @PluginApi(a=6)
  public static String getServerUrl()
  {
    return QMiJceCommonData.b();
  }

  @PluginApi(a=6)
  public static String getUUID()
  {
    return QMiJceCommonData.k;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.api.QMiApi.ProtocolCommonData
 * JD-Core Version:    0.6.0
 */