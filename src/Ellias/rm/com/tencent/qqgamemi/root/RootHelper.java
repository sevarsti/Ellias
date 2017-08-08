package com.tencent.qqgamemi.root;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.QMiService;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class RootHelper
{
  private static final String a = RootHelper.class.getSimpleName();
  private static boolean b = false;
  private static Process c;
  private static DataOutputStream d;
  private static DataInputStream e;
  private static String f = "RootHelper";
  private static String g = "echo " + f + "\n";

  private static void a()
  {
    if (!b);
    try
    {
      if (d != null)
        d.close();
      if (e != null)
        e.close();
      if (c != null)
        c.destroy();
      localProcessBuilder = new ProcessBuilder(new String[] { "su" });
      localProcessBuilder.redirectErrorStream(false);
    }
    catch (IOException localIOException1)
    {
      try
      {
        ProcessBuilder localProcessBuilder;
        c = localProcessBuilder.start();
        if (c != null)
        {
          d = new DataOutputStream(c.getOutputStream());
          e = new DataInputStream(c.getInputStream());
        }
        b();
        return;
        localIOException1 = localIOException1;
        localIOException1.printStackTrace();
      }
      catch (IOException localIOException2)
      {
        while (true)
          localIOException2.printStackTrace();
      }
    }
  }

  private static void a(Context paramContext, String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2)
  {
    b(paramContext, paramString1, paramString2, paramOnClickListener1, paramOnClickListener2).show();
  }

  private static RootAlertDialog b(Context paramContext, String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2)
  {
    RootAlertDialog.Builder localBuilder = new RootAlertDialog.Builder(paramContext);
    localBuilder.a(paramString1);
    localBuilder.a(paramString2);
    localBuilder.a(ResourceUtil.b("qmi_root_dialog_go"), paramOnClickListener1);
    localBuilder.b(ResourceUtil.b("qmi_root_dialog_cancel"), paramOnClickListener2);
    return localBuilder.a();
  }

  private static void b()
  {
    String str1 = executeRootCmds(new String[] { "id" });
    if ((!TextUtils.isEmpty(str1)) && (str1.contains("uid=0")))
      b = true;
    String str2 = a;
    StringBuilder localStringBuilder = new StringBuilder().append("requestAndSetRootPermission ");
    if (b);
    for (String str3 = "success"; ; str3 = "failed")
    {
      Log.d(str2, str3);
      return;
    }
  }

  @PluginApi(a=9)
  public static String executeRootCmds(String[] paramArrayOfString)
  {
    monitorenter;
    if (paramArrayOfString != null);
    try
    {
      String str1;
      if (paramArrayOfString.length == 0)
        str1 = "";
      while (true)
      {
        return str1;
        StringBuilder localStringBuilder1 = new StringBuilder();
        StringBuilder localStringBuilder2 = new StringBuilder();
        for (int i = 0; i < paramArrayOfString.length; i++)
        {
          localStringBuilder2.append(paramArrayOfString[i]);
          localStringBuilder2.append(";");
        }
        localStringBuilder2.append(g);
        Log.d(a, "executeRootCmds:" + localStringBuilder2);
        try
        {
          d.writeBytes(localStringBuilder2.toString());
          d.flush();
          while (true)
          {
            String str2 = e.readLine();
            if (str2 != null)
            {
              if (TextUtils.isEmpty(str2))
                continue;
              if (!str2.contains(f));
            }
            else
            {
              Log.d(a, "dataInputStream:" + localStringBuilder1.toString());
              str1 = localStringBuilder1.toString();
              break;
            }
            localStringBuilder1.append(str2);
          }
        }
        catch (Exception localException)
        {
          Log.e(a, "Unexpected error - Here is what I know: " + localException.getMessage());
          str1 = "";
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @PluginApi(a=9)
  public static boolean isRoot()
  {
    a();
    return b;
  }

  @PluginApi(a=9)
  public static boolean isRootWithOutRequest()
  {
    return b;
  }

  @PluginApi(a=9)
  public static void showAlertDialog(String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2)
  {
    a(QMiService.a(), paramString1, paramString2, paramOnClickListener1, paramOnClickListener2);
  }

  @PluginApi(a=9)
  public static void showRootAlertDialog(String paramString1, String paramString2)
  {
    a(QMiService.a(), paramString1, paramString2, null, null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.root.RootHelper
 * JD-Core Version:    0.6.0
 */