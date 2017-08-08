package com.tencent.component.utils;

import android.text.TextUtils;

public class NetworkUtil$NetworkProxy
  implements Cloneable
{
  public final String a;
  public final int b;

  NetworkUtil$NetworkProxy(String paramString, int paramInt)
  {
    this.a = paramString;
    this.b = paramInt;
  }

  final NetworkProxy a()
  {
    try
    {
      NetworkProxy localNetworkProxy = (NetworkProxy)clone();
      return localNetworkProxy;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      localCloneNotSupportedException.printStackTrace();
    }
    return null;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    NetworkProxy localNetworkProxy;
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof NetworkProxy)))
        break;
      localNetworkProxy = (NetworkProxy)paramObject;
    }
    while ((TextUtils.equals(this.a, localNetworkProxy.a)) && (this.b == localNetworkProxy.b));
    return false;
  }

  public String toString()
  {
    return this.a + ":" + this.b;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.NetworkUtil.NetworkProxy
 * JD-Core Version:    0.6.0
 */