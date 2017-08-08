package com.tencent.qqgamemi.animation;

import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.QMiSpirit;
import com.tencent.qqgamemi.QMiWindowManager;

public class AnimationManager
{
  public static final int a = 1;
  public static final int b = 2;
  public static final int c = 3;
  public static final int d = 4;
  public static final int e = 5;
  public static final int f = 6;
  public static final int g = 7;
  public static final int h = 8;
  public static final AnimationManager i = new AnimationManager();
  private ShakeAction A;
  private FrameAction j;
  private FrameAction k;
  private FrameAction l;
  private FrameAction m;
  private FrameAction n;
  private FrameAction o;
  private FrameAction p;
  private FrameAction q;
  private FrameAction r;
  private FrameAction s;
  private FrameAction t;
  private FrameAction u;
  private FrameAction v;
  private FrameAction w;
  private FrameAction x;
  private FrameBackGroundAction y;
  private QmiSpiritAction z = null;

  private void a(QmiSpiritAction paramQmiSpiritAction, ImageView paramImageView, ActionListener paramActionListener)
  {
    paramQmiSpiritAction.b();
    paramQmiSpiritAction.a(paramImageView);
    paramQmiSpiritAction.a(paramActionListener);
    paramQmiSpiritAction.a(null);
  }

  private QmiSpiritAction d(ImageView paramImageView, ActionListener paramActionListener)
  {
    int[] arrayOfInt = new int[5];
    arrayOfInt[0] = ResourceUtil.c("qmi_move_to_left");
    arrayOfInt[1] = ResourceUtil.c("qmi_move_to_left_up");
    arrayOfInt[2] = ResourceUtil.c("qmi_stand2");
    arrayOfInt[3] = ResourceUtil.c("qmi_stand1");
    arrayOfInt[4] = ResourceUtil.c("qmi_stand0");
    if (this.s == null)
      this.s = new FrameAction(arrayOfInt);
    a(this.s, paramImageView, paramActionListener);
    return this.n;
  }

  private QmiSpiritAction e(ImageView paramImageView, ActionListener paramActionListener)
  {
    int[] arrayOfInt = new int[5];
    arrayOfInt[0] = ResourceUtil.c("qmi_move_to_right");
    arrayOfInt[1] = ResourceUtil.c("qmi_move_to_right_up");
    arrayOfInt[2] = ResourceUtil.c("qmi_stand2");
    arrayOfInt[3] = ResourceUtil.c("qmi_stand1");
    arrayOfInt[4] = ResourceUtil.c("qmi_stand0");
    if (this.s == null)
      this.s = new FrameAction(arrayOfInt);
    a(this.s, paramImageView, paramActionListener);
    return this.n;
  }

  private QmiSpiritAction f(ImageView paramImageView, ActionListener paramActionListener)
  {
    int[] arrayOfInt = new int[5];
    arrayOfInt[0] = ResourceUtil.c("qmi_move_to_left_down");
    arrayOfInt[1] = ResourceUtil.c("qmi_move_to_left_up");
    arrayOfInt[2] = ResourceUtil.c("qmi_stand2");
    arrayOfInt[3] = ResourceUtil.c("qmi_stand1");
    arrayOfInt[4] = ResourceUtil.c("qmi_stand0");
    if (this.s == null)
      this.s = new FrameAction(arrayOfInt);
    a(this.s, paramImageView, paramActionListener);
    return this.n;
  }

  private QmiSpiritAction g(ImageView paramImageView, ActionListener paramActionListener)
  {
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = ResourceUtil.c("qmi_super_up");
    arrayOfInt[1] = ResourceUtil.c("qmi_stand2");
    arrayOfInt[2] = ResourceUtil.c("qmi_stand1");
    arrayOfInt[3] = ResourceUtil.c("qmi_stand0");
    if (this.q == null)
      this.q = new FrameAction(arrayOfInt);
    a(this.q, paramImageView, paramActionListener);
    return this.n;
  }

  private QmiSpiritAction h(ImageView paramImageView, ActionListener paramActionListener)
  {
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = ResourceUtil.c("qmi_super_right");
    arrayOfInt[1] = ResourceUtil.c("qmi_stand2");
    arrayOfInt[2] = ResourceUtil.c("qmi_stand1");
    arrayOfInt[3] = ResourceUtil.c("qmi_stand0");
    if (this.k == null)
      this.k = new FrameAction(arrayOfInt);
    a(this.k, paramImageView, paramActionListener);
    return this.n;
  }

