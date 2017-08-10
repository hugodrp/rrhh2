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
 *  py.gov.mca.sisrrhh.entitys.Horarios
 *  py.gov.mca.sisrrhh.sessionbeans.HorariosSB
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
import py.gov.mca.sisrrhh.entitys.Horarios;
import py.gov.mca.sisrrhh.sessionbeans.HorariosSB;

@ManagedBean(name = "horariosConverter")
@RequestScoped
public class HorariosConverter
        implements Converter {

    @EJB
    private HorariosSB ejbHorariosSB;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value != null && value.trim().length() > 0) {
                return this.ejbHorariosSB.findByHorario(value);
            }
            return null;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Error!", "Horario no v\u00e1lido");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(msg);
        }
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Horarios) value).getHorario();
        }
        return null;
    }
}
