/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.faces.FacesException
 *  javax.faces.application.FacesMessage
 *  javax.faces.application.FacesMessage$Severity
 *  javax.faces.application.ViewExpiredException
 *  javax.faces.context.ExceptionHandler
 *  javax.faces.context.ExceptionHandlerWrapper
 *  javax.faces.event.ExceptionQueuedEvent
 *  javax.faces.event.ExceptionQueuedEventContext
 *  org.primefaces.context.RequestContext
 */
package py.gov.mca.sisrrhh.utiles;

import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import org.primefaces.context.RequestContext;

public class CustomExceptionHandler
        extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public CustomExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void handle() throws FacesException {
        Iterator iterator = this.getUnhandledExceptionQueuedEvents().iterator();
        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable throwable = context.getException();
            try {
                String error = throwable.getMessage() + "(" + throwable.getClass().getName() + ")";
                if (throwable.getClass() == ViewExpiredException.class) {
                    error = "Su sesi\u00f3n a caducado, por favor vuelva a iniciar su sesi\u00f3n.";
                }
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Disculpe ", error);
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } finally {
                iterator.remove();
            }
        }
        this.getWrapped().handle();
    }
}
