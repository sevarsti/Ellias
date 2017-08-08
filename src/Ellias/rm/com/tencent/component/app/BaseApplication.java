package com.tencent.component.app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.tencent.component.ComponentInitializer;
import java.util.ArrayList;

public class BaseApplication extends Application
{
  private final ArrayList a = new ArrayList();
  private final ArrayList b = new ArrayList();

  private Object[] a()
  {
    synchronized (this.a)
    {
      int i = this.a.size();
      Object[] arrayOfObject = null;
      if (i > 0)
        arrayOfObject = this.a.toArray();
      return arrayOfObject;
    }
  }

  private Object[] b()
  {
    synchronized (this.b)
    {
      int i = this.b.size();
      Object[] arrayOfObject = null;
      if (i > 0)
        arrayOfObject = this.b.toArray();
      return arrayOfObject;
    }
  }

  void a(Activity paramActivity)
  {
    Object[] arrayOfObject = a();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.ActivityLifecycleCallbacks)arrayOfObject[i]).a(paramActivity);
  }

  void a(Activity paramActivity, int paramInt1, int paramInt2, Intent paramIntent)
  {
    Object[] arrayOfObject = a();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.ActivityLifecycleCallbacks)arrayOfObject[i]).a(paramActivity, paramInt1, paramInt2, paramIntent);
  }

  void a(Activity paramActivity, Bundle paramBundle)
  {
    Object[] arrayOfObject = a();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.ActivityLifecycleCallbacks)arrayOfObject[i]).a(paramActivity, paramBundle);
  }

  void a(Fragment paramFragment)
  {
    Object[] arrayOfObject = b();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.FragmentLifecycleCallbacks)arrayOfObject[i]).a(paramFragment);
  }

  void a(Fragment paramFragment, Activity paramActivity)
  {
    Object[] arrayOfObject = b();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.FragmentLifecycleCallbacks)arrayOfObject[i]).a(paramFragment, paramActivity);
  }

  void a(Fragment paramFragment, Bundle paramBundle)
  {
    Object[] arrayOfObject = b();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.FragmentLifecycleCallbacks)arrayOfObject[i]).a(paramFragment, paramBundle);
  }

  public void a(BaseApplication.ActivityLifecycleCallbacks paramActivityLifecycleCallbacks)
  {
    synchronized (this.a)
    {
      this.a.add(paramActivityLifecycleCallbacks);
      return;
    }
  }

  public void a(BaseApplication.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks)
  {
    synchronized (this.b)
    {
      this.b.add(paramFragmentLifecycleCallbacks);
      return;
    }
  }

  void b(Activity paramActivity)
  {
    Object[] arrayOfObject = a();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.ActivityLifecycleCallbacks)arrayOfObject[i]).b(paramActivity);
  }

  void b(Activity paramActivity, Bundle paramBundle)
  {
    Object[] arrayOfObject = a();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.ActivityLifecycleCallbacks)arrayOfObject[i]).b(paramActivity, paramBundle);
  }

  void b(Fragment paramFragment)
  {
    Object[] arrayOfObject = b();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.FragmentLifecycleCallbacks)arrayOfObject[i]).b(paramFragment);
  }

  void b(Fragment paramFragment, Bundle paramBundle)
  {
    Object[] arrayOfObject = b();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.FragmentLifecycleCallbacks)arrayOfObject[i]).b(paramFragment, paramBundle);
  }

  public void b(BaseApplication.ActivityLifecycleCallbacks paramActivityLifecycleCallbacks)
  {
    synchronized (this.a)
    {
      this.a.remove(paramActivityLifecycleCallbacks);
      return;
    }
  }

  public void b(BaseApplication.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks)
  {
    synchronized (this.b)
    {
      this.b.remove(paramFragmentLifecycleCallbacks);
      return;
    }
  }

  void c(Activity paramActivity)
  {
    Object[] arrayOfObject = a();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.ActivityLifecycleCallbacks)arrayOfObject[i]).c(paramActivity);
  }

  void c(Fragment paramFragment)
  {
    Object[] arrayOfObject = b();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.FragmentLifecycleCallbacks)arrayOfObject[i]).c(paramFragment);
  }

  void d(Activity paramActivity)
  {
    Object[] arrayOfObject = a();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.ActivityLifecycleCallbacks)arrayOfObject[i]).d(paramActivity);
  }

  void d(Fragment paramFragment)
  {
    Object[] arrayOfObject = b();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.FragmentLifecycleCallbacks)arrayOfObject[i]).d(paramFragment);
  }

  void e(Activity paramActivity)
  {
    Object[] arrayOfObject = a();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.ActivityLifecycleCallbacks)arrayOfObject[i]).e(paramActivity);
  }

  void e(Fragment paramFragment)
  {
    Object[] arrayOfObject = b();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.FragmentLifecycleCallbacks)arrayOfObject[i]).e(paramFragment);
  }

  void f(Activity paramActivity)
  {
    Object[] arrayOfObject = a();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.ActivityLifecycleCallbacks)arrayOfObject[i]).f(paramActivity);
  }

  void f(Fragment paramFragment)
  {
    Object[] arrayOfObject = b();
    if (arrayOfObject != null)
      for (int i = 0; i < arrayOfObject.length; i++)
        ((BaseApplication.FragmentLifecycleCallbacks)arrayOfObject[i]).f(paramFragment);
  }

  public void onCreate()
  {
    super.onCreate();
    ComponentInitializer.a(this);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.app.BaseApplication
 * JD-Core Version:    0.6.0
 */