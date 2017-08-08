package com.tencent.component.plugin;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.PluginFragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import com.tencent.component.utils.log.LogUtil;

public class PluginShellActivity extends PluginFragmentActivity
{
  private static final String q = "PluginShellActivity";
  private static final String r = "state_plugin_info";
  private static final String s = "state_plugin_platform_id";
  private PluginManager.PluginListener A;
  private String B;
  private boolean C = false;
  private boolean D = false;
  private PluginInfo t;
  private Plugin u;
  private ClassLoader v;
  private Resources w;
  private Resources.Theme x;
  private LayoutInflater y;
  private int z;

  private PluginInfo a(Intent paramIntent)
  {
    if (paramIntent == null)
      return null;
    if (paramIntent.hasExtra("plugin_inner"));
    for (PluginInfo localPluginInfo = (PluginInfo)paramIntent.getParcelableExtra("plugin_inner"); ; localPluginInfo = null)
    {
      String str1;
      String str2;
      if (localPluginInfo == null)
      {
        str1 = paramIntent.getStringExtra("intent_plugin");
        str2 = b(paramIntent);
        if (!TextUtils.isEmpty(str2))
          if (str1 != null)
            break label59;
      }
      label59: for (localPluginInfo = null; ; localPluginInfo = PluginManager.a(this, str2).f(str1))
        return localPluginInfo;
    }
  }

  private static boolean a(int paramInt)
  {
    return paramInt >>> 24 == 1;
  }

  private boolean a(Bundle paramBundle)
  {
    if (paramBundle == null)
      this.B = b(getIntent());
    for (Object localObject = a(getIntent()); TextUtils.isEmpty(this.B); localObject = (PluginInfo)paramBundle.getParcelable("state_plugin_info"))
    {
      return false;
      this.B = paramBundle.getString("state_plugin_platform_id");
    }
    Plugin localPlugin;
    StringBuilder localStringBuilder;
    if (localObject == null)
    {
      localPlugin = null;
      if ((localObject != null) && (localPlugin != null))
        break label136;
      localStringBuilder = new StringBuilder().append("fail to init plugin for ");
      if (localObject == null)
        break label124;
    }
    while (true)
    {
      LogUtil.i("PluginShellActivity", localObject);
      return false;
      localPlugin = PluginManager.a(this, this.B).a((PluginInfo)localObject);
      break;
      label124: localObject = c(getIntent());
    }
    label136: if ((a(((PluginInfo)localObject).g)) || ((a((PluginInfo)localObject)) && (((PluginInfo)localObject).g != 0)))
      setTheme(((PluginInfo)localObject).g);
    localPlugin.b();
    this.t = ((PluginInfo)localObject);
    this.u = localPlugin;
    this.v = localPlugin.getClass().getClassLoader();
    return true;
  }

  private static boolean a(PluginInfo paramPluginInfo)
  {
    return (paramPluginInfo != null) && (paramPluginInfo.a());
  }

  private String b(Intent paramIntent)
  {
    if (paramIntent == null)
      return null;
    return paramIntent.getStringExtra("plugin_platform_id");
  }

  private boolean b(Bundle paramBundle)
  {
    if (paramBundle != null)
      return true;
    Plugin localPlugin = this.u;
    Intent localIntent = getIntent();
    String str = d(localIntent);
    Bundle localBundle = e(localIntent);
    try
    {
      PluginFragment localPluginFragment2 = PluginFragment.instantiate(localPlugin, str, localBundle);
      localPluginFragment1 = localPluginFragment2;
      if (localPluginFragment1 == null)
      {
        LogUtil.i("PluginShellActivity", "fail to init plugin fragment for " + this.t);
        return false;
      }
      getSupportFragmentManager().beginTransaction().replace(16908290, localPluginFragment1).commit();
      getSupportFragmentManager().executePendingTransactions();
      return true;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        PluginFragment localPluginFragment1 = null;
    }
  }

  private String c(Intent paramIntent)
  {
    if (paramIntent == null)
      return null;
    return paramIntent.getStringExtra("intent_plugin");
  }

  private String d(Intent paramIntent)
  {
    if (paramIntent == null)
      return null;
    return paramIntent.getStringExtra("plugin_fragment");
  }

  private Bundle e(Intent paramIntent)
  {
    if (paramIntent == null)
      return null;
    return paramIntent.getBundleExtra("plugin_data");
  }

