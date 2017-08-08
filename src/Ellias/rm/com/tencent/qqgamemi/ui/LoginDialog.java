package com.tencent.qqgamemi.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.login.LoginCallBack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginDialog extends QMiDialog
{
  private static final int B = 1;
  private static final int C = 2;
  private static final int D = 3;
  private static final String a = "LoginDialog";
  private static final int c = 3;
  private static final String d = "******";
  private View.OnClickListener A = new k(this);
  private Handler E = new l(this);
  private Context b = null;
  private String e = null;
  private boolean f = true;
  private List g = new ArrayList();
  private w h = null;
  private EditText i = null;
  private ImageView j = null;
  private View k = null;
  private CheckBox l = null;
  private PopupWindow m = null;
  private ListView n = null;
  private TextView o = null;
  private ImageView p = null;
  private Button q = null;
  private boolean r = false;
  private boolean s = false;
  private View t = null;
  private ImageView u = null;
  private EditText v = null;
  private boolean w = false;
  private boolean x = false;
  private LoginCallBack y = null;
  private boolean z = false;

  public LoginDialog(Context paramContext)
  {
    super(paramContext, ResourceUtil.d("Qmi_Common_Dialog"));
    a(paramContext);
  }

  public LoginDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    a(paramContext);
  }

  private void a(int paramInt)
  {
    this.g.remove(paramInt);
    if (this.g.size() < 1)
      this.m.dismiss();
    this.h.notifyDataSetChanged();
    a("");
  }

  private void a(Context paramContext)
  {
    this.b = paramContext;
    b();
    View localView = getLayoutInflater().inflate(ResourceUtil.a("qmi_login_dialog"), null);
    a(localView);
    a(this.e);
    addContentView(localView, new ViewGroup.LayoutParams(-1, -1));
    setCanceledOnTouchOutside(true);
    getWindow().setType(2003);
  }

  private void a(Bitmap paramBitmap)
  {
    this.w = true;
    this.t.setVisibility(0);
    this.u.setImageBitmap(paramBitmap);
    this.u.setOnClickListener(new m(this));
    this.v.setText("");
  }

  private void a(View paramView)
  {
    ((TextView)paramView.findViewById(ResourceUtil.f("title_text"))).setText(ResourceUtil.b("qmi_login_text_login"));
    paramView.findViewById(ResourceUtil.f("title_close")).setOnClickListener(new i(this));
    paramView.findViewById(ResourceUtil.f("title_back")).setOnClickListener(new n(this));
    this.i = ((EditText)paramView.findViewById(ResourceUtil.f("login_account")));
    if (this.i != null)
    {
      this.i.addTextChangedListener(new o(this));
      this.i.setOnFocusChangeListener(new p(this));
    }
    this.j = ((ImageView)paramView.findViewById(ResourceUtil.f("login_account_delete")));
    if (this.j != null)
      this.j.setOnClickListener(new q(this));
    this.o = ((EditText)paramView.findViewById(ResourceUtil.f("login_password")));
    if (this.o != null)
    {
      this.o.addTextChangedListener(new r(this));
      this.o.setOnFocusChangeListener(new s(this));
    }
    this.p = ((ImageView)paramView.findViewById(ResourceUtil.f("login_password_delete")));
    if (this.p != null)
      this.p.setOnClickListener(new t(this));
    this.k = paramView.findViewById(ResourceUtil.f("divider_image"));
    this.l = ((CheckBox)paramView.findViewById(ResourceUtil.f("login_spinner")));
    this.l.setOnCheckedChangeListener(new u(this));
    if (this.g.size() > 0)
      this.l.setVisibility(0);
    while (true)
    {
      this.n = ((ListView)LayoutInflater.from(this.b).inflate(ResourceUtil.a("qmi_login_spinner"), null).findViewById(ResourceUtil.f("login_spinner_list")));
      this.h = new w(this, this.b, this.g);
      this.n.setAdapter(this.h);
      this.n.setOnItemClickListener(new j(this));
      this.t = paramView.findViewById(ResourceUtil.f("verify_layout"));
      this.u = ((ImageView)paramView.findViewById(ResourceUtil.f("verify_code")));
      this.v = ((EditText)paramView.findViewById(ResourceUtil.f("verify_input")));
      this.q = ((Button)paramView.findViewById(ResourceUtil.f("login_btn")));
      if (this.q != null)
        this.q.setOnClickListener(this.A);
      return;
      this.l.setVisibility(8);
    }
  }

  private void a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      this.i.setText("");
      this.o.setText("");
      this.f = true;
      return;
    }
    this.i.setText(paramString);
    this.i.setSelection(paramString.length());
  }

  private boolean a(long paramLong)
  {
    Iterator localIterator = this.g.iterator();
    while (localIterator.hasNext())
      if (((y)localIterator.next()).b.longValue() == paramLong)
        return true;
    return false;
  }

  private void b()
  {
    if (this.g != null)
    {
      this.g.clear();
      for (int i1 = -1 + this.g.size(); i1 >= 0; i1--);
    }
  }

  private void d()
  {
    this.w = false;
    this.t.setVisibility(8);
  }

  public void a()
  {
    ((InputMethodManager)this.b.getSystemService("input_method")).hideSoftInputFromWindow(this.i.getWindowToken(), 2);
  }

  public void a(LoginCallBack paramLoginCallBack)
  {
    this.z = false;
    this.y = paramLoginCallBack;
  }

  public void dismiss()
  {
    TLog.c("LoginDialog", "dismiss");
    if (this.r)
      QMiCommon.c(this.b, "正在登录,请稍候");
    do
    {
      return;
      super.dismiss();
      if ((this.z) || (this.y == null))
        continue;
      this.z = true;
      this.y.onLoginClose();
    }
    while (this.r);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.LoginDialog
 * JD-Core Version:    0.6.0
 */