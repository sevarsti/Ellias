/*     */ package com.ogzq.service;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.ogzq.entity.UserEntity;
/*     */ import com.ogzq.util.IniEditor;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class MainApp
/*     */ {
/*  19 */   protected Logger logger = LoggerFactory.getLogger(getClass());
/*     */ 
/*     */   public List<UserEntity> getUser() throws IOException
/*     */   {
/*  23 */     IniEditor editor = new IniEditor();
/*  24 */     editor.load(getClass().getClassLoader().getResourceAsStream("user.ini"));
/*  25 */     List userList = Lists.newArrayList();
/*     */ 
/*  27 */     for (Iterator iter = editor.sectionNames().iterator(); iter.hasNext(); ) {
/*  28 */       String str = (String)iter.next();
/*  29 */       UserEntity entity = new UserEntity();
/*  30 */       entity.setUserNmae(editor.get(str, "username"));
/*  31 */       entity.setPassword(editor.get(str, "password"));
/*  32 */       entity.setServer(editor.get(str, "server"));
/*  33 */       String shilichaju = editor.get(str, "shilichaju");
/*  34 */       if (StringUtils.isBlank(shilichaju)) {
/*  35 */         entity.setShilichaju(0);
/*     */       } else {
/*  37 */         entity.setShilichaju(Integer.parseInt(shilichaju));
/*  38 */         System.out.println(entity.getShilichaju());
/*     */       }
/*  40 */       entity.setNum(Integer.parseInt(editor.get(str, "num").trim()));
/*  41 */       if (entity.getServer().equals("hupu")) {
/*  42 */         entity.setLoginUrl("http://passport.hupu.com/login?from_webgame_ogzq");
/*  43 */         entity.setBaseUrl("http://s" + entity.getNum() + ".ogzq.hupu.com/");
/*  44 */         entity.setRoleUrl("http://ogzq.hupu.com/?s=" + entity.getNum());
/*  45 */       } else if (entity.getServer().equals("xdgame")) {
/*  46 */         entity.setLoginUrl("http://ogzq.xdgame.cn/iframePage/Loginiframe.aspx");
/*  47 */         entity.setBaseUrl("http://f" + entity.getNum() + ".ogzq.xdgame.cn/");
/*  48 */         entity.setRoleUrl(entity.getBaseUrl() + "ChooseRole.aspx");
/*  49 */       } else if (entity.getServer().equals("qqgame")) {
/*  50 */         entity.setLoginUrl("http://qq.ogzq.xdgame.cn/iframePage/Loginiframe.aspx");
/*  51 */         entity.setBaseUrl("http://f" + entity.getNum() + ".ogzq.xdgame.cn/");
/*  52 */         entity.setRoleUrl(entity.getBaseUrl() + "ChooseRole.aspx");
/*  53 */       } else if (entity.getServer().equals("7k7k")) {
/*  54 */         int num = 14;
/*  55 */         if (entity.getNum() == 7)
/*  56 */           num = 14;
/*  57 */         else if (entity.getNum() == 7) {
/*  58 */           num = 16;
/*     */         }
/*  60 */         entity.setLoginUrl("http://web.7k7k.com/source/Post.php");
/*  61 */         entity.setBaseUrl("http://f" + num + ".ogzq.xdgame.cn/");
/*  62 */         entity.setRoleUrl(entity.getBaseUrl() + "ChooseRole.aspx");
/*     */       }
/*  64 */       entity.setMainUrl(entity.getBaseUrl() + "Default.aspx");
/*  65 */       if (str.startsWith("user")) {
/*  66 */         userList.add(entity);
/*     */       }
/*     */     }
/*     */ 
/*  70 */     return userList;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  75 */     Logger logger = LoggerFactory.getLogger("main");
/*  76 */     logger.info("程序开始运行");
/*     */ 
/*  79 */     List userList = Lists.newArrayList();
/*     */     try
/*     */     {
/*  82 */       MainApp mainApp = new MainApp();
/*  83 */       userList = mainApp.getUser();
/*     */     } catch (Exception e) {
/*  85 */       e.printStackTrace();
/*  86 */       System.exit(0);
/*     */     }
/*     */ 
/*  89 */     UserEntity[] entitys = new UserEntity[userList.size()];
/*  90 */     userList.toArray(entitys);
/*     */ 
/*  93 */     System.out.println("输入q退出程序");
/*     */ 
/*  95 */     System.out.println("选择具体功能----1:巅峰球会个人;2:生涯闯关;3:巅峰拜仁");
/*  96 */     System.out.println("4:大折扣;5:画龙鱼;6:冠军训练赛;7:联盟争夺战-防守");
/*  97 */     System.out.println(";8:银币鼓舞;9:世界杯32强;10:老兵传奇;11:联盟争夺战-进攻");
/*  98 */     System.out.print("请输入:");
/*  99 */     String code = "0";
/* 100 */     String acode = "0";
/*     */     try {
/* 102 */       BufferedReader bin = new BufferedReader(new InputStreamReader(System.in, "GBK"));
/* 103 */       code = bin.readLine().trim();
/* 104 */       if (code.equals("q")) {
/* 105 */         logger.debug("SB,退出就去屎");
/* 106 */         System.exit(0);
/* 107 */       } else if ((code.equals("1")) || (code.equals("2"))) {
/* 108 */         System.out.println("选择球员----1:里杰卡尔德;2:古力特;3:范巴斯滕;4:布雷默;5:马特乌斯;6:克林斯曼");
/* 109 */         System.out.print("请输入:");
/* 110 */         bin = new BufferedReader(new InputStreamReader(System.in, "GBK"));
/* 111 */         acode = bin.readLine().trim();
/* 112 */       } else if (code.equals("4")) {
/* 113 */         System.out.println("选择二级功能----1:免费10次;2:全部翻光");
/* 114 */         System.out.print("请输入:");
/* 115 */         bin = new BufferedReader(new InputStreamReader(System.in, "GBK"));
/* 116 */         acode = bin.readLine().trim();
/*     */       }
/* 118 */       if (!StringUtils.isNumeric(code)) {
/* 119 */         logger.debug("输入数字啊，SB");
/* 120 */         System.exit(0);
/*     */       }
/* 122 */       if (!StringUtils.isNumeric(acode)) {
/* 123 */         logger.debug("输入数字啊，SB");
/* 124 */         System.exit(0);
/*     */       }
/*     */ 
/* 127 */       com.ogzq.util.Constants.methodCode = Integer.parseInt(code);
/* 128 */       com.ogzq.util.Constants.aCode = Integer.parseInt(acode);
/*     */     }
/*     */     catch (Exception e) {
/* 131 */       e.printStackTrace();
/* 132 */       return;
/*     */     }
/*     */ 
/* 136 */     UserThreadService[] uts = new UserThreadService[entitys.length];
/* 137 */     for (int i = 0; i < entitys.length; i++) {
/* 138 */       uts[i] = new UserThreadService(i, entitys[i]);
/*     */     }
/* 140 */     for (int i = 0; i < uts.length; i++)
/* 141 */       uts[i].start();
/*     */   }
/*     */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.service.MainApp
 * JD-Core Version:    0.6.0
 */