package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TBodyQmiGamePageListReq extends JceStruct
  implements Cloneable
{
  static ArrayList cache_plugInVerInfos;
  public int categoryId = 0;
  public String gamePkgName = "";
  public int gameVersion = 0;
  public int pageNo = 0;
  public int pageSize = 0;
  public ArrayList plugInVerInfos = null;

  static
  {
    if (!TBodyQmiGamePageListReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyQmiGamePageListReq()
  {
  }

  public TBodyQmiGamePageListReq(int paramInt1, int paramInt2, int paramInt3, String paramString, int paramInt4, ArrayList paramArrayList)
  {
    this.categoryId = paramInt1;
    this.pageNo = paramInt2;
    this.pageSize = paramInt3;
    this.gamePkgName = paramString;
    this.gameVersion = paramInt4;
    this.plugInVerInfos = paramArrayList;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyQmiGamePageListReq";
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
    localJceDisplayer.display(this.categoryId, "categoryId");
    localJceDisplayer.display(this.pageNo, "pageNo");
    localJceDisplayer.display(this.pageSize, "pageSize");
    localJceDisplayer.display(this.gamePkgName, "gamePkgName");
    localJceDisplayer.display(this.gameVersion, "gameVersion");
    localJceDisplayer.display(this.plugInVerInfos, "plugInVerInfos");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.categoryId, true);
    localJceDisplayer.displaySimple(this.pageNo, true);
    localJceDisplayer.displaySimple(this.pageSize, true);
    localJceDisplayer.displaySimple(this.gamePkgName, true);
    localJceDisplayer.displaySimple(this.gameVersion, true);
    localJceDisplayer.displaySimple(this.plugInVerInfos, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TBodyQmiGamePageListReq localTBodyQmiGamePageListReq;
    do
    {
      return false;
      localTBodyQmiGamePageListReq = (TBodyQmiGamePageListReq)paramObject;
    }
    while ((!JceUtil.equals(this.categoryId, localTBodyQmiGamePageListReq.categoryId)) || (!JceUtil.equals(this.pageNo, localTBodyQmiGamePageListReq.pageNo)) || (!JceUtil.equals(this.pageSize, localTBodyQmiGamePageListReq.pageSize)) || (!JceUtil.equals(this.gamePkgName, localTBodyQmiGamePageListReq.gamePkgName)) || (!JceUtil.equals(this.gameVersion, localTBodyQmiGamePageListReq.gameVersion)) || (!JceUtil.equals(this.plugInVerInfos, localTBodyQmiGamePageListReq.plugInVerInfos)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyQmiGamePageListReq";
  }

  public int getCategoryId()
  {
    return this.categoryId;
  }

  public String getGamePkgName()
  {
    return this.gamePkgName;
  }

  public int getGameVersion()
  {
    return this.gameVersion;
  }

  public int getPageNo()
  {
    return this.pageNo;
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public ArrayList getPlugInVerInfos()
  {
    return this.plugInVerInfos;
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
    this.categoryId = paramJceInputStream.read(this.categoryId, 0, true);
    this.pageNo = paramJceInputStream.read(this.pageNo, 1, true);
    this.pageSize = paramJceInputStream.read(this.pageSize, 2, true);
    this.gamePkgName = paramJceInputStream.readString(3, false);
    this.gameVersion = paramJceInputStream.read(this.gameVersion, 4, false);
    if (cache_plugInVerInfos == null)
    {
      cache_plugInVerInfos = new ArrayList();
      TQmiPlugInVerInfo localTQmiPlugInVerInfo = new TQmiPlugInVerInfo();
      cache_plugInVerInfos.add(localTQmiPlugInVerInfo);
    }
    this.plugInVerInfos = ((ArrayList)paramJceInputStream.read(cache_plugInVerInfos, 5, false));
  }

  public void setCategoryId(int paramInt)
  {
    this.categoryId = paramInt;
  }

  public void setGamePkgName(String paramString)
  {
    this.gamePkgName = paramString;
  }

  public void setGameVersion(int paramInt)
  {
    this.gameVersion = paramInt;
  }

  public void setPageNo(int paramInt)
  {
    this.pageNo = paramInt;
  }

  public void setPageSize(int paramInt)
  {
    this.pageSize = paramInt;
  }

  public void setPlugInVerInfos(ArrayList paramArrayList)
  {
    this.plugInVerInfos = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.categoryId, 0);
    paramJceOutputStream.write(this.pageNo, 1);
    paramJceOutputStream.write(this.pageSize, 2);
    if (this.gamePkgName != null)
      paramJceOutputStream.write(this.gamePkgName, 3);
    paramJceOutputStream.write(this.gameVersion, 4);
    if (this.plugInVerInfos != null)
      paramJceOutputStream.write(this.plugInVerInfos, 5);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyQmiGamePageListReq
 * JD-Core Version:    0.6.0
 */