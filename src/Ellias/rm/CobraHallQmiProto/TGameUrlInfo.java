package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TGameUrlInfo extends JceStruct
  implements Cloneable
{
  public String sBbsUrl = "";
  public String sGamePkgName = "";
  public String sWalkthroughUrl = "";

  static
  {
    if (!TGameUrlInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TGameUrlInfo()
  {
  }

  public TGameUrlInfo(String paramString1, String paramString2, String paramString3)
  {
    this.sGamePkgName = paramString1;
    this.sWalkthroughUrl = paramString2;
    this.sBbsUrl = paramString3;
  }

  public String className()
  {
    return "CobraHallQmiProto.TGameUrlInfo";
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
    localJceDisplayer.display(this.sGamePkgName, "sGamePkgName");
    localJceDisplayer.display(this.sWalkthroughUrl, "sWalkthroughUrl");
    localJceDisplayer.display(this.sBbsUrl, "sBbsUrl");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.sGamePkgName, true);
    localJceDisplayer.displaySimple(this.sWalkthroughUrl, true);
    localJceDisplayer.displaySimple(this.sBbsUrl, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TGameUrlInfo localTGameUrlInfo;
    do
    {
      return false;
      localTGameUrlInfo = (TGameUrlInfo)paramObject;
    }
    while ((!JceUtil.equals(this.sGamePkgName, localTGameUrlInfo.sGamePkgName)) || (!JceUtil.equals(this.sWalkthroughUrl, localTGameUrlInfo.sWalkthroughUrl)) || (!JceUtil.equals(this.sBbsUrl, localTGameUrlInfo.sBbsUrl)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TGameUrlInfo";
  }

  public String getSBbsUrl()
  {
    return this.sBbsUrl;
  }

  public String getSGamePkgName()
  {
    return this.sGamePkgName;
  }

  public String getSWalkthroughUrl()
  {
    return this.sWalkthroughUrl;
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
    this.sGamePkgName = paramJceInputStream.readString(0, true);
    this.sWalkthroughUrl = paramJceInputStream.readString(1, true);
    this.sBbsUrl = paramJceInputStream.readString(2, true);
  }

  public void setSBbsUrl(String paramString)
  {
    this.sBbsUrl = paramString;
  }

  public void setSGamePkgName(String paramString)
  {
    this.sGamePkgName = paramString;
  }

  public void setSWalkthroughUrl(String paramString)
  {
    this.sWalkthroughUrl = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.sGamePkgName, 0);
    paramJceOutputStream.write(this.sWalkthroughUrl, 1);
    paramJceOutputStream.write(this.sBbsUrl, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TGameUrlInfo
 * JD-Core Version:    0.6.0
 */