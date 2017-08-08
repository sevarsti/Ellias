/*     */ package com.ogzq.service.impl;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.ogzq.entity.Result;
/*     */ import com.ogzq.entity.UserEntity;
/*     */ import com.ogzq.util.HttpUtils;
/*     */ import com.ogzq.util.IFrameTag;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import org.apache.http.client.ClientProtocolException;
/*     */ import org.apache.http.conn.ClientConnectionManager;
/*     */ import org.apache.http.impl.client.DefaultHttpClient;
/*     */ import org.htmlparser.Node;
/*     */ import org.htmlparser.NodeFilter;
/*     */ import org.htmlparser.Parser;
/*     */ import org.htmlparser.PrototypicalNodeFactory;
/*     */ import org.htmlparser.filters.TagNameFilter;
/*     */ import org.htmlparser.nodes.TagNode;
/*     */ import org.htmlparser.util.NodeList;
/*     */ import org.htmlparser.util.ParserException;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class HupuUserServiceImpl extends BaseUserService
/*     */ {
/*  26 */   protected Logger logger = LoggerFactory.getLogger(getClass());
/*     */ 
/*     */   public Result login(UserEntity entity) throws ClientProtocolException, IOException {
/*  29 */     DefaultHttpClient httpClient = HttpUtils.getHttpClient();
/*  30 */     HashMap map = Maps.newHashMap();
/*     */ 
/*  32 */     map.put("username", entity.getUserNmae());
/*  33 */     map.put("password", entity.getPassword());
/*  34 */     Result result = HttpUtils.post(httpClient, entity.getLoginUrl(), map);
/*  35 */     this.cs = httpClient.getCookieStore();
/*  36 */     httpClient.getConnectionManager().shutdown();
/*  37 */     return result;
/*     */   }
/*     */ 
/*     */   public String getRole(UserEntity entity) throws ClientProtocolException, IOException, ParserException {
/*  41 */     DefaultHttpClient httpClient = HttpUtils.getHttpClient();
/*  42 */     httpClient.setCookieStore(this.cs);
/*     */ 
/*  45 */     Result result = HttpUtils.get(httpClient, entity.getRoleUrl());
/*  46 */     this.cs = httpClient.getCookieStore();
/*     */ 
/*  48 */     Parser parser = Parser.createParser(result.getBody(), "GBK");
/*  49 */     PrototypicalNodeFactory pnfPrototypicalNodeFactory = new PrototypicalNodeFactory();
/*  50 */     pnfPrototypicalNodeFactory.registerTag(new IFrameTag());
/*  51 */     parser.setNodeFactory(pnfPrototypicalNodeFactory);
/*  52 */     NodeFilter filter = new TagNameFilter("iframe");
/*  53 */     NodeList nodeList = parser.extractAllNodesThatMatch(filter);
/*  54 */     String getUrl = "";
/*  55 */     for (int i = 0; i < nodeList.size(); i++) {
/*  56 */       TagNode tagNode = new TagNode();
/*  57 */       tagNode.setText(nodeList.elementAt(i).toHtml());
/*  58 */       getUrl = tagNode.getAttribute("src");
/*     */     }
/*  60 */     this.cs = httpClient.getCookieStore();
/*  61 */     httpClient.getConnectionManager().shutdown();
/*  62 */     return getUrl;
/*     */   }
/*     */ 
/*     */   public Result getRole2(UserEntity entity, String getUrl) throws ClientProtocolException, IOException
/*     */   {
/*  67 */     DefaultHttpClient httpClient = HttpUtils.getHttpClient();
/*  68 */     Result result = null;
/*  69 */     httpClient.setCookieStore(this.cs);
/*     */ 
/*  72 */     result = HttpUtils.get(httpClient, getUrl);
/*     */ 
/*  74 */     this.cs = httpClient.getCookieStore();
/*  75 */     HashMap map = Maps.newHashMap();
/*  76 */     map = Maps.newHashMap();
/*  77 */     map.put("type", "0");
/*  78 */     result = HttpUtils.post(httpClient, entity.getBaseUrl() + "ChooseRole.aspx", map);
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
/*     */ 
/* 100 */     map = Maps.newHashMap();
/* 101 */     map.put("type", "2");
/* 102 */     map.put("ai", this.accountID);
/* 103 */     result = HttpUtils.post(httpClient, entity.getBaseUrl() + "ChooseRole.aspx", map);
/*     */ 
/* 105 */     HttpUtils.get(httpClient, entity.getMainUrl());
/* 106 */     this.cs = httpClient.getCookieStore();
/* 107 */     httpClient.getConnectionManager().shutdown();
/* 108 */     return result;
/*     */   }
/*     */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.service.impl.HupuUserServiceImpl
 * JD-Core Version:    0.6.0
 */