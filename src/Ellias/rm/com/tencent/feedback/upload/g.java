package com.tencent.feedback.upload;

import android.content.Context;
import android.util.SparseArray;
import com.qq.jce.wup.UniPacket;
import com.qq.taf.jce.JceInputStream;
import com.tencent.feedback.common.a;
import com.tencent.feedback.common.h;
import com.tencent.feedback.common.k;
import common.MixPackage;
import common.RequestPackage;
import common.ResponsePackage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import qimei.QIMeiInfoPackage;

public final class g
  implements f
{
  private static g a = null;
  private SparseArray<e> b = new SparseArray(5);
  private List<UploadHandleListener> c = new ArrayList(5);
  private d d;
  private Context e = null;
  private boolean f = true;
  private int g = 0;

  private g(Context paramContext, boolean paramBoolean)
  {
    this.e = paramContext.getApplicationContext();
    this.f = paramBoolean;
    this.d = d.a(this.e);
  }

  private int a()
  {
    monitorenter;
    try
    {
      int i = this.g;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static g a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (a == null)
      {
        a = new g(paramContext, true);
        com.tencent.feedback.common.e.h("rqdp{  create uphandler up:true}", new Object[0]);
      }
      g localg = a;
      return localg;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static g a(Context paramContext, boolean paramBoolean)
  {
    monitorenter;
    try
    {
      if (a == null)
      {
        a = new g(paramContext, paramBoolean);
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Boolean.valueOf(paramBoolean);
        com.tencent.feedback.common.e.h("rqdp{  create uphandler up:}%b", arrayOfObject2);
      }
      if (a.e() != paramBoolean)
      {
        a.a(paramBoolean);
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Boolean.valueOf(paramBoolean);
        com.tencent.feedback.common.e.h("rqdp{  change uphandler up:}%b", arrayOfObject1);
      }
      g localg = a;
      return localg;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static ResponsePackage a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
      try
      {
        UniPacket localUniPacket = new UniPacket();
        localUniPacket.decode(paramArrayOfByte);
        Object localObject = localUniPacket.get("detail");
        if (ResponsePackage.class.isInstance(localObject))
        {
          com.tencent.feedback.common.e.b("rqdp{  covert to ResponsePackage}", new Object[0]);
          ResponsePackage localResponsePackage = (ResponsePackage)ResponsePackage.class.cast(localObject);
          return localResponsePackage;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    return null;
  }

  private void a(int paramInt)
  {
    monitorenter;
    try
    {
      this.g = paramInt;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private void a(int paramInt1, int paramInt2, long paramLong1, long paramLong2, boolean paramBoolean, String paramString)
  {
    UploadHandleListener[] arrayOfUploadHandleListener = b();
    if (arrayOfUploadHandleListener != null)
    {
      int i = arrayOfUploadHandleListener.length;
      for (int j = 0; j < i; j++)
        arrayOfUploadHandleListener[j].onUploadEnd(paramInt1, paramInt2, paramLong1, paramLong2, paramBoolean, paramString);
    }
  }

  private void a(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.f = paramBoolean;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private boolean a(SparseArray<e> paramSparseArray, int paramInt, byte[] paramArrayOfByte)
  {
    if ((paramSparseArray == null) || (paramArrayOfByte == null))
      return true;
    e locale;
    switch (paramInt)
    {
    default:
      locale = (e)paramSparseArray.get(paramInt);
      if (locale != null)
        break;
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = Integer.valueOf(paramInt);
      com.tencent.feedback.common.e.c("rqdp{  no handler key:}%d", arrayOfObject4);
      return false;
    case 305:
      MixPackage localMixPackage2 = b(paramArrayOfByte);
      if (localMixPackage2 == null)
      {
        com.tencent.feedback.common.e.c("rqdp{  mix error}", new Object[0]);
        return false;
      }
      Map localMap2 = localMixPackage2.getMixMap();
      if ((localMap2 != null) && (localMap2.size() > 0))
      {
        Iterator localIterator = localMap2.keySet().iterator();
        while (localIterator.hasNext())
        {
          int i = ((Integer)localIterator.next()).intValue();
          a(paramSparseArray, i, (byte[])localMap2.get(Integer.valueOf(i)));
        }
      }
      return true;
    case 304:
      MixPackage localMixPackage1 = b(paramArrayOfByte);
      if (localMixPackage1 == null)
      {
        com.tencent.feedback.common.e.c("rqdp{  mix error}", new Object[0]);
        return false;
      }
      Map localMap1 = localMixPackage1.getMixMap();
      if ((localMap1 != null) && (localMap1.size() > 0))
      {
        if (localMap1.containsKey(Integer.valueOf(301)))
          a(paramSparseArray, 301, (byte[])localMap1.get(Integer.valueOf(301)));
        if (localMap1.containsKey(Integer.valueOf(303)))
          a(paramSparseArray, 303, (byte[])localMap1.get(Integer.valueOf(303)));
      }
      return true;
    case 15:
      try
      {
        com.tencent.feedback.common.e.a("rqdp{  process CMD_RESPONSE_GEN_QIMEI}", new Object[0]);
        JceInputStream localJceInputStream = new JceInputStream(paramArrayOfByte);
        QIMeiInfoPackage localQIMeiInfoPackage = new QIMeiInfoPackage();
        localQIMeiInfoPackage.readFrom(localJceInputStream);
        if (localQIMeiInfoPackage.qIMEI != null)
        {
          h.a(this.e).a(localQIMeiInfoPackage.qIMEI);
          a.a(this.e, "QIMEI_DENGTA", localQIMeiInfoPackage.qIMEI);
        }
        Object[] arrayOfObject1 = new Object[5];
        arrayOfObject1[0] = localQIMeiInfoPackage.qIMEI;
        arrayOfObject1[1] = localQIMeiInfoPackage.imei;
        arrayOfObject1[2] = localQIMeiInfoPackage.imsi;
        arrayOfObject1[3] = localQIMeiInfoPackage.androidId;
        arrayOfObject1[4] = localQIMeiInfoPackage.mac;
        com.tencent.feedback.common.e.h("rqdp{  Qimei:}%s rqdp{  imei:}%s rqdp{  imsi:}%s rqdp{  aid:}%s rqdp{  mac:}%s ", arrayOfObject1);
        return true;
      }
      catch (Throwable localThrowable1)
      {
        while (true)
          localThrowable1.printStackTrace();
      }
    }
    try
    {
      Object[] arrayOfObject3 = new Object[2];
      arrayOfObject3[0] = Integer.valueOf(paramInt);
      arrayOfObject3[1] = locale.getClass().toString();
      com.tencent.feedback.common.e.b("rqdp{  key:}%d rqdp{  handler: }%s", arrayOfObject3);
      locale.a(paramInt, paramArrayOfByte, true);
      return true;
    }
    catch (Throwable localThrowable2)
    {
      localThrowable2.printStackTrace();
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(paramInt);
      com.tencent.feedback.common.e.d("rqdp{  handle error key:}%d", arrayOfObject2);
    }
    return false;
  }

  private static MixPackage b(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return null;
    try
    {
      MixPackage localMixPackage = new MixPackage();
      localMixPackage.readFrom(new JceInputStream(paramArrayOfByte));
      return localMixPackage;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }

  private static byte[] b(AbstractUploadDatas paramAbstractUploadDatas)
  {
    if (paramAbstractUploadDatas != null)
      try
      {
        RequestPackage localRequestPackage = paramAbstractUploadDatas.a();
        if (localRequestPackage != null)
        {
          Object[] arrayOfObject = new Object[5];
          arrayOfObject[0] = localRequestPackage.productId;
          arrayOfObject[1] = localRequestPackage.bundleId;
          arrayOfObject[2] = localRequestPackage.productVersion;
          arrayOfObject[3] = localRequestPackage.sdkId;
          arrayOfObject[4] = localRequestPackage.sdkVersion;
          com.tencent.feedback.common.e.b("rqdp{  [pid:}%s rqdp{  \nbid:}%s_%s rqdp{  \nsid:}%s_%s \n]", arrayOfObject);
          UniPacket localUniPacket = new UniPacket();
          localUniPacket.setRequestId(1);
          localUniPacket.setServantName("test");
          localUniPacket.setFuncName("test");
          localUniPacket.put("detail", localRequestPackage);
          byte[] arrayOfByte = localUniPacket.encode();
          return arrayOfByte;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        AbstractUploadDatas.d();
      }
    return null;
  }

  private UploadHandleListener[] b()
  {
    monitorenter;
    try
    {
      if ((this.c != null) && (this.c.size() > 0))
      {
        arrayOfUploadHandleListener = (UploadHandleListener[])this.c.toArray(new UploadHandleListener[0]);
        return arrayOfUploadHandleListener;
      }
      UploadHandleListener[] arrayOfUploadHandleListener = null;
    }
    finally
    {
      monitorexit;
    }
  }

  private d c()
  {
    monitorenter;
    try
    {
      d locald = this.d;
      monitorexit;
      return locald;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private SparseArray<e> d()
  {
    monitorenter;
    try
    {
      if ((this.b != null) && (this.b.size() > 0))
      {
        new k();
        SparseArray localSparseArray2 = k.a(this.b);
        localSparseArray1 = localSparseArray2;
        return localSparseArray1;
      }
      SparseArray localSparseArray1 = null;
    }
    finally
    {
      monitorexit;
    }
  }

  private boolean e()
  {
    monitorenter;
    try
    {
      boolean bool = this.f;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  public final void a(AbstractUploadDatas paramAbstractUploadDatas)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 85	com/tencent/feedback/upload/g:e	()Z
    //   4: ifne +78 -> 82
    //   7: aload_1
    //   8: invokevirtual 330	com/tencent/feedback/upload/AbstractUploadDatas:b	()I
    //   11: bipush 11
    //   13: if_icmpne +23 -> 36
    //   16: ldc_w 332
    //   19: iconst_0
    //   20: anewarray 4	java/lang/Object
    //   23: invokestatic 73	com/tencent/feedback/common/e:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   26: aload_1
    //   27: invokevirtual 269	com/tencent/feedback/upload/AbstractUploadDatas:a	()Lcommon/RequestPackage;
    //   30: pop
    //   31: aload_1
    //   32: iconst_0
    //   33: invokevirtual 335	com/tencent/feedback/upload/AbstractUploadDatas:done	(Z)V
    //   36: aload_1
    //   37: getfield 337	com/tencent/feedback/upload/AbstractUploadDatas:b	I
    //   40: sipush 1111
    //   43: if_icmpeq +29 -> 72
    //   46: iconst_1
    //   47: anewarray 4	java/lang/Object
    //   50: astore 42
    //   52: aload 42
    //   54: iconst_0
    //   55: aload_1
    //   56: invokevirtual 330	com/tencent/feedback/upload/AbstractUploadDatas:b	()I
    //   59: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   62: aastore
    //   63: ldc_w 339
    //   66: aload 42
    //   68: invokestatic 73	com/tencent/feedback/common/e:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   71: return
    //   72: ldc_w 341
    //   75: iconst_0
    //   76: anewarray 4	java/lang/Object
    //   79: invokestatic 73	com/tencent/feedback/common/e:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   82: aload_0
    //   83: getfield 44	com/tencent/feedback/upload/g:e	Landroid/content/Context;
    //   86: invokestatic 346	com/tencent/feedback/common/g:b	(Landroid/content/Context;)Z
    //   89: ifne +28 -> 117
    //   92: ldc_w 348
    //   95: iconst_0
    //   96: anewarray 4	java/lang/Object
    //   99: invokestatic 149	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   102: aload_1
    //   103: invokevirtual 330	com/tencent/feedback/upload/AbstractUploadDatas:b	()I
    //   106: bipush 11
    //   108: if_icmpne -37 -> 71
    //   111: aload_1
    //   112: iconst_0
    //   113: invokevirtual 335	com/tencent/feedback/upload/AbstractUploadDatas:done	(Z)V
    //   116: return
    //   117: aload_0
    //   118: aload_0
    //   119: getfield 44	com/tencent/feedback/upload/g:e	Landroid/content/Context;
    //   122: invokestatic 353	com/tencent/feedback/common/f:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/f;
    //   125: invokevirtual 356	com/tencent/feedback/upload/g:a	(Lcom/tencent/feedback/upload/UploadHandleListener;)Z
    //   128: pop
    //   129: aload_1
    //   130: ifnonnull +14 -> 144
    //   133: ldc_w 358
    //   136: iconst_0
    //   137: anewarray 4	java/lang/Object
    //   140: invokestatic 261	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   143: return
    //   144: aload_1
    //   145: invokevirtual 330	com/tencent/feedback/upload/AbstractUploadDatas:b	()I
    //   148: istore_3
    //   149: lconst_0
    //   150: lstore 4
    //   152: lconst_0
    //   153: lstore 6
    //   155: iconst_0
    //   156: istore 8
    //   158: iconst_m1
    //   159: istore 9
    //   161: aload_0
    //   162: invokespecial 129	com/tencent/feedback/upload/g:b	()[Lcom/tencent/feedback/upload/UploadHandleListener;
    //   165: astore 10
    //   167: aload 10
    //   169: ifnull +35 -> 204
    //   172: aload 10
    //   174: arraylength
    //   175: istore 40
    //   177: iconst_0
    //   178: istore 41
    //   180: iload 41
    //   182: iload 40
    //   184: if_icmpge +20 -> 204
    //   187: aload 10
    //   189: iload 41
    //   191: aaload
    //   192: iload_3
    //   193: invokeinterface 361 2 0
    //   198: iinc 41 1
    //   201: goto -21 -> 180
    //   204: aload_1
    //   205: invokevirtual 363	com/tencent/feedback/upload/AbstractUploadDatas:c	()Ljava/lang/String;
    //   208: astore 11
    //   210: aload 11
    //   212: ifnonnull +40 -> 252
    //   215: ldc_w 365
    //   218: iconst_0
    //   219: anewarray 4	java/lang/Object
    //   222: invokestatic 261	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   225: aload_1
    //   226: invokevirtual 330	com/tencent/feedback/upload/AbstractUploadDatas:b	()I
    //   229: bipush 11
    //   231: if_icmpne +8 -> 239
    //   234: aload_1
    //   235: iconst_0
    //   236: invokevirtual 335	com/tencent/feedback/upload/AbstractUploadDatas:done	(Z)V
    //   239: aload_0
    //   240: iload_3
    //   241: iconst_m1
    //   242: lconst_0
    //   243: lconst_0
    //   244: iconst_0
    //   245: ldc_w 367
    //   248: invokespecial 369	com/tencent/feedback/upload/g:a	(IIJJZLjava/lang/String;)V
    //   251: return
    //   252: iconst_3
    //   253: anewarray 4	java/lang/Object
    //   256: astore 12
    //   258: aload 12
    //   260: iconst_0
    //   261: iload_3
    //   262: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   265: aastore
    //   266: aload 12
    //   268: iconst_1
    //   269: aload 11
    //   271: aastore
    //   272: aload 12
    //   274: iconst_2
    //   275: aload_1
    //   276: invokevirtual 248	java/lang/Object:getClass	()Ljava/lang/Class;
    //   279: invokevirtual 252	java/lang/Class:toString	()Ljava/lang/String;
    //   282: aastore
    //   283: ldc_w 371
    //   286: aload 12
    //   288: invokestatic 73	com/tencent/feedback/common/e:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   291: aload_1
    //   292: invokestatic 373	com/tencent/feedback/upload/g:b	(Lcom/tencent/feedback/upload/AbstractUploadDatas;)[B
    //   295: astore 13
    //   297: aload 13
    //   299: ifnonnull +26 -> 325
    //   302: ldc_w 375
    //   305: iconst_0
    //   306: anewarray 4	java/lang/Object
    //   309: invokestatic 261	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   312: aload_0
    //   313: iload_3
    //   314: iconst_m1
    //   315: lconst_0
    //   316: lconst_0
    //   317: iconst_0
    //   318: ldc_w 377
    //   321: invokespecial 369	com/tencent/feedback/upload/g:a	(IIJJZLjava/lang/String;)V
    //   324: return
    //   325: aload_0
    //   326: invokespecial 379	com/tencent/feedback/upload/g:c	()Lcom/tencent/feedback/upload/d;
    //   329: astore 14
    //   331: aload 14
    //   333: ifnonnull +26 -> 359
    //   336: ldc_w 381
    //   339: iconst_0
    //   340: anewarray 4	java/lang/Object
    //   343: invokestatic 261	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   346: aload_0
    //   347: iload_3
    //   348: iconst_m1
    //   349: lconst_0
    //   350: lconst_0
    //   351: iconst_0
    //   352: ldc_w 383
    //   355: invokespecial 369	com/tencent/feedback/upload/g:a	(IIJJZLjava/lang/String;)V
    //   358: return
    //   359: new 385	com/tencent/feedback/upload/c
    //   362: dup
    //   363: invokespecial 386	com/tencent/feedback/upload/c:<init>	()V
    //   366: astore 15
    //   368: aload 14
    //   370: aload 11
    //   372: aload 13
    //   374: aload 15
    //   376: invokevirtual 389	com/tencent/feedback/upload/d:a	(Ljava/lang/String;[BLcom/tencent/feedback/upload/c;)[B
    //   379: astore 20
    //   381: iconst_0
    //   382: istore 8
    //   384: aload 20
    //   386: ifnonnull +84 -> 470
    //   389: aload_0
    //   390: iconst_1
    //   391: aload_0
    //   392: invokespecial 391	com/tencent/feedback/upload/g:a	()I
    //   395: iadd
    //   396: invokespecial 393	com/tencent/feedback/upload/g:a	(I)V
    //   399: aload 15
    //   401: invokevirtual 396	com/tencent/feedback/upload/c:a	()J
    //   404: lstore 4
    //   406: aload 15
    //   408: invokevirtual 398	com/tencent/feedback/upload/c:b	()J
    //   411: lstore 6
    //   413: aload 20
    //   415: invokestatic 400	com/tencent/feedback/upload/g:a	([B)Lcommon/ResponsePackage;
    //   418: astore 21
    //   420: iconst_0
    //   421: istore 8
    //   423: aload 21
    //   425: ifnull +678 -> 1103
    //   428: aload 21
    //   430: invokevirtual 403	common/ResponsePackage:getCmd	()I
    //   433: istore 9
    //   435: aload 21
    //   437: getfield 407	common/ResponsePackage:result	B
    //   440: ifne +96 -> 536
    //   443: iconst_1
    //   444: istore 22
    //   446: goto +653 -> 1099
    //   449: aload_0
    //   450: iload_3
    //   451: iload 9
    //   453: lload 4
    //   455: lload 6
    //   457: iload 8
    //   459: aconst_null
    //   460: invokespecial 369	com/tencent/feedback/upload/g:a	(IIJJZLjava/lang/String;)V
    //   463: aload_1
    //   464: iload 8
    //   466: invokevirtual 335	com/tencent/feedback/upload/AbstractUploadDatas:done	(Z)V
    //   469: return
    //   470: aload_0
    //   471: iconst_0
    //   472: invokespecial 393	com/tencent/feedback/upload/g:a	(I)V
    //   475: goto -76 -> 399
    //   478: astore 17
    //   480: iload 8
    //   482: istore 18
    //   484: aload 17
    //   486: invokevirtual 125	java/lang/Throwable:printStackTrace	()V
    //   489: aload_0
    //   490: iload_3
    //   491: iload 9
    //   493: lload 4
    //   495: lload 6
    //   497: iconst_0
    //   498: aload 17
    //   500: invokevirtual 408	java/lang/Throwable:toString	()Ljava/lang/String;
    //   503: invokespecial 369	com/tencent/feedback/upload/g:a	(IIJJZLjava/lang/String;)V
    //   506: iconst_1
    //   507: anewarray 4	java/lang/Object
    //   510: astore 19
    //   512: aload 19
    //   514: iconst_0
    //   515: aload 17
    //   517: invokevirtual 408	java/lang/Throwable:toString	()Ljava/lang/String;
    //   520: aastore
    //   521: ldc_w 410
    //   524: aload 19
    //   526: invokestatic 261	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   529: aload_1
    //   530: iload 18
    //   532: invokevirtual 335	com/tencent/feedback/upload/AbstractUploadDatas:done	(Z)V
    //   535: return
    //   536: iconst_0
    //   537: istore 22
    //   539: goto +560 -> 1099
    //   542: invokestatic 416	com/tencent/feedback/common/c:p	()Lcom/tencent/feedback/common/c;
    //   545: astore 23
    //   547: aload 23
    //   549: ifnull +73 -> 622
    //   552: aload 23
    //   554: aload 21
    //   556: invokevirtual 419	common/ResponsePackage:getSrcGatewayIp	()Ljava/lang/String;
    //   559: invokevirtual 421	com/tencent/feedback/common/c:b	(Ljava/lang/String;)V
    //   562: new 423	java/util/Date
    //   565: dup
    //   566: invokespecial 424	java/util/Date:<init>	()V
    //   569: astore 24
    //   571: aload 23
    //   573: aload 21
    //   575: invokevirtual 427	common/ResponsePackage:getServerTime	()J
    //   578: aload 24
    //   580: invokevirtual 430	java/util/Date:getTime	()J
    //   583: lsub
    //   584: invokevirtual 433	com/tencent/feedback/common/c:a	(J)V
    //   587: iconst_2
    //   588: anewarray 4	java/lang/Object
    //   591: astore 25
    //   593: aload 25
    //   595: iconst_0
    //   596: aload 23
    //   598: invokevirtual 435	com/tencent/feedback/common/c:h	()Ljava/lang/String;
    //   601: aastore
    //   602: aload 25
    //   604: iconst_1
    //   605: aload 23
    //   607: invokevirtual 438	com/tencent/feedback/common/c:i	()J
    //   610: invokestatic 443	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   613: aastore
    //   614: ldc_w 445
    //   617: aload 25
    //   619: invokestatic 73	com/tencent/feedback/common/e:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   622: aload 21
    //   624: invokevirtual 448	common/ResponsePackage:getSBuffer	()[B
    //   627: astore 26
    //   629: aload 26
    //   631: ifnonnull +27 -> 658
    //   634: ldc_w 450
    //   637: iconst_0
    //   638: anewarray 4	java/lang/Object
    //   641: invokestatic 73	com/tencent/feedback/common/e:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   644: goto -195 -> 449
    //   647: astore 16
    //   649: aload_1
    //   650: iload 8
    //   652: invokevirtual 335	com/tencent/feedback/upload/AbstractUploadDatas:done	(Z)V
    //   655: aload 16
    //   657: athrow
    //   658: aload 26
    //   660: aload 21
    //   662: invokevirtual 454	common/ResponsePackage:getZipType	()B
    //   665: aload 21
    //   667: invokevirtual 457	common/ResponsePackage:getEncryType	()B
    //   670: aload_0
    //   671: getfield 44	com/tencent/feedback/upload/g:e	Landroid/content/Context;
    //   674: invokestatic 462	com/tencent/feedback/common/b/c:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/b/c;
    //   677: invokevirtual 465	com/tencent/feedback/common/b/c:b	()Lcom/tencent/feedback/common/b/f;
    //   680: invokevirtual 469	com/tencent/feedback/common/b/f:e	()Ljava/lang/String;
    //   683: invokestatic 474	com/tencent/feedback/anr/a:b	([BIILjava/lang/String;)[B
    //   686: astore 27
    //   688: aload_0
    //   689: invokespecial 476	com/tencent/feedback/upload/g:d	()Landroid/util/SparseArray;
    //   692: astore 28
    //   694: aload 28
    //   696: ifnull +11 -> 707
    //   699: aload 28
    //   701: invokevirtual 321	android/util/SparseArray:size	()I
    //   704: ifgt +16 -> 720
    //   707: ldc_w 478
    //   710: iconst_0
    //   711: anewarray 4	java/lang/Object
    //   714: invokestatic 73	com/tencent/feedback/common/e:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   717: goto -268 -> 449
    //   720: aload_1
    //   721: invokevirtual 330	com/tencent/feedback/upload/AbstractUploadDatas:b	()I
    //   724: istore 29
    //   726: aload 21
    //   728: invokevirtual 403	common/ResponsePackage:getCmd	()I
    //   731: istore 30
    //   733: iload 30
    //   735: ifne +380 -> 1115
    //   738: ldc_w 480
    //   741: iconst_0
    //   742: anewarray 4	java/lang/Object
    //   745: invokestatic 73	com/tencent/feedback/common/e:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   748: goto -299 -> 449
    //   751: iconst_1
    //   752: anewarray 4	java/lang/Object
    //   755: astore 39
    //   757: aload 39
    //   759: iconst_0
    //   760: iload 29
    //   762: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   765: aastore
    //   766: ldc_w 482
    //   769: aload 39
    //   771: invokestatic 149	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   774: goto -325 -> 449
    //   777: iload 30
    //   779: sipush 305
    //   782: if_icmpeq +294 -> 1076
    //   785: iconst_2
    //   786: anewarray 4	java/lang/Object
    //   789: astore 38
    //   791: aload 38
    //   793: iconst_0
    //   794: iload 29
    //   796: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   799: aastore
    //   800: aload 38
    //   802: iconst_1
    //   803: iload 30
    //   805: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   808: aastore
    //   809: ldc_w 484
    //   812: aload 38
    //   814: invokestatic 149	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   817: goto -368 -> 449
    //   820: iload 30
    //   822: sipush 302
    //   825: if_icmpeq +251 -> 1076
    //   828: iconst_2
    //   829: anewarray 4	java/lang/Object
    //   832: astore 37
    //   834: aload 37
    //   836: iconst_0
    //   837: iload 29
    //   839: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   842: aastore
    //   843: aload 37
    //   845: iconst_1
    //   846: iload 30
    //   848: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   851: aastore
    //   852: ldc_w 484
    //   855: aload 37
    //   857: invokestatic 149	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   860: goto -411 -> 449
    //   863: iload 30
    //   865: sipush 304
    //   868: if_icmpeq +208 -> 1076
    //   871: iconst_2
    //   872: anewarray 4	java/lang/Object
    //   875: astore 36
    //   877: aload 36
    //   879: iconst_0
    //   880: iload 29
    //   882: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   885: aastore
    //   886: aload 36
    //   888: iconst_1
    //   889: iload 30
    //   891: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   894: aastore
    //   895: ldc_w 484
    //   898: aload 36
    //   900: invokestatic 149	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   903: goto -454 -> 449
    //   906: iload 30
    //   908: sipush 301
    //   911: if_icmpeq +165 -> 1076
    //   914: iconst_2
    //   915: anewarray 4	java/lang/Object
    //   918: astore 35
    //   920: aload 35
    //   922: iconst_0
    //   923: iload 29
    //   925: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   928: aastore
    //   929: aload 35
    //   931: iconst_1
    //   932: iload 30
    //   934: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   937: aastore
    //   938: ldc_w 484
    //   941: aload 35
    //   943: invokestatic 149	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   946: goto -497 -> 449
    //   949: iload 30
    //   951: sipush 303
    //   954: if_icmpeq +122 -> 1076
    //   957: iconst_2
    //   958: anewarray 4	java/lang/Object
    //   961: astore 34
    //   963: aload 34
    //   965: iconst_0
    //   966: iload 29
    //   968: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   971: aastore
    //   972: aload 34
    //   974: iconst_1
    //   975: iload 30
    //   977: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   980: aastore
    //   981: ldc_w 484
    //   984: aload 34
    //   986: invokestatic 149	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   989: goto -540 -> 449
    //   992: iload 30
    //   994: bipush 11
    //   996: if_icmpeq +80 -> 1076
    //   999: iconst_2
    //   1000: anewarray 4	java/lang/Object
    //   1003: astore 33
    //   1005: aload 33
    //   1007: iconst_0
    //   1008: iload 29
    //   1010: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1013: aastore
    //   1014: aload 33
    //   1016: iconst_1
    //   1017: iload 30
    //   1019: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1022: aastore
    //   1023: ldc_w 484
    //   1026: aload 33
    //   1028: invokestatic 149	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1031: goto -582 -> 449
    //   1034: iload 30
    //   1036: bipush 15
    //   1038: if_icmpeq +38 -> 1076
    //   1041: iconst_2
    //   1042: anewarray 4	java/lang/Object
    //   1045: astore 31
    //   1047: aload 31
    //   1049: iconst_0
    //   1050: iload 29
    //   1052: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1055: aastore
    //   1056: aload 31
    //   1058: iconst_1
    //   1059: iload 30
    //   1061: invokestatic 145	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1064: aastore
    //   1065: ldc_w 484
    //   1068: aload 31
    //   1070: invokestatic 149	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1073: goto -624 -> 449
    //   1076: aload_0
    //   1077: aload 28
    //   1079: iload 30
    //   1081: aload 27
    //   1083: invokespecial 193	com/tencent/feedback/upload/g:a	(Landroid/util/SparseArray;I[B)Z
    //   1086: pop
    //   1087: goto -638 -> 449
    //   1090: astore 16
    //   1092: iload 18
    //   1094: istore 8
    //   1096: goto -447 -> 649
    //   1099: iload 22
    //   1101: istore 8
    //   1103: aload_1
    //   1104: ifnull -655 -> 449
    //   1107: aload 21
    //   1109: ifnonnull -567 -> 542
    //   1112: goto -663 -> 449
    //   1115: iload 29
    //   1117: lookupswitch	default:+-366->751, 8:+-125->992, 12:+-125->992, 15:+-83->1034, 200:+-340->777, 201:+-211->906, 202:+-297->820, 203:+-168->949, 204:+-254->863, 205:+-168->949, 206:+-297->820, 207:+-254->863
    //
    // Exception table:
    //   from	to	target	type
    //   359	381	478	java/lang/Throwable
    //   389	399	478	java/lang/Throwable
    //   399	420	478	java/lang/Throwable
    //   428	443	478	java/lang/Throwable
    //   449	463	478	java/lang/Throwable
    //   470	475	478	java/lang/Throwable
    //   542	547	478	java/lang/Throwable
    //   552	622	478	java/lang/Throwable
    //   622	629	478	java/lang/Throwable
    //   634	644	478	java/lang/Throwable
    //   658	694	478	java/lang/Throwable
    //   699	707	478	java/lang/Throwable
    //   707	717	478	java/lang/Throwable
    //   720	733	478	java/lang/Throwable
    //   738	748	478	java/lang/Throwable
    //   751	774	478	java/lang/Throwable
    //   785	817	478	java/lang/Throwable
    //   828	860	478	java/lang/Throwable
    //   871	903	478	java/lang/Throwable
    //   914	946	478	java/lang/Throwable
    //   957	989	478	java/lang/Throwable
    //   999	1031	478	java/lang/Throwable
    //   1041	1073	478	java/lang/Throwable
    //   1076	1087	478	java/lang/Throwable
    //   359	381	647	finally
    //   389	399	647	finally
    //   399	420	647	finally
    //   428	443	647	finally
    //   449	463	647	finally
    //   470	475	647	finally
    //   542	547	647	finally
    //   552	622	647	finally
    //   622	629	647	finally
    //   634	644	647	finally
    //   658	694	647	finally
    //   699	707	647	finally
    //   707	717	647	finally
    //   720	733	647	finally
    //   738	748	647	finally
    //   751	774	647	finally
    //   785	817	647	finally
    //   828	860	647	finally
    //   871	903	647	finally
    //   914	946	647	finally
    //   957	989	647	finally
    //   999	1031	647	finally
    //   1041	1073	647	finally
    //   1076	1087	647	finally
    //   484	529	1090	finally
  }

  public final boolean a(int paramInt, e parame)
  {
    monitorenter;
    int i;
    if (parame == null)
      i = 0;
    while (true)
    {
      monitorexit;
      return i;
      try
      {
        this.b.append(paramInt, parame);
        i = 1;
      }
      finally
      {
        monitorexit;
      }
    }
  }

  public final boolean a(UploadHandleListener paramUploadHandleListener)
  {
    monitorenter;
    int i;
    if (paramUploadHandleListener == null)
      i = 0;
    while (true)
    {
      monitorexit;
      return i;
      try
      {
        if (!this.c.contains(paramUploadHandleListener))
          this.c.add(paramUploadHandleListener);
        i = 1;
      }
      finally
      {
        monitorexit;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.upload.g
 * JD-Core Version:    0.6.0
 */