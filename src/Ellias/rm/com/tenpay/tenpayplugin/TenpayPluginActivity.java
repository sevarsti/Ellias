package com.tenpay.tenpayplugin;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.text.Editable;
import android.text.TextPaint;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.tenpay.a.a.a;
import com.tenpay.a.a.c;
import com.tenpay.a.a.e;
import com.tenpay.tenpayplugin.view.BankSelectDialog;
import com.tenpay.tenpayplugin.view.TenpayProgressDialog;
import com.tenpay.tenpayplugin.view.TenpayResizeLinearLayout;
import com.tenpay.tenpayplugin.view.ValidDateEdit;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class TenpayPluginActivity extends Activity
  implements View.OnClickListener
{
  public static final int STATE_BALACE = 0;
  public static final int STATE_KUAIJIE = 1;
  public static final int STATE_NEW_SETPSW = 4;
  public static final int STATE_YZ = 5;
  private Button A;
  private ScrollView B;
  private LinearLayout C;
  private LinearLayout D;
  private TextView E;
  private TextView F;
  private ImageButton G;
  private ScrollView H;
  private EditText I;
  private Button J;
  private Button K;
  private Button L;
  private View M;
  private LinearLayout N;
  private View O;
  private TextView P;
  private EditText Q;
  private Button R;
  private ImageButton S;
  private Button T;
  private Button U;
  private ProgressDialog V;
  private ArrayList W;
  private ArrayList X;
  private ArrayList Y;
  private JSONObject Z;
  int a = 0;
  private Button aA;
  private LinearLayout aB;
  private EditText aC;
  private EditText aD;
  private TextView aE;
  private Button aF;
  private boolean aG;
  private boolean aH;
  private boolean aI;
  private boolean aJ;
  private BankSelectDialog aK;
  private Dialog aL;
  private Dialog aM;
  private boolean aN;
  private boolean aO;
  private JSONObject aP;
  private String aa;
  private String ab;
  private String ac;
  private String ad;
  private String ae;
  private int af;
  private int ag;
  private View ah;
  private long ai;
  private int aj;
  private TenpayPluginActivity.MyBLCallbackListener ak;
  private byte[] al;
  private String am;
  private String an;
  private String ao;
  private String ap;
  private int aq;
  private String ar;
  private TextView as;
  private TextView at;
  private String au;
  private boolean av;
  private int aw;
  private LinearLayout ax;
  private TextView ay;
  private ValidDateEdit az;
  int b = 0;
  int c = -1;
  ScrollView d;
  StringBuilder e = new StringBuilder();
  String f;
  String g = "";
  private long h;
  private TenpayResizeLinearLayout i;
  private ImageView j;
  private TextView k;
  private TextView l;
  private TextView m;
  protected boolean mFinished;
  protected Handler mHandler = new Handler();
  public boolean mInitCalled;
  protected JSONObject mKuaijiePre;
  protected int mLastPayType = -1;
  protected JSONObject mNewUserPre;
  protected JSONObject mPayGate;
  public Runnable mResendTimer = new TenpayPluginActivity.1(this);
  private TextView n;
  private LinearLayout o;
  private TextView p;
  private TextView q;
  private ImageView r;
  private TextView s;
  private TextView t;
  private ImageButton u;
  private LinearLayout v;
  private LinearLayout w;
  private LinearLayout x;
  private EditText y;
  private Button z;

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

  private void a(boolean paramBoolean)
  {
    a locala = c.a(e.a);
    if (locala != null)
      locala.a(this.ak);
    JSONObject localJSONObject1 = new JSONObject();
    if (paramBoolean);
    try
    {
      JSONObject localJSONObject2 = this.Z;
      if (this.a == 6)
      {
        localJSONObject1.put("bind_flag", "0");
        label57: String str = localJSONObject2.optString("type");
        if ((!"DEBIT".equals(str)) && (!"CREDIT".equals(str)))
          break label441;
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
      }
      while (true)
      {
        localJSONObject1.put("pass", this.I.getText().toString());
        localJSONObject1.put("transaction_id", this.mKuaijiePre.optString("transaction_id"));
        localJSONObject1.put("bank_type", localJSONObject2.optString("code"));
        localJSONObject1.put("token_id", this.aa);
        localJSONObject1.put("verify_code", this.Q.getText().toString());
        label377: locala.a(this, 3, localJSONObject1, this.h, this.ae, this.af);
        showProgressDialog(TenpayResourceUtil.getStringId(this, "unipay_tenpay_init_progress"));
        return;
        localJSONObject2 = (JSONObject)this.W.get(this.ag);
        break;
        localJSONObject1.put("bind_flag", "1");
        break label57;
        label441: localJSONObject1.put("kuaijie_type", "KJ");
        localJSONObject1.put("mobile", this.mKuaijiePre.optString("mobile"));
        localJSONObject1.put("business_type", this.mKuaijiePre.optString("business_type"));
        localJSONObject1.put("auth_params", this.mKuaijiePre.optString("auth_params"));
        localJSONObject1.put("token", this.mKuaijiePre.optString("token"));
        localJSONObject1.put("purchaser_id", this.mPayGate.optString("purchaser_id"));
      }
    }
    catch (JSONException localJSONException)
    {
      break label377;
    }
  }

  private void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    a locala = c.a(e.a);
    if (locala != null)
      locala.a(this.ak);
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      JSONObject localJSONObject2 = (JSONObject)this.W.get(this.ag);
      String str = localJSONObject2.optString("type");
      if (("DEBIT".equals(str)) || ("CREDIT".equals(str)))
      {
        localJSONObject1.put("kuaijie_type", "YDT");
        localJSONObject1.put("token_id", this.aa);
        localJSONObject1.put("bank_type", localJSONObject2.optString("code"));
        localJSONObject1.put("purchaser_id", this.mPayGate.optString("purchaser_id"));
        if (this.az != null)
          localJSONObject1.put("newcthru", this.az.getData().trim());
        if (this.aD != null)
          localJSONObject1.put("newcvv", this.aD.getText().toString().trim());
        if (this.aC != null)
          localJSONObject1.put("newmobile", this.aC.getText().toString().trim());
        if ((this.mPayGate.optInt("nopwdnosms_flag") != 1) || (this.I.getVisibility() != 8))
          break label327;
        localJSONObject1.put("nopwdnosms_flag", "1");
        label263: if (!paramBoolean1)
          break label377;
        localJSONObject1.put("verify_flag", "1");
      }
      while (true)
      {
        label279: locala.a(this, 6, localJSONObject1, this.h, this.ae, this.af);
        showProgressDialog(TenpayResourceUtil.getStringId(this, "unipay_tenpay_init_progress"));
        return;
        localJSONObject1.put("kuaijie_type", "KJ");
        break;
        label327: localJSONObject1.put("nopwdnosms_flag", "0");
        if ((!paramBoolean2) || (this.I.getVisibility() != 0))
          break label263;
        localJSONObject1.put("pass", this.I.getText().toString());
        break label263;
        label377: localJSONObject1.put("verify_flag", "2");
      }
    }
    catch (JSONException localJSONException)
    {
      break label279;
    }
  }

  private void b(String paramString)
  {
    a locala = c.a(e.a);
    if (locala != null)
      locala.a(this.ak);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("token_id", this.aa);
      localJSONObject.put("purchaser_id", this.mPayGate.optString("purchaser_id"));
      localJSONObject.put("time_stamp", this.mPayGate.optString("time_stamp"));
      localJSONObject.put("pass", this.y.getText().toString());
      if ((paramString != null) && (!"".equals(paramString)))
        localJSONObject.put("verify_code", paramString);
      label117: locala.a(this, 1, localJSONObject, this.h, this.ae, this.af);
      showProgressDialog(TenpayResourceUtil.getStringId(this, "unipay_tenpay_init_progress"));
      return;
    }
    catch (JSONException localJSONException)
    {
      break label117;
    }
  }

  private void c()
  {
    if (this.aO)
      if (this.aM != null)
      {
        this.aM.dismiss();
        this.aM = null;
      }
    switch (this.a)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 5:
      while (true)
      {
        this.i = ((TenpayResizeLinearLayout)findViewById(TenpayResourceUtil.getId(this, "tenpay_main_linear")));
        if (this.i != null)
          this.i.setOnSizeChangedListener(new TenpayPluginActivity.3(this));
        this.k = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_title")));
        this.l = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_unit")));
        this.m = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_amount")));
        this.n = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_amount_mark")));
        this.o = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "pay_amount_layout")));
        this.p = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_yuan")));
        this.t = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_fen")));
        this.u = ((ImageButton)findViewById(TenpayResourceUtil.getId(this, "pay_close_btn")));
        if (this.u != null)
          this.u.setOnClickListener(new TenpayPluginActivity.4(this));
        this.j = ((ImageView)findViewById(TenpayResourceUtil.getId(this, "pay_image")));
        this.q = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_price")));
        this.r = ((ImageView)findViewById(TenpayResourceUtil.getId(this, "pay_vip_image")));
        this.s = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_vip_price")));
        this.d = ((ScrollView)findViewById(TenpayResourceUtil.getId(this, "yz_scroll")));
        if ((this.Q != null) && (this.d != null))
          this.Q.setOnClickListener(new TenpayPluginActivity.5(this));
        this.v = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "pay_info")));
        this.w = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "pay_content")));
        if ((this.w != null) && (this.v != null))
        {
          this.w.setClickable(true);
          this.w.setOnClickListener(new TenpayPluginActivity.6(this));
        }
        d();
        this.mInitCalled = TenpayUtil.onTenpayInited(this);
        if (this.aP != null)
          initBanks(this.aP);
        return;
        if (this.aN)
          break;
        this.aN = true;
        return;
        setContentView(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_tips_frame"));
        f();
        if (getResources().getConfiguration().orientation != 1)
          continue;
        this.mHandler.postDelayed(new TenpayPluginActivity.2(this), 300L);
        continue;
        setContentView(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_tips_frame"));
        e();
        continue;
        setContentView(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_big_view"));
        f();
        continue;
        setContentView(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_big_view"));
        e();
      }
    case 4:
    }
    gotoBind(false);
  }

  private void c(String paramString)
  {
    View localView = getLayoutInflater().inflate(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_toast_custom"), null);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_toast_txt"))).setText(paramString);
    Toast localToast = new Toast(getApplicationContext());
    localToast.setDuration(0);
    localToast.setGravity(16, 0, 0);
    localToast.setView(localView);
    localToast.show();
  }

  private void d()
  {
    if (this.mPayGate != null)
    {
      this.k.setText(this.mPayGate.optString("desc"));
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
      this.p.setText(str1.subSequence(0, -2 + str1.length()));
      this.t.setText(str1.substring(-2 + str1.length()));
      String str2 = this.mPayGate.optString("mobile");
      if ((this.P != null) && (str2.length() > 10))
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(str2.substring(0, 3));
        localStringBuilder2.append("******");
        localStringBuilder2.append(str2.substring(-2 + str2.length()));
        String str3 = getResources().getString(TenpayResourceUtil.getStringId(this, "unipay_tenpay_yz_cftphone_text"));
        TextView localTextView = this.P;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localStringBuilder2.toString();
        localTextView.setText(String.format(str3, arrayOfObject));
      }
      if (this.as != null)
      {
        double d1 = this.mPayGate.optDouble("balance", 0.0D) / 100.0D;
        NumberFormat localNumberFormat = NumberFormat.getNumberInstance(Locale.CHINA);
        localNumberFormat.setMaximumFractionDigits(2);
        localNumberFormat.setMinimumFractionDigits(2);
        this.as.setText(localNumberFormat.format(d1));
      }
    }
    if (this.l != null)
      this.l.setText(this.am);
    if ((this.al != null) && (this.j != null))
      this.j.setBackgroundDrawable(new BitmapDrawable(new ByteArrayInputStream(this.al)));
    if (this.m != null)
      this.m.setText(this.an);
    if (this.n != null)
      this.n.setText(this.ao);
    if ((this.o != null) && ((this.an == null) || ("".equals(this.an))))
      this.o.setVisibility(8);
    if (this.q != null)
      this.q.setText(this.ap);
    if (this.r != null)
    {
      if (this.aq != 0)
        this.r.setImageResource(this.aq);
    }
    else if (this.s != null)
    {
      if ((this.ar != null) && (!"".equals(this.ar)))
        break label523;
      this.s.setVisibility(8);
    }
    label523: 
    do
    {
      return;
      this.r.setVisibility(8);
      break;
      this.s.setText(this.ar);
    }
    while ((this.ar == null) || ("".equals(this.ar)) || (this.q == null));
    this.q.getPaint().setFlags(17);
  }

  private void e()
  {
    this.C = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "pay_kuai")));
    this.F = ((TextView)findViewById(TenpayResourceUtil.getId(this, "kuai_discount")));
    if (this.F != null)
      this.F.setText(this.au);
    this.D = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "kuai_bank")));
    this.E = ((TextView)findViewById(TenpayResourceUtil.getId(this, "kuai_bank_name")));
    this.G = ((ImageButton)findViewById(TenpayResourceUtil.getId(this, "kuai_bank_btn")));
    this.D.setClickable(true);
    this.D.setOnClickListener(this);
    this.G.setOnClickListener(this);
    this.H = ((ScrollView)findViewById(TenpayResourceUtil.getId(this, "tenpay_kuai_scroll")));
    this.I = ((EditText)findViewById(TenpayResourceUtil.getId(this, "kuai_pass")));
    if ((this.I != null) && (this.H != null))
      this.I.setOnClickListener(new TenpayPluginActivity.7(this));
    this.J = ((Button)findViewById(TenpayResourceUtil.getId(this, "kuai_more")));
    if (this.J != null)
      this.J.setOnClickListener(this);
    this.K = ((Button)findViewById(TenpayResourceUtil.getId(this, "kuai_confirm")));
    this.K.setOnClickListener(this);
    this.L = ((Button)findViewById(TenpayResourceUtil.getId(this, "kuai_forget_pass")));
    this.L.setVisibility(0);
    this.M = findViewById(TenpayResourceUtil.getId(this, "kuai_nopwdnosms"));
    if (this.L != null)
      this.L.setOnClickListener(this);
    this.ax = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "tenpay_youxiaoqi")));
    this.ay = ((TextView)findViewById(TenpayResourceUtil.getId(this, "reset_credit_card")));
    this.az = ((ValidDateEdit)findViewById(TenpayResourceUtil.getId(this, "reset_credit_thru")));
    this.az.setOnFocusChangeListener(new TenpayPluginActivity.8(this));
    this.az.addTextChangedListener(new TenpayPluginActivity.9(this));
    this.aD = ((EditText)findViewById(TenpayResourceUtil.getId(this, "reset_credit_cvv")));
    this.aD.setOnFocusChangeListener(new TenpayPluginActivity.10(this));
    this.aA = ((Button)findViewById(TenpayResourceUtil.getId(this, "reset_thru_confirm")));
    this.aA.setOnClickListener(this);
    this.aB = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "tenpay_bank_phone")));
    this.aE = ((TextView)findViewById(TenpayResourceUtil.getId(this, "reset_phone_card")));
    this.aC = ((EditText)findViewById(TenpayResourceUtil.getId(this, "reset_phone_edit")));
    this.aF = ((Button)findViewById(TenpayResourceUtil.getId(this, "reset_phone_confirm")));
    this.aF.setOnClickListener(this);
    this.N = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "pay_yz")));
    this.O = findViewById(TenpayResourceUtil.getId(this, "yz_add"));
    this.P = ((TextView)findViewById(TenpayResourceUtil.getId(this, "yz_phone")));
    this.Q = ((EditText)findViewById(TenpayResourceUtil.getId(this, "yz_code")));
    this.R = ((Button)findViewById(TenpayResourceUtil.getId(this, "yz_resend")));
    this.R.setOnClickListener(this);
    this.S = ((ImageButton)findViewById(TenpayResourceUtil.getId(this, "yz_reset_phone")));
    this.S.setOnClickListener(this);
    this.T = ((Button)findViewById(TenpayResourceUtil.getId(this, "yz_more")));
    if (this.T != null)
      this.T.setOnClickListener(this);
    this.U = ((Button)findViewById(TenpayResourceUtil.getId(this, "yz_confirm")));
    this.U.setOnClickListener(this);
    if (this.b == 0)
      this.b = 1;
    a();
    initYZ();
  }

  private void f()
  {
    this.x = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "pay_cft")));
    this.at = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_discount")));
    if (this.at != null)
      this.at.setText(this.au);
    this.as = ((TextView)findViewById(TenpayResourceUtil.getId(this, "pay_account")));
    this.y = ((EditText)findViewById(TenpayResourceUtil.getId(this, "pay_pass")));
    this.B = ((ScrollView)findViewById(TenpayResourceUtil.getId(this, "pay_scroll")));
    if ((this.y != null) && (this.B != null))
      this.y.setOnClickListener(new TenpayPluginActivity.11(this));
    this.z = ((Button)findViewById(TenpayResourceUtil.getId(this, "pay_more")));
    if (this.z != null)
      this.z.setOnClickListener(this);
    this.A = ((Button)findViewById(TenpayResourceUtil.getId(this, "pay_confirm")));
    this.A.setOnClickListener(this);
    Button localButton = (Button)findViewById(TenpayResourceUtil.getId(this, "pay_forget_pass"));
    if (localButton != null)
      localButton.setOnClickListener(this);
    this.N = ((LinearLayout)findViewById(TenpayResourceUtil.getId(this, "pay_yz")));
    this.P = ((TextView)findViewById(TenpayResourceUtil.getId(this, "yz_phone")));
    this.Q = ((EditText)findViewById(TenpayResourceUtil.getId(this, "yz_code")));
    this.R = ((Button)findViewById(TenpayResourceUtil.getId(this, "yz_resend")));
    this.R.setOnClickListener(this);
    this.T = ((Button)findViewById(TenpayResourceUtil.getId(this, "yz_more")));
    if (this.T != null)
      this.T.setOnClickListener(this);
    this.U = ((Button)findViewById(TenpayResourceUtil.getId(this, "yz_confirm")));
    this.U.setOnClickListener(this);
    a();
    initYZ();
  }

  private void g()
  {
    if (this.aj > 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.aj);
      localStringBuilder.append(getResources().getString(TenpayResourceUtil.getStringId(this, "unipay_tenpay_resend_time")));
      localStringBuilder.append(getResources().getString(TenpayResourceUtil.getStringId(this, "unipay_tenpay_resend")));
      this.R.setText(localStringBuilder.toString());
      this.R.setTextColor(-6710887);
      this.R.setTextSize(12.0F);
      this.R.setBackgroundResource(TenpayResourceUtil.getDrawableId(this, "unipay_tenpay_inputbtnbg_dis"));
      this.R.setEnabled(false);
      return;
    }
    this.R.setEnabled(true);
    this.R.setText(TenpayResourceUtil.getStringId(this, "unipay_tenpay_resend"));
    this.R.setTextSize(15.0F);
    this.R.setBackgroundResource(TenpayResourceUtil.getDrawableId(this, "unipay_tenpay_inputbtn"));
    this.R.setTextColor(-16777216);
  }

  private void h()
  {
    if (((this.W == null) || (this.W.size() == 0)) && (this.a != 3))
      return;
    ArrayList localArrayList = this.W;
    int i1 = this.D.getWidth();
    if (this.a == 3);
    for (boolean bool = true; ; bool = false)
    {
      this.aK = new BankSelectDialog(this, localArrayList, i1, bool, this.ag);
      this.aK.setOnItemSelectedListener(new TenpayPluginActivity.25(this));
      this.aK.showAsDropDown(this.D, 0, 0 - this.D.getHeight());
      this.aK.setOnDismissListener(new TenpayPluginActivity.26(this));
      this.D.setClickable(false);
      a("refresh");
      return;
    }
  }

  private void i()
  {
    a locala = c.a(e.a);
    if (locala != null)
      locala.a(this.ak);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("token_id", this.aa);
      localJSONObject.put("uin", this.ab);
      localJSONObject.put("skey", this.ad);
      localJSONObject.put("key_type", this.ac);
      label75: locala.a(this, 0, localJSONObject, this.h, this.ae, this.af);
      if (this.a == 4)
      {
        showMyCFTDialog();
        return;
      }
      this.aO = true;
      showProgressDialog(TenpayResourceUtil.getStringId(this, "unipay_tenpay_init_progress"));
      return;
    }
    catch (JSONException localJSONException)
    {
      break label75;
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
      locala.a(this.ak);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("token_id", this.aa);
      localJSONObject.put("query_type", "PAY");
      label50: if ((this.mPayGate.optInt("user_type") == 0) || (this.mPayGate.optInt("user_type") == 3))
        locala.a(this, 7, localJSONObject, this.h, null, this.af);
      while (true)
      {
        showProgressDialog(TenpayResourceUtil.getStringId(this, "unipay_tenpay_init_progress"));
        return;
        locala.a(this, 7, localJSONObject, this.h, this.ae, this.af);
      }
    }
    catch (JSONException localJSONException)
    {
      break label50;
    }
  }

  void a()
  {
    this.aj = 0;
    if (this.x != null)
      this.x.setVisibility(8);
    if (this.C != null)
      this.C.setVisibility(8);
    if (this.N != null)
      this.N.setVisibility(8);
    switch (this.b)
    {
    case 2:
    case 3:
    case 4:
    default:
      if (this.H != null)
        this.mHandler.postDelayed(new TenpayPluginActivity.22(this), 300L);
      if (this.B != null)
        this.mHandler.postDelayed(new TenpayPluginActivity.23(this), 300L);
      if ((this.P != null) && (this.d != null))
        this.mHandler.postDelayed(new TenpayPluginActivity.24(this), 300L);
      a("show");
      return;
    case 0:
      if (this.a == 0);
      for (this.g = "tenpay.yue."; ; this.g = "tenpay.yuebig.")
      {
        this.x.setVisibility(0);
        this.y.requestFocus();
        break;
      }
    case 1:
      if (this.a == 1);
      for (this.g = "tenpay.kj."; ; this.g = "tenpay.kjbig.")
      {
        this.aG = false;
        this.I.requestFocus();
        this.C.setVisibility(0);
        break;
      }
    case 5:
    }
    if (this.a == 0)
    {
      this.g = "tenpay.yuemsg.";
      label295: if (((this.a == 1) || (this.a == 3)) && (this.W != null))
      {
        String str = ((JSONObject)this.W.get(this.ag)).optString("type");
        if (this.S != null)
        {
          if ((!"DEBIT".equals(str)) && (!"CREDIT".equals(str)))
            break label559;
          this.S.setVisibility(8);
        }
      }
    }
    while (true)
    {
      if ((this.mPayGate != null) && (this.I != null) && (this.mPayGate.optInt("nopwdnosms_flag") == 1) && (this.I.getVisibility() == 8) && (this.O != null))
        this.O.setVisibility(0);
      this.aj = 60;
      g();
      this.Q.requestFocus();
      this.N.setVisibility(0);
      this.mHandler.postDelayed(this.mResendTimer, 1000L);
      break;
      if (this.a == 1)
      {
        this.g = "tenpay.kjmsg.";
        break label295;
      }
      if (this.a == 2)
      {
        this.g = "tenpay.yuebigmsg.";
        break label295;
      }
      if (this.a == 3)
      {
        this.g = "tenpay.kjbigmsg.";
        break label295;
      }
      if (this.a == 4)
      {
        this.g = "tenpay.choosemsg.";
        break label295;
      }
      this.g = "tenpay.choosemsg.";
      break label295;
      label559: this.S.setVisibility(0);
    }
  }

  void b()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("trace", this.e.toString());
    localBundle.putInt("pay_type", getPayType());
    localBundle.putInt("backfrom", this.aw);
    TenpayUtil.onCallback(this, 2, localBundle);
    finish();
  }

  protected ProgressDialog createDialog()
  {
    return new TenpayProgressDialog(this);
  }

  public void dismissProgressDialog()
  {
    if (this.V != null)
    {
      this.V.dismiss();
      this.V = null;
    }
  }

  protected void doSuccess(JSONObject paramJSONObject)
  {
    doSuccess(paramJSONObject, getPayType());
  }

  protected void doSuccess(JSONObject paramJSONObject, int paramInt)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("sp_data", paramJSONObject.optString("sp_data"));
    localBundle.putString("trace", this.e.toString());
    localBundle.putString("total_fee", this.mPayGate.optString("total_fee"));
    localBundle.putInt("pay_type", paramInt);
    localBundle.putInt("backfrom", this.aw);
    TenpayUtil.onCallback(this, 1, localBundle);
    finish();
  }

  public int getPayType()
  {
    if (this.aH)
      return 11;
    if (this.aI)
      return 12;
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
    {
      if (this.W == null)
        return 5;
      String str = ((JSONObject)this.W.get(this.ag)).optString("type");
      if ((("FASTPAY_DEBIT".equals(str)) || ("FASTPAY_CREDIT".equals(str))) && (this.mPayGate.optInt("nopwdnosms_flag") == 1))
        return 11;
      if (this.mPayGate.optInt("full_check", 1) == 1)
        return 5;
      if (this.aG)
        return 9;
      return 8;
    }
    if (i1 == 4)
    {
      if (this.mPayGate.optInt("user_type") == 3)
        return 7;
      if (this.mPayGate.optInt("user_type") == 0)
        return 4;
      if (this.Z != null)
      {
        JSONObject localJSONObject2 = this.Z;
        if (this.Z == null)
          return 2;
        if (localJSONObject2.optInt("pwd_verify") == 1)
          return 3;
        return 2;
      }
      return 2;
    }
    if (i1 == 5)
    {
      JSONObject localJSONObject1 = this.Z;
      if (this.Z == null)
        return 2;
      if (localJSONObject1.optInt("pwd_verify") == 1)
        return 3;
      return 2;
    }
    if (i1 == 6)
      return 10;
    return -1;
  }

  protected void gotoBind(boolean paramBoolean)
  {
    Intent localIntent = new Intent(this, TenpayNewCardActivity.class);
    localIntent.putExtra("receiver", (ResultReceiver)getIntent().getParcelableExtra("receiver"));
    localIntent.putExtra("uin", this.ab);
    localIntent.putExtra("image_id", this.al);
    localIntent.putExtra("amount_title", this.am);
    localIntent.putExtra("amount", this.an);
    localIntent.putExtra("amount_mark", this.ao);
    localIntent.putExtra("price", this.ap);
    localIntent.putExtra("vip_image_id", this.aq);
    localIntent.putExtra("vip_price", this.ar);
    localIntent.putExtra("discount", this.au);
    localIntent.putExtra("pay_type", this.a);
    localIntent.putExtra("token_id", this.aa);
    localIntent.putExtra("time", this.h);
    localIntent.putExtra("pay_gate", this.mPayGate.toString());
    localIntent.putExtra("bank_data", this.aP.toString());
    localIntent.putExtra("is_forget", paramBoolean);
    localIntent.putExtra("orientation", getResources().getConfiguration().orientation);
    startActivityForResult(localIntent, 0);
  }

  protected void initBanks(JSONObject paramJSONObject)
  {
    this.aP = paramJSONObject;
    if ((this.X == null) && (this.X == null) && (this.W == null))
    {
      JSONArray localJSONArray = paramJSONObject.optJSONArray("banklist");
      int i1 = localJSONArray.length();
      int i2 = 0;
      if (i2 >= i1)
      {
        if ((this.a != 1) && (this.a != 3))
          break label764;
        if ((this.W == null) || (this.W.size() == 0))
        {
          this.mLastPayType = this.a;
          this.a = 4;
          gotoBind(true);
          return;
        }
      }
      else
      {
        JSONObject localJSONObject2 = localJSONArray.optJSONObject(i2);
        String str3 = localJSONObject2.optString("type");
        if ((localJSONObject2.optInt("disabled") == 1) || (("-1".equals(this.ac)) && (localJSONObject2.optInt("quickmode") != 1)));
        while (true)
        {
          i2++;
          break;
          if (("FASTPAY_DEBIT".equals(str3)) || ("FASTPAY_CREDIT".equals(str3)) || ("DEBIT".equals(str3)) || ("CREDIT".equals(str3)))
          {
            if (this.W == null)
              this.W = new ArrayList();
            this.W.add(localJSONObject2);
            continue;
          }
          if ("FASTPAY_DEBIT_UNBIND".equals(localJSONObject2.optString("type")))
          {
            if (this.X == null)
              this.X = new ArrayList();
            this.X.add(localJSONObject2);
            continue;
          }
          if (this.Y == null)
            this.Y = new ArrayList();
          this.Y.add(localJSONObject2);
        }
      }
      c();
      if ((this.a == 1) && (this.W != null) && (this.W.size() == 1))
      {
        this.D.setClickable(false);
        this.G.setClickable(false);
        this.D.setFocusable(false);
        this.D.setBackgroundResource(TenpayResourceUtil.getDrawableId(this, "unipay_tenpay_inputbg_normal"));
        this.G.setVisibility(8);
        this.D.setOnClickListener(null);
        this.G.setOnClickListener(null);
      }
    }
    label413: label472: JSONObject localJSONObject1;
    if ((getWindowManager().getDefaultDisplay().getWidth() < 490) && (this.a == 1))
    {
      if (getResources().getConfiguration().orientation == 1)
        this.mHandler.postDelayed(new TenpayPluginActivity.27(this), 100L);
      if ((this.E == null) || (this.W == null) || (this.W.size() <= 0))
        break label848;
      localJSONObject1 = (JSONObject)this.W.get(this.ag);
      String str1 = localJSONObject1.optString("card_tail");
      if ("".equals(str1))
        break label850;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(localJSONObject1.optString("name"));
      localStringBuilder.append(" **");
      localStringBuilder.append(str1);
      this.E.setText(localStringBuilder.toString());
    }
    while (true)
    {
      if (this.ay != null)
        this.ay.setText(this.E.getText());
      if (this.aE != null)
        this.aE.setText(this.E.getText());
      String str2 = localJSONObject1.optString("type");
      if ((("FASTPAY_DEBIT".equals(str2)) || ("FASTPAY_CREDIT".equals(str2))) && (this.mPayGate.optInt("nopwdnosms_flag", 0) == 1))
      {
        this.I.setVisibility(8);
        if (this.L != null)
          this.L.setVisibility(8);
        if (this.M != null)
          this.M.setVisibility(0);
        this.K.setClickable(true);
        this.K.setEnabled(true);
      }
      if ((!"DEBIT".equals(str2)) && (!"CREDIT".equals(str2)))
        break;
      if (this.S == null)
        break label867;
      this.S.setVisibility(8);
      return;
      label764: if (this.a <= 3)
        break label413;
      if ((this.aM != null) && (this.aM.isShowing()))
      {
        this.aM.dismiss();
        this.aM = null;
      }
      gotoBind(false);
      return;
      if ((this.I == null) || (getResources().getConfiguration().orientation != 1))
        break label472;
      this.mHandler.postDelayed(new TenpayPluginActivity.28(this), 300L);
      break label472;
      label848: break;
      label850: this.E.setText(localJSONObject1.optString("name"));
    }
    label867: this.S.setVisibility(0);
  }

  protected void initYZ()
  {
    if ((this.a == 3) && (this.W != null))
      this.W.size();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    int i1 = 1;
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    int i2;
    if (paramIntent != null)
    {
      String str = paramIntent.getStringExtra("trace");
      if (this.e != null)
      {
        if (str == null)
          break label125;
        i2 = i1;
        if (str.length() <= 0)
          break label131;
        if ((i2 & i1) != 0)
        {
          if (this.e.length() > 0)
            this.e.append('|');
          this.e.append(str);
        }
      }
    }
    this.a = this.mLastPayType;
    if (paramInt2 == -1);
    label125: 
    do
      try
      {
        doSuccess(new JSONObject(paramIntent.getStringExtra("data")), paramIntent.getIntExtra("pay_type", 2));
        return;
        i2 = 0;
        break;
        i1 = 0;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
    while (paramInt2 != 100);
    label131: finish();
  }

  public void onClick(View paramView)
  {
    int i1 = paramView.getId();
    if (i1 == TenpayResourceUtil.getId(this, "pay_confirm"))
    {
      if (this.b != 5)
        a("pay");
      if (this.y.getText().toString().length() < 6)
        a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_alert_psd_wrong"));
    }
    label432: 
    do
    {
      do
      {
        while (true)
        {
          return;
          this.ah = paramView;
          b(null);
          return;
          if (i1 == TenpayResourceUtil.getId(this, "yz_reset_phone"))
          {
            a("change");
            String str9 = TenpayResourceUtil.getString(this, "unipay_tenpay_change_phone_alert");
            String str10 = TenpayResourceUtil.getString(this, "unipay_tenpay_set_newphone");
            if (this.mKuaijiePre != null)
            {
              String str11 = this.mKuaijiePre.optString("mobile");
              if ((this.P != null) && (str11.length() > 10))
              {
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append(str11.substring(0, 3));
                localStringBuilder.append("******");
                localStringBuilder.append(str11.substring(-2 + str11.length()));
                Object[] arrayOfObject5 = new Object[1];
                arrayOfObject5[0] = localStringBuilder.toString();
                str10 = String.format(str9, arrayOfObject5);
              }
            }
            this.f = this.g;
            this.g = "tenpay.changem.";
            a("show");
            showMyDialogWithCancel(TenpayResourceUtil.getString(this, "unipay_tenpay_alert"), str10, TenpayResourceUtil.getString(this, "unipay_tenpay_cancel"), TenpayResourceUtil.getString(this, "unipay_tenpay_change_phone"), new TenpayPluginActivity.12(this), new TenpayPluginActivity.13(this));
            return;
          }
          if ((i1 == TenpayResourceUtil.getId(this, "kuai_confirm")) || (i1 == TenpayResourceUtil.getId(this, "reset_thru_confirm")) || (i1 == TenpayResourceUtil.getId(this, "reset_phone_confirm")))
          {
            if ((this.b != 5) || ((this.aB != null) && (this.aB.getVisibility() == 0)) || ((this.ax != null) && (this.ax.getVisibility() == 0)))
            {
              if (i1 != TenpayResourceUtil.getId(this, "kuai_confirm"))
                break label432;
              a("pay");
            }
            while ((i1 == TenpayResourceUtil.getId(this, "kuai_confirm")) && (this.I.getVisibility() == 0) && (this.I.getText().toString().length() < 6))
            {
              a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_alert_psd_wrong"));
              return;
              a("sure");
            }
            if ((this.W == null) || (this.W.size() == 0))
            {
              a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_alert_kuai_bank_wrong"));
              return;
            }
            if (i1 == TenpayResourceUtil.getId(this, "reset_thru_confirm"))
            {
              if ("".equals(this.az.getText().toString()))
              {
                a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_valid_time_not_null"));
                this.az.onError();
                this.az.requestFocus();
                return;
              }
              int i2 = this.az.isValid(System.currentTimeMillis() + 1000L * this.h);
              if (i2 == 3)
              {
                a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_valid_time_out"));
                this.az.onError();
                this.az.requestFocus();
                return;
              }
              if (i2 != 0)
              {
                a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_valid_time_not_null"));
                this.az.onError();
                this.az.requestFocus();
                return;
              }
              if ((this.aD.getVisibility() == 0) && (this.aD.getText().toString().length() < 3))
              {
                a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_cvv_not_null"));
                this.aD.requestFocus();
                return;
              }
            }
            else if ((i1 == TenpayResourceUtil.getId(this, "reset_phone_confirm")) && (this.aC.getText().toString().length() < 11))
            {
              a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_phone_not_null"));
              this.aC.requestFocus();
              return;
            }
            if (i1 == TenpayResourceUtil.getId(this, "kuai_confirm"))
            {
              JSONObject localJSONObject1 = (JSONObject)this.W.get(this.ag);
              if (localJSONObject1.optInt("expired_flag") == 1)
              {
                String str3 = getResources().getString(TenpayResourceUtil.getStringId(this, "unipay_tenpay_change_thru_alert1"));
                Object[] arrayOfObject2 = new Object[2];
                arrayOfObject2[0] = localJSONObject1.optString("card_tail");
                arrayOfObject2[1] = localJSONObject1.optString("name");
                String str4 = String.format(str3, arrayOfObject2);
                this.f = this.g;
                this.g = "tenpay.expired.";
                a("show");
                showMyDialogWithCancel(TenpayResourceUtil.getString(this, "unipay_tenpay_change_thru"), str4, TenpayResourceUtil.getString(this, "unipay_tenpay_cancel"), TenpayResourceUtil.getString(this, "unipay_tenpay_change_thru_now1"), new TenpayPluginActivity.14(this), new TenpayPluginActivity.15(this));
                return;
              }
              if (localJSONObject1.optInt("closetoexpired_flag") == 1)
              {
                String str1 = getResources().getString(TenpayResourceUtil.getStringId(this, "unipay_tenpay_change_thru_alert"));
                Object[] arrayOfObject1 = new Object[2];
                arrayOfObject1[0] = localJSONObject1.optString("card_tail");
                arrayOfObject1[1] = localJSONObject1.optString("name");
                String str2 = String.format(str1, arrayOfObject1);
                this.f = this.g;
                this.g = "tenpay.mayexpire.";
                a("show");
                showMyDialogWithCancel(TenpayResourceUtil.getString(this, "unipay_tenpay_change_thru"), str2, TenpayResourceUtil.getString(this, "unipay_tenpay_change_thru_notnow"), TenpayResourceUtil.getString(this, "unipay_tenpay_change_thru_now"), new TenpayPluginActivity.16(this), new TenpayPluginActivity.17(this));
                return;
              }
            }
            this.ah = paramView;
            if (this.mPayGate.optInt("full_check", 1) == 1)
            {
              a(true, true);
              return;
            }
            a(false, true);
            return;
          }
          if (i1 == TenpayResourceUtil.getId(this, "pay_forget_pass"))
          {
            a("skippwd");
            this.aJ = true;
            j();
            return;
          }
          if (i1 == TenpayResourceUtil.getId(this, "kuai_forget_pass"))
          {
            a("skippwd");
            if (this.mPayGate.optInt("full_check", 1) == 1)
            {
              this.mLastPayType = this.a;
              this.a = 6;
              gotoBind(true);
              return;
            }
            this.ah = paramView;
            this.aG = true;
            this.I.setText("");
            JSONObject localJSONObject2 = (JSONObject)this.W.get(this.ag);
            if (localJSONObject2.optInt("expired_flag") == 1)
            {
              String str7 = getResources().getString(TenpayResourceUtil.getStringId(this, "unipay_tenpay_change_thru_alert1"));
              Object[] arrayOfObject4 = new Object[2];
              arrayOfObject4[0] = localJSONObject2.optString("card_tail");
              arrayOfObject4[1] = localJSONObject2.optString("name");
              String str8 = String.format(str7, arrayOfObject4);
              this.f = this.g;
              this.g = "tenpay.expired.";
              a("show");
              showMyDialogWithCancel(TenpayResourceUtil.getString(this, "unipay_tenpay_change_thru"), str8, TenpayResourceUtil.getString(this, "unipay_tenpay_cancel"), TenpayResourceUtil.getString(this, "unipay_tenpay_change_thru_now1"), new TenpayPluginActivity.18(this), new TenpayPluginActivity.19(this));
              return;
            }
            if (localJSONObject2.optInt("closetoexpired_flag") == 1)
            {
              String str5 = getResources().getString(TenpayResourceUtil.getStringId(this, "unipay_tenpay_change_thru_alert"));
              Object[] arrayOfObject3 = new Object[2];
              arrayOfObject3[0] = localJSONObject2.optString("card_tail");
              arrayOfObject3[1] = localJSONObject2.optString("name");
              String str6 = String.format(str5, arrayOfObject3);
              this.f = this.g;
              this.g = "tenpay.mayexpire.";
              a("show");
              showMyDialogWithCancel(TenpayResourceUtil.getString(this, "unipay_tenpay_change_thru"), str6, TenpayResourceUtil.getString(this, "unipay_tenpay_change_thru_notnow"), TenpayResourceUtil.getString(this, "unipay_tenpay_change_thru_now"), new TenpayPluginActivity.20(this), new TenpayPluginActivity.21(this));
              return;
            }
            a(true, false);
            return;
          }
          if (i1 != TenpayResourceUtil.getId(this, "yz_confirm"))
            break;
          a("sure");
          if (this.Q.getText().toString().length() < 6)
          {
            a(TenpayResourceUtil.getStringId(this, "unipay_tenpay_alert_yz_code_wrong"));
            return;
          }
          if ((this.a == 0) || (this.a == 2))
          {
            b(this.Q.getText().toString());
            return;
          }
          if ((this.a != 1) && (this.a != 3) && (this.a != 6))
            continue;
          a(false);
          return;
        }
        if (i1 == TenpayResourceUtil.getId(this, "yz_resend"))
        {
          a("refresh");
          this.ah.performClick();
          return;
        }
        if ((i1 != TenpayResourceUtil.getId(this, "kuai_bank")) && (i1 != TenpayResourceUtil.getId(this, "kuai_bank_btn")))
          continue;
        h();
        return;
      }
      while ((i1 != TenpayResourceUtil.getId(this, "kuai_more")) && (i1 != TenpayResourceUtil.getId(this, "pay_more")) && (i1 != TenpayResourceUtil.getId(this, "yz_more")));
      a("change");
      Bundle localBundle = new Bundle();
      localBundle.putString("trace", this.e.toString());
      localBundle.putInt("backfrom", this.aw);
      TenpayUtil.onCallback(this, 3, localBundle);
    }
    while (this.mInitCalled);
    finish();
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  public void onCreate(Bundle paramBundle)
  {
    int i1 = 1;
    super.onCreate(paramBundle);
    requestWindowFeature(i1);
    int i2 = getIntent().getIntExtra("orientation", -1);
    if (i2 == -1)
      i2 = getResources().getConfiguration().orientation;
    if (i2 == 2);
    while (true)
    {
      Intent localIntent;
      try
      {
        int i4 = ActivityInfo.class.getField("SCREEN_ORIENTATION_SENSOR_LANDSCAPE").getInt(null);
        i1 = i4;
        setRequestedOrientation(i1);
        this.ak = new TenpayPluginActivity.MyBLCallbackListener(this);
        localIntent = getIntent();
        this.a = localIntent.getIntExtra("pay_type", 0);
        this.aw = this.a;
        this.ab = localIntent.getStringExtra("uin");
        this.ad = localIntent.getStringExtra("skey");
        this.ac = localIntent.getStringExtra("skey_type");
        if (!"-1".equals(this.ac))
          continue;
        this.ad = "";
        this.aa = localIntent.getStringExtra("token_id");
        if (this.aa != null)
          break label279;
        Bundle localBundle = new Bundle();
        localBundle.putString("trace", this.e.toString());
        localBundle.putInt("backfrom", this.aw);
        localBundle.putString("msg", "token为空");
        TenpayUtil.onCallback(this, 4, localBundle);
        finish();
        return;
      }
      catch (Exception localException2)
      {
        i1 = 0;
        continue;
      }
      if (i2 == i1)
      {
        try
        {
          int i3 = ActivityInfo.class.getField("SCREEN_ORIENTATION_SENSOR_PORTRAIT").getInt(null);
          i1 = i3;
          continue;
          label279: this.al = localIntent.getByteArrayExtra("image_id");
          this.an = localIntent.getStringExtra("amount");
          this.am = localIntent.getStringExtra("amount_title");
          this.ao = localIntent.getStringExtra("amount_mark");
          this.ap = localIntent.getStringExtra("price");
          this.aq = localIntent.getIntExtra("vip_image_id", 0);
          this.ar = localIntent.getStringExtra("vip_price");
          this.au = localIntent.getStringExtra("discount");
          i();
          return;
        }
        catch (Exception localException1)
        {
        }
        continue;
      }
      i1 = 0;
    }
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
      if ((this.aL == null) || (!this.aL.isShowing()))
        break;
      this.aL.dismiss();
      if (this.f == null)
        continue;
      a("keyback");
      this.g = this.f;
      this.f = null;
      return i1;
    }
    if ((this.aB != null) && (this.aB.getVisibility() == 0))
    {
      a("keyback");
      this.aH = false;
      this.aB.setVisibility(8);
      this.aC.setText("");
      a();
      return i1;
    }
    if ((this.ax != null) && (this.ax.getVisibility() == 0))
    {
      a("keyback");
      this.aI = false;
      this.ax.setVisibility(8);
      a();
      return i1;
    }
    if (this.a > i1)
      if (this.ai != 0L)
        break label234;
    label234: long l1;
    for (this.ai = System.currentTimeMillis(); (this.aK != null) && (this.aK.isShowing()); this.ai = l1)
    {
      this.aK.dismiss();
      this.aK = null;
      return i1;
      l1 = System.currentTimeMillis();
      if (l1 - this.ai >= 300L)
        continue;
      b();
      return i1;
    }
    a("keyback");
    switch (this.b)
    {
    case 2:
    case 3:
    case 4:
    default:
      return i1;
    case 0:
    case 1:
      b();
      return i1;
    case 5:
    }
    this.aH = false;
    this.aI = false;
    this.b = this.c;
    a();
    return i1;
  }

  protected void onResume()
  {
    super.onResume();
    if (!TenpayUtil.hasCallback())
      finish();
  }

  public void showMyCFTDialog()
  {
    View localView = LayoutInflater.from(this).inflate(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_floattips_cft"), null);
    ImageView localImageView = (ImageView)localView.findViewById(TenpayResourceUtil.getId(this, "float_image"));
    this.aM = new Dialog(this, TenpayResourceUtil.getStyleId(this, "unipay_tenpay_dialog"));
    if (Build.VERSION.SDK_INT > 7)
      this.aM.setOnShowListener(new TenpayPluginActivity.32(this, localImageView));
    this.aM.setOnKeyListener(new TenpayPluginActivity.33(this));
    this.aM.requestWindowFeature(1);
    this.aM.setContentView(localView);
    this.aM.getWindow().setLayout(-1, -2);
    this.aM.show();
    localImageView.postDelayed(new TenpayPluginActivity.34(this), 3000L);
  }

  public void showMyDialog(int paramInt1, int paramInt2, View.OnClickListener paramOnClickListener)
  {
    View localView = LayoutInflater.from(this).inflate(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_floattips"), null);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_title"))).setText(paramInt1);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_message"))).setText(paramInt2);
    ((Button)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_ok"))).setOnClickListener(paramOnClickListener);
    this.aL = new Dialog(this, TenpayResourceUtil.getStyleId(this, "unipay_tenpay_dialog"));
    this.aL.requestWindowFeature(1);
    this.aL.setContentView(localView);
    this.aL.getWindow().setLayout(-1, -2);
    this.aL.setOnDismissListener(new TenpayPluginActivity.35(this));
    this.aL.show();
  }

  public void showMyDialog(String paramString1, String paramString2, View.OnClickListener paramOnClickListener)
  {
    View localView = LayoutInflater.from(this).inflate(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_floattips"), null);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_title"))).setText(paramString1);
    ((TextView)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_message"))).setText(paramString2);
    ((Button)localView.findViewById(TenpayResourceUtil.getId(this, "tenpay_alert_ok"))).setOnClickListener(paramOnClickListener);
    this.aL = new Dialog(this, TenpayResourceUtil.getStyleId(this, "unipay_tenpay_dialog"));
    this.aL.requestWindowFeature(1);
    this.aL.setContentView(localView);
    this.aL.getWindow().setLayout(-1, -2);
    this.aL.setOnDismissListener(new TenpayPluginActivity.29(this));
    this.aL.show();
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
    this.aL = new Dialog(this, TenpayResourceUtil.getStyleId(this, "unipay_tenpay_dialog"));
    this.aL.requestWindowFeature(1);
    this.aL.setContentView(localView);
    this.aL.getWindow().setLayout(-1, -2);
    this.aL.setOnDismissListener(new TenpayPluginActivity.30(this));
    this.aL.show();
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
    this.aL = new Dialog(this, TenpayResourceUtil.getStyleId(this, "unipay_tenpay_dialog"));
    this.aL.requestWindowFeature(1);
    this.aL.setContentView(localView);
    this.aL.getWindow().setLayout(-1, -2);
    this.aL.setOnDismissListener(new TenpayPluginActivity.31(this));
    this.aL.show();
  }

  public void showProgressDialog(int paramInt)
  {
    if (isFinishing());
    do
    {
      do
        return;
      while (((this.aM != null) && (this.aM.isShowing())) || ((this.V != null) && (this.V.isShowing())));
      this.V = createDialog();
    }
    while (this.V == null);
    this.V.setMessage(getString(paramInt));
    this.V.setIndeterminate(true);
    this.V.setCancelable(false);
    this.V.show();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity
 * JD-Core Version:    0.6.0
 */