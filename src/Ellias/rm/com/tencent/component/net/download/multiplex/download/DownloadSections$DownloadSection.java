package com.tencent.component.net.download.multiplex.download;

import java.io.DataInput;
import java.io.DataOutput;

public class DownloadSections$DownloadSection
{
  public long a;
  public long b;
  public int c = -1;
  private long d;

  protected DownloadSections$DownloadSection(int paramInt)
  {
    this.c = paramInt;
  }

  public long a()
  {
    return this.d;
  }

  public void a(long paramLong)
  {
    this.d = paramLong;
  }

  public void a(DataInput paramDataInput)
  {
    this.a = paramDataInput.readLong();
    this.d = paramDataInput.readLong();
    this.b = paramDataInput.readLong();
  }

  public void a(DataOutput paramDataOutput)
  {
    paramDataOutput.writeLong(this.a);
    paramDataOutput.writeLong(this.d);
    paramDataOutput.writeLong(this.b);
  }

  public boolean b()
  {
    return this.b >= this.d;
  }

  public boolean c()
  {
    return false;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("sectionId=").append(this.c).append(",").append("startPos=").append(this.a).append(",").append("endPos=").append(this.a).append(",").append("currentPos=").append(this.a).append(",");
    return localStringBuffer.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.DownloadSections.DownloadSection
 * JD-Core Version:    0.6.0
 */