package com.tencent.tmassistantsdk.f;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.g.f;
import com.tencent.tmassistantsdk.g.l;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;

public class b
{
  protected String a = null;
  protected String b = null;
  protected FileOutputStream c = null;
  protected byte[] d = null;
  protected long e = 0L;
  protected int f = 0;

  public b(String paramString1, String paramString2)
  {
    this.a = paramString1;
    this.b = paramString2;
    this.e = b();
  }

  private boolean a(FileOutputStream paramFileOutputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2, long paramLong)
  {
    if ((paramFileOutputStream == null) || (paramArrayOfByte == null))
      return false;
    if (paramLong != this.e)
    {
      l.b("TMAssistantFile", "writeData0 failed,filelen:" + this.e + ",offset:" + paramLong + ",filename:" + this.a);
      return false;
    }
    if (paramInt2 >= 16384);
    while (true)
    {
      try
      {
        if (this.f <= 0)
          continue;
        paramFileOutputStream.write(this.d, 0, this.f);
        this.f = 0;
        paramFileOutputStream.write(paramArrayOfByte, paramInt1, paramInt2);
        this.e += paramInt2;
        return true;
      }
      catch (IOException localIOException2)
      {
        localIOException2.printStackTrace();
        l.d("TMAssistantFile", "writeData1 failed " + localIOException2);
        return false;
      }
      if ((paramInt2 + this.f > 16384) && (this.f > 0));
      try
      {
        paramFileOutputStream.write(this.d, 0, this.f);
        this.f = 0;
        System.arraycopy(paramArrayOfByte, paramInt1, this.d, this.f, paramInt2);
        this.f = (paramInt2 + this.f);
        this.e += paramInt2;
      }
      catch (IOException localIOException1)
      {
        localIOException1.printStackTrace();
        l.d("TMAssistantFile", "writeData2 failed " + localIOException1);
      }
    }
    return false;
  }

  private boolean a(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString2 != null))
    {
      File localFile = new File(paramString1);
      if (localFile.exists() == true)
      {
        boolean bool = localFile.renameTo(new File(paramString2));
        if (bool)
          f.d(paramString2);
        return bool;
      }
    }
    l.d("TMAssistantFile", "moveFileFromTmpToSavaPath failed ");
    return false;
  }

  public static String b(String paramString)
  {
    if (paramString == null);
    String str;
    do
    {
      return null;
      str = e();
    }
    while (str == null);
    return str + File.separator + paramString;
  }

  public static String e()
  {
    Context localContext = f.a().b();
    if (localContext == null)
      return null;
    if (g())
    {
      String str1 = localContext.getPackageName();
      String str2 = "/tencent/TMAssistantSDK/Download/" + str1;
      return Environment.getExternalStorageDirectory().getAbsolutePath() + str2;
    }
    return localContext.getFilesDir().getAbsolutePath() + "/TMAssistantSDK/Download";
  }

  public static boolean g()
  {
    return ("mounted".equals(Environment.getExternalStorageState())) && (Environment.getExternalStorageDirectory().canWrite());
  }

  public void a()
  {
    String str = c(this.a);
    l.b("TMAssistantFile", "deleteFile 1 tmpFilePathString: " + str);
    if (!TextUtils.isEmpty(str))
    {
      File localFile = new File(str);
      l.b("TMAssistantFile", "deleteFile 2 file: " + localFile);
      if ((localFile != null) && (localFile.exists()))
      {
        boolean bool = localFile.delete();
        l.b("TMAssistantFile", "deleteFile result:" + bool + ",filename:" + str);
      }
    }
    else
    {
      return;
    }
    l.b("TMAssistantFile", "deleteFile 3");
  }

  void a(String paramString)
  {
    int i = 1;
    if (TextUtils.isEmpty(paramString))
      throw new InvalidParameterException("fileFullPath is null or the filename.size is zero.");
    int j = paramString.lastIndexOf("/");
    if ((j == -1) || (paramString.length() == i))
      throw new InvalidParameterException("fileFullPath is not a valid full path. fileName: " + paramString);
    if (j > 0)
      paramString = paramString.substring(0, j);
    File localFile = new File(paramString);
    boolean bool;
    if (!localFile.exists())
      bool = localFile.mkdirs();
    if (!bool)
      throw new IOException("Failed to create directory. dir: " + paramString);
  }

  public boolean a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, long paramLong, boolean paramBoolean)
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (this.c != null)
          continue;
        String str = c(this.a);
        if (str != null)
          try
          {
            a(str);
            this.c = new FileOutputStream(str, true);
            if (this.d != null)
              continue;
            this.d = new byte[16384];
            this.f = 0;
            boolean bool1 = a(this.c, paramArrayOfByte, paramInt1, paramInt2, paramLong);
            bool2 = bool1;
            if (bool2)
              break label152;
            monitorexit;
            return bool2;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            l.d("TMAssistantFile", "write failed" + localException);
            bool2 = false;
            continue;
          }
      }
      finally
      {
        monitorexit;
      }
      l.d("TMAssistantFile", "write failed tmpFilePathString is null");
      boolean bool2 = false;
      continue;
      label152: if ((paramBoolean == true) && (c() == true))
      {
        boolean bool3 = a(c(this.a), b(this.b));
        bool2 = bool3;
        continue;
      }
      bool2 = true;
    }
  }

  public long b()
  {
    long l = 0L;
    String str1 = b(this.b);
    File localFile1;
    File localFile2;
    if (str1 != null)
    {
      localFile1 = new File(str1);
      if (localFile1.exists())
        break label90;
      String str2 = c(this.a);
      if (str2 != null)
      {
        localFile2 = new File(str2);
        if (localFile2.exists())
          break label78;
        this.e = l;
      }
    }
    while (true)
    {
      l = this.e;
      return l;
      label78: this.e = localFile2.length();
      continue;
      label90: this.e = localFile1.length();
    }
  }

  public String c(String paramString)
  {
    if (paramString == null);
    String str;
    do
    {
      return null;
      str = e();
    }
    while (str == null);
    return str + "/.tmp/" + paramString + ".tmp";
  }

  public boolean c()
  {
    monitorenter;
    try
    {
      FileOutputStream localFileOutputStream = this.c;
      int i = 0;
      if (localFileOutputStream != null)
      {
        int j = this.f;
        i = 0;
        if (j <= 0);
      }
      try
      {
        this.c.write(this.d, 0, this.f);
        this.f = 0;
        i = 1;
        monitorexit;
        return i;
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          localIOException.printStackTrace();
          l.d("TMAssistantFile", "flush failed " + localIOException);
          i = 0;
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void d()
  {
    monitorenter;
    try
    {
      c();
      try
      {
        if (this.c != null)
          this.c.close();
        this.c = null;
        this.d = null;
        this.e = 0L;
        monitorexit;
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          localIOException.printStackTrace();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void f()
  {
    a(c(this.a), b(this.b));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.f.b
 * JD-Core Version:    0.6.0
 */