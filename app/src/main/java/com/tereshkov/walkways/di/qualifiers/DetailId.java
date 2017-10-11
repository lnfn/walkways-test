package com.tereshkov.walkways.di.qualifiers;

/**
 * Created by Tereshkov on 09.10.2017.
 */

import java.lang.annotation.Documented;

import javax.inject.Qualifier;

import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;

@Qualifier
@Documented
@Retention(AnnotationRetention.RUNTIME)
public @interface DetailId {
}
