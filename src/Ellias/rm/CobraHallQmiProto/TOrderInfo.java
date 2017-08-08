package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TOrderInfo extends JceStruct
  implements Cloneable
{
  public String iconUrl = "";
  public long id = 0L;
  public String name = "";
  public int order = 0;
  public int timestamp = 0;
  public short updateType = 0;

  static
  {
    if (!TOrderInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TOrderInfo()
  {
  }

  public TOrderInfo(long paramLong, int paramInt1, String paramString1, int paramInt2, short paramShort, String paramString2)
  {
    this.id = paramLong;
    this.order = paramInt1;
    this.name = paramString1;
    this.timestamp = paramInt2;
    this.updateType = paramShort;
    this.iconUrl = paramString2;
  }

  public String className()
  {
    return "CobraHallQmiProto.TOrderInfo";
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
    localJceDisplayer.display(this.id, "id");
    localJceDisplayer.display(this.order, "order");
    localJceDisplayer.display(this.name, "name");
    localJceDisplayer.display(this.timestamp, "timestamp");
    localJceDisplayer.display(this.updateType, "updateType");
    localJceDisplayer.display(this.iconUrl, "iconUrl");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.id, true);
    localJceDisplayer.displaySimple(this.order, true);
    localJceDisplayer.displaySimple(this.name, true);
    localJceDisplayer.displaySimple(this.timestamp, true);
    localJceDisplayer.displaySimple(this.updateType, true);
    localJceDisplayer.displaySimple(this.iconUrl, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TOrderInfo localTOrderInfo;
    do
    {
      return false;
      localTOrderInfo = (TOrderInfo)paramObject;
    }
    while ((!JceUtil.equals(this.id, localTOrderInfo.id)) || (!JceUtil.equals(this.order, localTOrderInfo.order)) || (!JceUtil.equals(this.name, localTOrderInfo.name)) || (!JceUtil.equals(this.timestamp, localTOrderInfo.timestamp)) || (!JceUtil.equals(this.updateType, localTOrderInfo.updateType)) || (!JceUtil.equals(this.iconUrl, localTOrderInfo.iconUrl)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TOrderInfo";
  }

  public String getIconUrl()
  {
    return this.iconUrl;
  }

  public long getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public int getOrder()
  {
    return this.order;
  }

  public int getTimestamp()
  {
    return this.timestamp;
  }

  public short getUpdateType()
  {
    return this.updateType;
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
    this.id = paramJceInputStream.read(this.id, 0, true);
    this.order = paramJceInputStream.read(this.order, 1, true);
    this.name = paramJceInputStream.readString(2, false);
    this.timestamp = paramJceInputStream.read(this.timestamp, 3, false);
    this.updateType = paramJceInputStream.read(this.updateType, 4, false);
    this.iconUrl = paramJceInputStream.readString(5, false);
  }

  public void setIconUrl(String paramString)
  {
    this.iconUrl = paramString;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setOrder(int paramInt)
  {
    this.order = paramInt;
  }

  public void setTimestamp(int paramInt)
  {
    this.timestamp = paramInt;
  }

  public void setUpdateType(short paramShort)
  {
    this.updateType = paramShort;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.id, 0);
    paramJceOutputStream.write(this.order, 1);
    if (this.name != null)
      paramJceOutputStream.write(this.name, 2);
    paramJceOutputStream.write(this.timestamp, 3);
    paramJceOutputStream.write(this.updateType, 4);
    if (this.iconUrl != null)
      paramJceOutputStream.write(this.iconUrl, 5);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TOrderInfo
 * JD-Core Version:    0.6.0
 */