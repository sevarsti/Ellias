package com.tencent.component.utils;

import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

class a
  implements Enumeration
{
  private final JarFile a;
  private final String[] b;
  private int c = 0;

  public a(JarFile paramJarFile, String[] paramArrayOfString)
  {
    this.a = paramJarFile;
    this.b = paramArrayOfString;
  }

  public JarEntry a()
  {
    JarFile localJarFile = this.a;
    String[] arrayOfString = this.b;
    int i = this.c;
    this.c = (i + 1);
    return localJarFile.getJarEntry(arrayOfString[i]);
  }

  public boolean hasMoreElements()
  {
    return this.c < this.b.length;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.a
 * JD-Core Version:    0.6.0
 */