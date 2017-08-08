package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import com.tencent.component.annotation.PluginApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@PluginApi
public final class TBodyGetUserInfoV2Resp extends JceStruct
  implements Cloneable
{
  static Map cache_fieldValues;
  static ArrayList cache_recentPlayGames;
  static ArrayList cache_recentVistorInfo;
  public int age = 0;
  public String face = "";
  public Map fieldValues = null;
  public int gameVipType = 0;
  public int gender = 0;
  public int level = 0;
  public String qqNickName = "";
  public ArrayList recentPlayGames = null;
  public ArrayList recentVistorInfo = null;
  public long uin = 0L;

  static
  {
    if (!TBodyGetUserInfoV2Resp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  @PluginApi
  public TBodyGetUserInfoV2Resp()
  {
  }

  @PluginApi
  public TBodyGetUserInfoV2Resp(long paramLong, String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3, int paramInt4, Map paramMap, ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    this.uin = paramLong;
    this.qqNickName = paramString1;
    this.gender = paramInt1;
    this.age = paramInt2;
    this.face = paramString2;
    this.level = paramInt3;
    this.gameVipType = paramInt4;
    this.fieldValues = paramMap;
    this.recentPlayGames = paramArrayList1;
    this.recentVistorInfo = paramArrayList2;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyGetUserInfoV2Resp";
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

  public void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.uin, "uin");
    localJceDisplayer.display(this.qqNickName, "qqNickName");
    localJceDisplayer.display(this.gender, "gender");
    localJceDisplayer.display(this.age, "age");
    localJceDisplayer.display(this.face, "face");
    localJceDisplayer.display(this.level, "level");
    localJceDisplayer.display(this.gameVipType, "gameVipType");
    localJceDisplayer.display(this.fieldValues, "fieldValues");
    localJceDisplayer.display(this.recentPlayGames, "recentPlayGames");
    localJceDisplayer.display(this.recentVistorInfo, "recentVistorInfo");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.uin, true);
    localJceDisplayer.displaySimple(this.qqNickName, true);
    localJceDisplayer.displaySimple(this.gender, true);
    localJceDisplayer.displaySimple(this.age, true);
    localJceDisplayer.displaySimple(this.face, true);
    localJceDisplayer.displaySimple(this.level, true);
    localJceDisplayer.displaySimple(this.gameVipType, true);
    localJceDisplayer.displaySimple(this.fieldValues, true);
    localJceDisplayer.displaySimple(this.recentPlayGames, true);
    localJceDisplayer.displaySimple(this.recentVistorInfo, false);
  }

  @PluginApi
  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TBodyGetUserInfoV2Resp localTBodyGetUserInfoV2Resp;
    do
    {
      return false;
      localTBodyGetUserInfoV2Resp = (TBodyGetUserInfoV2Resp)paramObject;
    }
    while ((!JceUtil.equals(this.uin, localTBodyGetUserInfoV2Resp.uin)) || (!JceUtil.equals(this.qqNickName, localTBodyGetUserInfoV2Resp.qqNickName)) || (!JceUtil.equals(this.gender, localTBodyGetUserInfoV2Resp.gender)) || (!JceUtil.equals(this.age, localTBodyGetUserInfoV2Resp.age)) || (!JceUtil.equals(this.face, localTBodyGetUserInfoV2Resp.face)) || (!JceUtil.equals(this.level, localTBodyGetUserInfoV2Resp.level)) || (!JceUtil.equals(this.gameVipType, localTBodyGetUserInfoV2Resp.gameVipType)) || (!JceUtil.equals(this.fieldValues, localTBodyGetUserInfoV2Resp.fieldValues)) || (!JceUtil.equals(this.recentPlayGames, localTBodyGetUserInfoV2Resp.recentPlayGames)) || (!JceUtil.equals(this.recentVistorInfo, localTBodyGetUserInfoV2Resp.recentVistorInfo)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyGetUserInfoV2Resp";
  }

  @PluginApi
  public int getAge()
  {
    return this.age;
  }

  @PluginApi
  public String getFace()
  {
    return this.face;
  }

  @PluginApi
  public Map getFieldValues()
  {
    return this.fieldValues;
  }

  @PluginApi
  public int getGameVipType()
  {
    return this.gameVipType;
  }

  @PluginApi
  public int getGender()
  {
    return this.gender;
  }

  @PluginApi
  public int getLevel()
  {
    return this.level;
  }

  @PluginApi
  public String getQqNickName()
  {
    return this.qqNickName;
  }

  @PluginApi
  public ArrayList getRecentPlayGames()
  {
    return this.recentPlayGames;
  }

  @PluginApi
  public ArrayList getRecentVistorInfo()
  {
    return this.recentVistorInfo;
  }

  @PluginApi
  public long getUin()
  {
    return this.uin;
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
    this.uin = paramJceInputStream.read(this.uin, 0, true);
    this.qqNickName = paramJceInputStream.readString(1, true);
    this.gender = paramJceInputStream.read(this.gender, 2, true);
    this.age = paramJceInputStream.read(this.age, 3, true);
    this.face = paramJceInputStream.readString(4, true);
    this.level = paramJceInputStream.read(this.level, 5, true);
    this.gameVipType = paramJceInputStream.read(this.gameVipType, 6, true);
    if (cache_fieldValues == null)
    {
      cache_fieldValues = new HashMap();
      Integer localInteger = Integer.valueOf(0);
      cache_fieldValues.put(localInteger, "");
    }
    this.fieldValues = ((Map)paramJceInputStream.read(cache_fieldValues, 7, false));
    if (cache_recentPlayGames == null)
    {
      cache_recentPlayGames = new ArrayList();
      TRecentPlayGameInfo localTRecentPlayGameInfo = new TRecentPlayGameInfo();
      cache_recentPlayGames.add(localTRecentPlayGameInfo);
    }
    this.recentPlayGames = ((ArrayList)paramJceInputStream.read(cache_recentPlayGames, 8, false));
    if (cache_recentVistorInfo == null)
    {
      cache_recentVistorInfo = new ArrayList();
      TRecentVistorInfo localTRecentVistorInfo = new TRecentVistorInfo();
      cache_recentVistorInfo.add(localTRecentVistorInfo);
    }
    this.recentVistorInfo = ((ArrayList)paramJceInputStream.read(cache_recentVistorInfo, 9, false));
  }

  @PluginApi
  public void setAge(int paramInt)
  {
    this.age = paramInt;
  }

  @PluginApi
  public void setFace(String paramString)
  {
    this.face = paramString;
  }

  @PluginApi
  public void setFieldValues(Map paramMap)
  {
    this.fieldValues = paramMap;
  }

  @PluginApi
  public void setGameVipType(int paramInt)
  {
    this.gameVipType = paramInt;
  }

  @PluginApi
  public void setGender(int paramInt)
  {
    this.gender = paramInt;
  }

  public void setLevel(int paramInt)
  {
    this.level = paramInt;
  }

  @PluginApi
  public void setQqNickName(String paramString)
  {
    this.qqNickName = paramString;
  }

  @PluginApi
  public void setRecentPlayGames(ArrayList paramArrayList)
  {
    this.recentPlayGames = paramArrayList;
  }

  @PluginApi
  public void setRecentVistorInfo(ArrayList paramArrayList)
  {
    this.recentVistorInfo = paramArrayList;
  }

  @PluginApi
  public void setUin(long paramLong)
  {
    this.uin = paramLong;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.uin, 0);
    paramJceOutputStream.write(this.qqNickName, 1);
    paramJceOutputStream.write(this.gender, 2);
    paramJceOutputStream.write(this.age, 3);
    paramJceOutputStream.write(this.face, 4);
    paramJceOutputStream.write(this.level, 5);
    paramJceOutputStream.write(this.gameVipType, 6);
    if (this.fieldValues != null)
      paramJceOutputStream.write(this.fieldValues, 7);
    if (this.recentPlayGames != null)
      paramJceOutputStream.write(this.recentPlayGames, 8);
    if (this.recentVistorInfo != null)
      paramJceOutputStream.write(this.recentVistorInfo, 9);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyGetUserInfoV2Resp
 * JD-Core Version:    0.6.0
 */