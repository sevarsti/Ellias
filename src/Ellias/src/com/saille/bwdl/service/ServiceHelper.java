package com.saille.bwdl.service;

import org.springframework.context.ApplicationContext;
import servlet.GlobalContext;

public class ServiceHelper {
    public static BwdlService getBwdlService() {
        BwdlService service = (BwdlService) GlobalContext.getSpringContext().getBean("bwdlService", BwdlService.class);
        return service;
    }

    public static BwdlSettingService getSettingService() {
        BwdlSettingService settingService = (BwdlSettingService) GlobalContext.getSpringContext().getBean("bwdlSettingService", BwdlSettingService.class);
        return settingService;
    }
}