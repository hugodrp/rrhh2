/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.faces.context.ExceptionHandler
 *  javax.faces.context.ExceptionHandlerFactory
 */
package py.gov.mca.sisrrhh.utiles;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;
import py.gov.mca.sisrrhh.utiles.CustomExceptionHandler;

public class CustomExceptionHandlerFactory
        extends ExceptionHandlerFactory {

    private final ExceptionHandlerFactory parent;

    public CustomExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    public ExceptionHandler getExceptionHandler() {
        CustomExceptionHandler result = new CustomExceptionHandler(this.parent.getExceptionHandler());
        return result;
    }
}
