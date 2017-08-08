package com.tencent.component.net.socket;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public class SocketConfigure
{
  public String a;
  public int b;

  @PluginApi(a=6)
  public SocketConfigure()
  {
  }

  @PluginApi(a=6)
  public SocketConfigure(String paramString, int paramInt)
  {
    this.a = paramString;
    this.b = paramInt;
  }

  public static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      return null;
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
      if (str.length() < 2)
        localStringBuilder.append(0);
      localStringBuilder.append(str).append(' ');
    }
    return localStringBuilder.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.SocketConfigure
 * JD-Core Version:    0.6.0
 */