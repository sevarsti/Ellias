package com.tencent.msdk.weixin;

import com.tencent.msdk.tools.T;
import org.json.JSONException;
import org.json.JSONStringer;

public class MsgImage extends MsgBase
{
  private static final String MSG_TYPE = "image";
  private static int sDefaultHeight;
  private static String sDefaultPicUrl;
  private static int sDefaultWidth = 0;
  private final String MSG_KEY = "type_info";
  private Image mImage = new Image();

  static
  {
    sDefaultHeight = 0;
    sDefaultPicUrl = "";
  }

  public MsgImage()
  {
    super("image");
    setmPicUrl(sDefaultPicUrl);
    setmHeight(sDefaultHeight);
    setmWidth(sDefaultWidth);
  }

  public MsgImage(String paramString, int paramInt1, int paramInt2)
  {
    super("image");
    setmPicUrl(paramString);
    setmHeight(paramInt1);
    setmWidth(paramInt2);
  }

  public String checkParam()
  {
    String str = super.checkParam();
    if (T.ckIsEmpty(this.mImage.mPicUrl))
      str = str + "mPicUrl cann't be Empty;";
    if (this.mImage.mHeight <= 0)
      str = str + "mHeihgt cann't be a nagtive number;";
    if (this.mImage.mWidth <= 0)
      str = str + "mWidth cann't be a nagtive number;";
    return str.trim();
  }

  protected String getMsgKey()
  {
    return "type_info";
  }

  public void setmHeight(int paramInt)
  {
    this.mImage.setmHeight(paramInt);
  }

  public void setmPicUrl(String paramString)
  {
    this.mImage.setmPicUrl(paramString);
  }

  public void setmWidth(int paramInt)
  {
    this.mImage.setmWidth(paramInt);
  }

  public String toString()
  {
    try
    {
      JSONStringer localJSONStringer = new JSONStringer();
      localJSONStringer.object().key("picurl").value(this.mImage.mPicUrl).key("height").value(this.mImage.mHeight).key("width").value(this.mImage.mWidth).endObject();
      String str = localJSONStringer.toString();
      return str;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return "";
  }

  class Image
  {
    private int mHeight = 0;
    private String mPicUrl = "";
    private int mWidth = 0;

    Image()
    {
    }

    public void setmHeight(int paramInt)
    {
      this.mHeight = paramInt;
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
 * Qualified Name:     com.tencent.msdk.weixin.MsgImage
 * JD-Core Version:    0.6.0
 */