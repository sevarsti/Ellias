package com.tencent.qqgamemi.protocol;

import CobraHallQmiProto.TCmdReqHead;
import CobraHallQmiProto.TPkgReqHead;
import android.os.Handler;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.protocol.ProtocolResponse;
import java.lang.ref.WeakReference;

@PluginApi(a=6)
public abstract class QMiProtocolRequest extends BaseProtocolRequest
{
  private static final String y = "QMiProtocolRequest";
  private int z;

  @PluginApi(a=6)
  public QMiProtocolRequest(int paramInt1, Handler paramHandler, int paramInt2, Object[] paramArrayOfObject)
  {
    b(paramInt1);
    this.r = paramArrayOfObject;
    if (paramHandler != null)
    {
      this.o = new WeakReference(paramHandler);
      setMsg(paramInt2);
    }
  }

  public JceStruct a()
  {
    return packageJceStruct(this.r);
  }

  public void a(ProtocolResponse paramProtocolResponse)
  {
    if ((paramProtocolResponse != null) && (paramProtocolResponse.getResultCode() == 0))
    {
      onRequestSuccess(paramProtocolResponse);
      return;
    }
    onRequestFailed(paramProtocolResponse);
  }

  @PluginApi(a=6)
  protected void fillCustomHeader(TPkgReqHead paramTPkgReqHead)
  {
    paramTPkgReqHead.appVer = String.valueOf(QMiJceCommonData.f());
    paramTPkgReqHead.appId = QMiJceCommonData.o;
  }

  @PluginApi(a=7)
  protected void fillCustomeCmdHeader(TCmdReqHead paramTCmdReqHead)
  {
    super.fillCustomeCmdHeader(paramTCmdReqHead);
  }

  @PluginApi(a=6)
  public int getMsg()
  {
    return this.z;
  }

  @PluginApi(a=6)
  public abstract void onRequestFailed(ProtocolResponse paramProtocolResponse);

  @PluginApi(a=6)
  public abstract void onRequestSuccess(ProtocolResponse paramProtocolResponse);

  @PluginApi(a=6)
  protected abstract JceStruct packageJceStruct(Object[] paramArrayOfObject);

  @PluginApi(a=6)
  public void setMsg(int paramInt)
  {
    this.z = paramInt;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.QMiProtocolRequest
 * JD-Core Version:    0.6.0
 */