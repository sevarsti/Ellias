package com.tencent.mm.sdk.b;

import java.util.TimeZone;

public final class c
{
  private static final TimeZone GMT;
  private static final long[] q = { 300L, 200L, 300L, 200L };
  private static final char[] r;
  private static final String[] s;

  static
  {
    GMT = TimeZone.getTimeZone("GMT");
    r = new char[] { 60, 62, 34, 39, 38 };
    s = new String[] { "&lt;", "&gt;", "&quot;", "&apos;", "&amp;" };
  }

  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() <= 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mm.sdk.b.c
 * JD-Core Version:    0.6.0
 */