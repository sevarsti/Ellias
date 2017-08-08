package exceptionupload;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class Attachment extends JceStruct
  implements Cloneable
{
  static byte[] cache_data;
  public byte[] data = null;
  public String fileName = "";
  public byte type = 0;

  static
  {
    if (!Attachment.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public Attachment()
  {
  }

  public Attachment(byte paramByte, String paramString, byte[] paramArrayOfByte)
  {
    this.type = paramByte;
    this.fileName = paramString;
    this.data = paramArrayOfByte;
  }

  public final String className()
  {
    return "exceptionupload.Attachment";
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
    localJceDisplayer.display(this.type, "type");
    localJceDisplayer.display(this.fileName, "fileName");
    localJceDisplayer.display(this.data, "data");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    Attachment localAttachment;
    do
    {
      return false;
      localAttachment = (Attachment)paramObject;
    }
    while ((!JceUtil.equals(this.type, localAttachment.type)) || (!JceUtil.equals(this.fileName, localAttachment.fileName)) || (!JceUtil.equals(this.data, localAttachment.data)));
    return true;
  }

  public final String fullClassName()
  {
    return "exceptionupload.Attachment";
  }

  public final byte[] getData()
  {
    return this.data;
  }

  public final String getFileName()
  {
    return this.fileName;
  }

  public final byte getType()
  {
    return this.type;
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
    this.type = paramJceInputStream.read(this.type, 0, true);
    this.fileName = paramJceInputStream.readString(1, false);
    if (cache_data == null)
    {
      byte[] arrayOfByte = new byte[1];
      cache_data = arrayOfByte;
      arrayOfByte[0] = 0;
    }
    this.data = paramJceInputStream.read(cache_data, 2, true);
  }

  public final void setData(byte[] paramArrayOfByte)
  {
    this.data = paramArrayOfByte;
  }

  public final void setFileName(String paramString)
  {
    this.fileName = paramString;
  }

  public final void setType(byte paramByte)
  {
    this.type = paramByte;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.type, 0);
    if (this.fileName != null)
      paramJceOutputStream.write(this.fileName, 1);
    paramJceOutputStream.write(this.data, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     exceptionupload.Attachment
 * JD-Core Version:    0.6.0
 */