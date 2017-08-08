package com.tencent.apkupdate.a;

import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceStruct;
import com.tencent.apkupdate.logic.protocol.jce.Response;
import com.tencent.apkupdate.logic.protocol.jce.RspHead;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class a
{
  private String a;
  private final LinkedHashMap b = new LinkedHashMap();

  public static int a(int paramInt)
  {
    int i = paramInt & 0xFF;
    int j = 0xFF & paramInt >> 8;
    int k = 0xFF & paramInt >> 16;
    return paramInt >>> 24 | (i << 24 | j << 16 | k << 8);
  }

  private static JceStruct a(JceStruct paramJceStruct)
  {
    if (paramJceStruct == null)
      return null;
    String str1 = paramJceStruct.getClass().getSimpleName();
    String str2 = str1.substring(0, -7 + str1.length()) + "Response";
    try
    {
      localJceStruct = (JceStruct)Class.forName("com.tencent.apkupdate.logic.protocol.jce." + str2).newInstance();
      return localJceStruct;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      while (true)
      {
        localClassNotFoundException.printStackTrace();
        localJceStruct = null;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
      {
        localIllegalAccessException.printStackTrace();
        localJceStruct = null;
      }
    }
    catch (InstantiationException localInstantiationException)
    {
      while (true)
      {
        localInstantiationException.printStackTrace();
        JceStruct localJceStruct = null;
      }
    }
  }

  public static JceStruct a(JceStruct paramJceStruct, byte[] paramArrayOfByte)
  {
    if ((paramJceStruct != null) && (paramArrayOfByte != null))
    {
      JceStruct localJceStruct = a(paramJceStruct);
      if (localJceStruct != null)
        try
        {
          JceInputStream localJceInputStream = new JceInputStream(paramArrayOfByte);
          localJceInputStream.setServerEncoding("utf-8");
          localJceStruct.readFrom(localJceInputStream);
          return localJceStruct;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          return null;
        }
    }
    return null;
  }

  public static Response a(byte[] paramArrayOfByte)
  {
    Response localResponse;
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 4))
      localResponse = null;
    while (true)
    {
      return localResponse;
      localResponse = new Response();
      try
      {
        JceInputStream localJceInputStream = new JceInputStream(paramArrayOfByte);
        localJceInputStream.setServerEncoding("utf-8");
        localResponse.readFrom(localJceInputStream);
        if (localResponse.head.ret != 0)
          continue;
        if ((0x2 & localResponse.head.encryptWithPack) == 2)
        {
          byte[] arrayOfByte1 = localResponse.body;
          byte[] arrayOfByte2 = "ji*9^&43U0X-~./(".getBytes();
          localResponse.body = new com.tencent.apkupdate.c.a().a(arrayOfByte1, 0, arrayOfByte1.length, arrayOfByte2);
        }
        if ((0x1 & localResponse.head.encryptWithPack) == 1)
          localResponse.body = c(localResponse.body);
        com.tencent.apkupdate.c.b.a().b(localResponse.head.phoneGuid);
        return localResponse;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return null;
  }

  public static void a(b paramb, DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(1347094280);
    paramDataOutputStream.writeInt(a(paramb.g));
    paramDataOutputStream.writeInt(a(paramb.h));
    paramDataOutputStream.writeInt(a(paramb.i));
  }

  // ERROR //
  public static byte[] b(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 172	java/util/zip/Deflater
    //   3: dup
    //   4: invokespecial 173	java/util/zip/Deflater:<init>	()V
    //   7: astore_1
    //   8: new 175	java/io/ByteArrayOutputStream
    //   11: dup
    //   12: aload_0
    //   13: arraylength
    //   14: invokespecial 177	java/io/ByteArrayOutputStream:<init>	(I)V
    //   17: astore_2
    //   18: aload_1
    //   19: bipush 9
    //   21: invokevirtual 180	java/util/zip/Deflater:setLevel	(I)V
    //   24: aload_1
    //   25: aload_0
    //   26: invokevirtual 183	java/util/zip/Deflater:setInput	([B)V
    //   29: aload_1
    //   30: invokevirtual 186	java/util/zip/Deflater:finish	()V
    //   33: sipush 1024
    //   36: newarray byte
    //   38: astore 5
    //   40: aload_1
    //   41: invokevirtual 190	java/util/zip/Deflater:finished	()Z
    //   44: ifne +34 -> 78
    //   47: aload_2
    //   48: aload 5
    //   50: iconst_0
    //   51: aload_1
    //   52: aload 5
    //   54: invokevirtual 194	java/util/zip/Deflater:deflate	([B)I
    //   57: invokevirtual 198	java/io/ByteArrayOutputStream:write	([BII)V
    //   60: goto -20 -> 40
    //   63: astore_3
    //   64: aload_1
    //   65: invokevirtual 201	java/util/zip/Deflater:end	()V
    //   68: aload_2
    //   69: ifnull +7 -> 76
    //   72: aload_2
    //   73: invokevirtual 204	java/io/ByteArrayOutputStream:close	()V
    //   76: aload_3
    //   77: athrow
    //   78: aload_1
    //   79: invokevirtual 201	java/util/zip/Deflater:end	()V
    //   82: aload_2
    //   83: invokevirtual 204	java/io/ByteArrayOutputStream:close	()V
    //   86: aload_2
    //   87: invokevirtual 207	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   90: areturn
    //   91: astore 6
    //   93: aload 6
    //   95: invokevirtual 208	java/io/IOException:printStackTrace	()V
    //   98: goto -12 -> 86
    //   101: astore 4
    //   103: aload 4
    //   105: invokevirtual 208	java/io/IOException:printStackTrace	()V
    //   108: goto -32 -> 76
    //   111: astore_3
    //   112: aconst_null
    //   113: astore_2
    //   114: goto -50 -> 64
    //
    // Exception table:
    //   from	to	target	type
    //   18	40	63	finally
    //   40	60	63	finally
    //   82	86	91	java/io/IOException
    //   72	76	101	java/io/IOException
    //   8	18	111	finally
  }

  // ERROR //
  public static byte[] c(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 212	java/util/zip/Inflater
    //   3: dup
    //   4: invokespecial 213	java/util/zip/Inflater:<init>	()V
    //   7: astore_1
    //   8: aconst_null
    //   9: astore_2
    //   10: new 175	java/io/ByteArrayOutputStream
    //   13: dup
    //   14: aload_0
    //   15: arraylength
    //   16: invokespecial 177	java/io/ByteArrayOutputStream:<init>	(I)V
    //   19: astore_3
    //   20: sipush 1024
    //   23: newarray byte
    //   25: astore 9
    //   27: aload_1
    //   28: aload_0
    //   29: invokevirtual 214	java/util/zip/Inflater:setInput	([B)V
    //   32: aload_1
    //   33: invokevirtual 215	java/util/zip/Inflater:finished	()Z
    //   36: ifne +43 -> 79
    //   39: aload_3
    //   40: aload 9
    //   42: iconst_0
    //   43: aload_1
    //   44: aload 9
    //   46: invokevirtual 218	java/util/zip/Inflater:inflate	([B)I
    //   49: invokevirtual 198	java/io/ByteArrayOutputStream:write	([BII)V
    //   52: goto -20 -> 32
    //   55: astore 7
    //   57: aload 7
    //   59: invokevirtual 219	java/util/zip/DataFormatException:printStackTrace	()V
    //   62: aload_1
    //   63: invokevirtual 220	java/util/zip/Inflater:end	()V
    //   66: aload_3
    //   67: ifnull +7 -> 74
    //   70: aload_3
    //   71: invokevirtual 204	java/io/ByteArrayOutputStream:close	()V
    //   74: aload_3
    //   75: invokevirtual 207	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   78: areturn
    //   79: aload_1
    //   80: invokevirtual 220	java/util/zip/Inflater:end	()V
    //   83: aload_3
    //   84: invokevirtual 204	java/io/ByteArrayOutputStream:close	()V
    //   87: goto -13 -> 74
    //   90: astore 10
    //   92: goto -18 -> 74
    //   95: astore 5
    //   97: aload_1
    //   98: invokevirtual 220	java/util/zip/Inflater:end	()V
    //   101: aload_2
    //   102: ifnull +7 -> 109
    //   105: aload_2
    //   106: invokevirtual 204	java/io/ByteArrayOutputStream:close	()V
    //   109: aload 5
    //   111: athrow
    //   112: astore 8
    //   114: goto -40 -> 74
    //   117: astore 6
    //   119: goto -10 -> 109
    //   122: astore 4
    //   124: aload_3
    //   125: astore_2
    //   126: aload 4
    //   128: astore 5
    //   130: goto -33 -> 97
    //   133: astore 11
    //   135: aload 11
    //   137: astore 7
    //   139: aconst_null
    //   140: astore_3
    //   141: goto -84 -> 57
    //
    // Exception table:
    //   from	to	target	type
    //   20	32	55	java/util/zip/DataFormatException
    //   32	52	55	java/util/zip/DataFormatException
    //   83	87	90	java/io/IOException
    //   10	20	95	finally
    //   70	74	112	java/io/IOException
    //   105	109	117	java/io/IOException
    //   20	32	122	finally
    //   32	52	122	finally
    //   57	62	122	finally
    //   10	20	133	java/util/zip/DataFormatException
  }

  public final LinkedHashMap a()
  {
    return this.b;
  }

  public final void a(String paramString)
  {
    this.a = paramString;
    g localg = new g();
    try
    {
      localg.a(this.a);
      Iterator localIterator = localg.b.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        this.b.put(localEntry.getKey(), Long.valueOf(((b)localEntry.getValue()).g | ((b)localEntry.getValue()).i));
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.a.a
 * JD-Core Version:    0.6.0
 */