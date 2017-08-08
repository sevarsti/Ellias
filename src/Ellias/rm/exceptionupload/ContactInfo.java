package exceptionupload;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class ContactInfo extends JceStruct
  implements Cloneable
{
  public String all = "";
  public String comment = "";
  public String email = "";
  public String other = "";
  public String qua = "";
  public String tel = "";

  static
  {
    if (!ContactInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public ContactInfo()
  {
  }

  public ContactInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.all = paramString1;
    this.tel = paramString2;
    this.qua = paramString3;
    this.email = paramString4;
    this.comment = paramString5;
    this.other = paramString6;
  }

  public final String className()
  {
    return "exceptionupload.ContactInfo";
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
    localJceDisplayer.display(this.all, "all");
    localJceDisplayer.display(this.tel, "tel");
    localJceDisplayer.display(this.qua, "qua");
    localJceDisplayer.display(this.email, "email");
    localJceDisplayer.display(this.comment, "comment");
    localJceDisplayer.display(this.other, "other");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    ContactInfo localContactInfo;
    do
    {
      return false;
      localContactInfo = (ContactInfo)paramObject;
    }
    while ((!JceUtil.equals(this.all, localContactInfo.all)) || (!JceUtil.equals(this.tel, localContactInfo.tel)) || (!JceUtil.equals(this.qua, localContactInfo.qua)) || (!JceUtil.equals(this.email, localContactInfo.email)) || (!JceUtil.equals(this.comment, localContactInfo.comment)) || (!JceUtil.equals(this.other, localContactInfo.other)));
    return true;
  }

  public final String fullClassName()
  {
    return "exceptionupload.ContactInfo";
  }

  public final String getAll()
  {
    return this.all;
  }

  public final String getComment()
  {
    return this.comment;
  }

  public final String getEmail()
  {
    return this.email;
  }

  public final String getOther()
  {
    return this.other;
  }

  public final String getQua()
  {
    return this.qua;
  }

  public final String getTel()
  {
    return this.tel;
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
    this.all = paramJceInputStream.readString(0, true);
    this.tel = paramJceInputStream.readString(1, true);
    this.qua = paramJceInputStream.readString(2, true);
    this.email = paramJceInputStream.readString(3, true);
    this.comment = paramJceInputStream.readString(4, true);
    this.other = paramJceInputStream.readString(5, false);
  }

  public final void setAll(String paramString)
  {
    this.all = paramString;
  }

  public final void setComment(String paramString)
  {
    this.comment = paramString;
  }

  public final void setEmail(String paramString)
  {
    this.email = paramString;
  }

  public final void setOther(String paramString)
  {
    this.other = paramString;
  }

  public final void setQua(String paramString)
  {
    this.qua = paramString;
  }

  public final void setTel(String paramString)
  {
    this.tel = paramString;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.all, 0);
    paramJceOutputStream.write(this.tel, 1);
    paramJceOutputStream.write(this.qua, 2);
    paramJceOutputStream.write(this.email, 3);
    paramJceOutputStream.write(this.comment, 4);
    if (this.other != null)
      paramJceOutputStream.write(this.other, 5);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     exceptionupload.ContactInfo
 * JD-Core Version:    0.6.0
 */