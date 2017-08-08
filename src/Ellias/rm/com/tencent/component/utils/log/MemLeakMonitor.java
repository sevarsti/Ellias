package com.tencent.component.utils.log;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.StrictMode;
import android.os.StrictMode.VmPolicy.Builder;
import com.tencent.component.app.BaseActivity;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.PlatformUtil;
import com.tencent.component.utils.collections.EqualWeakReference;
import com.tencent.component.utils.thread.HandlerUtils;
import java.util.ArrayList;
import java.util.List;

public class MemLeakMonitor
{
  private static final int a = 8000;
  private static MemLeakMonitor d = new MemLeakMonitor();
  private List b = new ArrayList();
  private List c = new ArrayList();
  private Handler e = new Handler(HandlerUtils.a());
  private Runnable f = new d(this);

  @SuppressLint({"NewAPI"})
  public static void a(Context paramContext)
  {
    if ((DebugUtil.a(paramContext)) && (PlatformUtil.version() >= 9))
      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().build());
  }

  public static void a(BaseActivity paramBaseActivity)
  {
    if ((DebugUtil.a(paramBaseActivity)) && (PlatformUtil.version() < 9))
      d.b.add(new EqualWeakReference(paramBaseActivity));
  }

  public static void b(BaseActivity paramBaseActivity)
  {
    if ((DebugUtil.a(paramBaseActivity)) && (PlatformUtil.version() < 9))
    {
      EqualWeakReference localEqualWeakReference = new EqualWeakReference(paramBaseActivity);
      d.b.remove(localEqualWeakReference);
      d.c.add(localEqualWeakReference);
      d.e.postDelayed(d.f, 8000L);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.log.MemLeakMonitor
 * JD-Core Version:    0.6.0
 */