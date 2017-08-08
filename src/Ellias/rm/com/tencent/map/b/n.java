package com.tencent.map.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import com.tencent.map.a.a.d;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public final class n
  implements b.a, e.b, f.a, m.a
{
  private static boolean t = false;
  private static n u = null;
  private d A = null;
  private int B = 0;
  private int C = 0;
  private int D = 1;
  private String E = "";
  private String F = "";
  private String G = "";
  private String H = "";
  private String I = "";
  private String J = "";
  private boolean K = false;
  private boolean L = false;
  private long M = 0L;
  private Handler N = null;
  private Runnable O = new o(this);
  private final BroadcastReceiver P = new p(this);
  private long a = 5000L;
  private Context b = null;
  private e c = null;
  private m d = null;
  private f e = null;
  private int f = 1024;
  private int g = 4;
  private c h = null;
  private b i = null;
  private com.tencent.map.a.a.b j = null;
  private int k;
  private int l;
  private int m;
  private byte[] n = new byte[0];
  private byte[] o = new byte[0];
  private boolean p = false;
  private a q = null;
  private b r = null;
  private c s = null;
  private long v = -1L;
  private e.a w = null;
  private m.b x = null;
  private f.b y = null;
  private d z = null;

  public static n a()
  {
    monitorenter;
    try
    {
      if (u == null)
        u = new n();
      n localn = u;
      return localn;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static ArrayList<com.tencent.map.a.a.c> a(JSONArray paramJSONArray)
    throws Exception
  {
    int i1 = paramJSONArray.length();
    ArrayList localArrayList = new ArrayList();
    for (int i2 = 0; i2 < i1; i2++)
    {
      JSONObject localJSONObject = paramJSONArray.getJSONObject(i2);
      localArrayList.add(new com.tencent.map.a.a.c(localJSONObject.getString("name"), localJSONObject.getString("addr"), localJSONObject.getString("catalog"), localJSONObject.getDouble("dist"), Double.parseDouble(localJSONObject.getString("latitude")), Double.parseDouble(localJSONObject.getString("longitude"))));
    }
    return localArrayList;
  }

  private void a(String paramString)
  {
    try
    {
      this.z = new d();
      JSONObject localJSONObject1 = new JSONObject(paramString);
      JSONObject localJSONObject2 = localJSONObject1.getJSONObject("location");
      this.z.a = 1;
      this.z.b = s.a(localJSONObject2.getDouble("latitude"), 6);
      this.z.c = s.a(localJSONObject2.getDouble("longitude"), 6);
      this.z.d = s.a(localJSONObject2.getDouble("altitude"), 1);
      this.z.e = s.a(localJSONObject2.getDouble("accuracy"), 1);
      d locald1 = this.z;
      boolean bool;
      int i1;
      int i2;
      double d1;
      double d2;
      label236: int i5;
      JSONObject localJSONObject4;
      if (this.m == 1)
      {
        bool = true;
        locald1.x = bool;
        String str = localJSONObject1.getString("bearing");
        i1 = -100;
        i2 = 0;
        if (str != null)
        {
          int i3 = str.split(",").length;
          i2 = 0;
          if (i3 > 1)
            i2 = Integer.parseInt(str.split(",")[1]);
        }
        if (this.x != null)
          i1 = this.x.f;
        d locald2 = this.z;
        d1 = this.z.e;
        if (i2 < 6)
          break label674;
        d2 = 40.0D;
        locald2.e = d2;
        this.z.z = 0;
        if (((this.l == 3) || (this.l == 4)) && (this.m == 1))
        {
          JSONObject localJSONObject5 = localJSONObject1.getJSONObject("details").getJSONObject("subnation");
          this.z.a(localJSONObject5.getString("name"));
          this.z.m = localJSONObject5.getString("town");
          this.z.n = localJSONObject5.getString("village");
          this.z.o = localJSONObject5.getString("street");
          this.z.p = localJSONObject5.getString("street_no");
          this.z.z = 3;
          this.z.h = 0;
        }
        if ((this.l == 4) && (this.m == 1))
        {
          JSONArray localJSONArray = localJSONObject1.getJSONObject("details").getJSONArray("poilist");
          this.z.w = a(localJSONArray);
          this.z.z = 4;
        }
        if ((this.l == 7) && (this.m == 1))
        {
          JSONObject localJSONObject3 = localJSONObject1.getJSONObject("details");
          i5 = localJSONObject3.getInt("stat");
          localJSONObject4 = localJSONObject3.getJSONObject("subnation");
          if (i5 != 0)
            break label850;
          this.z.a(localJSONObject4.getString("name"));
          this.z.m = localJSONObject4.getString("town");
          this.z.n = localJSONObject4.getString("village");
          this.z.o = localJSONObject4.getString("street");
          this.z.p = localJSONObject4.getString("street_no");
        }
      }
      while (true)
      {
        this.z.h = i5;
        this.z.z = 7;
        this.z.y = 0;
        this.A = new d(this.z);
        this.B = 0;
        if (this.h != null)
          this.h.a(paramString);
        if ((this.j != null) && (this.k == 1) && ((this.w == null) || (!this.w.a())))
        {
          this.j.a(this.z);
          this.v = System.currentTimeMillis();
        }
        return;
        bool = false;
        break;
        label674: if (i2 == 5)
        {
          d2 = 60.0D;
          break label236;
        }
        if (i2 == 4)
        {
          d2 = 70.0D;
          break label236;
        }
        if (i2 == 3)
        {
          d2 = 90.0D;
          break label236;
        }
        if (i2 == 2)
        {
          d2 = 110.0D;
          break label236;
        }
        int i4;
        if ((i1 >= -72) && (i2 == 0))
          i4 = 10 * (int)(0.45D * d1 / 10.0D);
        while (true)
        {
          d2 = i4;
          break;
          if (d1 <= 100.0D)
          {
            i4 = 10 * (int)(1.0D + (d1 - 1.0D) / 10.0D);
            continue;
          }
          if ((d1 > 100.0D) && (d1 <= 800.0D))
          {
            i4 = 10 * (int)(0.85D * d1 / 10.0D);
            continue;
          }
          i4 = 10 * (int)(0.8D * d1 / 10.0D);
        }
        label850: if (i5 != 1)
          continue;
        this.z.i = localJSONObject4.getString("nation");
        this.z.q = localJSONObject4.getString("admin_level_1");
        this.z.r = localJSONObject4.getString("admin_level_2");
        this.z.s = localJSONObject4.getString("admin_level_3");
        this.z.t = localJSONObject4.getString("locality");
        this.z.u = localJSONObject4.getString("sublocality");
        this.z.v = localJSONObject4.getString("route");
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        this.z = new d();
        this.z.z = -1;
        this.z.y = 2;
        this.B = 2;
      }
    }
  }

  private void b(boolean paramBoolean)
  {
    if ((this.w != null) && (this.w.a()))
    {
      Location localLocation = this.w.b();
      this.z = new d();
      this.z.b = s.a(localLocation.getLatitude(), 6);
      this.z.c = s.a(localLocation.getLongitude(), 6);
      this.z.d = s.a(localLocation.getAltitude(), 1);
      this.z.e = s.a(localLocation.getAccuracy(), 1);
      this.z.f = s.a(localLocation.getSpeed(), 1);
      this.z.g = s.a(localLocation.getBearing(), 1);
      this.z.a = 0;
      this.z.x = false;
      if (paramBoolean)
        break label237;
    }
    label237: for (this.z.y = 0; ; this.z.y = 1)
    {
      this.z.z = 0;
      this.A = new d(this.z);
      this.B = 0;
      if ((System.currentTimeMillis() - this.v >= this.a) && (this.j != null) && (this.k == 1))
      {
        this.j.a(this.z);
        this.v = System.currentTimeMillis();
      }
      return;
    }
  }

  private void d()
  {
    if (this.s != null)
      return;
    this.s = new c(this.w, this.x, this.y);
    this.s.start();
  }

  private void e()
  {
    this.z = new d();
    this.B = 1;
    this.z.y = 1;
    this.z.z = -1;
    this.z.a = 1;
    if ((this.j != null) && (this.k == 1))
      this.j.a(this.z);
  }

  public final void a(double paramDouble1, double paramDouble2)
  {
    synchronized (this.o)
    {
      Message localMessage = this.q.obtainMessage(6);
      Location localLocation = new Location("Deflect");
      localLocation.setLatitude(paramDouble1);
      localLocation.setLongitude(paramDouble2);
      localMessage.obj = localLocation;
      this.q.sendMessage(localMessage);
      return;
    }
  }

  public final void a(int paramInt)
  {
    synchronized (this.o)
    {
      Message localMessage = this.q.obtainMessage(4, paramInt, 0);
      this.q.sendMessage(localMessage);
      return;
    }
  }

  public final void a(e.a parama)
  {
    synchronized (this.o)
    {
      Message localMessage = this.q.obtainMessage(1, parama);
      this.q.sendMessage(localMessage);
      return;
    }
  }

  public final void a(f.b paramb)
  {
    synchronized (this.o)
    {
      Message localMessage = this.q.obtainMessage(3, paramb);
      this.q.sendMessage(localMessage);
      return;
    }
  }

  public final void a(m.b paramb)
  {
    synchronized (this.o)
    {
      Message localMessage = this.q.obtainMessage(2, paramb);
      this.q.sendMessage(localMessage);
      return;
    }
  }

  // ERROR //
  public final boolean a(Context paramContext, com.tencent.map.a.a.b paramb)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 108	com/tencent/map/b/n:n	[B
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: aload_1
    //   8: ifnull +7 -> 15
    //   11: aload_2
    //   12: ifnonnull +7 -> 19
    //   15: aload_3
    //   16: monitorexit
    //   17: iconst_0
    //   18: ireturn
    //   19: aload_0
    //   20: getfield 152	com/tencent/map/b/n:J	Ljava/lang/String;
    //   23: invokestatic 591	com/tencent/map/b/s:a	(Ljava/lang/String;)Z
    //   26: ifne +7 -> 33
    //   29: aload_3
    //   30: monitorexit
    //   31: iconst_0
    //   32: ireturn
    //   33: aload_0
    //   34: new 555	com/tencent/map/b/n$a
    //   37: dup
    //   38: aload_0
    //   39: invokespecial 592	com/tencent/map/b/n$a:<init>	(Lcom/tencent/map/b/n;)V
    //   42: putfield 114	com/tencent/map/b/n:q	Lcom/tencent/map/b/n$a;
    //   45: aload_0
    //   46: new 594	android/os/Handler
    //   49: dup
    //   50: invokestatic 600	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   53: invokespecial 603	android/os/Handler:<init>	(Landroid/os/Looper;)V
    //   56: putfield 160	com/tencent/map/b/n:N	Landroid/os/Handler;
    //   59: aload_0
    //   60: aload_1
    //   61: putfield 90	com/tencent/map/b/n:b	Landroid/content/Context;
    //   64: aload_0
    //   65: aload_2
    //   66: putfield 106	com/tencent/map/b/n:j	Lcom/tencent/map/a/a/b;
    //   69: invokestatic 608	com/tencent/map/b/t:a	()Lcom/tencent/map/b/t;
    //   72: aload_0
    //   73: getfield 90	com/tencent/map/b/n:b	Landroid/content/Context;
    //   76: invokevirtual 614	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   79: invokevirtual 617	com/tencent/map/b/t:a	(Landroid/content/Context;)V
    //   82: aload_1
    //   83: ldc_w 619
    //   86: invokevirtual 623	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   89: checkcast 625	android/net/ConnectivityManager
    //   92: astore 9
    //   94: aload 9
    //   96: ifnull +23 -> 119
    //   99: aload 9
    //   101: invokevirtual 629	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   104: ifnull +15 -> 119
    //   107: aload_0
    //   108: aload 9
    //   110: invokevirtual 629	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   113: invokevirtual 634	android/net/NetworkInfo:isRoaming	()Z
    //   116: putfield 154	com/tencent/map/b/n:K	Z
    //   119: aload_0
    //   120: getfield 90	com/tencent/map/b/n:b	Landroid/content/Context;
    //   123: aload_0
    //   124: getfield 172	com/tencent/map/b/n:P	Landroid/content/BroadcastReceiver;
    //   127: new 636	android/content/IntentFilter
    //   130: dup
    //   131: ldc_w 638
    //   134: invokespecial 639	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   137: invokevirtual 643	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   140: pop
    //   141: aload_0
    //   142: aload_0
    //   143: getfield 106	com/tencent/map/b/n:j	Lcom/tencent/map/a/a/b;
    //   146: invokevirtual 645	com/tencent/map/a/a/b:a	()I
    //   149: putfield 357	com/tencent/map/b/n:k	I
    //   152: aload_0
    //   153: aload_0
    //   154: getfield 106	com/tencent/map/b/n:j	Lcom/tencent/map/a/a/b;
    //   157: invokevirtual 647	com/tencent/map/a/a/b:b	()I
    //   160: putfield 310	com/tencent/map/b/n:l	I
    //   163: aload_0
    //   164: aload_0
    //   165: getfield 106	com/tencent/map/b/n:j	Lcom/tencent/map/a/a/b;
    //   168: invokevirtual 649	com/tencent/map/a/a/b:c	()I
    //   171: putfield 375	com/tencent/map/b/n:m	I
    //   174: aload_0
    //   175: ldc2_w 119
    //   178: putfield 122	com/tencent/map/b/n:v	J
    //   181: aload_0
    //   182: getfield 310	com/tencent/map/b/n:l	I
    //   185: bipush 7
    //   187: if_icmpne +8 -> 195
    //   190: aload_0
    //   191: iconst_0
    //   192: putfield 310	com/tencent/map/b/n:l	I
    //   195: aload_0
    //   196: iconst_0
    //   197: putfield 156	com/tencent/map/b/n:L	Z
    //   200: aload_0
    //   201: iconst_1
    //   202: putfield 138	com/tencent/map/b/n:D	I
    //   205: aload_0
    //   206: getfield 92	com/tencent/map/b/n:c	Lcom/tencent/map/b/e;
    //   209: aload_0
    //   210: aload_0
    //   211: getfield 90	com/tencent/map/b/n:b	Landroid/content/Context;
    //   214: invokevirtual 652	com/tencent/map/b/e:a	(Lcom/tencent/map/b/e$b;Landroid/content/Context;)Z
    //   217: istore 6
    //   219: aload_0
    //   220: getfield 94	com/tencent/map/b/n:d	Lcom/tencent/map/b/m;
    //   223: aload_0
    //   224: getfield 90	com/tencent/map/b/n:b	Landroid/content/Context;
    //   227: aload_0
    //   228: invokevirtual 655	com/tencent/map/b/m:a	(Landroid/content/Context;Lcom/tencent/map/b/m$a;)Z
    //   231: istore 7
    //   233: aload_0
    //   234: getfield 96	com/tencent/map/b/n:e	Lcom/tencent/map/b/f;
    //   237: aload_0
    //   238: getfield 90	com/tencent/map/b/n:b	Landroid/content/Context;
    //   241: aload_0
    //   242: iconst_1
    //   243: invokevirtual 658	com/tencent/map/b/f:a	(Landroid/content/Context;Lcom/tencent/map/b/f$a;I)Z
    //   246: istore 8
    //   248: aload_0
    //   249: invokestatic 661	com/tencent/map/b/c:a	()Lcom/tencent/map/b/c;
    //   252: putfield 102	com/tencent/map/b/n:h	Lcom/tencent/map/b/c;
    //   255: aload_0
    //   256: invokestatic 664	com/tencent/map/b/b:a	()Lcom/tencent/map/b/b;
    //   259: putfield 104	com/tencent/map/b/n:i	Lcom/tencent/map/b/b;
    //   262: aload_0
    //   263: aconst_null
    //   264: putfield 124	com/tencent/map/b/n:w	Lcom/tencent/map/b/e$a;
    //   267: aload_0
    //   268: aconst_null
    //   269: putfield 126	com/tencent/map/b/n:x	Lcom/tencent/map/b/m$b;
    //   272: aload_0
    //   273: aconst_null
    //   274: putfield 128	com/tencent/map/b/n:y	Lcom/tencent/map/b/f$b;
    //   277: aload_0
    //   278: aconst_null
    //   279: putfield 130	com/tencent/map/b/n:z	Lcom/tencent/map/a/a/d;
    //   282: aload_0
    //   283: aconst_null
    //   284: putfield 132	com/tencent/map/b/n:A	Lcom/tencent/map/a/a/d;
    //   287: aload_0
    //   288: iconst_0
    //   289: putfield 134	com/tencent/map/b/n:B	I
    //   292: aload_0
    //   293: getfield 102	com/tencent/map/b/n:h	Lcom/tencent/map/b/c;
    //   296: ifnull +10 -> 306
    //   299: aload_0
    //   300: getfield 102	com/tencent/map/b/n:h	Lcom/tencent/map/b/c;
    //   303: invokevirtual 666	com/tencent/map/b/c:b	()V
    //   306: aload_0
    //   307: iconst_1
    //   308: putfield 136	com/tencent/map/b/n:C	I
    //   311: iload 6
    //   313: ifeq +14 -> 327
    //   316: aload_0
    //   317: getfield 375	com/tencent/map/b/n:m	I
    //   320: ifne +7 -> 327
    //   323: aload_3
    //   324: monitorexit
    //   325: iconst_1
    //   326: ireturn
    //   327: iload 7
    //   329: ifne +8 -> 337
    //   332: iload 8
    //   334: ifeq +7 -> 341
    //   337: aload_3
    //   338: monitorexit
    //   339: iconst_1
    //   340: ireturn
    //   341: aload_3
    //   342: monitorexit
    //   343: iconst_0
    //   344: ireturn
    //   345: astore 4
    //   347: aload_3
    //   348: monitorexit
    //   349: aload 4
    //   351: athrow
    //   352: astore 5
    //   354: goto -213 -> 141
    //
    // Exception table:
    //   from	to	target	type
    //   19	31	345	finally
    //   33	82	345	finally
    //   82	94	345	finally
    //   99	119	345	finally
    //   119	141	345	finally
    //   141	195	345	finally
    //   195	306	345	finally
    //   306	311	345	finally
    //   316	325	345	finally
    //   82	94	352	java/lang/Exception
    //   99	119	352	java/lang/Exception
    //   119	141	352	java/lang/Exception
  }

  public final boolean a(String paramString1, String paramString2)
  {
    synchronized (this.n)
    {
      if (a.a().a(paramString1, paramString2))
      {
        this.J = paramString1;
        return true;
      }
      return false;
    }
  }

  public final void b()
  {
    try
    {
      synchronized (this.n)
      {
        if (this.j != null)
        {
          this.j = null;
          this.N.removeCallbacks(this.O);
          this.b.unregisterReceiver(this.P);
          this.c.a();
          this.d.a();
          this.e.a();
        }
        label62: return;
      }
    }
    catch (Exception localException)
    {
      break label62;
    }
  }

  public final void b(int paramInt)
  {
    synchronized (this.o)
    {
      Message localMessage = this.q.obtainMessage(5, paramInt, 0);
      this.q.sendMessage(localMessage);
      return;
    }
  }

  final class a extends Handler
  {
    public a()
    {
      super();
    }

    public final void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 256:
      case 16:
      case 8:
      }
      do
      {
        do
        {
          do
          {
            return;
            n.a(n.this, (e.a)paramMessage.obj);
            return;
            n.a(n.this, (m.b)paramMessage.obj);
            return;
            n.a(n.this, (f.b)paramMessage.obj);
            return;
            n.a(n.this, paramMessage.arg1);
            return;
            n.b(n.this, paramMessage.arg1);
            return;
            n.a(n.this, (Location)paramMessage.obj);
            return;
          }
          while (n.e(n.this) != 1);
          n.c(n.this);
          return;
        }
        while (paramMessage.obj == null);
        n.a(n.this, (String)paramMessage.obj);
        n.a(n.this, null);
        return;
        if (paramMessage.arg1 != 0)
          continue;
        n.b(n.this, (String)paramMessage.obj);
        return;
      }
      while ((n.f(n.this) != null) && (n.f(n.this).a()));
      n.g(n.this);
    }
  }

  final class b extends Thread
  {
    private String a = null;
    private String b = null;
    private String c = null;

    public b(String arg2)
    {
      Object localObject;
      this.a = localObject;
      StringBuilder localStringBuilder = new StringBuilder();
      if (n.h(n.this) == 0);
      for (String str = "http://lstest.map.soso.com/loc?c=1"; ; str = "http://lbs.map.qq.com/loc?c=1")
      {
        this.b = (str + "&mars=" + n.i(n.this));
        return;
      }
    }

    private String a(byte[] paramArrayOfByte, String paramString)
    {
      n.a(n.this, System.currentTimeMillis());
      StringBuffer localStringBuffer = new StringBuffer();
      try
      {
        localStringBuffer.append(new String(paramArrayOfByte, paramString));
        return localStringBuffer.toString();
      }
      catch (Exception localException)
      {
      }
      return null;
    }

    public final void run()
    {
      Message localMessage = new Message();
      localMessage.what = 8;
      int i;
      try
      {
        byte[] arrayOfByte2 = r.a(this.a.getBytes());
        n.a(n.this, true);
        u localu2 = b.a(this.b, "SOSO MAP LBS SDK", arrayOfByte2);
        n.a(n.this, false);
        this.c = a(r.b(localu2.a), localu2.b);
        if (this.c != null)
        {
          localMessage.arg1 = 0;
          localMessage.obj = this.c;
        }
        while (true)
        {
          n.j(n.this);
          n.d(n.this).sendMessage(localMessage);
          return;
          localMessage.arg1 = 1;
        }
      }
      catch (Exception localException1)
      {
        i = 0;
      }
      while (true)
        while (true)
        {
          i++;
          if (i <= 3);
          try
          {
            sleep(1000L);
            byte[] arrayOfByte1 = r.a(this.a.getBytes());
            n.a(n.this, true);
            u localu1 = b.a(this.b, "SOSO MAP LBS SDK", arrayOfByte1);
            n.a(n.this, false);
            this.c = a(r.b(localu1.a), localu1.b);
            if (this.c != null)
            {
              localMessage.arg1 = 0;
              localMessage.obj = this.c;
              continue;
            }
            localMessage.arg1 = 1;
            continue;
            n.a(n.this, false);
            localMessage.arg1 = 1;
          }
          catch (Exception localException2)
          {
          }
        }
    }
  }

  final class c extends Thread
  {
    private e.a a = null;
    private m.b b = null;
    private f.b c = null;

    c(e.a paramb, m.b paramb1, f.b arg4)
    {
      if (paramb != null)
        this.a = ((e.a)paramb.clone());
      if (paramb1 != null)
        this.b = ((m.b)paramb1.clone());
      Object localObject;
      if (localObject != null)
        this.c = ((f.b)localObject.clone());
    }

    public final void run()
    {
      if (!n.c());
      try
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)n.k(n.this).getSystemService("phone");
        n.c(n.this, localTelephonyManager.getDeviceId());
        n.d(n.this, localTelephonyManager.getSubscriberId());
        n.e(n.this, localTelephonyManager.getLine1Number());
        Pattern localPattern = Pattern.compile("[0-9a-zA-Z+-]*");
        n localn5 = n.this;
        String str12;
        String str17;
        label135: label143: String str13;
        label163: String str16;
        label209: label217: String str14;
        label237: String str15;
        label283: label291: String str8;
        label316: String str9;
        label344: String str10;
        label372: String str11;
        label400: String str1;
        label430: String str2;
        String str3;
        if (n.l(n.this) == null)
        {
          str12 = "";
          n.c(localn5, str12);
          if (!localPattern.matcher(n.l(n.this)).matches())
            break label707;
          n localn10 = n.this;
          if (n.l(n.this) != null)
            break label695;
          str17 = "";
          n.c(localn10, str17);
          n localn6 = n.this;
          if (n.m(n.this) != null)
            break label720;
          str13 = "";
          n.d(localn6, str13);
          if (!localPattern.matcher(n.m(n.this)).matches())
            break label744;
          n localn9 = n.this;
          if (n.m(n.this) != null)
            break label732;
          str16 = "";
          n.d(localn9, str16);
          n localn7 = n.this;
          if (n.n(n.this) != null)
            break label757;
          str14 = "";
          n.e(localn7, str14);
          if (!localPattern.matcher(n.n(n.this)).matches())
            break label781;
          n localn8 = n.this;
          if (n.n(n.this) != null)
            break label769;
          str15 = "";
          n.e(localn8, str15);
          n.a(true);
          n localn1 = n.this;
          if (n.l(n.this) != null)
            break label794;
          str8 = "";
          n.c(localn1, str8);
          n localn2 = n.this;
          if (n.m(n.this) != null)
            break label806;
          str9 = "";
          n.d(localn2, str9);
          n localn3 = n.this;
          if (n.n(n.this) != null)
            break label818;
          str10 = "";
          n.e(localn3, str10);
          n localn4 = n.this;
          if (n.l(n.this) != null)
            break label830;
          str11 = "0123456789ABCDEF";
          n.f(localn4, r.a(str11));
          if (n.o(n.this) != 4)
            break label842;
          str1 = s.a(this.c);
          str2 = s.a(this.b, n.p(n.this).b());
          str3 = s.a(n.l(n.this), n.m(n.this), n.n(n.this), n.q(n.this), n.r(n.this));
          if ((this.a == null) || (!this.a.a()))
            break label848;
        }
        label695: label707: label842: label848: for (String str4 = s.a(this.a); ; str4 = "{}")
        {
          String str5 = "{\"version\":\"1.1.8\",\"address\":" + n.s(n.this);
          String str6 = str5 + ",\"source\":203,\"access_token\":\"" + n.t(n.this) + "\",\"app_name\":" + "\"" + n.u(n.this) + "\",\"bearing\":1";
          String str7 = str6 + ",\"attribute\":" + str3 + ",\"location\":" + str4 + ",\"cells\":" + str2 + ",\"wifis\":" + str1 + "}";
          Message localMessage = n.d(n.this).obtainMessage(16, str7);
          n.d(n.this).sendMessage(localMessage);
          return;
          str12 = n.l(n.this);
          break;
          str17 = n.l(n.this);
          break label135;
          n.c(n.this, "");
          break label143;
          label720: str13 = n.m(n.this);
          break label163;
          label732: str16 = n.m(n.this);
          break label209;
          label744: n.d(n.this, "");
          break label217;
          label757: str14 = n.n(n.this);
          break label237;
          str15 = n.n(n.this);
          break label283;
          n.e(n.this, "");
          break label291;
          str8 = n.l(n.this);
          break label316;
          str9 = n.m(n.this);
          break label344;
          str10 = n.n(n.this);
          break label372;
          str11 = n.l(n.this);
          break label400;
          str1 = "[]";
          break label430;
        }
      }
      catch (Exception localException)
      {
        label769: label781: label794: label806: label818: label830: break label291;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.map.b.n
 * JD-Core Version:    0.6.0
 */