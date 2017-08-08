package com.tenpay.a.a;

import android.content.Context;
import android.util.Log;
import org.json.JSONObject;
import tencent.com.cftutils.BCDEncUtil;
import tencent.com.cftutils.PassWdEncUtil;

public class h extends a
{
  protected static final String c = h.class.getSimpleName();
  int d;
  private long e;

  protected Object a(Context paramContext, JSONObject paramJSONObject)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://cl.tenpay.com/cgi-bin/clientv1.0/wal_query_cardbin_conf.cgi?ver=2.0&chv=3&");
    return localStringBuilder.toString();
  }

  String a()
  {
    return Long.toString(System.currentTimeMillis() / 1000L + this.e);
  }

  public void a(Context paramContext, int paramInt1, JSONObject paramJSONObject, long paramLong, String paramString, int paramInt2)
  {
    Context localContext = paramContext.getApplicationContext();
    this.d = paramInt2;
    this.e = paramLong;
    this.a = new i(this, paramInt1, localContext, paramJSONObject, paramString);
    this.a.start();
  }

  protected Object b(Context paramContext, JSONObject paramJSONObject)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("https://cl.tenpay.com/cgi-bin/clientv1.0/game_verify.cgi?ver=2.0&chv=3&req_text=");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("token_id=");
    localStringBuilder2.append(paramJSONObject.optString("token_id"));
    localStringBuilder2.append("&bank_type=");
    localStringBuilder2.append(paramJSONObject.optString("bank_type"));
    localStringBuilder2.append("&mobile=");
    localStringBuilder2.append(paramJSONObject.optString("mobile"));
    localStringBuilder2.append("&auth_params=");
    localStringBuilder2.append(paramJSONObject.optString("auth_params"));
    localStringBuilder2.append("&verify_code=");
    localStringBuilder2.append(paramJSONObject.optString("verify_code"));
    localStringBuilder1.append(g.a(paramContext, this.d, false, localStringBuilder2.toString()));
    return localStringBuilder1.toString();
  }

  protected String c(Context paramContext, JSONObject paramJSONObject)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://cl.tenpay.com/cgi-bin/clientv1.0/wal_bank_query.cgi?ver=2.0&chv=3&pay_type=YDT|FASTPAY");
    localStringBuilder.append("&query_type=");
    localStringBuilder.append(paramJSONObject.optString("query_type"));
    localStringBuilder.append("&token_id=");
    localStringBuilder.append(paramJSONObject.optString("token_id"));
    localStringBuilder.append("&unbind_flag=1");
    localStringBuilder.append("&only_fastpay=0");
    localStringBuilder.append("&fastpay_debit=1");
    localStringBuilder.append("&");
    localStringBuilder.append(g.b(paramContext));
    return localStringBuilder.toString();
  }

  protected String d(Context paramContext, JSONObject paramJSONObject)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(com.tenpay.a.c.a.b);
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("token_id=");
    localStringBuilder2.append(paramJSONObject.optString("token_id"));
    String str1 = paramJSONObject.optString("pass");
    PassWdEncUtil localPassWdEncUtil;
    if ((str1 != null) && (!"".equals(str1)))
    {
      localPassWdEncUtil = new PassWdEncUtil();
      localPassWdEncUtil.setTimeStamp(a());
      if (!"YDT".equals(paramJSONObject.optString("kuaijie_type")))
        break label400;
      localPassWdEncUtil.encryptPasswd1(str1);
    }
    while (true)
    {
      String str5 = localPassWdEncUtil.getTimeStamp();
      String str6 = new BCDEncUtil().BcdEncode(str5);
      String str7 = localPassWdEncUtil.getEncryptPasswd();
      localStringBuilder2.append("&p=");
      localStringBuilder2.append(str6);
      localStringBuilder2.append("F0D6C4CEE093903BFD05D6303A581B97E8442ABD");
      localStringBuilder2.append(str7);
      localStringBuilder2.append("&purchaser_id=");
      localStringBuilder2.append(paramJSONObject.optString("purchaser_id"));
      localStringBuilder2.append("&bank_type=");
      localStringBuilder2.append(paramJSONObject.optString("bank_type"));
      localStringBuilder2.append("&verify_flag=");
      localStringBuilder2.append(paramJSONObject.optString("verify_flag"));
      localStringBuilder2.append("&nopwdnosms_flag=");
      localStringBuilder2.append(paramJSONObject.optString("nopwdnosms_flag"));
      Log.d("CFT", localStringBuilder2.toString());
      String str2 = paramJSONObject.optString("newcthru");
      if ((str2 != null) && (!"".equals(str2)))
      {
        localStringBuilder2.append("&newcthru=");
        localStringBuilder2.append(str2);
      }
      String str3 = paramJSONObject.optString("newcvv");
      if ((str3 != null) && (!"".equals(str3)))
      {
        localStringBuilder2.append("&newcvv=");
        localStringBuilder2.append(str3);
      }
      String str4 = paramJSONObject.optString("newmobile");
      if ((str4 != null) && (!"".equals(str4)))
      {
        localStringBuilder2.append("&newmobile=");
        localStringBuilder2.append(str4);
      }
      localStringBuilder1.append(g.a(paramContext, this.d, false, localStringBuilder2.toString()));
      return localStringBuilder1.toString();
      label400: localPassWdEncUtil.encryptPasswd(str1);
    }
  }

  protected String e(Context paramContext, JSONObject paramJSONObject)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("https://cl.tenpay.com/cgi-bin/clientv1.0/game_fpay_regbind.cgi?ver=2.0&chv=3&req_text=");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("token_id=");
    localStringBuilder2.append(paramJSONObject.optString("token_id"));
    localStringBuilder2.append("&auth_params=");
    localStringBuilder2.append(paramJSONObject.optString("auth_params"));
    localStringBuilder2.append("&wap_token=");
    localStringBuilder2.append(paramJSONObject.optString("wap_token"));
    localStringBuilder2.append("&bank_type=");
    localStringBuilder2.append(paramJSONObject.optString("bank_type"));
    localStringBuilder2.append("&bank_card_id=");
    localStringBuilder2.append(paramJSONObject.optString("bank_card_id"));
    localStringBuilder2.append("&true_name=");
    localStringBuilder2.append(paramJSONObject.optString("true_name"));
    localStringBuilder2.append("&creditcard_id=");
    localStringBuilder2.append(paramJSONObject.optString("creditcard_id"));
    localStringBuilder2.append("&mobile=");
    localStringBuilder2.append(paramJSONObject.optString("mobile"));
    String str1 = paramJSONObject.optString("valid_thru");
    if ((str1 != null) && (!"".equals(str1)))
    {
      localStringBuilder2.append("&valid_thru=");
      localStringBuilder2.append(str1);
    }
    String str2 = paramJSONObject.optString("cvc");
    if ((str2 != null) && (!"".equals(str2)))
    {
      localStringBuilder2.append("&cvc=");
      localStringBuilder2.append(str2);
    }
    localStringBuilder2.append("&purchaser_id=");
    localStringBuilder2.append(paramJSONObject.optString("purchaser_id"));
    localStringBuilder2.append("&verify_code=");
    localStringBuilder2.append(paramJSONObject.optString("verify_code"));
    localStringBuilder2.append("&p=");
    PassWdEncUtil localPassWdEncUtil = new PassWdEncUtil();
    localPassWdEncUtil.setTimeStamp(a());
    localPassWdEncUtil.encryptPasswd(paramJSONObject.optString("pass"));
    String str3 = localPassWdEncUtil.getEncryptPasswd();
    String str4 = localPassWdEncUtil.getTimeStamp();
    localStringBuilder2.append(new BCDEncUtil().BcdEncode(str4));
    localStringBuilder2.append("F0D6C4CEE093903BFD05D6303A581B97E8442ABD");
    localStringBuilder2.append(str3);
    localStringBuilder1.append(g.a(paramContext, this.d, false, localStringBuilder2.toString()));
    return localStringBuilder1.toString();
  }

  protected String f(Context paramContext, JSONObject paramJSONObject)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("https://cl.tenpay.com/cgi-bin/clientv1.0/game_fauth.cgi?ver=2.0&chv=3&req_text=");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("token_id=");
    localStringBuilder2.append(paramJSONObject.optString("token_id"));
    localStringBuilder2.append("&bank_type=");
    localStringBuilder2.append(paramJSONObject.optString("bank_type"));
    localStringBuilder2.append("&bank_card_id=");
    localStringBuilder2.append(paramJSONObject.optString("bank_card_id"));
    localStringBuilder2.append("&true_name=");
    localStringBuilder2.append(paramJSONObject.optString("true_name"));
    localStringBuilder2.append("&creditcard_id=");
    localStringBuilder2.append(paramJSONObject.optString("creditcard_id"));
    localStringBuilder2.append("&mobile=");
    localStringBuilder2.append(paramJSONObject.optString("mobile"));
    String str1 = paramJSONObject.optString("valid_thru");
    if ((str1 != null) && (!"".equals(str1)))
    {
      localStringBuilder2.append("&valid_thru=");
      localStringBuilder2.append(str1);
    }
    String str2 = paramJSONObject.optString("cvc");
    if ((str2 != null) && (!"".equals(str2)))
    {
      localStringBuilder2.append("&cvc=");
      localStringBuilder2.append(str2);
    }
    localStringBuilder1.append(g.a(paramContext, this.d, false, localStringBuilder2.toString()));
    return localStringBuilder1.toString();
  }

  protected String g(Context paramContext, JSONObject paramJSONObject)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("https://cl.tenpay.com/cgi-bin/clientv1.0/game_fpay_verify.cgi?ver=2.0&chv=3&req_text=");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("token_id=");
    localStringBuilder2.append(paramJSONObject.optString("token_id"));
    localStringBuilder2.append("&bank_type=");
    localStringBuilder2.append(paramJSONObject.optString("bank_type"));
    localStringBuilder2.append("&verify_code=");
    localStringBuilder2.append(paramJSONObject.optString("verify_code"));
    localStringBuilder2.append("&bind_flag=");
    localStringBuilder2.append(paramJSONObject.optString("bind_flag"));
    String str1 = paramJSONObject.optString("kuaijie_type");
    String str2 = paramJSONObject.optString("pass");
    if ("YDT".equals(str1))
    {
      localStringBuilder2.append("&bank_data=");
      localStringBuilder2.append(paramJSONObject.optString("bank_data"));
      localStringBuilder2.append("&billno=");
      localStringBuilder2.append(paramJSONObject.optString("billno"));
      localStringBuilder2.append("&fee1=");
      localStringBuilder2.append(paramJSONObject.optString("fee1"));
      localStringBuilder2.append("&fee2=");
      localStringBuilder2.append(paramJSONObject.optString("fee2"));
      localStringBuilder2.append("&is_wap=");
      localStringBuilder2.append(paramJSONObject.optString("is_wap"));
      localStringBuilder2.append("&pay_type=");
      localStringBuilder2.append(paramJSONObject.optString("pay_type"));
      localStringBuilder2.append("&purchaser_id=");
      localStringBuilder2.append(paramJSONObject.optString("purchaser_id"));
      if (!"".equals(str2))
      {
        localStringBuilder2.append("&p=");
        PassWdEncUtil localPassWdEncUtil2 = new PassWdEncUtil();
        localPassWdEncUtil2.setTimeStamp(a());
        localPassWdEncUtil2.encryptPasswd(str2);
        String str5 = localPassWdEncUtil2.getEncryptPasswd();
        String str6 = localPassWdEncUtil2.getTimeStamp();
        localStringBuilder2.append(new BCDEncUtil().BcdEncode(str6));
        localStringBuilder2.append("F0D6C4CEE093903BFD05D6303A581B97E8442ABD");
        localStringBuilder2.append(str5);
      }
      localStringBuilder2.append("&request_text=");
      localStringBuilder2.append(paramJSONObject.optString("request_text"));
      localStringBuilder2.append("&sign=");
      localStringBuilder2.append(paramJSONObject.optString("sign"));
      localStringBuilder2.append("&sp_id=");
      localStringBuilder2.append(paramJSONObject.optString("sp_id"));
      localStringBuilder2.append("&total_fee=");
      localStringBuilder2.append(paramJSONObject.optString("total_fee"));
      localStringBuilder2.append("&transaction_id=");
      localStringBuilder2.append(paramJSONObject.optString("transaction_id"));
    }
    while (true)
    {
      localStringBuilder1.append(g.a(paramContext, this.d, false, localStringBuilder2.toString()));
      return localStringBuilder1.toString();
      localStringBuilder2.append("&transaction_id=");
      localStringBuilder2.append(paramJSONObject.optString("transaction_id"));
      localStringBuilder2.append("&mobile=");
      localStringBuilder2.append(paramJSONObject.optString("mobile"));
      localStringBuilder2.append("&business_type=");
      localStringBuilder2.append(paramJSONObject.optString("business_type"));
      localStringBuilder2.append("&bind_flag=");
      localStringBuilder2.append(paramJSONObject.optString("bind_flag"));
      localStringBuilder2.append("&auth_params=");
      localStringBuilder2.append(paramJSONObject.optString("auth_params"));
      localStringBuilder2.append("&token=");
      localStringBuilder2.append(paramJSONObject.optString("token"));
      localStringBuilder2.append("&purchaser_id=");
      localStringBuilder2.append(paramJSONObject.optString("purchaser_id"));
      if ("".equals(str2))
        continue;
      localStringBuilder2.append("&p=");
      PassWdEncUtil localPassWdEncUtil1 = new PassWdEncUtil();
      localPassWdEncUtil1.setTimeStamp(a());
      localPassWdEncUtil1.encryptPasswd(str2);
      String str3 = localPassWdEncUtil1.getEncryptPasswd();
      String str4 = localPassWdEncUtil1.getTimeStamp();
      localStringBuilder2.append(new BCDEncUtil().BcdEncode(str4));
      localStringBuilder2.append("F0D6C4CEE093903BFD05D6303A581B97E8442ABD");
      localStringBuilder2.append(str3);
    }
  }

  protected String h(Context paramContext, JSONObject paramJSONObject)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("https://cl.tenpay.com/cgi-bin/clientv1.0/game_fpay.cgi?ver=2.0&chv=3&req_text=");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("token_id=");
    localStringBuilder2.append(paramJSONObject.optString("token_id"));
    localStringBuilder2.append("&bank_type=");
    localStringBuilder2.append(paramJSONObject.optString("bank_type"));
    localStringBuilder2.append("&bank_card_id=");
    localStringBuilder2.append(paramJSONObject.optString("bank_card_id"));
    localStringBuilder2.append("&true_name=");
    localStringBuilder2.append(paramJSONObject.optString("true_name"));
    localStringBuilder2.append("&creditcard_id=");
    localStringBuilder2.append(paramJSONObject.optString("creditcard_id"));
    localStringBuilder2.append("&mobile=");
    localStringBuilder2.append(paramJSONObject.optString("mobile"));
    String str1 = paramJSONObject.optString("valid_thru");
    if ((str1 != null) && (!"".equals(str1)))
    {
      localStringBuilder2.append("&valid_thru=");
      localStringBuilder2.append(str1);
    }
    String str2 = paramJSONObject.optString("cvc");
    if ((str2 != null) && (!"".equals(str2)))
    {
      localStringBuilder2.append("&cvc=");
      localStringBuilder2.append(str2);
    }
    String str3 = paramJSONObject.optString("purchaser_id");
    if ((str3 != null) && (!"".equals(str3)))
    {
      localStringBuilder2.append("&purchaser_id=");
      localStringBuilder2.append(str3);
    }
    String str4 = paramJSONObject.optString("pass");
    if (!"".equals(str4))
    {
      PassWdEncUtil localPassWdEncUtil = new PassWdEncUtil();
      localPassWdEncUtil.setTimeStamp(a());
      localPassWdEncUtil.encryptPasswd(str4);
      String str5 = localPassWdEncUtil.getTimeStamp();
      String str6 = new BCDEncUtil().BcdEncode(str5);
      String str7 = localPassWdEncUtil.getEncryptPasswd();
      localStringBuilder2.append("&p=");
      localStringBuilder2.append(str6);
      localStringBuilder2.append("F0D6C4CEE093903BFD05D6303A581B97E8442ABD");
      localStringBuilder2.append(str7);
    }
    localStringBuilder1.append(g.a(paramContext, this.d, false, localStringBuilder2.toString()));
    return localStringBuilder1.toString();
  }

  protected String i(Context paramContext, JSONObject paramJSONObject)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("https://cl.tenpay.com/cgi-bin/clientv1.0/game_balance.cgi?ver=2.0&chv=3&req_text=");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("token_id=");
    localStringBuilder2.append(paramJSONObject.optString("token_id"));
    localStringBuilder2.append("&purchaser_id=");
    localStringBuilder2.append(paramJSONObject.optString("purchaser_id"));
    String str1 = paramJSONObject.optString("pass");
    PassWdEncUtil localPassWdEncUtil = new PassWdEncUtil();
    localPassWdEncUtil.setTimeStamp(a());
    localPassWdEncUtil.encryptPasswd(str1);
    String str2 = localPassWdEncUtil.getTimeStamp();
    String str3 = new BCDEncUtil().BcdEncode(str2);
    String str4 = localPassWdEncUtil.getEncryptPasswd();
    localStringBuilder2.append("&p=");
    localStringBuilder2.append(str3);
    localStringBuilder2.append("F0D6C4CEE093903BFD05D6303A581B97E8442ABD");
    localStringBuilder2.append(str4);
    String str5 = paramJSONObject.optString("verify_code");
    if ((str5 != null) && (!"".equals(str5)))
    {
      localStringBuilder2.append("&verify_code=");
      localStringBuilder2.append(paramJSONObject.optString("verify_code"));
    }
    localStringBuilder1.append(g.a(paramContext, this.d, false, localStringBuilder2.toString()));
    return localStringBuilder1.toString();
  }

  String j(Context paramContext, JSONObject paramJSONObject)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("https://cl.tenpay.com/cgi-bin/clientv1.0/game_gate.cgi?ver=2.0&chv=3&req_text=");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("token_id=");
    localStringBuilder2.append(paramJSONObject.optString("token_id"));
    localStringBuilder2.append("&skey=");
    localStringBuilder2.append(paramJSONObject.optString("skey"));
    localStringBuilder2.append("&skey_type=");
    localStringBuilder2.append(paramJSONObject.optString("key_type"));
    localStringBuilder1.append(g.a(paramContext, this.d, false, localStringBuilder2.toString()));
    return localStringBuilder1.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.a.a.h
 * JD-Core Version:    0.6.0
 */