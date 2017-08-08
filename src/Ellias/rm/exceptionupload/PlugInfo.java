package exceptionupload;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class PlugInfo extends JceStruct
  implements Cloneable
{
  public String pluginId = "";
  public String pluginUUID = "";
  public String pluginVer = "";

  static
  {
    if (!PlugInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public PlugInfo()
  {
  }

  public PlugInfo(String paramString1, String paramString2, String paramString3)
  {
    this.pluginId = paramString1;
    this.pluginVer = paramString2;
    this.pluginUUID = paramString3;
  }

  public final String className()
  {
    return "exceptionupload.PlugInfo";
  }

  public final Object clone()
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
        bool = $assertionsDisabled;
        Object localObject1 = null;
      }
      while (bool);
    }
    throw new AssertionError();
  }

  public final void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.pluginId, "pluginId");
    localJceDisplayer.display(this.pluginVer, "pluginVer");
    localJceDisplayer.display(this.pluginUUID, "pluginUUID");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    PlugInfo localPlugInfo;
    do
    {
      return false;
      localPlugInfo = (PlugInfo)paramObject;
    }
    while ((!JceUtil.equals(this.pluginId, localPlugInfo.pluginId)) || (!JceUtil.equals(this.pluginVer, localPlugInfo.pluginVer)) || (!JceUtil.equals(this.pluginUUID, localPlugInfo.pluginUUID)));
    return true;
  }

  public final String fullClassName()
  {
    return "exceptionupload.PlugInfo";
  }

  public final String getPluginId()
  {
    return this.pluginId;
  }

  public final String getPluginUUID()
  {
    return this.pluginUUID;
  }

  public final String getPluginVer()
  {
    return this.pluginVer;
  }

  public final int hashCode()
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

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    this.pluginId = paramJceInputStream.readString(0, true);
    this.pluginVer = paramJceInputStream.readString(1, true);
    this.pluginUUID = paramJceInputStream.readString(2, false);
  }

  public final void setPluginId(String paramString)
  {
    this.pluginId = paramString;
  }

  public final void setPluginUUID(String paramString)
  {
    this.pluginUUID = paramString;
  }

  public final void setPluginVer(String paramString)
  {
    this.pluginVer = paramString;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.pluginId, 0);
    paramJceOutputStream.write(this.pluginVer, 1);
    if (this.pluginUUID != null)
      paramJceOutputStream.write(this.pluginUUID, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     exceptionupload.PlugInfo
 * JD-Core Version:    0.6.0
 */