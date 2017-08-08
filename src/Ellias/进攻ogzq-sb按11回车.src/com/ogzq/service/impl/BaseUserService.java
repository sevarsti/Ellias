/*    */ package com.ogzq.service.impl;
/*    */ 
/*    */ import com.ogzq.service.UserService;
/*    */ import org.apache.http.client.CookieStore;
/*    */ 
/*    */ public abstract class BaseUserService
/*    */   implements UserService
/*    */ {
/*  9 */   public CookieStore cs = null;
/* 10 */   public String account = "";
/* 11 */   public String accountName = "";
/* 12 */   public String accountID = "";
/* 13 */   public String accountSign = "";
/*    */ 
/*    */   public CookieStore getCookieStore() {
/* 16 */     return this.cs;
/*    */   }
/*    */ 
/*    */   public String getAccount() {
/* 20 */     return this.account;
/*    */   }
/*    */ 
/*    */   public String getAccountName() {
/* 24 */     return this.accountName;
/*    */   }
/*    */ 
/*    */   public String getAccountID() {
/* 28 */     return this.accountID;
/*    */   }
/*    */ 
/*    */   public String getAccountSign() {
/* 32 */     return this.accountSign;
/*    */   }
/*    */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.service.impl.BaseUserService
 * JD-Core Version:    0.6.0
 */