package com.hooverz.privilege;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解
 * 
 * 不使用此注解表示任何人动能操作
 * 
 * @PrivilegeAnno("")或@PrivilegeAnno或@PrivilegeAnno()表示只有登录了才能操作
 * 
 * @PrivilegeAnno({"manager", "admin"}) 表示要同时拥有admin和manager的角色的用户才能操作
 * 
 * @author love5
 * 
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PrivilegeAnno {

	// 所有指定的权限
	String[] value() default "";
}
