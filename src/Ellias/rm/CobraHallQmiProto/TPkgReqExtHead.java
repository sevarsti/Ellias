package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import com.tencent.component.annotation.PluginApi;

public final class TPkgReqExtHead extends JceStruct
  implements Cloneable
{
  public String cpu = "";
  public String dpi = "";
  public String fwVer = "";
  public String imei = "";
  public String imsi = "";
  public String mac = "";
  public String mem = "";
  public String model = "";
  public String openGLVer = "";
  public String osVer = "";
  public int rootFlag = 0;
  public String sdkVer = "";

  static
  {
    if (!TPkgReqExtHead.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  @PluginApi
  public TPkgReqExtHead()
  {
  }

  @PluginApi
  public TPkgReqExtHead(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10)
  {
    this.model = paramString1;
    this.osVer = paramString2;
    this.sdkVer = paramString3;
    this.fwVer = paramString4;
    this.dpi = paramString5;
    this.imei = paramString6;
    this.mac = paramString7;
    this.imsi = paramString8;
    this.cpu = paramString9;
    this.mem = paramString10;
    this.openGLVer = this.openGLVer;
    this.rootFlag = this.rootFlag;
  }

  public String className()
  {
    return "CobraHallQmiProto.TPkgReqExtHead";
  }

  public Object clone()
  {
    try
    {
      Object localObject2 = super.clone();
      localObject1 = localObject2;
      return localObject1;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      boolean bool;
      do
      {
        bool = $assertionsDisabled;
        Object localObject1 = null;
      }
      while (bool);
    }
    throw new AssertionError();
  }

  public void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.model, "model");
    localJceDisplayer.display(this.osVer, "osVer");
    localJceDisplayer.display(this.sdkVer, "sdkVer");
    localJceDisplayer.display(this.fwVer, "fwVer");
    localJceDisplayer.display(this.dpi, "dpi");
    localJceDisplayer.display(this.imei, "imei");
    localJceDisplayer.display(this.mac, "mac");
    localJceDisplayer.display(this.imsi, "imsi");
    localJceDisplayer.display(this.cpu, "cpu");
    localJceDisplayer.display(this.mem, "mem");
    localJceDisplayer.display(this.openGLVer, "openGLVer");
    localJceDisplayer.display(this.rootFlag, "rootFlag");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.model, true);
    localJceDisplayer.displaySimple(this.osVer, true);
    localJceDisplayer.displaySimple(this.sdkVer, true);
    localJceDisplayer.displaySimple(this.fwVer, true);
    localJceDisplayer.displaySimple(this.dpi, true);
    localJceDisplayer.displaySimple(this.imei, true);
    localJceDisplayer.displaySimple(this.mac, true);
    localJceDisplayer.displaySimple(this.imsi, true);
    localJceDisplayer.displaySimple(this.cpu, true);
    localJceDisplayer.displaySimple(this.mem, true);
    localJceDisplayer.displaySimple(this.openGLVer, true);
    localJceDisplayer.displaySimple(this.rootFlag, false);
  }

  @PluginApi
  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TPkgReqExtHead localTPkgReqExtHead;
    do
    {
      return false;
      localTPkgReqExtHead = (TPkgReqExtHead)paramObject;
    }
    while ((!JceUtil.equals(this.model, localTPkgReqExtHead.model)) || (!JceUtil.equals(this.osVer, localTPkgReqExtHead.osVer)) || (!JceUtil.equals(this.sdkVer, localTPkgReqExtHead.sdkVer)) || (!JceUtil.equals(this.fwVer, localTPkgReqExtHead.fwVer)) || (!JceUtil.equals(this.dpi, localTPkgReqExtHead.dpi)) || (!JceUtil.equals(this.imei, localTPkgReqExtHead.imei)) || (!JceUtil.equals(this.mac, localTPkgReqExtHead.mac)) || (!JceUtil.equals(this.imsi, localTPkgReqExtHead.imsi)) || (!JceUtil.equals(this.cpu, localTPkgReqExtHead.cpu)) || (!JceUtil.equals(this.mem, localTPkgReqExtHead.mem)) || (!JceUtil.equals(this.openGLVer, localTPkgReqExtHead.openGLVer)) || (!JceUtil.equals(this.rootFlag, localTPkgReqExtHead.rootFlag)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TPkgReqExtHead";
  }

  @PluginApi
  public String getCpu()
  {
    return this.cpu;
  }

  @PluginApi
  public String getDpi()
  {
    return this.dpi;
  }

  @PluginApi
  public String getFwVer()
  {
    return this.fwVer;
  }

  @PluginApi
  public String getImei()
  {
    return this.imei;
  }

  @PluginApi
  public String getImsi()
  {
    return this.imsi;
  }

  @PluginApi
  public String getMac()
  {
    return this.mac;
  }

  @PluginApi
  public String getMem()
  {
    return this.mem;
  }

  @PluginApi
  public String getModel()
  {
    return this.model;
  }

  @PluginApi
  public String getOpenGLVer()
  {
    return this.openGLVer;
  }

  @PluginApi
  public String getOsVer()
  {
    return this.osVer;
  }

  @PluginApi
  public int getRootFlag()
  {
    return this.rootFlag;
  }

  @PluginApi
  public String getSdkVer()
  {
    return this.sdkVer;
  }

  @PluginApi
  public int hashCode()
  {
    try
    {
      throw new Exception("Need define key first!");
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }

  public void readFrom(JceInputStream paramJceInputStream)
  {
    this.model = paramJceInputStream.readString(0, true);
    this.osVer = paramJceInputStream.readString(1, false);
    this.sdkVer = paramJceInputStream.readString(2, false);
    this.fwVer = paramJceInputStream.readString(3, false);
    this.dpi = paramJceInputStream.readString(4, false);
    this.imei = paramJceInputStream.readString(5, false);
    this.mac = paramJceInputStream.readString(6, false);
    this.imsi = paramJceInputStream.readString(7, false);
    this.cpu = paramJceInputStream.readString(8, false);
    this.mem = paramJceInputStream.readString(9, false);
    this.openGLVer = paramJceInputStream.readString(10, false);
    this.rootFlag = paramJceInputStream.read(this.rootFlag, 11, false);
  }

  @PluginApi
  public void setCpu(String paramString)
  {
    this.cpu = paramString;
  }

  @PluginApi
  public void setDpi(String paramString)
  {
    this.dpi = paramString;
  }

  @PluginApi
  public void setFwVer(String paramString)
  {
    this.fwVer = paramString;
  }

  @PluginApi
  public void setImei(String paramString)
  {
    this.imei = paramString;
  }

  @PluginApi
  public void setImsi(String paramString)
  {
    this.imsi = paramString;
  }

  @PluginApi
  public void setMac(String paramString)
  {
    this.mac = paramString;
  }

  @PluginApi
  public void setMem(String paramString)
  {
    this.mem = paramString;
  }

  @PluginApi
  public void setModel(String paramString)
  {
    this.model = paramString;
  }

  @PluginApi
  public void setOpenGLVer(String paramString)
  {
    this.openGLVer = paramString;
  }

  @PluginApi
  public void setOsVer(String paramString)
  {
    this.osVer = paramString;
  }

  @PluginApi
  public void setRootFlag(int paramInt)
  {
    this.rootFlag = paramInt;
  }

  @PluginApi
  public void setSdkVer(String paramString)
  {
    this.sdkVer = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.model, 0);
    if (this.osVer != null)
      paramJceOutputStream.write(this.osVer, 1);
    if (this.sdkVer != null)
      paramJceOutputStream.write(this.sdkVer, 2);
    if (this.fwVer != null)
      paramJceOutputStream.write(this.fwVer, 3);
    if (this.dpi != null)
      paramJceOutputStream.write(this.dpi, 4);
    if (this.imei != null)
      paramJceOutputStream.write(this.imei, 5);
    if (this.mac != null)
      paramJceOutputStream.write(this.mac, 6);
    if (this.imsi != null)
      paramJceOutputStream.write(this.imsi, 7);
    if (this.cpu != null)
      paramJceOutputStream.write(this.cpu, 8);
    if (this.mem != null)
      paramJceOutputStream.write(this.mem, 9);
    if (this.openGLVer != null)
      paramJceOutputStream.write(this.openGLVer, 10);
    paramJceOutputStream.write(this.rootFlag, 11);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TPkgReqExtHead
 * JD-Core Version:    0.6.0
 */