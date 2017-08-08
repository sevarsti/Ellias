package android.support.v4.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.LevelListDrawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public class ActionBarDrawerToggle
  implements DrawerLayout.DrawerListener
{
  private static final int ID_HOME = 16908332;
  private static final ActionBarDrawerToggleImpl IMPL;
  private static final float TOGGLE_DRAWABLE_OFFSET = 0.3333333F;
  private final Activity mActivity;
  private final Delegate mActivityImpl;
  private final int mCloseDrawerContentDescRes;
  private Drawable mDrawerImage;
  private final int mDrawerImageResource;
  private boolean mDrawerIndicatorEnabled = true;
  private final DrawerLayout mDrawerLayout;
  private final int mOpenDrawerContentDescRes;
  private Object mSetIndicatorInfo;
  private SlideDrawable mSlider;
  private Drawable mThemeImage;

  static
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      IMPL = new ActionBarDrawerToggleImplHC(null);
      return;
    }
    IMPL = new ActionBarDrawerToggleImplBase(null);
  }

  public ActionBarDrawerToggle(Activity paramActivity, DrawerLayout paramDrawerLayout, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mActivity = paramActivity;
    if ((paramActivity instanceof DelegateProvider));
    for (this.mActivityImpl = ((DelegateProvider)paramActivity).getDrawerToggleDelegate(); ; this.mActivityImpl = null)
    {
      this.mDrawerLayout = paramDrawerLayout;
      this.mDrawerImageResource = paramInt1;
      this.mOpenDrawerContentDescRes = paramInt2;
      this.mCloseDrawerContentDescRes = paramInt3;
      this.mThemeImage = getThemeUpIndicator();
      this.mDrawerImage = paramActivity.getResources().getDrawable(paramInt1);
      this.mSlider = new SlideDrawable(this.mDrawerImage, null);
      this.mSlider.setOffset(0.3333333F);
      return;
    }
  }

  Drawable getThemeUpIndicator()
  {
    if (this.mActivityImpl != null)
      return this.mActivityImpl.getThemeUpIndicator();
    return IMPL.getThemeUpIndicator(this.mActivity);
  }

  public boolean isDrawerIndicatorEnabled()
  {
    return this.mDrawerIndicatorEnabled;
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    this.mThemeImage = getThemeUpIndicator();
    this.mDrawerImage = this.mActivity.getResources().getDrawable(this.mDrawerImageResource);
    syncState();
  }

  public void onDrawerClosed(View paramView)
  {
    this.mSlider.setPosition(0.0F);
    if (this.mDrawerIndicatorEnabled)
      setActionBarDescription(this.mOpenDrawerContentDescRes);
  }

  public void onDrawerOpened(View paramView)
  {
    this.mSlider.setPosition(1.0F);
    if (this.mDrawerIndicatorEnabled)
      setActionBarDescription(this.mCloseDrawerContentDescRes);
  }

  public void onDrawerSlide(View paramView, float paramFloat)
  {
    float f1 = this.mSlider.getPosition();
    float f2;
    if (paramFloat > 0.5F)
      f2 = Math.max(f1, 2.0F * Math.max(0.0F, paramFloat - 0.5F));
    while (true)
    {
      this.mSlider.setPosition(f2);
      return;
      f2 = Math.min(f1, paramFloat * 2.0F);
    }
  }

  public void onDrawerStateChanged(int paramInt)
  {
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if ((paramMenuItem != null) && (paramMenuItem.getItemId() == 16908332) && (this.mDrawerIndicatorEnabled))
    {
      if (this.mDrawerLayout.isDrawerVisible(8388611))
        this.mDrawerLayout.closeDrawer(8388611);
      while (true)
      {
        return true;
        this.mDrawerLayout.openDrawer(8388611);
      }
    }
    return false;
  }

  void setActionBarDescription(int paramInt)
  {
    if (this.mActivityImpl != null)
    {
      this.mActivityImpl.setActionBarDescription(paramInt);
      return;
    }
    this.mSetIndicatorInfo = IMPL.setActionBarDescription(this.mSetIndicatorInfo, this.mActivity, paramInt);
  }

  void setActionBarUpIndicator(Drawable paramDrawable, int paramInt)
  {
    if (this.mActivityImpl != null)
    {
      this.mActivityImpl.setActionBarUpIndicator(paramDrawable, paramInt);
      return;
    }
    this.mSetIndicatorInfo = IMPL.setActionBarUpIndicator(this.mSetIndicatorInfo, this.mActivity, paramDrawable, paramInt);
  }

  public void setDrawerIndicatorEnabled(boolean paramBoolean)
  {
    int i;
    if (paramBoolean != this.mDrawerIndicatorEnabled)
    {
      if (!paramBoolean)
        break label54;
      SlideDrawable localSlideDrawable = this.mSlider;
      if (!this.mDrawerLayout.isDrawerOpen(8388611))
        break label46;
      i = this.mCloseDrawerContentDescRes;
      setActionBarUpIndicator(localSlideDrawable, i);
    }
    while (true)
    {
      this.mDrawerIndicatorEnabled = paramBoolean;
      return;
      label46: i = this.mOpenDrawerContentDescRes;
      break;
      label54: setActionBarUpIndicator(this.mThemeImage, 0);
    }
  }

  public void syncState()
  {
    SlideDrawable localSlideDrawable;
    if (this.mDrawerLayout.isDrawerOpen(8388611))
    {
      this.mSlider.setPosition(1.0F);
      if (this.mDrawerIndicatorEnabled)
      {
        localSlideDrawable = this.mSlider;
        if (!this.mDrawerLayout.isDrawerOpen(8388611))
          break label67;
      }
    }
    label67: for (int i = this.mCloseDrawerContentDescRes; ; i = this.mOpenDrawerContentDescRes)
    {
      setActionBarUpIndicator(localSlideDrawable, i);
      return;
      this.mSlider.setPosition(0.0F);
      break;
    }
  }

  private static abstract interface ActionBarDrawerToggleImpl
  {
    public abstract Drawable getThemeUpIndicator(Activity paramActivity);

    public abstract Object setActionBarDescription(Object paramObject, Activity paramActivity, int paramInt);

    public abstract Object setActionBarUpIndicator(Object paramObject, Activity paramActivity, Drawable paramDrawable, int paramInt);
  }

  private static class ActionBarDrawerToggleImplBase
    implements ActionBarDrawerToggle.ActionBarDrawerToggleImpl
  {
    public Drawable getThemeUpIndicator(Activity paramActivity)
    {
      return null;
    }

    public Object setActionBarDescription(Object paramObject, Activity paramActivity, int paramInt)
    {
      return paramObject;
    }

    public Object setActionBarUpIndicator(Object paramObject, Activity paramActivity, Drawable paramDrawable, int paramInt)
    {
      return paramObject;
    }
  }

  private static class ActionBarDrawerToggleImplHC
    implements ActionBarDrawerToggle.ActionBarDrawerToggleImpl
  {
    public Drawable getThemeUpIndicator(Activity paramActivity)
    {
      return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(paramActivity);
    }

    public Object setActionBarDescription(Object paramObject, Activity paramActivity, int paramInt)
    {
      return ActionBarDrawerToggleHoneycomb.setActionBarDescription(paramObject, paramActivity, paramInt);
    }

    public Object setActionBarUpIndicator(Object paramObject, Activity paramActivity, Drawable paramDrawable, int paramInt)
    {
      return ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(paramObject, paramActivity, paramDrawable, paramInt);
    }
  }

  public static abstract interface Delegate
  {
    public abstract Drawable getThemeUpIndicator();

    public abstract void setActionBarDescription(int paramInt);

    public abstract void setActionBarUpIndicator(Drawable paramDrawable, int paramInt);
  }

  public static abstract interface DelegateProvider
  {
    public abstract ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();
  }

  private class SlideDrawable extends LevelListDrawable
    implements Drawable.Callback
  {
    private final boolean mHasMirroring;
    private float mOffset;
    private float mPosition;
    private final Rect mTmpRect;

    private SlideDrawable(Drawable arg2)
    {
      if (Build.VERSION.SDK_INT > 18);
      for (boolean bool = true; ; bool = false)
      {
        this.mHasMirroring = bool;
        this.mTmpRect = new Rect();
        Drawable localDrawable;
        if (DrawableCompat.isAutoMirrored(localDrawable))
          DrawableCompat.setAutoMirrored(this, true);
        addLevel(0, 0, localDrawable);
        return;
      }
    }

    public void draw(Canvas paramCanvas)
    {
      int i = 1;
      copyBounds(this.mTmpRect);
      paramCanvas.save();
      if (ViewCompat.getLayoutDirection(ActionBarDrawerToggle.this.mActivity.getWindow().getDecorView()) == i);
      for (int j = i; ; j = 0)
      {
        if (j != 0)
          i = -1;
        int k = this.mTmpRect.width();
        paramCanvas.translate(-this.mOffset * k * this.mPosition * i, 0.0F);
        if ((j != 0) && (!this.mHasMirroring))
        {
          paramCanvas.translate(k, 0.0F);
          paramCanvas.scale(-1.0F, 1.0F);
        }
        super.draw(paramCanvas);
        paramCanvas.restore();
        return;
      }
    }

    public float getPosition()
    {
      return this.mPosition;
    }

    public void setOffset(float paramFloat)
    {
      this.mOffset = paramFloat;
      invalidateSelf();
    }

    public void setPosition(float paramFloat)
    {
      this.mPosition = paramFloat;
      invalidateSelf();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     android.support.v4.app.ActionBarDrawerToggle
 * JD-Core Version:    0.6.0
 */