package com.tenpay.tenpayplugin;

import android.content.res.Resources;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tenpay.tenpayplugin.view.ClearableEditText;
import com.tenpay.tenpayplugin.view.TenpayNumberEditText;
import com.tenpay.tenpayplugin.view.ValidDateEdit;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

class TenpayNewCardActivity$8
  implements TextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
    String str1 = TenpayNewCardActivity.r(this.a).getData();
    int i = str1.length();
    if (i < 10)
    {
      TenpayNewCardActivity.s(this.a).setVisibility(8);
      TenpayNewCardActivity.t(this.a).setVisibility(8);
      TenpayNewCardActivity.u(this.a).setText("");
      TenpayNewCardActivity.v(this.a).setText("");
      TenpayNewCardActivity.f(this.a).setText("");
      TenpayNewCardActivity.w(this.a).setVisibility(8);
      TenpayNewCardActivity.x(this.a).setVisibility(8);
      return;
    }
    JSONObject localJSONObject1;
    String str6;
    int i1;
    label151: int j;
    if (TenpayNewCardActivity.r(this.a).changeStart < 12)
    {
      if (TenpayNewCardActivity.h(this.a) == null)
        break label1826;
      String str5 = str1.substring(0, 10);
      localJSONObject1 = null;
      str6 = str5;
      i1 = 0;
      if (str6.length() > 2)
        break label737;
      j = i1;
    }
    while (true)
    {
      String str2;
      int n;
      label266: Object localObject1;
      if (localJSONObject1 != null)
      {
        this.a.h = localJSONObject1.optInt("len", -1);
        TenpayNumberEditText localTenpayNumberEditText = TenpayNewCardActivity.r(this.a);
        InputFilter[] arrayOfInputFilter = new InputFilter[1];
        arrayOfInputFilter[0] = new InputFilter.LengthFilter(this.a.h + (-1 + this.a.h) / 4);
        localTenpayNumberEditText.setFilters(arrayOfInputFilter);
        str2 = localJSONObject1.optString("sname").toUpperCase();
        if (j != 0)
        {
          if (TenpayNewCardActivity.y(this.a) == null)
            break label1110;
          n = 0;
          if (n >= TenpayNewCardActivity.y(this.a).size())
            localObject1 = null;
        }
      }
      while (true)
      {
        label284: int k;
        label302: Object localObject2;
        if ((localObject1 == null) && (TenpayNewCardActivity.A(this.a) != null))
        {
          k = 0;
          if (k < TenpayNewCardActivity.A(this.a).size());
        }
        else
        {
          localObject2 = localObject1;
          label321: if (TenpayNewCardActivity.B(this.a) != localObject2)
          {
            TenpayNewCardActivity.s(this.a).setVisibility(8);
            TenpayNewCardActivity.t(this.a).setVisibility(8);
            TenpayNewCardActivity.u(this.a).setText("");
            TenpayNewCardActivity.v(this.a).setText("");
          }
          TenpayNewCardActivity.a(this.a, (JSONObject)localObject2);
          if (localObject2 == null)
            break label1312;
          TenpayNewCardActivity.b(this.a, "define." + ((JSONObject)localObject2).optString("code"));
          String str3 = ((JSONObject)localObject2).optString("name");
          StringBuilder localStringBuilder1 = new StringBuilder();
          localStringBuilder1.append(this.a.getResources().getString(TenpayResourceUtil.getStringId(this.a, "unipay_tenpay_sure_bank")));
          localStringBuilder1.append(str3);
          TenpayNewCardActivity.C(this.a).setText(localStringBuilder1.toString());
          AnimationSet localAnimationSet = new AnimationSet(true);
          AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
          localAlphaAnimation.setDuration(500L);
          TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, -1.0F, 1, 0.0F);
          localTranslateAnimation.setDuration(500L);
          localAnimationSet.addAnimation(localTranslateAnimation);
          localAnimationSet.addAnimation(localAlphaAnimation);
          LayoutAnimationController localLayoutAnimationController = new LayoutAnimationController(localAnimationSet, 0.5F);
          TenpayNewCardActivity.w(this.a).setLayoutAnimation(localLayoutAnimationController);
          TenpayNewCardActivity.s(this.a).setLayoutAnimation(localLayoutAnimationController);
          TenpayNewCardActivity.t(this.a).setLayoutAnimation(localLayoutAnimationController);
          TenpayNewCardActivity.x(this.a).setVisibility(8);
          TenpayNewCardActivity.w(this.a).setVisibility(0);
        }
        label737: JSONObject localJSONObject2;
        while (true)
        {
          if ((this.a.h <= 0) || (i <= -1 + this.a.h))
            break label1482;
          this.a.initInputBankInfo(TenpayNewCardActivity.B(this.a));
          if (TenpayNewCardActivity.B(this.a) == null)
            break;
          if (TenpayNewCardActivity.s(this.a).getVisibility() != 0)
            break label1668;
          if ((TenpayNewCardActivity.v(this.a).getVisibility() != 0) || (TenpayNewCardActivity.v(this.a).getText().toString().length() != 0))
            break label1484;
          TenpayNewCardActivity.v(this.a).requestFocus();
          return;
          label974: label980: for (int i2 = 0; ; i2++)
          {
            if (i2 >= TenpayNewCardActivity.h(this.a).length());
            while (true)
            {
              if (localJSONObject1 == null)
                break label986;
              j = i1;
              break;
              String str7 = TenpayNewCardActivity.h(this.a).optJSONObject(i2).optString("bin");
              int i3;
              if (str7.equals(str6))
                i3 = 1;
              while (true)
              {
                if (i3 < 0)
                  break label980;
                localJSONObject1 = TenpayNewCardActivity.h(this.a).optJSONObject(i2);
                if (localJSONObject1.optInt("type") != 2)
                  break label974;
                i1 = 1;
                break;
                StringBuilder localStringBuilder2 = new StringBuilder();
                localStringBuilder2.append('|');
                localStringBuilder2.append(str6);
                localStringBuilder2.append('|');
                i3 = str7.indexOf(localStringBuilder2.toString());
                if (i3 >= 0)
                  continue;
                StringBuilder localStringBuilder3 = new StringBuilder();
                localStringBuilder3.append(str6);
                localStringBuilder3.append('|');
                if (str7.startsWith(localStringBuilder3.toString()))
                {
                  i3 = 1;
                  continue;
                }
                StringBuilder localStringBuilder4 = new StringBuilder();
                localStringBuilder4.append('|');
                localStringBuilder4.append(str6);
                if (!str7.endsWith(localStringBuilder4.toString()))
                  continue;
                i3 = 1;
              }
              i1 = 0;
            }
          }
          label986: str6 = str6.substring(0, -1 + str6.length());
          break label151;
          localJSONObject2 = (JSONObject)TenpayNewCardActivity.y(this.a).get(n);
          if (localJSONObject2.optString("sname").equals(str2))
          {
            if (this.a.mLastPayType == -1)
              break label1819;
            this.a.a = this.a.mLastPayType;
            this.a.mLastPayType = -1;
            localObject1 = localJSONObject2;
            break label284;
          }
          n++;
          break label266;
          if (TenpayNewCardActivity.z(this.a) != null);
          for (int m = 0; ; m++)
          {
            if (m >= TenpayNewCardActivity.z(this.a).size())
            {
              label1110: localObject1 = null;
              break;
            }
            localJSONObject2 = (JSONObject)TenpayNewCardActivity.z(this.a).get(m);
            if (!localJSONObject2.optString("sname").equals(str2))
              continue;
            if (this.a.mLastPayType == -1)
              break label1819;
            this.a.a = this.a.mLastPayType;
            this.a.mLastPayType = -1;
            localObject1 = localJSONObject2;
            break;
          }
          localObject2 = (JSONObject)TenpayNewCardActivity.A(this.a).get(k);
          String str4 = ((JSONObject)localObject2).optString("type");
          if ((("FASTPAY_DEBIT".equals(str4)) && (j == 0)) || (("FASTPAY_CREDIT".equals(str4)) && (j != 0) && (((JSONObject)localObject2).optString("sname").equals(str2))))
          {
            if (this.a.a != 6)
              this.a.mLastPayType = this.a.a;
            this.a.a = 6;
            break label321;
          }
          k++;
          break label302;
          label1312: TenpayNewCardActivity.b(this.a, "undefine");
          TenpayNewCardActivity.s(this.a).setLayoutAnimation(null);
          TenpayNewCardActivity.t(this.a).setLayoutAnimation(null);
          TenpayNewCardActivity.D(this.a).setText(TenpayResourceUtil.getStringId(this.a, "unipay_tenpay_ple_sel_bank"));
          TenpayNewCardActivity.w(this.a).setVisibility(8);
          TenpayNewCardActivity.x(this.a).setVisibility(0);
          continue;
          TenpayNewCardActivity.b(this.a, "undefine");
          TenpayNewCardActivity.a(this.a, null);
          this.a.h = -1;
          TenpayNewCardActivity.s(this.a).setLayoutAnimation(null);
          TenpayNewCardActivity.t(this.a).setLayoutAnimation(null);
          TenpayNewCardActivity.D(this.a).setText(TenpayResourceUtil.getStringId(this.a, "unipay_tenpay_ple_sel_bank"));
          TenpayNewCardActivity.w(this.a).setVisibility(8);
          TenpayNewCardActivity.x(this.a).setVisibility(0);
        }
        label1482: break;
        label1484: if (TenpayNewCardActivity.u(this.a).getText().toString().length() == 0)
        {
          TenpayNewCardActivity.u(this.a).requestFocus();
          return;
        }
        if ((TenpayNewCardActivity.E(this.a).getVisibility() == 0) && (TenpayNewCardActivity.E(this.a).isEnabled()) && (TenpayNewCardActivity.E(this.a).getText().toString().length() == 0))
        {
          TenpayNewCardActivity.E(this.a).requestFocus();
          return;
        }
        if ((TenpayNewCardActivity.F(this.a).getVisibility() == 0) && (TenpayNewCardActivity.F(this.a).isEnabled()) && (TenpayNewCardActivity.F(this.a).getText().toString().length() == 0))
        {
          TenpayNewCardActivity.F(this.a).requestFocus();
          return;
        }
        if (TenpayNewCardActivity.f(this.a).getText().toString().length() != 0)
          break;
        TenpayNewCardActivity.f(this.a).requestFocus();
        return;
        label1668: if ((TenpayNewCardActivity.E(this.a).getVisibility() == 0) && (TenpayNewCardActivity.E(this.a).isEnabled()) && (TenpayNewCardActivity.E(this.a).getText().toString().length() == 0))
        {
          TenpayNewCardActivity.E(this.a).requestFocus();
          return;
        }
        if ((TenpayNewCardActivity.F(this.a).getVisibility() == 0) && (TenpayNewCardActivity.F(this.a).isEnabled()) && (TenpayNewCardActivity.F(this.a).getText().toString().length() == 0))
        {
          TenpayNewCardActivity.F(this.a).requestFocus();
          return;
        }
        if (TenpayNewCardActivity.f(this.a).getText().toString().length() != 0)
          break;
        TenpayNewCardActivity.f(this.a).requestFocus();
        return;
        label1819: localObject1 = localJSONObject2;
      }
      label1826: localJSONObject1 = null;
      j = 0;
    }
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.8
 * JD-Core Version:    0.6.0
 */