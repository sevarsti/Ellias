package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TBodyGetSdkSybAccessTokenResp extends JceStruct
  implements Cloneable
{
  static byte[] cache_accessToken;
  static TAccountInfo cache_accoutInfo;
  public byte[] accessToken = null;
  public boolean isNewUser = true;
  public int sybAccessTokenType = 0;
  public long sybId = 0L;

  static
  {
    if (!TBodyGetSdkSybAccessTokenResp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyGetSdkSybAccessTokenResp()
  {
  }

  public TBodyGetSdkSybAccessTokenResp(int paramInt, byte[] paramArrayOfByte, long paramLong, boolean paramBoolean)
  {
    this.sybAccessTokenType = paramInt;
    this.accessToken = paramArrayOfByte;
    this.sybId = paramLong;
    this.isNewUser = paramBoolean;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyGetSdkSybAccessTokenResp";
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
    localJceDisplayer.display(this.sybAccessTokenType, "sybAccessTokenType");
    localJceDisplayer.display(this.accessToken, "accessToken");
    localJceDisplayer.display(this.sybId, "sybId");
    localJceDisplayer.display(this.isNewUser, "isNewUser");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.sybAccessTokenType, true);
    localJceDisplayer.displaySimple(this.accessToken, true);
    localJceDisplayer.displaySimple(this.sybId, true);
    localJceDisplayer.displaySimple(this.isNewUser, true);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TBodyGetSdkSybAccessTokenResp localTBodyGetSdkSybAccessTokenResp;
    do
    {
      return false;
      localTBodyGetSdkSybAccessTokenResp = (TBodyGetSdkSybAccessTokenResp)paramObject;
    }
    while ((!JceUtil.equals(this.sybAccessTokenType, localTBodyGetSdkSybAccessTokenResp.sybAccessTokenType)) || (!JceUtil.equals(this.accessToken, localTBodyGetSdkSybAccessTokenResp.accessToken)) || (!JceUtil.equals(this.sybId, localTBodyGetSdkSybAccessTokenResp.sybId)) || (!JceUtil.equals(this.isNewUser, localTBodyGetSdkSybAccessTokenResp.isNewUser)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyGetSdkSybAccessTokenResp";
  }

  public byte[] getAccessToken()
  {
    return this.accessToken;
  }

  public boolean getIsNewUser()
  {
    return this.isNewUser;
  }

  public int getSybAccessTokenType()
  {
    return this.sybAccessTokenType;
  }

  public long getSybId()
  {
    return this.sybId;
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
    this.sybAccessTokenType = paramJceInputStream.read(this.sybAccessTokenType, 0, true);
    if (cache_accessToken == null)
    {
      cache_accessToken = (byte[])new byte[1];
      ((byte[])cache_accessToken)[0] = 0;
    }
    this.accessToken = ((byte[])paramJceInputStream.read(cache_accessToken, 1, true));
    this.sybId = paramJceInputStream.read(this.sybId, 2, false);
    this.isNewUser = paramJceInputStream.read(this.isNewUser, 3, false);
    if (cache_accoutInfo == null)
      cache_accoutInfo = new TAccountInfo();
  }

  public void setAccessToken(byte[] paramArrayOfByte)
  {
    this.accessToken = paramArrayOfByte;
  }

  public void setIsNewUser(boolean paramBoolean)
  {
    this.isNewUser = paramBoolean;
  }

  public void setSybAccessTokenType(int paramInt)
  {
    this.sybAccessTokenType = paramInt;
  }

  public void setSybId(long paramLong)
  {
    this.sybId = paramLong;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.sybAccessTokenType, 0);
    paramJceOutputStream.write(this.accessToken, 1);
    paramJceOutputStream.write(this.sybId, 2);
    paramJceOutputStream.write(this.isNewUser, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyGetSdkSybAccessTokenResp
 * JD-Core Version:    0.6.0
 */