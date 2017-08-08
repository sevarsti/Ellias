package com.tencent.component.ui.widget.drawable;

public enum ScaleDrawable$ScaleType
{
  final int nativeInt;

  static
  {
    CROP_END = new ScaleType("CROP_END", 2, 2);
    FIT_CENTER = new ScaleType("FIT_CENTER", 3, 3);
    FIT_START = new ScaleType("FIT_START", 4, 4);
    FIT_END = new ScaleType("FIT_END", 5, 5);
    MATCH_WIDTH_TOP = new ScaleType("MATCH_WIDTH_TOP", 6, 6);
    MATCH_WIDTH_BOTTOM = new ScaleType("MATCH_WIDTH_BOTTOM", 7, 7);
    MATCH_WIDTH_CENTER = new ScaleType("MATCH_WIDTH_CENTER", 8, 8);
    CENTER = new ScaleType("CENTER", 9, 9);
    CROP_BY_PIVOT = new ScaleType("CROP_BY_PIVOT", 10, 10);
    ScaleType[] arrayOfScaleType = new ScaleType[11];
    arrayOfScaleType[0] = CROP_CENTER;
    arrayOfScaleType[1] = CROP_START;
    arrayOfScaleType[2] = CROP_END;
    arrayOfScaleType[3] = FIT_CENTER;
    arrayOfScaleType[4] = FIT_START;
    arrayOfScaleType[5] = FIT_END;
    arrayOfScaleType[6] = MATCH_WIDTH_TOP;
    arrayOfScaleType[7] = MATCH_WIDTH_BOTTOM;
    arrayOfScaleType[8] = MATCH_WIDTH_CENTER;
    arrayOfScaleType[9] = CENTER;
    arrayOfScaleType[10] = CROP_BY_PIVOT;
    $VALUES = arrayOfScaleType;
  }

  private ScaleDrawable$ScaleType(int arg3)
  {
    int j;
    this.nativeInt = j;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.ScaleDrawable.ScaleType
 * JD-Core Version:    0.6.0
 */