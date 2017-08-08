package com.tencent.msdk.tools;

import android.content.res.Resources;

public class ResID
{
  public static int loadIdentifierResource(Resources paramResources, String paramString1, String paramString2, String paramString3)
  {
    int i = paramResources.getIdentifier(paramString1, paramString2, paramString3);
    if (i == 0)
    {
      new Exception(String.format("Resource %s(type=%s, pkg=%s) is not found", new Object[] { paramString1, paramString2, paramString3 })).printStackTrace();
      System.exit(-1);
    }
    return i;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.ResID
 * JD-Core Version:    0.6.0
 */