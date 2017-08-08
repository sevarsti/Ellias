package com.tencent.msdk.weixin;

import org.json.JSONException;
import org.json.JSONStringer;

public class BtnRank extends BtnBase
{
  private static String sDefaultButtonName;
  private static String sDefaultMsgExt = "";
  private static String sDefaultTitle = "";
  protected static String sRankViewKey;
  private RankView mRankView = new RankView(null);
  private String sDefaultName = "";

  static
  {
    sDefaultButtonName = "";
    sRankViewKey = "rankview";
  }

  public BtnRank()
  {
    this.mType = "rank";
    setmName(this.sDefaultName);
    setmTitle(sDefaultTitle);
    setmRankViewButtonName(sDefaultButtonName);
    setmMessageExt(sDefaultMsgExt);
  }

  public BtnRank(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.mType = "rank";
    setmName(paramString1);
    setmTitle(paramString2);
    setmRankViewButtonName(paramString3);
    setmMessageExt(paramString4);
  }

  public void setmMessageExt(String paramString)
  {
    this.mRankView.setmMessageExt(paramString);
  }

  public void setmRankViewButtonName(String paramString)
  {
    this.mRankView.setmRankViewButtonName(paramString);
  }

  public void setmTitle(String paramString)
  {
    this.mRankView.setmTitle(paramString);
  }

  public String toString()
  {
    try
    {
      JSONStringer localJSONStringer = new JSONStringer();
      localJSONStringer.object().key("type").value(this.mType).key("name").value(this.mName).key(sRankViewKey).object().key("title").value(this.mRankView.mTitle).key("button_name").value(this.mRankView.mRankViewButtonName).key("message_ext").value(this.mRankView.mMessageExt).endObject().endObject();
      String str = localJSONStringer.toString();
      return str;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return "";
  }

  private class RankView
  {
    private String mMessageExt = "";
    private String mRankViewButtonName = "";
    private String mTitle = "";

    private RankView()
    {
    }

    public void setmMessageExt(String paramString)
    {
      this.mMessageExt = paramString;
    }

    public void setmRankViewButtonName(String paramString)
    {
      this.mRankViewButtonName = paramString;
    }

    public void setmTitle(String paramString)
    {
      this.mTitle = paramString;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.weixin.BtnRank
 * JD-Core Version:    0.6.0
 */