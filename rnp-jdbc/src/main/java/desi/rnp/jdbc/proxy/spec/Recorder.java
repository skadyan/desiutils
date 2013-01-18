package desi.rnp.jdbc.proxy.spec;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Recorder {
	String name() default "";
	String value();
}
