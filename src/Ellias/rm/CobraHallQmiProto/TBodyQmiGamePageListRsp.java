package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TBodyQmiGamePageListRsp extends JceStruct
  implements Cloneable
{
  static ArrayList cache_gameList;
  public String categoryIntro = "";
  public ArrayList gameList = null;
  public String gamePkgName = "";
  public int totalPages = 0;
  public int totalRecords = 0;

  static
  {
    if (!TBodyQmiGamePageListRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyQmiGamePageListRsp()
  {
  }

  public TBodyQmiGamePageListRsp(ArrayList paramArrayList, int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    this.gameList = paramArrayList;
    this.totalRecords = paramInt1;
    this.totalPages = paramInt2;
    this.categoryIntro = paramString1;
    this.gamePkgName = paramString2;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyQmiGamePageListRsp";
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
    localJceDisplayer.display(this.gameList, "gameList");
    localJceDisplayer.display(this.totalRecords, "totalRecords");
    localJceDisplayer.display(this.totalPages, "totalPages");
    localJceDisplayer.display(this.categoryIntro, "categoryIntro");
    localJceDisplayer.display(this.gamePkgName, "gamePkgName");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.gameList, true);
    localJceDisplayer.displaySimple(this.totalRecords, true);
    localJceDisplayer.displaySimple(this.totalPages, true);
    localJceDisplayer.displaySimple(this.categoryIntro, true);
    localJceDisplayer.displaySimple(this.gamePkgName, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TBodyQmiGamePageListRsp localTBodyQmiGamePageListRsp;
    do
    {
      return false;
      localTBodyQmiGamePageListRsp = (TBodyQmiGamePageListRsp)paramObject;
    }
    while ((!JceUtil.equals(this.gameList, localTBodyQmiGamePageListRsp.gameList)) || (!JceUtil.equals(this.totalRecords, localTBodyQmiGamePageListRsp.totalRecords)) || (!JceUtil.equals(this.totalPages, localTBodyQmiGamePageListRsp.totalPages)) || (!JceUtil.equals(this.categoryIntro, localTBodyQmiGamePageListRsp.categoryIntro)) || (!JceUtil.equals(this.gamePkgName, localTBodyQmiGamePageListRsp.gamePkgName)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyQmiGamePageListRsp";
  }

  public String getCategoryIntro()
  {
    return this.categoryIntro;
  }

  public ArrayList getGameList()
  {
    return this.gameList;
  }

  public String getGamePkgName()
  {
    return this.gamePkgName;
  }

  public int getTotalPages()
  {
    return this.totalPages;
  }

  public int getTotalRecords()
  {
    return this.totalRecords;
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
    if (cache_gameList == null)
    {
      cache_gameList = new ArrayList();
      TQmiUnitBaseInfo localTQmiUnitBaseInfo = new TQmiUnitBaseInfo();
      cache_gameList.add(localTQmiUnitBaseInfo);
    }
    this.gameList = ((ArrayList)paramJceInputStream.read(cache_gameList, 0, true));
    this.totalRecords = paramJceInputStream.read(this.totalRecords, 1, true);
    this.totalPages = paramJceInputStream.read(this.totalPages, 2, true);
    this.categoryIntro = paramJceInputStream.readString(3, false);
    this.gamePkgName = paramJceInputStream.readString(4, false);
  }

  public void setCategoryIntro(String paramString)
  {
    this.categoryIntro = paramString;
  }

  public void setGameList(ArrayList paramArrayList)
  {
    this.gameList = paramArrayList;
  }

  public void setGamePkgName(String paramString)
  {
    this.gamePkgName = paramString;
  }

  public void setTotalPages(int paramInt)
  {
    this.totalPages = paramInt;
  }

  public void setTotalRecords(int paramInt)
  {
    this.totalRecords = paramInt;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.gameList, 0);
    paramJceOutputStream.write(this.totalRecords, 1);
    paramJceOutputStream.write(this.totalPages, 2);
    if (this.categoryIntro != null)
      paramJceOutputStream.write(this.categoryIntro, 3);
    if (this.gamePkgName != null)
      paramJceOutputStream.write(this.gamePkgName, 4);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyQmiGamePageListRsp
 * JD-Core Version:    0.6.0
 */