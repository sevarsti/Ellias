package com.tencent.msdk.weixin;

import com.tencent.msdk.tools.T;
import org.json.JSONException;
import org.json.JSONStringer;

public class MsgVideo extends MsgBase
{
  private static int sDefaultHeight = 0;
  private static String sDefaultMediaUrl;
  private static String sDefaultPicUrl;
  private static int sDefaultWidth = 0;
  private static final String sMSG_KEY = "type_info";
  private static final String sMSG_TYPE = "video";
  private Video mVideo = new Video(null);

  static
  {
    sDefaultHeight = 0;
    sDefaultPicUrl = "";
    sDefaultMediaUrl = "";
  }

  public MsgVideo()
  {
    super("video");
    setmMediaUrl(sDefaultMediaUrl);
    setmPicUrl(sDefaultPicUrl);
    setmHeight(sDefaultHeight);
    setmWidth(sDefaultWidth);
  }

  public MsgVideo(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    this();
    setmMediaUrl(paramString1);
    setmPicUrl(paramString2);
    setmHeight(paramInt1);
    setmWidth(paramInt2);
  }

  public String checkParam()
  {
    String str = super.checkParam();
    if (T.ckIsEmpty(this.mVideo.mPicUrl))
      str = str + "mPicUrl cann't be Empty;";
    if (T.ckIsEmpty(this.mVideo.mMediaUrl))
      str = str + "mMediaUrl cann't be Empty;";
    if (this.mVideo.mHeight <= 0)
      str = str + "mHeihgt cann't be a nagtive number;";
    if (this.mVideo.mWidth <= 0)
      str = str + "mWidth cann't be a nagtive number;";
    return str.trim();
  }

  protected String getMsgKey()
  {
    return "type_info";
  }

  public void setmHeight(int paramInt)
  {
    this.mVideo.setmHeight(paramInt);
  }

  public void setmMediaUrl(String paramString)
  {
    this.mVideo.setmMediaurl(paramString);
  }

  public void setmPicUrl(String paramString)
  {
    this.mVideo.setmPicUrl(paramString);
  }

  public void setmWidth(int paramInt)
  {
    this.mVideo.setmWidth(paramInt);
  }

  public String toString()
  {
    try
    {
      JSONStringer localJSONStringer = new JSONStringer();
      localJSONStringer.object().key("mediaurl").value(this.mVideo.mMediaUrl).key("picurl").value(this.mVideo.mPicUrl).key("width").value(this.mVideo.mWidth).key("height").value(this.mVideo.mHeight).endObject();
      String str = localJSONStringer.toString();
      return str;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return "";
  }

  private class Video
  {
    private int mHeight = 0;
    private String mMediaUrl = "";
    private String mPicUrl = "";
    private int mWidth = 0;

    private Video()
    {
    }

    public void setmHeight(int paramInt)
    {
      this.mHeight = paramInt;
    }

    public void setmMediaurl(String paramString)
    {
      this.mMediaUrl = paramString;
    }

    public void setmPicUrl(String paramString)
    {
      this.mPicUrl = paramString;
    }

    public void setmWidth(int paramInt)
    {
      this.mWidth = paramInt;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.weixin.MsgVideo
 * JD-Core Version:    0.6.0
 */