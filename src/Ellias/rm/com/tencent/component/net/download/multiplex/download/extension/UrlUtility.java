package com.tencent.component.net.download.multiplex.download.extension;

import android.net.Uri;
import android.text.TextUtils;
import com.tencent.component.utils.log.LogUtil;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class UrlUtility
{
  public static final int a = 1;
  public static final int b = 2;
  public static final int c = 3;
  public static final String d = "webpay_url";
  private static Pattern e = Pattern.compile("(.+)(\\.)(.+)[^\\w]*(.*)", 2);
  private static Pattern f = Pattern.compile("(.+)localhost(:)?(\\d)*/(.+)(\\.)(.+)", 2);
  private static Pattern g = Pattern.compile("mtt://(.+)", 2);
  private static Pattern h = Pattern.compile("qb://(.+)", 2);
  private static Pattern i = Pattern.compile("(tenpay|alipay)://(.+)", 2);
  private static Pattern j = Pattern.compile("(\\d){1,3}\\.(\\d){1,3}\\.(\\d){1,3}\\.(\\d){1,3}(:\\d{1,4})?(/(.*))?", 2);
  private static String[] k = { "gd", "com", "cn", "ac", "edu", "gov", "mil", "arpa", "net", "org", "biz", "info", "pro", "name", "coop", "mobi", "int", "us", "travel", "xxx", "idv", "co", "so", "tv", "hk", "asia", "me", "cc", "tw" };
  private static final Pattern l = Pattern.compile("attachment;\\s*filename\\s*=\\s*(\"?)([^\"]*)\\1\\s*[;\\s*charset=\\s*]*([^\"]*)\\s*$", 2);
  private static final Pattern m = Pattern.compile("inline;\\s*filename\\s*=\\s*(\"?)([^\"]*)\\1\\s*[;\\s*charset=\\s*]*([^\"]*)\\s*$", 2);
  private static final String n = "utf-8";
  private static final String[] o = { "alipay.com", "115.124.16.81", "110.75.128.59" };
  private static final String p = "UrlUtility";

  public static boolean A(String paramString)
  {
    return (y(paramString)) || (z(paramString));
  }

  public static boolean B(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return false;
    return paramString.substring(0, 5).equalsIgnoreCase("data:");
  }

  public static boolean C(String paramString)
  {
    int i1 = 0;
    if (paramString != null)
    {
      int i2 = paramString.length();
      i1 = 0;
      if (i2 > 6)
      {
        boolean bool = paramString.substring(0, 7).equalsIgnoreCase("file://");
        i1 = 0;
        if (bool)
          i1 = 1;
      }
    }
    return i1;
  }

  public static boolean D(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return false;
    return paramString.startsWith("Login://");
  }

  public static boolean E(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return false;
    return paramString.startsWith("*");
  }

  public static boolean F(String paramString)
  {
    int i1 = 0;
    if (paramString != null)
    {
      int i2 = paramString.length();
      i1 = 0;
      if (i2 > 8)
      {
        boolean bool = paramString.substring(0, 9).equalsIgnoreCase("market://");
        i1 = 0;
        if (bool)
          i1 = 1;
      }
    }
    return i1;
  }

  public static boolean G(String paramString)
  {
    int i1 = 0;
    if (paramString != null)
    {
      int i2 = paramString.length();
      i1 = 0;
      if (i2 > 6)
      {
        boolean bool = paramString.substring(0, 7).equalsIgnoreCase("rtsp://");
        i1 = 0;
        if (bool)
          i1 = 1;
      }
    }
    return i1;
  }

  public static boolean H(String paramString)
  {
    int i1 = 0;
    if (paramString != null)
    {
      int i2 = paramString.length();
      i1 = 0;
      if (i2 > 5)
      {
        boolean bool = paramString.substring(0, 6).equalsIgnoreCase("ftp://");
        i1 = 0;
        if (bool)
          i1 = 1;
      }
    }
    return i1;
  }

  public static boolean I(String paramString)
  {
    int i1 = 0;
    if (paramString != null)
    {
      int i2 = paramString.length();
      i1 = 0;
      if (i2 > 4)
      {
        boolean bool = paramString.substring(0, 4).equalsIgnoreCase("sms:");
        i1 = 0;
        if (bool)
          i1 = 1;
      }
    }
    return i1;
  }

  public static boolean J(String paramString)
  {
    int i1 = 0;
    if (paramString != null)
    {
      int i2 = paramString.length();
      i1 = 0;
      if (i2 > 4)
      {
        boolean bool = paramString.substring(0, 4).equalsIgnoreCase("tel:");
        i1 = 0;
        if (bool)
          i1 = 1;
      }
    }
    return i1;
  }

  public static String K(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    if (J(paramString));
    while (true)
    {
      return paramString;
      String str = O(paramString);
      if (str != null)
      {
        paramString = "tel:" + str;
        continue;
      }
      paramString = null;
    }
  }

  public static String L(String paramString)
  {
    if ((paramString == null) || ("".equalsIgnoreCase(paramString)))
      paramString = null;
    do
      return paramString;
    while (M(paramString));
    return null;
  }

  public static boolean M(String paramString)
  {
    int i1 = 0;
    if (paramString != null)
    {
      int i2 = paramString.length();
      i1 = 0;
      if (i2 > 7)
      {
        boolean bool = paramString.substring(0, 7).equalsIgnoreCase("mailto:");
        i1 = 0;
        if (bool)
          i1 = 1;
      }
    }
    return i1;
  }

  public static boolean N(String paramString)
  {
    int i1 = 0;
    if (paramString != null)
    {
      int i2 = paramString.length();
      i1 = 0;
      if (i2 > 13)
      {
        boolean bool = paramString.substring(0, 13).equalsIgnoreCase("wtai://wp/mc;");
        i1 = 0;
        if (bool)
          i1 = 1;
      }
    }
    return i1;
  }

  public static String O(String paramString)
  {
    if (N(paramString))
    {
      int i1 = paramString.indexOf("?", 13);
      if (i1 != -1)
        return paramString.substring(13, i1);
      return paramString.substring(13);
    }
    return null;
  }

  public static boolean P(String paramString)
  {
    int i1 = 0;
    if (paramString != null)
    {
      int i2 = paramString.length();
      i1 = 0;
      if (i2 > 5)
      {
        boolean bool = paramString.substring(0, 5).equalsIgnoreCase("qb://");
        i1 = 0;
        if (bool)
          i1 = 1;
      }
    }
    return i1;
  }

  public static boolean Q(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    do
    {
      return false;
      int i1 = paramString.indexOf("://");
      if (i1 == -1)
        continue;
      paramString = paramString.substring(i1 + 3);
    }
    while ((!paramString.startsWith("wap.")) && (!paramString.contains(".3g.")) && (!paramString.startsWith("3g.")) && (!paramString.contains(".wap.")));
    return true;
  }

  public static boolean R(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return false;
    return paramString.toLowerCase().startsWith("dttp://");
  }

  public static boolean S(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return false;
    return paramString.toLowerCase().startsWith("webkit://");
  }

  public static String T(String paramString)
  {
    if (paramString == null)
      paramString = null;
    int i1;
    do
    {
      return paramString;
      if (paramString.startsWith("page://"))
      {
        int i2 = paramString.indexOf("http://");
        if (i2 != -1)
          return paramString.substring(i2);
      }
      i1 = paramString.indexOf("://");
    }
    while (i1 < 0);
    return paramString.substring(i1 + 3);
  }

  public static String U(String paramString)
  {
    int i1 = paramString.indexOf("://");
    if (i1 != -1)
      paramString = paramString.substring(i1 + 3);
    return paramString;
  }

  public static String V(String paramString)
  {
    int i1 = paramString.indexOf('#');
    if (i1 != -1)
      paramString = paramString.substring(0, i1);
    return paramString;
  }

  public static boolean W(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (!paramString.startsWith("http://")))
      return false;
    String str1 = Uri.decode(paramString);
    int i3;
    if (!TextUtils.isEmpty(paramString))
    {
      int i2 = str1.indexOf('?');
      if (i2 > 0)
        str1 = str1.substring(0, i2);
      if (!str1.endsWith("/"))
      {
        i3 = 1 + str1.lastIndexOf('/');
        if (i3 <= 0);
      }
    }
    for (String str2 = str1.substring(i3); ; str2 = null)
    {
      if (TextUtils.isEmpty(str2))
        return false;
      int i1 = str2.lastIndexOf('.');
      if (i1 < 0)
        return false;
      return FileUtils.d(str2.substring(i1 + 1));
    }
  }

  public static String X(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return null;
      if ((paramString != null) && (paramString.toLowerCase().startsWith("text/")))
      {
        if (paramString.equalsIgnoreCase("text/html"))
          return ".html";
        return ".txt";
      }
      if ((paramString == null) || (!paramString.toLowerCase().startsWith("image/")))
        break;
      if (paramString.equalsIgnoreCase("image/png"))
        return ".png";
      if (paramString.equalsIgnoreCase("image/jpeg"))
        return ".jpeg";
      if (paramString.equalsIgnoreCase("image/jpg"))
        return ".jpg";
      if (paramString.equalsIgnoreCase("image/gif"))
        return ".gif";
    }
    return ".bin";
  }

  public static String Y(String paramString)
  {
    String str;
    if ((paramString == null) || (paramString.length() == 0))
    {
      str = null;
      return str;
    }
    int i1 = paramString.indexOf("://");
    if (i1 != -1);
    for (int i2 = i1 + 3; ; i2 = 0)
    {
      int i3 = paramString.indexOf('/', i2);
      if (i3 != -1)
        str = paramString.substring(i2, i3);
      while (true)
      {
        int i5 = str.indexOf(":");
        if (i5 < 0)
          break;
        return str.substring(0, i5);
        int i4 = paramString.indexOf('?', i2);
        if (i4 != -1)
        {
          str = paramString.substring(i2, i4);
          continue;
        }
        str = paramString.substring(i2);
      }
    }
  }

  public static String Z(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return "";
    try
    {
      URL localURL = new URL(paramString);
      String str = localURL.getPath() + localURL.getQuery();
      return str;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      localMalformedURLException.printStackTrace();
    }
    return "";
  }

  public static int a(String paramString, Map paramMap)
  {
    String str;
    if (((paramString.indexOf("pay") == -1) && (paramString.indexOf("webpay") == -1)) || (paramString.indexOf("?") == -1) || (paramString.indexOf("&") == -1) || (paramString.indexOf("=") == -1))
      str = "invalid";
    while (str.equalsIgnoreCase("pay"))
    {
      return 1;
      str = paramString.substring(9 + paramString.indexOf("tenpay://"), paramString.indexOf("?"));
      try
      {
        String[] arrayOfString1 = paramString.substring(1 + paramString.indexOf("?")).split("&");
        for (int i1 = 0; i1 < arrayOfString1.length; i1++)
        {
          String[] arrayOfString2 = arrayOfString1[i1].split("=");
          paramMap.put(arrayOfString2[0], arrayOfString2[1]);
        }
      }
      catch (Exception localException)
      {
        str = "invalid";
        localException.printStackTrace();
      }
    }
    if (str.equalsIgnoreCase("webpay"))
      return 2;
    return 0;
  }

  public static String a()
  {
    try
    {
      while (true)
      {
        Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
        while (true)
          if ((localEnumeration1 != null) && (localEnumeration1.hasMoreElements()))
          {
            Enumeration localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
            if (!localEnumeration2.hasMoreElements())
              continue;
            InetAddress localInetAddress = (InetAddress)localEnumeration2.nextElement();
            if ((localInetAddress.isLoopbackAddress()) || (!(localInetAddress instanceof Inet4Address)))
              break;
            String str = localInetAddress.getHostAddress().toString();
            return str;
          }
      }
    }
    catch (SocketException localSocketException)
    {
      localSocketException.printStackTrace();
    }
    return null;
  }

  public static String a(String paramString1, String paramString2)
  {
    int i1 = 0;
    if (paramString1 == null);
    while (true)
    {
      return "";
      String[] arrayOfString1 = paramString1.substring(1 + paramString1.indexOf('?')).split("&");
      try
      {
        int i2 = arrayOfString1.length;
        while (i1 < i2)
        {
          String[] arrayOfString2 = arrayOfString1[i1].split("=");
          if (arrayOfString2[0].equalsIgnoreCase(paramString2))
          {
            String str = arrayOfString2[1];
            return str;
          }
          i1++;
        }
      }
      catch (Exception localException)
      {
      }
    }
    return null;
  }

  public static String a(String paramString1, String paramString2, String paramString3)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)))
      return paramString1;
    StringBuilder localStringBuilder1 = new StringBuilder("");
    String str1 = "?";
    int i1 = paramString1.indexOf('?');
    int i3;
    label80: String[] arrayOfString2;
    String str2;
    label132: StringBuilder localStringBuilder2;
    if (i1 != -1)
    {
      localStringBuilder1.append(paramString1.substring(0, i1));
      String[] arrayOfString1 = paramString1.substring(i1 + 1).split("&");
      int i2 = arrayOfString1.length;
      i3 = 0;
      if (i3 >= i2)
        break label202;
      arrayOfString2 = arrayOfString1[i3].split("=");
      if ((arrayOfString2[0].equalsIgnoreCase(paramString2)) && (arrayOfString2.length == 2))
        arrayOfString2[1] = paramString3;
      if (i3 != 0)
        break label187;
      str2 = str1;
      localStringBuilder2 = localStringBuilder1.append(str2).append(arrayOfString2[0]).append("=");
      if (arrayOfString2.length != 2)
        break label195;
    }
    label187: label195: for (String str3 = arrayOfString2[1]; ; str3 = "")
    {
      localStringBuilder2.append(str3);
      i3++;
      break label80;
      str1 = "";
      break;
      str2 = "&";
      break label132;
    }
    label202: return localStringBuilder1.toString();
  }

  public static URL a(URL paramURL)
  {
    String str1 = paramURL.getFile();
    int i1 = str1.indexOf("?");
    if (i1 == -1)
      return paramURL;
    String str2 = str1.substring(0, i1);
    return new URL(paramURL.getProtocol(), paramURL.getHost(), paramURL.getPort(), str2);
  }

  public static void a(String paramString, Vector paramVector, int paramInt)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramVector == null))
      return;
    Iterator localIterator = paramVector.iterator();
    int i1 = 1;
    label20: if (localIterator.hasNext())
      if (!((String)localIterator.next()).equalsIgnoreCase(paramString))
        break label85;
    label85: for (int i2 = 0; ; i2 = i1)
    {
      i1 = i2;
      break label20;
      if (i1 == 0)
        break;
      if ((paramInt > 0) && (paramVector.size() >= paramInt))
        paramVector.remove(0);
      paramVector.add(paramString);
      return;
    }
  }

  private static boolean a(long paramLong1, long paramLong2, long paramLong3)
  {
    return (paramLong1 >= paramLong2) && (paramLong1 <= paramLong3);
  }

  public static boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return false;
      for (String str : o)
        if ((str != null) && (paramString.contains(str)))
          return true;
    }
  }

  public static String aA(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      paramString = "";
    int i1;
    do
    {
      do
        return paramString;
      while ((TextUtils.isEmpty(paramString)) || ((!paramString.contains("?sid=")) && (!paramString.contains("&sid="))));
      i1 = paramString.indexOf("sid=");
    }
    while (i1 == -1);
    String str1 = paramString.substring(0, i1);
    String str2 = paramString.substring(i1 + 4);
    if ((!TextUtils.isEmpty(str2)) && (str2.indexOf("&") > 0))
    {
      String str3 = str2.substring(1 + str2.indexOf("&"));
      return str1 + str3;
    }
    return paramString.substring(0, i1 - 1);
  }

  public static String aB(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      paramString = "";
    do
      return paramString;
    while ((!S(paramString)) && (!R(paramString)) && (!s(paramString)) && (!aC(paramString)));
    return T(paramString);
  }

  public static boolean aC(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return false;
    return paramString.toLowerCase().startsWith("mtt://");
  }

  private static boolean aD(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return false;
    char[] arrayOfChar = paramString.toCharArray();
    int i1 = arrayOfChar.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        break label44;
      if ((byte)(0xFF & arrayOfChar[i2] >> '\b') != 0)
        break;
    }
    label44: return true;
  }

  private static long aE(String paramString)
  {
    String[] arrayOfString = paramString.split("\\.");
    long l1 = Integer.parseInt(arrayOfString[0]);
    long l2 = Integer.parseInt(arrayOfString[1]);
    long l3 = Integer.parseInt(arrayOfString[2]);
    return Integer.parseInt(arrayOfString[3]) + (256L * (256L * (l1 * 256L)) + 256L * (l2 * 256L) + l3 * 256L);
  }

  private static boolean aF(String paramString)
  {
    return (paramString != null) && (paramString.startsWith("qb://"));
  }

  public static String aa(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    String str;
    do
    {
      return null;
      str = Y(paramString);
    }
    while ((str == null) || ("".equals(str)));
    return "http://" + str;
  }

  public static String ab(String paramString)
  {
    String str1 = Y(paramString);
    if ((str1 != null) && (!"".equals(str1)))
    {
      int i1 = str1.lastIndexOf('.');
      if (i1 != -1)
      {
        String str2 = str1.substring(i1 + 1);
        String str3 = str1.substring(0, i1);
        if ((str2 != null) && (str2.equalsIgnoreCase("cn")))
        {
          int i3 = str3.lastIndexOf('.');
          if (i3 != -1)
          {
            String str4 = str3.substring(i3 + 1);
            if ((str4 != null) && (str4.length() > 0) && ((str4.equalsIgnoreCase("com")) || (str4.equalsIgnoreCase("edu")) || (str4.equalsIgnoreCase("gov"))))
            {
              str2 = str4.concat(String.valueOf('.')).concat(str2);
              str3 = str3.substring(0, i3);
            }
          }
        }
        int i2 = str3.lastIndexOf('.');
        if (i2 != -1)
          str3 = str3.substring(i2 + 1);
        if ((str3 != null) && (str3.length() > 0))
          return str3.concat(String.valueOf('.')).concat(str2);
      }
    }
    return null;
  }

  public static boolean ac(String paramString)
  {
    String str = ab(paramString);
    if (!TextUtils.isEmpty(str))
      return str.equals("qq.com");
    return false;
  }

  public static String ad(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return null;
    int i1 = paramString.indexOf("://");
    int i2 = 0;
    if (i1 != -1)
      i2 = i1 + 3;
    int i3 = paramString.indexOf('/', i2);
    if (i3 != -1)
    {
      int i4 = paramString.indexOf('?', i3);
      if (i4 != -1)
        return paramString.substring(i3 + 1, i4);
      return paramString.substring(i3 + 1);
    }
    return null;
  }

  public static String ae(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return null;
    int i1 = paramString.indexOf("://");
    int i2 = 0;
    if (i1 != -1)
      i2 = i1 + 3;
    int i3 = paramString.indexOf('/', i2);
    if (i3 != -1)
      return paramString.substring(i3 + 1);
    return null;
  }

  public static boolean af(String paramString)
  {
    return (y(paramString)) || (z(paramString)) || (paramString.startsWith("about:blank")) || (g(paramString));
  }

  public static boolean ag(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    Matcher localMatcher1;
    Matcher localMatcher2;
    Matcher localMatcher3;
    Matcher localMatcher4;
    Matcher localMatcher5;
    Matcher localMatcher6;
    do
    {
      do
        return false;
      while (paramString.startsWith("data:"));
      String str = paramString.trim();
      localMatcher1 = e.matcher(str);
      localMatcher2 = f.matcher(str);
      localMatcher3 = j.matcher(str);
      localMatcher4 = g.matcher(str);
      localMatcher5 = h.matcher(str);
      localMatcher6 = i.matcher(str);
    }
    while ((!localMatcher1.find()) && (!localMatcher2.find()) && (!localMatcher3.find()) && (!localMatcher4.find()) && (!localMatcher5.find()) && (!localMatcher6.find()));
    return true;
  }

  public static boolean ah(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    String str;
    do
    {
      return false;
      str = paramString.trim();
    }
    while (!j.matcher(str).find());
    return true;
  }

  public static boolean ai(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (!ah(paramString)))
      return false;
    try
    {
      long l1 = aE(paramString);
      long l2 = aE("10.0.0.0");
      long l3 = aE("10.255.255.255");
      long l4 = aE("172.16.0.0");
      long l5 = aE("172.31.255.255");
      long l6 = aE("192.168.0.0");
      long l7 = aE("192.168.255.255");
      boolean bool;
      if ((!a(l1, l2, l3)) && (!a(l1, l4, l5)) && (!a(l1, l6, l7)) && (!paramString.equals("127.0.0.1")))
        bool = paramString.equals("1.1.1.1");
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public static boolean aj(String paramString)
  {
    String str = r(paramString);
    if (TextUtils.isEmpty(str));
    do
      return false;
    while ((!str.equals("mtt://")) && (!str.equals("hotpre://")) && (!str.equals("dttp://")) && (!str.equals("page://")) && (!str.equals("tencent://")));
    return true;
  }

  public static boolean ak(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    int i1;
    int i2;
    do
    {
      return false;
      i1 = paramString.indexOf("://");
      i2 = paramString.indexOf('.');
    }
    while ((i1 > 0) && (i2 > 0) && (i1 > i2));
    return paramString.contains("://");
  }

  public static String al(String paramString)
  {
    String str;
    if ((paramString == null) || (paramString.length() == 0))
      str = null;
    while (true)
    {
      return str;
      str = paramString.trim();
      if ((g(str)) || (n(str)))
        continue;
      if (!ag(str))
        break;
      if (!ak(str))
        return "http://" + str;
    }
    return null;
  }

  public static Uri am(String paramString)
  {
    if (I(paramString))
    {
      String str = paramString.replaceFirst("sms:", "smsto:");
      int i1 = str.indexOf('?');
      if (i1 > -1)
        return Uri.parse(str.substring(0, i1));
      return Uri.parse(str);
    }
    return null;
  }

  public static String an(String paramString)
  {
    boolean bool = I(paramString);
    String str1 = null;
    String[] arrayOfString;
    int i3;
    if (bool)
    {
      int i1 = paramString.indexOf('?');
      str1 = null;
      if (i1 > -1)
      {
        int i2 = -1 + paramString.length();
        str1 = null;
        if (i1 < i2)
        {
          arrayOfString = paramString.substring(i1 + 1).split("&");
          i3 = arrayOfString.length;
        }
      }
    }
    for (int i4 = 0; ; i4++)
    {
      str1 = null;
      if (i4 < i3)
      {
        String str2 = arrayOfString[i4];
        if (!str2.startsWith("body="))
          continue;
        int i5 = str2.indexOf('=');
        if ((i5 <= -1) || (i5 >= -1 + str2.length()))
          continue;
        str1 = str2.substring(i5 + 1);
      }
      return str1;
    }
  }

  public static boolean ao(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    do
      return false;
    while (!paramString.toLowerCase().trim().startsWith("alipay://securitypay/?"));
    return true;
  }

  public static boolean ap(String paramString)
  {
    if (paramString == null);
    do
      return false;
    while (!paramString.toLowerCase().trim().startsWith("uppay://"));
    return true;
  }

  public static boolean aq(String paramString)
  {
    boolean bool = paramString.toLowerCase().trim().contains("mqqapi://");
    int i1 = 0;
    if (bool)
      i1 = 1;
    return i1;
  }

  public static boolean ar(String paramString)
  {
    return (y(paramString)) || (z(paramString)) || (aF(paramString)) || (C(paramString)) || (H(paramString)) || (paramString.startsWith("about:blank")) || (g(paramString)) || (B(paramString));
  }

  public static String as(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return paramString;
    return paramString.replaceAll("'", "''");
  }

  public static boolean at(String paramString)
  {
    if (paramString == null);
    do
      return false;
    while (!paramString.toLowerCase().trim().startsWith("tenpay://"));
    return true;
  }

  public static boolean au(String paramString)
  {
    int i1 = 0;
    if (paramString != null)
    {
      String str = paramString.toLowerCase().trim();
      if ((!str.startsWith("qb://player/")) && (!str.startsWith("qb://addon/")))
      {
        boolean bool1 = str.startsWith("qb://app/");
        i1 = 0;
        if (bool1)
        {
          boolean bool2 = str.startsWith("qb://app/id");
          i1 = 0;
          if (bool2);
        }
      }
      else
      {
        i1 = 1;
      }
    }
    return i1;
  }

  public static String av(String paramString)
  {
    int i1 = paramString.indexOf("qb://");
    int i2 = paramString.indexOf("/", i1 + 5);
    return paramString.substring(i1 + 5, i2);
  }

  public static boolean aw(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    String str;
    do
    {
      return false;
      str = Y(paramString);
    }
    while ((TextUtils.isEmpty(str)) || (!str.equalsIgnoreCase("m.cnbeta.com")));
    return true;
  }

  public static String ax(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    try
    {
      String str = URLDecoder.decode(paramString, "utf-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
      return paramString;
    }
    catch (Exception localException)
    {
      LogUtil.d("UrlUtility", "decode url failure");
    }
    return paramString;
  }

  public static boolean ay(String paramString)
  {
    String str1 = al(paramString);
    String str2 = paramString.toLowerCase();
    int i1 = 0;
    if (str1 != null)
    {
      int i2 = paramString.lastIndexOf(".");
      i1 = 0;
      if (i2 <= 0);
    }
    for (int i3 = 0; ; i3++)
    {
      int i4 = k.length;
      i1 = 0;
      if (i3 < i4)
      {
        if (!str2.endsWith(k[i3]))
          continue;
        i1 = 1;
      }
      return i1;
    }
  }

  public static HashMap az(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    HashMap localHashMap = new HashMap();
    int i1 = paramString.indexOf('?');
    if (i1 != -1)
    {
      String[] arrayOfString = paramString.substring(i1 + 1).split("&");
      if ((arrayOfString != null) && (arrayOfString.length > 0))
      {
        int i2 = arrayOfString.length;
        int i3 = 0;
        while (true)
          if (i3 < i2)
          {
            String str1 = arrayOfString[i3];
            int i4 = str1.indexOf('=');
            String str2;
            Object localObject;
            if (i4 != -1)
            {
              str2 = str1.substring(0, i4);
              localObject = str1.substring(i4 + 1, str1.length());
            }
            try
            {
              String str3 = URLDecoder.decode((String)localObject, "UTF-8");
              localObject = str3;
              localHashMap.put(str2, localObject);
              i3++;
            }
            catch (UnsupportedEncodingException localUnsupportedEncodingException)
            {
              while (true)
                localUnsupportedEncodingException.printStackTrace();
            }
          }
      }
    }
    return (HashMap)localHashMap;
  }

  public static String b(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    do
      return paramString;
    while (paramString.charAt(0) == '#');
    try
    {
      paramString = paramString.replaceAll(" ", "%20");
      paramString = paramString.replaceAll("&amp;", "&");
      paramString = paramString.replaceAll("\\|", "%7C");
      paramString = paramString.replaceAll("\\^", "%5E");
      paramString = paramString.replaceAll("<", "%3C");
      paramString = paramString.replaceAll(">", "%3E");
      paramString = paramString.replaceAll("\\{", "%7B");
      String str2 = paramString.replaceAll("\\}", "%7D");
      str1 = str2;
      if (!I(str1))
        str1 = e(str1);
      return str1;
    }
    catch (PatternSyntaxException localPatternSyntaxException)
    {
      while (true)
      {
        String str1 = paramString;
        localPatternSyntaxException.printStackTrace();
      }
    }
  }

  public static HashMap b(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
      return null;
    HashMap localHashMap = new HashMap();
    int i1 = paramString1.indexOf(paramString2);
    String str1;
    Object localObject1;
    Object localObject2;
    if (i1 != -1)
    {
      str1 = paramString1.substring(i1 + paramString2.length(), paramString1.length());
      int i2 = str1.indexOf('/');
      if (i2 == -1)
        break label151;
      localObject1 = str1.substring(0, i2);
      localObject2 = str1.substring(i2 + 1, str1.length());
    }
    while (true)
    {
      if (!TextUtils.isEmpty((CharSequence)localObject1));
      try
      {
        String str3 = URLDecoder.decode((String)localObject1, "UTF-8");
        localObject1 = str3;
        localHashMap.put("appid", localObject1);
        if (TextUtils.isEmpty((CharSequence)localObject2));
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException2)
      {
        try
        {
          String str2 = URLDecoder.decode((String)localObject2, "UTF-8");
          localObject2 = str2;
          localHashMap.put("title", localObject2);
          return localHashMap;
          label151: localObject1 = str1;
          localObject2 = null;
          continue;
          localUnsupportedEncodingException2 = localUnsupportedEncodingException2;
          localUnsupportedEncodingException2.printStackTrace();
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException1)
        {
          while (true)
            localUnsupportedEncodingException1.printStackTrace();
        }
      }
    }
  }

  public static URL c(String paramString)
  {
    URL localURL = new URL(paramString);
    if ((localURL.getPath() == null) || ("".equals(localURL.getPath())))
    {
      if ((localURL.getFile() != null) && (localURL.getFile().startsWith("?")))
      {
        int i1 = paramString.indexOf('?');
        if (i1 != -1)
        {
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append(paramString.substring(0, i1));
          localStringBuilder2.append('/');
          localStringBuilder2.append(paramString.substring(i1));
          localURL = new URL(localStringBuilder2.toString());
        }
      }
      if ((localURL.getFile() == null) || ("".equals(localURL.getFile())))
      {
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append(paramString);
        localStringBuilder1.append("/");
        localURL = new URL(localStringBuilder1.toString());
      }
    }
    return localURL;
  }

  public static boolean d(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0) || (paramString.trim().equals("/"));
  }

  public static String e(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i1 = 0;
    if (i1 < paramString.length())
    {
      int i2 = paramString.charAt(i1);
      if (((i2 >= 19968) && (i2 <= 40959)) || ((i2 >= 65072) && (i2 <= 65440)));
      while (true)
      {
        try
        {
          String str = URLEncoder.encode(String.valueOf(i2), "utf-8");
          LogUtil.d("UrlUtility", " : " + str);
          localStringBuilder.append(str);
          i1++;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          localUnsupportedEncodingException.printStackTrace();
          continue;
        }
        localStringBuilder.append(i2);
      }
    }
    return localStringBuilder.toString();
  }

  public static String f(String paramString)
  {
    if ((paramString == null) || (paramString.trim().length() == 0))
      return paramString;
    while (true)
    {
      StringBuilder localStringBuilder;
      int i3;
      int i4;
      String str2;
      String str3;
      int i6;
      int i7;
      int i8;
      int i9;
      try
      {
        int i1 = paramString.indexOf('?');
        if (i1 == -1)
          break;
        String str1 = paramString.substring(0, i1 + 1);
        String[] arrayOfString = paramString.substring(i1 + 1).split("&");
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(str1);
        int i2 = arrayOfString.length;
        i3 = 0;
        i4 = 0;
        if (i3 >= i2)
          break label326;
        str2 = arrayOfString[i3];
        if ((str2 == null) || (str2.length() <= 0))
          break label435;
        int i5 = str2.indexOf('=');
        if (i5 == -1)
          break label315;
        localStringBuilder.append(str2.substring(0, i5 + 1));
        str3 = str2.substring(i5 + 1);
        if ((str3 == null) || (str3.length() <= 0))
          break label301;
        i6 = 1;
        i7 = 0;
        i8 = 0;
        if (i8 < str3.length())
        {
          i9 = str3.charAt(i8);
          if (i9 < 19968)
            break label384;
          if (i9 <= 40959)
            break label400;
          break label384;
          if (i6 != 0)
            continue;
          localStringBuilder.append(URLEncoder.encode(str3.substring(i7, i8), "utf-8"));
          if (i9 != 47)
            continue;
          localStringBuilder.append(i9);
          break label425;
          localStringBuilder.append(i9);
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return paramString;
      }
      if ((i6 == 0) && (i7 < i8))
        localStringBuilder.append(URLEncoder.encode(str3.substring(i7, i8), "utf-8"));
      while (true)
      {
        label301: localStringBuilder.append('&');
        i4 = 1;
        break label435;
        label315: localStringBuilder.append(str2);
        continue;
        label326: String str4 = localStringBuilder.toString();
        String str6;
        if ((i4 != 0) && (str4.charAt(-1 + str4.length()) == '&'))
          str6 = str4.substring(0, -1 + str4.length());
        for (String str5 = str6; ; str5 = str4)
          return str5;
        label384: if ((i9 < 65072) || (i9 > 65440))
          break;
        label400: int i11 = i7;
        int i10 = 0;
        while (true)
        {
          i8++;
          int i12 = i10;
          i7 = i11;
          i6 = i12;
          break;
          label425: i10 = 1;
          i11 = i8;
        }
      }
      label435: i3++;
    }
  }

  public static boolean g(String paramString)
  {
    int i1 = 0;
    if (paramString != null)
    {
      int i2 = paramString.length();
      i1 = 0;
      if (i2 > 10)
      {
        boolean bool = paramString.substring(0, 11).equalsIgnoreCase("javascript:");
        i1 = 0;
        if (bool)
          i1 = 1;
      }
    }
    return i1;
  }

  public static String h(String paramString)
  {
    int i1 = paramString.indexOf(':');
    int i2 = paramString.indexOf(';');
    if (i1 == -1);
    for (int i3 = 0; ; i3 = i1 + 1)
    {
      if (i2 == -1)
        i2 = paramString.length();
      return paramString.substring(i3, i2);
    }
  }

  public static boolean i(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return false;
    return paramString.startsWith("#");
  }

  public static boolean j(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (paramString.startsWith("mttbrowser://"));
  }

  public static boolean k(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (paramString.startsWith("http://openmobile.qq.com/api/check?page=shareindex.html&style=9"));
  }

  public static boolean l(String paramString)
  {
    String str = T(paramString);
    if (!TextUtils.isEmpty(str))
    {
      HashMap localHashMap = new HashMap();
      String[] arrayOfString1 = str.split("&");
      int i1 = arrayOfString1.length;
      for (int i2 = 0; i2 < i1; i2++)
      {
        String[] arrayOfString2 = arrayOfString1[i2].split("=", 2);
        if (arrayOfString2.length != 2)
          continue;
        localHashMap.put(arrayOfString2[0], arrayOfString2[1]);
      }
      if (!TextUtils.isEmpty((CharSequence)localHashMap.get("result")))
        return ((String)localHashMap.get("result")).equals("complete");
    }
    return false;
  }

  public static boolean m(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    String str;
    do
    {
      do
      {
        return false;
        if ((paramString != null) && (paramString.length() > 7) && (paramString.substring(0, 7).equalsIgnoreCase("mrvp://")))
          return true;
      }
      while (!ac(paramString));
      str = ad(paramString);
    }
    while ((TextUtils.isEmpty(str)) || (!str.contains("mrvp://")));
    return true;
  }

  public static boolean n(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (paramString.startsWith("samsungapps://"));
  }

  public static boolean o(String paramString)
  {
    if (paramString == null);
    String str;
    do
    {
      return false;
      str = paramString.toLowerCase();
    }
    while ((!str.startsWith("samsungapps://")) && (!str.startsWith("about:blank")));
    return true;
  }

  public static String p(String paramString)
  {
    if ((R(paramString)) || (v(paramString)) || (w(paramString)) || (s(paramString)) || (S(paramString)))
      paramString = T(paramString);
    return paramString;
  }

  public static String q(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    do
    {
      return paramString;
      if (paramString.startsWith("http://"))
        return paramString.substring("http://".length());
    }
    while (!paramString.startsWith("https://"));
    return paramString.substring("https://".length());
  }

  public static String r(String paramString)
  {
    String str = "";
    if (!TextUtils.isEmpty(paramString))
    {
      int i1 = paramString.indexOf("://");
      if (i1 > 0)
        str = paramString.substring(0, i1 + 3);
    }
    return str;
  }

  public static boolean s(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    do
      return false;
    while ((!paramString.startsWith("page:")) && (!paramString.startsWith("hotpre:")));
    return true;
  }

  public static boolean t(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    do
      return false;
    while ((!C(paramString)) && (!D(paramString)));
    return true;
  }

  public static boolean u(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return false;
    return paramString.startsWith("qb://ext/read");
  }

  public static boolean v(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return false;
    return paramString.startsWith("security://");
  }

  public static boolean w(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return false;
    return paramString.startsWith("securityFile://");
  }

  public static boolean x(String paramString)
  {
    return (paramString != null) && (paramString.startsWith("data:text/html; charset=utf-8;base64,"));
  }

  public static boolean y(String paramString)
  {
    int i1 = 0;
    if (paramString != null)
    {
      int i2 = paramString.length();
      i1 = 0;
      if (i2 > 6)
      {
        boolean bool = paramString.substring(0, 7).equalsIgnoreCase("http://");
        i1 = 0;
        if (bool)
          i1 = 1;
      }
    }
    return i1;
  }

  public static boolean z(String paramString)
  {
    int i1 = 0;
    if (paramString != null)
    {
      int i2 = paramString.length();
      i1 = 0;
      if (i2 > 7)
      {
        boolean bool = paramString.substring(0, 8).equalsIgnoreCase("https://");
        i1 = 0;
        if (bool)
          i1 = 1;
      }
    }
    return i1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.extension.UrlUtility
 * JD-Core Version:    0.6.0
 */