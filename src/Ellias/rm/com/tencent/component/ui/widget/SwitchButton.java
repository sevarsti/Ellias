package com.tencent.component.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.tencent.component.utils.ResourceUtil;

public class SwitchButton extends CheckBox
{
  private boolean A;
  private j B;
  private CompoundButton.OnCheckedChangeListener C;
  private CompoundButton.OnCheckedChangeListener D;
  private boolean E;
  private final float F = 350.0F;
  private float G;
  private final float H = 15.0F;
  private float I;
  private float J;
  private boolean K = true;
  private float L;
  private ViewParent a;
  private Paint b;
  private Bitmap c;
  private Bitmap d;
  private Bitmap e;
  private Bitmap f;
  private Bitmap g;
  private Bitmap h;
  private RectF i;
  private PorterDuffXfermode j;
  private float k;
  private float l;
  private float m;
  private float n;
  private float o;
  private float p;
  private float q;
  private float r;
  private float s;
  private float t;
  private int u;
  private int v;
  private final int w = 255;
  private int x = 255;
  private boolean y = false;
  private boolean z;

  public SwitchButton(Context paramContext)
  {
    this(paramContext, null);
  }

  public SwitchButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 16842860);
  }

  public SwitchButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramContext);
  }

  private float a(float paramFloat)
  {
    return paramFloat - this.s / 2.0F;
  }

  private void a()
  {
    this.a = getParent();
    if (this.a != null)
      this.a.requestDisallowInterceptTouchEvent(true);
  }

  private void a(Context paramContext)
  {
    this.b = new Paint();
    this.b.setColor(-1);
    Resources localResources = paramContext.getResources();
    this.u = (ViewConfiguration.getPressedStateDuration() + ViewConfiguration.getTapTimeout());
    this.v = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    this.c = BitmapFactory.decodeResource(localResources, ResourceUtil.c("appfw_switch_bottom"));
    this.e = BitmapFactory.decodeResource(localResources, ResourceUtil.c("appfw_switch_btn_pressed"));
    this.f = BitmapFactory.decodeResource(localResources, ResourceUtil.c("appfw_switch_btn_unpressed"));
    this.g = BitmapFactory.decodeResource(localResources, ResourceUtil.c("appfw_switch_frame"));
    this.h = BitmapFactory.decodeResource(localResources, ResourceUtil.c("appfw_switch_mask"));
    this.d = this.f;
    this.s = this.e.getWidth();
    this.q = this.h.getWidth();
    this.r = this.h.getHeight();
    this.o = (this.s / 2.0F);
    this.p = (this.q - this.s / 2.0F);
    float f1;
    if (this.y)
      f1 = this.o;
    while (true)
    {
      this.n = f1;
      this.m = a(this.n);
      float f2 = getResources().getDisplayMetrics().density;
      this.G = (int)(0.5F + 350.0F * f2);
      this.I = (int)(0.5F + f2 * 15.0F);
      this.i = new RectF(0.0F, this.I, this.h.getWidth(), this.h.getHeight() + this.I);
      this.j = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
      return;
      f1 = this.p;
    }
  }

  private void a(boolean paramBoolean)
  {
    this.E = true;
    float f1;
    if (paramBoolean)
      f1 = this.G;
    while (true)
    {
      this.L = f1;
      this.J = this.n;
      new k(this, null).run();
      return;
      f1 = -this.G;
    }
  }

  private void b()
  {
    this.E = false;
  }

  private void b(float paramFloat)
  {
    this.n = paramFloat;
    this.m = a(this.n);
    invalidate();
  }

  private void c()
  {
    this.J += 16.0F * this.L / 1000.0F;
    if (this.J <= this.p)
    {
      b();
      this.J = this.p;
      setCheckedDelayed(false);
    }
    while (true)
    {
      b(this.J);
      return;
      if (this.J < this.o)
        continue;
      b();
      this.J = this.o;
      setCheckedDelayed(true);
    }
  }

  private void setCheckedDelayed(boolean paramBoolean)
  {
    postDelayed(new i(this, paramBoolean), 10L);
  }

  public boolean isChecked()
  {
    return this.y;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.saveLayerAlpha(this.i, this.x, 31);
    paramCanvas.drawBitmap(this.h, 0.0F, this.I, this.b);
    this.b.setXfermode(this.j);
    paramCanvas.drawBitmap(this.c, this.m, this.I, this.b);
    Rect localRect;
    if (this.K)
    {
      paramCanvas.save();
      localRect = paramCanvas.getClipBounds();
      if (!this.y)
        break label206;
      paramCanvas.clipRect(new Rect((int)this.o, localRect.top, (int)(this.o + this.p), localRect.bottom));
      paramCanvas.drawBitmap(this.c, this.m + this.p, this.I, this.b);
    }
    while (true)
    {
      paramCanvas.restore();
      this.b.setXfermode(null);
      paramCanvas.drawBitmap(this.g, 0.0F, this.I, this.b);
      paramCanvas.drawBitmap(this.d, this.m, this.I, this.b);
      paramCanvas.restore();
      return;
      label206: paramCanvas.clipRect(new Rect(0, localRect.top, (int)this.p, localRect.bottom));
      paramCanvas.drawBitmap(this.c, this.m - this.p, this.I, this.b);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension((int)this.q, (int)(this.r + 2.0F * this.I));
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i1 = paramMotionEvent.getAction();
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    float f3 = Math.abs(f1 - this.l);
    float f4 = Math.abs(f2 - this.k);
    this.K = false;
    switch (i1)
    {
    default:
    case 0:
    case 2:
    case 1:
    }
    while (true)
    {
      invalidate();
      return isEnabled();
      a();
      this.l = f1;
      this.k = f2;
      this.d = this.e;
      float f6;
      if (this.y)
        f6 = this.o;
      while (true)
      {
        this.t = f6;
        break;
        f6 = this.p;
      }
      (float)(paramMotionEvent.getEventTime() - paramMotionEvent.getDownTime());
      this.n = (this.t + paramMotionEvent.getX() - this.l);
      if (this.n >= this.o)
        this.n = this.o;
      if (this.n <= this.p)
        this.n = this.p;
      boolean bool1 = this.n < (this.o - this.p) / 2.0F + this.p;
      boolean bool2 = false;
      if (!bool1)
        bool2 = true;
      this.A = bool2;
      this.m = a(this.n);
      continue;
      this.d = this.f;
      float f5 = (float)(paramMotionEvent.getEventTime() - paramMotionEvent.getDownTime());
      if ((f4 < this.v) && (f3 < this.v) && (f5 < this.u))
      {
        if (this.B == null)
          this.B = new j(this, null);
        if (post(this.B))
          continue;
        performClick();
        continue;
      }
      a(this.A);
    }
  }

  public boolean performClick()
  {
    if (!this.y);
    for (boolean bool = true; ; bool = false)
    {
      a(bool);
      return true;
    }
  }

  public void setChecked(boolean paramBoolean)
  {
    float f1;
    if (this.y != paramBoolean)
    {
      this.y = paramBoolean;
      if (!paramBoolean)
        break label51;
      f1 = this.o;
    }
    while (true)
    {
      this.n = f1;
      this.m = a(this.n);
      invalidate();
      if (!this.z)
        break;
      return;
      label51: f1 = this.p;
    }
    this.z = true;
    if (this.C != null)
      this.C.onCheckedChanged(this, this.y);
    if (this.D != null)
      this.D.onCheckedChanged(this, this.y);
    this.z = false;
  }

  public void setEnabled(boolean paramBoolean)
  {
    if (paramBoolean);
    for (int i1 = 255; ; i1 = 127)
    {
      this.x = i1;
      super.setEnabled(paramBoolean);
      return;
    }
  }

  public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener paramOnCheckedChangeListener)
  {
    this.C = paramOnCheckedChangeListener;
  }

  void setOnCheckedChangeWidgetListener(CompoundButton.OnCheckedChangeListener paramOnCheckedChangeListener)
  {
    this.D = paramOnCheckedChangeListener;
  }

  public void toggle()
  {
    if (!this.y);
    for (boolean bool = true; ; bool = false)
    {
      setChecked(bool);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.SwitchButton
 * JD-Core Version:    0.6.0
 */