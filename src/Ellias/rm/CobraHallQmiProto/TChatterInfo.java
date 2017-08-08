package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TChatterInfo extends JceStruct
  implements Cloneable
{
  public int age = 0;
  public String face = "";
  public int gameVipType = 0;
  public int gender = 0;
  public int level = 0;
  public String qqNickName = "";
  public long uin = 0L;

  static
  {
    if (!TChatterInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TChatterInfo()
  {
  }

  public TChatterInfo(long paramLong, String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3, int paramInt4)
  {
    this.uin = paramLong;
    this.qqNickName = paramString1;
    this.gender = paramInt1;
    this.age = paramInt2;
    this.face = paramString2;
    this.level = paramInt3;
    this.gameVipType = paramInt4;
  }

  public String className()
  {
    return "CobraHallQmiProto.TChatterInfo";
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
    localJceDisplayer.display(this.uin, "uin");
    localJceDisplayer.display(this.qqNickName, "qqNickName");
    localJceDisplayer.display(this.gender, "gender");
    localJceDisplayer.display(this.age, "age");
    localJceDisplayer.display(this.face, "face");
    localJceDisplayer.display(this.level, "level");
    localJceDisplayer.display(this.gameVipType, "gameVipType");
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
    localJceDisplayer.displaySimple(this.gameVipType, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TChatterInfo localTChatterInfo;
    do
    {
      return false;
      localTChatterInfo = (TChatterInfo)paramObject;
    }
    while ((!JceUtil.equals(this.uin, localTChatterInfo.uin)) || (!JceUtil.equals(this.qqNickName, localTChatterInfo.qqNickName)) || (!JceUtil.equals(this.gender, localTChatterInfo.gender)) || (!JceUtil.equals(this.age, localTChatterInfo.age)) || (!JceUtil.equals(this.face, localTChatterInfo.face)) || (!JceUtil.equals(this.level, localTChatterInfo.level)) || (!JceUtil.equals(this.gameVipType, localTChatterInfo.gameVipType)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TChatterInfo";
  }

  public int getAge()
  {
    return this.age;
  }

  public String getFace()
  {
    return this.face;
  }

  public int getGameVipType()
  {
    return this.gameVipType;
  }

  public int getGender()
  {
    return this.gender;
  }

  public int getLevel()
  {
    return this.level;
  }

  public String getQqNickName()
  {
    return this.qqNickName;
  }

  public long getUin()
  {
    return this.uin;
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
    this.uin = paramJceInputStream.read(this.uin, 0, true);
    this.qqNickName = paramJceInputStream.readString(1, true);
    this.gender = paramJceInputStream.read(this.gender, 2, true);
    this.age = paramJceInputStream.read(this.age, 3, true);
    this.face = paramJceInputStream.readString(4, true);
    this.level = paramJceInputStream.read(this.level, 5, false);
    this.gameVipType = paramJceInputStream.read(this.gameVipType, 6, false);
  }

  public void setAge(int paramInt)
  {
    this.age = paramInt;
  }

  public void setFace(String paramString)
  {
    this.face = paramString;
  }

  public void setGameVipType(int paramInt)
  {
    this.gameVipType = paramInt;
  }

  public void setGender(int paramInt)
  {
    this.gender = paramInt;
  }

  public void setLevel(int paramInt)
  {
    this.level = paramInt;
  }

  public void setQqNickName(String paramString)
  {
    this.qqNickName = paramString;
  }

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
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TChatterInfo
 * JD-Core Version:    0.6.0
 */