  private QmiSpiritAction i(ImageView paramImageView, ActionListener paramActionListener)
  {
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = ResourceUtil.c("qmi_super_left");
    arrayOfInt[1] = ResourceUtil.c("qmi_stand2");
    arrayOfInt[2] = ResourceUtil.c("qmi_stand1");
    arrayOfInt[3] = ResourceUtil.c("qmi_stand0");
    if (this.n == null)
      this.n = new FrameAction(arrayOfInt);
    a(this.n, paramImageView, paramActionListener);
    return this.n;
  }

  private QmiSpiritAction j(ImageView paramImageView, ActionListener paramActionListener)
  {
    int[] arrayOfInt = new int[3];
    arrayOfInt[0] = ResourceUtil.c("qmi_stand0");
    arrayOfInt[1] = ResourceUtil.c("qmi_stand1");
    arrayOfInt[2] = ResourceUtil.c("qmi_stand2");
    if (this.j == null)
      this.j = new FrameAction(arrayOfInt);
    a(this.j, paramImageView, paramActionListener);
    return this.j;
  }

  private QmiSpiritAction k(ImageView paramImageView, ActionListener paramActionListener)
  {
    int[] arrayOfInt = new int[3];
    arrayOfInt[0] = ResourceUtil.c("qmi_stand0");
    arrayOfInt[1] = ResourceUtil.c("qmi_stand1");
    arrayOfInt[2] = ResourceUtil.c("qmi_stand2");
    if (this.t == null)
      this.t = new FrameAction(arrayOfInt);
    a(this.t, paramImageView, paramActionListener);
    return this.t;
  }

  public void a()
  {
    if (this.z != null)
      this.z.a(true);
  }

  public void a(int paramInt, ImageView paramImageView)
  {
    switch (paramInt)
    {
    default:
      return;
    case 2:
      paramImageView.setImageResource(ResourceUtil.c("qmi_super_shadow_h"));
      return;
    case 1:
      paramImageView.setImageResource(ResourceUtil.c("qmi_super_shadow_h"));
      return;
    case 3:
      paramImageView.setImageResource(ResourceUtil.c("qmi_super_shadow_v"));
      return;
    case 4:
    }
    paramImageView.setImageResource(ResourceUtil.c("qmi_super_shadow_v"));
  }

  public void a(int paramInt, ImageView paramImageView, ActionListener paramActionListener)
  {
    if (this.z != null)
      this.z.a(true);
    QmiSpiritAction localQmiSpiritAction = null;
    switch (paramInt)
    {
    default:
    case 2:
    case 1:
    case 3:
    case 5:
    case 7:
    case 4:
    case 6:
    case 8:
    }
    while (true)
    {
      this.z = localQmiSpiritAction;
      return;
      localQmiSpiritAction = i(paramImageView, paramActionListener);
      continue;
      localQmiSpiritAction = h(paramImageView, paramActionListener);
      continue;
      localQmiSpiritAction = g(paramImageView, paramActionListener);
      continue;
      localQmiSpiritAction = f(paramImageView, paramActionListener);
      continue;
      localQmiSpiritAction = d(paramImageView, paramActionListener);
      continue;
      localQmiSpiritAction = e(paramImageView, paramActionListener);
    }
  }

  public void a(int paramInt, ImageView paramImageView, ActionListener paramActionListener, QMiSpirit paramQMiSpirit)
  {
    if (this.z != null)
      this.z.a(true);
    if (this.A == null)
      this.A = new ShakeAction(paramQMiSpirit);
    switch (paramInt)
    {
    default:
    case 2:
    case 1:
    case 3:
    case 4:
    }
    while (true)
    {
      this.A.a(paramActionListener);
      this.A.b();
      AnimationParam localAnimationParam = new AnimationParam();
      localAnimationParam.a = QMiWindowManager.e().x;
      localAnimationParam.b = QMiWindowManager.e().y;
      this.A.a(localAnimationParam);
      this.z = this.A;
      return;
      paramImageView.setImageResource(ResourceUtil.c("qmi_super_left"));
      continue;
      paramImageView.setImageResource(ResourceUtil.c("qmi_super_right"));
      continue;
      paramImageView.setImageResource(ResourceUtil.c("qmi_super_up"));
      continue;
      paramImageView.setImageResource(ResourceUtil.c("qmi_super_down"));
    }
  }

