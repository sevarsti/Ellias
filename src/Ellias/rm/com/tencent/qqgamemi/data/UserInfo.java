package com.tencent.qqgamemi.data;

import CobraHallQmiProto.TBodyGetUserInfoV2Resp;
import com.tencent.component.db.annotation.Column;
import com.tencent.component.db.annotation.Id;
import com.tencent.component.db.annotation.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Table(name="UserInfoTable", version=2)
public class UserInfo
{

  @Column
  private int age;

  @Column
  private String faceUrl;

  @Column
  private String nickName;

  @Column
  private String place;

  @Column
  private ArrayList recentPlayGames;

  @Column
  private int sex;

  @Column
  private String sign;

  @Column
  private String star;

  @Id(strategy=1)
  private long uin;

  public UserInfo()
  {
  }

  public UserInfo(TBodyGetUserInfoV2Resp paramTBodyGetUserInfoV2Resp)
  {
    this.uin = paramTBodyGetUserInfoV2Resp.uin;
    this.nickName = paramTBodyGetUserInfoV2Resp.qqNickName;
    this.sex = paramTBodyGetUserInfoV2Resp.gender;
    this.age = paramTBodyGetUserInfoV2Resp.age;
    this.star = translate2Constellation((String)paramTBodyGetUserInfoV2Resp.fieldValues.get(Integer.valueOf(213)));
    this.faceUrl = paramTBodyGetUserInfoV2Resp.face;
    this.sign = ((String)paramTBodyGetUserInfoV2Resp.fieldValues.get(Integer.valueOf(211)));
    this.place = ((String)paramTBodyGetUserInfoV2Resp.fieldValues.get(Integer.valueOf(6)));
    this.recentPlayGames = paramTBodyGetUserInfoV2Resp.recentPlayGames;
  }

  public static int safeParseInt(String paramString)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (Exception localException)
    {
    }
    return -1;
  }

  private String translate2Constellation(String paramString)
  {
    if (paramString == null)
      return "";
    switch (safeParseInt(paramString))
    {
    default:
      return "";
    case 0:
      return "水瓶座";
    case 1:
      return "双鱼座";
    case 2:
      return "牡羊座";
    case 3:
      return "金牛座";
    case 4:
      return "双子座";
    case 5:
      return "巨蟹座";
    case 6:
      return "狮子座";
    case 7:
      return "处女座";
    case 8:
      return "天秤座";
    case 9:
      return "天蝎座";
    case 10:
      return "射手座";
    case 11:
    }
    return "魔羯座";
  }

  public int getAge()
  {
    return this.age;
  }

  public String getFaceUrl()
  {
    return this.faceUrl;
  }

  public String getNickName()
  {
    return this.nickName;
  }

  public String getPlace()
  {
    return this.place;
  }

  public List getRecentPlayGames()
  {
    return this.recentPlayGames;
  }

  public int getSex()
  {
    return this.sex;
  }

  public String getSign()
  {
    return this.sign;
  }

  public String getStar()
  {
    return this.star;
  }

  public long getUin()
  {
    return this.uin;
  }

  public String toString()
  {
    return "[" + this.uin + "," + this.nickName + "," + this.sex + "," + this.age + "," + this.sign + "," + this.star + "," + this.place + "," + this.recentPlayGames + "]";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.data.UserInfo
 * JD-Core Version:    0.6.0
 */