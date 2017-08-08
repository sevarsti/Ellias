package com.tencent.component.net.http.strategy;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=8)
public class StrategyInfo
{

  @PluginApi(a=8)
  public boolean allowProxy;

  @PluginApi(a=8)
  public boolean useConfigApn;

  @PluginApi(a=8)
  public StrategyInfo(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.allowProxy = paramBoolean1;
    this.useConfigApn = paramBoolean2;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    StrategyInfo localStrategyInfo;
    do
    {
      return true;
      if (paramObject == null)
        return false;
      if (getClass() != paramObject.getClass())
        return false;
      localStrategyInfo = (StrategyInfo)paramObject;
      if (this.allowProxy != localStrategyInfo.allowProxy)
        return false;
    }
    while (this.useConfigApn == localStrategyInfo.useConfigApn);
    return false;
  }

  public int hashCode()
  {
    int i = 1231;
    int j;
    int k;
    if (this.allowProxy)
    {
      j = i;
      k = 31 * (j + 31);
      if (!this.useConfigApn)
        break label39;
    }
    while (true)
    {
      return k + i;
      j = 1237;
      break;
      label39: i = 1237;
    }
  }

  public String toString()
  {
    return "StrategyInfo [allowProxy=" + this.allowProxy + ", useConfigApn=" + this.useConfigApn + "]";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.strategy.StrategyInfo
 * JD-Core Version:    0.6.0
 */