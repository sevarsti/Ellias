package common;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.HashMap;
import java.util.Map;

public final class MixPackage extends JceStruct
  implements Cloneable
{
  static Map<Integer, byte[]> cache_mixMap;
  public Map<Integer, byte[]> mixMap = null;

  static
  {
    if (!MixPackage.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public MixPackage()
  {
    setMixMap(this.mixMap);
  }

  public MixPackage(Map<Integer, byte[]> paramMap)
  {
    setMixMap(paramMap);
  }

  public final String className()
  {
    return "common.MixPackage";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.mixMap, "mixMap");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    MixPackage localMixPackage = (MixPackage)paramObject;
    return JceUtil.equals(this.mixMap, localMixPackage.mixMap);
  }

  public final String fullClassName()
  {
    return "common.MixPackage";
  }

  public final Map<Integer, byte[]> getMixMap()
  {
    return this.mixMap;
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
    if (cache_mixMap == null)
    {
      cache_mixMap = new HashMap();
      Integer localInteger = Integer.valueOf(0);
      byte[] arrayOfByte = { 0 };
      cache_mixMap.put(localInteger, arrayOfByte);
    }
    setMixMap((Map)paramJceInputStream.read(cache_mixMap, 0, true));
  }

  public final void setMixMap(Map<Integer, byte[]> paramMap)
  {
    this.mixMap = paramMap;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.mixMap, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     common.MixPackage
 * JD-Core Version:    0.6.0
 */