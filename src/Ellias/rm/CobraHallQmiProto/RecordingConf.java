package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class RecordingConf extends JceStruct
  implements Cloneable
{
  static QulityConfBundle cache_qualities;
  public short defaultQuality = 0;
  public String enc = "";
  public boolean forceSoftEncode = true;
  public String man = "";
  public String model = "";
  public boolean nv21 = true;
  public QulityConfBundle qualities = null;
  public boolean verticalImageData = true;

  static
  {
    if (!RecordingConf.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public RecordingConf()
  {
  }

  public RecordingConf(String paramString1, String paramString2, String paramString3, short paramShort, boolean paramBoolean1, boolean paramBoolean2, QulityConfBundle paramQulityConfBundle, boolean paramBoolean3)
  {
    this.man = paramString1;
    this.model = paramString2;
    this.enc = paramString3;
    this.defaultQuality = paramShort;
    this.forceSoftEncode = paramBoolean1;
    this.nv21 = paramBoolean2;
    this.qualities = paramQulityConfBundle;
    this.verticalImageData = paramBoolean3;
  }

  public String className()
  {
    return "CobraHallQmiProto.RecordingConf";
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
    localJceDisplayer.display(this.man, "man");
    localJceDisplayer.display(this.model, "model");
    localJceDisplayer.display(this.enc, "enc");
    localJceDisplayer.display(this.defaultQuality, "defaultQuality");
    localJceDisplayer.display(this.forceSoftEncode, "forceSoftEncode");
    localJceDisplayer.display(this.nv21, "nv21");
    localJceDisplayer.display(this.qualities, "qualities");
    localJceDisplayer.display(this.verticalImageData, "verticalImageData");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.man, true);
    localJceDisplayer.displaySimple(this.model, true);
    localJceDisplayer.displaySimple(this.enc, true);
    localJceDisplayer.displaySimple(this.defaultQuality, true);
    localJceDisplayer.displaySimple(this.forceSoftEncode, true);
    localJceDisplayer.displaySimple(this.nv21, true);
    localJceDisplayer.displaySimple(this.qualities, true);
    localJceDisplayer.displaySimple(this.verticalImageData, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    RecordingConf localRecordingConf;
    do
    {
      return false;
      localRecordingConf = (RecordingConf)paramObject;
    }
    while ((!JceUtil.equals(this.man, localRecordingConf.man)) || (!JceUtil.equals(this.model, localRecordingConf.model)) || (!JceUtil.equals(this.enc, localRecordingConf.enc)) || (!JceUtil.equals(this.defaultQuality, localRecordingConf.defaultQuality)) || (!JceUtil.equals(this.forceSoftEncode, localRecordingConf.forceSoftEncode)) || (!JceUtil.equals(this.nv21, localRecordingConf.nv21)) || (!JceUtil.equals(this.qualities, localRecordingConf.qualities)) || (!JceUtil.equals(this.verticalImageData, localRecordingConf.verticalImageData)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.RecordingConf";
  }

  public short getDefaultQuality()
  {
    return this.defaultQuality;
  }

  public String getEnc()
  {
    return this.enc;
  }

  public boolean getForceSoftEncode()
  {
    return this.forceSoftEncode;
  }

  public String getMan()
  {
    return this.man;
  }

  public String getModel()
  {
    return this.model;
  }

  public boolean getNv21()
  {
    return this.nv21;
  }

  public QulityConfBundle getQualities()
  {
    return this.qualities;
  }

  public boolean getVerticalImageData()
  {
    return this.verticalImageData;
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
    this.man = paramJceInputStream.readString(0, true);
    this.model = paramJceInputStream.readString(1, true);
    this.enc = paramJceInputStream.readString(2, true);
    this.defaultQuality = paramJceInputStream.read(this.defaultQuality, 3, false);
    this.forceSoftEncode = paramJceInputStream.read(this.forceSoftEncode, 4, false);
    this.nv21 = paramJceInputStream.read(this.nv21, 5, false);
    if (cache_qualities == null)
      cache_qualities = new QulityConfBundle();
    this.qualities = ((QulityConfBundle)paramJceInputStream.read(cache_qualities, 6, false));
    this.verticalImageData = paramJceInputStream.read(this.verticalImageData, 7, false);
  }

  public void setDefaultQuality(short paramShort)
  {
    this.defaultQuality = paramShort;
  }

  public void setEnc(String paramString)
  {
    this.enc = paramString;
  }

  public void setForceSoftEncode(boolean paramBoolean)
  {
    this.forceSoftEncode = paramBoolean;
  }

  public void setMan(String paramString)
  {
    this.man = paramString;
  }

  public void setModel(String paramString)
  {
    this.model = paramString;
  }

  public void setNv21(boolean paramBoolean)
  {
    this.nv21 = paramBoolean;
  }

  public void setQualities(QulityConfBundle paramQulityConfBundle)
  {
    this.qualities = paramQulityConfBundle;
  }

  public void setVerticalImageData(boolean paramBoolean)
  {
    this.verticalImageData = paramBoolean;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.man, 0);
    paramJceOutputStream.write(this.model, 1);
    paramJceOutputStream.write(this.enc, 2);
    paramJceOutputStream.write(this.defaultQuality, 3);
    paramJceOutputStream.write(this.forceSoftEncode, 4);
    paramJceOutputStream.write(this.nv21, 5);
    if (this.qualities != null)
      paramJceOutputStream.write(this.qualities, 6);
    paramJceOutputStream.write(this.verticalImageData, 7);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.RecordingConf
 * JD-Core Version:    0.6.0
 */