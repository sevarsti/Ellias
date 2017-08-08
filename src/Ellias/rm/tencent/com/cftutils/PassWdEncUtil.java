package tencent.com.cftutils;

import com.tenpay.a.c.a;
import java.io.File;

public class PassWdEncUtil
{
  private byte[] enc_passwd;
  private byte[] raw_passwd;
  private String server_time_stamp = "0";
  private int time_stamp;

  static
  {
    if (a.a != null)
      try
      {
        File localFile = new File(a.a);
        if ((!localFile.exists()) || (localFile.isDirectory()))
        {
          System.loadLibrary("cftutils");
          return;
        }
        System.load(a.a);
        return;
      }
      catch (Exception localException)
      {
        return;
      }
    System.loadLibrary("cftutils");
  }

  private native boolean encrypt_passwd(byte[] paramArrayOfByte);

  private native boolean encrypt_passwd1(byte[] paramArrayOfByte);

  private native boolean encrypt_passwd2(byte[] paramArrayOfByte);

  public boolean encryptPasswd(String paramString)
  {
    this.time_stamp = 0;
    this.raw_passwd = null;
    this.enc_passwd = null;
    int i = paramString.length();
    boolean bool = false;
    if (i > 0);
    try
    {
      this.raw_passwd = paramString.getBytes("ASCII");
      bool = encrypt_passwd(this.raw_passwd);
      return bool;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public boolean encryptPasswd(String paramString1, String paramString2)
  {
    setTimeStamp(paramString2);
    return encryptPasswd(paramString1);
  }

  public boolean encryptPasswd1(String paramString)
  {
    this.time_stamp = 0;
    this.raw_passwd = null;
    this.enc_passwd = null;
    int i = paramString.length();
    boolean bool = false;
    if (i > 0);
    try
    {
      this.raw_passwd = paramString.getBytes("ASCII");
      bool = encrypt_passwd1(this.raw_passwd);
      return bool;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public boolean encryptPasswd1(String paramString1, String paramString2)
  {
    setTimeStamp(paramString2);
    return encryptPasswd1(paramString1);
  }

  public boolean encryptPasswd2(String paramString)
  {
    this.time_stamp = 0;
    this.raw_passwd = null;
    this.enc_passwd = null;
    int i = paramString.length();
    boolean bool = false;
    if (i > 0);
    try
    {
      this.raw_passwd = paramString.getBytes("ASCII");
      bool = encrypt_passwd2(this.raw_passwd);
      return bool;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public String getEncryptPasswd()
  {
    if (this.enc_passwd == null)
      return null;
    try
    {
      String str = new String(this.enc_passwd, "ASCII");
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public String getTimeStamp()
  {
    return String.valueOf(this.time_stamp);
  }

  public void setTimeStamp(String paramString)
  {
    this.server_time_stamp = paramString;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     tencent.com.cftutils.PassWdEncUtil
 * JD-Core Version:    0.6.0
 */