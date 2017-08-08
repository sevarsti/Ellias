package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TQmiPlugInVerInfo extends JceStruct
  implements Cloneable
{
  public int pluginVer = 0;
  public String runPkgName = "";

  static
  {
    if (!TQmiPlugInVerInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TQmiPlugInVerInfo()
  {
  }

  public TQmiPlugInVerInfo(String paramString, int paramInt)
  {
    this.runPkgName = paramString;
    this.pluginVer = paramInt;
  }

  public String className()
  {
    return "CobraHallQmiProto.TQmiPlugInVerInfo";
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
    localJceDisplayer.display(this.runPkgName, "runPkgName");
    localJceDisplayer.display(this.pluginVer, "pluginVer");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.runPkgName, true);
    localJceDisplayer.displaySimple(this.pluginVer, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TQmiPlugInVerInfo localTQmiPlugInVerInfo;
    do
    {
      return false;
      localTQmiPlugInVerInfo = (TQmiPlugInVerInfo)paramObject;
    }
    while ((!JceUtil.equals(this.runPkgName, localTQmiPlugInVerInfo.runPkgName)) || (!JceUtil.equals(this.pluginVer, localTQmiPlugInVerInfo.pluginVer)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TQmiPlugInVerInfo";
  }

  public int getPluginVer()
  {
    return this.pluginVer;
  }

  public String getRunPkgName()
  {
    return this.runPkgName;
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
    this.runPkgName = paramJceInputStream.readString(0, true);
    this.pluginVer = paramJceInputStream.read(this.pluginVer, 1, true);
  }

  public void setPluginVer(int paramInt)
  {
    this.pluginVer = paramInt;
  }

  public void setRunPkgName(String paramString)
  {
    this.runPkgName = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.runPkgName, 0);
    paramJceOutputStream.write(this.pluginVer, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TQmiPlugInVerInfo
 * JD-Core Version:    0.6.0
 */