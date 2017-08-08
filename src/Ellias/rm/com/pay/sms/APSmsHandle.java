package com.pay.sms;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.EditText;
import com.pay.ui.qdsafe.APSmmActivity;

public class APSmsHandle extends Handler
{
  private APSmmActivity a;

  public APSmsHandle(APSmmActivity paramAPSmmActivity)
  {
    this.a = paramAPSmmActivity;
  }

  private static String a(String paramString)
  {
    int i = 0;
    try
    {
      if ((paramString.length() > 8) && (paramString.contains("消费Q币验证码")))
      {
        int j = -1;
        while (true)
        {
          if (i >= paramString.length())
          {
            if (j == -1)
              break;
            return paramString.substring(0, j);
          }
          int k = paramString.charAt(i);
          if (((k > 57) || (k < 48)) && (j == -1))
          {
            if (((i != 6) && (i != 8)) || (k != 65288))
              break;
            j = i;
          }
          if ((j != -1) && (((i == j + 1) || (j + 2 == i)) && ((k != 81) || (((i == j + 3) || (i == j + 4)) && (k < 48) && (k > 57)))))
            break;
          i++;
        }
      }
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    String str1 = (String)paramMessage.obj;
    if (!TextUtils.isEmpty(str1))
    {
      String str2 = a(str1);
      if (str2 != null)
      {
        this.a.vercodeEdit.setText(str2);
        this.a.vercodeEdit.setSelection(str2.length());
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.sms.APSmsHandle
 * JD-Core Version:    0.6.0
 */