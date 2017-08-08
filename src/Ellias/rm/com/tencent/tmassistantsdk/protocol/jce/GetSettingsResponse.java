package com.tencent.tmassistantsdk.protocol.jce;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class GetSettingsResponse extends JceStruct
  implements Cloneable
{
  static ArrayList a;
  public int ret = 0;
  public ArrayList settings = null;

  static
  {
    if (!GetSettingsResponse.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      b = bool;
      return;
    }
  }

  public GetSettingsResponse()
  {
  }

  public GetSettingsResponse(int paramInt, ArrayList paramArrayList)
  {
    this.ret = paramInt;
    this.settings = paramArrayList;
  }

  public String className()
  {
    return "jce.GetSettingsResponse";
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
        bool = b;
        Object localObject1 = null;
      }
      while (bool);
    }
    throw new AssertionError();
  }

  public void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.ret, "ret");
    localJceDisplayer.display(this.settings, "settings");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.ret, true);
    localJceDisplayer.displaySimple(this.settings, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    GetSettingsResponse localGetSettingsResponse;
    do
    {
      return false;
      localGetSettingsResponse = (GetSettingsResponse)paramObject;
    }
    while ((!JceUtil.equals(this.ret, localGetSettingsResponse.ret)) || (!JceUtil.equals(this.settings, localGetSettingsResponse.settings)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.tmassistantsdk.protocol.jce.GetSettingsResponse";
  }

  public int getRet()
  {
    return this.ret;
  }

  public ArrayList getSettings()
  {
    return this.settings;
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
    this.ret = paramJceInputStream.read(this.ret, 0, true);
    if (a == null)
    {
      a = new ArrayList();
      SettingsCfg localSettingsCfg = new SettingsCfg();
      a.add(localSettingsCfg);
    }
    this.settings = ((ArrayList)paramJceInputStream.read(a, 1, false));
  }

  public void setRet(int paramInt)
  {
    this.ret = paramInt;
  }

  public void setSettings(ArrayList paramArrayList)
  {
    this.settings = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.ret, 0);
    if (this.settings != null)
      paramJceOutputStream.write(this.settings, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.protocol.jce.GetSettingsResponse
 * JD-Core Version:    0.6.0
 */