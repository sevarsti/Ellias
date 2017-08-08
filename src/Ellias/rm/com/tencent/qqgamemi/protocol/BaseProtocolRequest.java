package com.tencent.qqgamemi.protocol;

import CobraHallQmiProto.CMDID;
import CobraHallQmiProto.TCmdReq;
import CobraHallQmiProto.TCmdReqHead;
import CobraHallQmiProto.TCmdRsp;
import CobraHallQmiProto.TCmdRspHead;
import CobraHallQmiProto.TPackageReq;
import CobraHallQmiProto.TPackageRsp;
import CobraHallQmiProto.TPkgReqExtHead;
import CobraHallQmiProto.TPkgReqHead;
import android.app.Service;
import android.os.Build.VERSION;
import android.os.Handler;
import android.text.TextUtils;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.net.http.AsyncHttpResult.FailDescription;
import com.tencent.component.protocol.ProtocolRequest;
import com.tencent.component.protocol.ProtocolRequestListener;
import com.tencent.component.protocol.ProtocolResponse;
import com.tencent.component.utils.WupTools;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.QMiService;
import com.tencent.qqgamemi.business.QMiEnvironmentHelper;
import com.tencent.qqgamemi.common.Global.BuildConfig;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.login.QMiLoginManager;
import com.tencent.qqgamemi.root.RootUtil;
import com.tencent.qqgamemi.view.QMiToast;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.net.ssl.SSLPeerUnverifiedException;
import org.apache.http.ConnectionClosedException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;

public abstract class BaseProtocolRequest extends ProtocolRequest
{
  private static final boolean y = true;
  private static final boolean z;
  protected WeakReference o;
  protected ProtocolRequestListener p;
  protected JceStruct q;
  protected Object[] r;
  protected int s;
  protected short t;
  protected byte[] u;
  protected String v;
  protected boolean w = true;
  protected boolean x = false;

  private static int a(Throwable paramThrowable)
  {
    int i = -10013;
    if ((paramThrowable instanceof ClientProtocolException))
      i = -10001;
    do
    {
      return i;
      if ((paramThrowable instanceof SSLPeerUnverifiedException))
        return -10002;
      if ((paramThrowable instanceof NoHttpResponseException))
        return -10003;
      if ((paramThrowable instanceof UnknownHostException))
        return -10004;
      if ((paramThrowable instanceof ConnectionPoolTimeoutException))
        return -10005;
      if ((paramThrowable instanceof ConnectTimeoutException))
        return -10006;
      if ((paramThrowable instanceof IllegalStateException))
        return -10007;
      if ((paramThrowable instanceof SocketException))
        return -10008;
      if ((paramThrowable instanceof SocketTimeoutException))
        return -10009;
      if ((paramThrowable instanceof FileNotFoundException))
        return -10010;
      if ((paramThrowable instanceof ConnectionClosedException))
        return -10011;
      if ((paramThrowable instanceof IOException))
        return -10012;
    }
    while (((paramThrowable instanceof Exception)) || (!(paramThrowable instanceof OutOfMemoryError)));
    return -10014;
  }

  private void a(long paramLong, int paramInt)
  {
    this.j = paramLong;
    this.k = paramInt;
    this.h = System.currentTimeMillis();
  }

  private static ProtocolResponse b(AsyncHttpResult.FailDescription paramFailDescription)
  {
    ProtocolResponse localProtocolResponse = new ProtocolResponse(false);
    switch (paramFailDescription.failType)
    {
    default:
    case 3:
    case 2:
    case 5:
    case 6:
    case 4:
    }
    while (true)
    {
      localProtocolResponse.setResultMsg(ClientCode.a(localProtocolResponse.getResultCode()));
      return localProtocolResponse;
      int i = ((Integer)paramFailDescription.extraInfo).intValue();
      if (i != -10900)
        i = ClientCode.b(i);
      localProtocolResponse.setResultCode(i);
      continue;
      localProtocolResponse.setResultCode(-8);
      localProtocolResponse.setResultMsg((String)paramFailDescription.extraInfo);
      continue;
      localProtocolResponse.setResultCode(-7);
      continue;
      localProtocolResponse.setResultCode(-1);
      continue;
      localProtocolResponse.setResultCode(a((Throwable)paramFailDescription.extraInfo));
    }
  }

