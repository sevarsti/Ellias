package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TGameExtendInfo extends JceStruct
  implements Cloneable
{
  public String bbsUrl = "";
  public String gamePkgName = "";
  public int pluginShowType = 0;
  public String walkthroughUrl = "";
  public int xPos = 0;
  public int yPos = 0;

  static
  {
    if (!TGameExtendInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TGameExtendInfo()
  {
  }

  public TGameExtendInfo(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, int paramInt3)
  {
    this.gamePkgName = paramString1;
    this.walkthroughUrl = paramString2;
    this.bbsUrl = paramString3;
    this.xPos = paramInt1;
    this.yPos = paramInt2;
    this.pluginShowType = paramInt3;
  }

  public String className()
  {
    return "CobraHallQmiProto.TGameExtendInfo";
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
    localJceDisplayer.display(this.gamePkgName, "gamePkgName");
    localJceDisplayer.display(this.walkthroughUrl, "walkthroughUrl");
    localJceDisplayer.display(this.bbsUrl, "bbsUrl");
    localJceDisplayer.display(this.xPos, "xPos");
    localJceDisplayer.display(this.yPos, "yPos");
    localJceDisplayer.display(this.pluginShowType, "pluginShowType");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.gamePkgName, true);
    localJceDisplayer.displaySimple(this.walkthroughUrl, true);
    localJceDisplayer.displaySimple(this.bbsUrl, true);
    localJceDisplayer.displaySimple(this.xPos, true);
    localJceDisplayer.displaySimple(this.yPos, true);
    localJceDisplayer.displaySimple(this.pluginShowType, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TGameExtendInfo localTGameExtendInfo;
    do
    {
      return false;
      localTGameExtendInfo = (TGameExtendInfo)paramObject;
    }
    while ((!JceUtil.equals(this.gamePkgName, localTGameExtendInfo.gamePkgName)) || (!JceUtil.equals(this.walkthroughUrl, localTGameExtendInfo.walkthroughUrl)) || (!JceUtil.equals(this.bbsUrl, localTGameExtendInfo.bbsUrl)) || (!JceUtil.equals(this.xPos, localTGameExtendInfo.xPos)) || (!JceUtil.equals(this.yPos, localTGameExtendInfo.yPos)) || (!JceUtil.equals(this.pluginShowType, localTGameExtendInfo.pluginShowType)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TGameExtendInfo";
  }

  public String getBbsUrl()
  {
    return this.bbsUrl;
  }

  public String getGamePkgName()
  {
    return this.gamePkgName;
  }

  public int getPluginShowType()
  {
    return this.pluginShowType;
  }

  public String getWalkthroughUrl()
  {
    return this.walkthroughUrl;
  }

  public int getXPos()
  {
    return this.xPos;
  }

  public int getYPos()
  {
    return this.yPos;
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
    this.walkthroughUrl = paramJceInputStream.readString(1, false);
    this.bbsUrl = paramJceInputStream.readString(2, false);
    this.xPos = paramJceInputStream.read(this.xPos, 3, false);
    this.yPos = paramJceInputStream.read(this.yPos, 4, false);
    this.pluginShowType = paramJceInputStream.read(this.pluginShowType, 5, false);
  }

  public void setBbsUrl(String paramString)
  {
    this.bbsUrl = paramString;
  }

  public void setGamePkgName(String paramString)
  {
    this.gamePkgName = paramString;
  }

  public void setPluginShowType(int paramInt)
  {
    this.pluginShowType = paramInt;
  }

  public void setWalkthroughUrl(String paramString)
  {
    this.walkthroughUrl = paramString;
  }

  public void setXPos(int paramInt)
  {
    this.xPos = paramInt;
  }

  public void setYPos(int paramInt)
  {
    this.yPos = paramInt;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.gamePkgName, 0);
    if (this.walkthroughUrl != null)
      paramJceOutputStream.write(this.walkthroughUrl, 1);
    if (this.bbsUrl != null)
      paramJceOutputStream.write(this.bbsUrl, 2);
    paramJceOutputStream.write(this.xPos, 3);
    paramJceOutputStream.write(this.yPos, 4);
    paramJceOutputStream.write(this.pluginShowType, 5);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TGameExtendInfo
 * JD-Core Version:    0.6.0
 */