package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TBodyQmiGetChatterInfoReq extends JceStruct
  implements Cloneable
{
  static ArrayList cache_uins;
  public ArrayList uins = null;

  static
  {
    if (!TBodyQmiGetChatterInfoReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyQmiGetChatterInfoReq()
  {
  }

  public TBodyQmiGetChatterInfoReq(ArrayList paramArrayList)
  {
    this.uins = paramArrayList;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyQmiGetChatterInfoReq";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.uins, "uins");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.uins, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TBodyQmiGetChatterInfoReq localTBodyQmiGetChatterInfoReq = (TBodyQmiGetChatterInfoReq)paramObject;
    return JceUtil.equals(this.uins, localTBodyQmiGetChatterInfoReq.uins);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyQmiGetChatterInfoReq";
  }

  public ArrayList getUins()
  {
    return this.uins;
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
    if (cache_uins == null)
    {
      cache_uins = new ArrayList();
      Long localLong = Long.valueOf(0L);
      cache_uins.add(localLong);
    }
    this.uins = ((ArrayList)paramJceInputStream.read(cache_uins, 0, true));
  }

  public void setUins(ArrayList paramArrayList)
  {
    this.uins = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.uins, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyQmiGetChatterInfoReq
 * JD-Core Version:    0.6.0
 */