  public void a(ImageView paramImageView, ActionListener paramActionListener)
  {
    int[] arrayOfInt = new int[5];
    arrayOfInt[0] = ResourceUtil.c("qmi_turn_ball");
    arrayOfInt[1] = ResourceUtil.c("qmi_turn_ball_3");
    arrayOfInt[2] = ResourceUtil.c("qmi_turn_ball_2");
    arrayOfInt[3] = ResourceUtil.c("qmi_turn_ball_1");
    arrayOfInt[4] = ResourceUtil.c("qmi_stand0");
    if (this.x == null)
      this.x = new FrameAction(arrayOfInt);
    a(this.x, paramImageView, paramActionListener);
  }

  public void b(int paramInt, ImageView paramImageView)
  {
    switch (paramInt)
    {
    default:
      return;
    case 2:
      paramImageView.setImageResource(ResourceUtil.c("qmi_move_to_left"));
      return;
    case 1:
      paramImageView.setImageResource(ResourceUtil.c("qmi_move_to_right"));
      return;
    case 3:
      paramImageView.setImageResource(ResourceUtil.c("qmi_move_to_up"));
      return;
    case 4:
      paramImageView.setImageResource(ResourceUtil.c("qmi_move_to_down"));
      return;
    case 6:
      paramImageView.setImageResource(ResourceUtil.c("qmi_move_to_left_down"));
      return;
    case 8:
      paramImageView.setImageResource(ResourceUtil.c("qmi_move_to_right_down"));
      return;
    case 5:
      paramImageView.setImageResource(ResourceUtil.c("qmi_move_to_left_up"));
      return;
    case 7:
    }
    paramImageView.setImageResource(ResourceUtil.c("qmi_move_to_right_up"));
  }

  public void b(int paramInt, ImageView paramImageView, ActionListener paramActionListener)
  {
    if (this.z != null)
      this.z.a(true);
    QmiSpiritAction localQmiSpiritAction = null;
    switch (paramInt)
    {
    default:
    case 2:
    case 1:
    case 3:
    case 4:
    case 6:
    case 8:
    case 5:
    case 7:
    }
    while (true)
    {
      this.z = localQmiSpiritAction;
      return;
      localQmiSpiritAction = k(paramImageView, paramActionListener);
      continue;
      localQmiSpiritAction = j(paramImageView, paramActionListener);
      continue;
      localQmiSpiritAction = j(paramImageView, paramActionListener);
      continue;
      localQmiSpiritAction = j(paramImageView, paramActionListener);
      continue;
      localQmiSpiritAction = j(paramImageView, paramActionListener);
      continue;
      localQmiSpiritAction = j(paramImageView, paramActionListener);
      continue;
      localQmiSpiritAction = j(paramImageView, paramActionListener);
      continue;
      localQmiSpiritAction = j(paramImageView, paramActionListener);
    }
  }

  public void b(ImageView paramImageView, ActionListener paramActionListener)
  {
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = ResourceUtil.c("qmi_turn_ball_1");
    arrayOfInt[1] = ResourceUtil.c("qmi_turn_ball_2");
    arrayOfInt[2] = ResourceUtil.c("qmi_turn_ball_3");
    arrayOfInt[3] = ResourceUtil.c("qmi_turn_ball");
    if (this.w == null)
      this.w = new FrameAction(arrayOfInt);
    a(this.w, paramImageView, paramActionListener);
  }

  public void c(ImageView paramImageView, ActionListener paramActionListener)
  {
    int[] arrayOfInt1 = new int[5];
    arrayOfInt1[0] = ResourceUtil.c("qmi_stand0");
    arrayOfInt1[1] = ResourceUtil.c("qmi_stand1");
    arrayOfInt1[2] = ResourceUtil.c("qmi_stand2");
    arrayOfInt1[3] = ResourceUtil.c("qmi_stand1");
    arrayOfInt1[4] = ResourceUtil.c("qmi_stand0");
    int[] arrayOfInt2 = { 25, 25, 100, 25, 25 };
    boolean[] arrayOfBoolean = { 0, 0, 1, 0, 0 };
    int[] arrayOfInt3 = new int[4];
    arrayOfInt3[0] = ResourceUtil.c("qmi_cricle1");
    arrayOfInt3[1] = ResourceUtil.c("qmi_circle2");
    arrayOfInt3[2] = ResourceUtil.c("qmi_circle3");
    arrayOfInt3[3] = ResourceUtil.c("qmi_transparent");
    if (this.y == null)
      this.y = new FrameBackGroundAction(arrayOfInt1, arrayOfInt2, arrayOfBoolean, arrayOfInt3, null, 2);
    a(this.y, paramImageView, paramActionListener);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.animation.AnimationManager
 * JD-Core Version:    0.6.0
 */