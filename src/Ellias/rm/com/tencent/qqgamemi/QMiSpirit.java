package com.tencent.qqgamemi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.PlatformUtil;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.animation.ActionListener;
import com.tencent.qqgamemi.animation.AnimationManager;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.GameItem;
import com.tencent.qqgamemi.report.UserAccessStatics;

public class QMiSpirit
{
  public static final double G = 0.3639D;
  public static final double H = 2.7474D;
  public static final double I = -2.7474D;
  public static final double J = -0.3639D;
  public static final double K = 0.3639D;
  public static final double L = 2.7474D;
  public static final double M = -2.7474D;
  public static final double N = -0.3639D;
  private static final String O = QMiSpirit.class.getSimpleName();
  private static final int P = 10;
  private static final int V = 3000;
  private static int W = 0;
  private static final int X = 500;
  static final int a = 250;
  private static final int al = 0;
  private static final int am = 1;
  private static final int an = 12;
  private static final int ao = 13;
  private static final int ap = 14;
  private static final int aq = 100;
  private static final int ar = 2;
  private static final int as = 3;
  private static final int at = 4;
  private static final int au = 5;
  static final float b = 0.5882353F;
  static final float c = 1.0F;
  static final int d = 200;
  static final int e = 200;
  public static final int f = 2;
  public static final int g = -2;
  public static final int h = -1;
  public static final int i = 1;
  public static final int j = 1;
  public static final int k = 2;
  public static final int l = 3;
  public static final int m = 4;
  public static final int n = 5;
  public static final int o = 6;
  public static final int p = 7;
  public static final int q = 8;
  int A = 0;
  boolean B = true;
  ActionListener C = new f(this);
  ActionListener D = new h(this);
  ActionListener E = new i(this);
  t F = new t(this);
  private final QMiViewManager Q;
  private s R;
  private int S = 8;
  private int T = 8;
  private int U;
  private boolean Y = false;
  private boolean Z = false;
  private Handler aA = new j(this, Looper.getMainLooper());
  private SimpleGestureDetector aB = new SimpleGestureDetector(QMiService.a(), new k(this));
  private View.OnTouchListener aC = new l(this);
  private boolean aD;
  private boolean aE = false;
  private QMiWindowManager aF;
  private View.OnTouchListener aG = new m(this);
  private ActionListener aH = new e(this);
  private ValueAnimator aI;
  private boolean aa = false;
  private boolean ab = false;
  private int ac = 0;
  private int ad = 0;
  private int ae = 0;
  private int af = 0;
  private Context ag;
  private View ah;
  private WindowManager ai;
  private VelocityTracker aj;
  private ImageView ak;
  private boolean av = true;
  private ImageView aw;
  private ActionListener ax = new d(this);
  private AnimatorSet ay;
  private AnimatorSet az;
  public boolean r = false;
  public boolean s = false;
  boolean t = false;
  int u = 0;
  int v = 0;
  final int w = 5;
  int[] x = new int[5];
  int[] y = new int[5];
  int z = 0;

