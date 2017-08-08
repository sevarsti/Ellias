/*    */ package com.ogzq.util;
/*    */ 
/*    */ import org.htmlparser.lexer.Page;
/*    */ import org.htmlparser.nodes.TagNode;
/*    */ 
/*    */ public class IFrameTag extends TagNode
/*    */ {
/*  7 */   private static final String[] mIds = { "IFRAME" };
/*    */ 
/*    */   public String[] getIds() {
/* 10 */     return mIds;
/*    */   }
/*    */ 
/*    */   public String getFrameLocation() {
/* 14 */     String ret = super.getAttribute("SRC");
/* 15 */     if (ret == null)
/* 16 */       ret = "";
/* 17 */     else if (super.getPage() != null) {
/* 18 */       ret = super.getPage().getAbsoluteURL(ret);
/*    */     }
/* 20 */     return ret;
/*    */   }
/*    */ 
/*    */   public void setFrameLocation(String url) {
/* 24 */     super.setAttribute("SRC", url);
/*    */   }
/*    */ 
/*    */   public String getFrameName() {
/* 28 */     return super.getAttribute("NAME");
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 32 */     return "IFRAME TAG : IFrame " + getFrameName() + " at " + getFrameLocation() + "; begins at : " + 
/* 33 */       super.getStartPosition() + "; ends at : " + super.getEndPosition();
/*    */   }
/*    */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.util.IFrameTag
 * JD-Core Version:    0.6.0
 */