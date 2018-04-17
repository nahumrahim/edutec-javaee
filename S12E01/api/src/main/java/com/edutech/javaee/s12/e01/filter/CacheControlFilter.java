package com.edutech.javaee.s12.e01.filter;

import com.edutech.javaee.s12.e01.annotation.CacheControl;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 *
 * @author nahum
 */

//@Provider
public class CacheControlFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext pRequestContext, ContainerResponseContext pResponseContext)
            throws IOException {
        
        for (Annotation a : pResponseContext.getEntityAnnotations()) {
            System.out.println(a.annotationType());
            if (a.annotationType() == CacheControl.class) {
                String value = ((CacheControl) a).value();
                pResponseContext.getHeaders().putSingle(HttpHeaders.CACHE_CONTROL, value);
                break;
            }
        }
        
        pResponseContext.getHeaders().putSingle("X-UA-Compatible", "IE=edge");
    }

}