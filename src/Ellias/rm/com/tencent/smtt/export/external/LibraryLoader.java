package com.tencent.smtt.export.external;

import android.content.Context;
import android.os.Build.VERSION;
import java.io.File;
import java.util.ArrayList;

public class LibraryLoader
{
  private static String[] sLibrarySearchPaths = null;

  public static String[] getLibrarySearchPaths(Context paramContext)
  {
    if (sLibrarySearchPaths != null)
      return sLibrarySearchPaths;
    if (paramContext == null)
      return new String[] { "/system/lib" };
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(getNativeLibraryDir(paramContext));
    localArrayList.add("/system/lib");
    String[] arrayOfString = new String[localArrayList.size()];
    localArrayList.toArray(arrayOfString);
    sLibrarySearchPaths = arrayOfString;
    return sLibrarySearchPaths;
  }

  public static String getNativeLibraryDir(Context paramContext)
  {
    int i = getSdkVersion();
    if (i >= 9)
      return X5Adapter_23.getNativeLibraryDirGB(paramContext);
    if (i >= 4)
      return X5Adapter_16.getNativeLibraryDirDonut(paramContext);
    return "/data/data/" + paramContext.getPackageName() + "/lib";
  }

  public static int getSdkVersion()
  {
    return Integer.parseInt(Build.VERSION.SDK);
  }

  public static void loadLibrary(Context paramContext, String paramString)
    throws UnsatisfiedLinkError
  {
    String[] arrayOfString = getLibrarySearchPaths(paramContext);
    String str1 = System.mapLibraryName(paramString);
    int i = arrayOfString.length;
    int j = 0;
    while (j < i)
    {
      String str2 = arrayOfString[j];
      String str3 = str2 + "/" + str1;
      if (!new File(str3).exists())
      {
        j++;
        continue;
      }
      System.load(str3);
      return;
    }
    System.loadLibrary(paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.export.external.LibraryLoader
 * JD-Core Version:    0.6.0
 */