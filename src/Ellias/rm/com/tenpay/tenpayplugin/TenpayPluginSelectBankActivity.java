package com.tenpay.tenpayplugin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.ListView;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class TenpayPluginSelectBankActivity extends Activity
{
  Map a = new LinkedHashMap();
  String b;
  String c;
  StringBuilder d = new StringBuilder();
  String e = "tenpay.choose.";
  int f;
  private ListView g;
  private String h;
  private TenpayPluginSelectBankActivity.CustomAdapter i;
  private int j;

  private void a(String paramString)
  {
    if (this.d.length() > 0)
      this.d.append('|');
    this.d.append(this.e);
    this.d.append(paramString);
    this.d.append("-");
    this.d.append(this.j);
  }

  protected void initBanks(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray = paramJSONObject.optJSONArray("banklist");
    int k = localJSONArray.length();
    int m = 0;
    if (m >= k)
    {
      this.i = new TenpayPluginSelectBankActivity.CustomAdapter(this, this);
      this.g.setAdapter(this.i);
      this.g.setOnItemClickListener(new TenpayPluginSelectBankActivity.2(this));
      return;
    }
    JSONObject localJSONObject = localJSONArray.optJSONObject(m);
    String str1 = localJSONObject.optString("type");
    if ((localJSONObject.optInt("disabled") == 1) || (("-1".equals(this.h)) && (localJSONObject.optInt("quickmode") != 1)));
    while (true)
    {
      m++;
      break;
      if (("DEBIT".equals(str1)) || ("CREDIT".equals(str1)))
        continue;
      String str2 = localJSONObject.optString("sname");
      TenpayPluginSelectBankActivity.BankData localBankData;
      if (this.a.containsKey(str2))
      {
        localBankData = (TenpayPluginSelectBankActivity.BankData)this.a.get(str2);
        if (("FASTPAY_DEBIT_UNBIND".equals(str1)) || ("FASTPAY_DEBIT".equals(str1)))
        {
          localBankData.debit = localJSONObject;
          continue;
        }
      }
      else
      {
        localBankData = new TenpayPluginSelectBankActivity.BankData(this);
        String str3 = localJSONObject.optString("name");
        int n = str3.indexOf('-');
        if (n > 0);
        for (localBankData.name = str3.substring(0, n); ; localBankData.name = str3)
        {
          this.a.put(str2, localBankData);
          break;
        }
      }
      localBankData.credit = localJSONObject;
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    if (getResources().getConfiguration().orientation == 2);
    try
    {
      int i1 = ActivityInfo.class.getField("SCREEN_ORIENTATION_SENSOR_LANDSCAPE").getInt(null);
      m = i1;
      while (true)
      {
        setRequestedOrientation(m);
        setContentView(TenpayResourceUtil.getLayoutId(this, "unipay_tenpay_bigview_selectbank"));
        this.g = ((ListView)findViewById(TenpayResourceUtil.getId(this, "select_list")));
        Intent localIntent = getIntent();
        try
        {
          this.h = localIntent.getStringExtra("key_type");
          this.j = localIntent.getIntExtra("pay_type", 0);
          JSONObject localJSONObject = new JSONObject(localIntent.getStringExtra("bank_data"));
          this.b = localIntent.getStringExtra("bank_name");
          this.c = this.b;
          this.f = localIntent.getIntExtra("index", -1);
          initBanks(localJSONObject);
          label160: a("show");
          new Handler().postDelayed(new TenpayPluginSelectBankActivity.1(this), 500L);
          return;
          int k = getResources().getConfiguration().orientation;
          m = 0;
          if (k != 1)
            continue;
          try
          {
            int n = ActivityInfo.class.getField("SCREEN_ORIENTATION_SENSOR_PORTRAIT").getInt(null);
            m = n;
          }
          catch (Exception localException1)
          {
            m = 1;
          }
        }
        catch (Exception localException2)
        {
          break label160;
        }
      }
    }
    catch (Exception localException3)
    {
      while (true)
        int m = 0;
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default:
    case 4:
    }
    while (true)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      a("keyback");
      Intent localIntent = new Intent();
      localIntent.putExtra("trace", this.d.toString());
      setResult(0, localIntent);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginSelectBankActivity
 * JD-Core Version:    0.6.0
 */