  private void b(ProtocolResponse paramProtocolResponse)
  {
    a(paramProtocolResponse);
    ProtocolRequestListener localProtocolRequestListener = this.p;
    if (localProtocolRequestListener != null)
    {
      if (paramProtocolResponse.getResultCode() != 0)
        break label143;
      localProtocolRequestListener.a(this.b, this, paramProtocolResponse);
    }
    while (true)
    {
      a(paramProtocolResponse.getResultCode(), d(), k() + "\n" + paramProtocolResponse.getResultMsg());
      TLog.b("ProtocolRequest", "Receive Response => [seqNo:" + f() + "] " + "[cmd:" + d() + "] resultCode = " + paramProtocolResponse.getResultCode() + " |resultMsg:" + paramProtocolResponse.getResultMsg());
      return;
      label143: if ((paramProtocolResponse.getResultCode() == 104) || (paramProtocolResponse.getResultCode() == 104))
      {
        Service localService = QMiService.a();
        if (localService != null)
        {
          QMiLoginManager.a().a(localService, false);
          QMiToast.a(localService, "身份过期，请重新登录", 1000).show();
        }
      }
      localProtocolRequestListener.b(this.b, this, paramProtocolResponse);
    }
  }

  public JceStruct a()
  {
    return this.q;
  }

  protected ProtocolResponse a(TPackageRsp paramTPackageRsp)
  {
    ProtocolResponse localProtocolResponse = new ProtocolResponse(false);
    Class localClass;
    if ((paramTPackageRsp != null) && (paramTPackageRsp.cmdRsp != null))
    {
      TCmdRspHead localTCmdRspHead = paramTPackageRsp.cmdRsp.cmdRspHead;
      if (localTCmdRspHead != null)
      {
        localProtocolResponse.setResultCode(localTCmdRspHead.cmdResultId);
        String str = localTCmdRspHead.reason;
        if ((TextUtils.isEmpty(str)) && (localTCmdRspHead.cmdResultId != 0))
          str = ClientCode.a(localTCmdRspHead.cmdResultId);
        localProtocolResponse.setResultMsg(str);
        localProtocolResponse.setTimestamp(localTCmdRspHead.timestamp);
        localClass = getResponseClass();
        if (localClass == null);
      }
    }
    while (true)
    {
      try
      {
        localProtocolResponse.setBusiResponse(WupTools.decodeWup(localClass, paramTPackageRsp.cmdRsp.cmdRspBody));
        if (!Global.BuildConfig.a())
          continue;
        TLog.b("ProtocolRequest", "BusiResponse Datas => [seqNo:" + f() + "] " + "[cmd:" + d() + "] resp = " + localProtocolResponse.getBusiResponse());
        return localProtocolResponse;
        localProtocolResponse.setResultCode(-2);
        localProtocolResponse.setResultMsg(ClientCode.a(-2));
      }
      catch (Exception localException)
      {
        TLog.c("ProtocolRequest", localException.getMessage(), localException);
        localProtocolResponse.setResultCode(-2);
        localProtocolResponse.setResultMsg(ClientCode.a(-2));
        continue;
      }
      localProtocolResponse.setResultCode(-2);
      localProtocolResponse.setResultMsg(ClientCode.a(-2));
    }
  }

  protected void a(int paramInt, String paramString1, String paramString2)
  {
    if (paramInt == -1)
      return;
    while (true)
    {
      try
      {
        HashMap localHashMap = new HashMap();
        localHashMap.put(Integer.valueOf(9), Long.valueOf(QMiLoginManager.a().i()));
        localHashMap.put(Integer.valueOf(10), paramString1);
        localHashMap.put(Integer.valueOf(11), Integer.valueOf(paramInt));
        l = this.h - this.e;
        if (l >= 0L)
          continue;
        l = System.currentTimeMillis() - this.e;
        Integer localInteger = Integer.valueOf(12);
        if (l > 0L)
        {
          localHashMap.put(localInteger, Long.valueOf(l));
          localHashMap.put(Integer.valueOf(13), Long.valueOf(this.i));
          localHashMap.put(Integer.valueOf(14), Long.valueOf(this.j));
          localHashMap.put(Integer.valueOf(17), paramString2);
          localHashMap.put(Integer.valueOf(18), Integer.valueOf(f()));
          return;
        }
      }
      catch (Exception localException)
      {
        return;
      }
      long l = 0L;
    }
  }

