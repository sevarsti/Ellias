package com.tencent.msdk.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ApkSigReader
{
  public static String getSign(Context paramContext)
  {
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(64).iterator();
    String str1 = "";
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (!localPackageInfo.packageName.equals(paramContext.getPackageName()))
        continue;
      str1 = localPackageInfo.signatures[0].toCharsString();
    }
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(str1.getBytes());
      String str2 = HexUtil.bytes2HexStr(localMessageDigest.digest()).toLowerCase(Locale.CHINA);
      return str2;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return "";
  }

  public static String getSignaturesFromApk(Context paramContext)
    throws IOException
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    PackageInfo localPackageInfo;
    try
    {
      localPackageInfo = localPackageManager.getPackageInfo(paramContext.getPackageName(), 64);
      if (localPackageInfo == null)
        return null;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      return null;
    }
    String str1 = new String(localPackageInfo.signatures[0].toByteArray());
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(str1.getBytes());
      String str2 = HexUtil.bytes2HexStr(localMessageDigest.digest()).toLowerCase(Locale.CHINA);
      return str2;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return "";
  }

  private static Certificate[] loadCertificates(JarFile paramJarFile, JarEntry paramJarEntry, byte[] paramArrayOfByte)
  {
    try
    {
      InputStream localInputStream = paramJarFile.getInputStream(paramJarEntry);
      while (localInputStream.read(paramArrayOfByte, 0, paramArrayOfByte.length) != -1);
      localInputStream.close();
      Object localObject = null;
      if (paramJarEntry != null)
      {
        Certificate[] arrayOfCertificate = paramJarEntry.getCertificates();
        localObject = arrayOfCertificate;
      }
      return localObject;
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  private static String toCharsString(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    char[] arrayOfChar = new char[i * 2];
    int j = 0;
    if (j < i)
    {
      int k = paramArrayOfByte[j];
      int m = 0xF & k >> 4;
      int n = j * 2;
      int i1;
      label52: int i2;
      int i3;
      if (m >= 10)
      {
        i1 = -10 + (m + 97);
        arrayOfChar[n] = (char)i1;
        i2 = k & 0xF;
        i3 = 1 + j * 2;
        if (i2 < 10)
          break label113;
      }
      label113: for (int i4 = -10 + (i2 + 97); ; i4 = i2 + 48)
      {
        arrayOfChar[i3] = (char)i4;
        j++;
        break;
        i1 = m + 48;
        break label52;
      }
    }
    return new String(arrayOfChar);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.ApkSigReader
 * JD-Core Version:    0.6.0
 */