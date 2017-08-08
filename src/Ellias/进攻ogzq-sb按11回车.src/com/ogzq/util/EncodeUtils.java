/*     */ package com.ogzq.util;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLDecoder;
/*     */ import java.net.URLEncoder;
/*     */ import org.apache.commons.codec.DecoderException;
/*     */ import org.apache.commons.codec.binary.Base64;
/*     */ import org.apache.commons.codec.binary.Hex;
/*     */ import org.apache.commons.lang.StringEscapeUtils;
/*     */ 
/*     */ public abstract class EncodeUtils
/*     */ {
/*     */   private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
/*     */   private static final String DEFAULT_URL_ENCODING = "UTF-8";
/*     */ 
/*     */   public static String encodeHex(byte[] input)
/*     */   {
/*  30 */     return Hex.encodeHexString(input);
/*     */   }
/*     */ 
/*     */   public static byte[] decodeHex(String input)
/*     */   {
/*     */     try
/*     */     {
/*  38 */       return Hex.decodeHex(input.toCharArray()); } catch (DecoderException e) {
/*     */     }
/*  40 */     throw new IllegalStateException("Hex Decoder exception", e);
/*     */   }
/*     */ 
/*     */   public static String encodeBase64(byte[] input)
/*     */   {
/*  48 */     return Base64.encodeBase64String(input);
/*     */   }
/*     */ 
/*     */   public static String encodeUrlSafeBase64(byte[] input)
/*     */   {
/*  55 */     return Base64.encodeBase64URLSafeString(input);
/*     */   }
/*     */ 
/*     */   public static byte[] decodeBase64(String input)
/*     */   {
/*  62 */     return Base64.decodeBase64(input);
/*     */   }
/*     */ 
/*     */   public static String encodeBase62(long num)
/*     */   {
/*  69 */     return alphabetEncode(num, 62);
/*     */   }
/*     */ 
/*     */   public static long decodeBase62(String str)
/*     */   {
/*  76 */     return alphabetDecode(str, 62);
/*     */   }
/*     */ 
/*     */   private static String alphabetEncode(long num, int base) {
/*  80 */     num = Math.abs(num);
/*  81 */     StringBuilder sb = new StringBuilder();
/*  82 */     for (; num > 0L; num /= base) {
/*  83 */       sb.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt((int)(num % base)));
/*     */     }
/*     */ 
/*  86 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private static long alphabetDecode(String str, int base)
/*     */   {
/*  92 */     long result = 0L;
/*  93 */     for (int i = 0; i < str.length(); i++) {
/*  94 */       result = ()(result + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".indexOf(str.charAt(i)) * Math.pow(base, i));
/*     */     }
/*     */ 
/*  97 */     return result;
/*     */   }
/*     */ 
/*     */   public static String htmlEscape(String html)
/*     */   {
/* 107 */     return StringEscapeUtils.escapeHtml(html);
/*     */   }
/*     */ 
/*     */   public static String htmlUnescape(String htmlEscaped)
/*     */   {
/* 114 */     return StringEscapeUtils.unescapeHtml(htmlEscaped);
/*     */   }
/*     */ 
/*     */   public static String xmlEscape(String xml)
/*     */   {
/* 121 */     return StringEscapeUtils.escapeXml(xml);
/*     */   }
/*     */ 
/*     */   public static String xmlUnescape(String xmlEscaped)
/*     */   {
/* 128 */     return StringEscapeUtils.unescapeXml(xmlEscaped);
/*     */   }
/*     */ 
/*     */   public static String urlEncode(String part)
/*     */     throws UnsupportedEncodingException
/*     */   {
/*     */     try
/*     */     {
/* 137 */       return URLEncoder.encode(part, "UTF-8"); } catch (UnsupportedEncodingException e) {
/*     */     }
/* 139 */     throw e;
/*     */   }
/*     */ 
/*     */   public static String urlDecode(String part)
/*     */     throws UnsupportedEncodingException
/*     */   {
/*     */     try
/*     */     {
/* 150 */       return URLDecoder.decode(part, "UTF-8"); } catch (UnsupportedEncodingException e) {
/*     */     }
/* 152 */     throw e;
/*     */   }
/*     */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.util.EncodeUtils
 * JD-Core Version:    0.6.0
 */