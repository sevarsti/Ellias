package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TBodyQmiGetChatterInfoResp extends JceStruct
  implements Cloneable
{
  static ArrayList cache_chatterInfos;
  public ArrayList chatterInfos = null;

  static
  {
    if (!TBodyQmiGetChatterInfoResp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyQmiGetChatterInfoResp()
  {
  }

  public TBodyQmiGetChatterInfoResp(ArrayList paramArrayList)
  {
    this.chatterInfos = paramArrayList;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyQmiGetChatterInfoResp";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.chatterInfos, "chatterInfos");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.chatterInfos, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TBodyQmiGetChatterInfoResp localTBodyQmiGetChatterInfoResp = (TBodyQmiGetChatterInfoResp)paramObject;
    return JceUtil.equals(this.chatterInfos, localTBodyQmiGetChatterInfoResp.chatterInfos);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyQmiGetChatterInfoResp";
  }

  public ArrayList getChatterInfos()
  {
    return this.chatterInfos;
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
    if (cache_chatterInfos == null)
    {
      cache_chatterInfos = new ArrayList();
      TChatterInfo localTChatterInfo = new TChatterInfo();
      cache_chatterInfos.add(localTChatterInfo);
    }
    this.chatterInfos = ((ArrayList)paramJceInputStream.read(cache_chatterInfos, 0, true));
  }

  public void setChatterInfos(ArrayList paramArrayList)
  {
    this.chatterInfos = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.chatterInfos, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyQmiGetChatterInfoResp
 * JD-Core Version:    0.6.0
 */