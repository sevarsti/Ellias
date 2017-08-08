package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import com.tencent.component.annotation.PluginApi;
import java.util.ArrayList;

@PluginApi
public final class TPkgReqHead extends JceStruct
  implements Cloneable
{
  static ArrayList cache_extHead;
  public long appId = 0L;
  public String appVer = "";
  public String channel = "";
  public String coChannel = "";
  public ArrayList extHead = null;
  public short platform = 0;
  public short productform = 0;
  public short protocolVer = 0;
  public String qimei = "";
  public String scrRes = "";
  public String sdkAppId = "";
  public String uid = "";
  public long uin = 0L;
  public String uuid = "";

  static
  {
    if (!TPkgReqHead.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  @PluginApi
  public TPkgReqHead()
  {
  }

  @PluginApi
  public TPkgReqHead(short paramShort1, short paramShort2, String paramString1, long paramLong1, String paramString2, String paramString3, long paramLong2, String paramString4, String paramString5, String paramString6, ArrayList paramArrayList, String paramString7, short paramShort3)
  {
    this.protocolVer = paramShort1;
    this.platform = paramShort2;
    this.channel = paramString1;
    this.appId = paramLong1;
    this.appVer = paramString2;
    this.scrRes = paramString3;
    this.uin = paramLong2;
    this.uuid = paramString4;
    this.uid = paramString5;
    this.coChannel = paramString6;
    this.extHead = paramArrayList;
    this.qimei = paramString7;
    this.productform = paramShort3;
  }

  public String className()
  {
    return "CobraHallQmiProto.TPkgReqHead";
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
    localJceDisplayer.display(this.protocolVer, "protocolVer");
    localJceDisplayer.display(this.platform, "platform");
    localJceDisplayer.display(this.channel, "channel");
    localJceDisplayer.display(this.appId, "appId");
    localJceDisplayer.display(this.appVer, "appVer");
    localJceDisplayer.display(this.scrRes, "scrRes");
    localJceDisplayer.display(this.uin, "uin");
    localJceDisplayer.display(this.uuid, "uuid");
    localJceDisplayer.display(this.uid, "uid");
    localJceDisplayer.display(this.coChannel, "coChannel");
    localJceDisplayer.display(this.extHead, "extHead");
    localJceDisplayer.display(this.qimei, "qimei");
    localJceDisplayer.display(this.productform, "productform");
    localJceDisplayer.display(this.sdkAppId, "sdkAppId");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.protocolVer, true);
    localJceDisplayer.displaySimple(this.platform, true);
    localJceDisplayer.displaySimple(this.channel, true);
    localJceDisplayer.displaySimple(this.appId, true);
    localJceDisplayer.displaySimple(this.appVer, true);
    localJceDisplayer.displaySimple(this.scrRes, true);
    localJceDisplayer.displaySimple(this.uin, true);
    localJceDisplayer.displaySimple(this.uuid, true);
    localJceDisplayer.displaySimple(this.uid, true);
    localJceDisplayer.displaySimple(this.coChannel, true);
    localJceDisplayer.displaySimple(this.extHead, true);
    localJceDisplayer.displaySimple(this.qimei, true);
    localJceDisplayer.displaySimple(this.productform, false);
    localJceDisplayer.displaySimple(this.sdkAppId, false);
  }

  @PluginApi
  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TPkgReqHead localTPkgReqHead;
    do
    {
      return false;
      localTPkgReqHead = (TPkgReqHead)paramObject;
    }
    while ((!JceUtil.equals(this.protocolVer, localTPkgReqHead.protocolVer)) || (!JceUtil.equals(this.platform, localTPkgReqHead.platform)) || (!JceUtil.equals(this.channel, localTPkgReqHead.channel)) || (!JceUtil.equals(this.appId, localTPkgReqHead.appId)) || (!JceUtil.equals(this.appVer, localTPkgReqHead.appVer)) || (!JceUtil.equals(this.scrRes, localTPkgReqHead.scrRes)) || (!JceUtil.equals(this.uin, localTPkgReqHead.uin)) || (!JceUtil.equals(this.uuid, localTPkgReqHead.uuid)) || (!JceUtil.equals(this.uid, localTPkgReqHead.uid)) || (!JceUtil.equals(this.coChannel, localTPkgReqHead.coChannel)) || (!JceUtil.equals(this.extHead, localTPkgReqHead.extHead)) || (!JceUtil.equals(this.qimei, localTPkgReqHead.qimei)) || (!JceUtil.equals(this.productform, localTPkgReqHead.productform)) || (!JceUtil.equals(this.sdkAppId, localTPkgReqHead.sdkAppId)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TPkgReqHead";
  }

  @PluginApi
  public long getAppId()
  {
    return this.appId;
  }

  @PluginApi
  public String getAppVer()
  {
    return this.appVer;
  }

  @PluginApi
  public String getChannel()
  {
    return this.channel;
  }

  @PluginApi
  public String getCoChannel()
  {
    return this.coChannel;
  }

  @PluginApi
  public ArrayList getExtHead()
  {
    return this.extHead;
  }

  @PluginApi
  public short getPlatform()
  {
    return this.platform;
  }

  @PluginApi
  public short getProductform()
  {
    return this.productform;
  }

  @PluginApi
  public short getProtocolVer()
  {
    return this.protocolVer;
  }

  @PluginApi
  public String getQimei()
  {
    return this.qimei;
  }

  @PluginApi
  public String getScrRes()
  {
    return this.scrRes;
  }

  @PluginApi
  public String getUid()
  {
    return this.uid;
  }

  @PluginApi
  public long getUin()
  {
    return this.uin;
  }

  @PluginApi
  public String getUuid()
  {
    return this.uuid;
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
    this.protocolVer = paramJceInputStream.read(this.protocolVer, 0, true);
    this.platform = paramJceInputStream.read(this.platform, 1, true);
    this.channel = paramJceInputStream.readString(2, true);
    this.appId = paramJceInputStream.read(this.appId, 3, true);
    this.appVer = paramJceInputStream.readString(4, true);
    this.scrRes = paramJceInputStream.readString(5, true);
    this.uin = paramJceInputStream.read(this.uin, 6, true);
    this.uuid = paramJceInputStream.readString(7, true);
    this.uid = paramJceInputStream.readString(8, false);
    this.coChannel = paramJceInputStream.readString(9, false);
    if (cache_extHead == null)
    {
      cache_extHead = new ArrayList();
      TPkgReqExtHead localTPkgReqExtHead = new TPkgReqExtHead();
      cache_extHead.add(localTPkgReqExtHead);
    }
    this.extHead = ((ArrayList)paramJceInputStream.read(cache_extHead, 10, false));
    this.qimei = paramJceInputStream.readString(11, false);
    this.productform = paramJceInputStream.read(this.productform, 12, false);
    this.sdkAppId = paramJceInputStream.readString(13, false);
  }

  @PluginApi
  public void setAppId(long paramLong)
  {
    this.appId = paramLong;
  }

  @PluginApi
  public void setAppVer(String paramString)
  {
    this.appVer = paramString;
  }

  @PluginApi
  public void setChannel(String paramString)
  {
    this.channel = paramString;
  }

  @PluginApi
  public void setCoChannel(String paramString)
  {
    this.coChannel = paramString;
  }

  @PluginApi
  public void setExtHead(ArrayList paramArrayList)
  {
    this.extHead = paramArrayList;
  }

  @PluginApi
  public void setPlatform(short paramShort)
  {
    this.platform = paramShort;
  }

  @PluginApi
  public void setProductform(short paramShort)
  {
    this.productform = paramShort;
  }

  @PluginApi
  public void setProtocolVer(short paramShort)
  {
    this.protocolVer = paramShort;
  }

  @PluginApi
  public void setQimei(String paramString)
  {
    this.qimei = paramString;
  }

  @PluginApi
  public void setScrRes(String paramString)
  {
    this.scrRes = paramString;
  }

  @PluginApi
  public void setUid(String paramString)
  {
    this.uid = paramString;
  }

  @PluginApi
  public void setUin(long paramLong)
  {
    this.uin = paramLong;
  }

  @PluginApi
  public void setUuid(String paramString)
  {
    this.uuid = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.protocolVer, 0);
    paramJceOutputStream.write(this.platform, 1);
    paramJceOutputStream.write(this.channel, 2);
    paramJceOutputStream.write(this.appId, 3);
    paramJceOutputStream.write(this.appVer, 4);
    paramJceOutputStream.write(this.scrRes, 5);
    paramJceOutputStream.write(this.uin, 6);
    paramJceOutputStream.write(this.uuid, 7);
    if (this.uid != null)
      paramJceOutputStream.write(this.uid, 8);
    if (this.coChannel != null)
      paramJceOutputStream.write(this.coChannel, 9);
    if (this.extHead != null)
      paramJceOutputStream.write(this.extHead, 10);
    if (this.qimei != null)
      paramJceOutputStream.write(this.qimei, 11);
    paramJceOutputStream.write(this.productform, 12);
    if (this.sdkAppId != null)
      paramJceOutputStream.write(this.sdkAppId, 13);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TPkgReqHead
 * JD-Core Version:    0.6.0
 */