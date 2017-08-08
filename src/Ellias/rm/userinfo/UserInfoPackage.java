package userinfo;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class UserInfoPackage extends JceStruct
  implements Cloneable
{
  static ArrayList<SummaryInfo> cache_list;
  static Map<String, byte[]> cache_valueMap;
  public String guid = "";
  public String imei = "";
  public ArrayList<SummaryInfo> list = null;
  public String mac = "";
  public String proceName = "";
  public byte type = 0;
  public Map<String, byte[]> valueMap = null;

  static
  {
    if (!UserInfoPackage.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public UserInfoPackage()
  {
  }

  public UserInfoPackage(String paramString1, String paramString2, String paramString3, String paramString4, Map<String, byte[]> paramMap, byte paramByte, ArrayList<SummaryInfo> paramArrayList)
  {
    this.guid = paramString1;
    this.proceName = paramString2;
    this.imei = paramString3;
    this.mac = paramString4;
    this.valueMap = paramMap;
    this.type = paramByte;
    this.list = paramArrayList;
  }

  public final String className()
  {
    return "userinfo.UserInfoPackage";
  }

  public final Object clone()
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

  public final void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.guid, "guid");
    localJceDisplayer.display(this.proceName, "proceName");
    localJceDisplayer.display(this.imei, "imei");
    localJceDisplayer.display(this.mac, "mac");
    localJceDisplayer.display(this.valueMap, "valueMap");
    localJceDisplayer.display(this.type, "type");
    localJceDisplayer.display(this.list, "list");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    UserInfoPackage localUserInfoPackage;
    do
    {
      return false;
      localUserInfoPackage = (UserInfoPackage)paramObject;
    }
    while ((!JceUtil.equals(this.guid, localUserInfoPackage.guid)) || (!JceUtil.equals(this.proceName, localUserInfoPackage.proceName)) || (!JceUtil.equals(this.imei, localUserInfoPackage.imei)) || (!JceUtil.equals(this.mac, localUserInfoPackage.mac)) || (!JceUtil.equals(this.valueMap, localUserInfoPackage.valueMap)) || (!JceUtil.equals(this.type, localUserInfoPackage.type)) || (!JceUtil.equals(this.list, localUserInfoPackage.list)));
    return true;
  }

  public final String fullClassName()
  {
    return "userinfo.UserInfoPackage";
  }

  public final String getGuid()
  {
    return this.guid;
  }

  public final String getImei()
  {
    return this.imei;
  }

  public final ArrayList<SummaryInfo> getList()
  {
    return this.list;
  }

  public final String getMac()
  {
    return this.mac;
  }

  public final String getProceName()
  {
    return this.proceName;
  }

  public final byte getType()
  {
    return this.type;
  }

  public final Map<String, byte[]> getValueMap()
  {
    return this.valueMap;
  }

  public final int hashCode()
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

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    this.guid = paramJceInputStream.readString(0, true);
    this.proceName = paramJceInputStream.readString(1, false);
    this.imei = paramJceInputStream.readString(2, false);
    this.mac = paramJceInputStream.readString(3, false);
    if (cache_valueMap == null)
    {
      cache_valueMap = new HashMap();
      byte[] arrayOfByte = { 0 };
      cache_valueMap.put("", arrayOfByte);
    }
    this.valueMap = ((Map)paramJceInputStream.read(cache_valueMap, 4, false));
    this.type = paramJceInputStream.read(this.type, 5, false);
    if (cache_list == null)
    {
      cache_list = new ArrayList();
      SummaryInfo localSummaryInfo = new SummaryInfo();
      cache_list.add(localSummaryInfo);
    }
    this.list = ((ArrayList)paramJceInputStream.read(cache_list, 6, false));
  }

  public final void setGuid(String paramString)
  {
    this.guid = paramString;
  }

  public final void setImei(String paramString)
  {
    this.imei = paramString;
  }

  public final void setList(ArrayList<SummaryInfo> paramArrayList)
  {
    this.list = paramArrayList;
  }

  public final void setMac(String paramString)
  {
    this.mac = paramString;
  }

  public final void setProceName(String paramString)
  {
    this.proceName = paramString;
  }

  public final void setType(byte paramByte)
  {
    this.type = paramByte;
  }

  public final void setValueMap(Map<String, byte[]> paramMap)
  {
    this.valueMap = paramMap;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.guid, 0);
    if (this.proceName != null)
      paramJceOutputStream.write(this.proceName, 1);
    if (this.imei != null)
      paramJceOutputStream.write(this.imei, 2);
    if (this.mac != null)
      paramJceOutputStream.write(this.mac, 3);
    if (this.valueMap != null)
      paramJceOutputStream.write(this.valueMap, 4);
    paramJceOutputStream.write(this.type, 5);
    if (this.list != null)
      paramJceOutputStream.write(this.list, 6);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     userinfo.UserInfoPackage
 * JD-Core Version:    0.6.0
 */