package oicq.wlogin_sdk.request;

import java.net.Socket;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

public class request_transport extends oicq_request
{
  public int _req_transport_eext_head_len = 0;
  public int _rsp_transport_eext_head_len = 5;

  public request_transport(request_global paramrequest_global)
  {
    this._cmd = 2066;
    this._sub_cmd = 1;
    this._service_cmd = "wtlogin.trans";
    this._g = paramrequest_global;
  }

  public int get_port(boolean paramBoolean)
  {
    if (paramBoolean);
    return 8080;
  }

  public byte[] get_request_body(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, long paramLong1, long paramLong2, int paramInt)
  {
    if (paramArrayOfByte2 == null)
    {
      this._req_transport_eext_head_len = 14;
      arrayOfByte = new byte[paramArrayOfByte1.length + this._req_transport_eext_head_len];
      if (paramInt == 0)
        util.int8_to_buf(arrayOfByte, 0, 0);
      while (true)
      {
        int i2 = 0 + 1;
        util.int16_to_buf(arrayOfByte, i2, paramArrayOfByte1.length);
        int i3 = i2 + 2;
        util.int32_to_buf(arrayOfByte, i3, (int)paramLong1);
        int i4 = i3 + 4;
        util.int64_to_buf32(arrayOfByte, i4, paramLong2);
        int i5 = i4 + 4;
        util.int16_to_buf(arrayOfByte, i5, 0);
        int i6 = i5 + 2;
        util.int8_to_buf(arrayOfByte, i6, 0);
        System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, i6 + 1, paramArrayOfByte1.length);
        (14 + paramArrayOfByte1.length);
        util.LOGD("request_transport.get_request_body:", util.buf_to_string(arrayOfByte));
        return encrypt_body(arrayOfByte);
        util.int8_to_buf(arrayOfByte, 0, 3);
      }
    }
    this._req_transport_eext_head_len = (1 + (13 + paramArrayOfByte2.length));
    byte[] arrayOfByte = new byte[paramArrayOfByte1.length + this._req_transport_eext_head_len];
    if (paramInt == 0)
      util.int8_to_buf(arrayOfByte, 0, 1);
    while (true)
    {
      int i = 0 + 1;
      util.int16_to_buf(arrayOfByte, i, paramArrayOfByte1.length);
      int j = i + 2;
      util.int32_to_buf(arrayOfByte, j, (int)paramLong1);
      int k = j + 4;
      util.int64_to_buf32(arrayOfByte, k, paramLong2);
      int m = k + 4;
      util.int16_to_buf(arrayOfByte, m, paramArrayOfByte2.length);
      System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, m + 2, paramArrayOfByte2.length);
      int n = 13 + paramArrayOfByte2.length;
      util.int8_to_buf(arrayOfByte, n, 0);
      int i1 = n + 1;
      System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, i1, paramArrayOfByte1.length);
      (i1 + paramArrayOfByte1.length);
      util.LOGD("request_transport.get_request_body:", util.buf_to_string(arrayOfByte));
      break;
      util.int8_to_buf(arrayOfByte, 0, 2);
    }
  }

  byte[] get_request_msf_head(long paramLong1, long paramLong2, byte[] paramArrayOfByte1, long paramLong3, long paramLong4, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, int paramInt, long paramLong5, byte[] paramArrayOfByte5)
  {
    if (paramArrayOfByte2 == null)
      paramArrayOfByte2 = new byte[0];
    byte[] arrayOfByte = new byte[4 + (4 + (4 + (1 + (4 + (4 + (4 + (16 + (4 + (16 + paramArrayOfByte1.length))) + paramArrayOfByte2.length) + paramArrayOfByte3.length) + paramArrayOfByte4.length))) + paramArrayOfByte5.length)];
    util.int64_to_buf32(arrayOfByte, 0, paramLong1 + arrayOfByte.length);
    int i = 0 + 4;
    util.int64_to_buf32(arrayOfByte, i, -4 + (-4 + arrayOfByte.length));
    int j = i + 4;
    util.int64_to_buf32(arrayOfByte, j, paramLong2);
    int k = j + 4;
    util.int32_to_buf(arrayOfByte, k, 4 + paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, k + 4, paramArrayOfByte1.length);
    int m = 16 + paramArrayOfByte1.length;
    util.int64_to_buf32(arrayOfByte, m, paramLong3);
    int n = m + 4;
    util.int64_to_buf32(arrayOfByte, n, paramLong4);
    int i1 = n + 16;
    util.int32_to_buf(arrayOfByte, i1, 4 + paramArrayOfByte2.length);
    int i2 = i1 + 4;
    int i3 = paramArrayOfByte2.length;
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, i2, i3);
    int i4 = i2 + paramArrayOfByte2.length;
    util.int32_to_buf(arrayOfByte, i4, 4 + paramArrayOfByte3.length);
    int i5 = i4 + 4;
    System.arraycopy(paramArrayOfByte3, 0, arrayOfByte, i5, paramArrayOfByte3.length);
    int i6 = i5 + paramArrayOfByte3.length;
    util.int32_to_buf(arrayOfByte, i6, 4 + paramArrayOfByte4.length);
    int i7 = i6 + 4;
    System.arraycopy(paramArrayOfByte4, 0, arrayOfByte, i7, paramArrayOfByte4.length);
    int i8 = i7 + paramArrayOfByte4.length;
    util.int8_to_buf(arrayOfByte, i8, paramInt);
    int i9 = i8 + 1;
    util.int64_to_buf32(arrayOfByte, i9, paramLong5);
    int i10 = i9 + 4;
    util.int32_to_buf(arrayOfByte, i10, 4 + paramArrayOfByte5.length);
    int i11 = i10 + 4;
    System.arraycopy(paramArrayOfByte5, 0, arrayOfByte, i11, paramArrayOfByte5.length);
    int i12 = i11 + paramArrayOfByte5.length;
    util.int64_to_buf32(arrayOfByte, i12, 4L + paramLong1);
    (i12 + 4);
    return arrayOfByte;
  }

  public int get_response(byte[] paramArrayOfByte, int paramInt, TransReqContext paramTransReqContext)
  {
    if (paramInt <= 2 + this._rsp_head_len)
      return -1009;
    this._rsp_body_len = (-2 + (paramInt - this._rsp_head_len));
    set_buf(paramArrayOfByte, paramInt);
    int i = decrypt_body(this._buf, 1 + this._rsp_head_len, this._rsp_body_len, this._g._rand_key);
    if (i < 0)
      return i;
    return get_response_body(this._buf, 1 + this._rsp_head_len, this._rsp_body_len, paramTransReqContext);
  }

  public int get_response_body(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramInt2 < this._rsp_transport_eext_head_len)
      return -1009;
    int i = get_response_ret_code(paramArrayOfByte, paramInt1);
    set_err_msg(null);
    util.LOGD(getClass().getName(), "type=" + i);
    switch (i)
    {
    case 0:
    }
    return i;
  }

  public int get_response_body(byte[] paramArrayOfByte, int paramInt1, int paramInt2, TransReqContext paramTransReqContext)
  {
    if (paramInt2 < this._rsp_transport_eext_head_len)
      return -1009;
    int i = get_response_ret_code(paramArrayOfByte, paramInt1);
    set_err_msg(null);
    util.LOGD(getClass().getName(), "type=" + i);
    switch (i)
    {
    default:
    case 0:
    }
    while (true)
    {
      return i;
      int j = paramInt1 + this._rsp_transport_eext_head_len;
      byte[] arrayOfByte = new byte[paramInt2 - this._rsp_transport_eext_head_len];
      System.arraycopy(paramArrayOfByte, j, arrayOfByte, 0, arrayOfByte.length);
      paramTransReqContext.set_body(arrayOfByte);
    }
  }

  public Socket get_sk()
  {
    if (this._g._transport_sk != null)
      util.LOGD("_transport_sk", "_transport_sk" + this._g._transport_sk.toString());
    while (true)
    {
      return this._g._transport_sk;
      util.LOGD("_transport_sk", "_transport_sk null");
    }
  }

  public int make_request(long paramLong1, TransReqContext paramTransReqContext, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, long paramLong2, long paramLong3)
  {
    monitorenter;
    try
    {
      int i = this._g._app_client_version;
      byte[] arrayOfByte1 = paramTransReqContext._body;
      long l = System.currentTimeMillis() / 1000L + request_global._l_init_time;
      Object localObject2;
      int j;
      if (arrayOfByte1 == null)
      {
        localObject2 = new byte[0];
        j = 0;
        if (localObject2 != null)
        {
          int k = localObject2.length;
          j = 0;
          if (k > 0)
          {
            byte[] arrayOfByte2 = get_request_body(localObject2, paramArrayOfByte1, paramLong2, paramLong3, 0);
            get_request(this._default_client_version, this._cmd, this._default_client_seq, paramLong1, this._default_ext_retry, this._default_ext_type, i, this._default_ext_instance, arrayOfByte2);
            j = snd_rcv_req(String.valueOf(paramLong1));
            if (j == 0)
              break label232;
          }
        }
      }
      while (true)
      {
        util.LOGD("request_transport rsp: ret=", new Integer(j).toString());
        return j;
        if (paramArrayOfByte1 == null)
        {
          byte[] arrayOfByte5 = new byte[4 + arrayOfByte1.length];
          util.int64_to_buf32(arrayOfByte5, 0, l);
          System.arraycopy(arrayOfByte1, 0, arrayOfByte5, 4, arrayOfByte1.length);
          localObject2 = arrayOfByte5;
          break;
        }
        byte[] arrayOfByte4 = new byte[4 + arrayOfByte1.length];
        util.int64_to_buf32(arrayOfByte4, 0, l);
        System.arraycopy(arrayOfByte1, 0, arrayOfByte4, 4, arrayOfByte1.length);
        localObject2 = cryptor.encrypt(arrayOfByte4, 0, arrayOfByte4.length, paramArrayOfByte2);
        break;
        label232: j = get_response(this._rsp_buf, this._rsp_buf.length, paramTransReqContext);
        if ((j != 0) || (paramArrayOfByte1 == null))
          continue;
        byte[] arrayOfByte3 = paramTransReqContext.get_body();
        paramTransReqContext.set_body(cryptor.decrypt(arrayOfByte3, 0, arrayOfByte3.length, paramArrayOfByte2));
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  // ERROR //
  public int make_request(long paramLong1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, long paramLong2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 32	oicq/wlogin_sdk/request/request_transport:_g	Loicq/wlogin_sdk/request/request_global;
    //   6: getfield 166	oicq/wlogin_sdk/request/request_global:_app_client_version	I
    //   9: istore 9
    //   11: invokestatic 173	java/lang/System:currentTimeMillis	()J
    //   14: ldc2_w 174
    //   17: ldiv
    //   18: getstatic 179	oicq/wlogin_sdk/request/request_global:_l_init_time	J
    //   21: ladd
    //   22: lstore 10
    //   24: aload_0
    //   25: getfield 32	oicq/wlogin_sdk/request/request_transport:_g	Loicq/wlogin_sdk/request/request_global;
    //   28: getfield 243	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   31: getstatic 248	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   34: new 202	java/lang/String
    //   37: dup
    //   38: aload_0
    //   39: getfield 32	oicq/wlogin_sdk/request/request_transport:_g	Loicq/wlogin_sdk/request/request_global;
    //   42: getfield 251	oicq/wlogin_sdk/request/request_global:_apk_v	[B
    //   45: invokespecial 253	java/lang/String:<init>	([B)V
    //   48: ldc 255
    //   50: aload_0
    //   51: getfield 32	oicq/wlogin_sdk/request/request_transport:_g	Loicq/wlogin_sdk/request/request_global;
    //   54: getfield 259	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   57: invokestatic 263	oicq/wlogin_sdk/tools/util:get_ksid	(Landroid/content/Context;)[B
    //   60: invokestatic 64	oicq/wlogin_sdk/tools/util:buf_to_string	([B)Ljava/lang/String;
    //   63: new 202	java/lang/String
    //   66: dup
    //   67: aload_0
    //   68: getfield 32	oicq/wlogin_sdk/request/request_transport:_g	Loicq/wlogin_sdk/request/request_global;
    //   71: getfield 266	oicq/wlogin_sdk/request/request_global:_apk_id	[B
    //   74: invokespecial 253	java/lang/String:<init>	([B)V
    //   77: new 202	java/lang/String
    //   80: dup
    //   81: aload_0
    //   82: getfield 32	oicq/wlogin_sdk/request/request_transport:_g	Loicq/wlogin_sdk/request/request_global;
    //   85: getfield 269	oicq/wlogin_sdk/request/request_global:_device	[B
    //   88: invokespecial 253	java/lang/String:<init>	([B)V
    //   91: new 202	java/lang/String
    //   94: dup
    //   95: aload_0
    //   96: getfield 32	oicq/wlogin_sdk/request/request_transport:_g	Loicq/wlogin_sdk/request/request_global;
    //   99: getfield 272	oicq/wlogin_sdk/request/request_global:_apk_sig	[B
    //   102: invokespecial 253	java/lang/String:<init>	([B)V
    //   105: invokevirtual 278	oicq/wlogin_sdk/report/report_t1:commit	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   108: ldc_w 280
    //   111: aload_0
    //   112: getfield 32	oicq/wlogin_sdk/request/request_transport:_g	Loicq/wlogin_sdk/request/request_global;
    //   115: getfield 243	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   118: invokevirtual 284	oicq/wlogin_sdk/report/report_t1:toJasonObj	()Lorg/json/JSONObject;
    //   121: iconst_4
    //   122: invokevirtual 289	org/json/JSONObject:toString	(I)Ljava/lang/String;
    //   125: invokestatic 68	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;Ljava/lang/String;)V
    //   128: aload_0
    //   129: getfield 32	oicq/wlogin_sdk/request/request_transport:_g	Loicq/wlogin_sdk/request/request_global;
    //   132: getfield 243	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   135: invokevirtual 284	oicq/wlogin_sdk/report/report_t1:toJasonObj	()Lorg/json/JSONObject;
    //   138: invokevirtual 290	org/json/JSONObject:toString	()Ljava/lang/String;
    //   141: invokevirtual 293	java/lang/String:getBytes	()[B
    //   144: astore 13
    //   146: aload 13
    //   148: ifnonnull +148 -> 296
    //   151: iconst_0
    //   152: newarray byte
    //   154: astore 14
    //   156: aload_0
    //   157: getfield 32	oicq/wlogin_sdk/request/request_transport:_g	Loicq/wlogin_sdk/request/request_global;
    //   160: getfield 259	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   163: invokestatic 299	oicq/wlogin_sdk/report/report_t:delete_file	(Landroid/content/Context;)V
    //   166: iconst_0
    //   167: istore 15
    //   169: aload 14
    //   171: ifnull +80 -> 251
    //   174: aload 14
    //   176: arraylength
    //   177: istore 16
    //   179: iconst_0
    //   180: istore 15
    //   182: iload 16
    //   184: ifle +67 -> 251
    //   187: aload_0
    //   188: aload 14
    //   190: aload 4
    //   192: lload 6
    //   194: ldc2_w 300
    //   197: iconst_0
    //   198: invokevirtual 181	oicq/wlogin_sdk/request/request_transport:get_request_body	([B[BJJI)[B
    //   201: astore 17
    //   203: aload_0
    //   204: aload_0
    //   205: getfield 184	oicq/wlogin_sdk/request/request_transport:_default_client_version	I
    //   208: aload_0
    //   209: getfield 19	oicq/wlogin_sdk/request/request_transport:_cmd	I
    //   212: aload_0
    //   213: getfield 187	oicq/wlogin_sdk/request/request_transport:_default_client_seq	I
    //   216: lload_1
    //   217: aload_0
    //   218: getfield 190	oicq/wlogin_sdk/request/request_transport:_default_ext_retry	I
    //   221: aload_0
    //   222: getfield 193	oicq/wlogin_sdk/request/request_transport:_default_ext_type	I
    //   225: iload 9
    //   227: aload_0
    //   228: getfield 196	oicq/wlogin_sdk/request/request_transport:_default_ext_instance	I
    //   231: aload 17
    //   233: invokevirtual 200	oicq/wlogin_sdk/request/request_transport:get_request	(IIIJIIII[B)V
    //   236: aload_0
    //   237: lload_1
    //   238: invokestatic 206	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   241: invokevirtual 210	oicq/wlogin_sdk/request/request_transport:snd_rcv_req	(Ljava/lang/String;)I
    //   244: istore 15
    //   246: iload 15
    //   248: ifeq +119 -> 367
    //   251: ldc 212
    //   253: new 214	java/lang/Integer
    //   256: dup
    //   257: iload 15
    //   259: invokespecial 217	java/lang/Integer:<init>	(I)V
    //   262: invokevirtual 218	java/lang/Integer:toString	()Ljava/lang/String;
    //   265: invokestatic 68	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;Ljava/lang/String;)V
    //   268: iload 15
    //   270: ifeq +115 -> 385
    //   273: aload_0
    //   274: getfield 32	oicq/wlogin_sdk/request/request_transport:_g	Loicq/wlogin_sdk/request/request_global;
    //   277: getfield 243	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   280: aload_0
    //   281: getfield 32	oicq/wlogin_sdk/request/request_transport:_g	Loicq/wlogin_sdk/request/request_global;
    //   284: getfield 259	oicq/wlogin_sdk/request/request_global:_context	Landroid/content/Context;
    //   287: invokestatic 305	oicq/wlogin_sdk/report/report_t:write_tofile	(Loicq/wlogin_sdk/report/report_t1;Landroid/content/Context;)I
    //   290: pop
    //   291: aload_0
    //   292: monitorexit
    //   293: iload 15
    //   295: ireturn
    //   296: bipush 8
    //   298: aload 13
    //   300: arraylength
    //   301: iadd
    //   302: newarray byte
    //   304: astore 19
    //   306: aload 19
    //   308: iconst_0
    //   309: lload 10
    //   311: invokestatic 52	oicq/wlogin_sdk/tools/util:int64_to_buf32	([BIJ)V
    //   314: aload 19
    //   316: iconst_4
    //   317: iconst_0
    //   318: invokestatic 42	oicq/wlogin_sdk/tools/util:int8_to_buf	([BII)V
    //   321: aload 19
    //   323: iconst_5
    //   324: iconst_0
    //   325: invokestatic 42	oicq/wlogin_sdk/tools/util:int8_to_buf	([BII)V
    //   328: aload 19
    //   330: bipush 6
    //   332: aload 13
    //   334: arraylength
    //   335: invokestatic 45	oicq/wlogin_sdk/tools/util:int16_to_buf	([BII)V
    //   338: aload 13
    //   340: iconst_0
    //   341: aload 19
    //   343: bipush 8
    //   345: aload 13
    //   347: arraylength
    //   348: invokestatic 58	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   351: aload 19
    //   353: iconst_0
    //   354: aload 19
    //   356: arraylength
    //   357: aload 5
    //   359: invokestatic 224	oicq/wlogin_sdk/tools/cryptor:encrypt	([BII[B)[B
    //   362: astore 14
    //   364: goto -208 -> 156
    //   367: aload_0
    //   368: aload_0
    //   369: getfield 227	oicq/wlogin_sdk/request/request_transport:_rsp_buf	[B
    //   372: aload_0
    //   373: getfield 227	oicq/wlogin_sdk/request/request_transport:_rsp_buf	[B
    //   376: arraylength
    //   377: invokevirtual 307	oicq/wlogin_sdk/request/request_transport:get_response	([BI)I
    //   380: istore 15
    //   382: goto -131 -> 251
    //   385: aload_0
    //   386: getfield 32	oicq/wlogin_sdk/request/request_transport:_g	Loicq/wlogin_sdk/request/request_global;
    //   389: getfield 243	oicq/wlogin_sdk/request/request_global:_rt1	Loicq/wlogin_sdk/report/report_t1;
    //   392: invokevirtual 310	oicq/wlogin_sdk/report/report_t1:clear_t2	()V
    //   395: goto -104 -> 291
    //   398: astore 8
    //   400: aload_0
    //   401: monitorexit
    //   402: aload 8
    //   404: athrow
    //   405: astore 12
    //   407: goto -279 -> 128
    //
    // Exception table:
    //   from	to	target	type
    //   2	108	398	finally
    //   108	128	398	finally
    //   128	146	398	finally
    //   151	156	398	finally
    //   156	166	398	finally
    //   174	179	398	finally
    //   187	246	398	finally
    //   251	268	398	finally
    //   273	291	398	finally
    //   296	364	398	finally
    //   367	382	398	finally
    //   385	395	398	finally
    //   108	128	405	java/lang/Exception
  }

  public int make_request_msf(long paramLong1, TransReqContext paramTransReqContext, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, long paramLong2, long paramLong3)
  {
    monitorenter;
    try
    {
      int i = this._g._app_client_version;
      long l1 = paramTransReqContext._body.length;
      int j = this._msf_seq;
      this._msf_seq = (j + 1);
      byte[] arrayOfByte1 = get_request_msf_head(l1, j, new Long(paramLong1).toString().getBytes(), paramLong2, paramLong2, paramArrayOfByte3, new String("wtlogin_conn_trans").getBytes(), new byte[8], 0, 0L, this._g._IMEI);
      byte[] arrayOfByte2 = paramTransReqContext._body;
      long l2 = System.currentTimeMillis() / 1000L + request_global._l_init_time;
      Object localObject2;
      int k;
      if (arrayOfByte2 == null)
      {
        localObject2 = new byte[0];
        k = 0;
        if (localObject2 != null)
        {
          int m = localObject2.length;
          k = 0;
          if (m > 0)
          {
            byte[] arrayOfByte3 = get_request_body(localObject2, paramArrayOfByte1, paramLong2, paramLong3, 1);
            get_request(this._default_client_version, this._cmd, this._default_client_seq, paramLong1, this._default_ext_retry, this._default_ext_type, i, this._default_ext_instance, arrayOfByte3);
            k = snd_rcv_req(String.valueOf(paramLong1));
            if (k == 0)
              break label351;
          }
        }
      }
      while (true)
      {
        util.LOGD("request_transport rsp: ret=", new Integer(k).toString());
        return k;
        if (paramArrayOfByte1 == null)
        {
          byte[] arrayOfByte6 = new byte[4 + arrayOfByte1.length + arrayOfByte2.length];
          util.int64_to_buf32(arrayOfByte6, 0, l2);
          System.arraycopy(arrayOfByte1, 0, arrayOfByte6, 4, arrayOfByte1.length);
          System.arraycopy(arrayOfByte2, 0, arrayOfByte6, 4 + arrayOfByte1.length, arrayOfByte2.length);
          localObject2 = arrayOfByte6;
          break;
        }
        byte[] arrayOfByte5 = new byte[4 + arrayOfByte1.length + arrayOfByte2.length];
        util.int64_to_buf32(arrayOfByte5, 0, l2);
        System.arraycopy(arrayOfByte1, 0, arrayOfByte5, 4, arrayOfByte1.length);
        System.arraycopy(arrayOfByte2, 0, arrayOfByte5, 4 + arrayOfByte1.length, arrayOfByte2.length);
        localObject2 = cryptor.encrypt(arrayOfByte5, 0, arrayOfByte5.length, paramArrayOfByte2);
        break;
        label351: k = get_response(this._rsp_buf, this._rsp_buf.length, paramTransReqContext);
        if ((k != 0) || (paramArrayOfByte1 == null))
          continue;
        byte[] arrayOfByte4 = paramTransReqContext.get_body();
        paramTransReqContext.set_body(cryptor.decrypt(arrayOfByte4, 0, arrayOfByte4.length, paramArrayOfByte2));
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public void set_sk(Socket paramSocket)
  {
    this._g._transport_sk = paramSocket;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.request_transport
 * JD-Core Version:    0.6.0
 */