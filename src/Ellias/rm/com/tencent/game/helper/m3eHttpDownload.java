package com.tencent.game.helper;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

public class m3eHttpDownload
{
  private static m3eHttpDownload instance = new m3eHttpDownload();
  public String fileDir = "";
  public String fileName = "";
  public String fileURL = "";
  public int iHeight = 0;
  public int iWidth = 0;
  private Activity mActivity;
  public int misIcon = 0;

  private void createCacheDir()
  {
    String str1 = m3eFileHelper.getInstance().getUserDirectory();
    m3eFileHelper.getInstance().createDir(str1);
    String str2 = m3eFileHelper.getInstance().getResDirectory();
    m3eFileHelper.getInstance().createDir(str2);
    String str3 = str2 + "/song";
    m3eFileHelper.getInstance().createDir(str3);
    String str4 = str2 + "/UserIcon";
    m3eFileHelper.getInstance().createDir(str4);
    String str5 = str2 + "/bagsong";
    m3eFileHelper.getInstance().createDir(str5);
    String str6 = str2 + "/icon";
    m3eFileHelper.getInstance().createDir(str6);
  }

  public static m3eHttpDownload getInstance()
  {
    return instance;
  }

  public void beginDown(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.fileName = paramString2;
    this.fileDir = paramString1;
    this.fileURL = paramString3;
    this.iWidth = Integer.valueOf(paramString4).intValue();
    this.iHeight = Integer.valueOf(paramString5).intValue();
    this.misIcon = Integer.valueOf(paramString6).intValue();
    createCacheDir();
    Message localMessage = new Message();
    localMessage.what = 6;
    m3eActivity.handler.sendMessage(localMessage);
  }

  public void setActivity(Activity paramActivity)
  {
    this.mActivity = paramActivity;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.m3eHttpDownload
 * JD-Core Version:    0.6.0
 */