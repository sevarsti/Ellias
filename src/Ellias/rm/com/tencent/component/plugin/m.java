package com.tencent.component.plugin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Filter;
import android.view.View;
import com.tencent.component.utils.log.LogUtil;
import java.util.HashMap;

final class m extends LayoutInflater
  implements LayoutInflaterProxy.InflaterImpl
{
  static final Class[] a;
  private static final String b = "PluginLayoutInflater";
  private static final String[] c = { "android.widget.", "android.webkit." };
  private HashMap d;
  private final Object[] e = new Object[2];
  private final HashMap f = new HashMap();
  private final Plugin g;

  static
  {
    a = new Class[] { Context.class, AttributeSet.class };
  }

  private m(Context paramContext, LayoutInflaterProxy paramLayoutInflaterProxy, Plugin paramPlugin)
  {
    super(paramLayoutInflaterProxy, paramContext);
    this.g = paramPlugin;
    paramLayoutInflaterProxy.a(this);
  }

  protected m(Context paramContext, Plugin paramPlugin)
  {
    this(paramContext, new LayoutInflaterProxy(paramContext), paramPlugin);
  }

  protected m(m paramm, Context paramContext)
  {
    super(paramm, paramContext);
    this.g = paramm.g;
  }

  private View a(String paramString, AttributeSet paramAttributeSet)
  {
    if (-1 == paramString.indexOf('.'))
      return onCreateView(paramString, paramAttributeSet);
    return createView(paramString, null, paramAttributeSet);
  }

  // ERROR //
  @android.annotation.SuppressLint({"InlinedApi", "NewApi"})
  private View b(String paramString, AttributeSet paramAttributeSet)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 48	com/tencent/component/plugin/m:e	[Ljava/lang/Object;
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 48	com/tencent/component/plugin/m:e	[Ljava/lang/Object;
    //   11: iconst_0
    //   12: aload_0
    //   13: invokevirtual 96	com/tencent/component/plugin/m:getContext	()Landroid/content/Context;
    //   16: aastore
    //   17: aload_0
    //   18: getfield 54	com/tencent/component/plugin/m:f	Ljava/util/HashMap;
    //   21: aload_1
    //   22: invokevirtual 100	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   25: checkcast 102	java/lang/reflect/Constructor
    //   28: astore 5
    //   30: aload_0
    //   31: invokevirtual 106	com/tencent/component/plugin/m:getFilter	()Landroid/view/LayoutInflater$Filter;
    //   34: astore 20
    //   36: aload 5
    //   38: ifnonnull +134 -> 172
    //   41: aload_0
    //   42: getfield 56	com/tencent/component/plugin/m:g	Lcom/tencent/component/plugin/Plugin;
    //   45: invokevirtual 110	java/lang/Object:getClass	()Ljava/lang/Class;
    //   48: invokevirtual 114	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   51: aload_1
    //   52: invokevirtual 120	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   55: ldc 122
    //   57: invokevirtual 126	java/lang/Class:asSubclass	(Ljava/lang/Class;)Ljava/lang/Class;
    //   60: astore 21
    //   62: aload 21
    //   64: astore 7
    //   66: aload 20
    //   68: ifnull +26 -> 94
    //   71: aload 7
    //   73: ifnull +21 -> 94
    //   76: aload 20
    //   78: aload 7
    //   80: invokeinterface 132 2 0
    //   85: ifne +9 -> 94
    //   88: aload_0
    //   89: aload_1
    //   90: aload_2
    //   91: invokespecial 135	com/tencent/component/plugin/m:c	(Ljava/lang/String;Landroid/util/AttributeSet;)V
    //   94: aload 7
    //   96: getstatic 39	com/tencent/component/plugin/m:a	[Ljava/lang/Class;
    //   99: invokevirtual 139	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   102: astore 5
    //   104: aload_0
    //   105: getfield 54	com/tencent/component/plugin/m:f	Ljava/util/HashMap;
    //   108: aload_1
    //   109: aload 5
    //   111: invokevirtual 143	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: pop
    //   115: aload 7
    //   117: astore 23
    //   119: aload_0
    //   120: getfield 48	com/tencent/component/plugin/m:e	[Ljava/lang/Object;
    //   123: astore 24
    //   125: aload 24
    //   127: iconst_1
    //   128: aload_2
    //   129: aastore
    //   130: aload 5
    //   132: aload 24
    //   134: invokevirtual 147	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   137: checkcast 122	android/view/View
    //   140: astore 25
    //   142: getstatic 153	android/os/Build$VERSION:SDK_INT	I
    //   145: bipush 16
    //   147: if_icmplt +20 -> 167
    //   150: aload 25
    //   152: instanceof 155
    //   155: ifeq +12 -> 167
    //   158: aload 25
    //   160: checkcast 155	android/view/ViewStub
    //   163: aload_0
    //   164: invokevirtual 159	android/view/ViewStub:setLayoutInflater	(Landroid/view/LayoutInflater;)V
    //   167: aload_3
    //   168: monitorexit
    //   169: aload 25
    //   171: areturn
    //   172: aconst_null
    //   173: astore 23
    //   175: aload 20
    //   177: ifnull -58 -> 119
    //   180: aload_0
    //   181: getfield 161	com/tencent/component/plugin/m:d	Ljava/util/HashMap;
    //   184: aload_1
    //   185: invokevirtual 100	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   188: checkcast 163	java/lang/Boolean
    //   191: astore 26
    //   193: aload 26
    //   195: ifnonnull +86 -> 281
    //   198: aload_0
    //   199: getfield 56	com/tencent/component/plugin/m:g	Lcom/tencent/component/plugin/Plugin;
    //   202: invokevirtual 110	java/lang/Object:getClass	()Ljava/lang/Class;
    //   205: invokevirtual 114	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   208: aload_1
    //   209: invokevirtual 120	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   212: ldc 122
    //   214: invokevirtual 126	java/lang/Class:asSubclass	(Ljava/lang/Class;)Ljava/lang/Class;
    //   217: astore 27
    //   219: aload 27
    //   221: astore 7
    //   223: aload 7
    //   225: ifnull +50 -> 275
    //   228: aload 20
    //   230: aload 7
    //   232: invokeinterface 132 2 0
    //   237: ifeq +38 -> 275
    //   240: iconst_1
    //   241: istore 28
    //   243: aload_0
    //   244: getfield 161	com/tencent/component/plugin/m:d	Ljava/util/HashMap;
    //   247: aload_1
    //   248: iload 28
    //   250: invokestatic 167	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   253: invokevirtual 143	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   256: pop
    //   257: iload 28
    //   259: ifne +9 -> 268
    //   262: aload_0
    //   263: aload_1
    //   264: aload_2
    //   265: invokespecial 135	com/tencent/component/plugin/m:c	(Ljava/lang/String;Landroid/util/AttributeSet;)V
    //   268: aload 7
    //   270: astore 23
    //   272: goto -153 -> 119
    //   275: iconst_0
    //   276: istore 28
    //   278: goto -35 -> 243
    //   281: aload 26
    //   283: getstatic 171	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   286: invokevirtual 175	java/lang/Boolean:equals	(Ljava/lang/Object;)Z
    //   289: istore 30
    //   291: aconst_null
    //   292: astore 23
    //   294: iload 30
    //   296: ifeq -177 -> 119
    //   299: aload_0
    //   300: aload_1
    //   301: aload_2
    //   302: invokespecial 135	com/tencent/component/plugin/m:c	(Ljava/lang/String;Landroid/util/AttributeSet;)V
    //   305: aconst_null
    //   306: astore 23
    //   308: goto -189 -> 119
    //   311: astore 17
    //   313: new 177	android/view/InflateException
    //   316: dup
    //   317: new 179	java/lang/StringBuilder
    //   320: dup
    //   321: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   324: aload_2
    //   325: invokeinterface 184 1 0
    //   330: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   333: ldc 190
    //   335: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: aload_1
    //   339: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: invokevirtual 193	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   345: invokespecial 196	android/view/InflateException:<init>	(Ljava/lang/String;)V
    //   348: astore 18
    //   350: aload 18
    //   352: aload 17
    //   354: invokevirtual 200	android/view/InflateException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   357: pop
    //   358: aload 18
    //   360: athrow
    //   361: astore 4
    //   363: aload_3
    //   364: monitorexit
    //   365: aload 4
    //   367: athrow
    //   368: astore 14
    //   370: new 177	android/view/InflateException
    //   373: dup
    //   374: new 179	java/lang/StringBuilder
    //   377: dup
    //   378: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   381: aload_2
    //   382: invokeinterface 184 1 0
    //   387: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   390: ldc 202
    //   392: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   395: aload_1
    //   396: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: invokevirtual 193	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   402: invokespecial 196	android/view/InflateException:<init>	(Ljava/lang/String;)V
    //   405: astore 15
    //   407: aload 15
    //   409: aload 14
    //   411: invokevirtual 200	android/view/InflateException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   414: pop
    //   415: aload 15
    //   417: athrow
    //   418: astore 13
    //   420: aload 13
    //   422: athrow
    //   423: new 179	java/lang/StringBuilder
    //   426: dup
    //   427: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   430: aload_2
    //   431: invokeinterface 184 1 0
    //   436: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   439: ldc 190
    //   441: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: astore 8
    //   446: aload 7
    //   448: ifnonnull +37 -> 485
    //   451: ldc 204
    //   453: astore 10
    //   455: new 177	android/view/InflateException
    //   458: dup
    //   459: aload 8
    //   461: aload 10
    //   463: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   466: invokevirtual 193	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   469: invokespecial 196	android/view/InflateException:<init>	(Ljava/lang/String;)V
    //   472: astore 11
    //   474: aload 11
    //   476: aload 6
    //   478: invokevirtual 200	android/view/InflateException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   481: pop
    //   482: aload 11
    //   484: athrow
    //   485: aload 7
    //   487: invokevirtual 207	java/lang/Class:getName	()Ljava/lang/String;
    //   490: astore 9
    //   492: aload 9
    //   494: astore 10
    //   496: goto -41 -> 455
    //   499: astore 6
    //   501: goto -78 -> 423
    //   504: astore 6
    //   506: aload 23
    //   508: astore 7
    //   510: goto -87 -> 423
    //   513: astore 6
    //   515: aconst_null
    //   516: astore 7
    //   518: goto -95 -> 423
    //
    // Exception table:
    //   from	to	target	type
    //   30	36	311	java/lang/NoSuchMethodException
    //   41	62	311	java/lang/NoSuchMethodException
    //   76	94	311	java/lang/NoSuchMethodException
    //   94	115	311	java/lang/NoSuchMethodException
    //   119	167	311	java/lang/NoSuchMethodException
    //   180	193	311	java/lang/NoSuchMethodException
    //   198	219	311	java/lang/NoSuchMethodException
    //   228	240	311	java/lang/NoSuchMethodException
    //   243	257	311	java/lang/NoSuchMethodException
    //   262	268	311	java/lang/NoSuchMethodException
    //   281	291	311	java/lang/NoSuchMethodException
    //   299	305	311	java/lang/NoSuchMethodException
    //   7	30	361	finally
    //   30	36	361	finally
    //   41	62	361	finally
    //   76	94	361	finally
    //   94	115	361	finally
    //   119	167	361	finally
    //   167	169	361	finally
    //   180	193	361	finally
    //   198	219	361	finally
    //   228	240	361	finally
    //   243	257	361	finally
    //   262	268	361	finally
    //   281	291	361	finally
    //   299	305	361	finally
    //   313	361	361	finally
    //   363	365	361	finally
    //   370	418	361	finally
    //   420	423	361	finally
    //   423	446	361	finally
    //   455	485	361	finally
    //   485	492	361	finally
    //   30	36	368	java/lang/ClassCastException
    //   41	62	368	java/lang/ClassCastException
    //   76	94	368	java/lang/ClassCastException
    //   94	115	368	java/lang/ClassCastException
    //   119	167	368	java/lang/ClassCastException
    //   180	193	368	java/lang/ClassCastException
    //   198	219	368	java/lang/ClassCastException
    //   228	240	368	java/lang/ClassCastException
    //   243	257	368	java/lang/ClassCastException
    //   262	268	368	java/lang/ClassCastException
    //   281	291	368	java/lang/ClassCastException
    //   299	305	368	java/lang/ClassCastException
    //   30	36	418	java/lang/ClassNotFoundException
    //   41	62	418	java/lang/ClassNotFoundException
    //   76	94	418	java/lang/ClassNotFoundException
    //   94	115	418	java/lang/ClassNotFoundException
    //   119	167	418	java/lang/ClassNotFoundException
    //   180	193	418	java/lang/ClassNotFoundException
    //   198	219	418	java/lang/ClassNotFoundException
    //   228	240	418	java/lang/ClassNotFoundException
    //   243	257	418	java/lang/ClassNotFoundException
    //   262	268	418	java/lang/ClassNotFoundException
    //   281	291	418	java/lang/ClassNotFoundException
    //   299	305	418	java/lang/ClassNotFoundException
    //   76	94	499	java/lang/Exception
    //   94	115	499	java/lang/Exception
    //   228	240	499	java/lang/Exception
    //   243	257	499	java/lang/Exception
    //   262	268	499	java/lang/Exception
    //   119	167	504	java/lang/Exception
    //   30	36	513	java/lang/Exception
    //   41	62	513	java/lang/Exception
    //   180	193	513	java/lang/Exception
    //   198	219	513	java/lang/Exception
    //   281	291	513	java/lang/Exception
    //   299	305	513	java/lang/Exception
  }

  private void c(String paramString, AttributeSet paramAttributeSet)
  {
    throw new InflateException(paramAttributeSet.getPositionDescription() + ": Class not allowed to be inflated " + paramString);
  }

  public View a(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    int i = paramString.indexOf('.');
    Object localObject = null;
    if (-1 != i);
    try
    {
      View localView2 = b(paramString, paramAttributeSet);
      localObject = localView2;
      if (localObject != null);
    }
    catch (Throwable localThrowable2)
    {
      try
      {
        View localView1 = a(paramString, paramAttributeSet);
        localObject = localView1;
        return localObject;
        localThrowable2 = localThrowable2;
        LogUtil.d("PluginLayoutInflater", "fail to create view internal for " + paramString + " with " + this.g.getClass().getClassLoader());
        localObject = null;
      }
      catch (Throwable localThrowable1)
      {
      }
    }
    return localObject;
  }

  public LayoutInflater cloneInContext(Context paramContext)
  {
    return new m(this, paramContext);
  }

  protected View onCreateView(String paramString, AttributeSet paramAttributeSet)
  {
    String[] arrayOfString = c;
    int i = arrayOfString.length;
    int j = 0;
    while (j < i)
    {
      String str = arrayOfString[j];
      try
      {
        View localView = createView(paramString, str, paramAttributeSet);
        if (localView != null)
          return localView;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        j++;
      }
    }
    return super.onCreateView(paramString, paramAttributeSet);
  }

  public void setFilter(LayoutInflater.Filter paramFilter)
  {
    super.setFilter(paramFilter);
    if (paramFilter != null)
      this.d = new HashMap();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.m
 * JD-Core Version:    0.6.0
 */