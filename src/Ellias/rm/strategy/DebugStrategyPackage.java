package strategy;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class DebugStrategyPackage extends JceStruct
  implements Cloneable
{
  public boolean localLog = true;
  public int logBufferSize = 0;
  public int loguploadDur = 0;
  public boolean remoteLog = true;
  public boolean saveLocalLog = true;

  static
  {
    if (!DebugStrategyPackage.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public DebugStrategyPackage()
  {
  }

  public DebugStrategyPackage(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean3)
  {
    this.localLog = paramBoolean1;
    this.remoteLog = paramBoolean2;
    this.loguploadDur = paramInt1;
    this.logBufferSize = paramInt2;
    this.saveLocalLog = paramBoolean3;
  }

  public final String className()
  {
    return "strategy.DebugStrategyPackage";
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
    localJceDisplayer.display(this.localLog, "localLog");
    localJceDisplayer.display(this.remoteLog, "remoteLog");
    localJceDisplayer.display(this.loguploadDur, "loguploadDur");
    localJceDisplayer.display(this.logBufferSize, "logBufferSize");
    localJceDisplayer.display(this.saveLocalLog, "saveLocalLog");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    DebugStrategyPackage localDebugStrategyPackage;
    do
    {
      return false;
      localDebugStrategyPackage = (DebugStrategyPackage)paramObject;
    }
    while ((!JceUtil.equals(this.localLog, localDebugStrategyPackage.localLog)) || (!JceUtil.equals(this.remoteLog, localDebugStrategyPackage.remoteLog)) || (!JceUtil.equals(this.loguploadDur, localDebugStrategyPackage.loguploadDur)) || (!JceUtil.equals(this.logBufferSize, localDebugStrategyPackage.logBufferSize)) || (!JceUtil.equals(this.saveLocalLog, localDebugStrategyPackage.saveLocalLog)));
    return true;
  }

  public final String fullClassName()
  {
    return "strategy.DebugStrategyPackage";
  }

  public final boolean getLocalLog()
  {
    return this.localLog;
  }

  public final int getLogBufferSize()
  {
    return this.logBufferSize;
  }

  public final int getLoguploadDur()
  {
    return this.loguploadDur;
  }

  public final boolean getRemoteLog()
  {
    return this.remoteLog;
  }

  public final boolean getSaveLocalLog()
  {
    return this.saveLocalLog;
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
    this.localLog = paramJceInputStream.read(this.localLog, 0, false);
    this.remoteLog = paramJceInputStream.read(this.remoteLog, 1, false);
    this.loguploadDur = paramJceInputStream.read(this.loguploadDur, 2, false);
    this.logBufferSize = paramJceInputStream.read(this.logBufferSize, 3, false);
    this.saveLocalLog = paramJceInputStream.read(this.saveLocalLog, 4, false);
  }

  public final void setLocalLog(boolean paramBoolean)
  {
    this.localLog = paramBoolean;
  }

  public final void setLogBufferSize(int paramInt)
  {
    this.logBufferSize = paramInt;
  }

  public final void setLoguploadDur(int paramInt)
  {
    this.loguploadDur = paramInt;
  }

  public final void setRemoteLog(boolean paramBoolean)
  {
    this.remoteLog = paramBoolean;
  }

  public final void setSaveLocalLog(boolean paramBoolean)
  {
    this.saveLocalLog = paramBoolean;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.localLog, 0);
    paramJceOutputStream.write(this.remoteLog, 1);
    paramJceOutputStream.write(this.loguploadDur, 2);
    paramJceOutputStream.write(this.logBufferSize, 3);
    paramJceOutputStream.write(this.saveLocalLog, 4);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     strategy.DebugStrategyPackage
 * JD-Core Version:    0.6.0
 */