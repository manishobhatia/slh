/**
 * 
 */
package com.mob.rs.server.web.bind.provider;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mob.rs.server.core.MediaTypeExtended;
import com.mob.rs.server.web.bind.IJsonMarshalable;
import com.mob.rs.server.web.bind.builder.json.GSON;


/**
 * The Class GsonMarshallingProvider. This class is responsible for marshalling objects to JSON using the third part
 * JSON provider GSON.
 * 
 * @author cupton
 */
@Provider
@Component("JsonMarshallingProvider")
@Produces( { MediaTypeExtended.APPLICATION_JSON_UTF_8, MediaTypeExtended.TEXT_PLAIN_UTF_8 })
@Consumes( { MediaTypeExtended.APPLICATION_JSON_UTF_8, MediaTypeExtended.TEXT_PLAIN_UTF_8 })
public class JsonMarshallingProvider extends BaseMarshallingProvider<Object> 
{
	
	private static final String CHARSET = "UTF-8";
//	private Gson gson;



    /*
     * (non-Javadoc)
     * @see javax.ws.rs.ext.MessageBodyWriter#writeTo(java.lang.Object, java.lang.Class, java.lang.reflect.Type,
     * java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType, javax.ws.rs.core.MultivaluedMap,
     * java.io.OutputStream)
     */ 
    /**
     * @throws IOException
     */
    @Override
    public void writeTo(Object valueParm, Class<?> clazzParm, Type typeParm, Annotation[] annotationsParm,
            MediaType mediaTypeParm, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException, WebApplicationException
    {
        if (valueParm != null)
        {
            OutputStreamWriter writer = new OutputStreamWriter(entityStream, ENCODING);
            try
            {
                if(valueParm instanceof IJsonMarshalable) {
                    GSON.getGsonInstance().toJson(valueParm, IJsonMarshalable.class, writer);
                } else {
                    GSON.getGsonInstance().toJson(valueParm, clazzParm, writer);
                }
            }
            catch (Throwable exception)
            {
                new WebApplicationException(exception, Response.Status.BAD_REQUEST);
            } finally {
                writer.close();
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see javax.ws.rs.ext.MessageBodyReader#readFrom(java.lang.Class, java.lang.reflect.Type,
     * java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType, javax.ws.rs.core.MultivaluedMap,
     * java.io.InputStream)
     */
    /**
     * @throws IOException
     */
    @Override
    public Object readFrom(Class<Object> clazzParm, Type typeParm, Annotation[] annotationsParm,
            MediaType mediaTypeParm, MultivaluedMap<String, String> multivaluedMapParm, InputStream inputStreamParm)
            throws IOException, WebApplicationException
    {
        final InputStreamReader streamReader = new InputStreamReader(inputStreamParm, CHARSET);
        try {
            final Type jsonType;
            if (clazzParm.equals(typeParm)) {
                jsonType = clazzParm;
            } else {
                jsonType = typeParm;
            }

            return  GSON.getGsonInstance().fromJson(streamReader, jsonType);
        } finally {
            streamReader.close();
        }

    }
 /*   
    private Gson getGson() {
        if (gson == null) {
            final GsonBuilder gsonBuilder = new GsonBuilder();

            // additional step: configure gson builder if needed
            // the configuration info is fetched from the particular instance of ContextResolver<GsonAware> class.
      //      final ContextResolver<GsonAware> configContextResolver = providers.getContextResolver(GsonAware.class, null);
      //      if (configContextResolver != null) {
      //          final GsonAware gsonAware = configContextResolver.getContext(GsonAware.class);
      //          if (gsonAware != null) {
      //             gsonAware.setGsonBuilder(gsonBuilder);
      //          }
      //      }

            gson = gsonBuilder.create();
        }

        return gson;
    }
*/

}
