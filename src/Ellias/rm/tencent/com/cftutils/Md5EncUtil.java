package tencent.com.cftutils;

import com.tenpay.a.c.a;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Md5EncUtil
{
  private byte[] enc_buf;
  private byte[] raw_buf;

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

  private native boolean encrypt_md5(int paramInt, byte[] paramArrayOfByte);

  public boolean encryptMd5(int paramInt, String paramString)
  {
    int i = paramString.length();
    boolean bool = false;
    if (i > 0);
    try
    {
      this.raw_buf = paramString.getBytes("UTF-8");
      bool = encrypt_md5(paramInt, this.raw_buf);
      return bool;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public String getMd5Sign()
  {
    if (this.enc_buf == null)
      return null;
    try
    {
      String str = new String(this.enc_buf, "UTF-8");
      return str.toUpperCase();
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public String sortConstructVars(HashMap paramHashMap)
  {
    String str1 = new String();
    LinkedList localLinkedList = new LinkedList(paramHashMap.entrySet());
    Collections.sort(localLinkedList, new Md5EncUtil.1(this));
    Iterator localIterator = localLinkedList.iterator();
    String str2 = str1;
    int i = 1;
    while (true)
    {
      if (!localIterator.hasNext())
        return str2;
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if ((localEntry.getKey().toString().length() <= 0) || (localEntry.getValue().toString().length() <= 0))
        continue;
      if (i != 0)
      {
        str2 = new StringBuilder(String.valueOf(localEntry.getKey().toString())).append("=").toString() + localEntry.getValue().toString();
        i = 0;
        continue;
      }
      str2 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(str2)).append("&").toString())).append(localEntry.getKey().toString()).toString())).append("=").toString() + localEntry.getValue().toString();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     tencent.com.cftutils.Md5EncUtil
 * JD-Core Version:    0.6.0
 */