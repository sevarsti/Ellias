package com.tencent.component.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.tencent.component.annotation.PluginApi;

@PluginApi(a=4)
public class BaseFragment extends Fragment
{
  private Application a;
  private Thread b = Looper.getMainLooper().getThread();
  private Handler c = new Handler(Looper.getMainLooper());
  private Toast d;

  private boolean a()
  {
    return this.a instanceof BaseApplication;
  }

  private Toast b()
  {
    if (this.d != null)
      return this.d;
    monitorenter;
    try
    {
      if (this.d != null)
      {
        Toast localToast2 = this.d;
        return localToast2;
      }
    }
    finally
    {
      monitorexit;
    }
    Toast localToast1 = Toast.makeText(getActivity(), null, 0);
    this.d = localToast1;
    monitorexit;
    return localToast1;
  }

  public void a(int paramInt)
  {
    a(paramInt, 81);
  }

  public void a(int paramInt1, int paramInt2)
  {
    if (paramInt1 == 0);
    for (String str = null; ; str = getString(paramInt1))
    {
      a(str, paramInt2);
      return;
    }
  }

  public void a(String paramString)
  {
    a(paramString, 81);
  }

  public void a(String paramString, int paramInt)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return;
    if (isMainThread())
    {
      Toast localToast = b();
      localToast.setText(paramString);
      localToast.setGravity(paramInt, localToast.getXOffset(), localToast.getYOffset());
      localToast.show();
      return;
    }
    runOnUiThread(new a(this, paramString, paramInt));
  }

  @PluginApi(a=4)
  public final boolean isMainThread()
  {
    return this.b == Thread.currentThread();
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    if (paramActivity != null)
    {
      this.a = paramActivity.getApplication();
      if (a())
        ((BaseApplication)this.a).a(this, paramActivity);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (a())
      ((BaseApplication)this.a).a(this, paramBundle);
  }

  public void onDestroy()
  {
    super.onDestroy();
    if (a())
      ((BaseApplication)this.a).e(this);
  }

  public void onDetach()
  {
    super.onDetach();
    if (a())
      ((BaseApplication)this.a).f(this);
  }

  public void onPause()
  {
    super.onPause();
    if (a())
      ((BaseApplication)this.a).c(this);
  }

  public void onResume()
  {
    super.onResume();
    if (a())
      ((BaseApplication)this.a).b(this);
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (a())
      ((BaseApplication)this.a).b(this, paramBundle);
  }

  public void onStart()
  {
    super.onStart();
    if (a())
      ((BaseApplication)this.a).a(this);
  }

  public void onStop()
  {
    super.onStop();
    if (a())
      ((BaseApplication)this.a).d(this);
  }

  @PluginApi(a=4)
  public final void post(Runnable paramRunnable)
  {
    this.c.post(paramRunnable);
  }

  @PluginApi(a=4)
  public final void postDelayed(Runnable paramRunnable, long paramLong)
  {
    this.c.postDelayed(paramRunnable, paramLong);
  }

  @PluginApi(a=4)
  public final void runOnUiThread(Runnable paramRunnable)
  {
    if (!isMainThread())
    {
      this.c.post(paramRunnable);
      return;
    }
    paramRunnable.run();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.app.BaseFragment
 * JD-Core Version:    0.6.0
 */