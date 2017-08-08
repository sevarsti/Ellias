/*     */ package com.ogzq.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class PropertiesLoader
/*     */ {
/*     */   private final Properties properties;
/*     */ 
/*     */   public PropertiesLoader(String[] resourcesPaths)
/*     */   {
/*  14 */     this.properties = loadProperties(resourcesPaths);
/*     */   }
/*     */ 
/*     */   public Properties getProperties() {
/*  18 */     return this.properties;
/*     */   }
/*     */ 
/*     */   private Properties loadProperties(String[] resourcesPaths)
/*     */   {
/*  25 */     Properties props = new Properties();
/*     */ 
/*  27 */     for (String location : resourcesPaths) {
/*  28 */       InputStream is = null;
/*     */       try {
/*  30 */         is = getClass().getResourceAsStream(location);
/*  31 */         props.load(is);
/*     */       } catch (IOException ex) {
/*  33 */         ex.printStackTrace();
/*     */ 
/*  35 */         if (is == null) continue;
/*     */         try {
/*  37 */           is.close();
/*     */         }
/*     */         catch (IOException localIOException1)
/*     */         {
/*     */         }
/*     */       }
/*     */       finally
/*     */       {
/*  35 */         if (is != null)
/*     */           try {
/*  37 */             is.close();
/*     */           } catch (IOException localIOException2) {
/*     */           }
/*     */       }
/*     */     }
/*  42 */     return props;
/*     */   }
/*     */ 
/*     */   private String getValue(String key)
/*     */   {
/*  49 */     String systemProperty = System.getProperty(key);
/*  50 */     if (systemProperty != null) {
/*  51 */       return systemProperty;
/*     */     }
/*  53 */     return this.properties.getProperty(key);
/*     */   }
/*     */ 
/*     */   public String getProperty(String key)
/*     */     throws Exception
/*     */   {
/*  61 */     String value = getValue(key);
/*  62 */     if (value == null) {
/*  63 */       throw new Exception("String is null");
/*     */     }
/*  65 */     return value.trim();
/*     */   }
/*     */ 
/*     */   public String getProperty(String key, String defaultValue)
/*     */   {
/*  72 */     String value = getValue(key);
/*  73 */     return StringUtils.isNotBlank(value) ? value : defaultValue;
/*     */   }
/*     */ 
/*     */   public Integer getInteger(String key)
/*     */     throws Exception
/*     */   {
/*  81 */     String value = getValue(key);
/*  82 */     if (value == null) {
/*  83 */       throw new Exception("Integer is null");
/*     */     }
/*  85 */     return Integer.valueOf(value);
/*     */   }
/*     */ 
/*     */   public Integer getInteger(String key, Integer defaultValue)
/*     */   {
/*  92 */     String value = getValue(key);
/*  93 */     return value != null ? Integer.valueOf(value) : defaultValue;
/*     */   }
/*     */ 
/*     */   public Double getDouble(String key)
/*     */     throws Exception
/*     */   {
/* 101 */     String value = getValue(key);
/* 102 */     if (value == null) {
/* 103 */       throw new Exception("Double is null");
/*     */     }
/* 105 */     return Double.valueOf(value);
/*     */   }
/*     */ 
/*     */   public Double getDouble(String key, Integer defaultValue)
/*     */   {
/* 112 */     String value = getValue(key);
/* 113 */     return Double.valueOf(defaultValue.intValue());
/*     */   }
/*     */ 
/*     */   public Boolean getBoolean(String key)
/*     */     throws Exception
/*     */   {
/* 121 */     String value = getValue(key);
/* 122 */     if (value == null) {
/* 123 */       throw new Exception("Boolean is null");
/*     */     }
/* 125 */     return Boolean.valueOf(value);
/*     */   }
/*     */ 
/*     */   public Boolean getBoolean(String key, boolean defaultValue)
/*     */   {
/* 132 */     String value = getValue(key);
/* 133 */     return Boolean.valueOf(value != null ? Boolean.valueOf(value).booleanValue() : defaultValue);
/*     */   }
/*     */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.util.PropertiesLoader
 * JD-Core Version:    0.6.0
 */