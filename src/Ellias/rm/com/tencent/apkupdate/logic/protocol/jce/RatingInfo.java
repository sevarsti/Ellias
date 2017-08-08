package com.tencent.apkupdate.logic.protocol.jce;

import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import java.util.HashMap;
import java.util.Map;

public final class RatingInfo extends JceStruct
{
  static Map a;
  public double averageRating = 0.0D;
  public long ratingCount = 0L;
  public Map ratingDistribution = null;

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    this.ratingCount = paramJceInputStream.read(this.ratingCount, 0, true);
    this.averageRating = paramJceInputStream.read(this.averageRating, 1, true);
    if (a == null)
    {
      a = new HashMap();
      Integer localInteger = Integer.valueOf(0);
      Long localLong = Long.valueOf(0L);
      a.put(localInteger, localLong);
    }
    this.ratingDistribution = ((Map)paramJceInputStream.read(a, 2, true));
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.ratingCount, 0);
    paramJceOutputStream.write(this.averageRating, 1);
    paramJceOutputStream.write(this.ratingDistribution, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.logic.protocol.jce.RatingInfo
 * JD-Core Version:    0.6.0
 */