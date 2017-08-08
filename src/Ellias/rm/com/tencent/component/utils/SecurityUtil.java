package com.tencent.component.utils;

import android.webkit.WebView;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil
{
  private static final char[] a = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  private static final long b = -7661587058870466123L;
  private static final long c = -1L;
  private static long[] d = new long[256];

  static
  {
    for (int i = 0; i < 256; i++)
    {
      long l1 = i;
      int j = 0;
      long l2 = l1;
      if (j < 8)
      {
        long l3;
        if ((0x1 & (int)l2) != 0)
          l3 = -7661587058870466123L;
        while (true)
        {
          l2 = l3 ^ l2 >> 1;
          j++;
          break;
          l3 = 0L;
        }
      }
      d[i] = l2;
    }
  }

  public static long a(byte[] paramArrayOfByte)
  {
    long l = -1L;
    int i = 0;
    int j = paramArrayOfByte.length;
    while (i < j)
    {
      l = d[(0xFF & ((int)l ^ paramArrayOfByte[i]))] ^ l >> 8;
      i++;
    }
    return l;
  }

  public static String a(File paramFile)
  {
    return a(paramFile, "MD5");
  }

  public static String a(File paramFile, String paramString)
  {
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.isFile()))
      return null;
    try
    {
      String str = b(paramFile, paramString);
      return str;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return null;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return null;
  }

  public static String a(String paramString)
  {
    return a(paramString, "MD5");
  }

  public static String a(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance(paramString2);
      localMessageDigest.update(paramString1.getBytes());
      String str = b(localMessageDigest.digest());
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return null;
  }

  public static void a(WebView paramWebView, Object paramObject, String paramString)
  {
    try
    {
      WebView.class.getMethod("addJavascript" + "Interface", new Class[] { Object.class, String.class }).invoke(paramWebView, new Object[] { paramObject, paramString });
      if (PlatformUtil.version() >= 11)
        paramWebView.removeJavascriptInterface("searchBoxJavaBridge_");
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
  }

  public static void a(WebView paramWebView, String paramString)
  {
    try
    {
      WebView.class.getMethod("removeJavascriptInterface", new Class[] { Object.class, String.class }).invoke(paramWebView, new Object[] { paramString });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
  }

  public static byte[] a(byte[] paramArrayOfByte, String paramString)
  {
    return new TEA(paramArrayOfByte).a(paramString);
  }

  public static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return new TEA(paramArrayOfByte1).a(paramArrayOfByte2);
  }

  public static long b(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return 0L;
    return a(c(paramString));
  }

  public static String b(File paramFile)
  {
    return b(paramFile, "MD5");
  }

  // ERROR //
  public static String b(File paramFile, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnonnull +5 -> 8
    //   6: aload_2
    //   7: areturn
    //   8: aload_1
    //   9: invokestatic 76	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   12: astore 7
    //   14: new 170	java/io/FileInputStream
    //   17: dup
    //   18: aload_0
    //   19: invokespecial 173	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   22: astore 5
    //   24: sipush 1024
    //   27: newarray byte
    //   29: astore 8
    //   31: aload 5
    //   33: aload 8
    //   35: invokevirtual 177	java/io/FileInputStream:read	([B)I
    //   38: istore 9
    //   40: iload 9
    //   42: ifle +31 -> 73
    //   45: aload 7
    //   47: aload 8
    //   49: iconst_0
    //   50: iload 9
    //   52: invokevirtual 180	java/security/MessageDigest:update	([BII)V
    //   55: goto -24 -> 31
    //   58: astore 4
    //   60: aload 5
    //   62: ifnull +8 -> 70
    //   65: aload 5
    //   67: invokevirtual 183	java/io/FileInputStream:close	()V
    //   70: aload 4
    //   72: athrow
    //   73: aload 7
    //   75: invokevirtual 89	java/security/MessageDigest:digest	()[B
    //   78: invokestatic 92	com/tencent/component/utils/SecurityUtil:b	([B)Ljava/lang/String;
    //   81: astore 10
    //   83: aload 10
    //   85: astore_2
    //   86: aload 5
    //   88: ifnull -82 -> 6
    //   91: aload 5
    //   93: invokevirtual 183	java/io/FileInputStream:close	()V
    //   96: aload_2
    //   97: areturn
    //   98: astore 11
    //   100: aload 11
    //   102: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   105: aload_2
    //   106: areturn
    //   107: astore 6
    //   109: aload 6
    //   111: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   114: goto -44 -> 70
    //   117: astore_3
    //   118: aload_3
    //   119: astore 4
    //   121: aconst_null
    //   122: astore 5
    //   124: goto -64 -> 60
    //
    // Exception table:
    //   from	to	target	type
    //   24	31	58	finally
    //   31	40	58	finally
    //   45	55	58	finally
    //   73	83	58	finally
    //   91	96	98	java/io/IOException
    //   65	70	107	java/io/IOException
    //   8	24	117	finally
  }

  private static String b(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      return null;
    char[] arrayOfChar = new char[2 * paramArrayOfByte.length];
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      int j = paramArrayOfByte[i];
      arrayOfChar[(1 + i * 2)] = a[(j & 0xF)];
      int k = (byte)(j >>> 4);
      arrayOfChar[(i * 2)] = a[(k & 0xF)];
    }
    return new String(arrayOfChar);
  }

  public static byte[] b(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return new TEA(paramArrayOfByte1).b(paramArrayOfByte2);
  }

  public static String c(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return new TEA(paramArrayOfByte1).c(paramArrayOfByte2);
  }

  public static byte[] c(String paramString)
  {
    int i = 0;
    byte[] arrayOfByte = new byte[2 * paramString.length()];
    char[] arrayOfChar = paramString.toCharArray();
    int j = arrayOfChar.length;
    int k = 0;
    while (i < j)
    {
      int m = arrayOfChar[i];
      int n = k + 1;
      arrayOfByte[k] = (byte)(m & 0xFF);
      k = n + 1;
      arrayOfByte[n] = (byte)(m >> 8);
      i++;
    }
    return arrayOfByte;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.SecurityUtil
 * JD-Core Version:    0.6.0
 */