  public QMiSpirit(QMiViewManager paramQMiViewManager, Context paramContext, QMiWindowManager paramQMiWindowManager)
  {
    this.Q = paramQMiViewManager;
    this.aF = paramQMiWindowManager;
    this.ag = paramContext;
    if (this.ai == null)
      this.ai = ((WindowManager)paramContext.getSystemService("window"));
    this.R = new s(this, paramContext);
    try
    {
      this.S = (int)(1.5D * ViewConfiguration.get(paramContext).getScaledTouchSlop());
      this.T = ViewConfiguration.get(paramContext).getScaledTouchSlop();
      this.U = ViewConfiguration.get(paramContext).getScaledMaximumFlingVelocity();
      c();
      if (DebugUtil.a())
        W = 9000;
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public static int a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i1 = paramInt3 - paramInt1;
    int i2 = -paramInt4 - -paramInt2;
    if (i1 == 0)
    {
      if (i2 > 0)
        return 3;
      return 4;
    }
    double d1 = 1.0D * i2 / i1;
    TLog.c("Direction", "tan:" + d1 + " dx:" + i1 + "  dy:" + i2);
    if (((d1 >= 0.3639D) && (d1 <= 2.7474D)) || ((d1 >= 0.3639D) && (d1 <= 2.7474D)))
    {
      if (i2 > 0)
        return 7;
      return 6;
    }
    if ((d1 >= 2.7474D) || (d1 <= -2.7474D) || (d1 >= 2.7474D) || (d1 <= -2.7474D))
    {
      if (i2 > 0)
        return 3;
      return 4;
    }
    if (((d1 >= -2.7474D) && (d1 <= -0.3639D)) || ((d1 >= -2.7474D) && (d1 <= -0.3639D)))
    {
      if (i2 > 0)
        return 5;
      return 8;
    }
    if (((d1 >= -0.3639D) && (d1 <= 0.3639D)) || ((d1 <= 0.3639D) && (d1 >= -0.3639D)))
    {
      if (i1 < 0)
        return 2;
      return 1;
    }
    return 0;
  }

  private void a(GameItem paramGameItem)
  {
    this.aA.removeMessages(14);
    Message localMessage = new Message();
    localMessage.what = 14;
    localMessage.obj = paramGameItem;
    this.aA.sendMessageDelayed(localMessage, 500L);
  }

  private boolean b(MotionEvent paramMotionEvent)
  {
    if (this.Y)
    {
      int i4 = (int)paramMotionEvent.getRawX() - this.ac;
      TLog.e("OnTouch", "startTrack Left dx=" + i4);
      if (i4 < -this.T)
      {
        TLog.e("OnTouch", "start hide x float view");
        return true;
      }
    }
    if (this.Z)
    {
      int i3 = (int)paramMotionEvent.getRawX() - this.ad;
      TLog.e("OnTouch", "startTrack Right dx=" + i3);
      if (i3 > this.T)
      {
        TLog.e("OnTouch", "start hide x float view");
        return true;
      }
    }
    if (this.aa)
    {
      int i2 = (int)paramMotionEvent.getRawY() - this.ae;
      TLog.e("OnTouch", "startTrack top dy=" + i2);
      if (i2 < -this.T)
      {
        TLog.e("OnTouch", "start hide y float view");
        return true;
      }
    }
    if (this.ab)
    {
      int i1 = (int)paramMotionEvent.getRawY() - this.af;
      TLog.e("OnTouch", "startTrack bottom dy=" + i1);
      if (i1 > this.T)
      {
        TLog.e("OnTouch", "start hide y float view");
        return true;
      }
    }
    return false;
  }

  private void c(int paramInt)
  {
    q();
    if (this.t)
    {
      Message localMessage = this.aA.obtainMessage();
      localMessage.what = 13;
      localMessage.arg1 = paramInt;
      this.aA.sendMessageDelayed(localMessage, W);
    }
  }

  private boolean c(MotionEvent paramMotionEvent)
  {
    if (this.Q.l());
    do
    {
      int i1;
      int i2;
      do
      {
        do
        {
          return false;
          switch (paramMotionEvent.getAction())
          {
          default:
            return false;
          case 0:
            a();
            d(paramMotionEvent);
            return false;
          case 2:
          case 1:
          case 3:
          }
        }
        while ((this.Q.f) || (this.aD) || (this.aE));
        i1 = (int)paramMotionEvent.getRawX() - this.u;
        i2 = (int)paramMotionEvent.getRawY() - this.v;
      }
      while ((Math.abs(i1) < this.T) && (Math.abs(i2) < this.T));
      if ((this.Y) && ((int)paramMotionEvent.getRawX() - this.ac < -this.T))
      {
        TLog.e("OnTouch", "start hide x float view");
        this.r = true;
        this.s = true;
        this.B = false;
        this.Q.g.b(this.Q, 1);
        UserAccessStatics.getInstance(this.Q.c).addQMiAction(204, System.currentTimeMillis(), QMiCommon.a(this.ag), null);
        return true;
      }
      if ((this.Z) && ((int)paramMotionEvent.getRawX() - this.ad > this.T))
      {
        TLog.e("OnTouch", "start hide x float view");
        this.r = true;
        this.s = false;
        this.B = false;
        this.Q.g.b(this.Q, -1);
        UserAccessStatics.getInstance(this.Q.c).addQMiAction(204, System.currentTimeMillis(), QMiCommon.a(this.ag), null);
        return true;
      }
      if ((!this.aa) || ((int)paramMotionEvent.getRawY() - this.ae >= -this.T))
        continue;
      TLog.e("OnTouch", "start hide y float view");
      this.r = false;
      this.s = true;
      this.B = false;
      this.Q.g.a(this.Q, -2);
      UserAccessStatics.getInstance(this.Q.c).addQMiAction(204, System.currentTimeMillis(), QMiCommon.a(this.ag), null);
      return true;
    }
    while ((!this.ab) || ((int)paramMotionEvent.getRawY() - this.af <= this.T));
    TLog.e("OnTouch", "start hide y float view");
    this.r = false;
    this.s = false;
    this.B = false;
    this.Q.g.a(this.Q, 2);
    UserAccessStatics.getInstance(this.Q.c).addQMiAction(204, System.currentTimeMillis(), QMiCommon.a(this.ag), null);
    return true;
    this.Y = false;
    this.Z = false;
    this.aa = false;
    this.ab = false;
    return false;
  }

  private void d(MotionEvent paramMotionEvent)
  {
    if (this.Q.f);
    do
    {
      return;
      if (this.Q.b())
      {
        TLog.c("OnTouch", "startTrack border left");
        this.Y = true;
        this.ac = (int)paramMotionEvent.getRawX();
      }
      if (this.Q.c())
      {
        TLog.c("OnTouch", "startTrack border right");
        this.Z = true;
        this.ad = (int)paramMotionEvent.getRawX();
      }
      if (!this.Q.d())
        continue;
      TLog.c("OnTouch", "startTrack border top");
      this.aa = true;
      this.ae = (int)paramMotionEvent.getRawY();
    }
    while (!this.Q.e());
    TLog.c("OnTouch", "startTrack border bottom");
    this.ab = true;
    this.af = (int)paramMotionEvent.getRawY();
  }

  private void e(MotionEvent paramMotionEvent)
  {
    if (this.aj == null)
      this.aj = VelocityTracker.obtain();
    this.aj.addMovement(paramMotionEvent);
  }

  private void f(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    case 2:
    default:
    case 0:
    case 1:
    case 3:
    }
    do
    {
      return;
      k();
      return;
    }
    while (this.Q.l());
    i();
  }

