package com.tencent.component.utils;

import android.text.TextUtils;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
{
  private static final Pattern a = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
  private static final ThreadLocal b = new g();
  private static final String c = "<br/>";
  private static final ThreadLocal d = new h();

  public static String A(String paramString)
  {
    int i = paramString.lastIndexOf('?');
    if (i > 1);
    for (String str = paramString.substring(1 + paramString.lastIndexOf('.'), i); ; str = paramString.substring(1 + paramString.lastIndexOf('.')))
      return B(paramString) + "." + str;
  }

  public static String B(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      String str = a(localMessageDigest.digest());
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    return String.valueOf(paramString.hashCode());
  }

  public static byte[] C(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      byte[] arrayOfByte2 = paramString.getBytes("GBK");
      return arrayOfByte2;
    }
    catch (Exception localException1)
    {
      try
      {
        byte[] arrayOfByte1 = paramString.getBytes("gbk");
        return arrayOfByte1;
      }
      catch (Exception localException2)
      {
      }
    }
    return paramString.getBytes();
  }

  public static String D(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return paramString;
    return Pattern.compile("[\\r,\\n]{2,}").matcher(paramString).replaceAll("\n");
  }

  public static boolean E(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    Matcher localMatcher;
    int i;
    do
    {
      return false;
      localMatcher = Pattern.compile("\\s*").matcher(paramString);
      if (!localMatcher.matches())
        break;
      i = localMatcher.start();
    }
    while (localMatcher.end() - i >= paramString.length());
    return true;
  }

  public static boolean F(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    do
      return false;
    while ((!G(paramString)) && (!H(paramString)));
    return true;
  }

  public static boolean G(String paramString)
  {
    int i = 0;
    if (paramString != null)
    {
      int j = paramString.length();
      i = 0;
      if (j > 6)
      {
        boolean bool = paramString.substring(0, 7).equalsIgnoreCase("http://");
        i = 0;
        if (bool)
          i = 1;
      }
    }
    return i;
  }

  public static boolean H(String paramString)
  {
    int i = 0;
    if (paramString != null)
    {
      int j = paramString.length();
      i = 0;
      if (j > 7)
      {
        boolean bool = paramString.substring(0, 8).equalsIgnoreCase("https://");
        i = 0;
        if (bool)
          i = 1;
      }
    }
    return i;
  }

  public static int a(Object paramObject)
  {
    if (paramObject == null)
      return 0;
    return c(paramObject.toString(), 0);
  }

  public static long a(CharSequence paramCharSequence)
  {
    double d1 = 0.0D;
    int i = 0;
    if (i < paramCharSequence.length())
    {
      int j = paramCharSequence.charAt(i);
      if ((j > 0) && (j < 127))
        d1 += 0.5D;
      while (true)
      {
        i++;
        break;
        d1 += 1.0D;
      }
    }
    return Math.round(d1);
  }

  public static String a(String paramString)
  {
    char[] arrayOfChar = paramString.toCharArray();
    int i = 0;
    if (i < arrayOfChar.length)
    {
      if (arrayOfChar[i] == '　')
        arrayOfChar[i] = ' ';
      while (true)
      {
        i++;
        break;
        if ((arrayOfChar[i] <= 65280) || (arrayOfChar[i] >= 65375))
          continue;
        arrayOfChar[i] = (char)(arrayOfChar[i] - 65248);
      }
    }
    return new String(arrayOfChar);
  }

  public static String a(String paramString, int paramInt)
  {
    byte[] arrayOfByte = paramString.getBytes("Unicode");
    int i = 2;
    int j = 0;
    if ((i < arrayOfByte.length) && (j < paramInt))
    {
      if (i % 2 == 1)
        j++;
      while (true)
      {
        i++;
        break;
        if (arrayOfByte[i] == 0)
          continue;
        j++;
      }
    }
    if (i % 2 == 1)
    {
      if (arrayOfByte[(i - 1)] == 0)
        break label83;
      i--;
    }
    while (true)
    {
      return new String(arrayOfByte, 0, i, "Unicode");
      label83: i++;
    }
  }

  public static String a(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString3 == null) || (paramString1 == null) || (paramString2 == null))
      return null;
    StringBuffer localStringBuffer = new StringBuffer("");
    while (true)
    {
      int i = paramString3.indexOf(paramString1);
      if (i == -1)
        break;
      localStringBuffer.append(paramString3.substring(0, i) + paramString2);
      paramString3 = paramString3.substring(i + paramString1.length());
      paramString3.indexOf(paramString1);
    }
    localStringBuffer.append(paramString3);
    return localStringBuffer.toString();
  }

  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
      if (str.length() == 1)
        localStringBuilder.append('0');
      localStringBuilder.append(str);
    }
    return localStringBuilder.toString();
  }

  public static String[] a(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return null;
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      int i = paramString1.indexOf(paramString2);
      if (i == -1)
        break;
      localArrayList.add(paramString1.substring(0, i));
      paramString1 = paramString1.substring(i + paramString2.length());
    }
    localArrayList.add(paramString1);
    return (String[])(String[])localArrayList.toArray(new String[0]);
  }

  public static String b(Object paramObject)
  {
    if (paramObject == null)
      return "";
    return paramObject.toString();
  }

  public static String b(String paramString)
  {
    if (paramString == null)
      return null;
    return a("\"", "&quot;", a("<", "&lt;", paramString));
  }

  public static String b(String paramString, int paramInt)
  {
    if (paramString == null)
      paramString = null;
    while (true)
    {
      return paramString;
      if (paramInt <= 0)
        return "";
      try
      {
        int k = paramString.getBytes("GBK").length;
        if (k <= paramInt)
          continue;
        label30: StringBuffer localStringBuffer = new StringBuffer();
        int i = paramInt - 3;
        int j = 0;
        if (i > 0)
        {
          char c1 = paramString.charAt(j);
          if (c1 < '')
            i--;
          while (true)
          {
            localStringBuffer.append(c1);
            j++;
            break;
            i = -1 + (i - 1);
          }
        }
        localStringBuffer.append("...");
        return localStringBuffer.toString();
      }
      catch (Exception localException)
      {
        break label30;
      }
    }
  }

  public static boolean b(String paramString1, String paramString2)
  {
    if (paramString1 == paramString2)
      return true;
    if ((paramString1 == null) || (paramString2 == null))
      return false;
    return paramString1.regionMatches(true, 0, paramString2, 0, paramString2.length());
  }

  public static int c(String paramString, int paramInt)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (Exception localException)
    {
    }
    return paramInt;
  }

  public static String c(String paramString)
  {
    if (paramString == null)
      return null;
    return a("&quot;", "\"", a("&lt;", "<", paramString));
  }

  public static String d(String paramString)
  {
    if (paramString == null)
      return null;
    return a("\t", "&nbsp;&nbsp;&nbsp;&nbsp;", a("\n", "<br/>", a("\r\n", "<br/>", a(" ", "&nbsp;", a("<", "&lt;", paramString)))));
  }

  public static boolean d(String paramString, int paramInt)
  {
    return paramString.length() <= paramInt;
  }

  public static String e(String paramString)
  {
    String str1 = paramString.substring(paramString.lastIndexOf("/"));
    String str2 = str1.substring(1, str1.length());
    if (str2.equalsIgnoreCase(""))
    {
      Calendar localCalendar = Calendar.getInstance();
      str2 = localCalendar.get(1) + "" + localCalendar.get(2) + "" + localCalendar.get(5) + "" + localCalendar.get(12);
    }
    return str2;
  }

  public static boolean e(String paramString, int paramInt)
  {
    return paramString.length() <= 120;
  }

  public static String f(String paramString)
  {
    Object localObject = "";
    if (paramString != null);
    try
    {
      String str = paramString.replaceAll("\r", "").replaceAll("&gt;", ">").replaceAll("&ldquo;", "“").replaceAll("&rdquo;", "”").replaceAll("&#39;", "'").replaceAll("&nbsp;", "").replaceAll("<br\\s*/>", "\n").replaceAll("&quot;", "\"").replaceAll("&lt;", "<").replaceAll("&lsquo;", "《").replaceAll("&rsquo;", "》").replaceAll("&middot;", "·").replace("&mdash;", "—").replace("&hellip;", "…").replace("&amp;", "×").replaceAll("\\s*", "").trim().replaceAll("<p>", "\n      ").replaceAll("</p>", "").replaceAll("<div.*?>", "\n      ").replaceAll("</div>", "");
      localObject = str;
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return (String)localObject;
  }

  public static String g(String paramString)
  {
    Log.v("htmlStr", paramString);
    try
    {
      paramString = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>", 2).matcher(paramString).replaceAll("");
      paramString = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>", 2).matcher(paramString).replaceAll("");
      String str = Pattern.compile("<[^>]+>", 2).matcher(paramString).replaceAll("");
      return str;
    }
    catch (Exception localException)
    {
    }
    return paramString;
  }

  public static String h(String paramString)
  {
    if (paramString != null)
      paramString = paramString.replaceAll("\r", "").replaceAll("\n", "").replace(" ", "");
    return paramString;
  }

  public static boolean i(String paramString)
  {
    return (paramString != null) && (!"".equalsIgnoreCase(paramString.trim()));
  }

  public static Date j(String paramString)
  {
    try
    {
      Date localDate = ((SimpleDateFormat)b.get()).parse(paramString);
      return localDate;
    }
    catch (ParseException localParseException)
    {
    }
    return null;
  }

  public static String k(String paramString)
  {
    Date localDate = j(paramString);
    if (localDate == null)
      return "Unknown";
    Calendar localCalendar = Calendar.getInstance();
    if (((SimpleDateFormat)d.get()).format(localCalendar.getTime()).equals(((SimpleDateFormat)d.get()).format(localDate)))
    {
      int k = (int)((localCalendar.getTimeInMillis() - localDate.getTime()) / 3600000L);
      if (k == 0)
        return Math.max((localCalendar.getTimeInMillis() - localDate.getTime()) / 60000L, 1L) + "分钟前";
      return k + "小时前";
    }
    long l = localDate.getTime() / 86400000L;
    int i = (int)(localCalendar.getTimeInMillis() / 86400000L - l);
    if (i == 0)
    {
      int j = (int)((localCalendar.getTimeInMillis() - localDate.getTime()) / 3600000L);
      if (j == 0)
        return Math.max((localCalendar.getTimeInMillis() - localDate.getTime()) / 60000L, 1L) + "分钟前";
      return j + "小时前";
    }
    if (i == 1)
      return "昨天";
    if (i == 2)
      return "前天";
    if ((i > 2) && (i <= 10))
      return i + "天前";
    if (i > 10)
      return ((SimpleDateFormat)d.get()).format(localDate);
    return "";
  }

  public static String l(String paramString)
  {
    String str = "";
    if (paramString != null)
      str = paramString.replaceAll("-", "").replaceAll("\\+", "");
    return str;
  }

  public static String m(String paramString)
  {
    String str = "";
    if (paramString != null)
      str = Pattern.compile("\r").matcher(paramString).replaceAll("");
    return str;
  }

  public static boolean n(String paramString)
  {
    Date localDate1 = j(paramString);
    Date localDate2 = new Date();
    return (localDate1 != null) && (((SimpleDateFormat)d.get()).format(localDate2).equals(((SimpleDateFormat)d.get()).format(localDate1)));
  }

  public static boolean o(String paramString)
  {
    int i;
    if ((paramString == null) || ("".equals(paramString)))
    {
      i = 1;
      return i;
    }
    for (int j = 0; ; j++)
    {
      if (j >= paramString.length())
        break label65;
      int k = paramString.charAt(j);
      if ((k == 32) || (k == 9) || (k == 13))
        continue;
      i = 0;
      if (k != 10)
        break;
    }
    label65: return true;
  }

  public static boolean p(String paramString)
  {
    if ((paramString == null) || (paramString.trim().length() == 0))
      return false;
    return a.matcher(paramString).matches();
  }

  public static long q(String paramString)
  {
    try
    {
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (Exception localException)
    {
    }
    return 0L;
  }

  public static boolean r(String paramString)
  {
    try
    {
      boolean bool = Boolean.parseBoolean(paramString);
      return bool;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public static boolean s(String paramString)
  {
    try
    {
      if (!paramString.substring(0, 1).equals("1"))
        return false;
      if ((paramString != null) && (paramString.length() == 11))
      {
        boolean bool = Pattern.compile("^[0123456789]+$").matcher(paramString).matches();
        if (bool)
          return true;
      }
    }
    catch (RuntimeException localRuntimeException)
    {
    }
    return false;
  }

  public static boolean t(String paramString)
  {
    return Pattern.compile("[Α-￥]+$").matcher(paramString).matches();
  }

  public static boolean u(String paramString)
  {
    return Pattern.compile("[0-9]*").matcher(paramString).matches();
  }

  public static boolean v(String paramString)
  {
    return Pattern.compile("^[-\\+]?[\\d]*$").matcher(paramString).matches();
  }

  public static boolean w(String paramString)
  {
    return Pattern.compile("^[-\\+]?[.\\d]*$").matcher(paramString).matches();
  }

  public static boolean x(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0);
  }

  public static boolean y(String paramString)
  {
    return paramString.length() <= 70;
  }

  public static boolean z(String paramString)
  {
    return Pattern.compile("^\\w+([-.]\\w+)*@\\w+([-]\\w+)*\\.(\\w+([-]\\w+)*\\.)*[a-z]{2,3}$").matcher(paramString).matches();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.StringUtils
 * JD-Core Version:    0.6.0
 */