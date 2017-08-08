package mqq.a.a.a;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public final class d
  implements b
{
  public final byte[] a(byte[] paramArrayOfByte)
    throws Exception
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    ZipOutputStream localZipOutputStream = new ZipOutputStream(localByteArrayOutputStream);
    ZipEntry localZipEntry = new ZipEntry("zip");
    localZipEntry.setSize(paramArrayOfByte.length);
    localZipOutputStream.putNextEntry(localZipEntry);
    localZipOutputStream.write(paramArrayOfByte);
    localZipOutputStream.closeEntry();
    localZipOutputStream.close();
    byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.close();
    return arrayOfByte;
  }

  public final byte[] b(byte[] paramArrayOfByte)
    throws Exception
  {
    byte[] arrayOfByte1 = null;
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    ZipInputStream localZipInputStream = new ZipInputStream(localByteArrayInputStream);
    if (localZipInputStream.getNextEntry() == null)
    {
      localZipInputStream.close();
      localByteArrayInputStream.close();
      return arrayOfByte1;
    }
    byte[] arrayOfByte2 = new byte[1024];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    while (true)
    {
      int i = localZipInputStream.read(arrayOfByte2, 0, arrayOfByte2.length);
      if (i == -1)
      {
        arrayOfByte1 = localByteArrayOutputStream.toByteArray();
        localByteArrayOutputStream.flush();
        localByteArrayOutputStream.close();
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte2, 0, i);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     mqq.a.a.a.d
 * JD-Core Version:    0.6.0
 */