  private void p()
  {
    if (this.aj != null)
    {
      this.aj.clear();
      this.aj.recycle();
      this.aj = null;
    }
  }

  private void q()
  {
    this.aA.removeMessages(13);
  }

  private void r()
  {
    this.aA.removeMessages(12);
  }

  public void a()
  {
    if (this.Q.f)
    {
      this.ak.setTag(ResourceUtil.f("qmi_tag_show_source"), Integer.valueOf(0));
      if (this.aD)
        UserAccessStatics.getInstance(this.Q.c).addQMiAction(205, System.currentTimeMillis(), QMiCommon.a(this.ag), null);
      this.Q.g.a(this.Q, true);
      TLog.c("Benson", "[QMiSprite] handleGestureHideOrShow cancel hello check on touch down");
      q();
      this.t = false;
    }
  }

  public void a(int paramInt)
  {
    QMiWindowManager.a(paramInt);
  }

  protected void a(MotionEvent paramMotionEvent)
  {
    boolean bool1 = true;
    if ((this.Q.f) || (this.Q.l()));
    VelocityTracker localVelocityTracker;
    do
    {
      int i1;
      int i2;
      do
      {
        do
        {
          return;
          e(paramMotionEvent);
          localVelocityTracker = this.aj;
          switch (paramMotionEvent.getAction())
          {
          default:
            return;
          case 0:
            if (b())
            {
              TLog.c("Benson", "[QMiSprite] handleMoveAndClicked cancel hello check on touch down");
              q();
              this.t = false;
            }
            this.u = (int)paramMotionEvent.getRawX();
            this.v = (int)paramMotionEvent.getRawY();
            this.z = QMiWindowManager.e().x;
            this.A = QMiWindowManager.e().y;
            this.aA.removeMessages(3);
            if (this.aI != null)
              this.aI.cancel();
            AnimationManager.i.a();
            this.ak.clearAnimation();
            this.aE = false;
            return;
          case 2:
            i1 = (int)paramMotionEvent.getRawX() - this.u;
            i2 = (int)paramMotionEvent.getRawY() - this.v;
          case 1:
          case 3:
          }
        }
        while ((Math.abs(i1) < this.S) && (Math.abs(i2) < this.S));
        this.B = false;
      }
      while ((this.Q.l()) || (this.aD) || (b(paramMotionEvent)));
      if (DebugUtil.a())
        LogUtil.d("OnTouch", " going to move. dx=" + i1 + ",dy=" + i2);
      boolean bool2 = QMiWindowManager.b(this, i1 + this.z, i2 + this.A);
      if ((this.aE) || (bool2));
      while (true)
      {
        this.aE = bool1;
        int i3 = a(this.x[4], this.y[4], this.x[0], this.y[0]);
        AnimationManager.i.b(i3, this.ak);
        return;
        bool1 = false;
      }
      LogUtil.d("OnTouch", " isTurnBall:" + this.aD + " canBeClicked:" + this.B + " isBorder():" + b() + " this.qMiViewManager.isInflate():" + this.Q.l());
    }
    while (this.aD);
    if (this.B)
    {
      if (b())
      {
        this.Q.g();
        return;
      }
      this.aA.sendEmptyMessage(2);
      this.B = bool1;
      return;
    }
    if (!this.Q.l())
    {
      localVelocityTracker.computeCurrentVelocity(200, this.U);
      this.R.a(localVelocityTracker);
    }
    this.B = bool1;
  }

