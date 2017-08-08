package com.tencent.android.tpush.data;

import com.tencent.android.tpush.service.c.c;

public class a
{
  public String a = "";
  public int b;
  public int c;

  public boolean a(a parama)
  {
    boolean bool1 = c.a(this.a);
    int i = 0;
    boolean bool3;
    if (!bool1)
    {
      i = 0;
      if (parama != null)
      {
        boolean bool2 = c.a(parama.a);
        i = 0;
        if (!bool2)
        {
          bool3 = this.a.equals(parama.a);
          if (!bool3)
            break label87;
          int j = this.b;
          int k = parama.b;
          boolean bool4 = false;
          if (j == k)
            bool4 = true;
          i = bool4 & bool3;
        }
      }
    }
    return i;
    label87: return bool3;
  }

  public boolean a(String paramString)
  {
    if ((!c.a(this.a)) && (!c.a(paramString)))
    {
      String[] arrayOfString = paramString.split(":");
      if ((arrayOfString != null) && (arrayOfString.length > 0))
      {
        boolean bool = this.a.equals(paramString);
        if ((bool) && (arrayOfString.length == 2))
          bool &= String.valueOf(this.b).equals(arrayOfString[1]);
        return bool;
      }
    }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.data.a
 * JD-Core Version:    0.6.0
 */