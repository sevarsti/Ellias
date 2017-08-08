package com.tencent.component.utils;

import java.util.Collection;
import java.util.Map;

public class MiscUtil
{
  public static boolean a(String paramString)
  {
    return (paramString != null) && (!paramString.equals(""));
  }

  public static boolean a(Collection paramCollection)
  {
    return (paramCollection != null) && (!paramCollection.isEmpty());
  }

  public static boolean a(Map paramMap)
  {
    return (paramMap != null) && (!paramMap.isEmpty());
  }

  public static boolean a(byte[] paramArrayOfByte)
  {
    return (paramArrayOfByte != null) && (paramArrayOfByte.length != 0);
  }

  public static boolean a(Object[] paramArrayOfObject)
  {
    return (paramArrayOfObject != null) && (paramArrayOfObject.length != 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.MiscUtil
 * JD-Core Version:    0.6.0
 */