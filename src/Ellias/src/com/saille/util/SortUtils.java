package com.saille.util;


import java.util.List;
import java.util.Map;

public class SortUtils {
    //��������demo,low=0, high=a.length - 1
    public static void quickSortDemo(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];

        while(end > start) {
            //�Ӻ���ǰ�Ƚ�
            while(end > start && a[end] >= key) { //���û�бȹؼ�ֵС�ģ��Ƚ���һ����ֱ���бȹؼ�ֵС�Ľ���λ�ã�Ȼ���ִ�ǰ����Ƚ�
                end--;
            }
            if(a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //��ǰ����Ƚ�
            while(end > start && a[start] <= key) { //���û�бȹؼ�ֵ��ģ��Ƚ���һ����ֱ���бȹؼ�ֵ��Ľ���λ��
                start++;
            }
            if(a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //��ʱ��һ��ѭ���ȽϽ������ؼ�ֵ��λ���Ѿ�ȷ���ˡ���ߵ�ֵ���ȹؼ�ֵС���ұߵ�ֵ���ȹؼ�ֵ�󣬵������ߵ�˳���п����ǲ�һ���ģ���������ĵݹ����
        }
        //�ݹ�
        int count1 = 1, count2 = 1;
        if(start > low) {
            quickSortDemo(a, low, start - 1);//������С���һ������λ�õ��ؼ�ֵ����-1
        }
        if(end < high) {
            quickSortDemo(a, end + 1, high);//�ұ����С��ӹؼ�ֵ����+1�����һ��
        }
    }

    //ŷ��������Ա��Ƕʱ�Ŀ���Ƕ��Ա�嵥
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

    //ŷ�������������ʵ������
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

    //ŷ�������������ʵ������
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

    //ŷ�����򣬽�����������
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