package com.tencent.component.plugin;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.utils.log.LogUtil;

@PluginApi(a=6)
public abstract class BootCompleteReceiver
{
  private static final String a = "BootCompleteReceiver";

  static BootCompleteReceiver a(Context paramContext, PluginInfo paramPluginInfo)
  {
    if (paramPluginInfo == null);
    String str;
    while (true)
    {
      return null;
      str = paramPluginInfo.b;
      if (TextUtils.isEmpty(str))
        continue;
      try
      {
        Class localClass = PluginClassLoader.a(paramContext, paramPluginInfo).a(str);
        LogUtil.d("BootCompleteReceiver", "new boot receiver for " + paramPluginInfo.pluginId + " " + paramPluginInfo.installPath);
        if (localClass == null)
          continue;
        BootCompleteReceiver localBootCompleteReceiver = (BootCompleteReceiver)localClass.newInstance();
        return localBootCompleteReceiver;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new BootCompleteReceiver.InstantiationException("Unable to instantiate boot receiver " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", localClassNotFoundException);
      }
      catch (InstantiationException localInstantiationException)
      {
        throw new BootCompleteReceiver.InstantiationException("Unable to instantiate boot receiver " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", localInstantiationException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
      }
    }
    throw new BootCompleteReceiver.InstantiationException("Unable to instantiate boot receiver " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", localIllegalAccessException);
  }

  @PluginApi(a=6)
  public abstract void onBootComplete(Context paramContext);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.BootCompleteReceiver
 * JD-Core Version:    0.6.0
 */