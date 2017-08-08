package exceptionupload;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class MergedAssertInfo extends JceStruct
  implements Cloneable
{
  static ArrayList<AssertDetailInfo> cache_asserts;
  static ArrayList<Attachment> cache_attachments;
  public String assertName = "";
  public ArrayList<AssertDetailInfo> asserts = null;
  public ArrayList<Attachment> attachments = null;
  public String callStack = "";
  public String processName = "";

  static
  {
    if (!MergedAssertInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public MergedAssertInfo()
  {
  }

  public MergedAssertInfo(String paramString1, ArrayList<AssertDetailInfo> paramArrayList, String paramString2, String paramString3, ArrayList<Attachment> paramArrayList1)
  {
    this.assertName = paramString1;
    this.asserts = paramArrayList;
    this.callStack = paramString2;
    this.processName = paramString3;
    this.attachments = paramArrayList1;
  }

  public final String className()
  {
    return "exceptionupload.MergedAssertInfo";
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
    localJceDisplayer.display(this.assertName, "assertName");
    localJceDisplayer.display(this.asserts, "asserts");
    localJceDisplayer.display(this.callStack, "callStack");
    localJceDisplayer.display(this.processName, "processName");
    localJceDisplayer.display(this.attachments, "attachments");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    MergedAssertInfo localMergedAssertInfo;
    do
    {
      return false;
      localMergedAssertInfo = (MergedAssertInfo)paramObject;
    }
    while ((!JceUtil.equals(this.assertName, localMergedAssertInfo.assertName)) || (!JceUtil.equals(this.asserts, localMergedAssertInfo.asserts)) || (!JceUtil.equals(this.callStack, localMergedAssertInfo.callStack)) || (!JceUtil.equals(this.processName, localMergedAssertInfo.processName)) || (!JceUtil.equals(this.attachments, localMergedAssertInfo.attachments)));
    return true;
  }

  public final String fullClassName()
  {
    return "exceptionupload.MergedAssertInfo";
  }

  public final String getAssertName()
  {
    return this.assertName;
  }

  public final ArrayList<AssertDetailInfo> getAsserts()
  {
    return this.asserts;
  }

  public final ArrayList<Attachment> getAttachments()
  {
    return this.attachments;
  }

  public final String getCallStack()
  {
    return this.callStack;
  }

  public final String getProcessName()
  {
    return this.processName;
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
    this.assertName = paramJceInputStream.readString(0, true);
    if (cache_asserts == null)
    {
      cache_asserts = new ArrayList();
      AssertDetailInfo localAssertDetailInfo = new AssertDetailInfo();
      cache_asserts.add(localAssertDetailInfo);
    }
    this.asserts = ((ArrayList)paramJceInputStream.read(cache_asserts, 1, true));
    this.callStack = paramJceInputStream.readString(2, false);
    this.processName = paramJceInputStream.readString(3, false);
    if (cache_attachments == null)
    {
      cache_attachments = new ArrayList();
      Attachment localAttachment = new Attachment();
      cache_attachments.add(localAttachment);
    }
    this.attachments = ((ArrayList)paramJceInputStream.read(cache_attachments, 4, false));
  }

  public final void setAssertName(String paramString)
  {
    this.assertName = paramString;
  }

  public final void setAsserts(ArrayList<AssertDetailInfo> paramArrayList)
  {
    this.asserts = paramArrayList;
  }

  public final void setAttachments(ArrayList<Attachment> paramArrayList)
  {
    this.attachments = paramArrayList;
  }

  public final void setCallStack(String paramString)
  {
    this.callStack = paramString;
  }

  public final void setProcessName(String paramString)
  {
    this.processName = paramString;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.assertName, 0);
    paramJceOutputStream.write(this.asserts, 1);
    if (this.callStack != null)
      paramJceOutputStream.write(this.callStack, 2);
    if (this.processName != null)
      paramJceOutputStream.write(this.processName, 3);
    if (this.attachments != null)
      paramJceOutputStream.write(this.attachments, 4);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     exceptionupload.MergedAssertInfo
 * JD-Core Version:    0.6.0
 */