  public void a(long paramLong, byte[] paramArrayOfByte)
  {
    int i;
    if (paramArrayOfByte != null)
      i = paramArrayOfByte.length;
    while (true)
    {
      a(paramLong, i);
      try
      {
        localTPackageRsp = (TPackageRsp)WupTools.decodeWup(TPackageRsp.class, paramArrayOfByte);
        b(a(localTPackageRsp));
        return;
        i = 0;
      }
      catch (Exception localException)
      {
        while (true)
        {
          LogUtil.e("ProtocolRequest", "decode failed. " + localException.getMessage(), localException);
          TPackageRsp localTPackageRsp = null;
        }
      }
    }
  }

  public void a(AsyncHttpResult.FailDescription paramFailDescription)
  {
    a(0L, 0);
    b(b(paramFailDescription));
  }

  public void a(ProtocolRequestListener paramProtocolRequestListener)
  {
    this.p = paramProtocolRequestListener;
  }

  protected void a(ProtocolResponse paramProtocolResponse)
  {
  }

  public byte[] a(byte[] paramArrayOfByte)
  {
    TLog.b("SYBACCOUNT", "buildRequestDatas start!");
    TPkgReqHead localTPkgReqHead = new TPkgReqHead();
    localTPkgReqHead.protocolVer = QMiJceCommonData.g;
    localTPkgReqHead.platform = (short)QMiJceCommonData.i;
    localTPkgReqHead.appId = 2L;
    localTPkgReqHead.channel = QMiJceCommonData.g();
    localTPkgReqHead.coChannel = QMiJceCommonData.h();
    long l;
    label98: ArrayList localArrayList;
    TPkgReqExtHead localTPkgReqExtHead;
    if (this.l != null)
    {
      localTPkgReqHead.uuid = this.l;
      localTPkgReqHead.uid = "";
      localTPkgReqHead.scrRes = QMiJceCommonData.e();
      if (this.c == -1L)
        break label601;
      l = this.c;
      localTPkgReqHead.uin = l;
      if (localTPkgReqHead.uin == 0L)
        localTPkgReqHead.uin = QMiEnvironmentHelper.a.longValue();
      localTPkgReqHead.qimei = QMiJceCommonData.p;
      localTPkgReqHead.productform = QMiJceCommonData.q;
      localTPkgReqHead.sdkAppId = QMiLoginManager.a().g();
      fillCustomHeader(localTPkgReqHead);
      if (this.x)
      {
        localArrayList = new ArrayList();
        localTPkgReqExtHead = new TPkgReqExtHead();
        localTPkgReqExtHead.model = QMiJceCommonData.r;
        localTPkgReqExtHead.osVer = QMiJceCommonData.s;
        localTPkgReqExtHead.fwVer = "";
        localTPkgReqExtHead.dpi = String.valueOf(QMiJceCommonData.d());
        localTPkgReqExtHead.imei = QMiJceCommonData.l();
        localTPkgReqExtHead.mac = QMiJceCommonData.n();
        localTPkgReqExtHead.sdkVer = String.valueOf(Build.VERSION.SDK_INT);
        localTPkgReqExtHead.imsi = QMiJceCommonData.p();
        localTPkgReqExtHead.cpu = QMiJceCommonData.q();
        localTPkgReqExtHead.mem = QMiJceCommonData.t();
        localTPkgReqExtHead.openGLVer = Integer.toString(QMiJceCommonData.s());
        if (!RootUtil.a().b())
          break label608;
      }
    }
    label601: label608: for (int i = 1; ; i = 0)
    {
      localTPkgReqExtHead.rootFlag = i;
      localArrayList.add(localTPkgReqExtHead);
      localTPkgReqHead.extHead = localArrayList;
      TPackageReq localTPackageReq = new TPackageReq();
      localTPackageReq.pkgReqHead = localTPkgReqHead;
      TCmdReq localTCmdReq = new TCmdReq();
      TCmdReqHead localTCmdReqHead = new TCmdReqHead();
      localTCmdReq.cmdReqBody = new byte[1];
      localTCmdReqHead.sign = new byte[0];
      localTCmdReqHead.encData = new byte[1];
      localTCmdReqHead.cmdId = (short)this.b;
      localTCmdReqHead.lastTimestamp = this.s;
      localTCmdReqHead.appCap = 1;
      if (this.w)
      {
        localTCmdReqHead.svcType = 3;
        localTCmdReqHead.sign = QMiLoginManager.a().k();
        localTCmdReqHead.encData = QMiLoginManager.a().a(0, 0);
        localTCmdReqHead.sybStType = (short)QMiLoginManager.a().d();
        localTCmdReqHead.sybStData = QMiLoginManager.a().e();
        localTCmdReqHead.openId = QMiLoginManager.a().h();
        StringBuilder localStringBuilder = new StringBuilder().append("buildRequestDatas, cmdId=").append(localTCmdReqHead.cmdId).append(", sybStType=").append(localTCmdReqHead.sybStType).append(", sybStData=");
        byte[] arrayOfByte = localTCmdReqHead.sybStData;
        int j = 0;
        if (arrayOfByte != null)
          j = localTCmdReqHead.sybStData.length;
        TLog.b("SYBACCOUNT", j + ", openId=" + localTCmdReqHead.openId);
      }
      fillCustomeCmdHeader(localTCmdReqHead);
      localTCmdReq.cmdReqHead = localTCmdReqHead;
      if (paramArrayOfByte != null)
        localTCmdReq.cmdReqBody = paramArrayOfByte;
      localTPackageReq.cmdReq = localTCmdReq;
      return WupTools.encodeWup(localTPackageReq);
      localTPkgReqHead.uuid = QMiJceCommonData.i();
      break;
      l = QMiJceCommonData.k();
      break label98;
    }
  }

