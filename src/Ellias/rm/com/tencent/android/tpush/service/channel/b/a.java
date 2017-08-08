package com.tencent.android.tpush.service.channel.b;

import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class a extends f
  implements d
{
  private static final Pattern k = Pattern.compile("\\A(\\S+) +(\\d+) +(.*)\r\n");
  private static final Pattern l = Pattern.compile("(.*) *: *(.*)\r\n");
  protected StringBuffer a = new StringBuffer();
  protected String b;
  public int c;
  protected String d;
  protected final HashMap e = new HashMap();
  protected int f = -1;
  protected int g = 0;
  protected int h = -1;
  public final ArrayList i = new ArrayList();
  private int m = 0;
  private g n = null;

  public int a(InputStream paramInputStream)
  {
    TLog.d("Channel.HttpRecvPacket", ">>> read(" + paramInputStream + ")");
    c();
    int j = paramInputStream.available();
    int i1 = 0;
    if (j == 0);
    while (true)
    {
      return i1;
      try
      {
        this.g = 0;
        if (b())
          continue;
        int i2 = this.g;
        this.g = (i2 + 1);
        if (i2 <= 2)
          break;
        throw new InnerException("the duration of the current step is too long!");
      }
      catch (IORefusedException localIORefusedException)
      {
        TLog.d("Channel.HttpRecvPacket", "read >>> IORefusedException thrown", localIORefusedException);
        return i1;
      }
    }
    switch (this.h)
    {
    default:
      throw new InnerException("illegal step value!");
    case -1:
      i1 += b(paramInputStream);
    case -2:
    case -3:
    case 0:
    }
    while ((this.h != 0) && (paramInputStream.available() == 0))
    {
      return i1;
      i1 += c(paramInputStream);
      continue;
      i1 += d(paramInputStream);
      continue;
      d();
    }
  }

  void a(int paramInt)
  {
    if (this.h != paramInt)
      this.g = 0;
    this.h = paramInt;
  }

  protected int b(InputStream paramInputStream)
  {
    int j = paramInputStream.available();
    int i1 = 0;
    int i2 = j - 1;
    int i3;
    if (j > 0)
    {
      i3 = i1 + 1;
      int i4 = paramInputStream.read();
      switch (i4)
      {
      default:
        this.a.append((char)i4);
      case -1:
      case 10:
      }
      int i5;
      do
      {
        i1 = i3;
        j = i2;
        break;
        throw new IOException("the end of stream has been reached!");
        this.a.append((char)i4);
        i5 = this.a.length();
      }
      while ((i5 < 4) || (!"\r\n\r\n".contentEquals(this.a.subSequence(i5 - 4, i5))));
      Matcher localMatcher1 = k.matcher(this.a.subSequence(0, this.a.length()));
      if ((!localMatcher1.find()) || (localMatcher1.groupCount() != 3))
        break label447;
      this.b = localMatcher1.group(1);
      try
      {
        this.c = Integer.parseInt(localMatcher1.group(2).trim());
        this.d = localMatcher1.group(3);
        Matcher localMatcher2 = l.matcher(this.a.subSequence(0, this.a.length()));
        while ((localMatcher2.find()) && (localMatcher2.groupCount() == 2))
          this.e.put(localMatcher2.group(1).toLowerCase(Locale.US), localMatcher2.group(2));
      }
      catch (NumberFormatException localNumberFormatException1)
      {
        TLog.e("TPush", localNumberFormatException1.toString());
        throw new UnexpectedDataException("http statusLine can not parsed!");
      }
      if ((this.e.containsKey("Transfer-Encoding".toLowerCase(Locale.US))) && (((String)this.e.get("Transfer-Encoding".toLowerCase(Locale.US))).equalsIgnoreCase("chunked")))
      {
        this.f = -1;
        a(-3);
        i1 = i3;
      }
    }
    else
    {
      return i1;
    }
    if (this.e.get("Content-Length".toLowerCase(Locale.US)) != null)
      try
      {
        this.f = Integer.parseInt(((String)this.e.get("Content-Length".toLowerCase(Locale.US))).trim());
        a(-2);
        return i3;
      }
      catch (NumberFormatException localNumberFormatException2)
      {
        TLog.e("TPush", localNumberFormatException2.toString());
        throw new UnexpectedDataException("http Content-Length can not parsed!");
      }
    throw new UnexpectedDataException("http Content-Length == null && Transfer-Encoding not equal to 'chunked'!");
    label447: throw new UnexpectedDataException("http statusLine can not parsed!");
  }

  protected int c(InputStream paramInputStream)
  {
    int i2;
    for (int j = 0; ; j = i2)
    {
      if (paramInputStream.available() >= 0)
      {
        if (this.m > this.f)
          throw new UnexpectedDataException("readBodyLength > contentLength ?!!");
        if (this.m == this.f)
        {
          if (this.n != null)
            throw new InnerException("currentRecvPacket != null ?!!");
          a(0);
        }
      }
      else
      {
        return j;
      }
      if (this.n == null)
      {
        this.n = new g();
        this.n.a(this.j);
      }
      int i1 = this.n.a(paramInputStream);
      i2 = j + i1;
      this.m = (i1 + this.m);
      TLog.v("HttpRecvPacket", "readLen:" + i2 + ",bodyLen:" + this.m + ",curLen:" + i1);
      if (this.n.b())
      {
        this.i.add(this.n);
        this.n = null;
      }
      if (j == i2)
        return i2;
    }
  }

  protected int d(InputStream paramInputStream)
  {
    throw new InnerException("not support chunked transfer encoding!");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.b.a
 * JD-Core Version:    0.6.0
 */