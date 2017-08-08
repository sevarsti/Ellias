package com.tencent.tp;

public class TssSdk
{
  static
  {
    System.loadLibrary("tersafe");
  }

  public static native void forceExit();

  public static native void loadMalwareScanInfo(TssSdkMalwareInfo paramTssSdkMalwareInfo);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tp.TssSdk
 * JD-Core Version:    0.6.0
 */