  private void f(Intent paramIntent)
  {
    int i = 1;
    if (paramIntent == null)
      throw new IllegalArgumentException("invalid intent null");
    PluginInfo localPluginInfo = this.t;
    PluginFragment localPluginFragment = i();
    String str1 = c(paramIntent);
    String str2 = d(paramIntent);
    Object localObject = a(paramIntent);
    if ((localObject == null) && (str1 != null) && (str1.equals(localPluginInfo.pluginId)))
      localObject = localPluginInfo;
    if ((localObject == null) || (str2 == null))
    {
      StringBuilder localStringBuilder = new StringBuilder().append("invalid plugin ");
      if (localObject != null);
      while (true)
      {
        throw new IllegalArgumentException(localObject + ", or fragment " + str2);
        localObject = str1;
      }
    }
    int j = ((PluginInfo)localObject).k.d;
    int k;
    int m;
    if ((j == i) || (j == 2))
    {
      k = i;
      if (j != i)
        break label253;
      if ((!((PluginInfo)localObject).pluginId.equals(localPluginInfo.pluginId)) || (localPluginFragment == null))
        break label247;
      m = i;
    }
    Intent localIntent;
    while (true)
    {
      if (k == 0)
        break label334;
      localIntent = new Intent(paramIntent);
      if (m == 0)
        break label312;
      Bundle localBundle = e(localIntent);
      if (localBundle != null)
        localIntent = localIntent.replaceExtras(localBundle);
      localPluginFragment.onNewIntent(localIntent);
      return;
      k = 0;
      break;
      label247: m = 0;
      continue;
      label253: if (j == 2)
      {
        if ((((PluginInfo)localObject).pluginId.equals(localPluginInfo.pluginId)) && (localPluginFragment != null) && (str2.equals(localPluginFragment.getClass().getName())));
        while (true)
        {
          m = i;
          break;
          i = 0;
        }
      }
      m = 0;
    }
    label312: localIntent.setFlags(0xDFFFFFFF & localIntent.getFlags());
    startActivity(localIntent);
    return;
    label334: throw new IllegalArgumentException("new intent is not permitted for plugin " + localObject);
  }

  private boolean g()
  {
    if (this.D)
      return true;
    this.D = h();
    return this.D;
  }

  private boolean h()
  {
    PluginInfo localPluginInfo = this.t;
    Plugin localPlugin = this.u;
    if (a(localPluginInfo))
      return true;
    this.y = new m(this, localPlugin);
    this.w = PluginManager.a(this, this.B).b(localPluginInfo);
    if (this.w == null)
    {
      LogUtil.i("PluginShellActivity", "fail to init plugin resources for " + localPluginInfo);
      return false;
    }
    this.x = this.w.newTheme();
    j();
    if (localPluginInfo.g != 0);
    for (int i = localPluginInfo.g; ; i = this.z)
    {
      Resources.Theme localTheme = getBaseContext().getTheme();
      if (localTheme != null)
        this.x.setTo(localTheme);
      if (i != 0)
        this.x.applyStyle(i, true);
      return true;
    }
  }

  private PluginFragment i()
  {
    Fragment localFragment = getSupportFragmentManager().findFragmentById(16908290);
    if ((localFragment != null) && ((localFragment instanceof PluginFragment)))
      return (PluginFragment)localFragment;
    return null;
  }

  private void j()
  {
    if (this.z == 0)
      super.getTheme();
  }

  private void k()
  {
    if (this.A == null)
    {
      this.A = new an(this);
      PluginManager.a(this, this.B).a(this.A);
    }
  }

  private void l()
  {
    if (this.A != null)
      PluginManager.a(this, this.B).b(this.A);
  }

