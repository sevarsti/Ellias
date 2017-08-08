package com.tenpay.tenpayplugin.view;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import java.util.Calendar;

public class ValidDateEdit extends TenpayEditText
{
  public static final int VALID_FORMAT_ERROR = 1;
  public static final int VALID_OK = 0;
  public static final int VALID_OUT_TIME = 3;
  public static final int VALID_TIME_ERROR = 2;
  private int a;
  private boolean b;

  public ValidDateEdit(Context paramContext)
  {
    super(paramContext);
  }

  public ValidDateEdit(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public String getData()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str1 = getText().toString();
    if (str1.length() > 4)
    {
      String str2 = str1.substring(0, 2);
      localStringBuilder.append(str1.substring(3, 5));
      localStringBuilder.append(str2);
    }
    return localStringBuilder.toString();
  }

  public int isValid(long paramLong)
  {
    String str1 = getText().toString();
    if (str1.length() < 5);
    do
      return 1;
    while (str1.indexOf("/") != 2);
    String str2 = str1.substring(0, 2);
    String str3 = str1.substring(3, 5);
    int i = Integer.parseInt(str2);
    int j = Integer.parseInt(str3);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    int k = 1 + localCalendar.get(2);
    int m = localCalendar.get(1) % 1000;
    if (i > 12)
      return 2;
    if ((j < m) || ((j == m) && (i < k)))
      return 3;
    return 0;
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    super.onTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
    String str1 = getText().toString();
    String str7;
    String str8;
    if ((paramInt2 > paramInt3) && (str1.length() > 1) && (str1.indexOf('/') < 0))
    {
      if (str1.length() > 2)
      {
        str1 = str1.substring(0, 1) + str1.substring(2);
        paramInt3--;
      }
    }
    else
    {
      if (this.b)
        break label393;
      this.a = (paramInt1 + paramInt3);
      if (str1.length() > 0)
      {
        if (this.a > str1.length())
          break label399;
        str7 = str1.substring(0, this.a);
        str8 = str7.replaceAll("/", "");
      }
    }
    label393: label399: for (int i = str7.length() - str8.length(); ; i = 0)
    {
      String str2 = str1.replaceAll("/", "");
      StringBuffer localStringBuffer = new StringBuffer();
      label235: String str3;
      String str5;
      String str6;
      if (str2.length() > 1)
      {
        localStringBuffer.append(str2.substring(0, 2));
        localStringBuffer.append("/");
        localStringBuffer.append(str2.subSequence(2, str2.length()));
        setSelection(str2.length());
        str3 = localStringBuffer.toString();
        if (this.a > str3.length())
          break label366;
        str5 = str3.substring(0, this.a);
        str6 = str5.replaceAll("/", "");
      }
      label366: String str4;
      for (int j = str5.length() - str6.length(); ; j = str3.length() - str4.length())
      {
        int k = j + this.a - i;
        this.b = true;
        setText(str3);
        if ((k == 2) && (str3.length() > 2))
          k = 3;
        if (k > 0)
          setSelection(k);
        return;
        str1 = str1.substring(0, 1);
        break;
        localStringBuffer.append(str2);
        break label235;
        str4 = str3.replaceAll("/", "");
      }
      this.b = false;
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.ValidDateEdit
 * JD-Core Version:    0.6.0
 */