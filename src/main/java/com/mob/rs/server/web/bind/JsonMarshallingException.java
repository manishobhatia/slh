/**
 * 
 */
package com.mob.rs.server.web.bind;

/**
 * The Class JsonMarshallingException.
 *
 * @author cupton
 */
@SuppressWarnings("serial")
public class JsonMarshallingException extends Exception
{

    /**
     * Instantiates a new json marshalling exception.
     */
    public JsonMarshallingException()
    {
        super();
    }

    /**
     * Instantiates a new json marshalling exception.
     *
     * @param arg0Parm the arg0 parm
     * @param arg1Parm the arg1 parm
     */
    public JsonMarshallingException(String arg0Parm, Throwable arg1Parm)
    {
        super(arg0Parm, arg1Parm);
    }

    /**
     * Instantiates a new json marshalling exception.
     *
     * @param arg0Parm the arg0 parm
     */
    public JsonMarshallingException(String arg0Parm)
    {
        super(arg0Parm);
    }

    /**
     * Instantiates a new json marshalling exception.
     *
     * @param arg0Parm the arg0 parm
     */
    public JsonMarshallingException(Throwable arg0Parm)
    {
        super(arg0Parm);
    }

}
