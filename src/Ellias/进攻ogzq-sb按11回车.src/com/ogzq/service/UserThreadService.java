/*    */ package com.ogzq.service;
/*    */ 
/*    */ import com.ogzq.entity.UserEntity;
/*    */ import com.ogzq.service.impl.HupuUserServiceImpl;
/*    */ import com.ogzq.service.impl.K7kUserServiceImpl;
/*    */ import com.ogzq.service.impl.QqgameUserServiceImpl;
/*    */ import com.ogzq.service.impl.XdgameUserServiceImpl;
/*    */ import com.ogzq.util.Constants;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class UserThreadService extends Thread
/*    */ {
/*    */   private UserEntity entity;
/*    */   private String threadNum;
/*    */   private UserService userService;
/*    */   private OperateService operateService;
/*    */   private TempService tempService;
/* 21 */   protected Logger logger = LoggerFactory.getLogger(getClass());
/*    */ 
/*    */   public UserThreadService(int num, UserEntity entity) {
/* 24 */     this.entity = entity;
/* 25 */     this.threadNum = ("账号" + num);
/*    */   }
/*    */ 
/*    */   public void run() {
/* 29 */     getUserService();
/* 30 */     this.operateService = new OperateService();
/* 31 */     this.tempService = new TempService(this.userService, this.operateService, this.entity);
/*    */     try {
/* 33 */       login();
/*    */     } catch (Exception e) {
/* 35 */       this.logger.error("{} 登陆失败", this.threadNum);
/* 36 */       e.printStackTrace();
/* 37 */       return;
/*    */     }
/* 39 */     switch (Constants.methodCode) {
/*    */     case 1:
/* 41 */       this.tempService.person(Constants.aCode); break;
/*    */     case 2:
/* 42 */       this.tempService.challenge(Constants.aCode); break;
/*    */     case 3:
/* 43 */       this.tempService.bairen(); break;
/*    */     case 4:
/* 44 */       this.tempService.dazhekou(Constants.aCode); break;
/*    */     case 6:
/* 46 */       this.tempService.gjxls(); break;
/*    */     case 7:
/* 47 */       this.tempService.oglm(this.entity); break;
/*    */     case 8:
/* 48 */       this.tempService.guwu(); break;
/*    */     case 9:
/* 49 */       this.tempService.worldcup32(); break;
/*    */     case 10:
/* 50 */       this.tempService.laobingchuanqi(); break;
/*    */     case 11:
/* 51 */       this.tempService.oglmattack(); break;
/*    */     case 5:
/*    */     default:
/*    */       try { this.operateService.post("", new String[0]);
/*    */       } catch (Exception e) {
/* 56 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public void login()
/*    */     throws Exception
/*    */   {
/* 77 */     for (int i = 0; i < 3; )
/*    */       try
/*    */       {
/* 80 */         this.userService.login(this.entity);
/*    */ 
/* 82 */         String getUrl = this.userService.getRole(this.entity);
/* 83 */         this.userService.getRole2(this.entity, getUrl);
/* 84 */         this.operateService.setCookieStore(this.userService.getCookieStore());
/*    */ 
/* 86 */         this.logger.debug("{}角色:{}", this.threadNum, this.operateService.onlyPost(this.entity.getBaseUrl() + "Default.aspx", new String[] { "type=7" }));
/*    */       }
/*    */       catch (Exception e) {
/* 89 */         this.logger.error("登陆错误:{}", e.getMessage());
/* 90 */         Thread.sleep(2000L);
/* 91 */         e.printStackTrace();
/*    */ 
/* 77 */         i++;
/*    */       }
/*    */   }
/*    */ 
/*    */   public void getUserService()
/*    */   {
/* 97 */     if (this.entity.getServer().equals("hupu"))
/* 98 */       this.userService = new HupuUserServiceImpl();
/* 99 */     else if (this.entity.getServer().equals("xdgame"))
/* 100 */       this.userService = new XdgameUserServiceImpl();
/* 101 */     else if (this.entity.getServer().equals("qqgame"))
/* 102 */       this.userService = new QqgameUserServiceImpl();
/* 103 */     else if (this.entity.getServer().equals("7k7k"))
/* 104 */       this.userService = new K7kUserServiceImpl();
/*    */   }
/*    */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.service.UserThreadService
 * JD-Core Version:    0.6.0
 */