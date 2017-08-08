package com.tencent.component.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;

public class BaseActivity extends FragmentActivity
{
  private Thread a = Looper.getMainLooper().getThread();
  private Handler b = new Handler(Looper.getMainLooper());
  private boolean c = false;
  private boolean d = false;

  private boolean e()
  {
    return getApplication() instanceof BaseApplication;
  }

  public final void a(Runnable paramRunnable)
  {
    this.b.post(paramRunnable);
  }

  public final void a(Runnable paramRunnable, long paramLong)
  {
    this.b.postDelayed(paramRunnable, paramLong);
  }

  public final boolean a()
  {
    return this.a == Thread.currentThread();
  }

  public final void b(Runnable paramRunnable)
  {
    this.b.removeCallbacks(paramRunnable);
  }

  public final boolean b()
  {
    return this.c;
  }

  public final boolean c()
  {
    return this.d;
  }

  public final Handler d()
  {
    return this.b;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (e())
      ((BaseApplication)getApplication()).a(this, paramInt1, paramInt2, paramIntent);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (e())
      ((BaseApplication)getApplication()).a(this, paramBundle);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (e())
      ((BaseApplication)getApplication()).e(this);
  }

  protected void onPause()
  {
    super.onPause();
    this.c = false;
    if (e())
      ((BaseApplication)getApplication()).c(this);
  }

  protected void onResume()
  {
    super.onResume();
    this.c = true;
    if (e())
      ((BaseApplication)getApplication()).b(this);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (e())
      ((BaseApplication)getApplication()).b(this, paramBundle);
  }

  protected void onStart()
  {
    super.onStart();
    this.d = true;
    if (e())
      ((BaseApplication)getApplication()).a(this);
  }

  protected void onStop()
  {
    super.onStop();
    this.d = false;
    if (e())
      ((BaseApplication)getApplication()).d(this);
  }

  protected void onUserLeaveHint()
  {
    super.onUserLeaveHint();
    if (e())
      ((BaseApplication)getApplication()).f(this);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.app.BaseActivity
 * JD-Core Version:    0.6.0
 */