package com.tencent.game.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.util.Log;

public class m3eFontHelper
{
  private static Typeface font;
  private static m3eFontHelper instance = new m3eFontHelper();
  private static Paint p;
  private Context context = null;

  public static m3eFontHelper getInstance()
  {
    return instance;
  }

  public void closeFontBmp(FontBmp paramFontBmp)
  {
    paramFontBmp.buffer = new int[0];
    paramFontBmp.w = 0;
    paramFontBmp.h = 0;
  }

  public FontBmp queryChar(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    try
    {
      font = Typeface.create(paramString1, 0);
      p = new Paint(1);
      p.setColor(-16777216);
      p.setTypeface(font);
      if (paramInt2 == 0)
        p.setStyle(Paint.Style.FILL);
      while (true)
      {
        p.setTextSize(paramInt1);
        Paint.FontMetrics localFontMetrics = p.getFontMetrics();
        float f1 = paramInt2 + 0;
        float f2 = -localFontMetrics.top + paramInt2;
        (f2 + localFontMetrics.ascent);
        (f2 + localFontMetrics.descent);
        (f2 + localFontMetrics.bottom - 1.0F);
        float[] arrayOfFloat = new float[1];
        p.getTextWidths(paramString2, arrayOfFloat);
        int i = (int)arrayOfFloat[0] + paramInt2 * 2;
        int j = (int)(localFontMetrics.bottom - localFontMetrics.top) + paramInt2 * 2;
        Log.d("=====", "queryChar : " + paramString1 + " " + i + "  " + j);
        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
        new Canvas(localBitmap).drawText(paramString2, f1, f2, p);
        FontBmp localFontBmp = new FontBmp();
        localFontBmp.w = i;
        localFontBmp.h = j;
        localFontBmp.buffer = new int[localFontBmp.w * localFontBmp.h];
        localBitmap.getPixels(localFontBmp.buffer, 0, localFontBmp.w, 0, 0, localFontBmp.w, localFontBmp.h);
        return localFontBmp;
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(paramInt2);
      }
    }
    catch (Exception localException)
    {
      Log.d("m3ej", "queryChar error " + localException.getMessage());
    }
    return null;
  }

  public void setContext(Context paramContext)
  {
    this.context = paramContext;
  }

  public class FontBmp
  {
    public int[] buffer;
    public int h;
    public int w;

    public FontBmp()
    {
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.m3eFontHelper
 * JD-Core Version:    0.6.0
 */