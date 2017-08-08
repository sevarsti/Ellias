package tencent.com.cftutils;

import java.util.Comparator;
import java.util.Map.Entry;

class Md5EncUtil$1
  implements Comparator
{
  public int compare(Object paramObject1, Object paramObject2)
  {
    return ((Map.Entry)paramObject1).getKey().toString().compareTo(((Map.Entry)paramObject2).getKey().toString());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     tencent.com.cftutils.Md5EncUtil.1
 * JD-Core Version:    0.6.0
 */