package com.nineoldandroids.animation;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ValueAnimator extends Animator
{
  static final int ANIMATION_FRAME = 1;
  static final int ANIMATION_START = 0;
  private static final long DEFAULT_FRAME_DELAY = 10L;
  public static final int INFINITE = -1;
  public static final int RESTART = 1;
  public static final int REVERSE = 2;
  static final int RUNNING = 1;
  static final int SEEKED = 2;
  static final int STOPPED;
  private static ThreadLocal<AnimationHandler> sAnimationHandler = new ThreadLocal();
  private static final ThreadLocal<ArrayList<ValueAnimator>> sAnimations = new ThreadLocal()
  {
    protected ArrayList<ValueAnimator> initialValue()
    {
      return new ArrayList();
    }
  };
  private static final Interpolator sDefaultInterpolator;
  private static final ThreadLocal<ArrayList<ValueAnimator>> sDelayedAnims;
  private static final ThreadLocal<ArrayList<ValueAnimator>> sEndingAnims;
  private static final TypeEvaluator sFloatEvaluator;
  private static long sFrameDelay;
  private static final TypeEvaluator sIntEvaluator;
  private static final ThreadLocal<ArrayList<ValueAnimator>> sPendingAnimations = new ThreadLocal()
  {
    protected ArrayList<ValueAnimator> initialValue()
    {
      return new ArrayList();
    }
  };
  private static final ThreadLocal<ArrayList<ValueAnimator>> sReadyAnims;
  private float mCurrentFraction = 0.0F;
  private int mCurrentIteration = 0;
  private long mDelayStartTime;
  private long mDuration = 300L;
  boolean mInitialized = false;
  private Interpolator mInterpolator = sDefaultInterpolator;
  private boolean mPlayingBackwards = false;
  int mPlayingState = 0;
  private int mRepeatCount = 0;
  private int mRepeatMode = 1;
  private boolean mRunning = false;
  long mSeekTime = -1L;
  private long mStartDelay = 0L;
  long mStartTime;
  private boolean mStarted = false;
  private boolean mStartedDelay = false;
  private ArrayList<AnimatorUpdateListener> mUpdateListeners = null;
  PropertyValuesHolder[] mValues;
  HashMap<String, PropertyValuesHolder> mValuesMap;

  static
  {
    sDelayedAnims = new ThreadLocal()
    {
      protected ArrayList<ValueAnimator> initialValue()
      {
        return new ArrayList();
      }
    };
    sEndingAnims = new ThreadLocal()
    {
      protected ArrayList<ValueAnimator> initialValue()
      {
        return new ArrayList();
      }
    };
    sReadyAnims = new ThreadLocal()
    {
      protected ArrayList<ValueAnimator> initialValue()
      {
        return new ArrayList();
      }
    };
    sDefaultInterpolator = new AccelerateDecelerateInterpolator();
    sIntEvaluator = new IntEvaluator();
    sFloatEvaluator = new FloatEvaluator();
    sFrameDelay = 10L;
  }

  public static void clearAllAnimations()
  {
    ((ArrayList)sAnimations.get()).clear();
    ((ArrayList)sPendingAnimations.get()).clear();
    ((ArrayList)sDelayedAnims.get()).clear();
  }

  private boolean delayedAnimationFrame(long paramLong)
  {
    if (!this.mStartedDelay)
    {
      this.mStartedDelay = true;
      this.mDelayStartTime = paramLong;
    }
    long l;
    do
    {
      return false;
      l = paramLong - this.mDelayStartTime;
    }
    while (l <= this.mStartDelay);
    this.mStartTime = (paramLong - (l - this.mStartDelay));
    this.mPlayingState = 1;
    return true;
  }

  private void endAnimation()
  {
    ((ArrayList)sAnimations.get()).remove(this);
    ((ArrayList)sPendingAnimations.get()).remove(this);
    ((ArrayList)sDelayedAnims.get()).remove(this);
    this.mPlayingState = 0;
    if ((this.mRunning) && (this.mListeners != null))
    {
      ArrayList localArrayList = (ArrayList)this.mListeners.clone();
      int i = localArrayList.size();
      for (int j = 0; j < i; j++)
        ((Animator.AnimatorListener)localArrayList.get(j)).onAnimationEnd(this);
    }
    this.mRunning = false;
    this.mStarted = false;
  }

  public static int getCurrentAnimationsCount()
  {
    return ((ArrayList)sAnimations.get()).size();
  }

  public static long getFrameDelay()
  {
    return sFrameDelay;
  }

  public static ValueAnimator ofFloat(float[] paramArrayOfFloat)
  {
    ValueAnimator localValueAnimator = new ValueAnimator();
    localValueAnimator.setFloatValues(paramArrayOfFloat);
    return localValueAnimator;
  }

  public static ValueAnimator ofInt(int[] paramArrayOfInt)
  {
    ValueAnimator localValueAnimator = new ValueAnimator();
    localValueAnimator.setIntValues(paramArrayOfInt);
    return localValueAnimator;
  }

  public static ValueAnimator ofObject(TypeEvaluator paramTypeEvaluator, Object[] paramArrayOfObject)
  {
    ValueAnimator localValueAnimator = new ValueAnimator();
    localValueAnimator.setObjectValues(paramArrayOfObject);
    localValueAnimator.setEvaluator(paramTypeEvaluator);
    return localValueAnimator;
  }

  public static ValueAnimator ofPropertyValuesHolder(PropertyValuesHolder[] paramArrayOfPropertyValuesHolder)
  {
    ValueAnimator localValueAnimator = new ValueAnimator();
    localValueAnimator.setValues(paramArrayOfPropertyValuesHolder);
    return localValueAnimator;
  }

  public static void setFrameDelay(long paramLong)
  {
    sFrameDelay = paramLong;
  }

  private void start(boolean paramBoolean)
  {
    if (Looper.myLooper() == null)
      throw new AndroidRuntimeException("Animators may only be run on Looper threads");
    this.mPlayingBackwards = paramBoolean;
    this.mCurrentIteration = 0;
    this.mPlayingState = 0;
    this.mStarted = true;
    this.mStartedDelay = false;
    ((ArrayList)sPendingAnimations.get()).add(this);
    if (this.mStartDelay == 0L)
    {
      setCurrentPlayTime(getCurrentPlayTime());
      this.mPlayingState = 0;
      this.mRunning = true;
      if (this.mListeners != null)
      {
        ArrayList localArrayList = (ArrayList)this.mListeners.clone();
        int i = localArrayList.size();
        for (int j = 0; j < i; j++)
          ((Animator.AnimatorListener)localArrayList.get(j)).onAnimationStart(this);
      }
    }
    AnimationHandler localAnimationHandler = (AnimationHandler)sAnimationHandler.get();
    if (localAnimationHandler == null)
    {
      localAnimationHandler = new AnimationHandler(null);
      sAnimationHandler.set(localAnimationHandler);
    }
    localAnimationHandler.sendEmptyMessage(0);
  }

  private void startAnimation()
  {
    initAnimation();
    ((ArrayList)sAnimations.get()).add(this);
    if ((this.mStartDelay > 0L) && (this.mListeners != null))
    {
      ArrayList localArrayList = (ArrayList)this.mListeners.clone();
      int i = localArrayList.size();
      for (int j = 0; j < i; j++)
        ((Animator.AnimatorListener)localArrayList.get(j)).onAnimationStart(this);
    }
  }

  public void addUpdateListener(AnimatorUpdateListener paramAnimatorUpdateListener)
  {
    if (this.mUpdateListeners == null)
      this.mUpdateListeners = new ArrayList();
    this.mUpdateListeners.add(paramAnimatorUpdateListener);
  }

  void animateValue(float paramFloat)
  {
    float f = this.mInterpolator.getInterpolation(paramFloat);
    this.mCurrentFraction = f;
    int i = this.mValues.length;
    for (int j = 0; j < i; j++)
      this.mValues[j].calculateValue(f);
    if (this.mUpdateListeners != null)
    {
      int k = this.mUpdateListeners.size();
      for (int m = 0; m < k; m++)
        ((AnimatorUpdateListener)this.mUpdateListeners.get(m)).onAnimationUpdate(this);
    }
  }

  boolean animationFrame(long paramLong)
  {
    if (this.mPlayingState == 0)
    {
      this.mPlayingState = 1;
      if (this.mSeekTime >= 0L)
        break label54;
      this.mStartTime = paramLong;
    }
    while (true)
      switch (this.mPlayingState)
      {
      default:
        return false;
        label54: this.mStartTime = (paramLong - this.mSeekTime);
        this.mSeekTime = -1L;
      case 1:
      case 2:
      }
    float f;
    if (this.mDuration > 0L)
      f = (float)(paramLong - this.mStartTime) / (float)this.mDuration;
    int i;
    boolean bool2;
    while (true)
    {
      boolean bool1 = f < 1.0F;
      i = 0;
      if (bool1)
        break;
      if ((this.mCurrentIteration >= this.mRepeatCount) && (this.mRepeatCount != -1))
        break label261;
      if (this.mListeners != null)
      {
        int j = this.mListeners.size();
        int k = 0;
        while (true)
          if (k < j)
          {
            ((Animator.AnimatorListener)this.mListeners.get(k)).onAnimationRepeat(this);
            k++;
            continue;
            f = 1.0F;
            break;
          }
      }
      if (this.mRepeatMode == 2)
      {
        if (!this.mPlayingBackwards)
          break label255;
        bool2 = false;
        this.mPlayingBackwards = bool2;
      }
      this.mCurrentIteration += (int)f;
      f %= 1.0F;
      this.mStartTime += this.mDuration;
    }
    while (true)
    {
      if (this.mPlayingBackwards)
        f = 1.0F - f;
      animateValue(f);
      return i;
      label255: bool2 = true;
      break;
      label261: i = 1;
      f = Math.min(f, 1.0F);
    }
  }

  public void cancel()
  {
    if ((this.mPlayingState != 0) || (((ArrayList)sPendingAnimations.get()).contains(this)) || (((ArrayList)sDelayedAnims.get()).contains(this)))
    {
      if ((this.mRunning) && (this.mListeners != null))
      {
        Iterator localIterator = ((ArrayList)this.mListeners.clone()).iterator();
        while (localIterator.hasNext())
          ((Animator.AnimatorListener)localIterator.next()).onAnimationCancel(this);
      }
      endAnimation();
    }
  }

  public ValueAnimator clone()
  {
    ValueAnimator localValueAnimator = (ValueAnimator)super.clone();
    if (this.mUpdateListeners != null)
    {
      ArrayList localArrayList = this.mUpdateListeners;
      localValueAnimator.mUpdateListeners = new ArrayList();
      int k = localArrayList.size();
      for (int m = 0; m < k; m++)
        localValueAnimator.mUpdateListeners.add(localArrayList.get(m));
    }
    localValueAnimator.mSeekTime = -1L;
    localValueAnimator.mPlayingBackwards = false;
    localValueAnimator.mCurrentIteration = 0;
    localValueAnimator.mInitialized = false;
    localValueAnimator.mPlayingState = 0;
    localValueAnimator.mStartedDelay = false;
    PropertyValuesHolder[] arrayOfPropertyValuesHolder = this.mValues;
    if (arrayOfPropertyValuesHolder != null)
    {
      int i = arrayOfPropertyValuesHolder.length;
      localValueAnimator.mValues = new PropertyValuesHolder[i];
      localValueAnimator.mValuesMap = new HashMap(i);
      for (int j = 0; j < i; j++)
      {
        PropertyValuesHolder localPropertyValuesHolder = arrayOfPropertyValuesHolder[j].clone();
        localValueAnimator.mValues[j] = localPropertyValuesHolder;
        localValueAnimator.mValuesMap.put(localPropertyValuesHolder.getPropertyName(), localPropertyValuesHolder);
      }
    }
    return localValueAnimator;
  }

  public void end()
  {
    if ((!((ArrayList)sAnimations.get()).contains(this)) && (!((ArrayList)sPendingAnimations.get()).contains(this)))
    {
      this.mStartedDelay = false;
      startAnimation();
      if ((this.mRepeatCount <= 0) || ((0x1 & this.mRepeatCount) != 1))
        break label82;
      animateValue(0.0F);
    }
    while (true)
    {
      endAnimation();
      return;
      if (this.mInitialized)
        break;
      initAnimation();
      break;
      label82: animateValue(1.0F);
    }
  }

  public float getAnimatedFraction()
  {
    return this.mCurrentFraction;
  }

  public Object getAnimatedValue()
  {
    if ((this.mValues != null) && (this.mValues.length > 0))
      return this.mValues[0].getAnimatedValue();
    return null;
  }

  public Object getAnimatedValue(String paramString)
  {
    PropertyValuesHolder localPropertyValuesHolder = (PropertyValuesHolder)this.mValuesMap.get(paramString);
    if (localPropertyValuesHolder != null)
      return localPropertyValuesHolder.getAnimatedValue();
    return null;
  }

  public long getCurrentPlayTime()
  {
    if ((!this.mInitialized) || (this.mPlayingState == 0))
      return 0L;
    return AnimationUtils.currentAnimationTimeMillis() - this.mStartTime;
  }

  public long getDuration()
  {
    return this.mDuration;
  }

  public Interpolator getInterpolator()
  {
    return this.mInterpolator;
  }

  public int getRepeatCount()
  {
    return this.mRepeatCount;
  }

  public int getRepeatMode()
  {
    return this.mRepeatMode;
  }

  public long getStartDelay()
  {
    return this.mStartDelay;
  }

  public PropertyValuesHolder[] getValues()
  {
    return this.mValues;
  }

  void initAnimation()
  {
    if (!this.mInitialized)
    {
      int i = this.mValues.length;
      for (int j = 0; j < i; j++)
        this.mValues[j].init();
      this.mInitialized = true;
    }
  }

  public boolean isRunning()
  {
    return (this.mPlayingState == 1) || (this.mRunning);
  }

  public boolean isStarted()
  {
    return this.mStarted;
  }

  public void removeAllUpdateListeners()
  {
    if (this.mUpdateListeners == null)
      return;
    this.mUpdateListeners.clear();
    this.mUpdateListeners = null;
  }

  public void removeUpdateListener(AnimatorUpdateListener paramAnimatorUpdateListener)
  {
    if (this.mUpdateListeners == null);
    do
    {
      return;
      this.mUpdateListeners.remove(paramAnimatorUpdateListener);
    }
    while (this.mUpdateListeners.size() != 0);
    this.mUpdateListeners = null;
  }

  public void reverse()
  {
    if (!this.mPlayingBackwards);
    for (boolean bool = true; ; bool = false)
    {
      this.mPlayingBackwards = bool;
      if (this.mPlayingState != 1)
        break;
      long l1 = AnimationUtils.currentAnimationTimeMillis();
      long l2 = l1 - this.mStartTime;
      this.mStartTime = (l1 - (this.mDuration - l2));
      return;
    }
    start(true);
  }

  public void setCurrentPlayTime(long paramLong)
  {
    initAnimation();
    long l = AnimationUtils.currentAnimationTimeMillis();
    if (this.mPlayingState != 1)
    {
      this.mSeekTime = paramLong;
      this.mPlayingState = 2;
    }
    this.mStartTime = (l - paramLong);
    animationFrame(l);
  }

  public ValueAnimator setDuration(long paramLong)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("Animators cannot have negative duration: " + paramLong);
    this.mDuration = paramLong;
    return this;
  }

  public void setEvaluator(TypeEvaluator paramTypeEvaluator)
  {
    if ((paramTypeEvaluator != null) && (this.mValues != null) && (this.mValues.length > 0))
      this.mValues[0].setEvaluator(paramTypeEvaluator);
  }

  public void setFloatValues(float[] paramArrayOfFloat)
  {
    if ((paramArrayOfFloat == null) || (paramArrayOfFloat.length == 0))
      return;
    if ((this.mValues == null) || (this.mValues.length == 0))
    {
      PropertyValuesHolder[] arrayOfPropertyValuesHolder = new PropertyValuesHolder[1];
      arrayOfPropertyValuesHolder[0] = PropertyValuesHolder.ofFloat("", paramArrayOfFloat);
      setValues(arrayOfPropertyValuesHolder);
    }
    while (true)
    {
      this.mInitialized = false;
      return;
      this.mValues[0].setFloatValues(paramArrayOfFloat);
    }
  }

  public void setIntValues(int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0))
      return;
    if ((this.mValues == null) || (this.mValues.length == 0))
    {
      PropertyValuesHolder[] arrayOfPropertyValuesHolder = new PropertyValuesHolder[1];
      arrayOfPropertyValuesHolder[0] = PropertyValuesHolder.ofInt("", paramArrayOfInt);
      setValues(arrayOfPropertyValuesHolder);
    }
    while (true)
    {
      this.mInitialized = false;
      return;
      this.mValues[0].setIntValues(paramArrayOfInt);
    }
  }

  public void setInterpolator(Interpolator paramInterpolator)
  {
    if (paramInterpolator != null)
    {
      this.mInterpolator = paramInterpolator;
      return;
    }
    this.mInterpolator = new LinearInterpolator();
  }

  public void setObjectValues(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0))
      return;
    if ((this.mValues == null) || (this.mValues.length == 0))
    {
      PropertyValuesHolder[] arrayOfPropertyValuesHolder = new PropertyValuesHolder[1];
      arrayOfPropertyValuesHolder[0] = PropertyValuesHolder.ofObject("", (TypeEvaluator)null, paramArrayOfObject);
      setValues(arrayOfPropertyValuesHolder);
    }
    while (true)
    {
      this.mInitialized = false;
      return;
      this.mValues[0].setObjectValues(paramArrayOfObject);
    }
  }

  public void setRepeatCount(int paramInt)
  {
    this.mRepeatCount = paramInt;
  }

  public void setRepeatMode(int paramInt)
  {
    this.mRepeatMode = paramInt;
  }

  public void setStartDelay(long paramLong)
  {
    this.mStartDelay = paramLong;
  }

  public void setValues(PropertyValuesHolder[] paramArrayOfPropertyValuesHolder)
  {
    int i = paramArrayOfPropertyValuesHolder.length;
    this.mValues = paramArrayOfPropertyValuesHolder;
    this.mValuesMap = new HashMap(i);
    for (int j = 0; j < i; j++)
    {
      PropertyValuesHolder localPropertyValuesHolder = paramArrayOfPropertyValuesHolder[j];
      this.mValuesMap.put(localPropertyValuesHolder.getPropertyName(), localPropertyValuesHolder);
    }
    this.mInitialized = false;
  }

  public void start()
  {
    start(false);
  }

  public String toString()
  {
    String str = "ValueAnimator@" + Integer.toHexString(hashCode());
    if (this.mValues != null)
      for (int i = 0; i < this.mValues.length; i++)
        str = str + "\n    " + this.mValues[i].toString();
    return str;
  }

  private static class AnimationHandler extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      int i = 1;
      ArrayList localArrayList1 = (ArrayList)ValueAnimator.sAnimations.get();
      ArrayList localArrayList2 = (ArrayList)ValueAnimator.sDelayedAnims.get();
      switch (paramMessage.what)
      {
      default:
      case 0:
      case 1:
      }
      label110: long l;
      label150: 
      do
      {
        return;
        ArrayList localArrayList5 = (ArrayList)ValueAnimator.sPendingAnimations.get();
        if ((localArrayList1.size() > 0) || (localArrayList2.size() > 0))
          i = 0;
        if (localArrayList5.size() > 0)
        {
          ArrayList localArrayList6 = (ArrayList)localArrayList5.clone();
          localArrayList5.clear();
          int i4 = localArrayList6.size();
          int i5 = 0;
          ValueAnimator localValueAnimator4;
          if (i5 < i4)
          {
            localValueAnimator4 = (ValueAnimator)localArrayList6.get(i5);
            if (localValueAnimator4.mStartDelay != 0L)
              break label150;
            localValueAnimator4.startAnimation();
          }
          while (true)
          {
            i5++;
            break label110;
            break;
            localArrayList2.add(localValueAnimator4);
          }
        }
        l = AnimationUtils.currentAnimationTimeMillis();
        ArrayList localArrayList3 = (ArrayList)ValueAnimator.sReadyAnims.get();
        ArrayList localArrayList4 = (ArrayList)ValueAnimator.sEndingAnims.get();
        int j = localArrayList2.size();
        for (int k = 0; k < j; k++)
        {
          ValueAnimator localValueAnimator3 = (ValueAnimator)localArrayList2.get(k);
          if (!localValueAnimator3.delayedAnimationFrame(l))
            continue;
          localArrayList3.add(localValueAnimator3);
        }
        int m = localArrayList3.size();
        if (m > 0)
        {
          for (int i3 = 0; i3 < m; i3++)
          {
            ValueAnimator localValueAnimator2 = (ValueAnimator)localArrayList3.get(i3);
            localValueAnimator2.startAnimation();
            ValueAnimator.access$802(localValueAnimator2, true);
            localArrayList2.remove(localValueAnimator2);
          }
          localArrayList3.clear();
        }
        int n = localArrayList1.size();
        int i1 = 0;
        while (i1 < n)
        {
          ValueAnimator localValueAnimator1 = (ValueAnimator)localArrayList1.get(i1);
          if (localValueAnimator1.animationFrame(l))
            localArrayList4.add(localValueAnimator1);
          if (localArrayList1.size() == n)
          {
            i1++;
            continue;
          }
          n--;
          localArrayList4.remove(localValueAnimator1);
        }
        if (localArrayList4.size() <= 0)
          continue;
        for (int i2 = 0; i2 < localArrayList4.size(); i2++)
          ((ValueAnimator)localArrayList4.get(i2)).endAnimation();
        localArrayList4.clear();
      }
      while ((i == 0) || ((localArrayList1.isEmpty()) && (localArrayList2.isEmpty())));
      sendEmptyMessageDelayed(1, Math.max(0L, ValueAnimator.sFrameDelay - (AnimationUtils.currentAnimationTimeMillis() - l)));
    }
  }

  public static abstract interface AnimatorUpdateListener
  {
    public abstract void onAnimationUpdate(ValueAnimator paramValueAnimator);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.nineoldandroids.animation.ValueAnimator
 * JD-Core Version:    0.6.0
 */