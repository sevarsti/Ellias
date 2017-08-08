package com.tencent.beacon.e;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Set;

public final class d
{
  private static HashMap<String, byte[]> e = null;
  private HashMap<String, byte[]> a = new HashMap();
  private a b = new a();
  private String c = "GBK";
  private e d = new e();

  public final void a(int paramInt)
  {
    this.d.b = 1;
  }

  public final void a(String paramString)
  {
    this.d.d = paramString;
  }

  public final <T> void a(String paramString, T paramT)
  {
    if (paramString == null)
      throw new IllegalArgumentException("put key can not is null");
    if (paramT == null)
      throw new IllegalArgumentException("put value can not is null");
    if ((paramT instanceof Set))
      throw new IllegalArgumentException("can not support Set");
    b localb = new b();
    localb.a(this.c);
    localb.a(paramT, 0);
    ByteBuffer localByteBuffer = localb.a();
    byte[] arrayOfByte = new byte[localByteBuffer.position()];
    System.arraycopy(localByteBuffer.array(), 0, arrayOfByte, 0, arrayOfByte.length);
    this.a.put(paramString, arrayOfByte);
  }

  public final void a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length < 4)
      throw new IllegalArgumentException("decode package must include size head");
    try
    {
      a locala1 = new a(paramArrayOfByte, 4);
      locala1.a(this.c);
      this.d.a(locala1);
      a locala2 = new a(this.d.e);
      locala2.a(this.c);
      if (e == null)
      {
        HashMap localHashMap = new HashMap();
        e = localHashMap;
        localHashMap.put("", new byte[0]);
      }
      this.a = locala2.a(e, 0, false);
      return;
    }
    catch (Exception localException)
    {
    }
    throw new RuntimeException(localException);
  }

  public final byte[] a()
  {
    b localb1 = new b(0);
    localb1.a(this.c);
    localb1.a(this.a, 0);
    this.d.a = 3;
    e locale = this.d;
    ByteBuffer localByteBuffer1 = localb1.a();
    byte[] arrayOfByte1 = new byte[localByteBuffer1.position()];
    System.arraycopy(localByteBuffer1.array(), 0, arrayOfByte1, 0, arrayOfByte1.length);
    locale.e = arrayOfByte1;
    b localb2 = new b(0);
    localb2.a(this.c);
    this.d.a(localb2);
    ByteBuffer localByteBuffer2 = localb2.a();
    byte[] arrayOfByte2 = new byte[localByteBuffer2.position()];
    System.arraycopy(localByteBuffer2.array(), 0, arrayOfByte2, 0, arrayOfByte2.length);
    int i = arrayOfByte2.length;
    ByteBuffer localByteBuffer3 = ByteBuffer.allocate(i + 4);
    localByteBuffer3.putInt(i + 4).put(arrayOfByte2).flip();
    return localByteBuffer3.array();
  }

  public final <T> T b(String paramString, T paramT)
    throws Exception
  {
    if (!this.a.containsKey(paramString))
      return null;
    byte[] arrayOfByte = (byte[])this.a.get(paramString);
    try
    {
      this.b.a(arrayOfByte);
      this.b.a(this.c);
      Object localObject = this.b.a(paramT, 0, true);
      return localObject;
    }
    catch (Exception localException)
    {
    }
    throw new Exception(localException);
  }

  public final void b(String paramString)
  {
    this.d.c = paramString;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.e.d
 * JD-Core Version:    0.6.0
 */