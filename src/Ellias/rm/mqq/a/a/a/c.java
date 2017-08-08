package mqq.a.a.a;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class c
  implements b
{
  public final byte[] a(byte[] paramArrayOfByte)
    throws Exception
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
    localGZIPOutputStream.write(paramArrayOfByte);
    localGZIPOutputStream.finish();
    localGZIPOutputStream.close();
    byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.close();
    return arrayOfByte;
  }

  public final byte[] b(byte[] paramArrayOfByte)
    throws Exception
  {
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    GZIPInputStream localGZIPInputStream = new GZIPInputStream(localByteArrayInputStream);
    byte[] arrayOfByte1 = new byte[1024];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    while (true)
    {
      int i = localGZIPInputStream.read(arrayOfByte1, 0, arrayOfByte1.length);
      if (i == -1)
      {
        byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
        localByteArrayOutputStream.flush();
        localByteArrayOutputStream.close();
        localGZIPInputStream.close();
        localByteArrayInputStream.close();
        return arrayOfByte2;
      }
      localByteArrayOutputStream.write(arrayOfByte1, 0, i);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     mqq.a.a.a.c
 * JD-Core Version:    0.6.0
 */