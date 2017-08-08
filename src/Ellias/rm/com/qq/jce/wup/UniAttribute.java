package com.qq.jce.wup;

import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class UniAttribute extends OldUniAttribute
{
  JceInputStream _is = new JceInputStream();
  protected HashMap<String, byte[]> _newData = null;
  private HashMap<String, Object> cachedData = new HashMap();

  private Object decodeData(byte[] paramArrayOfByte, Object paramObject)
  {
    this._is.wrap(paramArrayOfByte);
    this._is.setServerEncoding(this.encodeName);
    return this._is.read(paramObject, 0, true);
  }

  private void saveDataCache(String paramString, Object paramObject)
  {
    this.cachedData.put(paramString, paramObject);
  }

  public void clearCacheData()
  {
    this.cachedData.clear();
  }

  public boolean containsKey(String paramString)
  {
    if (this._newData != null)
      return this._newData.containsKey(paramString);
    return this._data.containsKey(paramString);
  }

  public void decode(byte[] paramArrayOfByte)
  {
    try
    {
      super.decode(paramArrayOfByte);
      return;
    }
    catch (Exception localException)
    {
      this._is.wrap(paramArrayOfByte);
      this._is.setServerEncoding(this.encodeName);
      HashMap localHashMap = new HashMap(1);
      localHashMap.put("", new byte[0]);
      this._newData = this._is.readMap(localHashMap, 0, false);
    }
  }

  public void decodeVersion2(byte[] paramArrayOfByte)
  {
    super.decode(paramArrayOfByte);
  }

  public void decodeVersion3(byte[] paramArrayOfByte)
  {
    this._is.wrap(paramArrayOfByte);
    this._is.setServerEncoding(this.encodeName);
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("", new byte[0]);
    this._newData = this._is.readMap(localHashMap, 0, false);
  }

  public byte[] encode()
  {
    if (this._newData != null)
    {
      JceOutputStream localJceOutputStream = new JceOutputStream(0);
      localJceOutputStream.setServerEncoding(this.encodeName);
      localJceOutputStream.write(this._newData, 0);
      return JceUtil.getJceBufArray(localJceOutputStream.getByteBuffer());
    }
    return super.encode();
  }

  public <T> T get(String paramString)
    throws ObjectCreateException
  {
    return get(paramString, true, null);
  }

  public <T> T get(String paramString, Object paramObject)
  {
    return get(paramString, paramObject, true, null);
  }

  public <T> T get(String paramString, T paramT, Object paramObject)
  {
    if (!this._newData.containsKey(paramString))
      return paramObject;
    return getByClass(paramString, paramT);
  }

  public <T> T get(String paramString, Object paramObject, boolean paramBoolean, ClassLoader paramClassLoader)
  {
    if (this._newData != null)
      throw new RuntimeException("data is encoded by new version, please use getByClass(String name, T proxy, Object defaultValue)");
    return super.get(paramString, paramObject, paramBoolean, paramClassLoader);
  }

  public <T> T get(String paramString, boolean paramBoolean, ClassLoader paramClassLoader)
    throws ObjectCreateException
  {
    if (this._newData != null)
      throw new RuntimeException("data is encoded by new version, please use getByClass(String name, T proxy)");
    return super.get(paramString, paramBoolean, paramClassLoader);
  }

  public <T> T getByClass(String paramString, T paramT)
    throws ObjectCreateException
  {
    Object localObject1;
    if (this._newData != null)
    {
      boolean bool2 = this._newData.containsKey(paramString);
      localObject1 = null;
      if (bool2);
    }
    boolean bool1;
    do
    {
      while (true)
      {
        return localObject1;
        if (this.cachedData.containsKey(paramString))
          return this.cachedData.get(paramString);
        byte[] arrayOfByte2 = (byte[])this._newData.get(paramString);
        try
        {
          localObject1 = decodeData(arrayOfByte2, paramT);
          if (localObject1 == null)
            continue;
          saveDataCache(paramString, localObject1);
          return localObject1;
        }
        catch (Exception localException2)
        {
          throw new ObjectCreateException(localException2);
        }
      }
      bool1 = this._data.containsKey(paramString);
      localObject1 = null;
    }
    while (!bool1);
    if (this.cachedData.containsKey(paramString))
      return this.cachedData.get(paramString);
    HashMap localHashMap = (HashMap)this._data.get(paramString);
    byte[] arrayOfByte1 = new byte[0];
    Iterator localIterator = localHashMap.entrySet().iterator();
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      ((String)localEntry.getKey());
      arrayOfByte1 = (byte[])localEntry.getValue();
    }
    try
    {
      this._is.wrap(arrayOfByte1);
      this._is.setServerEncoding(this.encodeName);
      Object localObject2 = this._is.read(paramT, 0, true);
      saveDataCache(paramString, localObject2);
      return localObject2;
    }
    catch (Exception localException1)
    {
    }
    throw new ObjectCreateException(localException1);
  }

  public <T> T getByClass(String paramString, T paramT1, T paramT2)
    throws ObjectCreateException
  {
    if (this._newData != null)
      if (this._newData.containsKey(paramString));
    do
    {
      return paramT2;
      if (this.cachedData.containsKey(paramString))
        return this.cachedData.get(paramString);
      byte[] arrayOfByte2 = (byte[])this._newData.get(paramString);
      try
      {
        Object localObject2 = decodeData(arrayOfByte2, paramT1);
        if (localObject2 != null)
          saveDataCache(paramString, localObject2);
        return localObject2;
      }
      catch (Exception localException2)
      {
        throw new ObjectCreateException(localException2);
      }
    }
    while (!this._data.containsKey(paramString));
    if (this.cachedData.containsKey(paramString))
      return this.cachedData.get(paramString);
    HashMap localHashMap = (HashMap)this._data.get(paramString);
    byte[] arrayOfByte1 = new byte[0];
    Iterator localIterator = localHashMap.entrySet().iterator();
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      ((String)localEntry.getKey());
      arrayOfByte1 = (byte[])localEntry.getValue();
    }
    try
    {
      this._is.wrap(arrayOfByte1);
      this._is.setServerEncoding(this.encodeName);
      Object localObject1 = this._is.read(paramT1, 0, true);
      saveDataCache(paramString, localObject1);
      return localObject1;
    }
    catch (Exception localException1)
    {
    }
    throw new ObjectCreateException(localException1);
  }

  public <T> T getJceStruct(String paramString)
    throws ObjectCreateException
  {
    return getJceStruct(paramString, true, null);
  }

  public <T> T getJceStruct(String paramString, T paramT)
    throws ObjectCreateException
  {
    Object localObject;
    if (!this._newData.containsKey(paramString))
      localObject = null;
    while (true)
    {
      return localObject;
      if (this.cachedData.containsKey(paramString))
        return this.cachedData.get(paramString);
      byte[] arrayOfByte = (byte[])this._newData.get(paramString);
      try
      {
        localObject = decodeData(arrayOfByte, paramT);
        if (localObject == null)
          continue;
        saveDataCache(paramString, localObject);
        return localObject;
      }
      catch (Exception localException)
      {
      }
    }
    throw new ObjectCreateException(localException);
  }

  public <T> T getJceStruct(String paramString, boolean paramBoolean, ClassLoader paramClassLoader)
    throws ObjectCreateException
  {
    if (this._newData != null)
      throw new RuntimeException("data is encoded by new version, please use getJceStruct(String name,T proxy)");
    return super.getJceStruct(paramString, paramBoolean, paramClassLoader);
  }

  public Set<String> getKeySet()
  {
    if (this._newData != null)
      return Collections.unmodifiableSet(this._newData.keySet());
    return Collections.unmodifiableSet(this._data.keySet());
  }

  public boolean isEmpty()
  {
    if (this._newData != null)
      return this._newData.isEmpty();
    return this._data.isEmpty();
  }

  public <T> void put(String paramString, T paramT)
  {
    if (this._newData != null)
    {
      if (paramString == null)
        throw new IllegalArgumentException("put key can not is null");
      if (paramT == null)
        throw new IllegalArgumentException("put value can not is null");
      if ((paramT instanceof Set))
        throw new IllegalArgumentException("can not support Set");
      JceOutputStream localJceOutputStream = new JceOutputStream();
      localJceOutputStream.setServerEncoding(this.encodeName);
      localJceOutputStream.write(paramT, 0);
      byte[] arrayOfByte = JceUtil.getJceBufArray(localJceOutputStream.getByteBuffer());
      this._newData.put(paramString, arrayOfByte);
      return;
    }
    super.put(paramString, paramT);
  }

  public <T> T remove(String paramString)
    throws ObjectCreateException
  {
    return remove(paramString, true, null);
  }

  public <T> T remove(String paramString, T paramT)
    throws ObjectCreateException
  {
    if (!this._newData.containsKey(paramString))
      return null;
    if (paramT != null)
      return decodeData((byte[])this._newData.remove(paramString), paramT);
    this._newData.remove(paramString);
    return null;
  }

  public <T> T remove(String paramString, boolean paramBoolean, ClassLoader paramClassLoader)
    throws ObjectCreateException
  {
    if (this._newData != null)
    {
      if (!this._newData.containsKey(paramString))
        return null;
      this._newData.remove(paramString);
      return null;
    }
    return super.remove(paramString, paramBoolean, paramClassLoader);
  }

  public int size()
  {
    if (this._newData != null)
      return this._newData.size();
    return this._data.size();
  }

  public void useVersion3()
  {
    this._newData = new HashMap();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.qq.jce.wup.UniAttribute
 * JD-Core Version:    0.6.0
 */