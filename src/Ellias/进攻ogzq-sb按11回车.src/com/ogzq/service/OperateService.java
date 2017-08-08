/*     */ package com.ogzq.service;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.ogzq.entity.Result;
/*     */ import com.ogzq.entity.UserEntity;
/*     */ import com.ogzq.util.HttpUtils;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.http.client.ClientProtocolException;
/*     */ import org.apache.http.client.CookieStore;
/*     */ import org.apache.http.conn.ClientConnectionManager;
/*     */ import org.apache.http.impl.client.DefaultHttpClient;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class OperateService
/*     */ {
/*  23 */   protected Logger logger = LoggerFactory.getLogger(getClass());
/*     */ 
/*  25 */   public CookieStore cs = null;
/*     */   public UserEntity entity;
/*     */ 
/*     */   public void setCookieStore(CookieStore cs)
/*     */   {
/*  29 */     this.cs = cs;
/*     */   }
/*     */ 
/*     */   public String onlyPost(String input_url, String[] strs) throws ClientProtocolException, IOException {
/*  33 */     DefaultHttpClient httpClient = HttpUtils.getHttpClient();
/*  34 */     httpClient.setCookieStore(this.cs);
/*  35 */     Result result = null;
/*  36 */     HashMap map = null;
/*  37 */     map = Maps.newHashMap();
/*  38 */     String return_str = "";
/*     */ 
/*  40 */     if (StringUtils.isBlank(input_url)) {
/*  41 */       return "输入错误";
/*     */     }
/*  43 */     for (String str : strs) {
/*  44 */       if (StringUtils.isNotBlank(str)) {
/*  45 */         String[] temp_strs = str.split("[=]");
/*  46 */         if (temp_strs.length != 2)
/*  47 */           System.out.println("参数错误：" + str);
/*     */         else
/*  49 */           map.put(temp_strs[0], temp_strs[1]);
/*     */       }
/*     */       else {
/*  52 */         System.out.println("有空参数，请确认是否有错误");
/*     */       }
/*     */     }
/*  55 */     result = HttpUtils.post(httpClient, input_url, map);
/*  56 */     return_str = result.getBody();
/*  57 */     httpClient.getConnectionManager().shutdown();
/*  58 */     return return_str;
/*     */   }
/*     */ 
/*     */   public void post(String input_url, String[] strs)
/*     */     throws ClientProtocolException, IOException
/*     */   {
/*  64 */     DefaultHttpClient httpClient = HttpUtils.getHttpClient();
/*  65 */     httpClient.setCookieStore(this.cs);
/*  66 */     String current_url = "";
/*  67 */     Result result = null;
/*  68 */     HashMap map = null;
/*  69 */     if (StringUtils.isNotBlank(input_url)) {
/*  70 */       current_url = input_url;
/*     */     }
/*  72 */     HashMap temp_map = Maps.newHashMap();
/*  73 */     for (String str : strs) {
/*  74 */       if (StringUtils.isNotBlank(str)) {
/*  75 */         String[] temp_strs = str.split("[=]");
/*  76 */         if (temp_strs.length != 2)
/*  77 */           System.out.println("参数错误：" + str);
/*     */         else
/*  79 */           temp_map.put(temp_strs[0], temp_strs[1]);
/*     */       }
/*     */       else {
/*  82 */         System.out.println("有空参数，请确认是否有错误");
/*     */       }
/*     */     }
/*  85 */     System.out.println("开始post请求");
/*     */     while (true) {
/*  87 */       map = Maps.newHashMap(temp_map);
/*  88 */       String url = "";
/*  89 */       String value = "";
/*  90 */       String[] values = null;
/*  91 */       System.out.print("请输入URL,q退出程序，回车用上一次或传入url:");
/*  92 */       BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "GBK"));
/*  93 */       url = in.readLine();
/*     */ 
/*  95 */       if (StringUtils.isNotBlank(url)) {
/*  96 */         if (url.trim().toLowerCase().equals("q")) {
/*     */           break;
/*     */         }
/*  99 */         current_url = url;
/*     */       }
/*     */       while (true)
/*     */       {
/* 103 */         System.out.print("请输入参数，以=分割，q退出输入参数:");
/* 104 */         value = in.readLine();
/* 105 */         if (StringUtils.isNotBlank(value)) {
/* 106 */           if (value.trim().toLowerCase().equals("q")) {
/*     */             break;
/*     */           }
/* 109 */           values = value.split("[=]");
/* 110 */           if (values.length != 2) {
/*     */             continue;
/*     */           }
/* 113 */           map.put(values[0], values[1]);
/*     */         }
/*     */       }
/* 116 */       result = HttpUtils.post(httpClient, current_url, map);
/* 117 */       System.out.println(result.getBody());
/*     */     }
/* 119 */     httpClient.getConnectionManager().shutdown();
/*     */   }
/*     */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.service.OperateService
 * JD-Core Version:    0.6.0
 */