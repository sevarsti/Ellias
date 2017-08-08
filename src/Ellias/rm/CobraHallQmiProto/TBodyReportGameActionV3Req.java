package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TBodyReportGameActionV3Req extends JceStruct
  implements Cloneable
{
  public int gameActionType = 0;
  public String gamePackageName = "";

  static
  {
    if (!TBodyReportGameActionV3Req.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyReportGameActionV3Req()
  {
  }

  public TBodyReportGameActionV3Req(String paramString, int paramInt)
  {
    this.gamePackageName = paramString;
    this.gameActionType = paramInt;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyReportGameActionV3Req";
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
    localJceDisplayer.display(this.gamePackageName, "gamePackageName");
    localJceDisplayer.display(this.gameActionType, "gameActionType");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.gamePackageName, true);
    localJceDisplayer.displaySimple(this.gameActionType, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TBodyReportGameActionV3Req localTBodyReportGameActionV3Req;
    do
    {
      return false;
      localTBodyReportGameActionV3Req = (TBodyReportGameActionV3Req)paramObject;
    }
    while ((!JceUtil.equals(this.gamePackageName, localTBodyReportGameActionV3Req.gamePackageName)) || (!JceUtil.equals(this.gameActionType, localTBodyReportGameActionV3Req.gameActionType)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyReportGameActionV3Req";
  }

  public int getGameActionType()
  {
    return this.gameActionType;
  }

  public String getGamePackageName()
  {
    return this.gamePackageName;
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
    this.gamePackageName = paramJceInputStream.readString(0, true);
    this.gameActionType = paramJceInputStream.read(this.gameActionType, 1, true);
  }

  public void setGameActionType(int paramInt)
  {
    this.gameActionType = paramInt;
  }

  public void setGamePackageName(String paramString)
  {
    this.gamePackageName = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.gamePackageName, 0);
    paramJceOutputStream.write(this.gameActionType, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyReportGameActionV3Req
 * JD-Core Version:    0.6.0
 */