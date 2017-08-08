package com.tencent.msdk.notice;

import android.content.Context;
import android.content.res.Resources;
import com.tencent.msdk.tools.ResID;

public class NoticeResID extends ResID
{
  public static int alertNoticeImage;
  public static int confirmbtn;
  public static int land_0;
  public static int layout_image_notice;
  public static int layout_image_notice_url;
  public static int layout_scroll_notice;
  public static int layout_text_notice;
  public static int layout_text_notice_url;
  public static int layout_web_notice;
  public static int layout_web_notice_url;
  public static int marquee;
  public static int morebtn;
  public static int noticeContent;
  public static int noticeContentLine;
  public static int noticeTitle;
  public static int notice_alert_drawable;
  public static int notice_roll_drawable;
  public static int noticemain;
  public static int rollImage;
  public static int tempLayer;
  public static int tempLoadFailed;
  public static int tempLoadLayer;
  public static int web_load_gif;

  public static void loadImageLayout(Context paramContext)
  {
    String str = paramContext.getPackageName();
    Resources localResources = paramContext.getResources();
    notice_alert_drawable = loadIdentifierResource(localResources, "com_tencent_msdk_notice_popup", "drawable", str);
    noticemain = loadIdentifierResource(localResources, "noticemain", "id", str);
    alertNoticeImage = loadIdentifierResource(localResources, "popupImage", "id", str);
    confirmbtn = loadIdentifierResource(localResources, "confirmbtn", "id", str);
    morebtn = loadIdentifierResource(localResources, "morebtn", "id", str);
    layout_image_notice = loadIdentifierResource(localResources, "com_tencent_msdk_notice_image", "layout", str);
    layout_image_notice_url = loadIdentifierResource(localResources, "com_tencent_msdk_notice_image_url", "layout", str);
    noticeContent = loadIdentifierResource(localResources, "noticeContent", "id", str);
  }

  public static void loadScrollLayout(Context paramContext)
  {
    String str = paramContext.getPackageName();
    Resources localResources = paramContext.getResources();
    notice_roll_drawable = loadIdentifierResource(localResources, "com_tencent_msdk_notice_roll", "drawable", str);
    layout_scroll_notice = loadIdentifierResource(localResources, "com_tencent_msdk_notice_roll", "layout", str);
    rollImage = loadIdentifierResource(localResources, "rollImage", "id", str);
    marquee = loadIdentifierResource(localResources, "marquee", "id", str);
  }

  public static void loadTextLayout(Context paramContext)
  {
    String str = paramContext.getPackageName();
    Resources localResources = paramContext.getResources();
    notice_alert_drawable = loadIdentifierResource(localResources, "com_tencent_msdk_notice_popup", "drawable", str);
    noticemain = loadIdentifierResource(localResources, "noticemain", "id", str);
    alertNoticeImage = loadIdentifierResource(localResources, "popupImage", "id", str);
    confirmbtn = loadIdentifierResource(localResources, "confirmbtn", "id", str);
    morebtn = loadIdentifierResource(localResources, "morebtn", "id", str);
    layout_text_notice = loadIdentifierResource(localResources, "com_tencent_msdk_notice_text", "layout", str);
    layout_text_notice_url = loadIdentifierResource(localResources, "com_tencent_msdk_notice_text_url", "layout", str);
    noticeTitle = loadIdentifierResource(localResources, "noticeTitle", "id", str);
    noticeContent = loadIdentifierResource(localResources, "noticeContent", "id", str);
  }

  public static void loadWebLayout(Context paramContext)
  {
    String str = paramContext.getPackageName();
    Resources localResources = paramContext.getResources();
    notice_alert_drawable = loadIdentifierResource(localResources, "com_tencent_msdk_notice_popup", "drawable", str);
    noticemain = loadIdentifierResource(localResources, "noticemain", "id", str);
    alertNoticeImage = loadIdentifierResource(localResources, "popupImage", "id", str);
    confirmbtn = loadIdentifierResource(localResources, "confirmbtn", "id", str);
    morebtn = loadIdentifierResource(localResources, "morebtn", "id", str);
    layout_web_notice = loadIdentifierResource(localResources, "com_tencent_msdk_notice_web", "layout", str);
    layout_web_notice_url = loadIdentifierResource(localResources, "com_tencent_msdk_notice_web_url", "layout", str);
    noticeContent = loadIdentifierResource(localResources, "noticeContent", "id", str);
    noticeContentLine = loadIdentifierResource(localResources, "noticeContentLine", "id", str);
    tempLoadLayer = loadIdentifierResource(localResources, "tempLoadLayer", "id", str);
    tempLoadFailed = loadIdentifierResource(localResources, "tempLoadFailed", "id", str);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.NoticeResID
 * JD-Core Version:    0.6.0
 */