  public void a(View.OnTouchListener paramOnTouchListener)
  {
    this.ah.setOnTouchListener(paramOnTouchListener);
  }

  @SuppressLint({"NewApi"})
  public void a(WindowManager.LayoutParams paramLayoutParams)
  {
    this.ak.clearAnimation();
    if (PlatformUtil.version() >= 12)
      this.ak.animate().cancel();
    try
    {
      this.ai.updateViewLayout(this.ah, paramLayoutParams);
      TLog.c(QMiViewManager.a, "update floatview at(" + paramLayoutParams.x + "," + paramLayoutParams.y + ")");
      for (int i1 = -1 + this.x.length; i1 > 0; i1--)
      {
        this.x[i1] = this.x[(i1 - 1)];
        this.y[i1] = this.y[(i1 - 1)];
      }
    }
    catch (Exception localException)
    {
      while (true)
        TLog.c(QMiViewManager.a, "view not attach", localException);
      this.x[0] = paramLayoutParams.x;
      this.y[0] = paramLayoutParams.y;
    }
  }

  void a(QMiViewManager paramQMiViewManager)
  {
    TLog.c(QMiViewManager.a, "moveFloatViewToInflatePoint");
    paramQMiViewManager.j = false;
    Point localPoint1 = new Point(QMiWindowManager.e().x, QMiWindowManager.e().y);
    Point localPoint2 = paramQMiViewManager.i;
    ValueAnimator localValueAnimator = ValueAnimator.ofObject(new r(this, this.ah), new Object[] { localPoint1, localPoint2 });
    localValueAnimator.setDuration(250L);
    localValueAnimator.start();
  }

