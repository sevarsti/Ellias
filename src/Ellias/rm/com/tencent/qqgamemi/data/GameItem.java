package com.tencent.qqgamemi.data;

import com.tencent.component.db.annotation.Column;
import com.tencent.component.db.annotation.Id;
import com.tencent.component.db.annotation.Table;
import com.tencent.component.utils.log.LogUtil;

@Table(name="GameItemTable", version=6)
public class GameItem
{
  public static final int GAME_COMPLETE = 3;
  public static final int GAME_NOT_GAME = 0;
  public static final int GAME_NO_FORUM = 1;
  public static final int GAME_NO_STRATEGY = 2;
  public static final String GAME_TYPE = "type";
  public static final int GAME_UNKNOW = -1;
  public static final int NORUN = 1;
  public static final int RUNNOSHOW = 2;
  public static final int SHOW = 3;
  private static final String TAG = GameItem.class.getSimpleName();

  @Column
  public boolean bSupport;

  @Column
  public Point configPoint;

  @Column
  public String forumUrl;

  @Column
  public boolean isConfigPoint;

  @Column
  public Point movePoint;

  @Column
  public String name;

  @Id(strategy=1)
  public String packageName;

  @Column
  public int showType;

  @Column
  public String strategyUrl;

  @Column(name="type")
  public int type;

  @Column
  public long updateTimeStamp;

  public GameItem()
  {
  }

  private GameItem(String paramString1, String paramString2)
  {
    this.packageName = paramString1;
    this.name = paramString2;
    this.strategyUrl = "";
    this.forumUrl = "";
    this.updateTimeStamp = System.currentTimeMillis();
    this.bSupport = false;
    this.type = 0;
    this.isConfigPoint = false;
    this.showType = 1;
  }

  private GameItem(String paramString1, String paramString2, String paramString3, String paramString4, Point paramPoint, int paramInt)
  {
    this.packageName = paramString1;
    this.name = paramString2;
    this.strategyUrl = paramString3;
    this.forumUrl = paramString4;
    this.updateTimeStamp = System.currentTimeMillis();
    this.bSupport = true;
    if (paramString3.equals(""))
    {
      this.type = 2;
      if (paramPoint == null)
        break label125;
      this.isConfigPoint = true;
      this.configPoint = paramPoint;
      this.movePoint = new Point(-1, -1);
    }
    while (true)
    {
      if ((paramInt > 1) || (paramInt < 3))
        break label133;
      this.showType = paramInt;
      return;
      if (paramString4.equals(""))
      {
        this.type = 1;
        break;
      }
      this.type = 3;
      break;
      label125: this.isConfigPoint = false;
    }
    label133: this.showType = 3;
  }

  private String getGameTypeDescribe(int paramInt)
  {
    if (paramInt == 0)
      return "not a game";
    if (paramInt == 3)
      return "a game";
    if (paramInt == 1)
      return "game no forum";
    if (paramInt == 2)
      return "game no strategy";
    return "unknow type";
  }

  public static GameItem makeNotGameItem(String paramString1, String paramString2)
  {
    return new GameItem(paramString1, paramString2);
  }

  public static GameItem makeOneGameItem(String paramString1, String paramString2, String paramString3, String paramString4, Point paramPoint, int paramInt)
  {
    return new GameItem(paramString1, paramString2, paramString3, paramString4, paramPoint, paramInt);
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    GameItem localGameItem;
    do
      while (true)
      {
        return true;
        if (paramObject == null)
          return false;
        if (getClass() != paramObject.getClass())
          return false;
        localGameItem = (GameItem)paramObject;
        if (this.packageName != null)
          break;
        if (localGameItem.packageName != null)
          return false;
      }
    while (this.packageName.equals(localGameItem.packageName));
    return false;
  }

  public int hashCode()
  {
    if (this.packageName == null);
    for (int i = 0; ; i = this.packageName.hashCode())
      return i + 31;
  }

  public boolean isGame()
  {
    return (this.type == 3) || (this.type == 1) || (this.type == 2);
  }

  public boolean isShow()
  {
    if (this.showType == 3)
      return true;
    LogUtil.d(TAG, "isShow is false");
    return false;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("[").append(this.name).append(";").append(this.packageName).append(";").append(this.strategyUrl).append(";").append(this.forumUrl).append(";").append(getGameTypeDescribe(this.type)).append(";");
    if (this.bSupport);
    for (String str = "bSupport"; ; str = "not Support")
      return str + ";" + this.updateTimeStamp + ";" + this.isConfigPoint + ";" + this.configPoint + ";" + this.movePoint + ";" + this.showType + ";" + "]";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.data.GameItem
 * JD-Core Version:    0.6.0
 */