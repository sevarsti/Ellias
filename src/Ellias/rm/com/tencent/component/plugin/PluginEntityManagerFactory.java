package com.tencent.component.plugin;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.db.EntityManager;
import com.tencent.component.db.EntityManager.UpdateListener;
import com.tencent.component.db.EntityManagerFactory;
import com.tencent.component.db.SdcardSQLiteOpenHelper;
import java.util.HashMap;

@PluginApi(a=6)
public class PluginEntityManagerFactory
{
  private static final String a = String.valueOf(-2147483648);
  private static volatile HashMap c = new HashMap();
  private EntityManagerFactory b;

  private PluginEntityManagerFactory(Context paramContext, int paramInt, String paramString1, EntityManager.UpdateListener paramUpdateListener, boolean paramBoolean, String paramString2)
  {
    if (paramBoolean)
    {
      this.b = EntityManagerFactory.a(paramContext, paramInt, paramString1, SdcardSQLiteOpenHelper.a(paramString1, paramString2), paramUpdateListener);
      return;
    }
    this.b = EntityManagerFactory.a(paramContext, paramInt, paramString1, null, paramUpdateListener);
  }

  @PluginApi(a=6)
  public static PluginEntityManagerFactory getInstance(Context paramContext, int paramInt, String paramString, EntityManager.UpdateListener paramUpdateListener)
  {
    return getInstance(paramContext, paramInt, paramString, paramUpdateListener, false, null);
  }

  @PluginApi(a=100)
  public static PluginEntityManagerFactory getInstance(Context paramContext, int paramInt, String paramString1, EntityManager.UpdateListener paramUpdateListener, boolean paramBoolean, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1));
    PluginEntityManagerFactory localPluginEntityManagerFactory1;
    for (String str1 = a; ; str1 = paramString1)
    {
      if (paramBoolean)
        str1 = "sdcard_" + str1;
      if (!TextUtils.isEmpty(paramString2))
        str1 = str1 + "_" + paramString2.hashCode();
      String str2 = "plugin_" + str1;
      localPluginEntityManagerFactory1 = (PluginEntityManagerFactory)c.get(str2);
      if (localPluginEntityManagerFactory1 != null)
        break;
      synchronized (c)
      {
        PluginEntityManagerFactory localPluginEntityManagerFactory2 = (PluginEntityManagerFactory)c.get(str2);
        if (localPluginEntityManagerFactory2 == null)
        {
          localPluginEntityManagerFactory2 = new PluginEntityManagerFactory(paramContext, paramInt, str2, paramUpdateListener, paramBoolean, paramString2);
          c.put(str2, localPluginEntityManagerFactory2);
        }
        return localPluginEntityManagerFactory2;
      }
    }
    return localPluginEntityManagerFactory1;
  }

  @PluginApi(a=6)
  public void clear(String paramString)
  {
    this.b.b(paramString);
  }

  @PluginApi(a=6)
  public void close(String paramString)
  {
    this.b.a(paramString);
  }

  @PluginApi(a=6)
  public EntityManager getEntityManager(Class paramClass, String paramString, ClassLoader paramClassLoader)
  {
    return getEntityManager(paramClass, paramString, paramClassLoader, false);
  }

  @PluginApi(a=7)
  public EntityManager getEntityManager(Class paramClass, String paramString, ClassLoader paramClassLoader, boolean paramBoolean)
  {
    return this.b.a(paramClass, paramString, paramClassLoader, paramBoolean);
  }

  @PluginApi(a=6)
  public EntityManager getEntityManager(Class paramClass, String paramString, byte[] paramArrayOfByte, ClassLoader paramClassLoader)
  {
    return getEntityManager(paramClass, paramString, paramClassLoader, false);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginEntityManagerFactory
 * JD-Core Version:    0.6.0
 */