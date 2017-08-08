package com.tencent.msdk.webview;

import android.content.Context;
import android.content.res.Resources;
import com.tencent.msdk.tools.ResID;

public class WebViewResID extends ResID
{
  public static int back;
  public static int color_transparent;
  public static int dimen_more_cancel_height;
  public static int dimen_more_cancel_land_height;
  public static int dimen_titlebar_height;
  public static int dimen_titlebar_land_height;
  public static int dlg_btn_cancel;
  public static int dlg_gridview;
  public static int drawable_open_by_otherbrowser;
  public static int drawable_open_by_qqbrowser;
  public static int drawable_share_to_qq;
  public static int drawable_share_to_qzone;
  public static int drawable_share_to_wx;
  public static int drawable_share_to_wx_friend;
  public static int forward;
  public static int itemImage;
  public static int itemText;
  public static int layout_dlg_gridview_item;
  public static int layout_sheet_dlg;
  public static int layout_thrdcall_window;
  public static int more;
  public static int openByQQBrowser;
  public static int playout;
  public static int progress;
  public static int refresh;
  public static int return_app;
  public static int stop;
  public static int str_shareToQQ;
  public static int str_shareToQQ_url_too_long;
  public static int str_shareToQzone;
  public static int str_shareToWx;
  public static int str_shareToWxFriend;
  public static int str_thrdcall_cancel;
  public static int str_thrdcall_confirm;
  public static int str_thrdcall_openbrowser;
  public static int str_thrdcall_openqbx;
  public static int str_thrdcall_recom_mtt_content;
  public static int str_thrdcall_recom_mtt_title;
  public static int str_upload_file_title;
  public static int style_SheetDialogAnimation;
  public static int style_SheetDialogTheme;
  public static int title;
  public static int titleBar;
  public static int toolbar;
  public static int toolbar_holder;
  public static int webview;

  public static void init(Context paramContext)
  {
    String str = paramContext.getPackageName();
    Resources localResources = paramContext.getResources();
    layout_thrdcall_window = loadIdentifierResource(localResources, "msdk_thrdcall_window", "layout", str);
    layout_sheet_dlg = loadIdentifierResource(localResources, "msdk_thrdcall_dlg_sheet", "layout", str);
    layout_dlg_gridview_item = loadIdentifierResource(localResources, "msdk_thrdcall_dlg_griditem", "layout", str);
    playout = loadIdentifierResource(localResources, "playout", "id", str);
    webview = loadIdentifierResource(localResources, "webview", "id", str);
    title = loadIdentifierResource(localResources, "title", "id", str);
    progress = loadIdentifierResource(localResources, "progress", "id", str);
    refresh = loadIdentifierResource(localResources, "refresh", "id", str);
    stop = loadIdentifierResource(localResources, "stop", "id", str);
    back = loadIdentifierResource(localResources, "back", "id", str);
    openByQQBrowser = loadIdentifierResource(localResources, "open_by_qq_browser", "id", str);
    forward = loadIdentifierResource(localResources, "forward", "id", str);
    return_app = loadIdentifierResource(localResources, "return_app", "id", str);
    more = loadIdentifierResource(localResources, "more", "id", str);
    dlg_gridview = loadIdentifierResource(localResources, "dlg_gridview", "id", str);
    dlg_btn_cancel = loadIdentifierResource(localResources, "dlg_btn_cancel", "id", str);
    itemImage = loadIdentifierResource(localResources, "itemImage", "id", str);
    itemText = loadIdentifierResource(localResources, "itemText", "id", str);
    titleBar = loadIdentifierResource(localResources, "titlebar", "id", str);
    toolbar = loadIdentifierResource(localResources, "toolbar", "id", str);
    toolbar_holder = loadIdentifierResource(localResources, "toolbar_holder", "id", str);
    drawable_open_by_qqbrowser = loadIdentifierResource(localResources, "msdk_open_by_qq_browser", "drawable", str);
    drawable_open_by_otherbrowser = loadIdentifierResource(localResources, "msdk_open_by_other_browser", "drawable", str);
    drawable_share_to_wx_friend = loadIdentifierResource(localResources, "msdk_share_friend", "drawable", str);
    drawable_share_to_wx = loadIdentifierResource(localResources, "msdk_share_weixin", "drawable", str);
    drawable_share_to_qq = loadIdentifierResource(localResources, "msdk_share_qq", "drawable", str);
    drawable_share_to_qzone = loadIdentifierResource(localResources, "msdk_share_qzone", "drawable", str);
    style_SheetDialogTheme = loadIdentifierResource(localResources, "SheetDialogTheme", "style", str);
    style_SheetDialogAnimation = loadIdentifierResource(localResources, "SheetDialogAnimation", "style", str);
    str_thrdcall_recom_mtt_title = loadIdentifierResource(localResources, "thrdcall_recom_mtt_title", "string", str);
    str_thrdcall_recom_mtt_content = loadIdentifierResource(localResources, "thrdcall_recom_mtt_content", "string", str);
    str_thrdcall_confirm = loadIdentifierResource(localResources, "thrdcall_confirm", "string", str);
    str_thrdcall_cancel = loadIdentifierResource(localResources, "thrdcall_cancel", "string", str);
    str_thrdcall_openqbx = loadIdentifierResource(localResources, "thrdcall_openqbx", "string", str);
    str_thrdcall_openbrowser = loadIdentifierResource(localResources, "thrdcall_openbrowser", "string", str);
    str_shareToQzone = loadIdentifierResource(localResources, "msdk_more_shareToQzone", "string", str);
    str_shareToWxFriend = loadIdentifierResource(localResources, "msdk_more_shareToWxFriend", "string", str);
    str_shareToQQ = loadIdentifierResource(localResources, "msdk_more_shareToQQ", "string", str);
    str_shareToWx = loadIdentifierResource(localResources, "msdk_more_shareToWx", "string", str);
    str_shareToQQ_url_too_long = loadIdentifierResource(localResources, "msdk_more_shareToQQ_url_too_long", "string", str);
    str_upload_file_title = loadIdentifierResource(localResources, "msdk_upload_file_title", "string", str);
    color_transparent = loadIdentifierResource(localResources, "thrdcall_transparent", "color", str);
    dimen_titlebar_height = loadIdentifierResource(localResources, "thrdcall_toolbar_height", "dimen", str);
    dimen_titlebar_land_height = loadIdentifierResource(localResources, "thrdcall_toolbar_land_height", "dimen", str);
    dimen_more_cancel_height = loadIdentifierResource(localResources, "thrdcall_more_cancel_margin", "dimen", str);
    dimen_more_cancel_land_height = loadIdentifierResource(localResources, "thrdcall_more_cancel_margin_land", "dimen", str);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.webview.WebViewResID
 * JD-Core Version:    0.6.0
 */