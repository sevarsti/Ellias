package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class QualityConf extends JceStruct
  implements Cloneable
{
  public float bitRate = 0.0F;
  public int frameRate = 0;
  public float resolutionRatio = 0.0F;

  static
  {
    if (!QualityConf.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public QualityConf()
  {
  }

  public QualityConf(float paramFloat1, int paramInt, float paramFloat2)
  {
    this.resolutionRatio = paramFloat1;
    this.frameRate = paramInt;
    this.bitRate = paramFloat2;
  }

  public String className()
  {
    return "CobraHallQmiProto.QualityConf";
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
    localJceDisplayer.display(this.resolutionRatio, "resolutionRatio");
    localJceDisplayer.display(this.frameRate, "frameRate");
    localJceDisplayer.display(this.bitRate, "bitRate");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.resolutionRatio, true);
    localJceDisplayer.displaySimple(this.frameRate, true);
    localJceDisplayer.displaySimple(this.bitRate, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    QualityConf localQualityConf;
    do
    {
      return false;
      localQualityConf = (QualityConf)paramObject;
    }
    while ((!JceUtil.equals(this.resolutionRatio, localQualityConf.resolutionRatio)) || (!JceUtil.equals(this.frameRate, localQualityConf.frameRate)) || (!JceUtil.equals(this.bitRate, localQualityConf.bitRate)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.QualityConf";
  }

  public float getBitRate()
  {
    return this.bitRate;
  }

  public int getFrameRate()
  {
    return this.frameRate;
  }

  public float getResolutionRatio()
  {
    return this.resolutionRatio;
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
    this.resolutionRatio = paramJceInputStream.read(this.resolutionRatio, 0, true);
    this.frameRate = paramJceInputStream.read(this.frameRate, 1, true);
    this.bitRate = paramJceInputStream.read(this.bitRate, 2, true);
  }

  public void setBitRate(float paramFloat)
  {
    this.bitRate = paramFloat;
  }

  public void setFrameRate(int paramInt)
  {
    this.frameRate = paramInt;
  }

  public void setResolutionRatio(float paramFloat)
  {
    this.resolutionRatio = paramFloat;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.resolutionRatio, 0);
    paramJceOutputStream.write(this.frameRate, 1);
    paramJceOutputStream.write(this.bitRate, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.QualityConf
 * JD-Core Version:    0.6.0
 */