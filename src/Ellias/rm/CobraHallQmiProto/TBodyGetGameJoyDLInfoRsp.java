package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TBodyGetGameJoyDLInfoRsp extends JceStruct
  implements Cloneable
{
  static TGameJoyDLInfo cache_gameJoyDLInfo;
  public TGameJoyDLInfo gameJoyDLInfo = null;

  static
  {
    if (!TBodyGetGameJoyDLInfoRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyGetGameJoyDLInfoRsp()
  {
  }

  public TBodyGetGameJoyDLInfoRsp(TGameJoyDLInfo paramTGameJoyDLInfo)
  {
    this.gameJoyDLInfo = paramTGameJoyDLInfo;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyGetGameJoyDLInfoRsp";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.gameJoyDLInfo, "gameJoyDLInfo");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.gameJoyDLInfo, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TBodyGetGameJoyDLInfoRsp localTBodyGetGameJoyDLInfoRsp = (TBodyGetGameJoyDLInfoRsp)paramObject;
    return JceUtil.equals(this.gameJoyDLInfo, localTBodyGetGameJoyDLInfoRsp.gameJoyDLInfo);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyGetGameJoyDLInfoRsp";
  }

  public TGameJoyDLInfo getGameJoyDLInfo()
  {
    return this.gameJoyDLInfo;
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
    if (cache_gameJoyDLInfo == null)
      cache_gameJoyDLInfo = new TGameJoyDLInfo();
    this.gameJoyDLInfo = ((TGameJoyDLInfo)paramJceInputStream.read(cache_gameJoyDLInfo, 0, true));
  }

  public void setGameJoyDLInfo(TGameJoyDLInfo paramTGameJoyDLInfo)
  {
    this.gameJoyDLInfo = paramTGameJoyDLInfo;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.gameJoyDLInfo, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyGetGameJoyDLInfoRsp
 * JD-Core Version:    0.6.0
 */