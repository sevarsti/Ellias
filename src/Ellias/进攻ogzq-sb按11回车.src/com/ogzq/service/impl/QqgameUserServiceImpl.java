/*     */ package com.ogzq.service.impl;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.ogzq.entity.Result;
/*     */ import com.ogzq.entity.UserEntity;
/*     */ import com.ogzq.util.HttpUtils;
/*     */ import java.util.HashMap;
/*     */ import org.apache.http.conn.ClientConnectionManager;
/*     */ import org.apache.http.impl.client.DefaultHttpClient;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class QqgameUserServiceImpl extends BaseUserService
/*     */ {
/*  16 */   protected Logger logger = LoggerFactory.getLogger(getClass());
/*     */ 
/*     */   public Result login(UserEntity entity) throws Exception
/*     */   {
/*  20 */     DefaultHttpClient httpClient = HttpUtils.getHttpClient();
/*  21 */     HashMap map = Maps.newHashMap();
/*     */ 
/*  23 */     map.put("Login_1", entity.getUserNmae() + "*" + entity.getPassword());
/*  24 */     Result result = HttpUtils.post(httpClient, entity.getLoginUrl(), map);
/*  25 */     this.cs = httpClient.getCookieStore();
/*     */ 
/*  27 */     this.logger.debug("登录返回信息:{}", result.getBody());
/*     */ 
/*  29 */     String url = "";
/*  30 */     if (entity.getServer().equals("qqgame"))
/*  31 */       url = "http://ogzq.xdgame.cn/Transition.aspx?S=" + entity.getNum() + "&TP=QQ";
/*     */     else {
/*  33 */       url = "http://ogzq.xdgame.cn/Transition.aspx?S=" + entity.getNum();
/*     */     }
/*     */ 
/*  37 */     String str = HttpUtils.getLocation(httpClient, url);
/*  38 */     str = HttpUtils.getLocation(httpClient, str);
/*     */ 
/*  40 */     HttpUtils.getLocation(httpClient, str);
/*     */ 
/*  42 */     Thread.sleep(1000L);
/*  43 */     result = HttpUtils.get(httpClient, url);
/*     */ 
/*  45 */     this.logger.debug("开始获取角色");
/*  46 */     map = Maps.newHashMap();
/*     */ 
/*  48 */     map.put("type", "0");
/*  49 */     result = HttpUtils.post(httpClient, entity.getRoleUrl(), map);
/*     */ 
/*  52 */     this.cs = httpClient.getCookieStore();
/*     */ 
/*  78 */     this.cs = httpClient.getCookieStore();
/*     */ 
/*  80 */     String[] accounts = result.getBody().split("[@]");
/*     */ 
/*  82 */     String[][] allStr = new String[accounts.length][];
/*  83 */     for (int i = 0; i < accounts.length; i++) {
/*  84 */       allStr[i] = accounts[i].split("[*]");
/*     */     }
/*  86 */     int nengli = 0;
/*  87 */     int index = 0;
/*  88 */     for (int j = 0; j < accounts.length; j++) {
/*  89 */       if (Integer.parseInt(allStr[j][2]) > nengli) {
/*  90 */         index = j;
/*  91 */         nengli = Integer.parseInt(allStr[j][2]);
/*     */       }
/*     */     }
/*  94 */     String[] splitStr = allStr[index];
/*     */ 
/*  96 */     this.accountName = splitStr[1];
/*  97 */     this.accountID = splitStr[(splitStr.length - 1)];
/*  98 */     this.logger.debug("当前账号:{},当前ID:{}", splitStr[1], splitStr[(splitStr.length - 1)]);
/*     */ 
/* 100 */     str = str + "&ai=" + this.accountID;
/* 101 */     result = HttpUtils.get(httpClient, str);
/*     */ 
/* 104 */     HttpUtils.get(httpClient, entity.getMainUrl());
/* 105 */     this.cs = httpClient.getCookieStore();
/*     */ 
/* 107 */     httpClient.getConnectionManager().shutdown();
/* 108 */     return result;
/*     */   }
/*     */ 
/*     */   public String getRole(UserEntity entity)
/*     */     throws Exception
/*     */   {
/* 153 */     return null;
/*     */   }
/*     */ 
/*     */   public Result getRole2(UserEntity entity, String getUrl) throws Exception
/*     */   {
/* 158 */     return null;
/*     */   }
/*     */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.service.impl.QqgameUserServiceImpl
 * JD-Core Version:    0.6.0
 */