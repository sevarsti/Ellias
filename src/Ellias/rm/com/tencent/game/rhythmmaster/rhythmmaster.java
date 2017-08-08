package com.tencent.game.rhythmmaster;

import android.util.Log;
import com.tencent.game.helper.m3eActivity;

public class rhythmmaster extends m3eActivity
{
  static
  {
    Log.e("leofang", "lib tersafe RMEngine beging");
    System.loadLibrary("tersafe");
    System.loadLibrary("RMEngine");
    Log.e("leofang", "lib tersafe RMEngine end");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.rhythmmaster.rhythmmaster
 * JD-Core Version:    0.6.0
 */