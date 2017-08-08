package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import com.tencent.component.annotation.PluginApi;

@PluginApi
public final class TRecentVistorInfo extends JceStruct
  implements Cloneable
{
  public String friendFace = "";
  public long friendUin = 0L;
  public int gender = 0;
  public String mood = "";
  public String nickName = "";
  public int recentVisiteTime = 0;

  static
  {
    if (!TRecentVistorInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  @PluginApi
  public TRecentVistorInfo()
  {
  }

  @PluginApi
  public TRecentVistorInfo(long paramLong, String paramString1, int paramInt1, String paramString2, int paramInt2, String paramString3)
  {
    this.friendUin = paramLong;
    this.friendFace = paramString1;
    this.recentVisiteTime = paramInt1;
    this.nickName = paramString2;
    this.gender = paramInt2;
    this.mood = paramString3;
  }

  public String className()
  {
    return "CobraHallQmiProto.TRecentVistorInfo";
  }

  @PluginApi
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

  @PluginApi
  public void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.friendUin, "friendUin");
    localJceDisplayer.display(this.friendFace, "friendFace");
    localJceDisplayer.display(this.recentVisiteTime, "recentVisiteTime");
    localJceDisplayer.display(this.nickName, "nickName");
    localJceDisplayer.display(this.gender, "gender");
    localJceDisplayer.display(this.mood, "mood");
  }

  @PluginApi
  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.friendUin, true);
    localJceDisplayer.displaySimple(this.friendFace, true);
    localJceDisplayer.displaySimple(this.recentVisiteTime, true);
    localJceDisplayer.displaySimple(this.nickName, true);
    localJceDisplayer.displaySimple(this.gender, true);
    localJceDisplayer.displaySimple(this.mood, false);
  }

  @PluginApi
  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TRecentVistorInfo localTRecentVistorInfo;
    do
    {
      return false;
      localTRecentVistorInfo = (TRecentVistorInfo)paramObject;
    }
    while ((!JceUtil.equals(this.friendUin, localTRecentVistorInfo.friendUin)) || (!JceUtil.equals(this.friendFace, localTRecentVistorInfo.friendFace)) || (!JceUtil.equals(this.recentVisiteTime, localTRecentVistorInfo.recentVisiteTime)) || (!JceUtil.equals(this.nickName, localTRecentVistorInfo.nickName)) || (!JceUtil.equals(this.gender, localTRecentVistorInfo.gender)) || (!JceUtil.equals(this.mood, localTRecentVistorInfo.mood)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TRecentVistorInfo";
  }

  @PluginApi
  public String getFriendFace()
  {
    return this.friendFace;
  }

  @PluginApi
  public long getFriendUin()
  {
    return this.friendUin;
  }

  @PluginApi
  public int getGender()
  {
    return this.gender;
  }

  @PluginApi
  public String getMood()
  {
    return this.mood;
  }

  @PluginApi
  public String getNickName()
  {
    return this.nickName;
  }

  @PluginApi
  public int getRecentVisiteTime()
  {
    return this.recentVisiteTime;
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

  @PluginApi
  public void readFrom(JceInputStream paramJceInputStream)
  {
    this.friendUin = paramJceInputStream.read(this.friendUin, 0, true);
    this.friendFace = paramJceInputStream.readString(1, true);
    this.recentVisiteTime = paramJceInputStream.read(this.recentVisiteTime, 2, true);
    this.nickName = paramJceInputStream.readString(3, true);
    this.gender = paramJceInputStream.read(this.gender, 4, true);
    this.mood = paramJceInputStream.readString(5, true);
  }

  @PluginApi
  public void setFriendFace(String paramString)
  {
    this.friendFace = paramString;
  }

  @PluginApi
  public void setFriendUin(long paramLong)
  {
    this.friendUin = paramLong;
  }

  @PluginApi
  public void setGender(int paramInt)
  {
    this.gender = paramInt;
  }

  @PluginApi
  public void setMood(String paramString)
  {
    this.mood = paramString;
  }

  @PluginApi
  public void setNickName(String paramString)
  {
    this.nickName = paramString;
  }

  @PluginApi
  public void setRecentVisiteTime(int paramInt)
  {
    this.recentVisiteTime = paramInt;
  }

  @PluginApi
  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.friendUin, 0);
    paramJceOutputStream.write(this.friendFace, 1);
    paramJceOutputStream.write(this.recentVisiteTime, 2);
    paramJceOutputStream.write(this.nickName, 3);
    paramJceOutputStream.write(this.gender, 4);
    paramJceOutputStream.write(this.mood, 5);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TRecentVistorInfo
 * JD-Core Version:    0.6.0
 */