package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TBodyGetGameJoyDLInfoReq extends JceStruct
  implements Cloneable
{
  public String gamePkgName = "";

  static
  {
    if (!TBodyGetGameJoyDLInfoReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyGetGameJoyDLInfoReq()
  {
  }

  public TBodyGetGameJoyDLInfoReq(String paramString)
  {
    this.gamePkgName = paramString;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyGetGameJoyDLInfoReq";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.gamePkgName, "gamePkgName");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.gamePkgName, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TBodyGetGameJoyDLInfoReq localTBodyGetGameJoyDLInfoReq = (TBodyGetGameJoyDLInfoReq)paramObject;
    return JceUtil.equals(this.gamePkgName, localTBodyGetGameJoyDLInfoReq.gamePkgName);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyGetGameJoyDLInfoReq";
  }

  public String getGamePkgName()
  {
    return this.gamePkgName;
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
    this.gamePkgName = paramJceInputStream.readString(0, true);
  }

  public void setGamePkgName(String paramString)
  {
    this.gamePkgName = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.gamePkgName, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyGetGameJoyDLInfoReq
 * JD-Core Version:    0.6.0
 */