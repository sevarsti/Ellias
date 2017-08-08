/*     */ package com.ogzq.service;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.ogzq.entity.UserEntity;
/*     */ import com.ogzq.util.Constants;
/*     */ import com.ogzq.util.HttpUtils;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.http.conn.ClientConnectionManager;
/*     */ import org.apache.http.impl.client.DefaultHttpClient;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class TempService
/*     */ {
/*  26 */   protected Logger logger = LoggerFactory.getLogger(getClass());
/*     */   public OperateService operateService;
/*     */   public UserService userService;
/*     */   public UserEntity entity;
/*     */ 
/*     */   public TempService(UserService userService, OperateService operateService, UserEntity entity)
/*     */   {
/*  32 */     this.operateService = operateService;
/*  33 */     this.userService = userService;
/*  34 */     this.entity = entity;
/*     */   }
/*     */ 
/*     */   public void laobingchuanqi()
/*     */   {
/*  39 */     String result = "";
/*     */     while (true)
/*     */     {
/*     */       try
/*     */       {
/*  45 */         result = this.operateService.onlyPost(this.entity.getBaseUrl() + "LaoBingChuanQi.aspx", new String[] { "LoadTab01=1" });
/*  46 */         this.logger.debug(result);
/*  47 */         String[] list = result.split("[@]");
/*  48 */         int coin = Integer.parseInt(list[0]);
/*  49 */         int time = Integer.parseInt(list[2]);
/*  50 */         if (coin == 0) {
/*  51 */           this.logger.debug("五次已经完了，退出");
/*  52 */           break;
/*     */         }
/*  54 */         if (time == 0) {
/*  55 */           result = this.operateService.onlyPost(this.entity.getBaseUrl() + "LaoBingChuanQi.aspx", new String[] { "zhaoHuan=1", "mode=1" });
/*  56 */           this.logger.debug(result);
/*     */         }
/*  58 */         Thread.sleep(9000L);
/*  59 */         continue; } catch (Exception e) {
/*  60 */         e.printStackTrace();
/*  61 */         this.logger.error(e.getMessage());
/*     */         try {
/*  63 */           Thread.sleep(2000L); } catch (Exception localException1) {
/*     */         }
/*     */       }
/*     */     }int time;
/*     */     int coin;
/*     */     String[] list;
/*     */   }
/*  71 */   public void guwu() { String result = "";
/*     */     while (true)
/*     */     {
/*     */       try
/*     */       {
/*  78 */         Calendar now = Calendar.getInstance();
/*     */ 
/*  80 */         int hours = now.get(11);
/*  81 */         int minutes = now.get(12);
/*  82 */         boolean flag = false;
/*  83 */         switch (hours) {
/*     */         case 5:
/*  85 */           if (minutes < 55) continue; flag = true; break;
/*     */         case 6:
/*  87 */           flag = true; break;
/*     */         case 7:
/*  89 */           if (minutes > 30) continue; flag = true; break;
/*     */         case 11:
/*  91 */           if (minutes < 55) continue; flag = true; break;
/*     */         case 12:
/*  93 */           flag = true; break;
/*     */         case 13:
/*  95 */           if (minutes > 30) continue; flag = true; break;
/*     */         case 8:
/*     */         case 9:
/*     */         case 10:
/*     */         }
/*     */ 
/* 102 */         if (!flag) {
/* 103 */           this.logger.debug("hour:{},minute:{}", Integer.valueOf(hours), Integer.valueOf(minutes));
/* 104 */           this.logger.debug("不在鼓舞时间内");
/* 105 */           Thread.sleep(10000L);
/* 106 */           continue;
/*     */         }
/*     */ 
/* 109 */         result = this.operateService.onlyPost(this.entity.getBaseUrl() + "Tactical.aspx", new String[] { "type=13" });
/*     */ 
/* 111 */         String[] arr0 = result.split("[&]");
/* 112 */         String[] arr1 = arr0[0].split("[@]");
/* 113 */         String[] arr2 = arr1[1].split("[*]");
/* 114 */         String coach = arr2[0];
/* 115 */         String guwu = arr2[2];
/* 116 */         if (guwu.trim().equals("-1")) {
/* 117 */           this.logger.debug("没鼓舞");
/* 118 */           int i = 0; continue;
/* 119 */           result = this.operateService.onlyPost(this.entity.getBaseUrl() + "Coach/Coach.aspx", new String[] { "TrainManPower_DO1=2*" + coach });
/* 120 */           this.logger.debug("鼓舞第{}次,结果为{}", Integer.valueOf(i), result);
/* 121 */           Thread.sleep(500L);
/*     */ 
/* 118 */           i++; if (i < 9)
/*     */           {
/*     */             continue;
/*     */           }
/*     */ 
/* 123 */           result = this.operateService.onlyPost(this.entity.getBaseUrl() + "Coach/Coach.aspx", new String[] { "TrainManPower_DO_Sure1=1" });
/*     */         } else {
/* 125 */           this.logger.debug("鼓舞剩余时间:{}", guwu);
/*     */         }
/* 127 */         Thread.sleep(10000L);
/*     */ 
/* 129 */         continue; } catch (Exception e) {
/* 130 */         this.logger.error("鼓舞出问题：{}", e.getMessage());
/* 131 */         e.printStackTrace();
/*     */         try {
/* 133 */           Thread.sleep(5000L);
/*     */         }
/*     */         catch (Exception localException1)
/*     */         {
/*     */         }
/*     */       }
/*     */     } }
/*     */ 
/*     */   public void oglmattack() {
/* 142 */     String return_str = "";
/*     */     while (true)
/*     */     {
/*     */       try
/*     */       {
/* 150 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "OGLM.aspx", new String[] { "Limzdz1=-1" });
/* 151 */         if (return_str.indexOf("inmatch|") != -1) {
/* 152 */           this.logger.debug("比赛中,等5秒重试");
/* 153 */           Thread.sleep(5000L);
/* 154 */           continue;
/*     */         }
/* 156 */         String[] list = return_str.split("@");
/* 157 */         String str = list[3];
/* 158 */         this.logger.debug(str);
/* 159 */         if (StringUtils.isNotBlank(str)) {
/* 160 */           String[] clublist = str.split("[*]");
/* 161 */           break;
/*     */         }
/* 163 */         Thread.sleep(5000L);
/* 164 */         continue; } catch (Exception e) {
/* 165 */         e.printStackTrace();
/* 166 */         this.logger.error(e.getMessage()); }  } String[] clublist;
/*     */     String str;
/*     */     String[] list;
/*     */     try { Thread.sleep(2000L);
/*     */     } catch (Exception localException1) {
/*     */     }
/*     */     while (true) try {
/* 174 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "OGLM.aspx", new String[] { "Limzdz1=-1" });
/* 175 */         String[] list3 = return_str.split("@");
/* 176 */         if (Integer.parseInt(list3[6]) > 10) {
/* 177 */           this.logger.debug("进攻未到时间:{},休息3秒", list3[6]);
/* 178 */           Thread.sleep(3000L);
/* 179 */           continue;
/*     */         }
/*     */ 
/* 183 */         int i = 0; continue;
/* 184 */         list = clublist[i].split("[|]");
/*     */ 
/* 186 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "OGLM.aspx", new String[] { "Send1=getSJ*SJ" });
/* 187 */         Thread.sleep(500L);
/*     */ 
/* 190 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "OGLM.aspx", new String[] { "Limzdz1=" + list[0] });
/* 191 */         this.logger.debug(list[0] + "--" + return_str);
/* 192 */         if (return_str.indexOf("inmatch|") != -1) {
/* 193 */           Thread.sleep(2000L);
/*     */         }
/*     */         else {
/* 196 */           if (StringUtils.isBlank(return_str)) {
/* 197 */             this.logger.debug("读取错误-1");
/*     */           }
/*     */           else
/*     */           {
/* 202 */             str = return_str.split("[@]")[4];
/* 203 */             if (str.equals("-1"))
/*     */             {
/*     */               continue;
/*     */             }
/* 207 */             String[] kengList = str.split("[*]");
/*     */ 
/* 209 */             int[] ilist = { 1, 3, 5, 7, 0, 2, 6, 8 };
/*     */             int[] arrayOfInt1;
/* 210 */             int j = (arrayOfInt1 = ilist).length; int i = 0; continue; int j = arrayOfInt1[i];
/*     */ 
/* 213 */             String[] list2 = kengList[j].split("[&]");
/*     */ 
/* 215 */             if (list2[0].equals("1")) {
/* 216 */               return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "OGLM.aspx", new String[] { "Qianzhan1=" + j + "*" + list[0] });
/*     */ 
/* 218 */               this.logger.debug("Qianzhan1=" + j + "*" + list[0]);
/* 219 */               this.logger.debug("kimzs--{}---{}", list[0], kengList[j]);
/*     */             }
/*     */             else
/*     */             {
/* 210 */               i++; if (i < j)
/*     */               {
/*     */                 continue;
/*     */               }
/*     */ 
/*     */             }
/*     */ 
/* 228 */             Thread.sleep(500L);
/*     */           }
/* 183 */           i++; if (i < 1)
/*     */           {
/*     */             continue;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 230 */         Thread.sleep(1000L);
/* 231 */         continue; } catch (Exception e) {
/* 232 */         e.printStackTrace();
/* 233 */         this.logger.error(e.getMessage());
/*     */         try {
/* 235 */           Thread.sleep(3000L);
/*     */         } catch (Exception ex) {
/* 237 */           this.logger.error(ex.getMessage());
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public void oglm(UserEntity entity) /* defend */
/*     */   {
/* 245 */     String return_str = "";
/*     */ 
/* 248 */     int shili = 0;
/*     */     try {
/* 250 */       return_str = this.operateService.onlyPost(entity.getBaseUrl() + "OGLM.aspx", new String[] { "LoadComprehensive1=1" });
/* 251 */       this.logger.debug("联盟信息:{}", return_str);
/*     */     } catch (Exception e) {
/* 253 */       e.printStackTrace();
/*     */     }
/*     */     while (true)
/*     */       try {
/* 257 */         return_str = this.operateService.onlyPost(entity.getBaseUrl() + "Default.aspx", new String[] { "type=7" });
/*     */ 
/* 259 */         String[] list = return_str.split("[|]");
/* 260 */         shili = Integer.parseInt(list[7]);
/* 261 */         this.logger.debug("个人实力:{}", Integer.valueOf(shili));
/*     */ 
/* 263 */         return_str = this.operateService.onlyPost(entity.getBaseUrl() + "OGLM.aspx", new String[] { "Limzdz1=-1" });
/* 264 */         if (return_str.indexOf("inmatch|") != -1) {
/* 265 */           this.logger.debug("比赛中,等5秒重试");
/* 266 */           Thread.sleep(5000L);
/* 267 */           continue;
/*     */         }
/* 269 */         list = return_str.split("@");
/* 270 */         if (Integer.parseInt(list[5]) > 0) {
/* 271 */           this.logger.debug("防守未到时间:{},休息3秒", list[5]);
/* 272 */           Thread.sleep(3000L);
/* 273 */           continue;
/*     */         }
/*     */ 
/* 276 */         String[] tzlist = list[4].split("[*]");
/* 277 */         int j = -1;
/*     */ 
/* 279 */         int[] ilist = { 4, 1, 3, 5, 7, 0, 2, 6, 8 };
/* 280 */         boolean flag = true;
/*     */         int[] arrayOfInt1;
/* 281 */         int j = (arrayOfInt1 = ilist).length; int i = 0; continue; int i = arrayOfInt1[i];
/* 282 */         list = tzlist[i].split("[&]");
/* 283 */         if (list[0].trim().equals("2")) {
/* 284 */           String[] list2 = list[1].split("[|]");
/*     */ 
/* 286 */           if ((shili + entity.getShilichaju() > Integer.parseInt(list2[2])) && (flag)) {
/* 287 */             return_str = this.operateService.onlyPost(entity.getBaseUrl() + "OGLM.aspx", new String[] { "GuardRoom1=" + j });
/* 288 */             this.logger.debug("找到球队:{}", list[1]);
/* 289 */             flag = false;
/*     */           }
/*     */         }
/* 281 */         i++; if (i < j)
/*     */         {
/*     */           continue;
/*     */         }
/*     */ 
/* 294 */         this.logger.debug("休息3秒");
/* 295 */         Thread.sleep(3000L);
/*     */ 
/* 297 */         continue; } catch (Exception e) {
/* 298 */         e.printStackTrace();
/* 299 */         this.logger.error(e.getMessage());
/*     */       }
/*     */   }
/*     */ 
/*     */   public void worldcup32()
/*     */   {
/* 305 */     String return_str = "";
/*     */ 
/* 307 */     String[] array2 = null;
/* 308 */     String[] array3 = null;
/*     */     while (true)
/*     */       try
/*     */       {
/* 312 */         int i = 0; continue;
/* 313 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "WorldCup32.aspx", new String[] { "Load1=" + i });
/* 314 */         this.logger.debug(return_str);
/* 315 */         if (!return_str.startsWith("inmatch"))
/*     */           continue;
/* 317 */         Thread.sleep(3000L);
/* 318 */         continue;
/*     */ 
/* 320 */         array2 = return_str.split("[#]");
/* 321 */         array3 = array2[0].split("[@]");
/* 322 */         if (array3.length != 4)
/*     */           continue;
/* 324 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "WorldCup32.aspx", new String[] { "FlopCard1=3" });
/* 325 */         this.logger.debug("翻牌结果:{}", return_str);
/* 326 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "WorldCup32.aspx", new String[] { "AcceptCardAward1=1" });
/* 327 */         this.logger.debug("翻牌结果:{}", return_str);
/* 328 */         Thread.sleep(3000L);
/* 329 */         continue;
/*     */ 
/* 332 */         String[] array = array3[2].split("[\\^]");
/*     */         String[] arrayOfString1;
/* 333 */         int j = (arrayOfString1 = array).length; int i = 0; continue; String str = arrayOfString1[i];
/* 334 */         array2 = str.split("[*]");
/* 335 */         if (Integer.parseInt(array2[3]) > 0) {
/* 336 */           return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "WorldCup32.aspx", new String[] { "Challenge1=" + array2[0] });
/* 337 */           this.logger.debug("开始比赛：{}", str);
/* 338 */           Thread.sleep(3000L);
/*     */         }
/*     */         else
/*     */         {
/* 333 */           i++; if (i < j)
/*     */           {
/*     */             continue;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 342 */         Thread.sleep(3000L);
/*     */ 
/* 312 */         i++; if (i < 4)
/*     */         {
/*     */           continue;
/*     */         }
/*     */ 
/* 345 */         continue; } catch (Exception e) {
/* 346 */         e.printStackTrace();
/* 347 */         this.logger.error(e.getMessage());
/*     */       }
/*     */   }
/*     */ 
/*     */   public void gjxls()
/*     */   {
/* 355 */     String return_str = "";
/*     */ 
/* 357 */     String[] array2 = null;
/* 358 */     String[] array3 = null;
/* 359 */     int num = 8;
/*     */     try
/*     */     {
/* 362 */       num = new Random().nextInt(9);
/* 363 */       this.logger.debug("签到数字：{}", Integer.valueOf(num));
/* 364 */       return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "LeagueSign.aspx", new String[] { "GuessNum=" + num });
/* 365 */       return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "LeagueSign.aspx", new String[] { "Load=1" });
/* 366 */       this.logger.debug("签到结果：{}", return_str);
/* 367 */       Thread.sleep(3000L);
/*     */       while (true) {
/* 369 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "Gjxls.aspx", new String[] { "GameLoad1=1" });
/* 370 */         this.logger.debug(return_str);
/* 371 */         if (return_str.startsWith("inmatch")) {
/* 372 */           Thread.sleep(3000L);
/* 373 */           continue;
/*     */         }
/* 375 */         String[] array = return_str.split("[@]");
/*     */ 
/* 377 */         if (StringUtils.isNumeric(array[0])) {
/* 378 */           if (array[1].equals("0")) {
/* 379 */             this.logger.debug("十场已经打完...");
/* 380 */             break;
/*     */           }
/* 382 */           array2 = array[8].split("[#]");
/*     */         }
/*     */ 
/* 385 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "Gjxls.aspx", new String[] { "GameLoad1=2" });
/* 386 */         this.logger.debug(return_str);
/* 387 */         if (return_str.startsWith("inmatch")) {
/* 388 */           Thread.sleep(3000L);
/* 389 */           continue;
/*     */         }
/* 391 */         array = return_str.split("[@]");
/* 392 */         if (StringUtils.isNumeric(array[0])) {
/* 393 */           if (array[1].equals("0")) {
/* 394 */             this.logger.debug("十场已经打完...");
/* 395 */             break;
/*     */           }
/* 397 */           array3 = array[8].split("[#]");
/*     */         }
/* 399 */         if ((array2 != null) || (array3 != null))
/*     */         {
/* 402 */           array = (String[])ArrayUtils.addAll(array2, array3);
/* 403 */           for (int i = array.length; i > 0; i--) {
/* 404 */             String str = array[(i - 1)];
/* 405 */             String[] arrs = str.split("[&]");
/* 406 */             if (arrs[(arrs.length - 1)].equals("1")) {
/* 407 */               this.logger.debug("开始打球队：{}", str);
/* 408 */               return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "Gjxls.aspx", new String[] { "BeginGame1=" + arrs[0] + "*2" });
/* 409 */               this.logger.debug(return_str);
/* 410 */               break;
/*     */             }
/*     */           }
/*     */         }
/* 414 */         Thread.sleep(3000L);
/*     */       }
/*     */       String[] array;
/* 417 */       this.logger.debug("10场打完,开始领奖励");
/* 418 */       return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "Gjxls.aspx", new String[] { "ShowjiangliBox1=1" });
/* 419 */       this.logger.debug(return_str);
/* 420 */       for (int i = 4; i >= 0; i--) {
/* 421 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "Gjxls.aspx", new String[] { "JiangliLq1=" + i });
/* 422 */         this.logger.debug(return_str);
/* 423 */         Thread.sleep(3000L);
/*     */       }
/*     */     } catch (Exception e) {
/* 426 */       e.printStackTrace();
/* 427 */       this.logger.error(e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void hualongyu()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void dazhekou(int acode)
/*     */   {
/* 471 */     if (acode == 0) return;
/* 472 */     String return_str = "";
/*     */ 
/* 475 */     String[] temp_array = { "30", "30", "30", "30" };
/*     */     try {
/* 477 */       for (int i = 0; i < 30; i++) {
/* 478 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "BigDiscount.aspx", new String[] { "type=1" });
/* 479 */         String[] array1 = return_str.split("[@]");
/* 480 */         this.logger.debug("剩余次数:{}，折扣卡：{}", array1[0], array1[1]);
/* 481 */         temp_array = array1[1].split("[|]");
/* 482 */         if (Integer.parseInt(array1[0]) <= 0) {
/* 483 */           return;
/*     */         }
/* 485 */         if ((Constants.aCode == 1) && (Integer.parseInt(array1[0]) <= 20)) {
/* 486 */           return;
/*     */         }
/* 488 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "BigDiscount.aspx", new String[] { "type=2" });
/* 489 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "BigDiscount.aspx", new String[] { "type=1" });
/* 490 */         array1 = return_str.split("[@]");
/* 491 */         String[] array2 = array1[1].split("[|]");
/* 492 */         if ((array2[0].equals(temp_array[0])) && (array2[1].equals(temp_array[1]))) {
/* 493 */           Thread.sleep(5123L);
/* 494 */           this.logger.debug("手气太臭，休息5秒");
/*     */         }
/*     */       }
/* 497 */       return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "BigDiscount.aspx", new String[] { "type=1" });
/* 498 */       String[] array1 = return_str.split("[@]");
/* 499 */       this.logger.debug("完成了，剩余次数:{}，折扣卡：{}", array1[0], array1[1]);
/*     */     }
/*     */     catch (Exception e) {
/* 502 */       e.printStackTrace();
/* 503 */       this.logger.error(e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void person(int acode) {
/* 508 */     String name = "";
/* 509 */     String return_str = "";
/*     */ 
/* 511 */     if ((acode > 6) || (acode < 1)) {
/* 512 */       this.logger.error("输入错误，请重新开始");
/*     */     }
/* 514 */     switch (acode) { case 1:
/* 515 */       name = "Rijkaard"; break;
/*     */     case 2:
/* 516 */       name = "Gullit"; break;
/*     */     case 3:
/* 517 */       name = "Basten"; break;
/*     */     case 4:
/* 518 */       name = "Bremer"; break;
/*     */     case 5:
/* 519 */       name = "Mateus"; break;
/*     */     case 6:
/* 520 */       name = "Klinsmann";
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 526 */       return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + name + "_Top_Club.aspx", new String[] { "type=3" });
/* 527 */       this.logger.debug("begin:{}", return_str);
/* 528 */       return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + name + "_Top_Club.aspx", new String[] { "type=1" });
/* 529 */       this.logger.debug("begin:{}", return_str);
/*     */ 
/* 531 */       return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + name + "_Top_Person.aspx", new String[] { "type=1" });
/* 532 */       this.logger.debug("begin:{}", return_str);
/* 533 */       return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + name + "_Top_Person.aspx", new String[] { "type=2" });
/* 534 */       this.logger.debug("begin:{}", return_str);
/* 535 */       Thread.sleep(3000L);
/*     */     } catch (Exception e) {
/* 537 */       e.printStackTrace();
/*     */     }
/*     */     while (true)
/*     */       try
/*     */       {
/* 542 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + name + "_Top_Club.aspx", new String[] { "type=1" });
/* 543 */         this.logger.debug("俱乐部检查时间:{}", return_str);
/* 544 */         if (return_str.substring(0, 1).equals("0")) {
/* 545 */           return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + name + "_Top_Club.aspx", new String[] { "type=2" });
/* 546 */           this.logger.debug("开始比赛：{}", return_str);
/*     */         }
/* 548 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + name + "_Top_Person.aspx", new String[] { "type=2" });
/* 549 */         this.logger.debug("个人检查时间:{}", return_str);
/* 550 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + name + "_Top_Person.aspx", new String[] { "type=5" });
/*     */ 
/* 552 */         Thread.sleep(5000L);
/* 553 */         continue; } catch (Exception e) {
/* 554 */         this.logger.error("异常：{}", e.getMessage());
/*     */       }
/*     */   }
/*     */ 
/*     */   public void challenge(int acode)
/*     */   {
/* 560 */     String name = "";
/* 561 */     String return_str = "";
/*     */ 
/* 564 */     if ((acode > 6) || (acode < 1)) {
/* 565 */       this.logger.error("输入错误，请重新开始");
/*     */     }
/* 567 */     switch (acode) { case 1:
/* 568 */       name = "Rijkaard"; break;
/*     */     case 2:
/* 569 */       name = "Gullit"; break;
/*     */     case 3:
/* 570 */       name = "Basten"; break;
/*     */     case 4:
/* 571 */       name = "Bremer"; break;
/*     */     case 5:
/* 572 */       name = "Mateus"; break;
/*     */     case 6:
/* 573 */       name = "Klinsmann";
/*     */     }
/*     */     try
/*     */     {
/* 577 */       return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + name + "_Challenge.aspx", new String[] { "load=1" });
/* 578 */       this.logger.debug("begin:{}", return_str);
/*     */ 
/* 580 */       Thread.sleep(3000L);
/*     */     } catch (Exception e) {
/* 582 */       e.printStackTrace();
/*     */     }
/*     */     while (true)
/*     */       try {
/* 586 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + name + "_Challenge.aspx", new String[] { "load=1" });
/* 587 */         this.logger.debug("begin:{}", return_str);
/* 588 */         String[] list = return_str.split("[#]");
/* 589 */         if (list[0].equals("0")) {
/* 590 */           list = list[1].split("[&]");
/* 591 */           if (list[1].equals("0")) {
/* 592 */             return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + name + "_Challenge.aspx", new String[] { "Challenge=1", "index=" + list[0] });
/* 593 */             this.logger.debug("开始挑战第{}只球队", list[0]);
/*     */           } else {
/* 595 */             this.logger.debug("sb们，比赛完成，手工领取球员去吧");
/* 596 */             break;
/*     */           }
/* 598 */         } else if (list[0].equals("1")) {
/* 599 */           return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + name + "_Challenge.aspx", new String[] { "TurnCard=1", "index=1" });
/* 600 */           this.logger.debug("领取奖励:{}", return_str);
/* 601 */           return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + name + "_Challenge.aspx", new String[] { "getgift=1" });
/*     */         }
/* 603 */         Thread.sleep(3000L);
/*     */ 
/* 605 */         continue; } catch (Exception e) {
/* 606 */         this.logger.error("异常：{}", e.getMessage());
/*     */       }
/*     */     String[] list;
/*     */   }
/*     */ 
/*     */   public void liyu() {
/* 613 */     String return_str = "";
/*     */     try
/*     */     {
/* 616 */       return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "Fish_Jump.aspx", new String[] { "type=1" });
/* 617 */       this.logger.debug("begin:{}", return_str);
/*     */     } catch (Exception e) {
/* 619 */       e.printStackTrace();
/*     */     }
/* 621 */     for (int i = 0; i < 500; i++)
/*     */       try {
/* 623 */         Thread.sleep(1000L);
/* 624 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "Fish_Jump.aspx", new String[] { "type=2", "index=3" });
/* 625 */         this.logger.debug("鲤鱼跳龙门:{}", return_str);
/* 626 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "Fish_Jump.aspx", new String[] { "type=1" });
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 630 */         this.logger.error("异常：{}", e.getMessage());
/*     */       }
/*     */   }
/*     */ 
/*     */   public void bairen()
/*     */   {
/* 637 */     String return_str = "";
/*     */     try
/*     */     {
/* 640 */       return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "TopChallenge.aspx", new String[] { "type=1" });
/* 641 */       this.logger.debug("begin:{}", return_str);
/*     */     } catch (Exception e) {
/* 643 */       e.printStackTrace();
/*     */     }
/*     */     while (true)
/*     */       try {
/* 647 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "TopChallenge.aspx", new String[] { "type=2" });
/* 648 */         this.logger.debug("巅峰挑战:{}", return_str);
/* 649 */         return_str = this.operateService.onlyPost(this.entity.getBaseUrl() + "TopChallenge.aspx", new String[] { "type=5" });
/*     */ 
/* 651 */         Thread.sleep(5000L);
/* 652 */         continue; } catch (Exception e) {
/* 653 */         this.logger.error("异常：{}", e.getMessage());
/*     */       }
/*     */   }
/*     */ 
/*     */   public void chat(String accountID, String accountName) throws IOException
/*     */   {
/* 659 */     DefaultHttpClient httpClient = new DefaultHttpClient();
/* 660 */     httpClient.setCookieStore(this.userService.getCookieStore());
/* 661 */     HashMap temp_map = Maps.newHashMap();
/* 662 */     temp_map.put("type", "1");
/* 663 */     temp_map.put("ai", accountID);
/*     */ 
/* 665 */     temp_map.put("receiobj", "-1");
/* 666 */     HashMap map = Maps.newHashMap(temp_map);
/* 667 */     String send_club = accountName;
/* 668 */     String temp_club = "";
/* 669 */     String content = "";
/* 670 */     System.out.println("输入q退出程序");
/* 671 */     System.out.println("选择聊天频道----1:本服;2:全游戏");
/* 672 */     String channel = "0";
/* 673 */     BufferedReader bin = new BufferedReader(new InputStreamReader(System.in, "GBK"));
/* 674 */     String temp_channel = bin.readLine().trim();
/* 675 */     if (temp_channel.toLowerCase().equals("q")) {
/* 676 */       return;
/*     */     }
/* 678 */     if (temp_channel.equals("1"))
/* 679 */       temp_channel = "0";
/* 680 */     else if (temp_channel.equals("2")) {
/* 681 */       temp_channel = "1";
/*     */     }
/* 683 */     temp_map.put("channel", "0");
/*     */ 
/* 686 */     System.out.println("输入q退出程序");
/* 687 */     System.out.println("第一行输入俱乐部名，直接回车则使用自己的名字或者上次输入的俱乐部名字");
/* 688 */     System.out.println("第二行输入想说的话，回车结束");
/*     */     while (true) {
/* 690 */       boolean talk = false;
/* 691 */       map = Maps.newHashMap(temp_map);
/* 692 */       System.out.print("请输入俱乐部名:");
/* 693 */       BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "GBK"));
/* 694 */       temp_club = in.readLine();
/* 695 */       if (StringUtils.isNotBlank(temp_club)) {
/* 696 */         if (temp_club.trim().toLowerCase().equals("q")) {
/*     */           break;
/*     */         }
/* 699 */         send_club = temp_club;
/*     */       }
/* 701 */       map.put("sendclub", send_club);
/* 702 */       System.out.print("请输入要说的话:");
/* 703 */       content = in.readLine();
/* 704 */       if (StringUtils.isNotBlank(content)) {
/* 705 */         if (content.trim().toLowerCase().equals("q")) {
/*     */           break;
/*     */         }
/* 708 */         map.put("content", content);
/* 709 */         talk = true;
/*     */       }
/* 711 */       if (talk) {
/* 712 */         HttpUtils.post(httpClient, this.entity.getMainUrl(), map);
/* 713 */         System.out.println(send_club + ":" + content);
/* 714 */         continue;
/* 715 */       }System.out.println("好像输入有问题，再来一次吧\n");
/*     */     }
/*     */ 
/* 719 */     System.out.println();
/* 720 */     System.out.println("不好玩？那睡觉吧,bye");
/* 721 */     httpClient.getConnectionManager().shutdown();
/*     */   }
/*     */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.service.TempService
 * JD-Core Version:    0.6.0
 */