package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TGameJoyDLInfo extends JceStruct
  implements Cloneable
{
  public String downloadUrl = "";
  public int fileSize = 0;
  public String hallPkgName = "";
  public int stdVer = 0;
  public String versionCode = "";

  static
  {
    if (!TGameJoyDLInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TGameJoyDLInfo()
  {
  }

  public TGameJoyDLInfo(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3)
  {
    this.hallPkgName = paramString1;
    this.downloadUrl = paramString2;
    this.fileSize = paramInt1;
    this.stdVer = paramInt2;
    this.versionCode = paramString3;
  }

  public String className()
  {
    return "CobraHallQmiProto.TGameJoyDLInfo";
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
    localJceDisplayer.display(this.hallPkgName, "hallPkgName");
    localJceDisplayer.display(this.downloadUrl, "downloadUrl");
    localJceDisplayer.display(this.fileSize, "fileSize");
    localJceDisplayer.display(this.stdVer, "stdVer");
    localJceDisplayer.display(this.versionCode, "versionCode");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.hallPkgName, true);
    localJceDisplayer.displaySimple(this.downloadUrl, true);
    localJceDisplayer.displaySimple(this.fileSize, true);
    localJceDisplayer.displaySimple(this.stdVer, true);
    localJceDisplayer.displaySimple(this.versionCode, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TGameJoyDLInfo localTGameJoyDLInfo;
    do
    {
      return false;
      localTGameJoyDLInfo = (TGameJoyDLInfo)paramObject;
    }
    while ((!JceUtil.equals(this.hallPkgName, localTGameJoyDLInfo.hallPkgName)) || (!JceUtil.equals(this.downloadUrl, localTGameJoyDLInfo.downloadUrl)) || (!JceUtil.equals(this.fileSize, localTGameJoyDLInfo.fileSize)) || (!JceUtil.equals(this.stdVer, localTGameJoyDLInfo.stdVer)) || (!JceUtil.equals(this.versionCode, localTGameJoyDLInfo.versionCode)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TGameJoyDLInfo";
  }

  public String getDownloadUrl()
  {
    return this.downloadUrl;
  }

  public int getFileSize()
  {
    return this.fileSize;
  }

  public String getHallPkgName()
  {
    return this.hallPkgName;
  }

  public int getStdVer()
  {
    return this.stdVer;
  }

  public String getVersionCode()
  {
    return this.versionCode;
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
    this.hallPkgName = paramJceInputStream.readString(0, true);
    this.downloadUrl = paramJceInputStream.readString(1, true);
    this.fileSize = paramJceInputStream.read(this.fileSize, 2, true);
    this.stdVer = paramJceInputStream.read(this.stdVer, 3, true);
    this.versionCode = paramJceInputStream.readString(4, true);
  }

  public void setDownloadUrl(String paramString)
  {
    this.downloadUrl = paramString;
  }

  public void setFileSize(int paramInt)
  {
    this.fileSize = paramInt;
  }

  public void setHallPkgName(String paramString)
  {
    this.hallPkgName = paramString;
  }

  public void setStdVer(int paramInt)
  {
    this.stdVer = paramInt;
  }

  public void setVersionCode(String paramString)
  {
    this.versionCode = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.hallPkgName, 0);
    paramJceOutputStream.write(this.downloadUrl, 1);
    paramJceOutputStream.write(this.fileSize, 2);
    paramJceOutputStream.write(this.stdVer, 3);
    paramJceOutputStream.write(this.versionCode, 4);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TGameJoyDLInfo
 * JD-Core Version:    0.6.0
 */