package com.tencent.msdk.api;

import android.util.SparseArray;
import com.tencent.msdk.tools.Logger;
import java.util.ArrayList;

public class WGQZonePermissions
{
  private static final SparseArray<String> QzPmsMap = new SparseArray();
  public static final int eOPEN_ALL = 16777215;
  public static final int eOPEN_NONE = 0;
  public static final int eOPEN_PERMISSION_ADD_ALBUM = 8;
  public static final int eOPEN_PERMISSION_ADD_IDOL = 16;
  public static final int eOPEN_PERMISSION_ADD_ONE_BLOG = 32;
  public static final int eOPEN_PERMISSION_ADD_PIC_T = 64;
  public static final int eOPEN_PERMISSION_ADD_SHARE = 128;
  public static final int eOPEN_PERMISSION_ADD_TOPIC = 256;
  public static final int eOPEN_PERMISSION_CHECK_PAGE_FANS = 512;
  public static final int eOPEN_PERMISSION_DEL_IDOL = 1024;
  public static final int eOPEN_PERMISSION_DEL_T = 2048;
  public static final int eOPEN_PERMISSION_GET_APP_FRIENDS = 8388608;
  public static final int eOPEN_PERMISSION_GET_FANSLIST = 4096;
  public static final int eOPEN_PERMISSION_GET_IDOLLIST = 8192;
  public static final int eOPEN_PERMISSION_GET_INFO = 16384;
  public static final int eOPEN_PERMISSION_GET_INTIMATE_FRIENDS_WEIBO = 2097152;
  public static final int eOPEN_PERMISSION_GET_OTHER_INFO = 32768;
  public static final int eOPEN_PERMISSION_GET_REPOST_LIST = 65536;
  public static final int eOPEN_PERMISSION_GET_SIMPLE_USER_INFO = 4;
  public static final int eOPEN_PERMISSION_GET_USER_INFO = 2;
  public static final int eOPEN_PERMISSION_GET_VIP_INFO = 524288;
  public static final int eOPEN_PERMISSION_GET_VIP_RICH_INFO = 1048576;
  public static final int eOPEN_PERMISSION_LIST_ALBUM = 131072;
  public static final int eOPEN_PERMISSION_MATCH_NICK_TIPS_WEIBO = 4194304;
  public static final int eOPEN_PERMISSION_UPLOAD_PIC = 262144;

  static
  {
    QzPmsMap.put(2, "get_user_info");
    QzPmsMap.put(4, "get_simple_userinfo");
    QzPmsMap.put(8, "add_album");
    QzPmsMap.put(16, "add_idol");
    QzPmsMap.put(32, "add_one_blog");
    QzPmsMap.put(64, "add_pic_t");
    QzPmsMap.put(128, "add_share");
    QzPmsMap.put(256, "add_topic");
    QzPmsMap.put(512, "check_page_fans");
    QzPmsMap.put(1024, "del_idol");
    QzPmsMap.put(2048, "del_t");
    QzPmsMap.put(4096, "get_fanslist");
    QzPmsMap.put(8192, "get_idollist");
    QzPmsMap.put(16384, "get_info");
    QzPmsMap.put(32768, "get_other_info");
    QzPmsMap.put(65536, "get_repost_list");
    QzPmsMap.put(131072, "list_album");
    QzPmsMap.put(262144, "upload_pic");
    QzPmsMap.put(2097152, "get_intimate_friends");
    QzPmsMap.put(8388608, "get_app_friends");
  }

  public static String[] getPermissionStr(int paramInt)
  {
    Logger.d("" + paramInt);
    if (paramInt == 16777215)
      return new String[] { "all" };
    if (paramInt == 0)
      return new String[] { "none" };
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < QzPmsMap.size(); i++)
    {
      int j = QzPmsMap.keyAt(i);
      if ((paramInt & j) == 0)
        continue;
      Logger.d("" + j);
      localArrayList.add(QzPmsMap.get(j));
    }
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.api.WGQZonePermissions
 * JD-Core Version:    0.6.0
 */