  void a(QMiViewManager paramQMiViewManager, int paramInt)
  {
    if (!paramQMiViewManager.f)
    {
      this.ak.setTag(ResourceUtil.f("qmi_tag_temp"), Integer.valueOf(paramInt));
      AnimationManager.i.b(this.ak, this.E);
      this.aD = true;
    }
  }

  void a(QMiViewManager paramQMiViewManager, boolean paramBoolean)
  {
    ImageView localImageView;
    AnimatorSet localAnimatorSet;
    String str;
    int i1;
    label61: float f1;
    if (this.aD)
    {
      localImageView = this.ak;
      localImageView.setImageResource(ResourceUtil.c("qmi_turn_ball"));
      localAnimatorSet = new AnimatorSet();
      if (!this.r)
        break label190;
      str = "translationX";
      if (!this.s)
        break label198;
      i1 = 3 * (-localImageView.getWidth() / 4);
      if (i1 <= 0)
        break label211;
      f1 = 360.0F;
    }
    while (true)
    {
      ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(localImageView, "rotation", new float[] { f1, 0.0F });
      float[] arrayOfFloat = new float[2];
      arrayOfFloat[0] = i1;
      arrayOfFloat[1] = 0.0F;
      ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localImageView, str, arrayOfFloat);
      localObjectAnimator1.setDuration(100L);
      localObjectAnimator2.setDuration(100L);
      localAnimatorSet.playTogether(new Animator[] { localObjectAnimator2, localObjectAnimator1 });
      localAnimatorSet.start();
      localAnimatorSet.addListener(new q(this, paramBoolean));
      TLog.c(QMiViewManager.a, "makeFloatViewShow");
      this.aD = false;
      return;
      label190: str = "translationY";
      break;
      label198: i1 = 3 * (localImageView.getWidth() / 4);
      break label61;
      label211: f1 = -360.0F;
    }
  }

  public void a(boolean paramBoolean)
  {
    this.av = paramBoolean;
  }

  void b(int paramInt)
  {
    ImageView localImageView = this.ak;
    String str;
    int i1;
    int i2;
    if (this.r)
    {
      str = "translationX";
      TLog.c("Benson", "[QMiSprite] showHelloAnim orient:" + str + ", dir:" + paramInt);
      switch (paramInt)
      {
      default:
        i1 = 3 * (localImageView.getHeight() / 4);
        i2 = -5 + localImageView.getHeight() / 3;
        localImageView.setImageResource(ResourceUtil.c("qmi_super_up"));
      case 2:
      case 1:
      case 3:
      }
    }
    while (true)
    {
      this.az = new AnimatorSet();
      float[] arrayOfFloat1 = new float[2];
      arrayOfFloat1[0] = i1;
      arrayOfFloat1[1] = i2;
      ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(localImageView, str, arrayOfFloat1);
      localObjectAnimator1.setDuration(350L);
      float[] arrayOfFloat2 = new float[2];
      arrayOfFloat2[0] = i2;
      arrayOfFloat2[1] = i1;
      ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localImageView, str, arrayOfFloat2);
      localObjectAnimator2.setDuration(250L);
      localObjectAnimator2.setStartDelay(1000L);
      this.az.playSequentially(new Animator[] { localObjectAnimator1, localObjectAnimator2 });
      this.az.addListener(new p(this, localImageView));
      this.az.start();
      return;
      str = "translationY";
      break;
      i1 = 3 * (-localImageView.getWidth() / 4);
      i2 = -localImageView.getWidth() / 3;
      localImageView.setImageResource(ResourceUtil.c("qmi_super_right"));
      continue;
      i1 = 3 * (localImageView.getWidth() / 4);
      i2 = localImageView.getWidth() / 3;
      localImageView.setImageResource(ResourceUtil.c("qmi_super_left"));
      continue;
      i1 = 3 * (-localImageView.getHeight() / 4);
      i2 = -5 + -localImageView.getHeight() / 3;
      localImageView.setImageResource(ResourceUtil.c("qmi_super_down"));
    }
  }

  void b(QMiViewManager paramQMiViewManager)
  {
    TLog.c(QMiViewManager.a, "moveFloatViewToWidthBorder");
    paramQMiViewManager.j = true;
    paramQMiViewManager.i = new Point(QMiWindowManager.e().x, QMiWindowManager.e().y);
    Point localPoint = new Point(paramQMiViewManager.a(QMiWindowManager.e().x), QMiWindowManager.e().y);
    r localr = new r(this, this.ah);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramQMiViewManager.i;
    arrayOfObject[1] = localPoint;
    this.aI = ValueAnimator.ofObject(localr, arrayOfObject);
    this.aI.addListener(new o(this, paramQMiViewManager));
    this.aI.setDuration(250L);
    this.aI.start();
  }

  void b(QMiViewManager paramQMiViewManager, int paramInt)
  {
    if (!paramQMiViewManager.f)
    {
      this.ak.setTag(ResourceUtil.f("qmi_tag_temp"), Integer.valueOf(paramInt));
      AnimationManager.i.b(this.ak, this.D);
      this.aD = true;
    }
  }

  public boolean b()
  {
    return (this.Q.e()) || (this.Q.b()) || (this.Q.c()) || (this.Q.d());
  }

  public void c()
  {
    this.ah = View.inflate(this.ag, ResourceUtil.a("qmi_float_layout"), null);
    this.ak = ((ImageView)this.ah.findViewById(ResourceUtil.f("float_image")));
    a(this.aC);
    this.aw = ((ImageView)View.inflate(this.ag, ResourceUtil.a("qmi_half_spirit"), null));
    this.aw.setOnTouchListener(this.aG);
    this.aF.a(this.ah, this, this.aw);
  }

  void c(QMiViewManager paramQMiViewManager)
  {
    if (!paramQMiViewManager.e)
    {
      ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.ak, "alpha", new float[] { 1.0F, 0.5882353F });
      localObjectAnimator.setDuration(200L);
      localObjectAnimator.start();
    }
  }

  public void d()
  {
    QMiWindowManager.h();
  }

  public View e()
  {
    return this.ah;
  }

  public ImageView f()
  {
    return this.ak;
  }

  public void g()
  {
    TLog.c(QMiViewManager.a, "moveFloatViewToBorder");
    Point localPoint1 = new Point(QMiWindowManager.e().x, QMiWindowManager.e().y);
    Point localPoint2 = this.Q.f();
    if (localPoint1.equals(localPoint2))
      return;
    int i1 = a(localPoint1.x, localPoint1.y, localPoint2.x, localPoint2.y);
    AnimationManager.i.a(i1, this.ak);
    ValueAnimator localValueAnimator = ValueAnimator.ofObject(new r(this, e()), new Object[] { localPoint1, localPoint2 });
    localValueAnimator.setDuration(250L);
    localValueAnimator.start();
    localValueAnimator.addListener(new n(this, i1));
  }

  void h()
  {
    if (this.Q.e)
    {
      ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.ak, "alpha", new float[] { 0.5882353F, 1.0F });
      localObjectAnimator.setDuration(200L);
      localObjectAnimator.start();
    }
    a(this.Q, false);
  }

  void i()
  {
    l();
  }

  void j()
  {
    r();
    q();
  }

  void k()
  {
    q();
    this.t = false;
  }

  public void l()
  {
    this.aA.removeMessages(12);
    this.aA.sendEmptyMessageDelayed(12, 3000L);
  }

  public boolean m()
  {
    return this.av;
  }

  public void n()
  {
    for (int i1 = -1 + this.x.length; i1 >= 0; i1--)
    {
      this.x[i1] = QMiWindowManager.e().x;
      this.y[i1] = QMiWindowManager.e().y;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.QMiSpirit
 * JD-Core Version:    0.6.0
 */