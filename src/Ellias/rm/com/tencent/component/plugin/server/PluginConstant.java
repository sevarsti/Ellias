package com.tencent.component.plugin.server;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.plugin.PluginInfo;
import com.tencent.component.utils.SecurityUtil;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

public class PluginConstant
{
  static final String A = "lib";
  public static final String B = "intent_plugin";
  public static final String C = "plugin_inner";
  public static final String D = "plugin_fragment";
  public static final String E = "plugin_platform_id";
  public static final String F = "plugin_data";
  static final Uri G;
  public static final int H = 2147483647;
  private static final String I = "qqgame.plugin.";
  private static final String J = ".dex";
  private static final boolean K = true;
  private static final String L = "config.xml";
  private static final String M = "plugins";
  public static final int a = 150;
  public static final int b = 25;
  public static final String c = "2.2.1.25";
  public static final String d = "plugin_platform_initialize_start";
  public static final String e = "plugin_platform_initialize_finish";
  static final String f = "plugin.xml";
  static final String g = "qqgame.plugin.class";
  static final String h = "qqgame.plugin.boot.receiver";
  static final String i = "qqgame.plugin.launch.fragment";
  static final String j = "qqgame.plugin.survive.detector";
  static final String k = "qqgame.plugin.exclusive";
  static final String l = "qqgame.plugin.requires";
  static final String m = "qqgame.plugin.minPlatformVersion";
  static final String n = "qqgame.plugin.maxPlatformVersion";
  static final String o = "qqgame.plugin.minAndroidVersion";
  static final String p = "qqgame.plugin.maxAndroidVersion";
  static final String q = "qqgame.plugin.extra.singleTop";
  static final String r = "qqgame.plugin.extra.singleProcess";
  static final String s = "qqgame.plugin.extra.autoLoad";
  static final HashMap t = new PluginConstant.1();
  static final String u = "plugins_installed";
  public static final String v = "plugins_pending";
  static final String w = ".zip";
  static final boolean x = true;
  static final String y = "plugins_extra";
  static final String z = "dex_opt";

  static
  {
    G = null;
  }

  static File a(Context paramContext)
  {
    return paramContext.getDir("plugins_extra", 0);
  }

  static FilenameFilter a(String paramString)
  {
    return new d(b(paramString));
  }

  static String a(Context paramContext, PluginInfo paramPluginInfo)
  {
    String str = c(paramPluginInfo);
    if (!c(str))
      return a(paramContext).getAbsolutePath() + File.separator + "lib" + File.separator + str;
    return null;
  }

  static String a(PluginInfo paramPluginInfo)
  {
    String str = c(paramPluginInfo);
    if (!c(str))
      return str + ".zip";
    return null;
  }

  static String a(c paramc)
  {
    return "plugins/" + paramc.b() + "/" + "config.xml";
  }

  static File b(c paramc)
  {
    return paramc.a().getDir("plugins_installed_" + paramc.b(), 0);
  }

  static String b(Context paramContext)
  {
    return a(paramContext).getAbsolutePath() + File.separator + "dex_opt";
  }

  static String b(PluginInfo paramPluginInfo)
  {
    String str = c(paramPluginInfo);
    return str + ".dex";
  }

  private static String b(String paramString)
  {
    if (c(paramString))
      return null;
    return SecurityUtil.a(paramString);
  }

  static File c(c paramc)
  {
    Context localContext = paramc.a();
    String str = "plugins_pending_" + paramc.b();
    if (!d(localContext));
    for (int i1 = 0; ; i1 = 2)
      return localContext.getDir(str, i1);
  }

  private static String c(PluginInfo paramPluginInfo)
  {
    if (paramPluginInfo == null);
    for (String str = null; ; str = paramPluginInfo.pluginId)
      return b(str);
  }

  static boolean c(Context paramContext)
  {
    return true;
  }

  private static boolean c(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  static File d(c paramc)
  {
    String str = CacheManager.b(paramc.a(), "plugins_pending_" + paramc.b());
    if (!TextUtils.isEmpty(str))
      return new File(str);
    return null;
  }

  private static boolean d(Context paramContext)
  {
    ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo();
    return (localApplicationInfo != null) && ((0x2 & localApplicationInfo.flags) != 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.PluginConstant
 * JD-Core Version:    0.6.0
 */