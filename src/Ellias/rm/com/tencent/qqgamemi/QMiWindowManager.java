package com.tencent.qqgamemi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.tencent.component.event.Event.EventRank;
import com.tencent.component.event.EventCenter;
import com.tencent.component.event.EventSource;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.animation.GoAndReturnInterpolate;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.DataModel;
import com.tencent.qqgamemi.data.GameItem;
import com.tencent.qqgamemi.data.Point;
import com.tencent.qqgamemi.plugin.QMiPluginManager;
import com.tencent.qqgamemi.report.UserAccessStatics;

public class QMiWindowManager
{
  public static final int a = 100;
  private static final String b = QMiWindowManager.class.getSimpleName();
  private static Context c;
  private static WindowManager d;
  private static DisplayMetrics e = new DisplayMetrics();
  private static int f = 0;
  private static int g = 0;
  private static boolean h = true;
  private static View i = null;
  private static WindowManager.LayoutParams j;
  private static boolean k = false;
  private static boolean l = false;
  private static View m = null;
  private static WindowManager.LayoutParams n;
  private static boolean o = false;
  private static View p = null;
  private static WindowManager.LayoutParams q;
  private static boolean r = false;
  private static WindowManager.LayoutParams s;
  private static View t = null;
  private static QMiSpirit u;

  public QMiWindowManager(Context paramContext)
  {
    c = paramContext;
    if (d == null)
      d = (WindowManager)paramContext.getSystemService("window");
    d.getDefaultDisplay().getMetrics(e);
    t();
    u();
  }

  private static void A()
  {
    ImageView localImageView = (ImageView)i.findViewById(ResourceUtil.f("float_image"));
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(localImageView, "alpha", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setDuration(1000L);
    localObjectAnimator.start();
    localImageView.setTag(ResourceUtil.f("qmi_tag_show_source"), Integer.valueOf(1));
    u.h();
  }

  private static void B()
  {
    TLog.c("halfHide", "removeHideFloatView isFloatViewAdded:" + k + ",isHalfSpiritShow:" + o);
    if (o);
    try
    {
      d.removeView(m);
      o = false;
      return;
    }
    catch (Exception localException)
    {
      TLog.c(b, "wm removeHideFloatView", localException);
    }
  }

  private static void C()
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat((ImageView)i.findViewById(ResourceUtil.f("float_image")), "alpha", new float[] { 1.0F, 0.0F });
    localObjectAnimator.setDuration(500L);
    localObjectAnimator.addListener(new ac());
    localObjectAnimator.start();
  }

  private static void D()
  {
    SharedPreferences localSharedPreferences = c.getSharedPreferences("QMiPoint", 0);
    j.x = localSharedPreferences.getInt("x", 0);
    int i1 = localSharedPreferences.getInt("y", -1);
    if (i1 == -1)
      i1 = a().getHeight() / 4;
    j.y = i1;
    TLog.c(b, "restoreFloatPosition x:" + j.x + " y:" + j.y);
  }

