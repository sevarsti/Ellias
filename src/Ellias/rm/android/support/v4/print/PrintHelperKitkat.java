package android.support.v4.print;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Builder;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo.Builder;
import android.print.PrintManager;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PrintHelperKitkat
{
  public static final int COLOR_MODE_COLOR = 2;
  public static final int COLOR_MODE_MONOCHROME = 1;
  private static final String LOG_TAG = "PrintHelperKitkat";
  private static final int MAX_PRINT_SIZE = 3500;
  public static final int SCALE_MODE_FILL = 2;
  public static final int SCALE_MODE_FIT = 1;
  int mColorMode = 2;
  final Context mContext;
  int mScaleMode = 2;

  PrintHelperKitkat(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private Bitmap loadBitmap(Uri paramUri, BitmapFactory.Options paramOptions)
    throws FileNotFoundException
  {
    if ((paramUri == null) || (this.mContext == null))
      throw new IllegalArgumentException("bad argument to loadBitmap");
    InputStream localInputStream = null;
    try
    {
      localInputStream = this.mContext.getContentResolver().openInputStream(paramUri);
      Bitmap localBitmap = BitmapFactory.decodeStream(localInputStream, null, paramOptions);
      if (localInputStream != null);
      try
      {
        localInputStream.close();
        return localBitmap;
      }
      catch (IOException localIOException2)
      {
        Log.w("PrintHelperKitkat", "close fail ", localIOException2);
        return localBitmap;
      }
    }
    finally
    {
      if (localInputStream == null);
    }
    try
    {
      localInputStream.close();
      throw localObject;
    }
    catch (IOException localIOException1)
    {
      while (true)
        Log.w("PrintHelperKitkat", "close fail ", localIOException1);
    }
  }

  private Bitmap loadConstrainedBitmap(Uri paramUri, int paramInt)
    throws FileNotFoundException
  {
    if ((paramInt <= 0) || (paramUri == null) || (this.mContext == null))
      throw new IllegalArgumentException("bad argument to getScaledBitmap");
    BitmapFactory.Options localOptions1 = new BitmapFactory.Options();
    localOptions1.inJustDecodeBounds = true;
    loadBitmap(paramUri, localOptions1);
    int i = localOptions1.outWidth;
    int j = localOptions1.outHeight;
    if ((i <= 0) || (j <= 0));
    int m;
    do
    {
      return null;
      int k = Math.max(i, j);
      m = 1;
      while (k > paramInt)
      {
        k >>>= 1;
        m <<= 1;
      }
    }
    while ((m <= 0) || (Math.min(i, j) / m <= 0));
    BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
    localOptions2.inMutable = true;
    localOptions2.inSampleSize = m;
    return loadBitmap(paramUri, localOptions2);
  }

  public int getColorMode()
  {
    return this.mColorMode;
  }

  public int getScaleMode()
  {
    return this.mScaleMode;
  }

  public void printBitmap(String paramString, Bitmap paramBitmap)
  {
    if (paramBitmap == null)
      return;
    int i = this.mScaleMode;
    PrintManager localPrintManager = (PrintManager)this.mContext.getSystemService("print");
    PrintAttributes.MediaSize localMediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
    if (paramBitmap.getWidth() > paramBitmap.getHeight())
      localMediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
    PrintAttributes localPrintAttributes = new PrintAttributes.Builder().setMediaSize(localMediaSize).setColorMode(this.mColorMode).build();
    localPrintManager.print(paramString, new PrintDocumentAdapter(paramString, paramBitmap, i)
    {
      private PrintAttributes mAttributes;

      public void onLayout(PrintAttributes paramPrintAttributes1, PrintAttributes paramPrintAttributes2, CancellationSignal paramCancellationSignal, PrintDocumentAdapter.LayoutResultCallback paramLayoutResultCallback, Bundle paramBundle)
      {
        int i = 1;
        this.mAttributes = paramPrintAttributes2;
        PrintDocumentInfo localPrintDocumentInfo = new PrintDocumentInfo.Builder(this.val$jobName).setContentType(i).setPageCount(i).build();
        if (!paramPrintAttributes2.equals(paramPrintAttributes1));
        while (true)
        {
          paramLayoutResultCallback.onLayoutFinished(localPrintDocumentInfo, i);
          return;
          int j = 0;
        }
      }

      // ERROR //
      public void onWrite(android.print.PageRange[] paramArrayOfPageRange, android.os.ParcelFileDescriptor paramParcelFileDescriptor, CancellationSignal paramCancellationSignal, android.print.PrintDocumentAdapter.WriteResultCallback paramWriteResultCallback)
      {
        // Byte code:
        //   0: new 70	android/print/pdf/PrintedPdfDocument
        //   3: dup
        //   4: aload_0
        //   5: getfield 23	android/support/v4/print/PrintHelperKitkat$1:this$0	Landroid/support/v4/print/PrintHelperKitkat;
        //   8: getfield 74	android/support/v4/print/PrintHelperKitkat:mContext	Landroid/content/Context;
        //   11: aload_0
        //   12: getfield 36	android/support/v4/print/PrintHelperKitkat$1:mAttributes	Landroid/print/PrintAttributes;
        //   15: invokespecial 77	android/print/pdf/PrintedPdfDocument:<init>	(Landroid/content/Context;Landroid/print/PrintAttributes;)V
        //   18: astore 5
        //   20: aload 5
        //   22: iconst_1
        //   23: invokevirtual 81	android/print/pdf/PrintedPdfDocument:startPage	(I)Landroid/graphics/pdf/PdfDocument$Page;
        //   26: astore 8
        //   28: new 83	android/graphics/RectF
        //   31: dup
        //   32: aload 8
        //   34: invokevirtual 89	android/graphics/pdf/PdfDocument$Page:getInfo	()Landroid/graphics/pdf/PdfDocument$PageInfo;
        //   37: invokevirtual 95	android/graphics/pdf/PdfDocument$PageInfo:getContentRect	()Landroid/graphics/Rect;
        //   40: invokespecial 98	android/graphics/RectF:<init>	(Landroid/graphics/Rect;)V
        //   43: astore 9
        //   45: new 100	android/graphics/Matrix
        //   48: dup
        //   49: invokespecial 101	android/graphics/Matrix:<init>	()V
        //   52: astore 10
        //   54: aload 9
        //   56: invokevirtual 105	android/graphics/RectF:width	()F
        //   59: aload_0
        //   60: getfield 27	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
        //   63: invokevirtual 111	android/graphics/Bitmap:getWidth	()I
        //   66: i2f
        //   67: fdiv
        //   68: fstore 11
        //   70: aload_0
        //   71: getfield 29	android/support/v4/print/PrintHelperKitkat$1:val$fittingMode	I
        //   74: iconst_2
        //   75: if_icmpne +155 -> 230
        //   78: fload 11
        //   80: aload 9
        //   82: invokevirtual 114	android/graphics/RectF:height	()F
        //   85: aload_0
        //   86: getfield 27	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
        //   89: invokevirtual 117	android/graphics/Bitmap:getHeight	()I
        //   92: i2f
        //   93: fdiv
        //   94: invokestatic 123	java/lang/Math:max	(FF)F
        //   97: fstore 12
        //   99: aload 10
        //   101: fload 12
        //   103: fload 12
        //   105: invokevirtual 127	android/graphics/Matrix:postScale	(FF)Z
        //   108: pop
        //   109: aload 10
        //   111: aload 9
        //   113: invokevirtual 105	android/graphics/RectF:width	()F
        //   116: fload 12
        //   118: aload_0
        //   119: getfield 27	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
        //   122: invokevirtual 111	android/graphics/Bitmap:getWidth	()I
        //   125: i2f
        //   126: fmul
        //   127: fsub
        //   128: fconst_2
        //   129: fdiv
        //   130: aload 9
        //   132: invokevirtual 114	android/graphics/RectF:height	()F
        //   135: fload 12
        //   137: aload_0
        //   138: getfield 27	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
        //   141: invokevirtual 117	android/graphics/Bitmap:getHeight	()I
        //   144: i2f
        //   145: fmul
        //   146: fsub
        //   147: fconst_2
        //   148: fdiv
        //   149: invokevirtual 130	android/graphics/Matrix:postTranslate	(FF)Z
        //   152: pop
        //   153: aload 8
        //   155: invokevirtual 134	android/graphics/pdf/PdfDocument$Page:getCanvas	()Landroid/graphics/Canvas;
        //   158: aload_0
        //   159: getfield 27	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
        //   162: aload 10
        //   164: aconst_null
        //   165: invokevirtual 140	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
        //   168: aload 5
        //   170: aload 8
        //   172: invokevirtual 144	android/print/pdf/PrintedPdfDocument:finishPage	(Landroid/graphics/pdf/PdfDocument$Page;)V
        //   175: aload 5
        //   177: new 146	java/io/FileOutputStream
        //   180: dup
        //   181: aload_2
        //   182: invokevirtual 152	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
        //   185: invokespecial 155	java/io/FileOutputStream:<init>	(Ljava/io/FileDescriptor;)V
        //   188: invokevirtual 159	android/print/pdf/PrintedPdfDocument:writeTo	(Ljava/io/OutputStream;)V
        //   191: iconst_1
        //   192: anewarray 161	android/print/PageRange
        //   195: astore 18
        //   197: aload 18
        //   199: iconst_0
        //   200: getstatic 165	android/print/PageRange:ALL_PAGES	Landroid/print/PageRange;
        //   203: aastore
        //   204: aload 4
        //   206: aload 18
        //   208: invokevirtual 171	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFinished	([Landroid/print/PageRange;)V
        //   211: aload 5
        //   213: ifnull +8 -> 221
        //   216: aload 5
        //   218: invokevirtual 174	android/print/pdf/PrintedPdfDocument:close	()V
        //   221: aload_2
        //   222: ifnull +7 -> 229
        //   225: aload_2
        //   226: invokevirtual 175	android/os/ParcelFileDescriptor:close	()V
        //   229: return
        //   230: fload 11
        //   232: aload 9
        //   234: invokevirtual 114	android/graphics/RectF:height	()F
        //   237: aload_0
        //   238: getfield 27	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
        //   241: invokevirtual 117	android/graphics/Bitmap:getHeight	()I
        //   244: i2f
        //   245: fdiv
        //   246: invokestatic 178	java/lang/Math:min	(FF)F
        //   249: fstore 12
        //   251: goto -152 -> 99
        //   254: astore 15
        //   256: ldc 180
        //   258: ldc 182
        //   260: aload 15
        //   262: invokestatic 188	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   265: pop
        //   266: aload 4
        //   268: aconst_null
        //   269: invokevirtual 192	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFailed	(Ljava/lang/CharSequence;)V
        //   272: goto -61 -> 211
        //   275: astore 6
        //   277: aload 5
        //   279: ifnull +8 -> 287
        //   282: aload 5
        //   284: invokevirtual 174	android/print/pdf/PrintedPdfDocument:close	()V
        //   287: aload_2
        //   288: ifnull +7 -> 295
        //   291: aload_2
        //   292: invokevirtual 175	android/os/ParcelFileDescriptor:close	()V
        //   295: aload 6
        //   297: athrow
        //   298: astore 17
        //   300: return
        //   301: astore 7
        //   303: goto -8 -> 295
        //
        // Exception table:
        //   from	to	target	type
        //   175	211	254	java/io/IOException
        //   20	99	275	finally
        //   99	175	275	finally
        //   175	211	275	finally
        //   230	251	275	finally
        //   256	272	275	finally
        //   225	229	298	java/io/IOException
        //   291	295	301	java/io/IOException
      }
    }
    , localPrintAttributes);
  }

  public void printBitmap(String paramString, Uri paramUri)
    throws FileNotFoundException
  {
    printBitmap(paramString, loadConstrainedBitmap(paramUri, 3500));
  }

  public void setColorMode(int paramInt)
  {
    this.mColorMode = paramInt;
  }

  public void setScaleMode(int paramInt)
  {
    this.mScaleMode = paramInt;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     android.support.v4.print.PrintHelperKitkat
 * JD-Core Version:    0.6.0
 */