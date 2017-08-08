package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TBodyGetPluginNoticeReq extends JceStruct
  implements Cloneable
{
  static ArrayList cache_pluginPkgNameList;
  public ArrayList pluginPkgNameList = null;

  static
  {
    if (!TBodyGetPluginNoticeReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyGetPluginNoticeReq()
  {
  }

  public TBodyGetPluginNoticeReq(ArrayList paramArrayList)
  {
    this.pluginPkgNameList = paramArrayList;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyGetPluginNoticeReq";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.pluginPkgNameList, "pluginPkgNameList");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.pluginPkgNameList, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TBodyGetPluginNoticeReq localTBodyGetPluginNoticeReq = (TBodyGetPluginNoticeReq)paramObject;
    return JceUtil.equals(this.pluginPkgNameList, localTBodyGetPluginNoticeReq.pluginPkgNameList);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyGetPluginNoticeReq";
  }

  public ArrayList getPluginPkgNameList()
  {
    return this.pluginPkgNameList;
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
    if (cache_pluginPkgNameList == null)
    {
      cache_pluginPkgNameList = new ArrayList();
      cache_pluginPkgNameList.add("");
    }
    this.pluginPkgNameList = ((ArrayList)paramJceInputStream.read(cache_pluginPkgNameList, 0, true));
  }

  public void setPluginPkgNameList(ArrayList paramArrayList)
  {
    this.pluginPkgNameList = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.pluginPkgNameList, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyGetPluginNoticeReq
 * JD-Core Version:    0.6.0
 */