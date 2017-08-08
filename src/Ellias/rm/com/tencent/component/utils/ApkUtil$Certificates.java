package com.tencent.component.utils;

import android.content.pm.Signature;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ApkUtil$Certificates
{
  public static final String[] a = { "AndroidManifest.xml" };
  public static final String[] b = { "AndroidManifest.xml", "classes.dex" };
  private static final String c = "Certificates";
  private static final boolean d = false;
  private static final String e = "AndroidManifest.xml";
  private static final String f = "classes.dex";
  private static final Object g = new Object();
  private static WeakReference h;

  private static Enumeration a(JarFile paramJarFile, String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
      return paramJarFile.entries();
    return new a(paramJarFile, paramArrayOfString);
  }

  public static Signature[] a(String paramString)
  {
    return a(paramString, false);
  }

  public static Signature[] a(String paramString, boolean paramBoolean)
  {
    if (paramBoolean);
    for (String[] arrayOfString = b; ; arrayOfString = null)
      return a(paramString, arrayOfString);
  }

  public static Signature[] a(String paramString, String[] paramArrayOfString)
  {
    Signature[] arrayOfSignature;
    if (!b(paramString))
    {
      arrayOfSignature = null;
      return arrayOfSignature;
    }
    WeakReference localWeakReference1;
    byte[] arrayOfByte1;
    label43: byte[] arrayOfByte3;
    WeakReference localWeakReference3;
    label74: synchronized (g)
    {
      localWeakReference1 = h;
      if (localWeakReference1 != null)
      {
        h = null;
        arrayOfByte1 = (byte[])localWeakReference1.get();
        if (arrayOfByte1 == null)
        {
          byte[] arrayOfByte2 = new byte[8192];
          WeakReference localWeakReference2 = new WeakReference(arrayOfByte2);
          arrayOfByte3 = arrayOfByte2;
          localWeakReference3 = localWeakReference2;
        }
      }
    }
    while (true)
    {
      Object localObject3;
      int k;
      int m;
      try
      {
        while (true)
        {
          JarFile localJarFile = new JarFile(paramString);
          Enumeration localEnumeration = a(localJarFile, paramArrayOfString);
          localObject3 = null;
          while (localEnumeration.hasMoreElements())
          {
            JarEntry localJarEntry = (JarEntry)localEnumeration.nextElement();
            if ((localJarEntry == null) || (localJarEntry.isDirectory()) || (localJarEntry.getName().startsWith("META-INF/")))
              continue;
            Certificate[] arrayOfCertificate = a(localJarFile, localJarEntry, arrayOfByte3);
            if (arrayOfCertificate == null)
            {
              Log.e("Certificates", "File " + paramString + " has no certificates at entry " + localJarEntry.getName() + "; ignoring!");
              localJarFile.close();
              return null;
              localObject2 = finally;
              monitorexit;
              throw localObject2;
            }
            if (localObject3 == null)
            {
              localObject6 = arrayOfCertificate;
              localObject3 = localObject6;
              continue;
            }
            k = 0;
            if (k >= localObject3.length)
              break label565;
            m = 0;
            if (m >= arrayOfCertificate.length)
              break label559;
            if ((localObject3[k] == null) || (!localObject3[k].equals(arrayOfCertificate[m])))
              break label589;
            n = 1;
            if ((n != 0) && (localObject3.length == arrayOfCertificate.length))
              break label595;
            Log.e("Certificates", "File " + paramString + " has mismatched certificates at entry " + localJarEntry.getName() + "; ignoring!");
            localJarFile.close();
            return null;
          }
          localJarFile.close();
          synchronized (g)
          {
            h = localWeakReference3;
            if ((localObject3 != null) && (localObject3.length > 0))
            {
              int i = localObject3.length;
              arrayOfSignature = new Signature[localObject3.length];
              int j = 0;
              if (j >= i)
                break;
              arrayOfSignature[j] = new Signature(localObject3[j].getEncoded());
              j++;
            }
          }
        }
      }
      catch (CertificateEncodingException localCertificateEncodingException)
      {
        Log.w("Certificates", "Exception reading " + paramString, localCertificateEncodingException);
        return null;
        Log.e("Certificates", "File " + paramString + " has no certificates; ignoring!");
        return null;
      }
      catch (IOException localIOException)
      {
        Log.w("Certificates", "Exception reading " + paramString, localIOException);
        return null;
      }
      catch (RuntimeException localRuntimeException)
      {
        Log.w("Certificates", "Exception reading " + paramString, localRuntimeException);
        return null;
      }
      label559: int n = 0;
      continue;
      label565: Object localObject6 = localObject3;
      continue;
      arrayOfByte3 = arrayOfByte1;
      localWeakReference3 = localWeakReference1;
      break label74;
      arrayOfByte1 = null;
      break label43;
      label589: m++;
      continue;
      label595: k++;
    }
  }

  // ERROR //
  private static Certificate[] a(JarFile paramJarFile, JarEntry paramJarEntry, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 162	java/io/BufferedInputStream
    //   3: dup
    //   4: aload_0
    //   5: aload_1
    //   6: invokevirtual 166	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   9: invokespecial 169	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   12: astore_3
    //   13: aload_3
    //   14: aload_2
    //   15: iconst_0
    //   16: aload_2
    //   17: arraylength
    //   18: invokevirtual 175	java/io/InputStream:read	([BII)I
    //   21: iconst_m1
    //   22: if_icmpne -9 -> 13
    //   25: aconst_null
    //   26: astore 9
    //   28: aload_1
    //   29: ifnull +13 -> 42
    //   32: aload_1
    //   33: invokevirtual 179	java/util/jar/JarEntry:getCertificates	()[Ljava/security/cert/Certificate;
    //   36: astore 16
    //   38: aload 16
    //   40: astore 9
    //   42: aload_3
    //   43: ifnull +7 -> 50
    //   46: aload_3
    //   47: invokevirtual 180	java/io/InputStream:close	()V
    //   50: aload 9
    //   52: areturn
    //   53: astore 17
    //   55: ldc 11
    //   57: new 111	java/lang/StringBuilder
    //   60: dup
    //   61: invokespecial 112	java/lang/StringBuilder:<init>	()V
    //   64: ldc 182
    //   66: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: aload_1
    //   70: invokevirtual 101	java/util/jar/JarEntry:getName	()Ljava/lang/String;
    //   73: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: ldc 184
    //   78: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: aload_0
    //   82: invokevirtual 185	java/util/jar/JarFile:getName	()Ljava/lang/String;
    //   85: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   91: aload 17
    //   93: invokestatic 156	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   96: pop
    //   97: aload 9
    //   99: areturn
    //   100: astore 4
    //   102: aconst_null
    //   103: astore_3
    //   104: ldc 11
    //   106: new 111	java/lang/StringBuilder
    //   109: dup
    //   110: invokespecial 112	java/lang/StringBuilder:<init>	()V
    //   113: ldc 152
    //   115: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: aload_1
    //   119: invokevirtual 101	java/util/jar/JarEntry:getName	()Ljava/lang/String;
    //   122: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: ldc 184
    //   127: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: aload_0
    //   131: invokevirtual 185	java/util/jar/JarFile:getName	()Ljava/lang/String;
    //   134: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   140: aload 4
    //   142: invokestatic 156	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   145: pop
    //   146: aconst_null
    //   147: astore 9
    //   149: aload_3
    //   150: ifnull -100 -> 50
    //   153: aload_3
    //   154: invokevirtual 180	java/io/InputStream:close	()V
    //   157: aconst_null
    //   158: areturn
    //   159: astore 10
    //   161: ldc 11
    //   163: new 111	java/lang/StringBuilder
    //   166: dup
    //   167: invokespecial 112	java/lang/StringBuilder:<init>	()V
    //   170: ldc 182
    //   172: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: aload_1
    //   176: invokevirtual 101	java/util/jar/JarEntry:getName	()Ljava/lang/String;
    //   179: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: ldc 184
    //   184: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: aload_0
    //   188: invokevirtual 185	java/util/jar/JarFile:getName	()Ljava/lang/String;
    //   191: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   197: aload 10
    //   199: invokestatic 156	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   202: pop
    //   203: aconst_null
    //   204: areturn
    //   205: astore 12
    //   207: aconst_null
    //   208: astore_3
    //   209: ldc 11
    //   211: new 111	java/lang/StringBuilder
    //   214: dup
    //   215: invokespecial 112	java/lang/StringBuilder:<init>	()V
    //   218: ldc 152
    //   220: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: aload_1
    //   224: invokevirtual 101	java/util/jar/JarEntry:getName	()Ljava/lang/String;
    //   227: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: ldc 184
    //   232: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: aload_0
    //   236: invokevirtual 185	java/util/jar/JarFile:getName	()Ljava/lang/String;
    //   239: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   245: aload 12
    //   247: invokestatic 156	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   250: pop
    //   251: aconst_null
    //   252: astore 9
    //   254: aload_3
    //   255: ifnull -205 -> 50
    //   258: aload_3
    //   259: invokevirtual 180	java/io/InputStream:close	()V
    //   262: aconst_null
    //   263: areturn
    //   264: astore 14
    //   266: ldc 11
    //   268: new 111	java/lang/StringBuilder
    //   271: dup
    //   272: invokespecial 112	java/lang/StringBuilder:<init>	()V
    //   275: ldc 182
    //   277: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: aload_1
    //   281: invokevirtual 101	java/util/jar/JarEntry:getName	()Ljava/lang/String;
    //   284: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: ldc 184
    //   289: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   292: aload_0
    //   293: invokevirtual 185	java/util/jar/JarFile:getName	()Ljava/lang/String;
    //   296: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   302: aload 14
    //   304: invokestatic 156	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   307: pop
    //   308: aconst_null
    //   309: areturn
    //   310: astore 19
    //   312: aconst_null
    //   313: astore_3
    //   314: aload 19
    //   316: astore 5
    //   318: aload_3
    //   319: ifnull +7 -> 326
    //   322: aload_3
    //   323: invokevirtual 180	java/io/InputStream:close	()V
    //   326: aload 5
    //   328: athrow
    //   329: astore 6
    //   331: ldc 11
    //   333: new 111	java/lang/StringBuilder
    //   336: dup
    //   337: invokespecial 112	java/lang/StringBuilder:<init>	()V
    //   340: ldc 182
    //   342: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   345: aload_1
    //   346: invokevirtual 101	java/util/jar/JarEntry:getName	()Ljava/lang/String;
    //   349: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   352: ldc 184
    //   354: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: aload_0
    //   358: invokevirtual 185	java/util/jar/JarFile:getName	()Ljava/lang/String;
    //   361: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   364: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   367: aload 6
    //   369: invokestatic 156	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   372: pop
    //   373: goto -47 -> 326
    //   376: astore 5
    //   378: goto -60 -> 318
    //   381: astore 12
    //   383: goto -174 -> 209
    //   386: astore 4
    //   388: goto -284 -> 104
    //
    // Exception table:
    //   from	to	target	type
    //   46	50	53	java/lang/Exception
    //   0	13	100	java/io/IOException
    //   153	157	159	java/lang/Exception
    //   0	13	205	java/lang/RuntimeException
    //   258	262	264	java/lang/Exception
    //   0	13	310	finally
    //   322	326	329	java/lang/Exception
    //   13	25	376	finally
    //   32	38	376	finally
    //   104	146	376	finally
    //   209	251	376	finally
    //   13	25	381	java/lang/RuntimeException
    //   32	38	381	java/lang/RuntimeException
    //   13	25	386	java/io/IOException
    //   32	38	386	java/io/IOException
  }

  private static boolean b(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    File localFile;
    do
    {
      return false;
      localFile = new File(paramString);
    }
    while ((!localFile.exists()) || (!localFile.isFile()));
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.ApkUtil.Certificates
 * JD-Core Version:    0.6.0
 */