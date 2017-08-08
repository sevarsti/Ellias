package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TBodyGetGameExtendInfoRsp extends JceStruct
  implements Cloneable
{
  static ArrayList cache_vGameExtendInfo;
  public int noGameFreshTimeInterval = 0;
  public ArrayList vGameExtendInfo = null;

  static
  {
    if (!TBodyGetGameExtendInfoRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyGetGameExtendInfoRsp()
  {
  }

  public TBodyGetGameExtendInfoRsp(ArrayList paramArrayList, int paramInt)
  {
    this.vGameExtendInfo = paramArrayList;
    this.noGameFreshTimeInterval = paramInt;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyGetGameExtendInfoRsp";
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
    localJceDisplayer.display(this.vGameExtendInfo, "vGameExtendInfo");
    localJceDisplayer.display(this.noGameFreshTimeInterval, "noGameFreshTimeInterval");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.vGameExtendInfo, true);
    localJceDisplayer.displaySimple(this.noGameFreshTimeInterval, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TBodyGetGameExtendInfoRsp localTBodyGetGameExtendInfoRsp;
    do
    {
      return false;
      localTBodyGetGameExtendInfoRsp = (TBodyGetGameExtendInfoRsp)paramObject;
    }
    while ((!JceUtil.equals(this.vGameExtendInfo, localTBodyGetGameExtendInfoRsp.vGameExtendInfo)) || (!JceUtil.equals(this.noGameFreshTimeInterval, localTBodyGetGameExtendInfoRsp.noGameFreshTimeInterval)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyGetGameExtendInfoRsp";
  }

  public int getNoGameFreshTimeInterval()
  {
    return this.noGameFreshTimeInterval;
  }

  public ArrayList getVGameExtendInfo()
  {
    return this.vGameExtendInfo;
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
    if (cache_vGameExtendInfo == null)
    {
      cache_vGameExtendInfo = new ArrayList();
      TGameExtendInfo localTGameExtendInfo = new TGameExtendInfo();
      cache_vGameExtendInfo.add(localTGameExtendInfo);
    }
    this.vGameExtendInfo = ((ArrayList)paramJceInputStream.read(cache_vGameExtendInfo, 0, true));
    this.noGameFreshTimeInterval = paramJceInputStream.read(this.noGameFreshTimeInterval, 1, false);
  }

  public void setNoGameFreshTimeInterval(int paramInt)
  {
    this.noGameFreshTimeInterval = paramInt;
  }

  public void setVGameExtendInfo(ArrayList paramArrayList)
  {
    this.vGameExtendInfo = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.vGameExtendInfo, 0);
    paramJceOutputStream.write(this.noGameFreshTimeInterval, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyGetGameExtendInfoRsp
 * JD-Core Version:    0.6.0
 */