package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.a.a;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TaskGuide extends BaseApi
{
  private static int K;
  static long b;
  private static Drawable k;
  private static Drawable l;
  private static Drawable m;
  private static int n = 75;
  private static int o = 284;
  private static int p = 75;
  private static int q = 30;
  private static int r = 29;
  private static int s = 5;
  private static int t = 74;
  private static int u = 0;
  private static int v = 6;
  private static int w = 153;
  private static int x = 30;
  private static int y = 6;
  private static int z = 3;
  private int A = 0;
  private int B = 0;
  private float C = 0.0F;
  private Interpolator D = new AccelerateInterpolator();
  private boolean E = false;
  private boolean F = false;
  private boolean G = false;
  private long H;
  private int I;
  private int J;
  private Runnable L = null;
  private Runnable M = null;
  boolean a = false;
  IUiListener c;
  private WindowManager.LayoutParams d = null;
  private ViewGroup e = null;
  private WindowManager f;
  private Handler g = new Handler(Looper.getMainLooper());
  private i h;
  private d i = d.a;
  private d j = d.a;

  static
  {
    b = 5000L;
    K = 3000;
  }

  public TaskGuide(Context paramContext, QQAuth paramQQAuth, QQToken paramQQToken)
  {
    super(paramContext, paramQQAuth, paramQQToken);
    this.f = ((WindowManager)paramContext.getSystemService("window"));
    c();
  }

  public TaskGuide(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
    this.f = ((WindowManager)paramContext.getSystemService("window"));
    c();
  }

  private int a(int paramInt)
  {
    return (int)(paramInt * this.C);
  }

  private Drawable a(String paramString, Context paramContext)
  {
    AssetManager localAssetManager = paramContext.getApplicationContext().getAssets();
    InputStream localInputStream;
    Object localObject;
    IOException localIOException2;
    try
    {
      localInputStream = localAssetManager.open(paramString);
      if (localInputStream == null)
        return null;
      if (paramString.endsWith(".9.png"))
      {
        Bitmap localBitmap = BitmapFactory.decodeStream(localInputStream);
        if (localBitmap != null)
        {
          byte[] arrayOfByte = localBitmap.getNinePatchChunk();
          NinePatch.isNinePatchChunk(arrayOfByte);
          NinePatchDrawable localNinePatchDrawable = new NinePatchDrawable(localBitmap, arrayOfByte, new Rect(), null);
          return localNinePatchDrawable;
        }
      }
    }
    catch (IOException localIOException1)
    {
      localObject = null;
      localIOException2 = localIOException1;
    }
    while (true)
    {
      localIOException2.printStackTrace();
      return localObject;
      return null;
      Drawable localDrawable = Drawable.createFromStream(localInputStream, paramString);
      localObject = localDrawable;
      try
      {
        localInputStream.close();
        return localObject;
      }
      catch (IOException localIOException3)
      {
      }
    }
  }

  private WindowManager.LayoutParams a(Context paramContext)
  {
    WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
    localLayoutParams.gravity = 49;
    this.f.getDefaultDisplay().getWidth();
    this.f.getDefaultDisplay().getHeight();
    localLayoutParams.width = a(o);
    localLayoutParams.height = a(n);
    localLayoutParams.windowAnimations = 16973826;
    localLayoutParams.format = 1;
    localLayoutParams.flags = (0x208 | localLayoutParams.flags);
    localLayoutParams.type = 2;
    this.d = localLayoutParams;
    return localLayoutParams;
  }

  private void a(int paramInt, d paramd)
  {
    if (paramInt == 0)
    {
      this.i = paramd;
      return;
    }
    if (paramInt == 1)
    {
      this.j = paramd;
      return;
    }
    this.i = paramd;
    this.j = paramd;
  }

  private void a(String paramString)
  {
    this.g.post(new Runnable(paramString)
    {
      public void run()
      {
        Toast.makeText(TaskGuide.z(TaskGuide.this), "失败：" + this.a, 1).show();
      }
    });
  }

  private void a(boolean paramBoolean)
  {
    this.H = SystemClock.currentThreadTimeMillis();
    if (paramBoolean)
      this.F = true;
    while (true)
    {
      this.I = this.d.height;
      this.J = this.d.y;
      WindowManager.LayoutParams localLayoutParams = this.d;
      localLayoutParams.flags = (0x10 | localLayoutParams.flags);
      this.f.updateViewLayout(this.e, this.d);
      return;
      this.G = true;
    }
  }

  private ViewGroup b(Context paramContext)
  {
    j localj = new j(paramContext);
    e[] arrayOfe = this.h.c;
    if (arrayOfe.length == 1)
    {
      h localh1 = new h(paramContext, arrayOfe[0]);
      localh1.setId(1);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams1.addRule(15);
      localj.addView(localh1, localLayoutParams1);
    }
    while (true)
    {
      localj.setBackgroundDrawable(e());
      return localj;
      h localh2 = new h(paramContext, arrayOfe[0]);
      localh2.setId(1);
      h localh3 = new h(paramContext, arrayOfe[1]);
      localh3.setId(2);
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams2.addRule(14);
      localLayoutParams2.setMargins(0, a(6), 0, 0);
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams3.addRule(14);
      localLayoutParams3.setMargins(0, a(4), 0, 0);
      localLayoutParams3.addRule(3, 1);
      localLayoutParams3.addRule(5, 1);
      localj.addView(localh2, localLayoutParams2);
      localj.addView(localh3, localLayoutParams3);
    }
  }

  private void b(int paramInt)
  {
    if (this.g != null)
      this.g.post(new Runnable(paramInt)
      {
        public void run()
        {
          if (TaskGuide.a(TaskGuide.this))
          {
            if (this.a != 0)
              break label42;
            ((TaskGuide.h)TaskGuide.b(TaskGuide.this).findViewById(1)).a(TaskGuide.c(TaskGuide.this));
          }
          label42: 
          do
          {
            do
            {
              return;
              if (this.a != 1)
                continue;
              ((TaskGuide.h)TaskGuide.b(TaskGuide.this).findViewById(2)).a(TaskGuide.d(TaskGuide.this));
              return;
            }
            while (this.a != 2);
            ((TaskGuide.h)TaskGuide.b(TaskGuide.this).findViewById(1)).a(TaskGuide.c(TaskGuide.this));
          }
          while (TaskGuide.b(TaskGuide.this).getChildCount() <= 1);
          ((TaskGuide.h)TaskGuide.b(TaskGuide.this).findViewById(2)).a(TaskGuide.d(TaskGuide.this));
        }
      });
  }

  private d c(int paramInt)
  {
    if (paramInt == 0)
      return this.i;
    if (paramInt == 1)
      return this.j;
    return d.a;
  }

  private void c()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.f.getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.A = localDisplayMetrics.widthPixels;
    this.B = localDisplayMetrics.heightPixels;
    this.C = localDisplayMetrics.density;
  }

  private void d()
  {
    if (this.d != null)
      this.d.y = (-this.d.height);
  }

  private void d(int paramInt)
  {
    h();
    this.M = new k(null);
    this.g.postDelayed(this.M, paramInt);
  }

  private Drawable e()
  {
    if (k == null)
      k = a("background.9.png", this.mContext);
    return k;
  }

  private void e(int paramInt)
  {
    Bundle localBundle = composeCGIParams();
    localBundle.putString("action", "get_gift");
    localBundle.putString("task_id", this.h.a);
    localBundle.putString("step_no", new Integer(paramInt).toString());
    localBundle.putString("appid", this.mToken.getAppId());
    a locala = new a(paramInt);
    HttpUtils.requestAsync(this.mToken, this.mContext, "http://appact.qzone.qq.com/appstore_activity_task_pcpush_sdk", localBundle, "GET", locala);
    a(paramInt, d.c);
    a.a(this.mContext, this.mToken, "TaskApi", new String[] { "getGift" });
  }

  private Drawable f()
  {
    if (l == null)
      l = a("button_green.9.png", this.mContext);
    return l;
  }

  private Drawable g()
  {
    if (m == null)
      m = a("button_red.9.png", this.mContext);
    return m;
  }

  private void h()
  {
    this.g.removeCallbacks(this.M);
    if (!j())
      this.g.removeCallbacks(this.L);
  }

  private void i()
  {
    if (this.F)
      d(3000);
    while (true)
    {
      if (this.F)
      {
        WindowManager.LayoutParams localLayoutParams = this.d;
        localLayoutParams.flags = (0xFFFFFFEF & localLayoutParams.flags);
        this.f.updateViewLayout(this.e, this.d);
      }
      this.F = false;
      this.G = false;
      return;
      removeWindow();
    }
  }

  private boolean j()
  {
    return (this.F) || (this.G);
  }

  private void k()
  {
    if (!j())
    {
      this.g.removeCallbacks(this.M);
      this.g.removeCallbacks(this.L);
      this.L = new b(true);
      a(true);
      this.g.post(this.L);
    }
  }

  private void l()
  {
    if (!j())
    {
      this.g.removeCallbacks(this.M);
      this.g.removeCallbacks(this.L);
      this.L = new b(false);
      a(false);
      this.g.post(this.L);
    }
  }

  public void removeWindow()
  {
    if (this.E)
    {
      this.f.removeView(this.e);
      this.E = false;
    }
  }

  public void showTaskGuideWindow(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    this.mContext = paramActivity;
    this.c = paramIUiListener;
    if ((this.i == d.b) || (this.j == d.b) || (this.E))
      return;
    this.h = null;
    Bundle localBundle;
    if (paramBundle != null)
    {
      localBundle = new Bundle(paramBundle);
      localBundle.putAll(composeCGIParams());
    }
    while (true)
    {
      c localc = new c(null);
      localBundle.putString("action", "task_list");
      localBundle.putString("auth", "mobile");
      localBundle.putString("appid", this.mToken.getAppId());
      HttpUtils.requestAsync(this.mToken, this.mContext, "http://appact.qzone.qq.com/appstore_activity_task_pcpush_sdk", localBundle, "GET", localc);
      a(2, d.b);
      return;
      localBundle = composeCGIParams();
    }
  }

  @SuppressLint({"ResourceAsColor"})
  public void showWindow()
  {
    new Handler(Looper.getMainLooper()).post(new Runnable()
    {
      public void run()
      {
        TaskGuide.a(TaskGuide.this, TaskGuide.a(TaskGuide.this, TaskGuide.f(TaskGuide.this)));
        TaskGuide.a(TaskGuide.this, TaskGuide.b(TaskGuide.this, TaskGuide.g(TaskGuide.this)));
        TaskGuide.h(TaskGuide.this);
        WindowManager localWindowManager = (WindowManager)TaskGuide.i(TaskGuide.this).getSystemService("window");
        if (((Activity)TaskGuide.j(TaskGuide.this)).isFinishing())
          return;
        if (!TaskGuide.a(TaskGuide.this))
          localWindowManager.addView(TaskGuide.b(TaskGuide.this), TaskGuide.k(TaskGuide.this));
        TaskGuide.a(TaskGuide.this, true);
        TaskGuide.c(TaskGuide.this, 2);
        TaskGuide.l(TaskGuide.this);
      }
    });
    a.a(this.mContext, this.mToken, "TaskApi", new String[] { "showTaskWindow" });
  }

  private class a extends TaskGuide.g
  {
    int a = -1;

    public a(int arg2)
    {
      super(null);
      int i;
      this.a = i;
    }

    protected void a(Exception paramException)
    {
      if (paramException != null)
        paramException.printStackTrace();
      TaskGuide.this.c.onError(new UiError(101, "error ", "金券领取时出现异常"));
      if (TaskGuide.x(TaskGuide.this) != null)
        TaskGuide.x(TaskGuide.this).post(new Runnable(paramException)
        {
          public void run()
          {
            if (TaskGuide.a.this.a == 0);
            for (TaskGuide.d locald = TaskGuide.c(TaskGuide.this); ; locald = TaskGuide.d(TaskGuide.this))
            {
              if (locald == TaskGuide.d.c)
              {
                TaskGuide.a(TaskGuide.this, TaskGuide.a.this.a, TaskGuide.d.d);
                TaskGuide.a(TaskGuide.this, "领取失败 :" + this.a.getClass().getName());
              }
              TaskGuide.c(TaskGuide.this, TaskGuide.a.this.a);
              TaskGuide.e(TaskGuide.this, 2000);
              return;
            }
          }
        });
    }

    public void onComplete(JSONObject paramJSONObject)
    {
      String str = null;
      while (true)
      {
        JSONObject localJSONObject2;
        try
        {
          int i = paramJSONObject.getInt("code");
          str = paramJSONObject.getString("message");
          if (i != 0)
            continue;
          TaskGuide.a(TaskGuide.this, this.a, TaskGuide.d.e);
          JSONObject localJSONObject1 = new JSONObject();
          try
          {
            localJSONObject1.put("result", "金券领取成功");
            TaskGuide.this.c.onComplete(localJSONObject1);
            TaskGuide.c(TaskGuide.this, this.a);
            TaskGuide.e(TaskGuide.this, 2000);
            return;
          }
          catch (JSONException localJSONException2)
          {
            localJSONException2.printStackTrace();
            continue;
          }
        }
        catch (JSONException localJSONException1)
        {
          TaskGuide.a(TaskGuide.this, this.a, TaskGuide.d.d);
          TaskGuide.a(TaskGuide.this, str);
          localJSONException1.printStackTrace();
          continue;
          TaskGuide.a(TaskGuide.this, this.a, TaskGuide.d.d);
          TaskGuide.a(TaskGuide.this, str);
          localJSONObject2 = new JSONObject();
        }
        try
        {
          localJSONObject2.put("result", "金券领取失败");
          TaskGuide.this.c.onComplete(localJSONObject2);
        }
        catch (JSONException localJSONException3)
        {
          while (true)
            localJSONException3.printStackTrace();
        }
      }
    }
  }

  class b
    implements Runnable
  {
    boolean a = false;
    float b = 0.0F;

    public b(boolean arg2)
    {
      boolean bool;
      this.a = bool;
    }

    public void run()
    {
      int i = 1;
      SystemClock.currentThreadTimeMillis();
      this.b = (float)(0.1D + this.b);
      float f = this.b;
      if (f > 1.0F)
        f = 1.0F;
      int j;
      int k;
      if (f >= 1.0F)
      {
        j = i;
        k = (int)(TaskGuide.r(TaskGuide.this).getInterpolation(f) * TaskGuide.s(TaskGuide.this));
        if (!this.a)
          break label171;
        TaskGuide.k(TaskGuide.this).y = (k + TaskGuide.t(TaskGuide.this));
        label99: Log.d("TAG", "mWinParams.y = " + TaskGuide.k(TaskGuide.this).y + "deltaDistence = " + k);
        if (TaskGuide.a(TaskGuide.this))
          break label194;
      }
      while (true)
      {
        if (i == 0)
          break label226;
        TaskGuide.v(TaskGuide.this);
        return;
        j = 0;
        break;
        label171: TaskGuide.k(TaskGuide.this).y = (TaskGuide.t(TaskGuide.this) - k);
        break label99;
        label194: TaskGuide.u(TaskGuide.this).updateViewLayout(TaskGuide.b(TaskGuide.this), TaskGuide.k(TaskGuide.this));
        i = j;
      }
      label226: TaskGuide.x(TaskGuide.this).postDelayed(TaskGuide.w(TaskGuide.this), 5L);
    }
  }

  private class c extends TaskGuide.g
  {
    private c()
    {
      super(null);
    }

    protected void a(Exception paramException)
    {
      if (paramException != null)
        paramException.printStackTrace();
      JSONObject localJSONObject;
      if (paramException == null)
        localJSONObject = new JSONObject();
      while (true)
      {
        try
        {
          localJSONObject.put("result", "暂无任务");
          TaskGuide.this.c.onComplete(localJSONObject);
          TaskGuide.x(TaskGuide.this).post(new Runnable()
          {
            public void run()
            {
              TaskGuide.a(TaskGuide.this, 2, TaskGuide.d.a);
            }
          });
          return;
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          continue;
        }
        TaskGuide.this.c.onError(new UiError(100, "error ", "获取任务失败"));
      }
    }

    public void onComplete(JSONObject paramJSONObject)
    {
      try
      {
        TaskGuide.a(TaskGuide.this, TaskGuide.i.a(paramJSONObject));
        if ((TaskGuide.y(TaskGuide.this) != null) && (TaskGuide.y(TaskGuide.this).a()))
        {
          TaskGuide.this.showWindow();
          TaskGuide.a(TaskGuide.this, 2, TaskGuide.d.d);
          localJSONObject = new JSONObject();
        }
      }
      catch (JSONException localJSONException1)
      {
        try
        {
          JSONObject localJSONObject;
          localJSONObject.put("result", "获取成功");
          TaskGuide.this.c.onComplete(localJSONObject);
          return;
          localJSONException1 = localJSONException1;
          localJSONException1.printStackTrace();
        }
        catch (JSONException localJSONException2)
        {
          while (true)
            localJSONException2.printStackTrace();
        }
        a(null);
      }
    }
  }

  private static enum d
  {
    static
    {
      d[] arrayOfd = new d[6];
      arrayOfd[0] = a;
      arrayOfd[1] = b;
      arrayOfd[2] = c;
      arrayOfd[3] = d;
      arrayOfd[4] = e;
      arrayOfd[5] = f;
      g = arrayOfd;
    }

    public static d[] a()
    {
      return (d[])g.clone();
    }
  }

  private static class e
  {
    int a;
    String b;
    String c;
    long d;
    int e;

    public e(int paramInt1, String paramString1, String paramString2, long paramLong, int paramInt2)
    {
      this.a = paramInt1;
      this.b = paramString1;
      this.c = paramString2;
      this.d = paramLong;
      this.e = paramInt2;
    }
  }

  class f
    implements View.OnClickListener
  {
    int a;

    public f(int arg2)
    {
      int i;
      this.a = i;
    }

    public void onClick(View paramView)
    {
      ((Button)paramView);
      if (TaskGuide.a(TaskGuide.this, this.a) == TaskGuide.d.d)
      {
        TaskGuide.b(TaskGuide.this, this.a);
        TaskGuide.c(TaskGuide.this, this.a);
      }
      TaskGuide.e(TaskGuide.this);
    }
  }

  private abstract class g
    implements IRequestListener
  {
    private g()
    {
    }

    protected abstract void a(Exception paramException);

    public void onConnectTimeoutException(ConnectTimeoutException paramConnectTimeoutException)
    {
      a(paramConnectTimeoutException);
    }

    public void onHttpStatusException(HttpUtils.HttpStatusException paramHttpStatusException)
    {
      a(paramHttpStatusException);
    }

    public void onIOException(IOException paramIOException)
    {
      a(paramIOException);
    }

    public void onJSONException(JSONException paramJSONException)
    {
      a(paramJSONException);
    }

    public void onMalformedURLException(MalformedURLException paramMalformedURLException)
    {
      a(paramMalformedURLException);
    }

    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException paramNetworkUnavailableException)
    {
      a(paramNetworkUnavailableException);
    }

    public void onSocketTimeoutException(SocketTimeoutException paramSocketTimeoutException)
    {
      a(paramSocketTimeoutException);
    }

    public void onUnknowException(Exception paramException)
    {
      a(paramException);
    }
  }

  private class h extends LinearLayout
  {
    private TextView b;
    private Button c;
    private TaskGuide.e d;

    public h(Context parame, TaskGuide.e arg3)
    {
      super();
      Object localObject;
      this.d = localObject;
      setOrientation(0);
      a();
    }

    private void a()
    {
      this.b = new TextView(TaskGuide.m(TaskGuide.this));
      this.b.setTextColor(Color.rgb(255, 255, 255));
      this.b.setTextSize(15.0F);
      this.b.setShadowLayer(1.0F, 1.0F, 1.0F, Color.rgb(242, 211, 199));
      this.b.setGravity(3);
      this.b.setEllipsize(TextUtils.TruncateAt.END);
      this.b.setIncludeFontPadding(false);
      this.b.setSingleLine(true);
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(0, -2);
      localLayoutParams1.weight = 1.0F;
      localLayoutParams1.leftMargin = TaskGuide.d(TaskGuide.this, 4);
      addView(this.b, localLayoutParams1);
      this.c = new Button(TaskGuide.n(TaskGuide.this));
      this.c.setPadding(0, 0, 0, 0);
      this.c.setTextSize(16.0F);
      this.c.setTextColor(Color.rgb(255, 255, 255));
      this.c.setShadowLayer(1.0F, 1.0F, 1.0F, Color.rgb(242, 211, 199));
      this.c.setIncludeFontPadding(false);
      this.c.setOnClickListener(new TaskGuide.f(TaskGuide.this, this.d.a));
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(TaskGuide.d(TaskGuide.this, TaskGuide.a()), TaskGuide.d(TaskGuide.this, TaskGuide.b()));
      localLayoutParams2.leftMargin = TaskGuide.d(TaskGuide.this, 2);
      localLayoutParams2.rightMargin = TaskGuide.d(TaskGuide.this, 8);
      addView(this.c, localLayoutParams2);
    }

    public void a(TaskGuide.d paramd)
    {
      if (!TextUtils.isEmpty(this.d.b))
        this.b.setText(this.d.b);
      switch (TaskGuide.1.a[paramd.ordinal()])
      {
      default:
      case 1:
      case 2:
        do
        {
          return;
          this.c.setEnabled(false);
          return;
          if (this.d.e != 1)
            continue;
          this.c.setText(this.d.c);
          this.c.setBackgroundDrawable(null);
          this.c.setTextColor(Color.rgb(255, 246, 0));
          this.c.setEnabled(false);
          return;
        }
        while (this.d.e != 2);
        this.c.setText("领取奖励");
        this.c.setTextColor(Color.rgb(255, 255, 255));
        this.c.setBackgroundDrawable(TaskGuide.o(TaskGuide.this));
        this.c.setEnabled(true);
        return;
      case 3:
        this.c.setText("领取中...");
        this.c.setEnabled(false);
        return;
      case 4:
      }
      this.c.setText("已领取");
      this.c.setBackgroundDrawable(TaskGuide.p(TaskGuide.this));
      this.c.setEnabled(false);
    }
  }

  private static class i
  {
    String a;
    String b;
    TaskGuide.e[] c;

    static i a(JSONObject paramJSONObject)
      throws JSONException
    {
      if (paramJSONObject == null)
        return null;
      i locali = new i();
      JSONObject localJSONObject1 = paramJSONObject.getJSONObject("task_info");
      locali.a = localJSONObject1.getString("task_id");
      locali.b = localJSONObject1.getString("task_desc");
      JSONArray localJSONArray = localJSONObject1.getJSONArray("step_info");
      int i = localJSONArray.length();
      if (i > 0)
        locali.c = new TaskGuide.e[i];
      for (int j = 0; j < i; j++)
      {
        JSONObject localJSONObject2 = localJSONArray.getJSONObject(j);
        int k = localJSONObject2.getInt("step_no");
        int m = localJSONObject2.getInt("status");
        TaskGuide.e locale = new TaskGuide.e(k, localJSONObject2.getString("step_desc"), localJSONObject2.getString("step_gift"), localJSONObject2.getLong("end_time"), m);
        locali.c[j] = locale;
      }
      return locali;
    }

    public boolean a()
    {
      return (!TextUtils.isEmpty(this.a)) && (this.c != null) && (this.c.length > 0);
    }
  }

  private class j extends RelativeLayout
  {
    int a = 0;

    public j(Context arg2)
    {
      super();
    }

    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
      int i = (int)paramMotionEvent.getY();
      Log.d("XXXX", "onInterceptTouchEvent-- action = " + paramMotionEvent.getAction() + "currentY = " + i);
      TaskGuide.e(TaskGuide.this, 3000);
      switch (paramMotionEvent.getAction())
      {
      default:
      case 0:
      case 1:
      }
      do
      {
        return super.onInterceptTouchEvent(paramMotionEvent);
        this.a = i;
        return false;
      }
      while (this.a - i <= 2 * ViewConfiguration.getTouchSlop());
      TaskGuide.q(TaskGuide.this);
      return true;
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      super.onTouchEvent(paramMotionEvent);
      int i = (int)paramMotionEvent.getY();
      Log.d("XXXX", " onTouchEvent-----startY = " + this.a + "currentY = " + i);
      switch (paramMotionEvent.getAction())
      {
      case 2:
      default:
      case 0:
      case 1:
      }
      while (true)
      {
        return false;
        this.a = i;
        continue;
        if (this.a - i <= 2 * ViewConfiguration.getTouchSlop())
          continue;
        TaskGuide.q(TaskGuide.this);
      }
    }
  }

  private class k
    implements Runnable
  {
    private k()
    {
    }

    public void run()
    {
      TaskGuide.q(TaskGuide.this);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.TaskGuide
 * JD-Core Version:    0.6.0
 */