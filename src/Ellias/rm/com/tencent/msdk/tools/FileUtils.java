package com.tencent.msdk.tools;

import android.os.Environment;
import java.io.File;

public class FileUtils
{
  private static final String DIR_EXT_MAIN = "MSDK";
  private static final String FILE_LOG = "msdk.log";

  public static File getExternalRootDir()
  {
    File localFile = new File(Environment.getExternalStorageDirectory(), "MSDK");
    if (!localFile.exists())
      localFile.mkdirs();
    return localFile;
  }

  public static File getLogFile()
  {
    return new File(getExternalRootDir(), "msdk.log");
  }

  public static boolean hasExternalStorage()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.FileUtils
 * JD-Core Version:    0.6.0
 */