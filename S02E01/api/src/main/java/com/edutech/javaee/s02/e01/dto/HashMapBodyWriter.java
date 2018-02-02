package com.edutech.javaee.s02.e01.dto;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author nahum
 */
@Provider
@Produces("application/json")
public class HashMapBodyWriter implements MessageBodyWriter<HashMap> {

    @Override
    public boolean isWriteable(Class<?> type, java.lang.reflect.Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == HashMap.class;
    }

    @Override
    public long getSize(HashMap t, Class<?> type, java.lang.reflect.Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(HashMap t, Class<?> type, java.lang.reflect.Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        //Writer writer = new PrintWriter(entityStream);
        PrintWriter writer = new PrintWriter(entityStream);
        Iterator it = t.entrySet().iterator();
        StringBuilder str = new StringBuilder();
        str.append("{");
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());            
            str.append("\"")
                    .append(pair.getKey())
                    .append("\":" + "\"")
                    .append(pair.getValue().toString())
                    .append("\"");
            if (it.hasNext())
                str.append(",");
            it.remove(); // avoids a ConcurrentModificationException
        }
        str.append("}");
        System.out.println(str.toString());
        writer.write(str.toString());
        writer.flush();
        writer.close();    
    }

}