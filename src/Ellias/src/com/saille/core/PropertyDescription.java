package com.saille.core;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2014-1-19
 * Time: 16:44:36
 * To change this template use File | Settings | File Templates.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyDescription {
	/**
	 * √Ë ˆ
	 * @return
	 */
	String desc() default "";
}
