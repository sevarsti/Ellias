package com.tencent.tmassistantsdk.g;

class d extends b
{
  private static final byte[] h;
  private static final byte[] i;
  int c;
  public final boolean d;
  public final boolean e;
  public final boolean f;
  private final byte[] j;
  private int k;
  private final byte[] l;

  static
  {
    if (!a.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      g = bool;
      h = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
      i = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
      return;
    }
  }

  public d(int paramInt, byte[] paramArrayOfByte)
  {
    this.a = paramArrayOfByte;
    boolean bool2;
    boolean bool3;
    label35: label47: byte[] arrayOfByte;
    if ((paramInt & 0x1) == 0)
    {
      bool2 = bool1;
      this.d = bool2;
      if ((paramInt & 0x2) != 0)
        break label106;
      bool3 = bool1;
      this.e = bool3;
      if ((paramInt & 0x4) == 0)
        break label112;
      this.f = bool1;
      if ((paramInt & 0x8) != 0)
        break label117;
      arrayOfByte = h;
      label64: this.l = arrayOfByte;
      this.j = new byte[2];
      this.c = 0;
      if (!this.e)
        break label125;
    }
    label106: label112: label117: label125: for (int m = 19; ; m = -1)
    {
      this.k = m;
      return;
      bool2 = false;
      break;
      bool3 = false;
      break label35;
      bool1 = false;
      break label47;
      arrayOfByte = i;
      break label64;
    }
  }

