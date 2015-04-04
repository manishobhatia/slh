/**
 * 
 */
package com.mob.rs.server.core;
import javax.ws.rs.core.MediaType;


/**
 * The Class MediaTypeExtended.
 *
 * @author cupton
 */
public class MediaTypeExtended extends MediaType
{

    public static final String UTF_8_EXTENTION = ";charset=UTF-8";
    
    /** The Constant APPLICATION_JSON_UTF_8. */
    public static final String APPLICATION_JSON_UTF_8 =  MediaType.APPLICATION_JSON + UTF_8_EXTENTION;
    
    /** The Constant TEXT_PLAIN_UTF_8. */
    public static final String TEXT_PLAIN_UTF_8 =  MediaType.TEXT_PLAIN + UTF_8_EXTENTION;
    
    /** The Constant TEXT_HTML_UTF_8. */
    public static final String TEXT_HTML_UTF_8 =  MediaType.TEXT_HTML + UTF_8_EXTENTION;
    
    /** The Constant IMAGE_PNG. */
    public static final String IMAGE_PNG =  "image/png";
    
    /** The Constant IMAGE_PNG_TYPE. */
    public static final MediaType IMAGE_PNG_TYPE = new MediaType("image","png");

    /** The Constant IMAGE_GIF. */
    public static final String IMAGE_GIF =  "image/gif";
    
    /** The Constant IMAGE_GIF_TYPE. */
    public static final MediaType IMAGE_GIF_TYPE = new MediaType("image","gif");
    

}
