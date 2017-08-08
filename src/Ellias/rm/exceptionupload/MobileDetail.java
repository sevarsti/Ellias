package exceptionupload;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class MobileDetail extends JceStruct
  implements Cloneable
{
  public String other = "";
  public String symbol = "";

  static
  {
    if (!MobileDetail.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public MobileDetail()
  {
  }

  public MobileDetail(String paramString1, String paramString2)
  {
    this.symbol = paramString1;
    this.other = paramString2;
  }

  public final String className()
  {
    return "exceptionupload.MobileDetail";
  }

  public final Object clone()
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

  public final void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.symbol, "symbol");
    localJceDisplayer.display(this.other, "other");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    MobileDetail localMobileDetail;
    do
    {
      return false;
      localMobileDetail = (MobileDetail)paramObject;
    }
    while ((!JceUtil.equals(this.symbol, localMobileDetail.symbol)) || (!JceUtil.equals(this.other, localMobileDetail.other)));
    return true;
  }

  public final String fullClassName()
  {
    return "exceptionupload.MobileDetail";
  }

  public final String getOther()
  {
    return this.other;
  }

  public final String getSymbol()
  {
    return this.symbol;
  }

  public final int hashCode()
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

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    this.symbol = paramJceInputStream.readString(0, true);
    this.other = paramJceInputStream.readString(1, false);
  }

  public final void setOther(String paramString)
  {
    this.other = paramString;
  }

  public final void setSymbol(String paramString)
  {
    this.symbol = paramString;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.symbol, 0);
    if (this.other != null)
      paramJceOutputStream.write(this.other, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     exceptionupload.MobileDetail
 * JD-Core Version:    0.6.0
 */