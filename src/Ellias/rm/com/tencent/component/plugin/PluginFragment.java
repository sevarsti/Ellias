package com.tencent.component.plugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.app.BaseFragment;

@PluginApi(a=4)
public class PluginFragment extends BaseFragment
{
  private Plugin a;

  private Intent a(Class paramClass, Intent paramIntent)
  {
    if (paramClass == null)
      return null;
    FragmentActivity localFragmentActivity = getActivity();
    if (localFragmentActivity == null)
      throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    if (!(localFragmentActivity instanceof PluginShellActivity))
      throw new IllegalStateException("Fragment " + this + " not attached to correct Activity to perform this");
    PluginInfo localPluginInfo = ((PluginShellActivity)localFragmentActivity).f();
    if (localPluginInfo == null)
      throw new IllegalStateException("Fragment's Activity " + localFragmentActivity + " not prepared");
    return this.a.getPluginHelper().b(localFragmentActivity, localPluginInfo, paramClass.getName(), paramIntent);
  }

  @PluginApi(a=4)
  public static PluginFragment instantiate(Plugin paramPlugin, Bundle paramBundle)
  {
    return instantiate(paramPlugin, null, paramBundle);
  }

  @PluginApi(a=4)
  public static PluginFragment instantiate(Plugin paramPlugin, String paramString, Bundle paramBundle)
  {
    PluginFragment localPluginFragment = null;
    if (paramPlugin == null);
    while (true)
    {
      return localPluginFragment;
      PluginInfo localPluginInfo = paramPlugin.a();
      localPluginFragment = null;
      if (localPluginInfo == null)
        continue;
      if (TextUtils.isEmpty(paramString))
        paramString = localPluginInfo.i;
      boolean bool = TextUtils.isEmpty(paramString);
      localPluginFragment = null;
      if (bool)
        continue;
      try
      {
        localPluginFragment = (PluginFragment)paramPlugin.getClass().getClassLoader().loadClass(paramString).newInstance();
        localPluginFragment.a = paramPlugin;
        if (paramBundle == null)
          continue;
        paramBundle.setClassLoader(localPluginFragment.getClass().getClassLoader());
        localPluginFragment.setArguments(paramBundle);
        return localPluginFragment;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new PluginFragment.InstantiationException("Unable to instantiate fragment " + paramString + ": make sure class name exists, is public, and has an" + " empty constructor that is public", localClassNotFoundException);
      }
      catch (InstantiationException localInstantiationException)
      {
        throw new PluginFragment.InstantiationException("Unable to instantiate fragment " + paramString + ": make sure class name exists, is public, and has an" + " empty constructor that is public", localInstantiationException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
      }
    }
    throw new PluginFragment.InstantiationException("Unable to instantiate fragment " + paramString + ": make sure class name exists, is public, and has an" + " empty constructor that is public", localIllegalAccessException);
  }

  @PluginApi(a=4)
  protected boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return false;
  }

  @PluginApi(a=4)
  protected boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }

  @PluginApi(a=4)
  public Plugin getPlugin()
  {
    return this.a;
  }

  public void onAttach(Activity paramActivity)
  {
    if ((paramActivity != null) && ((paramActivity instanceof PluginShellActivity)))
      ((PluginShellActivity)paramActivity).a(this);
    super.onAttach(paramActivity);
  }

  @PluginApi(a=4)
  protected boolean onBackPressed()
  {
    return false;
  }

  @PluginApi(a=4)
  protected boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  @PluginApi(a=4)
  protected boolean onKeyLongPress(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  @PluginApi(a=4)
  protected boolean onKeyMultiple(int paramInt1, int paramInt2, KeyEvent paramKeyEvent)
  {
    return false;
  }

  @PluginApi(a=4)
  protected boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  @PluginApi(a=4)
  protected boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  @PluginApi(a=4)
  protected void onNewIntent(Intent paramIntent)
  {
  }

  @PluginApi(a=4)
  protected boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }

  @PluginApi(a=4)
  protected void onUserInteraction()
  {
  }

  @PluginApi(a=4)
  protected void onUserLeaveHint()
  {
  }

  @PluginApi(a=4)
  protected void onWindowFocusChanged(boolean paramBoolean)
  {
  }

  public void startActivity(Intent paramIntent)
  {
    Class localClass = PluginHelper.a(paramIntent, getClass().getClassLoader());
    if ((localClass != null) && (PluginFragment.class.isAssignableFrom(localClass)))
    {
      startActivity(localClass, paramIntent);
      return;
    }
    super.startActivity(paramIntent);
  }

  @PluginApi(a=4)
  public void startActivity(Class paramClass, Intent paramIntent)
  {
    Intent localIntent = a(paramClass, paramIntent);
    if (localIntent == null)
      return;
    startActivity(localIntent);
  }

  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    Class localClass = PluginHelper.a(paramIntent, getClass().getClassLoader());
    if ((localClass != null) && (localClass.isAssignableFrom(PluginFragment.class)))
    {
      startActivityForResult(localClass, paramIntent, paramInt);
      return;
    }
    super.startActivityForResult(paramIntent, paramInt);
  }

  @PluginApi(a=4)
  public void startActivityForResult(Class paramClass, Intent paramIntent, int paramInt)
  {
    Intent localIntent = a(paramClass, paramIntent);
    if (localIntent == null)
      return;
    startActivityForResult(localIntent, paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginFragment
 * JD-Core Version:    0.6.0
 */