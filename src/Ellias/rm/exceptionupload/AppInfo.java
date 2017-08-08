package exceptionupload;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class AppInfo extends JceStruct
  implements Cloneable
{
  public String libArch = "";
  public String libBaseAddr = "";
  public String libName = "";
  public String libUUID = "";

  static
  {
    if (!AppInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public AppInfo()
  {
  }

  public AppInfo(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.libName = paramString1;
    this.libArch = paramString2;
    this.libUUID = paramString3;
    this.libBaseAddr = paramString4;
  }

  public final String className()
  {
    return "exceptionupload.AppInfo";
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
    localJceDisplayer.display(this.libName, "libName");
    localJceDisplayer.display(this.libArch, "libArch");
    localJceDisplayer.display(this.libUUID, "libUUID");
    localJceDisplayer.display(this.libBaseAddr, "libBaseAddr");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    AppInfo localAppInfo;
    do
    {
      return false;
      localAppInfo = (AppInfo)paramObject;
    }
    while ((!JceUtil.equals(this.libName, localAppInfo.libName)) || (!JceUtil.equals(this.libArch, localAppInfo.libArch)) || (!JceUtil.equals(this.libUUID, localAppInfo.libUUID)) || (!JceUtil.equals(this.libBaseAddr, localAppInfo.libBaseAddr)));
    return true;
  }

  public final String fullClassName()
  {
    return "exceptionupload.AppInfo";
  }

  public final String getLibArch()
  {
    return this.libArch;
  }

  public final String getLibBaseAddr()
  {
    return this.libBaseAddr;
  }

  public final String getLibName()
  {
    return this.libName;
  }

  public final String getLibUUID()
  {
    return this.libUUID;
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
    this.libName = paramJceInputStream.readString(0, false);
    this.libArch = paramJceInputStream.readString(1, false);
    this.libUUID = paramJceInputStream.readString(2, false);
    this.libBaseAddr = paramJceInputStream.readString(3, false);
  }

  public final void setLibArch(String paramString)
  {
    this.libArch = paramString;
  }

  public final void setLibBaseAddr(String paramString)
  {
    this.libBaseAddr = paramString;
  }

  public final void setLibName(String paramString)
  {
    this.libName = paramString;
  }

  public final void setLibUUID(String paramString)
  {
    this.libUUID = paramString;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    if (this.libName != null)
      paramJceOutputStream.write(this.libName, 0);
    if (this.libArch != null)
      paramJceOutputStream.write(this.libArch, 1);
    if (this.libUUID != null)
      paramJceOutputStream.write(this.libUUID, 2);
    if (this.libBaseAddr != null)
      paramJceOutputStream.write(this.libBaseAddr, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     exceptionupload.AppInfo
 * JD-Core Version:    0.6.0
 */