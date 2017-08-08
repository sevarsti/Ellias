package com.tencent.component.net.http.cookie;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

public class PersistentCookieStore
  implements CookieStore
{
  private static final String a = "CookiePrefsFile";
  private static final String b = "names";
  private static final String c = "cookie_";
  private final ConcurrentHashMap d;
  private final SharedPreferences e;

  public PersistentCookieStore(Context paramContext)
  {
    this.e = paramContext.getSharedPreferences("CookiePrefsFile", 0);
    this.d = new ConcurrentHashMap();
    String str1 = this.e.getString("names", null);
    if (str1 != null)
    {
      String[] arrayOfString = TextUtils.split(str1, ",");
      int j = arrayOfString.length;
      while (i < j)
      {
        String str2 = arrayOfString[i];
        String str3 = this.e.getString("cookie_" + str2, null);
        if (str3 != null)
        {
          Cookie localCookie = a(str3);
          if (localCookie != null)
            this.d.put(str2, localCookie);
        }
        i++;
      }
      clearExpired(new Date());
    }
  }

  protected String a(SerializableCookie paramSerializableCookie)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      new ObjectOutputStream(localByteArrayOutputStream).writeObject(paramSerializableCookie);
      return a(localByteArrayOutputStream.toByteArray());
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  protected String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
    int i = paramArrayOfByte.length;
    for (int j = 0; j < i; j++)
    {
      int k = 0xFF & paramArrayOfByte[j];
      if (k < 16)
        localStringBuilder.append('0');
      localStringBuilder.append(Integer.toHexString(k));
    }
    return localStringBuilder.toString().toUpperCase();
  }

  protected Cookie a(String paramString)
  {
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(b(paramString));
    try
    {
      Cookie localCookie = ((SerializableCookie)new ObjectInputStream(localByteArrayInputStream).readObject()).a();
      return localCookie;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public void addCookie(Cookie paramCookie)
  {
    String str = paramCookie.getName() + paramCookie.getDomain();
    if (!paramCookie.isExpired(new Date()))
      this.d.put(str, paramCookie);
    while (true)
    {
      SharedPreferences.Editor localEditor = this.e.edit();
      localEditor.putString("names", TextUtils.join(",", this.d.keySet()));
      localEditor.putString("cookie_" + str, a(new SerializableCookie(paramCookie)));
      localEditor.commit();
      return;
      this.d.remove(str);
    }
  }

  protected byte[] b(String paramString)
  {
    int i = paramString.length();
    byte[] arrayOfByte = new byte[i / 2];
    for (int j = 0; j < i; j += 2)
      arrayOfByte[(j / 2)] = (byte)((Character.digit(paramString.charAt(j), 16) << 4) + Character.digit(paramString.charAt(j + 1), 16));
    return arrayOfByte;
  }

  public void clear()
  {
    SharedPreferences.Editor localEditor = this.e.edit();
    Iterator localIterator = this.d.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localEditor.remove("cookie_" + str);
    }
    localEditor.remove("names");
    localEditor.commit();
    this.d.clear();
  }

  public boolean clearExpired(Date paramDate)
  {
    SharedPreferences.Editor localEditor = this.e.edit();
    Iterator localIterator = this.d.entrySet().iterator();
    int i = 0;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str = (String)localEntry.getKey();
      if (!((Cookie)localEntry.getValue()).isExpired(paramDate))
        break label160;
      this.d.remove(str);
      localEditor.remove("cookie_" + str);
    }
    label160: for (int j = 1; ; j = i)
    {
      i = j;
      break;
      if (i != 0)
        localEditor.putString("names", TextUtils.join(",", this.d.keySet()));
      localEditor.commit();
      return i;
    }
  }

  public List getCookies()
  {
    return new ArrayList(this.d.values());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.cookie.PersistentCookieStore
 * JD-Core Version:    0.6.0
 */