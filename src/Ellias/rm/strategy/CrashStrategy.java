package strategy;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.HashMap;
import java.util.Map;

public final class CrashStrategy extends JceStruct
  implements Cloneable
{
  static Map<String, byte[]> cache_valueMap;
  public int assertUploadCount = 0;
  public int assertUploadTime = 0;
  public boolean isMerge = true;
  public boolean useAssert = true;
  public Map<String, byte[]> valueMap = null;

  static
  {
    if (!CrashStrategy.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public CrashStrategy()
  {
  }

  public CrashStrategy(boolean paramBoolean1, Map<String, byte[]> paramMap, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    this.isMerge = paramBoolean1;
    this.valueMap = paramMap;
    this.useAssert = paramBoolean2;
    this.assertUploadTime = paramInt1;
    this.assertUploadCount = paramInt2;
  }

  public final String className()
  {
    return "strategy.CrashStrategy";
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
    localJceDisplayer.display(this.isMerge, "isMerge");
    localJceDisplayer.display(this.valueMap, "valueMap");
    localJceDisplayer.display(this.useAssert, "useAssert");
    localJceDisplayer.display(this.assertUploadTime, "assertUploadTime");
    localJceDisplayer.display(this.assertUploadCount, "assertUploadCount");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    CrashStrategy localCrashStrategy;
    do
    {
      return false;
      localCrashStrategy = (CrashStrategy)paramObject;
    }
    while ((!JceUtil.equals(this.isMerge, localCrashStrategy.isMerge)) || (!JceUtil.equals(this.valueMap, localCrashStrategy.valueMap)) || (!JceUtil.equals(this.useAssert, localCrashStrategy.useAssert)) || (!JceUtil.equals(this.assertUploadTime, localCrashStrategy.assertUploadTime)) || (!JceUtil.equals(this.assertUploadCount, localCrashStrategy.assertUploadCount)));
    return true;
  }

  public final String fullClassName()
  {
    return "strategy.CrashStrategy";
  }

  public final int getAssertUploadCount()
  {
    return this.assertUploadCount;
  }

  public final int getAssertUploadTime()
  {
    return this.assertUploadTime;
  }

  public final boolean getIsMerge()
  {
    return this.isMerge;
  }

  public final boolean getUseAssert()
  {
    return this.useAssert;
  }

  public final Map<String, byte[]> getValueMap()
  {
    return this.valueMap;
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
    this.isMerge = paramJceInputStream.read(this.isMerge, 0, true);
    if (cache_valueMap == null)
    {
      cache_valueMap = new HashMap();
      byte[] arrayOfByte = { 0 };
      cache_valueMap.put("", arrayOfByte);
    }
    this.valueMap = ((Map)paramJceInputStream.read(cache_valueMap, 1, false));
    this.useAssert = paramJceInputStream.read(this.useAssert, 2, false);
    this.assertUploadTime = paramJceInputStream.read(this.assertUploadTime, 3, false);
    this.assertUploadCount = paramJceInputStream.read(this.assertUploadCount, 4, false);
  }

  public final void setAssertUploadCount(int paramInt)
  {
    this.assertUploadCount = paramInt;
  }

  public final void setAssertUploadTime(int paramInt)
  {
    this.assertUploadTime = paramInt;
  }

  public final void setIsMerge(boolean paramBoolean)
  {
    this.isMerge = paramBoolean;
  }

  public final void setUseAssert(boolean paramBoolean)
  {
    this.useAssert = paramBoolean;
  }

  public final void setValueMap(Map<String, byte[]> paramMap)
  {
    this.valueMap = paramMap;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.isMerge, 0);
    if (this.valueMap != null)
      paramJceOutputStream.write(this.valueMap, 1);
    paramJceOutputStream.write(this.useAssert, 2);
    paramJceOutputStream.write(this.assertUploadTime, 3);
    paramJceOutputStream.write(this.assertUploadCount, 4);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     strategy.CrashStrategy
 * JD-Core Version:    0.6.0
 */