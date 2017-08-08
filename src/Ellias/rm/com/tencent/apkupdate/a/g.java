package com.tencent.apkupdate.a;

import B;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class g
{
  LinkedHashMap a = new LinkedHashMap();
  LinkedHashMap b = new LinkedHashMap();
  private ArrayList c = new ArrayList();
  private c d = new c();
  private long e = 0L;
  private String f = null;

  private void a()
  {
    DataInputStream localDataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(this.f)));
    localDataInputStream.skip(this.d.f);
    int i = 0;
    while ((localDataInputStream.available() >= 4) && (i == 0))
      switch (localDataInputStream.readInt())
      {
      default:
        break;
      case 1347092738:
        b localb = new b();
        int j = localDataInputStream.readShort();
        int k = j & 0xFF;
        localb.a = (short)(0xFF & j >> 8 | k << 8);
        int m = localDataInputStream.readShort();
        int n = m & 0xFF;
        localb.b = (short)(0xFF & m >> 8 | n << 8);
        int i1 = localDataInputStream.readShort();
        int i2 = i1 & 0xFF;
        localb.c = (short)(0xFF & i1 >> 8 | i2 << 8);
        int i3 = localDataInputStream.readShort();
        int i4 = i3 & 0xFF;
        localb.d = (short)(0xFF & i3 >> 8 | i4 << 8);
        int i5 = localDataInputStream.readShort();
        int i6 = i5 & 0xFF;
        localb.e = (short)(0xFF & i5 >> 8 | i6 << 8);
        int i7 = localDataInputStream.readShort();
        int i8 = i7 & 0xFF;
        localb.f = (short)(0xFF & i7 >> 8 | i8 << 8);
        localb.g = a.a(localDataInputStream.readInt());
        localb.h = a.a(localDataInputStream.readInt());
        localb.i = a.a(localDataInputStream.readInt());
        int i9 = localDataInputStream.readShort();
        int i10 = i9 & 0xFF;
        localb.j = (short)(0xFF & i9 >> 8 | i10 << 8);
        int i11 = localDataInputStream.readShort();
        int i12 = i11 & 0xFF;
        localb.l = (short)(0xFF & i11 >> 8 | i12 << 8);
        int i13 = localDataInputStream.readShort();
        int i14 = i13 & 0xFF;
        localb.m = (short)(0xFF & i13 >> 8 | i14 << 8);
        int i15 = localDataInputStream.readShort();
        int i16 = i15 & 0xFF;
        localb.n = (short)(0xFF & i15 >> 8 | i16 << 8);
        int i17 = localDataInputStream.readShort();
        int i18 = i17 & 0xFF;
        localb.o = (short)(0xFF & i17 >> 8 | i18 << 8);
        localb.p = a.a(localDataInputStream.readInt());
        localb.q = a.a(localDataInputStream.readInt());
        localb.r = false;
        localb.s = new byte[localb.j];
        localb.u = new byte[localb.l];
        localb.v = new byte[localb.m];
        localDataInputStream.read(localb.s, 0, localb.j);
        localDataInputStream.read(localb.u, 0, localb.l);
        localDataInputStream.read(localb.v, 0, localb.m);
        String str = new String(localb.s, Charset.defaultCharset().name());
        if (str.startsWith("META-INF/"))
          this.c.add(str);
        this.b.put(str, localb);
        break;
      case 1347093766:
        i = 1;
      }
    localDataInputStream.close();
  }

  private void a(RandomAccessFile paramRandomAccessFile)
  {
    long l = 1024L;
    if (l > this.e)
      l = this.e;
    byte[] arrayOfByte = new byte[1024];
    paramRandomAccessFile.skipBytes((int)(this.e - l));
    paramRandomAccessFile.read(arrayOfByte, 0, (int)l);
    int i = 0;
    while (true)
      if (i < l - 4L)
      {
        if ((arrayOfByte[i] << 24) + (arrayOfByte[(i + 1)] << 16) + (arrayOfByte[(i + 2)] << 8) + arrayOfByte[(i + 3)] == 1347093766);
        try
        {
          DataInputStream localDataInputStream = new DataInputStream(new ByteArrayInputStream(arrayOfByte, i + 4, (int)(l - (i + 4))));
          this.d.a(localDataInputStream);
          localDataInputStream.close();
          i++;
        }
        catch (IOException localIOException)
        {
          while (true)
            localIOException.printStackTrace();
        }
      }
  }

  private void b()
  {
    DataInputStream localDataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(this.f)));
    int i = 0;
    if ((localDataInputStream.available() >= 4) && (i == 0))
    {
      switch (localDataInputStream.readInt())
      {
      default:
      case 1347093252:
      case 1347094280:
      case 1347092738:
      case 1347093766:
      }
      for (int j = i; ; j = 1)
      {
        i = j;
        break;
        f localf = new f();
        localDataInputStream.readShort();
        localDataInputStream.readShort();
        localDataInputStream.readShort();
        localDataInputStream.readShort();
        localDataInputStream.readShort();
        localDataInputStream.readInt();
        localf.a = a.a(localDataInputStream.readInt());
        localDataInputStream.readInt();
        int k = localDataInputStream.readShort();
        int m = k & 0xFF;
        localf.b = (short)(0xFF & k >> 8 | m << 8);
        int n = localDataInputStream.readShort();
        int i1 = n & 0xFF;
        localf.c = (short)(0xFF & n >> 8 | i1 << 8);
        localf.d = new byte[localf.b];
        localf.e = new byte[localf.c];
        localDataInputStream.read(localf.d, 0, localf.b);
        localDataInputStream.read(localf.e, 0, localf.c);
        String str = new String(localf.d, Charset.defaultCharset().name());
        b localb = (b)this.b.get(str);
        localb.k = localf.c;
        if (localb.k > 0)
          localb.t = ((byte[])localf.e.clone());
        localf.a = localb.h;
        localDataInputStream.skipBytes(localf.a);
        this.a.put(str, localf);
        break;
        localDataInputStream.skipBytes(12);
        break;
        i = 1;
        break;
      }
    }
    localDataInputStream.close();
  }

  public final void a(String paramString)
  {
    this.f = paramString;
    RandomAccessFile localRandomAccessFile = new RandomAccessFile(paramString, "r");
    this.e = localRandomAccessFile.length();
    a(localRandomAccessFile);
    localRandomAccessFile.close();
    Log.i("ZipFileParser", "readEndOfCentralDirRecord finished.");
    a();
    Log.i("ZipFileParser", "readCentralDirFileHeaderList finished.");
    b();
    Log.i("ZipFileParser", "readLocalFileHeader finished.");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.a.g
 * JD-Core Version:    0.6.0
 */