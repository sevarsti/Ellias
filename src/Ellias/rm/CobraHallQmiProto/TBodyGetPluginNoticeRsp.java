package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TBodyGetPluginNoticeRsp extends JceStruct
  implements Cloneable
{
  static ArrayList cache_pluginNoticeList;
  public int getInterval = 0;
  public int getIntervalLonger = 0;
  public ArrayList pluginNoticeList = null;

  static
  {
    if (!TBodyGetPluginNoticeRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyGetPluginNoticeRsp()
  {
  }

  public TBodyGetPluginNoticeRsp(ArrayList paramArrayList, int paramInt1, int paramInt2)
  {
    this.pluginNoticeList = paramArrayList;
    this.getInterval = paramInt1;
    this.getIntervalLonger = paramInt2;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyGetPluginNoticeRsp";
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
    localJceDisplayer.display(this.pluginNoticeList, "pluginNoticeList");
    localJceDisplayer.display(this.getInterval, "getInterval");
    localJceDisplayer.display(this.getIntervalLonger, "getIntervalLonger");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.pluginNoticeList, true);
    localJceDisplayer.displaySimple(this.getInterval, true);
    localJceDisplayer.displaySimple(this.getIntervalLonger, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TBodyGetPluginNoticeRsp localTBodyGetPluginNoticeRsp;
    do
    {
      return false;
      localTBodyGetPluginNoticeRsp = (TBodyGetPluginNoticeRsp)paramObject;
    }
    while ((!JceUtil.equals(this.pluginNoticeList, localTBodyGetPluginNoticeRsp.pluginNoticeList)) || (!JceUtil.equals(this.getInterval, localTBodyGetPluginNoticeRsp.getInterval)) || (!JceUtil.equals(this.getIntervalLonger, localTBodyGetPluginNoticeRsp.getIntervalLonger)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyGetPluginNoticeRsp";
  }

  public int getGetInterval()
  {
    return this.getInterval;
  }

  public int getGetIntervalLonger()
  {
    return this.getIntervalLonger;
  }

  public ArrayList getPluginNoticeList()
  {
    return this.pluginNoticeList;
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
    if (cache_pluginNoticeList == null)
    {
      cache_pluginNoticeList = new ArrayList();
      TPloginNotice localTPloginNotice = new TPloginNotice();
      cache_pluginNoticeList.add(localTPloginNotice);
    }
    this.pluginNoticeList = ((ArrayList)paramJceInputStream.read(cache_pluginNoticeList, 0, true));
    this.getInterval = paramJceInputStream.read(this.getInterval, 1, true);
    this.getIntervalLonger = paramJceInputStream.read(this.getIntervalLonger, 2, false);
  }

  public void setGetInterval(int paramInt)
  {
    this.getInterval = paramInt;
  }

  public void setGetIntervalLonger(int paramInt)
  {
    this.getIntervalLonger = paramInt;
  }

  public void setPluginNoticeList(ArrayList paramArrayList)
  {
    this.pluginNoticeList = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.pluginNoticeList, 0);
    paramJceOutputStream.write(this.getInterval, 1);
    paramJceOutputStream.write(this.getIntervalLonger, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyGetPluginNoticeRsp
 * JD-Core Version:    0.6.0
 */