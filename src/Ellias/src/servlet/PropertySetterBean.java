package servlet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

public class PropertySetterBean implements InitializingBean {
    private static final Logger LOGGER = Logger.getLogger(PropertySetterBean.class);
    private boolean called;
    private static List CONVERTERABLE_ClASSES = new ArrayList();
    protected String targetClass;
    protected Map valueMap;
    protected Map methods;

    public PropertySetterBean() {
        this.called = false;

        this.methods = new HashMap();
    }

    public void setValues() {
        if(this.called) {
            return;
        }
        this.called = true;

        Class targetClass = null;
        try {
            targetClass = Class.forName(this.targetClass);
        } catch(ClassNotFoundException ex) {
            LOGGER.error("Cannot find class[" + this.targetClass + "]");
            return;
        }

        getMethods(targetClass);

        Iterator iterator = this.valueMap.keySet().iterator();
        while(iterator.hasNext()) {
            String propertyName = iterator.next().toString();
            Method method = getMethodByPropertyName(propertyName);

            if(method == null) {
                continue;
            }

            try {
                Object value = this.valueMap.get(propertyName);
                if((value.getClass().equals(String.class)) && (CONVERTERABLE_ClASSES.contains(method.getParameterTypes()[0]))) {
                    value = ConvertUtils.convert((String) value, method.getParameterTypes()[0]);
                }

                method.invoke(null, new Object[]{value});
            } catch(Exception ex) {
                LOGGER.error("Property[" + propertyName + "] of Class[" + targetClass + "] setter failed because of exception", ex);
            }
        }
    }

    protected String getSetterName(String propertyName) {
        if(propertyName.length() > 0) {
            String startChar = propertyName.substring(0, 1);
            startChar = startChar.toUpperCase();
            return "set" + startChar + propertyName.substring(1);
        }

        return propertyName;
    }

    protected void getMethods(Class cls) {
        Method[] ms = cls.getMethods();
        for(int i = 0; i < ms.length; i++) {
            Method m = ms[i];
            if(m.getParameterTypes().length != 1) {
                continue;
            }
            this.methods.put(m.getName(), m);
        }
    }

    protected Method getMethodByPropertyName(String propName) {
        Method m = (Method) this.methods.get(getSetterName(propName));
        if(m == null) {
            LOGGER.error("Cannot find set method for property[" + propName + "]");
        }

        return m;
    }

    public void setValueMap(Map valueMap) {
        this.valueMap = valueMap;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public Map getValueMap() {
        return this.valueMap;
    }

    public String getTargetClass() {
        return this.targetClass;
    }

    public void afterPropertiesSet() throws Exception {
        setValues();
    }

    static {
        CONVERTERABLE_ClASSES.add(Integer.class);
        CONVERTERABLE_ClASSES.add(Long.class);
        CONVERTERABLE_ClASSES.add(Double.class);
        CONVERTERABLE_ClASSES.add(Float.class);
        CONVERTERABLE_ClASSES.add(Short.class);
        CONVERTERABLE_ClASSES.add(Boolean.class);

        CONVERTERABLE_ClASSES.add(Integer.TYPE);
        CONVERTERABLE_ClASSES.add(Long.TYPE);
        CONVERTERABLE_ClASSES.add(Double.TYPE);
        CONVERTERABLE_ClASSES.add(Float.TYPE);
        CONVERTERABLE_ClASSES.add(Short.TYPE);
        CONVERTERABLE_ClASSES.add(Boolean.TYPE);
    }

    protected static class MethodComparable implements Comparator {
        public boolean equals(Object object) {
            if(object == null) {
                return false;
            }
            return getClass().equals(object.getClass());
        }

        public int compare(Object object, Object object1) {
            Method m = (Method) object;
            Method m1 = (Method) object1;
            return m.getName().compareTo(m1.getName());
        }
    }
}