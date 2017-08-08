package com.tencent.tmassistantsdk.protocol;

import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.tencent.tmassistantsdk.g.e;
import com.tencent.tmassistantsdk.g.f;
import com.tencent.tmassistantsdk.protocol.jce.ReportLogRequest;
import com.tencent.tmassistantsdk.protocol.jce.ReqHead;
import com.tencent.tmassistantsdk.protocol.jce.Request;
import com.tencent.tmassistantsdk.protocol.jce.Response;
import com.tencent.tmassistantsdk.protocol.jce.RspHead;
import com.tencent.tmassistantsdk.protocol.jce.Terminal;
import java.util.List;

public class a
{
  public static JceStruct a(byte paramByte, List paramList, String paramString1, int paramInt, String paramString2)
  {
    ReportLogRequest localReportLogRequest = new ReportLogRequest();
    localReportLogRequest.logType = paramByte;
    localReportLogRequest.logData = a(paramList);
    localReportLogRequest.hostAppPackageName = paramString1;
    localReportLogRequest.hostAppVersion = paramInt;
    localReportLogRequest.hostUserId = paramString2;
    return localReportLogRequest;
  }

  public static JceStruct a(JceStruct paramJceStruct, byte[] paramArrayOfByte)
  {
    if ((paramJceStruct != null) && (paramArrayOfByte != null))
    {
      JceStruct localJceStruct = d(paramJceStruct);
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

  public static JceStruct a(byte[] paramArrayOfByte, Class paramClass)
  {
    if (paramArrayOfByte == null)
      return null;
    try
    {
      JceInputStream localJceInputStream = new JceInputStream(paramArrayOfByte);
      localJceInputStream.setServerEncoding("utf-8");
      JceStruct localJceStruct = (JceStruct)paramClass.newInstance();
      localJceStruct.readFrom(localJceInputStream);
      return localJceStruct;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public static ReqHead a(JceStruct paramJceStruct)
  {
    if (paramJceStruct == null)
      return null;
    ReqHead localReqHead = new ReqHead();
    localReqHead.requestId = f.k();
    String str1 = paramJceStruct.getClass().getSimpleName();
    String str2 = str1.substring(0, str1.length() - "Request".length());
    localReqHead.cmdId = f.a().a(str2);
    localReqHead.qua = f.a().c;
    localReqHead.phoneGuid = f.a().g();
    Terminal localTerminal = new Terminal();
    localTerminal.androidId = f.a().f();
    localTerminal.androidIdSdCard = f.a().g();
    localTerminal.imei = f.a().h();
    localTerminal.imsi = f.a().i();
    localTerminal.macAdress = f.a().j();
    localReqHead.terminal = localTerminal;
    localReqHead.assistantAPILevel = f.a().m();
    localReqHead.assistantVersionCode = f.a().n();
    return localReqHead;
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
          localResponse.body = b(localResponse.body, "ji*9^&43U0X-~./(".getBytes());
        if ((0x1 & localResponse.head.encryptWithPack) == 1)
          localResponse.body = b.b(localResponse.body);
        f.a().b(localResponse.head.phoneGuid);
        return localResponse;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return null;
  }

  public static byte[] a(Request paramRequest)
  {
    if (paramRequest == null)
      return null;
    paramRequest.head.encryptWithPack = 0;
    if (paramRequest.body.length > 256)
    {
      paramRequest.body = b.a(paramRequest.body);
      paramRequest.head.encryptWithPack = (byte)(0x1 | paramRequest.head.encryptWithPack);
    }
    paramRequest.body = a(paramRequest.body, "ji*9^&43U0X-~./(".getBytes());
    paramRequest.head.encryptWithPack = (byte)(0x2 | paramRequest.head.encryptWithPack);
    return c(paramRequest);
  }

  // ERROR //
  public static byte[] a(List paramList)
  {
    // Byte code:
    //   0: new 224	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 225	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore_1
    //   8: new 227	java/io/DataOutputStream
    //   11: dup
    //   12: aload_1
    //   13: invokespecial 230	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   16: astore_2
    //   17: aload_0
    //   18: invokeinterface 236 1 0
    //   23: astore 10
    //   25: aload 10
    //   27: invokeinterface 242 1 0
    //   32: ifeq +60 -> 92
    //   35: aload 10
    //   37: invokeinterface 245 1 0
    //   42: checkcast 246	[B
    //   45: astore 14
    //   47: aload_2
    //   48: aload 14
    //   50: arraylength
    //   51: invokevirtual 250	java/io/DataOutputStream:writeInt	(I)V
    //   54: aload_2
    //   55: aload 14
    //   57: invokevirtual 253	java/io/DataOutputStream:write	([B)V
    //   60: goto -35 -> 25
    //   63: astore 6
    //   65: aload 6
    //   67: invokevirtual 60	java/lang/Exception:printStackTrace	()V
    //   70: aload_1
    //   71: ifnull +7 -> 78
    //   74: aload_1
    //   75: invokevirtual 256	java/io/ByteArrayOutputStream:close	()V
    //   78: aload_2
    //   79: ifnull +7 -> 86
    //   82: aload_2
    //   83: invokevirtual 257	java/io/DataOutputStream:close	()V
    //   86: aconst_null
    //   87: astore 7
    //   89: aload 7
    //   91: areturn
    //   92: aload_1
    //   93: invokevirtual 260	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   96: astore 11
    //   98: aload 11
    //   100: astore 7
    //   102: aload_1
    //   103: ifnull +7 -> 110
    //   106: aload_1
    //   107: invokevirtual 256	java/io/ByteArrayOutputStream:close	()V
    //   110: aload_2
    //   111: ifnull -22 -> 89
    //   114: aload_2
    //   115: invokevirtual 257	java/io/DataOutputStream:close	()V
    //   118: aload 7
    //   120: areturn
    //   121: astore 12
    //   123: aload 12
    //   125: invokevirtual 261	java/io/IOException:printStackTrace	()V
    //   128: aload 7
    //   130: areturn
    //   131: astore_3
    //   132: aconst_null
    //   133: astore_2
    //   134: aconst_null
    //   135: astore_1
    //   136: aload_1
    //   137: ifnull +7 -> 144
    //   140: aload_1
    //   141: invokevirtual 256	java/io/ByteArrayOutputStream:close	()V
    //   144: aload_2
    //   145: ifnull +7 -> 152
    //   148: aload_2
    //   149: invokevirtual 257	java/io/DataOutputStream:close	()V
    //   152: aload_3
    //   153: athrow
    //   154: astore 5
    //   156: aload 5
    //   158: invokevirtual 261	java/io/IOException:printStackTrace	()V
    //   161: goto -17 -> 144
    //   164: astore 4
    //   166: aload 4
    //   168: invokevirtual 261	java/io/IOException:printStackTrace	()V
    //   171: goto -19 -> 152
    //   174: astore 9
    //   176: aload 9
    //   178: invokevirtual 261	java/io/IOException:printStackTrace	()V
    //   181: goto -103 -> 78
    //   184: astore 8
    //   186: aload 8
    //   188: invokevirtual 261	java/io/IOException:printStackTrace	()V
    //   191: goto -105 -> 86
    //   194: astore 13
    //   196: aload 13
    //   198: invokevirtual 261	java/io/IOException:printStackTrace	()V
    //   201: goto -91 -> 110
    //   204: astore_3
    //   205: aconst_null
    //   206: astore_2
    //   207: goto -71 -> 136
    //   210: astore_3
    //   211: goto -75 -> 136
    //   214: astore 6
    //   216: aconst_null
    //   217: astore_2
    //   218: aconst_null
    //   219: astore_1
    //   220: goto -155 -> 65
    //   223: astore 6
    //   225: aconst_null
    //   226: astore_2
    //   227: goto -162 -> 65
    //
    // Exception table:
    //   from	to	target	type
    //   17	25	63	java/lang/Exception
    //   25	60	63	java/lang/Exception
    //   92	98	63	java/lang/Exception
    //   114	118	121	java/io/IOException
    //   0	8	131	finally
    //   140	144	154	java/io/IOException
    //   148	152	164	java/io/IOException
    //   74	78	174	java/io/IOException
    //   82	86	184	java/io/IOException
    //   106	110	194	java/io/IOException
    //   8	17	204	finally
    //   17	25	210	finally
    //   25	60	210	finally
    //   65	70	210	finally
    //   92	98	210	finally
    //   0	8	214	java/lang/Exception
    //   8	17	223	java/lang/Exception
  }

  public static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return new e().b(paramArrayOfByte1, paramArrayOfByte2);
  }

  public static Request b(JceStruct paramJceStruct)
  {
    if (paramJceStruct == null)
      return null;
    Request localRequest = new Request();
    localRequest.head = a(paramJceStruct);
    localRequest.body = c(paramJceStruct);
    return localRequest;
  }

  public static byte[] b(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return new e().a(paramArrayOfByte1, paramArrayOfByte2);
  }

  public static byte[] c(JceStruct paramJceStruct)
  {
    if (paramJceStruct == null)
      return null;
    JceOutputStream localJceOutputStream = new JceOutputStream();
    localJceOutputStream.setServerEncoding("utf-8");
    paramJceStruct.writeTo(localJceOutputStream);
    return localJceOutputStream.toByteArray();
  }

  private static JceStruct d(JceStruct paramJceStruct)
  {
    if (paramJceStruct == null)
      return null;
    String str1 = paramJceStruct.getClass().getName();
    String str2 = str1.substring(0, str1.length() - "Request".length()) + "Response";
    try
    {
      localJceStruct = (JceStruct)Class.forName(str2).newInstance();
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
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.protocol.a
 * JD-Core Version:    0.6.0
 */