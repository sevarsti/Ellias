package com.tencent.msdk.api;

import java.util.Vector;

public class WakeupRet extends CallbackRet
{
  public String country = "";
  public Vector<KVPair> extInfo = new Vector();
  public String lang = "";
  public String media_tag_name = "";
  public String messageExt = "";
  public String open_id = "";

  public String toString()
  {
    String str1 = super.toString();
    String str2 = str1 + "open_id: " + this.open_id + ";";
    return str2 + "media_tag_name: " + this.media_tag_name + ";";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.api.WakeupRet
 * JD-Core Version:    0.6.0
 */