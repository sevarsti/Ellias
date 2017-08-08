package com.tencent.mid.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.tencent.mid.util.Util;

public class d extends f
{
  public d(Context paramContext)
  {
    super(paramContext);
  }

  protected void a(a parama)
  {
    monitorenter;
    try
    {
      Util.logInfo("write CheckEntity to sharedPreferences:" + parama.toString());
      SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(this.a).edit();
      localEditor.putString(g(), parama.toString());
      localEditor.commit();
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected void a(String paramString)
  {
    monitorenter;
    try
    {
      Util.logInfo("write mid to sharedPreferences");
      SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(this.a).edit();
      localEditor.putString(k(), paramString);
      localEditor.commit();
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected boolean a()
  {
    return true;
  }

  protected String b()
  {
    monitorenter;
    try
    {
      Util.logInfo("read mid from sharedPreferences");
      String str = PreferenceManager.getDefaultSharedPreferences(this.a).getString(k(), null);
      return str;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected void c()
  {
    monitorenter;
    try
    {
      SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(this.a).edit();
      localEditor.putString(k(), "");
      localEditor.putString(g(), "");
      localEditor.commit();
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected a d()
  {
    monitorenter;
    try
    {
      a locala = new a(PreferenceManager.getDefaultSharedPreferences(this.a).getString(g(), null));
      Util.logInfo("read CheckEntity from sharedPreferences:" + locala.toString());
      return locala;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.b.d
 * JD-Core Version:    0.6.0
 */