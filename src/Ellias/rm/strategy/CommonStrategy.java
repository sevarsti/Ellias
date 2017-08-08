package strategy;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class CommonStrategy extends JceStruct
  implements Cloneable
{
  static ArrayList<ModuleStrategy> cache_moduleList;
  static SecurityStrategy cache_s;
  public boolean enforceQuery = true;
  public ArrayList<ModuleStrategy> moduleList = null;
  public int queryInterval = 0;
  public SecurityStrategy s = null;
  public int uploadStrategy = 0;
  public String url = "";
  public boolean useServer = true;

  static
  {
    if (!CommonStrategy.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public CommonStrategy()
  {
    setModuleList(this.moduleList);
    setS(this.s);
    setUploadStrategy(this.uploadStrategy);
    setQueryInterval(this.queryInterval);
    setUrl(this.url);
    setEnforceQuery(this.enforceQuery);
    setUseServer(this.useServer);
  }

  public CommonStrategy(ArrayList<ModuleStrategy> paramArrayList, SecurityStrategy paramSecurityStrategy, int paramInt1, int paramInt2, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    setModuleList(paramArrayList);
    setS(paramSecurityStrategy);
    setUploadStrategy(paramInt1);
    setQueryInterval(paramInt2);
    setUrl(paramString);
    setEnforceQuery(paramBoolean1);
    setUseServer(paramBoolean2);
  }

  public final String className()
  {
    return "strategy.CommonStrategy";
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
    localJceDisplayer.display(this.moduleList, "moduleList");
    localJceDisplayer.display(this.s, "s");
    localJceDisplayer.display(this.uploadStrategy, "uploadStrategy");
    localJceDisplayer.display(this.queryInterval, "queryInterval");
    localJceDisplayer.display(this.url, "url");
    localJceDisplayer.display(this.enforceQuery, "enforceQuery");
    localJceDisplayer.display(this.useServer, "useServer");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    CommonStrategy localCommonStrategy;
    do
    {
      return false;
      localCommonStrategy = (CommonStrategy)paramObject;
    }
    while ((!JceUtil.equals(this.moduleList, localCommonStrategy.moduleList)) || (!JceUtil.equals(this.s, localCommonStrategy.s)) || (!JceUtil.equals(this.uploadStrategy, localCommonStrategy.uploadStrategy)) || (!JceUtil.equals(this.queryInterval, localCommonStrategy.queryInterval)) || (!JceUtil.equals(this.url, localCommonStrategy.url)) || (!JceUtil.equals(this.enforceQuery, localCommonStrategy.enforceQuery)) || (!JceUtil.equals(this.useServer, localCommonStrategy.useServer)));
    return true;
  }

  public final String fullClassName()
  {
    return "strategy.CommonStrategy";
  }

  public final boolean getEnforceQuery()
  {
    return this.enforceQuery;
  }

  public final ArrayList<ModuleStrategy> getModuleList()
  {
    return this.moduleList;
  }

  public final int getQueryInterval()
  {
    return this.queryInterval;
  }

  public final SecurityStrategy getS()
  {
    return this.s;
  }

  public final int getUploadStrategy()
  {
    return this.uploadStrategy;
  }

  public final String getUrl()
  {
    return this.url;
  }

  public final boolean getUseServer()
  {
    return this.useServer;
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
    if (cache_moduleList == null)
    {
      cache_moduleList = new ArrayList();
      ModuleStrategy localModuleStrategy = new ModuleStrategy();
      cache_moduleList.add(localModuleStrategy);
    }
    setModuleList((ArrayList)paramJceInputStream.read(cache_moduleList, 0, true));
    if (cache_s == null)
      cache_s = new SecurityStrategy();
    setS((SecurityStrategy)paramJceInputStream.read(cache_s, 1, true));
    setUploadStrategy(paramJceInputStream.read(this.uploadStrategy, 2, true));
    setQueryInterval(paramJceInputStream.read(this.queryInterval, 3, true));
    setUrl(paramJceInputStream.readString(4, true));
    setEnforceQuery(paramJceInputStream.read(this.enforceQuery, 5, false));
    setUseServer(paramJceInputStream.read(this.useServer, 6, false));
  }

  public final void setEnforceQuery(boolean paramBoolean)
  {
    this.enforceQuery = paramBoolean;
  }

  public final void setModuleList(ArrayList<ModuleStrategy> paramArrayList)
  {
    this.moduleList = paramArrayList;
  }

  public final void setQueryInterval(int paramInt)
  {
    this.queryInterval = paramInt;
  }

  public final void setS(SecurityStrategy paramSecurityStrategy)
  {
    this.s = paramSecurityStrategy;
  }

  public final void setUploadStrategy(int paramInt)
  {
    this.uploadStrategy = paramInt;
  }

  public final void setUrl(String paramString)
  {
    this.url = paramString;
  }

  public final void setUseServer(boolean paramBoolean)
  {
    this.useServer = paramBoolean;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.moduleList, 0);
    paramJceOutputStream.write(this.s, 1);
    paramJceOutputStream.write(this.uploadStrategy, 2);
    paramJceOutputStream.write(this.queryInterval, 3);
    paramJceOutputStream.write(this.url, 4);
    paramJceOutputStream.write(this.enforceQuery, 5);
    paramJceOutputStream.write(this.useServer, 6);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     strategy.CommonStrategy
 * JD-Core Version:    0.6.0
 */