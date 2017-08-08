package strategy;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class ModuleStrategy extends JceStruct
  implements Cloneable
{
  public int mId = 0;
  public boolean needDetail = true;
  public String url = "";

  static
  {
    if (!ModuleStrategy.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public ModuleStrategy()
  {
    setMId(this.mId);
    setUrl(this.url);
    setNeedDetail(this.needDetail);
  }

  public ModuleStrategy(int paramInt, String paramString, boolean paramBoolean)
  {
    setMId(paramInt);
    setUrl(paramString);
    setNeedDetail(paramBoolean);
  }

  public final String className()
  {
    return "strategy.ModuleStrategy";
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
    localJceDisplayer.display(this.mId, "mId");
    localJceDisplayer.display(this.url, "url");
    localJceDisplayer.display(this.needDetail, "needDetail");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    ModuleStrategy localModuleStrategy;
    do
    {
      return false;
      localModuleStrategy = (ModuleStrategy)paramObject;
    }
    while ((!JceUtil.equals(this.mId, localModuleStrategy.mId)) || (!JceUtil.equals(this.url, localModuleStrategy.url)) || (!JceUtil.equals(this.needDetail, localModuleStrategy.needDetail)));
    return true;
  }

  public final String fullClassName()
  {
    return "strategy.ModuleStrategy";
  }

  public final int getMId()
  {
    return this.mId;
  }

  public final boolean getNeedDetail()
  {
    return this.needDetail;
  }

  public final String getUrl()
  {
    return this.url;
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
    this.mId = paramJceInputStream.read(this.mId, 0, true);
    this.url = paramJceInputStream.readString(1, true);
    this.needDetail = paramJceInputStream.read(this.needDetail, 2, false);
  }

  public final void setMId(int paramInt)
  {
    this.mId = paramInt;
  }

  public final void setNeedDetail(boolean paramBoolean)
  {
    this.needDetail = paramBoolean;
  }

  public final void setUrl(String paramString)
  {
    this.url = paramString;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.mId, 0);
    paramJceOutputStream.write(this.url, 1);
    paramJceOutputStream.write(this.needDetail, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     strategy.ModuleStrategy
 * JD-Core Version:    0.6.0
 */