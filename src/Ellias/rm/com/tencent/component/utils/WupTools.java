package com.tencent.component.utils;

import com.qq.taf.jce.JceDecodeException;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public class WupTools
{
  private static final String a = "UTF-8";

  @PluginApi(a=6)
  public static JceStruct decodeWup(Class paramClass, byte[] paramArrayOfByte)
  {
    if ((paramClass != null) && (paramArrayOfByte != null) && (paramArrayOfByte.length > 0));
    try
    {
      JceStruct localJceStruct = (JceStruct)paramClass.newInstance();
      JceInputStream localJceInputStream = new JceInputStream(paramArrayOfByte);
      localJceInputStream.setServerEncoding("UTF-8");
      localJceStruct.readFrom(localJceInputStream);
      return localJceStruct;
    }
    catch (JceDecodeException localJceDecodeException)
    {
      localJceDecodeException.printStackTrace();
      throw new RuntimeException("decode wup failed(class:" + paramClass.getName() + ")", localJceDecodeException);
    }
    catch (InstantiationException localInstantiationException)
    {
      return null;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      label95: break label95;
    }
  }

  @PluginApi(a=6)
  public static byte[] encodeWup(JceStruct paramJceStruct)
  {
    if (paramJceStruct == null)
      return null;
    JceOutputStream localJceOutputStream = new JceOutputStream();
    localJceOutputStream.setServerEncoding("UTF-8");
    paramJceStruct.writeTo(localJceOutputStream);
    return localJceOutputStream.toByteArray();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.WupTools
 * JD-Core Version:    0.6.0
 */