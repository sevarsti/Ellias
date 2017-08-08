package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TPloginNotice extends JceStruct
  implements Cloneable
{
  public int eventCount = 0;
  public short noticeType = 0;
  public String pluginPkgName = "";

  static
  {
    if (!TPloginNotice.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TPloginNotice()
  {
  }

  public TPloginNotice(String paramString, short paramShort, int paramInt)
  {
    this.pluginPkgName = paramString;
    this.noticeType = paramShort;
    this.eventCount = paramInt;
  }

  public String className()
  {
    return "CobraHallQmiProto.TPloginNotice";
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
    localJceDisplayer.display(this.pluginPkgName, "pluginPkgName");
    localJceDisplayer.display(this.noticeType, "noticeType");
    localJceDisplayer.display(this.eventCount, "eventCount");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.pluginPkgName, true);
    localJceDisplayer.displaySimple(this.noticeType, true);
    localJceDisplayer.displaySimple(this.eventCount, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TPloginNotice localTPloginNotice;
    do
    {
      return false;
      localTPloginNotice = (TPloginNotice)paramObject;
    }
    while ((!JceUtil.equals(this.pluginPkgName, localTPloginNotice.pluginPkgName)) || (!JceUtil.equals(this.noticeType, localTPloginNotice.noticeType)) || (!JceUtil.equals(this.eventCount, localTPloginNotice.eventCount)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TPloginNotice";
  }

  public int getEventCount()
  {
    return this.eventCount;
  }

  public short getNoticeType()
  {
    return this.noticeType;
  }

  public String getPluginPkgName()
  {
    return this.pluginPkgName;
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
    this.pluginPkgName = paramJceInputStream.readString(0, true);
    this.noticeType = paramJceInputStream.read(this.noticeType, 1, true);
    this.eventCount = paramJceInputStream.read(this.eventCount, 2, true);
  }

  public void setEventCount(int paramInt)
  {
    this.eventCount = paramInt;
  }

  public void setNoticeType(short paramShort)
  {
    this.noticeType = paramShort;
  }

  public void setPluginPkgName(String paramString)
  {
    this.pluginPkgName = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.pluginPkgName, 0);
    paramJceOutputStream.write(this.noticeType, 1);
    paramJceOutputStream.write(this.eventCount, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TPloginNotice
 * JD-Core Version:    0.6.0
 */