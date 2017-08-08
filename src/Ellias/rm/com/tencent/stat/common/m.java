package com.tencent.stat.common;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

class m
  implements FileFilter
{
  public boolean accept(File paramFile)
  {
    return Pattern.matches("cpu[0-9]", paramFile.getName());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.common.m
 * JD-Core Version:    0.6.0
 */