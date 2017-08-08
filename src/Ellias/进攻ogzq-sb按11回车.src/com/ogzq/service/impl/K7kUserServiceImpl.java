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
/*    */ public class K7kUserServiceImpl extends BaseUserService
/*    */ {
/* 16 */   protected Logger logger = LoggerFactory.getLogger(getClass());
/*    */ 
/*    */   public Result login(UserEntity entity) throws Exception
/*    */   {
/* 20 */     DefaultHttpClient httpClient = new DefaultHttpClient();
/* 21 */     HashMap map = Maps.newHashMap();
/*    */ 
/* 23 */     map.put("ch", "1");
/* 24 */     map.put("action", "login");
/* 25 */     map.put("username", entity.getUserNmae());
/* 26 */     map.put("password", entity.getPassword());
/* 27 */     map.put("from", "2079901");
/* 28 */     map.put("ran", "0.602663550991565");
/* 29 */     Result result = HttpUtils.post(httpClient, entity.getLoginUrl(), map);
/* 30 */     this.cs = httpClient.getCookieStore();
/*    */ 
/* 32 */     this.logger.debug("登录返回信息:{}", result.getBody());
/*    */ 
/* 36 */     result = HttpUtils.get(httpClient, "http://web.7k7k.com/games/togame.php?target=ogzq_83&server_id=" + entity.getNum());
/*    */ 
/* 38 */     this.cs = httpClient.getCookieStore();
/*    */ 
/* 40 */     httpClient.getConnectionManager().shutdown();
/* 41 */     return result;
/*    */   }
/*    */ 
/*    */   public String getRole(UserEntity entity) throws Exception
/*    */   {
/* 46 */     this.logger.debug("开始获取角色");
/* 47 */     DefaultHttpClient httpClient = new DefaultHttpClient();
/* 48 */     httpClient.setCookieStore(this.cs);
/* 49 */     HashMap map = Maps.newHashMap();
/*    */ 
/* 51 */     Result result = HttpUtils.get(httpClient, entity.getRoleUrl());
/* 52 */     this.cs = httpClient.getCookieStore();
/* 53 */     map.put("type", "0");
/* 54 */     result = HttpUtils.post(httpClient, entity.getRoleUrl(), map);
/* 55 */     this.cs = httpClient.getCookieStore();
/*    */ 
/* 59 */     String[] accounts = result.getBody().split("[@]");
/*    */ 
/* 61 */     String[][] allStr = new String[accounts.length][];
/* 62 */     for (int i = 0; i < accounts.length; i++) {
/* 63 */       allStr[i] = accounts[i].split("[*]");
/*    */     }
/* 65 */     int nengli = 0;
/* 66 */     int index = 0;
/* 67 */     for (int j = 0; j < accounts.length; j++) {
/* 68 */       if (Integer.parseInt(allStr[j][2]) > nengli) {
/* 69 */         index = j;
/* 70 */         nengli = Integer.parseInt(allStr[j][2]);
/*    */       }
/*    */     }
/* 73 */     String[] splitStr = allStr[index];
/*    */ 
/* 75 */     this.accountName = splitStr[1];
/* 76 */     this.accountID = splitStr[(splitStr.length - 1)];
/* 77 */     this.logger.debug("当前账号:{},当前ID:{}", splitStr[1], splitStr[(splitStr.length - 1)]);
/*    */ 
/* 79 */     map = Maps.newHashMap();
/* 80 */     map.put("type", "2");
/* 81 */     map.put("ai", this.accountID);
/* 82 */     result = HttpUtils.post(httpClient, entity.getRoleUrl(), map);
/* 83 */     this.cs = httpClient.getCookieStore();
/*    */ 
/* 85 */     HttpUtils.get(httpClient, entity.getMainUrl());
/* 86 */     this.cs = httpClient.getCookieStore();
/*    */ 
/* 88 */     httpClient.getConnectionManager().shutdown();
/* 89 */     return null;
/*    */   }
/*    */ 
/*    */   public Result getRole2(UserEntity entity, String getUrl)
/*    */     throws Exception
/*    */   {
/* 95 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.service.impl.K7kUserServiceImpl
 * JD-Core Version:    0.6.0
 */