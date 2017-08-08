/*     */ package com.ogzq.util;
/*     */ 
/*     */ import com.ogzq.entity.Result;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.HttpVersion;
/*     */ import org.apache.http.NameValuePair;
/*     */ import org.apache.http.StatusLine;
/*     */ import org.apache.http.client.ClientProtocolException;
/*     */ import org.apache.http.client.entity.UrlEncodedFormEntity;
/*     */ import org.apache.http.client.methods.HttpGet;
/*     */ import org.apache.http.client.methods.HttpPost;
/*     */ import org.apache.http.conn.scheme.PlainSocketFactory;
/*     */ import org.apache.http.conn.scheme.Scheme;
/*     */ import org.apache.http.conn.scheme.SchemeRegistry;
/*     */ import org.apache.http.conn.ssl.SSLSocketFactory;
/*     */ import org.apache.http.impl.client.DefaultHttpClient;
/*     */ import org.apache.http.impl.conn.PoolingClientConnectionManager;
/*     */ import org.apache.http.message.BasicNameValuePair;
/*     */ import org.apache.http.params.BasicHttpParams;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.params.HttpProtocolParams;
/*     */ import org.apache.http.util.EntityUtils;
/*     */ 
/*     */ public class HttpUtils
/*     */ {
/*     */   public static DefaultHttpClient getHttpClient()
/*     */   {
/*  42 */     HttpParams params = new BasicHttpParams();
/*  43 */     HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
/*  44 */     HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
/*  45 */     HttpProtocolParams.setUseExpectContinue(params, true);
/*     */ 
/*  48 */     int REQUEST_TIMEOUT = 10000;
/*  49 */     int SO_TIMEOUT = 10000;
/*     */ 
/*  52 */     params.setParameter("http.connection.timeout", Integer.valueOf(REQUEST_TIMEOUT));
/*  53 */     params.setParameter("http.socket.timeout", Integer.valueOf(SO_TIMEOUT));
/*     */ 
/*  56 */     SchemeRegistry schreg = new SchemeRegistry();
/*  57 */     schreg.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
/*  58 */     schreg.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
/*     */ 
/*  61 */     PoolingClientConnectionManager pccm = new PoolingClientConnectionManager(schreg);
/*  62 */     pccm.setDefaultMaxPerRoute(20);
/*  63 */     pccm.setMaxTotal(100);
/*     */ 
/*  65 */     DefaultHttpClient httpClient = new DefaultHttpClient(pccm, params);
/*  66 */     return httpClient;
/*     */   }
/*     */ 
/*     */   public static Result post(DefaultHttpClient httpClient, String url, HashMap<String, String> map)
/*     */     throws ClientProtocolException, IOException
/*     */   {
/*  73 */     HttpPost post = new HttpPost(url);
/*     */ 
/*  75 */     List nvps = new ArrayList();
/*  76 */     for (Map.Entry entry : map.entrySet()) {
/*  77 */       nvps.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
/*     */     }
/*     */ 
/*  80 */     post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
/*     */ 
/*  82 */     HttpResponse response = httpClient.execute(post);
/*  83 */     Result result = new Result();
/*  84 */     result.setStatusCode(response.getStatusLine().getStatusCode());
/*  85 */     result.setBody(EntityUtils.toString(response.getEntity()));
/*  86 */     post.releaseConnection();
/*  87 */     return result;
/*     */   }
/*     */ 
/*     */   public static Result get(DefaultHttpClient httpClient, String url) throws ClientProtocolException, IOException
/*     */   {
/*  92 */     HttpGet get = new HttpGet(url);
/*  93 */     HttpResponse response = httpClient.execute(get);
/*  94 */     Result result = new Result();
/*  95 */     result.setStatusCode(response.getStatusLine().getStatusCode());
/*  96 */     result.setBody(EntityUtils.toString(response.getEntity()));
/*  97 */     get.releaseConnection();
/*  98 */     return result;
/*     */   }
/*     */ 
/*     */   public static String getLocation(DefaultHttpClient httpClient, String url) throws ClientProtocolException, IOException
/*     */   {
/* 103 */     HttpGet get = new HttpGet(url);
/* 104 */     HttpParams params = get.getParams();
/* 105 */     params.setParameter("http.protocol.handle-redirects", Boolean.valueOf(false));
/* 106 */     HttpResponse response = httpClient.execute(get);
/* 107 */     Header header = response.getFirstHeader("Location");
/* 108 */     String str = header.getValue();
/*     */ 
/* 110 */     get.releaseConnection();
/* 111 */     return str;
/*     */   }
/*     */ 
/*     */   public static List<NameValuePair> getNvps(HashMap<String, String> map) {
/* 115 */     List nvps = new ArrayList();
/* 116 */     for (Map.Entry entry : map.entrySet()) {
/* 117 */       nvps.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
/*     */     }
/* 119 */     return nvps;
/*     */   }
/*     */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.util.HttpUtils
 * JD-Core Version:    0.6.0
 */