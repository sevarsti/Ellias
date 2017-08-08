package oicq.wlogin_sdk.request;

import B;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.concurrent.Semaphore;
import oicq.wlogin_sdk.tools.util;

public class WtloginMsfListener
  implements Runnable
{
  private byte[] data;
  private int ret;
  private byte[] ret_data = null;
  private String ret_serviceCmd;
  private Boolean ret_success = Boolean.valueOf(false);
  private String ret_uin;
  private final Semaphore semp = new Semaphore(1);
  private String serviceCmd;
  private int timeout;
  private String uin;

  public WtloginMsfListener(String paramString1, String paramString2, byte[] paramArrayOfByte, int paramInt)
  {
    if (paramString1 == null)
      paramString1 = "0";
    this.uin = paramString1;
    if (paramString2 == null)
      paramString2 = "";
    this.serviceCmd = paramString2;
    if (paramArrayOfByte == null)
      paramArrayOfByte = new byte[0];
    this.data = paramArrayOfByte;
    if (paramInt > 0);
    while (true)
    {
      this.timeout = paramInt;
      return;
      paramInt = 10000;
    }
  }

  public void Cancel()
  {
    try
    {
      Class localClass = Class.forName("com.tencent.mobileqq.msf.core.auth.WTLoginCenter");
      Object localObject = localClass.getMethod("getWtloginMsfProvider", new Class[0]).invoke(localClass, new Object[0]);
      Class.forName("com.tencent.mobileqq.msf.core.auth.WtloginMsfProvider").getMethod("Cancel", new Class[0]).invoke(localObject, new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      util.LOGW("exception", localStringWriter.toString());
    }
  }

  public byte[] RecvData()
  {
    try
    {
      this.semp.acquire();
      if (!this.ret_success.booleanValue())
        return null;
      if ((this.ret_uin == null) || (!this.ret_uin.equals(this.uin)))
      {
        this.ret = -1009;
        return null;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localInterruptedException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      util.LOGW("exception", localStringWriter.toString());
      return null;
    }
    if ((this.ret_serviceCmd == null) || (!this.ret_serviceCmd.equals(this.serviceCmd)))
    {
      this.ret = -1009;
      return null;
    }
    this.semp.release();
    byte[] arrayOfByte = this.ret_data;
    return arrayOfByte;
  }

  public int SendData(byte[] paramArrayOfByte, int paramInt)
  {
    try
    {
      Class localClass1 = Class.forName("com.tencent.mobileqq.msf.core.auth.WTLoginCenter");
      Object localObject = localClass1.getMethod("getWtloginMsfProvider", new Class[0]).invoke(localClass1, new Object[0]);
      Class localClass2 = Class.forName("com.tencent.mobileqq.msf.core.auth.WtloginMsfProvider");
      Class[] arrayOfClass = new Class[5];
      arrayOfClass[0] = String.class;
      arrayOfClass[1] = String.class;
      arrayOfClass[2] = [B.class;
      arrayOfClass[3] = Integer.TYPE;
      arrayOfClass[4] = WtloginMsfListener.class;
      Method localMethod = localClass2.getMethod("SendData", arrayOfClass);
      Object[] arrayOfObject = new Object[5];
      arrayOfObject[0] = new String(this.uin);
      arrayOfObject[1] = new String(this.serviceCmd);
      arrayOfObject[2] = paramArrayOfByte.clone();
      arrayOfObject[3] = new Integer(paramInt);
      arrayOfObject[4] = this;
      int i = Integer.valueOf(localMethod.invoke(localObject, arrayOfObject).toString()).intValue();
      if (i > 0)
        this.semp.acquire();
      return i;
    }
    catch (Exception localException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      util.LOGW("exception", localStringWriter.toString());
    }
    return -1000;
  }

  public int getRet()
  {
    return this.ret;
  }

  public byte[] getRetData()
  {
    return this.ret_data;
  }

  public void onReceiveData(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    this.ret_success = Boolean.valueOf(true);
    this.ret_uin = paramString1;
    this.ret_serviceCmd = paramString2;
    this.ret = 0;
    if (paramArrayOfByte != null)
    {
      this.ret_data = new byte[paramArrayOfByte.length];
      System.arraycopy(paramArrayOfByte, 0, this.ret_data, 0, paramArrayOfByte.length);
    }
    this.semp.release();
  }

  public void onReceiveFail(String paramString1, String paramString2, int paramInt)
  {
    this.ret_success = Boolean.valueOf(false);
    this.ret_uin = paramString1;
    this.ret_serviceCmd = paramString2;
    this.ret = paramInt;
    this.semp.release();
  }

  public void run()
  {
    try
    {
      this.ret = SendData(this.data, this.timeout);
      if (this.ret <= 0)
      {
        util.LOGI("msf request send data failed, ret=" + this.ret);
        return;
      }
      if (RecvData() == null)
      {
        util.LOGI("recv data from server failed, ret=" + this.ret);
        return;
      }
    }
    catch (Exception localException)
    {
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.WtloginMsfListener
 * JD-Core Version:    0.6.0
 */