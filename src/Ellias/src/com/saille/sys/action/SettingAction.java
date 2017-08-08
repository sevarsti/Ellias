package com.saille.sys.action;

import servlet.AbstractDispatchAction;
import servlet.GlobalContext;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saille.sys.dao.SettingDao;
import com.saille.sys.Setting;
import com.saille.sys.form.SettingForm;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2015-1-24
 * Time: 18:30:51
 * To change this template use File | Settings | File Templates.
 */
public class SettingAction extends AbstractDispatchAction {
    private SettingDao settingDao;

    public SettingAction() {
        this.settingDao = (SettingDao) GlobalContext.getContextBean(SettingDao.class);
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        SettingForm form = (SettingForm) _form;
        List<Setting> settings = this.settingDao.findAll();
        form.setSettings(settings);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        SettingForm form = (SettingForm) _form;
        Setting setting = this.settingDao.get(form.getSettingName());
        if(setting != null) {
            form.setGroup(setting.getGroup());
            form.setName(setting.getName());
            form.setMemo(setting.getMemo());
            form.setType(setting.getType());
            form.setIntValue(setting.getIntValue());
            form.setNumberValue(setting.getNumberValue());
            form.setStrValue(setting.getStrValue());
            form.setDateValue(setting.getDateValue());
            form.setPattern(setting.getPattern());
        }
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        SettingForm form = (SettingForm) _form;
        String settingName = form.getSettingName();
        Setting setting = new Setting();
        setting.setSetting(settingName);
        setting.setGroup(form.getGroup());
        setting.setName(form.getName());
        setting.setMemo(form.getMemo());
        setting.setGroup(form.getGroup());
        setting.setType(form.getType());
        if(setting.getType() == Setting.TYPE_INT) {
            setting.setIntValue(Integer.parseInt(form.getSettingValue()));
        } else if(setting.getType() == Setting.TYPE_STRING) {
            setting.setStrValue(form.getSettingValue());
        } else if(setting.getType() == Setting.TYPE_NUMBER) {
            setting.setNumberValue(Double.parseDouble(form.getSettingValue()));
            setting.setPattern(form.getPattern());
        } else if(setting.getType() == Setting.TYPE_DATE) {
            try {
                setting.setDateValue(new SimpleDateFormat(form.getPattern()).parse(form.getSettingValue()));
                setting.setPattern(form.getPattern());
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        this.settingDao.save(setting);
        form.setMsg("±£´æ³É¹¦");
        return this.list(mapping, form, request, response);
    }
}
