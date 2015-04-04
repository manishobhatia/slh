/**
 * 
 */
package com.mob.rs.server.web.bind.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 * The Class BaseMarshallingProvider.
 * 
 * @param <T> the generic type
 * @author cupton
 */
public abstract class BaseMarshallingProvider<T> implements MessageBodyWriter<T>, MessageBodyReader<T>
{

    /** The Constant ENCODING. */
    public static final String ENCODING = "UTF-8";
    
    /*
     * (non-Javadoc)
     * @see javax.ws.rs.ext.MessageBodyReader#readFrom(java.lang.Class, java.lang.reflect.Type,
     * java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType, javax.ws.rs.core.MultivaluedMap,
     * java.io.InputStream)
     */
    @Override
    public abstract T readFrom(Class<T> clazzParm, Type typeParm, Annotation[] annotationsParm,
            MediaType mediaTypeParm, MultivaluedMap<String, String> multivaluedMapParm, InputStream inputStreamParm)
            throws IOException, WebApplicationException;

    /*
     * (non-Javadoc)
     * @see javax.ws.rs.ext.MessageBodyWriter#writeTo(java.lang.Object, java.lang.Class, java.lang.reflect.Type,
     * java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType, javax.ws.rs.core.MultivaluedMap,
     * java.io.OutputStream)
     */
    @Override
    public abstract void writeTo(T valueParm, Class<?> clazzParm, Type typeParm, Annotation[] annotationsParm,
            MediaType mediaTypeParm, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException, WebApplicationException;

    /*
     * (non-Javadoc)
     * @see javax.ws.rs.ext.MessageBodyReader#isReadable(java.lang.Class, java.lang.reflect.Type,
     * java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType)
     */
    @Override
    public boolean isReadable(Class<?> clazzParm, Type typeParm, Annotation[] annotationsParm, MediaType mediaTypeParm)
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see javax.ws.rs.ext.MessageBodyWriter#getSize(java.lang.Object, java.lang.Class, java.lang.reflect.Type,
     * java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType)
     */
    @Override
    public long getSize(T valueParm, Class<?> clazzParm, Type typeParm, Annotation[] annotationsParm,
            MediaType mediaTypeParm)
    {
        return -1;
    }

    /*
     * (non-Javadoc)
     * @see javax.ws.rs.ext.MessageBodyWriter#isWriteable(java.lang.Class, java.lang.reflect.Type,
     * java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType)
     */
    @Override
    public boolean isWriteable(Class<?> clazzParm, Type typeParm, Annotation[] annotationsParm, MediaType mediaTypeParm)
    {
        return true;
    }

}
