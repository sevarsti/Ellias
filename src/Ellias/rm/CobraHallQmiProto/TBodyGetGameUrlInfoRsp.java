package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TBodyGetGameUrlInfoRsp extends JceStruct
  implements Cloneable
{
  static ArrayList cache_vGameUrlInfo;
  public ArrayList vGameUrlInfo = null;

  static
  {
    if (!TBodyGetGameUrlInfoRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyGetGameUrlInfoRsp()
  {
  }

  public TBodyGetGameUrlInfoRsp(ArrayList paramArrayList)
  {
    this.vGameUrlInfo = paramArrayList;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyGetGameUrlInfoRsp";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.vGameUrlInfo, "vGameUrlInfo");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.vGameUrlInfo, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TBodyGetGameUrlInfoRsp localTBodyGetGameUrlInfoRsp = (TBodyGetGameUrlInfoRsp)paramObject;
    return JceUtil.equals(this.vGameUrlInfo, localTBodyGetGameUrlInfoRsp.vGameUrlInfo);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyGetGameUrlInfoRsp";
  }

  public ArrayList getVGameUrlInfo()
  {
    return this.vGameUrlInfo;
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
    if (cache_vGameUrlInfo == null)
    {
      cache_vGameUrlInfo = new ArrayList();
      TGameUrlInfo localTGameUrlInfo = new TGameUrlInfo();
      cache_vGameUrlInfo.add(localTGameUrlInfo);
    }
    this.vGameUrlInfo = ((ArrayList)paramJceInputStream.read(cache_vGameUrlInfo, 0, true));
  }

  public void setVGameUrlInfo(ArrayList paramArrayList)
  {
    this.vGameUrlInfo = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.vGameUrlInfo, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyGetGameUrlInfoRsp
 * JD-Core Version:    0.6.0
 */