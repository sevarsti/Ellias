package com.tencent.component.utils;

import java.io.InputStream;
import java.util.zip.ZipEntry;

final class c
  implements d
{
  c(String paramString)
  {
  }

  public boolean a(InputStream paramInputStream, ZipEntry paramZipEntry, String paramString)
  {
    return NativeLibraryHelper.a(paramInputStream, paramZipEntry, this.a, paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.c
 * JD-Core Version:    0.6.0
 */