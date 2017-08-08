package com.tencent.stat.a;

import java.util.Arrays;
import java.util.Properties;

public class c
{
  String a;
  String[] b;
  Properties c = null;

  public c()
  {
  }

  public c(String paramString, String[] paramArrayOfString, Properties paramProperties)
  {
    this.a = paramString;
    this.b = paramArrayOfString;
    this.c = paramProperties;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    while (true)
    {
      return true;
      if (!(paramObject instanceof c))
        break;
      c localc = (c)paramObject;
      if ((this.a.equals(localc.a)) && (Arrays.equals(this.b, localc.b)));
      for (int i = 1; ; i = 0)
      {
        if (this.c == null)
          break label81;
        if ((i != 0) && (this.c.equals(localc.c)))
          break;
        return false;
      }
      label81: if ((i == 0) || (localc.c != null))
        return false;
    }
    return false;
  }

  public int hashCode()
  {
    String str = this.a;
    int i = 0;
    if (str != null)
      i = this.a.hashCode();
    if (this.b != null)
      i ^= Arrays.hashCode(this.b);
    if (this.c != null)
      i ^= this.c.hashCode();
    return i;
  }

  public String toString()
  {
    String str1 = this.a;
    String str2 = "";
    if (this.b != null)
    {
      String str3 = this.b[0];
      for (int i = 1; i < this.b.length; i++)
        str3 = str3 + "," + this.b[i];
      str2 = "[" + str3 + "]";
    }
    if (this.c != null)
      str2 = str2 + this.c.toString();
    return str1 + str2;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.a.c
 * JD-Core Version:    0.6.0
 */