package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TBodyGetSdkSybAccessTokenReq extends JceStruct
  implements Cloneable
{
  static byte[] cache_accessToken;
  public byte[] accessToken = null;
  public String appId = "";
  public String openId = "";
  public int tokenType = 0;

  static
  {
    if (!TBodyGetSdkSybAccessTokenReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyGetSdkSybAccessTokenReq()
  {
  }

  public TBodyGetSdkSybAccessTokenReq(String paramString1, String paramString2, byte[] paramArrayOfByte, int paramInt)
  {
    this.appId = paramString1;
    this.openId = paramString2;
    this.accessToken = paramArrayOfByte;
    this.tokenType = paramInt;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyGetSdkSybAccessTokenReq";
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
    localJceDisplayer.display(this.appId, "appId");
    localJceDisplayer.display(this.openId, "openId");
    localJceDisplayer.display(this.accessToken, "accessToken");
    localJceDisplayer.display(this.tokenType, "tokenType");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.appId, true);
    localJceDisplayer.displaySimple(this.openId, true);
    localJceDisplayer.displaySimple(this.accessToken, true);
    localJceDisplayer.displaySimple(this.tokenType, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TBodyGetSdkSybAccessTokenReq localTBodyGetSdkSybAccessTokenReq;
    do
    {
      return false;
      localTBodyGetSdkSybAccessTokenReq = (TBodyGetSdkSybAccessTokenReq)paramObject;
    }
    while ((!JceUtil.equals(this.appId, localTBodyGetSdkSybAccessTokenReq.appId)) || (!JceUtil.equals(this.openId, localTBodyGetSdkSybAccessTokenReq.openId)) || (!JceUtil.equals(this.accessToken, localTBodyGetSdkSybAccessTokenReq.accessToken)) || (!JceUtil.equals(this.tokenType, localTBodyGetSdkSybAccessTokenReq.tokenType)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyGetSdkSybAccessTokenReq";
  }

  public byte[] getAccessToken()
  {
    return this.accessToken;
  }

  public String getAppId()
  {
    return this.appId;
  }

  public String getOpenId()
  {
    return this.openId;
  }

  public int getTokenType()
  {
    return this.tokenType;
  }

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
    this.appId = paramJceInputStream.readString(0, false);
    this.openId = paramJceInputStream.readString(1, false);
    if (cache_accessToken == null)
    {
      cache_accessToken = (byte[])new byte[1];
      ((byte[])cache_accessToken)[0] = 0;
    }
    this.accessToken = ((byte[])paramJceInputStream.read(cache_accessToken, 2, false));
    this.tokenType = paramJceInputStream.read(this.tokenType, 3, false);
  }

  public void setAccessToken(byte[] paramArrayOfByte)
  {
    this.accessToken = paramArrayOfByte;
  }

  public void setAppId(String paramString)
  {
    this.appId = paramString;
  }

  public void setOpenId(String paramString)
  {
    this.openId = paramString;
  }

  public void setTokenType(int paramInt)
  {
    this.tokenType = paramInt;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    if (this.appId != null)
      paramJceOutputStream.write(this.appId, 0);
    if (this.openId != null)
      paramJceOutputStream.write(this.openId, 1);
    if (this.accessToken != null)
      paramJceOutputStream.write(this.accessToken, 2);
    paramJceOutputStream.write(this.tokenType, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyGetSdkSybAccessTokenReq
 * JD-Core Version:    0.6.0
 */