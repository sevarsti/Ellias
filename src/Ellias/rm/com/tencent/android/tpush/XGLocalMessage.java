package com.tencent.android.tpush;

import android.annotation.SuppressLint;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XGLocalMessage
{
  private int a = 1;
  private String b;
  private String c;
  private String d = "";
  private String e = "00";
  private String f = "00";
  private long g;

  public long getBuilderId()
  {
    return this.g;
  }

  public String getContent()
  {
    return this.c;
  }

  @SuppressLint({"SimpleDateFormat"})
  public String getDate()
  {
    if (!c.a(this.d));
    try
    {
      this.d = this.d.substring(0, 8);
      Long.parseLong(this.d);
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
      localSimpleDateFormat.setLenient(false);
      localSimpleDateFormat.parse(this.d);
      return this.d;
    }
    catch (ParseException localParseException)
    {
      TLog.e("TPush", "XGLocalMessage.getDate()" + localParseException);
      return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }
    catch (Exception localException)
    {
      TLog.e("TPush", "XGLocalMessage.getDate()" + localException);
    }
    return new SimpleDateFormat("yyyyMMdd").format(new Date());
  }

  public String getHour()
  {
    if (this.e.length() < 1)
      return "00";
    if ((this.e.length() > 0) && (this.e.length() < 2))
      return "0" + this.e;
    return this.e;
  }

  public String getMin()
  {
    if (this.f.length() < 1)
      return "00";
    if ((this.f.length() > 0) && (this.f.length() < 2))
      return "0" + this.f;
    return this.f;
  }

  public String getTitle()
  {
    return this.b;
  }

  public int getType()
  {
    return this.a;
  }

  public void setBuilderId(long paramLong)
  {
    this.g = paramLong;
  }

  public void setContent(String paramString)
  {
    this.c = paramString;
  }

  public void setDate(String paramString)
  {
    this.d = paramString;
  }

  public void setHour(String paramString)
  {
    this.e = paramString;
  }

  public void setMin(String paramString)
  {
    this.f = paramString;
  }

  public void setTitle(String paramString)
  {
    this.b = paramString;
  }

  public void setType(int paramInt)
  {
    this.a = paramInt;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGLocalMessage
 * JD-Core Version:    0.6.0
 */