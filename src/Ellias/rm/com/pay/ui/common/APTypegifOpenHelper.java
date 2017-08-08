package com.pay.ui.common;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import java.io.InputStream;
import java.util.Vector;

public class APTypegifOpenHelper
{
  protected static final int MaxStackSize = 4096;
  public static final int STATUS_FORMAT_ERROR = 1;
  public static final int STATUS_OK = 0;
  public static final int STATUS_OPEN_ERROR = 2;
  private static APTypegifOpenHelper a = new APTypegifOpenHelper();
  protected static Bitmap image;
  protected static Bitmap lastImage;
  protected int[] act;
  protected int bgColor;
  protected int bgIndex;
  protected byte[] block = new byte[256];
  protected int blockSize = 0;
  protected int delay = 0;
  protected int dispose = 0;
  protected int frameCount;
  protected int frameindex = 0;
  protected Vector frames;
  protected int[] gct;
  protected boolean gctFlag;
  protected int gctSize;
  protected int height;
  protected int ih;
  protected InputStream in;
  protected boolean interlace;
  protected int iw;
  protected int ix;
  protected int iy;
  protected int lastBgColor;
  protected int lastDispose = 0;
  protected int[] lct;
  protected boolean lctFlag;
  protected int lctSize;
  protected int loopCount = 1;
  protected int lrh;
  protected int lrw;
  protected int lrx;
  protected int lry;
  protected int pixelAspect;
  protected byte[] pixelStack;
  protected byte[] pixels;
  protected short[] prefix;
  protected int status;
  protected byte[] suffix;
  protected int transIndex;
  protected boolean transparency = false;
  protected int width;

  public static APTypegifOpenHelper getTypeIntance()
  {
    return a;
  }

  protected void decodeImageData()
  {
    int i = this.iw * this.ih;
    if ((this.pixels == null) || (this.pixels.length < i))
      this.pixels = new byte[i];
    if (this.prefix == null)
      this.prefix = new short[4096];
    if (this.suffix == null)
      this.suffix = new byte[4096];
    if (this.pixelStack == null)
      this.pixelStack = new byte[4097];
    int j = read();
    int k = 1 << j;
    int m = k + 1;
    int n = k + 2;
    int i1 = -1;
    int i2 = j + 1;
    int i3 = -1 + (1 << i2);
    int i4 = 0;
    int i5;
    int i6;
    int i7;
    int i8;
    int i9;
    int i10;
    int i11;
    int i12;
    if (i4 >= k)
    {
      i5 = 0;
      i6 = 0;
      i7 = 0;
      i8 = 0;
      i9 = 0;
      i10 = 0;
      i11 = 0;
      i12 = 0;
      label149: if (i9 < i)
        break label190;
    }
    int i30;
    label159: label190: label221: label252: int i24;
    int i25;
    int i26;
    label393: int i28;
    do
    {
      do
      {
        do
        {
          i30 = i5;
          if (i30 < i)
            break label636;
          return;
          this.prefix[i4] = 0;
          this.suffix[i4] = (byte)i4;
          i4++;
          break;
          if (i6 != 0)
            break label661;
          if (i11 >= i2)
            break label252;
          if (i10 != 0)
            break label221;
          i10 = readBlock();
        }
        while (i10 <= 0);
        i12 = 0;
        i8 += ((0xFF & this.block[i12]) << i11);
        i11 += 8;
        i12++;
        i10--;
        break label149;
        i24 = i8 & i3;
        i8 >>= i2;
        i11 -= i2;
      }
      while ((i24 > n) || (i24 == m));
      if (i24 == k)
      {
        i2 = j + 1;
        i3 = -1 + (1 << i2);
        n = k + 2;
        i1 = -1;
        break label149;
      }
      if (i1 == -1)
      {
        byte[] arrayOfByte5 = this.pixelStack;
        int i31 = i6 + 1;
        arrayOfByte5[i6] = this.suffix[i24];
        i6 = i31;
        i1 = i24;
        i7 = i24;
        break label149;
      }
      if (i24 != n)
        break label650;
      byte[] arrayOfByte4 = this.pixelStack;
      i25 = i6 + 1;
      arrayOfByte4[i6] = (byte)i7;
      i26 = i1;
      if (i26 > k)
        break label596;
      i28 = 0xFF & this.suffix[i26];
    }
    while (n >= 4096);
    byte[] arrayOfByte3 = this.pixelStack;
    int i29 = i25 + 1;
    arrayOfByte3[i25] = (byte)i28;
    this.prefix[n] = (short)i1;
    this.suffix[n] = (byte)i28;
    int i20 = n + 1;
    if (((i20 & i3) == 0) && (i20 < 4096))
    {
      i2++;
      i3 += i20;
    }
    int i18 = i8;
    int i19 = i24;
    int i13 = i3;
    int i14 = i28;
    int i15 = i11;
    int i16 = i2;
    int i17 = i29;
    while (true)
    {
      int i21 = i17 - 1;
      byte[] arrayOfByte1 = this.pixels;
      int i22 = i5 + 1;
      arrayOfByte1[i5] = this.pixelStack[i21];
      i9++;
      i5 = i22;
      i2 = i16;
      i11 = i15;
      i7 = i14;
      i3 = i13;
      int i23 = i19;
      i8 = i18;
      i6 = i21;
      n = i20;
      i1 = i23;
      break;
      label596: byte[] arrayOfByte2 = this.pixelStack;
      int i27 = i25 + 1;
      arrayOfByte2[i25] = this.suffix[i26];
      i26 = this.prefix[i26];
      i25 = i27;
      break label393;
      label636: this.pixels[i30] = 0;
      i30++;
      break label159;
      label650: i25 = i6;
      i26 = i24;
      break label393;
      label661: i13 = i3;
      i14 = i7;
      i15 = i11;
      i16 = i2;
      i17 = i6;
      i18 = i8;
      i19 = i1;
      i20 = n;
    }
  }

