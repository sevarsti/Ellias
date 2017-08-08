package com.tencent.component.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract interface BaseApplication$FragmentLifecycleCallbacks
{
  public abstract void a(Fragment paramFragment);

  public abstract void a(Fragment paramFragment, Activity paramActivity);

  public abstract void a(Fragment paramFragment, Bundle paramBundle);

  public abstract void b(Fragment paramFragment);

  public abstract void b(Fragment paramFragment, Bundle paramBundle);

  public abstract void c(Fragment paramFragment);

  public abstract void d(Fragment paramFragment);

  public abstract void e(Fragment paramFragment);

  public abstract void f(Fragment paramFragment);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.app.BaseApplication.FragmentLifecycleCallbacks
 * JD-Core Version:    0.6.0
 */