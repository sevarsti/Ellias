package com.tencent.component.plugin.server;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.component.plugin.PluginInfo;
import com.tencent.component.plugin.PluginInfo.ExtraInfo;
import com.tencent.component.utils.ApkUtil;
import com.tencent.component.utils.ApkUtil.Certificates;
import com.tencent.component.utils.log.LogUtil;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class PluginParser
{
  public static final int a = 1;
  private static final String b = "PluginParser";

  private static int a(String paramString, int paramInt)
  {
    if (paramString != null);
    try
    {
      int i = Integer.parseInt(paramString);
      paramInt = i;
      return paramInt;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return paramInt;
  }

  static PluginInfo a(Context paramContext, String paramString)
  {
    return a(paramContext, paramString, 0);
  }

  static PluginInfo a(Context paramContext, String paramString, int paramInt)
  {
    if (TextUtils.isEmpty(paramString));
    PackageInfo localPackageInfo;
    ApplicationInfo localApplicationInfo;
    label23: PluginInfo localPluginInfo1;
    int i;
    while (true)
    {
      return null;
      localPackageInfo = b(paramContext, paramString, paramInt);
      if (localPackageInfo != null)
        break;
      localApplicationInfo = null;
      if ((localPackageInfo == null) || (localApplicationInfo == null))
        break label401;
      Bundle localBundle = b(paramContext, paramString);
      if (localBundle == null)
        continue;
      localPluginInfo1 = new PluginInfo();
      localPluginInfo1.a = a(localBundle, "qqgame.plugin.class");
      localPluginInfo1.i = a(localBundle, "qqgame.plugin.launch.fragment");
      localPluginInfo1.b = a(localBundle, "qqgame.plugin.boot.receiver");
      localPluginInfo1.c = a(localBundle, "qqgame.plugin.survive.detector");
      localPluginInfo1.n = a(a(localBundle, "qqgame.plugin.exclusive"), false);
      localPluginInfo1.d = a(a(localBundle, "qqgame.plugin.requires"), " ");
      localPluginInfo1.minPlatformVersion = a(a(localBundle, "qqgame.plugin.minPlatformVersion"), 0);
      localPluginInfo1.maxPlatformVersion = a(a(localBundle, "qqgame.plugin.maxPlatformVersion"), 0);
      localPluginInfo1.minAndroidVersion = a(a(localBundle, "qqgame.plugin.minAndroidVersion"), 0);
      localPluginInfo1.maxAndroidVersion = a(a(localBundle, "qqgame.plugin.maxAndroidVersion"), 0);
      localPluginInfo1.pluginId = localApplicationInfo.packageName;
      localPluginInfo1.version = localPackageInfo.versionCode;
      localPluginInfo1.versionName = localPackageInfo.versionName;
      localPluginInfo1.e = a(paramContext, localApplicationInfo, paramString);
      localPluginInfo1.f = localApplicationInfo.icon;
      localPluginInfo1.g = localApplicationInfo.theme;
      localPluginInfo1.h = localPackageInfo.signatures;
      Integer localInteger = (Integer)a(PluginConstant.t, a(localBundle, "qqgame.plugin.extra.singleTop"), Integer.valueOf(0));
      PluginInfo.ExtraInfo localExtraInfo = localPluginInfo1.k;
      if (localInteger == null)
        break label403;
      i = localInteger.intValue();
      label308: localExtraInfo.d = i;
      localPluginInfo1.k.e = a(a(localBundle, "qqgame.plugin.extra.singleProcess"), false);
      localPluginInfo1.k.g = a(a(localBundle, "qqgame.plugin.extra.autoLoad"), false);
      localPluginInfo1.installPath = paramString;
      localPluginInfo1.nativeLibraryDir = PluginConstant.a(paramContext, localPluginInfo1);
      localPluginInfo1.dexOptimizeDir = PluginConstant.b(paramContext);
      if (!a(localPluginInfo1))
        break label409;
    }
    label401: label403: label409: for (PluginInfo localPluginInfo2 = localPluginInfo1; ; localPluginInfo2 = null)
    {
      return localPluginInfo2;
      localApplicationInfo = localPackageInfo.applicationInfo;
      break label23;
      break;
      i = 0;
      break label308;
    }
  }

  private static Boolean a(String paramString, Boolean paramBoolean)
  {
    if (!a(paramString))
      paramBoolean = Boolean.valueOf(paramString);
    return paramBoolean;
  }

  private static Object a(Map paramMap, Object paramObject1, Object paramObject2)
  {
    if ((paramMap == null) || (paramObject1 == null));
    Object localObject;
    do
    {
      return paramObject2;
      localObject = paramMap.get(paramObject1);
    }
    while (localObject == null);
    return localObject;
  }

  private static String a(Context paramContext, ApplicationInfo paramApplicationInfo, String paramString)
  {
    try
    {
      String str1 = ApkUtil.a(paramContext, paramString).getString(paramApplicationInfo.labelRes);
      if (!TextUtils.isEmpty(str1))
        return str1;
      CharSequence localCharSequence = paramContext.getPackageManager().getApplicationLabel(paramApplicationInfo);
      if (localCharSequence != null)
      {
        String str2 = localCharSequence.toString();
        return str2;
      }
    }
    catch (Exception localException)
    {
      LogUtil.e("PluginParser", localException.getMessage(), localException);
    }
    return null;
  }

  private static String a(Bundle paramBundle, String paramString)
  {
    if ((paramBundle == null) || (TextUtils.isEmpty(paramString)))
      return null;
    return paramBundle.getString(paramString);
  }

  private static boolean a(PluginInfo paramPluginInfo)
  {
    return (paramPluginInfo != null) && (!TextUtils.isEmpty(paramPluginInfo.installPath)) && (!TextUtils.isEmpty(paramPluginInfo.a)) && (!TextUtils.isEmpty(paramPluginInfo.pluginId));
  }

  private static boolean a(CharSequence paramCharSequence)
  {
    return (paramCharSequence == null) || (paramCharSequence.length() == 0);
  }

  private static boolean a(String paramString, boolean paramBoolean)
  {
    if (!a(paramString))
      paramBoolean = Boolean.parseBoolean(paramString);
    return paramBoolean;
  }

  private static String[] a(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return null;
    return paramString1.split(paramString2);
  }

  public static PackageInfo b(Context paramContext, String paramString, int paramInt)
  {
    PackageInfo localPackageInfo;
    if (TextUtils.isEmpty(paramString))
      localPackageInfo = null;
    do
    {
      return localPackageInfo;
      if ((paramInt & 0x1) != 0);
      localPackageInfo = ApkUtil.a(paramContext, paramString, 0);
    }
    while ((localPackageInfo == null) || ((paramInt & 0x1) == 0));
    localPackageInfo.signatures = ApkUtil.Certificates.a(paramString, true);
    return localPackageInfo;
  }

  private static Bundle b(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    Resources localResources;
    do
    {
      return null;
      localResources = ApkUtil.a(paramContext, paramString);
    }
    while (localResources == null);
    try
    {
      j localj = new j();
      XMLReader localXMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
      localXMLReader.setContentHandler(localj);
      localXMLReader.parse(new InputSource(localResources.getAssets().open("plugin.xml")));
      Bundle localBundle = localj.a();
      return localBundle;
    }
    catch (Throwable localThrowable)
    {
      LogUtil.e("PluginParser", "fail to parse meta-data for " + paramString, localThrowable);
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.PluginParser
 * JD-Core Version:    0.6.0
 */