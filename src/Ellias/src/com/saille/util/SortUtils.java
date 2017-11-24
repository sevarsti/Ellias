package com.saille.util;


import java.util.List;
import java.util.Map;

public class SortUtils {
    //快速排序demo,low=0, high=a.length - 1
    public static void quickSortDemo(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];

        while(end > start) {
            //从后往前比较
            while(end > start && a[end] >= key) { //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            }
            if(a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while(end > start && a[start] <= key) { //如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            }
            if(a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        int count1 = 1, count2 = 1;
        if(start > low) {
            quickSortDemo(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        }
        if(end < high) {
            quickSortDemo(a, end + 1, high);//右边序列。从关键值索引+1到最后一个
        }
    }

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