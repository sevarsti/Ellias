package com.tencent.android.tpush.service.channel.b;

import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class b extends f
  implements e
{
  private static final String h = b.class.getSimpleName();
  protected HashMap a = new HashMap(4);
  protected int b = 0;
  protected int c = -1;
  public ArrayList d = new ArrayList(1);
  protected String e = null;
  protected String f = null;
  protected final HashMap g = new HashMap(8);

  public b(String paramString1, String paramString2)
  {
    this.e = paramString1;
    this.f = paramString2;
  }

  public int a(OutputStream paramOutputStream)
  {
    TLog.d("Channel.HttpSendPacket", ">>> write(" + paramOutputStream + ")");
    c();
    try
    {
      this.b = 0;
      i = 0;
      while (true)
      {
        try
        {
          if (b())
            break label87;
          int j = this.b;
          this.b = (j + 1);
          if (j <= 2)
            break label90;
          throw new InnerException("the duration of the current step is too long!");
        }
        catch (IORefusedException localIORefusedException2)
        {
        }
        TLog.v("Channel.HttpSendPacket", "write >>> IORefusedException thrown", localIORefusedException2);
        label87: return i;
        label90: switch (this.c)
        {
        default:
          throw new InnerException("illegal step value!");
        case -1:
          i += b(paramOutputStream);
          break;
        case 0:
          d();
        }
      }
    }
    catch (IORefusedException localIORefusedException1)
    {
      while (true)
      {
        Object localObject = localIORefusedException1;
        int i = 0;
      }
    }
  }

  void a(int paramInt)
  {
    if (this.c != paramInt)
      this.b = 0;
    this.c = paramInt;
  }

  public void a(e parame)
  {
    this.d.add(parame);
  }

  public void a(String paramString1, String paramString2)
  {
    this.g.put(paramString1, paramString2);
  }

  protected int b(OutputStream paramOutputStream)
  {
    byte[] arrayOfByte1 = (byte[])(byte[])this.a.get("httpData");
    if (arrayOfByte1 == null)
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      byte[] arrayOfByte2;
      String str2;
      try
      {
        c(localByteArrayOutputStream);
        arrayOfByte2 = localByteArrayOutputStream.toByteArray();
        a("Content-Length", String.valueOf(arrayOfByte2.length));
        String str1 = "POST " + this.f + " HTTP/1.1" + "\r\n";
        Iterator localIterator = this.g.entrySet().iterator();
        Map.Entry localEntry;
        for (str2 = str1; localIterator.hasNext(); str2 = str2 + (String)localEntry.getKey() + ": " + (String)localEntry.getValue() + "\r\n")
          localEntry = (Map.Entry)localIterator.next();
      }
      catch (IOException localIOException)
      {
        throw new UnexpectedDataException("http content can not be write correctly!", localIOException);
      }
      byte[] arrayOfByte3 = (str2 + "\r\n").getBytes("UTF-8");
      arrayOfByte1 = new byte[arrayOfByte3.length + arrayOfByte2.length];
      System.arraycopy(arrayOfByte3, 0, arrayOfByte1, 0, arrayOfByte3.length);
      System.arraycopy(arrayOfByte2, 0, arrayOfByte1, arrayOfByte3.length, arrayOfByte2.length);
      this.a.put("httpData", arrayOfByte1);
      this.a.put("httpDataLeftLength", Integer.valueOf(arrayOfByte1.length));
    }
    byte[] arrayOfByte4 = arrayOfByte1;
    int i = ((Integer)this.a.get("httpDataLeftLength")).intValue();
    if (i == 0)
    {
      a(0);
      return 0;
    }
    int j = com.tencent.android.tpush.service.channel.c.e.a(paramOutputStream, arrayOfByte4);
    int k = i - j;
    this.a.put("httpDataLeftLength", Integer.valueOf(k));
    return j;
  }

  protected void c(OutputStream paramOutputStream)
  {
    TLog.i(h, ">>sendPackets size=" + this.d.size());
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext())
    {
      e locale = (e)localIterator.next();
      locale.a(this.j);
      locale.a(paramOutputStream);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.b.b
 * JD-Core Version:    0.6.0
 */