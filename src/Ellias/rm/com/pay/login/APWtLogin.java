package com.pay.login;

import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class APWtLogin
{
  private Context a;
  private APWtLoginListener b;
  private Class c;
  private Class d;
  private Object e;
  private Object f;

  public APWtLogin(Context paramContext, APWtLoginListener paramAPWtLoginListener)
  {
    this.a = paramContext;
    this.b = paramAPWtLoginListener;
    if (a());
    try
    {
      Class[] arrayOfClass1 = new Class[1];
      arrayOfClass1[0] = Class.forName("com.paylogin.sdk.IPayLoginCallBack");
      this.e = Proxy.newProxyInstance(this.d.getClassLoader(), arrayOfClass1, new APWtLogin.MyInvocationHandler(this));
      Class[] arrayOfClass2 = new Class[2];
      arrayOfClass2[0] = Context.class;
      arrayOfClass2[1] = this.d;
      Constructor localConstructor = this.c.getDeclaredConstructor(arrayOfClass2);
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.a;
      arrayOfObject[1] = this.e;
      this.f = localConstructor.newInstance(arrayOfObject);
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
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

  private boolean a()
  {
    try
    {
      this.c = Class.forName("com.paylogin.sdk.payLoginSDK");
      this.d = Class.forName("com.paylogin.sdk.IPayLoginCallBack");
      return true;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
    }
    return false;
  }

  public void checkVerifyCode(String paramString)
  {
    try
    {
      this.c.getMethod("checkVerifyCode", new Class[] { String.class }).invoke(this.f, new Object[] { paramString });
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
  }

  public void clearLoginData()
  {
    try
    {
      this.c.getMethod("clearLoginData", new Class[0]).invoke(this.f, new Object[0]);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
  }

  public String getLastUin()
  {
    try
    {
      String str = String.valueOf(this.c.getMethod("getLastUin", new Class[0]).invoke(this.f, new Object[0]));
      return str;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return "";
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return "";
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
    return "";
  }

  public byte[] getVerifyCodeImg(String paramString)
  {
    try
    {
      byte[] arrayOfByte = (byte[])this.c.getMethod("getVerifyCodeImg", new Class[] { String.class }).invoke(this.f, new Object[] { paramString });
      return arrayOfByte;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return null;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return null;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
    return null;
  }

  public boolean isSigValid()
  {
    try
    {
      boolean bool = ((Boolean)this.c.getMethod("isSigValid", new Class[0]).invoke(this.f, new Object[0])).booleanValue();
      return bool;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return false;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return false;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
    return false;
  }

  public boolean login(String paramString1, String paramString2)
  {
    try
    {
      Class[] arrayOfClass = { String.class, String.class };
      Object[] arrayOfObject = { paramString1, paramString2 };
      this.c.getMethod("loginAction", arrayOfClass).invoke(this.f, arrayOfObject);
      return true;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return true;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return true;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APWtLogin
 * JD-Core Version:    0.6.0
 */