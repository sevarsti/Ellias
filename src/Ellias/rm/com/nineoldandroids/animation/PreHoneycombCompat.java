package com.nineoldandroids.animation;

import android.view.View;
import com.nineoldandroids.util.FloatProperty;
import com.nineoldandroids.util.IntProperty;
import com.nineoldandroids.util.Property;
import com.nineoldandroids.view.animation.AnimatorProxy;

final class PreHoneycombCompat
{
  static Property<View, Float> ALPHA = new FloatProperty()
  {
    public Float get(View paramView)
    {
      return Float.valueOf(AnimatorProxy.wrap(paramView).getAlpha());
    }

    public void setValue(View paramView, float paramFloat)
    {
      AnimatorProxy.wrap(paramView).setAlpha(paramFloat);
    }
  };
  static Property<View, Float> PIVOT_X = new FloatProperty()
  {
    public Float get(View paramView)
    {
      return Float.valueOf(AnimatorProxy.wrap(paramView).getPivotX());
    }

    public void setValue(View paramView, float paramFloat)
    {
      AnimatorProxy.wrap(paramView).setPivotX(paramFloat);
    }
  };
  static Property<View, Float> PIVOT_Y = new FloatProperty()
  {
    public Float get(View paramView)
    {
      return Float.valueOf(AnimatorProxy.wrap(paramView).getPivotY());
    }

    public void setValue(View paramView, float paramFloat)
    {
      AnimatorProxy.wrap(paramView).setPivotY(paramFloat);
    }
  };
  static Property<View, Float> ROTATION;
  static Property<View, Float> ROTATION_X;
  static Property<View, Float> ROTATION_Y;
  static Property<View, Float> SCALE_X;
  static Property<View, Float> SCALE_Y;
  static Property<View, Integer> SCROLL_X;
  static Property<View, Integer> SCROLL_Y;
  static Property<View, Float> TRANSLATION_X = new FloatProperty()
  {
    public Float get(View paramView)
    {
      return Float.valueOf(AnimatorProxy.wrap(paramView).getTranslationX());
    }

    public void setValue(View paramView, float paramFloat)
    {
      AnimatorProxy.wrap(paramView).setTranslationX(paramFloat);
    }
  };
  static Property<View, Float> TRANSLATION_Y = new FloatProperty()
  {
    public Float get(View paramView)
    {
      return Float.valueOf(AnimatorProxy.wrap(paramView).getTranslationY());
    }

    public void setValue(View paramView, float paramFloat)
    {
      AnimatorProxy.wrap(paramView).setTranslationY(paramFloat);
    }
  };
  static Property<View, Float> X;
  static Property<View, Float> Y;

  static
  {
    ROTATION = new FloatProperty("rotation")
    {
      public Float get(View paramView)
      {
        return Float.valueOf(AnimatorProxy.wrap(paramView).getRotation());
      }

      public void setValue(View paramView, float paramFloat)
      {
        AnimatorProxy.wrap(paramView).setRotation(paramFloat);
      }
    };
    ROTATION_X = new FloatProperty("rotationX")
    {
      public Float get(View paramView)
      {
        return Float.valueOf(AnimatorProxy.wrap(paramView).getRotationX());
      }

      public void setValue(View paramView, float paramFloat)
      {
        AnimatorProxy.wrap(paramView).setRotationX(paramFloat);
      }
    };
    ROTATION_Y = new FloatProperty("rotationY")
    {
      public Float get(View paramView)
      {
        return Float.valueOf(AnimatorProxy.wrap(paramView).getRotationY());
      }

      public void setValue(View paramView, float paramFloat)
      {
        AnimatorProxy.wrap(paramView).setRotationY(paramFloat);
      }
    };
    SCALE_X = new FloatProperty("scaleX")
    {
      public Float get(View paramView)
      {
        return Float.valueOf(AnimatorProxy.wrap(paramView).getScaleX());
      }

      public void setValue(View paramView, float paramFloat)
      {
        AnimatorProxy.wrap(paramView).setScaleX(paramFloat);
      }
    };
    SCALE_Y = new FloatProperty("scaleY")
    {
      public Float get(View paramView)
      {
        return Float.valueOf(AnimatorProxy.wrap(paramView).getScaleY());
      }

      public void setValue(View paramView, float paramFloat)
      {
        AnimatorProxy.wrap(paramView).setScaleY(paramFloat);
      }
    };
    SCROLL_X = new IntProperty("scrollX")
    {
      public Integer get(View paramView)
      {
        return Integer.valueOf(AnimatorProxy.wrap(paramView).getScrollX());
      }

      public void setValue(View paramView, int paramInt)
      {
        AnimatorProxy.wrap(paramView).setScrollX(paramInt);
      }
    };
    SCROLL_Y = new IntProperty("scrollY")
    {
      public Integer get(View paramView)
      {
        return Integer.valueOf(AnimatorProxy.wrap(paramView).getScrollY());
      }

      public void setValue(View paramView, int paramInt)
      {
        AnimatorProxy.wrap(paramView).setScrollY(paramInt);
      }
    };
    X = new FloatProperty("x")
    {
      public Float get(View paramView)
      {
        return Float.valueOf(AnimatorProxy.wrap(paramView).getX());
      }

      public void setValue(View paramView, float paramFloat)
      {
        AnimatorProxy.wrap(paramView).setX(paramFloat);
      }
    };
    Y = new FloatProperty("y")
    {
      public Float get(View paramView)
      {
        return Float.valueOf(AnimatorProxy.wrap(paramView).getY());
      }

      public void setValue(View paramView, float paramFloat)
      {
        AnimatorProxy.wrap(paramView).setY(paramFloat);
      }
    };
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.nineoldandroids.animation.PreHoneycombCompat
 * JD-Core Version:    0.6.0
 */