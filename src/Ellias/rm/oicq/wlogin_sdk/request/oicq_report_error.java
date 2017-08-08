package oicq.wlogin_sdk.request;

import java.security.SecureRandom;
import oicq.wlogin_sdk.tools.cryptor;

public class oicq_report_error
{
  protected request_global _g;

  protected byte[] encrypt_body(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte1 = new byte[16];
    this._g._SR.nextBytes(arrayOfByte1);
    byte[] arrayOfByte2 = cryptor.encrypt(paramArrayOfByte, 0, paramArrayOfByte.length, arrayOfByte1);
    byte[] arrayOfByte3 = new byte[arrayOfByte2.length + arrayOfByte1.length];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 0, arrayOfByte1.length);
    System.arraycopy(arrayOfByte2, 0, arrayOfByte3, arrayOfByte1.length, arrayOfByte2.length);
    return arrayOfByte3;
  }

  public String get_host(int paramInt)
  {
    String[] arrayOfString = { "log.wtlogin.qq.com", "log1.wtlogin.qq.com" };
    return arrayOfString[java.lang.Math.abs(paramInt % arrayOfString.length)];
  }

  // ERROR //
  public int snd_rcv_req_error(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +8 -> 9
    //   4: aload_1
    //   5: arraylength
    //   6: ifne +5 -> 11
    //   9: iconst_0
    //   10: ireturn
    //   11: new 58	java/lang/StringBuilder
    //   14: dup
    //   15: aload_0
    //   16: invokevirtual 62	java/lang/Object:getClass	()Ljava/lang/Class;
    //   19: invokevirtual 68	java/lang/Class:getName	()Ljava/lang/String;
    //   22: invokestatic 72	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   25: invokespecial 75	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   28: ldc 77
    //   30: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   36: aload_0
    //   37: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   40: getfield 88	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   43: new 58	java/lang/StringBuilder
    //   46: dup
    //   47: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   50: aload_0
    //   51: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   54: getfield 93	oicq/wlogin_sdk/request/request_global:_uin	J
    //   57: invokevirtual 96	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   60: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: iconst_0
    //   64: invokestatic 102	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   67: iconst_0
    //   68: istore_2
    //   69: new 104	java/util/Random
    //   72: dup
    //   73: invokespecial 105	java/util/Random:<init>	()V
    //   76: invokevirtual 109	java/util/Random:nextInt	()I
    //   79: istore_3
    //   80: iload_2
    //   81: iconst_2
    //   82: if_icmplt +77 -> 159
    //   85: iload_2
    //   86: iconst_1
    //   87: if_icmplt +630 -> 717
    //   90: sipush -1000
    //   93: istore 12
    //   95: new 58	java/lang/StringBuilder
    //   98: dup
    //   99: aload_0
    //   100: invokevirtual 62	java/lang/Object:getClass	()Ljava/lang/Class;
    //   103: invokevirtual 68	java/lang/Class:getName	()Ljava/lang/String;
    //   106: invokestatic 72	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   109: invokespecial 75	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   112: ldc 111
    //   114: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: iload 12
    //   119: invokevirtual 114	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   122: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   125: aload_0
    //   126: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   129: getfield 88	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   132: new 58	java/lang/StringBuilder
    //   135: dup
    //   136: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   139: aload_0
    //   140: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   143: getfield 93	oicq/wlogin_sdk/request/request_global:_uin	J
    //   146: invokevirtual 96	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   149: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   152: iconst_1
    //   153: invokestatic 102	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   156: iload 12
    //   158: ireturn
    //   159: new 58	java/lang/StringBuilder
    //   162: dup
    //   163: ldc 116
    //   165: invokespecial 75	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   168: iload_2
    //   169: invokevirtual 114	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   172: ldc 118
    //   174: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: aload_0
    //   181: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   184: getfield 88	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   187: new 58	java/lang/StringBuilder
    //   190: dup
    //   191: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   194: aload_0
    //   195: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   198: getfield 93	oicq/wlogin_sdk/request/request_global:_uin	J
    //   201: invokevirtual 96	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   204: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   207: iconst_0
    //   208: invokestatic 102	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   211: aload_0
    //   212: iload_3
    //   213: invokevirtual 120	oicq/wlogin_sdk/request/oicq_report_error:get_host	(I)Ljava/lang/String;
    //   216: astore 4
    //   218: new 122	java/net/URL
    //   221: dup
    //   222: new 58	java/lang/StringBuilder
    //   225: dup
    //   226: ldc 124
    //   228: invokespecial 75	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   231: aload 4
    //   233: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   236: ldc 126
    //   238: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   241: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   244: invokespecial 127	java/net/URL:<init>	(Ljava/lang/String;)V
    //   247: astore 5
    //   249: new 58	java/lang/StringBuilder
    //   252: dup
    //   253: ldc 129
    //   255: invokespecial 75	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   258: aload 5
    //   260: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   263: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   266: aload_0
    //   267: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   270: getfield 88	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   273: new 58	java/lang/StringBuilder
    //   276: dup
    //   277: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   280: aload_0
    //   281: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   284: getfield 93	oicq/wlogin_sdk/request/request_global:_uin	J
    //   287: invokevirtual 96	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   290: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   293: iconst_0
    //   294: invokestatic 102	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   297: aload 5
    //   299: invokevirtual 136	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   302: checkcast 138	java/net/HttpURLConnection
    //   305: astore 9
    //   307: aload 9
    //   309: ldc 140
    //   311: invokevirtual 143	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   314: aload 9
    //   316: ldc 145
    //   318: ldc 147
    //   320: invokevirtual 151	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   323: aload 9
    //   325: ldc 153
    //   327: ldc 155
    //   329: invokevirtual 151	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   332: aload 9
    //   334: ldc 157
    //   336: new 159	java/lang/Integer
    //   339: dup
    //   340: aload_1
    //   341: arraylength
    //   342: invokespecial 162	java/lang/Integer:<init>	(I)V
    //   345: invokevirtual 163	java/lang/Integer:toString	()Ljava/lang/String;
    //   348: invokevirtual 151	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   351: aload 9
    //   353: aload_0
    //   354: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   357: getfield 167	oicq/wlogin_sdk/request/request_global:_time_out	I
    //   360: invokevirtual 170	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   363: aload 9
    //   365: aload_0
    //   366: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   369: getfield 167	oicq/wlogin_sdk/request/request_global:_time_out	I
    //   372: invokevirtual 173	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   375: aload 9
    //   377: iconst_1
    //   378: invokevirtual 177	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   381: ldc 179
    //   383: aload_0
    //   384: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   387: getfield 88	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   390: new 58	java/lang/StringBuilder
    //   393: dup
    //   394: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   397: aload_0
    //   398: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   401: getfield 93	oicq/wlogin_sdk/request/request_global:_uin	J
    //   404: invokevirtual 96	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   407: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   410: iconst_0
    //   411: invokestatic 102	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   414: aload 9
    //   416: aload_0
    //   417: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   420: getfield 167	oicq/wlogin_sdk/request/request_global:_time_out	I
    //   423: i2l
    //   424: invokestatic 185	oicq/wlogin_sdk/request/http_connect_ontime:connect_ontime	(Ljava/net/HttpURLConnection;J)Z
    //   427: ifne +45 -> 472
    //   430: ldc 187
    //   432: aload_0
    //   433: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   436: getfield 88	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   439: new 58	java/lang/StringBuilder
    //   442: dup
    //   443: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   446: aload_0
    //   447: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   450: getfield 93	oicq/wlogin_sdk/request/request_global:_uin	J
    //   453: invokevirtual 96	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   456: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   459: iconst_1
    //   460: invokestatic 102	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   463: iinc 2 1
    //   466: iinc 3 1
    //   469: goto -389 -> 80
    //   472: ldc 189
    //   474: aload_0
    //   475: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   478: getfield 88	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   481: new 58	java/lang/StringBuilder
    //   484: dup
    //   485: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   488: aload_0
    //   489: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   492: getfield 93	oicq/wlogin_sdk/request/request_global:_uin	J
    //   495: invokevirtual 96	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   498: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   501: iconst_0
    //   502: invokestatic 102	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   505: aload 9
    //   507: invokevirtual 193	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   510: astore 10
    //   512: aload 10
    //   514: aload_1
    //   515: iconst_0
    //   516: aload_1
    //   517: arraylength
    //   518: invokevirtual 199	java/io/OutputStream:write	([BII)V
    //   521: aload 10
    //   523: invokevirtual 202	java/io/OutputStream:flush	()V
    //   526: aload 9
    //   528: invokevirtual 205	java/net/HttpURLConnection:getResponseCode	()I
    //   531: istore 11
    //   533: new 58	java/lang/StringBuilder
    //   536: dup
    //   537: ldc 207
    //   539: invokespecial 75	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   542: iload 11
    //   544: invokevirtual 114	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   547: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   550: aload_0
    //   551: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   554: getfield 88	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   557: new 58	java/lang/StringBuilder
    //   560: dup
    //   561: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   564: aload_0
    //   565: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   568: getfield 93	oicq/wlogin_sdk/request/request_global:_uin	J
    //   571: invokevirtual 96	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   574: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   577: iconst_0
    //   578: invokestatic 102	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;I)V
    //   581: sipush 200
    //   584: iload 11
    //   586: if_icmpeq -501 -> 85
    //   589: new 58	java/lang/StringBuilder
    //   592: dup
    //   593: ldc 209
    //   595: invokespecial 75	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   598: iload 11
    //   600: invokevirtual 114	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   603: ldc 211
    //   605: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   608: aload 9
    //   610: invokevirtual 214	java/net/HttpURLConnection:getResponseMessage	()Ljava/lang/String;
    //   613: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   616: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   619: invokestatic 217	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;)V
    //   622: iinc 2 1
    //   625: iinc 3 1
    //   628: goto -548 -> 80
    //   631: astore 6
    //   633: new 219	java/io/StringWriter
    //   636: dup
    //   637: invokespecial 220	java/io/StringWriter:<init>	()V
    //   640: astore 7
    //   642: new 222	java/io/PrintWriter
    //   645: dup
    //   646: aload 7
    //   648: iconst_1
    //   649: invokespecial 225	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   652: astore 8
    //   654: aload 6
    //   656: aload 8
    //   658: invokevirtual 229	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   661: aload 8
    //   663: invokevirtual 230	java/io/PrintWriter:flush	()V
    //   666: aload 7
    //   668: invokevirtual 231	java/io/StringWriter:flush	()V
    //   671: ldc 233
    //   673: aload 7
    //   675: invokevirtual 234	java/io/StringWriter:toString	()Ljava/lang/String;
    //   678: aload_0
    //   679: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   682: getfield 88	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   685: new 58	java/lang/StringBuilder
    //   688: dup
    //   689: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   692: aload_0
    //   693: getfield 14	oicq/wlogin_sdk/request/oicq_report_error:_g	Loicq/wlogin_sdk/request/request_global;
    //   696: getfield 93	oicq/wlogin_sdk/request/request_global:_uin	J
    //   699: invokevirtual 96	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   702: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   705: invokestatic 238	oicq/wlogin_sdk/tools/util:LOGW	(Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;)V
    //   708: iinc 2 1
    //   711: iinc 3 1
    //   714: goto -634 -> 80
    //   717: iconst_0
    //   718: istore 12
    //   720: goto -625 -> 95
    //   723: astore 6
    //   725: goto -92 -> 633
    //
    // Exception table:
    //   from	to	target	type
    //   218	249	631	java/lang/Exception
    //   249	463	723	java/lang/Exception
    //   472	581	723	java/lang/Exception
    //   589	622	723	java/lang/Exception
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.oicq_report_error
 * JD-Core Version:    0.6.0
 */