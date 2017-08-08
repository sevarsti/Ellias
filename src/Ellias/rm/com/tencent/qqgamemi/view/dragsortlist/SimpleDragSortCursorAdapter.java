package com.tencent.qqgamemi.view.dragsortlist;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleDragSortCursorAdapter extends ResourceDragSortCursorAdapter
{
  protected int[] b;
  protected int[] c;
  String[] d;
  private int e = -1;
  private SimpleDragSortCursorAdapter.CursorToStringConverter f;
  private SimpleDragSortCursorAdapter.ViewBinder g;

  @Deprecated
  public SimpleDragSortCursorAdapter(Context paramContext, int paramInt, Cursor paramCursor, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    super(paramContext, paramInt, paramCursor);
    this.c = paramArrayOfInt;
    this.d = paramArrayOfString;
    a(paramCursor, paramArrayOfString);
  }

  public SimpleDragSortCursorAdapter(Context paramContext, int paramInt1, Cursor paramCursor, String[] paramArrayOfString, int[] paramArrayOfInt, int paramInt2)
  {
    super(paramContext, paramInt1, paramCursor, paramInt2);
    this.c = paramArrayOfInt;
    this.d = paramArrayOfString;
    a(paramCursor, paramArrayOfString);
  }

  private void a(Cursor paramCursor, String[] paramArrayOfString)
  {
    if (paramCursor != null)
    {
      int i = paramArrayOfString.length;
      if ((this.b == null) || (this.b.length != i))
        this.b = new int[i];
      for (int j = 0; j < i; j++)
        this.b[j] = paramCursor.getColumnIndexOrThrow(paramArrayOfString[j]);
    }
    this.b = null;
  }

  public void a(Cursor paramCursor, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    this.d = paramArrayOfString;
    this.c = paramArrayOfInt;
    a(paramCursor, this.d);
    super.changeCursor(paramCursor);
  }

  public void a(ImageView paramImageView, String paramString)
  {
    try
    {
      paramImageView.setImageResource(Integer.parseInt(paramString));
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      paramImageView.setImageURI(Uri.parse(paramString));
    }
  }

  public void a(TextView paramTextView, String paramString)
  {
    paramTextView.setText(paramString);
  }

  public void a(SimpleDragSortCursorAdapter.CursorToStringConverter paramCursorToStringConverter)
  {
    this.f = paramCursorToStringConverter;
  }

  public void a(SimpleDragSortCursorAdapter.ViewBinder paramViewBinder)
  {
    this.g = paramViewBinder;
  }

  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    SimpleDragSortCursorAdapter.ViewBinder localViewBinder = this.g;
    int i = this.c.length;
    int[] arrayOfInt1 = this.b;
    int[] arrayOfInt2 = this.c;
    int j = 0;
    View localView;
    if (j < i)
    {
      localView = paramView.findViewById(arrayOfInt2[j]);
      if (localView != null)
        if (localViewBinder == null)
          break label187;
    }
    label147: label187: for (boolean bool = localViewBinder.a(localView, paramCursor, arrayOfInt1[j]); ; bool = false)
    {
      String str;
      if (!bool)
      {
        str = paramCursor.getString(arrayOfInt1[j]);
        if (str == null)
          str = "";
        if (!(localView instanceof TextView))
          break label125;
        a((TextView)localView, str);
      }
      while (true)
      {
        j++;
        break;
        label125: if (!(localView instanceof ImageView))
          break label147;
        a((ImageView)localView, str);
      }
      throw new IllegalStateException(localView.getClass().getName() + " is not a " + " view that can be bounds by this SimpleCursorAdapter");
      return;
    }
  }

  public SimpleDragSortCursorAdapter.ViewBinder c()
  {
    return this.g;
  }

  public CharSequence convertToString(Cursor paramCursor)
  {
    if (this.f != null)
      return this.f.a(paramCursor);
    if (this.e > -1)
      return paramCursor.getString(this.e);
    return super.convertToString(paramCursor);
  }

  public int d()
  {
    return this.e;
  }

  public SimpleDragSortCursorAdapter.CursorToStringConverter e()
  {
    return this.f;
  }

  public void f(int paramInt)
  {
    this.e = paramInt;
  }

  public Cursor swapCursor(Cursor paramCursor)
  {
    a(paramCursor, this.d);
    return super.swapCursor(paramCursor);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.SimpleDragSortCursorAdapter
 * JD-Core Version:    0.6.0
 */