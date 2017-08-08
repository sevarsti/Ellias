package com.pay.ui.qdsafe;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.pay.buyManager.APPayManager;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APNetworkManager;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.network.modle.APSmsCodeAns;
import com.pay.sms.APSMSObserver;
import com.pay.sms.APSmsHandle;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APPassWordTools;
import com.pay.tool.APTools;
import com.pay.ui.common.APActivity;
import com.pay.ui.common.APProgressDialog;
import com.pay.ui.common.APUICommonMethod;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class APSmmActivity extends APActivity
  implements DialogInterface.OnCancelListener, IAPHttpAnsObserver
{
  Button a;
  Handler b;
  int c = 60;
  l d;
  String e;
  int f;
  ImageButton g;
  private TextView h;
  private TextView i;
  private LinearLayout j;
  private Button k;
  private TextView l;
  private LinearLayout m;
  private boolean n;
  private ImageButton o;
  private APSMSObserver p;
  private APSmsHandle q;
  private ContentResolver r;
  private List s;
  private String t;
  private APProgressDialog u;
  private TextView v;
  public EditText vercodeEdit;
  private String w = "";
  private APPayManager x;
  private TextWatcher y = new f(this);

  private void a()
  {
    if ((this.u != null) && (this.u.isShowing()))
      this.u.dismiss();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    System.out.println("resultCode=" + paramInt2 + " requestCode=" + paramInt1);
    if (paramInt1 == 100002)
    {
      if (paramInt2 != 10)
        break label55;
      setResult(10, paramIntent);
      finish();
    }
    label55: 
    do
      return;
    while (paramInt2 != 11);
    setResult(11, paramIntent);
    finish();
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    int i1 = this.s.size();
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return;
      String str = (String)this.s.get(i2);
      APNetworkManager.getInstance().stopNetWorkBykey(str);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_yzm_phone"));
    int i1 = APDataInterface.singleton().getOrderInfo().saveType;
    if (i1 == 3)
      this.w = APCommMethod.getStringId(this, "unipay_qcoin");
    while (true)
    {
      this.s = new ArrayList();
      this.v = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_tittleBuyInfo")));
      this.v.setText(this.w + APCommMethod.getStringId(this, "unipay_conume_vercode"));
      this.h = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_sendsmstips")));
      this.i = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_smsnumbertips")));
      this.j = ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_smsnumberlayout")));
      this.g = ((ImageButton)findViewById(APCommMethod.getId(this, "unipay_id_CardNumDel")));
      this.g.setOnClickListener(new g(this));
      this.a = ((Button)findViewById(APCommMethod.getId(this, "unipay_id_apverCodeBtn")));
      this.l = ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_changeChannel")));
      this.k = ((Button)findViewById(APCommMethod.getId(this, "unipay_id_apEnsureBtn")));
      this.vercodeEdit = ((EditText)findViewById(APCommMethod.getId(this, "unipay_id_apQCardNumEdit")));
      EditText localEditText = this.vercodeEdit;
      InputFilter[] arrayOfInputFilter = new InputFilter[1];
      arrayOfInputFilter[0] = new InputFilter.LengthFilter(8);
      localEditText.setFilters(arrayOfInputFilter);
      this.vercodeEdit.addTextChangedListener(this.y);
      this.vercodeEdit.setSelectAllOnFocus(false);
      this.vercodeEdit.requestFocus();
      this.vercodeEdit.setFocusable(true);
      this.vercodeEdit.setHint(this.w + APCommMethod.getStringId(this, "unipay_conume_vercode"));
      this.m = ((LinearLayout)findViewById(APCommMethod.getId(this, "unipay_id_send")));
      this.o = ((ImageButton)findViewById(APCommMethod.getId(this, "unipay_id_CloseBtn")));
      Intent localIntent = getIntent();
      this.e = ((String)localIntent.getCharSequenceExtra("requesturl"));
      this.f = localIntent.getIntExtra("count", -1);
      this.t = localIntent.getStringExtra("smsinfo");
      this.n = localIntent.getBooleanExtra("isOnlyMethod", false);
      this.b = new Handler();
      this.d = new l(this, 0);
      this.a.setClickable(true);
      this.a.requestFocus();
      this.a.setFocusable(true);
      this.q = new APSmsHandle(this);
      this.r = getContentResolver();
      this.a.setOnClickListener(new h(this));
      if (this.n)
        this.l.setText(APCommMethod.getStringId(this, "unipay_appeal"));
      this.l.setOnClickListener(new i(this));
      this.k.setOnClickListener(new j(this));
      this.o.setOnClickListener(new k(this));
      return;
      if (i1 != 2)
        continue;
      this.w = APCommMethod.getStringId(this, "unipay_qpoint");
    }
  }

  public void onDestroy()
  {
    this.b.removeCallbacksAndMessages(null);
    this.s.clear();
    if (this.p != null)
    {
      this.r.unregisterContentObserver(this.p);
      this.p = null;
    }
    this.r = null;
    this.q = null;
    this.d = null;
    this.b = null;
    this.s = null;
    super.onDestroy();
  }

  public void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
    a();
    APUICommonMethod.showToast(this, APCommMethod.getStringId(this, "unipay_neterror"), null, false);
  }

  public void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    APSmsCodeAns localAPSmsCodeAns;
    if (paramAPBaseHttpAns.getHttpReqKey().equals("getsmscode"))
    {
      a();
      localAPSmsCodeAns = (APSmsCodeAns)paramAPBaseHttpAns;
      switch (localAPSmsCodeAns.getResultCode())
      {
      default:
        APUICommonMethod.showToast(this, APCommMethod.getStringId(this, "unipay_sendvercode_error"), null, false);
      case 1094:
      case 1099:
      case 0:
      }
    }
    do
    {
      do
      {
        do
        {
          return;
          String str = localAPSmsCodeAns.getMobile();
          this.f = Integer.valueOf(localAPSmsCodeAns.getCount()).intValue();
          if ((APTools.isHavedPermission("android.permission.READ_SMS")) && (this.p == null))
          {
            this.p = new APSMSObserver(this.r, this.q);
            this.r.registerContentObserver(Uri.parse("content://sms/"), true, this.p);
          }
          this.m.setVisibility(0);
          this.h.setText(APCommMethod.getStringId(this, "unipay_vercode_send") + str);
          if ((this.f <= 0) || (this.f > 3))
            continue;
          this.j.setVisibility(0);
          this.i.setText(APCommMethod.getStringId(this, "unipay_vercode_left") + this.f + APCommMethod.getStringId(this, "unipay_vercode_unit"));
          return;
        }
        while (this.f != 0);
        this.a.setText(APCommMethod.getStringId(this, "unipay_vercode_over"));
        this.a.setEnabled(false);
      }
      while (this.b == null);
      this.b.removeCallbacksAndMessages(null);
      return;
    }
    while (!paramAPBaseHttpAns.getHttpReqKey().equals("save"));
    this.x.progressPayManagerAns(paramAPBaseHttpAns);
  }

  protected void onPause()
  {
    super.onPause();
    APPassWordTools.closeKeyboard(this, this.vercodeEdit);
  }

  public void onResume()
  {
    super.onResume();
    System.out.println("onResume come in");
    if (getResources().getConfiguration().orientation == 2)
      getWindow().setSoftInputMode(3);
    do
      return;
    while (getResources().getConfiguration().orientation != 1);
    this.vercodeEdit.requestFocus();
    this.vercodeEdit.setFocusable(true);
    APPassWordTools.openKeyboard(this, this.vercodeEdit);
  }

  public void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
    a();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.APSmmActivity
 * JD-Core Version:    0.6.0
 */