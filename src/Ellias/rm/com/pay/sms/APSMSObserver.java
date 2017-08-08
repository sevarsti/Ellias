package com.pay.sms;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import com.pay.tool.APPassWordTools;
import java.io.PrintStream;
import java.util.List;

public class APSMSObserver extends ContentObserver
{
  private ContentResolver a;
  private Handler b;

  public APSMSObserver(ContentResolver paramContentResolver, Handler paramHandler)
  {
    super(paramHandler);
    this.a = paramContentResolver;
    this.b = paramHandler;
  }

  public void onChange(boolean paramBoolean)
  {
    List localList = APPassWordTools.getSmsInfo(Uri.parse("content://sms/inbox"), this.a);
    if (localList.size() > 0)
    {
      Message localMessage = this.b.obtainMessage();
      localMessage.obj = ((APSmsInfo)localList.get(0)).smsBody;
      System.out.println("time=" + ((APSmsInfo)localList.get(0)).date);
      this.b.sendMessage(localMessage);
    }
    super.onChange(paramBoolean);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.sms.APSMSObserver
 * JD-Core Version:    0.6.0
 */