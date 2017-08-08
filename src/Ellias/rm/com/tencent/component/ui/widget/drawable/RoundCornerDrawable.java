package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class RoundCornerDrawable extends DrawableContainer
{
  private static final int a = 6;
  private static final ThreadLocal b = new k();
  private p c;
  private m d;
  private Path e = new Path();
  private RectF f = new RectF();
  private int g = -1;

  public RoundCornerDrawable(Drawable paramDrawable)
  {
    this(paramDrawable, 0.0F);
  }

  public RoundCornerDrawable(Drawable paramDrawable, float paramFloat)
  {
    this.c = new p(paramDrawable, this);
    a(this.c);
    a(paramFloat);
    a(paramDrawable);
  }

  public RoundCornerDrawable(Drawable paramDrawable, float[] paramArrayOfFloat)
  {
    this.c = new p(paramDrawable, this);
    a(this.c);
    a(paramArrayOfFloat);
    a(paramDrawable);
  }

  private RoundCornerDrawable(p paramp, Resources paramResources)
  {
    this.c = new p(paramp, this, paramResources);
    a(this.c);
  }

  private void a(Drawable paramDrawable)
  {
    if (paramDrawable == null);
    do
    {
      return;
      if ((paramDrawable instanceof ImageDrawable))
      {
        this.d = new o((ImageDrawable)paramDrawable);
        return;
      }
      if (!(paramDrawable instanceof BitmapDrawable))
        continue;
      this.d = new n((BitmapDrawable)paramDrawable);
      return;
    }
    while (!(paramDrawable instanceof android.graphics.drawable.DrawableContainer));
    this.d = new l((android.graphics.drawable.DrawableContainer)paramDrawable);
  }

  public void a(float paramFloat)
  {
    this.c.e = false;
    if (this.c.c != paramFloat)
    {
      this.c.c = paramFloat;
      invalidateSelf();
    }
  }

  public void a(boolean paramBoolean)
  {
    if (this.c.f != paramBoolean)
    {
      this.c.f = paramBoolean;
      invalidateSelf();
    }
  }

  public void a(float[] paramArrayOfFloat)
  {
    if ((paramArrayOfFloat != null) && (paramArrayOfFloat.length < 8))
      throw new ArrayIndexOutOfBoundsException("radius array must have >= 8 values");
    this.c.e = true;
    this.c.d = paramArrayOfFloat;
    invalidateSelf();
  }

  // ERROR //
  public void draw(android.graphics.Canvas paramCanvas)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 51	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:c	Lcom/tencent/component/ui/widget/drawable/p;
    //   4: getfield 105	com/tencent/component/ui/widget/drawable/p:f	Z
    //   7: istore_2
    //   8: aload_0
    //   9: getfield 51	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:c	Lcom/tencent/component/ui/widget/drawable/p;
    //   12: getfield 99	com/tencent/component/ui/widget/drawable/p:c	F
    //   15: fstore_3
    //   16: aload_0
    //   17: getfield 51	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:c	Lcom/tencent/component/ui/widget/drawable/p;
    //   20: getfield 115	com/tencent/component/ui/widget/drawable/p:d	[F
    //   23: astore 4
    //   25: iload_2
    //   26: ifne +20 -> 46
    //   29: fload_3
    //   30: fconst_0
    //   31: fcmpl
    //   32: ifne +14 -> 46
    //   35: aload 4
    //   37: ifnonnull +9 -> 46
    //   40: aload_0
    //   41: aload_1
    //   42: invokespecial 121	com/tencent/component/ui/widget/drawable/DrawableContainer:draw	(Landroid/graphics/Canvas;)V
    //   45: return
    //   46: aload_0
    //   47: getfield 79	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:d	Lcom/tencent/component/ui/widget/drawable/m;
    //   50: ifnull +148 -> 198
    //   53: aload_0
    //   54: getfield 79	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:d	Lcom/tencent/component/ui/widget/drawable/m;
    //   57: invokevirtual 126	com/tencent/component/ui/widget/drawable/m:a	()Z
    //   60: ifeq +138 -> 198
    //   63: getstatic 27	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:b	Ljava/lang/ThreadLocal;
    //   66: invokevirtual 132	java/lang/ThreadLocal:get	()Ljava/lang/Object;
    //   69: checkcast 134	android/graphics/Paint
    //   72: astore 11
    //   74: aload_0
    //   75: getfield 37	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:e	Landroid/graphics/Path;
    //   78: astore 12
    //   80: aload_0
    //   81: getfield 42	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:f	Landroid/graphics/RectF;
    //   84: astore 13
    //   86: aload 13
    //   88: aload_0
    //   89: invokevirtual 138	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:getBounds	()Landroid/graphics/Rect;
    //   92: invokevirtual 142	android/graphics/RectF:set	(Landroid/graphics/Rect;)V
    //   95: aload 11
    //   97: aconst_null
    //   98: invokevirtual 146	android/graphics/Paint:setShader	(Landroid/graphics/Shader;)Landroid/graphics/Shader;
    //   101: pop
    //   102: aload_0
    //   103: getfield 44	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:g	I
    //   106: ifle +34 -> 140
    //   109: aload 11
    //   111: aload_0
    //   112: getfield 44	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:g	I
    //   115: invokevirtual 150	android/graphics/Paint:setAlpha	(I)V
    //   118: aload_0
    //   119: getfield 79	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:d	Lcom/tencent/component/ui/widget/drawable/m;
    //   122: aload 11
    //   124: invokevirtual 153	com/tencent/component/ui/widget/drawable/m:a	(Landroid/graphics/Paint;)V
    //   127: iload_2
    //   128: ifeq +23 -> 151
    //   131: aload_1
    //   132: aload 13
    //   134: aload 11
    //   136: invokevirtual 159	android/graphics/Canvas:drawOval	(Landroid/graphics/RectF;Landroid/graphics/Paint;)V
    //   139: return
    //   140: aload 11
    //   142: sipush 255
    //   145: invokevirtual 150	android/graphics/Paint:setAlpha	(I)V
    //   148: goto -30 -> 118
    //   151: aload_0
    //   152: getfield 51	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:c	Lcom/tencent/component/ui/widget/drawable/p;
    //   155: getfield 96	com/tencent/component/ui/widget/drawable/p:e	Z
    //   158: ifeq +29 -> 187
    //   161: aload 12
    //   163: invokevirtual 162	android/graphics/Path:reset	()V
    //   166: aload 12
    //   168: aload 13
    //   170: aload 4
    //   172: getstatic 168	android/graphics/Path$Direction:CW	Landroid/graphics/Path$Direction;
    //   175: invokevirtual 172	android/graphics/Path:addRoundRect	(Landroid/graphics/RectF;[FLandroid/graphics/Path$Direction;)V
    //   178: aload_1
    //   179: aload 12
    //   181: aload 11
    //   183: invokevirtual 176	android/graphics/Canvas:drawPath	(Landroid/graphics/Path;Landroid/graphics/Paint;)V
    //   186: return
    //   187: aload_1
    //   188: aload 13
    //   190: fload_3
    //   191: fload_3
    //   192: aload 11
    //   194: invokevirtual 180	android/graphics/Canvas:drawRoundRect	(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V
    //   197: return
    //   198: aload_0
    //   199: getfield 37	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:e	Landroid/graphics/Path;
    //   202: astore 5
    //   204: aload_0
    //   205: getfield 42	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:f	Landroid/graphics/RectF;
    //   208: astore 6
    //   210: aload 6
    //   212: aload_0
    //   213: invokevirtual 138	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:getBounds	()Landroid/graphics/Rect;
    //   216: invokevirtual 142	android/graphics/RectF:set	(Landroid/graphics/Rect;)V
    //   219: aload 5
    //   221: invokevirtual 162	android/graphics/Path:reset	()V
    //   224: iload_2
    //   225: ifeq +38 -> 263
    //   228: aload 5
    //   230: aload 6
    //   232: getstatic 168	android/graphics/Path$Direction:CW	Landroid/graphics/Path$Direction;
    //   235: invokevirtual 184	android/graphics/Path:addOval	(Landroid/graphics/RectF;Landroid/graphics/Path$Direction;)V
    //   238: aload_1
    //   239: invokevirtual 188	android/graphics/Canvas:save	()I
    //   242: istore 7
    //   244: aload_1
    //   245: aload 5
    //   247: invokevirtual 192	android/graphics/Canvas:clipPath	(Landroid/graphics/Path;)Z
    //   250: pop
    //   251: aload_0
    //   252: aload_1
    //   253: invokespecial 121	com/tencent/component/ui/widget/drawable/DrawableContainer:draw	(Landroid/graphics/Canvas;)V
    //   256: aload_1
    //   257: iload 7
    //   259: invokevirtual 195	android/graphics/Canvas:restoreToCount	(I)V
    //   262: return
    //   263: aload_0
    //   264: getfield 51	com/tencent/component/ui/widget/drawable/RoundCornerDrawable:c	Lcom/tencent/component/ui/widget/drawable/p;
    //   267: getfield 96	com/tencent/component/ui/widget/drawable/p:e	Z
    //   270: ifeq +18 -> 288
    //   273: aload 5
    //   275: aload 6
    //   277: aload 4
    //   279: getstatic 168	android/graphics/Path$Direction:CW	Landroid/graphics/Path$Direction;
    //   282: invokevirtual 172	android/graphics/Path:addRoundRect	(Landroid/graphics/RectF;[FLandroid/graphics/Path$Direction;)V
    //   285: goto -47 -> 238
    //   288: aload 5
    //   290: aload 6
    //   292: fload_3
    //   293: fload_3
    //   294: getstatic 168	android/graphics/Path$Direction:CW	Landroid/graphics/Path$Direction;
    //   297: invokevirtual 198	android/graphics/Path:addRoundRect	(Landroid/graphics/RectF;FFLandroid/graphics/Path$Direction;)V
    //   300: goto -62 -> 238
    //   303: astore 9
    //   305: aload_1
    //   306: iload 7
    //   308: invokevirtual 195	android/graphics/Canvas:restoreToCount	(I)V
    //   311: return
    //   312: astore 8
    //   314: aload_1
    //   315: iload 7
    //   317: invokevirtual 195	android/graphics/Canvas:restoreToCount	(I)V
    //   320: aload 8
    //   322: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   244	256	303	java/lang/UnsupportedOperationException
    //   244	256	312	finally
  }

  protected void onBoundsChange(Rect paramRect)
  {
    super.onBoundsChange(paramRect);
    if ((this.d != null) && (this.d.a()))
      this.d.a(paramRect);
  }

  public void setAlpha(int paramInt)
  {
    this.g = paramInt;
    super.setAlpha(paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.RoundCornerDrawable
 * JD-Core Version:    0.6.0
 */