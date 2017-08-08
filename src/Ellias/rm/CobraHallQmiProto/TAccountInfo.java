package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TAccountInfo extends JceStruct
  implements Cloneable
{
  public long qqUin = 0L;
  public String sybAccount = "";
  public long sybId = 0L;
  public String wechatName = "";
  public String wechatOpenId = "";

  static
  {
    if (!TAccountInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TAccountInfo()
  {
  }

  public TAccountInfo(long paramLong1, String paramString1, long paramLong2, String paramString2, String paramString3)
  {
    this.sybId = paramLong1;
    this.sybAccount = paramString1;
    this.qqUin = paramLong2;
    this.wechatOpenId = paramString2;
    this.wechatName = paramString3;
  }

  public String className()
  {
    return "CobraHallQmiProto.TAccountInfo";
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
    localJceDisplayer.display(this.sybId, "sybId");
    localJceDisplayer.display(this.sybAccount, "sybAccount");
    localJceDisplayer.display(this.qqUin, "qqUin");
    localJceDisplayer.display(this.wechatOpenId, "wechatOpenId");
    localJceDisplayer.display(this.wechatName, "wechatName");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.sybId, true);
    localJceDisplayer.displaySimple(this.sybAccount, true);
    localJceDisplayer.displaySimple(this.qqUin, true);
    localJceDisplayer.displaySimple(this.wechatOpenId, true);
    localJceDisplayer.displaySimple(this.wechatName, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TAccountInfo localTAccountInfo;
    do
    {
      return false;
      localTAccountInfo = (TAccountInfo)paramObject;
    }
    while ((!JceUtil.equals(this.sybId, localTAccountInfo.sybId)) || (!JceUtil.equals(this.sybAccount, localTAccountInfo.sybAccount)) || (!JceUtil.equals(this.qqUin, localTAccountInfo.qqUin)) || (!JceUtil.equals(this.wechatOpenId, localTAccountInfo.wechatOpenId)) || (!JceUtil.equals(this.wechatName, localTAccountInfo.wechatName)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TAccountInfo";
  }

  public long getQqUin()
  {
    return this.qqUin;
  }

  public String getSybAccount()
  {
    return this.sybAccount;
  }

  public long getSybId()
  {
    return this.sybId;
  }

  public String getWechatName()
  {
    return this.wechatName;
  }

  public String getWechatOpenId()
  {
    return this.wechatOpenId;
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
    this.sybId = paramJceInputStream.read(this.sybId, 0, false);
    this.sybAccount = paramJceInputStream.readString(1, false);
    this.qqUin = paramJceInputStream.read(this.qqUin, 2, false);
    this.wechatOpenId = paramJceInputStream.readString(3, false);
    this.wechatName = paramJceInputStream.readString(4, false);
  }

  public void setQqUin(long paramLong)
  {
    this.qqUin = paramLong;
  }

  public void setSybAccount(String paramString)
  {
    this.sybAccount = paramString;
  }

  public void setSybId(long paramLong)
  {
    this.sybId = paramLong;
  }

  public void setWechatName(String paramString)
  {
    this.wechatName = paramString;
  }

  public void setWechatOpenId(String paramString)
  {
    this.wechatOpenId = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.sybId, 0);
    if (this.sybAccount != null)
      paramJceOutputStream.write(this.sybAccount, 1);
    paramJceOutputStream.write(this.qqUin, 2);
    if (this.wechatOpenId != null)
      paramJceOutputStream.write(this.wechatOpenId, 3);
    if (this.wechatName != null)
      paramJceOutputStream.write(this.wechatName, 4);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TAccountInfo
 * JD-Core Version:    0.6.0
 */