package com.tencent.component.utils;

import android.os.Parcel;
import android.os.Parcelable;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.utils.log.LogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParcelUtil
{
  private static final String a = "ParcelUtil";

  public static JceStruct a(byte[] paramArrayOfByte, ClassLoader paramClassLoader)
  {
    Parcel localParcel;
    String str;
    if (paramArrayOfByte != null)
    {
      localParcel = Parcel.obtain();
      localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
      localParcel.setDataPosition(0);
      str = localParcel.readString();
      if ((str != null) && (paramClassLoader != null));
    }
    try
    {
      paramClassLoader = ParcelUtil.class.getClassLoader();
      Class localClass = Class.forName(str, true, paramClassLoader);
      int i = localParcel.readInt();
      if (i > 0)
      {
        byte[] arrayOfByte = new byte[i];
        localParcel.readByteArray(arrayOfByte);
        JceStruct localJceStruct = WupTools.decodeWup(localClass, arrayOfByte);
        return localJceStruct;
      }
      return null;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      while (true)
      {
        LogUtil.e("ParcelUtil", localClassNotFoundException.getMessage(), localClassNotFoundException);
        localParcel.recycle();
      }
    }
    finally
    {
      localParcel.recycle();
    }
    throw localObject;
  }

  public static Serializable a(byte[] paramArrayOfByte)
  {
    Object localObject1 = null;
    Parcel localParcel;
    if (paramArrayOfByte != null)
      localParcel = Parcel.obtain();
    try
    {
      localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
      localParcel.setDataPosition(0);
      Serializable localSerializable = localParcel.readSerializable();
      localObject1 = localSerializable;
      return localObject1;
    }
    catch (Throwable localThrowable)
    {
      LogUtil.e("ParcelUtil", localThrowable.getMessage(), localThrowable);
      return null;
    }
    finally
    {
      localParcel.recycle();
    }
    throw localObject2;
  }

  public static void a(Parcel paramParcel, JceStruct paramJceStruct)
  {
    if (paramJceStruct == null)
    {
      paramParcel.writeString(null);
      return;
    }
    paramParcel.writeString(paramJceStruct.getClass().getName());
    byte[] arrayOfByte = WupTools.encodeWup(paramJceStruct);
    if (arrayOfByte != null)
    {
      paramParcel.writeInt(arrayOfByte.length);
      paramParcel.writeByteArray(arrayOfByte);
      return;
    }
    paramParcel.writeInt(0);
  }

  public static byte[] a(Parcelable paramParcelable)
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.setDataPosition(0);
      localParcel.writeParcelable(paramParcelable, 0);
      byte[] arrayOfByte = localParcel.marshall();
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      LogUtil.e("ParcelUtil", localThrowable.getMessage(), localThrowable);
      return null;
    }
    finally
    {
      localParcel.recycle();
    }
    throw localObject;
  }

  public static byte[] a(JceStruct paramJceStruct)
  {
    Parcel localParcel = Parcel.obtain();
    localParcel.setDataPosition(0);
    a(localParcel, paramJceStruct);
    byte[] arrayOfByte = localParcel.marshall();
    localParcel.recycle();
    return arrayOfByte;
  }

  public static byte[] a(Serializable paramSerializable)
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.setDataPosition(0);
      localParcel.writeSerializable(paramSerializable);
      byte[] arrayOfByte = localParcel.marshall();
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      LogUtil.e("ParcelUtil", localThrowable.getMessage(), localThrowable);
      return null;
    }
    finally
    {
      localParcel.recycle();
    }
    throw localObject;
  }

  public static byte[] a(List paramList)
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.setDataPosition(0);
      localParcel.writeList(paramList);
      byte[] arrayOfByte = localParcel.marshall();
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      LogUtil.e("ParcelUtil", localThrowable.getMessage(), localThrowable);
      return null;
    }
    finally
    {
      localParcel.recycle();
    }
    throw localObject;
  }

  public static Parcelable b(byte[] paramArrayOfByte, ClassLoader paramClassLoader)
  {
    Object localObject1 = null;
    Parcel localParcel;
    if (paramArrayOfByte != null)
      localParcel = Parcel.obtain();
    try
    {
      localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
      localParcel.setDataPosition(0);
      if (paramClassLoader == null)
        paramClassLoader = ParcelUtil.class.getClassLoader();
      Parcelable localParcelable = localParcel.readParcelable(paramClassLoader);
      localObject1 = localParcelable;
      return localObject1;
    }
    catch (Throwable localThrowable)
    {
      LogUtil.e("ParcelUtil", localThrowable.getMessage(), localThrowable);
      return null;
    }
    finally
    {
      localParcel.recycle();
    }
    throw localObject2;
  }

  public static List c(byte[] paramArrayOfByte, ClassLoader paramClassLoader)
  {
    Object localObject1 = null;
    Parcel localParcel;
    if (paramArrayOfByte != null)
      localParcel = Parcel.obtain();
    try
    {
      localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
      localParcel.setDataPosition(0);
      if (paramClassLoader == null)
        paramClassLoader = ParcelUtil.class.getClassLoader();
      ArrayList localArrayList = localParcel.readArrayList(paramClassLoader);
      localObject1 = localArrayList;
      return localObject1;
    }
    catch (Throwable localThrowable)
    {
      LogUtil.e("ParcelUtil", localThrowable.getMessage(), localThrowable);
      return null;
    }
    finally
    {
      localParcel.recycle();
    }
    throw localObject2;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.ParcelUtil
 * JD-Core Version:    0.6.0
 */