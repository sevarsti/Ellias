package com.tenpay.tenpayplugin.view;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;

public class TenpayNumberEditText extends ClearableEditText
{
  private int c;
  public int changeStart;
  private boolean d;

  public TenpayNumberEditText(Context paramContext)
  {
    super(paramContext);
  }

  public TenpayNumberEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public TenpayNumberEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public String getData()
  {
    return getText().toString().replaceAll(" ", "");
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    super.onTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
    String str1 = getText().toString();
    String str7;
    String str8;
    if (getGravity() == 5)
    {
      if ((str1 != null) && (str1.length() > 0))
        setGravity(5);
    }
    else
    {
      if (this.d)
        break label327;
      this.changeStart = paramInt1;
      this.c = (paramInt1 + paramInt3);
      if (str1.length() > 0)
      {
        if (this.c > str1.length())
          break label333;
        str7 = str1.substring(0, this.c);
        str8 = str7.replaceAll(" ", "");
      }
    }
    label145: label300: label327: label333: for (int i = str7.length() - str8.length(); ; i = 0)
    {
      String str2 = str1.replaceAll(" ", "");
      StringBuffer localStringBuffer = new StringBuffer();
      int j = 0;
      String str3;
      String str5;
      String str6;
      if (j + 4 >= str2.length())
      {
        localStringBuffer.append(str2.substring(j));
        str3 = localStringBuffer.toString();
        if (!str3.equals(str1))
        {
          this.d = true;
          setText(str3);
          if (this.c > str3.length())
            break label300;
          str5 = str3.substring(0, this.c);
          str6 = str5.replaceAll(" ", "");
        }
      }
      String str4;
      for (int k = str5.length() - str6.length(); ; k = str3.length() - str4.length())
      {
        setSelection(k + this.c - i);
        return;
        setGravity(3);
        break;
        localStringBuffer.append(str2.substring(j, j + 4));
        localStringBuffer.append(" ");
        j += 4;
        break label145;
        str4 = str3.replaceAll(" ", "");
      }
      this.d = false;
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.TenpayNumberEditText
 * JD-Core Version:    0.6.0
 */