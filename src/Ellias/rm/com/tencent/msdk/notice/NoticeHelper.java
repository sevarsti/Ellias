package com.tencent.msdk.notice;

import com.tencent.beacon.event.UserAction;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.db.AppDBModel;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;

public class NoticeHelper
{
  private static AppDBModel appDb;
  private static String mMatId;
  private static long sMatIdTimeOut = 10000L;

  static
  {
    mMatId = "";
    appDb = new AppDBModel();
  }

  public static void reqMatid(MatIdCallback paramMatIdCallback)
  {
    String str = appDb.getMatId(WeGame.getInstance().qq_appid);
    if (!T.ckIsEmpty(str))
    {
      paramMatIdCallback.onSuccess(str);
      return;
    }
    new Thread(paramMatIdCallback)
    {
      public void run()
      {
        long l = System.currentTimeMillis();
        while (System.currentTimeMillis() < l + NoticeHelper.sMatIdTimeOut)
          try
          {
            NoticeHelper.access$102(UserAction.getQIMEI());
            Logger.d("reqMatid matid: " + NoticeHelper.mMatId);
            if (!T.ckIsEmpty(NoticeHelper.mMatId))
            {
              this.val$callback.onSuccess(NoticeHelper.mMatId);
              return;
            }
          }
          catch (Exception localException)
          {
            while (true)
              localException.printStackTrace();
            try
            {
              sleep(1000L);
            }
            catch (InterruptedException localInterruptedException)
            {
              localInterruptedException.printStackTrace();
            }
          }
        this.val$callback.onTimeout();
      }
    }
    .start();
  }

  static abstract interface MatIdCallback
  {
    public abstract void onSuccess(String paramString);

    public abstract void onTimeout();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.NoticeHelper
 * JD-Core Version:    0.6.0
 */