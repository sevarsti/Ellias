package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TBodyAddPostReq extends JceStruct
  implements Cloneable
{
  public int fid = 0;
  public String info = "";
  public String text = "";
  public String title = "";

  static
  {
    if (!TBodyAddPostReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyAddPostReq()
  {
  }

  public TBodyAddPostReq(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    this.title = paramString1;
    this.text = paramString2;
    this.info = paramString3;
    this.fid = paramInt;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyAddPostReq";
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
    localJceDisplayer.display(this.title, "title");
    localJceDisplayer.display(this.text, "text");
    localJceDisplayer.display(this.info, "info");
    localJceDisplayer.display(this.fid, "fid");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.title, true);
    localJceDisplayer.displaySimple(this.text, true);
    localJceDisplayer.displaySimple(this.info, true);
    localJceDisplayer.displaySimple(this.fid, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TBodyAddPostReq localTBodyAddPostReq;
    do
    {
      return false;
      localTBodyAddPostReq = (TBodyAddPostReq)paramObject;
    }
    while ((!JceUtil.equals(this.title, localTBodyAddPostReq.title)) || (!JceUtil.equals(this.text, localTBodyAddPostReq.text)) || (!JceUtil.equals(this.info, localTBodyAddPostReq.info)) || (!JceUtil.equals(this.fid, localTBodyAddPostReq.fid)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyAddPostReq";
  }

  public int getFid()
  {
    return this.fid;
  }

  public String getInfo()
  {
    return this.info;
  }

  public String getText()
  {
    return this.text;
  }

  public String getTitle()
  {
    return this.title;
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
    this.title = paramJceInputStream.readString(0, true);
    this.text = paramJceInputStream.readString(1, true);
    this.info = paramJceInputStream.readString(2, true);
    this.fid = paramJceInputStream.read(this.fid, 3, false);
  }

  public void setFid(int paramInt)
  {
    this.fid = paramInt;
  }

  public void setInfo(String paramString)
  {
    this.info = paramString;
  }

  public void setText(String paramString)
  {
    this.text = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.title, 0);
    paramJceOutputStream.write(this.text, 1);
    paramJceOutputStream.write(this.info, 2);
    paramJceOutputStream.write(this.fid, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyAddPostReq
 * JD-Core Version:    0.6.0
 */