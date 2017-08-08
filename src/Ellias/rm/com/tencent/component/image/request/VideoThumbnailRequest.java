package com.tencent.component.image.request;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import com.tencent.component.cache.image.ImageEntry;
import com.tencent.component.utils.AssertUtil;
import com.tencent.component.utils.PlatformUtil;
import java.io.File;

public class VideoThumbnailRequest extends ImageRequest
{
  private static final int a = 1;
  private static final String[] b = { "DISTINCT _id", "_data" };
  private static final String c = "file://";
  private final Context d;

  public VideoThumbnailRequest(Context paramContext, ImageEntry paramImageEntry, ImageRequest.Callback paramCallback)
  {
    super(paramImageEntry, paramCallback, null);
    if (paramContext != null);
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      this.d = paramContext;
      return;
    }
  }

  private Bitmap b(String paramString)
  {
    if (PlatformUtil.version() < 8)
      return null;
    return ThumbnailUtils.createVideoThumbnail(paramString, 1);
  }

  // ERROR //
  private Bitmap c(String paramString)
  {
    // Byte code:
    //   0: invokestatic 44	com/tencent/component/utils/PlatformUtil:version	()I
    //   3: istore_2
    //   4: aconst_null
    //   5: astore_3
    //   6: iload_2
    //   7: iconst_5
    //   8: if_icmpge +5 -> 13
    //   11: aload_3
    //   12: areturn
    //   13: aload_0
    //   14: aload_1
    //   15: invokevirtual 53	com/tencent/component/image/request/VideoThumbnailRequest:a	(Ljava/lang/String;)Landroid/database/Cursor;
    //   18: astore 7
    //   20: aload 7
    //   22: astore 5
    //   24: aconst_null
    //   25: astore_3
    //   26: aload 5
    //   28: ifnull +72 -> 100
    //   31: aload 5
    //   33: invokeinterface 58 1 0
    //   38: istore 8
    //   40: aconst_null
    //   41: astore_3
    //   42: iload 8
    //   44: ifle +56 -> 100
    //   47: aload 5
    //   49: aload 5
    //   51: ldc 60
    //   53: invokeinterface 64 2 0
    //   58: invokeinterface 68 2 0
    //   63: lstore 9
    //   65: aload 5
    //   67: invokeinterface 72 1 0
    //   72: istore 11
    //   74: aconst_null
    //   75: astore_3
    //   76: iload 11
    //   78: ifeq +22 -> 100
    //   81: aload_0
    //   82: getfield 37	com/tencent/component/image/request/VideoThumbnailRequest:d	Landroid/content/Context;
    //   85: invokevirtual 78	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   88: lload 9
    //   90: iconst_1
    //   91: aconst_null
    //   92: invokestatic 84	android/provider/MediaStore$Video$Thumbnails:getThumbnail	(Landroid/content/ContentResolver;JILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   95: astore 12
    //   97: aload 12
    //   99: astore_3
    //   100: aload 5
    //   102: ifnull -91 -> 11
    //   105: aload 5
    //   107: invokeinterface 87 1 0
    //   112: aload_3
    //   113: areturn
    //   114: astore 4
    //   116: aconst_null
    //   117: astore 5
    //   119: aload 4
    //   121: astore 6
    //   123: aload 5
    //   125: ifnull +10 -> 135
    //   128: aload 5
    //   130: invokeinterface 87 1 0
    //   135: aload 6
    //   137: athrow
    //   138: astore 6
    //   140: goto -17 -> 123
    //
    // Exception table:
    //   from	to	target	type
    //   13	20	114	finally
    //   31	40	138	finally
    //   47	74	138	finally
    //   81	97	138	finally
  }

  private static String d(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      paramString = null;
    do
    {
      return paramString;
      if (!paramString.startsWith("file://"))
        continue;
      paramString = paramString.substring("file://".length());
    }
    while (paramString.startsWith(File.separator));
    return File.separator + paramString;
  }

  public Cursor a(String paramString)
  {
    String str = "_data='" + d(paramString) + "'" + " COLLATE NOCASE";
    return this.d.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, b, str, null, null);
  }

  // ERROR //
  public com.tencent.component.cache.image.ImageResult a(com.tencent.component.utils.thread.ThreadPool.JobContext paramJobContext)
  {
    // Byte code:
    //   0: new 147	com/tencent/component/cache/image/ImageResult
    //   3: dup
    //   4: invokespecial 148	com/tencent/component/cache/image/ImageResult:<init>	()V
    //   7: astore_2
    //   8: aload_1
    //   9: invokeinterface 153 1 0
    //   14: ifeq +5 -> 19
    //   17: aload_2
    //   18: areturn
    //   19: aload_0
    //   20: invokevirtual 156	com/tencent/component/image/request/VideoThumbnailRequest:a	()Lcom/tencent/component/cache/image/ImageEntry;
    //   23: astore_3
    //   24: aload_0
    //   25: invokevirtual 159	com/tencent/component/image/request/VideoThumbnailRequest:b	()Lcom/tencent/component/image/request/ImageRequest$Callback;
    //   28: aload_3
    //   29: invokeinterface 164 2 0
    //   34: astore 4
    //   36: aload 4
    //   38: ifnull +19 -> 57
    //   41: aload 4
    //   43: invokevirtual 168	com/tencent/component/image/image/Image:c	()Z
    //   46: ifne +11 -> 57
    //   49: aload_2
    //   50: aload 4
    //   52: invokevirtual 171	com/tencent/component/cache/image/ImageResult:a	(Lcom/tencent/component/image/image/Image;)V
    //   55: aload_2
    //   56: areturn
    //   57: aload_1
    //   58: iconst_1
    //   59: invokeinterface 175 2 0
    //   64: pop
    //   65: aconst_null
    //   66: astore 6
    //   68: aload_0
    //   69: aload_3
    //   70: getfield 179	com/tencent/component/cache/image/ImageEntry:a	Ljava/lang/String;
    //   73: invokespecial 181	com/tencent/component/image/request/VideoThumbnailRequest:b	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   76: astore 10
    //   78: aload 10
    //   80: astore 8
    //   82: aload 8
    //   84: ifnonnull +17 -> 101
    //   87: aload_0
    //   88: aload_3
    //   89: getfield 179	com/tencent/component/cache/image/ImageEntry:a	Ljava/lang/String;
    //   92: invokespecial 183	com/tencent/component/image/request/VideoThumbnailRequest:c	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   95: astore 12
    //   97: aload 12
    //   99: astore 8
    //   101: aload 8
    //   103: ifnonnull +87 -> 190
    //   106: aload_2
    //   107: invokevirtual 186	com/tencent/component/cache/image/ImageResult:b	()Ljava/lang/Throwable;
    //   110: ifnonnull +63 -> 173
    //   113: new 107	java/io/File
    //   116: dup
    //   117: aload_3
    //   118: getfield 179	com/tencent/component/cache/image/ImageEntry:a	Ljava/lang/String;
    //   121: invokespecial 189	java/io/File:<init>	(Ljava/lang/String;)V
    //   124: astore 9
    //   126: aload_2
    //   127: new 191	com/tencent/component/cache/image/ImageDecodeException
    //   130: dup
    //   131: new 112	java/lang/StringBuilder
    //   134: dup
    //   135: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   138: ldc 193
    //   140: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: aload 9
    //   145: invokevirtual 196	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   148: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: ldc 198
    //   153: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: aload 9
    //   158: invokevirtual 201	java/io/File:length	()J
    //   161: invokevirtual 204	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   164: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   167: invokespecial 205	com/tencent/component/cache/image/ImageDecodeException:<init>	(Ljava/lang/String;)V
    //   170: invokevirtual 208	com/tencent/component/cache/image/ImageResult:a	(Ljava/lang/Throwable;)V
    //   173: aload_2
    //   174: areturn
    //   175: astore 7
    //   177: aload_2
    //   178: aload 7
    //   180: invokevirtual 208	com/tencent/component/cache/image/ImageResult:a	(Ljava/lang/Throwable;)V
    //   183: aload 6
    //   185: astore 8
    //   187: goto -86 -> 101
    //   190: aload_1
    //   191: invokeinterface 153 1 0
    //   196: ifeq +10 -> 206
    //   199: aload 8
    //   201: invokevirtual 213	android/graphics/Bitmap:recycle	()V
    //   204: aload_2
    //   205: areturn
    //   206: aload_2
    //   207: new 215	com/tencent/component/image/image/BitmapImage
    //   210: dup
    //   211: aload 8
    //   213: invokespecial 218	com/tencent/component/image/image/BitmapImage:<init>	(Landroid/graphics/Bitmap;)V
    //   216: invokevirtual 171	com/tencent/component/cache/image/ImageResult:a	(Lcom/tencent/component/image/image/Image;)V
    //   219: aload_2
    //   220: areturn
    //   221: astore 11
    //   223: aload 8
    //   225: astore 6
    //   227: aload 11
    //   229: astore 7
    //   231: goto -54 -> 177
    //
    // Exception table:
    //   from	to	target	type
    //   68	78	175	java/lang/Throwable
    //   87	97	221	java/lang/Throwable
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.request.VideoThumbnailRequest
 * JD-Core Version:    0.6.0
 */