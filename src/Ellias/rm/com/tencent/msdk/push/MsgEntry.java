package com.tencent.msdk.push;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.msdk.remote.api.SafeJSONObject;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class MsgEntry
  implements Parcelable
{
  public static final Parcelable.Creator<MsgEntry> CREATOR = new Parcelable.Creator()
  {
    public MsgEntry createFromParcel(Parcel paramParcel)
    {
      return new MsgEntry(paramParcel);
    }

    public MsgEntry[] newArray(int paramInt)
    {
      return new MsgEntry[paramInt];
    }
  };
  private String mAppId = "";
  private int mClickAction = 0;
  private String mContent = "";
  private String mDisplayEndTime = "";
  private String mDisplayStartTime = "";
  private String mDisplayType = "";
  private String mEndTime = "";
  private int mId = 0;
  private String mPkgName = "";
  private String mStartTime = "";
  private String mTheme = "";
  private String mTitle = "";
  private int mType = 0;

  public MsgEntry()
  {
  }

  public MsgEntry(Parcel paramParcel)
  {
    this.mAppId = paramParcel.readString();
    this.mId = paramParcel.readInt();
    this.mContent = paramParcel.readString();
    this.mType = paramParcel.readInt();
    this.mStartTime = paramParcel.readString();
    this.mEndTime = paramParcel.readString();
    this.mTheme = paramParcel.readString();
    this.mDisplayType = paramParcel.readString();
    this.mDisplayStartTime = paramParcel.readString();
    this.mDisplayEndTime = paramParcel.readString();
    this.mPkgName = paramParcel.readString();
    this.mClickAction = paramParcel.readInt();
  }

  public MsgEntry(JSONObject paramJSONObject)
  {
    try
    {
      SafeJSONObject localSafeJSONObject = new SafeJSONObject(paramJSONObject);
      this.mAppId = localSafeJSONObject.getString("1");
      this.mId = Integer.parseInt(localSafeJSONObject.getString("25"));
      this.mContent = localSafeJSONObject.getString("27");
      this.mType = Integer.parseInt(localSafeJSONObject.getString("30"));
      this.mTitle = localSafeJSONObject.getString("31");
      this.mStartTime = localSafeJSONObject.getString("32");
      this.mEndTime = localSafeJSONObject.getString("33");
      this.mTheme = localSafeJSONObject.getString("34");
      this.mDisplayType = localSafeJSONObject.getString("36");
      this.mDisplayStartTime = localSafeJSONObject.getString("41");
      this.mDisplayEndTime = localSafeJSONObject.getString("42");
      this.mPkgName = localSafeJSONObject.getString("44");
      this.mClickAction = Integer.parseInt(localSafeJSONObject.getString("43"));
      return;
    }
    catch (JSONException localJSONException)
    {
      Logger.w(paramJSONObject.toString());
      localJSONException.printStackTrace();
    }
  }

  public static ArrayList<MsgEntry> formatJson2Msg(String paramString)
  {
    if (T.ckIsEmpty(paramString))
    {
      Logger.d("rawMsgList empty");
      return new ArrayList();
    }
    return null;
  }

  public int describeContents()
  {
    return 0;
  }

  public String getAppId()
  {
    return this.mAppId;
  }

  public int getClickAction()
  {
    return this.mClickAction;
  }

  public String getContent()
  {
    return this.mContent;
  }

  public String getDisplayEndTime()
  {
    return this.mDisplayEndTime;
  }

  public String getDisplayStartTime()
  {
    return this.mDisplayStartTime;
  }

  public String getDisplayType()
  {
    return this.mDisplayType;
  }

  public String getEndTime()
  {
    return this.mEndTime;
  }

  public int getId()
  {
    return this.mId;
  }

  public String getPkgName()
  {
    return this.mPkgName;
  }

  public String getStartTime()
  {
    return this.mStartTime;
  }

  public String getTheme()
  {
    return this.mTheme;
  }

  public String getTitle()
  {
    return this.mTitle;
  }

  public int getType()
  {
    return this.mType;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mAppId);
    paramParcel.writeInt(this.mId);
    paramParcel.writeString(this.mContent);
    paramParcel.writeInt(this.mType);
    paramParcel.writeString(this.mStartTime);
    paramParcel.writeString(this.mEndTime);
    paramParcel.writeString(this.mTheme);
    paramParcel.writeString(this.mDisplayType);
    paramParcel.writeString(this.mDisplayStartTime);
    paramParcel.writeString(this.mDisplayEndTime);
    paramParcel.writeString(this.mPkgName);
    paramParcel.writeInt(this.mClickAction);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.MsgEntry
 * JD-Core Version:    0.6.0
 */