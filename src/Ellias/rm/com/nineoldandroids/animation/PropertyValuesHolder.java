package com.nineoldandroids.animation;

import android.util.Log;
import com.nineoldandroids.util.FloatProperty;
import com.nineoldandroids.util.IntProperty;
import com.nineoldandroids.util.Property;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class PropertyValuesHolder
  implements Cloneable
{
  private static Class[] DOUBLE_VARIANTS;
  private static Class[] FLOAT_VARIANTS;
  private static Class[] INTEGER_VARIANTS;
  private static final TypeEvaluator sFloatEvaluator;
  private static final HashMap<Class, HashMap<String, Method>> sGetterPropertyMap;
  private static final TypeEvaluator sIntEvaluator = new IntEvaluator();
  private static final HashMap<Class, HashMap<String, Method>> sSetterPropertyMap;
  private Object mAnimatedValue;
  private TypeEvaluator mEvaluator;
  private Method mGetter = null;
  KeyframeSet mKeyframeSet = null;
  protected Property mProperty;
  final ReentrantReadWriteLock mPropertyMapLock = new ReentrantReadWriteLock();
  String mPropertyName;
  Method mSetter = null;
  final Object[] mTmpValueArray = new Object[1];
  Class mValueType;

  static
  {
    sFloatEvaluator = new FloatEvaluator();
    Class[] arrayOfClass1 = new Class[6];
    arrayOfClass1[0] = Float.TYPE;
    arrayOfClass1[1] = Float.class;
    arrayOfClass1[2] = Double.TYPE;
    arrayOfClass1[3] = Integer.TYPE;
    arrayOfClass1[4] = Double.class;
    arrayOfClass1[5] = Integer.class;
    FLOAT_VARIANTS = arrayOfClass1;
    Class[] arrayOfClass2 = new Class[6];
    arrayOfClass2[0] = Integer.TYPE;
    arrayOfClass2[1] = Integer.class;
    arrayOfClass2[2] = Float.TYPE;
    arrayOfClass2[3] = Double.TYPE;
    arrayOfClass2[4] = Float.class;
    arrayOfClass2[5] = Double.class;
    INTEGER_VARIANTS = arrayOfClass2;
    Class[] arrayOfClass3 = new Class[6];
    arrayOfClass3[0] = Double.TYPE;
    arrayOfClass3[1] = Double.class;
    arrayOfClass3[2] = Float.TYPE;
    arrayOfClass3[3] = Integer.TYPE;
    arrayOfClass3[4] = Float.class;
    arrayOfClass3[5] = Integer.class;
    DOUBLE_VARIANTS = arrayOfClass3;
    sSetterPropertyMap = new HashMap();
    sGetterPropertyMap = new HashMap();
  }

  private PropertyValuesHolder(Property paramProperty)
  {
    this.mProperty = paramProperty;
    if (paramProperty != null)
      this.mPropertyName = paramProperty.getName();
  }

  private PropertyValuesHolder(String paramString)
  {
    this.mPropertyName = paramString;
  }

  static String getMethodName(String paramString1, String paramString2)
  {
    if ((paramString2 == null) || (paramString2.length() == 0))
      return paramString1;
    char c = Character.toUpperCase(paramString2.charAt(0));
    String str = paramString2.substring(1);
    return paramString1 + c + str;
  }

  private Method getPropertyFunction(Class paramClass1, String paramString, Class paramClass2)
  {
    Object localObject = null;
    String str = getMethodName(paramString, this.mPropertyName);
    if (paramClass2 == null);
    while (true)
    {
      try
      {
        Method localMethod = paramClass1.getMethod(str, null);
        localObject = localMethod;
        return localObject;
      }
      catch (NoSuchMethodException localNoSuchMethodException3)
      {
        try
        {
          localObject = paramClass1.getDeclaredMethod(str, null);
          ((Method)localObject).setAccessible(true);
        }
        catch (NoSuchMethodException localNoSuchMethodException4)
        {
          Log.e("PropertyValuesHolder", "Couldn't find no-arg method for property " + this.mPropertyName + ": " + localNoSuchMethodException3);
        }
        continue;
      }
      Class[] arrayOfClass1 = new Class[1];
      Class[] arrayOfClass2;
      Class[] arrayOfClass3;
      int i;
      int j;
      if (this.mValueType.equals(Float.class))
      {
        arrayOfClass2 = FLOAT_VARIANTS;
        arrayOfClass3 = arrayOfClass2;
        i = arrayOfClass3.length;
        j = 0;
      }
      while (true)
        while (true)
        {
          if (j >= i)
            break label262;
          Class localClass = arrayOfClass3[j];
          arrayOfClass1[0] = localClass;
          try
          {
            localObject = paramClass1.getMethod(str, arrayOfClass1);
            this.mValueType = localClass;
            return localObject;
            if (this.mValueType.equals(Integer.class))
            {
              arrayOfClass2 = INTEGER_VARIANTS;
              break;
            }
            if (this.mValueType.equals(Double.class))
            {
              arrayOfClass2 = DOUBLE_VARIANTS;
              break;
            }
            arrayOfClass2 = new Class[1];
            arrayOfClass2[0] = this.mValueType;
          }
          catch (NoSuchMethodException localNoSuchMethodException1)
          {
            try
            {
              localObject = paramClass1.getDeclaredMethod(str, arrayOfClass1);
              ((Method)localObject).setAccessible(true);
              this.mValueType = localClass;
              return localObject;
            }
            catch (NoSuchMethodException localNoSuchMethodException2)
            {
              j++;
            }
          }
        }
      label262: Log.e("PropertyValuesHolder", "Couldn't find setter/getter for property " + this.mPropertyName + " with value type " + this.mValueType);
    }
  }

  public static PropertyValuesHolder ofFloat(Property<?, Float> paramProperty, float[] paramArrayOfFloat)
  {
    return new FloatPropertyValuesHolder(paramProperty, paramArrayOfFloat);
  }

  public static PropertyValuesHolder ofFloat(String paramString, float[] paramArrayOfFloat)
  {
    return new FloatPropertyValuesHolder(paramString, paramArrayOfFloat);
  }

  public static PropertyValuesHolder ofInt(Property<?, Integer> paramProperty, int[] paramArrayOfInt)
  {
    return new IntPropertyValuesHolder(paramProperty, paramArrayOfInt);
  }

  public static PropertyValuesHolder ofInt(String paramString, int[] paramArrayOfInt)
  {
    return new IntPropertyValuesHolder(paramString, paramArrayOfInt);
  }

  public static PropertyValuesHolder ofKeyframe(Property paramProperty, Keyframe[] paramArrayOfKeyframe)
  {
    KeyframeSet localKeyframeSet = KeyframeSet.ofKeyframe(paramArrayOfKeyframe);
    if ((localKeyframeSet instanceof IntKeyframeSet))
      return new IntPropertyValuesHolder(paramProperty, (IntKeyframeSet)localKeyframeSet);
    if ((localKeyframeSet instanceof FloatKeyframeSet))
      return new FloatPropertyValuesHolder(paramProperty, (FloatKeyframeSet)localKeyframeSet);
    PropertyValuesHolder localPropertyValuesHolder = new PropertyValuesHolder(paramProperty);
    localPropertyValuesHolder.mKeyframeSet = localKeyframeSet;
    localPropertyValuesHolder.mValueType = paramArrayOfKeyframe[0].getType();
    return localPropertyValuesHolder;
  }

  public static PropertyValuesHolder ofKeyframe(String paramString, Keyframe[] paramArrayOfKeyframe)
  {
    KeyframeSet localKeyframeSet = KeyframeSet.ofKeyframe(paramArrayOfKeyframe);
    if ((localKeyframeSet instanceof IntKeyframeSet))
      return new IntPropertyValuesHolder(paramString, (IntKeyframeSet)localKeyframeSet);
    if ((localKeyframeSet instanceof FloatKeyframeSet))
      return new FloatPropertyValuesHolder(paramString, (FloatKeyframeSet)localKeyframeSet);
    PropertyValuesHolder localPropertyValuesHolder = new PropertyValuesHolder(paramString);
    localPropertyValuesHolder.mKeyframeSet = localKeyframeSet;
    localPropertyValuesHolder.mValueType = paramArrayOfKeyframe[0].getType();
    return localPropertyValuesHolder;
  }

  public static <V> PropertyValuesHolder ofObject(Property paramProperty, TypeEvaluator<V> paramTypeEvaluator, V[] paramArrayOfV)
  {
    PropertyValuesHolder localPropertyValuesHolder = new PropertyValuesHolder(paramProperty);
    localPropertyValuesHolder.setObjectValues(paramArrayOfV);
    localPropertyValuesHolder.setEvaluator(paramTypeEvaluator);
    return localPropertyValuesHolder;
  }

  public static PropertyValuesHolder ofObject(String paramString, TypeEvaluator paramTypeEvaluator, Object[] paramArrayOfObject)
  {
    PropertyValuesHolder localPropertyValuesHolder = new PropertyValuesHolder(paramString);
    localPropertyValuesHolder.setObjectValues(paramArrayOfObject);
    localPropertyValuesHolder.setEvaluator(paramTypeEvaluator);
    return localPropertyValuesHolder;
  }

  private void setupGetter(Class paramClass)
  {
    this.mGetter = setupSetterOrGetter(paramClass, sGetterPropertyMap, "get", null);
  }

  private Method setupSetterOrGetter(Class paramClass1, HashMap<Class, HashMap<String, Method>> paramHashMap, String paramString, Class paramClass2)
  {
    try
    {
      this.mPropertyMapLock.writeLock().lock();
      HashMap localHashMap = (HashMap)paramHashMap.get(paramClass1);
      Method localMethod = null;
      if (localHashMap != null)
        localMethod = (Method)localHashMap.get(this.mPropertyName);
      if (localMethod == null)
      {
        localMethod = getPropertyFunction(paramClass1, paramString, paramClass2);
        if (localHashMap == null)
        {
          localHashMap = new HashMap();
          paramHashMap.put(paramClass1, localHashMap);
        }
        localHashMap.put(this.mPropertyName, localMethod);
      }
      return localMethod;
    }
    finally
    {
      this.mPropertyMapLock.writeLock().unlock();
    }
    throw localObject;
  }

  private void setupValue(Object paramObject, Keyframe paramKeyframe)
  {
    if (this.mProperty != null)
      paramKeyframe.setValue(this.mProperty.get(paramObject));
    try
    {
      if (this.mGetter == null)
        setupGetter(paramObject.getClass());
      paramKeyframe.setValue(this.mGetter.invoke(paramObject, new Object[0]));
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Log.e("PropertyValuesHolder", localInvocationTargetException.toString());
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Log.e("PropertyValuesHolder", localIllegalAccessException.toString());
    }
  }

  void calculateValue(float paramFloat)
  {
    this.mAnimatedValue = this.mKeyframeSet.getValue(paramFloat);
  }

  public PropertyValuesHolder clone()
  {
    try
    {
      PropertyValuesHolder localPropertyValuesHolder = (PropertyValuesHolder)super.clone();
      localPropertyValuesHolder.mPropertyName = this.mPropertyName;
      localPropertyValuesHolder.mProperty = this.mProperty;
      localPropertyValuesHolder.mKeyframeSet = this.mKeyframeSet.clone();
      localPropertyValuesHolder.mEvaluator = this.mEvaluator;
      return localPropertyValuesHolder;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
    }
    return null;
  }

  Object getAnimatedValue()
  {
    return this.mAnimatedValue;
  }

  public String getPropertyName()
  {
    return this.mPropertyName;
  }

  void init()
  {
    TypeEvaluator localTypeEvaluator;
    if (this.mEvaluator == null)
    {
      if (this.mValueType != Integer.class)
        break label44;
      localTypeEvaluator = sIntEvaluator;
    }
    while (true)
    {
      this.mEvaluator = localTypeEvaluator;
      if (this.mEvaluator != null)
        this.mKeyframeSet.setEvaluator(this.mEvaluator);
      return;
      label44: if (this.mValueType == Float.class)
      {
        localTypeEvaluator = sFloatEvaluator;
        continue;
      }
      localTypeEvaluator = null;
    }
  }

  void setAnimatedValue(Object paramObject)
  {
    if (this.mProperty != null)
      this.mProperty.set(paramObject, getAnimatedValue());
    if (this.mSetter != null);
    try
    {
      this.mTmpValueArray[0] = getAnimatedValue();
      this.mSetter.invoke(paramObject, this.mTmpValueArray);
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Log.e("PropertyValuesHolder", localInvocationTargetException.toString());
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Log.e("PropertyValuesHolder", localIllegalAccessException.toString());
    }
  }

  public void setEvaluator(TypeEvaluator paramTypeEvaluator)
  {
    this.mEvaluator = paramTypeEvaluator;
    this.mKeyframeSet.setEvaluator(paramTypeEvaluator);
  }

  public void setFloatValues(float[] paramArrayOfFloat)
  {
    this.mValueType = Float.TYPE;
    this.mKeyframeSet = KeyframeSet.ofFloat(paramArrayOfFloat);
  }

  public void setIntValues(int[] paramArrayOfInt)
  {
    this.mValueType = Integer.TYPE;
    this.mKeyframeSet = KeyframeSet.ofInt(paramArrayOfInt);
  }

  public void setKeyframes(Keyframe[] paramArrayOfKeyframe)
  {
    int i = paramArrayOfKeyframe.length;
    Keyframe[] arrayOfKeyframe = new Keyframe[Math.max(i, 2)];
    this.mValueType = paramArrayOfKeyframe[0].getType();
    for (int j = 0; j < i; j++)
      arrayOfKeyframe[j] = paramArrayOfKeyframe[j];
    this.mKeyframeSet = new KeyframeSet(arrayOfKeyframe);
  }

  public void setObjectValues(Object[] paramArrayOfObject)
  {
    this.mValueType = paramArrayOfObject[0].getClass();
    this.mKeyframeSet = KeyframeSet.ofObject(paramArrayOfObject);
  }

  public void setProperty(Property paramProperty)
  {
    this.mProperty = paramProperty;
  }

  public void setPropertyName(String paramString)
  {
    this.mPropertyName = paramString;
  }

  void setupEndValue(Object paramObject)
  {
    setupValue(paramObject, (Keyframe)this.mKeyframeSet.mKeyframes.get(-1 + this.mKeyframeSet.mKeyframes.size()));
  }

  void setupSetter(Class paramClass)
  {
    this.mSetter = setupSetterOrGetter(paramClass, sSetterPropertyMap, "set", this.mValueType);
  }

  void setupSetterAndGetter(Object paramObject)
  {
    if (this.mProperty != null)
    {
      try
      {
        this.mProperty.get(paramObject);
        Iterator localIterator2 = this.mKeyframeSet.mKeyframes.iterator();
        while (localIterator2.hasNext())
        {
          Keyframe localKeyframe2 = (Keyframe)localIterator2.next();
          if (localKeyframe2.hasValue())
            continue;
          localKeyframe2.setValue(this.mProperty.get(paramObject));
        }
      }
      catch (ClassCastException localClassCastException)
      {
        Log.e("PropertyValuesHolder", "No such property (" + this.mProperty.getName() + ") on target object " + paramObject + ". Trying reflection instead");
        this.mProperty = null;
      }
    }
    else
    {
      Class localClass = paramObject.getClass();
      if (this.mSetter == null)
        setupSetter(localClass);
      Iterator localIterator1 = this.mKeyframeSet.mKeyframes.iterator();
      while (localIterator1.hasNext())
      {
        Keyframe localKeyframe1 = (Keyframe)localIterator1.next();
        if (localKeyframe1.hasValue())
          continue;
        if (this.mGetter == null)
          setupGetter(localClass);
        try
        {
          localKeyframe1.setValue(this.mGetter.invoke(paramObject, new Object[0]));
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          Log.e("PropertyValuesHolder", localInvocationTargetException.toString());
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          Log.e("PropertyValuesHolder", localIllegalAccessException.toString());
        }
      }
    }
  }

  void setupStartValue(Object paramObject)
  {
    setupValue(paramObject, (Keyframe)this.mKeyframeSet.mKeyframes.get(0));
  }

  public String toString()
  {
    return this.mPropertyName + ": " + this.mKeyframeSet.toString();
  }

  static class FloatPropertyValuesHolder extends PropertyValuesHolder
  {
    float mFloatAnimatedValue;
    FloatKeyframeSet mFloatKeyframeSet;
    private FloatProperty mFloatProperty;

    public FloatPropertyValuesHolder(Property paramProperty, FloatKeyframeSet paramFloatKeyframeSet)
    {
      super(null);
      this.mValueType = Float.TYPE;
      this.mKeyframeSet = paramFloatKeyframeSet;
      this.mFloatKeyframeSet = ((FloatKeyframeSet)this.mKeyframeSet);
      if ((paramProperty instanceof FloatProperty))
        this.mFloatProperty = ((FloatProperty)this.mProperty);
    }

    public FloatPropertyValuesHolder(Property paramProperty, float[] paramArrayOfFloat)
    {
      super(null);
      setFloatValues(paramArrayOfFloat);
      if ((paramProperty instanceof FloatProperty))
        this.mFloatProperty = ((FloatProperty)this.mProperty);
    }

    public FloatPropertyValuesHolder(String paramString, FloatKeyframeSet paramFloatKeyframeSet)
    {
      super(null);
      this.mValueType = Float.TYPE;
      this.mKeyframeSet = paramFloatKeyframeSet;
      this.mFloatKeyframeSet = ((FloatKeyframeSet)this.mKeyframeSet);
    }

    public FloatPropertyValuesHolder(String paramString, float[] paramArrayOfFloat)
    {
      super(null);
      setFloatValues(paramArrayOfFloat);
    }

    void calculateValue(float paramFloat)
    {
      this.mFloatAnimatedValue = this.mFloatKeyframeSet.getFloatValue(paramFloat);
    }

    public FloatPropertyValuesHolder clone()
    {
      FloatPropertyValuesHolder localFloatPropertyValuesHolder = (FloatPropertyValuesHolder)super.clone();
      localFloatPropertyValuesHolder.mFloatKeyframeSet = ((FloatKeyframeSet)localFloatPropertyValuesHolder.mKeyframeSet);
      return localFloatPropertyValuesHolder;
    }

    Object getAnimatedValue()
    {
      return Float.valueOf(this.mFloatAnimatedValue);
    }

    void setAnimatedValue(Object paramObject)
    {
      if (this.mFloatProperty != null)
        this.mFloatProperty.setValue(paramObject, this.mFloatAnimatedValue);
      do
      {
        return;
        if (this.mProperty == null)
          continue;
        this.mProperty.set(paramObject, Float.valueOf(this.mFloatAnimatedValue));
        return;
      }
      while (this.mSetter == null);
      try
      {
        this.mTmpValueArray[0] = Float.valueOf(this.mFloatAnimatedValue);
        this.mSetter.invoke(paramObject, this.mTmpValueArray);
        return;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        Log.e("PropertyValuesHolder", localInvocationTargetException.toString());
        return;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Log.e("PropertyValuesHolder", localIllegalAccessException.toString());
      }
    }

    public void setFloatValues(float[] paramArrayOfFloat)
    {
      super.setFloatValues(paramArrayOfFloat);
      this.mFloatKeyframeSet = ((FloatKeyframeSet)this.mKeyframeSet);
    }

    void setupSetter(Class paramClass)
    {
      if (this.mProperty != null)
        return;
      super.setupSetter(paramClass);
    }
  }

  static class IntPropertyValuesHolder extends PropertyValuesHolder
  {
    int mIntAnimatedValue;
    IntKeyframeSet mIntKeyframeSet;
    private IntProperty mIntProperty;

    public IntPropertyValuesHolder(Property paramProperty, IntKeyframeSet paramIntKeyframeSet)
    {
      super(null);
      this.mValueType = Integer.TYPE;
      this.mKeyframeSet = paramIntKeyframeSet;
      this.mIntKeyframeSet = ((IntKeyframeSet)this.mKeyframeSet);
      if ((paramProperty instanceof IntProperty))
        this.mIntProperty = ((IntProperty)this.mProperty);
    }

    public IntPropertyValuesHolder(Property paramProperty, int[] paramArrayOfInt)
    {
      super(null);
      setIntValues(paramArrayOfInt);
      if ((paramProperty instanceof IntProperty))
        this.mIntProperty = ((IntProperty)this.mProperty);
    }

    public IntPropertyValuesHolder(String paramString, IntKeyframeSet paramIntKeyframeSet)
    {
      super(null);
      this.mValueType = Integer.TYPE;
      this.mKeyframeSet = paramIntKeyframeSet;
      this.mIntKeyframeSet = ((IntKeyframeSet)this.mKeyframeSet);
    }

    public IntPropertyValuesHolder(String paramString, int[] paramArrayOfInt)
    {
      super(null);
      setIntValues(paramArrayOfInt);
    }

    void calculateValue(float paramFloat)
    {
      this.mIntAnimatedValue = this.mIntKeyframeSet.getIntValue(paramFloat);
    }

    public IntPropertyValuesHolder clone()
    {
      IntPropertyValuesHolder localIntPropertyValuesHolder = (IntPropertyValuesHolder)super.clone();
      localIntPropertyValuesHolder.mIntKeyframeSet = ((IntKeyframeSet)localIntPropertyValuesHolder.mKeyframeSet);
      return localIntPropertyValuesHolder;
    }

    Object getAnimatedValue()
    {
      return Integer.valueOf(this.mIntAnimatedValue);
    }

    void setAnimatedValue(Object paramObject)
    {
      if (this.mIntProperty != null)
        this.mIntProperty.setValue(paramObject, this.mIntAnimatedValue);
      do
      {
        return;
        if (this.mProperty == null)
          continue;
        this.mProperty.set(paramObject, Integer.valueOf(this.mIntAnimatedValue));
        return;
      }
      while (this.mSetter == null);
      try
      {
        this.mTmpValueArray[0] = Integer.valueOf(this.mIntAnimatedValue);
        this.mSetter.invoke(paramObject, this.mTmpValueArray);
        return;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        Log.e("PropertyValuesHolder", localInvocationTargetException.toString());
        return;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Log.e("PropertyValuesHolder", localIllegalAccessException.toString());
      }
    }

    public void setIntValues(int[] paramArrayOfInt)
    {
      super.setIntValues(paramArrayOfInt);
      this.mIntKeyframeSet = ((IntKeyframeSet)this.mKeyframeSet);
    }

    void setupSetter(Class paramClass)
    {
      if (this.mProperty != null)
        return;
      super.setupSetter(paramClass);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.nineoldandroids.animation.PropertyValuesHolder
 * JD-Core Version:    0.6.0
 */