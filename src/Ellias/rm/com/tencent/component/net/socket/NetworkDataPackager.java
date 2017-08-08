package com.tencent.component.net.socket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class NetworkDataPackager
  implements ISocketPackageDataListener
{
  private static final int c = 4;
  private static final int d = 51200;
  ByteBuffer a = ByteBuffer.allocateDirect(51200);
  int b = -1;

  public ArrayList a(byte[] paramArrayOfByte, int paramInt)
  {
    ArrayList localArrayList = new ArrayList(2);
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0) || (paramInt <= 0))
      return localArrayList;
    if (paramInt + this.a.position() < this.a.limit())
    {
      this.a.put(paramArrayOfByte, 0, paramInt);
      this.a.flip();
    }
    while (true)
    {
      if (this.a.limit() <= this.a.position())
        break label300;
      if ((this.b == -1) && (this.a.limit() - this.a.position() >= 4))
        this.b = this.a.getInt();
      if ((this.b > 0) && (this.a.limit() - this.a.position() >= -4 + this.b))
      {
        byte[] arrayOfByte = new byte[-4 + this.b];
        this.a.get(arrayOfByte, 0, -4 + this.b);
        localArrayList.add(arrayOfByte);
        this.b = -1;
        continue;
        this.a.flip();
        int i = (int)(1.5D * (paramInt + this.a.limit())) >> 10 << 10;
        System.out.println("enlarge Buffer" + i);
        ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(i);
        localByteBuffer.put(this.a);
        localByteBuffer.put(paramArrayOfByte, 0, paramInt);
        this.a = localByteBuffer;
        break;
      }
      if (this.b != 0)
        break label300;
      this.b = -1;
    }
    label300: this.a.compact();
    return localArrayList;
  }

  public byte[] a(byte[] paramArrayOfByte)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
    try
    {
      localDataOutputStream.writeInt(4 + paramArrayOfByte.length);
      localDataOutputStream.write(paramArrayOfByte);
      localDataOutputStream.flush();
      return localByteArrayOutputStream.toByteArray();
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.socket.NetworkDataPackager
 * JD-Core Version:    0.6.0
 */