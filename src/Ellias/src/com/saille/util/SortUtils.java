package com.saille.util;


import java.util.List;
import java.util.Map;

public class SortUtils {
    public static final int SORT_JIAGE = 1;

    /*
    public static List<Gangkou> sortGangkou(List<Gangkou> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(((Gangkou) list.get(i)).getPinyin().compareTo(((Gangkou) list.get(pos)).getPinyin()) < 0) {
                needSwap = true;
            } else if(((Gangkou) list.get(i)).getPinyin().compareTo(((Gangkou) list.get(pos)).getPinyin()) > 0) {
                needSwap = false;
            } else
            if(Collator.getInstance(Locale.CHINESE).compare(((Gangkou) list.get(i)).getName(), ((Gangkou) list.get(pos)).getName()) < 0) {
                needSwap = true;
            }

            if(needSwap) {
                Gangkou tmp = (Gangkou) list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        sortGangkou(list, start, pos);
        sortGangkou(list, pos + 1, end);
        return list;
    }
    */

    /*
    public static List<Guojia> sortGuojia(List<Guojia> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(((Guojia) list.get(i)).getPinyin().compareTo(((Guojia) list.get(pos)).getPinyin()) < 0) {
                needSwap = true;
            } else if(((Guojia) list.get(i)).getPinyin().compareTo(((Guojia) list.get(pos)).getPinyin()) > 0) {
                needSwap = false;
            } else
            if(Collator.getInstance(Locale.CHINESE).compare(((Guojia) list.get(i)).getName(), ((Guojia) list.get(pos)).getName()) < 0) {
                needSwap = true;
            }

            if(needSwap) {
                Guojia tmp = (Guojia) list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        sortGuojia(list, start, pos);
        sortGuojia(list, pos + 1, end);
        return list;
    }
    */

    /*
    public static List<Shangpin> sortShangpin(List<Shangpin> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(((Shangpin) list.get(i)).getPinyin().compareTo(((Shangpin) list.get(pos)).getPinyin()) < 0) {
                needSwap = true;
            } else if(((Shangpin) list.get(i)).getPinyin().compareTo(((Shangpin) list.get(pos)).getPinyin()) > 0) {
                needSwap = false;
            } else
            if(Collator.getInstance(Locale.CHINESE).compare(((Shangpin) list.get(i)).getName(), ((Shangpin) list.get(pos)).getName()) < 0) {
                needSwap = true;
            }

            if(needSwap) {
                Shangpin tmp = (Shangpin) list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        sortShangpin(list, start, pos);
        sortShangpin(list, pos + 1, end);
        return list;
    }
    */

    /*
    public static List<Leibie> sortLeibie(List<Leibie> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(((Leibie) list.get(i)).getPinyin().compareTo(((Leibie) list.get(pos)).getPinyin()) < 0) {
                needSwap = true;
            } else if(((Leibie) list.get(i)).getPinyin().compareTo(((Leibie) list.get(pos)).getPinyin()) > 0) {
                needSwap = false;
            } else
            if(Collator.getInstance(Locale.CHINESE).compare(((Leibie) list.get(i)).getName(), ((Leibie) list.get(pos)).getName()) < 0) {
                needSwap = true;
            }

            if(needSwap) {
                Leibie tmp = (Leibie) list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        sortLeibie(list, start, pos);
        sortLeibie(list, pos + 1, end);
        return list;
    }
    */

    /*
    public static List<Jiage> sortJiage(List<Jiage> list, int start, int end, int sort) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if((sort == 1) && (((Jiage) list.get(i)).getJiage() > ((Jiage) list.get(pos)).getJiage())) {
                needSwap = true;
            }

            if(needSwap) {
                Jiage tmp = (Jiage) list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        sortJiage(list, start, pos, sort);
        sortJiage(list, pos + 1, end, sort);
        return list;
    }
    */

    //欧冠足球，球员镶嵌时的可镶嵌球员清单
    public static List<String[]> sortXiangqianPlayer(List<String[]> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(list.get(i)[1].compareTo(list.get(pos)[1]) > 0) {
                needSwap = true;
            } else if(list.get(i)[1].compareTo(list.get(pos)[1]) == 0) {
                if(list.get(i)[3].compareTo(list.get(pos)[3]) > 0) {
                    needSwap = true;
                }
            }

            if(needSwap) {
                String[] tmp = list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        sortXiangqianPlayer(list, start, pos);
        sortXiangqianPlayer(list, pos + 1, end);
        return list;
    }

    //欧冠足球，所有球队实力排序
    public static List<Map<String, String>> sortPlayerAbility(List<Map<String, String>> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(Integer.parseInt(list.get(i).get("ability")) > Integer.parseInt(list.get(pos).get("ability"))) {
                needSwap = true;
            }

            if(needSwap) {
                Map<String, String> tmp = list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        sortPlayerAbility(list, start, pos);
        sortPlayerAbility(list, pos + 1, end);
        return list;
    }

    //欧冠足球，所有球会实力排序
    public static List<Map<String, String>> sortClubAbility(List<Map<String, String>> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(Integer.parseInt(list.get(i).get("shengwang")) > Integer.parseInt(list.get(pos).get("shengwang"))) {
                needSwap = true;
            }

            if(needSwap) {
                Map<String, String> tmp = list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        sortClubAbility(list, start, pos);
        sortClubAbility(list, pos + 1, end);
        return list;
    }

    //欧冠足球，教练道具排序
    public static List<Map<String, String>> sortCoachBag(List<Map<String, String>> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(Integer.parseInt(list.get(i).get("itemCode")) < Integer.parseInt(list.get(pos).get("itemCode"))) {
                needSwap = true;
            }

            if(needSwap) {
                Map<String, String> tmp = list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        sortCoachBag(list, start, pos);
        sortCoachBag(list, pos + 1, end);
        return list;
    }
}