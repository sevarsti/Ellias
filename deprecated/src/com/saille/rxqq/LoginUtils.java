package com.saille.rxqq;

class LoginUtils {
    private static final int n = 0;
    private static final int o = 8;

    private static int q(int t, int b, int c, int d) {
        if(t < 20) {
            return b & c | (b ^ 0xFFFFFFFF) & d;
        }
        if(t < 40) {
            return b ^ c ^ d;
        }
        if(t < 60) {
            return b & c | b & d | c & d;
        }
        return b ^ c ^ d;
    }

    private static int r(int t) {
        return t < 60 ? -1894007588 : t < 40 ? 1859775393 : t < 20 ? 1518500249 : -899497514;
    }

    private static int u(int x, int y) {
        int a = (x & 0xFFFF) + (y & 0xFFFF);
        int b = (x >> 16) + (y >> 16) + (a >> 16);
        return b << 16 | a & 0xFFFF;
    }

    private static int v(int a, int b) {
        return a << b | a >>> 32 - b;
    }

    private static int[] z(String aa) {
        int[] a = new int[aa.length()];
        for(int i = 0; i < aa.length(); i++) {
            a[i] = aa.charAt(i);
        }
        int[] b = new int[a.length * 8];
        int c = 255;
        for(int i = 0; i < a.length * 8; i += 8) {
            b[(i >> 5)] |= (a[(i / 8)] & c) << 24 - i % 32;
        }
        int size = 0;
        for(int i = 0; i < b.length; i++) {
            if(b[i] == 0) {
                size = i;
                break;
            }
        }
        int[] ret = new int[size];
        for(int i = 0; i < size; i++) {
            ret[i] = b[i];
        }
        return ret;
    }

    private static String A(int[] a) {
        String b = "0123456789abcdef";
        String c = "";
        for(int i = 0; i < a.length * 4; i++) {
            c = c + b.charAt(a[(i >> 2)] >> (3 - i % 4) * 8 + 4 & 0xF);
            c = c + b.charAt(a[(i >> 2)] >> (3 - i % 4) * 8 & 0xF);
        }
        return c;
    }

    private static int[] p(int[] x, int f) {
        if(x.length < (f >> 5) + 1) {
            int[] xx = new int[(f >> 5) + 1];
            for(int i = 0; i < x.length; i++) {
                xx[i] = x[i];
            }
            for(int i = x.length; i < (f >> 5) + 1; i++) {
                xx[i] = 0;
            }
            x = xx;
        }
        x[(f >> 5)] |= 128 << 24 - f % 32;
        if(x.length < (f + 64 >> 9 << 4) + 16) {
            int[] n = new int[(f + 64 >> 9 << 4) + 16];
            for(int i = 0; i < x.length; i++) {
                n[i] = x[i];
            }
            for(int i = x.length; i < (f + 64 >> 9 << 4) + 16; i++) {
                n[i] = 0;
            }
            x = n;
        }
        x[((f + 64 >> 9 << 4) + 15)] = f;
        int[] w = new int[80];
        int a = 1732584193;
        int b = -271733879;
        int c = -1732584194;
        int d = 271733878;
        int e = -1009589776;
        for(int i = 0; i < x.length; i += 16) {
            int g = a;
            int h = b;
            int k = c;
            int l = d;
            int m = e;
            for(int j = 0; j < 80; j++) {
                if(j < 16) {
                    w[j] = x[(i + j)];
                } else {
                    w[j] = v(w[(j - 3)] ^ w[(j - 8)] ^ w[(j - 14)] ^ w[(j - 16)], 1);
                }
                int t = u(u(v(a, 5), q(j, b, c, d)), u(u(e, w[j]), r(j)));
                e = d;
                d = c;
                c = v(b, 30);
                b = a;
                a = t;
            }
            a = u(a, g);
            b = u(b, h);
            c = u(c, k);
            d = u(d, l);
            e = u(e, m);
        }
        return new int[]{a, b, c, d, e};
    }

    public static String hex_sha1(String s) {
        return A(p(z(s), s.length() * 8));
    }

    public static String base64Encode(String a) {
        a = a.replaceAll("@", "%40");
        String _keys = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
        a = "" + a;
        if(a.equals("")) {
            return "";
        }
        String b = "";
        int chr3 = 0;
        int enc4 = 0;
        int i = 0;
        do {
            int c;
            if(a.length() > i) {
                c = a.charAt(i);
            } else {
                c = -1;
            }
            i++;
            int chr2;
            if(a.length() > i) {
                chr2 = a.charAt(i);
            } else {
                chr2 = -1;
            }
            i++;
            if(a.length() > i) {
                chr3 = a.charAt(i);
            } else {
                chr3 = -1;
            }
            i++;
            int d = c >> 2;
            int enc2;
            if(chr2 == -1) {
                enc2 = (c & 0x3) << 4 | 0x0;
            } else {
                enc2 = (c & 0x3) << 4 | chr2 >> 4;
            }
            int enc3;
            if(chr3 == -1) {
                enc3 = (chr2 & 0xF) << 2 | 0x0;
            } else {
                enc3 = (chr2 & 0xF) << 2 | chr3 >> 6;
            }
            enc4 = chr3 & 0x3F;
            if(chr2 == -1) {
                enc3 = enc4 = 64;
            } else if(chr3 == -1) {
                enc4 = 64;
            }
            b = b + _keys.charAt(d) + _keys.charAt(enc2) + _keys.charAt(enc3) + _keys.charAt(enc4);
            c = chr2 = chr3 = 0;
            d = enc2 = enc3 = enc4 = 0;
        } while(i < a.length());

        return b;
    }

    public static String generateNonce(int a) {
        String x = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String b = "";
        for(int i = 0; i < a; i++) {
            b = b + x.charAt((int) Math.ceil(Math.random() * 1000000.0D) % x.length());
        }
        return b;
    }
}