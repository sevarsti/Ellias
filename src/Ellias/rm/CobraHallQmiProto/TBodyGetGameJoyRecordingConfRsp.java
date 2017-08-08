package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TBodyGetGameJoyRecordingConfRsp extends JceStruct
  implements Cloneable
{
  static ArrayList cache_confList;
  public ArrayList confList = null;

  static
  {
    if (!TBodyGetGameJoyRecordingConfRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyGetGameJoyRecordingConfRsp()
  {
  }

  public TBodyGetGameJoyRecordingConfRsp(ArrayList paramArrayList)
  {
    this.confList = paramArrayList;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyGetGameJoyRecordingConfRsp";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.confList, "confList");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.confList, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TBodyGetGameJoyRecordingConfRsp localTBodyGetGameJoyRecordingConfRsp = (TBodyGetGameJoyRecordingConfRsp)paramObject;
    return JceUtil.equals(this.confList, localTBodyGetGameJoyRecordingConfRsp.confList);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyGetGameJoyRecordingConfRsp";
  }

  public ArrayList getConfList()
  {
    return this.confList;
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
    if (cache_confList == null)
    {
      cache_confList = new ArrayList();
      RecordingConf localRecordingConf = new RecordingConf();
      cache_confList.add(localRecordingConf);
    }
    this.confList = ((ArrayList)paramJceInputStream.read(cache_confList, 0, true));
  }

  public void setConfList(ArrayList paramArrayList)
  {
    this.confList = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.confList, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyGetGameJoyRecordingConfRsp
 * JD-Core Version:    0.6.0
 */