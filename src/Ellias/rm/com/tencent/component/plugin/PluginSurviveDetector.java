package com.tencent.component.plugin;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.utils.log.LogUtil;

@PluginApi(a=16)
public abstract class PluginSurviveDetector
{
  private static final String a = "PluginSurviveDetector";

  static PluginSurviveDetector a(Context paramContext, PluginInfo paramPluginInfo)
  {
    if (paramPluginInfo == null);
    String str;
    while (true)
    {
      return null;
      str = paramPluginInfo.c;
      if (TextUtils.isEmpty(str))
        continue;
      try
      {
        Class localClass = PluginClassLoader.a(paramContext, paramPluginInfo).a(str);
        LogUtil.d("PluginSurviveDetector", "new survive detector for " + paramPluginInfo.pluginId + " " + paramPluginInfo.installPath);
        if (localClass == null)
          continue;
        PluginSurviveDetector localPluginSurviveDetector = (PluginSurviveDetector)localClass.newInstance();
        return localPluginSurviveDetector;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new PluginSurviveDetector.InstantiationException("Unable to instantiate survive detector " + str + ": make sure class name exists, is public, and has an empty constructor that is public", localClassNotFoundException);
      }
      catch (InstantiationException localInstantiationException)
      {
        throw new PluginSurviveDetector.InstantiationException("Unable to instantiate survive detector " + str + ": make sure class name exists, is public, and has an empty constructor that is public", localInstantiationException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
      }
    }
    throw new PluginSurviveDetector.InstantiationException("Unable to instantiate survive detector " + str + ": make sure class name exists, is public, and has an empty constructor that is public", localIllegalAccessException);
  }

  @PluginApi(a=16)
  public abstract boolean isSurvivable();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginSurviveDetector
 * JD-Core Version:    0.6.0
 */