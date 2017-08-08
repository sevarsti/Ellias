package com.tencent.component.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public abstract interface BaseApplication$ActivityLifecycleCallbacks
{
  public abstract void a(Activity paramActivity);

  public abstract void a(Activity paramActivity, int paramInt1, int paramInt2, Intent paramIntent);

  public abstract void a(Activity paramActivity, Bundle paramBundle);

  public abstract void b(Activity paramActivity);

  public abstract void b(Activity paramActivity, Bundle paramBundle);

  public abstract void c(Activity paramActivity);

  public abstract void d(Activity paramActivity);

  public abstract void e(Activity paramActivity);

  public abstract void f(Activity paramActivity);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.app.BaseApplication.ActivityLifecycleCallbacks
 * JD-Core Version:    0.6.0
 */