  void a(Fragment paramFragment)
  {
    if ((!this.C) && (this.t != null) && (this.u != null))
      g();
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    PluginFragment localPluginFragment = i();
    if ((localPluginFragment != null) && (localPluginFragment.dispatchKeyEvent(paramKeyEvent)))
      return true;
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    PluginFragment localPluginFragment = i();
    if ((localPluginFragment != null) && (localPluginFragment.dispatchTouchEvent(paramMotionEvent)))
      return true;
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  public Context e()
  {
    return PluginManager.a(this, this.B).e();
  }

  final PluginInfo f()
  {
    return this.t;
  }

  public AssetManager getAssets()
  {
    Resources localResources = getResources();
    if (localResources != null);
    for (AssetManager localAssetManager = localResources.getAssets(); localAssetManager != null; localAssetManager = null)
      return localAssetManager;
    return super.getAssets();
  }

  public ClassLoader getClassLoader()
  {
    if (this.v != null)
      return this.v;
    return super.getClassLoader();
  }

  public LayoutInflater getLayoutInflater()
  {
    if (this.y != null)
      return this.y;
    return super.getLayoutInflater();
  }

  public Resources getResources()
  {
    if (this.w != null)
      return this.w;
    return super.getResources();
  }

  public Resources.Theme getTheme()
  {
    if (this.x != null)
      return this.x;
    return super.getTheme();
  }

  protected Fragment newFragment(String paramString, Bundle paramBundle)
  {
    Plugin localPlugin = this.u;
    if (localPlugin == null)
      return null;
    return PluginFragment.instantiate(localPlugin, paramString, paramBundle);
  }

  protected void onApplyThemeResource(Resources.Theme paramTheme, int paramInt, boolean paramBoolean)
  {
    super.onApplyThemeResource(paramTheme, paramInt, paramBoolean);
    if ((paramBoolean) && (this.z == 0))
      this.z = paramInt;
  }

  public void onAttachFragment(Fragment paramFragment)
  {
    super.onAttachFragment(paramFragment);
    if ((!this.C) && (this.t != null) && (this.u != null))
      g();
  }

  public void onBackPressed()
  {
    PluginFragment localPluginFragment = i();
    if ((localPluginFragment != null) && (localPluginFragment.onBackPressed()))
      return;
    super.onBackPressed();
  }

  protected void onCreate(Bundle paramBundle)
  {
    boolean bool = a(paramBundle);
    super.onCreate(paramBundle);
    if (!bool)
    {
      finish();
      return;
    }
    if (!g())
    {
      finish();
      return;
    }
    if (!b(paramBundle))
    {
      finish();
      return;
    }
    k();
    this.C = true;
  }

  protected void onDestroy()
  {
    super.onDestroy();
    l();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    PluginFragment localPluginFragment = i();
    if ((localPluginFragment != null) && (localPluginFragment.onKeyDown(paramInt, paramKeyEvent)))
      return true;
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public boolean onKeyLongPress(int paramInt, KeyEvent paramKeyEvent)
  {
    PluginFragment localPluginFragment = i();
    if ((localPluginFragment != null) && (localPluginFragment.onKeyLongPress(paramInt, paramKeyEvent)))
      return true;
    return super.onKeyLongPress(paramInt, paramKeyEvent);
  }

  public boolean onKeyMultiple(int paramInt1, int paramInt2, KeyEvent paramKeyEvent)
  {
    PluginFragment localPluginFragment = i();
    if ((localPluginFragment != null) && (localPluginFragment.onKeyMultiple(paramInt1, paramInt2, paramKeyEvent)))
      return true;
    return super.onKeyMultiple(paramInt1, paramInt2, paramKeyEvent);
  }

  public boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    PluginFragment localPluginFragment = i();
    if ((localPluginFragment != null) && (localPluginFragment.onKeyShortcut(paramInt, paramKeyEvent)))
      return true;
    return super.onKeyShortcut(paramInt, paramKeyEvent);
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    PluginFragment localPluginFragment = i();
    if ((localPluginFragment != null) && (localPluginFragment.onKeyUp(paramInt, paramKeyEvent)))
      return true;
    return super.onKeyUp(paramInt, paramKeyEvent);
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    f(paramIntent);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putString("state_plugin_platform_id", this.B);
    paramBundle.putParcelable("state_plugin_info", this.t);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    PluginFragment localPluginFragment = i();
    if ((localPluginFragment != null) && (localPluginFragment.onTouchEvent(paramMotionEvent)))
      return true;
    return super.onTouchEvent(paramMotionEvent);
  }

  public void onUserInteraction()
  {
    super.onUserInteraction();
    PluginFragment localPluginFragment = i();
    if (localPluginFragment != null)
      localPluginFragment.onUserInteraction();
  }

  protected void onUserLeaveHint()
  {
    super.onUserLeaveHint();
    PluginFragment localPluginFragment = i();
    if (localPluginFragment != null)
      localPluginFragment.onUserLeaveHint();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    PluginFragment localPluginFragment = i();
    if (localPluginFragment != null)
      localPluginFragment.onWindowFocusChanged(paramBoolean);
  }

  public void setTheme(int paramInt)
  {
    if (this.x != null)
    {
      this.x.applyStyle(paramInt, true);
      return;
    }
    j();
    super.setTheme(paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginShellActivity
 * JD-Core Version:    0.6.0
 */