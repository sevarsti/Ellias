package com.pay.login;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class APWtLogin$MyInvocationHandler
  implements InvocationHandler
{
  APWtLogin$MyInvocationHandler(APWtLogin paramAPWtLogin)
  {
  }

  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    if (paramMethod.getName().equals("LoginSuccCallBack"))
      if (APWtLogin.a(this.a) != null)
        APWtLogin.a(this.a).LoginSuccCallBack(String.valueOf(paramArrayOfObject[0]), String.valueOf(paramArrayOfObject[1]));
    while (true)
    {
      return null;
      if (paramMethod.getName().equals("LoginNeedVerify"))
      {
        if (APWtLogin.a(this.a) == null)
          continue;
        byte[] arrayOfByte = (byte[])paramArrayOfObject[0];
        APWtLogin.a(this.a).LoginNeedVerify(arrayOfByte);
        continue;
      }
      if ((!paramMethod.getName().equals("LoginFailCallBack")) || (APWtLogin.a(this.a) == null))
        continue;
      APWtLogin.a(this.a).LoginFailCallBack(String.valueOf(paramArrayOfObject[1]));
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APWtLogin.MyInvocationHandler
 * JD-Core Version:    0.6.0
 */