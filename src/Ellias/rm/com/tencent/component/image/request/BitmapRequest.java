package com.tencent.component.image.request;

import android.graphics.Bitmap.Config;
import com.tencent.component.cache.image.ImageEntry;

public final class BitmapRequest extends ImageRequest
{
  public BitmapRequest(ImageEntry paramImageEntry, ImageRequest.Callback paramCallback, Bitmap.Config paramConfig)
  {
    super(paramImageEntry, paramCallback, paramConfig);
  }

  // ERROR //
  public com.tencent.component.cache.image.ImageResult a(com.tencent.component.utils.thread.ThreadPool.JobContext paramJobContext)
  {
    // Byte code:
    //   0: new 14	com/tencent/component/cache/image/ImageResult
    //   3: dup
    //   4: invokespecial 17	com/tencent/component/cache/image/ImageResult:<init>	()V
    //   7: astore_2
    //   8: aload_1
    //   9: invokeinterface 23 1 0
    //   14: ifeq +5 -> 19
    //   17: aload_2
    //   18: areturn
    //   19: aload_0
    //   20: invokevirtual 26	com/tencent/component/image/request/BitmapRequest:a	()Lcom/tencent/component/cache/image/ImageEntry;
    //   23: astore_3
    //   24: aload_0
    //   25: invokevirtual 30	com/tencent/component/image/request/BitmapRequest:b	()Lcom/tencent/component/image/request/ImageRequest$Callback;
    //   28: astore 4
    //   30: aload 4
    //   32: aload_3
    //   33: invokeinterface 35 2 0
    //   38: astore 5
    //   40: aload 5
    //   42: ifnull +19 -> 61
    //   45: aload 5
    //   47: invokevirtual 40	com/tencent/component/image/image/Image:c	()Z
    //   50: ifne +11 -> 61
    //   53: aload_2
    //   54: aload 5
    //   56: invokevirtual 43	com/tencent/component/cache/image/ImageResult:a	(Lcom/tencent/component/image/image/Image;)V
    //   59: aload_2
    //   60: areturn
    //   61: aload_1
    //   62: iconst_1
    //   63: invokeinterface 47 2 0
    //   68: pop
    //   69: new 49	android/graphics/BitmapFactory$Options
    //   72: dup
    //   73: invokespecial 50	android/graphics/BitmapFactory$Options:<init>	()V
    //   76: astore 7
    //   78: aload 7
    //   80: aload_0
    //   81: invokevirtual 53	com/tencent/component/image/request/BitmapRequest:c	()Landroid/graphics/Bitmap$Config;
    //   84: putfield 57	android/graphics/BitmapFactory$Options:inPreferredConfig	Landroid/graphics/Bitmap$Config;
    //   87: aload 7
    //   89: getfield 57	android/graphics/BitmapFactory$Options:inPreferredConfig	Landroid/graphics/Bitmap$Config;
    //   92: getstatic 62	android/graphics/Bitmap$Config:RGB_565	Landroid/graphics/Bitmap$Config;
    //   95: if_acmpne +9 -> 104
    //   98: aload 7
    //   100: iconst_1
    //   101: putfield 66	android/graphics/BitmapFactory$Options:inDither	Z
    //   104: aload 4
    //   106: invokeinterface 69 1 0
    //   111: astore 8
    //   113: aload 4
    //   115: aload_3
    //   116: aload 8
    //   118: invokeinterface 72 3 0
    //   123: istore 20
    //   125: aload_1
    //   126: invokeinterface 23 1 0
    //   131: istore 21
    //   133: iload 21
    //   135: ifeq +14 -> 149
    //   138: aload 4
    //   140: aload 8
    //   142: invokeinterface 75 2 0
    //   147: aload_2
    //   148: areturn
    //   149: iload 20
    //   151: ifeq +54 -> 205
    //   154: aload 8
    //   156: getfield 80	com/tencent/component/cache/common/BytesBufferPool$BytesBuffer:a	[B
    //   159: aload 8
    //   161: getfield 83	com/tencent/component/cache/common/BytesBufferPool$BytesBuffer:b	I
    //   164: aload 8
    //   166: getfield 85	com/tencent/component/cache/common/BytesBufferPool$BytesBuffer:c	I
    //   169: aload 7
    //   171: invokestatic 90	com/tencent/component/utils/DecodeUtils:a	([BIILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   174: astore 22
    //   176: aload 22
    //   178: ifnull +27 -> 205
    //   181: aload_2
    //   182: new 92	com/tencent/component/image/image/BitmapImage
    //   185: dup
    //   186: aload 22
    //   188: invokespecial 95	com/tencent/component/image/image/BitmapImage:<init>	(Landroid/graphics/Bitmap;)V
    //   191: invokevirtual 43	com/tencent/component/cache/image/ImageResult:a	(Lcom/tencent/component/image/image/Image;)V
    //   194: aload 4
    //   196: aload 8
    //   198: invokeinterface 75 2 0
    //   203: aload_2
    //   204: areturn
    //   205: aload 4
    //   207: aload 8
    //   209: invokeinterface 75 2 0
    //   214: aload_3
    //   215: getfield 100	com/tencent/component/cache/image/ImageEntry:a	Ljava/lang/String;
    //   218: astore 11
    //   220: aload 7
    //   222: aload_3
    //   223: getfield 101	com/tencent/component/cache/image/ImageEntry:b	I
    //   226: putfield 104	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   229: aload 11
    //   231: aload 7
    //   233: invokestatic 107	com/tencent/component/utils/DecodeUtils:a	(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   236: astore 19
    //   238: aload 19
    //   240: astore 13
    //   242: aload 13
    //   244: ifnonnull +118 -> 362
    //   247: aload_2
    //   248: invokevirtual 110	com/tencent/component/cache/image/ImageResult:b	()Ljava/lang/Throwable;
    //   251: ifnonnull -234 -> 17
    //   254: new 112	java/io/File
    //   257: dup
    //   258: aload 11
    //   260: invokespecial 115	java/io/File:<init>	(Ljava/lang/String;)V
    //   263: astore 18
    //   265: aload_2
    //   266: new 117	com/tencent/component/cache/image/ImageDecodeException
    //   269: dup
    //   270: new 119	java/lang/StringBuilder
    //   273: dup
    //   274: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   277: ldc 122
    //   279: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: aload 18
    //   284: invokevirtual 130	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   287: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: ldc 132
    //   292: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   295: aload 18
    //   297: invokevirtual 136	java/io/File:length	()J
    //   300: invokevirtual 139	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   303: invokevirtual 142	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   306: invokespecial 143	com/tencent/component/cache/image/ImageDecodeException:<init>	(Ljava/lang/String;)V
    //   309: invokevirtual 146	com/tencent/component/cache/image/ImageResult:a	(Ljava/lang/Throwable;)V
    //   312: aload_2
    //   313: areturn
    //   314: astore 10
    //   316: aload_2
    //   317: aload 10
    //   319: invokevirtual 146	com/tencent/component/cache/image/ImageResult:a	(Ljava/lang/Throwable;)V
    //   322: aload 4
    //   324: aload 8
    //   326: invokeinterface 75 2 0
    //   331: goto -117 -> 214
    //   334: astore 9
    //   336: aload 4
    //   338: aload 8
    //   340: invokeinterface 75 2 0
    //   345: aload 9
    //   347: athrow
    //   348: astore 12
    //   350: aload_2
    //   351: aload 12
    //   353: invokevirtual 146	com/tencent/component/cache/image/ImageResult:a	(Ljava/lang/Throwable;)V
    //   356: aconst_null
    //   357: astore 13
    //   359: goto -117 -> 242
    //   362: aload_1
    //   363: invokeinterface 23 1 0
    //   368: ifeq +10 -> 378
    //   371: aload 13
    //   373: invokevirtual 151	android/graphics/Bitmap:recycle	()V
    //   376: aload_2
    //   377: areturn
    //   378: aload 13
    //   380: aload 11
    //   382: invokestatic 156	com/tencent/component/utils/BitmapUtils:a	(Landroid/graphics/Bitmap;Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   385: astore 14
    //   387: aload_3
    //   388: getfield 101	com/tencent/component/cache/image/ImageEntry:b	I
    //   391: iconst_1
    //   392: if_icmple +43 -> 435
    //   395: aload 14
    //   397: invokevirtual 159	android/graphics/Bitmap:hasAlpha	()Z
    //   400: ifeq +50 -> 450
    //   403: getstatic 165	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   406: astore 16
    //   408: aload 14
    //   410: aload 16
    //   412: invokestatic 168	com/tencent/component/utils/BitmapUtils:a	(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap$CompressFormat;)[B
    //   415: astore 17
    //   417: aload_1
    //   418: invokeinterface 23 1 0
    //   423: ifeq +35 -> 458
    //   426: aload 14
    //   428: invokevirtual 151	android/graphics/Bitmap:recycle	()V
    //   431: aload_2
    //   432: areturn
    //   433: astore 15
    //   435: aload_2
    //   436: new 92	com/tencent/component/image/image/BitmapImage
    //   439: dup
    //   440: aload 14
    //   442: invokespecial 95	com/tencent/component/image/image/BitmapImage:<init>	(Landroid/graphics/Bitmap;)V
    //   445: invokevirtual 43	com/tencent/component/cache/image/ImageResult:a	(Lcom/tencent/component/image/image/Image;)V
    //   448: aload_2
    //   449: areturn
    //   450: getstatic 171	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   453: astore 16
    //   455: goto -47 -> 408
    //   458: aload 4
    //   460: aload_3
    //   461: aload 17
    //   463: invokeinterface 174 3 0
    //   468: goto -33 -> 435
    //
    // Exception table:
    //   from	to	target	type
    //   113	133	314	java/lang/Throwable
    //   154	176	314	java/lang/Throwable
    //   181	194	314	java/lang/Throwable
    //   113	133	334	finally
    //   154	176	334	finally
    //   181	194	334	finally
    //   316	322	334	finally
    //   229	238	348	java/lang/Throwable
    //   395	408	433	java/lang/Throwable
    //   408	431	433	java/lang/Throwable
    //   450	455	433	java/lang/Throwable
    //   458	468	433	java/lang/Throwable
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.request.BitmapRequest
 * JD-Core Version:    0.6.0
 */