package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TBodyGetGameExtendInfoReq extends JceStruct
  implements Cloneable
{
  static ArrayList cache_vGamePkgName;
  public ArrayList vGamePkgName = null;

  static
  {
    if (!TBodyGetGameExtendInfoReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyGetGameExtendInfoReq()
  {
  }

  public TBodyGetGameExtendInfoReq(ArrayList paramArrayList)
  {
    this.vGamePkgName = paramArrayList;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyGetGameExtendInfoReq";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.vGamePkgName, "vGamePkgName");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.vGamePkgName, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TBodyGetGameExtendInfoReq localTBodyGetGameExtendInfoReq = (TBodyGetGameExtendInfoReq)paramObject;
    return JceUtil.equals(this.vGamePkgName, localTBodyGetGameExtendInfoReq.vGamePkgName);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyGetGameExtendInfoReq";
  }

  public ArrayList getVGamePkgName()
  {
    return this.vGamePkgName;
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
    if (cache_vGamePkgName == null)
    {
      cache_vGamePkgName = new ArrayList();
      cache_vGamePkgName.add("");
    }
    this.vGamePkgName = ((ArrayList)paramJceInputStream.read(cache_vGamePkgName, 0, true));
  }

  public void setVGamePkgName(ArrayList paramArrayList)
  {
    this.vGamePkgName = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.vGamePkgName, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyGetGameExtendInfoReq
 * JD-Core Version:    0.6.0
 */