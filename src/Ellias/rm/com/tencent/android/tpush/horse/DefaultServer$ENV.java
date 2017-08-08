package com.tencent.android.tpush.horse;

public enum DefaultServer$ENV
{
  static
  {
    DEBUG = new ENV("DEBUG", 1);
    ENV[] arrayOfENV = new ENV[2];
    arrayOfENV[0] = RELEASE;
    arrayOfENV[1] = DEBUG;
    $VALUES = arrayOfENV;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.DefaultServer.ENV
 * JD-Core Version:    0.6.0
 */