  protected boolean err()
  {
    return this.status != 0;
  }

  public int getDelay(int paramInt)
  {
    this.delay = -1;
    if ((paramInt >= 0) && (paramInt < this.frameCount))
      this.delay = ((J)this.frames.elementAt(paramInt)).b;
    return this.delay;
  }

  public Bitmap getFrame(int paramInt)
  {
    Bitmap localBitmap = null;
    if (paramInt >= 0)
    {
      int i = this.frameCount;
      localBitmap = null;
      if (paramInt < i)
        localBitmap = ((J)this.frames.elementAt(paramInt)).a;
    }
    return localBitmap;
  }

  public int getFrameCount()
  {
    return this.frameCount;
  }

  public int getFrameindex()
  {
    return this.frameindex;
  }

  public int getHeigh()
  {
    return this.height;
  }

  public Bitmap getImage()
  {
    return getFrame(0);
  }

  public int getLoopCount()
  {
    return this.loopCount;
  }

  public int getWidth()
  {
    return this.width;
  }

  protected void init()
  {
    this.status = 0;
    this.frameCount = 0;
    this.frames = new Vector();
    this.gct = null;
    this.lct = null;
  }

  public Bitmap nextBitmap()
  {
    this.frameindex = (1 + this.frameindex);
    if (this.frameindex > -1 + this.frames.size())
      this.frameindex = 0;
    return ((J)this.frames.elementAt(this.frameindex)).a;
  }

  public int nextDelay()
  {
    return ((J)this.frames.elementAt(this.frameindex)).b;
  }

  protected int read()
  {
    try
    {
      int i = this.in.read();
      return i;
    }
    catch (Exception localException)
    {
      this.status = 1;
    }
    return 0;
  }

  public int read(InputStream paramInputStream)
  {
    init();
    if (paramInputStream != null)
    {
      this.in = paramInputStream;
      readHeader();
      if (!err())
      {
        readContents();
        if (this.frameCount < 0)
          this.status = 1;
      }
    }
    try
    {
      while (true)
      {
        paramInputStream.close();
        label44: return this.status;
        this.status = 2;
      }
    }
    catch (Exception localException)
    {
      break label44;
    }
  }

