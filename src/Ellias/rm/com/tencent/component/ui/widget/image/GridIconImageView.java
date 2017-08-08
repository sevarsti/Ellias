package com.tencent.component.ui.widget.image;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.image.ImageLoader;
import com.tencent.component.image.ImageLoader.ImageLoadListener;
import com.tencent.component.image.ImageLoader.Options;
import com.tencent.component.utils.log.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GridIconImageView extends ExtendImageView
{
  private static final String a = GridIconImageView.class.getSimpleName();
  private static final int b = 32;
  private static LruCache c = new LruCache(32);
  private static final int d = 1;
  private static final int e = 9;
  private List f = new ArrayList(9);
  private List g = new ArrayList(9);
  private String h;
  private int i = 0;
  private boolean j = false;
  private boolean k = false;
  private ImageLoader l;
  private ImageLoader.Options m = new ImageLoader.Options();
  private Drawable n;
  private Bitmap o;
  private int p = -1;
  private Handler q = new j(this);
  private m[][] r;
  private ImageLoader.ImageLoadListener s;

  @PluginApi(a=7)
  public GridIconImageView(Context paramContext)
  {
    super(paramContext);
    m[][] arrayOfm; = new m[9][];
    m[] arrayOfm1 = new m[1];
    arrayOfm1[0] = new m(this, 0.0D, 0.0D, 1.0D, 1.0D);
    arrayOfm;[0] = arrayOfm1;
    m[] arrayOfm2 = new m[2];
    arrayOfm2[0] = new m(this, 0.0D, 0.25D, 0.5D, 0.75D);
    arrayOfm2[1] = new m(this, 0.5D, 0.25D, 1.0D, 0.75D);
    arrayOfm;[1] = arrayOfm2;
    m[] arrayOfm3 = new m[3];
    arrayOfm3[0] = new m(this, 0.25D, 0.0D, 0.75D, 0.5D);
    arrayOfm3[1] = new m(this, 0.0D, 0.5D, 0.5D, 1.0D);
    arrayOfm3[2] = new m(this, 0.5D, 0.5D, 1.0D, 1.0D);
    arrayOfm;[2] = arrayOfm3;
    m[] arrayOfm4 = new m[4];
    arrayOfm4[0] = new m(this, 0.0D, 0.0D, 0.5D, 0.5D);
    arrayOfm4[1] = new m(this, 0.5D, 0.0D, 1.0D, 0.5D);
    arrayOfm4[2] = new m(this, 0.0D, 0.5D, 0.5D, 1.0D);
    arrayOfm4[3] = new m(this, 0.5D, 0.5D, 1.0D, 1.0D);
    arrayOfm;[3] = arrayOfm4;
    m[] arrayOfm5 = new m[5];
    arrayOfm5[0] = new m(this, 0.1666666666666667D, 0.1666666666666667D, 0.5D, 0.5D);
    arrayOfm5[1] = new m(this, 0.5D, 0.1666666666666667D, 0.8333333333333334D, 0.5D);
    arrayOfm5[2] = new m(this, 0.0D, 0.5D, 0.3333333333333333D, 0.8333333333333334D);
    arrayOfm5[3] = new m(this, 0.3333333333333333D, 0.5D, 0.6666666666666666D, 0.8333333333333334D);
    arrayOfm5[4] = new m(this, 0.6666666666666666D, 0.5D, 1.0D, 0.8333333333333334D);
    arrayOfm;[4] = arrayOfm5;
    m[] arrayOfm6 = new m[6];
    arrayOfm6[0] = new m(this, 0.0D, 0.1666666666666667D, 0.3333333333333333D, 0.5D);
    arrayOfm6[1] = new m(this, 0.3333333333333333D, 0.1666666666666667D, 0.6666666666666666D, 0.5D);
    arrayOfm6[2] = new m(this, 0.6666666666666666D, 0.1666666666666667D, 1.0D, 0.5D);
    arrayOfm6[3] = new m(this, 0.0D, 0.5D, 0.3333333333333333D, 0.8333333333333334D);
    arrayOfm6[4] = new m(this, 0.3333333333333333D, 0.5D, 0.6666666666666666D, 0.8333333333333334D);
    arrayOfm6[5] = new m(this, 0.6666666666666666D, 0.5D, 1.0D, 0.8333333333333334D);
    arrayOfm;[5] = arrayOfm6;
    m[] arrayOfm7 = new m[7];
    arrayOfm7[0] = new m(this, 0.3333333333333333D, 0.0D, 0.6666666666666666D, 0.3333333333333333D);
    arrayOfm7[1] = new m(this, 0.0D, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D);
    arrayOfm7[2] = new m(this, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D);
    arrayOfm7[3] = new m(this, 0.6666666666666666D, 0.3333333333333333D, 1.0D, 0.6666666666666666D);
    arrayOfm7[4] = new m(this, 0.0D, 0.6666666666666666D, 0.3333333333333333D, 1.0D);
    arrayOfm7[5] = new m(this, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D, 1.0D);
    arrayOfm7[6] = new m(this, 0.6666666666666666D, 0.6666666666666666D, 1.0D, 1.0D);
    arrayOfm;[6] = arrayOfm7;
    m[] arrayOfm8 = new m[8];
    arrayOfm8[0] = new m(this, 0.1666666666666667D, 0.0D, 0.5D, 0.3333333333333333D);
    arrayOfm8[1] = new m(this, 0.5D, 0.0D, 0.8333333333333334D, 0.3333333333333333D);
    arrayOfm8[2] = new m(this, 0.0D, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D);
    arrayOfm8[3] = new m(this, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D);
    arrayOfm8[4] = new m(this, 0.6666666666666666D, 0.3333333333333333D, 1.0D, 0.6666666666666666D);
    arrayOfm8[5] = new m(this, 0.0D, 0.6666666666666666D, 0.3333333333333333D, 1.0D);
    arrayOfm8[6] = new m(this, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D, 1.0D);
    arrayOfm8[7] = new m(this, 0.6666666666666666D, 0.6666666666666666D, 1.0D, 1.0D);
    arrayOfm;[7] = arrayOfm8;
    m[] arrayOfm9 = new m[9];
    arrayOfm9[0] = new m(this, 0.0D, 0.0D, 0.3333333333333333D, 0.3333333333333333D);
    arrayOfm9[1] = new m(this, 0.3333333333333333D, 0.0D, 0.6666666666666666D, 0.3333333333333333D);
    arrayOfm9[2] = new m(this, 0.6666666666666666D, 0.0D, 1.0D, 0.3333333333333333D);
    arrayOfm9[3] = new m(this, 0.0D, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D);
    arrayOfm9[4] = new m(this, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D);
    arrayOfm9[5] = new m(this, 0.6666666666666666D, 0.3333333333333333D, 1.0D, 0.6666666666666666D);
    arrayOfm9[6] = new m(this, 0.0D, 0.6666666666666666D, 0.3333333333333333D, 1.0D);
    arrayOfm9[7] = new m(this, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D, 1.0D);
    arrayOfm9[8] = new m(this, 0.6666666666666666D, 0.6666666666666666D, 1.0D, 1.0D);
    arrayOfm;[8] = arrayOfm9;
    this.r = arrayOfm;;
    this.s = new k(this);
    a(paramContext);
  }

  @PluginApi(a=7)
  public GridIconImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    m[][] arrayOfm; = new m[9][];
    m[] arrayOfm1 = new m[1];
    arrayOfm1[0] = new m(this, 0.0D, 0.0D, 1.0D, 1.0D);
    arrayOfm;[0] = arrayOfm1;
    m[] arrayOfm2 = new m[2];
    arrayOfm2[0] = new m(this, 0.0D, 0.25D, 0.5D, 0.75D);
    arrayOfm2[1] = new m(this, 0.5D, 0.25D, 1.0D, 0.75D);
    arrayOfm;[1] = arrayOfm2;
    m[] arrayOfm3 = new m[3];
    arrayOfm3[0] = new m(this, 0.25D, 0.0D, 0.75D, 0.5D);
    arrayOfm3[1] = new m(this, 0.0D, 0.5D, 0.5D, 1.0D);
    arrayOfm3[2] = new m(this, 0.5D, 0.5D, 1.0D, 1.0D);
    arrayOfm;[2] = arrayOfm3;
    m[] arrayOfm4 = new m[4];
    arrayOfm4[0] = new m(this, 0.0D, 0.0D, 0.5D, 0.5D);
    arrayOfm4[1] = new m(this, 0.5D, 0.0D, 1.0D, 0.5D);
    arrayOfm4[2] = new m(this, 0.0D, 0.5D, 0.5D, 1.0D);
    arrayOfm4[3] = new m(this, 0.5D, 0.5D, 1.0D, 1.0D);
    arrayOfm;[3] = arrayOfm4;
    m[] arrayOfm5 = new m[5];
    arrayOfm5[0] = new m(this, 0.1666666666666667D, 0.1666666666666667D, 0.5D, 0.5D);
    arrayOfm5[1] = new m(this, 0.5D, 0.1666666666666667D, 0.8333333333333334D, 0.5D);
    arrayOfm5[2] = new m(this, 0.0D, 0.5D, 0.3333333333333333D, 0.8333333333333334D);
    arrayOfm5[3] = new m(this, 0.3333333333333333D, 0.5D, 0.6666666666666666D, 0.8333333333333334D);
    arrayOfm5[4] = new m(this, 0.6666666666666666D, 0.5D, 1.0D, 0.8333333333333334D);
    arrayOfm;[4] = arrayOfm5;
    m[] arrayOfm6 = new m[6];
    arrayOfm6[0] = new m(this, 0.0D, 0.1666666666666667D, 0.3333333333333333D, 0.5D);
    arrayOfm6[1] = new m(this, 0.3333333333333333D, 0.1666666666666667D, 0.6666666666666666D, 0.5D);
    arrayOfm6[2] = new m(this, 0.6666666666666666D, 0.1666666666666667D, 1.0D, 0.5D);
    arrayOfm6[3] = new m(this, 0.0D, 0.5D, 0.3333333333333333D, 0.8333333333333334D);
    arrayOfm6[4] = new m(this, 0.3333333333333333D, 0.5D, 0.6666666666666666D, 0.8333333333333334D);
    arrayOfm6[5] = new m(this, 0.6666666666666666D, 0.5D, 1.0D, 0.8333333333333334D);
    arrayOfm;[5] = arrayOfm6;
    m[] arrayOfm7 = new m[7];
    arrayOfm7[0] = new m(this, 0.3333333333333333D, 0.0D, 0.6666666666666666D, 0.3333333333333333D);
    arrayOfm7[1] = new m(this, 0.0D, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D);
    arrayOfm7[2] = new m(this, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D);
    arrayOfm7[3] = new m(this, 0.6666666666666666D, 0.3333333333333333D, 1.0D, 0.6666666666666666D);
    arrayOfm7[4] = new m(this, 0.0D, 0.6666666666666666D, 0.3333333333333333D, 1.0D);
    arrayOfm7[5] = new m(this, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D, 1.0D);
    arrayOfm7[6] = new m(this, 0.6666666666666666D, 0.6666666666666666D, 1.0D, 1.0D);
    arrayOfm;[6] = arrayOfm7;
    m[] arrayOfm8 = new m[8];
    arrayOfm8[0] = new m(this, 0.1666666666666667D, 0.0D, 0.5D, 0.3333333333333333D);
    arrayOfm8[1] = new m(this, 0.5D, 0.0D, 0.8333333333333334D, 0.3333333333333333D);
    arrayOfm8[2] = new m(this, 0.0D, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D);
    arrayOfm8[3] = new m(this, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D);
    arrayOfm8[4] = new m(this, 0.6666666666666666D, 0.3333333333333333D, 1.0D, 0.6666666666666666D);
    arrayOfm8[5] = new m(this, 0.0D, 0.6666666666666666D, 0.3333333333333333D, 1.0D);
    arrayOfm8[6] = new m(this, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D, 1.0D);
    arrayOfm8[7] = new m(this, 0.6666666666666666D, 0.6666666666666666D, 1.0D, 1.0D);
    arrayOfm;[7] = arrayOfm8;
    m[] arrayOfm9 = new m[9];
    arrayOfm9[0] = new m(this, 0.0D, 0.0D, 0.3333333333333333D, 0.3333333333333333D);
    arrayOfm9[1] = new m(this, 0.3333333333333333D, 0.0D, 0.6666666666666666D, 0.3333333333333333D);
    arrayOfm9[2] = new m(this, 0.6666666666666666D, 0.0D, 1.0D, 0.3333333333333333D);
    arrayOfm9[3] = new m(this, 0.0D, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D);
    arrayOfm9[4] = new m(this, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D);
    arrayOfm9[5] = new m(this, 0.6666666666666666D, 0.3333333333333333D, 1.0D, 0.6666666666666666D);
    arrayOfm9[6] = new m(this, 0.0D, 0.6666666666666666D, 0.3333333333333333D, 1.0D);
    arrayOfm9[7] = new m(this, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D, 1.0D);
    arrayOfm9[8] = new m(this, 0.6666666666666666D, 0.6666666666666666D, 1.0D, 1.0D);
    arrayOfm;[8] = arrayOfm9;
    this.r = arrayOfm;;
    this.s = new k(this);
    a(paramContext);
  }

  @PluginApi(a=7)
  public GridIconImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    m[][] arrayOfm; = new m[9][];
    m[] arrayOfm1 = new m[1];
    arrayOfm1[0] = new m(this, 0.0D, 0.0D, 1.0D, 1.0D);
    arrayOfm;[0] = arrayOfm1;
    m[] arrayOfm2 = new m[2];
    arrayOfm2[0] = new m(this, 0.0D, 0.25D, 0.5D, 0.75D);
    arrayOfm2[1] = new m(this, 0.5D, 0.25D, 1.0D, 0.75D);
    arrayOfm;[1] = arrayOfm2;
    m[] arrayOfm3 = new m[3];
    arrayOfm3[0] = new m(this, 0.25D, 0.0D, 0.75D, 0.5D);
    arrayOfm3[1] = new m(this, 0.0D, 0.5D, 0.5D, 1.0D);
    arrayOfm3[2] = new m(this, 0.5D, 0.5D, 1.0D, 1.0D);
    arrayOfm;[2] = arrayOfm3;
    m[] arrayOfm4 = new m[4];
    arrayOfm4[0] = new m(this, 0.0D, 0.0D, 0.5D, 0.5D);
    arrayOfm4[1] = new m(this, 0.5D, 0.0D, 1.0D, 0.5D);
    arrayOfm4[2] = new m(this, 0.0D, 0.5D, 0.5D, 1.0D);
    arrayOfm4[3] = new m(this, 0.5D, 0.5D, 1.0D, 1.0D);
    arrayOfm;[3] = arrayOfm4;
    m[] arrayOfm5 = new m[5];
    arrayOfm5[0] = new m(this, 0.1666666666666667D, 0.1666666666666667D, 0.5D, 0.5D);
    arrayOfm5[1] = new m(this, 0.5D, 0.1666666666666667D, 0.8333333333333334D, 0.5D);
    arrayOfm5[2] = new m(this, 0.0D, 0.5D, 0.3333333333333333D, 0.8333333333333334D);
    arrayOfm5[3] = new m(this, 0.3333333333333333D, 0.5D, 0.6666666666666666D, 0.8333333333333334D);
    arrayOfm5[4] = new m(this, 0.6666666666666666D, 0.5D, 1.0D, 0.8333333333333334D);
    arrayOfm;[4] = arrayOfm5;
    m[] arrayOfm6 = new m[6];
    arrayOfm6[0] = new m(this, 0.0D, 0.1666666666666667D, 0.3333333333333333D, 0.5D);
    arrayOfm6[1] = new m(this, 0.3333333333333333D, 0.1666666666666667D, 0.6666666666666666D, 0.5D);
    arrayOfm6[2] = new m(this, 0.6666666666666666D, 0.1666666666666667D, 1.0D, 0.5D);
    arrayOfm6[3] = new m(this, 0.0D, 0.5D, 0.3333333333333333D, 0.8333333333333334D);
    arrayOfm6[4] = new m(this, 0.3333333333333333D, 0.5D, 0.6666666666666666D, 0.8333333333333334D);
    arrayOfm6[5] = new m(this, 0.6666666666666666D, 0.5D, 1.0D, 0.8333333333333334D);
    arrayOfm;[5] = arrayOfm6;
    m[] arrayOfm7 = new m[7];
    arrayOfm7[0] = new m(this, 0.3333333333333333D, 0.0D, 0.6666666666666666D, 0.3333333333333333D);
    arrayOfm7[1] = new m(this, 0.0D, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D);
    arrayOfm7[2] = new m(this, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D);
    arrayOfm7[3] = new m(this, 0.6666666666666666D, 0.3333333333333333D, 1.0D, 0.6666666666666666D);
    arrayOfm7[4] = new m(this, 0.0D, 0.6666666666666666D, 0.3333333333333333D, 1.0D);
    arrayOfm7[5] = new m(this, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D, 1.0D);
    arrayOfm7[6] = new m(this, 0.6666666666666666D, 0.6666666666666666D, 1.0D, 1.0D);
    arrayOfm;[6] = arrayOfm7;
    m[] arrayOfm8 = new m[8];
    arrayOfm8[0] = new m(this, 0.1666666666666667D, 0.0D, 0.5D, 0.3333333333333333D);
    arrayOfm8[1] = new m(this, 0.5D, 0.0D, 0.8333333333333334D, 0.3333333333333333D);
    arrayOfm8[2] = new m(this, 0.0D, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D);
    arrayOfm8[3] = new m(this, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D);
    arrayOfm8[4] = new m(this, 0.6666666666666666D, 0.3333333333333333D, 1.0D, 0.6666666666666666D);
    arrayOfm8[5] = new m(this, 0.0D, 0.6666666666666666D, 0.3333333333333333D, 1.0D);
    arrayOfm8[6] = new m(this, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D, 1.0D);
    arrayOfm8[7] = new m(this, 0.6666666666666666D, 0.6666666666666666D, 1.0D, 1.0D);
    arrayOfm;[7] = arrayOfm8;
    m[] arrayOfm9 = new m[9];
    arrayOfm9[0] = new m(this, 0.0D, 0.0D, 0.3333333333333333D, 0.3333333333333333D);
    arrayOfm9[1] = new m(this, 0.3333333333333333D, 0.0D, 0.6666666666666666D, 0.3333333333333333D);
    arrayOfm9[2] = new m(this, 0.6666666666666666D, 0.0D, 1.0D, 0.3333333333333333D);
    arrayOfm9[3] = new m(this, 0.0D, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D);
    arrayOfm9[4] = new m(this, 0.3333333333333333D, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D);
    arrayOfm9[5] = new m(this, 0.6666666666666666D, 0.3333333333333333D, 1.0D, 0.6666666666666666D);
    arrayOfm9[6] = new m(this, 0.0D, 0.6666666666666666D, 0.3333333333333333D, 1.0D);
    arrayOfm9[7] = new m(this, 0.3333333333333333D, 0.6666666666666666D, 0.6666666666666666D, 1.0D);
    arrayOfm9[8] = new m(this, 0.6666666666666666D, 0.6666666666666666D, 1.0D, 1.0D);
    arrayOfm;[8] = arrayOfm9;
    this.r = arrayOfm;;
    this.s = new k(this);
    a(paramContext);
  }

  public static Bitmap a(String paramString)
  {
    return (Bitmap)c.get(paramString);
  }

  public static Bitmap a(List paramList)
  {
    String str = b(paramList);
    return (Bitmap)c.get(str);
  }

  private void a(Context paramContext)
  {
    this.l = ImageLoader.a(paramContext);
    this.m.r = true;
  }

  private void a(Drawable paramDrawable, String paramString)
  {
    Iterator localIterator = this.f.iterator();
    l locall;
    while (localIterator.hasNext())
    {
      locall = (l)localIterator.next();
      if ((!locall.b.equals(paramString)) || (locall.c))
        continue;
      locall.c = true;
      if (paramDrawable != null)
        break label125;
    }
    label125: for (Drawable localDrawable = this.n; ; localDrawable = paramDrawable)
    {
      locall.a = localDrawable;
      this.i = (-1 + this.i);
      if (paramDrawable == null)
        this.k = true;
      if ((this.i == 0) && (!this.j))
      {
        this.j = true;
        if (Looper.myLooper() != Looper.getMainLooper())
          break;
        c();
      }
      return;
    }
    this.q.sendEmptyMessage(1);
  }

  public static void a(String paramString, Bitmap paramBitmap)
  {
    if (a(paramString) == null)
      c.put(paramString, paramBitmap);
  }

  public static void a(List paramList, Bitmap paramBitmap)
  {
    String str = b(paramList);
    if (a(str) == null)
      c.put(str, paramBitmap);
  }

  private m[] a(int paramInt)
  {
    if (paramInt > 9)
      return this.r[8];
    return this.r[(paramInt - 1)];
  }

  private static String b(List paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (TextUtils.isEmpty(str))
        continue;
      localStringBuilder.append(str).append("|");
    }
    return localStringBuilder.toString();
  }

  private void b()
  {
    Iterator localIterator = this.f.iterator();
    while (localIterator.hasNext())
    {
      l locall = (l)localIterator.next();
      Drawable localDrawable = this.l.a(locall.b, this.s, this.m);
      if (localDrawable == null)
        continue;
      LogUtil.d(a, "direct applyAsyncImage");
      a(localDrawable, locall.b);
    }
  }

  private Bitmap c(List paramList)
  {
    int i1 = getWidth();
    int i2 = getHeight();
    if (i1 == 0);
    for (int i3 = 90; ; i3 = i1)
    {
      if (i2 == 0);
      for (int i4 = 90; ; i4 = i2)
      {
        if (paramList.size() == 0)
          return null;
        Drawable localDrawable1 = (Drawable)paramList.get(0);
        if (localDrawable1 == null)
          localDrawable1 = this.n;
        if (localDrawable1.getOpacity() != -1);
        Bitmap localBitmap;
        for (Bitmap.Config localConfig = Bitmap.Config.ARGB_8888; ; localConfig = Bitmap.Config.RGB_565)
        {
          localBitmap = Bitmap.createBitmap(i3, i4, localConfig);
          LogUtil.d(a, "create bitmap w:" + i3 + " h:" + i4 + " config:" + localConfig + " size:" + paramList.size());
          Canvas localCanvas = new Canvas(localBitmap);
          localCanvas.drawColor(this.p);
          m[] arrayOfm = a(paramList.size());
          for (int i5 = 0; i5 < arrayOfm.length; i5++)
          {
            m localm = arrayOfm[i5];
            Rect localRect = new Rect((int)(localm.a * i3), (int)(localm.b * i4), (int)(localm.c * i3), (int)(localm.d * i4));
            Drawable localDrawable2 = (Drawable)paramList.get(i5);
            if (localDrawable2 == null)
              continue;
            localDrawable2.setBounds(localRect);
            localDrawable2.draw(localCanvas);
          }
        }
        return localBitmap;
      }
    }
  }

  private void c()
  {
    LogUtil.d(a, "notifyAsyncImageLoaded()");
    setImageBitmap(d());
    invalidate();
  }

  private Bitmap d()
  {
    if (this.o == null)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = this.f.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((l)localIterator.next()).a);
      this.o = c(localArrayList);
    }
    if ((this.o != null) && (!TextUtils.isEmpty(this.h)) && (!this.k))
      a(this.h, this.o);
    return this.o;
  }

  @PluginApi(a=7)
  public void setColorBG(int paramInt)
  {
    this.p = paramInt;
  }

  @PluginApi(a=7)
  public void setDefaultIcon(int paramInt)
  {
    setDefaultIcon(getContext().getResources().getDrawable(paramInt));
  }

  @PluginApi(a=7)
  public void setDefaultIcon(Drawable paramDrawable)
  {
    if (this.n != paramDrawable)
      this.n = paramDrawable;
  }

  @PluginApi(a=7)
  public void setImageUrls(List paramList)
  {
    if (this.n != null)
      setImageDrawable(this.n);
    if ((paramList == null) || (paramList.size() == 0));
    while (true)
    {
      return;
      LogUtil.d(a, "setImageUrls:" + paramList);
      String str1 = b(paramList);
      Bitmap localBitmap = a(str1);
      if (localBitmap != null)
      {
        LogUtil.d(a, "get bitmap cache");
        setImageBitmap(localBitmap);
        return;
      }
      if ((this.h == null) || (!this.h.equals(str1)))
        break;
      LogUtil.d(a, "return for the same urls");
      if (this.o == null)
        continue;
      setImageBitmap(this.o);
      return;
    }
    this.f.clear();
    this.g.clear();
    this.h = b(paramList);
    this.i = 0;
    this.j = false;
    this.k = false;
    this.o = null;
    this.g.addAll(paramList);
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      if (TextUtils.isEmpty(str2))
        continue;
      this.i = (1 + this.i);
      this.f.add(new l(this, str2));
    }
    b();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.GridIconImageView
 * JD-Core Version:    0.6.0
 */