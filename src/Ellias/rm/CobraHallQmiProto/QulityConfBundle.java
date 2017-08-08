package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class QulityConfBundle extends JceStruct
  implements Cloneable
{
  static QualityConf cache_high;
  static QualityConf cache_low;
  static QualityConf cache_mid;
  public QualityConf high = null;
  public QualityConf low = null;
  public QualityConf mid = null;

  static
  {
    if (!QulityConfBundle.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public QulityConfBundle()
  {
  }

  public QulityConfBundle(QualityConf paramQualityConf1, QualityConf paramQualityConf2, QualityConf paramQualityConf3)
  {
    this.high = paramQualityConf1;
    this.mid = paramQualityConf2;
    this.low = paramQualityConf3;
  }

  public String className()
  {
    return "CobraHallQmiProto.QulityConfBundle";
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
    localJceDisplayer.display(this.high, "high");
    localJceDisplayer.display(this.mid, "mid");
    localJceDisplayer.display(this.low, "low");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.high, true);
    localJceDisplayer.displaySimple(this.mid, true);
    localJceDisplayer.displaySimple(this.low, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    QulityConfBundle localQulityConfBundle;
    do
    {
      return false;
      localQulityConfBundle = (QulityConfBundle)paramObject;
    }
    while ((!JceUtil.equals(this.high, localQulityConfBundle.high)) || (!JceUtil.equals(this.mid, localQulityConfBundle.mid)) || (!JceUtil.equals(this.low, localQulityConfBundle.low)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.QulityConfBundle";
  }

  public QualityConf getHigh()
  {
    return this.high;
  }

  public QualityConf getLow()
  {
    return this.low;
  }

  public QualityConf getMid()
  {
    return this.mid;
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
    if (cache_high == null)
      cache_high = new QualityConf();
    this.high = ((QualityConf)paramJceInputStream.read(cache_high, 0, true));
    if (cache_mid == null)
      cache_mid = new QualityConf();
    this.mid = ((QualityConf)paramJceInputStream.read(cache_mid, 1, true));
    if (cache_low == null)
      cache_low = new QualityConf();
    this.low = ((QualityConf)paramJceInputStream.read(cache_low, 2, true));
  }

  public void setHigh(QualityConf paramQualityConf)
  {
    this.high = paramQualityConf;
  }

  public void setLow(QualityConf paramQualityConf)
  {
    this.low = paramQualityConf;
  }

  public void setMid(QualityConf paramQualityConf)
  {
    this.mid = paramQualityConf;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.high, 0);
    paramJceOutputStream.write(this.mid, 1);
    paramJceOutputStream.write(this.low, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.QulityConfBundle
 * JD-Core Version:    0.6.0
 */