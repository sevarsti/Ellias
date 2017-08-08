package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TBodyQmiStartReq extends JceStruct
  implements Cloneable
{
  static ArrayList cache_cmdTimestampInfos;
  public ArrayList cmdTimestampInfos = null;

  static
  {
    if (!TBodyQmiStartReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyQmiStartReq()
  {
  }

  public TBodyQmiStartReq(ArrayList paramArrayList)
  {
    this.cmdTimestampInfos = paramArrayList;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyQmiStartReq";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.cmdTimestampInfos, "cmdTimestampInfos");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.cmdTimestampInfos, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TBodyQmiStartReq localTBodyQmiStartReq = (TBodyQmiStartReq)paramObject;
    return JceUtil.equals(this.cmdTimestampInfos, localTBodyQmiStartReq.cmdTimestampInfos);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyQmiStartReq";
  }

  public ArrayList getCmdTimestampInfos()
  {
    return this.cmdTimestampInfos;
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
    if (cache_cmdTimestampInfos == null)
    {
      cache_cmdTimestampInfos = new ArrayList();
      TCmdTimeStampInfo localTCmdTimeStampInfo = new TCmdTimeStampInfo();
      cache_cmdTimestampInfos.add(localTCmdTimeStampInfo);
    }
    this.cmdTimestampInfos = ((ArrayList)paramJceInputStream.read(cache_cmdTimestampInfos, 0, true));
  }

  public void setCmdTimestampInfos(ArrayList paramArrayList)
  {
    this.cmdTimestampInfos = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.cmdTimestampInfos, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyQmiStartReq
 * JD-Core Version:    0.6.0
 */