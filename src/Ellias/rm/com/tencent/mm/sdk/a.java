package com.tencent.mm.sdk;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import android.net.Uri;
import com.tencent.mm.sdk.c.a.b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class a
  implements SharedPreferences
{
  private final ContentResolver a;
  private final String[] b = { "_id", "key", "type", "value" };
  private final HashMap<String, Object> c = new HashMap();
  private a d = null;

  public a(Context paramContext)
  {
    this.a = paramContext.getContentResolver();
  }

  private Object getValue(String paramString)
  {
    while (true)
    {
      try
      {
        Cursor localCursor = this.a.query(a.b.CONTENT_URI, this.b, "key = ?", new String[] { paramString }, null);
        if (localCursor == null)
          return null;
        int i = localCursor.getColumnIndex("type");
        int j = localCursor.getColumnIndex("value");
        if (localCursor.moveToFirst())
        {
          localObject = com.tencent.mm.sdk.c.a.a.a(localCursor.getInt(i), localCursor.getString(j));
          localCursor.close();
          return localObject;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return null;
      }
      Object localObject = null;
    }
  }

  public final boolean contains(String paramString)
  {
    return getValue(paramString) != null;
  }

  public final SharedPreferences.Editor edit()
  {
    if (this.d == null)
      this.d = new a(this.a);
    return this.d;
  }

  public final Map<String, ?> getAll()
  {
    Cursor localCursor;
    try
    {
      localCursor = this.a.query(a.b.CONTENT_URI, this.b, null, null, null);
      if (localCursor == null)
        return null;
      int i = localCursor.getColumnIndex("key");
      int j = localCursor.getColumnIndex("type");
      int k = localCursor.getColumnIndex("value");
      while (localCursor.moveToNext())
      {
        Object localObject = com.tencent.mm.sdk.c.a.a.a(localCursor.getInt(j), localCursor.getString(k));
        this.c.put(localCursor.getString(i), localObject);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return this.c;
    }
    localCursor.close();
    HashMap localHashMap = this.c;
    return localHashMap;
  }

  public final boolean getBoolean(String paramString, boolean paramBoolean)
  {
    Object localObject = getValue(paramString);
    if ((localObject != null) && ((localObject instanceof Boolean)))
      paramBoolean = ((Boolean)localObject).booleanValue();
    return paramBoolean;
  }

  public final float getFloat(String paramString, float paramFloat)
  {
    Object localObject = getValue(paramString);
    if ((localObject != null) && ((localObject instanceof Float)))
      paramFloat = ((Float)localObject).floatValue();
    return paramFloat;
  }

  public final int getInt(String paramString, int paramInt)
  {
    Object localObject = getValue(paramString);
    if ((localObject != null) && ((localObject instanceof Integer)))
      paramInt = ((Integer)localObject).intValue();
    return paramInt;
  }

  public final long getLong(String paramString, long paramLong)
  {
    Object localObject = getValue(paramString);
    if ((localObject != null) && ((localObject instanceof Long)))
      paramLong = ((Long)localObject).longValue();
    return paramLong;
  }

  public final String getString(String paramString1, String paramString2)
  {
    Object localObject = getValue(paramString1);
    if ((localObject != null) && ((localObject instanceof String)))
      return (String)localObject;
    return paramString2;
  }

  public final Set<String> getStringSet(String paramString, Set<String> paramSet)
  {
    return null;
  }

  public final void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener)
  {
  }

  public final void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener)
  {
  }

  private static final class a
    implements SharedPreferences.Editor
  {
    private ContentResolver a;
    private Map<String, Object> e = new HashMap();
    private Set<String> f = new HashSet();
    private boolean g = false;

    public a(ContentResolver paramContentResolver)
    {
      this.a = paramContentResolver;
    }

    public final void apply()
    {
    }

    public final SharedPreferences.Editor clear()
    {
      this.g = true;
      return this;
    }

    public final boolean commit()
    {
      ContentValues localContentValues = new ContentValues();
      if (this.g)
      {
        this.a.delete(a.b.CONTENT_URI, null, null);
        this.g = false;
      }
      Iterator localIterator1 = this.f.iterator();
      while (localIterator1.hasNext())
      {
        String str = (String)localIterator1.next();
        this.a.delete(a.b.CONTENT_URI, "key = ?", new String[] { str });
      }
      Iterator localIterator2 = this.e.entrySet().iterator();
      label147: label326: label352: 
      while (localIterator2.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator2.next();
        Object localObject = localEntry.getValue();
        int i;
        if (localObject == null)
        {
          com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.PluginProvider.Resolver", "unresolve failed, null value");
          i = 0;
          if (i != 0)
            break label326;
        }
        for (int j = 0; ; j = 1)
        {
          if (j == 0)
            break label352;
          ContentResolver localContentResolver = this.a;
          Uri localUri = a.b.CONTENT_URI;
          String[] arrayOfString = new String[1];
          arrayOfString[0] = ((String)localEntry.getKey());
          localContentResolver.update(localUri, localContentValues, "key = ?", arrayOfString);
          break;
          if ((localObject instanceof Integer))
          {
            i = 1;
            break label147;
          }
          if ((localObject instanceof Long))
          {
            i = 2;
            break label147;
          }
          if ((localObject instanceof String))
          {
            i = 3;
            break label147;
          }
          if ((localObject instanceof Boolean))
          {
            i = 4;
            break label147;
          }
          if ((localObject instanceof Float))
          {
            i = 5;
            break label147;
          }
          if ((localObject instanceof Double))
          {
            i = 6;
            break label147;
          }
          com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.PluginProvider.Resolver", "unresolve failed, unknown type=" + localObject.getClass().toString());
          i = 0;
          break label147;
          localContentValues.put("type", Integer.valueOf(i));
          localContentValues.put("value", localObject.toString());
        }
      }
      return true;
    }

    public final SharedPreferences.Editor putBoolean(String paramString, boolean paramBoolean)
    {
      this.e.put(paramString, Boolean.valueOf(paramBoolean));
      this.f.remove(paramString);
      return this;
    }

    public final SharedPreferences.Editor putFloat(String paramString, float paramFloat)
    {
      this.e.put(paramString, Float.valueOf(paramFloat));
      this.f.remove(paramString);
      return this;
    }

    public final SharedPreferences.Editor putInt(String paramString, int paramInt)
    {
      this.e.put(paramString, Integer.valueOf(paramInt));
      this.f.remove(paramString);
      return this;
    }

    public final SharedPreferences.Editor putLong(String paramString, long paramLong)
    {
      this.e.put(paramString, Long.valueOf(paramLong));
      this.f.remove(paramString);
      return this;
    }

    public final SharedPreferences.Editor putString(String paramString1, String paramString2)
    {
      this.e.put(paramString1, paramString2);
      this.f.remove(paramString1);
      return this;
    }

    public final SharedPreferences.Editor putStringSet(String paramString, Set<String> paramSet)
    {
      return null;
    }

    public final SharedPreferences.Editor remove(String paramString)
    {
      this.f.add(paramString);
      return this;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mm.sdk.a
 * JD-Core Version:    0.6.0
 */