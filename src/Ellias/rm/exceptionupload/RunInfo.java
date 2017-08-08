package exceptionupload;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class RunInfo extends JceStruct
  implements Cloneable
{
  public float battery = 0.0F;
  public float cpu = 0.0F;
  public long freeMem = 0L;
  public long freeSDCard = 0L;
  public long freeStorage = 0L;

  static
  {
    if (!RunInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public RunInfo()
  {
  }

  public RunInfo(float paramFloat1, float paramFloat2, long paramLong1, long paramLong2, long paramLong3)
  {
    this.battery = paramFloat1;
    this.cpu = paramFloat2;
    this.freeMem = paramLong1;
    this.freeStorage = paramLong2;
    this.freeSDCard = paramLong3;
  }

  public final String className()
  {
    return "exceptionupload.RunInfo";
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
    localJceDisplayer.display(this.battery, "battery");
    localJceDisplayer.display(this.cpu, "cpu");
    localJceDisplayer.display(this.freeMem, "freeMem");
    localJceDisplayer.display(this.freeStorage, "freeStorage");
    localJceDisplayer.display(this.freeSDCard, "freeSDCard");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    RunInfo localRunInfo;
    do
    {
      return false;
      localRunInfo = (RunInfo)paramObject;
    }
    while ((!JceUtil.equals(this.battery, localRunInfo.battery)) || (!JceUtil.equals(this.cpu, localRunInfo.cpu)) || (!JceUtil.equals(this.freeMem, localRunInfo.freeMem)) || (!JceUtil.equals(this.freeStorage, localRunInfo.freeStorage)) || (!JceUtil.equals(this.freeSDCard, localRunInfo.freeSDCard)));
    return true;
  }

  public final String fullClassName()
  {
    return "exceptionupload.RunInfo";
  }

  public final float getBattery()
  {
    return this.battery;
  }

  public final float getCpu()
  {
    return this.cpu;
  }

  public final long getFreeMem()
  {
    return this.freeMem;
  }

  public final long getFreeSDCard()
  {
    return this.freeSDCard;
  }

  public final long getFreeStorage()
  {
    return this.freeStorage;
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
    this.battery = paramJceInputStream.read(this.battery, 0, true);
    this.cpu = paramJceInputStream.read(this.cpu, 1, true);
    this.freeMem = paramJceInputStream.read(this.freeMem, 2, true);
    this.freeStorage = paramJceInputStream.read(this.freeStorage, 3, true);
    this.freeSDCard = paramJceInputStream.read(this.freeSDCard, 4, true);
  }

  public final void setBattery(float paramFloat)
  {
    this.battery = paramFloat;
  }

  public final void setCpu(float paramFloat)
  {
    this.cpu = paramFloat;
  }

  public final void setFreeMem(long paramLong)
  {
    this.freeMem = paramLong;
  }

  public final void setFreeSDCard(long paramLong)
  {
    this.freeSDCard = paramLong;
  }

  public final void setFreeStorage(long paramLong)
  {
    this.freeStorage = paramLong;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.battery, 0);
    paramJceOutputStream.write(this.cpu, 1);
    paramJceOutputStream.write(this.freeMem, 2);
    paramJceOutputStream.write(this.freeStorage, 3);
    paramJceOutputStream.write(this.freeSDCard, 4);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     exceptionupload.RunInfo
 * JD-Core Version:    0.6.0
 */