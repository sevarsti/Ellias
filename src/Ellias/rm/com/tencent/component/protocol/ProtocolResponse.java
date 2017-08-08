package com.tencent.component.protocol;

import com.qq.taf.jce.JceStruct;
import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public class ProtocolResponse
{
  private JceStruct a;
  private int b;
  private String c;
  private int d;
  private boolean e = true;

  @PluginApi(a=6)
  public ProtocolResponse()
  {
  }

  @PluginApi(a=6)
  public ProtocolResponse(boolean paramBoolean)
  {
    this.e = paramBoolean;
  }

  @PluginApi(a=6)
  public JceStruct getBusiResponse()
  {
    return this.a;
  }

  @PluginApi(a=6)
  public int getResultCode()
  {
    return this.b;
  }

  @PluginApi(a=6)
  public String getResultMsg()
  {
    return this.c;
  }

  @PluginApi(a=6)
  public int getTimestamp()
  {
    return this.d;
  }

  @PluginApi(a=6)
  public boolean isFake()
  {
    return this.e;
  }

  @PluginApi(a=6)
  public void setBusiResponse(JceStruct paramJceStruct)
  {
    this.a = paramJceStruct;
  }

  @PluginApi(a=6)
  public void setFake(boolean paramBoolean)
  {
    this.e = paramBoolean;
  }

  @PluginApi(a=6)
  public void setResultCode(int paramInt)
  {
    this.b = paramInt;
  }

  @PluginApi(a=6)
  public void setResultMsg(String paramString)
  {
    this.c = paramString;
  }

  @PluginApi(a=6)
  public void setTimestamp(int paramInt)
  {
    this.d = paramInt;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.protocol.ProtocolResponse
 * JD-Core Version:    0.6.0
 */