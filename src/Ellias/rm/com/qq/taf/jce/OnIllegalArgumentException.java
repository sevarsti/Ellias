package com.qq.taf.jce;

import java.nio.ByteBuffer;

public abstract interface OnIllegalArgumentException
{
  public abstract void onException(IllegalArgumentException paramIllegalArgumentException, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.qq.taf.jce.OnIllegalArgumentException
 * JD-Core Version:    0.6.0
 */