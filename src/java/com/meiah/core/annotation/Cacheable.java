/** * A级 */
package com.meiah.core.annotation;

/**
 * 
 * @author zengxb<br>
 * @email: zengxianbin@gmail.com<br>
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cacheable {
}
