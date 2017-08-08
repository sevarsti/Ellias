package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TBodyGetGameJoyRecordingConfReq extends JceStruct
  implements Cloneable
{
  public int version = 0;

  static
  {
    if (!TBodyGetGameJoyRecordingConfReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyGetGameJoyRecordingConfReq()
  {
  }

  public TBodyGetGameJoyRecordingConfReq(int paramInt)
  {
    this.version = paramInt;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyGetGameJoyRecordingConfReq";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.version, "version");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.version, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TBodyGetGameJoyRecordingConfReq localTBodyGetGameJoyRecordingConfReq = (TBodyGetGameJoyRecordingConfReq)paramObject;
    return JceUtil.equals(this.version, localTBodyGetGameJoyRecordingConfReq.version);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyGetGameJoyRecordingConfReq";
  }

  public int getVersion()
  {
    return this.version;
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
    this.version = paramJceInputStream.read(this.version, 0, true);
  }

  public void setVersion(int paramInt)
  {
    this.version = paramInt;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.version, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyGetGameJoyRecordingConfReq
 * JD-Core Version:    0.6.0
 */