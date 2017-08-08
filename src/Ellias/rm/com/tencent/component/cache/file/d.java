package com.tencent.component.cache.file;

import java.io.File;

class d
{
  public final String a;
  public final String b;
  public final long c;
  public final boolean d;

  public d(String paramString1, String paramString2)
  {
    File localFile = new File(paramString1, paramString2);
    this.a = localFile.getPath();
    this.b = paramString2;
    this.c = localFile.lastModified();
    this.d = true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.file.d
 * JD-Core Version:    0.6.0
 */