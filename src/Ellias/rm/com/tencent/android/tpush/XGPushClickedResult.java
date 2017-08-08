package com.tencent.android.tpush;

import android.content.Intent;
import com.tencent.android.tpush.encrypt.Rijndael;
import java.io.Serializable;

public class XGPushClickedResult
  implements XGIResult, Serializable
{
  public static final int NOTIFACTION_CLICKED_TYPE = 0;
  public static final int NOTIFACTION_DELETED_TYPE = 1;
  int actionType = 0;
  String activityName = "";
  String content = "";
  String customContent = "";
  long msgId = 0L;
  String title = "";

  public long getActionType()
  {
    return this.actionType;
  }

  public String getActivityName()
  {
    return this.activityName;
  }

  public String getContent()
  {
    return this.content;
  }

  public String getCustomContent()
  {
    return this.customContent;
  }

  public long getMsgId()
  {
    return this.msgId;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void parseIntent(Intent paramIntent)
  {
    this.msgId = paramIntent.getLongExtra("msgId", -1L);
    this.activityName = paramIntent.getStringExtra("activity");
    this.title = Rijndael.decrypt(paramIntent.getStringExtra("title"));
    this.content = Rijndael.decrypt(paramIntent.getStringExtra("content"));
    this.customContent = Rijndael.decrypt(paramIntent.getStringExtra("custom_content"));
    this.actionType = paramIntent.getIntExtra("action", 0);
  }

  public String toString()
  {
    return "XGPushClickedResult [msgId=" + this.msgId + ", title=" + this.title + ", content=" + this.content + ", customContent=" + this.customContent + ", activityName=" + this.activityName + ", actionType=" + this.actionType + "]";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGPushClickedResult
 * JD-Core Version:    0.6.0
 */