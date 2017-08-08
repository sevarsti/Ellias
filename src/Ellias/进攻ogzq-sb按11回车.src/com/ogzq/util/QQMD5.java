/*     */ package com.ogzq.util;
/*     */ 
/*     */ public class QQMD5
/*     */ {
/*  21 */   String hex_chr = "0123456789abcdef";
/*     */ 
/*     */   private String rhex(int[] num)
/*     */   {
/*  25 */     String str = "";
/*  26 */     for (int n : num) {
/*  27 */       for (int j = 0; j <= 3; j++) {
/*  28 */         str = str + this.hex_chr.charAt(n >> j * 8 + 4 & 0xF) + 
/*  29 */           this.hex_chr.charAt(n >> j * 8 & 0xF);
/*     */       }
/*     */     }
/*  32 */     return str.toUpperCase();
/*     */   }
/*     */ 
/*     */   private int add(int x, int y)
/*     */   {
/*  40 */     int C = (x & 0xFFFF) + (y & 0xFFFF);
/*  41 */     int B = (x >> 16) + (y >> 16) + (C >> 16);
/*     */ 
/*  43 */     return B << 16 | C & 0xFFFF;
/*     */   }
/*     */ 
/*     */   private int rol(int num, int cnt)
/*     */   {
/*  50 */     return num << cnt | num >>> 32 - cnt;
/*     */   }
/*     */ 
/*     */   private int cmn(int q, int a, int b, int x, int s, int t)
/*     */   {
/*  58 */     return add(rol(add(add(a, q), add(x, t)), s), b);
/*     */   }
/*     */ 
/*     */   private int ff(int a, int b, int c, int d, int x, int s, int t) {
/*  62 */     return cmn(b & c | (b ^ 0xFFFFFFFF) & d, a, b, x, s, t);
/*     */   }
/*     */ 
/*     */   private int gg(int a, int b, int c, int d, int x, int s, int t) {
/*  66 */     return cmn(b & d | c & (d ^ 0xFFFFFFFF), a, b, x, s, t);
/*     */   }
/*     */ 
/*     */   private int hh(int a, int b, int c, int d, int x, int s, int t) {
/*  70 */     return cmn(b ^ c ^ d, a, b, x, s, t);
/*     */   }
/*     */ 
/*     */   private int ii(int a, int b, int c, int d, int x, int s, int t) {
/*  74 */     return cmn(c ^ (b | d ^ 0xFFFFFFFF), a, b, x, s, t);
/*     */   }
/*     */ 
/*     */   public String calcMD5(String str)
/*     */   {
/*  81 */     int[] i = str2bin(str);
/*  82 */     int[] r = coreMD5(i, str.length() * 8);
/*     */ 
/*  84 */     return rhex(r);
/*     */   }
/*     */ 
/*     */   public String calcMD5_3(String str)
/*     */   {
/*  92 */     int[] i = str2bin(str);
/*     */ 
/*  94 */     int[] r = coreMD5(i, str.length() * 8);
/*  95 */     r = coreMD5(r, 128);
/*  96 */     r = coreMD5(r, 128);
/*     */ 
/*  98 */     return rhex(r);
/*     */   }
/*     */ 
/*     */   private int[] str2bin(String strlen)
/*     */   {
/* 109 */     int nblk = strlen.length() / 4 + (strlen.length() % 4 > 0 ? 1 : 0);
/* 110 */     int[] blks = new int[nblk];
/*     */ 
/* 112 */     for (int k = 0; k < strlen.length(); k++) {
/* 113 */       blks[(k >> 2)] |= strlen.charAt(k) << k % 4 * 8;
/*     */     }
/*     */ 
/* 116 */     return blks;
/*     */   }
/*     */ 
/*     */   private int[] coreMD5(int[] n, int length)
/*     */   {
/* 126 */     int[] x = new int[(length + 64 >>> 9 << 4) + 14 + 2];
/* 127 */     for (int i = 0; i < n.length; i++) {
/* 128 */       x[i] = n[i];
/*     */     }
/*     */ 
/* 131 */     x[(length >> 5)] |= 128 << length % 32;
/* 132 */     x[((length + 64 >>> 9 << 4) + 14)] = length;
/*     */ 
/* 134 */     int j = 1732584193;
/* 135 */     int i = -271733879;
/* 136 */     int h = -1732584194;
/* 137 */     int g = 271733878;
/*     */ 
/* 139 */     for (int y = 0; y < x.length - 1; y += 16)
/*     */     {
/* 141 */       int e = j;
/* 142 */       int d = i;
/* 143 */       int b = h;
/* 144 */       int a = g;
/*     */ 
/* 146 */       j = ff(j, i, h, g, x[(y + 0)], 7, -680876936);
/* 147 */       g = ff(g, j, i, h, x[(y + 1)], 12, -389564586);
/* 148 */       h = ff(h, g, j, i, x[(y + 2)], 17, 606105819);
/* 149 */       i = ff(i, h, g, j, x[(y + 3)], 22, -1044525330);
/* 150 */       j = ff(j, i, h, g, x[(y + 4)], 7, -176418897);
/* 151 */       g = ff(g, j, i, h, x[(y + 5)], 12, 1200080426);
/* 152 */       h = ff(h, g, j, i, x[(y + 6)], 17, -1473231341);
/* 153 */       i = ff(i, h, g, j, x[(y + 7)], 22, -45705983);
/* 154 */       j = ff(j, i, h, g, x[(y + 8)], 7, 1770035416);
/* 155 */       g = ff(g, j, i, h, x[(y + 9)], 12, -1958414417);
/* 156 */       h = ff(h, g, j, i, x[(y + 10)], 17, -42063);
/* 157 */       i = ff(i, h, g, j, x[(y + 11)], 22, -1990404162);
/* 158 */       j = ff(j, i, h, g, x[(y + 12)], 7, 1804603682);
/* 159 */       g = ff(g, j, i, h, x[(y + 13)], 12, -40341101);
/* 160 */       h = ff(h, g, j, i, x[(y + 14)], 17, -1502002290);
/* 161 */       i = ff(i, h, g, j, x[(y + 15)], 22, 1236535329);
/*     */ 
/* 163 */       j = gg(j, i, h, g, x[(y + 1)], 5, -165796510);
/* 164 */       g = gg(g, j, i, h, x[(y + 6)], 9, -1069501632);
/* 165 */       h = gg(h, g, j, i, x[(y + 11)], 14, 643717713);
/* 166 */       i = gg(i, h, g, j, x[(y + 0)], 20, -373897302);
/* 167 */       j = gg(j, i, h, g, x[(y + 5)], 5, -701558691);
/* 168 */       g = gg(g, j, i, h, x[(y + 10)], 9, 38016083);
/* 169 */       h = gg(h, g, j, i, x[(y + 15)], 14, -660478335);
/* 170 */       i = gg(i, h, g, j, x[(y + 4)], 20, -405537848);
/* 171 */       j = gg(j, i, h, g, x[(y + 9)], 5, 568446438);
/* 172 */       g = gg(g, j, i, h, x[(y + 14)], 9, -1019803690);
/* 173 */       h = gg(h, g, j, i, x[(y + 3)], 14, -187363961);
/* 174 */       i = gg(i, h, g, j, x[(y + 8)], 20, 1163531501);
/* 175 */       j = gg(j, i, h, g, x[(y + 13)], 5, -1444681467);
/* 176 */       g = gg(g, j, i, h, x[(y + 2)], 9, -51403784);
/* 177 */       h = gg(h, g, j, i, x[(y + 7)], 14, 1735328473);
/* 178 */       i = gg(i, h, g, j, x[(y + 12)], 20, -1926607734);
/*     */ 
/* 180 */       j = hh(j, i, h, g, x[(y + 5)], 4, -378558);
/* 181 */       g = hh(g, j, i, h, x[(y + 8)], 11, -2022574463);
/* 182 */       h = hh(h, g, j, i, x[(y + 11)], 16, 1839030562);
/* 183 */       i = hh(i, h, g, j, x[(y + 14)], 23, -35309556);
/* 184 */       j = hh(j, i, h, g, x[(y + 1)], 4, -1530992060);
/* 185 */       g = hh(g, j, i, h, x[(y + 4)], 11, 1272893353);
/* 186 */       h = hh(h, g, j, i, x[(y + 7)], 16, -155497632);
/* 187 */       i = hh(i, h, g, j, x[(y + 10)], 23, -1094730640);
/* 188 */       j = hh(j, i, h, g, x[(y + 13)], 4, 681279174);
/* 189 */       g = hh(g, j, i, h, x[(y + 0)], 11, -358537222);
/* 190 */       h = hh(h, g, j, i, x[(y + 3)], 16, -722521979);
/* 191 */       i = hh(i, h, g, j, x[(y + 6)], 23, 76029189);
/* 192 */       j = hh(j, i, h, g, x[(y + 9)], 4, -640364487);
/* 193 */       g = hh(g, j, i, h, x[(y + 12)], 11, -421815835);
/* 194 */       h = hh(h, g, j, i, x[(y + 15)], 16, 530742520);
/* 195 */       i = hh(i, h, g, j, x[(y + 2)], 23, -995338651);
/*     */ 
/* 197 */       j = ii(j, i, h, g, x[(y + 0)], 6, -198630844);
/* 198 */       g = ii(g, j, i, h, x[(y + 7)], 10, 1126891415);
/* 199 */       h = ii(h, g, j, i, x[(y + 14)], 15, -1416354905);
/* 200 */       i = ii(i, h, g, j, x[(y + 5)], 21, -57434055);
/* 201 */       j = ii(j, i, h, g, x[(y + 12)], 6, 1700485571);
/* 202 */       g = ii(g, j, i, h, x[(y + 3)], 10, -1894986606);
/* 203 */       h = ii(h, g, j, i, x[(y + 10)], 15, -1051523);
/* 204 */       i = ii(i, h, g, j, x[(y + 1)], 21, -2054922799);
/* 205 */       j = ii(j, i, h, g, x[(y + 8)], 6, 1873313359);
/* 206 */       g = ii(g, j, i, h, x[(y + 15)], 10, -30611744);
/* 207 */       h = ii(h, g, j, i, x[(y + 6)], 15, -1560198380);
/* 208 */       i = ii(i, h, g, j, x[(y + 13)], 21, 1309151649);
/* 209 */       j = ii(j, i, h, g, x[(y + 4)], 6, -145523070);
/* 210 */       g = ii(g, j, i, h, x[(y + 11)], 10, -1120210379);
/* 211 */       h = ii(h, g, j, i, x[(y + 2)], 15, 718787259);
/* 212 */       i = ii(i, h, g, j, x[(y + 9)], 21, -343485551);
/*     */ 
/* 214 */       j = add(j, e);
/* 215 */       i = add(i, d);
/* 216 */       h = add(h, b);
/* 217 */       g = add(g, a);
/*     */     }
/*     */ 
/* 220 */     int[] r = { j, i, h, g };
/* 221 */     return r;
/*     */   }
/*     */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.util.QQMD5
 * JD-Core Version:    0.6.0
 */