package com.tencent.stat.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class d
{
  public static File a(String paramString)
  {
    File localFile = new File(paramString);
    if (localFile.exists())
      return localFile;
    if (!localFile.getParentFile().exists())
      a(localFile.getParentFile().getAbsolutePath());
    localFile.mkdir();
    return localFile;
  }

  public static List<String> a(File paramFile)
  {
    BufferedReader localBufferedReader = new BufferedReader(new FileReader(paramFile));
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      String str = localBufferedReader.readLine();
      if (str == null)
        return localArrayList;
      localArrayList.add(str.trim());
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.common.d
 * JD-Core Version:    0.6.0
 */