  protected int readBlock()
  {
    this.blockSize = read();
    int i = this.blockSize;
    int j = 0;
    if (i > 0);
    try
    {
      while (true)
      {
        int k = this.blockSize;
        if (j >= k);
        label31: int m;
        do
        {
          if (j < this.blockSize)
            this.status = 1;
          return j;
          m = this.in.read(this.block, j, this.blockSize - j);
        }
        while (m == -1);
        j += m;
      }
    }
    catch (Exception localException)
    {
      break label31;
    }
  }

  protected int[] readColorTable(int paramInt)
  {
    int i = 0;
    int j = paramInt * 3;
    int[] arrayOfInt = null;
    byte[] arrayOfByte = new byte[j];
    while (true)
    {
      int m;
      try
      {
        int i6 = this.in.read(arrayOfByte);
        k = i6;
        if (k >= j)
          continue;
        this.status = 1;
        return arrayOfInt;
      }
      catch (Exception localException)
      {
        int k = 0;
        continue;
        arrayOfInt = new int[256];
        m = 0;
      }
      while (m < paramInt)
      {
        int n = i + 1;
        int i1 = 0xFF & arrayOfByte[i];
        int i2 = n + 1;
        int i3 = 0xFF & arrayOfByte[n];
        i = i2 + 1;
        int i4 = 0xFF & arrayOfByte[i2];
        int i5 = m + 1;
        arrayOfInt[m] = (i4 | (0xFF000000 | i1 << 16 | i3 << 8));
        m = i5;
      }
    }
  }

  protected void readContents()
  {
    int i = 0;
    while (true)
    {
      if ((i != 0) || (err()))
        return;
      switch (read())
      {
      case 0:
      default:
        this.status = 1;
        break;
      case 44:
        readImage();
        break;
      case 33:
        switch (read())
        {
        default:
          skip();
          break;
        case 249:
          readGraphicControlExt();
          break;
        case 255:
          readBlock();
          String str = "";
          for (int j = 0; ; j++)
          {
            if (j >= 11)
            {
              if (!str.equals("NETSCAPE2.0"))
                break label184;
              readNetscapeExt();
              break;
            }
            str = str + (char)this.block[j];
          }
          skip();
        }
        break;
      case 59:
        label184: i = 1;
      }
    }
  }

  protected void readGraphicControlExt()
  {
    int i = 1;
    read();
    int j = read();
    this.dispose = ((j & 0x1C) >> 2);
    if (this.dispose == 0)
      this.dispose = i;
    if ((j & 0x1) != 0);
    while (true)
    {
      this.transparency = i;
      this.delay = (10 * readShort());
      this.transIndex = read();
      read();
      return;
      i = 0;
    }
  }

  protected void readHeader()
  {
    String str = "";
    int i = 0;
    if (i >= 6)
    {
      if (str.startsWith("GIF"))
        break label55;
      this.status = 1;
    }
    label55: 
    do
    {
      return;
      str = str + (char)read();
      i++;
      break;
      readLSD();
    }
    while ((!this.gctFlag) || (err()));
    this.gct = readColorTable(this.gctSize);
    this.bgColor = this.gct[this.bgIndex];
  }

