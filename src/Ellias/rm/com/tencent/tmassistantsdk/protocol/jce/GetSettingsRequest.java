package com.tencent.tmassistantsdk.protocol.jce;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class GetSettingsRequest extends JceStruct
  implements Cloneable
{
  public String reserve = "";

  static
  {
    if (!GetSettingsRequest.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      a = bool;
      return;
    }
  }

  public GetSettingsRequest()
  {
  }

  public GetSettingsRequest(String paramString)
  {
    this.reserve = paramString;
  }

  public String className()
  {
    return "jce.GetSettingsRequest";
  }

  public Object clone()
  {
    try
    {
      Object localObject2 = super.clone();
      localObject1 = localObject2;
      return localObject1;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      boolean bool;
      do
      {
        bool = a;
        Object localObject1 = null;
      }
      while (bool);
    }
    throw new AssertionError();
  }

  public void display(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).display(this.reserve, "reserve");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.reserve, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    GetSettingsRequest localGetSettingsRequest = (GetSettingsRequest)paramObject;
    return JceUtil.equals(this.reserve, localGetSettingsRequest.reserve);
  }

  public String fullClassName()
  {
    return "com.tencent.tmassistantsdk.protocol.jce.GetSettingsRequest";
  }

  public String getReserve()
  {
    return this.reserve;
  }

  public int hashCode()
  {
    try
    {
      throw new Exception("Need define key first!");
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }

  public void readFrom(JceInputStream paramJceInputStream)
  {
    this.reserve = paramJceInputStream.readString(0, false);
  }

  public void setReserve(String paramString)
  {
    this.reserve = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    if (this.reserve != null)
      paramJceOutputStream.write(this.reserve, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.protocol.jce.GetSettingsRequest
 * JD-Core Version:    0.6.0
 */