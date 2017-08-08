package com.tenpay.tenpayplugin;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tenpay.a.a.a;
import com.tenpay.a.a.c;
import com.tenpay.a.a.e;
import com.tenpay.a.c.b;
import com.tenpay.tenpayplugin.view.BankSelectDialog;
import com.tenpay.tenpayplugin.view.ClearableEditText;
import com.tenpay.tenpayplugin.view.TenpayNumberEditText;
import com.tenpay.tenpayplugin.view.TenpayProgressDialog;
import com.tenpay.tenpayplugin.view.TenpayResizeLinearLayout;
import com.tenpay.tenpayplugin.view.TenpaySecureEditText;
import com.tenpay.tenpayplugin.view.ValidDateEdit;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class TenpayNewCardActivity extends Activity
  implements View.OnClickListener
{
  public static final int STATE_NEW_SELECI_BANK = 2;
  public static final int STATE_NEW_SETPSW = 4;
  public static final int STATE_YZ = 5;
  private EditText A;
  private Button B;
  private Button C;
  private Button D;
  private LinearLayout E;
  private ScrollView F;
  private EditText G;
  private TenpaySecureEditText H;
  private Button I;
  private ProgressDialog J;
  private ArrayList K;
  private ArrayList L;
  private ArrayList M;
  private JSONObject N;
  private String O;
  private String P;
  private String Q;
  private int R;
  private View S;
  private long T;
  private int U;
  private TextView V;
  private TextView W;
  private TenpayNewCardActivity.MyBLCallbackListener X;
  private byte[] Y;
  private String Z;
  int a = 0;
  private LinearLayout aA;
  private EditText aB;
  private TableLayout aC;
  private Button aD;
  private Button aE;
  private Button aF;
  private Button aG;
  private Button aH;
  private Button aI;
  private Button aJ;
  private Button aK;
  private Button aL;
  private Button aM;
  private Button aN;
  private ImageButton aO;
  private int aP;
  private int aQ;
  private BankSelectDialog aR;
  private Dialog aS;
  private JSONObject aT;
  private String aa;
  private String ab;
  private String ac;
  private int ad;
  private String ae;
  private TextView af;
  private boolean ag;
  private LinearLayout ah;
  private TenpayNumberEditText ai;
  private Button aj;
  private JSONArray ak;
  private TextView al;
  private TextView am;
  private LinearLayout an;
  private LinearLayout ao;
  private ValidDateEdit ap;
  private EditText aq;
  private ClearableEditText ar;
  private EditText as;
  private ClearableEditText at;
  private EditText au;
  private LinearLayout av;
  private LinearLayout aw;
  private ScrollView ax;
  private CheckBox ay;
  private String az;
  int b = 0;
  int c = -1;
  ScrollView d;
  StringBuilder e = new StringBuilder();
  String f;
  String g = "";
  int h;
  private long i;
  private TenpayResizeLinearLayout j;
  private ImageView k;
  private TextView l;
  private TextView m;
  protected boolean mFinished;
  protected Handler mHandler = new Handler();
  protected JSONObject mKuaijiePre;
  protected int mLastPayType = -1;
  protected JSONObject mNewUserPre;
  protected JSONObject mPayGate;
  public Runnable mResendTimer = new TenpayNewCardActivity.1(this);
  private TextView n;
  private TextView o;
  private LinearLayout p;
  private TextView q;
  private TextView r;
  private ImageView s;
  private TextView t;
  private TextView u;
  private ImageButton v;
  private LinearLayout w;
  private LinearLayout x;
  private LinearLayout y;
  private TextView z;

  private void a(int paramInt)
  {
    View localView = getLayoutInflater().inflate(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_toast_custom"), null);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_toast_txt"))).setText(paramInt);
    Toast localToast = new Toast(getApplicationContext());
    localToast.setDuration(0);
    localToast.setGravity(16, 0, 0);
    localToast.setView(localView);
    localToast.show();
  }

  private void a(String paramString)
  {
    if (this.e.length() > 0)
      this.e.append('|');
    this.e.append(this.g);
    this.e.append(paramString);
    this.e.append("-");
    this.e.append(getPayType());
  }

  private void b(String paramString)
  {
    View localView = getLayoutInflater().inflate(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_toast_custom"), null);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_toast_txt"))).setText(paramString);
    Toast localToast = new Toast(getApplicationContext());
    localToast.setDuration(0);
    localToast.setGravity(16, 0, 0);
    localToast.setView(localView);
    localToast.show();
  }

  private void c()
  {
    setContentView(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_big_view"));
    e();
    this.j = ((TenpayResizeLinearLayout)findViewById(TenpayResourceUtil.getId(this, "tenpay_main_linear")));
    if (this.j != null)
      this.j.setOnSizeChangedListener(new TenpayNewCardActivity.2(this));
    this.l = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_title")));
    this.m = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_unit")));
    this.n = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_amount")));
    this.o = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_amount_mark")));
    this.p = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "pay_amount_layout")));
    this.q = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_yuan")));
    this.u = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_fen")));
    this.v = ((ImageButton)findViewById(TenpayResourceUtil.getId(this, "pay_close_btn")));
    if (this.v != null)
      this.v.setOnClickListener(new TenpayNewCardActivity.3(this));
    this.k = ((ImageView)findViewById(TenpayResourceUtil.getId(this, "pay_image")));
    this.r = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_price")));
    this.s = ((ImageView)findViewById(TenpayResourceUtil.getId(this, "pay_vip_image")));
    this.t = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_vip_price")));
    this.d = ((ScrollView)findViewById(TenpayResourceUtil.getId(this, "yz_scroll")));
    if ((this.A != null) && (this.d != null))
      this.A.setOnClickListener(new TenpayNewCardActivity.4(this));
    this.w = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "pay_info")));
    this.x = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "pay_content")));
    if ((this.x != null) && (this.w != null))
    {
      this.x.setClickable(true);
      this.x.setOnClickListener(new TenpayNewCardActivity.5(this));
    }
    d();
    if (this.aT != null)
      initBanks(this.aT);
  }

  private void d()
  {
    if (this.mPayGate != null)
    {
      this.l.setText(this.mPayGate.optString("desc"));
      String str1 = this.mPayGate.optString("total_fee");
      int i1 = str1.length();
      if (i1 < 3)
      {
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append(0);
        if (i1 == 1)
          localStringBuilder1.append(0);
        localStringBuilder1.append(str1);
        str1 = localStringBuilder1.toString();
      }
      this.q.setText(str1.subSequence(0, -2 + str1.length()));
      this.u.setText(str1.substring(-2 + str1.length()));
      String str2 = this.mPayGate.optString("mobile");
      if ((this.z != null) && (str2.length() > 10))
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(str2.substring(0, 3));
        localStringBuilder2.append("******");
        localStringBuilder2.append(str2.substring(-2 + str2.length()));
        String str3 = getResources().getString(TenpayResourceUtil.getStringId(this, "unipay_tenpay_yz_cftphone_text"));
        TextView localTextView = this.z;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localStringBuilder2.toString();
        localTextView.setText(String.format(str3, arrayOfObject));
      }
      if (this.af != null)
      {
        double d1 = this.mPayGate.optDouble("balance", 0.0D) / 100.0D;
        NumberFormat localNumberFormat = NumberFormat.getNumberInstance(Locale.CHINA);
        localNumberFormat.setMaximumFractionDigits(2);
        localNumberFormat.setMinimumFractionDigits(2);
        this.af.setText(localNumberFormat.format(d1));
      }
    }
    if (this.m != null)
      this.m.setText(this.Z);
    if ((this.Y != null) && (this.k != null))
      this.k.setBackgroundDrawable(new BitmapDrawable(new ByteArrayInputStream(this.Y)));
    if (this.n != null)
      this.n.setText(this.aa);
    if (this.o != null)
      this.o.setText(this.ab);
    if ((this.p != null) && ((this.aa == null) || ("".equals(this.aa))))
      this.p.setVisibility(8);
    if (this.r != null)
      this.r.setText(this.ac);
    if (this.s != null)
    {
      if (this.ad != 0)
        this.s.setImageResource(this.ad);
    }
    else if (this.t != null)
    {
      if ((this.ae != null) && (!"".equals(this.ae)))
        break label523;
      this.t.setVisibility(8);
    }
    label523: 
    do
    {
      return;
      this.s.setVisibility(8);
      break;
      this.t.setText(this.ae);
    }
    while ((this.ae == null) || ("".equals(this.ae)) || (this.r == null));
    this.r.getPaint().setFlags(17);
  }

  private void e()
  {
    try
    {
      if (this.mPayGate != null)
        this.aP = 1;
    }
    catch (Exception localException1)
    {
      try
      {
        String str1 = this.mPayGate.optString("interf_cache_ver");
        String str2 = str1.substring(str1.indexOf("CARDBIN"));
        int i4 = str2.indexOf('|');
        if (i4 > 0)
          str2 = str2.substring(0, i4);
        this.aP = Integer.parseInt(str2.substring(1 + str2.indexOf('/')));
        label84: int i1 = getSharedPreferences("unipay_tenpay_prefer", 0).getInt("bin_ver", 2);
        if (this.aP <= i1)
        {
          int i2 = this.aP;
          if (i2 <= 2);
        }
        while (true)
        {
          try
          {
            FileInputStream localFileInputStream = openFileInput("unipay_tenpay_card_bin");
            byte[] arrayOfByte = new byte[1024];
            StringBuilder localStringBuilder = new StringBuilder();
            int i3 = localFileInputStream.read(arrayOfByte);
            if (i3 > 0)
              continue;
            this.ak = new JSONArray(localStringBuilder.toString());
            if (this.ak != null)
              continue;
            this.ak = new b().a;
            this.ah = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "tenpay_card_bin")));
            this.ai = ((TenpayNumberEditText)findViewById(TenpayResourceUtil.getId(this, "bin_card_no")));
            if (getResources().getConfiguration().orientation != 2)
              continue;
            this.ai.setKeyListener(new TenpayNewCardActivity.6(this));
            this.av = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "bin_input_bank_select")));
            this.aw = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "bin_input_bank_sure")));
            this.av.setClickable(true);
            this.av.setOnClickListener(new TenpayNewCardActivity.7(this));
            this.ai.addTextChangedListener(new TenpayNewCardActivity.8(this));
            this.al = ((TextView)findViewById(TenpayResourceUtil.getId(this, "bin_input_bank_name")));
            this.am = ((TextView)findViewById(TenpayResourceUtil.getId(this, "bin_input_sel_bank_name")));
            this.an = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "bin_input_credit")));
            this.ao = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "bin_input_user")));
            this.ap = ((ValidDateEdit)findViewById(TenpayResourceUtil.getId(this, "bin_input_credit_thru")));
            this.ap.setOnFocusChangeListener(new TenpayNewCardActivity.9(this));
            this.ap.addTextChangedListener(new TenpayNewCardActivity.10(this));
            this.aq = ((EditText)findViewById(TenpayResourceUtil.getId(this, "bin_input_bank_cvv")));
            if (this.aq == null)
              continue;
            this.aq.addTextChangedListener(new TenpayNewCardActivity.11(this));
            this.aq.setOnFocusChangeListener(new TenpayNewCardActivity.12(this));
            this.ar = ((ClearableEditText)findViewById(TenpayResourceUtil.getId(this, "bin_input_cre_id")));
            if (this.ar == null)
              continue;
            this.ar.addTextChangedListener(new TenpayNewCardActivity.13(this));
            this.ar.setKeyListener(new TenpayNewCardActivity.14(this));
            this.ar.setOnFocusChangeListener(new TenpayNewCardActivity.15(this));
            this.ar.setOnClickListener(new TenpayNewCardActivity.16(this));
            this.aC = ((TableLayout)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard")));
            this.aA = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_layout")));
            this.aB = ((EditText)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_txt")));
            if (this.aB == null)
              continue;
            this.aB.setClickable(false);
            this.aB.setFocusable(false);
            this.aD = ((Button)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_1")));
            this.aE = ((Button)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_2")));
            this.aF = ((Button)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_3")));
            this.aG = ((Button)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_4")));
            this.aH = ((Button)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_5")));
            this.aI = ((Button)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_6")));
            this.aJ = ((Button)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_7")));
            this.aK = ((Button)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_8")));
            this.aL = ((Button)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_9")));
            this.aM = ((Button)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_x")));
            this.aN = ((Button)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_0")));
            this.aO = ((ImageButton)findViewById(TenpayResourceUtil.getId(this, "bin_keyboard_d")));
            TenpayNewCardActivity.17 local17 = new TenpayNewCardActivity.17(this);
            this.aD.setOnClickListener(local17);
            this.aE.setOnClickListener(local17);
            this.aF.setOnClickListener(local17);
            this.aG.setOnClickListener(local17);
            this.aH.setOnClickListener(local17);
            this.aI.setOnClickListener(local17);
            this.aJ.setOnClickListener(local17);
            this.aK.setOnClickListener(local17);
            this.aL.setOnClickListener(local17);
            this.aM.setOnClickListener(local17);
            this.aN.setOnClickListener(local17);
            this.aO.setOnClickListener(local17);
            this.as = ((EditText)findViewById(TenpayResourceUtil.getId(this, "bin_input_user_name")));
            this.at = ((ClearableEditText)findViewById(TenpayResourceUtil.getId(this, "bin_input_phone")));
            if (Build.VERSION.SDK_INT >= 11)
              break label1635;
            this.at.setInputType(0);
            this.ar.setInputType(0);
            if (this.aB == null)
              continue;
            this.aB.setInputType(0);
            this.aj = ((Button)findViewById(TenpayResourceUtil.getId(this, "bin_confirm")));
            if (this.aj == null)
              continue;
            this.aj.setOnClickListener(this);
            this.ax = ((ScrollView)findViewById(TenpayResourceUtil.getId(this, "bin_input_scroll")));
            if (this.at == null)
              continue;
            this.at.setOnFocusChangeListener(new TenpayNewCardActivity.18(this));
            this.at.setOnClickListener(new TenpayNewCardActivity.19(this));
            this.at.addTextChangedListener(new TenpayNewCardActivity.20(this));
            this.ay = ((CheckBox)findViewById(TenpayResourceUtil.getId(this, "bin_input_agree_check")));
            if (this.ay == null)
              continue;
            this.ay.setOnCheckedChangeListener(new TenpayNewCardActivity.21(this));
            this.E = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "tenay_bigview_bank_setpwd_layout")));
            this.F = ((ScrollView)findViewById(TenpayResourceUtil.getId(this, "tenpay_setpwd_scroll")));
            this.G = ((EditText)findViewById(TenpayResourceUtil.getId(this, "setpwd_pass")));
            if (this.G == null)
              continue;
            this.G.setOnClickListener(new TenpayNewCardActivity.22(this));
            this.H = ((TenpaySecureEditText)findViewById(TenpayResourceUtil.getId(this, "setpwd_setpass")));
            if (this.H == null)
              continue;
            this.H.setOnClickListener(new TenpayNewCardActivity.23(this));
            this.V = ((TextView)findViewById(TenpayResourceUtil.getId(this, "setpwd_pass_txt")));
            this.W = ((TextView)findViewById(TenpayResourceUtil.getId(this, "setpwd_pass_txt_hint")));
            this.I = ((Button)findViewById(TenpayResourceUtil.getId(this, "setpwd_next")));
            this.I.setOnClickListener(this);
            this.y = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "pay_yz")));
            this.z = ((TextView)findViewById(TenpayResourceUtil.getId(this, "yz_phone")));
            this.A = ((EditText)findViewById(TenpayResourceUtil.getId(this, "yz_code")));
            this.B = ((Button)findViewById(TenpayResourceUtil.getId(this, "yz_resend")));
            this.B.setOnClickListener(this);
            this.C = ((Button)findViewById(TenpayResourceUtil.getId(this, "yz_more")));
            if (this.C == null)
              continue;
            this.C.setOnClickListener(this);
            this.D = ((Button)findViewById(TenpayResourceUtil.getId(this, "yz_confirm")));
            this.D.setOnClickListener(this);
            if (this.b != 0)
              continue;
            this.b = 2;
            if (this.a == 3)
              continue;
            a();
            if ((this.a <= 3) || (this.N == null))
              continue;
            initInputBankInfo(this.N);
            return;
            localStringBuilder.append(new String(arrayOfByte, 0, i3));
            continue;
          }
          catch (Exception localException5)
          {
            localException5.printStackTrace();
            continue;
          }
          localException1 = localException1;
          localException1.printStackTrace();
          continue;
          if (this.aP <= i1)
            continue;
          l();
          continue;
          try
          {
            label1635: Class[] arrayOfClass2 = new Class[1];
            arrayOfClass2[0] = Boolean.TYPE;
            Method localMethod2 = EditText.class.getMethod("setShowSoftInputOnFocus", arrayOfClass2);
            localMethod2.setAccessible(false);
            ClearableEditText localClearableEditText3 = this.ar;
            Object[] arrayOfObject4 = new Object[1];
            arrayOfObject4[0] = Boolean.valueOf(false);
            localMethod2.invoke(localClearableEditText3, arrayOfObject4);
            ClearableEditText localClearableEditText4 = this.at;
            Object[] arrayOfObject5 = new Object[1];
            arrayOfObject5[0] = Boolean.valueOf(false);
            localMethod2.invoke(localClearableEditText4, arrayOfObject5);
            if (this.aB == null)
              continue;
            EditText localEditText2 = this.aB;
            Object[] arrayOfObject6 = new Object[1];
            arrayOfObject6[0] = Boolean.valueOf(false);
            localMethod2.invoke(localEditText2, arrayOfObject6);
          }
          catch (NoSuchMethodException localNoSuchMethodException)
          {
            try
            {
              Class[] arrayOfClass1 = new Class[1];
              arrayOfClass1[0] = Boolean.TYPE;
              Method localMethod1 = EditText.class.getMethod("setSoftInputShownOnFocus", arrayOfClass1);
              localMethod1.setAccessible(false);
              ClearableEditText localClearableEditText1 = this.ar;
              Object[] arrayOfObject1 = new Object[1];
              arrayOfObject1[0] = Boolean.valueOf(false);
              localMethod1.invoke(localClearableEditText1, arrayOfObject1);
              ClearableEditText localClearableEditText2 = this.at;
              Object[] arrayOfObject2 = new Object[1];
              arrayOfObject2[0] = Boolean.valueOf(false);
              localMethod1.invoke(localClearableEditText2, arrayOfObject2);
              if (this.aB == null)
                continue;
              EditText localEditText1 = this.aB;
              Object[] arrayOfObject3 = new Object[1];
              arrayOfObject3[0] = Boolean.valueOf(false);
              localMethod1.invoke(localEditText1, arrayOfObject3);
            }
            catch (Exception localException3)
            {
              this.at.setInputType(0);
              this.ar.setInputType(0);
              localException3.printStackTrace();
            }
          }
          catch (Exception localException2)
          {
            localException2.printStackTrace();
          }
        }
      }
      catch (Exception localException4)
      {
        break label84;
      }
    }
  }

  private void f()
  {
    if (this.U > 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.U);
      localStringBuilder.append(getResources().getString(TenpayResourceUtil.getStringId(this, "unipay_tenpay_resend_time")));
      localStringBuilder.append(getResources().getString(TenpayResourceUtil.getStringId(this, "unipay_tenpay_resend")));
      this.B.setText(localStringBuilder.toString());
      this.B.setTextColor(-6710887);
      this.B.setTextSize(12.0F);
      this.B.setBackgroundResource(TenpayResourceUtil.getDrawableId(this, "unipay_tenpay_inputbtnbg_dis"));
      this.B.setEnabled(false);
      return;
    }
    this.B.setEnabled(true);
    this.B.setText(TenpayResourceUtil.getStringId(this, "unipay_tenpay_resend"));
    this.B.setTextSize(15.0F);
    this.B.setBackgroundResource(TenpayResourceUtil.getDrawableId(this, "unipay_tenpay_inputbtn"));
    this.B.setTextColor(-16777216);
  }

  private void g()
  {
    a locala = c.a(e.a);
    if (locala != null)
      locala.a(this.X);
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      JSONObject localJSONObject2 = this.N;
      localJSONObject1.put("token_id", this.O);
      localJSONObject1.put("bank_type", localJSONObject2.optString("code"));
      localJSONObject1.put("bank_card_id", this.ai.getData());
      localJSONObject1.put("true_name", this.as.getText().toString());
      if ((this.mPayGate.optInt("user_type") == 0) || (localJSONObject2.optInt("needcertid") == 1))
        localJSONObject1.put("creditcard_id", this.ar.getText().toString());
      localJSONObject1.put("mobile", this.at.getText().toString());
      if (!"FASTPAY_DEBIT_UNBIND".equals(localJSONObject2.optString("type")))
      {
        if (localJSONObject2.optInt("needthru") == 1)
          localJSONObject1.put("valid_thru", this.ap.getData());
        if (localJSONObject2.optInt("needcvv") == 1)
          localJSONObject1.put("cvc", this.aq.getText().toString());
      }
      localJSONObject1.put("purchaser_id", this.mPayGate.optString("purchaser_id"));
      if (this.a > 3)
      {
        if (this.H.getVisibility() != 0)
          break label324;
        localJSONObject1.put("pass", this.H.getText().toString());
      }
      while (true)
      {
        label293: locala.a(this, 2, localJSONObject1, this.i, this.Q, this.R);
        showProgressDialog(TenpayResourceUtil.getStringId(this, "unipay_tenpay_init_progress"));
        return;
        label324: localJSONObject1.put("pass", this.G.getText().toString());
      }
    }
    catch (JSONException localJSONException)
    {
      break label293;
    }
  }

  private void h()
  {
    a locala = c.a(e.a);
    if (locala != null)
      locala.a(this.X);
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      JSONObject localJSONObject2 = this.N;
      if (this.a == 6)
      {
        localJSONObject1.put("bind_flag", "0");
        String str = localJSONObject2.optString("type");
        if ((!"DEBIT".equals(str)) && (!"CREDIT".equals(str)))
          break label436;
        localJSONObject1.put("kuaijie_type", "YDT");
        localJSONObject1.put("bank_data", this.mKuaijiePre.optString("bank_data"));
        localJSONObject1.put("billno", this.mKuaijiePre.optString("billno"));
        localJSONObject1.put("fee1", this.mKuaijiePre.optString("fee1"));
        localJSONObject1.put("fee2", this.mKuaijiePre.optString("fee2"));
        localJSONObject1.put("is_wap", "0");
        localJSONObject1.put("pay_type", this.mKuaijiePre.optString("pay_type"));
        localJSONObject1.put("purchaser_id", this.mPayGate.optString("purchaser_id"));
        localJSONObject1.put("request_text", this.mKuaijiePre.optString("request_text"));
        localJSONObject1.put("sign", this.mKuaijiePre.optString("sign"));
        localJSONObject1.put("sp_id", this.mPayGate.optString("bargainor_id"));
        localJSONObject1.put("total_fee", this.mPayGate.optString("total_fee"));
        label287: if (this.a > 3)
        {
          if (this.H.getVisibility() != 0)
            break label540;
          localJSONObject1.put("pass", this.H.getText().toString());
        }
      }
      while (true)
      {
        localJSONObject1.put("transaction_id", this.mKuaijiePre.optString("transaction_id"));
        localJSONObject1.put("bank_type", localJSONObject2.optString("code"));
        localJSONObject1.put("token_id", this.O);
        localJSONObject1.put("verify_code", this.A.getText().toString());
        label391: locala.a(this, 3, localJSONObject1, this.i, this.Q, this.R);
        showProgressDialog(TenpayResourceUtil.getStringId(this, "unipay_tenpay_init_progress"));
        return;
        localJSONObject1.put("bind_flag", "1");
        break;
        label436: localJSONObject1.put("kuaijie_type", "KJ");
        localJSONObject1.put("mobile", this.mKuaijiePre.optString("mobile"));
        localJSONObject1.put("business_type", this.mKuaijiePre.optString("business_type"));
        localJSONObject1.put("auth_params", this.mKuaijiePre.optString("auth_params"));
        localJSONObject1.put("token", this.mKuaijiePre.optString("token"));
        localJSONObject1.put("purchaser_id", this.mPayGate.optString("purchaser_id"));
        break label287;
        label540: localJSONObject1.put("pass", this.G.getText().toString());
      }
    }
    catch (JSONException localJSONException)
    {
      break label391;
    }
  }

  private void i()
  {
    a locala = c.a(e.a);
    if (locala != null)
      locala.a(this.X);
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      JSONObject localJSONObject2 = this.N;
      localJSONObject1.put("token_id", this.O);
      localJSONObject1.put("bank_type", localJSONObject2.optString("code"));
      localJSONObject1.put("mobile", this.at.getText().toString());
      localJSONObject1.put("auth_params", this.mNewUserPre.optString("auth_params"));
      localJSONObject1.put("verify_code", this.A.getText().toString());
      label119: locala.a(this, 9, localJSONObject1, this.i, this.Q, this.R);
      showProgressDialog(TenpayResourceUtil.getStringId(this, "unipay_tenpay_init_progress"));
      return;
    }
    catch (JSONException localJSONException)
    {
      break label119;
    }
  }

  public static boolean invalidateID(String paramString)
  {
    int[] arrayOfInt = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
    char[] arrayOfChar = { 49, 48, 88, 57, 56, 55, 54, 53, 52, 51, 50 };
    int i1 = 0;
    int i2 = 0;
    try
    {
      while (true)
      {
        if (i1 >= -1 + paramString.length())
        {
          int i3 = i2 % 11;
          if (paramString.charAt(17) == arrayOfChar[i3])
            break;
          return false;
        }
        int i4 = Integer.parseInt(paramString.substring(i1, i1 + 1));
        int i5 = arrayOfInt[i1];
        i2 += i4 * i5;
        i1++;
      }
      return true;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  private void j()
  {
    a locala = c.a(e.a);
    if (locala != null)
      locala.a(this.X);
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      JSONObject localJSONObject2 = this.N;
      localJSONObject1.put("token_id", this.O);
      localJSONObject1.put("bank_type", localJSONObject2.optString("code"));
      localJSONObject1.put("bank_card_id", this.ai.getData());
      localJSONObject1.put("true_name", this.as.getText().toString());
      localJSONObject1.put("creditcard_id", this.ar.getText().toString());
      localJSONObject1.put("mobile", this.at.getText().toString());
      if (!"FASTPAY_DEBIT_UNBIND".equals(localJSONObject2.optString("type")))
      {
        localJSONObject1.put("valid_thru", this.ap.getData());
        if (localJSONObject2.optInt("needcvv") == 1)
          localJSONObject1.put("cvc", this.aq.getText().toString());
      }
      label200: locala.a(this, 4, localJSONObject1, this.i, this.Q, this.R);
      showProgressDialog(TenpayResourceUtil.getStringId(this, "unipay_tenpay_init_progress"));
      return;
    }
    catch (JSONException localJSONException)
    {
      break label200;
    }
  }

  private void k()
  {
    a locala = c.a(e.a);
    if (locala != null)
      locala.a(this.X);
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      JSONObject localJSONObject2 = this.N;
      String str = localJSONObject2.optString("type");
      localJSONObject1.put("token_id", this.O);
      localJSONObject1.put("wap_token", this.mNewUserPre.optString("wap_token"));
      localJSONObject1.put("auth_params", this.mNewUserPre.optString("auth_params"));
      localJSONObject1.put("bank_type", localJSONObject2.optString("code"));
      localJSONObject1.put("bank_card_id", this.ai.getData());
      localJSONObject1.put("true_name", this.as.getText().toString());
      localJSONObject1.put("creditcard_id", this.ar.getText().toString());
      localJSONObject1.put("mobile", this.at.getText().toString());
      if (!"FASTPAY_DEBIT_UNBIND".equals(str))
      {
        localJSONObject1.put("valid_thru", this.ap.getData());
        if (localJSONObject2.optInt("needcvv") == 1)
          localJSONObject1.put("cvc", this.aq.getText().toString());
      }
      localJSONObject1.put("purchaser_id", this.mPayGate.optString("purchaser_id"));
      if (this.H.getVisibility() == 0)
        localJSONObject1.put("pass", this.H.getText().toString());
      while (true)
      {
        localJSONObject1.put("verify_code", this.A.getText().toString());
        label308: locala.a(this, 5, localJSONObject1, this.i, this.Q, this.R);
        showProgressDialog(TenpayResourceUtil.getStringId(this, "unipay_tenpay_init_progress"));
        return;
        localJSONObject1.put("pass", this.G.getText().toString());
      }
    }
    catch (JSONException localJSONException)
    {
      break label308;
    }
  }

  private void l()
  {
    a locala = c.a(e.a);
    if (locala != null)
      locala.a(this.X);
    locala.a(this, 11, new JSONObject(), this.i, this.Q, this.R);
  }

  void a()
  {
    this.U = 0;
    if (this.y != null)
      this.y.setVisibility(8);
    if (this.E != null)
      this.E.setVisibility(8);
    if (this.ah != null)
      this.ah.setVisibility(8);
    switch (this.b)
    {
    case 3:
    default:
    case 2:
      while (true)
      {
        if (this.F != null)
          this.mHandler.postDelayed(new TenpayNewCardActivity.24(this), 300L);
        if ((this.z != null) && (this.d != null))
          this.mHandler.postDelayed(new TenpayNewCardActivity.25(this), 300L);
        a("show");
        return;
        this.g = "tenpay.info.";
        this.ai.requestFocus();
        this.ah.setVisibility(0);
      }
    case 4:
      this.g = "tenpay.pwd.";
      if (this.H.getVisibility() == 0)
        this.H.requestFocus();
      while (true)
      {
        this.E.setVisibility(0);
        break;
        this.G.requestFocus();
      }
    case 5:
    }
    if (this.a == 0)
      this.g = "tenpay.yuemsg.";
    while (true)
    {
      this.U = 60;
      f();
      this.A.requestFocus();
      this.y.setVisibility(0);
      this.mHandler.postDelayed(this.mResendTimer, 1000L);
      break;
      if (this.a == 1)
      {
        this.g = "tenpay.kjmsg.";
        continue;
      }
      if (this.a == 2)
      {
        this.g = "tenpay.yuebigmsg.";
        continue;
      }
      if (this.a == 3)
      {
        this.g = "tenpay.kjbigmsg.";
        continue;
      }
      if (this.a == 4)
      {
        this.g = "tenpay.choosemsg.";
        continue;
      }
      this.g = "tenpay.choosemsg.";
    }
  }

  void b()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("trace", this.e.toString());
    localBundle.putInt("pay_type", getPayType());
    localBundle.putInt("backfrom", this.aQ);
    TenpayUtil.onCallback(this, 2, localBundle);
    Intent localIntent = new Intent();
    localIntent.putExtra("trace", this.e.toString());
    setResult(100, localIntent);
    finish();
  }

  protected ProgressDialog createDialog()
  {
    return new TenpayProgressDialog(this);
  }

  public void dismissProgressDialog()
  {
    if (this.J != null)
    {
      this.J.dismiss();
      this.J = null;
    }
  }

  protected void doSuccess(JSONObject paramJSONObject)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("data", paramJSONObject.toString());
    localIntent.putExtra("trace", this.e.toString());
    localIntent.putExtra("pay_type", getPayType());
    setResult(-1, localIntent);
    finish();
  }

  public int getPayType()
  {
    if (this.mLastPayType != -1);
    for (int i1 = this.mLastPayType; this.mPayGate == null; i1 = this.a)
      return 0;
    if ((i1 == 0) || (i1 == 2))
    {
      if (this.mPayGate.optInt("cert_user") > 0)
        return 0;
      return 1;
    }
    if ((i1 == 1) || (i1 == 3))
      return 5;
    if (i1 == 4)
    {
      if (this.mPayGate.optInt("user_type") == 3)
        return 7;
      if (this.mPayGate.optInt("user_type") == 0)
        return 4;
      if (this.N != null)
      {
        JSONObject localJSONObject2 = this.N;
        if (this.N == null)
          return 2;
        if (localJSONObject2.optInt("pwd_verify") == 1)
          return 3;
        return 2;
      }
      return 2;
    }
    if (i1 == 5)
    {
      JSONObject localJSONObject1 = this.N;
      if (this.N == null)
        return 2;
      if (localJSONObject1.optInt("pwd_verify") == 1)
        return 3;
      return 2;
    }
    if (i1 == 6)
      return 10;
    return -1;
  }

  protected void initBanks(JSONObject paramJSONObject)
  {
    this.aT = paramJSONObject;
    JSONArray localJSONArray;
    int i2;
    if ((this.L == null) && (this.L == null) && (this.K == null))
    {
      localJSONArray = paramJSONObject.optJSONArray("banklist");
      int i1 = localJSONArray.length();
      i2 = 0;
      if (i2 < i1);
    }
    else
    {
      return;
    }
    JSONObject localJSONObject = localJSONArray.optJSONObject(i2);
    String str = localJSONObject.optString("type");
    if ((localJSONObject.optInt("disabled") == 1) || (("-1".equals(this.P)) && (localJSONObject.optInt("quickmode") != 1)));
    while (true)
    {
      i2++;
      break;
      if (("FASTPAY_DEBIT".equals(str)) || ("FASTPAY_CREDIT".equals(str)) || ("DEBIT".equals(str)) || ("CREDIT".equals(str)))
      {
        if (this.K == null)
          this.K = new ArrayList();
        this.K.add(localJSONObject);
        continue;
      }
      if ("FASTPAY_DEBIT_UNBIND".equals(localJSONObject.optString("type")))
      {
        if (this.L == null)
          this.L = new ArrayList();
        this.L.add(localJSONObject);
        continue;
      }
      if (this.M == null)
        this.M = new ArrayList();
      this.M.add(localJSONObject);
    }
  }

  protected void initInputBankInfo(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
    {
      this.ao.setVisibility(8);
      this.an.setVisibility(8);
      return;
    }
    String str = paramJSONObject.optString("name");
    this.am.setText(str);
    this.ar.setText(this.mPayGate.optString("creditcard_id"));
    this.as.setText(this.mPayGate.optString("purchaser_true_name"));
    this.as.setTextSize(15.0F);
    if ((this.mPayGate.optInt("user_type") == 2) || ((this.mPayGate.optInt("user_type") == 1) && (paramJSONObject.optInt("real_name") == 0)))
    {
      this.ar.setFocusable(false);
      this.ar.setEnabled(false);
      this.as.setFocusable(false);
      this.as.setEnabled(false);
      if ((this.mPayGate.optInt("user_type") <= 0) || (paramJSONObject.optInt("needcertid") != 0))
        break label357;
      this.ar.setVisibility(8);
      label185: this.ai.setNextFocusDownId(TenpayResourceUtil.getId(this, "bin_input_bank_month"));
      this.ai.setNextFocusRightId(TenpayResourceUtil.getId(this, "bin_input_bank_month"));
      if ((!"FASTPAY_DEBIT_UNBIND".equals(paramJSONObject.optString("type"))) && (!"FASTPAY_DEBIT".equals(paramJSONObject.optString("type"))))
        break label440;
      if (this.mPayGate.optInt("user_type") != 2)
        break label368;
      this.ai.setNextFocusDownId(TenpayResourceUtil.getId(this, "bin_input_phone"));
      this.ai.setNextFocusRightId(TenpayResourceUtil.getId(this, "bin_input_phone"));
    }
    while (true)
    {
      if (this.ao.getVisibility() != 0)
        a("name");
      this.ao.setVisibility(0);
      this.an.setVisibility(8);
      return;
      this.ar.setFocusable(true);
      this.ar.setEnabled(true);
      this.as.setFocusable(true);
      this.as.setEnabled(true);
      break;
      label357: this.ar.setVisibility(0);
      break label185;
      label368: if (this.as.getVisibility() == 0)
      {
        this.ai.setNextFocusDownId(TenpayResourceUtil.getId(this, "bin_input_user_name"));
        this.ai.setNextFocusRightId(TenpayResourceUtil.getId(this, "bin_input_user_name"));
        continue;
      }
      this.ai.setNextFocusDownId(TenpayResourceUtil.getId(this, "bin_input_cre_id"));
      this.ai.setNextFocusRightId(TenpayResourceUtil.getId(this, "bin_input_cre_id"));
    }
    label440: int i1 = paramJSONObject.optInt("needcvv");
    if (paramJSONObject.optInt("needthru") == 0)
    {
      if (i1 == 0)
      {
        if (this.mPayGate.optInt("user_type") == 2)
        {
          this.ai.setNextFocusDownId(TenpayResourceUtil.getId(this, "bin_input_phone"));
          this.ai.setNextFocusRightId(TenpayResourceUtil.getId(this, "bin_input_phone"));
        }
        while (true)
        {
          if (this.ao.getVisibility() != 0)
            a("name");
          this.ao.setVisibility(0);
          this.an.setVisibility(8);
          return;
          if (this.as.getVisibility() == 0)
          {
            this.ai.setNextFocusDownId(TenpayResourceUtil.getId(this, "bin_input_user_name"));
            this.ai.setNextFocusRightId(TenpayResourceUtil.getId(this, "bin_input_user_name"));
            continue;
          }
          this.ai.setNextFocusDownId(TenpayResourceUtil.getId(this, "bin_input_cre_id"));
          this.ai.setNextFocusRightId(TenpayResourceUtil.getId(this, "bin_input_cre_id"));
        }
      }
      this.ao.setVisibility(8);
      a("cvv");
      this.aq.setVisibility(0);
      this.ap.setVisibility(8);
      this.an.setVisibility(0);
      this.ai.setNextFocusDownId(TenpayResourceUtil.getId(this, "bin_input_bank_cvv"));
      this.ai.setNextFocusRightId(TenpayResourceUtil.getId(this, "bin_input_bank_cvv"));
      return;
    }
    if (i1 == 0)
    {
      this.ao.setVisibility(8);
      a("cvv");
      this.an.setVisibility(0);
      this.ap.setVisibility(0);
      this.aq.setVisibility(8);
      return;
    }
    this.ao.setVisibility(8);
    a("cvv");
    this.an.setVisibility(0);
    this.ap.setVisibility(0);
    this.aq.setVisibility(0);
  }

  protected void initYZ()
  {
    if ((this.a == 3) && (this.K != null))
      this.K.size();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    String str2;
    if (paramIntent != null)
    {
      String str1 = paramIntent.getStringExtra("trace");
      if (this.e != null)
      {
        if (this.e.length() > 0)
          this.e.append('|');
        this.e.append(str1);
      }
      str2 = paramIntent.getStringExtra("bank");
      if (str2 == null);
    }
    try
    {
      this.N = new JSONObject(str2);
      if (this.mLastPayType != -1)
      {
        this.a = this.mLastPayType;
        this.mLastPayType = -1;
      }
      initInputBankInfo(this.N);
      if (this.ao.getVisibility() != 0)
        a("name");
      this.ao.setVisibility(0);
      if (this.an.getVisibility() == 0)
        if (this.ap.getVisibility() == 0)
          this.ap.requestFocus();
      while (true)
      {
        label166: a("show");
        return;
        this.aq.requestFocus();
        continue;
        if ((this.as.getVisibility() == 0) && (this.as.isEnabled()))
        {
          this.as.requestFocus();
          continue;
        }
        if ((this.ar.getVisibility() == 0) && (this.ar.isEnabled()))
        {
          this.ar.requestFocus();
          continue;
        }
        this.at.requestFocus();
      }
    }
    catch (Exception localException)
    {
      break label166;
    }
  }

  public void onClick(View paramView)
  {
    int i1 = paramView.getId();
    if (i1 == TenpayResourceUtil.getId(this, "bin_confirm"))
    {
      if (this.b != 5)
        a("sure");
      if (!this.ay.isChecked())
        a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_pay_agreement_error"));
    }
    do
    {
      return;
      if (this.ai.getData().length() < 13)
      {
        a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_bank_card_not_null"));
        this.ai.requestFocus();
        return;
      }
      if (this.an.getVisibility() == 0)
      {
        if (this.ap.getVisibility() == 0)
        {
          if ("".equals(this.ap.getText().toString()))
          {
            a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_valid_time_not_null"));
            this.ap.onError();
            this.ap.requestFocus();
            return;
          }
          int i2 = this.ap.isValid(System.currentTimeMillis() + 1000L * this.i);
          if (i2 == 3)
          {
            a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_valid_time_out"));
            this.ap.onError();
            this.ap.requestFocus();
            return;
          }
          if (i2 != 0)
          {
            a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_valid_time_not_null"));
            this.ap.onError();
            this.ap.requestFocus();
            return;
          }
        }
        if ((this.aq.getVisibility() == 0) && (this.aq.getText().toString().length() < 3))
        {
          a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_cvv_not_null"));
          this.aq.requestFocus();
          return;
        }
      }
      if (this.as.getText().toString().length() < 1)
      {
        a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_user_name_not_null"));
        this.as.requestFocus();
        return;
      }
      if ((this.mPayGate.optInt("user_type") != 2) && (this.ar.getVisibility() == 0) && (this.ar.isEnabled()))
      {
        String str2 = this.ar.getText().toString();
        if ((!this.mPayGate.optString("creditcard_id").equals(str2)) || ("".equals(str2)))
        {
          if ((str2.length() < 18) && (str2.length() != 15))
          {
            a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_idcard_not_null"));
            this.ar.requestFocus();
            return;
          }
          if ((str2.length() == 18) && (!invalidateID(str2.toUpperCase())))
          {
            a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_idcard_not_null"));
            this.ar.requestFocus();
            return;
          }
        }
      }
      if (this.at.getText().toString().length() < 11)
      {
        a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_phone_not_null"));
        this.at.requestFocus();
        return;
      }
      if (this.mPayGate.optInt("user_type") == 3)
      {
        this.S = paramView;
        g();
        return;
      }
      if (this.mPayGate.optInt("user_type") == 0)
      {
        this.S = paramView;
        j();
        this.D.setText(TenpayResourceUtil.getStringId(this, "unipay_tenpay_next"));
        return;
      }
      this.S = paramView;
      g();
      return;
      if (i1 == TenpayResourceUtil.getId(this, "yz_confirm"))
      {
        a("sure");
        if (this.A.getText().toString().length() < 6)
        {
          a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_alert_yz_code_wrong"));
          return;
        }
        if (this.mPayGate.optInt("user_type") == 0)
        {
          if (this.mNewUserPre.optInt("check_vcode_flag") == 0)
          {
            this.b = 4;
            this.G.setVisibility(8);
            this.H.setVisibility(0);
            this.H.setHint(TenpayResourceUtil.getStringId(this, "unipay_tenpay_set_pass_hint"));
            this.V.setText(TenpayResourceUtil.getStringId(this, "unipay_tenpay_set_pass"));
            this.W.setText(TenpayResourceUtil.getStringId(this, "unipay_tenpay_set_pass_next_hint"));
            a();
            return;
          }
          i();
          return;
        }
        if (this.mKuaijiePre.optInt("pwd_verify") == 1)
        {
          this.b = 4;
          this.G.setVisibility(0);
          this.H.setVisibility(8);
          this.G.setHint(TenpayResourceUtil.getStringId(this, "unipay_tenpay_cft_pass"));
          this.V.setText(TenpayResourceUtil.getStringId(this, "unipay_tenpay_yz_cft_pass"));
          this.W.setText(TenpayResourceUtil.getStringId(this, "unipay_tenpay_yz_cft_pass_hint"));
          a();
          return;
        }
        h();
        return;
      }
      if (i1 != TenpayResourceUtil.getId(this, "yz_resend"))
        continue;
      a("refresh");
      this.S.performClick();
      return;
    }
    while (i1 != TenpayResourceUtil.getId(this, "setpwd_next"));
    if (this.b != 5)
    {
      if (this.az != null)
        break label939;
      a("sure");
    }
    while (this.H.getVisibility() == 0)
    {
      str1 = this.H.getText().toString();
      if (str1.length() < 6)
      {
        a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_alert_psd_wrong1"));
        return;
        label939: a("nextstep");
        continue;
      }
      if ("111111|222222|333333|444444|555555|666666|777777|888888|999999|000000".indexOf(str1) < 0)
        break label1005;
      a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_alert_psd_simple"));
      return;
    }
    String str1 = this.G.getText().toString();
    if (str1.length() < 6)
    {
      a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_alert_psd_wrong"));
      return;
    }
    label1005: if (this.mPayGate.optInt("user_type") == 0)
    {
      if (this.az == null)
      {
        this.g = "tenpay.conpwd.";
        a("show");
        this.az = str1;
        this.W.setText(TenpayResourceUtil.getStringId(this, "unipay_tenpay_set_pass_next_hint2"));
        this.H.setText("");
        return;
      }
      if (!this.az.equals(str1))
      {
        this.az = null;
        this.W.setText(TenpayResourceUtil.getStringId(this, "unipay_tenpay_set_pass_next_hint"));
        this.H.setText("");
        a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_set_pass_samefail"));
        a("again");
        this.g = "tenpay.pwd.";
        a("show");
        return;
      }
      k();
      return;
    }
    h();
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  // ERROR //
  public void onCreate(Bundle paramBundle)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: aload_1
    //   4: invokespecial 1428	android/app/Activity:onCreate	(Landroid/os/Bundle;)V
    //   7: aload_0
    //   8: iload_2
    //   9: invokevirtual 1432	com/tenpay/tenpayplugin/TenpayNewCardActivity:requestWindowFeature	(I)Z
    //   12: pop
    //   13: aload_0
    //   14: invokevirtual 1436	com/tenpay/tenpayplugin/TenpayNewCardActivity:getIntent	()Landroid/content/Intent;
    //   17: ldc_w 1437
    //   20: iconst_m1
    //   21: invokevirtual 1440	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   24: istore 4
    //   26: iload 4
    //   28: iconst_m1
    //   29: if_icmpne +15 -> 44
    //   32: aload_0
    //   33: invokevirtual 486	com/tenpay/tenpayplugin/TenpayNewCardActivity:getResources	()Landroid/content/res/Resources;
    //   36: invokevirtual 656	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   39: getfield 661	android/content/res/Configuration:orientation	I
    //   42: istore 4
    //   44: iload 4
    //   46: iconst_2
    //   47: if_icmpne +268 -> 315
    //   50: ldc_w 1442
    //   53: ldc_w 1444
    //   56: invokevirtual 1448	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   59: aconst_null
    //   60: invokevirtual 1453	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
    //   63: istore 10
    //   65: iload 10
    //   67: istore_2
    //   68: aload_0
    //   69: iload_2
    //   70: invokevirtual 1456	com/tenpay/tenpayplugin/TenpayNewCardActivity:setRequestedOrientation	(I)V
    //   73: aload_0
    //   74: new 1458	com/tenpay/tenpayplugin/TenpayNewCardActivity$MyBLCallbackListener
    //   77: dup
    //   78: aload_0
    //   79: invokespecial 1459	com/tenpay/tenpayplugin/TenpayNewCardActivity$MyBLCallbackListener:<init>	(Lcom/tenpay/tenpayplugin/TenpayNewCardActivity;)V
    //   82: putfield 990	com/tenpay/tenpayplugin/TenpayNewCardActivity:X	Lcom/tenpay/tenpayplugin/TenpayNewCardActivity$MyBLCallbackListener;
    //   85: aload_0
    //   86: invokevirtual 1436	com/tenpay/tenpayplugin/TenpayNewCardActivity:getIntent	()Landroid/content/Intent;
    //   89: astore 5
    //   91: aload_0
    //   92: aload 5
    //   94: ldc_w 1092
    //   97: iconst_0
    //   98: invokevirtual 1440	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   101: putfield 150	com/tenpay/tenpayplugin/TenpayNewCardActivity:a	I
    //   104: aload_0
    //   105: getfield 150	com/tenpay/tenpayplugin/TenpayNewCardActivity:a	I
    //   108: bipush 12
    //   110: if_icmpne +9 -> 119
    //   113: aload_0
    //   114: bipush 6
    //   116: putfield 150	com/tenpay/tenpayplugin/TenpayNewCardActivity:a	I
    //   119: aload_0
    //   120: aload_0
    //   121: getfield 150	com/tenpay/tenpayplugin/TenpayNewCardActivity:a	I
    //   124: putfield 446	com/tenpay/tenpayplugin/TenpayNewCardActivity:aQ	I
    //   127: aload_0
    //   128: aload 5
    //   130: ldc_w 998
    //   133: invokevirtual 1307	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   136: putfield 1000	com/tenpay/tenpayplugin/TenpayNewCardActivity:O	Ljava/lang/String;
    //   139: aload_0
    //   140: aload 5
    //   142: ldc_w 1461
    //   145: lconst_0
    //   146: invokevirtual 1465	android/content/Intent:getLongExtra	(Ljava/lang/String;J)J
    //   149: putfield 197	com/tenpay/tenpayplugin/TenpayNewCardActivity:i	J
    //   152: aload_0
    //   153: aload 5
    //   155: ldc_w 1467
    //   158: invokevirtual 1471	android/content/Intent:getByteArrayExtra	(Ljava/lang/String;)[B
    //   161: putfield 537	com/tenpay/tenpayplugin/TenpayNewCardActivity:Y	[B
    //   164: aload_0
    //   165: aload 5
    //   167: ldc_w 1473
    //   170: invokevirtual 1307	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   173: putfield 553	com/tenpay/tenpayplugin/TenpayNewCardActivity:aa	Ljava/lang/String;
    //   176: aload_0
    //   177: aload 5
    //   179: ldc_w 1475
    //   182: invokevirtual 1307	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   185: putfield 535	com/tenpay/tenpayplugin/TenpayNewCardActivity:Z	Ljava/lang/String;
    //   188: aload_0
    //   189: aload 5
    //   191: ldc_w 1477
    //   194: invokevirtual 1307	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   197: putfield 555	com/tenpay/tenpayplugin/TenpayNewCardActivity:ab	Ljava/lang/String;
    //   200: aload_0
    //   201: aload 5
    //   203: ldc_w 1479
    //   206: invokevirtual 1307	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   209: putfield 564	com/tenpay/tenpayplugin/TenpayNewCardActivity:ac	Ljava/lang/String;
    //   212: aload_0
    //   213: aload 5
    //   215: ldc_w 1481
    //   218: iconst_0
    //   219: invokevirtual 1440	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   222: putfield 566	com/tenpay/tenpayplugin/TenpayNewCardActivity:ad	I
    //   225: aload_0
    //   226: aload 5
    //   228: ldc_w 1483
    //   231: invokevirtual 1307	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   234: putfield 571	com/tenpay/tenpayplugin/TenpayNewCardActivity:ae	Ljava/lang/String;
    //   237: aload_0
    //   238: new 452	org/json/JSONObject
    //   241: dup
    //   242: aload 5
    //   244: ldc_w 1485
    //   247: invokevirtual 1307	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   250: invokespecial 1310	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   253: putfield 448	com/tenpay/tenpayplugin/TenpayNewCardActivity:mPayGate	Lorg/json/JSONObject;
    //   256: aload_0
    //   257: aload_0
    //   258: getfield 448	com/tenpay/tenpayplugin/TenpayNewCardActivity:mPayGate	Lorg/json/JSONObject;
    //   261: ldc_w 1487
    //   264: invokevirtual 1027	org/json/JSONObject:optInt	(Ljava/lang/String;)I
    //   267: putfield 1057	com/tenpay/tenpayplugin/TenpayNewCardActivity:R	I
    //   270: aload_0
    //   271: aload_0
    //   272: getfield 448	com/tenpay/tenpayplugin/TenpayNewCardActivity:mPayGate	Lorg/json/JSONObject;
    //   275: ldc_w 1489
    //   278: invokevirtual 456	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   281: putfield 1055	com/tenpay/tenpayplugin/TenpayNewCardActivity:Q	Ljava/lang/String;
    //   284: aload_0
    //   285: new 452	org/json/JSONObject
    //   288: dup
    //   289: aload 5
    //   291: ldc_w 1080
    //   294: invokevirtual 1307	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   297: invokespecial 1310	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   300: putfield 440	com/tenpay/tenpayplugin/TenpayNewCardActivity:aT	Lorg/json/JSONObject;
    //   303: aload_0
    //   304: invokespecial 1491	com/tenpay/tenpayplugin/TenpayNewCardActivity:c	()V
    //   307: return
    //   308: astore 9
    //   310: iconst_0
    //   311: istore_2
    //   312: goto -244 -> 68
    //   315: iload 4
    //   317: iload_2
    //   318: if_icmpne +39 -> 357
    //   321: ldc_w 1442
    //   324: ldc_w 1493
    //   327: invokevirtual 1448	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   330: aconst_null
    //   331: invokevirtual 1453	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
    //   334: istore 8
    //   336: iload 8
    //   338: istore_2
    //   339: goto -271 -> 68
    //   342: astore 6
    //   344: aload 6
    //   346: invokevirtual 916	java/lang/Exception:printStackTrace	()V
    //   349: goto -46 -> 303
    //   352: astore 7
    //   354: goto -286 -> 68
    //   357: iconst_0
    //   358: istore_2
    //   359: goto -291 -> 68
    //
    // Exception table:
    //   from	to	target	type
    //   50	65	308	java/lang/Exception
    //   237	303	342	java/lang/Exception
    //   321	336	352	java/lang/Exception
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.mHandler = null;
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    int i1 = 1;
    switch (paramInt)
    {
    default:
      i1 = super.onKeyDown(paramInt, paramKeyEvent);
    case 4:
    }
    while (true)
    {
      return i1;
      if ((this.aS == null) || (!this.aS.isShowing()))
        break;
      this.aS.dismiss();
      if (this.f == null)
        continue;
      a("keyback");
      this.g = this.f;
      this.f = null;
      return i1;
    }
    if ((this.aC != null) && (this.aC.getVisibility() == 0))
    {
      if (this.aA != null)
        this.aA.setVisibility(8);
      this.aC.setVisibility(8);
      return i1;
    }
    if (this.a > i1)
      if (this.T != 0L)
        break label181;
    label181: long l1;
    for (this.T = System.currentTimeMillis(); (this.aR != null) && (this.aR.isShowing()); this.T = l1)
    {
      this.aR.dismiss();
      this.aR = null;
      return i1;
      l1 = System.currentTimeMillis();
      if (l1 - this.T >= 300L)
        continue;
      b();
      return i1;
    }
    a("keyback");
    switch (this.b)
    {
    case 3:
    default:
      return i1;
    case 2:
      if ((this.a == 4) || ((this.a == 6) && (this.mLastPayType == 4)))
      {
        b();
        return i1;
      }
      Intent localIntent = new Intent();
      localIntent.putExtra("trace", this.e.toString());
      setResult(0, localIntent);
      finish();
      return i1;
    case 4:
      if (this.az != null)
      {
        this.az = null;
        this.W.setText(TenpayResourceUtil.getStringId(this, "unipay_tenpay_set_pass_next_hint"));
        this.H.setText("");
        this.g = "tenpay.pwd.";
        a("show");
        return i1;
      }
      this.b = 5;
      this.H.setText("");
      this.G.setText("");
      a();
      return i1;
    case 5:
    }
    this.b = this.c;
    a();
    return i1;
  }

  protected void onResume()
  {
    super.onResume();
    if (!TenpayUtil.hasCallback())
    {
      Intent localIntent = new Intent();
      localIntent.putExtra("trace", this.e.toString());
      setResult(0, localIntent);
      finish();
    }
  }

  public void onStop()
  {
    super.onStop();
    try
    {
      ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 1);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void showMyDialog(int paramInt1, int paramInt2, View.OnClickListener paramOnClickListener)
  {
    View localView = LayoutInflater.from(this).inflate(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_floattips"), null);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_title"))).setText(paramInt1);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_message"))).setText(paramInt2);
    ((Button)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_ok"))).setOnClickListener(paramOnClickListener);
    this.aS = new Dialog(this, TenpayResourceUtil.getStyleId(this, "unipay_tenpay_dialog"));
    this.aS.requestWindowFeature(1);
    this.aS.setContentView(localView);
    this.aS.getWindow().setLayout(-1, -2);
    this.aS.setOnDismissListener(new TenpayNewCardActivity.29(this));
    this.aS.show();
  }

  public void showMyDialog(String paramString1, String paramString2, View.OnClickListener paramOnClickListener)
  {
    View localView = LayoutInflater.from(this).inflate(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_floattips"), null);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_title"))).setText(paramString1);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_message"))).setText(paramString2);
    ((Button)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_ok"))).setOnClickListener(paramOnClickListener);
    this.aS = new Dialog(this, TenpayResourceUtil.getStyleId(this, "unipay_tenpay_dialog"));
    this.aS.requestWindowFeature(1);
    this.aS.setContentView(localView);
    this.aS.getWindow().setLayout(-1, -2);
    this.aS.setOnDismissListener(new TenpayNewCardActivity.26(this));
    this.aS.show();
  }

  public void showMyDialogWithCancel(String paramString1, String paramString2, View.OnClickListener paramOnClickListener1, View.OnClickListener paramOnClickListener2)
  {
    View localView = LayoutInflater.from(this).inflate(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_floattips"), null);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_title"))).setText(paramString1);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_message"))).setText(paramString2);
    ((Button)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_ok"))).setOnClickListener(paramOnClickListener1);
    Button localButton = (Button)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_cancle"));
    localButton.setVisibility(0);
    localButton.setOnClickListener(paramOnClickListener2);
    this.aS = new Dialog(this, TenpayResourceUtil.getStyleId(this, "unipay_tenpay_dialog"));
    this.aS.requestWindowFeature(1);
    this.aS.setContentView(localView);
    this.aS.getWindow().setLayout(-1, -2);
    this.aS.setOnDismissListener(new TenpayNewCardActivity.27(this));
    this.aS.show();
  }

  public void showMyDialogWithCancel(String paramString1, String paramString2, String paramString3, String paramString4, View.OnClickListener paramOnClickListener1, View.OnClickListener paramOnClickListener2)
  {
    View localView = LayoutInflater.from(this).inflate(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_floattips"), null);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_title"))).setText(paramString1);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_message"))).setText(paramString2);
    Button localButton1 = (Button)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_ok"));
    localButton1.setText(paramString3);
    localButton1.setOnClickListener(paramOnClickListener1);
    Button localButton2 = (Button)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_cancle"));
    localButton2.setText(paramString4);
    localButton2.setVisibility(0);
    localButton2.setOnClickListener(paramOnClickListener2);
    this.aS = new Dialog(this, TenpayResourceUtil.getStyleId(this, "unipay_tenpay_dialog"));
    this.aS.requestWindowFeature(1);
    this.aS.setContentView(localView);
    this.aS.getWindow().setLayout(-1, -2);
    this.aS.setOnDismissListener(new TenpayNewCardActivity.28(this));
    this.aS.show();
  }

  public void showProgressDialog(int paramInt)
  {
    if ((this.J == null) || (!this.J.isShowing()))
    {
      this.J = createDialog();
      if (this.J != null)
      {
        this.J.setMessage(getString(paramInt));
        this.J.setIndeterminate(true);
        this.J.setCancelable(false);
        this.J.show();
      }
    }
  }

  public void showSelectBankDialog()
  {
    Intent localIntent;
    if ((this.aT != null) && (!isFinishing()))
    {
      a("choose");
      localIntent = new Intent(this, TenpayPluginSelectBankActivity.class);
      localIntent.putExtra("key_type", this.P);
      localIntent.putExtra("pay_type", getPayType());
      localIntent.putExtra("bank_data", this.aT.toString());
      if (this.N != null)
      {
        localIntent.putExtra("bank_name", this.N.optString("sname"));
        if (!"FASTPAY_DEBIT_UNBIND".equals(this.N.optString("type")))
          break label136;
      }
    }
    label136: for (int i1 = 0; ; i1 = 1)
    {
      localIntent.putExtra("index", i1);
      startActivityForResult(localIntent, 0);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity
 * JD-Core Version:    0.6.0
 */