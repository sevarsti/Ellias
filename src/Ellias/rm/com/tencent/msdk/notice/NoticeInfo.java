package com.tencent.msdk.notice;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.msdk.db.NoticeDBModel;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NoticeInfo
  implements Parcelable
{
  public static final Parcelable.Creator<NoticeInfo> CREATOR = new Parcelable.Creator()
  {
    public NoticeInfo createFromParcel(Parcel paramParcel)
    {
      if (paramParcel == null)
        return new NoticeInfo();
      return new NoticeInfo(paramParcel);
    }

    public NoticeInfo[] newArray(int paramInt)
    {
      return new NoticeInfo[paramInt];
    }
  };
  public String mAppId = "";
  public String mNoticeContent = "";
  public eMSG_CONTENTTYPE mNoticeContentType;
  public String mNoticeContentWebUrl = "";
  public String mNoticeEndTime = "";
  public String mNoticeHImgHash = "";
  public String mNoticeHImgUrl = "";
  public String mNoticeId = "";
  public Vector<NoticePic> mNoticePics = new Vector();
  public String mNoticeScene = "";
  public String mNoticeStartTime = "";
  public String mNoticeTitle = "";
  public eMSG_NOTICETYPE mNoticeType;
  public String mNoticeUrl = "";
  public String mNoticeVImgHash = "";
  public String mNoticeVImgUrl = "";
  public String mOpenId = "";

  public NoticeInfo()
  {
  }

  public NoticeInfo(Parcel paramParcel)
  {
    this.mNoticeId = paramParcel.readString();
    this.mAppId = paramParcel.readString();
    this.mOpenId = paramParcel.readString();
    this.mNoticeUrl = paramParcel.readString();
    this.mNoticeContentType = eMSG_CONTENTTYPE.getEnum(paramParcel.readInt());
    this.mNoticeScene = paramParcel.readString();
    this.mNoticeStartTime = paramParcel.readString();
    this.mNoticeEndTime = paramParcel.readString();
    this.mNoticeType = eMSG_NOTICETYPE.getEnum(paramParcel.readInt());
    this.mNoticeTitle = paramParcel.readString();
    this.mNoticeContent = paramParcel.readString();
    this.mNoticeHImgUrl = paramParcel.readString();
    this.mNoticeHImgHash = paramParcel.readString();
    this.mNoticeVImgUrl = paramParcel.readString();
    this.mNoticeVImgHash = paramParcel.readString();
    this.mNoticeContentWebUrl = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public void getBaseInfoFromJson(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return;
    this.mNoticeId = getStringInfoFromJson(paramJSONObject, "msgid");
    this.mAppId = getStringInfoFromJson(paramJSONObject, "appid");
    this.mOpenId = getStringInfoFromJson(paramJSONObject, "openid");
    this.mNoticeUrl = getStringInfoFromJson(paramJSONObject, "msgUrl");
    this.mNoticeType = eMSG_NOTICETYPE.getEnum(getIntInfoFromJson(paramJSONObject, "noticeType"));
    this.mNoticeScene = getStringInfoFromJson(paramJSONObject, "scene");
    this.mNoticeStartTime = getStringInfoFromJson(paramJSONObject, "beginTime");
    this.mNoticeEndTime = getStringInfoFromJson(paramJSONObject, "endTime");
    this.mNoticeContentType = eMSG_CONTENTTYPE.getEnum(getIntInfoFromJson(paramJSONObject, "contentType"));
    this.mNoticeTitle = getStringInfoFromJson(paramJSONObject, "title");
    this.mNoticeContent = getStringInfoFromJson(paramJSONObject, "msgContent");
    JSONArray localJSONArray = getJSONArrayInfoFromJson(paramJSONObject, "picUrlList");
    for (int i = 0; ; i++)
    {
      try
      {
        if (i < localJSONArray.length())
        {
          JSONObject localJSONObject = localJSONArray.getJSONObject(i);
          if (!localJSONObject.has("screenDir"))
            break label256;
          eMSDK_SCREENDIR localeMSDK_SCREENDIR = eMSDK_SCREENDIR.getEnum(localJSONObject.getInt("screenDir"));
          if (eMSDK_SCREENDIR.eMSDK_SCREENDIR_LANDSCAPE == localeMSDK_SCREENDIR)
          {
            this.mNoticeHImgUrl = localJSONObject.getString("picUrl");
            this.mNoticeHImgHash = localJSONObject.getString("hashValue");
            continue;
          }
          this.mNoticeVImgUrl = localJSONObject.getString("picUrl");
          this.mNoticeVImgHash = localJSONObject.getString("hashValue");
        }
      }
      catch (Exception localException)
      {
        Logger.w("JSONException");
        this.mNoticeContentWebUrl = getStringInfoFromJson(paramJSONObject, "contentUrl");
        return;
      }
      label256: Logger.e("Error picList, no screen dir");
    }
  }

  public int getIntInfoFromJson(JSONObject paramJSONObject, String paramString)
  {
    if (T.ckIsEmpty(paramString))
      Logger.w("json key is empty");
    while (true)
    {
      return 0;
      try
      {
        if (!paramJSONObject.has(paramString))
          continue;
        int i = paramJSONObject.getInt(paramString);
        return i;
      }
      catch (JSONException localJSONException)
      {
        Logger.w("JSONException");
      }
    }
    return 0;
  }

  public JSONArray getJSONArrayInfoFromJson(JSONObject paramJSONObject, String paramString)
  {
    JSONArray localJSONArray1;
    if (T.ckIsEmpty(paramString))
    {
      Logger.w("json key is empty");
      localJSONArray1 = new JSONArray();
    }
    while (true)
    {
      return localJSONArray1;
      localJSONArray1 = new JSONArray();
      try
      {
        if (!paramJSONObject.has(paramString))
          continue;
        JSONArray localJSONArray2 = paramJSONObject.getJSONArray(paramString);
        return localJSONArray2;
      }
      catch (JSONException localJSONException)
      {
        Logger.w("JSONException");
      }
    }
    return localJSONArray1;
  }

  public String getStringInfoFromJson(JSONObject paramJSONObject, String paramString)
  {
    String str1;
    if (T.ckIsEmpty(paramString))
    {
      Logger.w("json key is empty");
      str1 = "";
    }
    while (true)
    {
      return str1;
      str1 = "";
      try
      {
        if (!paramJSONObject.has(paramString))
          continue;
        String str2 = paramJSONObject.getString(paramString);
        return str2;
      }
      catch (JSONException localJSONException)
      {
        Logger.w("JSONException");
      }
    }
    return str1;
  }

  public ContentValues getUsableContentValues(NoticeDBModel paramNoticeDBModel)
  {
    ContentValues localContentValues = new ContentValues();
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_msg_id, this.mNoticeId);
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_app_id, this.mAppId);
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_open_id, this.mOpenId);
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_msg_url, this.mNoticeUrl);
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_msg_type, String.valueOf(this.mNoticeType.val()));
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_msg_scene, this.mNoticeScene);
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_start_time, this.mNoticeStartTime);
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_end_time, this.mNoticeEndTime);
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_content_type, String.valueOf(this.mNoticeContentType.val()));
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_msg_title, this.mNoticeTitle);
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_msg_content, this.mNoticeContent);
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_horizontal_img_url, this.mNoticeHImgUrl);
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_horizontal_img_hash, this.mNoticeHImgHash);
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_vertical_img_url, this.mNoticeVImgUrl);
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_vertical_img_hash, this.mNoticeVImgHash);
    paramNoticeDBModel.putValues(localContentValues, NoticeDBModel.col_web_url, this.mNoticeContentWebUrl);
    return localContentValues;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mNoticeId);
    paramParcel.writeString(this.mAppId);
    paramParcel.writeString(this.mOpenId);
    paramParcel.writeString(this.mNoticeUrl);
    paramParcel.writeInt(this.mNoticeContentType.val());
    paramParcel.writeString(this.mNoticeScene);
    paramParcel.writeString(this.mNoticeStartTime);
    paramParcel.writeString(this.mNoticeEndTime);
    paramParcel.writeInt(this.mNoticeType.val());
    paramParcel.writeString(this.mNoticeTitle);
    paramParcel.writeString(this.mNoticeContent);
    paramParcel.writeString(this.mNoticeHImgUrl);
    paramParcel.writeString(this.mNoticeHImgHash);
    paramParcel.writeString(this.mNoticeVImgUrl);
    paramParcel.writeString(this.mNoticeVImgHash);
    paramParcel.writeString(this.mNoticeContentWebUrl);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.NoticeInfo
 * JD-Core Version:    0.6.0
 */