  protected void readImage()
  {
    this.ix = readShort();
    this.iy = readShort();
    this.iw = readShort();
    this.ih = readShort();
    int i = read();
    boolean bool1;
    boolean bool2;
    label61: label103: int j;
    if ((i & 0x80) != 0)
    {
      bool1 = true;
      this.lctFlag = bool1;
      if ((i & 0x40) == 0)
        break label167;
      bool2 = true;
      this.interlace = bool2;
      this.lctSize = (2 << (i & 0x7));
      if (!this.lctFlag)
        break label172;
      this.lct = readColorTable(this.lctSize);
      this.act = this.lct;
      boolean bool3 = this.transparency;
      j = 0;
      if (bool3)
      {
        int k = this.act[this.transIndex];
        this.act[this.transIndex] = 0;
        j = k;
      }
      if (this.act == null)
        this.status = 1;
      if (!err())
        break label199;
    }
    label167: label172: label199: 
    do
    {
      return;
      bool1 = false;
      break;
      bool2 = false;
      break label61;
      this.act = this.gct;
      if (this.bgIndex != this.transIndex)
        break label103;
      this.bgColor = 0;
      break label103;
      decodeImageData();
      skip();
    }
    while (err());
    this.frameCount = (1 + this.frameCount);
    image = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.RGB_565);
    setPixels();
    this.frames.addElement(new J(image, this.delay));
    if (this.transparency)
      this.act[this.transIndex] = j;
    resetFrame();
  }

  protected void readLSD()
  {
    this.width = readShort();
    this.height = readShort();
    int i = read();
    if ((i & 0x80) != 0);
    for (boolean bool = true; ; bool = false)
    {
      this.gctFlag = bool;
      this.gctSize = (2 << (i & 0x7));
      this.bgIndex = read();
      this.pixelAspect = read();
      return;
    }
  }

  protected void readNetscapeExt()
  {
    do
    {
      readBlock();
      if (this.block[0] != 1)
        continue;
      this.loopCount = (0xFF & this.block[1] | (0xFF & this.block[2]) << 8);
    }
    while ((this.blockSize > 0) && (!err()));
  }

  protected int readShort()
  {
    return read() | read() << 8;
  }

  protected void resetFrame()
  {
    this.lastDispose = this.dispose;
    this.lrx = this.ix;
    this.lry = this.iy;
    this.lrw = this.iw;
    this.lrh = this.ih;
    lastImage = image;
    this.lastBgColor = this.bgColor;
    this.dispose = 0;
    this.transparency = false;
    this.delay = 0;
    this.lct = null;
  }

  public void setFrameindex(int paramInt)
  {
    this.frameindex = paramInt;
    this.frames.size();
  }

  protected void setPixels()
  {
    int i = 0;
    int[] arrayOfInt = new int[this.width * this.height];
    if (this.lastDispose > 0)
    {
      if (this.lastDispose == 3)
      {
        int i15 = -2 + this.frameCount;
        if (i15 <= 0)
          break label151;
        lastImage = getFrame(i15 - 1);
      }
      if (lastImage != null)
      {
        lastImage.getPixels(arrayOfInt, 0, this.width, 0, 0, this.width, this.height);
        if (this.lastDispose == 2)
          if (this.transparency)
            break label446;
      }
    }
    label151: label446: for (int i11 = this.lastBgColor; ; i11 = 0)
    {
      int i12 = 0;
      int j;
      int k;
      int m;
      int i13;
      int i14;
      if (i12 >= this.lrh)
      {
        j = 8;
        k = 1;
        m = 0;
        if (i >= this.ih)
        {
          image = Bitmap.createBitmap(arrayOfInt, this.width, this.height, Bitmap.Config.RGB_565);
          return;
          lastImage = null;
          break;
        }
      }
      else
      {
        i13 = (i12 + this.lry) * this.width + this.lrx;
        i14 = i13 + this.lrw;
        while (true)
        {
          if (i13 >= i14)
          {
            i12++;
            break;
          }
          arrayOfInt[i13] = i11;
          i13++;
        }
      }
      int i10;
      if (this.interlace)
      {
        if (m >= this.ih)
          k++;
        switch (k)
        {
        default:
          int i9 = m + j;
          i10 = m;
          m = i9;
        case 2:
        case 3:
        case 4:
        }
      }
      for (int n = i10; ; n = i)
      {
        int i1 = n + this.iy;
        int i3;
        int i4;
        if (i1 < this.height)
        {
          int i2 = i1 * this.width;
          i3 = i2 + this.ix;
          i4 = i3 + this.iw;
          if (i2 + this.width < i4)
            i4 = i2 + this.width;
        }
        int i6;
        for (int i5 = i * this.iw; ; i5 = i6)
        {
          if (i3 >= i4)
          {
            i++;
            break;
            m = 4;
            break label260;
            m = 2;
            j = 4;
            break label260;
            m = 1;
            j = 2;
            break label260;
          }
          byte[] arrayOfByte = this.pixels;
          i6 = i5 + 1;
          int i7 = 0xFF & arrayOfByte[i5];
          int i8 = this.act[i7];
          if (i8 != 0)
            arrayOfInt[i3] = i8;
          i3++;
        }
      }
    }
  }

  protected void skip()
  {
    do
      readBlock();
    while ((this.blockSize > 0) && (!err()));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APTypegifOpenHelper
 * JD-Core Version:    0.6.0
 */