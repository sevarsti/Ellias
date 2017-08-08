package com.tencent.android.tpush.service.channel.c;

import android.util.SparseArray;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.service.channel.exception.CommandMappingException;
import com.tencent.android.tpush.service.channel.protocol.TpnsClientReportReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsClientReportRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsConfigReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsConfigRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsGetApListReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsGetApListRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClickReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClickRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsRedirectReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRedirectRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsTokenTagReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsTokenTagRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsTriggerReportReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsTriggerReportRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterRsp;
import java.util.HashMap;

public class d
{
  public static final Integer a = Integer.valueOf(0);
  public static final Integer b = Integer.valueOf(128);
  public static final SparseArray c = new SparseArray();
  public static final HashMap d = new HashMap();

  static
  {
    a(a, Byte.valueOf(1), TpnsPushClientReq.class);
    a(a, Byte.valueOf(2), TpnsGetApListReq.class);
    a(b, Byte.valueOf(2), TpnsGetApListRsp.class);
    a(a, Byte.valueOf(3), TpnsConfigReq.class);
    a(b, Byte.valueOf(3), TpnsConfigRsp.class);
    a(a, Byte.valueOf(4), TpnsRegisterReq.class);
    a(b, Byte.valueOf(4), TpnsRegisterRsp.class);
    a(a, Byte.valueOf(5), TpnsUnregisterReq.class);
    a(b, Byte.valueOf(5), TpnsUnregisterRsp.class);
    a(a, Byte.valueOf(6), TpnsReconnectReq.class);
    a(b, Byte.valueOf(6), TpnsReconnectRsp.class);
    a(a, Byte.valueOf(9), TpnsClientReportReq.class);
    a(b, Byte.valueOf(9), TpnsClientReportRsp.class);
    a(a, Byte.valueOf(10), TpnsRedirectReq.class);
    a(b, Byte.valueOf(10), TpnsRedirectRsp.class);
    a(a, Byte.valueOf(11), TpnsPushVerifyReq.class);
    a(b, Byte.valueOf(11), TpnsPushVerifyRsp.class);
    a(a, Byte.valueOf(13), TpnsTriggerReportReq.class);
    a(b, Byte.valueOf(13), TpnsTriggerReportRsp.class);
    a(a, Byte.valueOf(15), TpnsTokenTagReq.class);
    a(b, Byte.valueOf(15), TpnsTokenTagRsp.class);
    a(a, Byte.valueOf(16), TpnsPushClickReq.class);
    a(b, Byte.valueOf(16), TpnsPushClickRsp.class);
  }

  public static JceStruct a(short paramShort, byte[] paramArrayOfByte)
  {
    Class localClass = a(paramShort);
    if ((localClass != null) && (paramArrayOfByte != null))
      try
      {
        JceStruct localJceStruct = (JceStruct)localClass.newInstance();
        JceInputStream localJceInputStream = new JceInputStream(paramArrayOfByte);
        localJceInputStream.setServerEncoding("UTF-8");
        localJceStruct.readFrom(localJceInputStream);
        return localJceStruct;
      }
      catch (Exception localException)
      {
        throw new CommandMappingException(localException.getMessage(), localException);
      }
    return null;
  }

  public static Class a(short paramShort)
  {
    return (Class)c.get(paramShort);
  }

  public static short a(Class paramClass)
  {
    return ((Integer)d.get(paramClass)).shortValue();
  }

  public static void a(Integer paramInteger, Byte paramByte, Class paramClass)
  {
    c.put(paramInteger.intValue() | paramByte.byteValue(), paramClass);
    d.put(paramClass, Integer.valueOf(paramInteger.intValue() | paramByte.byteValue()));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.c.d
 * JD-Core Version:    0.6.0
 */