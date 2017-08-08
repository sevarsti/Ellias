package strategy;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.HashMap;
import java.util.Map;

public final class UploadStrategyPackage extends JceStruct
  implements Cloneable
{
  static Map<Integer, Boolean> cache_moduleStrategy;
  public int maxPackageSize = 0;
  public Map<Integer, Boolean> moduleStrategy = null;
  public int testCount = 0;
  public String uploadServer = "";
  public int uploadStrategy = 0;

  static
  {
    if (!UploadStrategyPackage.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public UploadStrategyPackage()
  {
    setTestCount(this.testCount);
    setMaxPackageSize(this.maxPackageSize);
    setUploadStrategy(this.uploadStrategy);
    setUploadServer(this.uploadServer);
    setModuleStrategy(this.moduleStrategy);
  }

  public UploadStrategyPackage(int paramInt1, int paramInt2, int paramInt3, String paramString, Map<Integer, Boolean> paramMap)
  {
    setTestCount(paramInt1);
    setMaxPackageSize(paramInt2);
    setUploadStrategy(paramInt3);
    setUploadServer(paramString);
    setModuleStrategy(paramMap);
  }

  public final String className()
  {
    return "strategy.UploadStrategyPackage";
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
    localJceDisplayer.display(this.testCount, "testCount");
    localJceDisplayer.display(this.maxPackageSize, "maxPackageSize");
    localJceDisplayer.display(this.uploadStrategy, "uploadStrategy");
    localJceDisplayer.display(this.uploadServer, "uploadServer");
    localJceDisplayer.display(this.moduleStrategy, "moduleStrategy");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    UploadStrategyPackage localUploadStrategyPackage;
    do
    {
      return false;
      localUploadStrategyPackage = (UploadStrategyPackage)paramObject;
    }
    while ((!JceUtil.equals(this.testCount, localUploadStrategyPackage.testCount)) || (!JceUtil.equals(this.maxPackageSize, localUploadStrategyPackage.maxPackageSize)) || (!JceUtil.equals(this.uploadStrategy, localUploadStrategyPackage.uploadStrategy)) || (!JceUtil.equals(this.uploadServer, localUploadStrategyPackage.uploadServer)) || (!JceUtil.equals(this.moduleStrategy, localUploadStrategyPackage.moduleStrategy)));
    return true;
  }

  public final String fullClassName()
  {
    return "strategy.UploadStrategyPackage";
  }

  public final int getMaxPackageSize()
  {
    return this.maxPackageSize;
  }

  public final Map<Integer, Boolean> getModuleStrategy()
  {
    return this.moduleStrategy;
  }

  public final int getTestCount()
  {
    return this.testCount;
  }

  public final String getUploadServer()
  {
    return this.uploadServer;
  }

  public final int getUploadStrategy()
  {
    return this.uploadStrategy;
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
    this.testCount = paramJceInputStream.read(this.testCount, 0, false);
    this.maxPackageSize = paramJceInputStream.read(this.maxPackageSize, 1, true);
    this.uploadStrategy = paramJceInputStream.read(this.uploadStrategy, 2, false);
    this.uploadServer = paramJceInputStream.readString(3, false);
    if (cache_moduleStrategy == null)
    {
      cache_moduleStrategy = new HashMap();
      Integer localInteger = Integer.valueOf(0);
      Boolean localBoolean = Boolean.valueOf(false);
      cache_moduleStrategy.put(localInteger, localBoolean);
    }
    setModuleStrategy((Map)paramJceInputStream.read(cache_moduleStrategy, 4, true));
  }

  public final void setMaxPackageSize(int paramInt)
  {
    this.maxPackageSize = paramInt;
  }

  public final void setModuleStrategy(Map<Integer, Boolean> paramMap)
  {
    this.moduleStrategy = paramMap;
  }

  public final void setTestCount(int paramInt)
  {
    this.testCount = paramInt;
  }

  public final void setUploadServer(String paramString)
  {
    this.uploadServer = paramString;
  }

  public final void setUploadStrategy(int paramInt)
  {
    this.uploadStrategy = paramInt;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.testCount, 0);
    paramJceOutputStream.write(this.maxPackageSize, 1);
    paramJceOutputStream.write(this.uploadStrategy, 2);
    if (this.uploadServer != null)
      paramJceOutputStream.write(this.uploadServer, 3);
    paramJceOutputStream.write(this.moduleStrategy, 4);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     strategy.UploadStrategyPackage
 * JD-Core Version:    0.6.0
 */