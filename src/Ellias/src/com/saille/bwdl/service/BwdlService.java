package com.saille.bwdl.service;

import com.saille.bwdl.ChengShi;
import com.saille.bwdl.FangJu;
import com.saille.bwdl.ShiLi;
import com.saille.bwdl.Version;
import com.saille.bwdl.WuJiang;
import com.saille.bwdl.WuJiangChengShiRela;
import com.saille.bwdl.WuQi;
import com.saille.bwdl.dao.BwdlDao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

public class BwdlService implements InitializingBean {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private Map<Integer, WuJiang> allWuJiangs;
    private Map<Integer, ChengShi> allChengShis;
    private Map<Integer, WuJiangChengShiRela> allWuJiangChengShiRelas;
    private BwdlDao dao;

    public void setDao(BwdlDao dao) {
        this.dao = dao;
    }

    public void afterPropertiesSet() throws Exception {
        this.allWuJiangs = new HashMap();
        List<WuJiang> wujiangs = this.dao.findAllWuJiangs();
        for(WuJiang w : wujiangs) {
            if(w != null) {
                this.allWuJiangs.put(Integer.valueOf(w.getId()), w);
            }
        }
        this.LOGGER.info("武将初始化完成，共有" + this.allWuJiangs.size() + "条记录");
        this.allChengShis = new HashMap();
        List<ChengShi> chengshis = this.dao.findAllChengShis();
        for(ChengShi c : chengshis) {
            if(c != null) {
                this.allChengShis.put(Integer.valueOf(c.getId()), c);
            }
        }
        this.LOGGER.info("城市初始化完成，共有" + this.allChengShis.size() + "条记录");
        this.allWuJiangChengShiRelas = new HashMap();
        List<WuJiangChengShiRela> relas = this.dao.findAllWuJiangChengShiRelas();
        for(WuJiangChengShiRela r : relas) {
            if(r != null) {
                this.allWuJiangChengShiRelas.put(Integer.valueOf(r.getId()), r);
            }
        }
        this.LOGGER.info("城市内武将初始化完成，共有" + this.allWuJiangChengShiRelas.size() + "条记录");
    }

    public void refreshCache() {
        try {
            afterPropertiesSet();
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error(sw.toString());
        }
    }

    public List<Version> findAllVersions() {
        return this.dao.findAllVersions();
    }

    public Version getVersion(int versionId) {
        return this.dao.getVersion(versionId);
    }

    public int saveVersion(Version version) {
        return this.dao.saveVersion(version);
    }

    public List<WuJiang> findAllWuJiangs(int version) {
        return this.dao.findAllWuJiangs(version);
    }

    public List<WuJiang> findAllWuJiangsInit(int version) {
        return this.dao.findAllWuJiangsInit(version);
    }

    public List<WuQi> findAllWuQis(int version) {
        return this.dao.findAllWuQis(version);
    }

    public WuQi getWuQi(int wuqiId) {
        return this.dao.getWuQi(wuqiId);
    }

    public int saveWuQi(WuQi wuqi) {
        return this.dao.saveWuQi(wuqi);
    }

    public List<FangJu> findAllFangJus(int version) {
        return this.dao.findAllFangJus(version);
    }

    public FangJu getFangJu(int fangjuId) {
        return this.dao.getFangJu(fangjuId);
    }

    public int saveFangJu(FangJu fangju) {
        return this.dao.saveFangJu(fangju);
    }

    public WuJiang getWuJiang(int wujiangId) {
        WuJiang ret = (WuJiang) this.allWuJiangs.get(Integer.valueOf(wujiangId));
        if(ret == null) {
            ret = this.dao.getWuJiang(wujiangId);
            if(ret != null) {
                this.allWuJiangs.put(Integer.valueOf(ret.getId()), ret);
            }
        }
        return ret;
    }

    public WuJiang getWuJiangInit(int wujiangId) {
        return this.dao.getWuJiangInit(wujiangId);
    }

    public int saveWuJiang(WuJiang wujiang) {
        int id = this.dao.saveWuJiang(wujiang);
        if(!wujiang.isInit()) {
            this.allWuJiangs.put(Integer.valueOf(id), wujiang);
        }
        return id;
    }

    public List<ChengShi> findAllChengShisInit(int version) {
        return this.dao.findAllChengShisInit(version);
    }

    public List<ChengShi> findAllChengShis(int version) {
        return this.dao.findAllChengShis(version);
    }

    public ChengShi getChengShi(int chengshiId) {
        ChengShi ret = (ChengShi) this.allChengShis.get(Integer.valueOf(chengshiId));
        if(ret == null) {
            ret = this.dao.getChengShi(chengshiId);
            if(ret != null) {
                this.allChengShis.put(Integer.valueOf(ret.getId()), ret);
            }
        }
        return ret;
    }

    public ChengShi getChengShiInit(int chengshiId) {
        return this.dao.getChengShiInit(chengshiId);
    }

    public int saveChengShi(ChengShi chengshi) {
        int id = this.dao.saveChengShi(chengshi);
        if(!chengshi.isInit()) {
            this.allChengShis.put(Integer.valueOf(id), chengshi);
        }
        return id;
    }

    public List<WuJiangChengShiRela> findWuJiangChengShiRelasByChengShiInit(int chengshiId) {
        return this.dao.findWuJiangChengShiRelasByChengShiInit(chengshiId);
    }

    public List<WuJiang> findWuJiangsByChengShiInit(int chengshiId) {
        return this.dao.findWuJiangsByChengShiInit(chengshiId);
    }

    public List<WuJiang> findWuJiangsByChengShi(int chengshiId) {
        List<WuJiangChengShiRela> relas = new ArrayList();
        for(WuJiangChengShiRela rela : this.allWuJiangChengShiRelas.values()) {
            if(rela.getChengshiId() == chengshiId) {
                boolean added = false;
                for(int i = 0; i < relas.size(); i++) {
                    if(((WuJiangChengShiRela) relas.get(i)).getIndex() > rela.getIndex()) {
                        relas.add(i, rela);
                        added = true;
                        break;
                    }
                }
                if(!added) {
                    relas.add(rela);
                }
            }
        }
        List<WuJiang> ret = new ArrayList<WuJiang>();
        for(WuJiangChengShiRela rela : relas) {
            if(rela != null) {
                ret.add(this.allWuJiangs.get(rela.getWujiangId()));
            }
        }
        return ret;
    }

    public int deleteWuJiangChengShiRela(WuJiangChengShiRela rela) {
        if(!rela.isInit()) {
            this.allWuJiangChengShiRelas.remove(rela.getId());
        }
        return this.dao.deleteWuJiangChengShiRela(rela);
    }

    public int saveWuJiangChengShiRela(WuJiangChengShiRela rela) {
        rela.setUpdateTime(new Date());
        this.dao.saveWuJiangChengShiRela(rela);
        if(!rela.isInit()) {
            this.allWuJiangChengShiRelas.put(rela.getId(), rela);
        }
        return rela.getId();
    }

    public List<ShiLi> findAllShilisInit(int version) {
        return this.dao.findAllShilisInit(version);
    }

    public ShiLi getShiLiInit(int shiliId) {
        return this.dao.getShiLiInit(shiliId);
    }

    public int saveShili(ShiLi shili) {
        int id = this.dao.saveShiLi(shili);
        return id;
    }
}