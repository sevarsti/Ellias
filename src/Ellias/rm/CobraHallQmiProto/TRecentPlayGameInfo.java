package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import com.tencent.component.annotation.PluginApi;

@PluginApi
public final class TRecentPlayGameInfo extends JceStruct
  implements Cloneable
{
  public String gameIcon = "";
  public long gameId = 0L;
  public String gameName = "";
  public int recentPlayTime = 0;

  static
  {
    if (!TRecentPlayGameInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  @PluginApi
  public TRecentPlayGameInfo()
  {
  }

  @PluginApi
  public TRecentPlayGameInfo(long paramLong, String paramString1, int paramInt, String paramString2)
  {
    this.gameId = paramLong;
    this.gameName = paramString1;
    this.recentPlayTime = paramInt;
    this.gameIcon = paramString2;
  }

  public String className()
  {
    return "CobraHallQmiProto.TRecentPlayGameInfo";
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
    localJceDisplayer.display(this.gameId, "gameId");
    localJceDisplayer.display(this.gameName, "gameName");
    localJceDisplayer.display(this.recentPlayTime, "recentPlayTime");
    localJceDisplayer.display(this.gameIcon, "gameIcon");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.gameId, true);
    localJceDisplayer.displaySimple(this.gameName, true);
    localJceDisplayer.displaySimple(this.recentPlayTime, true);
    localJceDisplayer.displaySimple(this.gameIcon, false);
  }

  @PluginApi
  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TRecentPlayGameInfo localTRecentPlayGameInfo;
    do
    {
      return false;
      localTRecentPlayGameInfo = (TRecentPlayGameInfo)paramObject;
    }
    while ((!JceUtil.equals(this.gameId, localTRecentPlayGameInfo.gameId)) || (!JceUtil.equals(this.gameName, localTRecentPlayGameInfo.gameName)) || (!JceUtil.equals(this.recentPlayTime, localTRecentPlayGameInfo.recentPlayTime)) || (!JceUtil.equals(this.gameIcon, localTRecentPlayGameInfo.gameIcon)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TRecentPlayGameInfo";
  }

  @PluginApi
  public String getGameIcon()
  {
    return this.gameIcon;
  }

  @PluginApi
  public long getGameId()
  {
    return this.gameId;
  }

  @PluginApi
  public String getGameName()
  {
    return this.gameName;
  }

  @PluginApi
  public int getRecentPlayTime()
  {
    return this.recentPlayTime;
  }

  @PluginApi
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
    this.gameId = paramJceInputStream.read(this.gameId, 0, true);
    this.gameName = paramJceInputStream.readString(1, true);
    this.recentPlayTime = paramJceInputStream.read(this.recentPlayTime, 2, true);
    this.gameIcon = paramJceInputStream.readString(3, false);
  }

  @PluginApi
  public void setGameIcon(String paramString)
  {
    this.gameIcon = paramString;
  }

  @PluginApi
  public void setGameId(long paramLong)
  {
    this.gameId = paramLong;
  }

  @PluginApi
  public void setGameName(String paramString)
  {
    this.gameName = paramString;
  }

  @PluginApi
  public void setRecentPlayTime(int paramInt)
  {
    this.recentPlayTime = paramInt;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.gameId, 0);
    paramJceOutputStream.write(this.gameName, 1);
    paramJceOutputStream.write(this.recentPlayTime, 2);
    if (this.gameIcon != null)
      paramJceOutputStream.write(this.gameIcon, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TRecentPlayGameInfo
 * JD-Core Version:    0.6.0
 */