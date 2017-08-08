package exceptionupload;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class ExceptionUploadPackage extends JceStruct
  implements Cloneable
{
  static ArrayList<ExceptionUpload> cache_list;
  public ArrayList<ExceptionUpload> list = null;

  static
  {
    if (!ExceptionUploadPackage.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public ExceptionUploadPackage()
  {
  }

  public ExceptionUploadPackage(ArrayList<ExceptionUpload> paramArrayList)
  {
    this.list = paramArrayList;
  }

  public final String className()
  {
    return "exceptionupload.ExceptionUploadPackage";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.list, "list");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    ExceptionUploadPackage localExceptionUploadPackage = (ExceptionUploadPackage)paramObject;
    return JceUtil.equals(this.list, localExceptionUploadPackage.list);
  }

  public final String fullClassName()
  {
    return "exceptionupload.ExceptionUploadPackage";
  }

  public final ArrayList<ExceptionUpload> getList()
  {
    return this.list;
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
    if (cache_list == null)
    {
      cache_list = new ArrayList();
      ExceptionUpload localExceptionUpload = new ExceptionUpload();
      cache_list.add(localExceptionUpload);
    }
    this.list = ((ArrayList)paramJceInputStream.read(cache_list, 0, true));
  }

  public final void setList(ArrayList<ExceptionUpload> paramArrayList)
  {
    this.list = paramArrayList;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.list, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     exceptionupload.ExceptionUploadPackage
 * JD-Core Version:    0.6.0
 */