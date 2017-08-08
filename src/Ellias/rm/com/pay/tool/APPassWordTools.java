package com.pay.tool;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.pay.sms.APSmsInfo;
import com.pay.ui.qdsafe.APSafeCenterWebActivity;
import com.pay.ui.qdsafe.APSmmActivity;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class APPassWordTools
{
  public static final int REQUEST_PASSCENTER = 100001;
  public static final int REQUEST_PASSCENTER_SMS = 100003;
  public static final int REQUEST_PASSCENTER_h5 = 100002;
  public static final int VER_RESULT_FAIL = 11;
  public static final int VER_RESULT_SUCESS = 10;
  public static long sendTime = System.currentTimeMillis();

  public static void closeKeyboard(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }

  public static List getSmsInfo(Uri paramUri, ContentResolver paramContentResolver)
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = { "_id", "address", "person", "body", "date", "type" };
    String str = String.valueOf(sendTime - 30000L);
    System.out.println("data=" + str);
    Cursor localCursor = paramContentResolver.query(paramUri, arrayOfString, "read=? and date>?", new String[] { "0", str }, "date desc");
    int i = localCursor.getColumnIndex("person");
    int j = localCursor.getColumnIndex("address");
    int k = localCursor.getColumnIndex("body");
    int m = localCursor.getColumnIndex("date");
    int n = localCursor.getColumnIndex("type");
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        localCursor.close();
        return localArrayList;
      }
      APSmsInfo localAPSmsInfo = new APSmsInfo();
      localAPSmsInfo.name = localCursor.getString(i);
      localAPSmsInfo.date = localCursor.getString(m);
      localAPSmsInfo.phoneNumber = localCursor.getString(j);
      localAPSmsInfo.smsBody = localCursor.getString(k);
      localAPSmsInfo.type = localCursor.getString(n);
      localArrayList.add(localAPSmsInfo);
    }
  }

  public static void launPassActicity(Activity paramActivity, boolean paramBoolean1, int paramInt, String paramString1, String paramString2, boolean paramBoolean2)
  {
    Intent localIntent;
    if (paramBoolean1)
    {
      localIntent = new Intent(paramActivity, APSmmActivity.class);
      localIntent.putExtra("isOnlyMethod", paramBoolean2);
    }
    while (true)
    {
      localIntent.putExtra("requesturl", paramString1);
      localIntent.putExtra("count", paramInt);
      localIntent.putExtra("smsinfo", paramString2);
      paramActivity.startActivityForResult(localIntent, 100001);
      return;
      localIntent = new Intent(paramActivity, APSafeCenterWebActivity.class);
      localIntent.putExtra("isOnlyMethod", false);
    }
  }

  public static void openKeyboard(Activity paramActivity, View paramView)
  {
    new Timer().schedule(new d(paramActivity), 1L);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APPassWordTools
 * JD-Core Version:    0.6.0
 */