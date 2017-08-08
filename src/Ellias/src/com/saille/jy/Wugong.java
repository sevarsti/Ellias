package com.saille.jy;

import org.codehaus.jettison.json.JSONObject;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-11-25
 * Time: 20:34:10
 * To change this template use File | Settings | File Templates.
 */
public class Wugong {
    private final static int TYPE_NEIGONG = 1;
    private final static int TYPE_QINGGONG = 2;
    private final static int TYPE_QUANFA = 3;
    private final static int TYPE_JIANFA = 4;
    private final static int TYPE_DAOFA = 5;
    private final static int TYPE_QIANGFA = 6;
    private final static int TYPE_TESHU = 7;
    private final static int TYPE_ANQI = 8;

    public int id;
    public String name;
    public int type;
    public int nandu; //Îåµµ£ºDCBAS
    public double baseGongji; //»ù´¡¹¥»÷
    public double baseFangyu; //»ù´¡·ÀÓù
    public double baseMingzhong; //»ù´¡ÃüÖÐ
    public double baseShanbi; //»ù´¡ÉÁ±Ü
    public static void main(String[] args) {
        try {
            JSONObject json = new JSONObject();
            json.put("a", new int[]{2,3});
            System.out.println(json.toString());
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}
