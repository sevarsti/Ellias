/*    */ package com.ogzq.service.impl;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.ogzq.entity.Result;
/*    */ import com.ogzq.entity.UserEntity;
/*    */ import com.ogzq.util.HttpUtils;
/*    */ import java.util.HashMap;
/*    */ import org.apache.http.conn.ClientConnectionManager;
/*    */ import org.apache.http.impl.client.DefaultHttpClient;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class XdgameUserServiceImpl extends BaseUserService
/*    */ {
/* 16 */   protected Logger logger = LoggerFactory.getLogger(getClass());
/*    */ 
/*    */   public Result login(UserEntity entity) throws Exception
/*    */   {
/* 20 */     DefaultHttpClient httpClient = HttpUtils.getHttpClient();
/* 21 */     HashMap map = Maps.newHashMap();
/*    */ 
/* 23 */     map.put("Login_1", entity.getUserNmae() + "*" + entity.getPassword());
/* 24 */     Result result = HttpUtils.post(httpClient, entity.getLoginUrl(), map);
/* 25 */     this.cs = httpClient.getCookieStore();
/* 26 */     this.logger.debug("登录返回信息:{}", result.getBody());
/* 27 */     String[] splitStr = result.getBody().split("[*]");
/* 28 */     this.account = splitStr[0];
/* 29 */     this.accountSign = splitStr[1];
/* 30 */     map = Maps.newHashMap();
/*    */ 
/* 32 */     map.put("GetNewUrl_1", this.accountSign);
/* 33 */     result = HttpUtils.post(httpClient, entity.getLoginUrl(), map);
/* 34 */     this.cs = httpClient.getCookieStore();
/* 35 */     String url = "";
/* 36 */     if (entity.getServer().equals("qqgame"))
/* 37 */       url = "http://ogzq.xdgame.cn/Transition.aspx?S=" + entity.getNum() + "&TP=QQ";
/*    */     else {
/* 39 */       url = "http://ogzq.xdgame.cn/Transition.aspx?S=" + entity.getNum();
/*    */     }
/*    */ 
/* 42 */     result = HttpUtils.get(httpClient, url);
/*    */ 
/* 44 */     this.cs = httpClient.getCookieStore();
/*    */ 
/* 46 */     httpClient.getConnectionManager().shutdown();
/* 47 */     return result;
/*    */   }
/*    */ 
/*    */   public String getRole(UserEntity entity) throws Exception
/*    */   {
/* 52 */     this.logger.debug("开始获取角色");
/* 53 */     DefaultHttpClient httpClient = HttpUtils.getHttpClient();
/* 54 */     httpClient.setCookieStore(this.cs);
/* 55 */     HashMap map = Maps.newHashMap();
/*    */ 
/* 57 */     map.put("type", "0");
/* 58 */     Result result = HttpUtils.post(httpClient, entity.getRoleUrl(), map);
/* 59 */     this.cs = httpClient.getCookieStore();
/*    */ 
/* 62 */     String[] accounts = result.getBody().split("[@]");
/*    */ 
/* 64 */     String[][] allStr = new String[accounts.length][];
/* 65 */     for (int i = 0; i < accounts.length; i++) {
/* 66 */       allStr[i] = accounts[i].split("[*]");
/*    */     }
/* 68 */     int nengli = 0;
/* 69 */     int index = 0;
/* 70 */     for (int j = 0; j < accounts.length; j++) {
/* 71 */       if (Integer.parseInt(allStr[j][2]) > nengli) {
/* 72 */         index = j;
/* 73 */         nengli = Integer.parseInt(allStr[j][2]);
/*    */       }
/*    */     }
/* 76 */     String[] splitStr = allStr[index];
/*    */ 
/* 78 */     this.accountName = splitStr[1];
/* 79 */     this.accountID = splitStr[(splitStr.length - 1)];
/* 80 */     this.logger.debug("当前账号:{},当前ID:{}", splitStr[1], splitStr[(splitStr.length - 1)]);
/*    */ 
/* 82 */     map = Maps.newHashMap();
/* 83 */     map.put("type", "2");
/* 84 */     map.put("ai", this.accountID);
/* 85 */     result = HttpUtils.post(httpClient, entity.getRoleUrl(), map);
/* 86 */     this.cs = httpClient.getCookieStore();
/*    */ 
/* 88 */     HttpUtils.get(httpClient, entity.getMainUrl());
/* 89 */     this.cs = httpClient.getCookieStore();
/*    */ 
/* 91 */     httpClient.getConnectionManager().shutdown();
/* 92 */     return null;
/*    */   }
/*    */ 
/*    */   public Result getRole2(UserEntity entity, String getUrl) throws Exception
/*    */   {
/* 97 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.service.impl.XdgameUserServiceImpl
 * JD-Core Version:    0.6.0
 */