package com.pay.buyManager;

import android.content.Context;
import android.content.Intent;
import com.pay.common.tool.APLog;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class APWechatPayAPI
{
  private Class a;
  private Object b;

  public APWechatPayAPI(Context paramContext)
  {
    try
    {
      this.a = Class.forName("com.pay.buyManager.APWechatPaySDK");
      Class[] arrayOfClass = { Context.class };
      Object[] arrayOfObject = { paramContext };
      this.b = this.a.getDeclaredConstructor(arrayOfClass).newInstance(arrayOfObject);
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      APLog.i("APWechatPayAPI", "UnipayWechatSDK is lost");
      localClassNotFoundException.printStackTrace();
      return;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return;
    }
    catch (InstantiationException localInstantiationException)
    {
      localInstantiationException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
  }

  // ERROR //
  public boolean getWechatCanUse()
  {
    // Byte code:
    //   0: ldc 29
    //   2: invokestatic 35	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   5: pop
    //   6: ldc 74
    //   8: invokestatic 35	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   11: pop
    //   12: iconst_1
    //   13: ireturn
    //   14: astore_1
    //   15: ldc 76
    //   17: ldc 78
    //   19: invokestatic 61	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   22: iconst_0
    //   23: ireturn
    //   24: astore_3
    //   25: ldc 76
    //   27: ldc 80
    //   29: invokestatic 61	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   32: iconst_0
    //   33: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	6	14	java/lang/ClassNotFoundException
    //   6	12	24	java/lang/ClassNotFoundException
  }

  public void handleIntent(Intent paramIntent)
  {
    if ((this.a == null) || (this.b == null))
      return;
    try
    {
      this.a.getDeclaredMethod("handleIntent", new Class[] { Intent.class }).invoke(this.b, new Object[] { paramIntent });
      return;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
  }

  public boolean isInstallWechat()
  {
    if ((this.a == null) || (this.b == null))
      return true;
    try
    {
      boolean bool = ((Boolean)this.a.getMethod("isInstallWechat", new Class[0]).invoke(this.b, new Object[0])).booleanValue();
      return bool;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
      return false;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      while (true)
        localNoSuchMethodException.printStackTrace();
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
        localIllegalArgumentException.printStackTrace();
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
        localIllegalAccessException.printStackTrace();
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      while (true)
        localInvocationTargetException.printStackTrace();
    }
  }

  public boolean isSupportWechatAPI()
  {
    if ((this.a == null) || (this.b == null))
      return true;
    try
    {
      boolean bool = ((Boolean)this.a.getMethod("isSupportWechatAPI", new Class[0]).invoke(this.b, new Object[0])).booleanValue();
      return bool;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
      return false;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      while (true)
        localNoSuchMethodException.printStackTrace();
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
        localIllegalArgumentException.printStackTrace();
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
        localIllegalAccessException.printStackTrace();
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      while (true)
        localInvocationTargetException.printStackTrace();
    }
  }

  public void toWeChat(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    if ((this.a == null) || (this.b == null))
    {
      APLog.e("APWechatPayAPI", "toWeChat null");
      return;
    }
    try
    {
      Class[] arrayOfClass = { String.class, String.class, String.class, String.class, String.class, String.class, String.class };
      Object[] arrayOfObject = { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7 };
      this.a.getDeclaredMethod("toWeChat", arrayOfClass).invoke(this.b, arrayOfObject);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.APWechatPayAPI
 * JD-Core Version:    0.6.0
 */