  private static void E()
  {
    ViewGroup localViewGroup1 = (ViewGroup)p.findViewById(ResourceUtil.f("inflate_layout0_shadow"));
    View localView1 = localViewGroup1.getChildAt(0);
    localView1.measure(View.MeasureSpec.makeMeasureSpec(e.widthPixels, -2147483648), View.MeasureSpec.makeMeasureSpec(e.heightPixels, -2147483648));
    int i1 = localView1.getMeasuredWidth();
    int i2 = localView1.getMeasuredHeight();
    TLog.c(b, "showInflateViewPushIn item width=" + i1 + " height=" + i2);
    int i3 = 0;
    if (i3 < localViewGroup1.getChildCount())
    {
      View localView3 = localViewGroup1.getChildAt(i3);
      float f2;
      if (a(j))
        f2 = i1 * -i3;
      while (true)
      {
        AnimatorSet localAnimatorSet2 = new AnimatorSet();
        ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(localView3, "translationX", new float[] { f2, 0.0F });
        localObjectAnimator3.setDuration(100L);
        localObjectAnimator3.setInterpolator(new LinearInterpolator());
        ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(localView3, "alpha", new float[] { 0.0F, 1.0F });
        localObjectAnimator4.setDuration(100L);
        localAnimatorSet2.addListener(new af(localView3));
        localObjectAnimator4.setInterpolator(new GoAndReturnInterpolate(new AccelerateInterpolator(), new DecelerateInterpolator()));
        localAnimatorSet2.playTogether(new Animator[] { localObjectAnimator3, localObjectAnimator4 });
        localAnimatorSet2.start();
        i3++;
        break;
        f2 = i1 * (-1 + localViewGroup1.getChildCount() - i3);
      }
    }
    ViewGroup localViewGroup2 = (ViewGroup)p.findViewById(ResourceUtil.f("inflate_layout1_shadow"));
    int i4 = 0;
    if (i4 < localViewGroup2.getChildCount())
    {
      View localView2 = localViewGroup2.getChildAt(i4);
      float f1;
      if (a(j))
        f1 = i1 * -i4;
      while (true)
      {
        AnimatorSet localAnimatorSet1 = new AnimatorSet();
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(localView2, "translationX", new float[] { f1, 0.0F });
        localObjectAnimator1.setDuration(100L);
        localObjectAnimator1.setInterpolator(new LinearInterpolator());
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localView2, "alpha", new float[] { 0.0F, 1.0F });
        localObjectAnimator2.setDuration(100L);
        localObjectAnimator2.setInterpolator(new GoAndReturnInterpolate(new AccelerateInterpolator(), new DecelerateInterpolator()));
        af localaf = new af(localView2);
        localAnimatorSet1.playTogether(new Animator[] { localObjectAnimator1, localObjectAnimator2 });
        localAnimatorSet1.addListener(localaf);
        localAnimatorSet1.start();
        i4++;
        break;
        f1 = i1 * (-1 + localViewGroup1.getChildCount() - i4);
      }
    }
  }

  private static void F()
  {
    LinearLayout localLinearLayout1 = (LinearLayout)p.findViewById(ResourceUtil.f("inflate_layout0"));
    ViewGroup localViewGroup1 = (ViewGroup)localLinearLayout1.getChildAt(0);
    localViewGroup1.measure(View.MeasureSpec.makeMeasureSpec(e.widthPixels, -2147483648), View.MeasureSpec.makeMeasureSpec(e.heightPixels, -2147483648));
    int i1 = localViewGroup1.getMeasuredWidth();
    int i2 = localViewGroup1.getMeasuredHeight();
    TLog.c(b, "showInflateViewPushIn item width=" + i1 + " height=" + i2);
    int i3 = 0;
    if (i3 < localLinearLayout1.getChildCount())
    {
      ViewGroup localViewGroup3 = (ViewGroup)localLinearLayout1.getChildAt(i3);
      float f2;
      if (a(j))
        f2 = i1 * -i3;
      while (true)
      {
        AnimatorSet localAnimatorSet2 = new AnimatorSet();
        ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(localViewGroup3, "translationX", new float[] { f2, 0.0F });
        localObjectAnimator3.setDuration(100L);
        localObjectAnimator3.setInterpolator(AnimationUtils.loadInterpolator(c, 17432584));
        ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(localViewGroup3, "alpha", new float[] { 0.0F, 1.0F });
        localObjectAnimator4.setDuration(100L);
        localObjectAnimator4.setInterpolator(new AccelerateInterpolator());
        localAnimatorSet2.playTogether(new Animator[] { localObjectAnimator3, localObjectAnimator4 });
        localAnimatorSet2.start();
        i3++;
        break;
        f2 = i1 * (-1 + localLinearLayout1.getChildCount() - i3);
      }
    }
    LinearLayout localLinearLayout2 = (LinearLayout)p.findViewById(ResourceUtil.f("inflate_layout1"));
    int i4 = 0;
    if (i4 < localLinearLayout2.getChildCount())
    {
      ViewGroup localViewGroup2 = (ViewGroup)localLinearLayout2.getChildAt(i4);
      float f1;
      if (a(j))
        f1 = i1 * -i4;
      while (true)
      {
        AnimatorSet localAnimatorSet1 = new AnimatorSet();
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(localViewGroup2, "translationX", new float[] { f1, 0.0F });
        localObjectAnimator1.setDuration(100L);
        localObjectAnimator1.setInterpolator(AnimationUtils.loadInterpolator(c, 17432584));
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localViewGroup2, "alpha", new float[] { 0.0F, 1.0F });
        localObjectAnimator2.setDuration(100L);
        localObjectAnimator2.setInterpolator(new AccelerateInterpolator());
        localAnimatorSet1.playTogether(new Animator[] { localObjectAnimator1, localObjectAnimator2 });
        localAnimatorSet1.start();
        i4++;
        break;
        f1 = i1 * (-1 + localLinearLayout2.getChildCount() - i4);
      }
    }
  }

  private static void G()
  {
    if (r == true);
    try
    {
      d.removeView(p);
      d.removeView(t);
      r = false;
      return;
    }
    catch (Exception localException)
    {
      TLog.c(b, "wm removeInflateLayout", localException);
    }
  }

  private static void H()
  {
    ViewGroup localViewGroup1 = (ViewGroup)p.findViewById(ResourceUtil.f("inflate_layout0"));
    ViewGroup localViewGroup2 = (ViewGroup)localViewGroup1.getChildAt(0);
    int i1 = 100;
    if (localViewGroup2 != null)
    {
      localViewGroup2.measure(View.MeasureSpec.makeMeasureSpec(e.widthPixels, -2147483648), View.MeasureSpec.makeMeasureSpec(e.heightPixels, -2147483648));
      i1 = localViewGroup2.getMeasuredWidth();
      int i5 = localViewGroup2.getMeasuredHeight();
      TLog.c(b, "hideInflateViewPushOut item width=" + i1 + " height=" + i5);
    }
    int i2 = i1;
    int i3 = 0;
    if (i3 < localViewGroup1.getChildCount())
    {
      ViewGroup localViewGroup5 = (ViewGroup)localViewGroup1.getChildAt(i3);
      float f2;
      if (a(j))
        f2 = i2 * -i3;
      while (true)
      {
        ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(localViewGroup5, "translationX", new float[] { 0.0F, f2 });
        localObjectAnimator3.setDuration(100L);
        if (i3 == 1)
          localObjectAnimator3.addListener(new ad());
        AnimatorSet localAnimatorSet2 = new AnimatorSet();
        ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(localViewGroup5, "alpha", new float[] { 1.0F, 0.0F });
        localObjectAnimator4.setDuration(100L);
        localObjectAnimator4.setInterpolator(new AccelerateInterpolator());
        localAnimatorSet2.playTogether(new Animator[] { localObjectAnimator3, localObjectAnimator4 });
        localAnimatorSet2.start();
        i3++;
        break;
        f2 = i2 * (-1 + localViewGroup1.getChildCount() - i3);
      }
    }
    ViewGroup localViewGroup3 = (ViewGroup)p.findViewById(ResourceUtil.f("inflate_layout1"));
    int i4 = 0;
    if (i4 < localViewGroup3.getChildCount())
    {
      ViewGroup localViewGroup4 = (ViewGroup)localViewGroup3.getChildAt(i4);
      float f1;
      if (a(j))
        f1 = i2 * -i4;
      while (true)
      {
        AnimatorSet localAnimatorSet1 = new AnimatorSet();
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(localViewGroup4, "translationX", new float[] { 0.0F, f1 });
        localObjectAnimator1.setDuration(100L);
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localViewGroup4, "alpha", new float[] { 1.0F, 0.0F });
        localObjectAnimator2.setDuration(100L);
        localObjectAnimator2.setInterpolator(new AccelerateInterpolator());
        localAnimatorSet1.playTogether(new Animator[] { localObjectAnimator1, localObjectAnimator2 });
        localAnimatorSet1.start();
        i4++;
        break;
        f1 = i2 * (-1 + localViewGroup1.getChildCount() - i4);
      }
    }
  }

  private static void I()
  {
    ViewGroup localViewGroup1 = (ViewGroup)p.findViewById(ResourceUtil.f("inflate_layout0_shadow"));
    View localView1 = localViewGroup1.getChildAt(0);
    localView1.measure(View.MeasureSpec.makeMeasureSpec(e.widthPixels, -2147483648), View.MeasureSpec.makeMeasureSpec(e.heightPixels, -2147483648));
    int i1 = localView1.getMeasuredWidth();
    int i2 = localView1.getMeasuredHeight();
    TLog.c(b, "hideInflateViewPushOut item width=" + i1 + " height=" + i2);
    int i3 = 1;
    if (i3 < localViewGroup1.getChildCount())
    {
      View localView3 = localViewGroup1.getChildAt(i3);
      localView3.setVisibility(0);
      float f2;
      if (a(j))
        f2 = i1 * -(i3 - 1);
      while (true)
      {
        AnimatorSet localAnimatorSet2 = new AnimatorSet();
        ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(localView3, "translationX", new float[] { 0.0F, f2 });
        localObjectAnimator3.setDuration(100L);
        if (i3 == 0)
          localAnimatorSet2.addListener(new ae());
        ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(localView3, "alpha", new float[] { 0.0F, 0.6F });
        localObjectAnimator4.setDuration(100L);
        localObjectAnimator4.setInterpolator(new GoAndReturnInterpolate(new AccelerateInterpolator(), new DecelerateInterpolator()));
        localAnimatorSet2.playTogether(new Animator[] { localObjectAnimator3, localObjectAnimator4 });
        localAnimatorSet2.start();
        i3++;
        break;
        f2 = i1 * (-1 + localViewGroup1.getChildCount() - (i3 - 1));
      }
    }
    ViewGroup localViewGroup2 = (ViewGroup)p.findViewById(ResourceUtil.f("inflate_layout1_shadow"));
    int i4 = 1;
    if (i4 < localViewGroup2.getChildCount())
    {
      View localView2 = localViewGroup2.getChildAt(i4);
      localView2.setVisibility(0);
      float f1;
      if (a(j))
        f1 = i1 * -(i4 - 1);
      while (true)
      {
        AnimatorSet localAnimatorSet1 = new AnimatorSet();
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(localView2, "translationX", new float[] { 0.0F, f1 });
        localObjectAnimator1.setDuration(100L);
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localView2, "alpha", new float[] { 0.0F, 0.6F });
        localObjectAnimator2.setDuration(100L);
        localObjectAnimator2.setInterpolator(new GoAndReturnInterpolate(new AccelerateInterpolator(), new DecelerateInterpolator()));
        localAnimatorSet1.playTogether(new Animator[] { localObjectAnimator1, localObjectAnimator2 });
        localAnimatorSet1.start();
        i4++;
        break;
        f1 = i1 * (-1 + localViewGroup1.getChildCount() - (i4 - 1));
      }
    }
  }

  public static Display a()
  {
    return d.getDefaultDisplay();
  }

  public static void a(int paramInt)
  {
    TLog.c("halfHide", "hideHideFloatView isFloatViewAdded:" + k + ",isHalfSpiritShow:" + o);
    if ((k) && (!o))
      b(paramInt);
    try
    {
      d.addView(m, n);
      o = true;
      d.removeView(i);
      EventCenter.getInstance().notify(new EventSource("QmiUI"), 3, Event.EventRank.NORMAL, new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        TLog.c(b, "wm showHideFloatView", localException);
    }
  }

  private static void a(View paramView, WindowManager.LayoutParams paramLayoutParams)
  {
    paramView.measure(View.MeasureSpec.makeMeasureSpec(e.widthPixels, -2147483648), View.MeasureSpec.makeMeasureSpec(e.heightPixels, -2147483648));
    TLog.c(b, "setLayoutSize width=" + paramView.getMeasuredWidth() + " height=" + paramView.getMeasuredHeight());
    paramLayoutParams.width = paramView.getMeasuredWidth();
    paramLayoutParams.height = paramView.getMeasuredHeight();
  }

  private static boolean a(WindowManager.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams.x <= (a().getWidth() - e().width) / 2;
  }

  public static boolean a(QMiSpirit paramQMiSpirit, int paramInt1, int paramInt2)
  {
    return b(paramQMiSpirit, j.x - paramInt1, j.y - paramInt2);
  }

  public static boolean a(GameItem paramGameItem)
  {
    if (!k)
    {
      TLog.b(b, "showQMiView");
      if (!l)
      {
        a(i, j);
        l = true;
      }
      if ((paramGameItem != null) && (paramGameItem.isConfigPoint))
        if ((paramGameItem.movePoint != null) && (paramGameItem.configPoint != null))
        {
          if (paramGameItem.movePoint.a != -1)
            break label169;
          Display localDisplay = a();
          j.x = (localDisplay.getWidth() * paramGameItem.configPoint.a / 100);
          j.y = (localDisplay.getHeight() * paramGameItem.configPoint.b / 100);
          paramGameItem.movePoint.a = j.x;
          paramGameItem.movePoint.b = j.y;
          b(paramGameItem);
        }
      try
      {
        while (true)
        {
          d.addView(i, j);
          k = true;
          A();
          return true;
          label169: j.x = paramGameItem.movePoint.a;
          j.y = paramGameItem.movePoint.b;
          continue;
          if (h)
          {
            h = false;
            D();
          }
          b(null);
        }
      }
      catch (Exception localException)
      {
        while (true)
          TLog.c(b, "wm showFloatView", localException);
      }
    }
    return false;
  }

  public static DisplayMetrics b()
  {
    return e;
  }

  private static void b(int paramInt)
  {
    if (j != null)
    {
      if (paramInt != 2)
        break label76;
      n.x = 0;
      n.y = j.y;
      j.width /= 4;
      n.height = j.height;
      m.setPadding(0, 0, 3 * j.width / 4, 0);
    }
    label76: 
    do
    {
      return;
      if (paramInt == 1)
      {
        n.x = d.getDefaultDisplay().getWidth();
        n.y = j.y;
        j.width /= 4;
        n.height = j.height;
        m.setPadding(3 * j.width / 4, 0, 0, 0);
        return;
      }
      if (paramInt != 3)
        continue;
      n.x = j.x;
      n.y = 0;
      n.width = j.width;
      j.height /= 4;
      m.setPadding(0, 0, 0, 3 * j.height / 4);
      return;
    }
    while (paramInt != 4);
    n.x = j.x;
    n.y = d.getDefaultDisplay().getHeight();
    n.width = j.width;
    j.height /= 4;
    m.setPadding(0, 3 * j.height / 4, 0, 0);
  }

  public static void b(GameItem paramGameItem)
  {
    TLog.c(b, "saveFloatPosition x:" + j.x + " y:" + j.y);
    if ((paramGameItem != null) && (paramGameItem.isConfigPoint) && (paramGameItem.equals(QMiServiceLogic.b)))
    {
      DataModel.a(c).a(paramGameItem.packageName, new Point(j.x, j.y));
      return;
    }
    SharedPreferences.Editor localEditor = c.getSharedPreferences("QMiPoint", 2).edit();
    localEditor.putInt("x", j.x);
    localEditor.putInt("y", j.y);
    localEditor.commit();
  }

  public static boolean b(QMiSpirit paramQMiSpirit, int paramInt1, int paramInt2)
  {
    if (paramQMiSpirit == null)
      TLog.c(b, "view null");
    label167: label214: 
    while (true)
    {
      return false;
      if (!k)
      {
        RuntimeException localRuntimeException = new RuntimeException("here");
        localRuntimeException.fillInStackTrace();
        TLog.d(b, "view is not attach", localRuntimeException);
        return false;
      }
      int i1 = j.x;
      int i2 = j.y;
      if (paramInt1 < 0)
      {
        j.x = 0;
        if (paramInt2 >= 0)
          break label167;
        j.y = 0;
      }
      while (true)
      {
        if ((j.x == i1) && (j.y == i2))
          break label214;
        paramQMiSpirit.a(j);
        return true;
        if (paramInt1 > a().getWidth() - j.width)
        {
          j.x = (a().getWidth() - j.width);
          break;
        }
        j.x = paramInt1;
        break;
        if (paramInt2 > a().getHeight() - j.height)
        {
          j.y = (a().getHeight() - j.height);
          continue;
        }
        j.y = paramInt2;
      }
    }
  }

  public static int c()
  {
    return f;
  }

  public static int d()
  {
    return g;
  }

  public static WindowManager.LayoutParams e()
  {
    return j;
  }

  public static WindowManager.LayoutParams f()
  {
    return n;
  }

  public static void g()
  {
    if ((!DebugUtil.a(c)) || (k == true))
    {
      C();
      j();
      QMiPluginManager.a().e();
      EventCenter.getInstance().notify(new EventSource("QmiUI"), 1, Event.EventRank.NORMAL, new Object[0]);
      c.sendBroadcast(new Intent("com.tencent.qqgamemi.hide.action"));
      UserAccessStatics.getInstance(c).addQMiAction(215, System.currentTimeMillis(), "", "");
    }
  }

  public static void h()
  {
    TLog.c("halfHide", "hideHideFloatView isFloatViewAdded:" + k + ",isHalfSpiritShow:" + o);
    if ((k) && (o));
    try
    {
      d.addView(i, j);
      B();
      EventCenter.getInstance().notify(new EventSource("QmiUI"), 4, Event.EventRank.NORMAL, new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        TLog.c(b, "wm hideHideFloatView", localException);
    }
  }

  public static void i()
  {
    TLog.c(b, "showInfalteView:" + r);
    LinearLayout localLinearLayout1;
    View localView1;
    LinearLayout.LayoutParams localLayoutParams;
    LinearLayout localLinearLayout2;
    View localView2;
    if ((k) && (!r))
    {
      TLog.b(b, "showInfalteView");
      localLinearLayout1 = (LinearLayout)p.findViewById(ResourceUtil.f("inflate_layout0"));
      LayoutInflater localLayoutInflater = (LayoutInflater)c.getSystemService("layout_inflater");
      localView1 = localLayoutInflater.inflate(ResourceUtil.a("qmi_inflate_item"), null, false);
      localView1.setVisibility(4);
      int i1 = c.getResources().getDimensionPixelSize(ResourceUtil.j("qmi_menu_item_size"));
      localLayoutParams = new LinearLayout.LayoutParams(i1, i1);
      localLinearLayout2 = (LinearLayout)p.findViewById(ResourceUtil.f("inflate_layout1"));
      localView2 = localLayoutInflater.inflate(ResourceUtil.a("qmi_inflate_item"), null, false);
      localView2.setVisibility(4);
      if (!a(j))
        break label232;
      localLinearLayout1.addView(localView1, 0, localLayoutParams);
      localLinearLayout2.addView(localView2, 0, localLayoutParams);
    }
    while (true)
    {
      a(p, q);
      try
      {
        d.addView(t, w());
        d.addView(p, y());
        r = true;
        F();
        return;
        label232: localLinearLayout1.addView(localView1, localLayoutParams);
        localLinearLayout2.addView(localView2, localLayoutParams);
      }
      catch (Exception localException)
      {
        while (true)
          TLog.c(b, "wm showInfalteView", localException);
      }
    }
  }

  public static void j()
  {
    TLog.c(b, "hideInfalteView isInflateViewAdded=" + r);
    if ((r == true) || (!k))
    {
      TLog.b(b, "hideInfalteView");
      H();
    }
  }

  public static void k()
  {
    TLog.c(b, "updateLayoutConfiguration");
    if (c.getResources().getConfiguration().orientation == 2)
      TLog.c(b, "landscape");
    while (true)
    {
      t();
      return;
      if (c.getResources().getConfiguration().orientation != 1)
        continue;
      TLog.c(b, "portrait");
    }
  }

  private static void t()
  {
  }

  private static void u()
  {
    if (j == null)
      j = v();
    if (n == null)
      n = v();
    if (q == null)
      q = v();
    if (s == null)
      s = v();
  }

  private static WindowManager.LayoutParams v()
  {
    WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
    localLayoutParams.format = 1;
    localLayoutParams.type = 2002;
    localLayoutParams.gravity = 51;
    localLayoutParams.flags = 40;
    localLayoutParams.x = 0;
    localLayoutParams.y = 0;
    localLayoutParams.width = 0;
    localLayoutParams.height = 0;
    return localLayoutParams;
  }

  private static WindowManager.LayoutParams w()
  {
    x();
    return s;
  }

  private static void x()
  {
    if (s != null)
    {
      s.width = d.getDefaultDisplay().getWidth();
      s.height = d.getDefaultDisplay().getHeight();
    }
  }

  private static WindowManager.LayoutParams y()
  {
    z();
    return q;
  }

  private static void z()
  {
    if (j != null)
    {
      if (a(j))
      {
        q.x = j.x;
        q.y = j.y;
      }
    }
    else
      return;
    q.x = (j.x - q.width + j.width);
    q.y = j.y;
  }

  public void a(View paramView)
  {
    p = paramView;
  }

  public void a(View paramView1, QMiSpirit paramQMiSpirit, View paramView2)
  {
    i = paramView1;
    u = paramQMiSpirit;
    m = paramView2;
  }

  public void b(View paramView)
  {
    t = paramView;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.QMiWindowManager
 * JD-Core Version:    0.6.0
 */