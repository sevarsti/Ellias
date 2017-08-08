package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;

public class MatrixDrawable extends DrawableContainer
{
  private final j a;
  private final Matrix b = new Matrix();

  public MatrixDrawable(Drawable paramDrawable)
  {
    this(paramDrawable, null);
  }

  public MatrixDrawable(Drawable paramDrawable, Matrix paramMatrix)
  {
    this.a = new j(paramDrawable, this);
    a(this.a);
    a(paramMatrix);
  }

  private MatrixDrawable(j paramj, Resources paramResources)
  {
    this.a = new j(paramj, this, paramResources);
    a(this.a);
  }

  public void a(Matrix paramMatrix)
  {
    if ((paramMatrix != null) && (paramMatrix.isIdentity()))
      paramMatrix = null;
    if (((paramMatrix == null) && (!this.b.isIdentity())) || ((paramMatrix != null) && (!this.b.equals(paramMatrix))))
    {
      this.b.set(paramMatrix);
      invalidateSelf();
    }
  }

  public Matrix b()
  {
    return this.b;
  }

  public void draw(Canvas paramCanvas)
  {
    if (this.b.isIdentity())
    {
      super.draw(paramCanvas);
      return;
    }
    int i = paramCanvas.getSaveCount();
    paramCanvas.save();
    paramCanvas.concat(this.b);
    super.draw(paramCanvas);
    paramCanvas.restoreToCount(i);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.MatrixDrawable
 * JD-Core Version:    0.6.0
 */