  protected String c(int paramInt)
  {
    String str = "";
    CMDID localCMDID = CMDID.a(paramInt);
    if (localCMDID != null)
      str = localCMDID.toString();
    return str + "(" + paramInt + ")";
  }

  protected abstract void fillCustomHeader(TPkgReqHead paramTPkgReqHead);

  protected void fillCustomeCmdHeader(TCmdReqHead paramTCmdReqHead)
  {
  }

  @PluginApi(a=6)
  public Handler getHandler()
  {
    if (this.o != null)
      return (Handler)this.o.get();
    return null;
  }

  public void i()
  {
    a(0L, 0);
    ProtocolResponse localProtocolResponse = new ProtocolResponse(false);
    localProtocolResponse.setResultCode(-6);
    b(localProtocolResponse);
  }

  public void j()
  {
    a(0L, 0);
    ProtocolResponse localProtocolResponse = new ProtocolResponse(false);
    localProtocolResponse.setResultCode(-9);
    b(localProtocolResponse);
  }

  @PluginApi(a=6)
  protected void sendMessage(int paramInt1, int paramInt2, int paramInt3, Object paramObject)
  {
    Handler localHandler = getHandler();
    if (localHandler != null)
      localHandler.sendMessage(localHandler.obtainMessage(paramInt1, paramInt2, paramInt3, paramObject));
  }

  @PluginApi(a=6)
  protected void sendMessage(int paramInt1, int paramInt2, Object paramObject)
  {
    sendMessage(paramInt1, paramInt2, 0, paramObject);
  }

  @PluginApi(a=6)
  public void setHandler(Handler paramHandler)
  {
    this.o = new WeakReference(paramHandler);
  }

  @PluginApi(a=6)
  public void setNeedDeviceInfo(boolean paramBoolean)
  {
    this.x = paramBoolean;
  }

  @PluginApi(a=6)
  public void setNeedLoginStatus(boolean paramBoolean)
  {
    this.w = paramBoolean;
  }

  @PluginApi(a=6)
  public void setTimestamp(int paramInt)
  {
    this.s = paramInt;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.BaseProtocolRequest
 * JD-Core Version:    0.6.0
 */