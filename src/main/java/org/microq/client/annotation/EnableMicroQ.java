package org.microq.client.annotation;

import org.microq.client.MicroQueueClientApplication;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MicroQueueClientApplication.class)
public @interface EnableMicroQ {
}
