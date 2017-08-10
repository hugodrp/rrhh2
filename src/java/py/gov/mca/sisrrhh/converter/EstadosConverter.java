/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.EJB
 *  javax.faces.application.FacesMessage
 *  javax.faces.application.FacesMessage$Severity
 *  javax.faces.bean.ManagedBean
 *  javax.faces.bean.RequestScoped
 *  javax.faces.component.UIComponent
 *  javax.faces.context.FacesContext
 *  javax.faces.convert.Converter
 *  javax.faces.convert.ConverterException
 *  py.gov.mca.sisrrhh.entitys.Estados
 *  py.gov.mca.sisrrhh.sessionbeans.EstadosSB
 */
package py.gov.mca.sisrrhh.converter;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import py.gov.mca.sisrrhh.entitys.Estados;
import py.gov.mca.sisrrhh.sessionbeans.EstadosSB;

@ManagedBean(name = "estadosConverter")
@RequestScoped
public class EstadosConverter
        implements Converter {

    @EJB
    private EstadosSB ejbEstadosSB;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value != null && value.trim().length() > 0) {
                return this.ejbEstadosSB.findByCodigo(value);
            }
            return null;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Error!", "Estado no v\u00e1lido");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(msg);
        }
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Estados) value).getCodigo();
        }
        return null;
    }
}
