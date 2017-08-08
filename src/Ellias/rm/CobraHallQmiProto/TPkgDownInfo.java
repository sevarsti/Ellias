package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import com.tencent.component.annotation.PluginApi;

@PluginApi
public final class TPkgDownInfo extends JceStruct
  implements Cloneable
{
  public String downUrl = "";
  public String pkgHash = "";
  public int pkgSize = 0;

  static
  {
    if (!TPkgDownInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  @PluginApi
  public TPkgDownInfo()
  {
  }

  @PluginApi
  public TPkgDownInfo(String paramString1, int paramInt, String paramString2)
  {
    this.downUrl = paramString1;
    this.pkgSize = paramInt;
    this.pkgHash = paramString2;
  }

  public String className()
  {
    return "CobraHallQmiProto.TPkgDownInfo";
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
        bool = $assertionsDisabled;
        Object localObject1 = null;
      }
      while (bool);
    }
    throw new AssertionError();
  }

  public void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.downUrl, "downUrl");
    localJceDisplayer.display(this.pkgSize, "pkgSize");
    localJceDisplayer.display(this.pkgHash, "pkgHash");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.downUrl, true);
    localJceDisplayer.displaySimple(this.pkgSize, true);
    localJceDisplayer.displaySimple(this.pkgHash, false);
  }

  @PluginApi
  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TPkgDownInfo localTPkgDownInfo;
    do
    {
      return false;
      localTPkgDownInfo = (TPkgDownInfo)paramObject;
    }
    while ((!JceUtil.equals(this.downUrl, localTPkgDownInfo.downUrl)) || (!JceUtil.equals(this.pkgSize, localTPkgDownInfo.pkgSize)) || (!JceUtil.equals(this.pkgHash, localTPkgDownInfo.pkgHash)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TPkgDownInfo";
  }

  @PluginApi
  public String getDownUrl()
  {
    return this.downUrl;
  }

  @PluginApi
  public String getPkgHash()
  {
    return this.pkgHash;
  }

  @PluginApi
  public int getPkgSize()
  {
    return this.pkgSize;
  }

  @PluginApi
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
    this.downUrl = paramJceInputStream.readString(0, true);
    this.pkgSize = paramJceInputStream.read(this.pkgSize, 1, false);
    this.pkgHash = paramJceInputStream.readString(2, false);
  }

  @PluginApi
  public void setDownUrl(String paramString)
  {
    this.downUrl = paramString;
  }

  @PluginApi
  public void setPkgHash(String paramString)
  {
    this.pkgHash = paramString;
  }

  @PluginApi
  public void setPkgSize(int paramInt)
  {
    this.pkgSize = paramInt;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.downUrl, 0);
    paramJceOutputStream.write(this.pkgSize, 1);
    if (this.pkgHash != null)
      paramJceOutputStream.write(this.pkgHash, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TPkgDownInfo
 * JD-Core Version:    0.6.0
 */