package com.tencent.component.cache.file;

public enum FileStorageHandler$Mode
{
  static
  {
    BOTH = new Mode("BOTH", 2);
    Mode[] arrayOfMode = new Mode[3];
    arrayOfMode[0] = EXTERNAL;
    arrayOfMode[1] = INTERNAL;
    arrayOfMode[2] = BOTH;
    $VALUES = arrayOfMode;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.file.FileStorageHandler.Mode
 * JD-Core Version:    0.6.0
 */