  public boolean a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    byte[] arrayOfByte1 = this.l;
    byte[] arrayOfByte2 = this.a;
    int m = this.k;
    int n = paramInt2 + paramInt1;
    int i1 = -1;
    int i3;
    label59: int i4;
    int i38;
    switch (this.c)
    {
    default:
      i3 = paramInt1;
      i4 = 0;
      if (i1 == -1)
        break;
      arrayOfByte2[0] = arrayOfByte1[(0x3F & i1 >> 18)];
      arrayOfByte2[1] = arrayOfByte1[(0x3F & i1 >> 12)];
      arrayOfByte2[2] = arrayOfByte1[(0x3F & i1 >> 6)];
      i4 = 4;
      arrayOfByte2[3] = arrayOfByte1[(i1 & 0x3F)];
      m--;
      if (m != 0)
        break;
      if (this.f)
      {
        i38 = 5;
        arrayOfByte2[i4] = 13;
      }
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      int i39 = i38 + 1;
      arrayOfByte2[i38] = 10;
      int i5 = 19;
      int i6 = i39;
      while (true)
      {
        label174: int i36;
        if (i3 + 3 <= n)
        {
          int i35 = (0xFF & paramArrayOfByte[i3]) << 16 | (0xFF & paramArrayOfByte[(i3 + 1)]) << 8 | 0xFF & paramArrayOfByte[(i3 + 2)];
          arrayOfByte2[i6] = arrayOfByte1[(0x3F & i35 >> 18)];
          arrayOfByte2[(i6 + 1)] = arrayOfByte1[(0x3F & i35 >> 12)];
          arrayOfByte2[(i6 + 2)] = arrayOfByte1[(0x3F & i35 >> 6)];
          arrayOfByte2[(i6 + 3)] = arrayOfByte1[(i35 & 0x3F)];
          i3 += 3;
          i4 = i6 + 4;
          m = i5 - 1;
          if (m != 0)
            break label1235;
          if (!this.f)
            break label1228;
          i36 = i4 + 1;
          arrayOfByte2[i4] = 13;
        }
        while (true)
        {
          int i37 = i36 + 1;
          arrayOfByte2[i36] = 10;
          i5 = 19;
          i6 = i37;
          break label174;
          i3 = paramInt1;
          break label59;
          if (paramInt1 + 2 > n)
            break;
          int i40 = (0xFF & this.j[0]) << 16;
          int i41 = paramInt1 + 1;
          int i42 = i40 | (0xFF & paramArrayOfByte[paramInt1]) << 8;
          int i43 = i41 + 1;
          i1 = i42 | 0xFF & paramArrayOfByte[i41];
          this.c = 0;
          i3 = i43;
          break label59;
          if (paramInt1 + 1 > n)
            break;
          int i2 = (0xFF & this.j[0]) << 16 | (0xFF & this.j[1]) << 8;
          i3 = paramInt1 + 1;
          i1 = i2 | 0xFF & paramArrayOfByte[paramInt1];
          this.c = 0;
          break label59;
          int i13;
          int i12;
          label770: int i16;
          label811: int i20;
          int i21;
          if (paramBoolean)
          {
            if (i3 - this.c == n - 1)
            {
              int i28;
              int i26;
              int i27;
              if (this.c > 0)
              {
                byte[] arrayOfByte8 = this.j;
                i28 = 1;
                i26 = arrayOfByte8[0];
                i27 = i3;
              }
              while (true)
              {
                int i29 = (i26 & 0xFF) << 4;
                this.c -= i28;
                int i30 = i6 + 1;
                arrayOfByte2[i6] = arrayOfByte1[(0x3F & i29 >> 6)];
                int i31 = i30 + 1;
                arrayOfByte2[i30] = arrayOfByte1[(i29 & 0x3F)];
                if (this.d)
                {
                  int i34 = i31 + 1;
                  arrayOfByte2[i31] = 61;
                  i31 = i34 + 1;
                  arrayOfByte2[i34] = 61;
                }
                if (this.e)
                {
                  if (this.f)
                  {
                    int i33 = i31 + 1;
                    arrayOfByte2[i31] = 13;
                    i31 = i33;
                  }
                  int i32 = i31 + 1;
                  arrayOfByte2[i31] = 10;
                  i31 = i32;
                }
                i3 = i27;
                i6 = i31;
                if ((g) || (this.c == 0))
                  break;
                throw new AssertionError();
                int i25 = i3 + 1;
                i26 = paramArrayOfByte[i3];
                i27 = i25;
                i28 = 0;
              }
            }
            if (i3 - this.c == n - 2)
              if (this.c > 1)
              {
                byte[] arrayOfByte7 = this.j;
                i13 = 1;
                i12 = arrayOfByte7[0];
                int i14 = (i12 & 0xFF) << 10;
                if (this.c <= 0)
                  break label995;
                byte[] arrayOfByte6 = this.j;
                int i24 = i13 + 1;
                i16 = arrayOfByte6[i13];
                i13 = i24;
                int i17 = i14 | (i16 & 0xFF) << 2;
                this.c -= i13;
                int i18 = i6 + 1;
                arrayOfByte2[i6] = arrayOfByte1[(0x3F & i17 >> 12)];
                int i19 = i18 + 1;
                arrayOfByte2[i18] = arrayOfByte1[(0x3F & i17 >> 6)];
                i20 = i19 + 1;
                arrayOfByte2[i19] = arrayOfByte1[(i17 & 0x3F)];
                if (!this.d)
                  break label1221;
                i21 = i20 + 1;
                arrayOfByte2[i20] = 61;
              }
          }
          while (true)
          {
            if (this.e)
            {
              if (this.f)
              {
                int i23 = i21 + 1;
                arrayOfByte2[i21] = 13;
                i21 = i23;
              }
              int i22 = i21 + 1;
              arrayOfByte2[i21] = 10;
              i21 = i22;
            }
            i6 = i21;
            break;
            int i11 = i3 + 1;
            i12 = paramArrayOfByte[i3];
            i3 = i11;
            i13 = 0;
            break label770;
            label995: int i15 = i3 + 1;
            i16 = paramArrayOfByte[i3];
            i3 = i15;
            break label811;
            if ((!this.e) || (i6 <= 0) || (i5 == 19))
              break;
            int i10;
            if (this.f)
            {
              i10 = i6 + 1;
              arrayOfByte2[i6] = 13;
            }
            while (true)
            {
              i6 = i10 + 1;
              arrayOfByte2[i10] = 10;
              break;
              if ((!g) && (i3 != n))
              {
                throw new AssertionError();
                if (i3 != n - 1)
                  break label1142;
                byte[] arrayOfByte5 = this.j;
                int i9 = this.c;
                this.c = (i9 + 1);
                arrayOfByte5[i9] = paramArrayOfByte[i3];
              }
              while (true)
              {
                this.b = i6;
                this.k = i5;
                return true;
                label1142: if (i3 != n - 2)
                  continue;
                byte[] arrayOfByte3 = this.j;
                int i7 = this.c;
                this.c = (i7 + 1);
                arrayOfByte3[i7] = paramArrayOfByte[i3];
                byte[] arrayOfByte4 = this.j;
                int i8 = this.c;
                this.c = (i8 + 1);
                arrayOfByte4[i8] = paramArrayOfByte[(i3 + 1)];
              }
              i10 = i6;
            }
            label1221: i21 = i20;
          }
          label1228: i36 = i4;
        }
        label1235: i5 = m;
        i6 = i4;
      }
      i38 = i4;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.g.d
 * JD-Core Version:    0.6.0
 */