/*     */ package com.ogzq.util;
/*     */ 
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.util.Arrays;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import javax.crypto.Mac;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.SecretKeyFactory;
/*     */ import javax.crypto.spec.DESKeySpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ 
/*     */ public abstract class CryptoUtils
/*     */ {
/*     */   private static final String DES = "DES";
/*     */   private static final String AES = "AES";
/*     */   private static final String HMACSHA1 = "HmacSHA1";
/*     */   private static final int DEFAULT_HMACSHA1_KEYSIZE = 160;
/*     */   private static final int DEFAULT_AES_KEYSIZE = 128;
/*     */ 
/*     */   public static byte[] hmacSha1(String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/*     */     try
/*     */     {
/*  41 */       SecretKey secretKey = new SecretKeySpec(keyBytes, "HmacSHA1");
/*  42 */       Mac mac = Mac.getInstance("HmacSHA1");
/*  43 */       mac.init(secretKey);
/*  44 */       return mac.doFinal(input.getBytes()); } catch (GeneralSecurityException e) {
/*     */     }
/*  46 */     throw e;
/*     */   }
/*     */ 
/*     */   public static String hmacSha1ToHex(String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/*  58 */     byte[] macResult = hmacSha1(input, keyBytes);
/*  59 */     return EncodeUtils.encodeHex(macResult);
/*     */   }
/*     */ 
/*     */   public static String hmacSha1ToBase64(String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/*  70 */     byte[] macResult = hmacSha1(input, keyBytes);
/*  71 */     return EncodeUtils.encodeBase64(macResult);
/*     */   }
/*     */ 
/*     */   public static String hmacSha1ToBase64UrlSafe(String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/*  82 */     byte[] macResult = hmacSha1(input, keyBytes);
/*  83 */     return EncodeUtils.encodeUrlSafeBase64(macResult);
/*     */   }
/*     */ 
/*     */   public static boolean isHexMacValid(String hexMac, String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/*  98 */     byte[] expected = EncodeUtils.decodeHex(hexMac);
/*  99 */     byte[] actual = hmacSha1(input, keyBytes);
/*     */ 
/* 101 */     return Arrays.equals(expected, actual);
/*     */   }
/*     */ 
/*     */   public static boolean isBase64MacValid(String base64Mac, String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/* 117 */     byte[] expected = EncodeUtils.decodeBase64(base64Mac);
/* 118 */     byte[] actual = hmacSha1(input, keyBytes);
/*     */ 
/* 120 */     return Arrays.equals(expected, actual);
/*     */   }
/*     */ 
/*     */   public static byte[] generateMacSha1Key()
/*     */     throws GeneralSecurityException
/*     */   {
/*     */     try
/*     */     {
/* 131 */       KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA1");
/* 132 */       keyGenerator.init(160);
/* 133 */       SecretKey secretKey = keyGenerator.generateKey();
/* 134 */       return secretKey.getEncoded(); } catch (GeneralSecurityException e) {
/*     */     }
/* 136 */     throw e;
/*     */   }
/*     */ 
/*     */   public static String generateMacSha1HexKey()
/*     */     throws GeneralSecurityException
/*     */   {
/* 147 */     return EncodeUtils.encodeHex(generateMacSha1Key());
/*     */   }
/*     */ 
/*     */   public static String desEncryptToHex(String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/* 161 */     byte[] encryptResult = des(input.getBytes(), keyBytes, 1);
/* 162 */     return EncodeUtils.encodeHex(encryptResult);
/*     */   }
/*     */ 
/*     */   public static String desEncryptToBase64(String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/* 175 */     byte[] encryptResult = des(input.getBytes(), keyBytes, 1);
/* 176 */     return EncodeUtils.encodeBase64(encryptResult);
/*     */   }
/*     */ 
/*     */   public static String desDecryptFromHex(String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/* 189 */     byte[] decryptResult = des(EncodeUtils.decodeHex(input), keyBytes, 2);
/* 190 */     return new String(decryptResult);
/*     */   }
/*     */ 
/*     */   public static String desDecryptFromBase64(String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/* 203 */     byte[] decryptResult = des(EncodeUtils.decodeBase64(input), keyBytes, 2);
/* 204 */     return new String(decryptResult);
/*     */   }
/*     */ 
/*     */   private static byte[] des(byte[] inputBytes, byte[] keyBytes, int mode)
/*     */     throws GeneralSecurityException
/*     */   {
/*     */     try
/*     */     {
/* 220 */       DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
/* 221 */       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
/* 222 */       SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
/*     */ 
/* 224 */       Cipher cipher = Cipher.getInstance("DES");
/* 225 */       cipher.init(mode, secretKey);
/* 226 */       return cipher.doFinal(inputBytes); } catch (GeneralSecurityException e) {
/*     */     }
/* 228 */     throw e;
/*     */   }
/*     */ 
/*     */   public static byte[] generateDesKey()
/*     */     throws GeneralSecurityException
/*     */   {
/*     */     try
/*     */     {
/* 239 */       KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
/* 240 */       SecretKey secretKey = keyGenerator.generateKey();
/* 241 */       return secretKey.getEncoded(); } catch (GeneralSecurityException e) {
/*     */     }
/* 243 */     throw e;
/*     */   }
/*     */ 
/*     */   public static String generateDesHexKey()
/*     */     throws GeneralSecurityException
/*     */   {
/* 253 */     return EncodeUtils.encodeHex(generateDesKey());
/*     */   }
/*     */ 
/*     */   public static String aesEncryptToHex(String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/* 267 */     byte[] encryptResult = aes(input.getBytes(), keyBytes, 1);
/* 268 */     return EncodeUtils.encodeHex(encryptResult);
/*     */   }
/*     */ 
/*     */   public static String aesEncryptToBase64(String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/* 281 */     byte[] encryptResult = aes(input.getBytes(), keyBytes, 1);
/* 282 */     return EncodeUtils.encodeBase64(encryptResult);
/*     */   }
/*     */ 
/*     */   public static String aesDecryptFromHex(String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/* 295 */     byte[] decryptResult = aes(EncodeUtils.decodeHex(input), keyBytes, 2);
/* 296 */     return new String(decryptResult);
/*     */   }
/*     */ 
/*     */   public static String aesDecryptFromBase64(String input, byte[] keyBytes)
/*     */     throws GeneralSecurityException
/*     */   {
/* 309 */     byte[] decryptResult = aes(EncodeUtils.decodeBase64(input), keyBytes, 2);
/* 310 */     return new String(decryptResult);
/*     */   }
/*     */ 
/*     */   private static byte[] aes(byte[] inputBytes, byte[] keyBytes, int mode)
/*     */     throws GeneralSecurityException
/*     */   {
/*     */     try
/*     */     {
/* 326 */       SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
/* 327 */       Cipher cipher = Cipher.getInstance("AES");
/* 328 */       cipher.init(mode, secretKey);
/* 329 */       return cipher.doFinal(inputBytes); } catch (GeneralSecurityException e) {
/*     */     }
/* 331 */     throw e;
/*     */   }
/*     */ 
/*     */   public static byte[] generateAesKey()
/*     */     throws GeneralSecurityException
/*     */   {
/*     */     try
/*     */     {
/* 342 */       KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
/* 343 */       keyGenerator.init(128);
/* 344 */       SecretKey secretKey = keyGenerator.generateKey();
/* 345 */       return secretKey.getEncoded(); } catch (GeneralSecurityException e) {
/*     */     }
/* 347 */     throw e;
/*     */   }
/*     */ 
/*     */   public static String generateAesHexKey()
/*     */     throws GeneralSecurityException
/*     */   {
/* 358 */     return EncodeUtils.encodeHex(generateAesKey());
/*     */   }
/*     */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.util.CryptoUtils
 * JD-Core Version:    0.6.0
 */