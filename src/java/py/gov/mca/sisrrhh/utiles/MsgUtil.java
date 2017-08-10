/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.faces.application.FacesMessage
 *  javax.faces.application.FacesMessage$Severity
 *  javax.faces.context.FacesContext
 */
package py.gov.mca.sisrrhh.utiles;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MsgUtil {

    private static FacesMessage msg;

    public static void msg(String resumen, String detalle, String tipo) {
        switch (tipo) {
            case "INFO": {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, resumen, detalle);
                break;
            }
            case "WARNING": {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, resumen, detalle);
                break;
            }
            case "ERROR": {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, resumen, detalle);
                break;
            }
            default: {
                msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, resumen, detalle);
            }
        }
        MsgUtil.msgFacesMessage();
    }

    private static void msgFacesMessage() {
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
